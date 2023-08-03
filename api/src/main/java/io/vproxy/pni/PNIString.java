package io.vproxy.pni;

import java.lang.foreign.MemorySegment;
import java.nio.charset.StandardCharsets;

public class PNIString {
    public final MemorySegment MEMORY;

    public PNIString(MemorySegment memory) {
        MEMORY = memory;
    }

    public PNIString(Allocator allocator, String s) {
        var bytes = s.getBytes(StandardCharsets.UTF_8);
        var mem = allocator.allocate(bytes.length + 1);
        mem.setUtf8String(0, s);
        this.MEMORY = mem;
    }

    public String toString() {
        return MEMORY.reinterpret(Integer.MAX_VALUE).getUtf8String(0);
    }
}
