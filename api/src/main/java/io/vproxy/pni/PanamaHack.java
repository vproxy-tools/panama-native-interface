package io.vproxy.pni;

import java.lang.foreign.Linker;
import java.lang.reflect.InvocationTargetException;

public class PanamaHack {
    private PanamaHack() {
    }

    private static final Linker.Option CriticalOption;

    static {
        Linker.Option _CriticalOption;
        try {
            try {
                var m = Linker.Option.class.getDeclaredMethod("isTrivial");
                _CriticalOption = (Linker.Option) m.invoke(null);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException("cannot find Linker.Option.isTrivial()");
            }
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException("unable to retrieve LinkerOption.Critical");
        }
        CriticalOption = _CriticalOption;
    }

    public static Linker.Option getCriticalOption() {
        return CriticalOption;
    }
}
