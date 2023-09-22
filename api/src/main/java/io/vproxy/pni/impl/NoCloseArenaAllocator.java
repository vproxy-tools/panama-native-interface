package io.vproxy.pni.impl;

import io.vproxy.pni.Allocator;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;

public class NoCloseArenaAllocator extends AbstractAllocator implements Allocator {
    private final Arena arena;

    public NoCloseArenaAllocator(Arena arena) {
        this.arena = arena;
    }

    @Override
    public MemorySegment allocate(long size) {
        return arena.allocate(size);
    }

    public static final NoCloseArenaAllocator GLOBAL = new NoCloseArenaAllocator(Arena.global());
}
