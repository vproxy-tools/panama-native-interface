package io.vproxy.pni.exec.type;

import io.vproxy.pni.exec.internal.VarOpts;

public class VoidTypeInfo extends TypeInfo {
    @Override
    public String name() {
        return "void";
    }

    @Override
    public String internalName() {
        return "void";
    }

    @Override
    public String desc() {
        return "V";
    }

    @Override
    public String nativeEnvType(VarOpts opts) {
        return "void";
    }

    @Override
    public String nativeType(String fieldName, VarOpts opts) {
        return "void";
    }

    @Override
    public long nativeMemorySize(VarOpts opts) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long nativeMemoryAlign(VarOpts opts) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String memoryLayoutForField(VarOpts opts) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String javaTypeForField(VarOpts opts) {
        return "void";
    }

    @Override
    public String javaTypeForUpcallParam(VarOpts opts) {
        return "void";
    }

    @Override
    public void generateGetterSetter(StringBuilder sb, int indent, String fieldName, VarOpts opts) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void generateConstructor(StringBuilder sb, int indent, String fieldName, VarOpts opts) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String methodHandleType(VarOpts opts) {
        return "void.class";
    }

    @Override
    public String methodHandleTypeForUpcall(VarOpts opts) {
        return "void.class";
    }

    @Override
    public String convertParamToInvokeExactArgument(String name, VarOpts opts) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void convertInvokeExactReturnValueToJava(StringBuilder sb, int indent, VarOpts opts) {
        // do nothing
    }

    @Override
    public String convertToUpcallArgument(String name, VarOpts opts) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void convertFromUpcallReturn(StringBuilder sb, int indent, VarOpts opts) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void convertFromUpcallReturnGraal(StringBuilder sb, int indent, VarOpts opts) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void javaToString(StringBuilder sb, int indent, String callGetter, VarOpts opts) {
        throw new UnsupportedOperationException();
    }

    private VoidTypeInfo() {
    }

    private static final VoidTypeInfo INSTANCE = new VoidTypeInfo();

    public static VoidTypeInfo get() {
        return INSTANCE;
    }
}
