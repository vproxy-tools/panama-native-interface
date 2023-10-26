package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class KtDowncall {
    private KtDowncall() {
    }

    private static final KtDowncall INSTANCE = new KtDowncall();

    public static KtDowncall get() {
        return INSTANCE;
    }

    private static final MethodHandle retrieveLongMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), long.class, "JavaCritical_io_vproxy_pni_test_KtDowncall_retrieveLong", io.vproxy.pni.test.KtStruct.LAYOUT.getClass() /* o */);

    public long retrieveLong(io.vproxy.pni.test.KtStruct o) {
        long RESULT;
        try {
            RESULT = (long) retrieveLongMH.invokeExact((MemorySegment) (o == null ? MemorySegment.NULL : o.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle invokeHelloWorldMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), io.vproxy.pni.test.KtStruct.LAYOUT.getClass(), "JavaCritical_io_vproxy_pni_test_KtDowncall_invokeHelloWorld", int.class /* i */, long.class /* l */, MemorySegment.class /* return */);

    public io.vproxy.pni.test.KtStruct invokeHelloWorld(int i, long l, Allocator ALLOCATOR) {
        MemorySegment RESULT;
        try {
            RESULT = (MemorySegment) invokeHelloWorldMH.invokeExact(i, l, ALLOCATOR.allocate(io.vproxy.pni.test.KtStruct.LAYOUT));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (RESULT.address() == 0) return null;
        return RESULT == null ? null : new io.vproxy.pni.test.KtStruct(RESULT);
    }
}
// metadata.generator-version: pni test
// sha256:7937d85fc7d6b876e05450bed473ed8d1f9d6f6359cfaa1281c890cbe6794ba6
