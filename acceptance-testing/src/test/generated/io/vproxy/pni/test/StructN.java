package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class StructN {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        ValueLayout.JAVA_SHORT_UNALIGNED.withName("s"),
        MemoryLayout.sequenceLayout(6L, ValueLayout.JAVA_BYTE) /* padding */,
        ValueLayout.JAVA_LONG_UNALIGNED.withName("l")
    );
    public final MemorySegment MEMORY;

    private static final VarHandle sVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("s")
    );

    public short getS() {
        return (short) sVH.get(MEMORY);
    }

    public void setS(short s) {
        sVH.set(MEMORY, s);
    }

    private static final VarHandle lVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("l")
    );

    public long getL() {
        return (long) lVH.get(MEMORY);
    }

    public void setL(long l) {
        lVH.set(MEMORY, l);
    }

    public StructN(MemorySegment MEMORY) {
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
        OFFSET += ValueLayout.JAVA_SHORT_UNALIGNED.byteSize();
        OFFSET += 6; /* padding */
        OFFSET += ValueLayout.JAVA_LONG_UNALIGNED.byteSize();
    }

    public StructN(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT.byteSize()));
    }

    private final MethodHandle retrieveS = PanamaUtils.lookupPNIFunction(true, "Java_io_vproxy_pni_test_StructN_retrieveS", MemorySegment.class /* self */);

    public short retrieveS(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.retrieveS.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnShort();
    }

    private final MethodHandle retrieveL = PanamaUtils.lookupPNIFunction(true, "Java_io_vproxy_pni_test_StructN_retrieveL", MemorySegment.class /* self */);

    public long retrieveL(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.retrieveL.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnLong();
    }

    public static class Array extends RefArray<StructN> {
        public Array(MemorySegment buf) {
            super(buf, StructN.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            this(allocator.allocate(StructN.LAYOUT.byteSize() * len));
        }

        public Array(PNIBuf buf) {
            this(buf.get());
        }

        @Override
        protected StructN construct(MemorySegment seg) {
            return new StructN(seg);
        }

        @Override
        protected MemorySegment getSegment(StructN value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<StructN> {
        private Func(io.vproxy.pni.CallSite<StructN> func) {
            super(func);
        }

        public static Func of(io.vproxy.pni.CallSite<StructN> func) {
            return new Func(func);
        }

        @Override
        protected StructN construct(MemorySegment seg) {
            return new StructN(seg);
        }

        @Override
        protected MemorySegment getSegment(StructN value) {
            return value.MEMORY;
        }
    }
}
// sha256:084a6420d5081b75b3d83dabe07be7850742fe188a574e66ec9a0f30926e1fde
