package io.vproxy.pni.array;

import io.vproxy.pni.Allocator;
import io.vproxy.pni.NativeObjectTuple;
import io.vproxy.pni.PNIBuf;

import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import java.util.HashSet;
import java.util.Set;

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

    @Override
    public String toString() {
        var sb = new StringBuilder();
        toString(sb, 0, new HashSet<>(), false);
        return sb.toString();
    }

    @SuppressWarnings("DuplicatedCode")
    public String toString(StringBuilder sb, int indent, Set<NativeObjectTuple> visited, boolean corrupted) {
        sb.append(toStringTypeName()).append("[");
        var len = length();
        if (len > 0) {
            sb.append("\n");
        }
        for (long i = 0; i < len; ++i) {
            sb.append(" ".repeat(indent + 4)).append("[").append(i).append("] ");
            elementToString(get(i), sb, indent + 4, visited, corrupted);
            if (i != len - 1) {
                sb.append(",");
            }
            sb.append("\n");
        }
        if (len > 0) {
            sb.append(" ".repeat(indent));
        }
        sb.append("]");
        sb.append("@").append(Long.toString(MEMORY.address(), 16));
        return sb.toString();
    }

    @SuppressWarnings("unused")
    protected void elementToString(T elem, StringBuilder sb, int indent, Set<NativeObjectTuple> visited, boolean corrupted) {
        sb.append(elem);
    }

    protected String toStringTypeName() {
        return "RefArray";
    }
}
