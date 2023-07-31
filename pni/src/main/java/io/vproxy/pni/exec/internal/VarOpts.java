package io.vproxy.pni.exec.internal;

import io.vproxy.pni.exec.type.TypeInfo;

import java.util.List;

public class VarOpts {
    private final boolean unsigned;
    private final PointerInfo pointerInfo;
    private final long len;
    private final boolean raw;
    private final List<TypeInfo> genericParams;
    private final boolean critical;

    private VarOpts(boolean unsigned, PointerInfo pointerInfo, long len, boolean raw, List<TypeInfo> genericParams, boolean critical) {
        this.unsigned = unsigned;
        this.pointerInfo = pointerInfo;
        this.len = len;
        this.raw = raw;
        this.genericParams = genericParams;
        this.critical = critical;
    }

    public static VarOpts of(boolean unsigned, PointerInfo hasPointerAnno, long len) {
        return new VarOpts(unsigned, hasPointerAnno, len, false, null, false);
    }

    public static VarOpts ofReturn(boolean critical) {
        return new VarOpts(false, PointerInfo.ofMethod(false), -1, false, null, critical);
    }

    public static VarOpts of(boolean unsigned, PointerInfo hasPointerAnno, long len, boolean raw, List<TypeInfo> genericParams) {
        return new VarOpts(unsigned, hasPointerAnno, len, raw, genericParams, false);
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

    public List<TypeInfo> getGenericParams() {
        return genericParams;
    }

    public boolean isCritical() {
        return critical;
    }

    public boolean isPointerGeneral() {
        return getLen() < 0 || isPointer();
    }
}
