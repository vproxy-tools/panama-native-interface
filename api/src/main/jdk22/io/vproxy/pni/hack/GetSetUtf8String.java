package io.vproxy.pni.hack;

import java.lang.foreign.MemorySegment;

public class GetSetUtf8String {
    public static GetSetUtf8String of() {
        return new GetSetUtf8String();
    }

    private GetSetUtf8String() {
    }

    public String getUtf8String(MemorySegment seg, long index) {
        return seg.getString(index);
    }

    public void setUtf8String(MemorySegment seg, long index, String value) {
        seg.setString(index, value);
    }
}
