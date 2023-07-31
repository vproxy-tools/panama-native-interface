package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class GCCCompatibilityAlignFieldPacked {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        ValueLayout.JAVA_BYTE.withName("b1"),
        MemoryLayout.sequenceLayout(3L, ValueLayout.JAVA_BYTE) /* padding */,
        io.vproxy.pni.test.GCCCompatibilityPacked.LAYOUT.withName("packed"),
        MemoryLayout.sequenceLayout(1L, ValueLayout.JAVA_BYTE) /* padding */,
        ValueLayout.JAVA_INT_UNALIGNED.withName("n2")
    );
    public final MemorySegment MEMORY;

    private static final VarHandle b1VH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("b1")
    );

    public byte getB1() {
        return (byte) b1VH.get(MEMORY);
    }

    public void setB1(byte b1) {
        b1VH.set(MEMORY, b1);
    }

    private final io.vproxy.pni.test.GCCCompatibilityPacked packed;

    public io.vproxy.pni.test.GCCCompatibilityPacked getPacked() {
        return this.packed;
    }

    private static final VarHandle n2VH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("n2")
    );

    public int getN2() {
        return (int) n2VH.get(MEMORY);
    }

    public void setN2(int n2) {
        n2VH.set(MEMORY, n2);
    }

    public GCCCompatibilityAlignFieldPacked(MemorySegment MEMORY) {
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
        OFFSET += ValueLayout.JAVA_BYTE.byteSize();
        OFFSET += 3; /* padding */
        this.packed = new io.vproxy.pni.test.GCCCompatibilityPacked(MEMORY.asSlice(OFFSET, io.vproxy.pni.test.GCCCompatibilityPacked.LAYOUT.byteSize()));
        OFFSET += io.vproxy.pni.test.GCCCompatibilityPacked.LAYOUT.byteSize();
        OFFSET += 1; /* padding */
        OFFSET += ValueLayout.JAVA_INT_UNALIGNED.byteSize();
    }

    public GCCCompatibilityAlignFieldPacked(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT.byteSize()));
    }

    private final MethodHandle init = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_GCCCompatibilityAlignFieldPacked_init", MemorySegment.class /* self */);

    public void init(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.init.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
    }

    private final MethodHandle size = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_GCCCompatibilityAlignFieldPacked_size", MemorySegment.class /* self */);

    public long size(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.size.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnLong();
    }

    public static class Array extends RefArray<GCCCompatibilityAlignFieldPacked> {
        public Array(MemorySegment buf) {
            super(buf, GCCCompatibilityAlignFieldPacked.LAYOUT);
        }

        @Override
        protected GCCCompatibilityAlignFieldPacked construct(MemorySegment seg) {
            return new GCCCompatibilityAlignFieldPacked(seg);
        }

        @Override
        protected MemorySegment getSegment(GCCCompatibilityAlignFieldPacked value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<GCCCompatibilityAlignFieldPacked> {
        private Func(io.vproxy.pni.CallSite<GCCCompatibilityAlignFieldPacked> func) {
            super(func);
        }

        public static Func of(io.vproxy.pni.CallSite<GCCCompatibilityAlignFieldPacked> func) {
            return new Func(func);
        }

        @Override
        protected GCCCompatibilityAlignFieldPacked construct(MemorySegment seg) {
            return new GCCCompatibilityAlignFieldPacked(seg);
        }

        @Override
        protected MemorySegment getSegment(GCCCompatibilityAlignFieldPacked value) {
            return value.MEMORY;
        }
    }
}
// sha256:a4ac06a218075dc332ccef6ff84b9977f5e51a1dc2e118666fa87bce0644b2e5