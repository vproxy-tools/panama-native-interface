package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class GCCCompatibilityPackedAlignFieldSmallerAlign extends AbstractNativeObject implements NativeObject {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        ValueLayout.JAVA_BYTE.withName("b1"),
        MemoryLayout.sequenceLayout(1L, ValueLayout.JAVA_BYTE) /* padding */,
        ValueLayout.JAVA_LONG_UNALIGNED.withName("l"),
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

    private static final VarHandle lVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("l")
    );

    public long getL() {
        return (long) lVH.get(MEMORY);
    }

    public void setL(long l) {
        lVH.set(MEMORY, l);
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

    public GCCCompatibilityPackedAlignFieldSmallerAlign(MemorySegment MEMORY) {
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
        OFFSET += ValueLayout.JAVA_BYTE.byteSize();
        OFFSET += 1; /* padding */
        OFFSET += ValueLayout.JAVA_LONG_UNALIGNED.byteSize();
        OFFSET += ValueLayout.JAVA_INT_UNALIGNED.byteSize();
    }

    public GCCCompatibilityPackedAlignFieldSmallerAlign(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT));
    }

    private static final MethodHandle initMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_GCCCompatibilityPackedAlignFieldSmallerAlign_init", MemorySegment.class /* self */);

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

    private static final MethodHandle sizeMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_GCCCompatibilityPackedAlignFieldSmallerAlign_size", MemorySegment.class /* self */);

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
        SB.append("GCCCompatibilityPackedAlignFieldSmallerAlign{\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("b1 => ");
            SB.append(getB1());
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("l => ");
            SB.append(getL());
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("n2 => ");
            SB.append(getN2());
        }
        SB.append("\n");
        SB.append(" ".repeat(INDENT)).append("}@").append(Long.toString(MEMORY.address(), 16));
    }

    public static class Array extends RefArray<GCCCompatibilityPackedAlignFieldSmallerAlign> {
        public Array(MemorySegment buf) {
            super(buf, GCCCompatibilityPackedAlignFieldSmallerAlign.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            super(allocator, GCCCompatibilityPackedAlignFieldSmallerAlign.LAYOUT, len);
        }

        public Array(PNIBuf buf) {
            super(buf, GCCCompatibilityPackedAlignFieldSmallerAlign.LAYOUT);
        }

        @Override
        protected void elementToString(io.vproxy.pni.test.GCCCompatibilityPackedAlignFieldSmallerAlign ELEM, StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
            ELEM.toString(SB, INDENT, VISITED, CORRUPTED_MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "GCCCompatibilityPackedAlignFieldSmallerAlign.Array";
        }

        @Override
        protected GCCCompatibilityPackedAlignFieldSmallerAlign construct(MemorySegment seg) {
            return new GCCCompatibilityPackedAlignFieldSmallerAlign(seg);
        }

        @Override
        protected MemorySegment getSegment(GCCCompatibilityPackedAlignFieldSmallerAlign value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<GCCCompatibilityPackedAlignFieldSmallerAlign> {
        private Func(io.vproxy.pni.CallSite<GCCCompatibilityPackedAlignFieldSmallerAlign> func) {
            super(func);
        }

        private Func(io.vproxy.pni.CallSite<GCCCompatibilityPackedAlignFieldSmallerAlign> func, Options opts) {
            super(func, opts);
        }

        private Func(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static Func of(io.vproxy.pni.CallSite<GCCCompatibilityPackedAlignFieldSmallerAlign> func) {
            return new Func(func);
        }

        public static Func of(io.vproxy.pni.CallSite<GCCCompatibilityPackedAlignFieldSmallerAlign> func, Options opts) {
            return new Func(func, opts);
        }

        public static Func of(MemorySegment MEMORY) {
            return new Func(MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "GCCCompatibilityPackedAlignFieldSmallerAlign.Func";
        }

        @Override
        protected GCCCompatibilityPackedAlignFieldSmallerAlign construct(MemorySegment seg) {
            return new GCCCompatibilityPackedAlignFieldSmallerAlign(seg);
        }
    }
}
// metadata.generator-version: pni test
// sha256:8d5dd17b6173528c634b3b36ecea530dd36b0cfb51dfe93f5283bd184a121d86
