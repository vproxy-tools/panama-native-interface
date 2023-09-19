package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class BaseClass {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        ValueLayout.JAVA_BYTE.withName("a")
    );
    public final MemorySegment MEMORY;

    private static final VarHandle aVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("a")
    );

    public byte getA() {
        return (byte) aVH.get(MEMORY);
    }

    public void setA(byte a) {
        aVH.set(MEMORY, a);
    }

    public BaseClass(MemorySegment MEMORY) {
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
        OFFSET += ValueLayout.JAVA_BYTE.byteSize();
    }

    public BaseClass(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT.byteSize()));
    }

    private static final MethodHandle aaaMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_BaseClass_aaa", MemorySegment.class /* self */, byte.class /* a */);

    public void aaa(PNIEnv ENV, byte a) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) aaaMH.invokeExact(ENV.MEMORY, MEMORY, a);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
    }

    public static class Array extends RefArray<BaseClass> {
        public Array(MemorySegment buf) {
            super(buf, BaseClass.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            this(allocator.allocate(BaseClass.LAYOUT.byteSize() * len));
        }

        public Array(PNIBuf buf) {
            this(buf.get());
        }

        @Override
        protected BaseClass construct(MemorySegment seg) {
            return new BaseClass(seg);
        }

        @Override
        protected MemorySegment getSegment(BaseClass value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<BaseClass> {
        private Func(io.vproxy.pni.CallSite<BaseClass> func) {
            super(func);
        }

        private Func(io.vproxy.pni.CallSite<BaseClass> func, Options opts) {
            super(func, opts);
        }

        private Func(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static Func of(io.vproxy.pni.CallSite<BaseClass> func) {
            return new Func(func);
        }

        public static Func of(io.vproxy.pni.CallSite<BaseClass> func, Options opts) {
            return new Func(func, opts);
        }

        public static Func of(MemorySegment MEMORY) {
            return new Func(MEMORY);
        }

        @Override
        protected BaseClass construct(MemorySegment seg) {
            return new BaseClass(seg);
        }
    }
}
// metadata.generator-version: pni test
// sha256:2c2786c9b7b5175f54cac02ba3995b5cf8ad7b05f7aad54e15df04399c27af67
