package io.vproxy.pni.exec.internal;

public class VarOpts {
    private final boolean unsigned;
    private final PointerInfo pointerInfo;
    private final long len;
    private final boolean raw;
    private final boolean critical;

    private VarOpts(boolean unsigned, PointerInfo pointerInfo, long len, boolean raw, boolean critical) {
        this.unsigned = unsigned;
        this.pointerInfo = pointerInfo;
        this.len = len;
        this.raw = raw;
        this.critical = critical;
    }

    public static VarOpts of(boolean unsigned, PointerInfo hasPointerAnno, long len) {
        return new VarOpts(unsigned, hasPointerAnno, len, false, false);
    }

    public static VarOpts ofReturn(boolean critical) {
        return new VarOpts(false, PointerInfo.ofMethod(false), -1, false, critical);
    }

    public static VarOpts of(boolean unsigned, PointerInfo hasPointerAnno, long len, boolean raw) {
        return new VarOpts(unsigned, hasPointerAnno, len, raw, false);
    }

    public static VarOpts fieldDefault() {
        return new VarOpts(false, PointerInfo.ofField(false), -1, false, false);
    }

    public static VarOpts paramDefault() {
        return new VarOpts(false, PointerInfo.ofMethod(false), -1, false, false);
    }

    public static VarOpts returnDefault() {
        return new VarOpts(false, PointerInfo.ofMethod(false), -1, false, false);
    }

    public boolean isUnsigned() {
        return unsigned;
    }

    public PointerInfo getPointerInfo() {
        return pointerInfo;
    }

    public boolean isPointer() {
        return pointerInfo.isPointer();
    }

    public long getLen() {
        return len;
    }

    public boolean isRaw() {
        return raw;
    }

    public boolean isCritical() {
        return critical;
    }

    public boolean isPointerGeneral() {
        return getLen() < 0 || isPointer();
    }
}
