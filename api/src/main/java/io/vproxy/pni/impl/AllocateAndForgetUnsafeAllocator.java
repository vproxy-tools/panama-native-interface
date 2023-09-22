package io.vproxy.pni.impl;

import io.vproxy.pni.Allocator;
import io.vproxy.pni.unsafe.SunUnsafe;

import java.lang.foreign.MemorySegment;

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
}
