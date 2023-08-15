package io.vproxy.pni.exec.type;

import io.vproxy.pni.exec.internal.Utils;
import io.vproxy.pni.exec.internal.VarOpts;

import java.util.List;

public class PNIFuncTypeInfo extends BuiltInReferenceTypeInfo {
    protected PNIFuncTypeInfo() {
        super("io.vproxy.pni.PNIFunc", "io/vproxy/pni/PNIFunc", "Lio/vproxy/pni/PNIFunc;");
    }

    @Override
    public void checkType(List<String> errors, String path, VarOpts opts) {
        super.checkType(errors, path, opts);
        if (this.getClass() == PNIFuncTypeInfo.class) {
            errors.add(path + ": cannot use raw type of PNIFunc");
        }
    }

    @Override
    public String nativeEnvType(VarOpts opts) {
        return "func";
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
        return 8;
    }

    @Override
    public String memoryLayoutForField(VarOpts opts) {
        return "ValueLayout.ADDRESS_UNALIGNED";
    }

    @Override
    public String javaTypeForField(VarOpts opts) {
        throw new UnsupportedOperationException("implemented in subclass");
    }

    @Override
    public void generateGetterSetter(StringBuilder sb, int indent, String fieldName, VarOpts opts) {
        throw new UnsupportedOperationException("implemented in subclass");
    }

    @Override
    public void generateConstructor(StringBuilder sb, int indent, String fieldName, VarOpts opts) {
        Utils.appendIndent(sb, indent).append("OFFSET += ValueLayout.ADDRESS_UNALIGNED.byteSize();\n");
    }

    @Override
    public String methodHandleType(VarOpts opts) {
        return "PNIFunc.class";
    }

    @Override
    public String convertParamToInvokeExactArgument(String name, VarOpts opts) {
        return "(MemorySegment) (" + name + " == null ? MemorySegment.NULL : " + name + ".MEMORY)";
    }

    @Override
    public void convertInvokeExactReturnValueToJava(StringBuilder sb, int indent, VarOpts opts) {
        throw new UnsupportedOperationException("implemented in subclass");
    }

    private static final PNIFuncTypeInfo INSTANCE = new PNIFuncTypeInfo();

    public static PNIFuncTypeInfo get() {
        return INSTANCE;
    }
}
