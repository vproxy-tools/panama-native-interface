package io.vproxy.pni.exec.internal;

public class AllocationForReturnedValue {
    private final String extra;
    private final String pooled;

    private static final AllocationForReturnedValue NO_ALLOCATION_REQUIRED = new AllocationForReturnedValue(null, null);

    public static AllocationForReturnedValue noAllocationRequired() {
        return NO_ALLOCATION_REQUIRED;
    }

    private AllocationForReturnedValue(String extra, String pooled) {
        if (extra != null && pooled != null) {
            throw new IllegalArgumentException();
        }
        this.extra = extra;
        this.pooled = pooled;
    }

    public static AllocationForReturnedValue ofExtraAllocator(String byteSize) {
        return new AllocationForReturnedValue(byteSize, null);
    }

    public static AllocationForReturnedValue ofPooledAllocator(String byteSize) {
        return new AllocationForReturnedValue(null, byteSize);
    }

    public boolean requireAllocator() {
        return extra != null || pooled != null;
    }

    public boolean requireExtraParameter() {
        return extra != null;
    }

    public boolean requirePooledAllocator() {
        return pooled != null;
    }

    public String byteSize() {
        return extra != null ? extra : pooled;
    }
}
