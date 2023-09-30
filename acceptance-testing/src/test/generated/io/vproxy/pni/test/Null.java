package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class Null implements NativeObject {
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
        PNIBuf.LAYOUT.withName("oArr"),
        ValueLayout.ADDRESS_UNALIGNED.withName("ref"),
        ValueLayout.ADDRESS_UNALIGNED.withName("func"),
        ValueLayout.ADDRESS_UNALIGNED.withName("funcVoid"),
        ValueLayout.ADDRESS_UNALIGNED.withName("funcRef")
    );
    public final MemorySegment MEMORY;

    @Override
    public MemorySegment MEMORY() {
        return MEMORY;
    }

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

    private static final VarHandle refVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("ref")
    );

    public PNIRef<java.lang.Object> getRef() {
        var SEG = (MemorySegment) refVH.get(MEMORY);
        if (SEG.address() == 0) return null;
        return PNIRef.of(SEG);
    }

    public void setRef(PNIRef<java.lang.Object> ref) {
        if (ref == null) {
            refVH.set(MEMORY, MemorySegment.NULL);
        } else {
            refVH.set(MEMORY, ref.MEMORY);
        }
    }

    private static final VarHandle funcVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("func")
    );

    public PNIFunc<io.vproxy.pni.test.Null> getFunc() {
        var SEG = (MemorySegment) funcVH.get(MEMORY);
        if (SEG.address() == 0) return null;
        return io.vproxy.pni.test.Null.Func.of(SEG);
    }

    public void setFunc(PNIFunc<io.vproxy.pni.test.Null> func) {
        if (func == null) {
            funcVH.set(MEMORY, MemorySegment.NULL);
        } else {
            funcVH.set(MEMORY, func.MEMORY);
        }
    }

    private static final VarHandle funcVoidVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("funcVoid")
    );

    public PNIFunc<Void> getFuncVoid() {
        var SEG = (MemorySegment) funcVoidVH.get(MEMORY);
        if (SEG.address() == 0) return null;
        return PNIFunc.VoidFunc.of(SEG);
    }

    public void setFuncVoid(PNIFunc<Void> funcVoid) {
        if (funcVoid == null) {
            funcVoidVH.set(MEMORY, MemorySegment.NULL);
        } else {
            funcVoidVH.set(MEMORY, funcVoid.MEMORY);
        }
    }

    private static final VarHandle funcRefVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("funcRef")
    );

    public PNIFunc<java.lang.Object> getFuncRef() {
        var SEG = (MemorySegment) funcRefVH.get(MEMORY);
        if (SEG.address() == 0) return null;
        return PNIRef.Func.of(SEG);
    }

    public void setFuncRef(PNIFunc<java.lang.Object> funcRef) {
        if (funcRef == null) {
            funcRefVH.set(MEMORY, MemorySegment.NULL);
        } else {
            funcRefVH.set(MEMORY, funcRef.MEMORY);
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
        OFFSET += ValueLayout.ADDRESS_UNALIGNED.byteSize();
        OFFSET += ValueLayout.ADDRESS_UNALIGNED.byteSize();
        OFFSET += ValueLayout.ADDRESS_UNALIGNED.byteSize();
        OFFSET += ValueLayout.ADDRESS_UNALIGNED.byteSize();
    }

    public Null(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT.byteSize()));
    }

    private static final MethodHandle testParamMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Null_testParam", MemorySegment.class /* self */, io.vproxy.pni.test.ObjectStruct.LAYOUT.getClass() /* o */, String.class /* str */, MemorySegment.class /* seg */, PNIBuf.class /* buf */, PNIBuf.class /* byteArr */, PNIBuf.class /* boolArr */, PNIBuf.class /* charArr */, PNIBuf.class /* floatArr */, PNIBuf.class /* doubleArr */, PNIBuf.class /* intArr */, PNIBuf.class /* longArr */, PNIBuf.class /* shortArr */, PNIBuf.class /* oArr */, PNIRef.class /* ref */, io.vproxy.pni.CallSite.class /* func */, io.vproxy.pni.CallSite.class /* funcVoid */, io.vproxy.pni.CallSite.class /* funcRef */);

    public boolean testParam(PNIEnv ENV, io.vproxy.pni.test.ObjectStruct o, PNIString str, MemorySegment seg, ByteBuffer buf, MemorySegment byteArr, BoolArray boolArr, CharArray charArr, FloatArray floatArr, DoubleArray doubleArr, IntArray intArr, LongArray longArr, ShortArray shortArr, io.vproxy.pni.test.ObjectStruct.Array oArr, java.lang.Object ref, io.vproxy.pni.CallSite<io.vproxy.pni.test.Null> func, io.vproxy.pni.CallSite<Void> funcVoid, io.vproxy.pni.CallSite<java.lang.Object> funcRef) {
        ENV.reset();
        try (var POOLED = Allocator.ofPooled()) {
            int ERR;
            try {
                ERR = (int) testParamMH.invokeExact(ENV.MEMORY, MEMORY, (MemorySegment) (o == null ? MemorySegment.NULL : o.MEMORY), (MemorySegment) (str == null ? MemorySegment.NULL : str.MEMORY), (MemorySegment) (seg == null ? MemorySegment.NULL : seg), PanamaUtils.format(buf, POOLED), PNIBuf.memoryOf(POOLED, byteArr), PNIBuf.memoryOf(POOLED, boolArr), PNIBuf.memoryOf(POOLED, charArr), PNIBuf.memoryOf(POOLED, floatArr), PNIBuf.memoryOf(POOLED, doubleArr), PNIBuf.memoryOf(POOLED, intArr), PNIBuf.memoryOf(POOLED, longArr), PNIBuf.memoryOf(POOLED, shortArr), PNIBuf.memoryOf(POOLED, oArr), (MemorySegment) (ref == null ? MemorySegment.NULL : PNIRef.of(ref).MEMORY), (MemorySegment) (func == null ? MemorySegment.NULL : io.vproxy.pni.test.Null.Func.of(func).MEMORY), (MemorySegment) (funcVoid == null ? MemorySegment.NULL : PNIFunc.VoidFunc.of(funcVoid).MEMORY), (MemorySegment) (funcRef == null ? MemorySegment.NULL : PNIRef.Func.of(funcRef).MEMORY));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (ERR != 0) {
                ENV.throwLast();
            }
            return ENV.returnBool();
        }
    }

    private static final MethodHandle testParamCriticalMH = PanamaUtils.lookupPNICriticalFunction(false, boolean.class, "JavaCritical_io_vproxy_pni_test_Null_testParamCritical", MemorySegment.class /* self */, io.vproxy.pni.test.ObjectStruct.LAYOUT.getClass() /* o */, String.class /* str */, MemorySegment.class /* seg */, PNIBuf.class /* buf */, PNIBuf.class /* byteArr */, PNIBuf.class /* boolArr */, PNIBuf.class /* charArr */, PNIBuf.class /* floatArr */, PNIBuf.class /* doubleArr */, PNIBuf.class /* intArr */, PNIBuf.class /* longArr */, PNIBuf.class /* shortArr */, PNIBuf.class /* oArr */, PNIRef.class /* ref */, io.vproxy.pni.CallSite.class /* func */, io.vproxy.pni.CallSite.class /* funcVoid */, io.vproxy.pni.CallSite.class /* funcRef */);

    public boolean testParamCritical(io.vproxy.pni.test.ObjectStruct o, PNIString str, MemorySegment seg, ByteBuffer buf, MemorySegment byteArr, BoolArray boolArr, CharArray charArr, FloatArray floatArr, DoubleArray doubleArr, IntArray intArr, LongArray longArr, ShortArray shortArr, io.vproxy.pni.test.ObjectStruct.Array oArr, java.lang.Object ref, io.vproxy.pni.CallSite<io.vproxy.pni.test.Null> func, io.vproxy.pni.CallSite<Void> funcVoid, io.vproxy.pni.CallSite<java.lang.Object> funcRef) {
        try (var POOLED = Allocator.ofPooled()) {
            boolean RESULT;
            try {
                RESULT = (boolean) testParamCriticalMH.invokeExact(MEMORY, (MemorySegment) (o == null ? MemorySegment.NULL : o.MEMORY), (MemorySegment) (str == null ? MemorySegment.NULL : str.MEMORY), (MemorySegment) (seg == null ? MemorySegment.NULL : seg), PanamaUtils.format(buf, POOLED), PNIBuf.memoryOf(POOLED, byteArr), PNIBuf.memoryOf(POOLED, boolArr), PNIBuf.memoryOf(POOLED, charArr), PNIBuf.memoryOf(POOLED, floatArr), PNIBuf.memoryOf(POOLED, doubleArr), PNIBuf.memoryOf(POOLED, intArr), PNIBuf.memoryOf(POOLED, longArr), PNIBuf.memoryOf(POOLED, shortArr), PNIBuf.memoryOf(POOLED, oArr), (MemorySegment) (ref == null ? MemorySegment.NULL : PNIRef.of(ref).MEMORY), (MemorySegment) (func == null ? MemorySegment.NULL : io.vproxy.pni.test.Null.Func.of(func).MEMORY), (MemorySegment) (funcVoid == null ? MemorySegment.NULL : PNIFunc.VoidFunc.of(funcVoid).MEMORY), (MemorySegment) (funcRef == null ? MemorySegment.NULL : PNIRef.Func.of(funcRef).MEMORY));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            return RESULT;
        }
    }

    private static final MethodHandle testParamRawMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Null_testParamRaw", MemorySegment.class /* self */, ByteBuffer.class /* buf */, MemorySegment.class /* byteArr */, MemorySegment.class /* boolArr */, MemorySegment.class /* charArr */, MemorySegment.class /* floatArr */, MemorySegment.class /* doubleArr */, MemorySegment.class /* intArr */, MemorySegment.class /* longArr */, MemorySegment.class /* shortArr */, MemorySegment.class /* oArr */, PNIRef.class /* ref */, PNIFunc.class /* func */, PNIFunc.class /* funcVoid */, PNIFunc.class /* funcRef */);

    public boolean testParamRaw(PNIEnv ENV, ByteBuffer buf, MemorySegment byteArr, BoolArray boolArr, CharArray charArr, FloatArray floatArr, DoubleArray doubleArr, IntArray intArr, LongArray longArr, ShortArray shortArr, io.vproxy.pni.test.ObjectStruct.Array oArr, PNIRef<java.lang.Object> ref, PNIFunc<io.vproxy.pni.test.Null> func, PNIFunc<Void> funcVoid, PNIFunc<java.lang.Object> funcRef) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) testParamRawMH.invokeExact(ENV.MEMORY, MEMORY, PanamaUtils.format(buf), (MemorySegment) (byteArr == null ? MemorySegment.NULL : byteArr), (MemorySegment) (boolArr == null ? MemorySegment.NULL : boolArr.MEMORY), (MemorySegment) (charArr == null ? MemorySegment.NULL : charArr.MEMORY), (MemorySegment) (floatArr == null ? MemorySegment.NULL : floatArr.MEMORY), (MemorySegment) (doubleArr == null ? MemorySegment.NULL : doubleArr.MEMORY), (MemorySegment) (intArr == null ? MemorySegment.NULL : intArr.MEMORY), (MemorySegment) (longArr == null ? MemorySegment.NULL : longArr.MEMORY), (MemorySegment) (shortArr == null ? MemorySegment.NULL : shortArr.MEMORY), (MemorySegment) (oArr == null ? MemorySegment.NULL : oArr.MEMORY), (MemorySegment) (ref == null ? MemorySegment.NULL : ref.MEMORY), (MemorySegment) (func == null ? MemorySegment.NULL : func.MEMORY), (MemorySegment) (funcVoid == null ? MemorySegment.NULL : funcVoid.MEMORY), (MemorySegment) (funcRef == null ? MemorySegment.NULL : funcRef.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnBool();
    }

    private static final MethodHandle testParamRawCriticalMH = PanamaUtils.lookupPNICriticalFunction(false, boolean.class, "JavaCritical_io_vproxy_pni_test_Null_testParamRawCritical", MemorySegment.class /* self */, ByteBuffer.class /* buf */, MemorySegment.class /* byteArr */, MemorySegment.class /* boolArr */, MemorySegment.class /* charArr */, MemorySegment.class /* floatArr */, MemorySegment.class /* doubleArr */, MemorySegment.class /* intArr */, MemorySegment.class /* longArr */, MemorySegment.class /* shortArr */, MemorySegment.class /* oArr */, PNIRef.class /* ref */, PNIFunc.class /* func */, PNIFunc.class /* funcVoid */, PNIFunc.class /* funcRef */);

    public boolean testParamRawCritical(ByteBuffer buf, MemorySegment byteArr, BoolArray boolArr, CharArray charArr, FloatArray floatArr, DoubleArray doubleArr, IntArray intArr, LongArray longArr, ShortArray shortArr, io.vproxy.pni.test.ObjectStruct.Array oArr, PNIRef<java.lang.Object> ref, PNIFunc<io.vproxy.pni.test.Null> func, PNIFunc<Void> funcVoid, PNIFunc<java.lang.Object> funcRef) {
        boolean RESULT;
        try {
            RESULT = (boolean) testParamRawCriticalMH.invokeExact(MEMORY, PanamaUtils.format(buf), (MemorySegment) (byteArr == null ? MemorySegment.NULL : byteArr), (MemorySegment) (boolArr == null ? MemorySegment.NULL : boolArr.MEMORY), (MemorySegment) (charArr == null ? MemorySegment.NULL : charArr.MEMORY), (MemorySegment) (floatArr == null ? MemorySegment.NULL : floatArr.MEMORY), (MemorySegment) (doubleArr == null ? MemorySegment.NULL : doubleArr.MEMORY), (MemorySegment) (intArr == null ? MemorySegment.NULL : intArr.MEMORY), (MemorySegment) (longArr == null ? MemorySegment.NULL : longArr.MEMORY), (MemorySegment) (shortArr == null ? MemorySegment.NULL : shortArr.MEMORY), (MemorySegment) (oArr == null ? MemorySegment.NULL : oArr.MEMORY), (MemorySegment) (ref == null ? MemorySegment.NULL : ref.MEMORY), (MemorySegment) (func == null ? MemorySegment.NULL : func.MEMORY), (MemorySegment) (funcVoid == null ? MemorySegment.NULL : funcVoid.MEMORY), (MemorySegment) (funcRef == null ? MemorySegment.NULL : funcRef.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle returnOMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Null_returnO", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public io.vproxy.pni.test.ObjectStruct returnO(PNIEnv ENV, Allocator ALLOCATOR) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) returnOMH.invokeExact(ENV.MEMORY, MEMORY, ALLOCATOR.allocate(io.vproxy.pni.test.ObjectStruct.LAYOUT.byteSize()));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RESULT = ENV.returnPointer();
        return RESULT == null ? null : new io.vproxy.pni.test.ObjectStruct(RESULT);
    }

    private static final MethodHandle returnOCriticalMH = PanamaUtils.lookupPNICriticalFunction(false, io.vproxy.pni.test.ObjectStruct.LAYOUT.getClass(), "JavaCritical_io_vproxy_pni_test_Null_returnOCritical", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public io.vproxy.pni.test.ObjectStruct returnOCritical(Allocator ALLOCATOR) {
        MemorySegment RESULT;
        try {
            RESULT = (MemorySegment) returnOCriticalMH.invokeExact(MEMORY, ALLOCATOR.allocate(io.vproxy.pni.test.ObjectStruct.LAYOUT.byteSize()));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (RESULT.address() == 0) return null;
        return RESULT == null ? null : new io.vproxy.pni.test.ObjectStruct(RESULT);
    }

    private static final MethodHandle returnStrMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Null_returnStr", MemorySegment.class /* self */);

    public PNIString returnStr(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) returnStrMH.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RESULT = ENV.returnPointer();
        return RESULT == null ? null : new PNIString(RESULT);
    }

    private static final MethodHandle returnStrCriticalMH = PanamaUtils.lookupPNICriticalFunction(false, String.class, "JavaCritical_io_vproxy_pni_test_Null_returnStrCritical", MemorySegment.class /* self */);

    public PNIString returnStrCritical() {
        MemorySegment RESULT;
        try {
            RESULT = (MemorySegment) returnStrCriticalMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT.address() == 0 ? null : new PNIString(RESULT);
    }

    private static final MethodHandle returnSegMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Null_returnSeg", MemorySegment.class /* self */);

    public MemorySegment returnSeg(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) returnSegMH.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnPointer();
    }

    private static final MethodHandle returnSegCriticalMH = PanamaUtils.lookupPNICriticalFunction(false, MemorySegment.class, "JavaCritical_io_vproxy_pni_test_Null_returnSegCritical", MemorySegment.class /* self */);

    public MemorySegment returnSegCritical() {
        MemorySegment RESULT;
        try {
            RESULT = (MemorySegment) returnSegCriticalMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (RESULT.address() == 0) return null;
        return RESULT;
    }

    private static final MethodHandle returnBufMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Null_returnBuf", MemorySegment.class /* self */);

    public ByteBuffer returnBuf(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) returnBufMH.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RES_SEG = ENV.returnBuf();
        return RES_SEG.toByteBuffer();
    }

    private static final MethodHandle returnBufCriticalMH = PanamaUtils.lookupPNICriticalFunction(false, PNIBuf.class, "JavaCritical_io_vproxy_pni_test_Null_returnBufCritical", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public ByteBuffer returnBufCritical() {
        try (var POOLED = Allocator.ofPooled()) {
            MemorySegment RESULT;
            try {
                RESULT = (MemorySegment) returnBufCriticalMH.invokeExact(MEMORY, POOLED.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (RESULT.address() == 0) return null;
            var RES_SEG = new PNIBuf(RESULT);
            return RES_SEG.toByteBuffer();
        }
    }

    private static final MethodHandle returnBufCritical2MH = PanamaUtils.lookupPNICriticalFunction(false, PNIBuf.class, "JavaCritical_io_vproxy_pni_test_Null_returnBufCritical2", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public ByteBuffer returnBufCritical2() {
        try (var POOLED = Allocator.ofPooled()) {
            MemorySegment RESULT;
            try {
                RESULT = (MemorySegment) returnBufCritical2MH.invokeExact(MEMORY, POOLED.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (RESULT.address() == 0) return null;
            var RES_SEG = new PNIBuf(RESULT);
            return RES_SEG.toByteBuffer();
        }
    }

    private static final MethodHandle returnByteArrMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Null_returnByteArr", MemorySegment.class /* self */);

    public MemorySegment returnByteArr(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) returnByteArrMH.invokeExact(ENV.MEMORY, MEMORY);
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

    private static final MethodHandle returnByteArrCriticalMH = PanamaUtils.lookupPNICriticalFunction(false, PNIBuf.class, "JavaCritical_io_vproxy_pni_test_Null_returnByteArrCritical", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public MemorySegment returnByteArrCritical() {
        try (var POOLED = Allocator.ofPooled()) {
            MemorySegment RESULT;
            try {
                RESULT = (MemorySegment) returnByteArrCriticalMH.invokeExact(MEMORY, POOLED.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (RESULT.address() == 0) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return RES_SEG.get();
        }
    }

    private static final MethodHandle returnByteArrCritical2MH = PanamaUtils.lookupPNICriticalFunction(false, PNIBuf.class, "JavaCritical_io_vproxy_pni_test_Null_returnByteArrCritical2", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public MemorySegment returnByteArrCritical2() {
        try (var POOLED = Allocator.ofPooled()) {
            MemorySegment RESULT;
            try {
                RESULT = (MemorySegment) returnByteArrCritical2MH.invokeExact(MEMORY, POOLED.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (RESULT.address() == 0) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return RES_SEG.get();
        }
    }

    private static final MethodHandle returnBoolArrMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Null_returnBoolArr", MemorySegment.class /* self */);

    public BoolArray returnBoolArr(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) returnBoolArrMH.invokeExact(ENV.MEMORY, MEMORY);
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

    private static final MethodHandle returnBoolArrCriticalMH = PanamaUtils.lookupPNICriticalFunction(false, PNIBuf.class, "JavaCritical_io_vproxy_pni_test_Null_returnBoolArrCritical", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public BoolArray returnBoolArrCritical() {
        try (var POOLED = Allocator.ofPooled()) {
            MemorySegment RESULT;
            try {
                RESULT = (MemorySegment) returnBoolArrCriticalMH.invokeExact(MEMORY, POOLED.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (RESULT.address() == 0) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return new BoolArray(RES_SEG);
        }
    }

    private static final MethodHandle returnBoolArrCritical2MH = PanamaUtils.lookupPNICriticalFunction(false, PNIBuf.class, "JavaCritical_io_vproxy_pni_test_Null_returnBoolArrCritical2", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public BoolArray returnBoolArrCritical2() {
        try (var POOLED = Allocator.ofPooled()) {
            MemorySegment RESULT;
            try {
                RESULT = (MemorySegment) returnBoolArrCritical2MH.invokeExact(MEMORY, POOLED.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (RESULT.address() == 0) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return new BoolArray(RES_SEG);
        }
    }

    private static final MethodHandle returnCharArrMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Null_returnCharArr", MemorySegment.class /* self */);

    public CharArray returnCharArr(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) returnCharArrMH.invokeExact(ENV.MEMORY, MEMORY);
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

    private static final MethodHandle returnCharArrCriticalMH = PanamaUtils.lookupPNICriticalFunction(false, PNIBuf.class, "JavaCritical_io_vproxy_pni_test_Null_returnCharArrCritical", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public CharArray returnCharArrCritical() {
        try (var POOLED = Allocator.ofPooled()) {
            MemorySegment RESULT;
            try {
                RESULT = (MemorySegment) returnCharArrCriticalMH.invokeExact(MEMORY, POOLED.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (RESULT.address() == 0) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return new CharArray(RES_SEG);
        }
    }

    private static final MethodHandle returnCharArrCritical2MH = PanamaUtils.lookupPNICriticalFunction(false, PNIBuf.class, "JavaCritical_io_vproxy_pni_test_Null_returnCharArrCritical2", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public CharArray returnCharArrCritical2() {
        try (var POOLED = Allocator.ofPooled()) {
            MemorySegment RESULT;
            try {
                RESULT = (MemorySegment) returnCharArrCritical2MH.invokeExact(MEMORY, POOLED.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (RESULT.address() == 0) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return new CharArray(RES_SEG);
        }
    }

    private static final MethodHandle returnFloatArrMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Null_returnFloatArr", MemorySegment.class /* self */);

    public FloatArray returnFloatArr(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) returnFloatArrMH.invokeExact(ENV.MEMORY, MEMORY);
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

    private static final MethodHandle returnFloatArrCriticalMH = PanamaUtils.lookupPNICriticalFunction(false, PNIBuf.class, "JavaCritical_io_vproxy_pni_test_Null_returnFloatArrCritical", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public FloatArray returnFloatArrCritical() {
        try (var POOLED = Allocator.ofPooled()) {
            MemorySegment RESULT;
            try {
                RESULT = (MemorySegment) returnFloatArrCriticalMH.invokeExact(MEMORY, POOLED.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (RESULT.address() == 0) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return new FloatArray(RES_SEG);
        }
    }

    private static final MethodHandle returnFloatArrCritical2MH = PanamaUtils.lookupPNICriticalFunction(false, PNIBuf.class, "JavaCritical_io_vproxy_pni_test_Null_returnFloatArrCritical2", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public FloatArray returnFloatArrCritical2() {
        try (var POOLED = Allocator.ofPooled()) {
            MemorySegment RESULT;
            try {
                RESULT = (MemorySegment) returnFloatArrCritical2MH.invokeExact(MEMORY, POOLED.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (RESULT.address() == 0) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return new FloatArray(RES_SEG);
        }
    }

    private static final MethodHandle returnDoubleArrMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Null_returnDoubleArr", MemorySegment.class /* self */);

    public DoubleArray returnDoubleArr(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) returnDoubleArrMH.invokeExact(ENV.MEMORY, MEMORY);
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

    private static final MethodHandle returnDoubleArrCriticalMH = PanamaUtils.lookupPNICriticalFunction(false, PNIBuf.class, "JavaCritical_io_vproxy_pni_test_Null_returnDoubleArrCritical", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public DoubleArray returnDoubleArrCritical() {
        try (var POOLED = Allocator.ofPooled()) {
            MemorySegment RESULT;
            try {
                RESULT = (MemorySegment) returnDoubleArrCriticalMH.invokeExact(MEMORY, POOLED.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (RESULT.address() == 0) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return new DoubleArray(RES_SEG);
        }
    }

    private static final MethodHandle returnDoubleArrCritical2MH = PanamaUtils.lookupPNICriticalFunction(false, PNIBuf.class, "JavaCritical_io_vproxy_pni_test_Null_returnDoubleArrCritical2", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public DoubleArray returnDoubleArrCritical2() {
        try (var POOLED = Allocator.ofPooled()) {
            MemorySegment RESULT;
            try {
                RESULT = (MemorySegment) returnDoubleArrCritical2MH.invokeExact(MEMORY, POOLED.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (RESULT.address() == 0) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return new DoubleArray(RES_SEG);
        }
    }

    private static final MethodHandle returnIntArrMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Null_returnIntArr", MemorySegment.class /* self */);

    public IntArray returnIntArr(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) returnIntArrMH.invokeExact(ENV.MEMORY, MEMORY);
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

    private static final MethodHandle returnIntArrCriticalMH = PanamaUtils.lookupPNICriticalFunction(false, PNIBuf.class, "JavaCritical_io_vproxy_pni_test_Null_returnIntArrCritical", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public IntArray returnIntArrCritical() {
        try (var POOLED = Allocator.ofPooled()) {
            MemorySegment RESULT;
            try {
                RESULT = (MemorySegment) returnIntArrCriticalMH.invokeExact(MEMORY, POOLED.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (RESULT.address() == 0) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return new IntArray(RES_SEG);
        }
    }

    private static final MethodHandle returnIntArrCritical2MH = PanamaUtils.lookupPNICriticalFunction(false, PNIBuf.class, "JavaCritical_io_vproxy_pni_test_Null_returnIntArrCritical2", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public IntArray returnIntArrCritical2() {
        try (var POOLED = Allocator.ofPooled()) {
            MemorySegment RESULT;
            try {
                RESULT = (MemorySegment) returnIntArrCritical2MH.invokeExact(MEMORY, POOLED.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (RESULT.address() == 0) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return new IntArray(RES_SEG);
        }
    }

    private static final MethodHandle returnLongArrMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Null_returnLongArr", MemorySegment.class /* self */);

    public LongArray returnLongArr(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) returnLongArrMH.invokeExact(ENV.MEMORY, MEMORY);
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

    private static final MethodHandle returnLongArrCriticalMH = PanamaUtils.lookupPNICriticalFunction(false, PNIBuf.class, "JavaCritical_io_vproxy_pni_test_Null_returnLongArrCritical", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public LongArray returnLongArrCritical() {
        try (var POOLED = Allocator.ofPooled()) {
            MemorySegment RESULT;
            try {
                RESULT = (MemorySegment) returnLongArrCriticalMH.invokeExact(MEMORY, POOLED.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (RESULT.address() == 0) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return new LongArray(RES_SEG);
        }
    }

    private static final MethodHandle returnLongArrCritical2MH = PanamaUtils.lookupPNICriticalFunction(false, PNIBuf.class, "JavaCritical_io_vproxy_pni_test_Null_returnLongArrCritical2", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public LongArray returnLongArrCritical2() {
        try (var POOLED = Allocator.ofPooled()) {
            MemorySegment RESULT;
            try {
                RESULT = (MemorySegment) returnLongArrCritical2MH.invokeExact(MEMORY, POOLED.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (RESULT.address() == 0) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return new LongArray(RES_SEG);
        }
    }

    private static final MethodHandle returnShortArrMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Null_returnShortArr", MemorySegment.class /* self */);

    public ShortArray returnShortArr(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) returnShortArrMH.invokeExact(ENV.MEMORY, MEMORY);
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

    private static final MethodHandle returnShortArrCriticalMH = PanamaUtils.lookupPNICriticalFunction(false, PNIBuf.class, "JavaCritical_io_vproxy_pni_test_Null_returnShortArrCritical", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public ShortArray returnShortArrCritical() {
        try (var POOLED = Allocator.ofPooled()) {
            MemorySegment RESULT;
            try {
                RESULT = (MemorySegment) returnShortArrCriticalMH.invokeExact(MEMORY, POOLED.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (RESULT.address() == 0) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return new ShortArray(RES_SEG);
        }
    }

    private static final MethodHandle returnShortArrCritical2MH = PanamaUtils.lookupPNICriticalFunction(false, PNIBuf.class, "JavaCritical_io_vproxy_pni_test_Null_returnShortArrCritical2", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public ShortArray returnShortArrCritical2() {
        try (var POOLED = Allocator.ofPooled()) {
            MemorySegment RESULT;
            try {
                RESULT = (MemorySegment) returnShortArrCritical2MH.invokeExact(MEMORY, POOLED.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (RESULT.address() == 0) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return new ShortArray(RES_SEG);
        }
    }

    private static final MethodHandle returnOArrMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Null_returnOArr", MemorySegment.class /* self */);

    public io.vproxy.pni.test.ObjectStruct.Array returnOArr(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) returnOArrMH.invokeExact(ENV.MEMORY, MEMORY);
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

    private static final MethodHandle returnOArrCriticalMH = PanamaUtils.lookupPNICriticalFunction(false, PNIBuf.class, "JavaCritical_io_vproxy_pni_test_Null_returnOArrCritical", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public io.vproxy.pni.test.ObjectStruct.Array returnOArrCritical() {
        try (var POOLED = Allocator.ofPooled()) {
            MemorySegment RESULT;
            try {
                RESULT = (MemorySegment) returnOArrCriticalMH.invokeExact(MEMORY, POOLED.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (RESULT.address() == 0) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return new io.vproxy.pni.test.ObjectStruct.Array(RES_SEG);
        }
    }

    private static final MethodHandle returnOArrCritical2MH = PanamaUtils.lookupPNICriticalFunction(false, PNIBuf.class, "JavaCritical_io_vproxy_pni_test_Null_returnOArrCritical2", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public io.vproxy.pni.test.ObjectStruct.Array returnOArrCritical2() {
        try (var POOLED = Allocator.ofPooled()) {
            MemorySegment RESULT;
            try {
                RESULT = (MemorySegment) returnOArrCritical2MH.invokeExact(MEMORY, POOLED.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (RESULT.address() == 0) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return new io.vproxy.pni.test.ObjectStruct.Array(RES_SEG);
        }
    }

    private static final MethodHandle returnRefMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Null_returnRef", MemorySegment.class /* self */);

    public PNIRef<java.lang.Object> returnRef(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) returnRefMH.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RESULT = ENV.returnPointer();
        if (RESULT == null) return null;
        return PNIRef.of(RESULT);
    }

    private static final MethodHandle returnRefCriticalMH = PanamaUtils.lookupPNICriticalFunction(false, PNIRef.class, "JavaCritical_io_vproxy_pni_test_Null_returnRefCritical", MemorySegment.class /* self */);

    public PNIRef<java.lang.Object> returnRefCritical() {
        MemorySegment RESULT;
        try {
            RESULT = (MemorySegment) returnRefCriticalMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (RESULT.address() == 0) return null;
        return PNIRef.of(RESULT);
    }

    private static final MethodHandle returnFuncMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Null_returnFunc", MemorySegment.class /* self */);

    public PNIFunc<io.vproxy.pni.test.Null> returnFunc(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) returnFuncMH.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RESULT = ENV.returnPointer();
        if (RESULT == null) return null;
        return io.vproxy.pni.test.Null.Func.of(RESULT);
    }

    private static final MethodHandle returnFuncCriticalMH = PanamaUtils.lookupPNICriticalFunction(false, PNIFunc.class, "JavaCritical_io_vproxy_pni_test_Null_returnFuncCritical", MemorySegment.class /* self */);

    public PNIFunc<io.vproxy.pni.test.Null> returnFuncCritical() {
        MemorySegment RESULT;
        try {
            RESULT = (MemorySegment) returnFuncCriticalMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (RESULT.address() == 0) return null;
        return io.vproxy.pni.test.Null.Func.of(RESULT);
    }

    private static final MethodHandle returnFuncVoidMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Null_returnFuncVoid", MemorySegment.class /* self */);

    public PNIFunc<Void> returnFuncVoid(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) returnFuncVoidMH.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RESULT = ENV.returnPointer();
        if (RESULT == null) return null;
        return PNIFunc.VoidFunc.of(RESULT);
    }

    private static final MethodHandle returnFuncVoidCriticalMH = PanamaUtils.lookupPNICriticalFunction(false, PNIFunc.class, "JavaCritical_io_vproxy_pni_test_Null_returnFuncVoidCritical", MemorySegment.class /* self */);

    public PNIFunc<Void> returnFuncVoidCritical() {
        MemorySegment RESULT;
        try {
            RESULT = (MemorySegment) returnFuncVoidCriticalMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (RESULT.address() == 0) return null;
        return PNIFunc.VoidFunc.of(RESULT);
    }

    private static final MethodHandle returnFuncRefMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Null_returnFuncRef", MemorySegment.class /* self */);

    public PNIFunc<java.lang.Object> returnFuncRef(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) returnFuncRefMH.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RESULT = ENV.returnPointer();
        if (RESULT == null) return null;
        return PNIRef.Func.of(RESULT);
    }

    private static final MethodHandle returnFuncRefCriticalMH = PanamaUtils.lookupPNICriticalFunction(false, PNIFunc.class, "JavaCritical_io_vproxy_pni_test_Null_returnFuncRefCritical", MemorySegment.class /* self */);

    public PNIFunc<java.lang.Object> returnFuncRefCritical() {
        MemorySegment RESULT;
        try {
            RESULT = (MemorySegment) returnFuncRefCriticalMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (RESULT.address() == 0) return null;
        return PNIRef.Func.of(RESULT);
    }

    private static final MethodHandle emptyPassThroughMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Null_emptyPassThrough", MemorySegment.class /* self */, io.vproxy.pni.test.Empty.LAYOUT.getClass() /* empty */, MemorySegment.class /* return */);

    public io.vproxy.pni.test.Empty emptyPassThrough(PNIEnv ENV, io.vproxy.pni.test.Empty empty, Allocator ALLOCATOR) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) emptyPassThroughMH.invokeExact(ENV.MEMORY, MEMORY, (MemorySegment) (empty == null ? MemorySegment.NULL : empty.MEMORY), ALLOCATOR.allocate(io.vproxy.pni.test.Empty.LAYOUT.byteSize()));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RESULT = ENV.returnPointer();
        return RESULT == null ? null : new io.vproxy.pni.test.Empty(RESULT);
    }

    private static final MethodHandle emptyPassThroughCriticalMH = PanamaUtils.lookupPNICriticalFunction(false, io.vproxy.pni.test.Empty.LAYOUT.getClass(), "JavaCritical_io_vproxy_pni_test_Null_emptyPassThroughCritical", MemorySegment.class /* self */, io.vproxy.pni.test.Empty.LAYOUT.getClass() /* empty */, MemorySegment.class /* return */);

    public io.vproxy.pni.test.Empty emptyPassThroughCritical(io.vproxy.pni.test.Empty empty, Allocator ALLOCATOR) {
        MemorySegment RESULT;
        try {
            RESULT = (MemorySegment) emptyPassThroughCriticalMH.invokeExact(MEMORY, (MemorySegment) (empty == null ? MemorySegment.NULL : empty.MEMORY), ALLOCATOR.allocate(io.vproxy.pni.test.Empty.LAYOUT.byteSize()));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (RESULT.address() == 0) return null;
        return RESULT == null ? null : new io.vproxy.pni.test.Empty(RESULT);
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        toString(sb, 0, new java.util.HashSet<>(), false);
        return sb.toString();
    }

    @Override
    public void toString(StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
        if (!VISITED.add(new NativeObjectTuple(this))) {
            SB.append("<...>@").append(Long.toString(MEMORY.address(), 16));
            return;
        }
        SB.append("Null{\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("o => ");
            if (CORRUPTED_MEMORY) SB.append("<?>");
            else PanamaUtils.nativeObjectToString(getO(), SB, INDENT + 4, VISITED, CORRUPTED_MEMORY);
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("str => ");
            if (CORRUPTED_MEMORY) SB.append("<?>");
            else PanamaUtils.nativeObjectToString(getStr(), SB, INDENT + 4, VISITED, CORRUPTED_MEMORY);
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("seg => ");
            SB.append(PanamaUtils.memorySegmentToString(getSeg()));
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("buf => ");
            if (CORRUPTED_MEMORY) SB.append("<?>");
            else SB.append(PanamaUtils.byteBufferToString(getBuf()));
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("byteArr => ");
            if (CORRUPTED_MEMORY) SB.append("<?>");
            else SB.append(PanamaUtils.memorySegmentToString(getByteArr()));
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("boolArr => ");
            if (CORRUPTED_MEMORY) SB.append("<?>");
            else PanamaUtils.nativeObjectToString(getBoolArr(), SB, INDENT + 4, VISITED, CORRUPTED_MEMORY);
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("charArr => ");
            if (CORRUPTED_MEMORY) SB.append("<?>");
            else PanamaUtils.nativeObjectToString(getCharArr(), SB, INDENT + 4, VISITED, CORRUPTED_MEMORY);
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("floatArr => ");
            if (CORRUPTED_MEMORY) SB.append("<?>");
            else PanamaUtils.nativeObjectToString(getFloatArr(), SB, INDENT + 4, VISITED, CORRUPTED_MEMORY);
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("doubleArr => ");
            if (CORRUPTED_MEMORY) SB.append("<?>");
            else PanamaUtils.nativeObjectToString(getDoubleArr(), SB, INDENT + 4, VISITED, CORRUPTED_MEMORY);
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("intArr => ");
            if (CORRUPTED_MEMORY) SB.append("<?>");
            else PanamaUtils.nativeObjectToString(getIntArr(), SB, INDENT + 4, VISITED, CORRUPTED_MEMORY);
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("longArr => ");
            if (CORRUPTED_MEMORY) SB.append("<?>");
            else PanamaUtils.nativeObjectToString(getLongArr(), SB, INDENT + 4, VISITED, CORRUPTED_MEMORY);
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("shortArr => ");
            if (CORRUPTED_MEMORY) SB.append("<?>");
            else PanamaUtils.nativeObjectToString(getShortArr(), SB, INDENT + 4, VISITED, CORRUPTED_MEMORY);
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("oArr => ");
            if (CORRUPTED_MEMORY) SB.append("<?>");
            else PanamaUtils.nativeObjectToString(getOArr(), SB, INDENT + 4, VISITED, CORRUPTED_MEMORY);
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("ref => ");
            if (CORRUPTED_MEMORY) SB.append("<?>");
            else PanamaUtils.nativeObjectToString(getRef(), SB, INDENT + 4, VISITED, CORRUPTED_MEMORY);
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("func => ");
            if (CORRUPTED_MEMORY) SB.append("<?>");
            else PanamaUtils.nativeObjectToString(getFunc(), SB, INDENT + 4, VISITED, CORRUPTED_MEMORY);
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("funcVoid => ");
            if (CORRUPTED_MEMORY) SB.append("<?>");
            else PanamaUtils.nativeObjectToString(getFuncVoid(), SB, INDENT + 4, VISITED, CORRUPTED_MEMORY);
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("funcRef => ");
            if (CORRUPTED_MEMORY) SB.append("<?>");
            else PanamaUtils.nativeObjectToString(getFuncRef(), SB, INDENT + 4, VISITED, CORRUPTED_MEMORY);
        }
        SB.append("\n");
        SB.append(" ".repeat(INDENT)).append("}@").append(Long.toString(MEMORY.address(), 16));
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
        protected void elementToString(io.vproxy.pni.test.Null ELEM, StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
            ELEM.toString(SB, INDENT, VISITED, CORRUPTED_MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "Null.Array";
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

        private Func(io.vproxy.pni.CallSite<Null> func, Options opts) {
            super(func, opts);
        }

        private Func(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static Func of(io.vproxy.pni.CallSite<Null> func) {
            return new Func(func);
        }

        public static Func of(io.vproxy.pni.CallSite<Null> func, Options opts) {
            return new Func(func, opts);
        }

        public static Func of(MemorySegment MEMORY) {
            return new Func(MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "Null.Func";
        }

        @Override
        protected Null construct(MemorySegment seg) {
            return new Null(seg);
        }
    }
}
// metadata.generator-version: pni test
// sha256:97ecc3409ee5d5197d1834bd491319ec09daa380e478477c97b23f8644328d08
