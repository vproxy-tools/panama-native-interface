package io.vproxy.pni.array;

import io.vproxy.pni.*;

import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.util.HashSet;
import java.util.Set;

public class CharArray implements NativeObject {
    public final MemorySegment MEMORY;

    @Override
    public MemorySegment MEMORY() {
        return MEMORY;
    }

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

    public String toString() {
        var sb = new StringBuilder();
        toString(sb, 0, new HashSet<>(), false);
        return sb.toString();
    }

    @Override
    public void toString(StringBuilder sb, int indent, Set<NativeObjectTuple> visited, boolean corrupted) {
        sb.append("CharArray[");
        for (long i = 0, len = length(); i < len; ++i) {
            if (i != 0) {
                sb.append(", ");
            }
            sb.append(PanamaUtils.charToASCIIString(get(i)));
        }
        sb.append("]");
        sb.append("@").append(Long.toString(MEMORY.address(), 16));
    }
}
