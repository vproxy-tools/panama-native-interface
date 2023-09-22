package io.vproxy.pni;

import java.lang.foreign.MemorySegment;

@FunctionalInterface
public
interface MetadataAllocationCallback {
    void allocated(AllocationInfo info);

    /**
     * @param allocator         the original allocator which allocated the memory
     * @param metadataAllocator the metadata allocator
     * @param size              the returned memory segment byte size, without extra data
     * @param meta              memory segment for meta with byteSize of <code>extraMemory</code>
     */
    record AllocationInfo(
        Allocator allocator,
        Allocator metadataAllocator,
        long size,
        MemorySegment meta
    ) {
    }
}
