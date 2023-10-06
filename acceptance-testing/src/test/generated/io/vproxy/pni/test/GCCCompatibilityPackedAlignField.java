package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class GCCCompatibilityPackedAlignField extends AbstractNativeObject implements NativeObject {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        ValueLayout.JAVA_BYTE.withName("b1"),
        MemoryLayout.sequenceLayout(3L, ValueLayout.JAVA_BYTE) /* padding */,
        ValueLayout.JAVA_SHORT_UNALIGNED.withName("s"),
        ValueLayout.JAVA_INT_UNALIGNED.withName("n2"),
        MemoryLayout.sequenceLayout(2L, ValueLayout.JAVA_BYTE) /* padding */
    );
    public final MemorySegment MEMORY;

    @Override
    public MemorySegment MEMORY() {
        return MEMORY;
    }

    private static final VarHandle b1VH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("b1")
    );

    public byte getB1() {
        return (byte) b1VH.get(MEMORY);
    }

    public void setB1(byte b1) {
        b1VH.set(MEMORY, b1);
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

    private static final VarHandle n2VH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("n2")
    );

    public int getN2() {
        return (int) n2VH.get(MEMORY);
    }

    public void setN2(int n2) {
        n2VH.set(MEMORY, n2);
    }

    public GCCCompatibilityPackedAlignField(MemorySegment MEMORY) {
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
        OFFSET += ValueLayout.JAVA_BYTE.byteSize();
        OFFSET += 3; /* padding */
        OFFSET += ValueLayout.JAVA_SHORT_UNALIGNED.byteSize();
        OFFSET += ValueLayout.JAVA_INT_UNALIGNED.byteSize();
        OFFSET += 2; /* padding */
    }

    public GCCCompatibilityPackedAlignField(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT));
    }

    private static final MethodHandle initMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_GCCCompatibilityPackedAlignField_init", MemorySegment.class /* self */);

    public void init(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) initMH.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
    }

    private static final MethodHandle sizeMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_GCCCompatibilityPackedAlignField_size", MemorySegment.class /* self */);

    public long size(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) sizeMH.invokeExact(ENV.MEMORY, MEMORY);
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
        SB.append("GCCCompatibilityPackedAlignField{\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("b1 => ");
            SB.append(getB1());
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("s => ");
            SB.append(getS());
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("n2 => ");
            SB.append(getN2());
        }
        SB.append("\n");
        SB.append(" ".repeat(INDENT)).append("}@").append(Long.toString(MEMORY.address(), 16));
    }

    public static class Array extends RefArray<GCCCompatibilityPackedAlignField> {
        public Array(MemorySegment buf) {
            super(buf, GCCCompatibilityPackedAlignField.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            super(allocator, GCCCompatibilityPackedAlignField.LAYOUT, len);
        }

        public Array(PNIBuf buf) {
            super(buf, GCCCompatibilityPackedAlignField.LAYOUT);
        }

        @Override
        protected void elementToString(io.vproxy.pni.test.GCCCompatibilityPackedAlignField ELEM, StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
            ELEM.toString(SB, INDENT, VISITED, CORRUPTED_MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "GCCCompatibilityPackedAlignField.Array";
        }

        @Override
        protected GCCCompatibilityPackedAlignField construct(MemorySegment seg) {
            return new GCCCompatibilityPackedAlignField(seg);
        }

        @Override
        protected MemorySegment getSegment(GCCCompatibilityPackedAlignField value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<GCCCompatibilityPackedAlignField> {
        private Func(io.vproxy.pni.CallSite<GCCCompatibilityPackedAlignField> func) {
            super(func);
        }

        private Func(io.vproxy.pni.CallSite<GCCCompatibilityPackedAlignField> func, Options opts) {
            super(func, opts);
        }

        private Func(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static Func of(io.vproxy.pni.CallSite<GCCCompatibilityPackedAlignField> func) {
            return new Func(func);
        }

        public static Func of(io.vproxy.pni.CallSite<GCCCompatibilityPackedAlignField> func, Options opts) {
            return new Func(func, opts);
        }

        public static Func of(MemorySegment MEMORY) {
            return new Func(MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "GCCCompatibilityPackedAlignField.Func";
        }

        @Override
        protected GCCCompatibilityPackedAlignField construct(MemorySegment seg) {
            return new GCCCompatibilityPackedAlignField(seg);
        }
    }
}
// metadata.generator-version: pni test
// sha256:2378fba9a08b6a2a3d0ca18268e1f8ee62fe18f33ed9da7af824d48814781a68
