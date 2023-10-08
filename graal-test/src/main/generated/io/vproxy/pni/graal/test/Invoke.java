package io.vproxy.pni.graal.test;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class Invoke {
    private Invoke() {
    }

    private static final Invoke INSTANCE = new Invoke();

    public static Invoke get() {
        return INSTANCE;
    }

    private static final MethodHandle invokeSumMH = PanamaUtils.lookupPNICriticalFunction(false, int.class, "JavaCritical_io_vproxy_pni_graal_test_Invoke_invokeSum", MemorySegment.class /* func */, MemorySegment.class /* thread */, int.class /* a */, int.class /* b */);

    public int invokeSum(MemorySegment func, MemorySegment thread, int a, int b) {
        int RESULT;
        try {
            RESULT = (int) invokeSumMH.invokeExact((MemorySegment) (func == null ? MemorySegment.NULL : func), (MemorySegment) (thread == null ? MemorySegment.NULL : thread), a, b);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle invokePtrMH = PanamaUtils.lookupPNICriticalFunction(false, void.class, "JavaCritical_io_vproxy_pni_graal_test_Invoke_invokePtr", MemorySegment.class /* func */, MemorySegment.class /* thread */, int.class /* a */, MemorySegment.class /* p */);

    public void invokePtr(MemorySegment func, MemorySegment thread, int a, MemorySegment p) {
        try {
            invokePtrMH.invokeExact((MemorySegment) (func == null ? MemorySegment.NULL : func), (MemorySegment) (thread == null ? MemorySegment.NULL : thread), a, (MemorySegment) (p == null ? MemorySegment.NULL : p));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle releaseRefMH = PanamaUtils.lookupPNICriticalFunction(false, void.class, "JavaCritical_io_vproxy_pni_graal_test_Invoke_releaseRef", PNIRef.class /* ref */);

    public void releaseRef(PNIRef<java.lang.Integer> ref) {
        try {
            releaseRefMH.invokeExact((MemorySegment) (ref == null ? MemorySegment.NULL : ref.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle callFuncMH = PanamaUtils.lookupPNICriticalFunction(false, int.class, "JavaCritical_io_vproxy_pni_graal_test_Invoke_callFunc", io.vproxy.pni.CallSite.class /* func */);

    public int callFunc(io.vproxy.pni.CallSite<Void> func) {
        int RESULT;
        try {
            RESULT = (int) callFuncMH.invokeExact((MemorySegment) (func == null ? MemorySegment.NULL : PNIFunc.VoidFunc.of(func).MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }
}
// metadata.generator-version: pni test
// sha256:88ea0b2e013956ecad1d4e2d9016ec7f07b8db4bd7b4f6e1c34a4a12a3d83b9f
