package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class GrandChildClass extends io.vproxy.pni.test.ChildClass {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        io.vproxy.pni.test.ChildClass.LAYOUT,
        MemoryLayout.sequenceLayout(4L, ValueLayout.JAVA_BYTE) /* padding */,
        ValueLayout.JAVA_LONG_UNALIGNED.withName("y")
    );
    public final MemorySegment MEMORY;

    private static final VarHandle yVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("y")
    );

    public long getY() {
        return (long) yVH.get(MEMORY);
    }

    public void setY(long y) {
        yVH.set(MEMORY, y);
    }

    public GrandChildClass(MemorySegment MEMORY) {
        super(MEMORY);
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
        OFFSET += io.vproxy.pni.test.ChildClass.LAYOUT.byteSize();
        OFFSET += 4; // head padding
        OFFSET += ValueLayout.JAVA_LONG_UNALIGNED.byteSize();
    }

    public GrandChildClass(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT.byteSize()));
    }

    private static final MethodHandle yyyMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_GrandChildClass_yyy", MemorySegment.class /* self */, long.class /* y */);

    public void yyy(PNIEnv ENV, long y) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) yyyMH.invokeExact(ENV.MEMORY, MEMORY, y);
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

    public void toString(StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
        if (!VISITED.add(new NativeObjectTuple(getClass(), MEMORY.address()))) {
            SB.append("<...>@").append(Long.toString(MEMORY.address(), 16));
            return;
        }
        SB.append("GrandChildClass{\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("y => ");
            SB.append(getY());
        }
        SB.append("\n");
        SB.append(" ".repeat(INDENT)).append("}@").append(Long.toString(MEMORY.address(), 16));
    }

    public static class Array extends RefArray<GrandChildClass> {
        public Array(MemorySegment buf) {
            super(buf, GrandChildClass.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            this(allocator.allocate(GrandChildClass.LAYOUT.byteSize() * len));
        }

        public Array(PNIBuf buf) {
            this(buf.get());
        }

        @Override
        protected void elementToString(io.vproxy.pni.test.GrandChildClass ELEM, StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
            ELEM.toString(SB, INDENT, VISITED, CORRUPTED_MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "GrandChildClass.Array";
        }

        @Override
        protected GrandChildClass construct(MemorySegment seg) {
            return new GrandChildClass(seg);
        }

        @Override
        protected MemorySegment getSegment(GrandChildClass value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<GrandChildClass> {
        private Func(io.vproxy.pni.CallSite<GrandChildClass> func) {
            super(func);
        }

        private Func(io.vproxy.pni.CallSite<GrandChildClass> func, Options opts) {
            super(func, opts);
        }

        private Func(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static Func of(io.vproxy.pni.CallSite<GrandChildClass> func) {
            return new Func(func);
        }

        public static Func of(io.vproxy.pni.CallSite<GrandChildClass> func, Options opts) {
            return new Func(func, opts);
        }

        public static Func of(MemorySegment MEMORY) {
            return new Func(MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "GrandChildClass.Func";
        }

        @Override
        protected GrandChildClass construct(MemorySegment seg) {
            return new GrandChildClass(seg);
        }
    }
}
// metadata.generator-version: pni test
// sha256:4259a800d2a472e888f5cb24eaa640ae1299c4d143ec575771dcb616d5eb9147
