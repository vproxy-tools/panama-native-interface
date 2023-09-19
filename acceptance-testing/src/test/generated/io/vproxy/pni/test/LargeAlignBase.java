package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class LargeAlignBase {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        ValueLayout.JAVA_LONG_UNALIGNED.withName("x")
    );
    public final MemorySegment MEMORY;

    private static final VarHandle xVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("x")
    );

    public long getX() {
        return (long) xVH.get(MEMORY);
    }

    public void setX(long x) {
        xVH.set(MEMORY, x);
    }

    public LargeAlignBase(MemorySegment MEMORY) {
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
        OFFSET += ValueLayout.JAVA_LONG_UNALIGNED.byteSize();
    }

    public LargeAlignBase(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT.byteSize()));
    }

    public static class Array extends RefArray<LargeAlignBase> {
        public Array(MemorySegment buf) {
            super(buf, LargeAlignBase.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            this(allocator.allocate(LargeAlignBase.LAYOUT.byteSize() * len));
        }

        public Array(PNIBuf buf) {
            this(buf.get());
        }

        @Override
        protected LargeAlignBase construct(MemorySegment seg) {
            return new LargeAlignBase(seg);
        }

        @Override
        protected MemorySegment getSegment(LargeAlignBase value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<LargeAlignBase> {
        private Func(io.vproxy.pni.CallSite<LargeAlignBase> func) {
            super(func);
        }

        private Func(io.vproxy.pni.CallSite<LargeAlignBase> func, Options opts) {
            super(func, opts);
        }

        private Func(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static Func of(io.vproxy.pni.CallSite<LargeAlignBase> func) {
            return new Func(func);
        }

        public static Func of(io.vproxy.pni.CallSite<LargeAlignBase> func, Options opts) {
            return new Func(func, opts);
        }

        public static Func of(MemorySegment MEMORY) {
            return new Func(MEMORY);
        }

        @Override
        protected LargeAlignBase construct(MemorySegment seg) {
            return new LargeAlignBase(seg);
        }
    }
}
// metadata.generator-version: pni test
// sha256:71aa6e7a07c4e80ad1b0fc06714009453820fcc95b8553af5c20d194365d75ca
