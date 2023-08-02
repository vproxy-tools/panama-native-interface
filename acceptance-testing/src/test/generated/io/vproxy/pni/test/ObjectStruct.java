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

    public String getStr() {
        var SEG = (MemorySegment) strVH.get(MEMORY);
        if (SEG.address() == 0) return null;
        return SEG.reinterpret(Integer.MAX_VALUE).getUtf8String(0);
    }

    public void setStr(String str, Allocator ALLOCATOR) {
        strVH.set(MEMORY, PanamaUtils.format(str, ALLOCATOR));
    }

    public void setStr(MemorySegment str) {
        if (str == null) {
            strVH.set(MEMORY, MemorySegment.NULL);
        } else {
            strVH.set(MEMORY, str);
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

    private final MethodHandle func1 = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_ObjectStruct_func1", MemorySegment.class /* self */, String.class /* str */, String.class /* str2 */, MemorySegment.class /* seg */, PNIBuf.class /* buf */);

    public void func1(PNIEnv ENV, String str, String str2, MemorySegment seg, ByteBuffer buf) {
        ENV.reset();
        try (var ARENA = Arena.ofConfined()) {
            int ERR;
            try {
                ERR = (int) this.func1.invokeExact(ENV.MEMORY, MEMORY, PanamaUtils.format(str, ARENA), PanamaUtils.format(str2, ARENA), seg, PanamaUtils.format(buf, ARENA));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (ERR != 0) {
                ENV.throwLast();
            }
        }
    }

    private final MethodHandle func1Critical = PanamaUtils.lookupPNICriticalFunction(false, void.class, "JavaCritical_io_vproxy_pni_test_ObjectStruct_func1Critical", MemorySegment.class /* self */, String.class /* str */, String.class /* str2 */, MemorySegment.class /* seg */, PNIBuf.class /* buf */);

    public void func1Critical(String str, String str2, MemorySegment seg, ByteBuffer buf) {
        try (var ARENA = Arena.ofConfined()) {
            try {
                this.func1Critical.invokeExact(MEMORY, PanamaUtils.format(str, ARENA), PanamaUtils.format(str2, ARENA), seg, PanamaUtils.format(buf, ARENA));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
        }
    }

    private final MethodHandle retrieveStr = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_ObjectStruct_retrieveStr", MemorySegment.class /* self */);

    public String retrieveStr(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.retrieveStr.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RESULT = ENV.returnPointer();
        return RESULT == null ? null : RESULT.reinterpret(Integer.MAX_VALUE).getUtf8String(0);
    }

    private final MethodHandle retrieveStrCritical = PanamaUtils.lookupPNICriticalFunction(false, String.class, "JavaCritical_io_vproxy_pni_test_ObjectStruct_retrieveStrCritical", MemorySegment.class /* self */);

    public String retrieveStrCritical() {
        MemorySegment RESULT;
        try {
            RESULT = (MemorySegment) this.retrieveStrCritical.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (RESULT.address() == 0) RESULT = null;        return RESULT == null ? null : RESULT.reinterpret(Integer.MAX_VALUE).getUtf8String(0);
    }

    private final MethodHandle retrieveLenStr = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_ObjectStruct_retrieveLenStr", MemorySegment.class /* self */);

    public String retrieveLenStr(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.retrieveLenStr.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RESULT = ENV.returnPointer();
        return RESULT == null ? null : RESULT.reinterpret(Integer.MAX_VALUE).getUtf8String(0);
    }

    private final MethodHandle retrieveLenStrCritical = PanamaUtils.lookupPNICriticalFunction(false, String.class, "JavaCritical_io_vproxy_pni_test_ObjectStruct_retrieveLenStrCritical", MemorySegment.class /* self */);

    public String retrieveLenStrCritical() {
        MemorySegment RESULT;
        try {
            RESULT = (MemorySegment) this.retrieveLenStrCritical.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (RESULT.address() == 0) RESULT = null;        return RESULT == null ? null : RESULT.reinterpret(Integer.MAX_VALUE).getUtf8String(0);
    }

    private final MethodHandle retrieveSeg = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_ObjectStruct_retrieveSeg", MemorySegment.class /* self */);

    public MemorySegment retrieveSeg(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.retrieveSeg.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnPointer();
    }

    private final MethodHandle retrieveSegCritical = PanamaUtils.lookupPNICriticalFunction(false, MemorySegment.class, "JavaCritical_io_vproxy_pni_test_ObjectStruct_retrieveSegCritical", MemorySegment.class /* self */);

    public MemorySegment retrieveSegCritical() {
        MemorySegment RESULT;
        try {
            RESULT = (MemorySegment) this.retrieveSegCritical.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (RESULT.address() == 0) RESULT = null;        return RESULT;
    }

    private final MethodHandle retrieveBuf = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_ObjectStruct_retrieveBuf", MemorySegment.class /* self */);

    public ByteBuffer retrieveBuf(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.retrieveBuf.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RESULT = ENV.returnPointer();
        return PNIBuf.getByteBuffer(RESULT);
    }

    private final MethodHandle retrieveBufCritical = PanamaUtils.lookupPNICriticalFunction(false, PNIBuf.class, "JavaCritical_io_vproxy_pni_test_ObjectStruct_retrieveBufCritical", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public ByteBuffer retrieveBufCritical() {
        try (var ARENA = Arena.ofConfined()) {
            MemorySegment RESULT;
            try {
                RESULT = (MemorySegment) this.retrieveBufCritical.invokeExact(MEMORY, ARENA.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (RESULT.address() == 0) RESULT = null;            return PNIBuf.getByteBuffer(RESULT);
        }
    }

    private final MethodHandle checkPointerSetToNonNull = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_ObjectStruct_checkPointerSetToNonNull", MemorySegment.class /* self */);

    public boolean checkPointerSetToNonNull(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.checkPointerSetToNonNull.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnBool();
    }

    private final MethodHandle checkPointerSetToNonNullCritical = PanamaUtils.lookupPNICriticalFunction(false, boolean.class, "JavaCritical_io_vproxy_pni_test_ObjectStruct_checkPointerSetToNonNullCritical", MemorySegment.class /* self */);

    public boolean checkPointerSetToNonNullCritical() {
        boolean RESULT;
        try {
            RESULT = (boolean) this.checkPointerSetToNonNullCritical.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private final MethodHandle checkPointerSetToNull = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_ObjectStruct_checkPointerSetToNull", MemorySegment.class /* self */);

    public boolean checkPointerSetToNull(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.checkPointerSetToNull.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnBool();
    }

    private final MethodHandle checkPointerSetToNullCritical = PanamaUtils.lookupPNICriticalFunction(false, boolean.class, "JavaCritical_io_vproxy_pni_test_ObjectStruct_checkPointerSetToNullCritical", MemorySegment.class /* self */);

    public boolean checkPointerSetToNullCritical() {
        boolean RESULT;
        try {
            RESULT = (boolean) this.checkPointerSetToNullCritical.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    public static class Array extends RefArray<ObjectStruct> {
        public Array(MemorySegment buf) {
            super(buf, ObjectStruct.LAYOUT);
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

        public static Func of(io.vproxy.pni.CallSite<ObjectStruct> func) {
            return new Func(func);
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
}
// sha256:74abe719f2be9161a7bfb2bca2d8496ff2a38981fccefb4dd28c4fa8efc875ad
