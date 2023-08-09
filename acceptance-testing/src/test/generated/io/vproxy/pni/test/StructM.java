package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class StructM {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        io.vproxy.pni.test.StructN.LAYOUT.withName("n")
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
    }

    public StructM(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT.byteSize()));
    }

    private final MethodHandle nnn = PanamaUtils.lookupPNIFunction(true, "Java_io_vproxy_pni_test_StructM_nnn", MemorySegment.class /* self */, io.vproxy.pni.test.StructN.LAYOUT.getClass() /* n */);

    public void nnn(PNIEnv ENV, io.vproxy.pni.test.StructN n) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.nnn.invokeExact(ENV.MEMORY, MEMORY, (MemorySegment) (n == null ? MemorySegment.NULL : n.MEMORY));
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

        public Array(Allocator allocator, long len) {
            this(allocator.allocate(StructM.LAYOUT.byteSize() * len));
        }

        public Array(PNIBuf buf) {
            this(buf.get());
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
// metadata.generator-version: pni test
// sha256:8ac30306da613c04bc0cd734b4fccdaef0c17d9f8aa149ea2de991430afc4ce8
