package io.vproxy.pni.array;

import io.vproxy.pni.Allocator;
import io.vproxy.pni.PNIBuf;

import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public class BoolArray {
    public final MemorySegment MEMORY;

    public BoolArray(MemorySegment buf) {
        this.MEMORY = buf;
    }

    public BoolArray(PNIBuf buf) {
        this.MEMORY = buf.get();
    }

    public BoolArray(Allocator allocator, long len) {
        this(allocator.allocate(len));
    }

    public long length() {
        return MEMORY.byteSize();
    }

    public boolean get(long index) {
        return MEMORY.get(ValueLayout.JAVA_BOOLEAN, index);
    }

    public void set(long index, boolean v) {
        MEMORY.set(ValueLayout.JAVA_BOOLEAN, index, v);
    }

    public PNIBuf toPNIBuf(Allocator allocator) {
        var pnibuf = new PNIBuf(allocator);
        pnibuf.set(MEMORY);
        return pnibuf;
    }
}
