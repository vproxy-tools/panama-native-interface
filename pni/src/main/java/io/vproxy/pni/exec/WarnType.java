package io.vproxy.pni.exec;

public enum WarnType {
    INVALID_CLASSPATH_FILE(0x0001, "invalid-classpath-file", true, true),
    ;
    public final long flag;
    public final String name;
    private final boolean defaultEnabled;
    private final boolean defaultEnabledAsError;

    WarnType(long flag, String name, boolean defaultEnabled, boolean defaultEnabledAsError) {
        this.flag = flag;
        this.name = name;
        this.defaultEnabled = defaultEnabled;
        this.defaultEnabledAsError = defaultEnabledAsError;
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
