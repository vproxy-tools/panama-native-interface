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

    private final MethodHandle initB = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_GCCompatibilityPackedContainUnion_initB", MemorySegment.class /* self */);

    public void initB(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.initB.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
    }

    private final MethodHandle initS = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_GCCompatibilityPackedContainUnion_initS", MemorySegment.class /* self */);

    public void initS(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.initS.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
    }

    private final MethodHandle initN = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_GCCompatibilityPackedContainUnion_initN", MemorySegment.class /* self */);

    public void initN(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.initN.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
    }

    private final MethodHandle initF = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_GCCompatibilityPackedContainUnion_initF", MemorySegment.class /* self */);

    public void initF(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.initF.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
    }

    private final MethodHandle initD = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_GCCompatibilityPackedContainUnion_initD", MemorySegment.class /* self */);

    public void initD(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.initD.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
    }

    private final MethodHandle initL = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_GCCompatibilityPackedContainUnion_initL", MemorySegment.class /* self */);

    public void initL(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.initL.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
    }

    private final MethodHandle size = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_GCCompatibilityPackedContainUnion_size", MemorySegment.class /* self */);

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

    public static class Array extends RefArray<GCCompatibilityPackedContainUnion> {
        public Array(MemorySegment buf) {
            super(buf, GCCompatibilityPackedContainUnion.LAYOUT);
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

        public static Func of(io.vproxy.pni.CallSite<GCCompatibilityPackedContainUnion> func) {
            return new Func(func);
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
}
// sha256:696e53ef31275fdc3c0c1165cc54f9849d46d7cddcd2e2d6ec4400056aef8e86
