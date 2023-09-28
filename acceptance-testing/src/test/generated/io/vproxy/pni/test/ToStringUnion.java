package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class ToStringUnion {
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

    private static final VarHandle numVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("num")
    );

    public long getNum() {
        return (long) numVH.get(MEMORY);
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

    private static final VarHandle pc1VH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("pc1")
    );

    public io.vproxy.pni.test.ToStringClass getPc1() {
        var SEG = (MemorySegment) pc1VH.get(MEMORY);
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

    private static final VarHandle pc2VH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("pc2")
    );

    public io.vproxy.pni.test.ToStringClass2 getPc2() {
        var SEG = (MemorySegment) pc2VH.get(MEMORY);
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

    private static final VarHandle pcrVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("pcr")
    );

    public io.vproxy.pni.test.ToStringClassRecurse getPcr() {
        var SEG = (MemorySegment) pcrVH.get(MEMORY);
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
        this(ALLOCATOR.allocate(LAYOUT.byteSize()));
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        toString(sb, 0, new java.util.HashSet<>(), true);
        return sb.toString();
    }

    public void toString(StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
        if (!VISITED.add(new NativeObjectTuple(getClass(), MEMORY.address()))) {
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
            getC1().toString(SB, INDENT + 4, VISITED, CORRUPTED_MEMORY);
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("c2 => ");
            getC2().toString(SB, INDENT + 4, VISITED, CORRUPTED_MEMORY);
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("cr => ");
            getCr().toString(SB, INDENT + 4, VISITED, CORRUPTED_MEMORY);
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("pc1 => ");
            if (CORRUPTED_MEMORY) {
                SB.append("<?>");
            } else {
                var VALUE = getPc1();
                if (VALUE == null) SB.append("null");
                else VALUE.toString(SB, INDENT + 4, VISITED, CORRUPTED_MEMORY);
            }
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("pc2 => ");
            if (CORRUPTED_MEMORY) {
                SB.append("<?>");
            } else {
                var VALUE = getPc2();
                if (VALUE == null) SB.append("null");
                else VALUE.toString(SB, INDENT + 4, VISITED, CORRUPTED_MEMORY);
            }
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("pcr => ");
            if (CORRUPTED_MEMORY) {
                SB.append("<?>");
            } else {
                var VALUE = getPcr();
                if (VALUE == null) SB.append("null");
                else VALUE.toString(SB, INDENT + 4, VISITED, CORRUPTED_MEMORY);
            }
        }
        SB.append("\n");
        SB.append(" ".repeat(INDENT)).append(")@").append(Long.toString(MEMORY.address(), 16));
    }

    public static class Array extends RefArray<ToStringUnion> {
        public Array(MemorySegment buf) {
            super(buf, ToStringUnion.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            this(allocator.allocate(ToStringUnion.LAYOUT.byteSize() * len));
        }

        public Array(PNIBuf buf) {
            this(buf.get());
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
// sha256:bb864de46f429943b6f41936803c5bdf1c58f6d90634718f8e32cba46519816e
