package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.hack.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class ToStringClass extends AbstractNativeObject implements NativeObject {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        ValueLayout.JAVA_LONG_UNALIGNED.withName("num"),
        io.vproxy.pni.test.ToStringClassRecurse.LAYOUT.withName("cr")
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

    private final io.vproxy.pni.test.ToStringClassRecurse cr;

    public io.vproxy.pni.test.ToStringClassRecurse getCr() {
        return this.cr;
    }

    public ToStringClass(MemorySegment MEMORY) {
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
        OFFSET += ValueLayout.JAVA_LONG_UNALIGNED.byteSize();
        this.cr = new io.vproxy.pni.test.ToStringClassRecurse(MEMORY.asSlice(OFFSET, io.vproxy.pni.test.ToStringClassRecurse.LAYOUT.byteSize()));
        OFFSET += io.vproxy.pni.test.ToStringClassRecurse.LAYOUT.byteSize();
    }

    public ToStringClass(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT));
    }

    @Override
    public void toString(StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
        if (!VISITED.add(new NativeObjectTuple(this))) {
            SB.append("<...>@").append(Long.toString(MEMORY.address(), 16));
            return;
        }
        SB.append("ToStringClass{\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("num => ");
            SB.append(getNum());
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("cr => ");
            PanamaUtils.nativeObjectToString(getCr(), SB, INDENT + 4, VISITED, CORRUPTED_MEMORY);
        }
        SB.append("\n");
        SB.append(" ".repeat(INDENT)).append("}@").append(Long.toString(MEMORY.address(), 16));
    }

    public static class Array extends RefArray<ToStringClass> {
        public Array(MemorySegment buf) {
            super(buf, ToStringClass.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            super(allocator, ToStringClass.LAYOUT, len);
        }

        public Array(PNIBuf buf) {
            super(buf, ToStringClass.LAYOUT);
        }

        @Override
        protected void elementToString(io.vproxy.pni.test.ToStringClass ELEM, StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
            ELEM.toString(SB, INDENT, VISITED, CORRUPTED_MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "ToStringClass.Array";
        }

        @Override
        protected ToStringClass construct(MemorySegment seg) {
            return new ToStringClass(seg);
        }

        @Override
        protected MemorySegment getSegment(ToStringClass value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<ToStringClass> {
        private Func(io.vproxy.pni.CallSite<ToStringClass> func) {
            super(func);
        }

        private Func(io.vproxy.pni.CallSite<ToStringClass> func, Options opts) {
            super(func, opts);
        }

        private Func(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static Func of(io.vproxy.pni.CallSite<ToStringClass> func) {
            return new Func(func);
        }

        public static Func of(io.vproxy.pni.CallSite<ToStringClass> func, Options opts) {
            return new Func(func, opts);
        }

        public static Func of(MemorySegment MEMORY) {
            return new Func(MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "ToStringClass.Func";
        }

        @Override
        protected ToStringClass construct(MemorySegment seg) {
            return new ToStringClass(seg);
        }
    }
}
// metadata.generator-version: pni test
// sha256:eba1c1c90444b21bc14c70d83c2b37572805f23731e4dfe4a49a606cd4269eb0
