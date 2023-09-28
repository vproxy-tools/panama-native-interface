package io.vproxy.pni.exec.type;

import io.vproxy.pni.exec.internal.Utils;
import io.vproxy.pni.exec.internal.VarOpts;

public abstract class PrimitiveTypeInfo extends TypeInfo {
    @Override
    public String name() {
        return internalName();
    }

    @Override
    public String nativeEnvType(VarOpts opts) {
        return name();
    }

    @Override
    public String javaTypeForUpcallParam(VarOpts opts) {
        return javaTypeForField(opts);
    }

    @Override
    public void generateGetterSetter(StringBuilder sb, int indent, String fieldName, VarOpts opts) {
        Utils.varHandleField(sb, indent, fieldName);
        sb.append("\n");
        Utils.appendIndent(sb, indent)
            .append("public ").append(name()).append(" ").append(Utils.getterName(fieldName)).append("() {\n");
        Utils.appendIndent(sb, indent + 4)
            .append("return (").append(name()).append(") ").append(fieldName).append("VH.get(MEMORY);\n");
        Utils.appendIndent(sb, indent).append("}\n");
        sb.append("\n");
        Utils.appendIndent(sb, indent)
            .append("public void ").append(Utils.setterName(fieldName)).append("(").append(name()).append(" ").append(fieldName).append(") {\n");
        Utils.appendIndent(sb, indent + 4)
            .append(fieldName).append("VH.set(MEMORY, ").append(fieldName).append(");\n");
        Utils.appendIndent(sb, indent).append("}\n");
    }

    @Override
    public void generateConstructor(StringBuilder sb, int indent, String fieldName, VarOpts opts) {
        Utils.appendIndent(sb, indent).append("OFFSET += ").append(memoryLayoutForField(opts)).append(".byteSize();\n");
    }

    @Override
    public String methodHandleType(VarOpts opts) {
        return name() + ".class";
    }

    @Override
    public String methodHandleTypeForUpcall(VarOpts opts) {
        return methodHandleType(opts);
    }

    @Override
    public String convertParamToInvokeExactArgument(String name, VarOpts opts) {
        return name;
    }

    @Override
    public long nativeMemoryAlign(VarOpts opts) {
        return nativeMemorySize(opts);
    }

    @Override
    public String convertToUpcallArgument(String name, VarOpts opts) {
        return name;
    }

    @Override
    public void convertFromUpcallReturn(StringBuilder sb, int indent, VarOpts opts) {
        Utils.appendIndent(sb, indent)
            .append("return RESULT;\n");
    }

    @Override
    public void javaToString(StringBuilder sb, int indent, String callGetter, VarOpts opts) {
        Utils.appendIndent(sb, indent)
            .append("SB.append(").append(callGetter).append(");\n");
    }
}
