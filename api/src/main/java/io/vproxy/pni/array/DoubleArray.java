package io.vproxy.pni.array;

import io.vproxy.pni.Allocator;
import io.vproxy.pni.PNIBuf;

import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public class DoubleArray {
    public final MemorySegment MEMORY;

    public DoubleArray(MemorySegment buf) {
        this.MEMORY = buf;
    }

    public DoubleArray(PNIBuf buf) {
        this.MEMORY = buf.get();
    }

    public DoubleArray(Allocator allocator, long len) {
        this(allocator.allocate(len * 8));
    }

    public long length() {
        return MEMORY.byteSize() / 8;
    }

    public double get(long index) {
        return MEMORY.get(ValueLayout.JAVA_DOUBLE, index * 8);
    }

    public void set(long index, double v) {
        MEMORY.set(ValueLayout.JAVA_DOUBLE, index * 8, v);
    }

    public PNIBuf toPNIBuf(Allocator allocator) {
        var pnibuf = new PNIBuf(allocator);
        pnibuf.set(MEMORY);
        return pnibuf;
    }
}
