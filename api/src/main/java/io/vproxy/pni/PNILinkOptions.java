package io.vproxy.pni;

public class PNILinkOptions extends PNILookupOptions {
    private boolean critical = false;
    private boolean allowHeapAccess = false;

    public boolean isCritical() {
        return critical;
    }

    public boolean isAllowHeapAccess() {
        return allowHeapAccess;
    }

    public PNILinkOptions setCritical(boolean critical) {
        if (!critical) {
            clearCritical();
        }
        this.critical = critical;
        return this;
    }

    private void clearCritical() {
        setAllowHeapAccess(false);
    }

    public PNILinkOptions setAllowHeapAccess(boolean allowHeapAccess) {
        if (allowHeapAccess) {
            setCritical(true);
        }
        this.allowHeapAccess = allowHeapAccess;
        return this;
    }
}
