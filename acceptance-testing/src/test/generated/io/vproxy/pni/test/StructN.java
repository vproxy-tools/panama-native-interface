package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class StructN implements NativeObject {
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

    private static final VarHandle sVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("s")
    );

    public short getS() {
        return (short) sVH.get(MEMORY);
    }

    public void setS(short s) {
        sVH.set(MEMORY, s);
    }

    private static final VarHandle lVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("l")
    );

    public long getL() {
        return (long) lVH.get(MEMORY);
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
        this(ALLOCATOR.allocate(LAYOUT.byteSize()));
    }

    private static final MethodHandle retrieveSMH = PanamaUtils.lookupPNIFunction(true, "Java_io_vproxy_pni_test_StructN_retrieveS", MemorySegment.class /* self */);

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

    private static final MethodHandle retrieveLMH = PanamaUtils.lookupPNIFunction(true, "Java_io_vproxy_pni_test_StructN_retrieveL", MemorySegment.class /* self */);

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
    public String toString() {
        var sb = new StringBuilder();
        toString(sb, 0, new java.util.HashSet<>(), false);
        return sb.toString();
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
            this(allocator.allocate(StructN.LAYOUT.byteSize() * len));
        }

        public Array(PNIBuf buf) {
            this(buf.get());
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
// sha256:571cd03f5d30d01e73987a8d4fe8b1f5f734ec820b2c5ad6bad8951ef3c0a9b2
