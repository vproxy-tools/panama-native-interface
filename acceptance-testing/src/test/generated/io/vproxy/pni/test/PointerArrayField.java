package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class PointerArrayField extends AbstractNativeObject implements NativeObject {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        MemoryLayout.sequenceLayout(3L, ValueLayout.ADDRESS_UNALIGNED).withName("pointerArray"),
        PNIBuf.LAYOUT.withName("pointerArrayPointer")
    );
    public final MemorySegment MEMORY;

    @Override
    public MemorySegment MEMORY() {
        return MEMORY;
    }

    private final PointerArray pointerArray;

    public PointerArray getPointerArray() {
        return this.pointerArray;
    }

    private final PNIBuf pointerArrayPointer;

    public PointerArray getPointerArrayPointer() {
        var SEG = this.pointerArrayPointer.get();
        if (SEG == null) return null;
        return new PointerArray(SEG);
    }

    public void setPointerArrayPointer(PointerArray pointerArrayPointer) {
        if (pointerArrayPointer == null) {
            this.pointerArrayPointer.setToNull();
        } else {
            this.pointerArrayPointer.set(pointerArrayPointer.MEMORY);
        }
    }

    public PointerArrayField(MemorySegment MEMORY) {
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
        this.pointerArray = new PointerArray(MEMORY.asSlice(OFFSET, 3 * ValueLayout.ADDRESS_UNALIGNED.byteSize()));
        OFFSET += 3 * ValueLayout.ADDRESS_UNALIGNED.byteSize();
        this.pointerArrayPointer = new PNIBuf(MEMORY.asSlice(OFFSET, PNIBuf.LAYOUT.byteSize()));
        OFFSET += PNIBuf.LAYOUT.byteSize();
    }

    public PointerArrayField(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT));
    }

    private static final MethodHandle setMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PointerArrayField_set", MemorySegment.class /* self */, PNIBuf.class /* a */);

    public void set(PNIEnv ENV, PointerArray a) {
        ENV.reset();
        try (var POOLED = Allocator.ofPooled()) {
            int ERR;
            try {
                ERR = (int) setMH.invokeExact(ENV.MEMORY, MEMORY, PNIBuf.memoryOf(POOLED, a));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (ERR != 0) {
                ENV.throwLast();
            }
        }
    }

    private static final MethodHandle getPtrFieldMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PointerArrayField_getPtrField", MemorySegment.class /* self */);

    public PointerArray getPtrField(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) getPtrFieldMH.invokeExact(ENV.MEMORY, MEMORY);
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

    private static final MethodHandle getLenFieldMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PointerArrayField_getLenField", MemorySegment.class /* self */);

    public PointerArray getLenField(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) getLenFieldMH.invokeExact(ENV.MEMORY, MEMORY);
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
        SB.append("PointerArrayField{\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("pointerArray => ");
            if (CORRUPTED_MEMORY) SB.append("<?>");
            else PanamaUtils.nativeObjectToString(getPointerArray(), SB, INDENT + 4, VISITED, CORRUPTED_MEMORY);
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("pointerArrayPointer => ");
            if (CORRUPTED_MEMORY) SB.append("<?>");
            else PanamaUtils.nativeObjectToString(getPointerArrayPointer(), SB, INDENT + 4, VISITED, CORRUPTED_MEMORY);
        }
        SB.append("\n");
        SB.append(" ".repeat(INDENT)).append("}@").append(Long.toString(MEMORY.address(), 16));
    }

    public static class Array extends RefArray<PointerArrayField> {
        public Array(MemorySegment buf) {
            super(buf, PointerArrayField.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            super(allocator, PointerArrayField.LAYOUT, len);
        }

        public Array(PNIBuf buf) {
            super(buf, PointerArrayField.LAYOUT);
        }

        @Override
        protected void elementToString(io.vproxy.pni.test.PointerArrayField ELEM, StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
            ELEM.toString(SB, INDENT, VISITED, CORRUPTED_MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "PointerArrayField.Array";
        }

        @Override
        protected PointerArrayField construct(MemorySegment seg) {
            return new PointerArrayField(seg);
        }

        @Override
        protected MemorySegment getSegment(PointerArrayField value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<PointerArrayField> {
        private Func(io.vproxy.pni.CallSite<PointerArrayField> func) {
            super(func);
        }

        private Func(io.vproxy.pni.CallSite<PointerArrayField> func, Options opts) {
            super(func, opts);
        }

        private Func(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static Func of(io.vproxy.pni.CallSite<PointerArrayField> func) {
            return new Func(func);
        }

        public static Func of(io.vproxy.pni.CallSite<PointerArrayField> func, Options opts) {
            return new Func(func, opts);
        }

        public static Func of(MemorySegment MEMORY) {
            return new Func(MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "PointerArrayField.Func";
        }

        @Override
        protected PointerArrayField construct(MemorySegment seg) {
            return new PointerArrayField(seg);
        }
    }
}
// metadata.generator-version: pni test
// sha256:5fbb217e8a8da586afc571af9552556fc107d7ac84e6f5fca4d86e14db925dfe
