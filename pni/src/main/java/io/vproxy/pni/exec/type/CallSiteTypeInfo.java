package io.vproxy.pni.exec.type;

import io.vproxy.pni.exec.internal.VarOpts;

import java.util.List;

public class CallSiteTypeInfo extends BuiltInReferenceTypeInfo {
    protected CallSiteTypeInfo() {
        super("io.vproxy.pni.CallSite", "io/vproxy/pni/CallSite", "Lio/vproxy/pni/CallSite;");
    }

    @Override
    public void checkType(List<String> errors, String path, VarOpts opts, boolean upcall) {
        super.checkType(errors, path, opts, upcall);
        if (this.getClass() == CallSiteTypeInfo.class) {
            errors.add(path + ": cannot use raw type of CallSite");
        }
    }

    @Override
    public String nativeEnvType(VarOpts opts) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String nativeType(String fieldName, VarOpts opts) {
        var ret = "PNIFunc *";
        if (fieldName != null) {
            ret += " " + fieldName;
        }
        return ret;
    }

    @Override
    public long nativeMemorySize(VarOpts opts) {
        return 8; // address
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
    public String javaTypeForParam(VarOpts opts) {
        throw new UnsupportedOperationException("implemented in subclass");
    }

    @Override
    public String javaTypeForUpcallParam(VarOpts opts) {
        return "MemorySegment";
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
        return "io.vproxy.pni.CallSite.class";
    }

    @Override
    public String methodHandleTypeForUpcall(VarOpts opts) {
        return "MemorySegment.class";
    }

    @Override
    public String convertParamToInvokeExactArgument(String name, VarOpts opts) {
        throw new UnsupportedOperationException("implemented in subclass");
    }

    @Override
    public void convertInvokeExactReturnValueToJava(StringBuilder sb, int indent, VarOpts opts) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String convertToUpcallArgument(String name, VarOpts opts) {
        throw new UnsupportedOperationException("implemented in subclass");
    }

    @Override
    public void convertFromUpcallReturn(StringBuilder sb, int indent, VarOpts opts) {
        throw new UnsupportedOperationException();
    }

    private static final CallSiteTypeInfo INSTANCE = new CallSiteTypeInfo();

    public static CallSiteTypeInfo get() {
        return INSTANCE;
    }
}
