package io.vproxy.pni.impl;

import io.vproxy.pni.Allocator;
import io.vproxy.pni.unsafe.SunUnsafe;

import java.lang.foreign.MemorySegment;

public class UnsafeAllocator extends AbstractAllocator implements Allocator {
    private long[] allocatedAddresses = new long[4];
    private int usage = 0;

    @Override
    public MemorySegment allocate(long size) {
        if (size == 0) {
            return MemorySegment.NULL;
        }

        MemorySegment seg = SunUnsafe.allocateMemory(size);
        postAllocation(seg);
        return seg;
    }

    @Override
    public MemorySegment allocate(long size, int alignment) {
        if (alignment < 2 || alignment <= SunUnsafe.getDefaultAllocationAlignment()) {
            return allocate(size);
        }

        if (size == 0) {
            return MemorySegment.NULL;
        }
        alignment = Utils.smallestPositivePowerOf2GE(alignment);
        MemorySegment seg = SunUnsafe.allocateMemory(size + alignment - 1);
        postAllocation(seg);
        if ((seg.address() & (alignment - 1)) == 0)
            return seg.reinterpret(size);
        return MemorySegment.ofAddress(seg.address() + alignment - (seg.address() & (alignment - 1))).reinterpret(size);
    }

    private void postAllocation(MemorySegment seg) {
        SunUnsafe.setMemory(seg.address(), seg.byteSize(), (byte) 0);
        int currentCap = allocatedAddresses.length;
        while (currentCap <= usage) {
            int newCap = (currentCap < 16) ? 16 : currentCap + 16;
            var allocatedAddresses = new long[newCap];
            System.arraycopy(this.allocatedAddresses, 0, allocatedAddresses, 0, currentCap);
            this.allocatedAddresses = allocatedAddresses;
            currentCap = newCap;
        }
        allocatedAddresses[usage++] = seg.address();
    }

    @Override
    public void close() {
        super.close();
        for (int i = 0; i < allocatedAddresses.length; i++) {
            long addr = allocatedAddresses[i];
            if (addr == 0) {
                break;
            }
            allocatedAddresses[i] = 0;
            SunUnsafe.freeMemory(addr);
        }
    }
}
