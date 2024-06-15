package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.hack.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class BaseClass extends AbstractNativeObject implements NativeObject {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        ValueLayout.JAVA_BYTE.withName("a")
    );
    public final MemorySegment MEMORY;

    @Override
    public MemorySegment MEMORY() {
        return MEMORY;
    }

    private static final VarHandleW aVH = VarHandleW.of(
        LAYOUT.varHandle(
            MemoryLayout.PathElement.groupElement("a")
        )
    );

    public byte getA() {
        return aVH.getByte(MEMORY);
    }

    public void setA(byte a) {
        aVH.set(MEMORY, a);
    }

    public BaseClass(MemorySegment MEMORY) {
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
        OFFSET += ValueLayout.JAVA_BYTE.byteSize();
    }

    public BaseClass(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT));
    }

    private static final MethodHandle aaaMH = PanamaUtils.lookupPNIFunction(new PNILinkOptions(), "Java_io_vproxy_pni_test_BaseClass_aaa", MemorySegment.class /* self */, byte.class /* a */);

    public void aaa(PNIEnv ENV, byte a) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) aaaMH.invokeExact(ENV.MEMORY, MEMORY, a);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
    }

    @Override
    public void toString(StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
        if (!VISITED.add(new NativeObjectTuple(this))) {
            SB.append("<...>@").append(Long.toString(MEMORY.address(), 16));
            return;
        }
        SB.append("BaseClass{\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("a => ");
            SB.append(getA());
        }
        SB.append("\n");
        SB.append(" ".repeat(INDENT)).append("}@").append(Long.toString(MEMORY.address(), 16));
    }

    public static class Array extends RefArray<BaseClass> {
        public Array(MemorySegment buf) {
            super(buf, BaseClass.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            super(allocator, BaseClass.LAYOUT, len);
        }

        public Array(PNIBuf buf) {
            super(buf, BaseClass.LAYOUT);
        }

        @Override
        protected void elementToString(io.vproxy.pni.test.BaseClass ELEM, StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
            ELEM.toString(SB, INDENT, VISITED, CORRUPTED_MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "BaseClass.Array";
        }

        @Override
        protected BaseClass construct(MemorySegment seg) {
            return new BaseClass(seg);
        }

        @Override
        protected MemorySegment getSegment(BaseClass value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<BaseClass> {
        private Func(io.vproxy.pni.CallSite<BaseClass> func) {
            super(func);
        }

        private Func(io.vproxy.pni.CallSite<BaseClass> func, Options opts) {
            super(func, opts);
        }

        private Func(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static Func of(io.vproxy.pni.CallSite<BaseClass> func) {
            return new Func(func);
        }

        public static Func of(io.vproxy.pni.CallSite<BaseClass> func, Options opts) {
            return new Func(func, opts);
        }

        public static Func of(MemorySegment MEMORY) {
            return new Func(MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "BaseClass.Func";
        }

        @Override
        protected BaseClass construct(MemorySegment seg) {
            return new BaseClass(seg);
        }
    }
}
// metadata.generator-version: pni test
// sha256:aea86b3561d85a7b6ce37f4ac38e54b33c917416fc79018ee7dd636b97e9d346
