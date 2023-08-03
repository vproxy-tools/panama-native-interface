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
        io.vproxy.pni.test.StructD.LAYOUT.withName("d")
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
    }

    public StructA(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT.byteSize()));
    }

    private final MethodHandle bbb = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_StructA_bbb", MemorySegment.class /* self */, io.vproxy.pni.test.StructB.LAYOUT.getClass() /* b */);

    public void bbb(PNIEnv ENV, io.vproxy.pni.test.StructB b) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.bbb.invokeExact(ENV.MEMORY, MEMORY, b.MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
    }

    private final MethodHandle bbbCritical = PanamaUtils.lookupPNICriticalFunction(false, void.class, "JavaCritical_io_vproxy_pni_test_StructA_bbbCritical", MemorySegment.class /* self */, io.vproxy.pni.test.StructB.LAYOUT.getClass() /* b */);

    public void bbbCritical(io.vproxy.pni.test.StructB b) {
        try {
            this.bbbCritical.invokeExact(MEMORY, b.MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private final MethodHandle ccc = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_StructA_ccc", MemorySegment.class /* self */, io.vproxy.pni.test.UnionC.LAYOUT.getClass() /* c */);

    public void ccc(PNIEnv ENV, io.vproxy.pni.test.UnionC c) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.ccc.invokeExact(ENV.MEMORY, MEMORY, c.MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
    }

    private final MethodHandle cccCritical = PanamaUtils.lookupPNICriticalFunction(false, void.class, "JavaCritical_io_vproxy_pni_test_StructA_cccCritical", MemorySegment.class /* self */, io.vproxy.pni.test.UnionC.LAYOUT.getClass() /* c */);

    public void cccCritical(io.vproxy.pni.test.UnionC c) {
        try {
            this.cccCritical.invokeExact(MEMORY, c.MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private final MethodHandle cccPointer = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_StructA_cccPointer", MemorySegment.class /* self */, io.vproxy.pni.test.UnionC.LAYOUT.getClass() /* c */);

    public void cccPointer(PNIEnv ENV, io.vproxy.pni.test.UnionC c) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.cccPointer.invokeExact(ENV.MEMORY, MEMORY, c.MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
    }

    private final MethodHandle cccPointerCritical = PanamaUtils.lookupPNICriticalFunction(false, void.class, "JavaCritical_io_vproxy_pni_test_StructA_cccPointerCritical", MemorySegment.class /* self */, io.vproxy.pni.test.UnionC.LAYOUT.getClass() /* c */);

    public void cccPointerCritical(io.vproxy.pni.test.UnionC c) {
        try {
            this.cccPointerCritical.invokeExact(MEMORY, c.MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private final MethodHandle retrieveB = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_StructA_retrieveB", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public io.vproxy.pni.test.StructB retrieveB(PNIEnv ENV, Allocator ALLOCATOR) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.retrieveB.invokeExact(ENV.MEMORY, MEMORY, ALLOCATOR.allocate(io.vproxy.pni.test.StructB.LAYOUT.byteSize()));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RESULT = ENV.returnPointer();
        return RESULT == null ? null : new io.vproxy.pni.test.StructB(RESULT);
    }

    private final MethodHandle retrieveBCritical = PanamaUtils.lookupPNICriticalFunction(false, io.vproxy.pni.test.StructB.LAYOUT.getClass(), "JavaCritical_io_vproxy_pni_test_StructA_retrieveBCritical", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public io.vproxy.pni.test.StructB retrieveBCritical(Allocator ALLOCATOR) {
        MemorySegment RESULT;
        try {
            RESULT = (MemorySegment) this.retrieveBCritical.invokeExact(MEMORY, ALLOCATOR.allocate(io.vproxy.pni.test.StructB.LAYOUT.byteSize()));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (RESULT.address() == 0) return null;
        return RESULT == null ? null : new io.vproxy.pni.test.StructB(RESULT);
    }

    private final MethodHandle retrieveC = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_StructA_retrieveC", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public io.vproxy.pni.test.UnionC retrieveC(PNIEnv ENV, Allocator ALLOCATOR) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.retrieveC.invokeExact(ENV.MEMORY, MEMORY, ALLOCATOR.allocate(io.vproxy.pni.test.UnionC.LAYOUT.byteSize()));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RESULT = ENV.returnPointer();
        return RESULT == null ? null : new io.vproxy.pni.test.UnionC(RESULT);
    }

    private final MethodHandle retrieveCCritical = PanamaUtils.lookupPNICriticalFunction(false, io.vproxy.pni.test.UnionC.LAYOUT.getClass(), "JavaCritical_io_vproxy_pni_test_StructA_retrieveCCritical", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public io.vproxy.pni.test.UnionC retrieveCCritical(Allocator ALLOCATOR) {
        MemorySegment RESULT;
        try {
            RESULT = (MemorySegment) this.retrieveCCritical.invokeExact(MEMORY, ALLOCATOR.allocate(io.vproxy.pni.test.UnionC.LAYOUT.byteSize()));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (RESULT.address() == 0) return null;
        return RESULT == null ? null : new io.vproxy.pni.test.UnionC(RESULT);
    }

    private final MethodHandle retrieveCPointer = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_StructA_retrieveCPointer", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public io.vproxy.pni.test.UnionC retrieveCPointer(PNIEnv ENV, Allocator ALLOCATOR) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.retrieveCPointer.invokeExact(ENV.MEMORY, MEMORY, ALLOCATOR.allocate(io.vproxy.pni.test.UnionC.LAYOUT.byteSize()));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RESULT = ENV.returnPointer();
        return RESULT == null ? null : new io.vproxy.pni.test.UnionC(RESULT);
    }

    private final MethodHandle retrieveCPointerCritical = PanamaUtils.lookupPNICriticalFunction(false, io.vproxy.pni.test.UnionC.LAYOUT.getClass(), "JavaCritical_io_vproxy_pni_test_StructA_retrieveCPointerCritical", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public io.vproxy.pni.test.UnionC retrieveCPointerCritical(Allocator ALLOCATOR) {
        MemorySegment RESULT;
        try {
            RESULT = (MemorySegment) this.retrieveCPointerCritical.invokeExact(MEMORY, ALLOCATOR.allocate(io.vproxy.pni.test.UnionC.LAYOUT.byteSize()));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (RESULT.address() == 0) return null;
        return RESULT == null ? null : new io.vproxy.pni.test.UnionC(RESULT);
    }

    public static class Array extends RefArray<StructA> {
        public Array(MemorySegment buf) {
            super(buf, StructA.LAYOUT);
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
// sha256:e8e05ca81b3a3d9dc4b235f52d21820614fba55d616198b2658f784a19a8f58e
