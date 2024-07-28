package io.vproxy.pni;

import java.lang.foreign.Linker;
import java.lang.foreign.MemorySegment;

import io.vproxy.pni.hack.GetSetUtf8String;
import io.vproxy.pni.hack.PanamaHackStorage;

public class PanamaHack {
    private PanamaHack() {
    }

    private static final GetSetUtf8String _GetSetUtf8String = GetSetUtf8String.of();

    public static Linker.Option getCriticalOption() {
        return getCriticalOption(false);
    }

    public static Linker.Option getCriticalOption(boolean allowHeapAccess) {
        if (allowHeapAccess) {
            return PanamaHackStorage.CriticalOptionAllowHeapAccess;
        } else {
            return PanamaHackStorage.CriticalOption;
        }
    }

    public static String getUtf8String(MemorySegment seg, long index) {
        return _GetSetUtf8String.getUtf8String(seg, index);
    }

    public static void setUtf8String(MemorySegment seg, long index, String value) {
        _GetSetUtf8String.setUtf8String(seg, index, value);
    }
}
