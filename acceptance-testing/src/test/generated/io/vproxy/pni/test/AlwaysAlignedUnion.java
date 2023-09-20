package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class AlwaysAlignedUnion {
    public static final MemoryLayout LAYOUT = MemoryLayout.unionLayout(
        ValueLayout.JAVA_SHORT.withName("a"),
        ValueLayout.JAVA_INT.withName("b"),
        ValueLayout.JAVA_LONG.withName("c")
    );
    public final MemorySegment MEMORY;

    private static final VarHandle aVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("a")
    );

    public short getA() {
        return (short) aVH.get(MEMORY);
    }

    public void setA(short a) {
        aVH.set(MEMORY, a);
    }

    private static final VarHandle bVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("b")
    );

    public int getB() {
        return (int) bVH.get(MEMORY);
    }

    public void setB(int b) {
        bVH.set(MEMORY, b);
    }

    private static final VarHandle cVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("c")
    );

    public long getC() {
        return (long) cVH.get(MEMORY);
    }

    public void setC(long c) {
        cVH.set(MEMORY, c);
    }

    public AlwaysAlignedUnion(MemorySegment MEMORY) {
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
        OFFSET += ValueLayout.JAVA_SHORT_UNALIGNED.byteSize();
        OFFSET = 0;
        OFFSET += ValueLayout.JAVA_INT_UNALIGNED.byteSize();
        OFFSET = 0;
        OFFSET += ValueLayout.JAVA_LONG_UNALIGNED.byteSize();
        OFFSET = 0;
    }

    public AlwaysAlignedUnion(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT.byteSize()));
    }

    public static class Array extends RefArray<AlwaysAlignedUnion> {
        public Array(MemorySegment buf) {
            super(buf, AlwaysAlignedUnion.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            this(allocator.allocate(AlwaysAlignedUnion.LAYOUT.byteSize() * len));
        }

        public Array(PNIBuf buf) {
            this(buf.get());
        }

        @Override
        protected AlwaysAlignedUnion construct(MemorySegment seg) {
            return new AlwaysAlignedUnion(seg);
        }

        @Override
        protected MemorySegment getSegment(AlwaysAlignedUnion value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<AlwaysAlignedUnion> {
        private Func(io.vproxy.pni.CallSite<AlwaysAlignedUnion> func) {
            super(func);
        }

        private Func(io.vproxy.pni.CallSite<AlwaysAlignedUnion> func, Options opts) {
            super(func, opts);
        }

        private Func(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static Func of(io.vproxy.pni.CallSite<AlwaysAlignedUnion> func) {
            return new Func(func);
        }

        public static Func of(io.vproxy.pni.CallSite<AlwaysAlignedUnion> func, Options opts) {
            return new Func(func, opts);
        }

        public static Func of(MemorySegment MEMORY) {
            return new Func(MEMORY);
        }

        @Override
        protected AlwaysAlignedUnion construct(MemorySegment seg) {
            return new AlwaysAlignedUnion(seg);
        }
    }
}
// metadata.generator-version: pni test
// sha256:16e4f48bdf0526e3c6a10fd9c21a82cbec653223e3b76e74dc8d9256f87c905d
