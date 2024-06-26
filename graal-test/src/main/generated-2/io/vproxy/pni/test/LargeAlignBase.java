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

public class LargeAlignBase extends AbstractNativeObject implements NativeObject {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        ValueLayout.JAVA_LONG_UNALIGNED.withName("x")
    );
    public final MemorySegment MEMORY;

    @Override
    public MemorySegment MEMORY() {
        return MEMORY;
    }

    private static final VarHandleW xVH = VarHandleW.of(
        LAYOUT.varHandle(
            MemoryLayout.PathElement.groupElement("x")
        )
    );

    public long getX() {
        return xVH.getLong(MEMORY);
    }

    public void setX(long x) {
        xVH.set(MEMORY, x);
    }

    public LargeAlignBase(MemorySegment MEMORY) {
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
        OFFSET += ValueLayout.JAVA_LONG_UNALIGNED.byteSize();
    }

    public LargeAlignBase(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT));
    }

    @Override
    public void toString(StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
        if (!VISITED.add(new NativeObjectTuple(this))) {
            SB.append("<...>@").append(Long.toString(MEMORY.address(), 16));
            return;
        }
        SB.append("LargeAlignBase{\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("x => ");
            SB.append(getX());
        }
        SB.append("\n");
        SB.append(" ".repeat(INDENT)).append("}@").append(Long.toString(MEMORY.address(), 16));
    }

    public static class Array extends RefArray<LargeAlignBase> {
        public Array(MemorySegment buf) {
            super(buf, LargeAlignBase.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            super(allocator, LargeAlignBase.LAYOUT, len);
        }

        public Array(PNIBuf buf) {
            super(buf, LargeAlignBase.LAYOUT);
        }

        @Override
        protected void elementToString(io.vproxy.pni.test.LargeAlignBase ELEM, StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
            ELEM.toString(SB, INDENT, VISITED, CORRUPTED_MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "LargeAlignBase.Array";
        }

        @Override
        protected LargeAlignBase construct(MemorySegment seg) {
            return new LargeAlignBase(seg);
        }

        @Override
        protected MemorySegment getSegment(LargeAlignBase value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<LargeAlignBase> {
        private Func(io.vproxy.pni.CallSite<LargeAlignBase> func) {
            super(func);
        }

        private Func(io.vproxy.pni.CallSite<LargeAlignBase> func, Options opts) {
            super(func, opts);
        }

        private Func(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static Func of(io.vproxy.pni.CallSite<LargeAlignBase> func) {
            return new Func(func);
        }

        public static Func of(io.vproxy.pni.CallSite<LargeAlignBase> func, Options opts) {
            return new Func(func, opts);
        }

        public static Func of(MemorySegment MEMORY) {
            return new Func(MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "LargeAlignBase.Func";
        }

        @Override
        protected LargeAlignBase construct(MemorySegment seg) {
            return new LargeAlignBase(seg);
        }
    }
}
// metadata.generator-version: pni test
// sha256:f6557e86b31477fbdc0f49d9fe12fbc7e9824af95c6985ea4eb5a7a77ba729ea
