package io.vproxy.pni.exec.type;

import io.vproxy.pni.exec.ast.BitFieldInfo;
import io.vproxy.pni.exec.internal.AllocationForParam;
import io.vproxy.pni.exec.internal.AllocationForReturnedValue;
import io.vproxy.pni.exec.internal.Utils;
import io.vproxy.pni.exec.internal.VarOpts;

import java.util.List;

public abstract class TypeInfo {
    abstract public String name();

    abstract public String internalName();

    abstract public String desc();

    public void checkType(List<String> errors, String path, VarOpts opts, boolean upcall) {
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

    abstract public String nativeEnvType(VarOpts opts);

    abstract public String nativeType(String fieldName, VarOpts opts);

    public String nativeParamType(String fieldName, VarOpts opts) {
        return nativeType(fieldName, opts);
    }

    public String nativeReturnType(VarOpts opts) {
        return nativeParamType(null, opts);
    }

    abstract public long nativeMemorySize(VarOpts opts);

    abstract public long nativeMemoryAlign(VarOpts opts);

    public long rawNativeMemoryAlign(VarOpts opts) {
        return nativeMemoryAlign(opts);
    }

    abstract public String memoryLayoutForField(VarOpts opts);

    abstract public String javaTypeForField(VarOpts opts);

    public String javaTypeForParam(VarOpts opts) {
        return javaTypeForField(opts);
    }

    public String javaTypeForReturn(VarOpts opts) {
        return javaTypeForParam(opts);
    }

    public abstract String javaTypeForUpcallParam(VarOpts opts);

    public String javaTypeForUpcallReturn(VarOpts opts) {
        return javaTypeForUpcallParam(opts);
    }

    public String javaTypeForUpcallInterfaceParam(VarOpts opts) {
        return javaTypeForParam(opts);
    }

    public String javaTypeForExtraUpcallInterfaceParam(VarOpts opts) {
        return javaTypeForUpcallInterfaceParam(opts);
    }

    public String javaTypeForUpcallInterfaceReturn(VarOpts opts) {
        return javaTypeForReturn(opts);
    }

    abstract public void generateGetterSetter(StringBuilder sb, int indent, String fieldName, VarOpts opts);

    public void generateBitFieldGetterSetter(StringBuilder sb, int indent, String name, BitFieldInfo bitfield, VarOpts varOpts) {
        throw new UnsupportedOperationException();
    }

    abstract public void generateConstructor(StringBuilder sb, int indent, String fieldName, VarOpts opts);

    abstract public String methodHandleType(VarOpts opts);

    public String methodHandleTypeForReturn(VarOpts opts) {
        return methodHandleType(opts);
    }

    public abstract String methodHandleTypeForUpcall(VarOpts opts);

    @Override
    public String toString() {
        return name();
    }

    public abstract String convertParamToInvokeExactArgument(String name, VarOpts opts);

    public AllocationForReturnedValue allocationInfoForReturnValue(VarOpts opts) {
        return AllocationForReturnedValue.noAllocationRequired();
    }

    public AllocationForReturnedValue allocationInfoForUpcallInterfaceReturnValue(VarOpts opts) {
        return AllocationForReturnedValue.noAllocationRequired();
    }

    public abstract void convertInvokeExactReturnValueToJava(StringBuilder sb, int indent, VarOpts opts);

    public AllocationForParam allocationInfoForParam(VarOpts opts) {
        return AllocationForParam.noAllocationRequired();
    }

    public abstract String convertToUpcallArgument(String name, VarOpts opts);

    public String convertExtraToUpcallArgument(String name, VarOpts opts) {
        throw new UnsupportedOperationException();
    }

    public void convertFromUpcallReturn(StringBuilder sb, int indent, VarOpts opts) {
        Utils.appendIndent(sb, indent)
            .append("return RESULT == null ? MemorySegment.NULL : RESULT.MEMORY;\n");
    }

    public abstract void javaToString(StringBuilder sb, int indent, String callGetter, VarOpts opts);
}
