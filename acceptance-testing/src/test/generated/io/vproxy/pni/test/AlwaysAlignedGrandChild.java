package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class AlwaysAlignedGrandChild extends io.vproxy.pni.test.AlwaysAlignedChild implements NativeObject {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        io.vproxy.pni.test.AlwaysAlignedChild.LAYOUT,
        MemoryLayout.sequenceLayout(4L, ValueLayout.JAVA_BYTE) /* padding */,
        ValueLayout.JAVA_LONG.withName("c")
    );
    public final MemorySegment MEMORY;

    @Override
    public MemorySegment MEMORY() {
        return MEMORY;
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

    @Override
    public String toString() {
        var sb = new StringBuilder();
        toString(sb, 0, new java.util.HashSet<>(), false);
        return sb.toString();
    }

    @Override
    public void toString(StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
        if (!VISITED.add(new NativeObjectTuple(this))) {
            SB.append("<...>@").append(Long.toString(MEMORY.address(), 16));
            return;
        }
        SB.append("AlwaysAlignedGrandChild{\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("c => ");
            SB.append(getC());
        }
        SB.append("\n");
        SB.append(" ".repeat(INDENT)).append("}@").append(Long.toString(MEMORY.address(), 16));
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
        protected void elementToString(io.vproxy.pni.test.AlwaysAlignedGrandChild ELEM, StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
            ELEM.toString(SB, INDENT, VISITED, CORRUPTED_MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "AlwaysAlignedGrandChild.Array";
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
        protected String toStringTypeName() {
            return "AlwaysAlignedGrandChild.Func";
        }

        @Override
        protected AlwaysAlignedGrandChild construct(MemorySegment seg) {
            return new AlwaysAlignedGrandChild(seg);
        }
    }
}
// metadata.generator-version: pni test
// sha256:5c993610ea9ae279ddac2cab014384dcbeda7bc4baae78461d7d704e2f8275d3
