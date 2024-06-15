package io.vproxy.pni.exec.type;

import io.vproxy.pni.exec.internal.Utils;
import io.vproxy.pni.exec.internal.VarOpts;

public class LongTypeInfo extends PrimitiveIntegerTypeInfo {
    @Override
    public String internalName() {
        return "long";
    }

    @Override
    public String desc() {
        return "J";
    }

    @Override
    protected boolean canMarkWithUnsigned() {
        return true;
    }

    @Override
    public String nativeType(String fieldName, VarOpts opts) {
        var ret = "int64_t";
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
        return 8;
    }

    @Override
    public String memoryLayoutForField(VarOpts opts) {
        return "ValueLayout.JAVA_LONG_UNALIGNED";
    }

    @Override
    public String javaTypeForField(VarOpts opts) {
        return "long";
    }

    @Override
    public void convertInvokeExactReturnValueToJava(StringBuilder sb, int indent, VarOpts opts) {
        if (opts.isCritical()) {
            Utils.appendIndent(sb, indent).append("return RESULT;\n");
            return;
        }
        Utils.appendIndent(sb, indent).append("return ENV.returnLong();\n");
    }

    private LongTypeInfo() {
    }

    private static final LongTypeInfo INSTANCE = new LongTypeInfo();

    public static LongTypeInfo get() {
        return INSTANCE;
    }

    @Override
    protected String varHandleGetterName() {
        return "Long";
    }
}
