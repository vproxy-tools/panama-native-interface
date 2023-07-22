package io.vproxy.pni.array;

import io.vproxy.pni.Allocator;
import io.vproxy.pni.PNIBuf;

import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public class FloatArray {
    public final MemorySegment MEMORY;

    public FloatArray(MemorySegment buf) {
        this.MEMORY = buf;
    }

    public FloatArray(PNIBuf buf) {
        this.MEMORY = buf.get();
    }

    public FloatArray(Allocator allocator, long len) {
        this(allocator.allocate(len * 4));
    }

    public long length() {
        return MEMORY.byteSize() / 4;
    }

    public float get(long index) {
        return MEMORY.get(ValueLayout.JAVA_FLOAT, index * 4);
    }

    public void set(long index, float v) {
        MEMORY.set(ValueLayout.JAVA_FLOAT, index * 4, v);
    }

    public PNIBuf toPNIBuf(Allocator allocator) {
        var pnibuf = new PNIBuf(allocator);
        pnibuf.set(MEMORY);
        return pnibuf;
    }
}
