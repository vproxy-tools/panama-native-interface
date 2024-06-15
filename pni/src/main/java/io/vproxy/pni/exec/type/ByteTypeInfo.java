package io.vproxy.pni.exec.type;

import io.vproxy.pni.exec.internal.Utils;
import io.vproxy.pni.exec.internal.VarOpts;

public class ByteTypeInfo extends PrimitiveIntegerTypeInfo {
    @Override
    public String internalName() {
        return "byte";
    }

    @Override
    public String desc() {
        return "B";
    }

    @Override
    protected boolean canMarkWithUnsigned() {
        return true;
    }

    @Override
    public String nativeType(String fieldName, VarOpts opts) {
        var ret = "int8_t";
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
        return 1;
    }

    @Override
    public String memoryLayoutForField(VarOpts opts) {
        return "ValueLayout.JAVA_BYTE";
    }

    @Override
    public String javaTypeForField(VarOpts opts) {
        return "byte";
    }

    @Override
    public void convertInvokeExactReturnValueToJava(StringBuilder sb, int indent, VarOpts opts) {
        if (opts.isCritical()) {
            Utils.appendIndent(sb, indent).append("return RESULT;\n");
            return;
        }
        Utils.appendIndent(sb, indent).append("return ENV.returnByte();\n");
    }

    private ByteTypeInfo() {
    }

    private static final ByteTypeInfo INSTANCE = new ByteTypeInfo();

    public static ByteTypeInfo get() {
        return INSTANCE;
    }

    @Override
    protected String varHandleGetterName() {
        return "Byte";
    }
}
