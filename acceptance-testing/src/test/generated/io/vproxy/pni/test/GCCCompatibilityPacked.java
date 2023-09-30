package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class GCCCompatibilityPacked implements NativeObject {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        ValueLayout.JAVA_BYTE.withName("b"),
        ValueLayout.JAVA_SHORT_UNALIGNED.withName("s"),
        ValueLayout.JAVA_INT_UNALIGNED.withName("n"),
        ValueLayout.JAVA_FLOAT_UNALIGNED.withName("f"),
        ValueLayout.JAVA_DOUBLE_UNALIGNED.withName("d"),
        ValueLayout.JAVA_LONG_UNALIGNED.withName("l")
    );
    public final MemorySegment MEMORY;

    @Override
    public MemorySegment MEMORY() {
        return MEMORY;
    }

    private static final VarHandle bVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("b")
    );

    public byte getB() {
        return (byte) bVH.get(MEMORY);
    }

    public void setB(byte b) {
        bVH.set(MEMORY, b);
    }

    private static final VarHandle sVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("s")
    );

    public short getS() {
        return (short) sVH.get(MEMORY);
    }

    public void setS(short s) {
        sVH.set(MEMORY, s);
    }

    private static final VarHandle nVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("n")
    );

    public int getN() {
        return (int) nVH.get(MEMORY);
    }

    public void setN(int n) {
        nVH.set(MEMORY, n);
    }

    private static final VarHandle fVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("f")
    );

    public float getF() {
        return (float) fVH.get(MEMORY);
    }

    public void setF(float f) {
        fVH.set(MEMORY, f);
    }

    private static final VarHandle dVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("d")
    );

    public double getD() {
        return (double) dVH.get(MEMORY);
    }

    public void setD(double d) {
        dVH.set(MEMORY, d);
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

    public GCCCompatibilityPacked(MemorySegment MEMORY) {
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
        OFFSET += ValueLayout.JAVA_BYTE.byteSize();
        OFFSET += ValueLayout.JAVA_SHORT_UNALIGNED.byteSize();
        OFFSET += ValueLayout.JAVA_INT_UNALIGNED.byteSize();
        OFFSET += ValueLayout.JAVA_FLOAT_UNALIGNED.byteSize();
        OFFSET += ValueLayout.JAVA_DOUBLE_UNALIGNED.byteSize();
        OFFSET += ValueLayout.JAVA_LONG_UNALIGNED.byteSize();
    }

    public GCCCompatibilityPacked(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT.byteSize()));
    }

    private static final MethodHandle initMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_GCCCompatibilityPacked_init", MemorySegment.class /* self */);

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

    private static final MethodHandle sizeMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_GCCCompatibilityPacked_size", MemorySegment.class /* self */);

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
    public String toString() {
        var sb = new StringBuilder();
        toString(sb, 0, new java.util.HashSet<>(), false);
        return sb.toString();
    }

    @Override
    public void toString(StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
        if (!VISITED.add(new NativeObjectTuple(this))) {
            SB.append("<...>@").append(Long.toString(MEMORY.address(), 16));
            return;
        }
        SB.append("GCCCompatibilityPacked{\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("b => ");
            SB.append(getB());
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("s => ");
            SB.append(getS());
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("n => ");
            SB.append(getN());
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("f => ");
            SB.append(getF());
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("d => ");
            SB.append(getD());
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("l => ");
            SB.append(getL());
        }
        SB.append("\n");
        SB.append(" ".repeat(INDENT)).append("}@").append(Long.toString(MEMORY.address(), 16));
    }

    public static class Array extends RefArray<GCCCompatibilityPacked> {
        public Array(MemorySegment buf) {
            super(buf, GCCCompatibilityPacked.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            this(allocator.allocate(GCCCompatibilityPacked.LAYOUT.byteSize() * len));
        }

        public Array(PNIBuf buf) {
            this(buf.get());
        }

        @Override
        protected void elementToString(io.vproxy.pni.test.GCCCompatibilityPacked ELEM, StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
            ELEM.toString(SB, INDENT, VISITED, CORRUPTED_MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "GCCCompatibilityPacked.Array";
        }

        @Override
        protected GCCCompatibilityPacked construct(MemorySegment seg) {
            return new GCCCompatibilityPacked(seg);
        }

        @Override
        protected MemorySegment getSegment(GCCCompatibilityPacked value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<GCCCompatibilityPacked> {
        private Func(io.vproxy.pni.CallSite<GCCCompatibilityPacked> func) {
            super(func);
        }

        private Func(io.vproxy.pni.CallSite<GCCCompatibilityPacked> func, Options opts) {
            super(func, opts);
        }

        private Func(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static Func of(io.vproxy.pni.CallSite<GCCCompatibilityPacked> func) {
            return new Func(func);
        }

        public static Func of(io.vproxy.pni.CallSite<GCCCompatibilityPacked> func, Options opts) {
            return new Func(func, opts);
        }

        public static Func of(MemorySegment MEMORY) {
            return new Func(MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "GCCCompatibilityPacked.Func";
        }

        @Override
        protected GCCCompatibilityPacked construct(MemorySegment seg) {
            return new GCCCompatibilityPacked(seg);
        }
    }
}
// metadata.generator-version: pni test
// sha256:9b406b34c9ea5e89383e314326a3e79e744b739a7e6069ed0d2838f8cc02ecad
