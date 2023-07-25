package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class StructM {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        io.vproxy.pni.test.StructN.LAYOUT.withName("n"),
        MemoryLayout.sequenceLayout(6L, ValueLayout.JAVA_BYTE) /* padding */
    );
    public final MemorySegment MEMORY;

    private final io.vproxy.pni.test.StructN n;

    public io.vproxy.pni.test.StructN getN() {
        return this.n;
    }

    public StructM(MemorySegment MEMORY) {
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
        this.n = new io.vproxy.pni.test.StructN(MEMORY.asSlice(OFFSET, io.vproxy.pni.test.StructN.LAYOUT.byteSize()));
        OFFSET += io.vproxy.pni.test.StructN.LAYOUT.byteSize();
        OFFSET += 6; /* padding */
    }

    public StructM(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT.byteSize()));
    }

    private final MethodHandle nnn = PanamaUtils.lookupPNIFunction(true, "Java_io_vproxy_pni_test_StructM_nnn", MemorySegment.class /* self */, io.vproxy.pni.test.StructN.LAYOUT.getClass() /* n */);

    public void nnn(PNIEnv ENV, io.vproxy.pni.test.StructN n) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.nnn.invokeExact(ENV.MEMORY, MEMORY, n.MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
    }

    public static class Array extends RefArray<StructM> {
        public Array(MemorySegment buf) {
            super(buf, StructM.LAYOUT);
        }

        @Override
        protected StructM construct(MemorySegment seg) {
            return new StructM(seg);
        }

        @Override
        protected MemorySegment getSegment(StructM value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<StructM> {
        private Func(io.vproxy.pni.CallSite<StructM> func) {
            super(func);
        }

        public static Func of(io.vproxy.pni.CallSite<StructM> func) {
            return new Func(func);
        }

        @Override
        protected StructM construct(MemorySegment seg) {
            return new StructM(seg);
        }

        @Override
        protected MemorySegment getSegment(StructM value) {
            return value.MEMORY;
        }
    }
}
// sha256:a07a52c2345f4bebedcd79a3df76c16daf4be29f7d4068c91400c9717ab1bb36
