package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class ChildOfPacked extends io.vproxy.pni.test.PackedBaseClass implements NativeObject {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        io.vproxy.pni.test.PackedBaseClass.LAYOUT,
        MemoryLayout.sequenceLayout(1L, ValueLayout.JAVA_BYTE) /* padding */,
        ValueLayout.JAVA_INT_UNALIGNED.withName("x"),
        io.vproxy.pni.test.ObjectStruct.LAYOUT.withName("o")
    );
    public final MemorySegment MEMORY;

    @Override
    public MemorySegment MEMORY() {
        return MEMORY;
    }

    private static final VarHandle xVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("x")
    );

    public int getX() {
        return (int) xVH.get(MEMORY);
    }

    public void setX(int x) {
        xVH.set(MEMORY, x);
    }

    private final io.vproxy.pni.test.ObjectStruct o;

    public io.vproxy.pni.test.ObjectStruct getO() {
        return this.o;
    }

    public ChildOfPacked(MemorySegment MEMORY) {
        super(MEMORY);
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
        OFFSET += io.vproxy.pni.test.PackedBaseClass.LAYOUT.byteSize();
        OFFSET += 1; // head padding
        OFFSET += ValueLayout.JAVA_INT_UNALIGNED.byteSize();
        this.o = new io.vproxy.pni.test.ObjectStruct(MEMORY.asSlice(OFFSET, io.vproxy.pni.test.ObjectStruct.LAYOUT.byteSize()));
        OFFSET += io.vproxy.pni.test.ObjectStruct.LAYOUT.byteSize();
    }

    public ChildOfPacked(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT.byteSize()));
    }

    private static final MethodHandle xxxMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_ChildOfPacked_xxx", MemorySegment.class /* self */, int.class /* x */);

    public void xxx(PNIEnv ENV, int x) {
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

    private static final MethodHandle oooMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_ChildOfPacked_ooo", MemorySegment.class /* self */, io.vproxy.pni.test.ObjectStruct.LAYOUT.getClass() /* o */);

    public void ooo(PNIEnv ENV, io.vproxy.pni.test.ObjectStruct o) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) oooMH.invokeExact(ENV.MEMORY, MEMORY, (MemorySegment) (o == null ? MemorySegment.NULL : o.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        toString(sb, 0, new java.util.HashSet<>(), false);
        return sb.toString();
    }

    @Override
    public void toString(StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
        if (!VISITED.add(new NativeObjectTuple(this))) {
            SB.append("<...>@").append(Long.toString(MEMORY.address(), 16));
            return;
        }
        SB.append("ChildOfPacked{\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("x => ");
            SB.append(getX());
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("o => ");
            PanamaUtils.nativeObjectToString(getO(), SB, INDENT + 4, VISITED, CORRUPTED_MEMORY);
        }
        SB.append("\n");
        SB.append(" ".repeat(INDENT)).append("}@").append(Long.toString(MEMORY.address(), 16));
    }

    public static class Array extends RefArray<ChildOfPacked> {
        public Array(MemorySegment buf) {
            super(buf, ChildOfPacked.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            this(allocator.allocate(ChildOfPacked.LAYOUT.byteSize() * len));
        }

        public Array(PNIBuf buf) {
            this(buf.get());
        }

        @Override
        protected void elementToString(io.vproxy.pni.test.ChildOfPacked ELEM, StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
            ELEM.toString(SB, INDENT, VISITED, CORRUPTED_MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "ChildOfPacked.Array";
        }

        @Override
        protected ChildOfPacked construct(MemorySegment seg) {
            return new ChildOfPacked(seg);
        }

        @Override
        protected MemorySegment getSegment(ChildOfPacked value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<ChildOfPacked> {
        private Func(io.vproxy.pni.CallSite<ChildOfPacked> func) {
            super(func);
        }

        private Func(io.vproxy.pni.CallSite<ChildOfPacked> func, Options opts) {
            super(func, opts);
        }

        private Func(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static Func of(io.vproxy.pni.CallSite<ChildOfPacked> func) {
            return new Func(func);
        }

        public static Func of(io.vproxy.pni.CallSite<ChildOfPacked> func, Options opts) {
            return new Func(func, opts);
        }

        public static Func of(MemorySegment MEMORY) {
            return new Func(MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "ChildOfPacked.Func";
        }

        @Override
        protected ChildOfPacked construct(MemorySegment seg) {
            return new ChildOfPacked(seg);
        }
    }
}
// metadata.generator-version: pni test
// sha256:36c114a5f2e00baf09a5f99791b9b5b8f9efa7a0d0f06ce1859fd6e0ea1a89b1
