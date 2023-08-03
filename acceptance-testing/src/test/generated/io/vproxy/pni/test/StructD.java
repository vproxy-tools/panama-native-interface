package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class StructD {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        ValueLayout.JAVA_INT_UNALIGNED.withName("n"),
        MemoryLayout.sequenceLayout(4L, ValueLayout.JAVA_BYTE) /* padding */,
        ValueLayout.JAVA_DOUBLE_UNALIGNED.withName("d")
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

    private static final VarHandle dVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("d")
    );

    public double getD() {
        return (double) dVH.get(MEMORY);
    }

    public void setD(double d) {
        dVH.set(MEMORY, d);
    }

    public StructD(MemorySegment MEMORY) {
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
        OFFSET += ValueLayout.JAVA_INT_UNALIGNED.byteSize();
        OFFSET += 4; /* padding */
        OFFSET += ValueLayout.JAVA_DOUBLE_UNALIGNED.byteSize();
    }

    public StructD(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT.byteSize()));
    }

    public static class Array extends RefArray<StructD> {
        public Array(MemorySegment buf) {
            super(buf, StructD.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            this(allocator.allocate(StructD.LAYOUT.byteSize() * len));
        }

        public Array(PNIBuf buf) {
            this(buf.get());
        }

        @Override
        protected StructD construct(MemorySegment seg) {
            return new StructD(seg);
        }

        @Override
        protected MemorySegment getSegment(StructD value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<StructD> {
        private Func(io.vproxy.pni.CallSite<StructD> func) {
            super(func);
        }

        public static Func of(io.vproxy.pni.CallSite<StructD> func) {
            return new Func(func);
        }

        @Override
        protected StructD construct(MemorySegment seg) {
            return new StructD(seg);
        }

        @Override
        protected MemorySegment getSegment(StructD value) {
            return value.MEMORY;
        }
    }
}
// sha256:7904035ca157b7ce920be830d91254db9b267705f45bcb402fcede60fd9df3ac
