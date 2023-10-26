package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class RefAndFuncFields extends AbstractNativeObject implements NativeObject {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        ValueLayout.ADDRESS_UNALIGNED.withName("ref"),
        ValueLayout.ADDRESS_UNALIGNED.withName("ref2"),
        ValueLayout.ADDRESS_UNALIGNED.withName("ref3"),
        ValueLayout.ADDRESS_UNALIGNED.withName("func"),
        ValueLayout.ADDRESS_UNALIGNED.withName("func2")
    );
    public final MemorySegment MEMORY;

    @Override
    public MemorySegment MEMORY() {
        return MEMORY;
    }

    private static final VarHandle refVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("ref")
    );

    public PNIRef<java.util.Map<java.lang.String, java.lang.Integer>> getRef() {
        var SEG = (MemorySegment) refVH.get(MEMORY);
        if (SEG.address() == 0) return null;
        return PNIRef.of(SEG);
    }

    public void setRef(PNIRef<java.util.Map<java.lang.String, java.lang.Integer>> ref) {
        if (ref == null) {
            refVH.set(MEMORY, MemorySegment.NULL);
        } else {
            refVH.set(MEMORY, ref.MEMORY);
        }
    }

    private static final VarHandle ref2VH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("ref2")
    );

    public PNIRef<java.util.List<io.vproxy.pni.test.ObjectStruct>> getRef2() {
        var SEG = (MemorySegment) ref2VH.get(MEMORY);
        if (SEG.address() == 0) return null;
        return PNIRef.of(SEG);
    }

    public void setRef2(PNIRef<java.util.List<io.vproxy.pni.test.ObjectStruct>> ref2) {
        if (ref2 == null) {
            ref2VH.set(MEMORY, MemorySegment.NULL);
        } else {
            ref2VH.set(MEMORY, ref2.MEMORY);
        }
    }

    private static final VarHandle ref3VH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("ref3")
    );

    public PNIRef<io.vproxy.pni.test.ObjectStruct[]> getRef3() {
        var SEG = (MemorySegment) ref3VH.get(MEMORY);
        if (SEG.address() == 0) return null;
        return PNIRef.of(SEG);
    }

    public void setRef3(PNIRef<io.vproxy.pni.test.ObjectStruct[]> ref3) {
        if (ref3 == null) {
            ref3VH.set(MEMORY, MemorySegment.NULL);
        } else {
            ref3VH.set(MEMORY, ref3.MEMORY);
        }
    }

    private static final VarHandle funcVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("func")
    );

    public PNIFunc<java.util.Map<java.lang.String, java.lang.Integer>> getFunc() {
        var SEG = (MemorySegment) funcVH.get(MEMORY);
        if (SEG.address() == 0) return null;
        return PNIRef.Func.of(SEG);
    }

    public void setFunc(PNIFunc<java.util.Map<java.lang.String, java.lang.Integer>> func) {
        if (func == null) {
            funcVH.set(MEMORY, MemorySegment.NULL);
        } else {
            funcVH.set(MEMORY, func.MEMORY);
        }
    }

    private static final VarHandle func2VH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("func2")
    );

    public PNIFunc<io.vproxy.pni.test.ObjectStruct> getFunc2() {
        var SEG = (MemorySegment) func2VH.get(MEMORY);
        if (SEG.address() == 0) return null;
        return io.vproxy.pni.test.ObjectStruct.Func.of(SEG);
    }

    public void setFunc2(PNIFunc<io.vproxy.pni.test.ObjectStruct> func2) {
        if (func2 == null) {
            func2VH.set(MEMORY, MemorySegment.NULL);
        } else {
            func2VH.set(MEMORY, func2.MEMORY);
        }
    }

    public RefAndFuncFields(MemorySegment MEMORY) {
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
        OFFSET += ValueLayout.ADDRESS_UNALIGNED.byteSize();
        OFFSET += ValueLayout.ADDRESS_UNALIGNED.byteSize();
        OFFSET += ValueLayout.ADDRESS_UNALIGNED.byteSize();
        OFFSET += ValueLayout.ADDRESS_UNALIGNED.byteSize();
        OFFSET += ValueLayout.ADDRESS_UNALIGNED.byteSize();
    }

    public RefAndFuncFields(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT));
    }

    private static final MethodHandle callMH = PanamaUtils.lookupPNIFunction(new PNILinkOptions(), "Java_io_vproxy_pni_test_RefAndFuncFields_call", MemorySegment.class /* self */);

    public void call(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) callMH.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
    }

    private static final MethodHandle call2MH = PanamaUtils.lookupPNIFunction(new PNILinkOptions(), "Java_io_vproxy_pni_test_RefAndFuncFields_call2", MemorySegment.class /* self */, io.vproxy.pni.test.ObjectStruct.LAYOUT.getClass() /* o */);

    public void call2(PNIEnv ENV, io.vproxy.pni.test.ObjectStruct o) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) call2MH.invokeExact(ENV.MEMORY, MEMORY, (MemorySegment) (o == null ? MemorySegment.NULL : o.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
    }

    private static final MethodHandle setMH = PanamaUtils.lookupPNIFunction(new PNILinkOptions(), "Java_io_vproxy_pni_test_RefAndFuncFields_set", MemorySegment.class /* self */, PNIRef.class /* ref */, PNIRef.class /* ref2 */, PNIRef.class /* ref3 */, PNIFunc.class /* func */, PNIFunc.class /* func2 */);

    public void set(PNIEnv ENV, java.util.Map<java.lang.String, java.lang.Integer> ref, java.util.List<io.vproxy.pni.test.ObjectStruct> ref2, io.vproxy.pni.test.ObjectStruct[] ref3, PNIFunc<java.util.Map<java.lang.String, java.lang.Integer>> func, PNIFunc<io.vproxy.pni.test.ObjectStruct> func2) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) setMH.invokeExact(ENV.MEMORY, MEMORY, (MemorySegment) (ref == null ? MemorySegment.NULL : PNIRef.of(ref).MEMORY), (MemorySegment) (ref2 == null ? MemorySegment.NULL : PNIRef.of(ref2).MEMORY), (MemorySegment) (ref3 == null ? MemorySegment.NULL : PNIRef.of(ref3).MEMORY), (MemorySegment) (func == null ? MemorySegment.NULL : func.MEMORY), (MemorySegment) (func2 == null ? MemorySegment.NULL : func2.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
    }

    private static final MethodHandle setRawMH = PanamaUtils.lookupPNIFunction(new PNILinkOptions(), "Java_io_vproxy_pni_test_RefAndFuncFields_setRaw", MemorySegment.class /* self */, PNIRef.class /* ref */, PNIRef.class /* ref2 */, PNIRef.class /* ref3 */);

    public void setRaw(PNIEnv ENV, PNIRef<java.util.Map<java.lang.String, java.lang.Integer>> ref, PNIRef<java.util.List<io.vproxy.pni.test.ObjectStruct>> ref2, PNIRef<io.vproxy.pni.test.ObjectStruct[]> ref3) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) setRawMH.invokeExact(ENV.MEMORY, MEMORY, (MemorySegment) (ref == null ? MemorySegment.NULL : ref.MEMORY), (MemorySegment) (ref2 == null ? MemorySegment.NULL : ref2.MEMORY), (MemorySegment) (ref3 == null ? MemorySegment.NULL : ref3.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
    }

    private static final MethodHandle retrieveRefMH = PanamaUtils.lookupPNIFunction(new PNILinkOptions(), "Java_io_vproxy_pni_test_RefAndFuncFields_retrieveRef", MemorySegment.class /* self */);

    public PNIRef<java.util.Map<java.lang.String, java.lang.Integer>> retrieveRef(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) retrieveRefMH.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RESULT = ENV.returnPointer();
        if (RESULT == null) return null;
        return PNIRef.of(RESULT);
    }

    private static final MethodHandle retrieveRef2MH = PanamaUtils.lookupPNIFunction(new PNILinkOptions(), "Java_io_vproxy_pni_test_RefAndFuncFields_retrieveRef2", MemorySegment.class /* self */);

    public PNIRef<java.util.List<io.vproxy.pni.test.ObjectStruct>> retrieveRef2(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) retrieveRef2MH.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RESULT = ENV.returnPointer();
        if (RESULT == null) return null;
        return PNIRef.of(RESULT);
    }

    private static final MethodHandle retrieveRef3MH = PanamaUtils.lookupPNIFunction(new PNILinkOptions(), "Java_io_vproxy_pni_test_RefAndFuncFields_retrieveRef3", MemorySegment.class /* self */);

    public PNIRef<io.vproxy.pni.test.ObjectStruct[]> retrieveRef3(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) retrieveRef3MH.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RESULT = ENV.returnPointer();
        if (RESULT == null) return null;
        return PNIRef.of(RESULT);
    }

    private static final MethodHandle retrieveFuncMH = PanamaUtils.lookupPNIFunction(new PNILinkOptions(), "Java_io_vproxy_pni_test_RefAndFuncFields_retrieveFunc", MemorySegment.class /* self */);

    public PNIFunc<java.util.Map<java.lang.String, java.lang.Integer>> retrieveFunc(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) retrieveFuncMH.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RESULT = ENV.returnPointer();
        if (RESULT == null) return null;
        return PNIRef.Func.of(RESULT);
    }

    private static final MethodHandle retrieveFunc2MH = PanamaUtils.lookupPNIFunction(new PNILinkOptions(), "Java_io_vproxy_pni_test_RefAndFuncFields_retrieveFunc2", MemorySegment.class /* self */);

    public PNIFunc<io.vproxy.pni.test.ObjectStruct> retrieveFunc2(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) retrieveFunc2MH.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RESULT = ENV.returnPointer();
        if (RESULT == null) return null;
        return io.vproxy.pni.test.ObjectStruct.Func.of(RESULT);
    }

    @Override
    public void toString(StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
        if (!VISITED.add(new NativeObjectTuple(this))) {
            SB.append("<...>@").append(Long.toString(MEMORY.address(), 16));
            return;
        }
        SB.append("RefAndFuncFields{\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("ref => ");
            if (CORRUPTED_MEMORY) SB.append("<?>");
            else PanamaUtils.nativeObjectToString(getRef(), SB, INDENT + 4, VISITED, CORRUPTED_MEMORY);
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("ref2 => ");
            if (CORRUPTED_MEMORY) SB.append("<?>");
            else PanamaUtils.nativeObjectToString(getRef2(), SB, INDENT + 4, VISITED, CORRUPTED_MEMORY);
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("ref3 => ");
            if (CORRUPTED_MEMORY) SB.append("<?>");
            else PanamaUtils.nativeObjectToString(getRef3(), SB, INDENT + 4, VISITED, CORRUPTED_MEMORY);
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("func => ");
            if (CORRUPTED_MEMORY) SB.append("<?>");
            else PanamaUtils.nativeObjectToString(getFunc(), SB, INDENT + 4, VISITED, CORRUPTED_MEMORY);
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("func2 => ");
            if (CORRUPTED_MEMORY) SB.append("<?>");
            else PanamaUtils.nativeObjectToString(getFunc2(), SB, INDENT + 4, VISITED, CORRUPTED_MEMORY);
        }
        SB.append("\n");
        SB.append(" ".repeat(INDENT)).append("}@").append(Long.toString(MEMORY.address(), 16));
    }

    public static class Array extends RefArray<RefAndFuncFields> {
        public Array(MemorySegment buf) {
            super(buf, RefAndFuncFields.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            super(allocator, RefAndFuncFields.LAYOUT, len);
        }

        public Array(PNIBuf buf) {
            super(buf, RefAndFuncFields.LAYOUT);
        }

        @Override
        protected void elementToString(io.vproxy.pni.test.RefAndFuncFields ELEM, StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
            ELEM.toString(SB, INDENT, VISITED, CORRUPTED_MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "RefAndFuncFields.Array";
        }

        @Override
        protected RefAndFuncFields construct(MemorySegment seg) {
            return new RefAndFuncFields(seg);
        }

        @Override
        protected MemorySegment getSegment(RefAndFuncFields value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<RefAndFuncFields> {
        private Func(io.vproxy.pni.CallSite<RefAndFuncFields> func) {
            super(func);
        }

        private Func(io.vproxy.pni.CallSite<RefAndFuncFields> func, Options opts) {
            super(func, opts);
        }

        private Func(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static Func of(io.vproxy.pni.CallSite<RefAndFuncFields> func) {
            return new Func(func);
        }

        public static Func of(io.vproxy.pni.CallSite<RefAndFuncFields> func, Options opts) {
            return new Func(func, opts);
        }

        public static Func of(MemorySegment MEMORY) {
            return new Func(MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "RefAndFuncFields.Func";
        }

        @Override
        protected RefAndFuncFields construct(MemorySegment seg) {
            return new RefAndFuncFields(seg);
        }
    }
}
// metadata.generator-version: pni test
// sha256:61b0a19af1fae51e8c49a3c62cb6facc6c6aae2fb22b1d717ab3bf2e49249974
