package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class ChildOfLargeAlign extends io.vproxy.pni.test.LargeAlignBase {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        io.vproxy.pni.test.LargeAlignBase.LAYOUT,
        ValueLayout.JAVA_BYTE.withName("y"),
        MemoryLayout.sequenceLayout(7L, ValueLayout.JAVA_BYTE) /* padding */
    );
    public final MemorySegment MEMORY;

    private static final VarHandle yVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("y")
    );

    public byte getY() {
        return (byte) yVH.get(MEMORY);
    }

    public void setY(byte y) {
        yVH.set(MEMORY, y);
    }

    public ChildOfLargeAlign(MemorySegment MEMORY) {
        super(MEMORY);
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
        OFFSET += io.vproxy.pni.test.LargeAlignBase.LAYOUT.byteSize();
        OFFSET += ValueLayout.JAVA_BYTE.byteSize();
        OFFSET += 7; /* padding */
    }

    public ChildOfLargeAlign(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT.byteSize()));
    }

    public static class Array extends RefArray<ChildOfLargeAlign> {
        public Array(MemorySegment buf) {
            super(buf, ChildOfLargeAlign.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            this(allocator.allocate(ChildOfLargeAlign.LAYOUT.byteSize() * len));
        }

        public Array(PNIBuf buf) {
            this(buf.get());
        }

        @Override
        protected ChildOfLargeAlign construct(MemorySegment seg) {
            return new ChildOfLargeAlign(seg);
        }

        @Override
        protected MemorySegment getSegment(ChildOfLargeAlign value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<ChildOfLargeAlign> {
        private Func(io.vproxy.pni.CallSite<ChildOfLargeAlign> func) {
            super(func);
        }

        private Func(io.vproxy.pni.CallSite<ChildOfLargeAlign> func, Options opts) {
            super(func, opts);
        }

        private Func(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static Func of(io.vproxy.pni.CallSite<ChildOfLargeAlign> func) {
            return new Func(func);
        }

        public static Func of(io.vproxy.pni.CallSite<ChildOfLargeAlign> func, Options opts) {
            return new Func(func, opts);
        }

        public static Func of(MemorySegment MEMORY) {
            return new Func(MEMORY);
        }

        @Override
        protected ChildOfLargeAlign construct(MemorySegment seg) {
            return new ChildOfLargeAlign(seg);
        }
    }
}
// metadata.generator-version: pni test
// sha256:dc9a4eecda98eeee06803e7f282f7c2863ac6ff9e66dc396bb264eff455f4b3a
