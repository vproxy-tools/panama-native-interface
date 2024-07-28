package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.hack.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class ReferenceEmptyChild extends AbstractNativeObject implements NativeObject {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        io.vproxy.pni.test.EmptyChild.LAYOUT.withName("emptyChild")
    );
    public final MemorySegment MEMORY;

    @Override
    public MemorySegment MEMORY() {
        return MEMORY;
    }

    private final io.vproxy.pni.test.EmptyChild emptyChild;

    public io.vproxy.pni.test.EmptyChild getEmptyChild() {
        return this.emptyChild;
    }

    public ReferenceEmptyChild(MemorySegment MEMORY) {
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
        this.emptyChild = new io.vproxy.pni.test.EmptyChild(MEMORY.asSlice(OFFSET, io.vproxy.pni.test.EmptyChild.LAYOUT.byteSize()));
        OFFSET += io.vproxy.pni.test.EmptyChild.LAYOUT.byteSize();
    }

    public ReferenceEmptyChild(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT));
    }

    @Override
    public void toString(StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
        if (!VISITED.add(new NativeObjectTuple(this))) {
            SB.append("<...>@").append(Long.toString(MEMORY.address(), 16));
            return;
        }
        SB.append("ReferenceEmptyChild{\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("emptyChild => ");
            PanamaUtils.nativeObjectToString(getEmptyChild(), SB, INDENT + 4, VISITED, CORRUPTED_MEMORY);
        }
        SB.append("\n");
        SB.append(" ".repeat(INDENT)).append("}@").append(Long.toString(MEMORY.address(), 16));
    }

    public static class Array extends RefArray<ReferenceEmptyChild> {
        public Array(MemorySegment buf) {
            super(buf, ReferenceEmptyChild.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            super(allocator, ReferenceEmptyChild.LAYOUT, len);
        }

        public Array(PNIBuf buf) {
            super(buf, ReferenceEmptyChild.LAYOUT);
        }

        @Override
        protected void elementToString(io.vproxy.pni.test.ReferenceEmptyChild ELEM, StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
            ELEM.toString(SB, INDENT, VISITED, CORRUPTED_MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "ReferenceEmptyChild.Array";
        }

        @Override
        protected ReferenceEmptyChild construct(MemorySegment seg) {
            return new ReferenceEmptyChild(seg);
        }

        @Override
        protected MemorySegment getSegment(ReferenceEmptyChild value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<ReferenceEmptyChild> {
        private Func(io.vproxy.pni.CallSite<ReferenceEmptyChild> func) {
            super(func);
        }

        private Func(io.vproxy.pni.CallSite<ReferenceEmptyChild> func, Options opts) {
            super(func, opts);
        }

        private Func(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static Func of(io.vproxy.pni.CallSite<ReferenceEmptyChild> func) {
            return new Func(func);
        }

        public static Func of(io.vproxy.pni.CallSite<ReferenceEmptyChild> func, Options opts) {
            return new Func(func, opts);
        }

        public static Func of(MemorySegment MEMORY) {
            return new Func(MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "ReferenceEmptyChild.Func";
        }

        @Override
        protected ReferenceEmptyChild construct(MemorySegment seg) {
            return new ReferenceEmptyChild(seg);
        }
    }
}
// metadata.generator-version: pni test
// sha256:db5adec1217868a86038f94817f388045bc7abb80c8c04c8dd9b1c0c6ea3559c
