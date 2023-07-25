package io.vproxy.pni.exec.internal;

import io.vproxy.pni.exec.type.TypeInfo;

import java.util.List;

public class VarOpts {
    private final boolean unsigned;
    private final PointerInfo pointerInfo;
    private final long len;
    private final boolean raw;
    private final List<TypeInfo> genericParams;

    private VarOpts(boolean unsigned, PointerInfo pointerInfo, long len, boolean raw, List<TypeInfo> genericParams) {
        this.unsigned = unsigned;
        this.pointerInfo = pointerInfo;
        this.len = len;
        this.raw = raw;
        this.genericParams = genericParams;
    }

    public static VarOpts of(boolean unsigned, PointerInfo hasPointerAnno, long len) {
        return new VarOpts(unsigned, hasPointerAnno, len, false, null);
    }

    public static VarOpts of(boolean unsigned, PointerInfo hasPointerAnno, long len, boolean raw, List<TypeInfo> genericParams) {
        return new VarOpts(unsigned, hasPointerAnno, len, raw, genericParams);
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

    public boolean isPointerGeneral() {
        return getLen() < 0 || isPointer();
    }
}
