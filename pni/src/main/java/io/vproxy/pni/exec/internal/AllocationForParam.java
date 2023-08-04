package io.vproxy.pni.exec.internal;

public class AllocationForParam {
    private final boolean pooled;

    private static final AllocationForParam NO_ALLOCATION_REQUIRED = new AllocationForParam(false);

    public static AllocationForParam noAllocationRequired() {
        return NO_ALLOCATION_REQUIRED;
    }

    private AllocationForParam(boolean pooled) {
        this.pooled = pooled;
    }

    public static AllocationForParam ofPooledAllocator() {
        return new AllocationForParam(true);
    }

    public boolean requireAllocator() {
        return pooled;
    }

    public boolean requirePooledAllocator() {
        return pooled;
    }
}
