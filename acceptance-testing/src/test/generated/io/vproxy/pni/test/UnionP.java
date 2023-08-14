package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class UnionP {
    public static final MemoryLayout LAYOUT = MemoryLayout.unionLayout(
        ValueLayout.JAVA_INT_UNALIGNED.withName("i"),
        ValueLayout.JAVA_LONG_UNALIGNED.withName("l")
    );
    public final MemorySegment MEMORY;

    private static final VarHandle iVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("i")
    );

    public int getI() {
        return (int) iVH.get(MEMORY);
    }

    public void setI(int i) {
        iVH.set(MEMORY, i);
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

    public UnionP(MemorySegment MEMORY) {
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
        OFFSET += ValueLayout.JAVA_INT_UNALIGNED.byteSize();
        OFFSET = 0;
        OFFSET += ValueLayout.JAVA_LONG_UNALIGNED.byteSize();
        OFFSET = 0;
    }

    public UnionP(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT.byteSize()));
    }

    private static final MethodHandle retrieveIMH = PanamaUtils.lookupPNIFunction(true, "UnionP_retrieve_i", MemorySegment.class /* self */);

    public int retrieveI(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.retrieveIMH.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnInt();
    }

    private static final MethodHandle retrieveLMH = PanamaUtils.lookupPNIFunction(true, "UnionP_retrieve_l", MemorySegment.class /* self */);

    public long retrieveL(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.retrieveLMH.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnLong();
    }

    public static class Array extends RefArray<UnionP> {
        public Array(MemorySegment buf) {
            super(buf, UnionP.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            this(allocator.allocate(UnionP.LAYOUT.byteSize() * len));
        }

        public Array(PNIBuf buf) {
            this(buf.get());
        }

        @Override
        protected UnionP construct(MemorySegment seg) {
            return new UnionP(seg);
        }

        @Override
        protected MemorySegment getSegment(UnionP value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<UnionP> {
        private Func(io.vproxy.pni.CallSite<UnionP> func) {
            super(func);
        }

        public static Func of(io.vproxy.pni.CallSite<UnionP> func) {
            return new Func(func);
        }

        @Override
        protected UnionP construct(MemorySegment seg) {
            return new UnionP(seg);
        }

        @Override
        protected MemorySegment getSegment(UnionP value) {
            return value.MEMORY;
        }
    }
}
// metadata.generator-version: pni test
// sha256:063c4150369c39c859e26233b33776027c365dfd0a1db8ea7cea0dc4919182fb
