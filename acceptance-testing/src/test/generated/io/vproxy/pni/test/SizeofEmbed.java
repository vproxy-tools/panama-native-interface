package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class SizeofEmbed {
    private static final MethodHandle __getLayoutByteSizeMH = PanamaUtils.lookupPNICriticalFunction(true, long.class, "JavaCritical_io_vproxy_pni_test_SizeofEmbed___getLayoutByteSize");

    private static long __getLayoutByteSize() {
        long RESULT;
        try {
            RESULT = (long) __getLayoutByteSizeMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    public static final MemoryLayout LAYOUT = PanamaUtils.padLayout(__getLayoutByteSize(), MemoryLayout::structLayout,
        ValueLayout.JAVA_BYTE.withName("x"),
        MemoryLayout.sequenceLayout(7L, ValueLayout.JAVA_BYTE) /* padding */,
        ValueLayout.ADDRESS_UNALIGNED.withName("un"),
        io.vproxy.pni.test.SizeofStructExpr.LAYOUT.withName("st")
    );
    public final MemorySegment MEMORY;

    private static final VarHandle xVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("x")
    );

    public byte getX() {
        return (byte) xVH.get(MEMORY);
    }

    public void setX(byte x) {
        xVH.set(MEMORY, x);
    }

    private static final VarHandle unVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("un")
    );

    public io.vproxy.pni.test.SizeofUnion getUn() {
        var SEG = (MemorySegment) unVH.get(MEMORY);
        if (SEG.address() == 0) return null;
        return new io.vproxy.pni.test.SizeofUnion(SEG);
    }

    public void setUn(io.vproxy.pni.test.SizeofUnion un) {
        if (un == null) {
            unVH.set(MEMORY, MemorySegment.NULL);
        } else {
            unVH.set(MEMORY, un.MEMORY);
        }
    }

    private final io.vproxy.pni.test.SizeofStructExpr st;

    public io.vproxy.pni.test.SizeofStructExpr getSt() {
        return this.st;
    }

    public SizeofEmbed(MemorySegment MEMORY) {
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
        OFFSET += ValueLayout.JAVA_BYTE.byteSize();
        OFFSET += 7; /* padding */
        OFFSET += 8;
        this.st = new io.vproxy.pni.test.SizeofStructExpr(MEMORY.asSlice(OFFSET, io.vproxy.pni.test.SizeofStructExpr.LAYOUT.byteSize()));
        OFFSET += io.vproxy.pni.test.SizeofStructExpr.LAYOUT.byteSize();
    }

    public SizeofEmbed(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT.byteSize()));
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        toString(sb, 0, new java.util.HashSet<>(), false);
        return sb.toString();
    }

    public void toString(StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
        if (!VISITED.add(new NativeObjectTuple(getClass(), MEMORY.address()))) {
            SB.append("<...>@").append(Long.toString(MEMORY.address(), 16));
            return;
        }
        SB.append("SizeofEmbed{\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("x => ");
            SB.append(getX());
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("un => ");
            if (CORRUPTED_MEMORY) {
                SB.append("<?>");
            } else {
                var VALUE = getUn();
                if (VALUE == null) SB.append("null");
                else VALUE.toString(SB, INDENT + 4, VISITED, CORRUPTED_MEMORY);
            }
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("st => ");
            getSt().toString(SB, INDENT + 4, VISITED, CORRUPTED_MEMORY);
        }
        SB.append("\n");
        SB.append(" ".repeat(INDENT)).append("}@").append(Long.toString(MEMORY.address(), 16));
    }

    public static class Array extends RefArray<SizeofEmbed> {
        public Array(MemorySegment buf) {
            super(buf, SizeofEmbed.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            this(allocator.allocate(SizeofEmbed.LAYOUT.byteSize() * len));
        }

        public Array(PNIBuf buf) {
            this(buf.get());
        }

        @Override
        protected void elementToString(io.vproxy.pni.test.SizeofEmbed ELEM, StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
            ELEM.toString(SB, INDENT, VISITED, CORRUPTED_MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "SizeofEmbed.Array";
        }

        @Override
        protected SizeofEmbed construct(MemorySegment seg) {
            return new SizeofEmbed(seg);
        }

        @Override
        protected MemorySegment getSegment(SizeofEmbed value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<SizeofEmbed> {
        private Func(io.vproxy.pni.CallSite<SizeofEmbed> func) {
            super(func);
        }

        private Func(io.vproxy.pni.CallSite<SizeofEmbed> func, Options opts) {
            super(func, opts);
        }

        private Func(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static Func of(io.vproxy.pni.CallSite<SizeofEmbed> func) {
            return new Func(func);
        }

        public static Func of(io.vproxy.pni.CallSite<SizeofEmbed> func, Options opts) {
            return new Func(func, opts);
        }

        public static Func of(MemorySegment MEMORY) {
            return new Func(MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "SizeofEmbed.Func";
        }

        @Override
        protected SizeofEmbed construct(MemorySegment seg) {
            return new SizeofEmbed(seg);
        }
    }
}
// metadata.generator-version: pni test
// sha256:cb7a70887d7970dc9867515f954b6f9d67dd1d9c093b7b17dacb02ace0416493
