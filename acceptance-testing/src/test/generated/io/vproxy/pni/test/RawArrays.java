package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class RawArrays {
    private RawArrays() {
    }

    private static final RawArrays INSTANCE = new RawArrays();

    public static RawArrays get() {
        return INSTANCE;
    }

    private final MethodHandle byteArray = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_RawArrays_byteArray", MemorySegment.class /* array */, int.class /* off */);

    public byte byteArray(PNIEnv ENV, MemorySegment array, int off) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.byteArray.invokeExact(ENV.MEMORY, (MemorySegment) (array == null ? MemorySegment.NULL : array), off);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnByte();
    }

    private final MethodHandle unsignedByteArray = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_RawArrays_unsignedByteArray", MemorySegment.class /* array */, int.class /* off */);

    public byte unsignedByteArray(PNIEnv ENV, MemorySegment array, int off) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.unsignedByteArray.invokeExact(ENV.MEMORY, (MemorySegment) (array == null ? MemorySegment.NULL : array), off);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnByte();
    }

    private final MethodHandle boolArray = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_RawArrays_boolArray", MemorySegment.class /* array */, int.class /* off */);

    public boolean boolArray(PNIEnv ENV, BoolArray array, int off) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.boolArray.invokeExact(ENV.MEMORY, (MemorySegment) (array == null ? MemorySegment.NULL : array.MEMORY), off);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnBool();
    }

    private final MethodHandle charArray = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_RawArrays_charArray", MemorySegment.class /* array */, int.class /* off */);

    public char charArray(PNIEnv ENV, CharArray array, int off) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.charArray.invokeExact(ENV.MEMORY, (MemorySegment) (array == null ? MemorySegment.NULL : array.MEMORY), off);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnChar();
    }

    private final MethodHandle doubleArray = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_RawArrays_doubleArray", MemorySegment.class /* array */, int.class /* off */);

    public double doubleArray(PNIEnv ENV, DoubleArray array, int off) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.doubleArray.invokeExact(ENV.MEMORY, (MemorySegment) (array == null ? MemorySegment.NULL : array.MEMORY), off);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnDouble();
    }

    private final MethodHandle floatArray = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_RawArrays_floatArray", MemorySegment.class /* array */, int.class /* off */);

    public float floatArray(PNIEnv ENV, FloatArray array, int off) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.floatArray.invokeExact(ENV.MEMORY, (MemorySegment) (array == null ? MemorySegment.NULL : array.MEMORY), off);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnFloat();
    }

    private final MethodHandle intArray = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_RawArrays_intArray", MemorySegment.class /* array */, int.class /* off */);

    public int intArray(PNIEnv ENV, IntArray array, int off) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.intArray.invokeExact(ENV.MEMORY, (MemorySegment) (array == null ? MemorySegment.NULL : array.MEMORY), off);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnInt();
    }

    private final MethodHandle unsignedIntArray = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_RawArrays_unsignedIntArray", MemorySegment.class /* array */, int.class /* off */);

    public int unsignedIntArray(PNIEnv ENV, IntArray array, int off) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.unsignedIntArray.invokeExact(ENV.MEMORY, (MemorySegment) (array == null ? MemorySegment.NULL : array.MEMORY), off);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnInt();
    }

    private final MethodHandle longArray = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_RawArrays_longArray", MemorySegment.class /* array */, int.class /* off */);

    public long longArray(PNIEnv ENV, LongArray array, int off) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.longArray.invokeExact(ENV.MEMORY, (MemorySegment) (array == null ? MemorySegment.NULL : array.MEMORY), off);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnLong();
    }

    private final MethodHandle unsignedLongArray = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_RawArrays_unsignedLongArray", MemorySegment.class /* array */, int.class /* off */);

    public long unsignedLongArray(PNIEnv ENV, LongArray array, int off) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.unsignedLongArray.invokeExact(ENV.MEMORY, (MemorySegment) (array == null ? MemorySegment.NULL : array.MEMORY), off);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnLong();
    }

    private final MethodHandle shortArray = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_RawArrays_shortArray", MemorySegment.class /* array */, int.class /* off */);

    public short shortArray(PNIEnv ENV, ShortArray array, int off) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.shortArray.invokeExact(ENV.MEMORY, (MemorySegment) (array == null ? MemorySegment.NULL : array.MEMORY), off);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnShort();
    }

    private final MethodHandle unsignedShortArray = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_RawArrays_unsignedShortArray", MemorySegment.class /* array */, int.class /* off */);

    public short unsignedShortArray(PNIEnv ENV, ShortArray array, int off) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.unsignedShortArray.invokeExact(ENV.MEMORY, (MemorySegment) (array == null ? MemorySegment.NULL : array.MEMORY), off);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnShort();
    }

    private final MethodHandle structArray = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_RawArrays_structArray", MemorySegment.class /* array */, int.class /* off */, MemorySegment.class /* return */);

    public io.vproxy.pni.test.ObjectStruct structArray(PNIEnv ENV, io.vproxy.pni.test.ObjectStruct.Array array, int off, Allocator ALLOCATOR) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.structArray.invokeExact(ENV.MEMORY, (MemorySegment) (array == null ? MemorySegment.NULL : array.MEMORY), off, ALLOCATOR.allocate(io.vproxy.pni.test.ObjectStruct.LAYOUT.byteSize()));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RESULT = ENV.returnPointer();
        return RESULT == null ? null : new io.vproxy.pni.test.ObjectStruct(RESULT);
    }

    private final MethodHandle structArrayNotRaw = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_RawArrays_structArrayNotRaw", PNIBuf.class /* array */, int.class /* off */, MemorySegment.class /* return */);

    public io.vproxy.pni.test.ObjectStruct structArrayNotRaw(PNIEnv ENV, io.vproxy.pni.test.ObjectStruct.Array array, int off, Allocator ALLOCATOR) {
        ENV.reset();
        try (var POOLED = Allocator.ofPooled()) {
            int ERR;
            try {
                ERR = (int) this.structArrayNotRaw.invokeExact(ENV.MEMORY, PNIBuf.memoryOf(POOLED, array), off, ALLOCATOR.allocate(io.vproxy.pni.test.ObjectStruct.LAYOUT.byteSize()));
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
// sha256:dfaba3b8075316f7deec0cdbd880b1755ab18d98f641c815e81b67ff71a08bb9
