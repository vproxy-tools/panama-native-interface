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

    private final MethodHandle upcallVoidNoParam = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_DefiningCFunction_upcallVoidNoParam", MemorySegment.class /* func */);

    public void upcallVoidNoParam(PNIEnv ENV, MemorySegment func) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.upcallVoidNoParam.invokeExact(ENV.MEMORY, (MemorySegment) (func == null ? MemorySegment.NULL : func));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
    }

    private final MethodHandle upcallVoid1Param = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_DefiningCFunction_upcallVoid1Param", MemorySegment.class /* func */, MemorySegment.class /* data */);

    public void upcallVoid1Param(PNIEnv ENV, MemorySegment func, MemorySegment data) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.upcallVoid1Param.invokeExact(ENV.MEMORY, (MemorySegment) (func == null ? MemorySegment.NULL : func), (MemorySegment) (data == null ? MemorySegment.NULL : data));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
    }

    private final MethodHandle upcallVoid2Param = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_DefiningCFunction_upcallVoid2Param", MemorySegment.class /* func */, MemorySegment.class /* data */, byte.class /* b */);

    public void upcallVoid2Param(PNIEnv ENV, MemorySegment func, MemorySegment data, byte b) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.upcallVoid2Param.invokeExact(ENV.MEMORY, (MemorySegment) (func == null ? MemorySegment.NULL : func), (MemorySegment) (data == null ? MemorySegment.NULL : data), b);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
    }

    private final MethodHandle upcallVoid3Param = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_DefiningCFunction_upcallVoid3Param", MemorySegment.class /* func */, MemorySegment.class /* data */, boolean.class /* z */, char.class /* c */);

    public void upcallVoid3Param(PNIEnv ENV, MemorySegment func, MemorySegment data, boolean z, char c) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.upcallVoid3Param.invokeExact(ENV.MEMORY, (MemorySegment) (func == null ? MemorySegment.NULL : func), (MemorySegment) (data == null ? MemorySegment.NULL : data), z, c);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
    }

    private final MethodHandle upcallVoid4Param = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_DefiningCFunction_upcallVoid4Param", MemorySegment.class /* func */, MemorySegment.class /* data */, double.class /* d */, float.class /* f */, int.class /* i */);

    public void upcallVoid4Param(PNIEnv ENV, MemorySegment func, MemorySegment data, double d, float f, int i) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.upcallVoid4Param.invokeExact(ENV.MEMORY, (MemorySegment) (func == null ? MemorySegment.NULL : func), (MemorySegment) (data == null ? MemorySegment.NULL : data), d, f, i);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
    }

    private final MethodHandle upcallVoid3Param2 = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_DefiningCFunction_upcallVoid3Param2", MemorySegment.class /* func */, MemorySegment.class /* data */, long.class /* l */, short.class /* s */);

    public void upcallVoid3Param2(PNIEnv ENV, MemorySegment func, MemorySegment data, long l, short s) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.upcallVoid3Param2.invokeExact(ENV.MEMORY, (MemorySegment) (func == null ? MemorySegment.NULL : func), (MemorySegment) (data == null ? MemorySegment.NULL : data), l, s);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
    }

    private final MethodHandle upcallReturnByteNoParam = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_DefiningCFunction_upcallReturnByteNoParam", MemorySegment.class /* func */);

    public byte upcallReturnByteNoParam(PNIEnv ENV, MemorySegment func) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.upcallReturnByteNoParam.invokeExact(ENV.MEMORY, (MemorySegment) (func == null ? MemorySegment.NULL : func));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnByte();
    }

    private final MethodHandle upcallReturnBoolNoParam = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_DefiningCFunction_upcallReturnBoolNoParam", MemorySegment.class /* func */);

    public boolean upcallReturnBoolNoParam(PNIEnv ENV, MemorySegment func) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.upcallReturnBoolNoParam.invokeExact(ENV.MEMORY, (MemorySegment) (func == null ? MemorySegment.NULL : func));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnBool();
    }

    private final MethodHandle upcallReturnCharNoParam = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_DefiningCFunction_upcallReturnCharNoParam", MemorySegment.class /* func */);

    public char upcallReturnCharNoParam(PNIEnv ENV, MemorySegment func) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.upcallReturnCharNoParam.invokeExact(ENV.MEMORY, (MemorySegment) (func == null ? MemorySegment.NULL : func));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnChar();
    }

    private final MethodHandle upcallReturnDoubleNoParam = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_DefiningCFunction_upcallReturnDoubleNoParam", MemorySegment.class /* func */);

    public double upcallReturnDoubleNoParam(PNIEnv ENV, MemorySegment func) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.upcallReturnDoubleNoParam.invokeExact(ENV.MEMORY, (MemorySegment) (func == null ? MemorySegment.NULL : func));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnDouble();
    }

    private final MethodHandle upcallReturnFloatNoParam = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_DefiningCFunction_upcallReturnFloatNoParam", MemorySegment.class /* func */);

    public float upcallReturnFloatNoParam(PNIEnv ENV, MemorySegment func) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.upcallReturnFloatNoParam.invokeExact(ENV.MEMORY, (MemorySegment) (func == null ? MemorySegment.NULL : func));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnFloat();
    }

    private final MethodHandle upcallReturnIntNoParam = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_DefiningCFunction_upcallReturnIntNoParam", MemorySegment.class /* func */);

    public int upcallReturnIntNoParam(PNIEnv ENV, MemorySegment func) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.upcallReturnIntNoParam.invokeExact(ENV.MEMORY, (MemorySegment) (func == null ? MemorySegment.NULL : func));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnInt();
    }

    private final MethodHandle upcallReturnLongNoParam = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_DefiningCFunction_upcallReturnLongNoParam", MemorySegment.class /* func */);

    public long upcallReturnLongNoParam(PNIEnv ENV, MemorySegment func) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.upcallReturnLongNoParam.invokeExact(ENV.MEMORY, (MemorySegment) (func == null ? MemorySegment.NULL : func));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnLong();
    }

    private final MethodHandle upcallReturnShortNoParam = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_DefiningCFunction_upcallReturnShortNoParam", MemorySegment.class /* func */);

    public short upcallReturnShortNoParam(PNIEnv ENV, MemorySegment func) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.upcallReturnShortNoParam.invokeExact(ENV.MEMORY, (MemorySegment) (func == null ? MemorySegment.NULL : func));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnShort();
    }

    private final MethodHandle upcallReturnPointerNoParam = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_DefiningCFunction_upcallReturnPointerNoParam", MemorySegment.class /* func */);

    public MemorySegment upcallReturnPointerNoParam(PNIEnv ENV, MemorySegment func) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.upcallReturnPointerNoParam.invokeExact(ENV.MEMORY, (MemorySegment) (func == null ? MemorySegment.NULL : func));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnPointer();
    }

    private final MethodHandle upcallReturnByte1Param = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_DefiningCFunction_upcallReturnByte1Param", MemorySegment.class /* func */, byte.class /* b */);

    public byte upcallReturnByte1Param(PNIEnv ENV, MemorySegment func, byte b) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.upcallReturnByte1Param.invokeExact(ENV.MEMORY, (MemorySegment) (func == null ? MemorySegment.NULL : func), b);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnByte();
    }

    private final MethodHandle upcallReturnBool1Param = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_DefiningCFunction_upcallReturnBool1Param", MemorySegment.class /* func */, boolean.class /* z */);

    public boolean upcallReturnBool1Param(PNIEnv ENV, MemorySegment func, boolean z) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.upcallReturnBool1Param.invokeExact(ENV.MEMORY, (MemorySegment) (func == null ? MemorySegment.NULL : func), z);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnBool();
    }

    private final MethodHandle upcallReturnChar1Param = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_DefiningCFunction_upcallReturnChar1Param", MemorySegment.class /* func */, char.class /* c */);

    public char upcallReturnChar1Param(PNIEnv ENV, MemorySegment func, char c) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.upcallReturnChar1Param.invokeExact(ENV.MEMORY, (MemorySegment) (func == null ? MemorySegment.NULL : func), c);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnChar();
    }

    private final MethodHandle upcallReturnDouble1Param = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_DefiningCFunction_upcallReturnDouble1Param", MemorySegment.class /* func */, double.class /* d */);

    public double upcallReturnDouble1Param(PNIEnv ENV, MemorySegment func, double d) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.upcallReturnDouble1Param.invokeExact(ENV.MEMORY, (MemorySegment) (func == null ? MemorySegment.NULL : func), d);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnDouble();
    }

    private final MethodHandle upcallReturnFloat1Param = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_DefiningCFunction_upcallReturnFloat1Param", MemorySegment.class /* func */, float.class /* f */);

    public float upcallReturnFloat1Param(PNIEnv ENV, MemorySegment func, float f) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.upcallReturnFloat1Param.invokeExact(ENV.MEMORY, (MemorySegment) (func == null ? MemorySegment.NULL : func), f);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnFloat();
    }

    private final MethodHandle upcallReturnInt1Param = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_DefiningCFunction_upcallReturnInt1Param", MemorySegment.class /* func */, int.class /* i */);

    public int upcallReturnInt1Param(PNIEnv ENV, MemorySegment func, int i) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.upcallReturnInt1Param.invokeExact(ENV.MEMORY, (MemorySegment) (func == null ? MemorySegment.NULL : func), i);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnInt();
    }

    private final MethodHandle upcallReturnLong1Param = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_DefiningCFunction_upcallReturnLong1Param", MemorySegment.class /* func */, long.class /* j */);

    public long upcallReturnLong1Param(PNIEnv ENV, MemorySegment func, long j) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.upcallReturnLong1Param.invokeExact(ENV.MEMORY, (MemorySegment) (func == null ? MemorySegment.NULL : func), j);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnLong();
    }

    private final MethodHandle upcallReturnShort1Param = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_DefiningCFunction_upcallReturnShort1Param", MemorySegment.class /* func */, short.class /* s */);

    public short upcallReturnShort1Param(PNIEnv ENV, MemorySegment func, short s) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.upcallReturnShort1Param.invokeExact(ENV.MEMORY, (MemorySegment) (func == null ? MemorySegment.NULL : func), s);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnShort();
    }

    private final MethodHandle upcallReturnPointer1Param = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_DefiningCFunction_upcallReturnPointer1Param", MemorySegment.class /* func */, MemorySegment.class /* p */);

    public MemorySegment upcallReturnPointer1Param(PNIEnv ENV, MemorySegment func, MemorySegment p) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.upcallReturnPointer1Param.invokeExact(ENV.MEMORY, (MemorySegment) (func == null ? MemorySegment.NULL : func), (MemorySegment) (p == null ? MemorySegment.NULL : p));
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
// sha256:39c3f623c3502ae6b1b7aa5c62dbf3321ad3c0ebc22178c843ba7a18420c3226
