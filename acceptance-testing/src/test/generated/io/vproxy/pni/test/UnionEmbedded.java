package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.hack.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class UnionEmbedded extends AbstractNativeObject implements NativeObject {
    public static final MemoryLayout LAYOUT = MemoryLayout.unionLayout(
        ValueLayout.JAVA_INT_UNALIGNED.withName("n"),
        ValueLayout.ADDRESS_UNALIGNED.withName("seg")
    );
    public final MemorySegment MEMORY;

    @Override
    public MemorySegment MEMORY() {
        return MEMORY;
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

    private static final VarHandleW segVH = VarHandleW.of(
        LAYOUT.varHandle(
            MemoryLayout.PathElement.groupElement("seg")
        )
    );

    public MemorySegment getSeg() {
        var SEG = segVH.getMemorySegment(MEMORY);
        if (SEG.address() == 0) return null;
        return SEG;
    }

    public void setSeg(MemorySegment seg) {
        if (seg == null) {
            segVH.set(MEMORY, MemorySegment.NULL);
        } else {
            segVH.set(MEMORY, seg);
        }
    }

    public UnionEmbedded(MemorySegment MEMORY) {
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
        OFFSET += ValueLayout.JAVA_INT_UNALIGNED.byteSize();
        OFFSET = 0;
        OFFSET += ValueLayout.ADDRESS_UNALIGNED.byteSize();
        OFFSET = 0;
    }

    public UnionEmbedded(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT));
    }

    @Override
    public void toString(StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
        if (!VISITED.add(new NativeObjectTuple(this))) {
            SB.append("<...>@").append(Long.toString(MEMORY.address(), 16));
            return;
        }
        CORRUPTED_MEMORY = true;
        SB.append("UnionEmbedded(\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("n => ");
            SB.append(getN());
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("seg => ");
            SB.append(PanamaUtils.memorySegmentToString(getSeg()));
        }
        SB.append("\n");
        SB.append(" ".repeat(INDENT)).append(")@").append(Long.toString(MEMORY.address(), 16));
    }

    public static class Array extends RefArray<UnionEmbedded> {
        public Array(MemorySegment buf) {
            super(buf, UnionEmbedded.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            super(allocator, UnionEmbedded.LAYOUT, len);
        }

        public Array(PNIBuf buf) {
            super(buf, UnionEmbedded.LAYOUT);
        }

        @Override
        protected void elementToString(io.vproxy.pni.test.UnionEmbedded ELEM, StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
            ELEM.toString(SB, INDENT, VISITED, CORRUPTED_MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "UnionEmbedded.Array";
        }

        @Override
        protected UnionEmbedded construct(MemorySegment seg) {
            return new UnionEmbedded(seg);
        }

        @Override
        protected MemorySegment getSegment(UnionEmbedded value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<UnionEmbedded> {
        private Func(io.vproxy.pni.CallSite<UnionEmbedded> func) {
            super(func);
        }

        private Func(io.vproxy.pni.CallSite<UnionEmbedded> func, Options opts) {
            super(func, opts);
        }

        private Func(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static Func of(io.vproxy.pni.CallSite<UnionEmbedded> func) {
            return new Func(func);
        }

        public static Func of(io.vproxy.pni.CallSite<UnionEmbedded> func, Options opts) {
            return new Func(func, opts);
        }

        public static Func of(MemorySegment MEMORY) {
            return new Func(MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "UnionEmbedded.Func";
        }

        @Override
        protected UnionEmbedded construct(MemorySegment seg) {
            return new UnionEmbedded(seg);
        }
    }
}
// metadata.generator-version: pni test
// sha256:26ade4e31c58f66c8d9c854e594697943af67b3265b7648c5fb62b8726f0330d
