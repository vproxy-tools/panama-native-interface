package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class AlwaysAlignedClass {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        ValueLayout.JAVA_BYTE.withName("a"),
        MemoryLayout.sequenceLayout(1L, ValueLayout.JAVA_BYTE) /* padding */,
        ValueLayout.JAVA_SHORT.withName("b"),
        MemoryLayout.sequenceLayout(4L, ValueLayout.JAVA_BYTE) /* padding */,
        ValueLayout.JAVA_LONG.withName("c")
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

    private static final VarHandle bVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("b")
    );

    public short getB() {
        return (short) bVH.get(MEMORY);
    }

    public void setB(short b) {
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

    public AlwaysAlignedClass(MemorySegment MEMORY) {
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
        OFFSET += ValueLayout.JAVA_BYTE.byteSize();
        OFFSET += 1; /* padding */
        OFFSET += ValueLayout.JAVA_SHORT_UNALIGNED.byteSize();
        OFFSET += 4; /* padding */
        OFFSET += ValueLayout.JAVA_LONG_UNALIGNED.byteSize();
    }

    public AlwaysAlignedClass(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT.byteSize()));
    }

    public static class Array extends RefArray<AlwaysAlignedClass> {
        public Array(MemorySegment buf) {
            super(buf, AlwaysAlignedClass.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            this(allocator.allocate(AlwaysAlignedClass.LAYOUT.byteSize() * len));
        }

        public Array(PNIBuf buf) {
            this(buf.get());
        }

        @Override
        protected AlwaysAlignedClass construct(MemorySegment seg) {
            return new AlwaysAlignedClass(seg);
        }

        @Override
        protected MemorySegment getSegment(AlwaysAlignedClass value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<AlwaysAlignedClass> {
        private Func(io.vproxy.pni.CallSite<AlwaysAlignedClass> func) {
            super(func);
        }

        private Func(io.vproxy.pni.CallSite<AlwaysAlignedClass> func, Options opts) {
            super(func, opts);
        }

        private Func(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static Func of(io.vproxy.pni.CallSite<AlwaysAlignedClass> func) {
            return new Func(func);
        }

        public static Func of(io.vproxy.pni.CallSite<AlwaysAlignedClass> func, Options opts) {
            return new Func(func, opts);
        }

        public static Func of(MemorySegment MEMORY) {
            return new Func(MEMORY);
        }

        @Override
        protected AlwaysAlignedClass construct(MemorySegment seg) {
            return new AlwaysAlignedClass(seg);
        }
    }
}
// metadata.generator-version: pni test
// sha256:ba7624fbadceb2815d7e8d3d5a15d0649419692f7380f9210d40d8f7a7c7baf3
