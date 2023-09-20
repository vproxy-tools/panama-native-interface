package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class AlwaysAlignedBase {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        ValueLayout.JAVA_BYTE.withName("a")
    );
    public final MemorySegment MEMORY;

    private static final VarHandle aVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("a")
    );

    public byte getA() {
        return (byte) aVH.get(MEMORY);
    }

    public void setA(byte a) {
        aVH.set(MEMORY, a);
    }

    public AlwaysAlignedBase(MemorySegment MEMORY) {
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
        OFFSET += ValueLayout.JAVA_BYTE.byteSize();
    }

    public AlwaysAlignedBase(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT.byteSize()));
    }

    public static class Array extends RefArray<AlwaysAlignedBase> {
        public Array(MemorySegment buf) {
            super(buf, AlwaysAlignedBase.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            this(allocator.allocate(AlwaysAlignedBase.LAYOUT.byteSize() * len));
        }

        public Array(PNIBuf buf) {
            this(buf.get());
        }

        @Override
        protected AlwaysAlignedBase construct(MemorySegment seg) {
            return new AlwaysAlignedBase(seg);
        }

        @Override
        protected MemorySegment getSegment(AlwaysAlignedBase value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<AlwaysAlignedBase> {
        private Func(io.vproxy.pni.CallSite<AlwaysAlignedBase> func) {
            super(func);
        }

        private Func(io.vproxy.pni.CallSite<AlwaysAlignedBase> func, Options opts) {
            super(func, opts);
        }

        private Func(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static Func of(io.vproxy.pni.CallSite<AlwaysAlignedBase> func) {
            return new Func(func);
        }

        public static Func of(io.vproxy.pni.CallSite<AlwaysAlignedBase> func, Options opts) {
            return new Func(func, opts);
        }

        public static Func of(MemorySegment MEMORY) {
            return new Func(MEMORY);
        }

        @Override
        protected AlwaysAlignedBase construct(MemorySegment seg) {
            return new AlwaysAlignedBase(seg);
        }
    }
}
// metadata.generator-version: pni test
// sha256:2aa3dfb99409a5e9dc8bd7c91113c6109cfe036aa05ce07b85692ebbe58515ea
