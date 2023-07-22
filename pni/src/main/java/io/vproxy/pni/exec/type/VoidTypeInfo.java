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
    public String nativeType(String fieldName, VarOpts opts) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long nativeMemorySize(VarOpts opts) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String memoryLayout(VarOpts opts) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String javaType(VarOpts opts) {
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
        throw new UnsupportedOperationException();
    }

    @Override
    public String convertToNativeCallArgument(String name, VarOpts opts) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void returnValueFormatting(StringBuilder sb, int indent, VarOpts opts) {
        throw new UnsupportedOperationException();
    }

    private VoidTypeInfo() {
    }

    private static final VoidTypeInfo INSTANCE = new VoidTypeInfo();

    public static VoidTypeInfo get() {
        return INSTANCE;
    }
}
