package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class CustomNativeTypeStruct extends AbstractNativeObject implements NativeObject {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        ValueLayout.ADDRESS_UNALIGNED.withName("field"),
        MemoryLayout.sequenceLayout(3L, ValueLayout.ADDRESS_UNALIGNED).withName("array")
    );
    public final MemorySegment MEMORY;

    @Override
    public MemorySegment MEMORY() {
        return MEMORY;
    }

    private static final VarHandle fieldVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("field")
    );

    public MemorySegment getField() {
        var SEG = (MemorySegment) fieldVH.get(MEMORY);
        if (SEG.address() == 0) return null;
        return SEG;
    }

    public void setField(MemorySegment field) {
        if (field == null) {
            fieldVH.set(MEMORY, MemorySegment.NULL);
        } else {
            fieldVH.set(MEMORY, field);
        }
    }

    private final PointerArray array;

    public PointerArray getArray() {
        return this.array;
    }

    public CustomNativeTypeStruct(MemorySegment MEMORY) {
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
        OFFSET += ValueLayout.ADDRESS_UNALIGNED.byteSize();
        this.array = new PointerArray(MEMORY.asSlice(OFFSET, 3 * ValueLayout.ADDRESS_UNALIGNED.byteSize()));
        OFFSET += 3 * ValueLayout.ADDRESS_UNALIGNED.byteSize();
    }

    public CustomNativeTypeStruct(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT));
    }

    private static final MethodHandle getP1MH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_CustomNativeTypeStruct_getP1", MemorySegment.class /* self */);

    public long getP1(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) getP1MH.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnLong();
    }

    private static final MethodHandle getArrMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_CustomNativeTypeStruct_getArr", MemorySegment.class /* self */);

    public PointerArray getArr(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) getArrMH.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RES_SEG = ENV.returnBuf();
        if (RES_SEG.isNull()) return null;
        return new PointerArray(RES_SEG);
    }

    @Override
    public void toString(StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
        if (!VISITED.add(new NativeObjectTuple(this))) {
            SB.append("<...>@").append(Long.toString(MEMORY.address(), 16));
            return;
        }
        SB.append("CustomNativeTypeStruct{\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("field => ");
            SB.append(PanamaUtils.memorySegmentToString(getField()));
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("array => ");
            PanamaUtils.nativeObjectToString(getArray(), SB, INDENT + 4, VISITED, CORRUPTED_MEMORY);
        }
        SB.append("\n");
        SB.append(" ".repeat(INDENT)).append("}@").append(Long.toString(MEMORY.address(), 16));
    }

    public static class Array extends RefArray<CustomNativeTypeStruct> {
        public Array(MemorySegment buf) {
            super(buf, CustomNativeTypeStruct.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            super(allocator, CustomNativeTypeStruct.LAYOUT, len);
        }

        public Array(PNIBuf buf) {
            super(buf, CustomNativeTypeStruct.LAYOUT);
        }

        @Override
        protected void elementToString(io.vproxy.pni.test.CustomNativeTypeStruct ELEM, StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
            ELEM.toString(SB, INDENT, VISITED, CORRUPTED_MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "CustomNativeTypeStruct.Array";
        }

        @Override
        protected CustomNativeTypeStruct construct(MemorySegment seg) {
            return new CustomNativeTypeStruct(seg);
        }

        @Override
        protected MemorySegment getSegment(CustomNativeTypeStruct value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<CustomNativeTypeStruct> {
        private Func(io.vproxy.pni.CallSite<CustomNativeTypeStruct> func) {
            super(func);
        }

        private Func(io.vproxy.pni.CallSite<CustomNativeTypeStruct> func, Options opts) {
            super(func, opts);
        }

        private Func(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static Func of(io.vproxy.pni.CallSite<CustomNativeTypeStruct> func) {
            return new Func(func);
        }

        public static Func of(io.vproxy.pni.CallSite<CustomNativeTypeStruct> func, Options opts) {
            return new Func(func, opts);
        }

        public static Func of(MemorySegment MEMORY) {
            return new Func(MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "CustomNativeTypeStruct.Func";
        }

        @Override
        protected CustomNativeTypeStruct construct(MemorySegment seg) {
            return new CustomNativeTypeStruct(seg);
        }
    }
}
// metadata.generator-version: pni test
// sha256:ccd616a3761384f878a4a74c4d5be982644c685f8e28adffb81714a5af144ad3
