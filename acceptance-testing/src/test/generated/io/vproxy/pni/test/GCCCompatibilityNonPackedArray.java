package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class GCCCompatibilityNonPackedArray {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        ValueLayout.JAVA_BYTE.withName("b1"),
        MemoryLayout.sequenceLayout(7L, ValueLayout.JAVA_BYTE) /* padding */,
        MemoryLayout.sequenceLayout(2L, io.vproxy.pni.test.GCCCompatibilityNormal.LAYOUT).withName("array"),
        ValueLayout.JAVA_INT_UNALIGNED.withName("n2"),
        MemoryLayout.sequenceLayout(4L, ValueLayout.JAVA_BYTE) /* padding */
    );
    public final MemorySegment MEMORY;

    private static final VarHandle b1VH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("b1")
    );

    public byte getB1() {
        return (byte) b1VH.get(MEMORY);
    }

    public void setB1(byte b1) {
        b1VH.set(MEMORY, b1);
    }

    private final io.vproxy.pni.test.GCCCompatibilityNormal.Array array;

    public io.vproxy.pni.test.GCCCompatibilityNormal.Array getArray() {
        return this.array;
    }

    private static final VarHandle n2VH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("n2")
    );

    public int getN2() {
        return (int) n2VH.get(MEMORY);
    }

    public void setN2(int n2) {
        n2VH.set(MEMORY, n2);
    }

    public GCCCompatibilityNonPackedArray(MemorySegment MEMORY) {
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
        OFFSET += ValueLayout.JAVA_BYTE.byteSize();
        OFFSET += 7; /* padding */
        this.array = new io.vproxy.pni.test.GCCCompatibilityNormal.Array(MEMORY.asSlice(OFFSET, 2 * io.vproxy.pni.test.GCCCompatibilityNormal.LAYOUT.byteSize()));
        OFFSET += 2 * io.vproxy.pni.test.GCCCompatibilityNormal.LAYOUT.byteSize();
        OFFSET += ValueLayout.JAVA_INT_UNALIGNED.byteSize();
        OFFSET += 4; /* padding */
    }

    public GCCCompatibilityNonPackedArray(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT.byteSize()));
    }

    private final MethodHandle init = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_GCCCompatibilityNonPackedArray_init", MemorySegment.class /* self */);

    public void init(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.init.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
    }

    private final MethodHandle size = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_GCCCompatibilityNonPackedArray_size", MemorySegment.class /* self */);

    public long size(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.size.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnLong();
    }

    public static class Array extends RefArray<GCCCompatibilityNonPackedArray> {
        public Array(MemorySegment buf) {
            super(buf, GCCCompatibilityNonPackedArray.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            this(allocator.allocate(GCCCompatibilityNonPackedArray.LAYOUT.byteSize() * len));
        }

        public Array(PNIBuf buf) {
            this(buf.get());
        }

        @Override
        protected GCCCompatibilityNonPackedArray construct(MemorySegment seg) {
            return new GCCCompatibilityNonPackedArray(seg);
        }

        @Override
        protected MemorySegment getSegment(GCCCompatibilityNonPackedArray value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<GCCCompatibilityNonPackedArray> {
        private Func(io.vproxy.pni.CallSite<GCCCompatibilityNonPackedArray> func) {
            super(func);
        }

        public static Func of(io.vproxy.pni.CallSite<GCCCompatibilityNonPackedArray> func) {
            return new Func(func);
        }

        @Override
        protected GCCCompatibilityNonPackedArray construct(MemorySegment seg) {
            return new GCCCompatibilityNonPackedArray(seg);
        }

        @Override
        protected MemorySegment getSegment(GCCCompatibilityNonPackedArray value) {
            return value.MEMORY;
        }
    }
}
// metadata.generator-version: pni test
// sha256:08d229c09518db1e73fd609cd0a1c87fead157dbffe42ce93025886b24d48740
