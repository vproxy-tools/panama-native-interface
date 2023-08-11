package io.vproxy.pni;

import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.nio.charset.StandardCharsets;

public class PNIString {
    public final MemorySegment MEMORY;

    public PNIString(MemorySegment memory) {
        MEMORY = memory;
    }

    public PNIString(Allocator allocator, String s) {
        this.MEMORY = allocator.wrapString(s);
    }

    public String toString() {
        return MEMORY.reinterpret(Integer.MAX_VALUE).getUtf8String(0);
    }
}
