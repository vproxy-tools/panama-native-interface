package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class SizeofStruct {
    private static final MethodHandle __getLayoutByteSizeMH = PanamaUtils.lookupPNICriticalFunction(true, long.class, "JavaCritical_io_vproxy_pni_test_SizeofStruct___getLayoutByteSize");

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
        ValueLayout.JAVA_INT_UNALIGNED.withName("a"),
        ValueLayout.JAVA_SHORT_UNALIGNED.withName("b"),
        MemoryLayout.sequenceLayout(2L, ValueLayout.JAVA_BYTE) /* padding */
    );
    public final MemorySegment MEMORY;

    private static final VarHandle aVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("a")
    );

    public int getA() {
        return (int) aVH.get(MEMORY);
    }

    public void setA(int a) {
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

    public SizeofStruct(MemorySegment MEMORY) {
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
        OFFSET += ValueLayout.JAVA_INT_UNALIGNED.byteSize();
        OFFSET += ValueLayout.JAVA_SHORT_UNALIGNED.byteSize();
        OFFSET += 2; /* padding */
    }

    public SizeofStruct(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT.byteSize()));
    }

    public static class Array extends RefArray<SizeofStruct> {
        public Array(MemorySegment buf) {
            super(buf, SizeofStruct.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            this(allocator.allocate(SizeofStruct.LAYOUT.byteSize() * len));
        }

        public Array(PNIBuf buf) {
            this(buf.get());
        }

        @Override
        protected SizeofStruct construct(MemorySegment seg) {
            return new SizeofStruct(seg);
        }

        @Override
        protected MemorySegment getSegment(SizeofStruct value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<SizeofStruct> {
        private Func(io.vproxy.pni.CallSite<SizeofStruct> func) {
            super(func);
        }

        private Func(io.vproxy.pni.CallSite<SizeofStruct> func, Options opts) {
            super(func, opts);
        }

        private Func(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static Func of(io.vproxy.pni.CallSite<SizeofStruct> func) {
            return new Func(func);
        }

        public static Func of(io.vproxy.pni.CallSite<SizeofStruct> func, Options opts) {
            return new Func(func, opts);
        }

        public static Func of(MemorySegment MEMORY) {
            return new Func(MEMORY);
        }

        @Override
        protected SizeofStruct construct(MemorySegment seg) {
            return new SizeofStruct(seg);
        }
    }
}
// metadata.generator-version: pni test
// sha256:6a7bff6ea714d9eb41878d3c882588a0d302939c3c970ec878b11e529e52f2d7
