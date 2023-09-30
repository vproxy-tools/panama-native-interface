package io.vproxy.pni.array;

import io.vproxy.pni.Allocator;
import io.vproxy.pni.NativeObject;
import io.vproxy.pni.NativeObjectTuple;
import io.vproxy.pni.PNIBuf;

import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.util.HashSet;
import java.util.Set;

public class BoolArray implements NativeObject {
    public final MemorySegment MEMORY;

    @Override
    public MemorySegment MEMORY() {
        return MEMORY;
    }

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

    public String toString() {
        var sb = new StringBuilder();
        toString(sb, 0, new HashSet<>(), false);
        return sb.toString();
    }

    @SuppressWarnings({"DuplicatedCode"})
    @Override
    public void toString(StringBuilder sb, int indent, Set<NativeObjectTuple> visited, boolean corrupted) {
        sb.append("BoolArray[");
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
