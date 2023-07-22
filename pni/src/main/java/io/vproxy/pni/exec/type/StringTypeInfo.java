package io.vproxy.pni.exec.type;

import io.vproxy.pni.exec.internal.Utils;
import io.vproxy.pni.exec.internal.VarOpts;

public class StringTypeInfo extends BuiltInReferenceTypeInfo {
    private StringTypeInfo() {
        super("java.lang.String", "java/lang/String", "Ljava/lang/String;");
    }

    @Override
    protected boolean canMarkWithLen() {
        return true;
    }

    @Override
    public String nativeType(String fieldName, VarOpts opts) {
        String ret;
        if (opts.isPointerGeneral()) {
            ret = "char *";
        } else {
            ret = "char";
        }
        if (fieldName != null) {
            ret += " " + fieldName;
            if (opts.getLen() >= 0) {
                ret += "[" + opts.getLen() + "]";
            }
        }
        return ret;
    }

    @Override
    public long nativeMemorySize(VarOpts opts) {
        if (opts.isPointerGeneral()) {
            return 8;
        } else {
            return opts.getLen();
        }
    }

    @Override
    public String memoryLayout(VarOpts opts) {
        if (opts.isPointerGeneral()) {
            return "ValueLayout.ADDRESS_UNALIGNED";
        } else {
            return "MemoryLayout.sequenceLayout(" + opts.getLen() + "L, ValueLayout.JAVA_BYTE)";
        }
    }

    @Override
    public String javaType(VarOpts opts) {
        return "String";
    }

    @Override
    public void generateGetterSetter(StringBuilder sb, int indent, String fieldName, VarOpts opts) {
        if (opts.isPointerGeneral()) {
            Utils.varHandleField(sb, indent, fieldName);
            sb.append("\n");
            Utils.appendIndent(sb, indent)
                .append("public String ").append(Utils.getterName(fieldName)).append("() {\n");
            Utils.appendIndent(sb, indent + 4)
                .append("var SEG = (MemorySegment) ").append(fieldName).append("VH.get(MEMORY);\n");
            Utils.appendIndent(sb, indent + 4)
                .append("if (SEG.address() == 0) return null;\n");
            Utils.appendIndent(sb, indent + 4)
                .append("return SEG.reinterpret(Integer.MAX_VALUE).getUtf8String(0);\n");
            Utils.appendIndent(sb, indent).append("}\n");
            sb.append("\n");
            Utils.appendIndent(sb, indent)
                .append("public void ").append(Utils.setterName(fieldName)).append("(String ").append(fieldName).append(", Allocator ALLOCATOR) {\n");
            Utils.appendIndent(sb, indent + 4)
                .append(fieldName).append("VH.set(MEMORY, PanamaUtils.format(").append(fieldName).append(", ALLOCATOR));\n");
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
        } else {
            Utils.appendIndent(sb, indent)
                .append("private final MemorySegment ").append(fieldName).append(";\n");
            sb.append("\n");
            Utils.appendIndent(sb, indent)
                .append("public String ").append(Utils.getterName(fieldName)).append("() {\n");
            Utils.appendIndent(sb, indent + 4)
                .append("return ").append(fieldName).append(".getUtf8String(0)").append(";\n");
            Utils.appendIndent(sb, indent).append("}\n");
            sb.append("\n");
            Utils.appendIndent(sb, indent)
                .append("public void ").append(Utils.setterName(fieldName)).append("(String ").append(fieldName).append(") {\n");
            Utils.appendIndent(sb, indent + 4)
                .append("this.").append(fieldName).append(".setUtf8String(0, ").append(fieldName).append(")").append(";\n");
            Utils.appendIndent(sb, indent).append("}\n");
        }
    }

    @Override
    public void generateConstructor(StringBuilder sb, int indent, String fieldName, VarOpts opts) {
        if (opts.isPointerGeneral()) {
            Utils.appendIndent(sb, indent).append("OFFSET += 8;\n");
            return;
        }
        Utils.appendIndent(sb, indent).append("this.").append(fieldName).append(" = MEMORY.asSlice(OFFSET, ").append(opts.getLen()).append(");\n");
        Utils.appendIndent(sb, indent).append("OFFSET += ").append(opts.getLen()).append(";\n");
    }

    @Override
    public String methodHandleType(VarOpts opts) {
        return "String.class";
    }

    @Override
    public String convertToNativeCallArgument(String name, VarOpts opts) {
        return "PanamaUtils.format(" + name + ", ARENA)";
    }

    @Override
    public void returnValueFormatting(StringBuilder sb, int indent, VarOpts opts) {
        Utils.appendIndent(sb, indent)
            .append("var RESULT = ENV.returnPointer();\n");
        Utils.appendIndent(sb, indent)
            .append("return RESULT == null ? null : RESULT.reinterpret(Integer.MAX_VALUE).getUtf8String(0);\n");
    }

    @Override
    public boolean paramDependOnConfinedArena(VarOpts opts) {
        return opts.isPointerGeneral();
    }

    private static final StringTypeInfo INSTANCE = new StringTypeInfo();

    public static StringTypeInfo get() {
        return INSTANCE;
    }
}
