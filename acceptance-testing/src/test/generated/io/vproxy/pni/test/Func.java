package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class Func {
    private Func() {
    }

    private static final Func INSTANCE = new Func();

    public static Func get() {
        return INSTANCE;
    }

    private final MethodHandle func1 = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Func_func1");

    public int func1(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.func1.invokeExact(ENV.MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnInt();
    }

    private final MethodHandle func2 = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Func_func2");

    public void func2(PNIEnv ENV) throws java.io.IOException {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.func2.invokeExact(ENV.MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwIf(java.io.IOException.class);
            ENV.throwLast();
        }
    }

    private final MethodHandle func3 = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Func_func3", String.class /* ex */);

    public void func3(PNIEnv ENV, String ex) throws java.io.IOException, java.lang.UnsupportedOperationException {
        ENV.reset();
        try (var ARENA = Arena.ofConfined()) {
            int ERR;
            try {
                ERR = (int) this.func3.invokeExact(ENV.MEMORY, PanamaUtils.format(ex, ARENA));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (ERR != 0) {
                ENV.throwIf(java.io.IOException.class);
                ENV.throwIf(java.lang.UnsupportedOperationException.class);
                ENV.throwLast();
            }
        }
    }

    private final MethodHandle write = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Func_write", int.class /* fd */, ByteBuffer.class /* buf */, int.class /* off */, int.class /* len */);

    public int write(PNIEnv ENV, int fd, ByteBuffer buf, int off, int len) throws java.io.IOException {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.write.invokeExact(ENV.MEMORY, fd, PanamaUtils.format(buf), off, len);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwIf(java.io.IOException.class);
            ENV.throwLast();
        }
        return ENV.returnInt();
    }

    private final MethodHandle callJavaFromC = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Func_callJavaFromC", io.vproxy.pni.CallSite.class /* func */);

    public MemorySegment callJavaFromC(PNIEnv ENV, io.vproxy.pni.CallSite<io.vproxy.pni.test.ObjectStruct> func) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.callJavaFromC.invokeExact(ENV.MEMORY, io.vproxy.pni.test.ObjectStruct.Func.of(func).MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnPointer();
    }
}
// sha256:39403ab5f79d0ef9f4ffa5c2c64e8797845720b8e8c85b7a2991619b155fe1c5
