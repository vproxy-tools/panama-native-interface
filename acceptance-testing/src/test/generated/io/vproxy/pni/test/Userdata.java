package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class Userdata {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        ValueLayout.JAVA_INT_UNALIGNED.withName("x"),
        MemoryLayout.sequenceLayout(4L, ValueLayout.JAVA_BYTE) /* padding */,
        ValueLayout.JAVA_LONG_UNALIGNED.withName("y"),
        ValueLayout.JAVA_SHORT_UNALIGNED.withName("z"),
        MemoryLayout.sequenceLayout(6L, ValueLayout.JAVA_BYTE) /* padding */
    );
    public final MemorySegment MEMORY;

    private static final VarHandle xVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("x")
    );

    public int getX() {
        return (int) xVH.get(MEMORY);
    }

    public void setX(int x) {
        xVH.set(MEMORY, x);
    }

    private static final VarHandle yVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("y")
    );

    public long getY() {
        return (long) yVH.get(MEMORY);
    }

    public void setY(long y) {
        yVH.set(MEMORY, y);
    }

    private static final VarHandle zVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("z")
    );

    public short getZ() {
        return (short) zVH.get(MEMORY);
    }

    public void setZ(short z) {
        zVH.set(MEMORY, z);
    }

    public Userdata(MemorySegment MEMORY) {
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
        OFFSET += ValueLayout.JAVA_INT_UNALIGNED.byteSize();
        OFFSET += 4; /* padding */
        OFFSET += ValueLayout.JAVA_LONG_UNALIGNED.byteSize();
        OFFSET += ValueLayout.JAVA_SHORT_UNALIGNED.byteSize();
        OFFSET += 6; /* padding */
    }

    public Userdata(Allocator ALLOCATOR) {
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
        SB.append("Userdata{\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("x => ");
            SB.append(getX());
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("y => ");
            SB.append(getY());
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("z => ");
            SB.append(getZ());
        }
        SB.append("\n");
        SB.append(" ".repeat(INDENT)).append("}@").append(Long.toString(MEMORY.address(), 16));
    }

    public static class Array extends RefArray<Userdata> {
        public Array(MemorySegment buf) {
            super(buf, Userdata.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            this(allocator.allocate(Userdata.LAYOUT.byteSize() * len));
        }

        public Array(PNIBuf buf) {
            this(buf.get());
        }

        @Override
        protected void elementToString(io.vproxy.pni.test.Userdata ELEM, StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
            ELEM.toString(SB, INDENT, VISITED, CORRUPTED_MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "Userdata.Array";
        }

        @Override
        protected Userdata construct(MemorySegment seg) {
            return new Userdata(seg);
        }

        @Override
        protected MemorySegment getSegment(Userdata value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<Userdata> {
        private Func(io.vproxy.pni.CallSite<Userdata> func) {
            super(func);
        }

        private Func(io.vproxy.pni.CallSite<Userdata> func, Options opts) {
            super(func, opts);
        }

        private Func(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static Func of(io.vproxy.pni.CallSite<Userdata> func) {
            return new Func(func);
        }

        public static Func of(io.vproxy.pni.CallSite<Userdata> func, Options opts) {
            return new Func(func, opts);
        }

        public static Func of(MemorySegment MEMORY) {
            return new Func(MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "Userdata.Func";
        }

        @Override
        protected Userdata construct(MemorySegment seg) {
            return new Userdata(seg);
        }
    }
}
// metadata.generator-version: pni test
// sha256:2de31a086bbe697adbee5a26bff73273bd9fa1914fa326992b993489af9a6d26
