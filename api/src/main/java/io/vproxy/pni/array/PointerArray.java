package io.vproxy.pni.array;

import io.vproxy.pni.Allocator;
import io.vproxy.pni.NativeObject;
import io.vproxy.pni.NativeObjectTuple;
import io.vproxy.pni.PNIBuf;

import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.util.HashSet;
import java.util.Set;

public class PointerArray implements NativeObject {
    public final MemorySegment MEMORY;

    @Override
    public MemorySegment MEMORY() {
        return MEMORY;
    }

    public PointerArray(MemorySegment buf) {
        this.MEMORY = buf;
    }

    public PointerArray(PNIBuf buf) {
        this.MEMORY = buf.get();
    }

    public PointerArray(Allocator allocator, long len) {
        this(allocator.allocate(len * 8));
    }

    public long length() {
        return MEMORY.byteSize() / 8;
    }

    public MemorySegment get(long index) {
        var p = MEMORY.get(ValueLayout.ADDRESS, index * 8);
        if (p.address() == 0) return null;
        return p;
    }

    public void set(long index, MemorySegment v) {
        MEMORY.set(ValueLayout.ADDRESS, index * 8, v);
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

    @SuppressWarnings({"DuplicatedCode"})
    @Override
    public void toString(StringBuilder sb, int indent, Set<NativeObjectTuple> visited, boolean corrupted) {
        sb.append("PointerArray[");
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
