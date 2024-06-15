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

public class UpcallNull {
    private static final Arena ARENA = Arena.ofShared();

    public static MemorySegment testParam;
    public static final CEntryPointLiteral<CFunctionPointer> testParamCEPL = GraalUtils.defineCFunctionByName(new PNILinkOptions(), io.vproxy.pni.test.UpcallNull.class, "testParam");

    @CEntryPoint
    public static boolean testParam(IsolateThread THREAD, VoidPointer oPTR, VoidPointer strPTR, VoidPointer segPTR, VoidPointer bufPTR, VoidPointer byteArrPTR, VoidPointer boolArrPTR, VoidPointer charArrPTR, VoidPointer floatArrPTR, VoidPointer doubleArrPTR, VoidPointer intArrPTR, VoidPointer longArrPTR, VoidPointer shortArrPTR, VoidPointer pArrPTR, VoidPointer oArrPTR, VoidPointer refPTR, VoidPointer funcPTR, VoidPointer funcVoidPTR, VoidPointer funcRefPTR) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.UpcallNull#testParam");
            System.exit(1);
        }
        var o = MemorySegment.ofAddress(oPTR.rawValue());
        var str = MemorySegment.ofAddress(strPTR.rawValue());
        var seg = MemorySegment.ofAddress(segPTR.rawValue());
        var buf = MemorySegment.ofAddress(bufPTR.rawValue());
        var byteArr = MemorySegment.ofAddress(byteArrPTR.rawValue());
        var boolArr = MemorySegment.ofAddress(boolArrPTR.rawValue());
        var charArr = MemorySegment.ofAddress(charArrPTR.rawValue());
        var floatArr = MemorySegment.ofAddress(floatArrPTR.rawValue());
        var doubleArr = MemorySegment.ofAddress(doubleArrPTR.rawValue());
        var intArr = MemorySegment.ofAddress(intArrPTR.rawValue());
        var longArr = MemorySegment.ofAddress(longArrPTR.rawValue());
        var shortArr = MemorySegment.ofAddress(shortArrPTR.rawValue());
        var pArr = MemorySegment.ofAddress(pArrPTR.rawValue());
        var oArr = MemorySegment.ofAddress(oArrPTR.rawValue());
        var ref = MemorySegment.ofAddress(refPTR.rawValue());
        var func = MemorySegment.ofAddress(funcPTR.rawValue());
        var funcVoid = MemorySegment.ofAddress(funcVoidPTR.rawValue());
        var funcRef = MemorySegment.ofAddress(funcRefPTR.rawValue());
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

    public static MemorySegment testParamRaw;
    public static final CEntryPointLiteral<CFunctionPointer> testParamRawCEPL = GraalUtils.defineCFunctionByName(new PNILinkOptions(), io.vproxy.pni.test.UpcallNull.class, "testParamRaw");

    @CEntryPoint
    public static boolean testParamRaw(IsolateThread THREAD, VoidPointer refPTR, VoidPointer funcPTR, VoidPointer funcVoidPTR, VoidPointer funcRefPTR) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.UpcallNull#testParamRaw");
            System.exit(1);
        }
        var ref = MemorySegment.ofAddress(refPTR.rawValue());
        var func = MemorySegment.ofAddress(funcPTR.rawValue());
        var funcVoid = MemorySegment.ofAddress(funcVoidPTR.rawValue());
        var funcRef = MemorySegment.ofAddress(funcRefPTR.rawValue());
        var RESULT = IMPL.testParamRaw(
            (ref.address() == 0 ? null : PNIRef.of(ref)),
            (func.address() == 0 ? null : io.vproxy.pni.test.Null.Func.of(func)),
            (funcVoid.address() == 0 ? null : PNIFunc.VoidFunc.of(funcVoid)),
            (funcRef.address() == 0 ? null : PNIRef.Func.of(funcRef))
        );
        return RESULT;
    }

    public static MemorySegment returnO;
    public static final CEntryPointLiteral<CFunctionPointer> returnOCEPL = GraalUtils.defineCFunctionByName(new PNILinkOptions(), io.vproxy.pni.test.UpcallNull.class, "returnO");

    @CEntryPoint
    public static VoidPointer returnO(IsolateThread THREAD, VoidPointer return_PTR) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.UpcallNull#returnO");
            System.exit(1);
        }
        var return_ = MemorySegment.ofAddress(return_PTR.rawValue());
        var RESULT = IMPL.returnO(
            new io.vproxy.pni.test.ObjectStruct(return_)
        );
        return WordFactory.pointer(RESULT == null ? 0 : RESULT.MEMORY.address());
    }

    public static MemorySegment returnStr;
    public static final CEntryPointLiteral<CFunctionPointer> returnStrCEPL = GraalUtils.defineCFunctionByName(new PNILinkOptions(), io.vproxy.pni.test.UpcallNull.class, "returnStr");

    @CEntryPoint
    public static VoidPointer returnStr(IsolateThread THREAD) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.UpcallNull#returnStr");
            System.exit(1);
        }
        var RESULT = IMPL.returnStr();
        return WordFactory.pointer(RESULT == null ? 0 : RESULT.MEMORY.address());
    }

    public static MemorySegment returnSeg;
    public static final CEntryPointLiteral<CFunctionPointer> returnSegCEPL = GraalUtils.defineCFunctionByName(new PNILinkOptions(), io.vproxy.pni.test.UpcallNull.class, "returnSeg");

    @CEntryPoint
    public static VoidPointer returnSeg(IsolateThread THREAD) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.UpcallNull#returnSeg");
            System.exit(1);
        }
        var RESULT = IMPL.returnSeg();
        return WordFactory.pointer(RESULT == null ? 0 : RESULT.address());
    }

    public static MemorySegment returnBuf;
    public static final CEntryPointLiteral<CFunctionPointer> returnBufCEPL = GraalUtils.defineCFunctionByName(new PNILinkOptions(), io.vproxy.pni.test.UpcallNull.class, "returnBuf");

    @CEntryPoint
    public static VoidPointer returnBuf(IsolateThread THREAD, VoidPointer return_PTR) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.UpcallNull#returnBuf");
            System.exit(1);
        }
        var return_ = MemorySegment.ofAddress(return_PTR.rawValue());
        var RESULT = IMPL.returnBuf();
        if (RESULT == null) return WordFactory.pointer(0);
        var RETURN = new PNIBuf(return_);
        RETURN.set(RESULT);
        return WordFactory.pointer(return_.address());
    }

    public static MemorySegment returnByteArr;
    public static final CEntryPointLiteral<CFunctionPointer> returnByteArrCEPL = GraalUtils.defineCFunctionByName(new PNILinkOptions(), io.vproxy.pni.test.UpcallNull.class, "returnByteArr");

    @CEntryPoint
    public static VoidPointer returnByteArr(IsolateThread THREAD, VoidPointer return_PTR) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.UpcallNull#returnByteArr");
            System.exit(1);
        }
        var return_ = MemorySegment.ofAddress(return_PTR.rawValue());
        var RESULT = IMPL.returnByteArr();
        if (RESULT == null) return WordFactory.pointer(0);
        var RETURN = new PNIBuf(return_);
        RETURN.set(RESULT);
        return WordFactory.pointer(return_.address());
    }

    public static MemorySegment returnBoolArr;
    public static final CEntryPointLiteral<CFunctionPointer> returnBoolArrCEPL = GraalUtils.defineCFunctionByName(new PNILinkOptions(), io.vproxy.pni.test.UpcallNull.class, "returnBoolArr");

    @CEntryPoint
    public static VoidPointer returnBoolArr(IsolateThread THREAD, VoidPointer return_PTR) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.UpcallNull#returnBoolArr");
            System.exit(1);
        }
        var return_ = MemorySegment.ofAddress(return_PTR.rawValue());
        var RESULT = IMPL.returnBoolArr();
        if (RESULT == null) return WordFactory.pointer(0);
        var RETURN = new PNIBuf(return_);
        RETURN.set(RESULT.MEMORY);
        return WordFactory.pointer(return_.address());
    }

    public static MemorySegment returnCharArr;
    public static final CEntryPointLiteral<CFunctionPointer> returnCharArrCEPL = GraalUtils.defineCFunctionByName(new PNILinkOptions(), io.vproxy.pni.test.UpcallNull.class, "returnCharArr");

    @CEntryPoint
    public static VoidPointer returnCharArr(IsolateThread THREAD, VoidPointer return_PTR) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.UpcallNull#returnCharArr");
            System.exit(1);
        }
        var return_ = MemorySegment.ofAddress(return_PTR.rawValue());
        var RESULT = IMPL.returnCharArr();
        if (RESULT == null) return WordFactory.pointer(0);
        var RETURN = new PNIBuf(return_);
        RETURN.set(RESULT.MEMORY);
        return WordFactory.pointer(return_.address());
    }

    public static MemorySegment returnFloatArr;
    public static final CEntryPointLiteral<CFunctionPointer> returnFloatArrCEPL = GraalUtils.defineCFunctionByName(new PNILinkOptions(), io.vproxy.pni.test.UpcallNull.class, "returnFloatArr");

    @CEntryPoint
    public static VoidPointer returnFloatArr(IsolateThread THREAD, VoidPointer return_PTR) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.UpcallNull#returnFloatArr");
            System.exit(1);
        }
        var return_ = MemorySegment.ofAddress(return_PTR.rawValue());
        var RESULT = IMPL.returnFloatArr();
        if (RESULT == null) return WordFactory.pointer(0);
        var RETURN = new PNIBuf(return_);
        RETURN.set(RESULT.MEMORY);
        return WordFactory.pointer(return_.address());
    }

    public static MemorySegment returnDoubleArr;
    public static final CEntryPointLiteral<CFunctionPointer> returnDoubleArrCEPL = GraalUtils.defineCFunctionByName(new PNILinkOptions(), io.vproxy.pni.test.UpcallNull.class, "returnDoubleArr");

    @CEntryPoint
    public static VoidPointer returnDoubleArr(IsolateThread THREAD, VoidPointer return_PTR) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.UpcallNull#returnDoubleArr");
            System.exit(1);
        }
        var return_ = MemorySegment.ofAddress(return_PTR.rawValue());
        var RESULT = IMPL.returnDoubleArr();
        if (RESULT == null) return WordFactory.pointer(0);
        var RETURN = new PNIBuf(return_);
        RETURN.set(RESULT.MEMORY);
        return WordFactory.pointer(return_.address());
    }

    public static MemorySegment returnIntArr;
    public static final CEntryPointLiteral<CFunctionPointer> returnIntArrCEPL = GraalUtils.defineCFunctionByName(new PNILinkOptions(), io.vproxy.pni.test.UpcallNull.class, "returnIntArr");

    @CEntryPoint
    public static VoidPointer returnIntArr(IsolateThread THREAD, VoidPointer return_PTR) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.UpcallNull#returnIntArr");
            System.exit(1);
        }
        var return_ = MemorySegment.ofAddress(return_PTR.rawValue());
        var RESULT = IMPL.returnIntArr();
        if (RESULT == null) return WordFactory.pointer(0);
        var RETURN = new PNIBuf(return_);
        RETURN.set(RESULT.MEMORY);
        return WordFactory.pointer(return_.address());
    }

    public static MemorySegment returnLongArr;
    public static final CEntryPointLiteral<CFunctionPointer> returnLongArrCEPL = GraalUtils.defineCFunctionByName(new PNILinkOptions(), io.vproxy.pni.test.UpcallNull.class, "returnLongArr");

    @CEntryPoint
    public static VoidPointer returnLongArr(IsolateThread THREAD, VoidPointer return_PTR) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.UpcallNull#returnLongArr");
            System.exit(1);
        }
        var return_ = MemorySegment.ofAddress(return_PTR.rawValue());
        var RESULT = IMPL.returnLongArr();
        if (RESULT == null) return WordFactory.pointer(0);
        var RETURN = new PNIBuf(return_);
        RETURN.set(RESULT.MEMORY);
        return WordFactory.pointer(return_.address());
    }

    public static MemorySegment returnShortArr;
    public static final CEntryPointLiteral<CFunctionPointer> returnShortArrCEPL = GraalUtils.defineCFunctionByName(new PNILinkOptions(), io.vproxy.pni.test.UpcallNull.class, "returnShortArr");

    @CEntryPoint
    public static VoidPointer returnShortArr(IsolateThread THREAD, VoidPointer return_PTR) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.UpcallNull#returnShortArr");
            System.exit(1);
        }
        var return_ = MemorySegment.ofAddress(return_PTR.rawValue());
        var RESULT = IMPL.returnShortArr();
        if (RESULT == null) return WordFactory.pointer(0);
        var RETURN = new PNIBuf(return_);
        RETURN.set(RESULT.MEMORY);
        return WordFactory.pointer(return_.address());
    }

    public static MemorySegment returnPArr;
    public static final CEntryPointLiteral<CFunctionPointer> returnPArrCEPL = GraalUtils.defineCFunctionByName(new PNILinkOptions(), io.vproxy.pni.test.UpcallNull.class, "returnPArr");

    @CEntryPoint
    public static VoidPointer returnPArr(IsolateThread THREAD, VoidPointer return_PTR) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.UpcallNull#returnPArr");
            System.exit(1);
        }
        var return_ = MemorySegment.ofAddress(return_PTR.rawValue());
        var RESULT = IMPL.returnPArr();
        if (RESULT == null) return WordFactory.pointer(0);
        var RETURN = new PNIBuf(return_);
        RETURN.set(RESULT.MEMORY);
        return WordFactory.pointer(return_.address());
    }

    public static MemorySegment returnOArr;
    public static final CEntryPointLiteral<CFunctionPointer> returnOArrCEPL = GraalUtils.defineCFunctionByName(new PNILinkOptions(), io.vproxy.pni.test.UpcallNull.class, "returnOArr");

    @CEntryPoint
    public static VoidPointer returnOArr(IsolateThread THREAD, VoidPointer return_PTR) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.UpcallNull#returnOArr");
            System.exit(1);
        }
        var return_ = MemorySegment.ofAddress(return_PTR.rawValue());
        var RESULT = IMPL.returnOArr();
        if (RESULT == null) return WordFactory.pointer(0);
        var RETURN = new PNIBuf(return_);
        RETURN.set(RESULT.MEMORY);
        return WordFactory.pointer(return_.address());
    }

    public static MemorySegment returnRef;
    public static final CEntryPointLiteral<CFunctionPointer> returnRefCEPL = GraalUtils.defineCFunctionByName(new PNILinkOptions(), io.vproxy.pni.test.UpcallNull.class, "returnRef");

    @CEntryPoint
    public static VoidPointer returnRef(IsolateThread THREAD) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.UpcallNull#returnRef");
            System.exit(1);
        }
        var RESULT = IMPL.returnRef();
        return WordFactory.pointer(RESULT == null ? 0 : RESULT.MEMORY.address());
    }

    public static MemorySegment returnFunc;
    public static final CEntryPointLiteral<CFunctionPointer> returnFuncCEPL = GraalUtils.defineCFunctionByName(new PNILinkOptions(), io.vproxy.pni.test.UpcallNull.class, "returnFunc");

    @CEntryPoint
    public static VoidPointer returnFunc(IsolateThread THREAD) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.UpcallNull#returnFunc");
            System.exit(1);
        }
        var RESULT = IMPL.returnFunc();
        return WordFactory.pointer(RESULT == null ? 0 : RESULT.MEMORY.address());
    }

    public static MemorySegment returnFuncVoid;
    public static final CEntryPointLiteral<CFunctionPointer> returnFuncVoidCEPL = GraalUtils.defineCFunctionByName(new PNILinkOptions(), io.vproxy.pni.test.UpcallNull.class, "returnFuncVoid");

    @CEntryPoint
    public static VoidPointer returnFuncVoid(IsolateThread THREAD) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.UpcallNull#returnFuncVoid");
            System.exit(1);
        }
        var RESULT = IMPL.returnFuncVoid();
        return WordFactory.pointer(RESULT == null ? 0 : RESULT.MEMORY.address());
    }

    public static MemorySegment returnFuncRef;
    public static final CEntryPointLiteral<CFunctionPointer> returnFuncRefCEPL = GraalUtils.defineCFunctionByName(new PNILinkOptions(), io.vproxy.pni.test.UpcallNull.class, "returnFuncRef");

    @CEntryPoint
    public static VoidPointer returnFuncRef(IsolateThread THREAD) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.UpcallNull#returnFuncRef");
            System.exit(1);
        }
        var RESULT = IMPL.returnFuncRef();
        return WordFactory.pointer(RESULT == null ? 0 : RESULT.MEMORY.address());
    }

    private static void setNativeImpl() {
        testParam = MemorySegment.ofAddress(testParamCEPL.getFunctionPointer().rawValue());
        testParamRaw = MemorySegment.ofAddress(testParamRawCEPL.getFunctionPointer().rawValue());
        returnO = MemorySegment.ofAddress(returnOCEPL.getFunctionPointer().rawValue());
        returnStr = MemorySegment.ofAddress(returnStrCEPL.getFunctionPointer().rawValue());
        returnSeg = MemorySegment.ofAddress(returnSegCEPL.getFunctionPointer().rawValue());
        returnBuf = MemorySegment.ofAddress(returnBufCEPL.getFunctionPointer().rawValue());
        returnByteArr = MemorySegment.ofAddress(returnByteArrCEPL.getFunctionPointer().rawValue());
        returnBoolArr = MemorySegment.ofAddress(returnBoolArrCEPL.getFunctionPointer().rawValue());
        returnCharArr = MemorySegment.ofAddress(returnCharArrCEPL.getFunctionPointer().rawValue());
        returnFloatArr = MemorySegment.ofAddress(returnFloatArrCEPL.getFunctionPointer().rawValue());
        returnDoubleArr = MemorySegment.ofAddress(returnDoubleArrCEPL.getFunctionPointer().rawValue());
        returnIntArr = MemorySegment.ofAddress(returnIntArrCEPL.getFunctionPointer().rawValue());
        returnLongArr = MemorySegment.ofAddress(returnLongArrCEPL.getFunctionPointer().rawValue());
        returnShortArr = MemorySegment.ofAddress(returnShortArrCEPL.getFunctionPointer().rawValue());
        returnPArr = MemorySegment.ofAddress(returnPArrCEPL.getFunctionPointer().rawValue());
        returnOArr = MemorySegment.ofAddress(returnOArrCEPL.getFunctionPointer().rawValue());
        returnRef = MemorySegment.ofAddress(returnRefCEPL.getFunctionPointer().rawValue());
        returnFunc = MemorySegment.ofAddress(returnFuncCEPL.getFunctionPointer().rawValue());
        returnFuncVoid = MemorySegment.ofAddress(returnFuncVoidCEPL.getFunctionPointer().rawValue());
        returnFuncRef = MemorySegment.ofAddress(returnFuncRefCEPL.getFunctionPointer().rawValue());

        var initMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), void.class, "JavaCritical_io_vproxy_pni_test_UpcallNull_INIT", MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class);
        try {
            initMH.invoke(testParam, testParamRaw, returnO, returnStr, returnSeg, returnBuf, returnByteArr, returnBoolArr, returnCharArr, returnFloatArr, returnDoubleArr, returnIntArr, returnLongArr, returnShortArr, returnPArr, returnOArr, returnRef, returnFunc, returnFuncVoid, returnFuncRef);
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
        testParam = PanamaUtils.lookupFunctionPointer(new PNILookupOptions(), "JavaCritical_io_vproxy_pni_test_UpcallNull_testParam").orElseThrow(() -> new NullPointerException("JavaCritical_io_vproxy_pni_test_UpcallNull_testParam"));
        testParamRaw = PanamaUtils.lookupFunctionPointer(new PNILookupOptions(), "JavaCritical_io_vproxy_pni_test_UpcallNull_testParamRaw").orElseThrow(() -> new NullPointerException("JavaCritical_io_vproxy_pni_test_UpcallNull_testParamRaw"));
        returnO = PanamaUtils.lookupFunctionPointer(new PNILookupOptions(), "JavaCritical_io_vproxy_pni_test_UpcallNull_returnO").orElseThrow(() -> new NullPointerException("JavaCritical_io_vproxy_pni_test_UpcallNull_returnO"));
        returnStr = PanamaUtils.lookupFunctionPointer(new PNILookupOptions(), "JavaCritical_io_vproxy_pni_test_UpcallNull_returnStr").orElseThrow(() -> new NullPointerException("JavaCritical_io_vproxy_pni_test_UpcallNull_returnStr"));
        returnSeg = PanamaUtils.lookupFunctionPointer(new PNILookupOptions(), "JavaCritical_io_vproxy_pni_test_UpcallNull_returnSeg").orElseThrow(() -> new NullPointerException("JavaCritical_io_vproxy_pni_test_UpcallNull_returnSeg"));
        returnBuf = PanamaUtils.lookupFunctionPointer(new PNILookupOptions(), "JavaCritical_io_vproxy_pni_test_UpcallNull_returnBuf").orElseThrow(() -> new NullPointerException("JavaCritical_io_vproxy_pni_test_UpcallNull_returnBuf"));
        returnByteArr = PanamaUtils.lookupFunctionPointer(new PNILookupOptions(), "JavaCritical_io_vproxy_pni_test_UpcallNull_returnByteArr").orElseThrow(() -> new NullPointerException("JavaCritical_io_vproxy_pni_test_UpcallNull_returnByteArr"));
        returnBoolArr = PanamaUtils.lookupFunctionPointer(new PNILookupOptions(), "JavaCritical_io_vproxy_pni_test_UpcallNull_returnBoolArr").orElseThrow(() -> new NullPointerException("JavaCritical_io_vproxy_pni_test_UpcallNull_returnBoolArr"));
        returnCharArr = PanamaUtils.lookupFunctionPointer(new PNILookupOptions(), "JavaCritical_io_vproxy_pni_test_UpcallNull_returnCharArr").orElseThrow(() -> new NullPointerException("JavaCritical_io_vproxy_pni_test_UpcallNull_returnCharArr"));
        returnFloatArr = PanamaUtils.lookupFunctionPointer(new PNILookupOptions(), "JavaCritical_io_vproxy_pni_test_UpcallNull_returnFloatArr").orElseThrow(() -> new NullPointerException("JavaCritical_io_vproxy_pni_test_UpcallNull_returnFloatArr"));
        returnDoubleArr = PanamaUtils.lookupFunctionPointer(new PNILookupOptions(), "JavaCritical_io_vproxy_pni_test_UpcallNull_returnDoubleArr").orElseThrow(() -> new NullPointerException("JavaCritical_io_vproxy_pni_test_UpcallNull_returnDoubleArr"));
        returnIntArr = PanamaUtils.lookupFunctionPointer(new PNILookupOptions(), "JavaCritical_io_vproxy_pni_test_UpcallNull_returnIntArr").orElseThrow(() -> new NullPointerException("JavaCritical_io_vproxy_pni_test_UpcallNull_returnIntArr"));
        returnLongArr = PanamaUtils.lookupFunctionPointer(new PNILookupOptions(), "JavaCritical_io_vproxy_pni_test_UpcallNull_returnLongArr").orElseThrow(() -> new NullPointerException("JavaCritical_io_vproxy_pni_test_UpcallNull_returnLongArr"));
        returnShortArr = PanamaUtils.lookupFunctionPointer(new PNILookupOptions(), "JavaCritical_io_vproxy_pni_test_UpcallNull_returnShortArr").orElseThrow(() -> new NullPointerException("JavaCritical_io_vproxy_pni_test_UpcallNull_returnShortArr"));
        returnPArr = PanamaUtils.lookupFunctionPointer(new PNILookupOptions(), "JavaCritical_io_vproxy_pni_test_UpcallNull_returnPArr").orElseThrow(() -> new NullPointerException("JavaCritical_io_vproxy_pni_test_UpcallNull_returnPArr"));
        returnOArr = PanamaUtils.lookupFunctionPointer(new PNILookupOptions(), "JavaCritical_io_vproxy_pni_test_UpcallNull_returnOArr").orElseThrow(() -> new NullPointerException("JavaCritical_io_vproxy_pni_test_UpcallNull_returnOArr"));
        returnRef = PanamaUtils.lookupFunctionPointer(new PNILookupOptions(), "JavaCritical_io_vproxy_pni_test_UpcallNull_returnRef").orElseThrow(() -> new NullPointerException("JavaCritical_io_vproxy_pni_test_UpcallNull_returnRef"));
        returnFunc = PanamaUtils.lookupFunctionPointer(new PNILookupOptions(), "JavaCritical_io_vproxy_pni_test_UpcallNull_returnFunc").orElseThrow(() -> new NullPointerException("JavaCritical_io_vproxy_pni_test_UpcallNull_returnFunc"));
        returnFuncVoid = PanamaUtils.lookupFunctionPointer(new PNILookupOptions(), "JavaCritical_io_vproxy_pni_test_UpcallNull_returnFuncVoid").orElseThrow(() -> new NullPointerException("JavaCritical_io_vproxy_pni_test_UpcallNull_returnFuncVoid"));
        returnFuncRef = PanamaUtils.lookupFunctionPointer(new PNILookupOptions(), "JavaCritical_io_vproxy_pni_test_UpcallNull_returnFuncRef").orElseThrow(() -> new NullPointerException("JavaCritical_io_vproxy_pni_test_UpcallNull_returnFuncRef"));
    }

    private static Interface IMPL = null;

    public static void setImpl(Interface impl) {
        java.util.Objects.requireNonNull(impl);
        IMPL = impl;
        setNativeImpl();
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
// sha256:573b3557ae7c970d18d115973d587909f39cd6c9fc48bc6074d48a1f34ceb09d
