package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class GCCompatibilityStructAlign extends AbstractNativeObject implements NativeObject {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        ValueLayout.JAVA_INT_UNALIGNED.withName("n"),
        MemoryLayout.sequenceLayout(4L, ValueLayout.JAVA_BYTE) /* padding */,
        ValueLayout.JAVA_LONG_UNALIGNED.withName("l"),
        MemoryLayout.sequenceLayout(16L, ValueLayout.JAVA_BYTE) /* padding */
    );
    public final MemorySegment MEMORY;

    @Override
    public MemorySegment MEMORY() {
        return MEMORY;
    }

    private static final VarHandle nVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("n")
    );

    public int getN() {
        return (int) nVH.get(MEMORY);
    }

    public void setN(int n) {
        nVH.set(MEMORY, n);
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

    public GCCompatibilityStructAlign(MemorySegment MEMORY) {
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
        OFFSET += ValueLayout.JAVA_INT_UNALIGNED.byteSize();
        OFFSET += 4; /* padding */
        OFFSET += ValueLayout.JAVA_LONG_UNALIGNED.byteSize();
        OFFSET += 16; /* padding */
    }

    public GCCompatibilityStructAlign(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT));
    }

    private static final MethodHandle initMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_GCCompatibilityStructAlign_init", MemorySegment.class /* self */);

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

    private static final MethodHandle sizeMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_GCCompatibilityStructAlign_size", MemorySegment.class /* self */);

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
        SB.append("GCCompatibilityStructAlign{\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("n => ");
            SB.append(getN());
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("l => ");
            SB.append(getL());
        }
        SB.append("\n");
        SB.append(" ".repeat(INDENT)).append("}@").append(Long.toString(MEMORY.address(), 16));
    }

    public static class Array extends RefArray<GCCompatibilityStructAlign> {
        public Array(MemorySegment buf) {
            super(buf, GCCompatibilityStructAlign.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            super(allocator, GCCompatibilityStructAlign.LAYOUT, len);
        }

        public Array(PNIBuf buf) {
            super(buf, GCCompatibilityStructAlign.LAYOUT);
        }

        @Override
        protected void elementToString(io.vproxy.pni.test.GCCompatibilityStructAlign ELEM, StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
            ELEM.toString(SB, INDENT, VISITED, CORRUPTED_MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "GCCompatibilityStructAlign.Array";
        }

        @Override
        protected GCCompatibilityStructAlign construct(MemorySegment seg) {
            return new GCCompatibilityStructAlign(seg);
        }

        @Override
        protected MemorySegment getSegment(GCCompatibilityStructAlign value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<GCCompatibilityStructAlign> {
        private Func(io.vproxy.pni.CallSite<GCCompatibilityStructAlign> func) {
            super(func);
        }

        private Func(io.vproxy.pni.CallSite<GCCompatibilityStructAlign> func, Options opts) {
            super(func, opts);
        }

        private Func(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static Func of(io.vproxy.pni.CallSite<GCCompatibilityStructAlign> func) {
            return new Func(func);
        }

        public static Func of(io.vproxy.pni.CallSite<GCCompatibilityStructAlign> func, Options opts) {
            return new Func(func, opts);
        }

        public static Func of(MemorySegment MEMORY) {
            return new Func(MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "GCCompatibilityStructAlign.Func";
        }

        @Override
        protected GCCompatibilityStructAlign construct(MemorySegment seg) {
            return new GCCompatibilityStructAlign(seg);
        }
    }
}
// metadata.generator-version: pni test
// sha256:71465664263ee8e2456a70dd091b0e91609d0ae79d670c215210e2d410198213
