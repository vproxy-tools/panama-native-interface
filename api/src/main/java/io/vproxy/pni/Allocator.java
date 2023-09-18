package io.vproxy.pni;

import io.vproxy.pni.impl.*;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.SegmentAllocator;
import java.lang.foreign.ValueLayout;
import java.nio.charset.StandardCharsets;

public interface Allocator extends AutoCloseable {
    MemorySegment allocate(long size);

    default MemorySegment wrapString(String s) {
        var bytes = s.getBytes(StandardCharsets.UTF_8);
        var mem = allocate(bytes.length + 1);
        mem.copyFrom(MemorySegment.ofArray(bytes));
        mem.set(ValueLayout.JAVA_BYTE, mem.byteSize() - 1, (byte) 0);
        return mem;
    }

    static Allocator of(Arena arena) {
        return new ArenaAllocator(arena);
    }

    static Allocator ofConfined() {
        return of(Arena.ofConfined());
    }

    static Allocator ofShared() {
        return of(Arena.ofShared());
    }

    static Allocator ofAuto() {
        return new AutoArenaAllocator();
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

    static Allocator ofConcurrentPooled() {
        var provider = AllocatorUtils.concurrentProvider;
        if (provider == null) {
            return ofShared();
        } else {
            return provider.create();
        }
    }

    static Allocator ofUnsafe() {
        return new UnsafeAllocator();
    }

    static Allocator ofDummy() {
        return DummyAllocator.get();
    }

    @Override
    void close();

    static PooledAllocatorProvider getPooledAllocatorProvider() {
        return AllocatorUtils.provider;
    }

    static void setPooledAllocatorProvider(PooledAllocatorProvider allocatorProvider) {
        AllocatorUtils.provider = allocatorProvider;
    }

    static PooledAllocatorProvider getConcurrentPooledAllocatorProvider() {
        return AllocatorUtils.concurrentProvider;
    }

    static void setConcurrentPooledAllocatorProvider(PooledAllocatorProvider allocatorProvider) {
        AllocatorUtils.concurrentProvider = allocatorProvider;
    }
}

class AllocatorUtils {
    static PooledAllocatorProvider provider;
    static PooledAllocatorProvider concurrentProvider;

    private AllocatorUtils() {
    }
}
