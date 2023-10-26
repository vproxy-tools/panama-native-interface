package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class GCCompatibilityPackedContainUnion extends AbstractNativeObject implements NativeObject {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        ValueLayout.JAVA_BYTE.withName("b1"),
        io.vproxy.pni.test.GCCCompatibilityUnion.LAYOUT.withName("un"),
        ValueLayout.JAVA_INT_UNALIGNED.withName("n2")
    );
    public final MemorySegment MEMORY;

    @Override
    public MemorySegment MEMORY() {
        return MEMORY;
    }

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
        this(ALLOCATOR.allocate(LAYOUT));
    }

    private static final MethodHandle initBMH = PanamaUtils.lookupPNIFunction(new PNILinkOptions(), "Java_io_vproxy_pni_test_GCCompatibilityPackedContainUnion_initB", MemorySegment.class /* self */);

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

    private static final MethodHandle initSMH = PanamaUtils.lookupPNIFunction(new PNILinkOptions(), "Java_io_vproxy_pni_test_GCCompatibilityPackedContainUnion_initS", MemorySegment.class /* self */);

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

    private static final MethodHandle initNMH = PanamaUtils.lookupPNIFunction(new PNILinkOptions(), "Java_io_vproxy_pni_test_GCCompatibilityPackedContainUnion_initN", MemorySegment.class /* self */);

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

    private static final MethodHandle initFMH = PanamaUtils.lookupPNIFunction(new PNILinkOptions(), "Java_io_vproxy_pni_test_GCCompatibilityPackedContainUnion_initF", MemorySegment.class /* self */);

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

    private static final MethodHandle initDMH = PanamaUtils.lookupPNIFunction(new PNILinkOptions(), "Java_io_vproxy_pni_test_GCCompatibilityPackedContainUnion_initD", MemorySegment.class /* self */);

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

    private static final MethodHandle initLMH = PanamaUtils.lookupPNIFunction(new PNILinkOptions(), "Java_io_vproxy_pni_test_GCCompatibilityPackedContainUnion_initL", MemorySegment.class /* self */);

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

    private static final MethodHandle sizeMH = PanamaUtils.lookupPNIFunction(new PNILinkOptions(), "Java_io_vproxy_pni_test_GCCompatibilityPackedContainUnion_size", MemorySegment.class /* self */);

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

    @Override
    public void toString(StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
        if (!VISITED.add(new NativeObjectTuple(this))) {
            SB.append("<...>@").append(Long.toString(MEMORY.address(), 16));
            return;
        }
        SB.append("GCCompatibilityPackedContainUnion{\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("b1 => ");
            SB.append(getB1());
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("un => ");
            PanamaUtils.nativeObjectToString(getUn(), SB, INDENT + 4, VISITED, CORRUPTED_MEMORY);
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("n2 => ");
            SB.append(getN2());
        }
        SB.append("\n");
        SB.append(" ".repeat(INDENT)).append("}@").append(Long.toString(MEMORY.address(), 16));
    }

    public static class Array extends RefArray<GCCompatibilityPackedContainUnion> {
        public Array(MemorySegment buf) {
            super(buf, GCCompatibilityPackedContainUnion.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            super(allocator, GCCompatibilityPackedContainUnion.LAYOUT, len);
        }

        public Array(PNIBuf buf) {
            super(buf, GCCompatibilityPackedContainUnion.LAYOUT);
        }

        @Override
        protected void elementToString(io.vproxy.pni.test.GCCompatibilityPackedContainUnion ELEM, StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
            ELEM.toString(SB, INDENT, VISITED, CORRUPTED_MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "GCCompatibilityPackedContainUnion.Array";
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

        private Func(io.vproxy.pni.CallSite<GCCompatibilityPackedContainUnion> func, Options opts) {
            super(func, opts);
        }

        private Func(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static Func of(io.vproxy.pni.CallSite<GCCompatibilityPackedContainUnion> func) {
            return new Func(func);
        }

        public static Func of(io.vproxy.pni.CallSite<GCCompatibilityPackedContainUnion> func, Options opts) {
            return new Func(func, opts);
        }

        public static Func of(MemorySegment MEMORY) {
            return new Func(MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "GCCompatibilityPackedContainUnion.Func";
        }

        @Override
        protected GCCompatibilityPackedContainUnion construct(MemorySegment seg) {
            return new GCCompatibilityPackedContainUnion(seg);
        }
    }
}
// metadata.generator-version: pni test
// sha256:5b53058a0f4cdff22a9278b4ee6976b85600a129342ab38fab1706da78bcb01e
