package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class AlwaysAlignedUnion extends AbstractNativeObject implements NativeObject {
    public static final MemoryLayout LAYOUT = MemoryLayout.unionLayout(
        ValueLayout.JAVA_SHORT.withName("a"),
        ValueLayout.JAVA_INT.withName("b"),
        ValueLayout.JAVA_LONG.withName("c")
    ).withByteAlignment(8);
    public final MemorySegment MEMORY;

    @Override
    public MemorySegment MEMORY() {
        return MEMORY;
    }

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
        this(ALLOCATOR.allocate(LAYOUT));
    }

    @Override
    public void toString(StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
        if (!VISITED.add(new NativeObjectTuple(this))) {
            SB.append("<...>@").append(Long.toString(MEMORY.address(), 16));
            return;
        }
        CORRUPTED_MEMORY = true;
        SB.append("AlwaysAlignedUnion(\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("a => ");
            SB.append(getA());
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("b => ");
            SB.append(getB());
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("c => ");
            SB.append(getC());
        }
        SB.append("\n");
        SB.append(" ".repeat(INDENT)).append(")@").append(Long.toString(MEMORY.address(), 16));
    }

    public static class Array extends RefArray<AlwaysAlignedUnion> {
        public Array(MemorySegment buf) {
            super(buf, AlwaysAlignedUnion.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            super(allocator, AlwaysAlignedUnion.LAYOUT, len);
        }

        public Array(PNIBuf buf) {
            super(buf, AlwaysAlignedUnion.LAYOUT);
        }

        @Override
        protected void elementToString(io.vproxy.pni.test.AlwaysAlignedUnion ELEM, StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
            ELEM.toString(SB, INDENT, VISITED, CORRUPTED_MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "AlwaysAlignedUnion.Array";
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
        protected String toStringTypeName() {
            return "AlwaysAlignedUnion.Func";
        }

        @Override
        protected AlwaysAlignedUnion construct(MemorySegment seg) {
            return new AlwaysAlignedUnion(seg);
        }
    }
}
// metadata.generator-version: pni test
// sha256:46ac67402162c41b4df53e6cc7fc9c199802155e062b7a4dc63b8ecc7cf90596
