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

public class StructD extends AbstractNativeObject implements NativeObject {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        ValueLayout.JAVA_INT_UNALIGNED.withName("n"),
        MemoryLayout.sequenceLayout(4L, ValueLayout.JAVA_BYTE) /* padding */,
        ValueLayout.JAVA_DOUBLE_UNALIGNED.withName("d")
    );
    public final MemorySegment MEMORY;

    @Override
    public MemorySegment MEMORY() {
        return MEMORY;
    }

    private static final VarHandleW nVH = VarHandleW.of(
        LAYOUT.varHandle(
            MemoryLayout.PathElement.groupElement("n")
        )
    );

    public int getN() {
        return nVH.getInt(MEMORY);
    }

    public void setN(int n) {
        nVH.set(MEMORY, n);
    }

    private static final VarHandleW dVH = VarHandleW.of(
        LAYOUT.varHandle(
            MemoryLayout.PathElement.groupElement("d")
        )
    );

    public double getD() {
        return dVH.getDouble(MEMORY);
    }

    public void setD(double d) {
        dVH.set(MEMORY, d);
    }

    public StructD(MemorySegment MEMORY) {
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
        OFFSET += ValueLayout.JAVA_INT_UNALIGNED.byteSize();
        OFFSET += 4; /* padding */
        OFFSET += ValueLayout.JAVA_DOUBLE_UNALIGNED.byteSize();
    }

    public StructD(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT));
    }

    @Override
    public void toString(StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
        if (!VISITED.add(new NativeObjectTuple(this))) {
            SB.append("<...>@").append(Long.toString(MEMORY.address(), 16));
            return;
        }
        SB.append("StructD{\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("n => ");
            SB.append(getN());
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("d => ");
            SB.append(getD());
        }
        SB.append("\n");
        SB.append(" ".repeat(INDENT)).append("}@").append(Long.toString(MEMORY.address(), 16));
    }

    public static class Array extends RefArray<StructD> {
        public Array(MemorySegment buf) {
            super(buf, StructD.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            super(allocator, StructD.LAYOUT, len);
        }

        public Array(PNIBuf buf) {
            super(buf, StructD.LAYOUT);
        }

        @Override
        protected void elementToString(io.vproxy.pni.test.StructD ELEM, StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
            ELEM.toString(SB, INDENT, VISITED, CORRUPTED_MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "StructD.Array";
        }

        @Override
        protected StructD construct(MemorySegment seg) {
            return new StructD(seg);
        }

        @Override
        protected MemorySegment getSegment(StructD value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<StructD> {
        private Func(io.vproxy.pni.CallSite<StructD> func) {
            super(func);
        }

        private Func(io.vproxy.pni.CallSite<StructD> func, Options opts) {
            super(func, opts);
        }

        private Func(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static Func of(io.vproxy.pni.CallSite<StructD> func) {
            return new Func(func);
        }

        public static Func of(io.vproxy.pni.CallSite<StructD> func, Options opts) {
            return new Func(func, opts);
        }

        public static Func of(MemorySegment MEMORY) {
            return new Func(MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "StructD.Func";
        }

        @Override
        protected StructD construct(MemorySegment seg) {
            return new StructD(seg);
        }
    }
}
// metadata.generator-version: pni test
// sha256:a84495ae08eea925e8ff2b9e672b98c26d5a3b15a4093b16836ea6850730f61b
