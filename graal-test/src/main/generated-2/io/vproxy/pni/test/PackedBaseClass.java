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

public class PackedBaseClass extends AbstractNativeObject implements NativeObject {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        ValueLayout.JAVA_BYTE.withName("a"),
        ValueLayout.JAVA_SHORT_UNALIGNED.withName("b")
    );
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

    public short getB() {
        return bVH.getShort(MEMORY);
    }

    public void setB(short b) {
        bVH.set(MEMORY, b);
    }

    public PackedBaseClass(MemorySegment MEMORY) {
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
        OFFSET += ValueLayout.JAVA_BYTE.byteSize();
        OFFSET += ValueLayout.JAVA_SHORT_UNALIGNED.byteSize();
    }

    public PackedBaseClass(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT));
    }

    private static final MethodHandle aaaMH = PanamaUtils.lookupPNIFunction(new PNILinkOptions(), "Java_io_vproxy_pni_test_PackedBaseClass_aaa", MemorySegment.class /* self */, byte.class /* a */);

    public void aaa(PNIEnv ENV, byte a) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) aaaMH.invokeExact(ENV.MEMORY, MEMORY, a);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
    }

    private static final MethodHandle bbbMH = PanamaUtils.lookupPNIFunction(new PNILinkOptions(), "Java_io_vproxy_pni_test_PackedBaseClass_bbb", MemorySegment.class /* self */, short.class /* b */);

    public void bbb(PNIEnv ENV, short b) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) bbbMH.invokeExact(ENV.MEMORY, MEMORY, b);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
    }

    @Override
    public void toString(StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
        if (!VISITED.add(new NativeObjectTuple(this))) {
            SB.append("<...>@").append(Long.toString(MEMORY.address(), 16));
            return;
        }
        SB.append("PackedBaseClass{\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("a => ");
            SB.append(getA());
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("b => ");
            SB.append(getB());
        }
        SB.append("\n");
        SB.append(" ".repeat(INDENT)).append("}@").append(Long.toString(MEMORY.address(), 16));
    }

    public static class Array extends RefArray<PackedBaseClass> {
        public Array(MemorySegment buf) {
            super(buf, PackedBaseClass.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            super(allocator, PackedBaseClass.LAYOUT, len);
        }

        public Array(PNIBuf buf) {
            super(buf, PackedBaseClass.LAYOUT);
        }

        @Override
        protected void elementToString(io.vproxy.pni.test.PackedBaseClass ELEM, StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
            ELEM.toString(SB, INDENT, VISITED, CORRUPTED_MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "PackedBaseClass.Array";
        }

        @Override
        protected PackedBaseClass construct(MemorySegment seg) {
            return new PackedBaseClass(seg);
        }

        @Override
        protected MemorySegment getSegment(PackedBaseClass value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<PackedBaseClass> {
        private Func(io.vproxy.pni.CallSite<PackedBaseClass> func) {
            super(func);
        }

        private Func(io.vproxy.pni.CallSite<PackedBaseClass> func, Options opts) {
            super(func, opts);
        }

        private Func(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static Func of(io.vproxy.pni.CallSite<PackedBaseClass> func) {
            return new Func(func);
        }

        public static Func of(io.vproxy.pni.CallSite<PackedBaseClass> func, Options opts) {
            return new Func(func, opts);
        }

        public static Func of(MemorySegment MEMORY) {
            return new Func(MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "PackedBaseClass.Func";
        }

        @Override
        protected PackedBaseClass construct(MemorySegment seg) {
            return new PackedBaseClass(seg);
        }
    }
}
// metadata.generator-version: pni test
// sha256:061e72ab926d22e315cf9335e4104360bae036cd668221868a20944ac09b6490
