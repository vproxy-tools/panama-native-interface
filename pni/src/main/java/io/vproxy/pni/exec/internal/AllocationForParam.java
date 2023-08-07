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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AllocationForParam that = (AllocationForParam) o;

        return pooled == that.pooled;
    }

    @Override
    public int hashCode() {
        return (pooled ? 1 : 0);
    }
}
