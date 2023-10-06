package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class ToStringClass2 extends AbstractNativeObject implements NativeObject {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        ValueLayout.JAVA_LONG_UNALIGNED.withName("num"),
        ValueLayout.ADDRESS_UNALIGNED.withName("ref"),
        ValueLayout.ADDRESS_UNALIGNED.withName("func"),
        PNIBuf.LAYOUT.withName("arrc")
    );
    public final MemorySegment MEMORY;

    @Override
    public MemorySegment MEMORY() {
        return MEMORY;
    }

    private static final VarHandle numVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("num")
    );

    public long getNum() {
        return (long) numVH.get(MEMORY);
    }

    public void setNum(long num) {
        numVH.set(MEMORY, num);
    }

    private static final VarHandle refVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("ref")
    );

    public PNIRef<java.lang.Integer> getRef() {
        var SEG = (MemorySegment) refVH.get(MEMORY);
        if (SEG.address() == 0) return null;
        return PNIRef.of(SEG);
    }

    public void setRef(PNIRef<java.lang.Integer> ref) {
        if (ref == null) {
            refVH.set(MEMORY, MemorySegment.NULL);
        } else {
            refVH.set(MEMORY, ref.MEMORY);
        }
    }

    private static final VarHandle funcVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("func")
    );

    public PNIFunc<Void> getFunc() {
        var SEG = (MemorySegment) funcVH.get(MEMORY);
        if (SEG.address() == 0) return null;
        return PNIFunc.VoidFunc.of(SEG);
    }

    public void setFunc(PNIFunc<Void> func) {
        if (func == null) {
            funcVH.set(MEMORY, MemorySegment.NULL);
        } else {
            funcVH.set(MEMORY, func.MEMORY);
        }
    }

    private final PNIBuf arrc;

    public io.vproxy.pni.test.ToStringClass.Array getArrc() {
        var SEG = this.arrc.get();
        if (SEG == null) return null;
        return new io.vproxy.pni.test.ToStringClass.Array(SEG);
    }

    public void setArrc(io.vproxy.pni.test.ToStringClass.Array arrc) {
        if (arrc == null) {
            this.arrc.setToNull();
        } else {
            this.arrc.set(arrc.MEMORY);
        }
    }

    public ToStringClass2(MemorySegment MEMORY) {
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
        OFFSET += ValueLayout.JAVA_LONG_UNALIGNED.byteSize();
        OFFSET += ValueLayout.ADDRESS_UNALIGNED.byteSize();
        OFFSET += ValueLayout.ADDRESS_UNALIGNED.byteSize();
        this.arrc = new PNIBuf(MEMORY.asSlice(OFFSET, PNIBuf.LAYOUT.byteSize()));
        OFFSET += PNIBuf.LAYOUT.byteSize();
    }

    public ToStringClass2(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT));
    }

    @Override
    public void toString(StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
        if (!VISITED.add(new NativeObjectTuple(this))) {
            SB.append("<...>@").append(Long.toString(MEMORY.address(), 16));
            return;
        }
        SB.append("ToStringClass2{\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("num => ");
            SB.append(getNum());
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("ref => ");
            if (CORRUPTED_MEMORY) SB.append("<?>");
            else PanamaUtils.nativeObjectToString(getRef(), SB, INDENT + 4, VISITED, CORRUPTED_MEMORY);
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("func => ");
            if (CORRUPTED_MEMORY) SB.append("<?>");
            else PanamaUtils.nativeObjectToString(getFunc(), SB, INDENT + 4, VISITED, CORRUPTED_MEMORY);
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("arrc => ");
            if (CORRUPTED_MEMORY) SB.append("<?>");
            else PanamaUtils.nativeObjectToString(getArrc(), SB, INDENT + 4, VISITED, CORRUPTED_MEMORY);
        }
        SB.append("\n");
        SB.append(" ".repeat(INDENT)).append("}@").append(Long.toString(MEMORY.address(), 16));
    }

    public static class Array extends RefArray<ToStringClass2> {
        public Array(MemorySegment buf) {
            super(buf, ToStringClass2.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            super(allocator, ToStringClass2.LAYOUT, len);
        }

        public Array(PNIBuf buf) {
            super(buf, ToStringClass2.LAYOUT);
        }

        @Override
        protected void elementToString(io.vproxy.pni.test.ToStringClass2 ELEM, StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
            ELEM.toString(SB, INDENT, VISITED, CORRUPTED_MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "ToStringClass2.Array";
        }

        @Override
        protected ToStringClass2 construct(MemorySegment seg) {
            return new ToStringClass2(seg);
        }

        @Override
        protected MemorySegment getSegment(ToStringClass2 value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<ToStringClass2> {
        private Func(io.vproxy.pni.CallSite<ToStringClass2> func) {
            super(func);
        }

        private Func(io.vproxy.pni.CallSite<ToStringClass2> func, Options opts) {
            super(func, opts);
        }

        private Func(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static Func of(io.vproxy.pni.CallSite<ToStringClass2> func) {
            return new Func(func);
        }

        public static Func of(io.vproxy.pni.CallSite<ToStringClass2> func, Options opts) {
            return new Func(func, opts);
        }

        public static Func of(MemorySegment MEMORY) {
            return new Func(MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "ToStringClass2.Func";
        }

        @Override
        protected ToStringClass2 construct(MemorySegment seg) {
            return new ToStringClass2(seg);
        }
    }
}
// metadata.generator-version: pni test
// sha256:afd35a484be9bccd503d5c1944b3e17ee5434cfffeea1ed92ba0f221a899b638
