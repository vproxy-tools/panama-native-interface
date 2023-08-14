package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class DefiningCFunction {
    private DefiningCFunction() {
    }

    private static final DefiningCFunction INSTANCE = new DefiningCFunction();

    public static DefiningCFunction get() {
        return INSTANCE;
    }

    private static final MethodHandle upcallVoidNoParamMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_DefiningCFunction_upcallVoidNoParam", MemorySegment.class /* func */);

    public void upcallVoidNoParam(PNIEnv ENV, MemorySegment func) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.upcallVoidNoParamMH.invokeExact(ENV.MEMORY, (MemorySegment) (func == null ? MemorySegment.NULL : func));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
    }

    private static final MethodHandle upcallVoid1ParamMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_DefiningCFunction_upcallVoid1Param", MemorySegment.class /* func */, MemorySegment.class /* data */);

    public void upcallVoid1Param(PNIEnv ENV, MemorySegment func, MemorySegment data) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.upcallVoid1ParamMH.invokeExact(ENV.MEMORY, (MemorySegment) (func == null ? MemorySegment.NULL : func), (MemorySegment) (data == null ? MemorySegment.NULL : data));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
    }

    private static final MethodHandle upcallVoid2ParamMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_DefiningCFunction_upcallVoid2Param", MemorySegment.class /* func */, MemorySegment.class /* data */, byte.class /* b */);

    public void upcallVoid2Param(PNIEnv ENV, MemorySegment func, MemorySegment data, byte b) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.upcallVoid2ParamMH.invokeExact(ENV.MEMORY, (MemorySegment) (func == null ? MemorySegment.NULL : func), (MemorySegment) (data == null ? MemorySegment.NULL : data), b);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
    }

    private static final MethodHandle upcallVoid3ParamMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_DefiningCFunction_upcallVoid3Param", MemorySegment.class /* func */, MemorySegment.class /* data */, boolean.class /* z */, char.class /* c */);

    public void upcallVoid3Param(PNIEnv ENV, MemorySegment func, MemorySegment data, boolean z, char c) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.upcallVoid3ParamMH.invokeExact(ENV.MEMORY, (MemorySegment) (func == null ? MemorySegment.NULL : func), (MemorySegment) (data == null ? MemorySegment.NULL : data), z, c);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
    }

    private static final MethodHandle upcallVoid4ParamMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_DefiningCFunction_upcallVoid4Param", MemorySegment.class /* func */, MemorySegment.class /* data */, double.class /* d */, float.class /* f */, int.class /* i */);

    public void upcallVoid4Param(PNIEnv ENV, MemorySegment func, MemorySegment data, double d, float f, int i) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.upcallVoid4ParamMH.invokeExact(ENV.MEMORY, (MemorySegment) (func == null ? MemorySegment.NULL : func), (MemorySegment) (data == null ? MemorySegment.NULL : data), d, f, i);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
    }

    private static final MethodHandle upcallVoid3Param2MH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_DefiningCFunction_upcallVoid3Param2", MemorySegment.class /* func */, MemorySegment.class /* data */, long.class /* l */, short.class /* s */);

    public void upcallVoid3Param2(PNIEnv ENV, MemorySegment func, MemorySegment data, long l, short s) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.upcallVoid3Param2MH.invokeExact(ENV.MEMORY, (MemorySegment) (func == null ? MemorySegment.NULL : func), (MemorySegment) (data == null ? MemorySegment.NULL : data), l, s);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
    }

    private static final MethodHandle upcallReturnByteNoParamMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_DefiningCFunction_upcallReturnByteNoParam", MemorySegment.class /* func */);

    public byte upcallReturnByteNoParam(PNIEnv ENV, MemorySegment func) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.upcallReturnByteNoParamMH.invokeExact(ENV.MEMORY, (MemorySegment) (func == null ? MemorySegment.NULL : func));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnByte();
    }

    private static final MethodHandle upcallReturnBoolNoParamMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_DefiningCFunction_upcallReturnBoolNoParam", MemorySegment.class /* func */);

    public boolean upcallReturnBoolNoParam(PNIEnv ENV, MemorySegment func) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.upcallReturnBoolNoParamMH.invokeExact(ENV.MEMORY, (MemorySegment) (func == null ? MemorySegment.NULL : func));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnBool();
    }

    private static final MethodHandle upcallReturnCharNoParamMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_DefiningCFunction_upcallReturnCharNoParam", MemorySegment.class /* func */);

    public char upcallReturnCharNoParam(PNIEnv ENV, MemorySegment func) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.upcallReturnCharNoParamMH.invokeExact(ENV.MEMORY, (MemorySegment) (func == null ? MemorySegment.NULL : func));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnChar();
    }

    private static final MethodHandle upcallReturnDoubleNoParamMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_DefiningCFunction_upcallReturnDoubleNoParam", MemorySegment.class /* func */);

    public double upcallReturnDoubleNoParam(PNIEnv ENV, MemorySegment func) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.upcallReturnDoubleNoParamMH.invokeExact(ENV.MEMORY, (MemorySegment) (func == null ? MemorySegment.NULL : func));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnDouble();
    }

    private static final MethodHandle upcallReturnFloatNoParamMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_DefiningCFunction_upcallReturnFloatNoParam", MemorySegment.class /* func */);

    public float upcallReturnFloatNoParam(PNIEnv ENV, MemorySegment func) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.upcallReturnFloatNoParamMH.invokeExact(ENV.MEMORY, (MemorySegment) (func == null ? MemorySegment.NULL : func));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnFloat();
    }

    private static final MethodHandle upcallReturnIntNoParamMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_DefiningCFunction_upcallReturnIntNoParam", MemorySegment.class /* func */);

    public int upcallReturnIntNoParam(PNIEnv ENV, MemorySegment func) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.upcallReturnIntNoParamMH.invokeExact(ENV.MEMORY, (MemorySegment) (func == null ? MemorySegment.NULL : func));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnInt();
    }

    private static final MethodHandle upcallReturnLongNoParamMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_DefiningCFunction_upcallReturnLongNoParam", MemorySegment.class /* func */);

    public long upcallReturnLongNoParam(PNIEnv ENV, MemorySegment func) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.upcallReturnLongNoParamMH.invokeExact(ENV.MEMORY, (MemorySegment) (func == null ? MemorySegment.NULL : func));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnLong();
    }

    private static final MethodHandle upcallReturnShortNoParamMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_DefiningCFunction_upcallReturnShortNoParam", MemorySegment.class /* func */);

    public short upcallReturnShortNoParam(PNIEnv ENV, MemorySegment func) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.upcallReturnShortNoParamMH.invokeExact(ENV.MEMORY, (MemorySegment) (func == null ? MemorySegment.NULL : func));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnShort();
    }

    private static final MethodHandle upcallReturnPointerNoParamMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_DefiningCFunction_upcallReturnPointerNoParam", MemorySegment.class /* func */);

    public MemorySegment upcallReturnPointerNoParam(PNIEnv ENV, MemorySegment func) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.upcallReturnPointerNoParamMH.invokeExact(ENV.MEMORY, (MemorySegment) (func == null ? MemorySegment.NULL : func));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnPointer();
    }

    private static final MethodHandle upcallReturnByte1ParamMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_DefiningCFunction_upcallReturnByte1Param", MemorySegment.class /* func */, byte.class /* b */);

    public byte upcallReturnByte1Param(PNIEnv ENV, MemorySegment func, byte b) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.upcallReturnByte1ParamMH.invokeExact(ENV.MEMORY, (MemorySegment) (func == null ? MemorySegment.NULL : func), b);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnByte();
    }

    private static final MethodHandle upcallReturnBool1ParamMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_DefiningCFunction_upcallReturnBool1Param", MemorySegment.class /* func */, boolean.class /* z */);

    public boolean upcallReturnBool1Param(PNIEnv ENV, MemorySegment func, boolean z) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.upcallReturnBool1ParamMH.invokeExact(ENV.MEMORY, (MemorySegment) (func == null ? MemorySegment.NULL : func), z);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnBool();
    }

    private static final MethodHandle upcallReturnChar1ParamMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_DefiningCFunction_upcallReturnChar1Param", MemorySegment.class /* func */, char.class /* c */);

    public char upcallReturnChar1Param(PNIEnv ENV, MemorySegment func, char c) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.upcallReturnChar1ParamMH.invokeExact(ENV.MEMORY, (MemorySegment) (func == null ? MemorySegment.NULL : func), c);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnChar();
    }

    private static final MethodHandle upcallReturnDouble1ParamMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_DefiningCFunction_upcallReturnDouble1Param", MemorySegment.class /* func */, double.class /* d */);

    public double upcallReturnDouble1Param(PNIEnv ENV, MemorySegment func, double d) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.upcallReturnDouble1ParamMH.invokeExact(ENV.MEMORY, (MemorySegment) (func == null ? MemorySegment.NULL : func), d);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnDouble();
    }

    private static final MethodHandle upcallReturnFloat1ParamMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_DefiningCFunction_upcallReturnFloat1Param", MemorySegment.class /* func */, float.class /* f */);

    public float upcallReturnFloat1Param(PNIEnv ENV, MemorySegment func, float f) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.upcallReturnFloat1ParamMH.invokeExact(ENV.MEMORY, (MemorySegment) (func == null ? MemorySegment.NULL : func), f);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnFloat();
    }

    private static final MethodHandle upcallReturnInt1ParamMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_DefiningCFunction_upcallReturnInt1Param", MemorySegment.class /* func */, int.class /* i */);

    public int upcallReturnInt1Param(PNIEnv ENV, MemorySegment func, int i) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.upcallReturnInt1ParamMH.invokeExact(ENV.MEMORY, (MemorySegment) (func == null ? MemorySegment.NULL : func), i);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnInt();
    }

    private static final MethodHandle upcallReturnLong1ParamMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_DefiningCFunction_upcallReturnLong1Param", MemorySegment.class /* func */, long.class /* j */);

    public long upcallReturnLong1Param(PNIEnv ENV, MemorySegment func, long j) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.upcallReturnLong1ParamMH.invokeExact(ENV.MEMORY, (MemorySegment) (func == null ? MemorySegment.NULL : func), j);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnLong();
    }

    private static final MethodHandle upcallReturnShort1ParamMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_DefiningCFunction_upcallReturnShort1Param", MemorySegment.class /* func */, short.class /* s */);

    public short upcallReturnShort1Param(PNIEnv ENV, MemorySegment func, short s) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.upcallReturnShort1ParamMH.invokeExact(ENV.MEMORY, (MemorySegment) (func == null ? MemorySegment.NULL : func), s);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnShort();
    }

    private static final MethodHandle upcallReturnPointer1ParamMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_DefiningCFunction_upcallReturnPointer1Param", MemorySegment.class /* func */, MemorySegment.class /* p */);

    public MemorySegment upcallReturnPointer1Param(PNIEnv ENV, MemorySegment func, MemorySegment p) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.upcallReturnPointer1ParamMH.invokeExact(ENV.MEMORY, (MemorySegment) (func == null ? MemorySegment.NULL : func), (MemorySegment) (p == null ? MemorySegment.NULL : p));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnPointer();
    }
}
// metadata.generator-version: pni test
// sha256:1226b9f34dcc229cf3cdfc9883ff80d1b84e0f2a50b004a0cc0dc3fdfd7334cc
