package io.vproxy.pni;

import java.lang.foreign.MemorySegment;
import java.util.function.Consumer;
import java.util.function.Function;

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

    public static void temporary(String str, Consumer<PNIString> f) {
        try (var allocator = Allocator.ofPooled()) {
            var s = new PNIString(allocator, str);
            f.accept(s);
        }
    }

    public static <T> T temporary(String str, Function<PNIString, T> f) {
        try (var allocator = Allocator.ofPooled()) {
            var s = new PNIString(allocator, str);
            return f.apply(s);
        }
    }
}
