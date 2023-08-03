package io.vproxy.pni;

import io.vproxy.pni.impl.ArenaAllocator;
import io.vproxy.pni.impl.SegmentAllocatorAllocator;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.SegmentAllocator;

public interface Allocator extends AutoCloseable {
    MemorySegment allocate(long size);

    static Allocator of(Arena arena) {
        return new ArenaAllocator(arena);
    }

    static Allocator ofConfined() {
        return of(Arena.ofConfined());
    }

    static Allocator of(SegmentAllocator allocator) {
        return new SegmentAllocatorAllocator(allocator);
    }

    static Allocator ofPooled() {
        var provider = AllocatorUtils.provider;
        if (provider == null) {
            return ofConfined();
        } else {
            return provider.create();
        }
    }

    @Override
    void close();

    static PooledAllocatorProvider getPooledAllocatorProvider() {
        return AllocatorUtils.provider;
    }

    static void setPooledAllocatorProvider(PooledAllocatorProvider allocatorProvider) {
        AllocatorUtils.provider = allocatorProvider;
    }
}

class AllocatorUtils {
    static PooledAllocatorProvider provider;

    private AllocatorUtils() {
    }
}
