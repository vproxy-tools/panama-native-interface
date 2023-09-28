package io.vproxy.pni.array;

import io.vproxy.pni.Allocator;
import io.vproxy.pni.NativeObjectTuple;
import io.vproxy.pni.PNIBuf;

import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.util.HashSet;
import java.util.Set;

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

    public String toString() {
        var sb = new StringBuilder();
        toString(sb, 0, new HashSet<>(), false);
        return sb.toString();
    }

    @SuppressWarnings({"DuplicatedCode", "unused"})
    public void toString(StringBuilder sb, int indent, Set<NativeObjectTuple> visited, boolean corrupted) {
        sb.append("LongArray[");
        for (long i = 0, len = length(); i < len; ++i) {
            if (i != 0) {
                sb.append(", ");
            }
            sb.append(get(i));
        }
        sb.append("]");
        sb.append("@").append(Long.toString(MEMORY.address(), 16));
    }
}
