package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class AlwaysAlignedChild extends io.vproxy.pni.test.AlwaysAlignedBase {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        io.vproxy.pni.test.AlwaysAlignedBase.LAYOUT,
        MemoryLayout.sequenceLayout(1L, ValueLayout.JAVA_BYTE) /* padding */,
        ValueLayout.JAVA_SHORT.withName("b")
    );
    public final MemorySegment MEMORY;

    private static final VarHandle bVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("b")
    );

    public short getB() {
        return (short) bVH.get(MEMORY);
    }

    public void setB(short b) {
        bVH.set(MEMORY, b);
    }

    public AlwaysAlignedChild(MemorySegment MEMORY) {
        super(MEMORY);
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
        OFFSET += io.vproxy.pni.test.AlwaysAlignedBase.LAYOUT.byteSize();
        OFFSET += 1; // head padding
        OFFSET += ValueLayout.JAVA_SHORT_UNALIGNED.byteSize();
    }

    public AlwaysAlignedChild(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT.byteSize()));
    }

    public static class Array extends RefArray<AlwaysAlignedChild> {
        public Array(MemorySegment buf) {
            super(buf, AlwaysAlignedChild.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            this(allocator.allocate(AlwaysAlignedChild.LAYOUT.byteSize() * len));
        }

        public Array(PNIBuf buf) {
            this(buf.get());
        }

        @Override
        protected AlwaysAlignedChild construct(MemorySegment seg) {
            return new AlwaysAlignedChild(seg);
        }

        @Override
        protected MemorySegment getSegment(AlwaysAlignedChild value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<AlwaysAlignedChild> {
        private Func(io.vproxy.pni.CallSite<AlwaysAlignedChild> func) {
            super(func);
        }

        private Func(io.vproxy.pni.CallSite<AlwaysAlignedChild> func, Options opts) {
            super(func, opts);
        }

        private Func(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static Func of(io.vproxy.pni.CallSite<AlwaysAlignedChild> func) {
            return new Func(func);
        }

        public static Func of(io.vproxy.pni.CallSite<AlwaysAlignedChild> func, Options opts) {
            return new Func(func, opts);
        }

        public static Func of(MemorySegment MEMORY) {
            return new Func(MEMORY);
        }

        @Override
        protected AlwaysAlignedChild construct(MemorySegment seg) {
            return new AlwaysAlignedChild(seg);
        }
    }
}
// metadata.generator-version: pni test
// sha256:6f1271afc9d9d4723bd66da46d6db2922fb340c4e2bf7ee0215b977038fe7286
