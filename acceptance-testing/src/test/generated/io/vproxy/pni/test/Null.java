package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class Null {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        ValueLayout.ADDRESS_UNALIGNED.withName("o"),
        ValueLayout.ADDRESS_UNALIGNED.withName("str"),
        ValueLayout.ADDRESS_UNALIGNED.withName("seg"),
        PNIBuf.LAYOUT.withName("buf"),
        PNIBuf.LAYOUT.withName("byteArr"),
        PNIBuf.LAYOUT.withName("boolArr"),
        PNIBuf.LAYOUT.withName("charArr"),
        PNIBuf.LAYOUT.withName("floatArr"),
        PNIBuf.LAYOUT.withName("doubleArr"),
        PNIBuf.LAYOUT.withName("intArr"),
        PNIBuf.LAYOUT.withName("longArr"),
        PNIBuf.LAYOUT.withName("shortArr"),
        PNIBuf.LAYOUT.withName("oArr")
    );
    public final MemorySegment MEMORY;

    private static final VarHandle oVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("o")
    );

    public io.vproxy.pni.test.ObjectStruct getO() {
        var SEG = (MemorySegment) oVH.get(MEMORY);
        if (SEG.address() == 0) return null;
        return new io.vproxy.pni.test.ObjectStruct(SEG);
    }

    public void setO(io.vproxy.pni.test.ObjectStruct o) {
        if (o == null) {
            oVH.set(MEMORY, MemorySegment.NULL);
        } else {
            oVH.set(MEMORY, o.MEMORY);
        }
    }

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

    private final PNIBuf byteArr;

    public MemorySegment getByteArr() {
        var SEG = this.byteArr.get();
        if (SEG == null) return null;
        return SEG;
    }

    public void setByteArr(MemorySegment byteArr) {
        if (byteArr == null) {
            this.byteArr.setToNull();
        } else {
            this.byteArr.set(byteArr);
        }
    }

    private final PNIBuf boolArr;

    public BoolArray getBoolArr() {
        var SEG = this.boolArr.get();
        if (SEG == null) return null;
        return new BoolArray(SEG);
    }

    public void setBoolArr(BoolArray boolArr) {
        if (boolArr == null) {
            this.boolArr.setToNull();
        } else {
            this.boolArr.set(boolArr.MEMORY);
        }
    }

    private final PNIBuf charArr;

    public CharArray getCharArr() {
        var SEG = this.charArr.get();
        if (SEG == null) return null;
        return new CharArray(SEG);
    }

    public void setCharArr(CharArray charArr) {
        if (charArr == null) {
            this.charArr.setToNull();
        } else {
            this.charArr.set(charArr.MEMORY);
        }
    }

    private final PNIBuf floatArr;

    public FloatArray getFloatArr() {
        var SEG = this.floatArr.get();
        if (SEG == null) return null;
        return new FloatArray(SEG);
    }

    public void setFloatArr(FloatArray floatArr) {
        if (floatArr == null) {
            this.floatArr.setToNull();
        } else {
            this.floatArr.set(floatArr.MEMORY);
        }
    }

    private final PNIBuf doubleArr;

    public DoubleArray getDoubleArr() {
        var SEG = this.doubleArr.get();
        if (SEG == null) return null;
        return new DoubleArray(SEG);
    }

    public void setDoubleArr(DoubleArray doubleArr) {
        if (doubleArr == null) {
            this.doubleArr.setToNull();
        } else {
            this.doubleArr.set(doubleArr.MEMORY);
        }
    }

    private final PNIBuf intArr;

    public IntArray getIntArr() {
        var SEG = this.intArr.get();
        if (SEG == null) return null;
        return new IntArray(SEG);
    }

    public void setIntArr(IntArray intArr) {
        if (intArr == null) {
            this.intArr.setToNull();
        } else {
            this.intArr.set(intArr.MEMORY);
        }
    }

    private final PNIBuf longArr;

    public LongArray getLongArr() {
        var SEG = this.longArr.get();
        if (SEG == null) return null;
        return new LongArray(SEG);
    }

    public void setLongArr(LongArray longArr) {
        if (longArr == null) {
            this.longArr.setToNull();
        } else {
            this.longArr.set(longArr.MEMORY);
        }
    }

    private final PNIBuf shortArr;

    public ShortArray getShortArr() {
        var SEG = this.shortArr.get();
        if (SEG == null) return null;
        return new ShortArray(SEG);
    }

    public void setShortArr(ShortArray shortArr) {
        if (shortArr == null) {
            this.shortArr.setToNull();
        } else {
            this.shortArr.set(shortArr.MEMORY);
        }
    }

    private final PNIBuf oArr;

    public io.vproxy.pni.test.ObjectStruct.Array getOArr() {
        var SEG = this.oArr.get();
        if (SEG == null) return null;
        return new io.vproxy.pni.test.ObjectStruct.Array(SEG);
    }

    public void setOArr(io.vproxy.pni.test.ObjectStruct.Array oArr) {
        if (oArr == null) {
            this.oArr.setToNull();
        } else {
            this.oArr.set(oArr.MEMORY);
        }
    }

    public Null(MemorySegment MEMORY) {
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
        OFFSET += 8;
        OFFSET += 8;
        OFFSET += ValueLayout.ADDRESS_UNALIGNED.byteSize();
        this.buf = new PNIBuf(MEMORY.asSlice(OFFSET, PNIBuf.LAYOUT.byteSize()));
        OFFSET += PNIBuf.LAYOUT.byteSize();
        this.byteArr = new PNIBuf(MEMORY.asSlice(OFFSET, PNIBuf.LAYOUT.byteSize()));
        OFFSET += PNIBuf.LAYOUT.byteSize();
        this.boolArr = new PNIBuf(MEMORY.asSlice(OFFSET, PNIBuf.LAYOUT.byteSize()));
        OFFSET += PNIBuf.LAYOUT.byteSize();
        this.charArr = new PNIBuf(MEMORY.asSlice(OFFSET, PNIBuf.LAYOUT.byteSize()));
        OFFSET += PNIBuf.LAYOUT.byteSize();
        this.floatArr = new PNIBuf(MEMORY.asSlice(OFFSET, PNIBuf.LAYOUT.byteSize()));
        OFFSET += PNIBuf.LAYOUT.byteSize();
        this.doubleArr = new PNIBuf(MEMORY.asSlice(OFFSET, PNIBuf.LAYOUT.byteSize()));
        OFFSET += PNIBuf.LAYOUT.byteSize();
        this.intArr = new PNIBuf(MEMORY.asSlice(OFFSET, PNIBuf.LAYOUT.byteSize()));
        OFFSET += PNIBuf.LAYOUT.byteSize();
        this.longArr = new PNIBuf(MEMORY.asSlice(OFFSET, PNIBuf.LAYOUT.byteSize()));
        OFFSET += PNIBuf.LAYOUT.byteSize();
        this.shortArr = new PNIBuf(MEMORY.asSlice(OFFSET, PNIBuf.LAYOUT.byteSize()));
        OFFSET += PNIBuf.LAYOUT.byteSize();
        this.oArr = new PNIBuf(MEMORY.asSlice(OFFSET, PNIBuf.LAYOUT.byteSize()));
        OFFSET += PNIBuf.LAYOUT.byteSize();
    }

    public Null(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT.byteSize()));
    }

    private final MethodHandle testParam = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Null_testParam", MemorySegment.class /* self */, io.vproxy.pni.test.ObjectStruct.LAYOUT.getClass() /* o */, String.class /* str */, MemorySegment.class /* seg */, PNIBuf.class /* buf */, PNIBuf.class /* byteArr */, PNIBuf.class /* boolArr */, PNIBuf.class /* charArr */, PNIBuf.class /* floatArr */, PNIBuf.class /* doubleArr */, PNIBuf.class /* intArr */, PNIBuf.class /* longArr */, PNIBuf.class /* shortArr */, PNIBuf.class /* oArr */);

    public boolean testParam(PNIEnv ENV, io.vproxy.pni.test.ObjectStruct o, PNIString str, MemorySegment seg, ByteBuffer buf, MemorySegment byteArr, BoolArray boolArr, CharArray charArr, FloatArray floatArr, DoubleArray doubleArr, IntArray intArr, LongArray longArr, ShortArray shortArr, io.vproxy.pni.test.ObjectStruct.Array oArr) {
        ENV.reset();
        try (var POOLED = Allocator.ofPooled()) {
            int ERR;
            try {
                ERR = (int) this.testParam.invokeExact(ENV.MEMORY, MEMORY, (MemorySegment) (o == null ? MemorySegment.NULL : o.MEMORY), (MemorySegment) (str == null ? MemorySegment.NULL : str.MEMORY), (MemorySegment) (seg == null ? MemorySegment.NULL : seg), PanamaUtils.format(buf, POOLED), PNIBuf.memoryOf(POOLED, byteArr), PNIBuf.memoryOf(POOLED, boolArr), PNIBuf.memoryOf(POOLED, charArr), PNIBuf.memoryOf(POOLED, floatArr), PNIBuf.memoryOf(POOLED, doubleArr), PNIBuf.memoryOf(POOLED, intArr), PNIBuf.memoryOf(POOLED, longArr), PNIBuf.memoryOf(POOLED, shortArr), PNIBuf.memoryOf(POOLED, oArr));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (ERR != 0) {
                ENV.throwLast();
            }
            return ENV.returnBool();
        }
    }

    private final MethodHandle testParamCritical = PanamaUtils.lookupPNICriticalFunction(false, boolean.class, "JavaCritical_io_vproxy_pni_test_Null_testParamCritical", MemorySegment.class /* self */, io.vproxy.pni.test.ObjectStruct.LAYOUT.getClass() /* o */, String.class /* str */, MemorySegment.class /* seg */, PNIBuf.class /* buf */, PNIBuf.class /* byteArr */, PNIBuf.class /* boolArr */, PNIBuf.class /* charArr */, PNIBuf.class /* floatArr */, PNIBuf.class /* doubleArr */, PNIBuf.class /* intArr */, PNIBuf.class /* longArr */, PNIBuf.class /* shortArr */, PNIBuf.class /* oArr */);

    public boolean testParamCritical(io.vproxy.pni.test.ObjectStruct o, PNIString str, MemorySegment seg, ByteBuffer buf, MemorySegment byteArr, BoolArray boolArr, CharArray charArr, FloatArray floatArr, DoubleArray doubleArr, IntArray intArr, LongArray longArr, ShortArray shortArr, io.vproxy.pni.test.ObjectStruct.Array oArr) {
        try (var POOLED = Allocator.ofPooled()) {
            boolean RESULT;
            try {
                RESULT = (boolean) this.testParamCritical.invokeExact(MEMORY, (MemorySegment) (o == null ? MemorySegment.NULL : o.MEMORY), (MemorySegment) (str == null ? MemorySegment.NULL : str.MEMORY), (MemorySegment) (seg == null ? MemorySegment.NULL : seg), PanamaUtils.format(buf, POOLED), PNIBuf.memoryOf(POOLED, byteArr), PNIBuf.memoryOf(POOLED, boolArr), PNIBuf.memoryOf(POOLED, charArr), PNIBuf.memoryOf(POOLED, floatArr), PNIBuf.memoryOf(POOLED, doubleArr), PNIBuf.memoryOf(POOLED, intArr), PNIBuf.memoryOf(POOLED, longArr), PNIBuf.memoryOf(POOLED, shortArr), PNIBuf.memoryOf(POOLED, oArr));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            return RESULT;
        }
    }

    private final MethodHandle testParamRaw = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Null_testParamRaw", MemorySegment.class /* self */, ByteBuffer.class /* buf */, MemorySegment.class /* byteArr */, MemorySegment.class /* boolArr */, MemorySegment.class /* charArr */, MemorySegment.class /* floatArr */, MemorySegment.class /* doubleArr */, MemorySegment.class /* intArr */, MemorySegment.class /* longArr */, MemorySegment.class /* shortArr */, MemorySegment.class /* oArr */);

    public boolean testParamRaw(PNIEnv ENV, ByteBuffer buf, MemorySegment byteArr, BoolArray boolArr, CharArray charArr, FloatArray floatArr, DoubleArray doubleArr, IntArray intArr, LongArray longArr, ShortArray shortArr, io.vproxy.pni.test.ObjectStruct.Array oArr) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.testParamRaw.invokeExact(ENV.MEMORY, MEMORY, PanamaUtils.format(buf), (MemorySegment) (byteArr == null ? MemorySegment.NULL : byteArr), (MemorySegment) (boolArr == null ? MemorySegment.NULL : boolArr.MEMORY), (MemorySegment) (charArr == null ? MemorySegment.NULL : charArr.MEMORY), (MemorySegment) (floatArr == null ? MemorySegment.NULL : floatArr.MEMORY), (MemorySegment) (doubleArr == null ? MemorySegment.NULL : doubleArr.MEMORY), (MemorySegment) (intArr == null ? MemorySegment.NULL : intArr.MEMORY), (MemorySegment) (longArr == null ? MemorySegment.NULL : longArr.MEMORY), (MemorySegment) (shortArr == null ? MemorySegment.NULL : shortArr.MEMORY), (MemorySegment) (oArr == null ? MemorySegment.NULL : oArr.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnBool();
    }

    private final MethodHandle testParamRawCritical = PanamaUtils.lookupPNICriticalFunction(false, boolean.class, "JavaCritical_io_vproxy_pni_test_Null_testParamRawCritical", MemorySegment.class /* self */, ByteBuffer.class /* buf */, MemorySegment.class /* byteArr */, MemorySegment.class /* boolArr */, MemorySegment.class /* charArr */, MemorySegment.class /* floatArr */, MemorySegment.class /* doubleArr */, MemorySegment.class /* intArr */, MemorySegment.class /* longArr */, MemorySegment.class /* shortArr */, MemorySegment.class /* oArr */);

    public boolean testParamRawCritical(ByteBuffer buf, MemorySegment byteArr, BoolArray boolArr, CharArray charArr, FloatArray floatArr, DoubleArray doubleArr, IntArray intArr, LongArray longArr, ShortArray shortArr, io.vproxy.pni.test.ObjectStruct.Array oArr) {
        boolean RESULT;
        try {
            RESULT = (boolean) this.testParamRawCritical.invokeExact(MEMORY, PanamaUtils.format(buf), (MemorySegment) (byteArr == null ? MemorySegment.NULL : byteArr), (MemorySegment) (boolArr == null ? MemorySegment.NULL : boolArr.MEMORY), (MemorySegment) (charArr == null ? MemorySegment.NULL : charArr.MEMORY), (MemorySegment) (floatArr == null ? MemorySegment.NULL : floatArr.MEMORY), (MemorySegment) (doubleArr == null ? MemorySegment.NULL : doubleArr.MEMORY), (MemorySegment) (intArr == null ? MemorySegment.NULL : intArr.MEMORY), (MemorySegment) (longArr == null ? MemorySegment.NULL : longArr.MEMORY), (MemorySegment) (shortArr == null ? MemorySegment.NULL : shortArr.MEMORY), (MemorySegment) (oArr == null ? MemorySegment.NULL : oArr.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private final MethodHandle returnO = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Null_returnO", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public io.vproxy.pni.test.ObjectStruct returnO(PNIEnv ENV, Allocator ALLOCATOR) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.returnO.invokeExact(ENV.MEMORY, MEMORY, ALLOCATOR.allocate(io.vproxy.pni.test.ObjectStruct.LAYOUT.byteSize()));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RESULT = ENV.returnPointer();
        return RESULT == null ? null : new io.vproxy.pni.test.ObjectStruct(RESULT);
    }

    private final MethodHandle returnOCritical = PanamaUtils.lookupPNICriticalFunction(false, io.vproxy.pni.test.ObjectStruct.LAYOUT.getClass(), "JavaCritical_io_vproxy_pni_test_Null_returnOCritical", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public io.vproxy.pni.test.ObjectStruct returnOCritical(Allocator ALLOCATOR) {
        MemorySegment RESULT;
        try {
            RESULT = (MemorySegment) this.returnOCritical.invokeExact(MEMORY, ALLOCATOR.allocate(io.vproxy.pni.test.ObjectStruct.LAYOUT.byteSize()));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (RESULT.address() == 0) return null;
        return RESULT == null ? null : new io.vproxy.pni.test.ObjectStruct(RESULT);
    }

    private final MethodHandle returnStr = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Null_returnStr", MemorySegment.class /* self */);

    public PNIString returnStr(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.returnStr.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RESULT = ENV.returnPointer();
        return RESULT == null ? null : new PNIString(RESULT);
    }

    private final MethodHandle returnStrCritical = PanamaUtils.lookupPNICriticalFunction(false, String.class, "JavaCritical_io_vproxy_pni_test_Null_returnStrCritical", MemorySegment.class /* self */);

    public PNIString returnStrCritical() {
        MemorySegment RESULT;
        try {
            RESULT = (MemorySegment) this.returnStrCritical.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT.address() == 0 ? null : new PNIString(RESULT);
    }

    private final MethodHandle returnSeg = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Null_returnSeg", MemorySegment.class /* self */);

    public MemorySegment returnSeg(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.returnSeg.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnPointer();
    }

    private final MethodHandle returnSegCritical = PanamaUtils.lookupPNICriticalFunction(false, MemorySegment.class, "JavaCritical_io_vproxy_pni_test_Null_returnSegCritical", MemorySegment.class /* self */);

    public MemorySegment returnSegCritical() {
        MemorySegment RESULT;
        try {
            RESULT = (MemorySegment) this.returnSegCritical.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (RESULT.address() == 0) return null;
        return RESULT;
    }

    private final MethodHandle returnBuf = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Null_returnBuf", MemorySegment.class /* self */);

    public ByteBuffer returnBuf(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.returnBuf.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RES_SEG = ENV.returnBuf();
        return RES_SEG.toByteBuffer();
    }

    private final MethodHandle returnBufCritical = PanamaUtils.lookupPNICriticalFunction(false, PNIBuf.class, "JavaCritical_io_vproxy_pni_test_Null_returnBufCritical", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public ByteBuffer returnBufCritical() {
        try (var POOLED = Allocator.ofPooled()) {
            MemorySegment RESULT;
            try {
                RESULT = (MemorySegment) this.returnBufCritical.invokeExact(MEMORY, POOLED.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (RESULT.address() == 0) return null;
            var RES_SEG = new PNIBuf(RESULT);
            return RES_SEG.toByteBuffer();
        }
    }

    private final MethodHandle returnBufCritical2 = PanamaUtils.lookupPNICriticalFunction(false, PNIBuf.class, "JavaCritical_io_vproxy_pni_test_Null_returnBufCritical2", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public ByteBuffer returnBufCritical2() {
        try (var POOLED = Allocator.ofPooled()) {
            MemorySegment RESULT;
            try {
                RESULT = (MemorySegment) this.returnBufCritical2.invokeExact(MEMORY, POOLED.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (RESULT.address() == 0) return null;
            var RES_SEG = new PNIBuf(RESULT);
            return RES_SEG.toByteBuffer();
        }
    }

    private final MethodHandle returnByteArr = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Null_returnByteArr", MemorySegment.class /* self */);

    public MemorySegment returnByteArr(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.returnByteArr.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RES_SEG = ENV.returnBuf();
        if (RES_SEG.isNull()) return null;
        return RES_SEG.get();
    }

    private final MethodHandle returnByteArrCritical = PanamaUtils.lookupPNICriticalFunction(false, PNIBuf.class, "JavaCritical_io_vproxy_pni_test_Null_returnByteArrCritical", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public MemorySegment returnByteArrCritical() {
        try (var POOLED = Allocator.ofPooled()) {
            MemorySegment RESULT;
            try {
                RESULT = (MemorySegment) this.returnByteArrCritical.invokeExact(MEMORY, POOLED.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (RESULT.address() == 0) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return RES_SEG.get();
        }
    }

    private final MethodHandle returnByteArrCritical2 = PanamaUtils.lookupPNICriticalFunction(false, PNIBuf.class, "JavaCritical_io_vproxy_pni_test_Null_returnByteArrCritical2", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public MemorySegment returnByteArrCritical2() {
        try (var POOLED = Allocator.ofPooled()) {
            MemorySegment RESULT;
            try {
                RESULT = (MemorySegment) this.returnByteArrCritical2.invokeExact(MEMORY, POOLED.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (RESULT.address() == 0) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return RES_SEG.get();
        }
    }

    private final MethodHandle returnBoolArr = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Null_returnBoolArr", MemorySegment.class /* self */);

    public BoolArray returnBoolArr(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.returnBoolArr.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RES_SEG = ENV.returnBuf();
        if (RES_SEG.isNull()) return null;
        return new BoolArray(RES_SEG);
    }

    private final MethodHandle returnBoolArrCritical = PanamaUtils.lookupPNICriticalFunction(false, PNIBuf.class, "JavaCritical_io_vproxy_pni_test_Null_returnBoolArrCritical", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public BoolArray returnBoolArrCritical() {
        try (var POOLED = Allocator.ofPooled()) {
            MemorySegment RESULT;
            try {
                RESULT = (MemorySegment) this.returnBoolArrCritical.invokeExact(MEMORY, POOLED.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (RESULT.address() == 0) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return new BoolArray(RES_SEG);
        }
    }

    private final MethodHandle returnBoolArrCritical2 = PanamaUtils.lookupPNICriticalFunction(false, PNIBuf.class, "JavaCritical_io_vproxy_pni_test_Null_returnBoolArrCritical2", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public BoolArray returnBoolArrCritical2() {
        try (var POOLED = Allocator.ofPooled()) {
            MemorySegment RESULT;
            try {
                RESULT = (MemorySegment) this.returnBoolArrCritical2.invokeExact(MEMORY, POOLED.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (RESULT.address() == 0) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return new BoolArray(RES_SEG);
        }
    }

    private final MethodHandle returnCharArr = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Null_returnCharArr", MemorySegment.class /* self */);

    public CharArray returnCharArr(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.returnCharArr.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RES_SEG = ENV.returnBuf();
        if (RES_SEG.isNull()) return null;
        return new CharArray(RES_SEG);
    }

    private final MethodHandle returnCharArrCritical = PanamaUtils.lookupPNICriticalFunction(false, PNIBuf.class, "JavaCritical_io_vproxy_pni_test_Null_returnCharArrCritical", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public CharArray returnCharArrCritical() {
        try (var POOLED = Allocator.ofPooled()) {
            MemorySegment RESULT;
            try {
                RESULT = (MemorySegment) this.returnCharArrCritical.invokeExact(MEMORY, POOLED.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (RESULT.address() == 0) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return new CharArray(RES_SEG);
        }
    }

    private final MethodHandle returnCharArrCritical2 = PanamaUtils.lookupPNICriticalFunction(false, PNIBuf.class, "JavaCritical_io_vproxy_pni_test_Null_returnCharArrCritical2", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public CharArray returnCharArrCritical2() {
        try (var POOLED = Allocator.ofPooled()) {
            MemorySegment RESULT;
            try {
                RESULT = (MemorySegment) this.returnCharArrCritical2.invokeExact(MEMORY, POOLED.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (RESULT.address() == 0) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return new CharArray(RES_SEG);
        }
    }

    private final MethodHandle returnFloatArr = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Null_returnFloatArr", MemorySegment.class /* self */);

    public FloatArray returnFloatArr(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.returnFloatArr.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RES_SEG = ENV.returnBuf();
        if (RES_SEG.isNull()) return null;
        return new FloatArray(RES_SEG);
    }

    private final MethodHandle returnFloatArrCritical = PanamaUtils.lookupPNICriticalFunction(false, PNIBuf.class, "JavaCritical_io_vproxy_pni_test_Null_returnFloatArrCritical", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public FloatArray returnFloatArrCritical() {
        try (var POOLED = Allocator.ofPooled()) {
            MemorySegment RESULT;
            try {
                RESULT = (MemorySegment) this.returnFloatArrCritical.invokeExact(MEMORY, POOLED.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (RESULT.address() == 0) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return new FloatArray(RES_SEG);
        }
    }

    private final MethodHandle returnFloatArrCritical2 = PanamaUtils.lookupPNICriticalFunction(false, PNIBuf.class, "JavaCritical_io_vproxy_pni_test_Null_returnFloatArrCritical2", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public FloatArray returnFloatArrCritical2() {
        try (var POOLED = Allocator.ofPooled()) {
            MemorySegment RESULT;
            try {
                RESULT = (MemorySegment) this.returnFloatArrCritical2.invokeExact(MEMORY, POOLED.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (RESULT.address() == 0) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return new FloatArray(RES_SEG);
        }
    }

    private final MethodHandle returnDoubleArr = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Null_returnDoubleArr", MemorySegment.class /* self */);

    public DoubleArray returnDoubleArr(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.returnDoubleArr.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RES_SEG = ENV.returnBuf();
        if (RES_SEG.isNull()) return null;
        return new DoubleArray(RES_SEG);
    }

    private final MethodHandle returnDoubleArrCritical = PanamaUtils.lookupPNICriticalFunction(false, PNIBuf.class, "JavaCritical_io_vproxy_pni_test_Null_returnDoubleArrCritical", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public DoubleArray returnDoubleArrCritical() {
        try (var POOLED = Allocator.ofPooled()) {
            MemorySegment RESULT;
            try {
                RESULT = (MemorySegment) this.returnDoubleArrCritical.invokeExact(MEMORY, POOLED.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (RESULT.address() == 0) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return new DoubleArray(RES_SEG);
        }
    }

    private final MethodHandle returnDoubleArrCritical2 = PanamaUtils.lookupPNICriticalFunction(false, PNIBuf.class, "JavaCritical_io_vproxy_pni_test_Null_returnDoubleArrCritical2", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public DoubleArray returnDoubleArrCritical2() {
        try (var POOLED = Allocator.ofPooled()) {
            MemorySegment RESULT;
            try {
                RESULT = (MemorySegment) this.returnDoubleArrCritical2.invokeExact(MEMORY, POOLED.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (RESULT.address() == 0) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return new DoubleArray(RES_SEG);
        }
    }

    private final MethodHandle returnIntArr = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Null_returnIntArr", MemorySegment.class /* self */);

    public IntArray returnIntArr(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.returnIntArr.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RES_SEG = ENV.returnBuf();
        if (RES_SEG.isNull()) return null;
        return new IntArray(RES_SEG);
    }

    private final MethodHandle returnIntArrCritical = PanamaUtils.lookupPNICriticalFunction(false, PNIBuf.class, "JavaCritical_io_vproxy_pni_test_Null_returnIntArrCritical", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public IntArray returnIntArrCritical() {
        try (var POOLED = Allocator.ofPooled()) {
            MemorySegment RESULT;
            try {
                RESULT = (MemorySegment) this.returnIntArrCritical.invokeExact(MEMORY, POOLED.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (RESULT.address() == 0) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return new IntArray(RES_SEG);
        }
    }

    private final MethodHandle returnIntArrCritical2 = PanamaUtils.lookupPNICriticalFunction(false, PNIBuf.class, "JavaCritical_io_vproxy_pni_test_Null_returnIntArrCritical2", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public IntArray returnIntArrCritical2() {
        try (var POOLED = Allocator.ofPooled()) {
            MemorySegment RESULT;
            try {
                RESULT = (MemorySegment) this.returnIntArrCritical2.invokeExact(MEMORY, POOLED.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (RESULT.address() == 0) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return new IntArray(RES_SEG);
        }
    }

    private final MethodHandle returnLongArr = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Null_returnLongArr", MemorySegment.class /* self */);

    public LongArray returnLongArr(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.returnLongArr.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RES_SEG = ENV.returnBuf();
        if (RES_SEG.isNull()) return null;
        return new LongArray(RES_SEG);
    }

    private final MethodHandle returnLongArrCritical = PanamaUtils.lookupPNICriticalFunction(false, PNIBuf.class, "JavaCritical_io_vproxy_pni_test_Null_returnLongArrCritical", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public LongArray returnLongArrCritical() {
        try (var POOLED = Allocator.ofPooled()) {
            MemorySegment RESULT;
            try {
                RESULT = (MemorySegment) this.returnLongArrCritical.invokeExact(MEMORY, POOLED.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (RESULT.address() == 0) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return new LongArray(RES_SEG);
        }
    }

    private final MethodHandle returnLongArrCritical2 = PanamaUtils.lookupPNICriticalFunction(false, PNIBuf.class, "JavaCritical_io_vproxy_pni_test_Null_returnLongArrCritical2", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public LongArray returnLongArrCritical2() {
        try (var POOLED = Allocator.ofPooled()) {
            MemorySegment RESULT;
            try {
                RESULT = (MemorySegment) this.returnLongArrCritical2.invokeExact(MEMORY, POOLED.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (RESULT.address() == 0) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return new LongArray(RES_SEG);
        }
    }

    private final MethodHandle returnShortArr = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Null_returnShortArr", MemorySegment.class /* self */);

    public ShortArray returnShortArr(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.returnShortArr.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RES_SEG = ENV.returnBuf();
        if (RES_SEG.isNull()) return null;
        return new ShortArray(RES_SEG);
    }

    private final MethodHandle returnShortArrCritical = PanamaUtils.lookupPNICriticalFunction(false, PNIBuf.class, "JavaCritical_io_vproxy_pni_test_Null_returnShortArrCritical", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public ShortArray returnShortArrCritical() {
        try (var POOLED = Allocator.ofPooled()) {
            MemorySegment RESULT;
            try {
                RESULT = (MemorySegment) this.returnShortArrCritical.invokeExact(MEMORY, POOLED.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (RESULT.address() == 0) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return new ShortArray(RES_SEG);
        }
    }

    private final MethodHandle returnShortArrCritical2 = PanamaUtils.lookupPNICriticalFunction(false, PNIBuf.class, "JavaCritical_io_vproxy_pni_test_Null_returnShortArrCritical2", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public ShortArray returnShortArrCritical2() {
        try (var POOLED = Allocator.ofPooled()) {
            MemorySegment RESULT;
            try {
                RESULT = (MemorySegment) this.returnShortArrCritical2.invokeExact(MEMORY, POOLED.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (RESULT.address() == 0) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return new ShortArray(RES_SEG);
        }
    }

    private final MethodHandle returnOArr = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Null_returnOArr", MemorySegment.class /* self */);

    public io.vproxy.pni.test.ObjectStruct.Array returnOArr(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.returnOArr.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RES_SEG = ENV.returnBuf();
        if (RES_SEG.isNull()) return null;
        return new io.vproxy.pni.test.ObjectStruct.Array(RES_SEG);
    }

    private final MethodHandle returnOArrCritical = PanamaUtils.lookupPNICriticalFunction(false, PNIBuf.class, "JavaCritical_io_vproxy_pni_test_Null_returnOArrCritical", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public io.vproxy.pni.test.ObjectStruct.Array returnOArrCritical() {
        try (var POOLED = Allocator.ofPooled()) {
            MemorySegment RESULT;
            try {
                RESULT = (MemorySegment) this.returnOArrCritical.invokeExact(MEMORY, POOLED.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (RESULT.address() == 0) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return new io.vproxy.pni.test.ObjectStruct.Array(RES_SEG);
        }
    }

    private final MethodHandle returnOArrCritical2 = PanamaUtils.lookupPNICriticalFunction(false, PNIBuf.class, "JavaCritical_io_vproxy_pni_test_Null_returnOArrCritical2", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public io.vproxy.pni.test.ObjectStruct.Array returnOArrCritical2() {
        try (var POOLED = Allocator.ofPooled()) {
            MemorySegment RESULT;
            try {
                RESULT = (MemorySegment) this.returnOArrCritical2.invokeExact(MEMORY, POOLED.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (RESULT.address() == 0) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return new io.vproxy.pni.test.ObjectStruct.Array(RES_SEG);
        }
    }

    public static class Array extends RefArray<Null> {
        public Array(MemorySegment buf) {
            super(buf, Null.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            this(allocator.allocate(Null.LAYOUT.byteSize() * len));
        }

        public Array(PNIBuf buf) {
            this(buf.get());
        }

        @Override
        protected Null construct(MemorySegment seg) {
            return new Null(seg);
        }

        @Override
        protected MemorySegment getSegment(Null value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<Null> {
        private Func(io.vproxy.pni.CallSite<Null> func) {
            super(func);
        }

        public static Func of(io.vproxy.pni.CallSite<Null> func) {
            return new Func(func);
        }

        @Override
        protected Null construct(MemorySegment seg) {
            return new Null(seg);
        }

        @Override
        protected MemorySegment getSegment(Null value) {
            return value.MEMORY;
        }
    }
}
// sha256:cbe0f5936787c3ee59a09707acd14de3c2d332a81c480f022be94794ee79b7a4
