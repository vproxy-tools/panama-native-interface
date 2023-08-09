package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class GCCCompatibilityPackedContainNonPacked {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        ValueLayout.JAVA_BYTE.withName("b1"),
        io.vproxy.pni.test.GCCCompatibilityNormal.LAYOUT.withName("normal"),
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

    private final io.vproxy.pni.test.GCCCompatibilityNormal normal;

    public io.vproxy.pni.test.GCCCompatibilityNormal getNormal() {
        return this.normal;
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

    public GCCCompatibilityPackedContainNonPacked(MemorySegment MEMORY) {
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
        OFFSET += ValueLayout.JAVA_BYTE.byteSize();
        this.normal = new io.vproxy.pni.test.GCCCompatibilityNormal(MEMORY.asSlice(OFFSET, io.vproxy.pni.test.GCCCompatibilityNormal.LAYOUT.byteSize()));
        OFFSET += io.vproxy.pni.test.GCCCompatibilityNormal.LAYOUT.byteSize();
        OFFSET += ValueLayout.JAVA_INT_UNALIGNED.byteSize();
    }

    public GCCCompatibilityPackedContainNonPacked(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT.byteSize()));
    }

    private final MethodHandle init = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_GCCCompatibilityPackedContainNonPacked_init", MemorySegment.class /* self */);

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

    private final MethodHandle size = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_GCCCompatibilityPackedContainNonPacked_size", MemorySegment.class /* self */);

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

    public static class Array extends RefArray<GCCCompatibilityPackedContainNonPacked> {
        public Array(MemorySegment buf) {
            super(buf, GCCCompatibilityPackedContainNonPacked.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            this(allocator.allocate(GCCCompatibilityPackedContainNonPacked.LAYOUT.byteSize() * len));
        }

        public Array(PNIBuf buf) {
            this(buf.get());
        }

        @Override
        protected GCCCompatibilityPackedContainNonPacked construct(MemorySegment seg) {
            return new GCCCompatibilityPackedContainNonPacked(seg);
        }

        @Override
        protected MemorySegment getSegment(GCCCompatibilityPackedContainNonPacked value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<GCCCompatibilityPackedContainNonPacked> {
        private Func(io.vproxy.pni.CallSite<GCCCompatibilityPackedContainNonPacked> func) {
            super(func);
        }

        public static Func of(io.vproxy.pni.CallSite<GCCCompatibilityPackedContainNonPacked> func) {
            return new Func(func);
        }

        @Override
        protected GCCCompatibilityPackedContainNonPacked construct(MemorySegment seg) {
            return new GCCCompatibilityPackedContainNonPacked(seg);
        }

        @Override
        protected MemorySegment getSegment(GCCCompatibilityPackedContainNonPacked value) {
            return value.MEMORY;
        }
    }
}
// metadata.generator-version: pni test
// sha256:2b2fa27dcdee868845cf1e183a18093e4277150fad4fc3405ed6bb19245a75f9
