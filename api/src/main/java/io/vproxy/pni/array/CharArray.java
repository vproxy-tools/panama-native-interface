package io.vproxy.pni.array;

import io.vproxy.pni.Allocator;
import io.vproxy.pni.PNIBuf;

import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public class CharArray {
    public final MemorySegment MEMORY;

    public CharArray(MemorySegment buf) {
        this.MEMORY = buf;
    }

    public CharArray(PNIBuf buf) {
        this.MEMORY = buf.get();
    }

    public CharArray(Allocator allocator, long len) {
        this(allocator.allocate(len * 2));
    }

    public long length() {
        return MEMORY.byteSize() / 2;
    }

    public char get(long index) {
        return MEMORY.get(ValueLayout.JAVA_CHAR, index * 2);
    }

    public void set(long index, char v) {
        MEMORY.set(ValueLayout.JAVA_CHAR, index * 2, v);
    }

    public PNIBuf toPNIBuf(Allocator allocator) {
        var pnibuf = new PNIBuf(allocator);
        pnibuf.set(MEMORY);
        return pnibuf;
    }
}
