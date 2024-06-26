package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.hack.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class GCCompatibilityNormalContainUnion extends AbstractNativeObject implements NativeObject {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        ValueLayout.JAVA_BYTE.withName("b1"),
        MemoryLayout.sequenceLayout(7L, ValueLayout.JAVA_BYTE) /* padding */,
        io.vproxy.pni.test.GCCCompatibilityUnion.LAYOUT.withName("un"),
        ValueLayout.JAVA_INT_UNALIGNED.withName("n2"),
        MemoryLayout.sequenceLayout(4L, ValueLayout.JAVA_BYTE) /* padding */
    );
    public final MemorySegment MEMORY;

    @Override
    public MemorySegment MEMORY() {
        return MEMORY;
    }

    private static final VarHandleW b1VH = VarHandleW.of(
        LAYOUT.varHandle(
            MemoryLayout.PathElement.groupElement("b1")
        )
    );

    public byte getB1() {
        return b1VH.getByte(MEMORY);
    }

    public void setB1(byte b1) {
        b1VH.set(MEMORY, b1);
    }

    private final io.vproxy.pni.test.GCCCompatibilityUnion un;

    public io.vproxy.pni.test.GCCCompatibilityUnion getUn() {
        return this.un;
    }

    private static final VarHandleW n2VH = VarHandleW.of(
        LAYOUT.varHandle(
            MemoryLayout.PathElement.groupElement("n2")
        )
    );

    public int getN2() {
        return n2VH.getInt(MEMORY);
    }

    public void setN2(int n2) {
        n2VH.set(MEMORY, n2);
    }

    public GCCompatibilityNormalContainUnion(MemorySegment MEMORY) {
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
        OFFSET += ValueLayout.JAVA_BYTE.byteSize();
        OFFSET += 7; /* padding */
        this.un = new io.vproxy.pni.test.GCCCompatibilityUnion(MEMORY.asSlice(OFFSET, io.vproxy.pni.test.GCCCompatibilityUnion.LAYOUT.byteSize()));
        OFFSET += io.vproxy.pni.test.GCCCompatibilityUnion.LAYOUT.byteSize();
        OFFSET += ValueLayout.JAVA_INT_UNALIGNED.byteSize();
        OFFSET += 4; /* padding */
    }

    public GCCompatibilityNormalContainUnion(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT));
    }

    private static final MethodHandle initBMH = PanamaUtils.lookupPNIFunction(new PNILinkOptions(), "Java_io_vproxy_pni_test_GCCompatibilityNormalContainUnion_initB", MemorySegment.class /* self */);

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

    private static final MethodHandle initSMH = PanamaUtils.lookupPNIFunction(new PNILinkOptions(), "Java_io_vproxy_pni_test_GCCompatibilityNormalContainUnion_initS", MemorySegment.class /* self */);

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

    private static final MethodHandle initNMH = PanamaUtils.lookupPNIFunction(new PNILinkOptions(), "Java_io_vproxy_pni_test_GCCompatibilityNormalContainUnion_initN", MemorySegment.class /* self */);

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

    private static final MethodHandle initFMH = PanamaUtils.lookupPNIFunction(new PNILinkOptions(), "Java_io_vproxy_pni_test_GCCompatibilityNormalContainUnion_initF", MemorySegment.class /* self */);

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

    private static final MethodHandle initDMH = PanamaUtils.lookupPNIFunction(new PNILinkOptions(), "Java_io_vproxy_pni_test_GCCompatibilityNormalContainUnion_initD", MemorySegment.class /* self */);

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

    private static final MethodHandle initLMH = PanamaUtils.lookupPNIFunction(new PNILinkOptions(), "Java_io_vproxy_pni_test_GCCompatibilityNormalContainUnion_initL", MemorySegment.class /* self */);

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

    private static final MethodHandle sizeMH = PanamaUtils.lookupPNIFunction(new PNILinkOptions(), "Java_io_vproxy_pni_test_GCCompatibilityNormalContainUnion_size", MemorySegment.class /* self */);

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
        SB.append("GCCompatibilityNormalContainUnion{\n");
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

    public static class Array extends RefArray<GCCompatibilityNormalContainUnion> {
        public Array(MemorySegment buf) {
            super(buf, GCCompatibilityNormalContainUnion.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            super(allocator, GCCompatibilityNormalContainUnion.LAYOUT, len);
        }

        public Array(PNIBuf buf) {
            super(buf, GCCompatibilityNormalContainUnion.LAYOUT);
        }

        @Override
        protected void elementToString(io.vproxy.pni.test.GCCompatibilityNormalContainUnion ELEM, StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
            ELEM.toString(SB, INDENT, VISITED, CORRUPTED_MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "GCCompatibilityNormalContainUnion.Array";
        }

        @Override
        protected GCCompatibilityNormalContainUnion construct(MemorySegment seg) {
            return new GCCompatibilityNormalContainUnion(seg);
        }

        @Override
        protected MemorySegment getSegment(GCCompatibilityNormalContainUnion value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<GCCompatibilityNormalContainUnion> {
        private Func(io.vproxy.pni.CallSite<GCCompatibilityNormalContainUnion> func) {
            super(func);
        }

        private Func(io.vproxy.pni.CallSite<GCCompatibilityNormalContainUnion> func, Options opts) {
            super(func, opts);
        }

        private Func(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static Func of(io.vproxy.pni.CallSite<GCCompatibilityNormalContainUnion> func) {
            return new Func(func);
        }

        public static Func of(io.vproxy.pni.CallSite<GCCompatibilityNormalContainUnion> func, Options opts) {
            return new Func(func, opts);
        }

        public static Func of(MemorySegment MEMORY) {
            return new Func(MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "GCCompatibilityNormalContainUnion.Func";
        }

        @Override
        protected GCCompatibilityNormalContainUnion construct(MemorySegment seg) {
            return new GCCompatibilityNormalContainUnion(seg);
        }
    }
}
// metadata.generator-version: pni test
// sha256:816d50e7cd7fdecd5a3a485bc98f778cfa668144cf7a8949ff28b2e9565b8fb4
