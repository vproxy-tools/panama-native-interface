package io.vproxy.pni;

import io.vproxy.pni.impl.*;
import io.vproxy.pni.unsafe.SunUnsafe;

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
        return new NoCloseArenaAllocator(Arena.ofAuto());
    }

    static Allocator global() {
        return NoCloseArenaAllocator.GLOBAL;
    }

    static Allocator of(SegmentAllocator allocator) {
        return new SegmentAllocatorAllocator(allocator);
    }

    static Allocator ofPooled() {
        return PooledAllocator.ofPooled();
    }

    static Allocator ofUnsafe() {
        return new UnsafeAllocator();
    }

    static Allocator ofAllocateAndForgetUnsafe() {
        return AllocateAndForgetUnsafeAllocator.get();
    }

    static Allocator ofDummy() {
        return DummyAllocator.get();
    }

    @Override
    void close();

    PNIRef<Allocator> ref();

    default Allocator withMetadata(long extraMemory, MetadataAllocationCallback onAllocated) {
        return new MetadataAllocator(this, extraMemory, onAllocated);
    }

    class Unsafe {
        private Unsafe() {
        }

        public static void free(MemorySegment mem) {
            SunUnsafe.freeMemory(mem.address());
        }
    }
}
