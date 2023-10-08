package io.vproxy.pni.graal;

import io.vproxy.pni.PNIFunc;
import org.graalvm.nativeimage.IsolateThread;
import org.graalvm.nativeimage.c.function.CEntryPoint;
import org.graalvm.nativeimage.c.function.CEntryPointLiteral;
import org.graalvm.nativeimage.c.function.CFunctionPointer;
import org.graalvm.nativeimage.c.type.VoidPointer;

import java.lang.foreign.MemorySegment;

public class GraalPNIFunc {
    private static final CEntryPointLiteral<CFunctionPointer> releaseFunc
        = CEntryPointLiteral.create(GraalPNIFunc.class, "release", IsolateThread.class, long.class);
    private static final CEntryPointLiteral<CFunctionPointer> invokeFunc
        = CEntryPointLiteral.create(GraalPNIFunc.class, "invoke", IsolateThread.class, long.class, VoidPointer.class);

    public static MemorySegment getReleaseFunctionPointer() {
        return MemorySegment.ofAddress(releaseFunc.getFunctionPointer().rawValue());
    }

    public static MemorySegment getInvokeFunctionPointer() {
        return MemorySegment.ofAddress(invokeFunc.getFunctionPointer().rawValue());
    }

    @SuppressWarnings("unused")
    @CEntryPoint
    public static void release(IsolateThread thread, long index) {
        PNIFunc.release(index);
    }

    @SuppressWarnings("unused")
    @CEntryPoint
    public static void invoke(IsolateThread thread, long index, VoidPointer data) {
        PNIFunc.call(index, MemorySegment.ofAddress(data.rawValue()));
    }
}
