package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class GCCompatibilityStructAlign {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        ValueLayout.JAVA_INT_UNALIGNED.withName("n"),
        MemoryLayout.sequenceLayout(4L, ValueLayout.JAVA_BYTE) /* padding */,
        ValueLayout.JAVA_LONG_UNALIGNED.withName("l"),
        MemoryLayout.sequenceLayout(16L, ValueLayout.JAVA_BYTE) /* padding */
    );
    public final MemorySegment MEMORY;

    private static final VarHandle nVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("n")
    );

    public int getN() {
        return (int) nVH.get(MEMORY);
    }

    public void setN(int n) {
        nVH.set(MEMORY, n);
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

    public GCCompatibilityStructAlign(MemorySegment MEMORY) {
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
        OFFSET += ValueLayout.JAVA_INT_UNALIGNED.byteSize();
        OFFSET += 4; /* padding */
        OFFSET += ValueLayout.JAVA_LONG_UNALIGNED.byteSize();
        OFFSET += 16; /* padding */
    }

    public GCCompatibilityStructAlign(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT.byteSize()));
    }

    private final MethodHandle init = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_GCCompatibilityStructAlign_init", MemorySegment.class /* self */);

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

    private final MethodHandle size = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_GCCompatibilityStructAlign_size", MemorySegment.class /* self */);

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

    public static class Array extends RefArray<GCCompatibilityStructAlign> {
        public Array(MemorySegment buf) {
            super(buf, GCCompatibilityStructAlign.LAYOUT);
        }

        @Override
        protected GCCompatibilityStructAlign construct(MemorySegment seg) {
            return new GCCompatibilityStructAlign(seg);
        }

        @Override
        protected MemorySegment getSegment(GCCompatibilityStructAlign value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<GCCompatibilityStructAlign> {
        private Func(io.vproxy.pni.CallSite<GCCompatibilityStructAlign> func) {
            super(func);
        }

        public static Func of(io.vproxy.pni.CallSite<GCCompatibilityStructAlign> func) {
            return new Func(func);
        }

        @Override
        protected GCCompatibilityStructAlign construct(MemorySegment seg) {
            return new GCCompatibilityStructAlign(seg);
        }

        @Override
        protected MemorySegment getSegment(GCCompatibilityStructAlign value) {
            return value.MEMORY;
        }
    }
}
// sha256:f8bd6feff4257fa37fafc6a3cd295cba38ec2cbe6e92a9d3429d575169dc937a