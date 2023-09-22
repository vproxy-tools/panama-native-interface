package io.vproxy.pni.impl;

import io.vproxy.pni.Allocator;

import java.lang.foreign.MemorySegment;
import java.lang.foreign.SegmentAllocator;

public class SegmentAllocatorAllocator extends AbstractAllocator implements Allocator {
    private final SegmentAllocator allocator;

    public SegmentAllocatorAllocator(SegmentAllocator allocator) {
        this.allocator = allocator;
    }

    @Override
    public MemorySegment allocate(long size) {
        return allocator.allocate(size);
    }
}
