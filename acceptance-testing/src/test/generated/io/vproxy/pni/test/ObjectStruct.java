package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class ObjectStruct {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        ValueLayout.ADDRESS_UNALIGNED.withName("str"),
        MemoryLayout.sequenceLayout(16L, ValueLayout.JAVA_BYTE).withName("lenStr"),
        ValueLayout.ADDRESS_UNALIGNED.withName("seg"),
        PNIBuf.LAYOUT.withName("buf")
    );
    public final MemorySegment MEMORY;

    private static final VarHandle strVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("str")
    );

    public PNIString getStr() {
        var SEG = (MemorySegment) strVH.get(MEMORY);
        if (SEG.address() == 0) return null;
        return new PNIString(SEG);
    }

    public void setStr(String str, Allocator ALLOCATOR) {
        this.setStr(new PNIString(ALLOCATOR, str));
    }

    public void setStr(PNIString str) {
        if (str == null) {
            strVH.set(MEMORY, MemorySegment.NULL);
        } else {
            strVH.set(MEMORY, str.MEMORY);
        }
    }

    private final MemorySegment lenStr;

    public String getLenStr() {
        return lenStr.getUtf8String(0);
    }

    public void setLenStr(String lenStr) {
        this.lenStr.setUtf8String(0, lenStr);
    }

    private static final VarHandle segVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("seg")
    );

    public MemorySegment getSeg() {
        var SEG = (MemorySegment) segVH.get(MEMORY);
        if (SEG.address() == 0) return null;
        return SEG;
    }

    public void setSeg(MemorySegment seg) {
        if (seg == null) {
            segVH.set(MEMORY, MemorySegment.NULL);
        } else {
            segVH.set(MEMORY, seg);
        }
    }

    private final PNIBuf buf;

    public ByteBuffer getBuf() {
        var SEG = this.buf.get();
        if (SEG == null) return null;
        return SEG.asByteBuffer();
    }

    public void setBuf(ByteBuffer buf) {
        if (buf == null) {
            this.buf.setToNull();
        } else {
            this.buf.set(buf);
        }
    }

    public ObjectStruct(MemorySegment MEMORY) {
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
        OFFSET += 8;
        this.lenStr = MEMORY.asSlice(OFFSET, 16);
        OFFSET += 16;
        OFFSET += ValueLayout.ADDRESS_UNALIGNED.byteSize();
        this.buf = new PNIBuf(MEMORY.asSlice(OFFSET, PNIBuf.LAYOUT.byteSize()));
        OFFSET += PNIBuf.LAYOUT.byteSize();
    }

    public ObjectStruct(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT.byteSize()));
    }

    private static final MethodHandle func1MH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_ObjectStruct_func1", MemorySegment.class /* self */, String.class /* str */, String.class /* str2 */, MemorySegment.class /* seg */, PNIBuf.class /* buf */);

    public void func1(PNIEnv ENV, PNIString str, PNIString str2, MemorySegment seg, ByteBuffer buf) {
        ENV.reset();
        try (var POOLED = Allocator.ofPooled()) {
            int ERR;
            try {
                ERR = (int) func1MH.invokeExact(ENV.MEMORY, MEMORY, (MemorySegment) (str == null ? MemorySegment.NULL : str.MEMORY), (MemorySegment) (str2 == null ? MemorySegment.NULL : str2.MEMORY), (MemorySegment) (seg == null ? MemorySegment.NULL : seg), PanamaUtils.format(buf, POOLED));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (ERR != 0) {
                ENV.throwLast();
            }
        }
    }

    private static final MethodHandle func1CriticalMH = PanamaUtils.lookupPNICriticalFunction(false, void.class, "JavaCritical_io_vproxy_pni_test_ObjectStruct_func1Critical", MemorySegment.class /* self */, String.class /* str */, String.class /* str2 */, MemorySegment.class /* seg */, PNIBuf.class /* buf */);

    public void func1Critical(PNIString str, PNIString str2, MemorySegment seg, ByteBuffer buf) {
        try (var POOLED = Allocator.ofPooled()) {
            try {
                func1CriticalMH.invokeExact(MEMORY, (MemorySegment) (str == null ? MemorySegment.NULL : str.MEMORY), (MemorySegment) (str2 == null ? MemorySegment.NULL : str2.MEMORY), (MemorySegment) (seg == null ? MemorySegment.NULL : seg), PanamaUtils.format(buf, POOLED));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
        }
    }

    private static final MethodHandle retrieveStrMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_ObjectStruct_retrieveStr", MemorySegment.class /* self */);

    public PNIString retrieveStr(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) retrieveStrMH.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RESULT = ENV.returnPointer();
        return RESULT == null ? null : new PNIString(RESULT);
    }

    private static final MethodHandle retrieveStrCriticalMH = PanamaUtils.lookupPNICriticalFunction(false, String.class, "JavaCritical_io_vproxy_pni_test_ObjectStruct_retrieveStrCritical", MemorySegment.class /* self */);

    public PNIString retrieveStrCritical() {
        MemorySegment RESULT;
        try {
            RESULT = (MemorySegment) retrieveStrCriticalMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT.address() == 0 ? null : new PNIString(RESULT);
    }

    private static final MethodHandle retrieveLenStrMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_ObjectStruct_retrieveLenStr", MemorySegment.class /* self */);

    public PNIString retrieveLenStr(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) retrieveLenStrMH.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RESULT = ENV.returnPointer();
        return RESULT == null ? null : new PNIString(RESULT);
    }

    private static final MethodHandle retrieveLenStrCriticalMH = PanamaUtils.lookupPNICriticalFunction(false, String.class, "JavaCritical_io_vproxy_pni_test_ObjectStruct_retrieveLenStrCritical", MemorySegment.class /* self */);

    public PNIString retrieveLenStrCritical() {
        MemorySegment RESULT;
        try {
            RESULT = (MemorySegment) retrieveLenStrCriticalMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT.address() == 0 ? null : new PNIString(RESULT);
    }

    private static final MethodHandle retrieveSegMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_ObjectStruct_retrieveSeg", MemorySegment.class /* self */);

    public MemorySegment retrieveSeg(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) retrieveSegMH.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnPointer();
    }

    private static final MethodHandle retrieveSegCriticalMH = PanamaUtils.lookupPNICriticalFunction(false, MemorySegment.class, "JavaCritical_io_vproxy_pni_test_ObjectStruct_retrieveSegCritical", MemorySegment.class /* self */);

    public MemorySegment retrieveSegCritical() {
        MemorySegment RESULT;
        try {
            RESULT = (MemorySegment) retrieveSegCriticalMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (RESULT.address() == 0) return null;
        return RESULT;
    }

    private static final MethodHandle retrieveBufMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_ObjectStruct_retrieveBuf", MemorySegment.class /* self */);

    public ByteBuffer retrieveBuf(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) retrieveBufMH.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RES_SEG = ENV.returnBuf();
        return RES_SEG.toByteBuffer();
    }

    private static final MethodHandle retrieveBufCriticalMH = PanamaUtils.lookupPNICriticalFunction(false, PNIBuf.class, "JavaCritical_io_vproxy_pni_test_ObjectStruct_retrieveBufCritical", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public ByteBuffer retrieveBufCritical() {
        try (var POOLED = Allocator.ofPooled()) {
            MemorySegment RESULT;
            try {
                RESULT = (MemorySegment) retrieveBufCriticalMH.invokeExact(MEMORY, POOLED.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (RESULT.address() == 0) return null;
            var RES_SEG = new PNIBuf(RESULT);
            return RES_SEG.toByteBuffer();
        }
    }

    private static final MethodHandle checkPointerSetToNonNullMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_ObjectStruct_checkPointerSetToNonNull", MemorySegment.class /* self */);

    public boolean checkPointerSetToNonNull(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) checkPointerSetToNonNullMH.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnBool();
    }

    private static final MethodHandle checkPointerSetToNonNullCriticalMH = PanamaUtils.lookupPNICriticalFunction(false, boolean.class, "JavaCritical_io_vproxy_pni_test_ObjectStruct_checkPointerSetToNonNullCritical", MemorySegment.class /* self */);

    public boolean checkPointerSetToNonNullCritical() {
        boolean RESULT;
        try {
            RESULT = (boolean) checkPointerSetToNonNullCriticalMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle checkPointerSetToNullMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_ObjectStruct_checkPointerSetToNull", MemorySegment.class /* self */);

    public boolean checkPointerSetToNull(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) checkPointerSetToNullMH.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnBool();
    }

    private static final MethodHandle checkPointerSetToNullCriticalMH = PanamaUtils.lookupPNICriticalFunction(false, boolean.class, "JavaCritical_io_vproxy_pni_test_ObjectStruct_checkPointerSetToNullCritical", MemorySegment.class /* self */);

    public boolean checkPointerSetToNullCritical() {
        boolean RESULT;
        try {
            RESULT = (boolean) checkPointerSetToNullCriticalMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    public static class Array extends RefArray<ObjectStruct> {
        public Array(MemorySegment buf) {
            super(buf, ObjectStruct.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            this(allocator.allocate(ObjectStruct.LAYOUT.byteSize() * len));
        }

        public Array(PNIBuf buf) {
            this(buf.get());
        }

        @Override
        protected ObjectStruct construct(MemorySegment seg) {
            return new ObjectStruct(seg);
        }

        @Override
        protected MemorySegment getSegment(ObjectStruct value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<ObjectStruct> {
        private Func(io.vproxy.pni.CallSite<ObjectStruct> func) {
            super(func);
        }

        private Func(io.vproxy.pni.CallSite<ObjectStruct> func, Options opts) {
            super(func, opts);
        }

        private Func(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static Func of(io.vproxy.pni.CallSite<ObjectStruct> func) {
            return new Func(func);
        }

        public static Func of(io.vproxy.pni.CallSite<ObjectStruct> func, Options opts) {
            return new Func(func, opts);
        }

        public static Func of(MemorySegment MEMORY) {
            return new Func(MEMORY);
        }

        @Override
        protected ObjectStruct construct(MemorySegment seg) {
            return new ObjectStruct(seg);
        }
    }
}
// metadata.generator-version: pni test
// sha256:2c1f359d774e400fc934c4a9d05af7ad45f0c0f341361d2c11385a7752590bb1
