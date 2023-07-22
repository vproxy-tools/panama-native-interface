package io.vproxy.pni.array;

import io.vproxy.pni.Allocator;
import io.vproxy.pni.PNIBuf;

import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public class ShortArray {
    public final MemorySegment MEMORY;

    public ShortArray(MemorySegment buf) {
        MEMORY = buf;
    }

    public ShortArray(PNIBuf buf) {
        MEMORY = buf.get();
    }

    public ShortArray(Allocator allocator, long len) {
        this(allocator.allocate(len * 2));
    }

    public long length() {
        return MEMORY.byteSize() / 2;
    }

    public short get(long index) {
        return MEMORY.get(ValueLayout.JAVA_SHORT, index * 2);
    }

    public void set(long index, short v) {
        MEMORY.set(ValueLayout.JAVA_SHORT, index * 2, v);
    }

    public PNIBuf toPNIBuf(Allocator allocator) {
        var pnibuf = new PNIBuf(allocator);
        pnibuf.set(MEMORY);
        return pnibuf;
    }
}
