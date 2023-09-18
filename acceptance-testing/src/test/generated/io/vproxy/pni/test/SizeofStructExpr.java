package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class SizeofStructExpr {
    private static final MethodHandle __getLayoutByteSizeMH = PanamaUtils.lookupPNICriticalFunction(true, long.class, "JavaCritical_io_vproxy_pni_test_SizeofStructExpr___getLayoutByteSize");

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
        ValueLayout.JAVA_BYTE.withName("b"),
        MemoryLayout.sequenceLayout(1L, ValueLayout.JAVA_BYTE) /* padding */,
        ValueLayout.JAVA_SHORT_UNALIGNED.withName("s")
    );
    public final MemorySegment MEMORY;

    private static final VarHandle bVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("b")
    );

    public byte getB() {
        return (byte) bVH.get(MEMORY);
    }

    public void setB(byte b) {
        bVH.set(MEMORY, b);
    }

    private static final VarHandle sVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("s")
    );

    public short getS() {
        return (short) sVH.get(MEMORY);
    }

    public void setS(short s) {
        sVH.set(MEMORY, s);
    }

    public SizeofStructExpr(MemorySegment MEMORY) {
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
        OFFSET += ValueLayout.JAVA_BYTE.byteSize();
        OFFSET += 1; /* padding */
        OFFSET += ValueLayout.JAVA_SHORT_UNALIGNED.byteSize();
    }

    public SizeofStructExpr(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT.byteSize()));
    }

    public static class Array extends RefArray<SizeofStructExpr> {
        public Array(MemorySegment buf) {
            super(buf, SizeofStructExpr.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            this(allocator.allocate(SizeofStructExpr.LAYOUT.byteSize() * len));
        }

        public Array(PNIBuf buf) {
            this(buf.get());
        }

        @Override
        protected SizeofStructExpr construct(MemorySegment seg) {
            return new SizeofStructExpr(seg);
        }

        @Override
        protected MemorySegment getSegment(SizeofStructExpr value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<SizeofStructExpr> {
        private Func(io.vproxy.pni.CallSite<SizeofStructExpr> func) {
            super(func);
        }

        private Func(io.vproxy.pni.CallSite<SizeofStructExpr> func, Options opts) {
            super(func, opts);
        }

        private Func(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static Func of(io.vproxy.pni.CallSite<SizeofStructExpr> func) {
            return new Func(func);
        }

        public static Func of(io.vproxy.pni.CallSite<SizeofStructExpr> func, Options opts) {
            return new Func(func, opts);
        }

        public static Func of(MemorySegment MEMORY) {
            return new Func(MEMORY);
        }

        @Override
        protected SizeofStructExpr construct(MemorySegment seg) {
            return new SizeofStructExpr(seg);
        }
    }
}
// metadata.generator-version: pni test
// sha256:6083f2f86017c51e9fed5d1799229dd49644450580d5b130bfea5b4f551cb77a
