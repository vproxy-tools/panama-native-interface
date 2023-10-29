package io.vproxy.pni.exec.internal;

import java.util.Objects;

public class AllocationForReturnedValue {
    private final String requireJavaMethodExtraParameter;
    private final String requireJavaImplicitAllocator;

    private static final AllocationForReturnedValue NO_ALLOCATION_REQUIRED = new AllocationForReturnedValue(null, null);

    public static AllocationForReturnedValue noAllocationRequired() {
        return NO_ALLOCATION_REQUIRED;
    }

    private AllocationForReturnedValue(String requireJavaMethodExtraParameter, String requireJavaImplicitAllocator) {
        if (requireJavaMethodExtraParameter != null && requireJavaImplicitAllocator != null) {
            throw new IllegalArgumentException();
        }
        this.requireJavaMethodExtraParameter = requireJavaMethodExtraParameter;
        this.requireJavaImplicitAllocator = requireJavaImplicitAllocator;
    }

    public static AllocationForReturnedValue ofJavaMethodExtraParameter(String layout) {
        return new AllocationForReturnedValue(layout, null);
    }

    public static AllocationForReturnedValue ofJavaImplicitAllocator(String layout) {
        return new AllocationForReturnedValue(null, layout);
    }

    public boolean haveAdditionalAllocatedMemory() {
        return requireJavaMethodExtraParameter != null || requireJavaImplicitAllocator != null;
    }

    public boolean requireJavaMethodExtraParameter() {
        return requireJavaMethodExtraParameter != null;
    }

    public boolean requireJavaImplicitAllocator() {
        return requireJavaImplicitAllocator != null;
    }

    public String layout() {
        return requireJavaMethodExtraParameter != null ? requireJavaMethodExtraParameter : requireJavaImplicitAllocator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AllocationForReturnedValue that = (AllocationForReturnedValue) o;

        if (!Objects.equals(requireJavaMethodExtraParameter, that.requireJavaMethodExtraParameter)) return false;
        return Objects.equals(requireJavaImplicitAllocator, that.requireJavaImplicitAllocator);
    }

    @Override
    public int hashCode() {
        int result = requireJavaMethodExtraParameter != null ? requireJavaMethodExtraParameter.hashCode() : 0;
        result = 31 * result + (requireJavaImplicitAllocator != null ? requireJavaImplicitAllocator.hashCode() : 0);
        return result;
    }
}
