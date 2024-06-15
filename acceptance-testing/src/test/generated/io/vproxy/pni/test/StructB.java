package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.hack.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class StructB extends AbstractNativeObject implements NativeObject {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        ValueLayout.JAVA_INT_UNALIGNED.withName("i"),
        MemoryLayout.sequenceLayout(4L, ValueLayout.JAVA_BYTE) /* padding */,
        io.vproxy.pni.test.UnionC.LAYOUT.withName("c"),
        ValueLayout.JAVA_LONG_UNALIGNED.withName("l"),
        ValueLayout.ADDRESS_UNALIGNED.withName("d"),
        io.vproxy.pni.test.UnionEmbedded.LAYOUT.withName("embedded")
    );
    public final MemorySegment MEMORY;

    @Override
    public MemorySegment MEMORY() {
        return MEMORY;
    }

    private static final VarHandleW iVH = VarHandleW.of(
        LAYOUT.varHandle(
            MemoryLayout.PathElement.groupElement("i")
        )
    );

    public int getI() {
        return iVH.getInt(MEMORY);
    }

    public void setI(int i) {
        iVH.set(MEMORY, i);
    }

    private final io.vproxy.pni.test.UnionC c;

    public io.vproxy.pni.test.UnionC getC() {
        return this.c;
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

    private static final VarHandleW dVH = VarHandleW.of(
        LAYOUT.varHandle(
            MemoryLayout.PathElement.groupElement("d")
        )
    );

    public io.vproxy.pni.test.StructD getD() {
        var SEG = dVH.getMemorySegment(MEMORY);
        if (SEG.address() == 0) return null;
        return new io.vproxy.pni.test.StructD(SEG);
    }

    public void setD(io.vproxy.pni.test.StructD d) {
        if (d == null) {
            dVH.set(MEMORY, MemorySegment.NULL);
        } else {
            dVH.set(MEMORY, d.MEMORY);
        }
    }

    private final io.vproxy.pni.test.UnionEmbedded embedded;

    public io.vproxy.pni.test.UnionEmbedded getEmbedded() {
        return this.embedded;
    }

    public StructB(MemorySegment MEMORY) {
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
        OFFSET += ValueLayout.JAVA_INT_UNALIGNED.byteSize();
        OFFSET += 4; /* padding */
        this.c = new io.vproxy.pni.test.UnionC(MEMORY.asSlice(OFFSET, io.vproxy.pni.test.UnionC.LAYOUT.byteSize()));
        OFFSET += io.vproxy.pni.test.UnionC.LAYOUT.byteSize();
        OFFSET += ValueLayout.JAVA_LONG_UNALIGNED.byteSize();
        OFFSET += 8;
        this.embedded = new io.vproxy.pni.test.UnionEmbedded(MEMORY.asSlice(OFFSET, io.vproxy.pni.test.UnionEmbedded.LAYOUT.byteSize()));
        OFFSET += io.vproxy.pni.test.UnionEmbedded.LAYOUT.byteSize();
    }

    public StructB(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT));
    }

    @Override
    public void toString(StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
        if (!VISITED.add(new NativeObjectTuple(this))) {
            SB.append("<...>@").append(Long.toString(MEMORY.address(), 16));
            return;
        }
        SB.append("StructB{\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("i => ");
            SB.append(getI());
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("c => ");
            PanamaUtils.nativeObjectToString(getC(), SB, INDENT + 4, VISITED, CORRUPTED_MEMORY);
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("l => ");
            SB.append(getL());
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("d => ");
            if (CORRUPTED_MEMORY) SB.append("<?>");
            else PanamaUtils.nativeObjectToString(getD(), SB, INDENT + 4, VISITED, CORRUPTED_MEMORY);
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("embedded => ");
            PanamaUtils.nativeObjectToString(getEmbedded(), SB, INDENT + 4, VISITED, CORRUPTED_MEMORY);
        }
        SB.append("\n");
        SB.append(" ".repeat(INDENT)).append("}@").append(Long.toString(MEMORY.address(), 16));
    }

    public static class Array extends RefArray<StructB> {
        public Array(MemorySegment buf) {
            super(buf, StructB.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            super(allocator, StructB.LAYOUT, len);
        }

        public Array(PNIBuf buf) {
            super(buf, StructB.LAYOUT);
        }

        @Override
        protected void elementToString(io.vproxy.pni.test.StructB ELEM, StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
            ELEM.toString(SB, INDENT, VISITED, CORRUPTED_MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "StructB.Array";
        }

        @Override
        protected StructB construct(MemorySegment seg) {
            return new StructB(seg);
        }

        @Override
        protected MemorySegment getSegment(StructB value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<StructB> {
        private Func(io.vproxy.pni.CallSite<StructB> func) {
            super(func);
        }

        private Func(io.vproxy.pni.CallSite<StructB> func, Options opts) {
            super(func, opts);
        }

        private Func(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static Func of(io.vproxy.pni.CallSite<StructB> func) {
            return new Func(func);
        }

        public static Func of(io.vproxy.pni.CallSite<StructB> func, Options opts) {
            return new Func(func, opts);
        }

        public static Func of(MemorySegment MEMORY) {
            return new Func(MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "StructB.Func";
        }

        @Override
        protected StructB construct(MemorySegment seg) {
            return new StructB(seg);
        }
    }
}
// metadata.generator-version: pni test
// sha256:fd103382d71a58bbe37aeb3f25ad40b94be08eb4f0d9eec8c6a696a25d6edcec
