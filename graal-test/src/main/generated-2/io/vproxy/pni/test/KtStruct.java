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

public class KtStruct extends AbstractNativeObject implements NativeObject {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        ValueLayout.JAVA_INT_UNALIGNED.withName("aInt"),
        MemoryLayout.sequenceLayout(4L, ValueLayout.JAVA_BYTE) /* padding */,
        ValueLayout.JAVA_LONG_UNALIGNED.withName("aLong")
    );
    public final MemorySegment MEMORY;

    @Override
    public MemorySegment MEMORY() {
        return MEMORY;
    }

    private static final VarHandleW aIntVH = VarHandleW.of(
        LAYOUT.varHandle(
            MemoryLayout.PathElement.groupElement("aInt")
        )
    );

    public int getAInt() {
        return aIntVH.getInt(MEMORY);
    }

    public void setAInt(int aInt) {
        aIntVH.set(MEMORY, aInt);
    }

    private static final VarHandleW aLongVH = VarHandleW.of(
        LAYOUT.varHandle(
            MemoryLayout.PathElement.groupElement("aLong")
        )
    );

    public long getALong() {
        return aLongVH.getLong(MEMORY);
    }

    public void setALong(long aLong) {
        aLongVH.set(MEMORY, aLong);
    }

    public KtStruct(MemorySegment MEMORY) {
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
        OFFSET += ValueLayout.JAVA_INT_UNALIGNED.byteSize();
        OFFSET += 4; /* padding */
        OFFSET += ValueLayout.JAVA_LONG_UNALIGNED.byteSize();
    }

    public KtStruct(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT));
    }

    private static final MethodHandle retrieveIntMH = PanamaUtils.lookupPNIFunction(new PNILinkOptions(), "Java_io_vproxy_pni_test_KtStruct_retrieveInt", MemorySegment.class /* self */);

    public int retrieveInt(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) retrieveIntMH.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnInt();
    }

    private static final MethodHandle retrieveLongMH = PanamaUtils.lookupPNIFunction(new PNILinkOptions(), "Java_io_vproxy_pni_test_KtStruct_retrieveLong", MemorySegment.class /* self */);

    public long retrieveLong(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) retrieveLongMH.invokeExact(ENV.MEMORY, MEMORY);
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
    }

    public static class Array extends RefArray<KtStruct> {
        public Array(MemorySegment buf) {
            super(buf, KtStruct.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            super(allocator, KtStruct.LAYOUT, len);
        }

        public Array(PNIBuf buf) {
            super(buf, KtStruct.LAYOUT);
        }

        @Override
        protected void elementToString(io.vproxy.pni.test.KtStruct ELEM, StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
            ELEM.toString(SB, INDENT, VISITED, CORRUPTED_MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "KtStruct.Array";
        }

        @Override
        protected KtStruct construct(MemorySegment seg) {
            return new KtStruct(seg);
        }

        @Override
        protected MemorySegment getSegment(KtStruct value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<KtStruct> {
        private Func(io.vproxy.pni.CallSite<KtStruct> func) {
            super(func);
        }

        private Func(io.vproxy.pni.CallSite<KtStruct> func, Options opts) {
            super(func, opts);
        }

        private Func(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static Func of(io.vproxy.pni.CallSite<KtStruct> func) {
            return new Func(func);
        }

        public static Func of(io.vproxy.pni.CallSite<KtStruct> func, Options opts) {
            return new Func(func, opts);
        }

        public static Func of(MemorySegment MEMORY) {
            return new Func(MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "KtStruct.Func";
        }

        @Override
        protected KtStruct construct(MemorySegment seg) {
            return new KtStruct(seg);
        }
    }
}
// metadata.generator-version: pni test
// sha256:c758b712e3dfa498c7095450ca63b0ed328fdc907392224ec6fa93e49c457932
