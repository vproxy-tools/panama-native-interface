package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;
import io.vproxy.pni.graal.*;
import org.graalvm.nativeimage.*;
import org.graalvm.nativeimage.c.function.*;
import org.graalvm.nativeimage.c.type.VoidPointer;
import org.graalvm.word.WordFactory;

public class CustomNativeTypeFunc {
    private CustomNativeTypeFunc() {
    }

    private static final CustomNativeTypeFunc INSTANCE = new CustomNativeTypeFunc();

    public static CustomNativeTypeFunc get() {
        return INSTANCE;
    }

    private static final MethodHandle execMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), MemorySegment.class, "JavaCritical_io_vproxy_pni_test_CustomNativeTypeFunc_exec", MemorySegment.class /* o */);

    public MemorySegment exec(MemorySegment o) {
        MemorySegment RESULT;
        try {
            RESULT = (MemorySegment) execMH.invokeExact((MemorySegment) (o == null ? MemorySegment.NULL : o));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (RESULT.address() == 0) return null;
        return RESULT;
    }

    private static final MethodHandle invokeMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), MemorySegment.class, "JavaCritical_io_vproxy_pni_test_CustomNativeTypeFunc_invoke", io.vproxy.pni.test.SizeofStructExpr.LAYOUT.getClass() /* s */);

    public MemorySegment invoke(io.vproxy.pni.test.SizeofStructExpr s) {
        MemorySegment RESULT;
        try {
            RESULT = (MemorySegment) invokeMH.invokeExact((MemorySegment) (s == null ? MemorySegment.NULL : s.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (RESULT.address() == 0) return null;
        return RESULT;
    }
}
// metadata.generator-version: pni test
// sha256:d787e952ab0238cef3264df6642ad84df30c806b01778c6492fb3bc4a6ab798e
