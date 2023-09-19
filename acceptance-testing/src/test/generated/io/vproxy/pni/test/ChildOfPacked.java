package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class ChildOfPacked extends io.vproxy.pni.test.PackedBaseClass {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        io.vproxy.pni.test.PackedBaseClass.LAYOUT,
        MemoryLayout.sequenceLayout(1L, ValueLayout.JAVA_BYTE) /* padding */,
        ValueLayout.JAVA_INT_UNALIGNED.withName("x"),
        io.vproxy.pni.test.ObjectStruct.LAYOUT.withName("o")
    );
    public final MemorySegment MEMORY;

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
        protected ChildOfPacked construct(MemorySegment seg) {
            return new ChildOfPacked(seg);
        }
    }
}
// metadata.generator-version: pni test
// sha256:62a5161bf181d4bf6e0108718ac85bbacbc65ed01680cd42da6b62b7dc0eaa2b
