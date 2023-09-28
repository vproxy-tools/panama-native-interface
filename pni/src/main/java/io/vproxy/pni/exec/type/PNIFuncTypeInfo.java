package io.vproxy.pni.exec.type;

import io.vproxy.pni.exec.internal.Utils;
import io.vproxy.pni.exec.internal.VarOpts;

import java.util.List;

public class PNIFuncTypeInfo extends BuiltInReferenceTypeInfo {
    protected PNIFuncTypeInfo() {
        super("io.vproxy.pni.PNIFunc", "io/vproxy/pni/PNIFunc", "Lio/vproxy/pni/PNIFunc;");
    }

    @Override
    public void checkType(List<String> errors, String path, VarOpts opts, boolean upcall) {
        super.checkType(errors, path, opts, upcall);
        if (this.getClass() == PNIFuncTypeInfo.class) {
            errors.add(path + ": cannot use raw type of PNIFunc");
        }
    }

    @Override
    protected boolean canMarkWithRaw() {
        return true;
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
    public String javaTypeForUpcallParam(VarOpts opts) {
        return "MemorySegment";
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
        if (opts.isRaw()) {
            return "PNIFunc.class";
        } else {
            return "io.vproxy.pni.CallSite.class";
        }
    }

    @Override
    public String methodHandleTypeForReturn(VarOpts opts) {
        return "PNIFunc.class";
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
        throw new UnsupportedOperationException("implemented in subclass");
    }

    @Override
    public String convertToUpcallArgument(String name, VarOpts opts) {
        throw new UnsupportedOperationException("implemented in subclass");
    }

    @Override
    public void javaToString(StringBuilder sb, int indent, String callGetter, VarOpts opts) {
        Utils.appendIndent(sb, indent)
            .append("if (CORRUPTED_MEMORY) SB.append(\"<?>\");\n");
        Utils.appendIndent(sb, indent)
            .append("else {\n");
        Utils.appendIndent(sb, indent + 4)
            .append("var VALUE = ").append(callGetter).append(";\n");
        Utils.appendIndent(sb, indent + 4)
            .append("if (VALUE == null) SB.append(\"null\");\n");
        Utils.appendIndent(sb, indent + 4)
            .append("else VALUE.toString(SB, INDENT, VISITED, CORRUPTED_MEMORY);\n");
        Utils.appendIndent(sb, indent)
            .append("}\n");
    }

    private static final PNIFuncTypeInfo INSTANCE = new PNIFuncTypeInfo();

    public static PNIFuncTypeInfo get() {
        return INSTANCE;
    }
}
