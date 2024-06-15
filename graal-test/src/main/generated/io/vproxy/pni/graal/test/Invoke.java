package io.vproxy.pni.graal.test;

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

public class Invoke {
    private Invoke() {
    }

    private static final Invoke INSTANCE = new Invoke();

    public static Invoke get() {
        return INSTANCE;
    }

    private static final MethodHandle invokeSumMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), int.class, "JavaCritical_io_vproxy_pni_graal_test_Invoke_invokeSum", MemorySegment.class /* func */, MemorySegment.class /* thread */, int.class /* a */, int.class /* b */);

    public int invokeSum(MemorySegment func, MemorySegment thread, int a, int b) {
        int RESULT;
        try {
            RESULT = (int) invokeSumMH.invokeExact((MemorySegment) (func == null ? MemorySegment.NULL : func), (MemorySegment) (thread == null ? MemorySegment.NULL : thread), a, b);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle invokePtrMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), void.class, "JavaCritical_io_vproxy_pni_graal_test_Invoke_invokePtr", MemorySegment.class /* func */, MemorySegment.class /* thread */, int.class /* a */, MemorySegment.class /* p */);

    public void invokePtr(MemorySegment func, MemorySegment thread, int a, MemorySegment p) {
        try {
            invokePtrMH.invokeExact((MemorySegment) (func == null ? MemorySegment.NULL : func), (MemorySegment) (thread == null ? MemorySegment.NULL : thread), a, (MemorySegment) (p == null ? MemorySegment.NULL : p));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle releaseRefMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), void.class, "JavaCritical_io_vproxy_pni_graal_test_Invoke_releaseRef", PNIRef.class /* ref */);

    public void releaseRef(PNIRef<java.lang.Integer> ref) {
        try {
            releaseRefMH.invokeExact((MemorySegment) (ref == null ? MemorySegment.NULL : ref.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle callFuncMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), int.class, "JavaCritical_io_vproxy_pni_graal_test_Invoke_callFunc", io.vproxy.pni.CallSite.class /* func */);

    public int callFunc(io.vproxy.pni.CallSite<Void> func) {
        int RESULT;
        try {
            RESULT = (int) callFuncMH.invokeExact((MemorySegment) (func == null ? MemorySegment.NULL : PNIFunc.VoidFunc.of(func).MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle invokeDoNothingUpcallMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), void.class, "JavaCritical_io_vproxy_pni_graal_test_Invoke_invokeDoNothingUpcall", MemorySegment.class /* func */);

    public void invokeDoNothingUpcall(MemorySegment func) {
        try {
            invokeDoNothingUpcallMH.invokeExact((MemorySegment) (func == null ? MemorySegment.NULL : func));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle invokeIntUpcallMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), int.class, "JavaCritical_io_vproxy_pni_graal_test_Invoke_invokeIntUpcall", MemorySegment.class /* func */, int.class /* a */);

    public int invokeIntUpcall(MemorySegment func, int a) {
        int RESULT;
        try {
            RESULT = (int) invokeIntUpcallMH.invokeExact((MemorySegment) (func == null ? MemorySegment.NULL : func), a);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle invokeRefUpcallMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), int.class, "JavaCritical_io_vproxy_pni_graal_test_Invoke_invokeRefUpcall", MemorySegment.class /* func */, PNIRef.class /* ref */);

    public int invokeRefUpcall(MemorySegment func, java.lang.Integer ref) {
        int RESULT;
        try {
            RESULT = (int) invokeRefUpcallMH.invokeExact((MemorySegment) (func == null ? MemorySegment.NULL : func), (MemorySegment) (ref == null ? MemorySegment.NULL : PNIRef.of(ref).MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle invokeFuncUpcallMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), int.class, "JavaCritical_io_vproxy_pni_graal_test_Invoke_invokeFuncUpcall", MemorySegment.class /* func */, io.vproxy.pni.CallSite.class /* ff */);

    public int invokeFuncUpcall(MemorySegment func, io.vproxy.pni.CallSite<Void> ff) {
        int RESULT;
        try {
            RESULT = (int) invokeFuncUpcallMH.invokeExact((MemorySegment) (func == null ? MemorySegment.NULL : func), (MemorySegment) (ff == null ? MemorySegment.NULL : PNIFunc.VoidFunc.of(ff).MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle invokeReturnSegUpcallMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), MemorySegment.class, "JavaCritical_io_vproxy_pni_graal_test_Invoke_invokeReturnSegUpcall", MemorySegment.class /* func */);

    public MemorySegment invokeReturnSegUpcall(MemorySegment func) {
        MemorySegment RESULT;
        try {
            RESULT = (MemorySegment) invokeReturnSegUpcallMH.invokeExact((MemorySegment) (func == null ? MemorySegment.NULL : func));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (RESULT.address() == 0) return null;
        return RESULT;
    }
}
// metadata.generator-version: pni test
// sha256:8a0ac233af786331e2a838af519ad267c214a218c7a2441bf7032a806de5a467
