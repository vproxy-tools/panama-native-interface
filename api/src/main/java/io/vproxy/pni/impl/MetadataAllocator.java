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
        if (extraMemoryByteSize < 0 || !Utils.isPowerOf2(extraMemoryByteSize) || (extraMemoryByteSize > 0 && extraMemoryByteSize < 8))
            throw new IllegalArgumentException();
        this.allocator = allocator;
        this.extraMemoryByteSize = extraMemoryByteSize;
        this.onAllocated = onAllocated;
    }

    @Override
    public MemorySegment allocate(long size) {
        if (size < 0)
            throw new IllegalArgumentException("size = " + size + " < 0");
        var mem = allocator.allocate(size + extraMemoryByteSize);
        return postAllocate(mem, size, extraMemoryByteSize);
    }

    @Override
    public MemorySegment allocate(long size, int alignment) {
        if (alignment < 2) {
            return allocate(size);
        }
        if (size < 0)
            throw new IllegalArgumentException("size = " + size + " < 0");

        alignment = Utils.smallestPositivePowerOf2GE(alignment);
        long extraMemoryByteSize = this.extraMemoryByteSize;
        if (extraMemoryByteSize > 0 && extraMemoryByteSize < alignment) {
            extraMemoryByteSize = alignment;
        }

        var mem = allocator.allocate(size + extraMemoryByteSize, alignment);
        return postAllocate(mem, size, extraMemoryByteSize);
    }

    private MemorySegment postAllocate(MemorySegment mem, long size, long extraByteSize) {
        if (mem.byteSize() == 0) {
            return mem;
        }
        if (extraMemoryByteSize == 0) {
            onAllocated.allocated(
                new MetadataAllocationCallback.AllocationInfo(
                    allocator, this,
                    size, MemorySegment.NULL
                )
            );
        } else {
            onAllocated.allocated(
                new MetadataAllocationCallback.AllocationInfo(
                    allocator, this,
                    size, mem.asSlice(0, extraMemoryByteSize)
                )
            );
        }
        return mem.asSlice(extraByteSize);
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
