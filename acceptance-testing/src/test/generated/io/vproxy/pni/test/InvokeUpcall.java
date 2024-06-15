package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.hack.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class InvokeUpcall {
    private InvokeUpcall() {
    }

    private static final InvokeUpcall INSTANCE = new InvokeUpcall();

    public static InvokeUpcall get() {
        return INSTANCE;
    }

    private static final MethodHandle primaryParamsMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), void.class, "JavaCritical_io_vproxy_pni_test_InvokeUpcall_primaryParams", byte.class /* b */, byte.class /* ub */, boolean.class /* z */, char.class /* c */, double.class /* d */, float.class /* f */, int.class /* i */, int.class /* ui */, long.class /* j */, long.class /* uj */, short.class /* s */, short.class /* us */);

    public void primaryParams(byte b, byte ub, boolean z, char c, double d, float f, int i, int ui, long j, long uj, short s, short us) {
        try {
            primaryParamsMH.invokeExact(b, ub, z, c, d, f, i, ui, j, uj, s, us);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle returnByteMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), byte.class, "JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnByte");

    public byte returnByte() {
        byte RESULT;
        try {
            RESULT = (byte) returnByteMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle returnBoolMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), boolean.class, "JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnBool");

    public boolean returnBool() {
        boolean RESULT;
        try {
            RESULT = (boolean) returnBoolMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle returnCharMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), char.class, "JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnChar");

    public char returnChar() {
        char RESULT;
        try {
            RESULT = (char) returnCharMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle returnDoubleMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), double.class, "JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnDouble");

    public double returnDouble() {
        double RESULT;
        try {
            RESULT = (double) returnDoubleMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle returnFloatMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), float.class, "JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnFloat");

    public float returnFloat() {
        float RESULT;
        try {
            RESULT = (float) returnFloatMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle returnIntMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), int.class, "JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnInt");

    public int returnInt() {
        int RESULT;
        try {
            RESULT = (int) returnIntMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle returnLongMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), long.class, "JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnLong");

    public long returnLong() {
        long RESULT;
        try {
            RESULT = (long) returnLongMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle returnShortMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), short.class, "JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnShort");

    public short returnShort() {
        short RESULT;
        try {
            RESULT = (short) returnShortMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle primaryArrayParamsMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), void.class, "JavaCritical_io_vproxy_pni_test_InvokeUpcall_primaryArrayParams", PNIBuf.class /* b */, PNIBuf.class /* ub */, PNIBuf.class /* z */, PNIBuf.class /* c */, PNIBuf.class /* d */, PNIBuf.class /* f */, PNIBuf.class /* i */, PNIBuf.class /* ui */, PNIBuf.class /* j */, PNIBuf.class /* uj */, PNIBuf.class /* s */, PNIBuf.class /* us */);

    public void primaryArrayParams(MemorySegment b, MemorySegment ub, BoolArray z, CharArray c, DoubleArray d, FloatArray f, IntArray i, IntArray ui, LongArray j, LongArray uj, ShortArray s, ShortArray us) {
        try (var POOLED = Allocator.ofPooled()) {
            try {
                primaryArrayParamsMH.invokeExact(PNIBuf.memoryOf(POOLED, b), PNIBuf.memoryOf(POOLED, ub), PNIBuf.memoryOf(POOLED, z), PNIBuf.memoryOf(POOLED, c), PNIBuf.memoryOf(POOLED, d), PNIBuf.memoryOf(POOLED, f), PNIBuf.memoryOf(POOLED, i), PNIBuf.memoryOf(POOLED, ui), PNIBuf.memoryOf(POOLED, j), PNIBuf.memoryOf(POOLED, uj), PNIBuf.memoryOf(POOLED, s), PNIBuf.memoryOf(POOLED, us));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
        }
    }

    private static final MethodHandle returnByteArrayMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), PNIBuf.class, "JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnByteArray", MemorySegment.class /* return */);

    public MemorySegment returnByteArray() {
        try (var POOLED = Allocator.ofPooled()) {
            MemorySegment RESULT;
            try {
                RESULT = (MemorySegment) returnByteArrayMH.invokeExact(POOLED.allocate(PNIBuf.LAYOUT));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (RESULT.address() == 0) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return RES_SEG.get();
        }
    }

    private static final MethodHandle returnBoolArrayMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), PNIBuf.class, "JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnBoolArray", MemorySegment.class /* return */);

    public BoolArray returnBoolArray() {
        try (var POOLED = Allocator.ofPooled()) {
            MemorySegment RESULT;
            try {
                RESULT = (MemorySegment) returnBoolArrayMH.invokeExact(POOLED.allocate(PNIBuf.LAYOUT));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (RESULT.address() == 0) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return new BoolArray(RES_SEG);
        }
    }

    private static final MethodHandle returnCharArrayMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), PNIBuf.class, "JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnCharArray", MemorySegment.class /* return */);

    public CharArray returnCharArray() {
        try (var POOLED = Allocator.ofPooled()) {
            MemorySegment RESULT;
            try {
                RESULT = (MemorySegment) returnCharArrayMH.invokeExact(POOLED.allocate(PNIBuf.LAYOUT));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (RESULT.address() == 0) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return new CharArray(RES_SEG);
        }
    }

    private static final MethodHandle returnDoubleArrayMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), PNIBuf.class, "JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnDoubleArray", MemorySegment.class /* return */);

    public DoubleArray returnDoubleArray() {
        try (var POOLED = Allocator.ofPooled()) {
            MemorySegment RESULT;
            try {
                RESULT = (MemorySegment) returnDoubleArrayMH.invokeExact(POOLED.allocate(PNIBuf.LAYOUT));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (RESULT.address() == 0) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return new DoubleArray(RES_SEG);
        }
    }

    private static final MethodHandle returnFloatArrayMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), PNIBuf.class, "JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnFloatArray", MemorySegment.class /* return */);

    public FloatArray returnFloatArray() {
        try (var POOLED = Allocator.ofPooled()) {
            MemorySegment RESULT;
            try {
                RESULT = (MemorySegment) returnFloatArrayMH.invokeExact(POOLED.allocate(PNIBuf.LAYOUT));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (RESULT.address() == 0) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return new FloatArray(RES_SEG);
        }
    }

    private static final MethodHandle returnIntArrayMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), PNIBuf.class, "JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnIntArray", MemorySegment.class /* return */);

    public IntArray returnIntArray() {
        try (var POOLED = Allocator.ofPooled()) {
            MemorySegment RESULT;
            try {
                RESULT = (MemorySegment) returnIntArrayMH.invokeExact(POOLED.allocate(PNIBuf.LAYOUT));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (RESULT.address() == 0) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return new IntArray(RES_SEG);
        }
    }

    private static final MethodHandle returnLongArrayMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), PNIBuf.class, "JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnLongArray", MemorySegment.class /* return */);

    public LongArray returnLongArray() {
        try (var POOLED = Allocator.ofPooled()) {
            MemorySegment RESULT;
            try {
                RESULT = (MemorySegment) returnLongArrayMH.invokeExact(POOLED.allocate(PNIBuf.LAYOUT));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (RESULT.address() == 0) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return new LongArray(RES_SEG);
        }
    }

    private static final MethodHandle returnShortArrayMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), PNIBuf.class, "JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnShortArray", MemorySegment.class /* return */);

    public ShortArray returnShortArray() {
        try (var POOLED = Allocator.ofPooled()) {
            MemorySegment RESULT;
            try {
                RESULT = (MemorySegment) returnShortArrayMH.invokeExact(POOLED.allocate(PNIBuf.LAYOUT));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (RESULT.address() == 0) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return new ShortArray(RES_SEG);
        }
    }

    private static final MethodHandle objectParamsMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), void.class, "JavaCritical_io_vproxy_pni_test_InvokeUpcall_objectParams", io.vproxy.pni.test.ObjectStruct.LAYOUT.getClass() /* o */);

    public void objectParams(io.vproxy.pni.test.ObjectStruct o) {
        try {
            objectParamsMH.invokeExact((MemorySegment) (o == null ? MemorySegment.NULL : o.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle returnObjectMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), io.vproxy.pni.test.ObjectStruct.LAYOUT.getClass(), "JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnObject", MemorySegment.class /* return */);

    public io.vproxy.pni.test.ObjectStruct returnObject(Allocator ALLOCATOR) {
        MemorySegment RESULT;
        try {
            RESULT = (MemorySegment) returnObjectMH.invokeExact(ALLOCATOR.allocate(io.vproxy.pni.test.ObjectStruct.LAYOUT));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (RESULT.address() == 0) return null;
        return RESULT == null ? null : new io.vproxy.pni.test.ObjectStruct(RESULT);
    }

    private static final MethodHandle pointerArrayParamsMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), void.class, "JavaCritical_io_vproxy_pni_test_InvokeUpcall_pointerArrayParams", PNIBuf.class /* o */);

    public void pointerArrayParams(PointerArray o) {
        try (var POOLED = Allocator.ofPooled()) {
            try {
                pointerArrayParamsMH.invokeExact(PNIBuf.memoryOf(POOLED, o));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
        }
    }

    private static final MethodHandle returnPointerArrayMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), PNIBuf.class, "JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnPointerArray", MemorySegment.class /* return */);

    public PointerArray returnPointerArray() {
        try (var POOLED = Allocator.ofPooled()) {
            MemorySegment RESULT;
            try {
                RESULT = (MemorySegment) returnPointerArrayMH.invokeExact(POOLED.allocate(PNIBuf.LAYOUT));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (RESULT.address() == 0) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return new PointerArray(RES_SEG);
        }
    }

    private static final MethodHandle objectArrayParamsMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), void.class, "JavaCritical_io_vproxy_pni_test_InvokeUpcall_objectArrayParams", PNIBuf.class /* o */);

    public void objectArrayParams(io.vproxy.pni.test.ObjectStruct.Array o) {
        try (var POOLED = Allocator.ofPooled()) {
            try {
                objectArrayParamsMH.invokeExact(PNIBuf.memoryOf(POOLED, o));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
        }
    }

    private static final MethodHandle returnObjectArrayMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), PNIBuf.class, "JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnObjectArray", MemorySegment.class /* return */);

    public io.vproxy.pni.test.ObjectStruct.Array returnObjectArray() {
        try (var POOLED = Allocator.ofPooled()) {
            MemorySegment RESULT;
            try {
                RESULT = (MemorySegment) returnObjectArrayMH.invokeExact(POOLED.allocate(PNIBuf.LAYOUT));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (RESULT.address() == 0) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return new io.vproxy.pni.test.ObjectStruct.Array(RES_SEG);
        }
    }

    private static final MethodHandle otherParamsMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), void.class, "JavaCritical_io_vproxy_pni_test_InvokeUpcall_otherParams", PNIBuf.class /* buffer */, io.vproxy.pni.CallSite.class /* voidCallSite */, io.vproxy.pni.CallSite.class /* objCallSite */, io.vproxy.pni.CallSite.class /* refCallSite */, MemorySegment.class /* mem */, PNIFunc.class /* voidFunc */, PNIFunc.class /* objFunc */, PNIFunc.class /* refFunc */, PNIRef.class /* ref */, PNIRef.class /* rawRef */, String.class /* str */);

    public void otherParams(ByteBuffer buffer, io.vproxy.pni.CallSite<Void> voidCallSite, io.vproxy.pni.CallSite<io.vproxy.pni.test.ObjectStruct> objCallSite, io.vproxy.pni.CallSite<java.util.List<java.lang.String>> refCallSite, MemorySegment mem, PNIFunc<Void> voidFunc, PNIFunc<io.vproxy.pni.test.ObjectStruct> objFunc, PNIFunc<java.util.List<java.lang.String>> refFunc, java.util.List<java.lang.Integer> ref, PNIRef<java.util.List<java.lang.Integer>> rawRef, PNIString str) {
        try (var POOLED = Allocator.ofPooled()) {
            try {
                otherParamsMH.invokeExact(PanamaUtils.format(buffer, POOLED), (MemorySegment) (voidCallSite == null ? MemorySegment.NULL : PNIFunc.VoidFunc.of(voidCallSite).MEMORY), (MemorySegment) (objCallSite == null ? MemorySegment.NULL : io.vproxy.pni.test.ObjectStruct.Func.of(objCallSite).MEMORY), (MemorySegment) (refCallSite == null ? MemorySegment.NULL : PNIRef.Func.of(refCallSite).MEMORY), (MemorySegment) (mem == null ? MemorySegment.NULL : mem), (MemorySegment) (voidFunc == null ? MemorySegment.NULL : voidFunc.MEMORY), (MemorySegment) (objFunc == null ? MemorySegment.NULL : objFunc.MEMORY), (MemorySegment) (refFunc == null ? MemorySegment.NULL : refFunc.MEMORY), (MemorySegment) (ref == null ? MemorySegment.NULL : PNIRef.of(ref).MEMORY), (MemorySegment) (rawRef == null ? MemorySegment.NULL : rawRef.MEMORY), (MemorySegment) (str == null ? MemorySegment.NULL : str.MEMORY));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
        }
    }

    private static final MethodHandle returnBufferMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), PNIBuf.class, "JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnBuffer", MemorySegment.class /* return */);

    public ByteBuffer returnBuffer() {
        try (var POOLED = Allocator.ofPooled()) {
            MemorySegment RESULT;
            try {
                RESULT = (MemorySegment) returnBufferMH.invokeExact(POOLED.allocate(PNIBuf.LAYOUT));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (RESULT.address() == 0) return null;
            var RES_SEG = new PNIBuf(RESULT);
            return RES_SEG.toByteBuffer();
        }
    }

    private static final MethodHandle returnMemMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), MemorySegment.class, "JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnMem");

    public MemorySegment returnMem() {
        MemorySegment RESULT;
        try {
            RESULT = (MemorySegment) returnMemMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (RESULT.address() == 0) return null;
        return RESULT;
    }

    private static final MethodHandle returnVoidFuncMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), PNIFunc.class, "JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnVoidFunc");

    public PNIFunc<Void> returnVoidFunc() {
        MemorySegment RESULT;
        try {
            RESULT = (MemorySegment) returnVoidFuncMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (RESULT.address() == 0) return null;
        return PNIFunc.VoidFunc.of(RESULT);
    }

    private static final MethodHandle returnObjFuncMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), PNIFunc.class, "JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnObjFunc");

    public PNIFunc<io.vproxy.pni.test.ObjectStruct> returnObjFunc() {
        MemorySegment RESULT;
        try {
            RESULT = (MemorySegment) returnObjFuncMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (RESULT.address() == 0) return null;
        return io.vproxy.pni.test.ObjectStruct.Func.of(RESULT);
    }

    private static final MethodHandle returnRefFuncMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), PNIFunc.class, "JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnRefFunc");

    public PNIFunc<java.util.List<java.lang.String>> returnRefFunc() {
        MemorySegment RESULT;
        try {
            RESULT = (MemorySegment) returnRefFuncMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (RESULT.address() == 0) return null;
        return PNIRef.Func.of(RESULT);
    }

    private static final MethodHandle returnRefMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), PNIRef.class, "JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnRef");

    public PNIRef<java.util.List<java.lang.Integer>> returnRef() {
        MemorySegment RESULT;
        try {
            RESULT = (MemorySegment) returnRefMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (RESULT.address() == 0) return null;
        return PNIRef.of(RESULT);
    }

    private static final MethodHandle returnStrMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), String.class, "JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnStr");

    public PNIString returnStr() {
        MemorySegment RESULT;
        try {
            RESULT = (MemorySegment) returnStrMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT.address() == 0 ? null : new PNIString(RESULT);
    }

    private static final MethodHandle sumMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), int.class, "JavaCritical_io_vproxy_pni_test_InvokeUpcall_sum", int.class /* a */, int.class /* b */);

    public int sum(int a, int b) {
        int RESULT;
        try {
            RESULT = (int) sumMH.invokeExact(a, b);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }
}
// metadata.generator-version: pni test
// sha256:38ffab4909a28f47ef3f126ba0f341823d25547543a7b22dc97bb4f2c6a9effb
