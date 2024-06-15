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

public class Upcall {
    private static final Arena ARENA = Arena.ofShared();

    public static MemorySegment primaryParams;
    public static final CEntryPointLiteral<CFunctionPointer> primaryParamsCEPL = GraalUtils.defineCFunctionByName(new PNILinkOptions(), io.vproxy.pni.test.Upcall.class, "primaryParams");

    @CEntryPoint
    public static void primaryParams(IsolateThread THREAD, byte b, byte ub, boolean z, char c, double d, float f, int i, int ui, long j, long uj, short s, short us) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.Upcall#primaryParams");
            System.exit(1);
        }
        IMPL.primaryParams(
            b,
            ub,
            z,
            c,
            d,
            f,
            i,
            ui,
            j,
            uj,
            s,
            us
        );
    }

    public static MemorySegment returnByte;
    public static final CEntryPointLiteral<CFunctionPointer> returnByteCEPL = GraalUtils.defineCFunctionByName(new PNILinkOptions(), io.vproxy.pni.test.Upcall.class, "returnByte");

    @CEntryPoint
    public static byte returnByte(IsolateThread THREAD) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.Upcall#returnByte");
            System.exit(1);
        }
        var RESULT = IMPL.returnByte();
        return RESULT;
    }

    public static MemorySegment returnBool;
    public static final CEntryPointLiteral<CFunctionPointer> returnBoolCEPL = GraalUtils.defineCFunctionByName(new PNILinkOptions(), io.vproxy.pni.test.Upcall.class, "returnBool");

    @CEntryPoint
    public static boolean returnBool(IsolateThread THREAD) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.Upcall#returnBool");
            System.exit(1);
        }
        var RESULT = IMPL.returnBool();
        return RESULT;
    }

    public static MemorySegment returnChar;
    public static final CEntryPointLiteral<CFunctionPointer> returnCharCEPL = GraalUtils.defineCFunctionByName(new PNILinkOptions(), io.vproxy.pni.test.Upcall.class, "returnChar");

    @CEntryPoint
    public static char returnChar(IsolateThread THREAD) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.Upcall#returnChar");
            System.exit(1);
        }
        var RESULT = IMPL.returnChar();
        return RESULT;
    }

    public static MemorySegment returnDouble;
    public static final CEntryPointLiteral<CFunctionPointer> returnDoubleCEPL = GraalUtils.defineCFunctionByName(new PNILinkOptions(), io.vproxy.pni.test.Upcall.class, "returnDouble");

    @CEntryPoint
    public static double returnDouble(IsolateThread THREAD) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.Upcall#returnDouble");
            System.exit(1);
        }
        var RESULT = IMPL.returnDouble();
        return RESULT;
    }

    public static MemorySegment returnFloat;
    public static final CEntryPointLiteral<CFunctionPointer> returnFloatCEPL = GraalUtils.defineCFunctionByName(new PNILinkOptions(), io.vproxy.pni.test.Upcall.class, "returnFloat");

    @CEntryPoint
    public static float returnFloat(IsolateThread THREAD) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.Upcall#returnFloat");
            System.exit(1);
        }
        var RESULT = IMPL.returnFloat();
        return RESULT;
    }

    public static MemorySegment returnInt;
    public static final CEntryPointLiteral<CFunctionPointer> returnIntCEPL = GraalUtils.defineCFunctionByName(new PNILinkOptions(), io.vproxy.pni.test.Upcall.class, "returnInt");

    @CEntryPoint
    public static int returnInt(IsolateThread THREAD) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.Upcall#returnInt");
            System.exit(1);
        }
        var RESULT = IMPL.returnInt();
        return RESULT;
    }

    public static MemorySegment returnLong;
    public static final CEntryPointLiteral<CFunctionPointer> returnLongCEPL = GraalUtils.defineCFunctionByName(new PNILinkOptions(), io.vproxy.pni.test.Upcall.class, "returnLong");

    @CEntryPoint
    public static long returnLong(IsolateThread THREAD) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.Upcall#returnLong");
            System.exit(1);
        }
        var RESULT = IMPL.returnLong();
        return RESULT;
    }

    public static MemorySegment returnShort;
    public static final CEntryPointLiteral<CFunctionPointer> returnShortCEPL = GraalUtils.defineCFunctionByName(new PNILinkOptions(), io.vproxy.pni.test.Upcall.class, "returnShort");

    @CEntryPoint
    public static short returnShort(IsolateThread THREAD) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.Upcall#returnShort");
            System.exit(1);
        }
        var RESULT = IMPL.returnShort();
        return RESULT;
    }

    public static MemorySegment primaryArrayParams;
    public static final CEntryPointLiteral<CFunctionPointer> primaryArrayParamsCEPL = GraalUtils.defineCFunctionByName(new PNILinkOptions(), io.vproxy.pni.test.Upcall.class, "primaryArrayParams");

    @CEntryPoint
    public static void primaryArrayParams(IsolateThread THREAD, VoidPointer bPTR, VoidPointer ubPTR, VoidPointer zPTR, VoidPointer cPTR, VoidPointer dPTR, VoidPointer fPTR, VoidPointer iPTR, VoidPointer uiPTR, VoidPointer jPTR, VoidPointer ujPTR, VoidPointer sPTR, VoidPointer usPTR) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.Upcall#primaryArrayParams");
            System.exit(1);
        }
        var b = MemorySegment.ofAddress(bPTR.rawValue());
        var ub = MemorySegment.ofAddress(ubPTR.rawValue());
        var z = MemorySegment.ofAddress(zPTR.rawValue());
        var c = MemorySegment.ofAddress(cPTR.rawValue());
        var d = MemorySegment.ofAddress(dPTR.rawValue());
        var f = MemorySegment.ofAddress(fPTR.rawValue());
        var i = MemorySegment.ofAddress(iPTR.rawValue());
        var ui = MemorySegment.ofAddress(uiPTR.rawValue());
        var j = MemorySegment.ofAddress(jPTR.rawValue());
        var uj = MemorySegment.ofAddress(ujPTR.rawValue());
        var s = MemorySegment.ofAddress(sPTR.rawValue());
        var us = MemorySegment.ofAddress(usPTR.rawValue());
        IMPL.primaryArrayParams(
            (b.address() == 0 ? null : new PNIBuf(b).get()),
            (ub.address() == 0 ? null : new PNIBuf(ub).get()),
            (z.address() == 0 ? null : new PNIBuf(z).toBoolArray()),
            (c.address() == 0 ? null : new PNIBuf(c).toCharArray()),
            (d.address() == 0 ? null : new PNIBuf(d).toDoubleArray()),
            (f.address() == 0 ? null : new PNIBuf(f).toFloatArray()),
            (i.address() == 0 ? null : new PNIBuf(i).toIntArray()),
            (ui.address() == 0 ? null : new PNIBuf(ui).toIntArray()),
            (j.address() == 0 ? null : new PNIBuf(j).toLongArray()),
            (uj.address() == 0 ? null : new PNIBuf(uj).toLongArray()),
            (s.address() == 0 ? null : new PNIBuf(s).toShortArray()),
            (us.address() == 0 ? null : new PNIBuf(us).toShortArray())
        );
    }

    public static MemorySegment returnByteArray;
    public static final CEntryPointLiteral<CFunctionPointer> returnByteArrayCEPL = GraalUtils.defineCFunctionByName(new PNILinkOptions(), io.vproxy.pni.test.Upcall.class, "returnByteArray");

    @CEntryPoint
    public static VoidPointer returnByteArray(IsolateThread THREAD, VoidPointer return_PTR) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.Upcall#returnByteArray");
            System.exit(1);
        }
        var return_ = MemorySegment.ofAddress(return_PTR.rawValue());
        var RESULT = IMPL.returnByteArray();
        if (RESULT == null) return WordFactory.pointer(0);
        var RETURN = new PNIBuf(return_);
        RETURN.set(RESULT);
        return WordFactory.pointer(return_.address());
    }

    public static MemorySegment returnBoolArray;
    public static final CEntryPointLiteral<CFunctionPointer> returnBoolArrayCEPL = GraalUtils.defineCFunctionByName(new PNILinkOptions(), io.vproxy.pni.test.Upcall.class, "returnBoolArray");

    @CEntryPoint
    public static VoidPointer returnBoolArray(IsolateThread THREAD, VoidPointer return_PTR) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.Upcall#returnBoolArray");
            System.exit(1);
        }
        var return_ = MemorySegment.ofAddress(return_PTR.rawValue());
        var RESULT = IMPL.returnBoolArray();
        if (RESULT == null) return WordFactory.pointer(0);
        var RETURN = new PNIBuf(return_);
        RETURN.set(RESULT.MEMORY);
        return WordFactory.pointer(return_.address());
    }

    public static MemorySegment returnCharArray;
    public static final CEntryPointLiteral<CFunctionPointer> returnCharArrayCEPL = GraalUtils.defineCFunctionByName(new PNILinkOptions(), io.vproxy.pni.test.Upcall.class, "returnCharArray");

    @CEntryPoint
    public static VoidPointer returnCharArray(IsolateThread THREAD, VoidPointer return_PTR) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.Upcall#returnCharArray");
            System.exit(1);
        }
        var return_ = MemorySegment.ofAddress(return_PTR.rawValue());
        var RESULT = IMPL.returnCharArray();
        if (RESULT == null) return WordFactory.pointer(0);
        var RETURN = new PNIBuf(return_);
        RETURN.set(RESULT.MEMORY);
        return WordFactory.pointer(return_.address());
    }

    public static MemorySegment returnDoubleArray;
    public static final CEntryPointLiteral<CFunctionPointer> returnDoubleArrayCEPL = GraalUtils.defineCFunctionByName(new PNILinkOptions(), io.vproxy.pni.test.Upcall.class, "returnDoubleArray");

    @CEntryPoint
    public static VoidPointer returnDoubleArray(IsolateThread THREAD, VoidPointer return_PTR) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.Upcall#returnDoubleArray");
            System.exit(1);
        }
        var return_ = MemorySegment.ofAddress(return_PTR.rawValue());
        var RESULT = IMPL.returnDoubleArray();
        if (RESULT == null) return WordFactory.pointer(0);
        var RETURN = new PNIBuf(return_);
        RETURN.set(RESULT.MEMORY);
        return WordFactory.pointer(return_.address());
    }

    public static MemorySegment returnFloatArray;
    public static final CEntryPointLiteral<CFunctionPointer> returnFloatArrayCEPL = GraalUtils.defineCFunctionByName(new PNILinkOptions(), io.vproxy.pni.test.Upcall.class, "returnFloatArray");

    @CEntryPoint
    public static VoidPointer returnFloatArray(IsolateThread THREAD, VoidPointer return_PTR) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.Upcall#returnFloatArray");
            System.exit(1);
        }
        var return_ = MemorySegment.ofAddress(return_PTR.rawValue());
        var RESULT = IMPL.returnFloatArray();
        if (RESULT == null) return WordFactory.pointer(0);
        var RETURN = new PNIBuf(return_);
        RETURN.set(RESULT.MEMORY);
        return WordFactory.pointer(return_.address());
    }

    public static MemorySegment returnIntArray;
    public static final CEntryPointLiteral<CFunctionPointer> returnIntArrayCEPL = GraalUtils.defineCFunctionByName(new PNILinkOptions(), io.vproxy.pni.test.Upcall.class, "returnIntArray");

    @CEntryPoint
    public static VoidPointer returnIntArray(IsolateThread THREAD, VoidPointer return_PTR) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.Upcall#returnIntArray");
            System.exit(1);
        }
        var return_ = MemorySegment.ofAddress(return_PTR.rawValue());
        var RESULT = IMPL.returnIntArray();
        if (RESULT == null) return WordFactory.pointer(0);
        var RETURN = new PNIBuf(return_);
        RETURN.set(RESULT.MEMORY);
        return WordFactory.pointer(return_.address());
    }

    public static MemorySegment returnLongArray;
    public static final CEntryPointLiteral<CFunctionPointer> returnLongArrayCEPL = GraalUtils.defineCFunctionByName(new PNILinkOptions(), io.vproxy.pni.test.Upcall.class, "returnLongArray");

    @CEntryPoint
    public static VoidPointer returnLongArray(IsolateThread THREAD, VoidPointer return_PTR) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.Upcall#returnLongArray");
            System.exit(1);
        }
        var return_ = MemorySegment.ofAddress(return_PTR.rawValue());
        var RESULT = IMPL.returnLongArray();
        if (RESULT == null) return WordFactory.pointer(0);
        var RETURN = new PNIBuf(return_);
        RETURN.set(RESULT.MEMORY);
        return WordFactory.pointer(return_.address());
    }

    public static MemorySegment returnShortArray;
    public static final CEntryPointLiteral<CFunctionPointer> returnShortArrayCEPL = GraalUtils.defineCFunctionByName(new PNILinkOptions(), io.vproxy.pni.test.Upcall.class, "returnShortArray");

    @CEntryPoint
    public static VoidPointer returnShortArray(IsolateThread THREAD, VoidPointer return_PTR) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.Upcall#returnShortArray");
            System.exit(1);
        }
        var return_ = MemorySegment.ofAddress(return_PTR.rawValue());
        var RESULT = IMPL.returnShortArray();
        if (RESULT == null) return WordFactory.pointer(0);
        var RETURN = new PNIBuf(return_);
        RETURN.set(RESULT.MEMORY);
        return WordFactory.pointer(return_.address());
    }

    public static MemorySegment objectParams;
    public static final CEntryPointLiteral<CFunctionPointer> objectParamsCEPL = GraalUtils.defineCFunctionByName(new PNILinkOptions(), io.vproxy.pni.test.Upcall.class, "objectParams");

    @CEntryPoint
    public static void objectParams(IsolateThread THREAD, VoidPointer oPTR) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.Upcall#objectParams");
            System.exit(1);
        }
        var o = MemorySegment.ofAddress(oPTR.rawValue());
        IMPL.objectParams(
            (o.address() == 0 ? null : new io.vproxy.pni.test.ObjectStruct(o))
        );
    }

    public static MemorySegment returnObject;
    public static final CEntryPointLiteral<CFunctionPointer> returnObjectCEPL = GraalUtils.defineCFunctionByName(new PNILinkOptions(), io.vproxy.pni.test.Upcall.class, "returnObject");

    @CEntryPoint
    public static VoidPointer returnObject(IsolateThread THREAD, VoidPointer return_PTR) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.Upcall#returnObject");
            System.exit(1);
        }
        var return_ = MemorySegment.ofAddress(return_PTR.rawValue());
        var RESULT = IMPL.returnObject(
            new io.vproxy.pni.test.ObjectStruct(return_)
        );
        return WordFactory.pointer(RESULT == null ? 0 : RESULT.MEMORY.address());
    }

    public static MemorySegment pointerArrayParams;
    public static final CEntryPointLiteral<CFunctionPointer> pointerArrayParamsCEPL = GraalUtils.defineCFunctionByName(new PNILinkOptions(), io.vproxy.pni.test.Upcall.class, "pointerArrayParams");

    @CEntryPoint
    public static void pointerArrayParams(IsolateThread THREAD, VoidPointer pPTR) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.Upcall#pointerArrayParams");
            System.exit(1);
        }
        var p = MemorySegment.ofAddress(pPTR.rawValue());
        IMPL.pointerArrayParams(
            (p.address() == 0 ? null : new PNIBuf(p).toPointerArray())
        );
    }

    public static MemorySegment returnPointerArray;
    public static final CEntryPointLiteral<CFunctionPointer> returnPointerArrayCEPL = GraalUtils.defineCFunctionByName(new PNILinkOptions(), io.vproxy.pni.test.Upcall.class, "returnPointerArray");

    @CEntryPoint
    public static VoidPointer returnPointerArray(IsolateThread THREAD, VoidPointer return_PTR) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.Upcall#returnPointerArray");
            System.exit(1);
        }
        var return_ = MemorySegment.ofAddress(return_PTR.rawValue());
        var RESULT = IMPL.returnPointerArray();
        if (RESULT == null) return WordFactory.pointer(0);
        var RETURN = new PNIBuf(return_);
        RETURN.set(RESULT.MEMORY);
        return WordFactory.pointer(return_.address());
    }

    public static MemorySegment objectArrayParams;
    public static final CEntryPointLiteral<CFunctionPointer> objectArrayParamsCEPL = GraalUtils.defineCFunctionByName(new PNILinkOptions(), io.vproxy.pni.test.Upcall.class, "objectArrayParams");

    @CEntryPoint
    public static void objectArrayParams(IsolateThread THREAD, VoidPointer oPTR) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.Upcall#objectArrayParams");
            System.exit(1);
        }
        var o = MemorySegment.ofAddress(oPTR.rawValue());
        IMPL.objectArrayParams(
            (o.address() == 0 ? null : new io.vproxy.pni.test.ObjectStruct.Array(new PNIBuf(o).get()))
        );
    }

    public static MemorySegment returnObjectArray;
    public static final CEntryPointLiteral<CFunctionPointer> returnObjectArrayCEPL = GraalUtils.defineCFunctionByName(new PNILinkOptions(), io.vproxy.pni.test.Upcall.class, "returnObjectArray");

    @CEntryPoint
    public static VoidPointer returnObjectArray(IsolateThread THREAD, VoidPointer return_PTR) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.Upcall#returnObjectArray");
            System.exit(1);
        }
        var return_ = MemorySegment.ofAddress(return_PTR.rawValue());
        var RESULT = IMPL.returnObjectArray();
        if (RESULT == null) return WordFactory.pointer(0);
        var RETURN = new PNIBuf(return_);
        RETURN.set(RESULT.MEMORY);
        return WordFactory.pointer(return_.address());
    }

    public static MemorySegment otherParams;
    public static final CEntryPointLiteral<CFunctionPointer> otherParamsCEPL = GraalUtils.defineCFunctionByName(new PNILinkOptions(), io.vproxy.pni.test.Upcall.class, "otherParams");

    @CEntryPoint
    public static void otherParams(IsolateThread THREAD, VoidPointer bufferPTR, VoidPointer voidCallSitePTR, VoidPointer objCallSitePTR, VoidPointer refCallSitePTR, VoidPointer memPTR, VoidPointer voidFuncPTR, VoidPointer objFuncPTR, VoidPointer refFuncPTR, VoidPointer refPTR, VoidPointer rawRefPTR, VoidPointer strPTR) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.Upcall#otherParams");
            System.exit(1);
        }
        var buffer = MemorySegment.ofAddress(bufferPTR.rawValue());
        var voidCallSite = MemorySegment.ofAddress(voidCallSitePTR.rawValue());
        var objCallSite = MemorySegment.ofAddress(objCallSitePTR.rawValue());
        var refCallSite = MemorySegment.ofAddress(refCallSitePTR.rawValue());
        var mem = MemorySegment.ofAddress(memPTR.rawValue());
        var voidFunc = MemorySegment.ofAddress(voidFuncPTR.rawValue());
        var objFunc = MemorySegment.ofAddress(objFuncPTR.rawValue());
        var refFunc = MemorySegment.ofAddress(refFuncPTR.rawValue());
        var ref = MemorySegment.ofAddress(refPTR.rawValue());
        var rawRef = MemorySegment.ofAddress(rawRefPTR.rawValue());
        var str = MemorySegment.ofAddress(strPTR.rawValue());
        IMPL.otherParams(
            (buffer.address() == 0 ? null : new PNIBuf(buffer).toByteBuffer()),
            (voidCallSite.address() == 0 ? null : PNIFunc.VoidFunc.of(voidCallSite).getCallSite()),
            (objCallSite.address() == 0 ? null : io.vproxy.pni.test.ObjectStruct.Func.of(objCallSite).getCallSite()),
            (refCallSite.address() == 0 ? null : (io.vproxy.pni.CallSite) PNIRef.Func.of(refCallSite).getCallSite()),
            (mem.address() == 0 ? null : mem),
            (voidFunc.address() == 0 ? null : PNIFunc.VoidFunc.of(voidFunc)),
            (objFunc.address() == 0 ? null : io.vproxy.pni.test.ObjectStruct.Func.of(objFunc)),
            (refFunc.address() == 0 ? null : PNIRef.Func.of(refFunc)),
            (ref.address() == 0 ? null : PNIRef.getRef(ref)),
            (rawRef.address() == 0 ? null : PNIRef.of(rawRef)),
            (str.address() == 0 ? null : new PNIString(str))
        );
    }

    public static MemorySegment returnBuffer;
    public static final CEntryPointLiteral<CFunctionPointer> returnBufferCEPL = GraalUtils.defineCFunctionByName(new PNILinkOptions(), io.vproxy.pni.test.Upcall.class, "returnBuffer");

    @CEntryPoint
    public static VoidPointer returnBuffer(IsolateThread THREAD, VoidPointer return_PTR) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.Upcall#returnBuffer");
            System.exit(1);
        }
        var return_ = MemorySegment.ofAddress(return_PTR.rawValue());
        var RESULT = IMPL.returnBuffer();
        if (RESULT == null) return WordFactory.pointer(0);
        var RETURN = new PNIBuf(return_);
        RETURN.set(RESULT);
        return WordFactory.pointer(return_.address());
    }

    public static MemorySegment returnMem;
    public static final CEntryPointLiteral<CFunctionPointer> returnMemCEPL = GraalUtils.defineCFunctionByName(new PNILinkOptions(), io.vproxy.pni.test.Upcall.class, "returnMem");

    @CEntryPoint
    public static VoidPointer returnMem(IsolateThread THREAD) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.Upcall#returnMem");
            System.exit(1);
        }
        var RESULT = IMPL.returnMem();
        return WordFactory.pointer(RESULT == null ? 0 : RESULT.address());
    }

    public static MemorySegment returnVoidFunc;
    public static final CEntryPointLiteral<CFunctionPointer> returnVoidFuncCEPL = GraalUtils.defineCFunctionByName(new PNILinkOptions(), io.vproxy.pni.test.Upcall.class, "returnVoidFunc");

    @CEntryPoint
    public static VoidPointer returnVoidFunc(IsolateThread THREAD) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.Upcall#returnVoidFunc");
            System.exit(1);
        }
        var RESULT = IMPL.returnVoidFunc();
        return WordFactory.pointer(RESULT == null ? 0 : RESULT.MEMORY.address());
    }

    public static MemorySegment returnObjFunc;
    public static final CEntryPointLiteral<CFunctionPointer> returnObjFuncCEPL = GraalUtils.defineCFunctionByName(new PNILinkOptions(), io.vproxy.pni.test.Upcall.class, "returnObjFunc");

    @CEntryPoint
    public static VoidPointer returnObjFunc(IsolateThread THREAD) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.Upcall#returnObjFunc");
            System.exit(1);
        }
        var RESULT = IMPL.returnObjFunc();
        return WordFactory.pointer(RESULT == null ? 0 : RESULT.MEMORY.address());
    }

    public static MemorySegment returnRefFunc;
    public static final CEntryPointLiteral<CFunctionPointer> returnRefFuncCEPL = GraalUtils.defineCFunctionByName(new PNILinkOptions(), io.vproxy.pni.test.Upcall.class, "returnRefFunc");

    @CEntryPoint
    public static VoidPointer returnRefFunc(IsolateThread THREAD) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.Upcall#returnRefFunc");
            System.exit(1);
        }
        var RESULT = IMPL.returnRefFunc();
        return WordFactory.pointer(RESULT == null ? 0 : RESULT.MEMORY.address());
    }

    public static MemorySegment returnRef;
    public static final CEntryPointLiteral<CFunctionPointer> returnRefCEPL = GraalUtils.defineCFunctionByName(new PNILinkOptions(), io.vproxy.pni.test.Upcall.class, "returnRef");

    @CEntryPoint
    public static VoidPointer returnRef(IsolateThread THREAD) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.Upcall#returnRef");
            System.exit(1);
        }
        var RESULT = IMPL.returnRef();
        return WordFactory.pointer(RESULT == null ? 0 : RESULT.MEMORY.address());
    }

    public static MemorySegment returnStr;
    public static final CEntryPointLiteral<CFunctionPointer> returnStrCEPL = GraalUtils.defineCFunctionByName(new PNILinkOptions(), io.vproxy.pni.test.Upcall.class, "returnStr");

    @CEntryPoint
    public static VoidPointer returnStr(IsolateThread THREAD) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.Upcall#returnStr");
            System.exit(1);
        }
        var RESULT = IMPL.returnStr();
        return WordFactory.pointer(RESULT == null ? 0 : RESULT.MEMORY.address());
    }

    public static MemorySegment sum;
    public static final CEntryPointLiteral<CFunctionPointer> sumCEPL = GraalUtils.defineCFunctionByName(new PNILinkOptions(), io.vproxy.pni.test.Upcall.class, "sum");

    @CEntryPoint
    public static int sum(IsolateThread THREAD, int a, int b) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.Upcall#sum");
            System.exit(1);
        }
        var RESULT = IMPL.sum(
            a,
            b
        );
        return RESULT;
    }

    private static void setNativeImpl() {
        primaryParams = MemorySegment.ofAddress(primaryParamsCEPL.getFunctionPointer().rawValue());
        returnByte = MemorySegment.ofAddress(returnByteCEPL.getFunctionPointer().rawValue());
        returnBool = MemorySegment.ofAddress(returnBoolCEPL.getFunctionPointer().rawValue());
        returnChar = MemorySegment.ofAddress(returnCharCEPL.getFunctionPointer().rawValue());
        returnDouble = MemorySegment.ofAddress(returnDoubleCEPL.getFunctionPointer().rawValue());
        returnFloat = MemorySegment.ofAddress(returnFloatCEPL.getFunctionPointer().rawValue());
        returnInt = MemorySegment.ofAddress(returnIntCEPL.getFunctionPointer().rawValue());
        returnLong = MemorySegment.ofAddress(returnLongCEPL.getFunctionPointer().rawValue());
        returnShort = MemorySegment.ofAddress(returnShortCEPL.getFunctionPointer().rawValue());
        primaryArrayParams = MemorySegment.ofAddress(primaryArrayParamsCEPL.getFunctionPointer().rawValue());
        returnByteArray = MemorySegment.ofAddress(returnByteArrayCEPL.getFunctionPointer().rawValue());
        returnBoolArray = MemorySegment.ofAddress(returnBoolArrayCEPL.getFunctionPointer().rawValue());
        returnCharArray = MemorySegment.ofAddress(returnCharArrayCEPL.getFunctionPointer().rawValue());
        returnDoubleArray = MemorySegment.ofAddress(returnDoubleArrayCEPL.getFunctionPointer().rawValue());
        returnFloatArray = MemorySegment.ofAddress(returnFloatArrayCEPL.getFunctionPointer().rawValue());
        returnIntArray = MemorySegment.ofAddress(returnIntArrayCEPL.getFunctionPointer().rawValue());
        returnLongArray = MemorySegment.ofAddress(returnLongArrayCEPL.getFunctionPointer().rawValue());
        returnShortArray = MemorySegment.ofAddress(returnShortArrayCEPL.getFunctionPointer().rawValue());
        objectParams = MemorySegment.ofAddress(objectParamsCEPL.getFunctionPointer().rawValue());
        returnObject = MemorySegment.ofAddress(returnObjectCEPL.getFunctionPointer().rawValue());
        pointerArrayParams = MemorySegment.ofAddress(pointerArrayParamsCEPL.getFunctionPointer().rawValue());
        returnPointerArray = MemorySegment.ofAddress(returnPointerArrayCEPL.getFunctionPointer().rawValue());
        objectArrayParams = MemorySegment.ofAddress(objectArrayParamsCEPL.getFunctionPointer().rawValue());
        returnObjectArray = MemorySegment.ofAddress(returnObjectArrayCEPL.getFunctionPointer().rawValue());
        otherParams = MemorySegment.ofAddress(otherParamsCEPL.getFunctionPointer().rawValue());
        returnBuffer = MemorySegment.ofAddress(returnBufferCEPL.getFunctionPointer().rawValue());
        returnMem = MemorySegment.ofAddress(returnMemCEPL.getFunctionPointer().rawValue());
        returnVoidFunc = MemorySegment.ofAddress(returnVoidFuncCEPL.getFunctionPointer().rawValue());
        returnObjFunc = MemorySegment.ofAddress(returnObjFuncCEPL.getFunctionPointer().rawValue());
        returnRefFunc = MemorySegment.ofAddress(returnRefFuncCEPL.getFunctionPointer().rawValue());
        returnRef = MemorySegment.ofAddress(returnRefCEPL.getFunctionPointer().rawValue());
        returnStr = MemorySegment.ofAddress(returnStrCEPL.getFunctionPointer().rawValue());
        sum = MemorySegment.ofAddress(sumCEPL.getFunctionPointer().rawValue());

        var initMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), void.class, "JavaCritical_io_vproxy_pni_test_Upcall_INIT", MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class);
        try {
            initMH.invoke(primaryParams, returnByte, returnBool, returnChar, returnDouble, returnFloat, returnInt, returnLong, returnShort, primaryArrayParams, returnByteArray, returnBoolArray, returnCharArray, returnDoubleArray, returnFloatArray, returnIntArray, returnLongArray, returnShortArray, objectParams, returnObject, pointerArrayParams, returnPointerArray, objectArrayParams, returnObjectArray, otherParams, returnBuffer, returnMem, returnVoidFunc, returnObjFunc, returnRefFunc, returnRef, returnStr, sum);
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
        primaryParams = PanamaUtils.lookupFunctionPointer(new PNILookupOptions(), "JavaCritical_io_vproxy_pni_test_Upcall_primaryParams").orElseThrow(() -> new NullPointerException("JavaCritical_io_vproxy_pni_test_Upcall_primaryParams"));
        returnByte = PanamaUtils.lookupFunctionPointer(new PNILookupOptions(), "JavaCritical_io_vproxy_pni_test_Upcall_returnByte").orElseThrow(() -> new NullPointerException("JavaCritical_io_vproxy_pni_test_Upcall_returnByte"));
        returnBool = PanamaUtils.lookupFunctionPointer(new PNILookupOptions(), "JavaCritical_io_vproxy_pni_test_Upcall_returnBool").orElseThrow(() -> new NullPointerException("JavaCritical_io_vproxy_pni_test_Upcall_returnBool"));
        returnChar = PanamaUtils.lookupFunctionPointer(new PNILookupOptions(), "JavaCritical_io_vproxy_pni_test_Upcall_returnChar").orElseThrow(() -> new NullPointerException("JavaCritical_io_vproxy_pni_test_Upcall_returnChar"));
        returnDouble = PanamaUtils.lookupFunctionPointer(new PNILookupOptions(), "JavaCritical_io_vproxy_pni_test_Upcall_returnDouble").orElseThrow(() -> new NullPointerException("JavaCritical_io_vproxy_pni_test_Upcall_returnDouble"));
        returnFloat = PanamaUtils.lookupFunctionPointer(new PNILookupOptions(), "JavaCritical_io_vproxy_pni_test_Upcall_returnFloat").orElseThrow(() -> new NullPointerException("JavaCritical_io_vproxy_pni_test_Upcall_returnFloat"));
        returnInt = PanamaUtils.lookupFunctionPointer(new PNILookupOptions(), "JavaCritical_io_vproxy_pni_test_Upcall_returnInt").orElseThrow(() -> new NullPointerException("JavaCritical_io_vproxy_pni_test_Upcall_returnInt"));
        returnLong = PanamaUtils.lookupFunctionPointer(new PNILookupOptions(), "JavaCritical_io_vproxy_pni_test_Upcall_returnLong").orElseThrow(() -> new NullPointerException("JavaCritical_io_vproxy_pni_test_Upcall_returnLong"));
        returnShort = PanamaUtils.lookupFunctionPointer(new PNILookupOptions(), "JavaCritical_io_vproxy_pni_test_Upcall_returnShort").orElseThrow(() -> new NullPointerException("JavaCritical_io_vproxy_pni_test_Upcall_returnShort"));
        primaryArrayParams = PanamaUtils.lookupFunctionPointer(new PNILookupOptions(), "JavaCritical_io_vproxy_pni_test_Upcall_primaryArrayParams").orElseThrow(() -> new NullPointerException("JavaCritical_io_vproxy_pni_test_Upcall_primaryArrayParams"));
        returnByteArray = PanamaUtils.lookupFunctionPointer(new PNILookupOptions(), "JavaCritical_io_vproxy_pni_test_Upcall_returnByteArray").orElseThrow(() -> new NullPointerException("JavaCritical_io_vproxy_pni_test_Upcall_returnByteArray"));
        returnBoolArray = PanamaUtils.lookupFunctionPointer(new PNILookupOptions(), "JavaCritical_io_vproxy_pni_test_Upcall_returnBoolArray").orElseThrow(() -> new NullPointerException("JavaCritical_io_vproxy_pni_test_Upcall_returnBoolArray"));
        returnCharArray = PanamaUtils.lookupFunctionPointer(new PNILookupOptions(), "JavaCritical_io_vproxy_pni_test_Upcall_returnCharArray").orElseThrow(() -> new NullPointerException("JavaCritical_io_vproxy_pni_test_Upcall_returnCharArray"));
        returnDoubleArray = PanamaUtils.lookupFunctionPointer(new PNILookupOptions(), "JavaCritical_io_vproxy_pni_test_Upcall_returnDoubleArray").orElseThrow(() -> new NullPointerException("JavaCritical_io_vproxy_pni_test_Upcall_returnDoubleArray"));
        returnFloatArray = PanamaUtils.lookupFunctionPointer(new PNILookupOptions(), "JavaCritical_io_vproxy_pni_test_Upcall_returnFloatArray").orElseThrow(() -> new NullPointerException("JavaCritical_io_vproxy_pni_test_Upcall_returnFloatArray"));
        returnIntArray = PanamaUtils.lookupFunctionPointer(new PNILookupOptions(), "JavaCritical_io_vproxy_pni_test_Upcall_returnIntArray").orElseThrow(() -> new NullPointerException("JavaCritical_io_vproxy_pni_test_Upcall_returnIntArray"));
        returnLongArray = PanamaUtils.lookupFunctionPointer(new PNILookupOptions(), "JavaCritical_io_vproxy_pni_test_Upcall_returnLongArray").orElseThrow(() -> new NullPointerException("JavaCritical_io_vproxy_pni_test_Upcall_returnLongArray"));
        returnShortArray = PanamaUtils.lookupFunctionPointer(new PNILookupOptions(), "JavaCritical_io_vproxy_pni_test_Upcall_returnShortArray").orElseThrow(() -> new NullPointerException("JavaCritical_io_vproxy_pni_test_Upcall_returnShortArray"));
        objectParams = PanamaUtils.lookupFunctionPointer(new PNILookupOptions(), "JavaCritical_io_vproxy_pni_test_Upcall_objectParams").orElseThrow(() -> new NullPointerException("JavaCritical_io_vproxy_pni_test_Upcall_objectParams"));
        returnObject = PanamaUtils.lookupFunctionPointer(new PNILookupOptions(), "JavaCritical_io_vproxy_pni_test_Upcall_returnObject").orElseThrow(() -> new NullPointerException("JavaCritical_io_vproxy_pni_test_Upcall_returnObject"));
        pointerArrayParams = PanamaUtils.lookupFunctionPointer(new PNILookupOptions(), "JavaCritical_io_vproxy_pni_test_Upcall_pointerArrayParams").orElseThrow(() -> new NullPointerException("JavaCritical_io_vproxy_pni_test_Upcall_pointerArrayParams"));
        returnPointerArray = PanamaUtils.lookupFunctionPointer(new PNILookupOptions(), "JavaCritical_io_vproxy_pni_test_Upcall_returnPointerArray").orElseThrow(() -> new NullPointerException("JavaCritical_io_vproxy_pni_test_Upcall_returnPointerArray"));
        objectArrayParams = PanamaUtils.lookupFunctionPointer(new PNILookupOptions(), "JavaCritical_io_vproxy_pni_test_Upcall_objectArrayParams").orElseThrow(() -> new NullPointerException("JavaCritical_io_vproxy_pni_test_Upcall_objectArrayParams"));
        returnObjectArray = PanamaUtils.lookupFunctionPointer(new PNILookupOptions(), "JavaCritical_io_vproxy_pni_test_Upcall_returnObjectArray").orElseThrow(() -> new NullPointerException("JavaCritical_io_vproxy_pni_test_Upcall_returnObjectArray"));
        otherParams = PanamaUtils.lookupFunctionPointer(new PNILookupOptions(), "JavaCritical_io_vproxy_pni_test_Upcall_otherParams").orElseThrow(() -> new NullPointerException("JavaCritical_io_vproxy_pni_test_Upcall_otherParams"));
        returnBuffer = PanamaUtils.lookupFunctionPointer(new PNILookupOptions(), "JavaCritical_io_vproxy_pni_test_Upcall_returnBuffer").orElseThrow(() -> new NullPointerException("JavaCritical_io_vproxy_pni_test_Upcall_returnBuffer"));
        returnMem = PanamaUtils.lookupFunctionPointer(new PNILookupOptions(), "JavaCritical_io_vproxy_pni_test_Upcall_returnMem").orElseThrow(() -> new NullPointerException("JavaCritical_io_vproxy_pni_test_Upcall_returnMem"));
        returnVoidFunc = PanamaUtils.lookupFunctionPointer(new PNILookupOptions(), "JavaCritical_io_vproxy_pni_test_Upcall_returnVoidFunc").orElseThrow(() -> new NullPointerException("JavaCritical_io_vproxy_pni_test_Upcall_returnVoidFunc"));
        returnObjFunc = PanamaUtils.lookupFunctionPointer(new PNILookupOptions(), "JavaCritical_io_vproxy_pni_test_Upcall_returnObjFunc").orElseThrow(() -> new NullPointerException("JavaCritical_io_vproxy_pni_test_Upcall_returnObjFunc"));
        returnRefFunc = PanamaUtils.lookupFunctionPointer(new PNILookupOptions(), "JavaCritical_io_vproxy_pni_test_Upcall_returnRefFunc").orElseThrow(() -> new NullPointerException("JavaCritical_io_vproxy_pni_test_Upcall_returnRefFunc"));
        returnRef = PanamaUtils.lookupFunctionPointer(new PNILookupOptions(), "JavaCritical_io_vproxy_pni_test_Upcall_returnRef").orElseThrow(() -> new NullPointerException("JavaCritical_io_vproxy_pni_test_Upcall_returnRef"));
        returnStr = PanamaUtils.lookupFunctionPointer(new PNILookupOptions(), "JavaCritical_io_vproxy_pni_test_Upcall_returnStr").orElseThrow(() -> new NullPointerException("JavaCritical_io_vproxy_pni_test_Upcall_returnStr"));
        sum = PanamaUtils.lookupFunctionPointer(new PNILookupOptions(), "pni_sum").orElseThrow(() -> new NullPointerException("pni_sum"));
    }

    private static Interface IMPL = null;

    public static void setImpl(Interface impl) {
        java.util.Objects.requireNonNull(impl);
        IMPL = impl;
        setNativeImpl();
    }

    public interface Interface {
        void primaryParams(byte b, byte ub, boolean z, char c, double d, float f, int i, int ui, long j, long uj, short s, short us);

        byte returnByte();

        boolean returnBool();

        char returnChar();

        double returnDouble();

        float returnFloat();

        int returnInt();

        long returnLong();

        short returnShort();

        void primaryArrayParams(MemorySegment b, MemorySegment ub, BoolArray z, CharArray c, DoubleArray d, FloatArray f, IntArray i, IntArray ui, LongArray j, LongArray uj, ShortArray s, ShortArray us);

        MemorySegment returnByteArray();

        BoolArray returnBoolArray();

        CharArray returnCharArray();

        DoubleArray returnDoubleArray();

        FloatArray returnFloatArray();

        IntArray returnIntArray();

        LongArray returnLongArray();

        ShortArray returnShortArray();

        void objectParams(io.vproxy.pni.test.ObjectStruct o);

        io.vproxy.pni.test.ObjectStruct returnObject(io.vproxy.pni.test.ObjectStruct return_);

        void pointerArrayParams(PointerArray p);

        PointerArray returnPointerArray();

        void objectArrayParams(io.vproxy.pni.test.ObjectStruct.Array o);

        io.vproxy.pni.test.ObjectStruct.Array returnObjectArray();

        void otherParams(ByteBuffer buffer, io.vproxy.pni.CallSite<Void> voidCallSite, io.vproxy.pni.CallSite<io.vproxy.pni.test.ObjectStruct> objCallSite, io.vproxy.pni.CallSite<java.util.List<java.lang.String>> refCallSite, MemorySegment mem, PNIFunc<Void> voidFunc, PNIFunc<io.vproxy.pni.test.ObjectStruct> objFunc, PNIFunc<java.util.List<java.lang.String>> refFunc, java.util.List<java.lang.Integer> ref, PNIRef<java.util.List<java.lang.Integer>> rawRef, PNIString str);

        ByteBuffer returnBuffer();

        MemorySegment returnMem();

        PNIFunc<Void> returnVoidFunc();

        PNIFunc<io.vproxy.pni.test.ObjectStruct> returnObjFunc();

        PNIFunc<java.util.List<java.lang.String>> returnRefFunc();

        PNIRef<java.util.List<java.lang.Integer>> returnRef();

        PNIString returnStr();

        int sum(int a, int b);
    }
}
// metadata.generator-version: pni test
// sha256:fe096b5dbb13c4b29c2495084a538e7422705892e916cf99824cba1eded8bdb2
