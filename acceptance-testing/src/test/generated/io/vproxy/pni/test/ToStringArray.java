package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class ToStringArray extends AbstractNativeObject implements NativeObject {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        MemoryLayout.sequenceLayout(0L, io.vproxy.pni.test.ToStringClass.LAYOUT).withName("arrc"),
        MemoryLayout.sequenceLayout(2L, io.vproxy.pni.test.ToStringClass.LAYOUT).withName("arrcLen2"),
        PNIBuf.LAYOUT.withName("parrc")
    );
    public final MemorySegment MEMORY;

    @Override
    public MemorySegment MEMORY() {
        return MEMORY;
    }

    private final io.vproxy.pni.test.ToStringClass.Array arrc;

    public io.vproxy.pni.test.ToStringClass.Array getArrc() {
        return this.arrc;
    }

    private final io.vproxy.pni.test.ToStringClass.Array arrcLen2;

    public io.vproxy.pni.test.ToStringClass.Array getArrcLen2() {
        return this.arrcLen2;
    }

    private final PNIBuf parrc;

    public io.vproxy.pni.test.ToStringClass.Array getParrc() {
        var SEG = this.parrc.get();
        if (SEG == null) return null;
        return new io.vproxy.pni.test.ToStringClass.Array(SEG);
    }

    public void setParrc(io.vproxy.pni.test.ToStringClass.Array parrc) {
        if (parrc == null) {
            this.parrc.setToNull();
        } else {
            this.parrc.set(parrc.MEMORY);
        }
    }

    public ToStringArray(MemorySegment MEMORY) {
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
        this.arrc = new io.vproxy.pni.test.ToStringClass.Array(MEMORY.asSlice(OFFSET, 0 * io.vproxy.pni.test.ToStringClass.LAYOUT.byteSize()));
        OFFSET += 0 * io.vproxy.pni.test.ToStringClass.LAYOUT.byteSize();
        this.arrcLen2 = new io.vproxy.pni.test.ToStringClass.Array(MEMORY.asSlice(OFFSET, 2 * io.vproxy.pni.test.ToStringClass.LAYOUT.byteSize()));
        OFFSET += 2 * io.vproxy.pni.test.ToStringClass.LAYOUT.byteSize();
        this.parrc = new PNIBuf(MEMORY.asSlice(OFFSET, PNIBuf.LAYOUT.byteSize()));
        OFFSET += PNIBuf.LAYOUT.byteSize();
    }

    public ToStringArray(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT.byteSize()));
    }

    @Override
    public void toString(StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
        if (!VISITED.add(new NativeObjectTuple(this))) {
            SB.append("<...>@").append(Long.toString(MEMORY.address(), 16));
            return;
        }
        SB.append("ToStringArray{\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("arrc => ");
            if (CORRUPTED_MEMORY) SB.append("<?>");
            else PanamaUtils.nativeObjectToString(getArrc(), SB, INDENT + 4, VISITED, CORRUPTED_MEMORY);
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("arrcLen2 => ");
            if (CORRUPTED_MEMORY) SB.append("<?>");
            else PanamaUtils.nativeObjectToString(getArrcLen2(), SB, INDENT + 4, VISITED, CORRUPTED_MEMORY);
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("parrc => ");
            if (CORRUPTED_MEMORY) SB.append("<?>");
            else PanamaUtils.nativeObjectToString(getParrc(), SB, INDENT + 4, VISITED, CORRUPTED_MEMORY);
        }
        SB.append("\n");
        SB.append(" ".repeat(INDENT)).append("}@").append(Long.toString(MEMORY.address(), 16));
    }

    public static class Array extends RefArray<ToStringArray> {
        public Array(MemorySegment buf) {
            super(buf, ToStringArray.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            this(allocator.allocate(ToStringArray.LAYOUT.byteSize() * len));
        }

        public Array(PNIBuf buf) {
            this(buf.get());
        }

        @Override
        protected void elementToString(io.vproxy.pni.test.ToStringArray ELEM, StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
            ELEM.toString(SB, INDENT, VISITED, CORRUPTED_MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "ToStringArray.Array";
        }

        @Override
        protected ToStringArray construct(MemorySegment seg) {
            return new ToStringArray(seg);
        }

        @Override
        protected MemorySegment getSegment(ToStringArray value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<ToStringArray> {
        private Func(io.vproxy.pni.CallSite<ToStringArray> func) {
            super(func);
        }

        private Func(io.vproxy.pni.CallSite<ToStringArray> func, Options opts) {
            super(func, opts);
        }

        private Func(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static Func of(io.vproxy.pni.CallSite<ToStringArray> func) {
            return new Func(func);
        }

        public static Func of(io.vproxy.pni.CallSite<ToStringArray> func, Options opts) {
            return new Func(func, opts);
        }

        public static Func of(MemorySegment MEMORY) {
            return new Func(MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "ToStringArray.Func";
        }

        @Override
        protected ToStringArray construct(MemorySegment seg) {
            return new ToStringArray(seg);
        }
    }
}
// metadata.generator-version: pni test
// sha256:3286f67e193fc4d5aedddb369db5529ff9ed0d659d4e6d8e574a68e0f9d64d08
