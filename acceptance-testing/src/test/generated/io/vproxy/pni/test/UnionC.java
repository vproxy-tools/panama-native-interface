package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class UnionC {
    public static final MemoryLayout LAYOUT = MemoryLayout.unionLayout(
        ValueLayout.JAVA_INT_UNALIGNED.withName("n"),
        ValueLayout.JAVA_LONG_UNALIGNED.withName("l")
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

    private static final VarHandle lVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("l")
    );

    public long getL() {
        return (long) lVH.get(MEMORY);
    }

    public void setL(long l) {
        lVH.set(MEMORY, l);
    }

    public UnionC(MemorySegment MEMORY) {
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
        OFFSET += ValueLayout.JAVA_INT_UNALIGNED.byteSize();
        OFFSET = 0;
        OFFSET += ValueLayout.JAVA_LONG_UNALIGNED.byteSize();
        OFFSET = 0;
    }

    public UnionC(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT.byteSize()));
    }

    public static class Array extends RefArray<UnionC> {
        public Array(MemorySegment buf) {
            super(buf, UnionC.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            this(allocator.allocate(UnionC.LAYOUT.byteSize() * len));
        }

        public Array(PNIBuf buf) {
            this(buf.get());
        }

        @Override
        protected UnionC construct(MemorySegment seg) {
            return new UnionC(seg);
        }

        @Override
        protected MemorySegment getSegment(UnionC value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<UnionC> {
        private Func(io.vproxy.pni.CallSite<UnionC> func) {
            super(func);
        }

        public static Func of(io.vproxy.pni.CallSite<UnionC> func) {
            return new Func(func);
        }

        @Override
        protected UnionC construct(MemorySegment seg) {
            return new UnionC(seg);
        }

        @Override
        protected MemorySegment getSegment(UnionC value) {
            return value.MEMORY;
        }
    }
}
// sha256:87d0bc138f452dbded7b533b4bc776ebe8e26d9a7f1947003d5ce6227ed7d3a2
