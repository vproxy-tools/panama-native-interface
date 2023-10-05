package io.vproxy.pni.impl;

import io.vproxy.pni.Allocator;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;

public class ArenaAllocator extends AbstractAllocator implements Allocator {
    private final Arena arena;

    public ArenaAllocator(Arena arena) {
        this.arena = arena;
    }

    @Override
    public MemorySegment allocate(long size) {
        return arena.allocate(size);
    }

    @Override
    public MemorySegment allocate(long size, int alignment) {
        return arena.allocate(size, alignment);
    }

    @Override
    public void close() {
        super.close();
        arena.close();
    }
}
