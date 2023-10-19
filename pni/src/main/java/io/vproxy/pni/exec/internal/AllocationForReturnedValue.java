package io.vproxy.pni.exec.internal;

import java.util.Objects;

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

    public static AllocationForReturnedValue ofExtraAllocator(String layout) {
        return new AllocationForReturnedValue(layout, null);
    }

    public static AllocationForReturnedValue ofPooledAllocator(String layout) {
        return new AllocationForReturnedValue(null, layout);
    }

    public boolean requireAllocator() {
        return extra != null || pooled != null;
    }

    public boolean requireExtraParameterForJavaDowncall() {
        return extra != null;
    }

    public boolean requirePooledAllocator() {
        return pooled != null;
    }

    public String layout() {
        return extra != null ? extra : pooled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AllocationForReturnedValue that = (AllocationForReturnedValue) o;

        if (!Objects.equals(extra, that.extra)) return false;
        return Objects.equals(pooled, that.pooled);
    }

    @Override
    public int hashCode() {
        int result = extra != null ? extra.hashCode() : 0;
        result = 31 * result + (pooled != null ? pooled.hashCode() : 0);
        return result;
    }
}
