package io.vproxy.pni.graal;

import io.vproxy.pni.PNIRef;
import org.graalvm.nativeimage.IsolateThread;
import org.graalvm.nativeimage.c.function.CEntryPoint;
import org.graalvm.nativeimage.c.function.CEntryPointLiteral;
import org.graalvm.nativeimage.c.function.CFunctionPointer;

import java.lang.foreign.MemorySegment;

public class GraalPNIRef {
    private static final CEntryPointLiteral<CFunctionPointer> releaseFunc
        = CEntryPointLiteral.create(GraalPNIRef.class, "release", IsolateThread.class, long.class);

    public static MemorySegment getReleaseFunctionPointer() {
        return MemorySegment.ofAddress(releaseFunc.getFunctionPointer().rawValue());
    }

    @SuppressWarnings("unused")
    @CEntryPoint
    public static void release(IsolateThread thread, long index) {
        PNIRef.release(index);
    }
}
