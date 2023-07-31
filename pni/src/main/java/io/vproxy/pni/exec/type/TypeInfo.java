package io.vproxy.pni.exec.type;

import io.vproxy.pni.exec.internal.VarOpts;

import java.util.List;

public abstract class TypeInfo {
    abstract public String name();

    abstract public String internalName();

    abstract public String desc();

    public void checkType(List<String> errors, String path, VarOpts opts) {
        if (opts.isUnsigned() && !canMarkWithUnsigned()) {
            errors.add(path + ": " + name() + " cannot be marked with @Unsigned");
        }
        if (opts.getPointerInfo().isMarked && !canMarkWithPointer()) {
            errors.add(path + ": " + name() + " cannot be marked with @Pointer");
        }
        if (opts.getLen() >= 0 && !canMarkWithLen()) {
            errors.add(path + ": " + name() + " cannot be marked with @Len");
        }
        if (opts.getLen() >= 0) {
            if (opts.isPointer()) {
                if (opts.getPointerInfo().isMarked) {
                    errors.add(path + ": " + name() + " cannot be marked with @Pointer because it is marked with @Len");
                } else {
                    errors.add(path + ": " + name() + " cannot be marked with @Len because it is pointer by default");
                }
            }
        }
        if (opts.isRaw() && !canMarkWithRaw()) {
            errors.add(path + ": " + name() + " cannot be marked with @Raw");
        }
    }

    protected boolean canMarkWithUnsigned() {
        return false;
    }

    protected boolean canMarkWithPointer() {
        return false;
    }

    protected boolean canMarkWithLen() {
        return false;
    }

    protected boolean canMarkWithRaw() {
        return false;
    }

    abstract public String nativeType(String fieldName, VarOpts opts);

    public String nativeParamType(String fieldName, VarOpts opts) {
        return nativeType(fieldName, opts);
    }

    public String nativeReturnType(VarOpts opts) {
        return nativeParamType(null, opts);
    }

    abstract public long nativeMemorySize(VarOpts opts);

    abstract public long nativeMemoryAlign(VarOpts opts);

    abstract public String memoryLayout(VarOpts opts);

    abstract public String javaType(VarOpts opts);

    abstract public void generateGetterSetter(StringBuilder sb, int indent, String fieldName, VarOpts opts);

    abstract public void generateConstructor(StringBuilder sb, int indent, String fieldName, VarOpts opts);

    abstract public String methodHandleType(VarOpts opts);

    @Override
    public String toString() {
        return name();
    }

    public abstract String convertToNativeCallArgument(String name, VarOpts opts);

    public String sizeForUserAllocatorForNativeCallExtraArgument(@SuppressWarnings("unused") VarOpts opts) {
        return null;
    }

    public String sizeForConfinedArenaForNativeCallExtraArgument(@SuppressWarnings("unused") VarOpts opts) {
        return null;
    }

    public abstract void returnValueFormatting(StringBuilder sb, int indent, VarOpts opts);

    public boolean paramDependOnConfinedArena(@SuppressWarnings("unused") VarOpts opts) {
        return false;
    }
}
