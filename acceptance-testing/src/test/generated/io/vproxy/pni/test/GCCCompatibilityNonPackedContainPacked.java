package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class GCCCompatibilityNonPackedContainPacked {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        ValueLayout.JAVA_BYTE.withName("b1"),
        io.vproxy.pni.test.GCCCompatibilityPacked.LAYOUT.withName("packed"),
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

    public GCCCompatibilityNonPackedContainPacked(MemorySegment MEMORY) {
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
        OFFSET += ValueLayout.JAVA_BYTE.byteSize();
        this.packed = new io.vproxy.pni.test.GCCCompatibilityPacked(MEMORY.asSlice(OFFSET, io.vproxy.pni.test.GCCCompatibilityPacked.LAYOUT.byteSize()));
        OFFSET += io.vproxy.pni.test.GCCCompatibilityPacked.LAYOUT.byteSize();
        OFFSET += ValueLayout.JAVA_INT_UNALIGNED.byteSize();
    }

    public GCCCompatibilityNonPackedContainPacked(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT.byteSize()));
    }

    private final MethodHandle init = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_GCCCompatibilityNonPackedContainPacked_init", MemorySegment.class /* self */);

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

    private final MethodHandle size = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_GCCCompatibilityNonPackedContainPacked_size", MemorySegment.class /* self */);

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

    public static class Array extends RefArray<GCCCompatibilityNonPackedContainPacked> {
        public Array(MemorySegment buf) {
            super(buf, GCCCompatibilityNonPackedContainPacked.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            this(allocator.allocate(GCCCompatibilityNonPackedContainPacked.LAYOUT.byteSize() * len));
        }

        public Array(PNIBuf buf) {
            this(buf.get());
        }

        @Override
        protected GCCCompatibilityNonPackedContainPacked construct(MemorySegment seg) {
            return new GCCCompatibilityNonPackedContainPacked(seg);
        }

        @Override
        protected MemorySegment getSegment(GCCCompatibilityNonPackedContainPacked value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<GCCCompatibilityNonPackedContainPacked> {
        private Func(io.vproxy.pni.CallSite<GCCCompatibilityNonPackedContainPacked> func) {
            super(func);
        }

        public static Func of(io.vproxy.pni.CallSite<GCCCompatibilityNonPackedContainPacked> func) {
            return new Func(func);
        }

        @Override
        protected GCCCompatibilityNonPackedContainPacked construct(MemorySegment seg) {
            return new GCCCompatibilityNonPackedContainPacked(seg);
        }

        @Override
        protected MemorySegment getSegment(GCCCompatibilityNonPackedContainPacked value) {
            return value.MEMORY;
        }
    }
}
// metadata.generator-version: pni test
// sha256:a0b1c2b5b87b1e8537529c0c9687bb9a9e2a0754d5db4a4e3777a32febb82400
