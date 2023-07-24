package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class StructB {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        ValueLayout.JAVA_INT_UNALIGNED.withName("i"),
        MemoryLayout.sequenceLayout(4L, ValueLayout.JAVA_BYTE) /* padding */,
        io.vproxy.pni.test.UnionC.LAYOUT.withName("c"),
        ValueLayout.JAVA_LONG_UNALIGNED.withName("l"),
        ValueLayout.ADDRESS_UNALIGNED.withName("d"),
        io.vproxy.pni.test.UnionEmbedded.LAYOUT.withName("embedded")
    );
    public final MemorySegment MEMORY;

    private static final VarHandle iVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("i")
    );

    public int getI() {
        return (int) iVH.get(MEMORY);
    }

    public void setI(int i) {
        iVH.set(MEMORY, i);
    }

    private final io.vproxy.pni.test.UnionC c;

    public io.vproxy.pni.test.UnionC getC() {
        return this.c;
    }

    private static final VarHandle lVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("l")
    );

    public long getL() {
        return (long) lVH.get(MEMORY);
    }

    public void setL(long l) {
        lVH.set(MEMORY, l);
    }

    private static final VarHandle dVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("d")
    );

    public io.vproxy.pni.test.StructD getD() {
        var SEG = (MemorySegment) dVH.get(MEMORY);
        if (SEG.address() == 0) return null;
        return new io.vproxy.pni.test.StructD(SEG);
    }

    public void setD(io.vproxy.pni.test.StructD d) {
        if (d == null) {
            dVH.set(MEMORY, MemorySegment.NULL);
        } else {
            dVH.set(MEMORY, d.MEMORY);
        }
    }

    private final io.vproxy.pni.test.UnionEmbedded embedded;

    public io.vproxy.pni.test.UnionEmbedded getEmbedded() {
        return this.embedded;
    }

    public StructB(MemorySegment MEMORY) {
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
        OFFSET += ValueLayout.JAVA_INT_UNALIGNED.byteSize();
        OFFSET += 4; /* padding */
        this.c = new io.vproxy.pni.test.UnionC(MEMORY.asSlice(OFFSET, io.vproxy.pni.test.UnionC.LAYOUT.byteSize()));
        OFFSET += io.vproxy.pni.test.UnionC.LAYOUT.byteSize();
        OFFSET += ValueLayout.JAVA_LONG_UNALIGNED.byteSize();
        OFFSET += 8;
        this.embedded = new io.vproxy.pni.test.UnionEmbedded(MEMORY.asSlice(OFFSET, io.vproxy.pni.test.UnionEmbedded.LAYOUT.byteSize()));
        OFFSET += io.vproxy.pni.test.UnionEmbedded.LAYOUT.byteSize();
    }

    public StructB(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT.byteSize()));
    }

    public static class Array extends RefArray<StructB> {
        public Array(MemorySegment buf) {
            super(buf, StructB.LAYOUT);
        }

        @Override
        protected StructB construct(MemorySegment seg) {
            return new StructB(seg);
        }

        @Override
        protected MemorySegment getSegment(StructB value) {
            return value.MEMORY;
        }
    }
}
// sha256:d824f32a54cb5913a933a78e18a4823aab2cd5bee27d2bcd1040b7562a1f428c