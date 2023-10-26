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

public class KtStructInherit extends io.vproxy.pni.test.KtStruct implements NativeObject {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        io.vproxy.pni.test.KtStruct.LAYOUT,
        io.vproxy.pni.test.PrimitiveStruct.LAYOUT.withName("obj")
    );
    public final MemorySegment MEMORY;

    @Override
    public MemorySegment MEMORY() {
        return MEMORY;
    }

    private final io.vproxy.pni.test.PrimitiveStruct obj;

    public io.vproxy.pni.test.PrimitiveStruct getObj() {
        return this.obj;
    }

    public KtStructInherit(MemorySegment MEMORY) {
        super(MEMORY);
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
        OFFSET += io.vproxy.pni.test.KtStruct.LAYOUT.byteSize();
        this.obj = new io.vproxy.pni.test.PrimitiveStruct(MEMORY.asSlice(OFFSET, io.vproxy.pni.test.PrimitiveStruct.LAYOUT.byteSize()));
        OFFSET += io.vproxy.pni.test.PrimitiveStruct.LAYOUT.byteSize();
    }

    public KtStructInherit(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT));
    }

    private static final MethodHandle retrieveObjMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_KtStructInherit_retrieveObj", MemorySegment.class /* self */);

    public io.vproxy.pni.test.PrimitiveStruct retrieveObj(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) retrieveObjMH.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RESULT = ENV.returnPointer();
        return RESULT == null ? null : new io.vproxy.pni.test.PrimitiveStruct(RESULT);
    }

    @Override
    public void toString(StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
        if (!VISITED.add(new NativeObjectTuple(this))) {
            SB.append("<...>@").append(Long.toString(MEMORY.address(), 16));
            return;
        }
        SB.append("KtStructInherit{\n");
        SB.append(" ".repeat(INDENT + 4)).append("SUPER => ");
        {
            INDENT += 4;
            SB.append("KtStruct{\n");
            {
                SB.append(" ".repeat(INDENT + 4)).append("aInt => ");
                SB.append(getAInt());
            }
            SB.append(",\n");
            {
                SB.append(" ".repeat(INDENT + 4)).append("aLong => ");
                SB.append(getALong());
            }
            SB.append("\n");
            SB.append(" ".repeat(INDENT)).append("}@").append(Long.toString(MEMORY.address(), 16));
            INDENT -= 4;
            SB.append(",\n");
        }
        {
            SB.append(" ".repeat(INDENT + 4)).append("obj => ");
            PanamaUtils.nativeObjectToString(getObj(), SB, INDENT + 4, VISITED, CORRUPTED_MEMORY);
        }
        SB.append("\n");
        SB.append(" ".repeat(INDENT)).append("}@").append(Long.toString(MEMORY.address(), 16));
    }

    public static class Array extends RefArray<KtStructInherit> {
        public Array(MemorySegment buf) {
            super(buf, KtStructInherit.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            super(allocator, KtStructInherit.LAYOUT, len);
        }

        public Array(PNIBuf buf) {
            super(buf, KtStructInherit.LAYOUT);
        }

        @Override
        protected void elementToString(io.vproxy.pni.test.KtStructInherit ELEM, StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
            ELEM.toString(SB, INDENT, VISITED, CORRUPTED_MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "KtStructInherit.Array";
        }

        @Override
        protected KtStructInherit construct(MemorySegment seg) {
            return new KtStructInherit(seg);
        }

        @Override
        protected MemorySegment getSegment(KtStructInherit value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<KtStructInherit> {
        private Func(io.vproxy.pni.CallSite<KtStructInherit> func) {
            super(func);
        }

        private Func(io.vproxy.pni.CallSite<KtStructInherit> func, Options opts) {
            super(func, opts);
        }

        private Func(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static Func of(io.vproxy.pni.CallSite<KtStructInherit> func) {
            return new Func(func);
        }

        public static Func of(io.vproxy.pni.CallSite<KtStructInherit> func, Options opts) {
            return new Func(func, opts);
        }

        public static Func of(MemorySegment MEMORY) {
            return new Func(MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "KtStructInherit.Func";
        }

        @Override
        protected KtStructInherit construct(MemorySegment seg) {
            return new KtStructInherit(seg);
        }
    }
}
// metadata.generator-version: pni test
// sha256:6f428e8e252ff4aa55f7798377b13ebb48aa04642266390d78d090ad9266026c
