package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class PointerOnlyStructWithLen extends AbstractNativeObject implements NativeObject {
    private static final MethodHandle __getLayoutByteSizeMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), long.class, "JavaCritical_io_vproxy_pni_test_PointerOnlyStructWithLen___getLayoutByteSize");

    private static long __getLayoutByteSize() {
        long RESULT;
        try {
            RESULT = (long) __getLayoutByteSizeMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    public static final MemoryLayout LAYOUT = PanamaUtils.padLayout(__getLayoutByteSize(), MemoryLayout::structLayout

    );
    public final MemorySegment MEMORY;

    @Override
    public MemorySegment MEMORY() {
        return MEMORY;
    }

    public PointerOnlyStructWithLen(MemorySegment MEMORY) {
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
    }

    public PointerOnlyStructWithLen(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT));
    }

    @Override
    public void toString(StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
        if (!VISITED.add(new NativeObjectTuple(this))) {
            SB.append("<...>@").append(Long.toString(MEMORY.address(), 16));
            return;
        }
        SB.append("PointerOnlyStructWithLen{\n");
        SB.append(" ".repeat(INDENT)).append("}@").append(Long.toString(MEMORY.address(), 16));
    }

    public static class Array extends RefArray<PointerOnlyStructWithLen> {
        public Array(MemorySegment buf) {
            super(buf, PointerOnlyStructWithLen.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            super(allocator, PointerOnlyStructWithLen.LAYOUT, len);
        }

        public Array(PNIBuf buf) {
            super(buf, PointerOnlyStructWithLen.LAYOUT);
        }

        @Override
        protected void elementToString(io.vproxy.pni.test.PointerOnlyStructWithLen ELEM, StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
            ELEM.toString(SB, INDENT, VISITED, CORRUPTED_MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "PointerOnlyStructWithLen.Array";
        }

        @Override
        protected PointerOnlyStructWithLen construct(MemorySegment seg) {
            return new PointerOnlyStructWithLen(seg);
        }

        @Override
        protected MemorySegment getSegment(PointerOnlyStructWithLen value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<PointerOnlyStructWithLen> {
        private Func(io.vproxy.pni.CallSite<PointerOnlyStructWithLen> func) {
            super(func);
        }

        private Func(io.vproxy.pni.CallSite<PointerOnlyStructWithLen> func, Options opts) {
            super(func, opts);
        }

        private Func(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static Func of(io.vproxy.pni.CallSite<PointerOnlyStructWithLen> func) {
            return new Func(func);
        }

        public static Func of(io.vproxy.pni.CallSite<PointerOnlyStructWithLen> func, Options opts) {
            return new Func(func, opts);
        }

        public static Func of(MemorySegment MEMORY) {
            return new Func(MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "PointerOnlyStructWithLen.Func";
        }

        @Override
        protected PointerOnlyStructWithLen construct(MemorySegment seg) {
            return new PointerOnlyStructWithLen(seg);
        }
    }
}
// metadata.generator-version: pni test
// sha256:cacaa64a441ad050a623ccc7f2b0115c11fb4d383467bc5ddbbcd6d45d31d1b6
