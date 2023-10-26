package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class ChildClass extends io.vproxy.pni.test.BaseClass implements NativeObject {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        io.vproxy.pni.test.BaseClass.LAYOUT,
        MemoryLayout.sequenceLayout(1L, ValueLayout.JAVA_BYTE) /* padding */,
        ValueLayout.JAVA_SHORT_UNALIGNED.withName("x")
    );
    public final MemorySegment MEMORY;

    @Override
    public MemorySegment MEMORY() {
        return MEMORY;
    }

    private static final VarHandle xVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("x")
    );

    public short getX() {
        return (short) xVH.get(MEMORY);
    }

    public void setX(short x) {
        xVH.set(MEMORY, x);
    }

    public ChildClass(MemorySegment MEMORY) {
        super(MEMORY);
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
        OFFSET += io.vproxy.pni.test.BaseClass.LAYOUT.byteSize();
        OFFSET += 1; // head padding
        OFFSET += ValueLayout.JAVA_SHORT_UNALIGNED.byteSize();
    }

    public ChildClass(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT));
    }

    private static final MethodHandle xxxMH = PanamaUtils.lookupPNIFunction(new PNILinkOptions(), "Java_io_vproxy_pni_test_ChildClass_xxx", MemorySegment.class /* self */, short.class /* x */);

    public void xxx(PNIEnv ENV, short x) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) xxxMH.invokeExact(ENV.MEMORY, MEMORY, x);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
    }

    @Override
    public void toString(StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
        if (!VISITED.add(new NativeObjectTuple(this))) {
            SB.append("<...>@").append(Long.toString(MEMORY.address(), 16));
            return;
        }
        SB.append("ChildClass{\n");
        SB.append(" ".repeat(INDENT + 4)).append("SUPER => ");
        {
            INDENT += 4;
            SB.append("BaseClass{\n");
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
            SB.append(" ".repeat(INDENT + 4)).append("x => ");
            SB.append(getX());
        }
        SB.append("\n");
        SB.append(" ".repeat(INDENT)).append("}@").append(Long.toString(MEMORY.address(), 16));
    }

    public static class Array extends RefArray<ChildClass> {
        public Array(MemorySegment buf) {
            super(buf, ChildClass.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            super(allocator, ChildClass.LAYOUT, len);
        }

        public Array(PNIBuf buf) {
            super(buf, ChildClass.LAYOUT);
        }

        @Override
        protected void elementToString(io.vproxy.pni.test.ChildClass ELEM, StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
            ELEM.toString(SB, INDENT, VISITED, CORRUPTED_MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "ChildClass.Array";
        }

        @Override
        protected ChildClass construct(MemorySegment seg) {
            return new ChildClass(seg);
        }

        @Override
        protected MemorySegment getSegment(ChildClass value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<ChildClass> {
        private Func(io.vproxy.pni.CallSite<ChildClass> func) {
            super(func);
        }

        private Func(io.vproxy.pni.CallSite<ChildClass> func, Options opts) {
            super(func, opts);
        }

        private Func(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static Func of(io.vproxy.pni.CallSite<ChildClass> func) {
            return new Func(func);
        }

        public static Func of(io.vproxy.pni.CallSite<ChildClass> func, Options opts) {
            return new Func(func, opts);
        }

        public static Func of(MemorySegment MEMORY) {
            return new Func(MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "ChildClass.Func";
        }

        @Override
        protected ChildClass construct(MemorySegment seg) {
            return new ChildClass(seg);
        }
    }
}
// metadata.generator-version: pni test
// sha256:ec97b682c2ba9f64d7ec9d38445915a9401f7ee927a5c92709a34d49dcfc4249
