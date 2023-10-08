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
}
// metadata.generator-version: pni test
// sha256:74ad34f3a257769f973101370b8a99a331176c638f70d3ed07414a52ea52eea2
