package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.hack.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class GCCCompatibilityUnion extends AbstractNativeObject implements NativeObject {
    public static final MemoryLayout LAYOUT = MemoryLayout.unionLayout(
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

    private static final VarHandleW bVH = VarHandleW.of(
        LAYOUT.varHandle(
            MemoryLayout.PathElement.groupElement("b")
        )
    );

    public byte getB() {
        return bVH.getByte(MEMORY);
    }

    public void setB(byte b) {
        bVH.set(MEMORY, b);
    }

    private static final VarHandleW sVH = VarHandleW.of(
        LAYOUT.varHandle(
            MemoryLayout.PathElement.groupElement("s")
        )
    );

    public short getS() {
        return sVH.getShort(MEMORY);
    }

    public void setS(short s) {
        sVH.set(MEMORY, s);
    }

    private static final VarHandleW nVH = VarHandleW.of(
        LAYOUT.varHandle(
            MemoryLayout.PathElement.groupElement("n")
        )
    );

    public int getN() {
        return nVH.getInt(MEMORY);
    }

    public void setN(int n) {
        nVH.set(MEMORY, n);
    }

    private static final VarHandleW fVH = VarHandleW.of(
        LAYOUT.varHandle(
            MemoryLayout.PathElement.groupElement("f")
        )
    );

    public float getF() {
        return fVH.getFloat(MEMORY);
    }

    public void setF(float f) {
        fVH.set(MEMORY, f);
    }

    private static final VarHandleW dVH = VarHandleW.of(
        LAYOUT.varHandle(
            MemoryLayout.PathElement.groupElement("d")
        )
    );

    public double getD() {
        return dVH.getDouble(MEMORY);
    }

    public void setD(double d) {
        dVH.set(MEMORY, d);
    }

    private static final VarHandleW lVH = VarHandleW.of(
        LAYOUT.varHandle(
            MemoryLayout.PathElement.groupElement("l")
        )
    );

    public long getL() {
        return lVH.getLong(MEMORY);
    }

    public void setL(long l) {
        lVH.set(MEMORY, l);
    }

    public GCCCompatibilityUnion(MemorySegment MEMORY) {
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
        OFFSET += ValueLayout.JAVA_BYTE.byteSize();
        OFFSET = 0;
        OFFSET += ValueLayout.JAVA_SHORT_UNALIGNED.byteSize();
        OFFSET = 0;
        OFFSET += ValueLayout.JAVA_INT_UNALIGNED.byteSize();
        OFFSET = 0;
        OFFSET += ValueLayout.JAVA_FLOAT_UNALIGNED.byteSize();
        OFFSET = 0;
        OFFSET += ValueLayout.JAVA_DOUBLE_UNALIGNED.byteSize();
        OFFSET = 0;
        OFFSET += ValueLayout.JAVA_LONG_UNALIGNED.byteSize();
        OFFSET = 0;
    }

    public GCCCompatibilityUnion(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT));
    }

    @Override
    public void toString(StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
        if (!VISITED.add(new NativeObjectTuple(this))) {
            SB.append("<...>@").append(Long.toString(MEMORY.address(), 16));
            return;
        }
        CORRUPTED_MEMORY = true;
        SB.append("GCCCompatibilityUnion(\n");
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
        SB.append(" ".repeat(INDENT)).append(")@").append(Long.toString(MEMORY.address(), 16));
    }

    public static class Array extends RefArray<GCCCompatibilityUnion> {
        public Array(MemorySegment buf) {
            super(buf, GCCCompatibilityUnion.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            super(allocator, GCCCompatibilityUnion.LAYOUT, len);
        }

        public Array(PNIBuf buf) {
            super(buf, GCCCompatibilityUnion.LAYOUT);
        }

        @Override
        protected void elementToString(io.vproxy.pni.test.GCCCompatibilityUnion ELEM, StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
            ELEM.toString(SB, INDENT, VISITED, CORRUPTED_MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "GCCCompatibilityUnion.Array";
        }

        @Override
        protected GCCCompatibilityUnion construct(MemorySegment seg) {
            return new GCCCompatibilityUnion(seg);
        }

        @Override
        protected MemorySegment getSegment(GCCCompatibilityUnion value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<GCCCompatibilityUnion> {
        private Func(io.vproxy.pni.CallSite<GCCCompatibilityUnion> func) {
            super(func);
        }

        private Func(io.vproxy.pni.CallSite<GCCCompatibilityUnion> func, Options opts) {
            super(func, opts);
        }

        private Func(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static Func of(io.vproxy.pni.CallSite<GCCCompatibilityUnion> func) {
            return new Func(func);
        }

        public static Func of(io.vproxy.pni.CallSite<GCCCompatibilityUnion> func, Options opts) {
            return new Func(func, opts);
        }

        public static Func of(MemorySegment MEMORY) {
            return new Func(MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "GCCCompatibilityUnion.Func";
        }

        @Override
        protected GCCCompatibilityUnion construct(MemorySegment seg) {
            return new GCCCompatibilityUnion(seg);
        }
    }
}
// metadata.generator-version: pni test
// sha256:f00120835c93fc807609a7a0344a163d744f3d251c72ba84eff058b741925c35
