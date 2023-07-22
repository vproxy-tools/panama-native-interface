package io.vproxy.pni.impl;

import io.vproxy.pni.Allocator;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;

public class ArenaAllocator implements Allocator {
    private final Arena arena;

    public ArenaAllocator(Arena arena) {
        this.arena = arena;
    }

    @Override
    public MemorySegment allocate(long size) {
        return arena.allocate(size);
    }

    @Override
    public void close() {
        arena.close();
    }
}
