package io.vproxy.pni.impl;

import io.vproxy.pni.Allocator;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;

public class AutoArenaAllocator implements Allocator {
    private final Arena arena = Arena.ofAuto();

    @Override
    public MemorySegment allocate(long size) {
        return arena.allocate(size);
    }

    @Override
    public void close() {
        // do nothing
    }
}
