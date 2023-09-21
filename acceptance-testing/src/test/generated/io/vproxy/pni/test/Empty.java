package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class Empty {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(

    );
    public final MemorySegment MEMORY;

    public Empty(MemorySegment MEMORY) {
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
    }

    public Empty(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT.byteSize()));
    }

    public static class Array extends RefArray<Empty> {
        public Array(MemorySegment buf) {
            super(buf, Empty.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            this(allocator.allocate(Empty.LAYOUT.byteSize() * len));
        }

        public Array(PNIBuf buf) {
            this(buf.get());
        }

        @Override
        protected Empty construct(MemorySegment seg) {
            return new Empty(seg);
        }

        @Override
        protected MemorySegment getSegment(Empty value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<Empty> {
        private Func(io.vproxy.pni.CallSite<Empty> func) {
            super(func);
        }

        private Func(io.vproxy.pni.CallSite<Empty> func, Options opts) {
            super(func, opts);
        }

        private Func(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static Func of(io.vproxy.pni.CallSite<Empty> func) {
            return new Func(func);
        }

        public static Func of(io.vproxy.pni.CallSite<Empty> func, Options opts) {
            return new Func(func, opts);
        }

        public static Func of(MemorySegment MEMORY) {
            return new Func(MEMORY);
        }

        @Override
        protected Empty construct(MemorySegment seg) {
            return new Empty(seg);
        }
    }
}
// metadata.generator-version: pni test
// sha256:73d77f5d9d622048a324fcc8aac48dc1a7fe1b65a8ed066a5e775f5e0e153190
