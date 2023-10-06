package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class GCCCompatibilityNonPackedContainPacked extends AbstractNativeObject implements NativeObject {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        ValueLayout.JAVA_BYTE.withName("b1"),
        io.vproxy.pni.test.GCCCompatibilityPacked.LAYOUT.withName("packed"),
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
        this(ALLOCATOR.allocate(LAYOUT));
    }

    private static final MethodHandle initMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_GCCCompatibilityNonPackedContainPacked_init", MemorySegment.class /* self */);

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

    private static final MethodHandle sizeMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_GCCCompatibilityNonPackedContainPacked_size", MemorySegment.class /* self */);

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
        SB.append("GCCCompatibilityNonPackedContainPacked{\n");
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

    public static class Array extends RefArray<GCCCompatibilityNonPackedContainPacked> {
        public Array(MemorySegment buf) {
            super(buf, GCCCompatibilityNonPackedContainPacked.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            super(allocator, GCCCompatibilityNonPackedContainPacked.LAYOUT, len);
        }

        public Array(PNIBuf buf) {
            super(buf, GCCCompatibilityNonPackedContainPacked.LAYOUT);
        }

        @Override
        protected void elementToString(io.vproxy.pni.test.GCCCompatibilityNonPackedContainPacked ELEM, StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
            ELEM.toString(SB, INDENT, VISITED, CORRUPTED_MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "GCCCompatibilityNonPackedContainPacked.Array";
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

        private Func(io.vproxy.pni.CallSite<GCCCompatibilityNonPackedContainPacked> func, Options opts) {
            super(func, opts);
        }

        private Func(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static Func of(io.vproxy.pni.CallSite<GCCCompatibilityNonPackedContainPacked> func) {
            return new Func(func);
        }

        public static Func of(io.vproxy.pni.CallSite<GCCCompatibilityNonPackedContainPacked> func, Options opts) {
            return new Func(func, opts);
        }

        public static Func of(MemorySegment MEMORY) {
            return new Func(MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "GCCCompatibilityNonPackedContainPacked.Func";
        }

        @Override
        protected GCCCompatibilityNonPackedContainPacked construct(MemorySegment seg) {
            return new GCCCompatibilityNonPackedContainPacked(seg);
        }
    }
}
// metadata.generator-version: pni test
// sha256:dc6aebc7dbb51480d6201ffd396fc4aa5b9e6735b9a8447762a60b280e3dfb41
