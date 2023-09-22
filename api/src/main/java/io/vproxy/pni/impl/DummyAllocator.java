package io.vproxy.pni.impl;

import io.vproxy.pni.Allocator;

import java.lang.foreign.MemorySegment;

public class DummyAllocator extends AbstractAllocator implements Allocator {
    private static final DummyAllocator INSTANCE = new DummyAllocator();

    private DummyAllocator() {
    }

    public static DummyAllocator get() {
        return INSTANCE;
    }

    @Override
    public MemorySegment allocate(long size) {
        return MemorySegment.NULL;
    }
}
