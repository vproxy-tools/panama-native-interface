package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.hack.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;
import io.vproxy.pni.graal.*;
import org.graalvm.nativeimage.*;
import org.graalvm.nativeimage.c.function.*;
import org.graalvm.nativeimage.c.type.VoidPointer;
import org.graalvm.word.WordFactory;

public class PointerOnlyStruct extends AbstractNativeObject implements NativeObject {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(

    );
    public final MemorySegment MEMORY;

    @Override
    public MemorySegment MEMORY() {
        return MEMORY;
    }

    public PointerOnlyStruct(MemorySegment MEMORY) {
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
    }

    public PointerOnlyStruct(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT));
    }

    private static final MethodHandle retrieveMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), long.class, "JavaCritical_io_vproxy_pni_test_PointerOnlyStruct_retrieve", MemorySegment.class /* self */);

    public long retrieve() {
        long RESULT;
        try {
            RESULT = (long) retrieveMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    @Override
    public void toString(StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
        if (!VISITED.add(new NativeObjectTuple(this))) {
            SB.append("<...>@").append(Long.toString(MEMORY.address(), 16));
            return;
        }
        SB.append("PointerOnlyStruct{\n");
        SB.append(" ".repeat(INDENT)).append("}@").append(Long.toString(MEMORY.address(), 16));
    }

    public static class Array extends RefArray<PointerOnlyStruct> {
        public Array(MemorySegment buf) {
            super(buf, PointerOnlyStruct.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            super(allocator, PointerOnlyStruct.LAYOUT, len);
        }

        public Array(PNIBuf buf) {
            super(buf, PointerOnlyStruct.LAYOUT);
        }

        @Override
        protected void elementToString(io.vproxy.pni.test.PointerOnlyStruct ELEM, StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
            ELEM.toString(SB, INDENT, VISITED, CORRUPTED_MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "PointerOnlyStruct.Array";
        }

        @Override
        protected PointerOnlyStruct construct(MemorySegment seg) {
            return new PointerOnlyStruct(seg);
        }

        @Override
        protected MemorySegment getSegment(PointerOnlyStruct value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<PointerOnlyStruct> {
        private Func(io.vproxy.pni.CallSite<PointerOnlyStruct> func) {
            super(func);
        }

        private Func(io.vproxy.pni.CallSite<PointerOnlyStruct> func, Options opts) {
            super(func, opts);
        }

        private Func(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static Func of(io.vproxy.pni.CallSite<PointerOnlyStruct> func) {
            return new Func(func);
        }

        public static Func of(io.vproxy.pni.CallSite<PointerOnlyStruct> func, Options opts) {
            return new Func(func, opts);
        }

        public static Func of(MemorySegment MEMORY) {
            return new Func(MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "PointerOnlyStruct.Func";
        }

        @Override
        protected PointerOnlyStruct construct(MemorySegment seg) {
            return new PointerOnlyStruct(seg);
        }
    }
}
// metadata.generator-version: pni test
// sha256:834a9888443a3c6237a96502ebc50d2dab6dadf1b8df6192f9e594b41e19973b
