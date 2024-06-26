package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.hack.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;
import io.vproxy.pni.graal.*;
import org.graalvm.nativeimage.*;
import org.graalvm.nativeimage.c.function.*;
import org.graalvm.nativeimage.c.type.VoidPointer;
import org.graalvm.word.WordFactory;

public class RawArrays {
    private RawArrays() {
    }

    private static final RawArrays INSTANCE = new RawArrays();

    public static RawArrays get() {
        return INSTANCE;
    }

    private static final MethodHandle byteArrayMH = PanamaUtils.lookupPNIFunction(new PNILinkOptions(), "Java_io_vproxy_pni_test_RawArrays_byteArray", MemorySegment.class /* array */, int.class /* off */);

    public byte byteArray(PNIEnv ENV, MemorySegment array, int off) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) byteArrayMH.invokeExact(ENV.MEMORY, (MemorySegment) (array == null ? MemorySegment.NULL : array), off);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnByte();
    }

    private static final MethodHandle unsignedByteArrayMH = PanamaUtils.lookupPNIFunction(new PNILinkOptions(), "Java_io_vproxy_pni_test_RawArrays_unsignedByteArray", MemorySegment.class /* array */, int.class /* off */);

    public byte unsignedByteArray(PNIEnv ENV, MemorySegment array, int off) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) unsignedByteArrayMH.invokeExact(ENV.MEMORY, (MemorySegment) (array == null ? MemorySegment.NULL : array), off);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnByte();
    }

    private static final MethodHandle boolArrayMH = PanamaUtils.lookupPNIFunction(new PNILinkOptions(), "Java_io_vproxy_pni_test_RawArrays_boolArray", MemorySegment.class /* array */, int.class /* off */);

    public boolean boolArray(PNIEnv ENV, BoolArray array, int off) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) boolArrayMH.invokeExact(ENV.MEMORY, (MemorySegment) (array == null ? MemorySegment.NULL : array.MEMORY), off);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnBool();
    }

    private static final MethodHandle charArrayMH = PanamaUtils.lookupPNIFunction(new PNILinkOptions(), "Java_io_vproxy_pni_test_RawArrays_charArray", MemorySegment.class /* array */, int.class /* off */);

    public char charArray(PNIEnv ENV, CharArray array, int off) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) charArrayMH.invokeExact(ENV.MEMORY, (MemorySegment) (array == null ? MemorySegment.NULL : array.MEMORY), off);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnChar();
    }

    private static final MethodHandle doubleArrayMH = PanamaUtils.lookupPNIFunction(new PNILinkOptions(), "Java_io_vproxy_pni_test_RawArrays_doubleArray", MemorySegment.class /* array */, int.class /* off */);

    public double doubleArray(PNIEnv ENV, DoubleArray array, int off) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) doubleArrayMH.invokeExact(ENV.MEMORY, (MemorySegment) (array == null ? MemorySegment.NULL : array.MEMORY), off);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnDouble();
    }

    private static final MethodHandle floatArrayMH = PanamaUtils.lookupPNIFunction(new PNILinkOptions(), "Java_io_vproxy_pni_test_RawArrays_floatArray", MemorySegment.class /* array */, int.class /* off */);

    public float floatArray(PNIEnv ENV, FloatArray array, int off) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) floatArrayMH.invokeExact(ENV.MEMORY, (MemorySegment) (array == null ? MemorySegment.NULL : array.MEMORY), off);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnFloat();
    }

    private static final MethodHandle intArrayMH = PanamaUtils.lookupPNIFunction(new PNILinkOptions(), "Java_io_vproxy_pni_test_RawArrays_intArray", MemorySegment.class /* array */, int.class /* off */);

    public int intArray(PNIEnv ENV, IntArray array, int off) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) intArrayMH.invokeExact(ENV.MEMORY, (MemorySegment) (array == null ? MemorySegment.NULL : array.MEMORY), off);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnInt();
    }

    private static final MethodHandle unsignedIntArrayMH = PanamaUtils.lookupPNIFunction(new PNILinkOptions(), "Java_io_vproxy_pni_test_RawArrays_unsignedIntArray", MemorySegment.class /* array */, int.class /* off */);

    public int unsignedIntArray(PNIEnv ENV, IntArray array, int off) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) unsignedIntArrayMH.invokeExact(ENV.MEMORY, (MemorySegment) (array == null ? MemorySegment.NULL : array.MEMORY), off);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnInt();
    }

    private static final MethodHandle longArrayMH = PanamaUtils.lookupPNIFunction(new PNILinkOptions(), "Java_io_vproxy_pni_test_RawArrays_longArray", MemorySegment.class /* array */, int.class /* off */);

    public long longArray(PNIEnv ENV, LongArray array, int off) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) longArrayMH.invokeExact(ENV.MEMORY, (MemorySegment) (array == null ? MemorySegment.NULL : array.MEMORY), off);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnLong();
    }

    private static final MethodHandle unsignedLongArrayMH = PanamaUtils.lookupPNIFunction(new PNILinkOptions(), "Java_io_vproxy_pni_test_RawArrays_unsignedLongArray", MemorySegment.class /* array */, int.class /* off */);

    public long unsignedLongArray(PNIEnv ENV, LongArray array, int off) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) unsignedLongArrayMH.invokeExact(ENV.MEMORY, (MemorySegment) (array == null ? MemorySegment.NULL : array.MEMORY), off);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnLong();
    }

    private static final MethodHandle shortArrayMH = PanamaUtils.lookupPNIFunction(new PNILinkOptions(), "Java_io_vproxy_pni_test_RawArrays_shortArray", MemorySegment.class /* array */, int.class /* off */);

    public short shortArray(PNIEnv ENV, ShortArray array, int off) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) shortArrayMH.invokeExact(ENV.MEMORY, (MemorySegment) (array == null ? MemorySegment.NULL : array.MEMORY), off);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnShort();
    }

    private static final MethodHandle unsignedShortArrayMH = PanamaUtils.lookupPNIFunction(new PNILinkOptions(), "Java_io_vproxy_pni_test_RawArrays_unsignedShortArray", MemorySegment.class /* array */, int.class /* off */);

    public short unsignedShortArray(PNIEnv ENV, ShortArray array, int off) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) unsignedShortArrayMH.invokeExact(ENV.MEMORY, (MemorySegment) (array == null ? MemorySegment.NULL : array.MEMORY), off);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnShort();
    }

    private static final MethodHandle pointerArrayMH = PanamaUtils.lookupPNIFunction(new PNILinkOptions(), "Java_io_vproxy_pni_test_RawArrays_pointerArray", MemorySegment.class /* array */, int.class /* off */);

    public MemorySegment pointerArray(PNIEnv ENV, PointerArray array, int off) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) pointerArrayMH.invokeExact(ENV.MEMORY, (MemorySegment) (array == null ? MemorySegment.NULL : array.MEMORY), off);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnPointer();
    }

    private static final MethodHandle pointerArrayNotRawMH = PanamaUtils.lookupPNIFunction(new PNILinkOptions(), "Java_io_vproxy_pni_test_RawArrays_pointerArrayNotRaw", PNIBuf.class /* array */, int.class /* off */);

    public MemorySegment pointerArrayNotRaw(PNIEnv ENV, PointerArray array, int off) {
        ENV.reset();
        try (var POOLED = Allocator.ofPooled()) {
            int ERR;
            try {
                ERR = (int) pointerArrayNotRawMH.invokeExact(ENV.MEMORY, PNIBuf.memoryOf(POOLED, array), off);
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (ERR != 0) {
                ENV.throwLast();
            }
            return ENV.returnPointer();
        }
    }

    private static final MethodHandle structArrayMH = PanamaUtils.lookupPNIFunction(new PNILinkOptions(), "Java_io_vproxy_pni_test_RawArrays_structArray", MemorySegment.class /* array */, int.class /* off */, MemorySegment.class /* return */);

    public io.vproxy.pni.test.ObjectStruct structArray(PNIEnv ENV, io.vproxy.pni.test.ObjectStruct.Array array, int off, Allocator ALLOCATOR) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) structArrayMH.invokeExact(ENV.MEMORY, (MemorySegment) (array == null ? MemorySegment.NULL : array.MEMORY), off, ALLOCATOR.allocate(io.vproxy.pni.test.ObjectStruct.LAYOUT));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RESULT = ENV.returnPointer();
        return RESULT == null ? null : new io.vproxy.pni.test.ObjectStruct(RESULT);
    }

    private static final MethodHandle structArrayNotRawMH = PanamaUtils.lookupPNIFunction(new PNILinkOptions(), "Java_io_vproxy_pni_test_RawArrays_structArrayNotRaw", PNIBuf.class /* array */, int.class /* off */, MemorySegment.class /* return */);

    public io.vproxy.pni.test.ObjectStruct structArrayNotRaw(PNIEnv ENV, io.vproxy.pni.test.ObjectStruct.Array array, int off, Allocator ALLOCATOR) {
        ENV.reset();
        try (var POOLED = Allocator.ofPooled()) {
            int ERR;
            try {
                ERR = (int) structArrayNotRawMH.invokeExact(ENV.MEMORY, PNIBuf.memoryOf(POOLED, array), off, ALLOCATOR.allocate(io.vproxy.pni.test.ObjectStruct.LAYOUT));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (ERR != 0) {
                ENV.throwLast();
            }
            var RESULT = ENV.returnPointer();
            return RESULT == null ? null : new io.vproxy.pni.test.ObjectStruct(RESULT);
        }
    }
}
// metadata.generator-version: pni test
// sha256:84949277e15276de507036c9ef19ad57963244e1f88d9933156c9ad2973b03cf
