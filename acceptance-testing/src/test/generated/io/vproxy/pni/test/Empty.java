package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.hack.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class Empty extends AbstractNativeObject implements NativeObject {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(

    );
    public final MemorySegment MEMORY;

    @Override
    public MemorySegment MEMORY() {
        return MEMORY;
    }

    public Empty(MemorySegment MEMORY) {
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
    }

    public Empty(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT));
    }

    @Override
    public void toString(StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
        if (!VISITED.add(new NativeObjectTuple(this))) {
            SB.append("<...>@").append(Long.toString(MEMORY.address(), 16));
            return;
        }
        SB.append("Empty{\n");
        SB.append(" ".repeat(INDENT)).append("}@").append(Long.toString(MEMORY.address(), 16));
    }

    public static class Array extends RefArray<Empty> {
        public Array(MemorySegment buf) {
            super(buf, Empty.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            super(allocator, Empty.LAYOUT, len);
        }

        public Array(PNIBuf buf) {
            super(buf, Empty.LAYOUT);
        }

        @Override
        protected void elementToString(io.vproxy.pni.test.Empty ELEM, StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
            ELEM.toString(SB, INDENT, VISITED, CORRUPTED_MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "Empty.Array";
        }

        @Override
        protected Empty construct(MemorySegment seg) {
            return new Empty(seg);
        }

        @Override
        protected MemorySegment getSegment(Empty value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<Empty> {
        private Func(io.vproxy.pni.CallSite<Empty> func) {
            super(func);
        }

        private Func(io.vproxy.pni.CallSite<Empty> func, Options opts) {
            super(func, opts);
        }

        private Func(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static Func of(io.vproxy.pni.CallSite<Empty> func) {
            return new Func(func);
        }

        public static Func of(io.vproxy.pni.CallSite<Empty> func, Options opts) {
            return new Func(func, opts);
        }

        public static Func of(MemorySegment MEMORY) {
            return new Func(MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "Empty.Func";
        }

        @Override
        protected Empty construct(MemorySegment seg) {
            return new Empty(seg);
        }
    }
}
// metadata.generator-version: pni test
// sha256:8dc12c14c4e3179ff423892e389fd633d11159d1e5984505db4e9b4c59209d30
