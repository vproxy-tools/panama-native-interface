package io.vproxy.pni.impl;

import io.vproxy.pni.Allocator;
import io.vproxy.pni.MetadataAllocationCallback;
import io.vproxy.pni.PNIRef;

import java.lang.foreign.MemorySegment;

public class MetadataAllocator implements Allocator {
    private final Allocator allocator;
    private final long extraMemoryByteSize;
    private final MetadataAllocationCallback onAllocated;

    public MetadataAllocator(Allocator allocator, long extraMemoryByteSize, MetadataAllocationCallback onAllocated) {
        if (extraMemoryByteSize < 0 || extraMemoryByteSize % 8 != 0)
            throw new IllegalArgumentException();
        this.allocator = allocator;
        this.extraMemoryByteSize = extraMemoryByteSize;
        this.onAllocated = onAllocated;
    }

    @Override
    public MemorySegment allocate(long size) {
        if (size < 0)
            throw new IllegalArgumentException("size = " + size + " < 0");
        final long originalSize = size;
        size += extraMemoryByteSize;
        var mem = allocator.allocate(size);
        if (mem.byteSize() == 0) {
            return mem;
        }
        if (extraMemoryByteSize == 0) {
            onAllocated.allocated(
                new MetadataAllocationCallback.AllocationInfo(
                    allocator, this,
                    originalSize, MemorySegment.NULL
                )
            );
        } else {
            onAllocated.allocated(
                new MetadataAllocationCallback.AllocationInfo(
                    allocator, this,
                    originalSize, mem.asSlice(0, extraMemoryByteSize)
                )
            );
        }
        return mem.asSlice(extraMemoryByteSize);
    }

    @Override
    public MemorySegment wrapString(String s) {
        return allocator.wrapString(s);
    }

    @Override
    public void close() {
        allocator.close();
    }

    @Override
    public PNIRef<Allocator> ref() {
        return allocator.ref();
    }
}
