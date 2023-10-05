package io.vproxy.pni.impl;

import io.vproxy.pni.Allocator;
import io.vproxy.pni.unsafe.SunUnsafe;

import java.lang.foreign.MemorySegment;
import java.util.ArrayList;

public class AllocateAndForgetUnsafeAllocator extends AbstractAllocator implements Allocator {
    private AllocateAndForgetUnsafeAllocator() {
    }

    private static final AllocateAndForgetUnsafeAllocator IMPL = new AllocateAndForgetUnsafeAllocator();

    public static AllocateAndForgetUnsafeAllocator get() {
        return IMPL;
    }

    @Override
    public MemorySegment allocate(long size) {
        if (size == 0) {
            return MemorySegment.NULL;
        }
        var seg = SunUnsafe.allocateMemory(size);
        SunUnsafe.setMemory(seg.address(), size, (byte) 0);
        return seg;
    }

    @Override
    public MemorySegment allocate(long size, int alignment) {
        if (alignment < 2 || alignment <= SunUnsafe.getDefaultAllocationAlignment()) {
            return allocate(size);
        }
        alignment = Utils.smallestPositivePowerOf2GE(alignment);

        // since the user must release the memory based on the address
        // we must return the address returned by allocateMemory
        // so, we cannot allocate then add offset
        var ls = new ArrayList<MemorySegment>();
        try {
            while (true) {
                var mem = SunUnsafe.allocateMemory(size);
                if ((mem.address() & (alignment - 1)) == 0) {
                    return mem;
                }
                ls.add(mem);
            }
        } finally {
            for (var a : ls) {
                SunUnsafe.freeMemory(a.address());
            }
        }
    }
}
