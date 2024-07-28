package io.vproxy.pni.hack;

import java.lang.foreign.Linker;

public class PanamaHackStorage {
    public static final Linker.Option CriticalOption = Linker.Option.critical(false);
    public static final Linker.Option CriticalOptionAllowHeapAccess = Linker.Option.critical(true);

    private PanamaHackStorage() {
    }
}
