package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.hack.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class UnionC extends AbstractNativeObject implements NativeObject {
    public static final MemoryLayout LAYOUT = MemoryLayout.unionLayout(
        ValueLayout.JAVA_INT_UNALIGNED.withName("n"),
        ValueLayout.JAVA_LONG_UNALIGNED.withName("l")
    );
    public final MemorySegment MEMORY;

    @Override
    public MemorySegment MEMORY() {
        return MEMORY;
    }

    private static final VarHandleW nVH = VarHandleW.of(
        LAYOUT.varHandle(
            MemoryLayout.PathElement.groupElement("n")
        )
    );

    public int getN() {
        return nVH.getInt(MEMORY);
    }

    public void setN(int n) {
        nVH.set(MEMORY, n);
    }

    private static final VarHandleW lVH = VarHandleW.of(
        LAYOUT.varHandle(
            MemoryLayout.PathElement.groupElement("l")
        )
    );

    public long getL() {
        return lVH.getLong(MEMORY);
    }

    public void setL(long l) {
        lVH.set(MEMORY, l);
    }

    public UnionC(MemorySegment MEMORY) {
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
        OFFSET += ValueLayout.JAVA_INT_UNALIGNED.byteSize();
        OFFSET = 0;
        OFFSET += ValueLayout.JAVA_LONG_UNALIGNED.byteSize();
        OFFSET = 0;
    }

    public UnionC(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT));
    }

    @Override
    public void toString(StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
        if (!VISITED.add(new NativeObjectTuple(this))) {
            SB.append("<...>@").append(Long.toString(MEMORY.address(), 16));
            return;
        }
        CORRUPTED_MEMORY = true;
        SB.append("UnionC(\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("n => ");
            SB.append(getN());
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("l => ");
            SB.append(getL());
        }
        SB.append("\n");
        SB.append(" ".repeat(INDENT)).append(")@").append(Long.toString(MEMORY.address(), 16));
    }

    public static class Array extends RefArray<UnionC> {
        public Array(MemorySegment buf) {
            super(buf, UnionC.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            super(allocator, UnionC.LAYOUT, len);
        }

        public Array(PNIBuf buf) {
            super(buf, UnionC.LAYOUT);
        }

        @Override
        protected void elementToString(io.vproxy.pni.test.UnionC ELEM, StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
            ELEM.toString(SB, INDENT, VISITED, CORRUPTED_MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "UnionC.Array";
        }

        @Override
        protected UnionC construct(MemorySegment seg) {
            return new UnionC(seg);
        }

        @Override
        protected MemorySegment getSegment(UnionC value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<UnionC> {
        private Func(io.vproxy.pni.CallSite<UnionC> func) {
            super(func);
        }

        private Func(io.vproxy.pni.CallSite<UnionC> func, Options opts) {
            super(func, opts);
        }

        private Func(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static Func of(io.vproxy.pni.CallSite<UnionC> func) {
            return new Func(func);
        }

        public static Func of(io.vproxy.pni.CallSite<UnionC> func, Options opts) {
            return new Func(func, opts);
        }

        public static Func of(MemorySegment MEMORY) {
            return new Func(MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "UnionC.Func";
        }

        @Override
        protected UnionC construct(MemorySegment seg) {
            return new UnionC(seg);
        }
    }
}
// metadata.generator-version: pni test
// sha256:083ce5319737aab077a19da02751b75447bd40c67957f0a343380468f3c8d424
