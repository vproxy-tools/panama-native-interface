package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.hack.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class SizeofStruct extends AbstractNativeObject implements NativeObject {
    private static final MethodHandle __getLayoutByteSizeMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), long.class, "JavaCritical_io_vproxy_pni_test_SizeofStruct___getLayoutByteSize");

    private static long __getLayoutByteSize() {
        long RESULT;
        try {
            RESULT = (long) __getLayoutByteSizeMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    public static final MemoryLayout LAYOUT = PanamaUtils.padLayout(__getLayoutByteSize(), MemoryLayout::structLayout,
        ValueLayout.JAVA_INT_UNALIGNED.withName("a"),
        ValueLayout.JAVA_SHORT_UNALIGNED.withName("b"),
        MemoryLayout.sequenceLayout(2L, ValueLayout.JAVA_BYTE) /* padding */
    );
    public final MemorySegment MEMORY;

    @Override
    public MemorySegment MEMORY() {
        return MEMORY;
    }

    private static final VarHandleW aVH = VarHandleW.of(
        LAYOUT.varHandle(
            MemoryLayout.PathElement.groupElement("a")
        )
    );

    public int getA() {
        return aVH.getInt(MEMORY);
    }

    public void setA(int a) {
        aVH.set(MEMORY, a);
    }

    private static final VarHandleW bVH = VarHandleW.of(
        LAYOUT.varHandle(
            MemoryLayout.PathElement.groupElement("b")
        )
    );

    public short getB() {
        return bVH.getShort(MEMORY);
    }

    public void setB(short b) {
        bVH.set(MEMORY, b);
    }

    public SizeofStruct(MemorySegment MEMORY) {
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
        OFFSET += ValueLayout.JAVA_INT_UNALIGNED.byteSize();
        OFFSET += ValueLayout.JAVA_SHORT_UNALIGNED.byteSize();
        OFFSET += 2; /* padding */
    }

    public SizeofStruct(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT));
    }

    @Override
    public void toString(StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
        if (!VISITED.add(new NativeObjectTuple(this))) {
            SB.append("<...>@").append(Long.toString(MEMORY.address(), 16));
            return;
        }
        SB.append("SizeofStruct{\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("a => ");
            SB.append(getA());
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("b => ");
            SB.append(getB());
        }
        SB.append("\n");
        SB.append(" ".repeat(INDENT)).append("}@").append(Long.toString(MEMORY.address(), 16));
    }

    public static class Array extends RefArray<SizeofStruct> {
        public Array(MemorySegment buf) {
            super(buf, SizeofStruct.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            super(allocator, SizeofStruct.LAYOUT, len);
        }

        public Array(PNIBuf buf) {
            super(buf, SizeofStruct.LAYOUT);
        }

        @Override
        protected void elementToString(io.vproxy.pni.test.SizeofStruct ELEM, StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
            ELEM.toString(SB, INDENT, VISITED, CORRUPTED_MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "SizeofStruct.Array";
        }

        @Override
        protected SizeofStruct construct(MemorySegment seg) {
            return new SizeofStruct(seg);
        }

        @Override
        protected MemorySegment getSegment(SizeofStruct value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<SizeofStruct> {
        private Func(io.vproxy.pni.CallSite<SizeofStruct> func) {
            super(func);
        }

        private Func(io.vproxy.pni.CallSite<SizeofStruct> func, Options opts) {
            super(func, opts);
        }

        private Func(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static Func of(io.vproxy.pni.CallSite<SizeofStruct> func) {
            return new Func(func);
        }

        public static Func of(io.vproxy.pni.CallSite<SizeofStruct> func, Options opts) {
            return new Func(func, opts);
        }

        public static Func of(MemorySegment MEMORY) {
            return new Func(MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "SizeofStruct.Func";
        }

        @Override
        protected SizeofStruct construct(MemorySegment seg) {
            return new SizeofStruct(seg);
        }
    }
}
// metadata.generator-version: pni test
// sha256:25809e0478bd472fd09e099b8b044ddce8054b219ecbd2e211458bbc2157a0c8
