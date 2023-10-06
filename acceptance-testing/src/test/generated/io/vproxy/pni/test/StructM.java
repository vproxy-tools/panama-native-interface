package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class StructM extends AbstractNativeObject implements NativeObject {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        io.vproxy.pni.test.StructN.LAYOUT.withName("n")
    );
    public final MemorySegment MEMORY;

    @Override
    public MemorySegment MEMORY() {
        return MEMORY;
    }

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
        this(ALLOCATOR.allocate(LAYOUT));
    }

    private static final MethodHandle nnnMH = PanamaUtils.lookupPNIFunction(true, "Java_io_vproxy_pni_test_StructM_nnn", MemorySegment.class /* self */, io.vproxy.pni.test.StructN.LAYOUT.getClass() /* n */);

    public void nnn(PNIEnv ENV, io.vproxy.pni.test.StructN n) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) nnnMH.invokeExact(ENV.MEMORY, MEMORY, (MemorySegment) (n == null ? MemorySegment.NULL : n.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
    }

    @Override
    public void toString(StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
        if (!VISITED.add(new NativeObjectTuple(this))) {
            SB.append("<...>@").append(Long.toString(MEMORY.address(), 16));
            return;
        }
        SB.append("StructM{\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("n => ");
            PanamaUtils.nativeObjectToString(getN(), SB, INDENT + 4, VISITED, CORRUPTED_MEMORY);
        }
        SB.append("\n");
        SB.append(" ".repeat(INDENT)).append("}@").append(Long.toString(MEMORY.address(), 16));
    }

    public static class Array extends RefArray<StructM> {
        public Array(MemorySegment buf) {
            super(buf, StructM.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            super(allocator, StructM.LAYOUT, len);
        }

        public Array(PNIBuf buf) {
            super(buf, StructM.LAYOUT);
        }

        @Override
        protected void elementToString(io.vproxy.pni.test.StructM ELEM, StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
            ELEM.toString(SB, INDENT, VISITED, CORRUPTED_MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "StructM.Array";
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

        private Func(io.vproxy.pni.CallSite<StructM> func, Options opts) {
            super(func, opts);
        }

        private Func(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static Func of(io.vproxy.pni.CallSite<StructM> func) {
            return new Func(func);
        }

        public static Func of(io.vproxy.pni.CallSite<StructM> func, Options opts) {
            return new Func(func, opts);
        }

        public static Func of(MemorySegment MEMORY) {
            return new Func(MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "StructM.Func";
        }

        @Override
        protected StructM construct(MemorySegment seg) {
            return new StructM(seg);
        }
    }
}
// metadata.generator-version: pni test
// sha256:8b08e28d13722d0069ed145a6507cad713e56a2f66ff7ce1c28c7901897beeb1
