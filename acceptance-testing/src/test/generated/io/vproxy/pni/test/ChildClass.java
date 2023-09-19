package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class ChildClass extends io.vproxy.pni.test.BaseClass {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        io.vproxy.pni.test.BaseClass.LAYOUT,
        MemoryLayout.sequenceLayout(1L, ValueLayout.JAVA_BYTE) /* padding */,
        ValueLayout.JAVA_SHORT_UNALIGNED.withName("x")
    );
    public final MemorySegment MEMORY;

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
        this(ALLOCATOR.allocate(LAYOUT.byteSize()));
    }

    private static final MethodHandle xxxMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_ChildClass_xxx", MemorySegment.class /* self */, short.class /* x */);

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

    public static class Array extends RefArray<ChildClass> {
        public Array(MemorySegment buf) {
            super(buf, ChildClass.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            this(allocator.allocate(ChildClass.LAYOUT.byteSize() * len));
        }

        public Array(PNIBuf buf) {
            this(buf.get());
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
        protected ChildClass construct(MemorySegment seg) {
            return new ChildClass(seg);
        }
    }
}
// metadata.generator-version: pni test
// sha256:aac7e923b188e00c834bab8c1a5620cd266eeb979be93510e2910b339467c49d
