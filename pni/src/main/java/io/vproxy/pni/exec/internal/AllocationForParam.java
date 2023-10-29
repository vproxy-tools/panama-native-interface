package io.vproxy.pni.exec.internal;

public class AllocationForParam {
    private final boolean requireJavaImplicitAllocator;

    private static final AllocationForParam NO_ALLOCATION_REQUIRED = new AllocationForParam(false);

    public static AllocationForParam noAllocationRequired() {
        return NO_ALLOCATION_REQUIRED;
    }

    private AllocationForParam(boolean requireJavaImplicitAllocator) {
        this.requireJavaImplicitAllocator = requireJavaImplicitAllocator;
    }

    public static AllocationForParam ofJavaImplicitAllocator() {
        return new AllocationForParam(true);
    }

    public boolean requireJavaImplicitAllocator() {
        return requireJavaImplicitAllocator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AllocationForParam that = (AllocationForParam) o;

        return requireJavaImplicitAllocator == that.requireJavaImplicitAllocator;
    }

    @Override
    public int hashCode() {
        return (requireJavaImplicitAllocator ? 1 : 0);
    }
}
