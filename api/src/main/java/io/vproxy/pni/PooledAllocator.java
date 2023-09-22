package io.vproxy.pni;

public interface PooledAllocator extends Allocator {
    static Allocator ofPooled() {
        var provider = PooledAllocatorUtils.provider;
        if (provider == null) {
            return Allocator.ofConfined();
        } else {
            return provider.create();
        }
    }

    static Allocator ofConcurrentPooled() {
        var provider = PooledAllocatorUtils.concurrentProvider;
        if (provider == null) {
            return Allocator.ofShared();
        } else {
            return provider.create();
        }
    }

    static Allocator ofUnsafePooled() {
        var provider = PooledAllocatorUtils.unsafeProvider;
        if (provider == null) {
            return Allocator.ofUnsafe();
        } else {
            return provider.create();
        }
    }

    static PooledAllocatorProvider getPooledAllocatorProvider() {
        return PooledAllocatorUtils.provider;
    }

    static void setPooledAllocatorProvider(PooledAllocatorProvider allocatorProvider) {
        PooledAllocatorUtils.provider = allocatorProvider;
    }

    static PooledAllocatorProvider getConcurrentPooledAllocatorProvider() {
        return PooledAllocatorUtils.concurrentProvider;
    }

    static void setConcurrentPooledAllocatorProvider(PooledAllocatorProvider allocatorProvider) {
        PooledAllocatorUtils.concurrentProvider = allocatorProvider;
    }

    static PooledAllocatorProvider getUnsafePooledAllocatorProvider() {
        return PooledAllocatorUtils.unsafeProvider;
    }

    static void setUnsafePooledAllocatorProvider(PooledAllocatorProvider allocatorProvider) {
        PooledAllocatorUtils.unsafeProvider = allocatorProvider;
    }
}

class PooledAllocatorUtils {
    static PooledAllocatorProvider provider;
    static PooledAllocatorProvider concurrentProvider;
    static PooledAllocatorProvider unsafeProvider;

    private PooledAllocatorUtils() {
    }
}
