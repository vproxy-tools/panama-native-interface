package io.vproxy.pni.hack;

import java.lang.foreign.Linker;

public class PanamaHackStorage {
    public static final Linker.Option CriticalOption = Linker.Option.isTrivial();
    public static final Linker.Option CriticalOptionAllowHeapAccess = Linker.Option.isTrivial();

    private PanamaHackStorage() {
    }
}
