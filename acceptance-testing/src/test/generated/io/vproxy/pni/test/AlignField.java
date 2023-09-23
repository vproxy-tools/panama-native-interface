package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class AlignField {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        ValueLayout.JAVA_BYTE.withName("a"),
        MemoryLayout.sequenceLayout(1L, ValueLayout.JAVA_BYTE) /* padding */,
        ValueLayout.JAVA_BYTE.withName("b"),
        MemoryLayout.sequenceLayout(1L, ValueLayout.JAVA_BYTE) /* padding */,
        ValueLayout.JAVA_INT.withName("c")
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

    public AlignField(MemorySegment MEMORY) {
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
        OFFSET += ValueLayout.JAVA_BYTE.byteSize();
        OFFSET += 1; /* padding */
        OFFSET += ValueLayout.JAVA_BYTE.byteSize();
        OFFSET += 1; /* padding */
        OFFSET += ValueLayout.JAVA_INT_UNALIGNED.byteSize();
    }

    public AlignField(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT.byteSize()));
    }

    private static final MethodHandle aaaaMH = PanamaUtils.lookupPNICriticalFunction(false, byte.class, "JavaCritical_io_vproxy_pni_test_AlignField_aaaa", MemorySegment.class /* self */);

    public byte aaaa() {
        byte RESULT;
        try {
            RESULT = (byte) aaaaMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle bbbbMH = PanamaUtils.lookupPNICriticalFunction(false, byte.class, "JavaCritical_io_vproxy_pni_test_AlignField_bbbb", MemorySegment.class /* self */);

    public byte bbbb() {
        byte RESULT;
        try {
            RESULT = (byte) bbbbMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle ccccMH = PanamaUtils.lookupPNICriticalFunction(false, int.class, "JavaCritical_io_vproxy_pni_test_AlignField_cccc", MemorySegment.class /* self */);

    public int cccc() {
        int RESULT;
        try {
            RESULT = (int) ccccMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    public static class Array extends RefArray<AlignField> {
        public Array(MemorySegment buf) {
            super(buf, AlignField.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            this(allocator.allocate(AlignField.LAYOUT.byteSize() * len));
        }

        public Array(PNIBuf buf) {
            this(buf.get());
        }

        @Override
        protected AlignField construct(MemorySegment seg) {
            return new AlignField(seg);
        }

        @Override
        protected MemorySegment getSegment(AlignField value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<AlignField> {
        private Func(io.vproxy.pni.CallSite<AlignField> func) {
            super(func);
        }

        private Func(io.vproxy.pni.CallSite<AlignField> func, Options opts) {
            super(func, opts);
        }

        private Func(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static Func of(io.vproxy.pni.CallSite<AlignField> func) {
            return new Func(func);
        }

        public static Func of(io.vproxy.pni.CallSite<AlignField> func, Options opts) {
            return new Func(func, opts);
        }

        public static Func of(MemorySegment MEMORY) {
            return new Func(MEMORY);
        }

        @Override
        protected AlignField construct(MemorySegment seg) {
            return new AlignField(seg);
        }
    }
}
// metadata.generator-version: pni test
// sha256:8a9f9faffad8b19f0ef2badcb49391fddbfe401cd4c08d20e26606986dc297f9
