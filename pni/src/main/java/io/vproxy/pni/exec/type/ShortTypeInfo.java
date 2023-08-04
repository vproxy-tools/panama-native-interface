package io.vproxy.pni.exec.type;

import io.vproxy.pni.exec.internal.Utils;
import io.vproxy.pni.exec.internal.VarOpts;

public class ShortTypeInfo extends PrimitiveTypeInfo {
    @Override
    public String internalName() {
        return "short";
    }

    @Override
    public String desc() {
        return "S";
    }

    @Override
    protected boolean canMarkWithUnsigned() {
        return true;
    }

    @Override
    public String nativeType(String fieldName, VarOpts opts) {
        var ret = "int16_t";
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
        return 2;
    }

    @Override
    public String memoryLayoutForField(VarOpts opts) {
        return "ValueLayout.JAVA_SHORT_UNALIGNED";
    }

    @Override
    public String javaTypeForField(VarOpts opts) {
        return "short";
    }

    @Override
    public void convertInvokeExactReturnValueToJava(StringBuilder sb, int indent, VarOpts opts) {
        if (opts.isCritical()) {
            Utils.appendIndent(sb, indent).append("return RESULT;\n");
            return;
        }
        Utils.appendIndent(sb, indent).append("return ENV.returnShort();\n");
    }

    private ShortTypeInfo() {
    }

    private static final ShortTypeInfo INSTANCE = new ShortTypeInfo();

    public static ShortTypeInfo get() {
        return INSTANCE;
    }
}
