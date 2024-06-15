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

public class UnionP extends AbstractNativeObject implements NativeObject {
    public static final MemoryLayout LAYOUT = MemoryLayout.unionLayout(
        ValueLayout.JAVA_INT_UNALIGNED.withName("i"),
        ValueLayout.JAVA_LONG_UNALIGNED.withName("l")
    );
    public final MemorySegment MEMORY;

    @Override
    public MemorySegment MEMORY() {
        return MEMORY;
    }

    private static final VarHandleW iVH = VarHandleW.of(
        LAYOUT.varHandle(
            MemoryLayout.PathElement.groupElement("i")
        )
    );

    public int getI() {
        return iVH.getInt(MEMORY);
    }

    public void setI(int i) {
        iVH.set(MEMORY, i);
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

    public UnionP(MemorySegment MEMORY) {
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
        OFFSET += ValueLayout.JAVA_INT_UNALIGNED.byteSize();
        OFFSET = 0;
        OFFSET += ValueLayout.JAVA_LONG_UNALIGNED.byteSize();
        OFFSET = 0;
    }

    public UnionP(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT));
    }

    private static final MethodHandle retrieveIMH = PanamaUtils.lookupPNIFunction(new PNILinkOptions().setCritical(true), "UnionP_retrieve_i", MemorySegment.class /* self */);

    public int retrieveI(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) retrieveIMH.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnInt();
    }

    private static final MethodHandle retrieveLMH = PanamaUtils.lookupPNIFunction(new PNILinkOptions().setCritical(true), "UnionP_retrieve_l", MemorySegment.class /* self */);

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
        CORRUPTED_MEMORY = true;
        SB.append("UnionP(\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("i => ");
            SB.append(getI());
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("l => ");
            SB.append(getL());
        }
        SB.append("\n");
        SB.append(" ".repeat(INDENT)).append(")@").append(Long.toString(MEMORY.address(), 16));
    }

    public static class Array extends RefArray<UnionP> {
        public Array(MemorySegment buf) {
            super(buf, UnionP.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            super(allocator, UnionP.LAYOUT, len);
        }

        public Array(PNIBuf buf) {
            super(buf, UnionP.LAYOUT);
        }

        @Override
        protected void elementToString(io.vproxy.pni.test.UnionP ELEM, StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
            ELEM.toString(SB, INDENT, VISITED, CORRUPTED_MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "UnionP.Array";
        }

        @Override
        protected UnionP construct(MemorySegment seg) {
            return new UnionP(seg);
        }

        @Override
        protected MemorySegment getSegment(UnionP value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<UnionP> {
        private Func(io.vproxy.pni.CallSite<UnionP> func) {
            super(func);
        }

        private Func(io.vproxy.pni.CallSite<UnionP> func, Options opts) {
            super(func, opts);
        }

        private Func(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static Func of(io.vproxy.pni.CallSite<UnionP> func) {
            return new Func(func);
        }

        public static Func of(io.vproxy.pni.CallSite<UnionP> func, Options opts) {
            return new Func(func, opts);
        }

        public static Func of(MemorySegment MEMORY) {
            return new Func(MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "UnionP.Func";
        }

        @Override
        protected UnionP construct(MemorySegment seg) {
            return new UnionP(seg);
        }
    }
}
// metadata.generator-version: pni test
// sha256:4448d491a68c53dcd3ccf7fdda09077b7dedaf40cb982861e6b25e8f6f51583e
