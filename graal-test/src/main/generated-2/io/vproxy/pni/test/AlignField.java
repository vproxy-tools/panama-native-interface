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

public class AlignField extends AbstractNativeObject implements NativeObject {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        ValueLayout.JAVA_BYTE.withName("a"),
        MemoryLayout.sequenceLayout(1L, ValueLayout.JAVA_BYTE) /* padding */,
        ValueLayout.JAVA_BYTE.withName("b"),
        MemoryLayout.sequenceLayout(1L, ValueLayout.JAVA_BYTE) /* padding */,
        ValueLayout.JAVA_INT.withName("c")
    ).withByteAlignment(4);
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

    private static final VarHandleW bVH = VarHandleW.of(
        LAYOUT.varHandle(
            MemoryLayout.PathElement.groupElement("b")
        )
    );

    public byte getB() {
        return bVH.getByte(MEMORY);
    }

    public void setB(byte b) {
        bVH.set(MEMORY, b);
    }

    private static final VarHandleW cVH = VarHandleW.of(
        LAYOUT.varHandle(
            MemoryLayout.PathElement.groupElement("c")
        )
    );

    public int getC() {
        return cVH.getInt(MEMORY);
    }

    public void setC(int c) {
        cVH.set(MEMORY, c);
    }

    public AlignField(MemorySegment MEMORY) {
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
        OFFSET += ValueLayout.JAVA_BYTE.byteSize();
        OFFSET += 1; /* padding */
        OFFSET += ValueLayout.JAVA_BYTE.byteSize();
        OFFSET += 1; /* padding */
        OFFSET += ValueLayout.JAVA_INT_UNALIGNED.byteSize();
    }

    public AlignField(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT));
    }

    private static final MethodHandle aaaaMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), byte.class, "JavaCritical_io_vproxy_pni_test_AlignField_aaaa", MemorySegment.class /* self */);

    public byte aaaa() {
        byte RESULT;
        try {
            RESULT = (byte) aaaaMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle bbbbMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), byte.class, "JavaCritical_io_vproxy_pni_test_AlignField_bbbb", MemorySegment.class /* self */);

    public byte bbbb() {
        byte RESULT;
        try {
            RESULT = (byte) bbbbMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle ccccMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), int.class, "JavaCritical_io_vproxy_pni_test_AlignField_cccc", MemorySegment.class /* self */);

    public int cccc() {
        int RESULT;
        try {
            RESULT = (int) ccccMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle sizeMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), long.class, "JavaCritical_io_vproxy_pni_test_AlignField_size", MemorySegment.class /* self */);

    public long size() {
        long RESULT;
        try {
            RESULT = (long) sizeMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    @Override
    public void toString(StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
        if (!VISITED.add(new NativeObjectTuple(this))) {
            SB.append("<...>@").append(Long.toString(MEMORY.address(), 16));
            return;
        }
        SB.append("AlignField{\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("a => ");
            SB.append(getA());
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("b => ");
            SB.append(getB());
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("c => ");
            SB.append(getC());
        }
        SB.append("\n");
        SB.append(" ".repeat(INDENT)).append("}@").append(Long.toString(MEMORY.address(), 16));
    }

    public static class Array extends RefArray<AlignField> {
        public Array(MemorySegment buf) {
            super(buf, AlignField.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            super(allocator, AlignField.LAYOUT, len);
        }

        public Array(PNIBuf buf) {
            super(buf, AlignField.LAYOUT);
        }

        @Override
        protected void elementToString(io.vproxy.pni.test.AlignField ELEM, StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
            ELEM.toString(SB, INDENT, VISITED, CORRUPTED_MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "AlignField.Array";
        }

        @Override
        protected AlignField construct(MemorySegment seg) {
            return new AlignField(seg);
        }

        @Override
        protected MemorySegment getSegment(AlignField value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<AlignField> {
        private Func(io.vproxy.pni.CallSite<AlignField> func) {
            super(func);
        }

        private Func(io.vproxy.pni.CallSite<AlignField> func, Options opts) {
            super(func, opts);
        }

        private Func(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static Func of(io.vproxy.pni.CallSite<AlignField> func) {
            return new Func(func);
        }

        public static Func of(io.vproxy.pni.CallSite<AlignField> func, Options opts) {
            return new Func(func, opts);
        }

        public static Func of(MemorySegment MEMORY) {
            return new Func(MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "AlignField.Func";
        }

        @Override
        protected AlignField construct(MemorySegment seg) {
            return new AlignField(seg);
        }
    }
}
// metadata.generator-version: pni test
// sha256:65f0a1438c26f6013556e42763e167bd2b8f01058773367a13f5ac9fdc786358
