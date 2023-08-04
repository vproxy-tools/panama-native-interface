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

    private final MethodHandle func1Critical = PanamaUtils.lookupPNICriticalFunction(false, int.class, "JavaCritical_io_vproxy_pni_test_Func_func1Critical");

    public int func1Critical() {
        int RESULT;
        try {
            RESULT = (int) this.func1Critical.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
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

    public void func3(PNIEnv ENV, PNIString ex) throws java.io.IOException, java.lang.UnsupportedOperationException {
        ENV.reset();
        try (var POOLED = Allocator.ofPooled()) {
            int ERR;
            try {
                ERR = (int) this.func3.invokeExact(ENV.MEMORY, (MemorySegment) (ex == null ? MemorySegment.NULL : ex.MEMORY));
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

    private final MethodHandle writeCritical = PanamaUtils.lookupPNICriticalFunction(false, int.class, "JavaCritical_io_vproxy_pni_test_Func_writeCritical", int.class /* fd */, ByteBuffer.class /* buf */, int.class /* off */, int.class /* len */);

    public int writeCritical(int fd, ByteBuffer buf, int off, int len) {
        int RESULT;
        try {
            RESULT = (int) this.writeCritical.invokeExact(fd, PanamaUtils.format(buf), off, len);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private final MethodHandle writeWithErrno = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Func_writeWithErrno", int.class /* fd */, ByteBuffer.class /* buf */, int.class /* off */, int.class /* len */);

    public int writeWithErrno(PNIEnv ENV, int fd, ByteBuffer buf, int off, int len) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.writeWithErrno.invokeExact(ENV.MEMORY, fd, PanamaUtils.format(buf), off, len);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnInt();
    }

    private final MethodHandle writeByteArray = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Func_writeByteArray", int.class /* fd */, MemorySegment.class /* buf */, int.class /* off */, int.class /* len */);

    public int writeByteArray(PNIEnv ENV, int fd, MemorySegment buf, int off, int len) throws java.io.IOException {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.writeByteArray.invokeExact(ENV.MEMORY, fd, (MemorySegment) (buf == null ? MemorySegment.NULL : buf), off, len);
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

    private final MethodHandle callJavaFromCCritical = PanamaUtils.lookupPNICriticalFunction(false, MemorySegment.class, "JavaCritical_io_vproxy_pni_test_Func_callJavaFromCCritical", io.vproxy.pni.CallSite.class /* func */);

    public MemorySegment callJavaFromCCritical(io.vproxy.pni.CallSite<io.vproxy.pni.test.ObjectStruct> func) {
        MemorySegment RESULT;
        try {
            RESULT = (MemorySegment) this.callJavaFromCCritical.invokeExact(io.vproxy.pni.test.ObjectStruct.Func.of(func).MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (RESULT.address() == 0) return null;
        return RESULT;
    }
}
// sha256:945c1450e6c72c20df00da8524e094993e2e305461e4960cd8a599372137edd6
