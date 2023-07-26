package io.vproxy.pni.exec.internal;

public class ParamOpts {
    private final boolean dependOnAllocator;

    private ParamOpts(boolean dependOnAllocator) {
        this.dependOnAllocator = dependOnAllocator;
    }

    public boolean isDependOnAllocator() {
        return dependOnAllocator;
    }

    public static ParamOpts of(boolean dependOnAllocator) {
        return new ParamOpts(dependOnAllocator);
    }
}
