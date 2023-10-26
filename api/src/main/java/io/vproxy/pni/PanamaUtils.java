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
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@SuppressWarnings("rawtypes")
public class PanamaUtils {
    private PanamaUtils() {
    }

    private static boolean libpniLoaded = false;

    public static void loadLib() {
        if (libpniLoaded) {
            return;
        }
        synchronized (PanamaUtils.class) {
            if (libpniLoaded) {
                return;
            }

            // check whether it's already loaded (pni.c might have already been compiled into the user library)
            try {
                lookupPNICriticalFunction(new PNILinkOptions().setCritical(true),
                    void.class, "SetPNIRefReleaseFunc", MemorySegment.class);
            } catch (Throwable t) {
                // it's not loaded yet, do loading ...
                System.loadLibrary("pni");
            }
            libpniLoaded = true;
        }
    }

    private static boolean nativeLookupSupported = true;

    private static SymbolLookup linkerDefaultLookup(Linker linker) {
        if (!nativeLookupSupported) {
            return null;
        }
        try {
            return linker.defaultLookup();
        } catch (Throwable t) {
            nativeLookupSupported = false;
            System.out.println("[PNI][WARN][PanamaUtils#linkerDefaultLookup] " + linker + "#defaultLookup() is not supported: " + t);
            return null;
        }
    }

    public static Optional<MemorySegment> lookupFunctionPointer(@SuppressWarnings("unused") PNILookupOptions opts, String functionName) {
        var nativeLinker = Linker.nativeLinker();
        var loaderLookup = SymbolLookup.loaderLookup();
        var stdlibLookup = linkerDefaultLookup(nativeLinker);
        //noinspection UnnecessaryLocalVariable
        var p = loaderLookup.find(functionName)
            .or(() -> {
                if (stdlibLookup != null)
                    return stdlibLookup.find(functionName);
                return Optional.empty();
            });
        return p;
    }

    public static MethodHandle lookupPNIFunction(PNILinkOptions opts, String functionName, Class... parameterTypes) {
        var nativeLinker = Linker.nativeLinker();
        var h = lookupFunctionPointer(opts, functionName)
            .map(m -> {
                if (opts.isCritical()) {
                    return nativeLinker.downcallHandle(m, buildFunctionDescriptor(parameterTypes), PanamaHack.getCriticalOption());
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

    public static MethodHandle lookupPNICriticalFunction(PNILinkOptions opts, Class returnType, String functionName, Class... parameterTypes) {
        var nativeLinker = Linker.nativeLinker();
        var h = lookupFunctionPointer(opts, functionName)
            .map(m -> {
                if (opts.isCritical()) {
                    return nativeLinker.downcallHandle(m, buildCriticalFunctionDescriptor(returnType, parameterTypes), PanamaHack.getCriticalOption());
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

    public static FunctionDescriptor buildFunctionDescriptor(Class... parameterTypes) {
        MemoryLayout[] layouts = new MemoryLayout[parameterTypes.length + 1];
        layouts[0] = ValueLayout.ADDRESS; // JEnv*
        for (int i = 0; i < parameterTypes.length; ++i) {
            layouts[i + 1] = buildParameterMemoryLayout(parameterTypes[i]);
        }
        return FunctionDescriptor.of(ValueLayout.JAVA_INT, layouts);
    }

    public static FunctionDescriptor buildCriticalFunctionDescriptor(Class returnType, Class... parameterTypes) {
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
        } else if (type == PNIFunc.class) {
            return ValueLayout.ADDRESS; // PNIFunc*
        } else if (type == PNIRef.class) {
            return ValueLayout.ADDRESS; // PNIRef*
        } else if (type == CallSite.class) {
            return ValueLayout.ADDRESS; // PNIFunc*
        } else if (MemoryLayout.class.isAssignableFrom(type)) {
            return ValueLayout.ADDRESS; // Type*
        } else {
            throw new IllegalArgumentException("unsupported type, unable to convert to MemoryLayout: " + type);
        }
    }

    public static MemoryLayout padLayout(long minByteSize, Function<MemoryLayout[], MemoryLayout> builder, MemoryLayout... layouts) {
        var layout = builder.apply(layouts);
        if (layout.byteSize() >= minByteSize) {
            return layout;
        }
        long pad;
        if (layout instanceof UnionLayout) {
            pad = minByteSize;
        } else {
            pad = minByteSize - layout.byteSize();
        }
        var padLayout = MemoryLayout.sequenceLayout(pad, ValueLayout.JAVA_BYTE);

        MemoryLayout[] newArray = new MemoryLayout[layouts.length + 1];
        System.arraycopy(layouts, 0, newArray, 0, layouts.length);

        newArray[layouts.length] = padLayout;
        return builder.apply(newArray);
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

    public static void nativeObjectToString(NativeObject o, StringBuilder sb, int indent, Set<NativeObjectTuple> visited, boolean corrupted) {
        if (o == null) {
            sb.append("null");
            return;
        }
        o.toString(sb, indent, visited, corrupted);
    }

    public static String charToASCIIString(char c) {
        if (c < 33 || c > 126) {
            return "(" + (int) c + ")";
        } else {
            return "" + c;
        }
    }

    public static String memorySegmentToString(MemorySegment seg) {
        if (seg == null)
            return "null";
        var sb = new StringBuilder();
        sb.append("[");
        for (long i = 0, len = seg.byteSize(); i < len; ++i) {
            if (i != 0) {
                sb.append(" ");
            }
            byte b = seg.get(ValueLayout.JAVA_BYTE, i);
            int n = b & 0xff;
            var hex = Integer.toHexString(n);
            if (hex.length() == 1) {
                hex = "0" + hex;
            }
            sb.append(hex);
        }
        sb.append("]@").append(Long.toString(seg.address(), 16));
        return sb.toString();
    }

    public static String byteBufferToString(ByteBuffer buf) {
        if (buf == null)
            return "null";
        var sb = new StringBuilder();
        sb.append("[");
        for (int i = buf.position(), lim = buf.limit(); i < lim; ++i) {
            if (i != 0) {
                sb.append(" ");
            }
            byte b = buf.get(i);
            int n = b & 0xff;
            var hex = Integer.toHexString(n);
            if (hex.length() == 1) {
                hex = "0" + hex;
            }
            sb.append(hex);
        }
        sb.append("]");
        if (buf.isDirect()) {
            sb.append("@");
            sb.append(Long.toString(MemorySegment.ofBuffer(buf).address(), 16));
        }
        return sb.toString();
    }

    public static PNIException convertInvokeExactException(Throwable t) {
        throw new PNIException("invokeExact throws exception", t);
    }

    public static MemorySegment defineCFunctionByName(PNILinkOptions opts, Arena arena, Class<?> declaringClass, String name) {
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
        return defineCFunction(opts, arena, candidate);
    }

    public static MemorySegment defineCFunction(PNILinkOptions opts, Arena arena, Class<?> declaringClass, String name, Class<?>... paramTypes) {
        Method method;
        try {
            method = declaringClass.getDeclaredMethod(name, paramTypes);
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException(e);
        }
        return defineCFunction(opts, arena, method);
    }

    public static MemorySegment defineCFunction(PNILinkOptions opts, Arena arena, Method method) {
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
        return defineCFunction(opts, arena, methodHandle, method.getReturnType(), method.getParameterTypes());
    }

    public static MemorySegment defineCFunction(@SuppressWarnings("unused") PNILinkOptions opts,
                                                Arena arena, MethodHandle methodHandle, Class<?> returnType, Class<?>... paramTypes) {
        return Linker.nativeLinker().upcallStub(
            methodHandle,
            buildCriticalFunctionDescriptor(returnType, paramTypes),
            arena
        );
    }
}
