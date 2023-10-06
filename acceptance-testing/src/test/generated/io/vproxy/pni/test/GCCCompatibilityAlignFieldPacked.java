package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class GCCCompatibilityAlignFieldPacked extends AbstractNativeObject implements NativeObject {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        ValueLayout.JAVA_BYTE.withName("b1"),
        MemoryLayout.sequenceLayout(3L, ValueLayout.JAVA_BYTE) /* padding */,
        io.vproxy.pni.test.GCCCompatibilityPacked.LAYOUT.withName("packed"),
        MemoryLayout.sequenceLayout(1L, ValueLayout.JAVA_BYTE) /* padding */,
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
        this(ALLOCATOR.allocate(LAYOUT));
    }

    private static final MethodHandle initMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_GCCCompatibilityAlignFieldPacked_init", MemorySegment.class /* self */);

    public void init(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) initMH.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
    }

    private static final MethodHandle sizeMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_GCCCompatibilityAlignFieldPacked_size", MemorySegment.class /* self */);

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
        SB.append("GCCCompatibilityAlignFieldPacked{\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("b1 => ");
            SB.append(getB1());
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("packed => ");
            PanamaUtils.nativeObjectToString(getPacked(), SB, INDENT + 4, VISITED, CORRUPTED_MEMORY);
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("n2 => ");
            SB.append(getN2());
        }
        SB.append("\n");
        SB.append(" ".repeat(INDENT)).append("}@").append(Long.toString(MEMORY.address(), 16));
    }

    public static class Array extends RefArray<GCCCompatibilityAlignFieldPacked> {
        public Array(MemorySegment buf) {
            super(buf, GCCCompatibilityAlignFieldPacked.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            super(allocator, GCCCompatibilityAlignFieldPacked.LAYOUT, len);
        }

        public Array(PNIBuf buf) {
            super(buf, GCCCompatibilityAlignFieldPacked.LAYOUT);
        }

        @Override
        protected void elementToString(io.vproxy.pni.test.GCCCompatibilityAlignFieldPacked ELEM, StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
            ELEM.toString(SB, INDENT, VISITED, CORRUPTED_MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "GCCCompatibilityAlignFieldPacked.Array";
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

        private Func(io.vproxy.pni.CallSite<GCCCompatibilityAlignFieldPacked> func, Options opts) {
            super(func, opts);
        }

        private Func(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static Func of(io.vproxy.pni.CallSite<GCCCompatibilityAlignFieldPacked> func) {
            return new Func(func);
        }

        public static Func of(io.vproxy.pni.CallSite<GCCCompatibilityAlignFieldPacked> func, Options opts) {
            return new Func(func, opts);
        }

        public static Func of(MemorySegment MEMORY) {
            return new Func(MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "GCCCompatibilityAlignFieldPacked.Func";
        }

        @Override
        protected GCCCompatibilityAlignFieldPacked construct(MemorySegment seg) {
            return new GCCCompatibilityAlignFieldPacked(seg);
        }
    }
}
// metadata.generator-version: pni test
// sha256:97e441fddcb9a2fd43e23b4541738bd4e18c3ba01d0463587cf8a26433e3aede
