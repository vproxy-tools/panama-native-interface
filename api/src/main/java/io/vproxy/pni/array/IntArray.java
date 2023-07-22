package io.vproxy.pni.array;

import io.vproxy.pni.Allocator;
import io.vproxy.pni.PNIBuf;

import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public class IntArray {
    public final MemorySegment MEMORY;

    public IntArray(MemorySegment buf) {
        MEMORY = buf;
    }

    public IntArray(PNIBuf buf) {
        MEMORY = buf.get();
    }

    public IntArray(Allocator allocator, long len) {
        this(allocator.allocate(len * 4));
    }

    public long length() {
        return MEMORY.byteSize() / 4;
    }

    public int get(long index) {
        return MEMORY.get(ValueLayout.JAVA_INT, index * 4);
    }

    public void set(long index, int v) {
        MEMORY.set(ValueLayout.JAVA_INT, index * 4, v);
    }

    public PNIBuf toPNIBuf(Allocator allocator) {
        var pnibuf = new PNIBuf(allocator);
        pnibuf.set(MEMORY);
        return pnibuf;
    }
}
