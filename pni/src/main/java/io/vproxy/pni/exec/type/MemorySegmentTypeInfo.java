package io.vproxy.pni.exec.type;

import io.vproxy.pni.exec.internal.Utils;
import io.vproxy.pni.exec.internal.VarOpts;

public class MemorySegmentTypeInfo extends BuiltInReferenceTypeInfo {
    private MemorySegmentTypeInfo() {
        super("java.lang.foreign.MemorySegment", "java/lang/foreign/MemorySegment", "Ljava/lang/foreign/MemorySegment;");
    }

    @Override
    public String nativeEnvType(VarOpts opts) {
        return "pointer";
    }

    @Override
    public String nativeType(String fieldName, VarOpts opts) {
        var ret = "void *";
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
        return "MemorySegment";
    }

    @Override
    public void generateGetterSetter(StringBuilder sb, int indent, String fieldName, VarOpts opts) {
        Utils.varHandleField(sb, indent, fieldName);
        sb.append("\n");
        Utils.appendIndent(sb, indent)
            .append("public MemorySegment ").append(Utils.getterName(fieldName)).append("() {\n");
        Utils.appendIndent(sb, indent + 4)
            .append("var SEG = (MemorySegment) ").append(fieldName).append("VH.get(MEMORY);\n");
        Utils.appendIndent(sb, indent + 4)
            .append("if (SEG.address() == 0) return null;\n");
        Utils.appendIndent(sb, indent + 4)
            .append("return SEG;\n");
        Utils.appendIndent(sb, indent).append("}\n");
        sb.append("\n");
        Utils.appendIndent(sb, indent)
            .append("public void ").append(Utils.setterName(fieldName)).append("(MemorySegment ").append(fieldName).append(") {\n");
        Utils.appendIndent(sb, indent + 4)
            .append("if (").append(fieldName).append(" == null) {\n");
        Utils.appendIndent(sb, indent + 8)
            .append(fieldName).append("VH.set(MEMORY, MemorySegment.NULL);\n");
        Utils.appendIndent(sb, indent + 4).append("} else {\n");
        Utils.appendIndent(sb, indent + 8)
            .append(fieldName).append("VH.set(MEMORY, ").append(fieldName).append(");\n");
        Utils.appendIndent(sb, indent + 4).append("}\n");
        Utils.appendIndent(sb, indent).append("}\n");
    }

    @Override
    public void generateConstructor(StringBuilder sb, int indent, String fieldName, VarOpts opts) {
        Utils.appendIndent(sb, indent).append("OFFSET += ValueLayout.ADDRESS_UNALIGNED.byteSize();\n");
    }

    @Override
    public String methodHandleType(VarOpts opts) {
        return "MemorySegment.class";
    }

    @Override
    public String convertParamToInvokeExactArgument(String name, VarOpts opts) {
        return "(MemorySegment) (" + name + " == null ? MemorySegment.NULL : " + name + ")";
    }

    @Override
    public void convertInvokeExactReturnValueToJava(StringBuilder sb, int indent, VarOpts opts) {
        if (opts.isCritical()) {
            Utils.appendIndent(sb,indent).append("if (RESULT.address() == 0) return null;\n");
            Utils.appendIndent(sb, indent).append("return RESULT;\n");
            return;
        }
        Utils.appendIndent(sb, indent)
            .append("return ENV.returnPointer();\n");
    }

    private static final MemorySegmentTypeInfo INSTANCE = new MemorySegmentTypeInfo();

    public static MemorySegmentTypeInfo get() {
        return INSTANCE;
    }
}
