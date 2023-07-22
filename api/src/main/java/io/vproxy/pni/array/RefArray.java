package io.vproxy.pni.array;

import io.vproxy.pni.Allocator;
import io.vproxy.pni.PNIBuf;

import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;

public abstract class RefArray<T> {
    public final MemorySegment MEMORY;
    public final MemoryLayout LAYOUT;
    private final long length;

    public RefArray(MemorySegment buf, MemoryLayout layout) {
        MEMORY = buf;
        LAYOUT = layout;
        this.length = MEMORY.byteSize() / LAYOUT.byteSize();
    }

    public RefArray(PNIBuf buf, MemoryLayout layout) {
        this(buf.get(), layout);
    }

    public long length() {
        return length;
    }

    abstract protected T construct(MemorySegment seg);

    public T get(long index) {
        return construct(
            MEMORY.asSlice(index * LAYOUT.byteSize(), LAYOUT.byteSize())
        );
    }

    abstract protected MemorySegment getSegment(T t);

    public void set(long index, T v) {
        MEMORY.asSlice(index * LAYOUT.byteSize(), LAYOUT.byteSize())
            .copyFrom(getSegment(v));
    }

    public PNIBuf toPNIBuf(Allocator allocator) {
        var pnibuf = new PNIBuf(allocator);
        pnibuf.set(MEMORY);
        return pnibuf;
    }
}
