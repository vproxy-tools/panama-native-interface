package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class UnionO {
    public static final MemoryLayout LAYOUT = MemoryLayout.unionLayout(
        ValueLayout.JAVA_SHORT_UNALIGNED.withName("s"),
        ValueLayout.JAVA_INT_UNALIGNED.withName("i"),
        io.vproxy.pni.test.UnionP.LAYOUT.withName("p")
    );
    public final MemorySegment MEMORY;

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

        private Func(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static Func of(io.vproxy.pni.CallSite<UnionO> func) {
            return new Func(func);
        }

        public static Func of(MemorySegment MEMORY) {
            return new Func(MEMORY);
        }

        @Override
        protected UnionO construct(MemorySegment seg) {
            return new UnionO(seg);
        }
    }
}
// metadata.generator-version: pni test
// sha256:a7b1ec4283de0383e8c0b12d98b5d41ec7e17bb1a10faf4f946a7bedca2cd36b
