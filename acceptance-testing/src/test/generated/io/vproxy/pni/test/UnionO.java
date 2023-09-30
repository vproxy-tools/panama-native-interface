package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class UnionO implements NativeObject {
    public static final MemoryLayout LAYOUT = MemoryLayout.unionLayout(
        ValueLayout.JAVA_SHORT_UNALIGNED.withName("s"),
        ValueLayout.JAVA_INT_UNALIGNED.withName("i"),
        io.vproxy.pni.test.UnionP.LAYOUT.withName("p")
    );
    public final MemorySegment MEMORY;

    @Override
    public MemorySegment MEMORY() {
        return MEMORY;
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

    private static final VarHandle iVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("i")
    );

    public int getI() {
        return (int) iVH.get(MEMORY);
    }

    public void setI(int i) {
        iVH.set(MEMORY, i);
    }

    private final io.vproxy.pni.test.UnionP p;

    public io.vproxy.pni.test.UnionP getP() {
        return this.p;
    }

    public UnionO(MemorySegment MEMORY) {
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
        OFFSET += ValueLayout.JAVA_SHORT_UNALIGNED.byteSize();
        OFFSET = 0;
        OFFSET += ValueLayout.JAVA_INT_UNALIGNED.byteSize();
        OFFSET = 0;
        this.p = new io.vproxy.pni.test.UnionP(MEMORY.asSlice(OFFSET, io.vproxy.pni.test.UnionP.LAYOUT.byteSize()));
        OFFSET += io.vproxy.pni.test.UnionP.LAYOUT.byteSize();
        OFFSET = 0;
    }

    public UnionO(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT.byteSize()));
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        toString(sb, 0, new java.util.HashSet<>(), true);
        return sb.toString();
    }

    @Override
    public void toString(StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
        if (!VISITED.add(new NativeObjectTuple(this))) {
            SB.append("<...>@").append(Long.toString(MEMORY.address(), 16));
            return;
        }
        CORRUPTED_MEMORY = true;
        SB.append("UnionO(\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("s => ");
            SB.append(getS());
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("i => ");
            SB.append(getI());
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("p => ");
            PanamaUtils.nativeObjectToString(getP(), SB, INDENT + 4, VISITED, CORRUPTED_MEMORY);
        }
        SB.append("\n");
        SB.append(" ".repeat(INDENT)).append(")@").append(Long.toString(MEMORY.address(), 16));
    }

    public static class Array extends RefArray<UnionO> {
        public Array(MemorySegment buf) {
            super(buf, UnionO.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            this(allocator.allocate(UnionO.LAYOUT.byteSize() * len));
        }

        public Array(PNIBuf buf) {
            this(buf.get());
        }

        @Override
        protected void elementToString(io.vproxy.pni.test.UnionO ELEM, StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
            ELEM.toString(SB, INDENT, VISITED, CORRUPTED_MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "UnionO.Array";
        }

        @Override
        protected UnionO construct(MemorySegment seg) {
            return new UnionO(seg);
        }

        @Override
        protected MemorySegment getSegment(UnionO value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<UnionO> {
        private Func(io.vproxy.pni.CallSite<UnionO> func) {
            super(func);
        }

        private Func(io.vproxy.pni.CallSite<UnionO> func, Options opts) {
            super(func, opts);
        }

        private Func(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static Func of(io.vproxy.pni.CallSite<UnionO> func) {
            return new Func(func);
        }

        public static Func of(io.vproxy.pni.CallSite<UnionO> func, Options opts) {
            return new Func(func, opts);
        }

        public static Func of(MemorySegment MEMORY) {
            return new Func(MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "UnionO.Func";
        }

        @Override
        protected UnionO construct(MemorySegment seg) {
            return new UnionO(seg);
        }
    }
}
// metadata.generator-version: pni test
// sha256:cbdddcef32c7932ec5e957bebf05dcd47259e5af733318bbe988ab90c7ed0a9d
