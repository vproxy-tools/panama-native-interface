package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.hack.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class ToStringUnion extends AbstractNativeObject implements NativeObject {
    public static final MemoryLayout LAYOUT = MemoryLayout.unionLayout(
        ValueLayout.JAVA_LONG_UNALIGNED.withName("num"),
        io.vproxy.pni.test.ToStringClass.LAYOUT.withName("c1"),
        io.vproxy.pni.test.ToStringClass2.LAYOUT.withName("c2"),
        io.vproxy.pni.test.ToStringClassRecurse.LAYOUT.withName("cr"),
        ValueLayout.ADDRESS_UNALIGNED.withName("pc1"),
        ValueLayout.ADDRESS_UNALIGNED.withName("pc2"),
        ValueLayout.ADDRESS_UNALIGNED.withName("pcr")
    );
    public final MemorySegment MEMORY;

    @Override
    public MemorySegment MEMORY() {
        return MEMORY;
    }

    private static final VarHandleW numVH = VarHandleW.of(
        LAYOUT.varHandle(
            MemoryLayout.PathElement.groupElement("num")
        )
    );

    public long getNum() {
        return numVH.getLong(MEMORY);
    }

    public void setNum(long num) {
        numVH.set(MEMORY, num);
    }

    private final io.vproxy.pni.test.ToStringClass c1;

    public io.vproxy.pni.test.ToStringClass getC1() {
        return this.c1;
    }

    private final io.vproxy.pni.test.ToStringClass2 c2;

    public io.vproxy.pni.test.ToStringClass2 getC2() {
        return this.c2;
    }

    private final io.vproxy.pni.test.ToStringClassRecurse cr;

    public io.vproxy.pni.test.ToStringClassRecurse getCr() {
        return this.cr;
    }

    private static final VarHandleW pc1VH = VarHandleW.of(
        LAYOUT.varHandle(
            MemoryLayout.PathElement.groupElement("pc1")
        )
    );

    public io.vproxy.pni.test.ToStringClass getPc1() {
        var SEG = pc1VH.getMemorySegment(MEMORY);
        if (SEG.address() == 0) return null;
        return new io.vproxy.pni.test.ToStringClass(SEG);
    }

    public void setPc1(io.vproxy.pni.test.ToStringClass pc1) {
        if (pc1 == null) {
            pc1VH.set(MEMORY, MemorySegment.NULL);
        } else {
            pc1VH.set(MEMORY, pc1.MEMORY);
        }
    }

    private static final VarHandleW pc2VH = VarHandleW.of(
        LAYOUT.varHandle(
            MemoryLayout.PathElement.groupElement("pc2")
        )
    );

    public io.vproxy.pni.test.ToStringClass2 getPc2() {
        var SEG = pc2VH.getMemorySegment(MEMORY);
        if (SEG.address() == 0) return null;
        return new io.vproxy.pni.test.ToStringClass2(SEG);
    }

    public void setPc2(io.vproxy.pni.test.ToStringClass2 pc2) {
        if (pc2 == null) {
            pc2VH.set(MEMORY, MemorySegment.NULL);
        } else {
            pc2VH.set(MEMORY, pc2.MEMORY);
        }
    }

    private static final VarHandleW pcrVH = VarHandleW.of(
        LAYOUT.varHandle(
            MemoryLayout.PathElement.groupElement("pcr")
        )
    );

    public io.vproxy.pni.test.ToStringClassRecurse getPcr() {
        var SEG = pcrVH.getMemorySegment(MEMORY);
        if (SEG.address() == 0) return null;
        return new io.vproxy.pni.test.ToStringClassRecurse(SEG);
    }

    public void setPcr(io.vproxy.pni.test.ToStringClassRecurse pcr) {
        if (pcr == null) {
            pcrVH.set(MEMORY, MemorySegment.NULL);
        } else {
            pcrVH.set(MEMORY, pcr.MEMORY);
        }
    }

    public ToStringUnion(MemorySegment MEMORY) {
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
        OFFSET += ValueLayout.JAVA_LONG_UNALIGNED.byteSize();
        OFFSET = 0;
        this.c1 = new io.vproxy.pni.test.ToStringClass(MEMORY.asSlice(OFFSET, io.vproxy.pni.test.ToStringClass.LAYOUT.byteSize()));
        OFFSET += io.vproxy.pni.test.ToStringClass.LAYOUT.byteSize();
        OFFSET = 0;
        this.c2 = new io.vproxy.pni.test.ToStringClass2(MEMORY.asSlice(OFFSET, io.vproxy.pni.test.ToStringClass2.LAYOUT.byteSize()));
        OFFSET += io.vproxy.pni.test.ToStringClass2.LAYOUT.byteSize();
        OFFSET = 0;
        this.cr = new io.vproxy.pni.test.ToStringClassRecurse(MEMORY.asSlice(OFFSET, io.vproxy.pni.test.ToStringClassRecurse.LAYOUT.byteSize()));
        OFFSET += io.vproxy.pni.test.ToStringClassRecurse.LAYOUT.byteSize();
        OFFSET = 0;
        OFFSET += 8;
        OFFSET = 0;
        OFFSET += 8;
        OFFSET = 0;
        OFFSET += 8;
        OFFSET = 0;
    }

    public ToStringUnion(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT));
    }

    @Override
    public void toString(StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
        if (!VISITED.add(new NativeObjectTuple(this))) {
            SB.append("<...>@").append(Long.toString(MEMORY.address(), 16));
            return;
        }
        CORRUPTED_MEMORY = true;
        SB.append("ToStringUnion(\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("num => ");
            SB.append(getNum());
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("c1 => ");
            PanamaUtils.nativeObjectToString(getC1(), SB, INDENT + 4, VISITED, CORRUPTED_MEMORY);
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("c2 => ");
            PanamaUtils.nativeObjectToString(getC2(), SB, INDENT + 4, VISITED, CORRUPTED_MEMORY);
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("cr => ");
            PanamaUtils.nativeObjectToString(getCr(), SB, INDENT + 4, VISITED, CORRUPTED_MEMORY);
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("pc1 => ");
            if (CORRUPTED_MEMORY) SB.append("<?>");
            else PanamaUtils.nativeObjectToString(getPc1(), SB, INDENT + 4, VISITED, CORRUPTED_MEMORY);
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("pc2 => ");
            if (CORRUPTED_MEMORY) SB.append("<?>");
            else PanamaUtils.nativeObjectToString(getPc2(), SB, INDENT + 4, VISITED, CORRUPTED_MEMORY);
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("pcr => ");
            if (CORRUPTED_MEMORY) SB.append("<?>");
            else PanamaUtils.nativeObjectToString(getPcr(), SB, INDENT + 4, VISITED, CORRUPTED_MEMORY);
        }
        SB.append("\n");
        SB.append(" ".repeat(INDENT)).append(")@").append(Long.toString(MEMORY.address(), 16));
    }

    public static class Array extends RefArray<ToStringUnion> {
        public Array(MemorySegment buf) {
            super(buf, ToStringUnion.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            super(allocator, ToStringUnion.LAYOUT, len);
        }

        public Array(PNIBuf buf) {
            super(buf, ToStringUnion.LAYOUT);
        }

        @Override
        protected void elementToString(io.vproxy.pni.test.ToStringUnion ELEM, StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
            ELEM.toString(SB, INDENT, VISITED, CORRUPTED_MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "ToStringUnion.Array";
        }

        @Override
        protected ToStringUnion construct(MemorySegment seg) {
            return new ToStringUnion(seg);
        }

        @Override
        protected MemorySegment getSegment(ToStringUnion value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<ToStringUnion> {
        private Func(io.vproxy.pni.CallSite<ToStringUnion> func) {
            super(func);
        }

        private Func(io.vproxy.pni.CallSite<ToStringUnion> func, Options opts) {
            super(func, opts);
        }

        private Func(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static Func of(io.vproxy.pni.CallSite<ToStringUnion> func) {
            return new Func(func);
        }

        public static Func of(io.vproxy.pni.CallSite<ToStringUnion> func, Options opts) {
            return new Func(func, opts);
        }

        public static Func of(MemorySegment MEMORY) {
            return new Func(MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "ToStringUnion.Func";
        }

        @Override
        protected ToStringUnion construct(MemorySegment seg) {
            return new ToStringUnion(seg);
        }
    }
}
// metadata.generator-version: pni test
// sha256:580a980b2a94e2dcae8d763ea22aa639708565271344da86cd7d09b20bc7b534
