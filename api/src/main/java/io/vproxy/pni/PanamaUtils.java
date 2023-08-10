package io.vproxy.pni;

import io.vproxy.pni.exception.PNIException;

import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.stream.Collectors;

@SuppressWarnings("rawtypes")
public class PanamaUtils {
    private PanamaUtils() {
    }

    public static MethodHandle lookupPNIFunction(String functionName, Class... parameterTypes) {
        return lookupPNIFunction(true, functionName, parameterTypes);
    }

    public static MethodHandle lookupPNIFunction(boolean isTrivial, String functionName, Class... parameterTypes) {
        var nativeLinker = Linker.nativeLinker();
        var loaderLookup = SymbolLookup.loaderLookup();
        var stdlibLookup = nativeLinker.defaultLookup();
        var h = loaderLookup.find(functionName)
            .or(() -> stdlibLookup.find(functionName))
            .map(m -> {
                if (isTrivial) {
                    return nativeLinker.downcallHandle(m, buildFunctionDescriptor(parameterTypes), Linker.Option.isTrivial());
                } else {
                    return nativeLinker.downcallHandle(m, buildFunctionDescriptor(parameterTypes));
                }
            })
            .orElse(null);
        if (h == null) {
            throw new UnsatisfiedLinkError(functionName + Arrays.stream(parameterTypes).map(Class::getSimpleName).collect(Collectors.joining(", ", "(", ")")));
        }
        return h;
    }

    public static MethodHandle lookupPNICriticalFunction(Class returnType, String functionName, Class... parameterTypes) {
        return lookupPNICriticalFunction(true, returnType, functionName, parameterTypes);
    }

    public static MethodHandle lookupPNICriticalFunction(boolean isTrivial, Class returnType, String functionName, Class... parameterTypes) {
        var nativeLinker = Linker.nativeLinker();
        var loaderLookup = SymbolLookup.loaderLookup();
        var stdlibLookup = nativeLinker.defaultLookup();
        var h = loaderLookup.find(functionName)
            .or(() -> stdlibLookup.find(functionName))
            .map(m -> {
                if (isTrivial) {
                    return nativeLinker.downcallHandle(m, buildCriticalFunctionDescriptor(returnType, parameterTypes), Linker.Option.isTrivial());
                } else {
                    return nativeLinker.downcallHandle(m, buildCriticalFunctionDescriptor(returnType, parameterTypes));
                }
            })
            .orElse(null);
        if (h == null) {
            throw new UnsatisfiedLinkError(functionName + Arrays.stream(parameterTypes).map(Class::getSimpleName).collect(Collectors.joining(", ", "(", ")")));
        }
        return h;
    }

    private static FunctionDescriptor buildFunctionDescriptor(Class[] parameterTypes) {
        MemoryLayout[] layouts = new MemoryLayout[parameterTypes.length + 1];
        layouts[0] = ValueLayout.ADDRESS; // JEnv*
        for (int i = 0; i < parameterTypes.length; ++i) {
            layouts[i + 1] = buildParameterMemoryLayout(parameterTypes[i]);
        }
        return FunctionDescriptor.of(ValueLayout.JAVA_INT, layouts);
    }

    private static FunctionDescriptor buildCriticalFunctionDescriptor(Class returnType, Class[] parameterTypes) {
        MemoryLayout[] layouts = new MemoryLayout[parameterTypes.length];
        for (int i = 0; i < parameterTypes.length; ++i) {
            layouts[i] = buildParameterMemoryLayout(parameterTypes[i]);
        }
        if (returnType == void.class) {
            return FunctionDescriptor.ofVoid(layouts);
        } else {
            return FunctionDescriptor.of(buildParameterMemoryLayout(returnType), layouts);
        }
    }

    private static MemoryLayout buildParameterMemoryLayout(Class type) {
        if (type == byte.class) {
            return ValueLayout.JAVA_BYTE;
        } else if (type == char.class) {
            return ValueLayout.JAVA_CHAR;
        } else if (type == float.class) {
            return ValueLayout.JAVA_FLOAT;
        } else if (type == double.class) {
            return ValueLayout.JAVA_DOUBLE;
        } else if (type == int.class) {
            return ValueLayout.JAVA_INT;
        } else if (type == long.class) {
            return ValueLayout.JAVA_LONG;
        } else if (type == short.class) {
            return ValueLayout.JAVA_SHORT;
        } else if (type == boolean.class) {
            return ValueLayout.JAVA_BOOLEAN;
        } else if (type == String.class) {
            return ValueLayout.ADDRESS; // char*
        } else if (type == MemorySegment.class) {
            return ValueLayout.ADDRESS; // void*
        } else if (type == ByteBuffer.class) {
            return ValueLayout.ADDRESS; // void*
        } else if (type == PNIBuf.class) {
            return ValueLayout.ADDRESS; // PNIBuf*
        } else if (type == CallSite.class) {
            return ValueLayout.ADDRESS; // PNIFunc*
        } else if (MemoryLayout.class.isAssignableFrom(type)) {
            return ValueLayout.ADDRESS; // Type*
        } else {
            throw new IllegalArgumentException("unsupported type, unable to convert to MemoryLayout: " + type);
        }
    }

    public static MemorySegment format(ByteBuffer arg, Allocator allocator) {
        if (arg == null) {
            return MemorySegment.NULL;
        }
        var buf = new PNIBuf(allocator);
        buf.set(arg);
        return buf.MEMORY;
    }

    public static MemorySegment format(ByteBuffer arg) {
        if (arg == null) {
            return MemorySegment.NULL;
        }
        var pos = arg.position();
        var seg = MemorySegment.ofBuffer(arg);
        return MemorySegment.ofAddress(seg.address() - pos).reinterpret(arg.capacity());
    }

    public static PNIException convertInvokeExactException(Throwable t) {
        throw new PNIException("invokeExact throws exception", t);
    }

    public static MemorySegment defineCFunctionByName(Arena arena, Class<?> declaringClass, String name) {
        var methods = declaringClass.getDeclaredMethods();
        Method candidate = null;
        for (Method m : methods) {
            if (m.getName().equals(name)) {
                if (candidate == null) {
                    candidate = m;
                } else {
                    throw new IllegalArgumentException("more than one method in " + declaringClass + " has name " + name);
                }
            }
        }
        if (candidate == null) {
            throw new IllegalArgumentException(new NoSuchMethodException(declaringClass + "#" + name));
        }
        return defineCFunction(arena, candidate);
    }

    public static MemorySegment defineCFunction(Arena arena, Class<?> declaringClass, String name, Class<?>... paramTypes) {
        Method method;
        try {
            method = declaringClass.getDeclaredMethod(name, paramTypes);
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException(e);
        }
        return defineCFunction(arena, method);
    }

    public static MemorySegment defineCFunction(Arena arena, Method method) {
        var access = method.getModifiers();
        if ((access & Modifier.STATIC) != Modifier.STATIC) {
            throw new IllegalArgumentException("method " + method + " is not static and cannot be used to define a C function");
        }
        if (!method.canAccess(null)) {
            throw new IllegalArgumentException("method " + method + " is not accessible from " + PanamaUtils.class.getName());
        }
        // use do-while to implement goto
        //noinspection ConstantValue,LoopStatementThatDoesntLoop
        do {
            var returnType = method.getReturnType();
            if (returnType.isPrimitive()) {
                break;
            }
            if (returnType == MemorySegment.class) {
                break;
            }
            throw new IllegalArgumentException(returnType + " is not allowed for building a C function");
        } while (false);
        for (var p : method.getParameterTypes()) {
            if (p.isPrimitive()) {
                continue;
            }
            if (p == MemorySegment.class) {
                continue;
            }
            throw new IllegalArgumentException(p + " is not allowed for building a C function");
        }
        MethodHandle methodHandle;
        try {
            methodHandle = MethodHandles.lookup()
                .findStatic(method.getDeclaringClass(), method.getName(),
                    MethodType.methodType(method.getReturnType(), method.getParameterTypes()));
        } catch (Throwable t) {
            throw new IllegalArgumentException(t);
        }
        return Linker.nativeLinker().upcallStub(
            methodHandle,
            buildCriticalFunctionDescriptor(method.getReturnType(), method.getParameterTypes()),
            arena
        );
    }
}
