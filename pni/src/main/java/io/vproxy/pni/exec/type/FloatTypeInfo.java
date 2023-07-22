package io.vproxy.pni.exec.type;

import io.vproxy.pni.exec.internal.Utils;
import io.vproxy.pni.exec.internal.VarOpts;

public class FloatTypeInfo extends PrimitiveTypeInfo {
    @Override
    public String internalName() {
        return "float";
    }

    @Override
    public String desc() {
        return "F";
    }

    @Override
    public String nativeType(String fieldName, VarOpts opts) {
        var ret = "float";
        if (fieldName != null) {
            ret += " " + fieldName;
        }
        return ret;
    }

    @Override
    public long nativeMemorySize(VarOpts opts) {
        return 4;
    }

    @Override
    public String memoryLayout(VarOpts opts) {
        return "ValueLayout.JAVA_FLOAT_UNALIGNED";
    }

    @Override
    public String javaType(VarOpts opts) {
        return "float";
    }

    @Override
    public void returnValueFormatting(StringBuilder sb, int indent, VarOpts opts) {
        Utils.appendIndent(sb, indent).append("return ENV.returnFloat();\n");
    }

    private FloatTypeInfo() {
    }

    private static final FloatTypeInfo INSTANCE = new FloatTypeInfo();

    public static FloatTypeInfo get() {
        return INSTANCE;
    }
}
