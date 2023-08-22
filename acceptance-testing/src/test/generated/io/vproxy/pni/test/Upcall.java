package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class Upcall {
    private static final Arena ARENA = Arena.ofShared();

    public static final MemorySegment primaryParams;

    private static void primaryParams(byte b, byte ub, boolean z, char c, double d, float f, int i, int ui, long j, long uj, short s, short us) {
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

    public static final MemorySegment returnByte;

    private static byte returnByte() {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.Upcall#returnByte");
            System.exit(1);
        }
        var RESULT = IMPL.returnByte();
        return RESULT;
    }

    public static final MemorySegment returnBool;

    private static boolean returnBool() {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.Upcall#returnBool");
            System.exit(1);
        }
        var RESULT = IMPL.returnBool();
        return RESULT;
    }

    public static final MemorySegment returnChar;

    private static char returnChar() {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.Upcall#returnChar");
            System.exit(1);
        }
        var RESULT = IMPL.returnChar();
        return RESULT;
    }

    public static final MemorySegment returnDouble;

    private static double returnDouble() {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.Upcall#returnDouble");
            System.exit(1);
        }
        var RESULT = IMPL.returnDouble();
        return RESULT;
    }

    public static final MemorySegment returnFloat;

    private static float returnFloat() {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.Upcall#returnFloat");
            System.exit(1);
        }
        var RESULT = IMPL.returnFloat();
        return RESULT;
    }

    public static final MemorySegment returnInt;

    private static int returnInt() {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.Upcall#returnInt");
            System.exit(1);
        }
        var RESULT = IMPL.returnInt();
        return RESULT;
    }

    public static final MemorySegment returnLong;

    private static long returnLong() {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.Upcall#returnLong");
            System.exit(1);
        }
        var RESULT = IMPL.returnLong();
        return RESULT;
    }

    public static final MemorySegment returnShort;

    private static short returnShort() {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.Upcall#returnShort");
            System.exit(1);
        }
        var RESULT = IMPL.returnShort();
        return RESULT;
    }

    public static final MemorySegment primaryArrayParams;

    private static void primaryArrayParams(MemorySegment b, MemorySegment ub, MemorySegment z, MemorySegment c, MemorySegment d, MemorySegment f, MemorySegment i, MemorySegment ui, MemorySegment j, MemorySegment uj, MemorySegment s, MemorySegment us) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.Upcall#primaryArrayParams");
            System.exit(1);
        }
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

    public static final MemorySegment returnByteArray;

    private static MemorySegment returnByteArray(MemorySegment return_) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.Upcall#returnByteArray");
            System.exit(1);
        }
        var RESULT = IMPL.returnByteArray();
        if (RESULT == null) return MemorySegment.NULL;
        var RETURN = new PNIBuf(return_);
        RETURN.set(RESULT);
        return return_;
    }

    public static final MemorySegment returnBoolArray;

    private static MemorySegment returnBoolArray(MemorySegment return_) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.Upcall#returnBoolArray");
            System.exit(1);
        }
        var RESULT = IMPL.returnBoolArray();
        if (RESULT == null) return MemorySegment.NULL;
        var RETURN = new PNIBuf(return_);
        RETURN.set(RESULT.MEMORY);
        return return_;
    }

    public static final MemorySegment returnCharArray;

    private static MemorySegment returnCharArray(MemorySegment return_) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.Upcall#returnCharArray");
            System.exit(1);
        }
        var RESULT = IMPL.returnCharArray();
        if (RESULT == null) return MemorySegment.NULL;
        var RETURN = new PNIBuf(return_);
        RETURN.set(RESULT.MEMORY);
        return return_;
    }

    public static final MemorySegment returnDoubleArray;

    private static MemorySegment returnDoubleArray(MemorySegment return_) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.Upcall#returnDoubleArray");
            System.exit(1);
        }
        var RESULT = IMPL.returnDoubleArray();
        if (RESULT == null) return MemorySegment.NULL;
        var RETURN = new PNIBuf(return_);
        RETURN.set(RESULT.MEMORY);
        return return_;
    }

    public static final MemorySegment returnFloatArray;

    private static MemorySegment returnFloatArray(MemorySegment return_) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.Upcall#returnFloatArray");
            System.exit(1);
        }
        var RESULT = IMPL.returnFloatArray();
        if (RESULT == null) return MemorySegment.NULL;
        var RETURN = new PNIBuf(return_);
        RETURN.set(RESULT.MEMORY);
        return return_;
    }

    public static final MemorySegment returnIntArray;

    private static MemorySegment returnIntArray(MemorySegment return_) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.Upcall#returnIntArray");
            System.exit(1);
        }
        var RESULT = IMPL.returnIntArray();
        if (RESULT == null) return MemorySegment.NULL;
        var RETURN = new PNIBuf(return_);
        RETURN.set(RESULT.MEMORY);
        return return_;
    }

    public static final MemorySegment returnLongArray;

    private static MemorySegment returnLongArray(MemorySegment return_) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.Upcall#returnLongArray");
            System.exit(1);
        }
        var RESULT = IMPL.returnLongArray();
        if (RESULT == null) return MemorySegment.NULL;
        var RETURN = new PNIBuf(return_);
        RETURN.set(RESULT.MEMORY);
        return return_;
    }

    public static final MemorySegment returnShortArray;

    private static MemorySegment returnShortArray(MemorySegment return_) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.Upcall#returnShortArray");
            System.exit(1);
        }
        var RESULT = IMPL.returnShortArray();
        if (RESULT == null) return MemorySegment.NULL;
        var RETURN = new PNIBuf(return_);
        RETURN.set(RESULT.MEMORY);
        return return_;
    }

    public static final MemorySegment objectParams;

    private static void objectParams(MemorySegment o) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.Upcall#objectParams");
            System.exit(1);
        }
        IMPL.objectParams(
            (o.address() == 0 ? null : new io.vproxy.pni.test.ObjectStruct(o))
        );
    }

    public static final MemorySegment returnObject;

    private static MemorySegment returnObject(MemorySegment return_) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.Upcall#returnObject");
            System.exit(1);
        }
        var RESULT = IMPL.returnObject(
            new io.vproxy.pni.test.ObjectStruct(return_)
        );
        return RESULT == null ? MemorySegment.NULL : RESULT.MEMORY;
    }

    public static final MemorySegment objectArrayParams;

    private static void objectArrayParams(MemorySegment o) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.Upcall#objectArrayParams");
            System.exit(1);
        }
        IMPL.objectArrayParams(
            (o.address() == 0 ? null : new io.vproxy.pni.test.ObjectStruct.Array(new PNIBuf(o).get()))
        );
    }

    public static final MemorySegment returnObjectArray;

    private static MemorySegment returnObjectArray(MemorySegment return_) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.Upcall#returnObjectArray");
            System.exit(1);
        }
        var RESULT = IMPL.returnObjectArray();
        if (RESULT == null) return MemorySegment.NULL;
        var RETURN = new PNIBuf(return_);
        RETURN.set(RESULT.MEMORY);
        return return_;
    }

    public static final MemorySegment otherParams;

    private static void otherParams(MemorySegment buffer, MemorySegment voidCallSite, MemorySegment objCallSite, MemorySegment refCallSite, MemorySegment mem, MemorySegment voidFunc, MemorySegment objFunc, MemorySegment refFunc, MemorySegment ref, MemorySegment rawRef, MemorySegment str) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.Upcall#otherParams");
            System.exit(1);
        }
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

    public static final MemorySegment returnBuffer;

    private static MemorySegment returnBuffer(MemorySegment return_) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.Upcall#returnBuffer");
            System.exit(1);
        }
        var RESULT = IMPL.returnBuffer();
        if (RESULT == null) return MemorySegment.NULL;
        var RETURN = new PNIBuf(return_);
        RETURN.set(RESULT);
        return return_;
    }

    public static final MemorySegment returnMem;

    private static MemorySegment returnMem() {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.Upcall#returnMem");
            System.exit(1);
        }
        var RESULT = IMPL.returnMem();
        return RESULT == null ? MemorySegment.NULL : RESULT;
    }

    public static final MemorySegment returnVoidFunc;

    private static MemorySegment returnVoidFunc() {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.Upcall#returnVoidFunc");
            System.exit(1);
        }
        var RESULT = IMPL.returnVoidFunc();
        return RESULT == null ? MemorySegment.NULL : RESULT.MEMORY;
    }

    public static final MemorySegment returnObjFunc;

    private static MemorySegment returnObjFunc() {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.Upcall#returnObjFunc");
            System.exit(1);
        }
        var RESULT = IMPL.returnObjFunc();
        return RESULT == null ? MemorySegment.NULL : RESULT.MEMORY;
    }

    public static final MemorySegment returnRefFunc;

    private static MemorySegment returnRefFunc() {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.Upcall#returnRefFunc");
            System.exit(1);
        }
        var RESULT = IMPL.returnRefFunc();
        return RESULT == null ? MemorySegment.NULL : RESULT.MEMORY;
    }

    public static final MemorySegment returnRef;

    private static MemorySegment returnRef() {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.Upcall#returnRef");
            System.exit(1);
        }
        var RESULT = IMPL.returnRef();
        return RESULT == null ? MemorySegment.NULL : RESULT.MEMORY;
    }

    public static final MemorySegment returnStr;

    private static MemorySegment returnStr() {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.Upcall#returnStr");
            System.exit(1);
        }
        var RESULT = IMPL.returnStr();
        return RESULT == null ? MemorySegment.NULL : RESULT.MEMORY;
    }

    static {
        MethodHandle primaryParamsMH;
        MethodHandle returnByteMH;
        MethodHandle returnBoolMH;
        MethodHandle returnCharMH;
        MethodHandle returnDoubleMH;
        MethodHandle returnFloatMH;
        MethodHandle returnIntMH;
        MethodHandle returnLongMH;
        MethodHandle returnShortMH;
        MethodHandle primaryArrayParamsMH;
        MethodHandle returnByteArrayMH;
        MethodHandle returnBoolArrayMH;
        MethodHandle returnCharArrayMH;
        MethodHandle returnDoubleArrayMH;
        MethodHandle returnFloatArrayMH;
        MethodHandle returnIntArrayMH;
        MethodHandle returnLongArrayMH;
        MethodHandle returnShortArrayMH;
        MethodHandle objectParamsMH;
        MethodHandle returnObjectMH;
        MethodHandle objectArrayParamsMH;
        MethodHandle returnObjectArrayMH;
        MethodHandle otherParamsMH;
        MethodHandle returnBufferMH;
        MethodHandle returnMemMH;
        MethodHandle returnVoidFuncMH;
        MethodHandle returnObjFuncMH;
        MethodHandle returnRefFuncMH;
        MethodHandle returnRefMH;
        MethodHandle returnStrMH;
        try {
            primaryParamsMH = MethodHandles.lookup().findStatic(io.vproxy.pni.test.Upcall.class, "primaryParams", MethodType.methodType(void.class, byte.class, byte.class, boolean.class, char.class, double.class, float.class, int.class, int.class, long.class, long.class, short.class, short.class));
            returnByteMH = MethodHandles.lookup().findStatic(io.vproxy.pni.test.Upcall.class, "returnByte", MethodType.methodType(byte.class));
            returnBoolMH = MethodHandles.lookup().findStatic(io.vproxy.pni.test.Upcall.class, "returnBool", MethodType.methodType(boolean.class));
            returnCharMH = MethodHandles.lookup().findStatic(io.vproxy.pni.test.Upcall.class, "returnChar", MethodType.methodType(char.class));
            returnDoubleMH = MethodHandles.lookup().findStatic(io.vproxy.pni.test.Upcall.class, "returnDouble", MethodType.methodType(double.class));
            returnFloatMH = MethodHandles.lookup().findStatic(io.vproxy.pni.test.Upcall.class, "returnFloat", MethodType.methodType(float.class));
            returnIntMH = MethodHandles.lookup().findStatic(io.vproxy.pni.test.Upcall.class, "returnInt", MethodType.methodType(int.class));
            returnLongMH = MethodHandles.lookup().findStatic(io.vproxy.pni.test.Upcall.class, "returnLong", MethodType.methodType(long.class));
            returnShortMH = MethodHandles.lookup().findStatic(io.vproxy.pni.test.Upcall.class, "returnShort", MethodType.methodType(short.class));
            primaryArrayParamsMH = MethodHandles.lookup().findStatic(io.vproxy.pni.test.Upcall.class, "primaryArrayParams", MethodType.methodType(void.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class));
            returnByteArrayMH = MethodHandles.lookup().findStatic(io.vproxy.pni.test.Upcall.class, "returnByteArray", MethodType.methodType(MemorySegment.class, MemorySegment.class));
            returnBoolArrayMH = MethodHandles.lookup().findStatic(io.vproxy.pni.test.Upcall.class, "returnBoolArray", MethodType.methodType(MemorySegment.class, MemorySegment.class));
            returnCharArrayMH = MethodHandles.lookup().findStatic(io.vproxy.pni.test.Upcall.class, "returnCharArray", MethodType.methodType(MemorySegment.class, MemorySegment.class));
            returnDoubleArrayMH = MethodHandles.lookup().findStatic(io.vproxy.pni.test.Upcall.class, "returnDoubleArray", MethodType.methodType(MemorySegment.class, MemorySegment.class));
            returnFloatArrayMH = MethodHandles.lookup().findStatic(io.vproxy.pni.test.Upcall.class, "returnFloatArray", MethodType.methodType(MemorySegment.class, MemorySegment.class));
            returnIntArrayMH = MethodHandles.lookup().findStatic(io.vproxy.pni.test.Upcall.class, "returnIntArray", MethodType.methodType(MemorySegment.class, MemorySegment.class));
            returnLongArrayMH = MethodHandles.lookup().findStatic(io.vproxy.pni.test.Upcall.class, "returnLongArray", MethodType.methodType(MemorySegment.class, MemorySegment.class));
            returnShortArrayMH = MethodHandles.lookup().findStatic(io.vproxy.pni.test.Upcall.class, "returnShortArray", MethodType.methodType(MemorySegment.class, MemorySegment.class));
            objectParamsMH = MethodHandles.lookup().findStatic(io.vproxy.pni.test.Upcall.class, "objectParams", MethodType.methodType(void.class, MemorySegment.class));
            returnObjectMH = MethodHandles.lookup().findStatic(io.vproxy.pni.test.Upcall.class, "returnObject", MethodType.methodType(MemorySegment.class, MemorySegment.class));
            objectArrayParamsMH = MethodHandles.lookup().findStatic(io.vproxy.pni.test.Upcall.class, "objectArrayParams", MethodType.methodType(void.class, MemorySegment.class));
            returnObjectArrayMH = MethodHandles.lookup().findStatic(io.vproxy.pni.test.Upcall.class, "returnObjectArray", MethodType.methodType(MemorySegment.class, MemorySegment.class));
            otherParamsMH = MethodHandles.lookup().findStatic(io.vproxy.pni.test.Upcall.class, "otherParams", MethodType.methodType(void.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class));
            returnBufferMH = MethodHandles.lookup().findStatic(io.vproxy.pni.test.Upcall.class, "returnBuffer", MethodType.methodType(MemorySegment.class, MemorySegment.class));
            returnMemMH = MethodHandles.lookup().findStatic(io.vproxy.pni.test.Upcall.class, "returnMem", MethodType.methodType(MemorySegment.class));
            returnVoidFuncMH = MethodHandles.lookup().findStatic(io.vproxy.pni.test.Upcall.class, "returnVoidFunc", MethodType.methodType(MemorySegment.class));
            returnObjFuncMH = MethodHandles.lookup().findStatic(io.vproxy.pni.test.Upcall.class, "returnObjFunc", MethodType.methodType(MemorySegment.class));
            returnRefFuncMH = MethodHandles.lookup().findStatic(io.vproxy.pni.test.Upcall.class, "returnRefFunc", MethodType.methodType(MemorySegment.class));
            returnRefMH = MethodHandles.lookup().findStatic(io.vproxy.pni.test.Upcall.class, "returnRef", MethodType.methodType(MemorySegment.class));
            returnStrMH = MethodHandles.lookup().findStatic(io.vproxy.pni.test.Upcall.class, "returnStr", MethodType.methodType(MemorySegment.class));
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
        primaryParams = PanamaUtils.defineCFunction(ARENA, primaryParamsMH, void.class, byte.class, byte.class, boolean.class, char.class, double.class, float.class, int.class, int.class, long.class, long.class, short.class, short.class);
        returnByte = PanamaUtils.defineCFunction(ARENA, returnByteMH, byte.class);
        returnBool = PanamaUtils.defineCFunction(ARENA, returnBoolMH, boolean.class);
        returnChar = PanamaUtils.defineCFunction(ARENA, returnCharMH, char.class);
        returnDouble = PanamaUtils.defineCFunction(ARENA, returnDoubleMH, double.class);
        returnFloat = PanamaUtils.defineCFunction(ARENA, returnFloatMH, float.class);
        returnInt = PanamaUtils.defineCFunction(ARENA, returnIntMH, int.class);
        returnLong = PanamaUtils.defineCFunction(ARENA, returnLongMH, long.class);
        returnShort = PanamaUtils.defineCFunction(ARENA, returnShortMH, short.class);
        primaryArrayParams = PanamaUtils.defineCFunction(ARENA, primaryArrayParamsMH, void.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class);
        returnByteArray = PanamaUtils.defineCFunction(ARENA, returnByteArrayMH, MemorySegment.class, MemorySegment.class);
        returnBoolArray = PanamaUtils.defineCFunction(ARENA, returnBoolArrayMH, MemorySegment.class, MemorySegment.class);
        returnCharArray = PanamaUtils.defineCFunction(ARENA, returnCharArrayMH, MemorySegment.class, MemorySegment.class);
        returnDoubleArray = PanamaUtils.defineCFunction(ARENA, returnDoubleArrayMH, MemorySegment.class, MemorySegment.class);
        returnFloatArray = PanamaUtils.defineCFunction(ARENA, returnFloatArrayMH, MemorySegment.class, MemorySegment.class);
        returnIntArray = PanamaUtils.defineCFunction(ARENA, returnIntArrayMH, MemorySegment.class, MemorySegment.class);
        returnLongArray = PanamaUtils.defineCFunction(ARENA, returnLongArrayMH, MemorySegment.class, MemorySegment.class);
        returnShortArray = PanamaUtils.defineCFunction(ARENA, returnShortArrayMH, MemorySegment.class, MemorySegment.class);
        objectParams = PanamaUtils.defineCFunction(ARENA, objectParamsMH, void.class, MemorySegment.class);
        returnObject = PanamaUtils.defineCFunction(ARENA, returnObjectMH, MemorySegment.class, MemorySegment.class);
        objectArrayParams = PanamaUtils.defineCFunction(ARENA, objectArrayParamsMH, void.class, MemorySegment.class);
        returnObjectArray = PanamaUtils.defineCFunction(ARENA, returnObjectArrayMH, MemorySegment.class, MemorySegment.class);
        otherParams = PanamaUtils.defineCFunction(ARENA, otherParamsMH, void.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class);
        returnBuffer = PanamaUtils.defineCFunction(ARENA, returnBufferMH, MemorySegment.class, MemorySegment.class);
        returnMem = PanamaUtils.defineCFunction(ARENA, returnMemMH, MemorySegment.class);
        returnVoidFunc = PanamaUtils.defineCFunction(ARENA, returnVoidFuncMH, MemorySegment.class);
        returnObjFunc = PanamaUtils.defineCFunction(ARENA, returnObjFuncMH, MemorySegment.class);
        returnRefFunc = PanamaUtils.defineCFunction(ARENA, returnRefFuncMH, MemorySegment.class);
        returnRef = PanamaUtils.defineCFunction(ARENA, returnRefMH, MemorySegment.class);
        returnStr = PanamaUtils.defineCFunction(ARENA, returnStrMH, MemorySegment.class);

        var initMH = PanamaUtils.lookupPNICriticalFunction(true, void.class, "JavaCritical_io_vproxy_pni_test_Upcall_INIT", MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class);
        try {
            initMH.invoke(primaryParams, returnByte, returnBool, returnChar, returnDouble, returnFloat, returnInt, returnLong, returnShort, primaryArrayParams, returnByteArray, returnBoolArray, returnCharArray, returnDoubleArray, returnFloatArray, returnIntArray, returnLongArray, returnShortArray, objectParams, returnObject, objectArrayParams, returnObjectArray, otherParams, returnBuffer, returnMem, returnVoidFunc, returnObjFunc, returnRefFunc, returnRef, returnStr);
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
    }
}
// metadata.generator-version: pni test
// sha256:ecffd50795e1b182170935a2d26dfd4e3c3e7dfe2b1e1ec33f32fb1ec4614a33
