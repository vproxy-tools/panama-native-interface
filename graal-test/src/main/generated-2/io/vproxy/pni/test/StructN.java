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

public class StructN extends AbstractNativeObject implements NativeObject {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        ValueLayout.JAVA_SHORT_UNALIGNED.withName("s"),
        MemoryLayout.sequenceLayout(6L, ValueLayout.JAVA_BYTE) /* padding */,
        ValueLayout.JAVA_LONG_UNALIGNED.withName("l")
    );
    public final MemorySegment MEMORY;

    @Override
    public MemorySegment MEMORY() {
        return MEMORY;
    }

    private static final VarHandleW sVH = VarHandleW.of(
        LAYOUT.varHandle(
            MemoryLayout.PathElement.groupElement("s")
        )
    );

    public short getS() {
        return sVH.getShort(MEMORY);
    }

    public void setS(short s) {
        sVH.set(MEMORY, s);
    }

    private static final VarHandleW lVH = VarHandleW.of(
        LAYOUT.varHandle(
            MemoryLayout.PathElement.groupElement("l")
        )
    );

    public long getL() {
        return lVH.getLong(MEMORY);
    }

    public void setL(long l) {
        lVH.set(MEMORY, l);
    }

    public StructN(MemorySegment MEMORY) {
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
        OFFSET += ValueLayout.JAVA_SHORT_UNALIGNED.byteSize();
        OFFSET += 6; /* padding */
        OFFSET += ValueLayout.JAVA_LONG_UNALIGNED.byteSize();
    }

    public StructN(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT));
    }

    private static final MethodHandle retrieveSMH = PanamaUtils.lookupPNIFunction(new PNILinkOptions().setCritical(true), "Java_io_vproxy_pni_test_StructN_retrieveS", MemorySegment.class /* self */);

    public short retrieveS(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) retrieveSMH.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnShort();
    }

    private static final MethodHandle retrieveLMH = PanamaUtils.lookupPNIFunction(new PNILinkOptions().setCritical(true), "Java_io_vproxy_pni_test_StructN_retrieveL", MemorySegment.class /* self */);

    public long retrieveL(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) retrieveLMH.invokeExact(ENV.MEMORY, MEMORY);
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
        SB.append("StructN{\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("s => ");
            SB.append(getS());
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("l => ");
            SB.append(getL());
        }
        SB.append("\n");
        SB.append(" ".repeat(INDENT)).append("}@").append(Long.toString(MEMORY.address(), 16));
    }

    public static class Array extends RefArray<StructN> {
        public Array(MemorySegment buf) {
            super(buf, StructN.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            super(allocator, StructN.LAYOUT, len);
        }

        public Array(PNIBuf buf) {
            super(buf, StructN.LAYOUT);
        }

        @Override
        protected void elementToString(io.vproxy.pni.test.StructN ELEM, StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
            ELEM.toString(SB, INDENT, VISITED, CORRUPTED_MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "StructN.Array";
        }

        @Override
        protected StructN construct(MemorySegment seg) {
            return new StructN(seg);
        }

        @Override
        protected MemorySegment getSegment(StructN value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<StructN> {
        private Func(io.vproxy.pni.CallSite<StructN> func) {
            super(func);
        }

        private Func(io.vproxy.pni.CallSite<StructN> func, Options opts) {
            super(func, opts);
        }

        private Func(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static Func of(io.vproxy.pni.CallSite<StructN> func) {
            return new Func(func);
        }

        public static Func of(io.vproxy.pni.CallSite<StructN> func, Options opts) {
            return new Func(func, opts);
        }

        public static Func of(MemorySegment MEMORY) {
            return new Func(MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "StructN.Func";
        }

        @Override
        protected StructN construct(MemorySegment seg) {
            return new StructN(seg);
        }
    }
}
// metadata.generator-version: pni test
// sha256:b097162f06aaadcd9b88196ab1d94cfe98be5b44d763c6d076fe8bc9cfefcbb9
