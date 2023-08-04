package io.vproxy.pni.exec.type;

import io.vproxy.pni.exec.internal.Utils;
import io.vproxy.pni.exec.internal.VarOpts;

public class IntTypeInfo extends PrimitiveTypeInfo {
    @Override
    public String internalName() {
        return "int";
    }

    @Override
    public String desc() {
        return "I";
    }

    @Override
    protected boolean canMarkWithUnsigned() {
        return true;
    }

    @Override
    public String nativeType(String fieldName, VarOpts opts) {
        var ret = "int32_t";
        if (opts.isUnsigned()) {
            ret = "u" + ret;
        }
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
    public String memoryLayoutForField(VarOpts opts) {
        return "ValueLayout.JAVA_INT_UNALIGNED";
    }

    @Override
    public String javaTypeForField(VarOpts opts) {
        return "int";
    }

    @Override
    public void convertInvokeExactReturnValueToJava(StringBuilder sb, int indent, VarOpts opts) {
        if (opts.isCritical()) {
            Utils.appendIndent(sb, indent).append("return RESULT;\n");
            return;
        }
        Utils.appendIndent(sb, indent).append("return ENV.returnInt();\n");
    }

    private IntTypeInfo() {
    }

    private static final IntTypeInfo INSTANCE = new IntTypeInfo();

    public static IntTypeInfo get() {
        return INSTANCE;
    }
}
