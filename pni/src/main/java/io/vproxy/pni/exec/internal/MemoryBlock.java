package io.vproxy.pni.exec.internal;

public class MemoryBlock {
    private final long align;
    private long block;

    public MemoryBlock(long align) {
        this.align = align;
    }

    public boolean add(long size) {
        if (size > align) return false;
        if (block + size > align) return false;
        block += size;
        return true;
    }

    public long size() {
        return align;
    }

    public long padding() {
        return align - block;
    }

    public long block() {
        return block;
    }
}
