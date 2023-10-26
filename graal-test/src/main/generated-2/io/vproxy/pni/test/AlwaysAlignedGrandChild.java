package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;
import io.vproxy.pni.graal.*;
import org.graalvm.nativeimage.*;
import org.graalvm.nativeimage.c.function.*;
import org.graalvm.nativeimage.c.type.VoidPointer;
import org.graalvm.word.WordFactory;

public class AlwaysAlignedGrandChild extends io.vproxy.pni.test.AlwaysAlignedChild implements NativeObject {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        io.vproxy.pni.test.AlwaysAlignedChild.LAYOUT,
        MemoryLayout.sequenceLayout(4L, ValueLayout.JAVA_BYTE) /* padding */,
        ValueLayout.JAVA_LONG.withName("c"),
        MemoryLayout.sequenceLayout(3L, ValueLayout.JAVA_INT).withName("array"),
        MemoryLayout.sequenceLayout(4L, ValueLayout.JAVA_BYTE) /* padding */
    ).withByteAlignment(8);
    public final MemorySegment MEMORY;

    @Override
    public MemorySegment MEMORY() {
        return MEMORY;
    }

    private static final VarHandle cVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("c")
    );

    public long getC() {
        return (long) cVH.get(MEMORY);
    }

    public void setC(long c) {
        cVH.set(MEMORY, c);
    }

    private final IntArray array;

    public IntArray getArray() {
        return this.array;
    }

    public AlwaysAlignedGrandChild(MemorySegment MEMORY) {
        super(MEMORY);
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
        OFFSET += io.vproxy.pni.test.AlwaysAlignedChild.LAYOUT.byteSize();
        OFFSET += 4; // head padding
        OFFSET += ValueLayout.JAVA_LONG_UNALIGNED.byteSize();
        this.array = new IntArray(MEMORY.asSlice(OFFSET, 3 * ValueLayout.JAVA_INT_UNALIGNED.byteSize()));
        OFFSET += 3 * ValueLayout.JAVA_INT_UNALIGNED.byteSize();
        OFFSET += 4; /* padding */
    }

    public AlwaysAlignedGrandChild(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT));
    }

    @Override
    public void toString(StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
        if (!VISITED.add(new NativeObjectTuple(this))) {
            SB.append("<...>@").append(Long.toString(MEMORY.address(), 16));
            return;
        }
        SB.append("AlwaysAlignedGrandChild{\n");
        SB.append(" ".repeat(INDENT + 4)).append("SUPER => ");
        {
            INDENT += 4;
            SB.append("AlwaysAlignedChild{\n");
            SB.append(" ".repeat(INDENT + 4)).append("SUPER => ");
            {
                INDENT += 4;
                SB.append("AlwaysAlignedBase{\n");
                {
                    SB.append(" ".repeat(INDENT + 4)).append("a => ");
                    SB.append(getA());
                }
                SB.append("\n");
                SB.append(" ".repeat(INDENT)).append("}@").append(Long.toString(MEMORY.address(), 16));
                INDENT -= 4;
                SB.append(",\n");
            }
            {
                SB.append(" ".repeat(INDENT + 4)).append("b => ");
                SB.append(getB());
            }
            SB.append("\n");
            SB.append(" ".repeat(INDENT)).append("}@").append(Long.toString(MEMORY.address(), 16));
            INDENT -= 4;
            SB.append(",\n");
        }
        {
            SB.append(" ".repeat(INDENT + 4)).append("c => ");
            SB.append(getC());
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("array => ");
            PanamaUtils.nativeObjectToString(getArray(), SB, INDENT + 4, VISITED, CORRUPTED_MEMORY);
        }
        SB.append("\n");
        SB.append(" ".repeat(INDENT)).append("}@").append(Long.toString(MEMORY.address(), 16));
    }

    public static class Array extends RefArray<AlwaysAlignedGrandChild> {
        public Array(MemorySegment buf) {
            super(buf, AlwaysAlignedGrandChild.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            super(allocator, AlwaysAlignedGrandChild.LAYOUT, len);
        }

        public Array(PNIBuf buf) {
            super(buf, AlwaysAlignedGrandChild.LAYOUT);
        }

        @Override
        protected void elementToString(io.vproxy.pni.test.AlwaysAlignedGrandChild ELEM, StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
            ELEM.toString(SB, INDENT, VISITED, CORRUPTED_MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "AlwaysAlignedGrandChild.Array";
        }

        @Override
        protected AlwaysAlignedGrandChild construct(MemorySegment seg) {
            return new AlwaysAlignedGrandChild(seg);
        }

        @Override
        protected MemorySegment getSegment(AlwaysAlignedGrandChild value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<AlwaysAlignedGrandChild> {
        private Func(io.vproxy.pni.CallSite<AlwaysAlignedGrandChild> func) {
            super(func);
        }

        private Func(io.vproxy.pni.CallSite<AlwaysAlignedGrandChild> func, Options opts) {
            super(func, opts);
        }

        private Func(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static Func of(io.vproxy.pni.CallSite<AlwaysAlignedGrandChild> func) {
            return new Func(func);
        }

        public static Func of(io.vproxy.pni.CallSite<AlwaysAlignedGrandChild> func, Options opts) {
            return new Func(func, opts);
        }

        public static Func of(MemorySegment MEMORY) {
            return new Func(MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "AlwaysAlignedGrandChild.Func";
        }

        @Override
        protected AlwaysAlignedGrandChild construct(MemorySegment seg) {
            return new AlwaysAlignedGrandChild(seg);
        }
    }
}
// metadata.generator-version: pni test
// sha256:8d95550a45809d01414fbebb0af1c0d4959f6064b7fee2e4f687e7ae501a5bc1
