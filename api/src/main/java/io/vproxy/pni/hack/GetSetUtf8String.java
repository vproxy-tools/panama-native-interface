package io.vproxy.pni.hack;

import java.lang.foreign.MemorySegment;

public interface GetSetUtf8String {
    static GetSetUtf8String of() {
        var version = Runtime.version().version().getFirst();
        if (version < 22) {
            return new GetSetUtf8String21Impl();
        } else {
            return new GetSetUtf8String22Impl();
        }
    }

    String getUtf8String(MemorySegment seg, long index);

    void setUtf8String(MemorySegment seg, long index, String value);
}
