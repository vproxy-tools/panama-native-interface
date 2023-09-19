package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class PackedBaseClass {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        ValueLayout.JAVA_BYTE.withName("a"),
        ValueLayout.JAVA_SHORT_UNALIGNED.withName("b")
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

    private static final VarHandle bVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("b")
    );

    public short getB() {
        return (short) bVH.get(MEMORY);
    }

    public void setB(short b) {
        bVH.set(MEMORY, b);
    }

    public PackedBaseClass(MemorySegment MEMORY) {
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
        OFFSET += ValueLayout.JAVA_BYTE.byteSize();
        OFFSET += ValueLayout.JAVA_SHORT_UNALIGNED.byteSize();
    }

    public PackedBaseClass(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT.byteSize()));
    }

    private static final MethodHandle aaaMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PackedBaseClass_aaa", MemorySegment.class /* self */, byte.class /* a */);

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

    private static final MethodHandle bbbMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PackedBaseClass_bbb", MemorySegment.class /* self */, short.class /* b */);

    public void bbb(PNIEnv ENV, short b) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) bbbMH.invokeExact(ENV.MEMORY, MEMORY, b);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
    }

    public static class Array extends RefArray<PackedBaseClass> {
        public Array(MemorySegment buf) {
            super(buf, PackedBaseClass.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            this(allocator.allocate(PackedBaseClass.LAYOUT.byteSize() * len));
        }

        public Array(PNIBuf buf) {
            this(buf.get());
        }

        @Override
        protected PackedBaseClass construct(MemorySegment seg) {
            return new PackedBaseClass(seg);
        }

        @Override
        protected MemorySegment getSegment(PackedBaseClass value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<PackedBaseClass> {
        private Func(io.vproxy.pni.CallSite<PackedBaseClass> func) {
            super(func);
        }

        private Func(io.vproxy.pni.CallSite<PackedBaseClass> func, Options opts) {
            super(func, opts);
        }

        private Func(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static Func of(io.vproxy.pni.CallSite<PackedBaseClass> func) {
            return new Func(func);
        }

        public static Func of(io.vproxy.pni.CallSite<PackedBaseClass> func, Options opts) {
            return new Func(func, opts);
        }

        public static Func of(MemorySegment MEMORY) {
            return new Func(MEMORY);
        }

        @Override
        protected PackedBaseClass construct(MemorySegment seg) {
            return new PackedBaseClass(seg);
        }
    }
}
// metadata.generator-version: pni test
// sha256:2e4501ee7bf6612c2746f942db4088faef96d3ffa00bec12bd0873960cba68fd
