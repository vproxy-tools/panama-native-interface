package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class AlignField2 {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        ValueLayout.JAVA_BYTE.withName("a"),
        MemoryLayout.sequenceLayout(31L, ValueLayout.JAVA_BYTE) /* padding */,
        ValueLayout.JAVA_BYTE.withName("b"),
        MemoryLayout.sequenceLayout(3L, ValueLayout.JAVA_BYTE) /* padding */,
        ValueLayout.JAVA_INT.withName("c"),
        MemoryLayout.sequenceLayout(24L, ValueLayout.JAVA_BYTE) /* padding */
    );
    public final MemorySegment MEMORY;

    private static final VarHandle aVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("a")
    );

    public byte getA() {
        return (byte) aVH.get(MEMORY);
    }

    public void setA(byte a) {
        aVH.set(MEMORY, a);
    }

    private static final VarHandle bVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("b")
    );

    public byte getB() {
        return (byte) bVH.get(MEMORY);
    }

    public void setB(byte b) {
        bVH.set(MEMORY, b);
    }

    private static final VarHandle cVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("c")
    );

    public int getC() {
        return (int) cVH.get(MEMORY);
    }

    public void setC(int c) {
        cVH.set(MEMORY, c);
    }

    public AlignField2(MemorySegment MEMORY) {
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
        OFFSET += ValueLayout.JAVA_BYTE.byteSize();
        OFFSET += 31; /* padding */
        OFFSET += ValueLayout.JAVA_BYTE.byteSize();
        OFFSET += 3; /* padding */
        OFFSET += ValueLayout.JAVA_INT_UNALIGNED.byteSize();
        OFFSET += 24; /* padding */
    }

    public AlignField2(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT.byteSize()));
    }

    private static final MethodHandle aaaaMH = PanamaUtils.lookupPNICriticalFunction(false, byte.class, "JavaCritical_io_vproxy_pni_test_AlignField2_aaaa", MemorySegment.class /* self */);

    public byte aaaa() {
        byte RESULT;
        try {
            RESULT = (byte) aaaaMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle bbbbMH = PanamaUtils.lookupPNICriticalFunction(false, byte.class, "JavaCritical_io_vproxy_pni_test_AlignField2_bbbb", MemorySegment.class /* self */);

    public byte bbbb() {
        byte RESULT;
        try {
            RESULT = (byte) bbbbMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle ccccMH = PanamaUtils.lookupPNICriticalFunction(false, int.class, "JavaCritical_io_vproxy_pni_test_AlignField2_cccc", MemorySegment.class /* self */);

    public int cccc() {
        int RESULT;
        try {
            RESULT = (int) ccccMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle sizeMH = PanamaUtils.lookupPNICriticalFunction(false, long.class, "JavaCritical_io_vproxy_pni_test_AlignField2_size", MemorySegment.class /* self */);

    public long size() {
        long RESULT;
        try {
            RESULT = (long) sizeMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    public static class Array extends RefArray<AlignField2> {
        public Array(MemorySegment buf) {
            super(buf, AlignField2.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            this(allocator.allocate(AlignField2.LAYOUT.byteSize() * len));
        }

        public Array(PNIBuf buf) {
            this(buf.get());
        }

        @Override
        protected AlignField2 construct(MemorySegment seg) {
            return new AlignField2(seg);
        }

        @Override
        protected MemorySegment getSegment(AlignField2 value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<AlignField2> {
        private Func(io.vproxy.pni.CallSite<AlignField2> func) {
            super(func);
        }

        private Func(io.vproxy.pni.CallSite<AlignField2> func, Options opts) {
            super(func, opts);
        }

        private Func(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static Func of(io.vproxy.pni.CallSite<AlignField2> func) {
            return new Func(func);
        }

        public static Func of(io.vproxy.pni.CallSite<AlignField2> func, Options opts) {
            return new Func(func, opts);
        }

        public static Func of(MemorySegment MEMORY) {
            return new Func(MEMORY);
        }

        @Override
        protected AlignField2 construct(MemorySegment seg) {
            return new AlignField2(seg);
        }
    }
}
// metadata.generator-version: pni test
// sha256:587cc613f85b88dd04baa55fd63df74797cdd7f456d2eadafdeb1049e7a73c40
