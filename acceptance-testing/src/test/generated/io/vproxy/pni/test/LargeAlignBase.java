package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class LargeAlignBase implements NativeObject {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        ValueLayout.JAVA_LONG_UNALIGNED.withName("x")
    );
    public final MemorySegment MEMORY;

    @Override
    public MemorySegment MEMORY() {
        return MEMORY;
    }

    private static final VarHandle xVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("x")
    );

    public long getX() {
        return (long) xVH.get(MEMORY);
    }

    public void setX(long x) {
        xVH.set(MEMORY, x);
    }

    public LargeAlignBase(MemorySegment MEMORY) {
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
        OFFSET += ValueLayout.JAVA_LONG_UNALIGNED.byteSize();
    }

    public LargeAlignBase(Allocator ALLOCATOR) {
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
        SB.append("LargeAlignBase{\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("x => ");
            SB.append(getX());
        }
        SB.append("\n");
        SB.append(" ".repeat(INDENT)).append("}@").append(Long.toString(MEMORY.address(), 16));
    }

    public static class Array extends RefArray<LargeAlignBase> {
        public Array(MemorySegment buf) {
            super(buf, LargeAlignBase.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            this(allocator.allocate(LargeAlignBase.LAYOUT.byteSize() * len));
        }

        public Array(PNIBuf buf) {
            this(buf.get());
        }

        @Override
        protected void elementToString(io.vproxy.pni.test.LargeAlignBase ELEM, StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
            ELEM.toString(SB, INDENT, VISITED, CORRUPTED_MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "LargeAlignBase.Array";
        }

        @Override
        protected LargeAlignBase construct(MemorySegment seg) {
            return new LargeAlignBase(seg);
        }

        @Override
        protected MemorySegment getSegment(LargeAlignBase value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<LargeAlignBase> {
        private Func(io.vproxy.pni.CallSite<LargeAlignBase> func) {
            super(func);
        }

        private Func(io.vproxy.pni.CallSite<LargeAlignBase> func, Options opts) {
            super(func, opts);
        }

        private Func(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static Func of(io.vproxy.pni.CallSite<LargeAlignBase> func) {
            return new Func(func);
        }

        public static Func of(io.vproxy.pni.CallSite<LargeAlignBase> func, Options opts) {
            return new Func(func, opts);
        }

        public static Func of(MemorySegment MEMORY) {
            return new Func(MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "LargeAlignBase.Func";
        }

        @Override
        protected LargeAlignBase construct(MemorySegment seg) {
            return new LargeAlignBase(seg);
        }
    }
}
// metadata.generator-version: pni test
// sha256:ad32b62a9c3cdcf368d4979734ba6e0b83cb674e5722e567fff67ba23c682f96
