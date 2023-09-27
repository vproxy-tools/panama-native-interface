package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class AlignField3 {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        ValueLayout.JAVA_SHORT.withName("a"),
        MemoryLayout.sequenceLayout(30L, ValueLayout.JAVA_BYTE) /* padding */,
        ValueLayout.JAVA_INT.withName("b"),
        ValueLayout.JAVA_INT.withName("c"),
        MemoryLayout.sequenceLayout(24L, ValueLayout.JAVA_BYTE) /* padding */
    );
    public final MemorySegment MEMORY;

    private static final VarHandle aVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("a")
    );

    public short getA() {
        return (short) aVH.get(MEMORY);
    }

    public void setA(short a) {
        aVH.set(MEMORY, a);
    }

    private static final VarHandle bVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("b")
    );

    public int getB() {
        return (int) bVH.get(MEMORY);
    }

    public void setB(int b) {
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

    public AlignField3(MemorySegment MEMORY) {
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
        OFFSET += ValueLayout.JAVA_SHORT_UNALIGNED.byteSize();
        OFFSET += 30; /* padding */
        OFFSET += ValueLayout.JAVA_INT_UNALIGNED.byteSize();
        OFFSET += ValueLayout.JAVA_INT_UNALIGNED.byteSize();
        OFFSET += 24; /* padding */
    }

    public AlignField3(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT.byteSize()));
    }

    private static final MethodHandle aaaaMH = PanamaUtils.lookupPNICriticalFunction(false, short.class, "JavaCritical_io_vproxy_pni_test_AlignField3_aaaa", MemorySegment.class /* self */);

    public short aaaa() {
        short RESULT;
        try {
            RESULT = (short) aaaaMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle bbbbMH = PanamaUtils.lookupPNICriticalFunction(false, int.class, "JavaCritical_io_vproxy_pni_test_AlignField3_bbbb", MemorySegment.class /* self */);

    public int bbbb() {
        int RESULT;
        try {
            RESULT = (int) bbbbMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle ccccMH = PanamaUtils.lookupPNICriticalFunction(false, int.class, "JavaCritical_io_vproxy_pni_test_AlignField3_cccc", MemorySegment.class /* self */);

    public int cccc() {
        int RESULT;
        try {
            RESULT = (int) ccccMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle sizeMH = PanamaUtils.lookupPNICriticalFunction(false, long.class, "JavaCritical_io_vproxy_pni_test_AlignField3_size", MemorySegment.class /* self */);

    public long size() {
        long RESULT;
        try {
            RESULT = (long) sizeMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    public static class Array extends RefArray<AlignField3> {
        public Array(MemorySegment buf) {
            super(buf, AlignField3.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            this(allocator.allocate(AlignField3.LAYOUT.byteSize() * len));
        }

        public Array(PNIBuf buf) {
            this(buf.get());
        }

        @Override
        protected AlignField3 construct(MemorySegment seg) {
            return new AlignField3(seg);
        }

        @Override
        protected MemorySegment getSegment(AlignField3 value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<AlignField3> {
        private Func(io.vproxy.pni.CallSite<AlignField3> func) {
            super(func);
        }

        private Func(io.vproxy.pni.CallSite<AlignField3> func, Options opts) {
            super(func, opts);
        }

        private Func(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static Func of(io.vproxy.pni.CallSite<AlignField3> func) {
            return new Func(func);
        }

        public static Func of(io.vproxy.pni.CallSite<AlignField3> func, Options opts) {
            return new Func(func, opts);
        }

        public static Func of(MemorySegment MEMORY) {
            return new Func(MEMORY);
        }

        @Override
        protected AlignField3 construct(MemorySegment seg) {
            return new AlignField3(seg);
        }
    }
}
// metadata.generator-version: pni test
// sha256:ab8275f2c92d161229fe178b641db4949237c559bc4c53955f0d1c865a05fb76