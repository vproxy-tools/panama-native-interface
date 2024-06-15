package io.vproxy.pni;

import java.lang.foreign.Linker;
import java.lang.foreign.MemorySegment;
import java.lang.reflect.InvocationTargetException;

import io.vproxy.pni.hack.GetSetUtf8String;

public class PanamaHack {
    private PanamaHack() {
    }

    private static final Linker.Option CriticalOption;
    private static final Linker.Option CriticalOptionAllowHeapAccess;
    private static final GetSetUtf8String _GetSetUtf8String;

    static {
        int version = Runtime.version().version().getFirst();

        Linker.Option _CriticalOption;
        Linker.Option _CriticalOptionAllowHeapAccess;
        try {
            if (version < 22) {
                try {
                    var m = Linker.Option.class.getDeclaredMethod("isTrivial");
                    _CriticalOption = (Linker.Option) m.invoke(null);
                    _CriticalOptionAllowHeapAccess = _CriticalOption;
                } catch (NoSuchMethodException e) {
                    throw new RuntimeException("cannot find Linker.Option.isTrivial()");
                }
            } else {
                try {
                    var m = Linker.Option.class.getDeclaredMethod("critical", boolean.class);
                    _CriticalOption = (Linker.Option) m.invoke(null, false);
                    _CriticalOptionAllowHeapAccess = (Linker.Option) m.invoke(null, true);
                } catch (NoSuchMethodException e) {
                    throw new RuntimeException("cannot find Linker.Option.critical()");
                }
            }
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException("unable to retrieve LinkerOption.Critical");
        }
        CriticalOption = _CriticalOption;
        CriticalOptionAllowHeapAccess = _CriticalOptionAllowHeapAccess;

        _GetSetUtf8String = GetSetUtf8String.of();
    }

    public static Linker.Option getCriticalOption() {
        return getCriticalOption(false);
    }

    public static Linker.Option getCriticalOption(boolean allowHeapAccess) {
        if (allowHeapAccess) {
            return CriticalOptionAllowHeapAccess;
        } else {
            return CriticalOption;
        }
    }

    public static String getUtf8String(MemorySegment seg, long index) {
        return _GetSetUtf8String.getUtf8String(seg, index);
    }

    public static void setUtf8String(MemorySegment seg, long index, String value) {
        _GetSetUtf8String.setUtf8String(seg, index, value);
    }
}
