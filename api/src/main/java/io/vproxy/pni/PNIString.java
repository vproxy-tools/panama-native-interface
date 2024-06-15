package io.vproxy.pni;

import java.lang.foreign.MemorySegment;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;

public class PNIString implements NativeObject {
    public final MemorySegment MEMORY;

    @Override
    public MemorySegment MEMORY() {
        return MEMORY;
    }

    public PNIString(MemorySegment memory) {
        MEMORY = memory;
    }

    public PNIString(Allocator allocator, String s) {
        this.MEMORY = allocator.wrapString(s);
    }

    public String toString() {
        return PanamaHack.getUtf8String(MEMORY.reinterpret(Integer.MAX_VALUE), 0);
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

    @Override
    public void toString(StringBuilder sb, int indent, Set<NativeObjectTuple> visited, boolean corrupted) {
        if (corrupted) {
            sb.append("<?>");
        } else {
            sb.append(this);
        }
        sb.append("@").append(Long.toString(MEMORY.address(), 16));
    }
}
