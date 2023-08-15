package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class UnionEmbedded {
    public static final MemoryLayout LAYOUT = MemoryLayout.unionLayout(
        ValueLayout.JAVA_INT_UNALIGNED.withName("n"),
        ValueLayout.ADDRESS_UNALIGNED.withName("seg")
    );
    public final MemorySegment MEMORY;

    private static final VarHandle nVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("n")
    );

    public int getN() {
        return (int) nVH.get(MEMORY);
    }

    public void setN(int n) {
        nVH.set(MEMORY, n);
    }

    private static final VarHandle segVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("seg")
    );

    public MemorySegment getSeg() {
        var SEG = (MemorySegment) segVH.get(MEMORY);
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
        this(ALLOCATOR.allocate(LAYOUT.byteSize()));
    }

    public static class Array extends RefArray<UnionEmbedded> {
        public Array(MemorySegment buf) {
            super(buf, UnionEmbedded.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            this(allocator.allocate(UnionEmbedded.LAYOUT.byteSize() * len));
        }

        public Array(PNIBuf buf) {
            this(buf.get());
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

        private Func(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static Func of(io.vproxy.pni.CallSite<UnionEmbedded> func) {
            return new Func(func);
        }

        public static Func of(MemorySegment MEMORY) {
            return new Func(MEMORY);
        }

        @Override
        protected UnionEmbedded construct(MemorySegment seg) {
            return new UnionEmbedded(seg);
        }
    }
}
// metadata.generator-version: pni test
// sha256:a02fd14b1f628101f1559c38d2ff02e3b56f68e3e2ece6b8306ef0e2c7e81e0b
