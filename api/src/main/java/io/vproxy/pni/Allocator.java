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

    @Override
    void close();
}
