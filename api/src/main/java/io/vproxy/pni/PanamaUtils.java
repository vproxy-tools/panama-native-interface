package io.vproxy.pni;

import io.vproxy.pni.exception.PNIException;

import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;
import java.lang.reflect.InvocationTargetException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
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

    private static FunctionDescriptor buildFunctionDescriptor(Class[] parameterTypes) {
        MemoryLayout[] layouts = new MemoryLayout[parameterTypes.length + 1];
        layouts[0] = ValueLayout.ADDRESS; // JEnv*
        for (int i = 0; i < parameterTypes.length; ++i) {
            layouts[i + 1] = buildParameterMemoryLayout(parameterTypes[i]);
        }
        return FunctionDescriptor.of(ValueLayout.JAVA_INT, layouts);
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

    public static MemorySegment format(String arg, Arena arena) {
        if (arg == null) {
            return MemorySegment.NULL;
        }
        var bytes = arg.getBytes(StandardCharsets.UTF_8);
        var seg = arena.allocate(bytes.length + 1);
        seg.setUtf8String(0, arg);
        return seg;
    }

    public static MemorySegment format(String arg, Allocator allocator) {
        if (arg == null) {
            return MemorySegment.NULL;
        }
        var bytes = arg.getBytes(StandardCharsets.UTF_8);
        var seg = allocator.allocate(bytes.length + 1);
        seg.setUtf8String(0, arg);
        return seg;
    }

    public static MemorySegment format(ByteBuffer arg, Arena arena) {
        if (arg == null) {
            return MemorySegment.NULL;
        }
        var buf = new PNIBuf(arena);
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
}
