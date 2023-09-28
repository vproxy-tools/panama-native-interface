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

    @Override
    public String toString() {
        var sb = new StringBuilder();
        toString(sb, 0, new java.util.HashSet<>(), false);
        return sb.toString();
    }

    public void toString(StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
        if (!VISITED.add(new NativeObjectTuple(getClass(), MEMORY.address()))) {
            SB.append("<...>@").append(Long.toString(MEMORY.address(), 16));
            return;
        }
        SB.append("ChildOfLargeAlign{\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("y => ");
            SB.append(getY());
        }
        SB.append("\n");
        SB.append(" ".repeat(INDENT)).append("}@").append(Long.toString(MEMORY.address(), 16));
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
        protected void elementToString(io.vproxy.pni.test.ChildOfLargeAlign ELEM, StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
            ELEM.toString(SB, INDENT, VISITED, CORRUPTED_MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "ChildOfLargeAlign.Array";
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
        protected String toStringTypeName() {
            return "ChildOfLargeAlign.Func";
        }

        @Override
        protected ChildOfLargeAlign construct(MemorySegment seg) {
            return new ChildOfLargeAlign(seg);
        }
    }
}
// metadata.generator-version: pni test
// sha256:ae5dccc281e0326faa54e91c03182eb42286a2aa4af04432db98f0f2305853ea
