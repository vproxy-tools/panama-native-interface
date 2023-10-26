package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class AlignChildClass extends io.vproxy.pni.test.AlignBaseClass implements NativeObject {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        io.vproxy.pni.test.AlignBaseClass.LAYOUT,
        MemoryLayout.sequenceLayout(14L, ValueLayout.JAVA_BYTE) /* padding */,
        ValueLayout.JAVA_INT.withName("b"),
        ValueLayout.JAVA_INT.withName("c"),
        MemoryLayout.sequenceLayout(8L, ValueLayout.JAVA_BYTE) /* padding */
    ).withByteAlignment(16);
    public final MemorySegment MEMORY;

    @Override
    public MemorySegment MEMORY() {
        return MEMORY;
    }

    private static final VarHandle bVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("b")
    );

    public int getB() {
        return (int) bVH.get(MEMORY);
    }

    public void setB(int b) {
        bVH.set(MEMORY, b);
    }

    private static final VarHandle cVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("c")
    );

    public int getC() {
        return (int) cVH.get(MEMORY);
    }

    public void setC(int c) {
        cVH.set(MEMORY, c);
    }

    public AlignChildClass(MemorySegment MEMORY) {
        super(MEMORY);
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
        OFFSET += io.vproxy.pni.test.AlignBaseClass.LAYOUT.byteSize();
        OFFSET += 14; // head padding
        OFFSET += ValueLayout.JAVA_INT_UNALIGNED.byteSize();
        OFFSET += ValueLayout.JAVA_INT_UNALIGNED.byteSize();
        OFFSET += 8; /* padding */
    }

    public AlignChildClass(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT));
    }

    private static final MethodHandle bbbbMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), int.class, "JavaCritical_io_vproxy_pni_test_AlignChildClass_bbbb", MemorySegment.class /* self */);

    public int bbbb() {
        int RESULT;
        try {
            RESULT = (int) bbbbMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle ccccMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), int.class, "JavaCritical_io_vproxy_pni_test_AlignChildClass_cccc", MemorySegment.class /* self */);

    public int cccc() {
        int RESULT;
        try {
            RESULT = (int) ccccMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle sizeMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), long.class, "JavaCritical_io_vproxy_pni_test_AlignChildClass_size", MemorySegment.class /* self */);

    public long size() {
        long RESULT;
        try {
            RESULT = (long) sizeMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    @Override
    public void toString(StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
        if (!VISITED.add(new NativeObjectTuple(this))) {
            SB.append("<...>@").append(Long.toString(MEMORY.address(), 16));
            return;
        }
        SB.append("AlignChildClass{\n");
        SB.append(" ".repeat(INDENT + 4)).append("SUPER => ");
        {
            INDENT += 4;
            SB.append("AlignBaseClass{\n");
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
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("c => ");
            SB.append(getC());
        }
        SB.append("\n");
        SB.append(" ".repeat(INDENT)).append("}@").append(Long.toString(MEMORY.address(), 16));
    }

    public static class Array extends RefArray<AlignChildClass> {
        public Array(MemorySegment buf) {
            super(buf, AlignChildClass.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            super(allocator, AlignChildClass.LAYOUT, len);
        }

        public Array(PNIBuf buf) {
            super(buf, AlignChildClass.LAYOUT);
        }

        @Override
        protected void elementToString(io.vproxy.pni.test.AlignChildClass ELEM, StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
            ELEM.toString(SB, INDENT, VISITED, CORRUPTED_MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "AlignChildClass.Array";
        }

        @Override
        protected AlignChildClass construct(MemorySegment seg) {
            return new AlignChildClass(seg);
        }

        @Override
        protected MemorySegment getSegment(AlignChildClass value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<AlignChildClass> {
        private Func(io.vproxy.pni.CallSite<AlignChildClass> func) {
            super(func);
        }

        private Func(io.vproxy.pni.CallSite<AlignChildClass> func, Options opts) {
            super(func, opts);
        }

        private Func(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static Func of(io.vproxy.pni.CallSite<AlignChildClass> func) {
            return new Func(func);
        }

        public static Func of(io.vproxy.pni.CallSite<AlignChildClass> func, Options opts) {
            return new Func(func, opts);
        }

        public static Func of(MemorySegment MEMORY) {
            return new Func(MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "AlignChildClass.Func";
        }

        @Override
        protected AlignChildClass construct(MemorySegment seg) {
            return new AlignChildClass(seg);
        }
    }
}
// metadata.generator-version: pni test
// sha256:7f9c3b44ec9669cac8512d1b30fafa6a2000ace4da8272ced85738ba0472d862
