package io.vproxy.pni.exec.type;

import io.vproxy.pni.exec.internal.Utils;
import io.vproxy.pni.exec.internal.VarOpts;

public class DoubleTypeInfo extends PrimitiveTypeInfo {
    @Override
    public String internalName() {
        return "double";
    }

    @Override
    public String desc() {
        return "D";
    }

    @Override
    public String nativeType(String fieldName, VarOpts opts) {
        var ret = "double";
        if (fieldName != null) {
            ret += " " + fieldName;
        }
        return ret;
    }

    @Override
    public long nativeMemorySize(VarOpts opts) {
        return 8;
    }

    @Override
    public String memoryLayout(VarOpts opts) {
        return "ValueLayout.JAVA_DOUBLE_UNALIGNED";
    }

    @Override
    public String javaType(VarOpts opts) {
        return "double";
    }

    @Override
    public void returnValueFormatting(StringBuilder sb, int indent, VarOpts opts) {
        if (opts.isCritical()) {
            Utils.appendIndent(sb, indent).append("return RESULT;\n");
            return;
        }
        Utils.appendIndent(sb, indent).append("return ENV.returnDouble();\n");
    }

    private DoubleTypeInfo() {
    }

    private static final DoubleTypeInfo INSTANCE = new DoubleTypeInfo();

    public static DoubleTypeInfo get() {
        return INSTANCE;
    }
}
