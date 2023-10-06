package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class SizeofUnion extends AbstractNativeObject implements NativeObject {
    private static final MethodHandle __getLayoutByteSizeMH = PanamaUtils.lookupPNICriticalFunction(true, long.class, "JavaCritical_io_vproxy_pni_test_SizeofUnion___getLayoutByteSize");

    private static long __getLayoutByteSize() {
        long RESULT;
        try {
            RESULT = (long) __getLayoutByteSizeMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    public static final MemoryLayout LAYOUT = PanamaUtils.padLayout(__getLayoutByteSize(), MemoryLayout::unionLayout,
        io.vproxy.pni.test.SizeofStruct.LAYOUT.withName("st"),
        ValueLayout.JAVA_INT_UNALIGNED.withName("a")
    );
    public final MemorySegment MEMORY;

    @Override
    public MemorySegment MEMORY() {
        return MEMORY;
    }

    private final io.vproxy.pni.test.SizeofStruct st;

    public io.vproxy.pni.test.SizeofStruct getSt() {
        return this.st;
    }

    private static final VarHandle aVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("a")
    );

    public int getA() {
        return (int) aVH.get(MEMORY);
    }

    public void setA(int a) {
        aVH.set(MEMORY, a);
    }

    public SizeofUnion(MemorySegment MEMORY) {
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
        this.st = new io.vproxy.pni.test.SizeofStruct(MEMORY.asSlice(OFFSET, io.vproxy.pni.test.SizeofStruct.LAYOUT.byteSize()));
        OFFSET += io.vproxy.pni.test.SizeofStruct.LAYOUT.byteSize();
        OFFSET = 0;
        OFFSET += ValueLayout.JAVA_INT_UNALIGNED.byteSize();
        OFFSET = 0;
    }

    public SizeofUnion(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT));
    }

    @Override
    public void toString(StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
        if (!VISITED.add(new NativeObjectTuple(this))) {
            SB.append("<...>@").append(Long.toString(MEMORY.address(), 16));
            return;
        }
        CORRUPTED_MEMORY = true;
        SB.append("SizeofUnion(\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("st => ");
            PanamaUtils.nativeObjectToString(getSt(), SB, INDENT + 4, VISITED, CORRUPTED_MEMORY);
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("a => ");
            SB.append(getA());
        }
        SB.append("\n");
        SB.append(" ".repeat(INDENT)).append(")@").append(Long.toString(MEMORY.address(), 16));
    }

    public static class Array extends RefArray<SizeofUnion> {
        public Array(MemorySegment buf) {
            super(buf, SizeofUnion.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            super(allocator, SizeofUnion.LAYOUT, len);
        }

        public Array(PNIBuf buf) {
            super(buf, SizeofUnion.LAYOUT);
        }

        @Override
        protected void elementToString(io.vproxy.pni.test.SizeofUnion ELEM, StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
            ELEM.toString(SB, INDENT, VISITED, CORRUPTED_MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "SizeofUnion.Array";
        }

        @Override
        protected SizeofUnion construct(MemorySegment seg) {
            return new SizeofUnion(seg);
        }

        @Override
        protected MemorySegment getSegment(SizeofUnion value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<SizeofUnion> {
        private Func(io.vproxy.pni.CallSite<SizeofUnion> func) {
            super(func);
        }

        private Func(io.vproxy.pni.CallSite<SizeofUnion> func, Options opts) {
            super(func, opts);
        }

        private Func(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static Func of(io.vproxy.pni.CallSite<SizeofUnion> func) {
            return new Func(func);
        }

        public static Func of(io.vproxy.pni.CallSite<SizeofUnion> func, Options opts) {
            return new Func(func, opts);
        }

        public static Func of(MemorySegment MEMORY) {
            return new Func(MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "SizeofUnion.Func";
        }

        @Override
        protected SizeofUnion construct(MemorySegment seg) {
            return new SizeofUnion(seg);
        }
    }
}
// metadata.generator-version: pni test
// sha256:ab03785fbd3a4f4c2bb63632466a5100568978b1999cd25275de9a3d04e4dbec
