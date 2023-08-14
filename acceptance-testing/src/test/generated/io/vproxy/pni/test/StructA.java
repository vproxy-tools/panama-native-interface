package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class StructA {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        io.vproxy.pni.test.StructB.LAYOUT.withName("b"),
        io.vproxy.pni.test.UnionC.LAYOUT.withName("c"),
        ValueLayout.ADDRESS_UNALIGNED.withName("cPointer"),
        io.vproxy.pni.test.StructD.LAYOUT.withName("d"),
        PNIBuf.LAYOUT.withName("bArray"),
        MemoryLayout.sequenceLayout(5L, io.vproxy.pni.test.StructB.LAYOUT).withName("bArray2")
    );
    public final MemorySegment MEMORY;

    private final io.vproxy.pni.test.StructB b;

    public io.vproxy.pni.test.StructB getB() {
        return this.b;
    }

    private final io.vproxy.pni.test.UnionC c;

    public io.vproxy.pni.test.UnionC getC() {
        return this.c;
    }

    private static final VarHandle cPointerVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("cPointer")
    );

    public io.vproxy.pni.test.UnionC getCPointer() {
        var SEG = (MemorySegment) cPointerVH.get(MEMORY);
        if (SEG.address() == 0) return null;
        return new io.vproxy.pni.test.UnionC(SEG);
    }

    public void setCPointer(io.vproxy.pni.test.UnionC cPointer) {
        if (cPointer == null) {
            cPointerVH.set(MEMORY, MemorySegment.NULL);
        } else {
            cPointerVH.set(MEMORY, cPointer.MEMORY);
        }
    }

    private final io.vproxy.pni.test.StructD d;

    public io.vproxy.pni.test.StructD getD() {
        return this.d;
    }

    private final PNIBuf bArray;

    public io.vproxy.pni.test.StructB.Array getBArray() {
        var SEG = this.bArray.get();
        if (SEG == null) return null;
        return new io.vproxy.pni.test.StructB.Array(SEG);
    }

    public void setBArray(io.vproxy.pni.test.StructB.Array bArray) {
        if (bArray == null) {
            this.bArray.setToNull();
        } else {
            this.bArray.set(bArray.MEMORY);
        }
    }

    private final io.vproxy.pni.test.StructB.Array bArray2;

    public io.vproxy.pni.test.StructB.Array getBArray2() {
        return this.bArray2;
    }

    public StructA(MemorySegment MEMORY) {
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
        this.b = new io.vproxy.pni.test.StructB(MEMORY.asSlice(OFFSET, io.vproxy.pni.test.StructB.LAYOUT.byteSize()));
        OFFSET += io.vproxy.pni.test.StructB.LAYOUT.byteSize();
        this.c = new io.vproxy.pni.test.UnionC(MEMORY.asSlice(OFFSET, io.vproxy.pni.test.UnionC.LAYOUT.byteSize()));
        OFFSET += io.vproxy.pni.test.UnionC.LAYOUT.byteSize();
        OFFSET += 8;
        this.d = new io.vproxy.pni.test.StructD(MEMORY.asSlice(OFFSET, io.vproxy.pni.test.StructD.LAYOUT.byteSize()));
        OFFSET += io.vproxy.pni.test.StructD.LAYOUT.byteSize();
        this.bArray = new PNIBuf(MEMORY.asSlice(OFFSET, PNIBuf.LAYOUT.byteSize()));
        OFFSET += PNIBuf.LAYOUT.byteSize();
        this.bArray2 = new io.vproxy.pni.test.StructB.Array(MEMORY.asSlice(OFFSET, 5 * io.vproxy.pni.test.StructB.LAYOUT.byteSize()));
        OFFSET += 5 * io.vproxy.pni.test.StructB.LAYOUT.byteSize();
    }

    public StructA(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT.byteSize()));
    }

    private static final MethodHandle bbbMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_StructA_bbb", MemorySegment.class /* self */, io.vproxy.pni.test.StructB.LAYOUT.getClass() /* b */);

    public void bbb(PNIEnv ENV, io.vproxy.pni.test.StructB b) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.bbbMH.invokeExact(ENV.MEMORY, MEMORY, (MemorySegment) (b == null ? MemorySegment.NULL : b.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
    }

    private static final MethodHandle bbbCriticalMH = PanamaUtils.lookupPNICriticalFunction(false, void.class, "JavaCritical_io_vproxy_pni_test_StructA_bbbCritical", MemorySegment.class /* self */, io.vproxy.pni.test.StructB.LAYOUT.getClass() /* b */);

    public void bbbCritical(io.vproxy.pni.test.StructB b) {
        try {
            this.bbbCriticalMH.invokeExact(MEMORY, (MemorySegment) (b == null ? MemorySegment.NULL : b.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle cccMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_StructA_ccc", MemorySegment.class /* self */, io.vproxy.pni.test.UnionC.LAYOUT.getClass() /* c */);

    public void ccc(PNIEnv ENV, io.vproxy.pni.test.UnionC c) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.cccMH.invokeExact(ENV.MEMORY, MEMORY, (MemorySegment) (c == null ? MemorySegment.NULL : c.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
    }

    private static final MethodHandle cccCriticalMH = PanamaUtils.lookupPNICriticalFunction(false, void.class, "JavaCritical_io_vproxy_pni_test_StructA_cccCritical", MemorySegment.class /* self */, io.vproxy.pni.test.UnionC.LAYOUT.getClass() /* c */);

    public void cccCritical(io.vproxy.pni.test.UnionC c) {
        try {
            this.cccCriticalMH.invokeExact(MEMORY, (MemorySegment) (c == null ? MemorySegment.NULL : c.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle cccPointerMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_StructA_cccPointer", MemorySegment.class /* self */, io.vproxy.pni.test.UnionC.LAYOUT.getClass() /* c */);

    public void cccPointer(PNIEnv ENV, io.vproxy.pni.test.UnionC c) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.cccPointerMH.invokeExact(ENV.MEMORY, MEMORY, (MemorySegment) (c == null ? MemorySegment.NULL : c.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
    }

    private static final MethodHandle cccPointerCriticalMH = PanamaUtils.lookupPNICriticalFunction(false, void.class, "JavaCritical_io_vproxy_pni_test_StructA_cccPointerCritical", MemorySegment.class /* self */, io.vproxy.pni.test.UnionC.LAYOUT.getClass() /* c */);

    public void cccPointerCritical(io.vproxy.pni.test.UnionC c) {
        try {
            this.cccPointerCriticalMH.invokeExact(MEMORY, (MemorySegment) (c == null ? MemorySegment.NULL : c.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle bbbArrayMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_StructA_bbbArray", MemorySegment.class /* self */, PNIBuf.class /* bArray */);

    public void bbbArray(PNIEnv ENV, io.vproxy.pni.test.StructB.Array bArray) {
        ENV.reset();
        try (var POOLED = Allocator.ofPooled()) {
            int ERR;
            try {
                ERR = (int) this.bbbArrayMH.invokeExact(ENV.MEMORY, MEMORY, PNIBuf.memoryOf(POOLED, bArray));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (ERR != 0) {
                ENV.throwLast();
            }
        }
    }

    private static final MethodHandle bbbArrayCriticalMH = PanamaUtils.lookupPNICriticalFunction(false, void.class, "JavaCritical_io_vproxy_pni_test_StructA_bbbArrayCritical", MemorySegment.class /* self */, PNIBuf.class /* bArray */);

    public void bbbArrayCritical(io.vproxy.pni.test.StructB.Array bArray) {
        try (var POOLED = Allocator.ofPooled()) {
            try {
                this.bbbArrayCriticalMH.invokeExact(MEMORY, PNIBuf.memoryOf(POOLED, bArray));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
        }
    }

    private static final MethodHandle bbbArray2MH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_StructA_bbbArray2", MemorySegment.class /* self */, PNIBuf.class /* bArray */);

    public void bbbArray2(PNIEnv ENV, io.vproxy.pni.test.StructB.Array bArray) {
        ENV.reset();
        try (var POOLED = Allocator.ofPooled()) {
            int ERR;
            try {
                ERR = (int) this.bbbArray2MH.invokeExact(ENV.MEMORY, MEMORY, PNIBuf.memoryOf(POOLED, bArray));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (ERR != 0) {
                ENV.throwLast();
            }
        }
    }

    private static final MethodHandle bbbArray2CriticalMH = PanamaUtils.lookupPNICriticalFunction(false, void.class, "JavaCritical_io_vproxy_pni_test_StructA_bbbArray2Critical", MemorySegment.class /* self */, PNIBuf.class /* bArray */);

    public void bbbArray2Critical(io.vproxy.pni.test.StructB.Array bArray) {
        try (var POOLED = Allocator.ofPooled()) {
            try {
                this.bbbArray2CriticalMH.invokeExact(MEMORY, PNIBuf.memoryOf(POOLED, bArray));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
        }
    }

    private static final MethodHandle retrieveBMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_StructA_retrieveB", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public io.vproxy.pni.test.StructB retrieveB(PNIEnv ENV, Allocator ALLOCATOR) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.retrieveBMH.invokeExact(ENV.MEMORY, MEMORY, ALLOCATOR.allocate(io.vproxy.pni.test.StructB.LAYOUT.byteSize()));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RESULT = ENV.returnPointer();
        return RESULT == null ? null : new io.vproxy.pni.test.StructB(RESULT);
    }

    private static final MethodHandle retrieveBCriticalMH = PanamaUtils.lookupPNICriticalFunction(false, io.vproxy.pni.test.StructB.LAYOUT.getClass(), "JavaCritical_io_vproxy_pni_test_StructA_retrieveBCritical", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public io.vproxy.pni.test.StructB retrieveBCritical(Allocator ALLOCATOR) {
        MemorySegment RESULT;
        try {
            RESULT = (MemorySegment) this.retrieveBCriticalMH.invokeExact(MEMORY, ALLOCATOR.allocate(io.vproxy.pni.test.StructB.LAYOUT.byteSize()));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (RESULT.address() == 0) return null;
        return RESULT == null ? null : new io.vproxy.pni.test.StructB(RESULT);
    }

    private static final MethodHandle retrieveCMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_StructA_retrieveC", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public io.vproxy.pni.test.UnionC retrieveC(PNIEnv ENV, Allocator ALLOCATOR) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.retrieveCMH.invokeExact(ENV.MEMORY, MEMORY, ALLOCATOR.allocate(io.vproxy.pni.test.UnionC.LAYOUT.byteSize()));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RESULT = ENV.returnPointer();
        return RESULT == null ? null : new io.vproxy.pni.test.UnionC(RESULT);
    }

    private static final MethodHandle retrieveCCriticalMH = PanamaUtils.lookupPNICriticalFunction(false, io.vproxy.pni.test.UnionC.LAYOUT.getClass(), "JavaCritical_io_vproxy_pni_test_StructA_retrieveCCritical", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public io.vproxy.pni.test.UnionC retrieveCCritical(Allocator ALLOCATOR) {
        MemorySegment RESULT;
        try {
            RESULT = (MemorySegment) this.retrieveCCriticalMH.invokeExact(MEMORY, ALLOCATOR.allocate(io.vproxy.pni.test.UnionC.LAYOUT.byteSize()));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (RESULT.address() == 0) return null;
        return RESULT == null ? null : new io.vproxy.pni.test.UnionC(RESULT);
    }

    private static final MethodHandle retrieveCPointerMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_StructA_retrieveCPointer", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public io.vproxy.pni.test.UnionC retrieveCPointer(PNIEnv ENV, Allocator ALLOCATOR) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.retrieveCPointerMH.invokeExact(ENV.MEMORY, MEMORY, ALLOCATOR.allocate(io.vproxy.pni.test.UnionC.LAYOUT.byteSize()));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RESULT = ENV.returnPointer();
        return RESULT == null ? null : new io.vproxy.pni.test.UnionC(RESULT);
    }

    private static final MethodHandle retrieveCPointerCriticalMH = PanamaUtils.lookupPNICriticalFunction(false, io.vproxy.pni.test.UnionC.LAYOUT.getClass(), "JavaCritical_io_vproxy_pni_test_StructA_retrieveCPointerCritical", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public io.vproxy.pni.test.UnionC retrieveCPointerCritical(Allocator ALLOCATOR) {
        MemorySegment RESULT;
        try {
            RESULT = (MemorySegment) this.retrieveCPointerCriticalMH.invokeExact(MEMORY, ALLOCATOR.allocate(io.vproxy.pni.test.UnionC.LAYOUT.byteSize()));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (RESULT.address() == 0) return null;
        return RESULT == null ? null : new io.vproxy.pni.test.UnionC(RESULT);
    }

    private static final MethodHandle retrieveBArrayMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_StructA_retrieveBArray", MemorySegment.class /* self */);

    public io.vproxy.pni.test.StructB.Array retrieveBArray(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.retrieveBArrayMH.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RES_SEG = ENV.returnBuf();
        if (RES_SEG.isNull()) return null;
        return new io.vproxy.pni.test.StructB.Array(RES_SEG);
    }

    private static final MethodHandle retrieveBArrayCriticalMH = PanamaUtils.lookupPNICriticalFunction(false, PNIBuf.class, "JavaCritical_io_vproxy_pni_test_StructA_retrieveBArrayCritical", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public io.vproxy.pni.test.StructB.Array retrieveBArrayCritical() {
        try (var POOLED = Allocator.ofPooled()) {
            MemorySegment RESULT;
            try {
                RESULT = (MemorySegment) this.retrieveBArrayCriticalMH.invokeExact(MEMORY, POOLED.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (RESULT.address() == 0) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return new io.vproxy.pni.test.StructB.Array(RES_SEG);
        }
    }

    private static final MethodHandle retrieveBArray2MH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_StructA_retrieveBArray2", MemorySegment.class /* self */);

    public io.vproxy.pni.test.StructB.Array retrieveBArray2(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.retrieveBArray2MH.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RES_SEG = ENV.returnBuf();
        if (RES_SEG.isNull()) return null;
        return new io.vproxy.pni.test.StructB.Array(RES_SEG);
    }

    private static final MethodHandle retrieveBArray2CriticalMH = PanamaUtils.lookupPNICriticalFunction(false, PNIBuf.class, "JavaCritical_io_vproxy_pni_test_StructA_retrieveBArray2Critical", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public io.vproxy.pni.test.StructB.Array retrieveBArray2Critical() {
        try (var POOLED = Allocator.ofPooled()) {
            MemorySegment RESULT;
            try {
                RESULT = (MemorySegment) this.retrieveBArray2CriticalMH.invokeExact(MEMORY, POOLED.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (RESULT.address() == 0) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return new io.vproxy.pni.test.StructB.Array(RES_SEG);
        }
    }

    public static class Array extends RefArray<StructA> {
        public Array(MemorySegment buf) {
            super(buf, StructA.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            this(allocator.allocate(StructA.LAYOUT.byteSize() * len));
        }

        public Array(PNIBuf buf) {
            this(buf.get());
        }

        @Override
        protected StructA construct(MemorySegment seg) {
            return new StructA(seg);
        }

        @Override
        protected MemorySegment getSegment(StructA value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<StructA> {
        private Func(io.vproxy.pni.CallSite<StructA> func) {
            super(func);
        }

        public static Func of(io.vproxy.pni.CallSite<StructA> func) {
            return new Func(func);
        }

        @Override
        protected StructA construct(MemorySegment seg) {
            return new StructA(seg);
        }

        @Override
        protected MemorySegment getSegment(StructA value) {
            return value.MEMORY;
        }
    }
}
// metadata.generator-version: pni test
// sha256:af274ba5f2c2d20beb41ecb5d41ebdf302e7a88240e85acfc2f536ccc86f31a7
