package io.vproxy.pni.exec.internal;

public class PointerInfo {
    public final boolean isMarked;
    public final boolean isDefaultTrue;

    public PointerInfo(boolean isMarked, boolean isDefaultTrue) {
        this.isMarked = isMarked;
        this.isDefaultTrue = isDefaultTrue;
    }

    public boolean isPointer() {
        return isMarked || isDefaultTrue;
    }

    public static PointerInfo ofField(boolean pointer) {
        return new PointerInfo(pointer, false);
    }

    public static PointerInfo ofMethod(boolean pointer) {
        return new PointerInfo(pointer, true);
    }
}
