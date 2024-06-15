package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.hack.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class StructA extends AbstractNativeObject implements NativeObject {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        io.vproxy.pni.test.StructB.LAYOUT.withName("b"),
        io.vproxy.pni.test.UnionC.LAYOUT.withName("c"),
        ValueLayout.ADDRESS_UNALIGNED.withName("cPointer"),
        io.vproxy.pni.test.StructD.LAYOUT.withName("d"),
        PNIBuf.LAYOUT.withName("bArray"),
        MemoryLayout.sequenceLayout(5L, io.vproxy.pni.test.StructB.LAYOUT).withName("bArray2")
    );
    public final MemorySegment MEMORY;

    @Override
    public MemorySegment MEMORY() {
        return MEMORY;
    }

    private final io.vproxy.pni.test.StructB b;

    public io.vproxy.pni.test.StructB getB() {
        return this.b;
    }

    private final io.vproxy.pni.test.UnionC c;

    public io.vproxy.pni.test.UnionC getC() {
        return this.c;
    }

    private static final VarHandleW cPointerVH = VarHandleW.of(
        LAYOUT.varHandle(
            MemoryLayout.PathElement.groupElement("cPointer")
        )
    );

    public io.vproxy.pni.test.UnionC getCPointer() {
        var SEG = cPointerVH.getMemorySegment(MEMORY);
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
        this(ALLOCATOR.allocate(LAYOUT));
    }

    private static final MethodHandle bbbMH = PanamaUtils.lookupPNIFunction(new PNILinkOptions(), "Java_io_vproxy_pni_test_StructA_bbb", MemorySegment.class /* self */, io.vproxy.pni.test.StructB.LAYOUT.getClass() /* b */);

    public void bbb(PNIEnv ENV, io.vproxy.pni.test.StructB b) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) bbbMH.invokeExact(ENV.MEMORY, MEMORY, (MemorySegment) (b == null ? MemorySegment.NULL : b.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
    }

    private static final MethodHandle bbbCriticalMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), void.class, "JavaCritical_io_vproxy_pni_test_StructA_bbbCritical", MemorySegment.class /* self */, io.vproxy.pni.test.StructB.LAYOUT.getClass() /* b */);

    public void bbbCritical(io.vproxy.pni.test.StructB b) {
        try {
            bbbCriticalMH.invokeExact(MEMORY, (MemorySegment) (b == null ? MemorySegment.NULL : b.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle cccMH = PanamaUtils.lookupPNIFunction(new PNILinkOptions(), "Java_io_vproxy_pni_test_StructA_ccc", MemorySegment.class /* self */, io.vproxy.pni.test.UnionC.LAYOUT.getClass() /* c */);

    public void ccc(PNIEnv ENV, io.vproxy.pni.test.UnionC c) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) cccMH.invokeExact(ENV.MEMORY, MEMORY, (MemorySegment) (c == null ? MemorySegment.NULL : c.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
    }

    private static final MethodHandle cccCriticalMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), void.class, "JavaCritical_io_vproxy_pni_test_StructA_cccCritical", MemorySegment.class /* self */, io.vproxy.pni.test.UnionC.LAYOUT.getClass() /* c */);

    public void cccCritical(io.vproxy.pni.test.UnionC c) {
        try {
            cccCriticalMH.invokeExact(MEMORY, (MemorySegment) (c == null ? MemorySegment.NULL : c.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle cccPointerMH = PanamaUtils.lookupPNIFunction(new PNILinkOptions(), "Java_io_vproxy_pni_test_StructA_cccPointer", MemorySegment.class /* self */, io.vproxy.pni.test.UnionC.LAYOUT.getClass() /* c */);

    public void cccPointer(PNIEnv ENV, io.vproxy.pni.test.UnionC c) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) cccPointerMH.invokeExact(ENV.MEMORY, MEMORY, (MemorySegment) (c == null ? MemorySegment.NULL : c.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
    }

    private static final MethodHandle cccPointerCriticalMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), void.class, "JavaCritical_io_vproxy_pni_test_StructA_cccPointerCritical", MemorySegment.class /* self */, io.vproxy.pni.test.UnionC.LAYOUT.getClass() /* c */);

    public void cccPointerCritical(io.vproxy.pni.test.UnionC c) {
        try {
            cccPointerCriticalMH.invokeExact(MEMORY, (MemorySegment) (c == null ? MemorySegment.NULL : c.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle bbbArrayMH = PanamaUtils.lookupPNIFunction(new PNILinkOptions(), "Java_io_vproxy_pni_test_StructA_bbbArray", MemorySegment.class /* self */, PNIBuf.class /* bArray */);

    public void bbbArray(PNIEnv ENV, io.vproxy.pni.test.StructB.Array bArray) {
        ENV.reset();
        try (var POOLED = Allocator.ofPooled()) {
            int ERR;
            try {
                ERR = (int) bbbArrayMH.invokeExact(ENV.MEMORY, MEMORY, PNIBuf.memoryOf(POOLED, bArray));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (ERR != 0) {
                ENV.throwLast();
            }
        }
    }

    private static final MethodHandle bbbArrayCriticalMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), void.class, "JavaCritical_io_vproxy_pni_test_StructA_bbbArrayCritical", MemorySegment.class /* self */, PNIBuf.class /* bArray */);

    public void bbbArrayCritical(io.vproxy.pni.test.StructB.Array bArray) {
        try (var POOLED = Allocator.ofPooled()) {
            try {
                bbbArrayCriticalMH.invokeExact(MEMORY, PNIBuf.memoryOf(POOLED, bArray));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
        }
    }

    private static final MethodHandle bbbArray2MH = PanamaUtils.lookupPNIFunction(new PNILinkOptions(), "Java_io_vproxy_pni_test_StructA_bbbArray2", MemorySegment.class /* self */, PNIBuf.class /* bArray */);

    public void bbbArray2(PNIEnv ENV, io.vproxy.pni.test.StructB.Array bArray) {
        ENV.reset();
        try (var POOLED = Allocator.ofPooled()) {
            int ERR;
            try {
                ERR = (int) bbbArray2MH.invokeExact(ENV.MEMORY, MEMORY, PNIBuf.memoryOf(POOLED, bArray));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (ERR != 0) {
                ENV.throwLast();
            }
        }
    }

    private static final MethodHandle bbbArray2CriticalMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), void.class, "JavaCritical_io_vproxy_pni_test_StructA_bbbArray2Critical", MemorySegment.class /* self */, PNIBuf.class /* bArray */);

    public void bbbArray2Critical(io.vproxy.pni.test.StructB.Array bArray) {
        try (var POOLED = Allocator.ofPooled()) {
            try {
                bbbArray2CriticalMH.invokeExact(MEMORY, PNIBuf.memoryOf(POOLED, bArray));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
        }
    }

    private static final MethodHandle retrieveBMH = PanamaUtils.lookupPNIFunction(new PNILinkOptions(), "Java_io_vproxy_pni_test_StructA_retrieveB", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public io.vproxy.pni.test.StructB retrieveB(PNIEnv ENV, Allocator ALLOCATOR) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) retrieveBMH.invokeExact(ENV.MEMORY, MEMORY, ALLOCATOR.allocate(io.vproxy.pni.test.StructB.LAYOUT));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RESULT = ENV.returnPointer();
        return RESULT == null ? null : new io.vproxy.pni.test.StructB(RESULT);
    }

    private static final MethodHandle retrieveBCriticalMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), io.vproxy.pni.test.StructB.LAYOUT.getClass(), "JavaCritical_io_vproxy_pni_test_StructA_retrieveBCritical", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public io.vproxy.pni.test.StructB retrieveBCritical(Allocator ALLOCATOR) {
        MemorySegment RESULT;
        try {
            RESULT = (MemorySegment) retrieveBCriticalMH.invokeExact(MEMORY, ALLOCATOR.allocate(io.vproxy.pni.test.StructB.LAYOUT));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (RESULT.address() == 0) return null;
        return RESULT == null ? null : new io.vproxy.pni.test.StructB(RESULT);
    }

    private static final MethodHandle retrieveCMH = PanamaUtils.lookupPNIFunction(new PNILinkOptions(), "Java_io_vproxy_pni_test_StructA_retrieveC", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public io.vproxy.pni.test.UnionC retrieveC(PNIEnv ENV, Allocator ALLOCATOR) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) retrieveCMH.invokeExact(ENV.MEMORY, MEMORY, ALLOCATOR.allocate(io.vproxy.pni.test.UnionC.LAYOUT));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RESULT = ENV.returnPointer();
        return RESULT == null ? null : new io.vproxy.pni.test.UnionC(RESULT);
    }

    private static final MethodHandle retrieveCCriticalMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), io.vproxy.pni.test.UnionC.LAYOUT.getClass(), "JavaCritical_io_vproxy_pni_test_StructA_retrieveCCritical", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public io.vproxy.pni.test.UnionC retrieveCCritical(Allocator ALLOCATOR) {
        MemorySegment RESULT;
        try {
            RESULT = (MemorySegment) retrieveCCriticalMH.invokeExact(MEMORY, ALLOCATOR.allocate(io.vproxy.pni.test.UnionC.LAYOUT));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (RESULT.address() == 0) return null;
        return RESULT == null ? null : new io.vproxy.pni.test.UnionC(RESULT);
    }

    private static final MethodHandle retrieveCPointerMH = PanamaUtils.lookupPNIFunction(new PNILinkOptions(), "Java_io_vproxy_pni_test_StructA_retrieveCPointer", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public io.vproxy.pni.test.UnionC retrieveCPointer(PNIEnv ENV, Allocator ALLOCATOR) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) retrieveCPointerMH.invokeExact(ENV.MEMORY, MEMORY, ALLOCATOR.allocate(io.vproxy.pni.test.UnionC.LAYOUT));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RESULT = ENV.returnPointer();
        return RESULT == null ? null : new io.vproxy.pni.test.UnionC(RESULT);
    }

    private static final MethodHandle retrieveCPointerCriticalMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), io.vproxy.pni.test.UnionC.LAYOUT.getClass(), "JavaCritical_io_vproxy_pni_test_StructA_retrieveCPointerCritical", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public io.vproxy.pni.test.UnionC retrieveCPointerCritical(Allocator ALLOCATOR) {
        MemorySegment RESULT;
        try {
            RESULT = (MemorySegment) retrieveCPointerCriticalMH.invokeExact(MEMORY, ALLOCATOR.allocate(io.vproxy.pni.test.UnionC.LAYOUT));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (RESULT.address() == 0) return null;
        return RESULT == null ? null : new io.vproxy.pni.test.UnionC(RESULT);
    }

    private static final MethodHandle retrieveBArrayMH = PanamaUtils.lookupPNIFunction(new PNILinkOptions(), "Java_io_vproxy_pni_test_StructA_retrieveBArray", MemorySegment.class /* self */);

    public io.vproxy.pni.test.StructB.Array retrieveBArray(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) retrieveBArrayMH.invokeExact(ENV.MEMORY, MEMORY);
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

    private static final MethodHandle retrieveBArrayCriticalMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), PNIBuf.class, "JavaCritical_io_vproxy_pni_test_StructA_retrieveBArrayCritical", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public io.vproxy.pni.test.StructB.Array retrieveBArrayCritical() {
        try (var POOLED = Allocator.ofPooled()) {
            MemorySegment RESULT;
            try {
                RESULT = (MemorySegment) retrieveBArrayCriticalMH.invokeExact(MEMORY, POOLED.allocate(PNIBuf.LAYOUT));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (RESULT.address() == 0) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return new io.vproxy.pni.test.StructB.Array(RES_SEG);
        }
    }

    private static final MethodHandle retrieveBArray2MH = PanamaUtils.lookupPNIFunction(new PNILinkOptions(), "Java_io_vproxy_pni_test_StructA_retrieveBArray2", MemorySegment.class /* self */);

    public io.vproxy.pni.test.StructB.Array retrieveBArray2(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) retrieveBArray2MH.invokeExact(ENV.MEMORY, MEMORY);
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

    private static final MethodHandle retrieveBArray2CriticalMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), PNIBuf.class, "JavaCritical_io_vproxy_pni_test_StructA_retrieveBArray2Critical", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public io.vproxy.pni.test.StructB.Array retrieveBArray2Critical() {
        try (var POOLED = Allocator.ofPooled()) {
            MemorySegment RESULT;
            try {
                RESULT = (MemorySegment) retrieveBArray2CriticalMH.invokeExact(MEMORY, POOLED.allocate(PNIBuf.LAYOUT));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (RESULT.address() == 0) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return new io.vproxy.pni.test.StructB.Array(RES_SEG);
        }
    }

    @Override
    public void toString(StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
        if (!VISITED.add(new NativeObjectTuple(this))) {
            SB.append("<...>@").append(Long.toString(MEMORY.address(), 16));
            return;
        }
        SB.append("StructA{\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("b => ");
            PanamaUtils.nativeObjectToString(getB(), SB, INDENT + 4, VISITED, CORRUPTED_MEMORY);
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("c => ");
            PanamaUtils.nativeObjectToString(getC(), SB, INDENT + 4, VISITED, CORRUPTED_MEMORY);
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("cPointer => ");
            if (CORRUPTED_MEMORY) SB.append("<?>");
            else PanamaUtils.nativeObjectToString(getCPointer(), SB, INDENT + 4, VISITED, CORRUPTED_MEMORY);
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("d => ");
            PanamaUtils.nativeObjectToString(getD(), SB, INDENT + 4, VISITED, CORRUPTED_MEMORY);
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("bArray => ");
            if (CORRUPTED_MEMORY) SB.append("<?>");
            else PanamaUtils.nativeObjectToString(getBArray(), SB, INDENT + 4, VISITED, CORRUPTED_MEMORY);
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("bArray2 => ");
            PanamaUtils.nativeObjectToString(getBArray2(), SB, INDENT + 4, VISITED, CORRUPTED_MEMORY);
        }
        SB.append("\n");
        SB.append(" ".repeat(INDENT)).append("}@").append(Long.toString(MEMORY.address(), 16));
    }

    public static class Array extends RefArray<StructA> {
        public Array(MemorySegment buf) {
            super(buf, StructA.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            super(allocator, StructA.LAYOUT, len);
        }

        public Array(PNIBuf buf) {
            super(buf, StructA.LAYOUT);
        }

        @Override
        protected void elementToString(io.vproxy.pni.test.StructA ELEM, StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
            ELEM.toString(SB, INDENT, VISITED, CORRUPTED_MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "StructA.Array";
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

        private Func(io.vproxy.pni.CallSite<StructA> func, Options opts) {
            super(func, opts);
        }

        private Func(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static Func of(io.vproxy.pni.CallSite<StructA> func) {
            return new Func(func);
        }

        public static Func of(io.vproxy.pni.CallSite<StructA> func, Options opts) {
            return new Func(func, opts);
        }

        public static Func of(MemorySegment MEMORY) {
            return new Func(MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "StructA.Func";
        }

        @Override
        protected StructA construct(MemorySegment seg) {
            return new StructA(seg);
        }
    }
}
// metadata.generator-version: pni test
// sha256:a406cddc175b7a570e7591b5920f8037374ea53502d938f36359783fa29c3486
