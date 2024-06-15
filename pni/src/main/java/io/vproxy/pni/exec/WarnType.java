package io.vproxy.pni.exec;

public enum WarnType {
    INVALID_CLASSPATH_FILE(0x0001, "invalid-classpath-file", true, true,
        "classpath is invalid"),
    ALIGNMENT_NOT_POWER_OF_2(0x0002, "alignment-not-power-of-2", true, false,
        "set a non-power-of-2 value in @Align annotation"),
    JDK21_ALLOW_HEAP_ACCESS(0x0004, "jdk21-allow-heap-access", true, false,
        "critical linker option parameter 'allow-heap-access' is enabled but the running JDK version is 21"),
    ;
    public final long flag;
    public final String name;
    public final boolean defaultEnabled;
    public final boolean defaultEnabledAsError;
    public final String description;

    WarnType(long flag, String name, boolean defaultEnabled, boolean defaultEnabledAsError, String description) {
        this.flag = flag;
        this.name = name;
        this.defaultEnabled = defaultEnabled;
        this.defaultEnabledAsError = defaultEnabledAsError;
        this.description = description;
    }

    public boolean check(long flags) {
        return (flags & flag) == flag;
    }

    public static long defaultWarningFlags() {
        long n = 0;
        for (var w : values()) {
            if (w.defaultEnabled) {
                n |= w.flag;
            }
        }
        return n;
    }

    public static long defaultWarningAsErrorFlags() {
        long n = 0;
        for (var w : values()) {
            if (w.defaultEnabledAsError) {
                n |= w.flag;
            }
        }
        return n;
    }

    public static WarnType searchForWarnTypeByName(String name) {
        for (var w : values()) {
            if (w.name.equals(name)) {
                return w;
            }
        }
        return null;
    }
}
