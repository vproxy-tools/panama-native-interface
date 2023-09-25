package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class AlignBaseClass {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        ValueLayout.JAVA_SHORT.withName("a")
    );
    public final MemorySegment MEMORY;

    private static final VarHandle aVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("a")
    );

    public short getA() {
        return (short) aVH.get(MEMORY);
    }

    public void setA(short a) {
        aVH.set(MEMORY, a);
    }

    public AlignBaseClass(MemorySegment MEMORY) {
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
        OFFSET += ValueLayout.JAVA_SHORT_UNALIGNED.byteSize();
    }

    public AlignBaseClass(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT.byteSize()));
    }

    private static final MethodHandle aaaaMH = PanamaUtils.lookupPNICriticalFunction(false, short.class, "JavaCritical_io_vproxy_pni_test_AlignBaseClass_aaaa", MemorySegment.class /* self */);

    public short aaaa() {
        short RESULT;
        try {
            RESULT = (short) aaaaMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle size0MH = PanamaUtils.lookupPNICriticalFunction(false, long.class, "JavaCritical_io_vproxy_pni_test_AlignBaseClass_size0", MemorySegment.class /* self */);

    public long size0() {
        long RESULT;
        try {
            RESULT = (long) size0MH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    public static class Array extends RefArray<AlignBaseClass> {
        public Array(MemorySegment buf) {
            super(buf, AlignBaseClass.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            this(allocator.allocate(AlignBaseClass.LAYOUT.byteSize() * len));
        }

        public Array(PNIBuf buf) {
            this(buf.get());
        }

        @Override
        protected AlignBaseClass construct(MemorySegment seg) {
            return new AlignBaseClass(seg);
        }

        @Override
        protected MemorySegment getSegment(AlignBaseClass value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<AlignBaseClass> {
        private Func(io.vproxy.pni.CallSite<AlignBaseClass> func) {
            super(func);
        }

        private Func(io.vproxy.pni.CallSite<AlignBaseClass> func, Options opts) {
            super(func, opts);
        }

        private Func(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static Func of(io.vproxy.pni.CallSite<AlignBaseClass> func) {
            return new Func(func);
        }

        public static Func of(io.vproxy.pni.CallSite<AlignBaseClass> func, Options opts) {
            return new Func(func, opts);
        }

        public static Func of(MemorySegment MEMORY) {
            return new Func(MEMORY);
        }

        @Override
        protected AlignBaseClass construct(MemorySegment seg) {
            return new AlignBaseClass(seg);
        }
    }
}
// metadata.generator-version: pni test
// sha256:ca75321e27763fac9ff43da414d04204954d809ee2651ab703d3f9ad954ff8e6
