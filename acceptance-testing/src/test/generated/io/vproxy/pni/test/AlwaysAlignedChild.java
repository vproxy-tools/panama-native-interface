package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class AlwaysAlignedChild extends io.vproxy.pni.test.AlwaysAlignedBase implements NativeObject {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        io.vproxy.pni.test.AlwaysAlignedBase.LAYOUT,
        MemoryLayout.sequenceLayout(1L, ValueLayout.JAVA_BYTE) /* padding */,
        ValueLayout.JAVA_SHORT.withName("b")
    );
    public final MemorySegment MEMORY;

    @Override
    public MemorySegment MEMORY() {
        return MEMORY;
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
        SB.append("AlwaysAlignedChild{\n");
        SB.append(" ".repeat(INDENT + 4)).append("SUPER => ");
        {
            INDENT += 4;
            SB.append("AlwaysAlignedBase{\n");
            {
                SB.append(" ".repeat(INDENT + 4)).append("a => ");
                SB.append(getA());
            }
            SB.append("\n");
            SB.append(" ".repeat(INDENT)).append("}@").append(Long.toString(MEMORY.address(), 16));
            INDENT -= 4;
            SB.append(",\n");
        }
        {
            SB.append(" ".repeat(INDENT + 4)).append("b => ");
            SB.append(getB());
        }
        SB.append("\n");
        SB.append(" ".repeat(INDENT)).append("}@").append(Long.toString(MEMORY.address(), 16));
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
        protected void elementToString(io.vproxy.pni.test.AlwaysAlignedChild ELEM, StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
            ELEM.toString(SB, INDENT, VISITED, CORRUPTED_MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "AlwaysAlignedChild.Array";
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
        protected String toStringTypeName() {
            return "AlwaysAlignedChild.Func";
        }

        @Override
        protected AlwaysAlignedChild construct(MemorySegment seg) {
            return new AlwaysAlignedChild(seg);
        }
    }
}
// metadata.generator-version: pni test
// sha256:1aca9a53bbc4fe57a5fae63f3b05c5c2dfcc9708d9e6911e5d4432b4a0b01bc8
