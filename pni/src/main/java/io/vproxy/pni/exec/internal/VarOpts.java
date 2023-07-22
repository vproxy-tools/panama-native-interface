package io.vproxy.pni.exec.internal;

public class VarOpts {
    private final boolean unsigned;
    private final PointerInfo pointerInfo;
    private final long len;
    private final boolean raw;

    private VarOpts(boolean unsigned, PointerInfo pointerInfo, long len, boolean raw) {
        this.unsigned = unsigned;
        this.pointerInfo = pointerInfo;
        this.len = len;
        this.raw = raw;
    }

    public static VarOpts of(boolean unsigned, PointerInfo hasPointerAnno, long len) {
        return new VarOpts(unsigned, hasPointerAnno, len, false);
    }

    public static VarOpts of(boolean unsigned, PointerInfo hasPointerAnno, long len, boolean raw) {
        return new VarOpts(unsigned, hasPointerAnno, len, raw);
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

    public boolean isPointerGeneral() {
        return getLen() < 0 || isPointer();
    }
}
