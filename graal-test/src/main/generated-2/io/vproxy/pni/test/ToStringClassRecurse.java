package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.hack.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;
import io.vproxy.pni.graal.*;
import org.graalvm.nativeimage.*;
import org.graalvm.nativeimage.c.function.*;
import org.graalvm.nativeimage.c.type.VoidPointer;
import org.graalvm.word.WordFactory;

public class ToStringClassRecurse extends AbstractNativeObject implements NativeObject {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        ValueLayout.JAVA_LONG_UNALIGNED.withName("num"),
        ValueLayout.ADDRESS_UNALIGNED.withName("c"),
        MemoryLayout.sequenceLayout(0L, ValueLayout.JAVA_INT_UNALIGNED).withName("arri")
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

    private static final VarHandleW cVH = VarHandleW.of(
        LAYOUT.varHandle(
            MemoryLayout.PathElement.groupElement("c")
        )
    );

    public io.vproxy.pni.test.ToStringClass getC() {
        var SEG = cVH.getMemorySegment(MEMORY);
        if (SEG.address() == 0) return null;
        return new io.vproxy.pni.test.ToStringClass(SEG);
    }

    public void setC(io.vproxy.pni.test.ToStringClass c) {
        if (c == null) {
            cVH.set(MEMORY, MemorySegment.NULL);
        } else {
            cVH.set(MEMORY, c.MEMORY);
        }
    }

    private final IntArray arri;

    public IntArray getArri() {
        return this.arri;
    }

    public ToStringClassRecurse(MemorySegment MEMORY) {
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
        OFFSET += ValueLayout.JAVA_LONG_UNALIGNED.byteSize();
        OFFSET += 8;
        this.arri = new IntArray(MEMORY.asSlice(OFFSET, 0 * ValueLayout.JAVA_INT_UNALIGNED.byteSize()));
        OFFSET += 0 * ValueLayout.JAVA_INT_UNALIGNED.byteSize();
    }

    public ToStringClassRecurse(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT));
    }

    @Override
    public void toString(StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
        if (!VISITED.add(new NativeObjectTuple(this))) {
            SB.append("<...>@").append(Long.toString(MEMORY.address(), 16));
            return;
        }
        SB.append("ToStringClassRecurse{\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("num => ");
            SB.append(getNum());
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("c => ");
            if (CORRUPTED_MEMORY) SB.append("<?>");
            else PanamaUtils.nativeObjectToString(getC(), SB, INDENT + 4, VISITED, CORRUPTED_MEMORY);
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("arri => ");
            PanamaUtils.nativeObjectToString(getArri(), SB, INDENT + 4, VISITED, CORRUPTED_MEMORY);
        }
        SB.append("\n");
        SB.append(" ".repeat(INDENT)).append("}@").append(Long.toString(MEMORY.address(), 16));
    }

    public static class Array extends RefArray<ToStringClassRecurse> {
        public Array(MemorySegment buf) {
            super(buf, ToStringClassRecurse.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            super(allocator, ToStringClassRecurse.LAYOUT, len);
        }

        public Array(PNIBuf buf) {
            super(buf, ToStringClassRecurse.LAYOUT);
        }

        @Override
        protected void elementToString(io.vproxy.pni.test.ToStringClassRecurse ELEM, StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
            ELEM.toString(SB, INDENT, VISITED, CORRUPTED_MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "ToStringClassRecurse.Array";
        }

        @Override
        protected ToStringClassRecurse construct(MemorySegment seg) {
            return new ToStringClassRecurse(seg);
        }

        @Override
        protected MemorySegment getSegment(ToStringClassRecurse value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<ToStringClassRecurse> {
        private Func(io.vproxy.pni.CallSite<ToStringClassRecurse> func) {
            super(func);
        }

        private Func(io.vproxy.pni.CallSite<ToStringClassRecurse> func, Options opts) {
            super(func, opts);
        }

        private Func(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static Func of(io.vproxy.pni.CallSite<ToStringClassRecurse> func) {
            return new Func(func);
        }

        public static Func of(io.vproxy.pni.CallSite<ToStringClassRecurse> func, Options opts) {
            return new Func(func, opts);
        }

        public static Func of(MemorySegment MEMORY) {
            return new Func(MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "ToStringClassRecurse.Func";
        }

        @Override
        protected ToStringClassRecurse construct(MemorySegment seg) {
            return new ToStringClassRecurse(seg);
        }
    }
}
// metadata.generator-version: pni test
// sha256:560f8aa5985094f1be7bb1194a7e35e35350eb88938842b96bcd26fbcdd2529e
