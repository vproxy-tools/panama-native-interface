package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class GCCompatibilityPackedContainUnion {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        ValueLayout.JAVA_BYTE.withName("b1"),
        io.vproxy.pni.test.GCCCompatibilityUnion.LAYOUT.withName("un"),
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

    private final io.vproxy.pni.test.GCCCompatibilityUnion un;

    public io.vproxy.pni.test.GCCCompatibilityUnion getUn() {
        return this.un;
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

    public GCCompatibilityPackedContainUnion(MemorySegment MEMORY) {
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
        OFFSET += ValueLayout.JAVA_BYTE.byteSize();
        this.un = new io.vproxy.pni.test.GCCCompatibilityUnion(MEMORY.asSlice(OFFSET, io.vproxy.pni.test.GCCCompatibilityUnion.LAYOUT.byteSize()));
        OFFSET += io.vproxy.pni.test.GCCCompatibilityUnion.LAYOUT.byteSize();
        OFFSET += ValueLayout.JAVA_INT_UNALIGNED.byteSize();
    }

    public GCCompatibilityPackedContainUnion(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT.byteSize()));
    }

    private static final MethodHandle initBMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_GCCompatibilityPackedContainUnion_initB", MemorySegment.class /* self */);

    public void initB(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) initBMH.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
    }

    private static final MethodHandle initSMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_GCCompatibilityPackedContainUnion_initS", MemorySegment.class /* self */);

    public void initS(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) initSMH.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
    }

    private static final MethodHandle initNMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_GCCompatibilityPackedContainUnion_initN", MemorySegment.class /* self */);

    public void initN(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) initNMH.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
    }

    private static final MethodHandle initFMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_GCCompatibilityPackedContainUnion_initF", MemorySegment.class /* self */);

    public void initF(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) initFMH.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
    }

    private static final MethodHandle initDMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_GCCompatibilityPackedContainUnion_initD", MemorySegment.class /* self */);

    public void initD(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) initDMH.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
    }

    private static final MethodHandle initLMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_GCCompatibilityPackedContainUnion_initL", MemorySegment.class /* self */);

    public void initL(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) initLMH.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
    }

    private static final MethodHandle sizeMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_GCCompatibilityPackedContainUnion_size", MemorySegment.class /* self */);

    public long size(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) sizeMH.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnLong();
    }

    public static class Array extends RefArray<GCCompatibilityPackedContainUnion> {
        public Array(MemorySegment buf) {
            super(buf, GCCompatibilityPackedContainUnion.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            this(allocator.allocate(GCCompatibilityPackedContainUnion.LAYOUT.byteSize() * len));
        }

        public Array(PNIBuf buf) {
            this(buf.get());
        }

        @Override
        protected GCCompatibilityPackedContainUnion construct(MemorySegment seg) {
            return new GCCompatibilityPackedContainUnion(seg);
        }

        @Override
        protected MemorySegment getSegment(GCCompatibilityPackedContainUnion value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<GCCompatibilityPackedContainUnion> {
        private Func(io.vproxy.pni.CallSite<GCCompatibilityPackedContainUnion> func) {
            super(func);
        }

        private Func(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static Func of(io.vproxy.pni.CallSite<GCCompatibilityPackedContainUnion> func) {
            return new Func(func);
        }

        public static Func of(MemorySegment MEMORY) {
            return new Func(MEMORY);
        }

        @Override
        protected GCCompatibilityPackedContainUnion construct(MemorySegment seg) {
            return new GCCompatibilityPackedContainUnion(seg);
        }
    }
}
// metadata.generator-version: pni test
// sha256:cfed04f3ecd6d42952cf79e6cb98b95d3d1654def3ed277ae51ce0cde93e0122
