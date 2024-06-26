package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.hack.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class AlwaysAlignedBase extends AbstractNativeObject implements NativeObject {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        ValueLayout.JAVA_BYTE.withName("a")
    );
    public final MemorySegment MEMORY;

    @Override
    public MemorySegment MEMORY() {
        return MEMORY;
    }

    private static final VarHandleW aVH = VarHandleW.of(
        LAYOUT.varHandle(
            MemoryLayout.PathElement.groupElement("a")
        )
    );

    public byte getA() {
        return aVH.getByte(MEMORY);
    }

    public void setA(byte a) {
        aVH.set(MEMORY, a);
    }

    public AlwaysAlignedBase(MemorySegment MEMORY) {
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
        OFFSET += ValueLayout.JAVA_BYTE.byteSize();
    }

    public AlwaysAlignedBase(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT));
    }

    @Override
    public void toString(StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
        if (!VISITED.add(new NativeObjectTuple(this))) {
            SB.append("<...>@").append(Long.toString(MEMORY.address(), 16));
            return;
        }
        SB.append("AlwaysAlignedBase{\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("a => ");
            SB.append(getA());
        }
        SB.append("\n");
        SB.append(" ".repeat(INDENT)).append("}@").append(Long.toString(MEMORY.address(), 16));
    }

    public static class Array extends RefArray<AlwaysAlignedBase> {
        public Array(MemorySegment buf) {
            super(buf, AlwaysAlignedBase.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            super(allocator, AlwaysAlignedBase.LAYOUT, len);
        }

        public Array(PNIBuf buf) {
            super(buf, AlwaysAlignedBase.LAYOUT);
        }

        @Override
        protected void elementToString(io.vproxy.pni.test.AlwaysAlignedBase ELEM, StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
            ELEM.toString(SB, INDENT, VISITED, CORRUPTED_MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "AlwaysAlignedBase.Array";
        }

        @Override
        protected AlwaysAlignedBase construct(MemorySegment seg) {
            return new AlwaysAlignedBase(seg);
        }

        @Override
        protected MemorySegment getSegment(AlwaysAlignedBase value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<AlwaysAlignedBase> {
        private Func(io.vproxy.pni.CallSite<AlwaysAlignedBase> func) {
            super(func);
        }

        private Func(io.vproxy.pni.CallSite<AlwaysAlignedBase> func, Options opts) {
            super(func, opts);
        }

        private Func(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static Func of(io.vproxy.pni.CallSite<AlwaysAlignedBase> func) {
            return new Func(func);
        }

        public static Func of(io.vproxy.pni.CallSite<AlwaysAlignedBase> func, Options opts) {
            return new Func(func, opts);
        }

        public static Func of(MemorySegment MEMORY) {
            return new Func(MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "AlwaysAlignedBase.Func";
        }

        @Override
        protected AlwaysAlignedBase construct(MemorySegment seg) {
            return new AlwaysAlignedBase(seg);
        }
    }
}
// metadata.generator-version: pni test
// sha256:7fd17b00da0ed6ed84321e84cf715a6c51da76929519f4e87d545a625794c4e6
