package io.vproxy.pni.impl;

import io.vproxy.pni.Allocator;
import io.vproxy.pni.unsafe.SunUnsafe;

import java.lang.foreign.MemorySegment;

public class UnsafeAllocator implements Allocator {
    private long[] allocatedAddresses = new long[4];
    private int usage = 0;

    @Override
    public MemorySegment allocate(long size) {
        if (size == 0) {
            return MemorySegment.NULL;
        }

        MemorySegment seg = SunUnsafe.allocateMemory(size);
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
        return seg;
    }

    @Override
    public void close() {
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
