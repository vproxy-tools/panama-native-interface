package io.vproxy.pni.exec.type;

import io.vproxy.pni.exec.internal.VarOpts;

public abstract class NoGenReferenceTypeInfo extends BuiltInReferenceTypeInfo {
    public NoGenReferenceTypeInfo(String name, String internalName, String desc) {
        super(name, internalName, desc);
    }

    @Override
    public String nativeEnvType(VarOpts opts) {
        throw new UnsupportedOperationException();
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
    public long nativeMemoryAlign(VarOpts opts) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String memoryLayoutForField(VarOpts opts) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String javaTypeForField(VarOpts opts) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String javaTypeForUpcallParam(VarOpts opts) {
        throw new UnsupportedOperationException();
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
    public String methodHandleTypeForUpcall(VarOpts opts) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String convertParamToInvokeExactArgument(String name, VarOpts opts) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void convertInvokeExactReturnValueToJava(StringBuilder sb, int indent, VarOpts opts) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String convertToUpcallArgument(String name, VarOpts opts) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void convertFromUpcallReturn(StringBuilder sb, int indent, VarOpts opts) {
        throw new UnsupportedOperationException();
    }
}
