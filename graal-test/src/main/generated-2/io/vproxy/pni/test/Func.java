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

public class Func {
    private Func() {
    }

    private static final Func INSTANCE = new Func();

    public static Func get() {
        return INSTANCE;
    }

    private static final MethodHandle func1MH = PanamaUtils.lookupPNIFunction(new PNILinkOptions(), "Java_io_vproxy_pni_test_Func_func1");

    public int func1(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) func1MH.invokeExact(ENV.MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnInt();
    }

    private static final MethodHandle func1CriticalMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), int.class, "JavaCritical_io_vproxy_pni_test_Func_func1Critical");

    public int func1Critical() {
        int RESULT;
        try {
            RESULT = (int) func1CriticalMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle func2MH = PanamaUtils.lookupPNIFunction(new PNILinkOptions(), "Java_io_vproxy_pni_test_Func_func2");

    public void func2(PNIEnv ENV) throws java.io.IOException {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) func2MH.invokeExact(ENV.MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwIf(java.io.IOException.class);
            ENV.throwLast();
        }
    }

    private static final MethodHandle func3MH = PanamaUtils.lookupPNIFunction(new PNILinkOptions(), "Java_io_vproxy_pni_test_Func_func3", String.class /* ex */);

    public void func3(PNIEnv ENV, PNIString ex) throws java.io.IOException, java.lang.UnsupportedOperationException {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) func3MH.invokeExact(ENV.MEMORY, (MemorySegment) (ex == null ? MemorySegment.NULL : ex.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwIf(java.io.IOException.class);
            ENV.throwIf(java.lang.UnsupportedOperationException.class);
            ENV.throwLast();
        }
    }

    private static final MethodHandle writeMH = PanamaUtils.lookupPNIFunction(new PNILinkOptions(), "Java_io_vproxy_pni_test_Func_write", int.class /* fd */, ByteBuffer.class /* buf */, int.class /* off */, int.class /* len */);

    public int write(PNIEnv ENV, int fd, ByteBuffer buf, int off, int len) throws java.io.IOException {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) writeMH.invokeExact(ENV.MEMORY, fd, PanamaUtils.format(buf), off, len);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwIf(java.io.IOException.class);
            ENV.throwLast();
        }
        return ENV.returnInt();
    }

    private static final MethodHandle writeCriticalMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), int.class, "JavaCritical_io_vproxy_pni_test_Func_writeCritical", int.class /* fd */, ByteBuffer.class /* buf */, int.class /* off */, int.class /* len */);

    public int writeCritical(int fd, ByteBuffer buf, int off, int len) {
        int RESULT;
        try {
            RESULT = (int) writeCriticalMH.invokeExact(fd, PanamaUtils.format(buf), off, len);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle writeWithErrnoMH = PanamaUtils.lookupPNIFunction(new PNILinkOptions(), "Java_io_vproxy_pni_test_Func_writeWithErrno", int.class /* fd */, ByteBuffer.class /* buf */, int.class /* off */, int.class /* len */);

    public int writeWithErrno(PNIEnv ENV, int fd, ByteBuffer buf, int off, int len) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) writeWithErrnoMH.invokeExact(ENV.MEMORY, fd, PanamaUtils.format(buf), off, len);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnInt();
    }

    private static final MethodHandle testErrnoMH = PanamaUtils.lookupPNIFunction(new PNILinkOptions(), "Java_io_vproxy_pni_test_Func_testErrno");

    public int testErrno(PNIEnv ENV) throws java.io.IOException {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) testErrnoMH.invokeExact(ENV.MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwIf(java.io.IOException.class);
            ENV.throwLast();
        }
        return ENV.returnInt();
    }

    private static final MethodHandle writeByteArrayMH = PanamaUtils.lookupPNIFunction(new PNILinkOptions(), "Java_io_vproxy_pni_test_Func_writeByteArray", int.class /* fd */, MemorySegment.class /* buf */, int.class /* off */, int.class /* len */);

    public int writeByteArray(PNIEnv ENV, int fd, MemorySegment buf, int off, int len) throws java.io.IOException {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) writeByteArrayMH.invokeExact(ENV.MEMORY, fd, (MemorySegment) (buf == null ? MemorySegment.NULL : buf), off, len);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwIf(java.io.IOException.class);
            ENV.throwLast();
        }
        return ENV.returnInt();
    }

    private static final MethodHandle callJavaFromCMH = PanamaUtils.lookupPNIFunction(new PNILinkOptions(), "Java_io_vproxy_pni_test_Func_callJavaFromC", io.vproxy.pni.CallSite.class /* func */);

    public MemorySegment callJavaFromC(PNIEnv ENV, io.vproxy.pni.CallSite<io.vproxy.pni.test.ObjectStruct> func) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) callJavaFromCMH.invokeExact(ENV.MEMORY, (MemorySegment) (func == null ? MemorySegment.NULL : io.vproxy.pni.test.ObjectStruct.Func.of(func).MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnPointer();
    }

    private static final MethodHandle callJavaFromCCriticalMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), MemorySegment.class, "JavaCritical_io_vproxy_pni_test_Func_callJavaFromCCritical", io.vproxy.pni.CallSite.class /* func */);

    public MemorySegment callJavaFromCCritical(io.vproxy.pni.CallSite<io.vproxy.pni.test.ObjectStruct> func) {
        MemorySegment RESULT;
        try {
            RESULT = (MemorySegment) callJavaFromCCriticalMH.invokeExact((MemorySegment) (func == null ? MemorySegment.NULL : io.vproxy.pni.test.ObjectStruct.Func.of(func).MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (RESULT.address() == 0) return null;
        return RESULT;
    }

    private static final MethodHandle callJavaRefFromCMH = PanamaUtils.lookupPNIFunction(new PNILinkOptions(), "Java_io_vproxy_pni_test_Func_callJavaRefFromC", io.vproxy.pni.CallSite.class /* func */, PNIRef.class /* ref */);

    public void callJavaRefFromC(PNIEnv ENV, io.vproxy.pni.CallSite<java.util.List<java.lang.String>> func, java.util.List<java.lang.String> ref) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) callJavaRefFromCMH.invokeExact(ENV.MEMORY, (MemorySegment) (func == null ? MemorySegment.NULL : PNIRef.Func.of(func).MEMORY), (MemorySegment) (ref == null ? MemorySegment.NULL : PNIRef.of(ref).MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
    }

    private static final MethodHandle callJavaRefFromCCriticalMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), void.class, "JavaCritical_io_vproxy_pni_test_Func_callJavaRefFromCCritical", io.vproxy.pni.CallSite.class /* func */, PNIRef.class /* ref */);

    public void callJavaRefFromCCritical(io.vproxy.pni.CallSite<java.util.List<java.lang.String>> func, java.util.List<java.lang.String> ref) {
        try {
            callJavaRefFromCCriticalMH.invokeExact((MemorySegment) (func == null ? MemorySegment.NULL : PNIRef.Func.of(func).MEMORY), (MemorySegment) (ref == null ? MemorySegment.NULL : PNIRef.of(ref).MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle callJavaMethodWithRefFromCMH = PanamaUtils.lookupPNIFunction(new PNILinkOptions(), "Java_io_vproxy_pni_test_Func_callJavaMethodWithRefFromC", MemorySegment.class /* func */, PNIRef.class /* ref */, int.class /* a */);

    public int callJavaMethodWithRefFromC(PNIEnv ENV, MemorySegment func, java.util.List<java.lang.Integer> ref, int a) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) callJavaMethodWithRefFromCMH.invokeExact(ENV.MEMORY, (MemorySegment) (func == null ? MemorySegment.NULL : func), (MemorySegment) (ref == null ? MemorySegment.NULL : PNIRef.of(ref).MEMORY), a);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnInt();
    }

    private static final MethodHandle callJavaMethodWithRefFromCCriticalMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), int.class, "JavaCritical_io_vproxy_pni_test_Func_callJavaMethodWithRefFromCCritical", MemorySegment.class /* func */, PNIRef.class /* ref */, int.class /* a */);

    public int callJavaMethodWithRefFromCCritical(MemorySegment func, java.util.List<java.lang.Integer> ref, int a) {
        int RESULT;
        try {
            RESULT = (int) callJavaMethodWithRefFromCCriticalMH.invokeExact((MemorySegment) (func == null ? MemorySegment.NULL : func), (MemorySegment) (ref == null ? MemorySegment.NULL : PNIRef.of(ref).MEMORY), a);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }
}
// metadata.generator-version: pni test
// sha256:98da81a060df9f957739a1c7a2452b4514e6197ced45299dbad59dd4588e70c2
