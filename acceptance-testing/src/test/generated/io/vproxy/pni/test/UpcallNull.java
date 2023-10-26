package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class UpcallNull {
    private static final Arena ARENA = Arena.ofShared();

    public static final MemorySegment testParam;

    private static boolean testParam(MemorySegment o, MemorySegment str, MemorySegment seg, MemorySegment buf, MemorySegment byteArr, MemorySegment boolArr, MemorySegment charArr, MemorySegment floatArr, MemorySegment doubleArr, MemorySegment intArr, MemorySegment longArr, MemorySegment shortArr, MemorySegment pArr, MemorySegment oArr, MemorySegment ref, MemorySegment func, MemorySegment funcVoid, MemorySegment funcRef) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.UpcallNull#testParam");
            System.exit(1);
        }
        var RESULT = IMPL.testParam(
            (o.address() == 0 ? null : new io.vproxy.pni.test.ObjectStruct(o)),
            (str.address() == 0 ? null : new PNIString(str)),
            (seg.address() == 0 ? null : seg),
            (buf.address() == 0 ? null : new PNIBuf(buf).toByteBuffer()),
            (byteArr.address() == 0 ? null : new PNIBuf(byteArr).get()),
            (boolArr.address() == 0 ? null : new PNIBuf(boolArr).toBoolArray()),
            (charArr.address() == 0 ? null : new PNIBuf(charArr).toCharArray()),
            (floatArr.address() == 0 ? null : new PNIBuf(floatArr).toFloatArray()),
            (doubleArr.address() == 0 ? null : new PNIBuf(doubleArr).toDoubleArray()),
            (intArr.address() == 0 ? null : new PNIBuf(intArr).toIntArray()),
            (longArr.address() == 0 ? null : new PNIBuf(longArr).toLongArray()),
            (shortArr.address() == 0 ? null : new PNIBuf(shortArr).toShortArray()),
            (pArr.address() == 0 ? null : new PNIBuf(pArr).toPointerArray()),
            (oArr.address() == 0 ? null : new io.vproxy.pni.test.ObjectStruct.Array(new PNIBuf(oArr).get())),
            (ref.address() == 0 ? null : PNIRef.getRef(ref)),
            (func.address() == 0 ? null : io.vproxy.pni.test.Null.Func.of(func).getCallSite()),
            (funcVoid.address() == 0 ? null : PNIFunc.VoidFunc.of(funcVoid).getCallSite()),
            (funcRef.address() == 0 ? null : (io.vproxy.pni.CallSite) PNIRef.Func.of(funcRef).getCallSite())
        );
        return RESULT;
    }

    public static final MemorySegment testParamRaw;

    private static boolean testParamRaw(MemorySegment ref, MemorySegment func, MemorySegment funcVoid, MemorySegment funcRef) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.UpcallNull#testParamRaw");
            System.exit(1);
        }
        var RESULT = IMPL.testParamRaw(
            (ref.address() == 0 ? null : PNIRef.of(ref)),
            (func.address() == 0 ? null : io.vproxy.pni.test.Null.Func.of(func)),
            (funcVoid.address() == 0 ? null : PNIFunc.VoidFunc.of(funcVoid)),
            (funcRef.address() == 0 ? null : PNIRef.Func.of(funcRef))
        );
        return RESULT;
    }

    public static final MemorySegment returnO;

    private static MemorySegment returnO(MemorySegment return_) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.UpcallNull#returnO");
            System.exit(1);
        }
        var RESULT = IMPL.returnO(
            new io.vproxy.pni.test.ObjectStruct(return_)
        );
        return RESULT == null ? MemorySegment.NULL : RESULT.MEMORY;
    }

    public static final MemorySegment returnStr;

    private static MemorySegment returnStr() {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.UpcallNull#returnStr");
            System.exit(1);
        }
        var RESULT = IMPL.returnStr();
        return RESULT == null ? MemorySegment.NULL : RESULT.MEMORY;
    }

    public static final MemorySegment returnSeg;

    private static MemorySegment returnSeg() {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.UpcallNull#returnSeg");
            System.exit(1);
        }
        var RESULT = IMPL.returnSeg();
        return RESULT == null ? MemorySegment.NULL : RESULT;
    }

    public static final MemorySegment returnBuf;

    private static MemorySegment returnBuf(MemorySegment return_) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.UpcallNull#returnBuf");
            System.exit(1);
        }
        var RESULT = IMPL.returnBuf();
        if (RESULT == null) return MemorySegment.NULL;
        var RETURN = new PNIBuf(return_);
        RETURN.set(RESULT);
        return return_;
    }

    public static final MemorySegment returnByteArr;

    private static MemorySegment returnByteArr(MemorySegment return_) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.UpcallNull#returnByteArr");
            System.exit(1);
        }
        var RESULT = IMPL.returnByteArr();
        if (RESULT == null) return MemorySegment.NULL;
        var RETURN = new PNIBuf(return_);
        RETURN.set(RESULT);
        return return_;
    }

    public static final MemorySegment returnBoolArr;

    private static MemorySegment returnBoolArr(MemorySegment return_) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.UpcallNull#returnBoolArr");
            System.exit(1);
        }
        var RESULT = IMPL.returnBoolArr();
        if (RESULT == null) return MemorySegment.NULL;
        var RETURN = new PNIBuf(return_);
        RETURN.set(RESULT.MEMORY);
        return return_;
    }

    public static final MemorySegment returnCharArr;

    private static MemorySegment returnCharArr(MemorySegment return_) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.UpcallNull#returnCharArr");
            System.exit(1);
        }
        var RESULT = IMPL.returnCharArr();
        if (RESULT == null) return MemorySegment.NULL;
        var RETURN = new PNIBuf(return_);
        RETURN.set(RESULT.MEMORY);
        return return_;
    }

    public static final MemorySegment returnFloatArr;

    private static MemorySegment returnFloatArr(MemorySegment return_) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.UpcallNull#returnFloatArr");
            System.exit(1);
        }
        var RESULT = IMPL.returnFloatArr();
        if (RESULT == null) return MemorySegment.NULL;
        var RETURN = new PNIBuf(return_);
        RETURN.set(RESULT.MEMORY);
        return return_;
    }

    public static final MemorySegment returnDoubleArr;

    private static MemorySegment returnDoubleArr(MemorySegment return_) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.UpcallNull#returnDoubleArr");
            System.exit(1);
        }
        var RESULT = IMPL.returnDoubleArr();
        if (RESULT == null) return MemorySegment.NULL;
        var RETURN = new PNIBuf(return_);
        RETURN.set(RESULT.MEMORY);
        return return_;
    }

    public static final MemorySegment returnIntArr;

    private static MemorySegment returnIntArr(MemorySegment return_) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.UpcallNull#returnIntArr");
            System.exit(1);
        }
        var RESULT = IMPL.returnIntArr();
        if (RESULT == null) return MemorySegment.NULL;
        var RETURN = new PNIBuf(return_);
        RETURN.set(RESULT.MEMORY);
        return return_;
    }

    public static final MemorySegment returnLongArr;

    private static MemorySegment returnLongArr(MemorySegment return_) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.UpcallNull#returnLongArr");
            System.exit(1);
        }
        var RESULT = IMPL.returnLongArr();
        if (RESULT == null) return MemorySegment.NULL;
        var RETURN = new PNIBuf(return_);
        RETURN.set(RESULT.MEMORY);
        return return_;
    }

    public static final MemorySegment returnShortArr;

    private static MemorySegment returnShortArr(MemorySegment return_) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.UpcallNull#returnShortArr");
            System.exit(1);
        }
        var RESULT = IMPL.returnShortArr();
        if (RESULT == null) return MemorySegment.NULL;
        var RETURN = new PNIBuf(return_);
        RETURN.set(RESULT.MEMORY);
        return return_;
    }

    public static final MemorySegment returnPArr;

    private static MemorySegment returnPArr(MemorySegment return_) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.UpcallNull#returnPArr");
            System.exit(1);
        }
        var RESULT = IMPL.returnPArr();
        if (RESULT == null) return MemorySegment.NULL;
        var RETURN = new PNIBuf(return_);
        RETURN.set(RESULT.MEMORY);
        return return_;
    }

    public static final MemorySegment returnOArr;

    private static MemorySegment returnOArr(MemorySegment return_) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.UpcallNull#returnOArr");
            System.exit(1);
        }
        var RESULT = IMPL.returnOArr();
        if (RESULT == null) return MemorySegment.NULL;
        var RETURN = new PNIBuf(return_);
        RETURN.set(RESULT.MEMORY);
        return return_;
    }

    public static final MemorySegment returnRef;

    private static MemorySegment returnRef() {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.UpcallNull#returnRef");
            System.exit(1);
        }
        var RESULT = IMPL.returnRef();
        return RESULT == null ? MemorySegment.NULL : RESULT.MEMORY;
    }

    public static final MemorySegment returnFunc;

    private static MemorySegment returnFunc() {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.UpcallNull#returnFunc");
            System.exit(1);
        }
        var RESULT = IMPL.returnFunc();
        return RESULT == null ? MemorySegment.NULL : RESULT.MEMORY;
    }

    public static final MemorySegment returnFuncVoid;

    private static MemorySegment returnFuncVoid() {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.UpcallNull#returnFuncVoid");
            System.exit(1);
        }
        var RESULT = IMPL.returnFuncVoid();
        return RESULT == null ? MemorySegment.NULL : RESULT.MEMORY;
    }

    public static final MemorySegment returnFuncRef;

    private static MemorySegment returnFuncRef() {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.UpcallNull#returnFuncRef");
            System.exit(1);
        }
        var RESULT = IMPL.returnFuncRef();
        return RESULT == null ? MemorySegment.NULL : RESULT.MEMORY;
    }

    static {
        MethodHandle testParamMH;
        MethodHandle testParamRawMH;
        MethodHandle returnOMH;
        MethodHandle returnStrMH;
        MethodHandle returnSegMH;
        MethodHandle returnBufMH;
        MethodHandle returnByteArrMH;
        MethodHandle returnBoolArrMH;
        MethodHandle returnCharArrMH;
        MethodHandle returnFloatArrMH;
        MethodHandle returnDoubleArrMH;
        MethodHandle returnIntArrMH;
        MethodHandle returnLongArrMH;
        MethodHandle returnShortArrMH;
        MethodHandle returnPArrMH;
        MethodHandle returnOArrMH;
        MethodHandle returnRefMH;
        MethodHandle returnFuncMH;
        MethodHandle returnFuncVoidMH;
        MethodHandle returnFuncRefMH;
        try {
            testParamMH = MethodHandles.lookup().findStatic(io.vproxy.pni.test.UpcallNull.class, "testParam", MethodType.methodType(boolean.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class));
            testParamRawMH = MethodHandles.lookup().findStatic(io.vproxy.pni.test.UpcallNull.class, "testParamRaw", MethodType.methodType(boolean.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class));
            returnOMH = MethodHandles.lookup().findStatic(io.vproxy.pni.test.UpcallNull.class, "returnO", MethodType.methodType(MemorySegment.class, MemorySegment.class));
            returnStrMH = MethodHandles.lookup().findStatic(io.vproxy.pni.test.UpcallNull.class, "returnStr", MethodType.methodType(MemorySegment.class));
            returnSegMH = MethodHandles.lookup().findStatic(io.vproxy.pni.test.UpcallNull.class, "returnSeg", MethodType.methodType(MemorySegment.class));
            returnBufMH = MethodHandles.lookup().findStatic(io.vproxy.pni.test.UpcallNull.class, "returnBuf", MethodType.methodType(MemorySegment.class, MemorySegment.class));
            returnByteArrMH = MethodHandles.lookup().findStatic(io.vproxy.pni.test.UpcallNull.class, "returnByteArr", MethodType.methodType(MemorySegment.class, MemorySegment.class));
            returnBoolArrMH = MethodHandles.lookup().findStatic(io.vproxy.pni.test.UpcallNull.class, "returnBoolArr", MethodType.methodType(MemorySegment.class, MemorySegment.class));
            returnCharArrMH = MethodHandles.lookup().findStatic(io.vproxy.pni.test.UpcallNull.class, "returnCharArr", MethodType.methodType(MemorySegment.class, MemorySegment.class));
            returnFloatArrMH = MethodHandles.lookup().findStatic(io.vproxy.pni.test.UpcallNull.class, "returnFloatArr", MethodType.methodType(MemorySegment.class, MemorySegment.class));
            returnDoubleArrMH = MethodHandles.lookup().findStatic(io.vproxy.pni.test.UpcallNull.class, "returnDoubleArr", MethodType.methodType(MemorySegment.class, MemorySegment.class));
            returnIntArrMH = MethodHandles.lookup().findStatic(io.vproxy.pni.test.UpcallNull.class, "returnIntArr", MethodType.methodType(MemorySegment.class, MemorySegment.class));
            returnLongArrMH = MethodHandles.lookup().findStatic(io.vproxy.pni.test.UpcallNull.class, "returnLongArr", MethodType.methodType(MemorySegment.class, MemorySegment.class));
            returnShortArrMH = MethodHandles.lookup().findStatic(io.vproxy.pni.test.UpcallNull.class, "returnShortArr", MethodType.methodType(MemorySegment.class, MemorySegment.class));
            returnPArrMH = MethodHandles.lookup().findStatic(io.vproxy.pni.test.UpcallNull.class, "returnPArr", MethodType.methodType(MemorySegment.class, MemorySegment.class));
            returnOArrMH = MethodHandles.lookup().findStatic(io.vproxy.pni.test.UpcallNull.class, "returnOArr", MethodType.methodType(MemorySegment.class, MemorySegment.class));
            returnRefMH = MethodHandles.lookup().findStatic(io.vproxy.pni.test.UpcallNull.class, "returnRef", MethodType.methodType(MemorySegment.class));
            returnFuncMH = MethodHandles.lookup().findStatic(io.vproxy.pni.test.UpcallNull.class, "returnFunc", MethodType.methodType(MemorySegment.class));
            returnFuncVoidMH = MethodHandles.lookup().findStatic(io.vproxy.pni.test.UpcallNull.class, "returnFuncVoid", MethodType.methodType(MemorySegment.class));
            returnFuncRefMH = MethodHandles.lookup().findStatic(io.vproxy.pni.test.UpcallNull.class, "returnFuncRef", MethodType.methodType(MemorySegment.class));
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
        testParam = PanamaUtils.defineCFunction(new PNILinkOptions(), ARENA, testParamMH, boolean.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class);
        testParamRaw = PanamaUtils.defineCFunction(new PNILinkOptions(), ARENA, testParamRawMH, boolean.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class);
        returnO = PanamaUtils.defineCFunction(new PNILinkOptions(), ARENA, returnOMH, MemorySegment.class, MemorySegment.class);
        returnStr = PanamaUtils.defineCFunction(new PNILinkOptions(), ARENA, returnStrMH, MemorySegment.class);
        returnSeg = PanamaUtils.defineCFunction(new PNILinkOptions(), ARENA, returnSegMH, MemorySegment.class);
        returnBuf = PanamaUtils.defineCFunction(new PNILinkOptions(), ARENA, returnBufMH, MemorySegment.class, MemorySegment.class);
        returnByteArr = PanamaUtils.defineCFunction(new PNILinkOptions(), ARENA, returnByteArrMH, MemorySegment.class, MemorySegment.class);
        returnBoolArr = PanamaUtils.defineCFunction(new PNILinkOptions(), ARENA, returnBoolArrMH, MemorySegment.class, MemorySegment.class);
        returnCharArr = PanamaUtils.defineCFunction(new PNILinkOptions(), ARENA, returnCharArrMH, MemorySegment.class, MemorySegment.class);
        returnFloatArr = PanamaUtils.defineCFunction(new PNILinkOptions(), ARENA, returnFloatArrMH, MemorySegment.class, MemorySegment.class);
        returnDoubleArr = PanamaUtils.defineCFunction(new PNILinkOptions(), ARENA, returnDoubleArrMH, MemorySegment.class, MemorySegment.class);
        returnIntArr = PanamaUtils.defineCFunction(new PNILinkOptions(), ARENA, returnIntArrMH, MemorySegment.class, MemorySegment.class);
        returnLongArr = PanamaUtils.defineCFunction(new PNILinkOptions(), ARENA, returnLongArrMH, MemorySegment.class, MemorySegment.class);
        returnShortArr = PanamaUtils.defineCFunction(new PNILinkOptions(), ARENA, returnShortArrMH, MemorySegment.class, MemorySegment.class);
        returnPArr = PanamaUtils.defineCFunction(new PNILinkOptions(), ARENA, returnPArrMH, MemorySegment.class, MemorySegment.class);
        returnOArr = PanamaUtils.defineCFunction(new PNILinkOptions(), ARENA, returnOArrMH, MemorySegment.class, MemorySegment.class);
        returnRef = PanamaUtils.defineCFunction(new PNILinkOptions(), ARENA, returnRefMH, MemorySegment.class);
        returnFunc = PanamaUtils.defineCFunction(new PNILinkOptions(), ARENA, returnFuncMH, MemorySegment.class);
        returnFuncVoid = PanamaUtils.defineCFunction(new PNILinkOptions(), ARENA, returnFuncVoidMH, MemorySegment.class);
        returnFuncRef = PanamaUtils.defineCFunction(new PNILinkOptions(), ARENA, returnFuncRefMH, MemorySegment.class);

        var initMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), void.class, "JavaCritical_io_vproxy_pni_test_UpcallNull_INIT", MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class);
        try {
            initMH.invoke(testParam, testParamRaw, returnO, returnStr, returnSeg, returnBuf, returnByteArr, returnBoolArr, returnCharArr, returnFloatArr, returnDoubleArr, returnIntArr, returnLongArr, returnShortArr, returnPArr, returnOArr, returnRef, returnFunc, returnFuncVoid, returnFuncRef);
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }

    private static Interface IMPL = null;

    public static void setImpl(Interface impl) {
        java.util.Objects.requireNonNull(impl);
        IMPL = impl;
    }

    public interface Interface {
        boolean testParam(io.vproxy.pni.test.ObjectStruct o, PNIString str, MemorySegment seg, ByteBuffer buf, MemorySegment byteArr, BoolArray boolArr, CharArray charArr, FloatArray floatArr, DoubleArray doubleArr, IntArray intArr, LongArray longArr, ShortArray shortArr, PointerArray pArr, io.vproxy.pni.test.ObjectStruct.Array oArr, java.lang.Object ref, io.vproxy.pni.CallSite<io.vproxy.pni.test.Null> func, io.vproxy.pni.CallSite<Void> funcVoid, io.vproxy.pni.CallSite<java.lang.Object> funcRef);

        boolean testParamRaw(PNIRef<java.lang.Object> ref, PNIFunc<io.vproxy.pni.test.Null> func, PNIFunc<Void> funcVoid, PNIFunc<java.lang.Object> funcRef);

        io.vproxy.pni.test.ObjectStruct returnO(io.vproxy.pni.test.ObjectStruct return_);

        PNIString returnStr();

        MemorySegment returnSeg();

        ByteBuffer returnBuf();

        MemorySegment returnByteArr();

        BoolArray returnBoolArr();

        CharArray returnCharArr();

        FloatArray returnFloatArr();

        DoubleArray returnDoubleArr();

        IntArray returnIntArr();

        LongArray returnLongArr();

        ShortArray returnShortArr();

        PointerArray returnPArr();

        io.vproxy.pni.test.ObjectStruct.Array returnOArr();

        PNIRef<java.lang.Object> returnRef();

        PNIFunc<io.vproxy.pni.test.Null> returnFunc();

        PNIFunc<Void> returnFuncVoid();

        PNIFunc<java.lang.Object> returnFuncRef();
    }
}
// metadata.generator-version: pni test
// sha256:ab763834886613d2a1b988b2e666bf57986cb05d9cfbb11f8220b7b1b331b0fe
