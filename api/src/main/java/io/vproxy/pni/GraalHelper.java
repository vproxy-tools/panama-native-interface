package io.vproxy.pni;

import java.lang.foreign.MemorySegment;

public class GraalHelper {
    private GraalHelper() {
    }

    private static MemorySegment releaseRef;

    public static MemorySegment getReleaseRef() {
        return releaseRef;
    }

    public static void setReleaseRef(MemorySegment releaseRef) {
        GraalHelper.releaseRef = releaseRef;
    }

    private static MemorySegment invokeFunc;

    public static MemorySegment getInvokeFunc() {
        return invokeFunc;
    }

    public static void setInvokeFunc(MemorySegment invokeFunc) {
        GraalHelper.invokeFunc = invokeFunc;
    }

    private static MemorySegment releaseFunc;

    public static MemorySegment getReleaseFunc() {
        return releaseFunc;
    }

    public static void setReleaseFunc(MemorySegment releaseFunc) {
        GraalHelper.releaseFunc = releaseFunc;
    }

}
