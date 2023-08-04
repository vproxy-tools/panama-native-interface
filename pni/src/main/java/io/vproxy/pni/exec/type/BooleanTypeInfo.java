package io.vproxy.pni.exec.type;

import io.vproxy.pni.exec.internal.Utils;
import io.vproxy.pni.exec.internal.VarOpts;

public class BooleanTypeInfo extends PrimitiveTypeInfo {
    @Override
    public String internalName() {
        return "boolean";
    }

    @Override
    public String desc() {
        return "Z";
    }

    @Override
    public String nativeEnvType(VarOpts opts) {
        return "bool";
    }

    @Override
    public String nativeType(String fieldName, VarOpts opts) {
        var ret = "uint8_t";
        if (fieldName != null) {
            ret += " " + fieldName;
        }
        return ret;
    }

    @Override
    public long nativeMemorySize(VarOpts opts) {
        return 1;
    }

    @Override
    public String memoryLayoutForField(VarOpts opts) {
        return "ValueLayout.JAVA_BOOLEAN";
    }

    @Override
    public String javaTypeForField(VarOpts opts) {
        return "boolean";
    }

    @Override
    public void convertInvokeExactReturnValueToJava(StringBuilder sb, int indent, VarOpts opts) {
        if (opts.isCritical()) {
            Utils.appendIndent(sb, indent).append("return RESULT;\n");
            return;
        }
        Utils.appendIndent(sb, indent).append("return ENV.returnBool();\n");
    }

    private BooleanTypeInfo() {
    }

    private static final BooleanTypeInfo INSTANCE = new BooleanTypeInfo();

    public static BooleanTypeInfo get() {
        return INSTANCE;
    }
}
