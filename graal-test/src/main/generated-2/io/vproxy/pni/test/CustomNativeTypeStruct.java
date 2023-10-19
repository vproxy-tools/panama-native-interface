package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;
import io.vproxy.pni.graal.*;
import org.graalvm.nativeimage.*;
import org.graalvm.nativeimage.c.function.*;
import org.graalvm.nativeimage.c.type.VoidPointer;
import org.graalvm.word.WordFactory;

public class CustomNativeTypeStruct extends AbstractNativeObject implements NativeObject {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        ValueLayout.ADDRESS_UNALIGNED.withName("field")
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

    public CustomNativeTypeStruct(MemorySegment MEMORY) {
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
        OFFSET += ValueLayout.ADDRESS_UNALIGNED.byteSize();
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
// sha256:8550dd287be418d87f020348f607c9d62b6b0fce83f5d8671b3f2797ae5034ba
