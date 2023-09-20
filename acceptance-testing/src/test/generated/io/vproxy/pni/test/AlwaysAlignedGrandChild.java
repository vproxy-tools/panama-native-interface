package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class AlwaysAlignedGrandChild extends io.vproxy.pni.test.AlwaysAlignedChild {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        io.vproxy.pni.test.AlwaysAlignedChild.LAYOUT,
        MemoryLayout.sequenceLayout(4L, ValueLayout.JAVA_BYTE) /* padding */,
        ValueLayout.JAVA_LONG.withName("c")
    );
    public final MemorySegment MEMORY;

    private static final VarHandle cVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("c")
    );

    public long getC() {
        return (long) cVH.get(MEMORY);
    }

    public void setC(long c) {
        cVH.set(MEMORY, c);
    }

    public AlwaysAlignedGrandChild(MemorySegment MEMORY) {
        super(MEMORY);
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
        OFFSET += io.vproxy.pni.test.AlwaysAlignedChild.LAYOUT.byteSize();
        OFFSET += 4; // head padding
        OFFSET += ValueLayout.JAVA_LONG_UNALIGNED.byteSize();
    }

    public AlwaysAlignedGrandChild(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT.byteSize()));
    }

    public static class Array extends RefArray<AlwaysAlignedGrandChild> {
        public Array(MemorySegment buf) {
            super(buf, AlwaysAlignedGrandChild.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            this(allocator.allocate(AlwaysAlignedGrandChild.LAYOUT.byteSize() * len));
        }

        public Array(PNIBuf buf) {
            this(buf.get());
        }

        @Override
        protected AlwaysAlignedGrandChild construct(MemorySegment seg) {
            return new AlwaysAlignedGrandChild(seg);
        }

        @Override
        protected MemorySegment getSegment(AlwaysAlignedGrandChild value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<AlwaysAlignedGrandChild> {
        private Func(io.vproxy.pni.CallSite<AlwaysAlignedGrandChild> func) {
            super(func);
        }

        private Func(io.vproxy.pni.CallSite<AlwaysAlignedGrandChild> func, Options opts) {
            super(func, opts);
        }

        private Func(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static Func of(io.vproxy.pni.CallSite<AlwaysAlignedGrandChild> func) {
            return new Func(func);
        }

        public static Func of(io.vproxy.pni.CallSite<AlwaysAlignedGrandChild> func, Options opts) {
            return new Func(func, opts);
        }

        public static Func of(MemorySegment MEMORY) {
            return new Func(MEMORY);
        }

        @Override
        protected AlwaysAlignedGrandChild construct(MemorySegment seg) {
            return new AlwaysAlignedGrandChild(seg);
        }
    }
}
// metadata.generator-version: pni test
// sha256:3702df61aa153971028c42e81f943d7591b9ec28bff4b56554a086b62bd86abb
