package io.vproxy.pni;

public class PNILinkOptions extends PNILookupOptions {
    private boolean critical = false;

    public boolean isCritical() {
        return critical;
    }

    public PNILinkOptions setCritical(boolean critical) {
        this.critical = critical;
        return this;
    }
}
