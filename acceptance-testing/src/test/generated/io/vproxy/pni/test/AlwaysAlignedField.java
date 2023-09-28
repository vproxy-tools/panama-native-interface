package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class AlwaysAlignedField {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        ValueLayout.JAVA_BYTE.withName("a"),
        ValueLayout.JAVA_SHORT_UNALIGNED.withName("b"),
        ValueLayout.JAVA_BYTE.withName("c"),
        ValueLayout.JAVA_INT.withName("d"),
        ValueLayout.JAVA_LONG.withName("e")
    );
    public final MemorySegment MEMORY;

    private static final VarHandle aVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("a")
    );

    public byte getA() {
        return (byte) aVH.get(MEMORY);
    }

    public void setA(byte a) {
        aVH.set(MEMORY, a);
    }

    private static final VarHandle bVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("b")
    );

    public short getB() {
        return (short) bVH.get(MEMORY);
    }

    public void setB(short b) {
        bVH.set(MEMORY, b);
    }

    private static final VarHandle cVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("c")
    );

    public byte getC() {
        return (byte) cVH.get(MEMORY);
    }

    public void setC(byte c) {
        cVH.set(MEMORY, c);
    }

    private static final VarHandle dVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("d")
    );

    public int getD() {
        return (int) dVH.get(MEMORY);
    }

    public void setD(int d) {
        dVH.set(MEMORY, d);
    }

    private static final VarHandle eVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("e")
    );

    public long getE() {
        return (long) eVH.get(MEMORY);
    }

    public void setE(long e) {
        eVH.set(MEMORY, e);
    }

    public AlwaysAlignedField(MemorySegment MEMORY) {
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
        OFFSET += ValueLayout.JAVA_BYTE.byteSize();
        OFFSET += ValueLayout.JAVA_SHORT_UNALIGNED.byteSize();
        OFFSET += ValueLayout.JAVA_BYTE.byteSize();
        OFFSET += ValueLayout.JAVA_INT_UNALIGNED.byteSize();
        OFFSET += ValueLayout.JAVA_LONG_UNALIGNED.byteSize();
    }

    public AlwaysAlignedField(Allocator ALLOCATOR) {
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
        SB.append("AlwaysAlignedField{\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("a => ");
            SB.append(getA());
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("b => ");
            SB.append(getB());
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("c => ");
            SB.append(getC());
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("d => ");
            SB.append(getD());
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("e => ");
            SB.append(getE());
        }
        SB.append("\n");
        SB.append(" ".repeat(INDENT)).append("}@").append(Long.toString(MEMORY.address(), 16));
    }

    public static class Array extends RefArray<AlwaysAlignedField> {
        public Array(MemorySegment buf) {
            super(buf, AlwaysAlignedField.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            this(allocator.allocate(AlwaysAlignedField.LAYOUT.byteSize() * len));
        }

        public Array(PNIBuf buf) {
            this(buf.get());
        }

        @Override
        protected void elementToString(io.vproxy.pni.test.AlwaysAlignedField ELEM, StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
            ELEM.toString(SB, INDENT, VISITED, CORRUPTED_MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "AlwaysAlignedField.Array";
        }

        @Override
        protected AlwaysAlignedField construct(MemorySegment seg) {
            return new AlwaysAlignedField(seg);
        }

        @Override
        protected MemorySegment getSegment(AlwaysAlignedField value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<AlwaysAlignedField> {
        private Func(io.vproxy.pni.CallSite<AlwaysAlignedField> func) {
            super(func);
        }

        private Func(io.vproxy.pni.CallSite<AlwaysAlignedField> func, Options opts) {
            super(func, opts);
        }

        private Func(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static Func of(io.vproxy.pni.CallSite<AlwaysAlignedField> func) {
            return new Func(func);
        }

        public static Func of(io.vproxy.pni.CallSite<AlwaysAlignedField> func, Options opts) {
            return new Func(func, opts);
        }

        public static Func of(MemorySegment MEMORY) {
            return new Func(MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "AlwaysAlignedField.Func";
        }

        @Override
        protected AlwaysAlignedField construct(MemorySegment seg) {
            return new AlwaysAlignedField(seg);
        }
    }
}
// metadata.generator-version: pni test
// sha256:fd43e55348ad756433cf92e28686765ddf35b13d67d4bd6ded7bb075954bbf77
