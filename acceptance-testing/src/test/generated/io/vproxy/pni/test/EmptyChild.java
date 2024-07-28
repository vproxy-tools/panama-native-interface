package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.hack.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class EmptyChild extends io.vproxy.pni.test.LargeAlignBase implements NativeObject {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        io.vproxy.pni.test.LargeAlignBase.LAYOUT

    );
    public final MemorySegment MEMORY;

    @Override
    public MemorySegment MEMORY() {
        return MEMORY;
    }

    public EmptyChild(MemorySegment MEMORY) {
        super(MEMORY);
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
        OFFSET += io.vproxy.pni.test.LargeAlignBase.LAYOUT.byteSize();
    }

    public EmptyChild(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT));
    }

    @Override
    public void toString(StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
        if (!VISITED.add(new NativeObjectTuple(this))) {
            SB.append("<...>@").append(Long.toString(MEMORY.address(), 16));
            return;
        }
        SB.append("EmptyChild{\n");
        SB.append(" ".repeat(INDENT + 4)).append("SUPER => ");
        {
            INDENT += 4;
            SB.append("LargeAlignBase{\n");
            {
                SB.append(" ".repeat(INDENT + 4)).append("x => ");
                SB.append(getX());
            }
            SB.append("\n");
            SB.append(" ".repeat(INDENT)).append("}@").append(Long.toString(MEMORY.address(), 16));
            INDENT -= 4;
            SB.append("\n");

        }
        SB.append(" ".repeat(INDENT)).append("}@").append(Long.toString(MEMORY.address(), 16));
    }

    public static class Array extends RefArray<EmptyChild> {
        public Array(MemorySegment buf) {
            super(buf, EmptyChild.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            super(allocator, EmptyChild.LAYOUT, len);
        }

        public Array(PNIBuf buf) {
            super(buf, EmptyChild.LAYOUT);
        }

        @Override
        protected void elementToString(io.vproxy.pni.test.EmptyChild ELEM, StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
            ELEM.toString(SB, INDENT, VISITED, CORRUPTED_MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "EmptyChild.Array";
        }

        @Override
        protected EmptyChild construct(MemorySegment seg) {
            return new EmptyChild(seg);
        }

        @Override
        protected MemorySegment getSegment(EmptyChild value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<EmptyChild> {
        private Func(io.vproxy.pni.CallSite<EmptyChild> func) {
            super(func);
        }

        private Func(io.vproxy.pni.CallSite<EmptyChild> func, Options opts) {
            super(func, opts);
        }

        private Func(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static Func of(io.vproxy.pni.CallSite<EmptyChild> func) {
            return new Func(func);
        }

        public static Func of(io.vproxy.pni.CallSite<EmptyChild> func, Options opts) {
            return new Func(func, opts);
        }

        public static Func of(MemorySegment MEMORY) {
            return new Func(MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "EmptyChild.Func";
        }

        @Override
        protected EmptyChild construct(MemorySegment seg) {
            return new EmptyChild(seg);
        }
    }
}
// metadata.generator-version: pni test
// sha256:cc43ab7fa0f3f5e817446c51e4a793d00413d1f7dcbefcff8c8675429251793c
