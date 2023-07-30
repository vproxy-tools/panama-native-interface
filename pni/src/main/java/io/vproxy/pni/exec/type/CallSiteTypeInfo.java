package io.vproxy.pni.exec.type;

import io.vproxy.pni.exec.internal.VarOpts;

public class CallSiteTypeInfo extends BuiltInReferenceTypeInfo {
    private CallSiteTypeInfo() {
        super("io.vproxy.pni.CallSite", "io/vproxy/pni/CallSite", "Lio/vproxy/pni/CallSite;");
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
    public String memoryLayout(VarOpts opts) {
        return "ValueLayout.ADDRESS_UNALIGNED";
    }

    @Override
    public String javaType(VarOpts opts) {
        if (opts.getGenericParams().get(0) instanceof VoidRefTypeInfo) {
            return "io.vproxy.pni.CallSite<Void>";
        }
        return "io.vproxy.pni.CallSite<" + ((ClassTypeInfo) opts.getGenericParams().get(0)).getClazz().fullName() + ">";
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
    public String convertToNativeCallArgument(String name, VarOpts opts) {
        if (opts.getGenericParams().get(0) instanceof VoidRefTypeInfo) {
            return "PNIFunc.VoidFunc.of(" + name + ").MEMORY";
        }
        return ((ClassTypeInfo) opts.getGenericParams().get(0)).getClazz().fullName() + ".Func.of(" + name + ").MEMORY";
    }

    @Override
    public void returnValueFormatting(StringBuilder sb, int indent, VarOpts opts) {
        throw new UnsupportedOperationException();
    }

    private static final CallSiteTypeInfo INSTANCE = new CallSiteTypeInfo();

    public static CallSiteTypeInfo get() {
        return INSTANCE;
    }
}
