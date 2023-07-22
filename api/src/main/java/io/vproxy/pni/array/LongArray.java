package io.vproxy.pni.array;

import io.vproxy.pni.Allocator;
import io.vproxy.pni.PNIBuf;

import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public class LongArray {
    public final MemorySegment MEMORY;

    public LongArray(MemorySegment buf) {
        this.MEMORY = buf;
    }

    public LongArray(PNIBuf buf) {
        this.MEMORY = buf.get();
    }

    public LongArray(Allocator allocator, long len) {
        this(allocator.allocate(len * 8));
    }

    public long length() {
        return MEMORY.byteSize() / 8;
    }

    public long get(long index) {
        return MEMORY.get(ValueLayout.JAVA_LONG, index * 8);
    }

    public void set(long index, long v) {
        MEMORY.set(ValueLayout.JAVA_LONG, index * 8, v);
    }

    public PNIBuf toPNIBuf(Allocator allocator) {
        var pnibuf = new PNIBuf(allocator);
        pnibuf.set(MEMORY);
        return pnibuf;
    }
}
