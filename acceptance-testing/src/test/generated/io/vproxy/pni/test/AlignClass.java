package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class AlignClass {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        ValueLayout.JAVA_BYTE.withName("a"),
        MemoryLayout.sequenceLayout(1L, ValueLayout.JAVA_BYTE) /* padding */,
        ValueLayout.JAVA_SHORT.withName("b"),
        MemoryLayout.sequenceLayout(4L, ValueLayout.JAVA_BYTE) /* padding */,
        ValueLayout.JAVA_LONG.withName("c"),
        MemoryLayout.sequenceLayout(16L, ValueLayout.JAVA_BYTE) /* padding */
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

    public short getB() {
        return (short) bVH.get(MEMORY);
    }

    public void setB(short b) {
        bVH.set(MEMORY, b);
    }

    private static final VarHandle cVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("c")
    );

    public long getC() {
        return (long) cVH.get(MEMORY);
    }

    public void setC(long c) {
        cVH.set(MEMORY, c);
    }

    public AlignClass(MemorySegment MEMORY) {
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
        OFFSET += ValueLayout.JAVA_BYTE.byteSize();
        OFFSET += 1; /* padding */
        OFFSET += ValueLayout.JAVA_SHORT_UNALIGNED.byteSize();
        OFFSET += 4; /* padding */
        OFFSET += ValueLayout.JAVA_LONG_UNALIGNED.byteSize();
        OFFSET += 16; /* padding */
    }

    public AlignClass(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT.byteSize()));
    }

    private static final MethodHandle aaaaMH = PanamaUtils.lookupPNICriticalFunction(false, byte.class, "JavaCritical_io_vproxy_pni_test_AlignClass_aaaa", MemorySegment.class /* self */);

    public byte aaaa() {
        byte RESULT;
        try {
            RESULT = (byte) aaaaMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle bbbbMH = PanamaUtils.lookupPNICriticalFunction(false, short.class, "JavaCritical_io_vproxy_pni_test_AlignClass_bbbb", MemorySegment.class /* self */);

    public short bbbb() {
        short RESULT;
        try {
            RESULT = (short) bbbbMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle ccccMH = PanamaUtils.lookupPNICriticalFunction(false, long.class, "JavaCritical_io_vproxy_pni_test_AlignClass_cccc", MemorySegment.class /* self */);

    public long cccc() {
        long RESULT;
        try {
            RESULT = (long) ccccMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    public static class Array extends RefArray<AlignClass> {
        public Array(MemorySegment buf) {
            super(buf, AlignClass.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            this(allocator.allocate(AlignClass.LAYOUT.byteSize() * len));
        }

        public Array(PNIBuf buf) {
            this(buf.get());
        }

        @Override
        protected AlignClass construct(MemorySegment seg) {
            return new AlignClass(seg);
        }

        @Override
        protected MemorySegment getSegment(AlignClass value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<AlignClass> {
        private Func(io.vproxy.pni.CallSite<AlignClass> func) {
            super(func);
        }

        private Func(io.vproxy.pni.CallSite<AlignClass> func, Options opts) {
            super(func, opts);
        }

        private Func(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static Func of(io.vproxy.pni.CallSite<AlignClass> func) {
            return new Func(func);
        }

        public static Func of(io.vproxy.pni.CallSite<AlignClass> func, Options opts) {
            return new Func(func, opts);
        }

        public static Func of(MemorySegment MEMORY) {
            return new Func(MEMORY);
        }

        @Override
        protected AlignClass construct(MemorySegment seg) {
            return new AlignClass(seg);
        }
    }
}
// metadata.generator-version: pni test
// sha256:de36b4a29916fbfc0c230ec92d6076f80f4e77055310f01f014132c743e25797
