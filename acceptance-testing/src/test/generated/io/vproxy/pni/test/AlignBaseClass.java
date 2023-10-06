package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class AlignBaseClass extends AbstractNativeObject implements NativeObject {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        ValueLayout.JAVA_SHORT.withName("a")
    ).withByteAlignment(2);
    public final MemorySegment MEMORY;

    @Override
    public MemorySegment MEMORY() {
        return MEMORY;
    }

    private static final VarHandle aVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("a")
    );

    public short getA() {
        return (short) aVH.get(MEMORY);
    }

    public void setA(short a) {
        aVH.set(MEMORY, a);
    }

    public AlignBaseClass(MemorySegment MEMORY) {
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
        OFFSET += ValueLayout.JAVA_SHORT_UNALIGNED.byteSize();
    }

    public AlignBaseClass(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT));
    }

    private static final MethodHandle aaaaMH = PanamaUtils.lookupPNICriticalFunction(false, short.class, "JavaCritical_io_vproxy_pni_test_AlignBaseClass_aaaa", MemorySegment.class /* self */);

    public short aaaa() {
        short RESULT;
        try {
            RESULT = (short) aaaaMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle size0MH = PanamaUtils.lookupPNICriticalFunction(false, long.class, "JavaCritical_io_vproxy_pni_test_AlignBaseClass_size0", MemorySegment.class /* self */);

    public long size0() {
        long RESULT;
        try {
            RESULT = (long) size0MH.invokeExact(MEMORY);
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
        SB.append("AlignBaseClass{\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("a => ");
            SB.append(getA());
        }
        SB.append("\n");
        SB.append(" ".repeat(INDENT)).append("}@").append(Long.toString(MEMORY.address(), 16));
    }

    public static class Array extends RefArray<AlignBaseClass> {
        public Array(MemorySegment buf) {
            super(buf, AlignBaseClass.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            super(allocator, AlignBaseClass.LAYOUT, len);
        }

        public Array(PNIBuf buf) {
            super(buf, AlignBaseClass.LAYOUT);
        }

        @Override
        protected void elementToString(io.vproxy.pni.test.AlignBaseClass ELEM, StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
            ELEM.toString(SB, INDENT, VISITED, CORRUPTED_MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "AlignBaseClass.Array";
        }

        @Override
        protected AlignBaseClass construct(MemorySegment seg) {
            return new AlignBaseClass(seg);
        }

        @Override
        protected MemorySegment getSegment(AlignBaseClass value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<AlignBaseClass> {
        private Func(io.vproxy.pni.CallSite<AlignBaseClass> func) {
            super(func);
        }

        private Func(io.vproxy.pni.CallSite<AlignBaseClass> func, Options opts) {
            super(func, opts);
        }

        private Func(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static Func of(io.vproxy.pni.CallSite<AlignBaseClass> func) {
            return new Func(func);
        }

        public static Func of(io.vproxy.pni.CallSite<AlignBaseClass> func, Options opts) {
            return new Func(func, opts);
        }

        public static Func of(MemorySegment MEMORY) {
            return new Func(MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "AlignBaseClass.Func";
        }

        @Override
        protected AlignBaseClass construct(MemorySegment seg) {
            return new AlignBaseClass(seg);
        }
    }
}
// metadata.generator-version: pni test
// sha256:8db22e9b5439a44c0608ef4e71d1ec9f7e31ff0aa0e9d9a2529af4c79cfa387b
