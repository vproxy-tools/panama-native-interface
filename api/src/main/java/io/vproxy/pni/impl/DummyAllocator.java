package io.vproxy.pni.impl;

import io.vproxy.pni.Allocator;

import java.lang.foreign.MemorySegment;

public class DummyAllocator implements Allocator {
    private static final DummyAllocator INSTANCE = new DummyAllocator();

    private DummyAllocator() {
    }

    public static DummyAllocator get() {
        return INSTANCE;
    }

    @Override
    public MemorySegment allocate(long size) {
        if (size <= 0)
            return MemorySegment.NULL;
        throw new OutOfMemoryError("cannot allocate memory because it's a DummyAllocator");
    }

    @Override
    public void close() {
    }
}
