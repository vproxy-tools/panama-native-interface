package io.vproxy.pni.exec.type;

import io.vproxy.pni.exec.internal.Utils;
import io.vproxy.pni.exec.internal.VarOpts;

public class CharTypeInfo extends PrimitiveTypeInfo {
    @Override
    public String internalName() {
        return "char";
    }

    @Override
    public String desc() {
        return "C";
    }

    @Override
    public String nativeType(String fieldName, VarOpts opts) {
        var ret = "uint16_t";
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
        return "ValueLayout.JAVA_CHAR_UNALIGNED";
    }

    @Override
    public String javaTypeForField(VarOpts opts) {
        return "char";
    }

    @Override
    public void convertInvokeExactReturnValueToJava(StringBuilder sb, int indent, VarOpts opts) {
        if (opts.isCritical()) {
            Utils.appendIndent(sb, indent).append("return RESULT;\n");
            return;
        }
        Utils.appendIndent(sb, indent).append("return ENV.returnChar();\n");
    }

    @Override
    protected String varHandleGetterName() {
        return "Char";
    }

    @Override
    public void javaToString(StringBuilder sb, int indent, String callGetter, VarOpts opts) {
        Utils.appendIndent(sb, indent)
            .append("SB.append(PanamaUtils.charToASCIIString(").append(callGetter).append("));\n");
    }

    private CharTypeInfo() {
    }

    private static final CharTypeInfo INSTANCE = new CharTypeInfo();

    public static CharTypeInfo get() {
        return INSTANCE;
    }
}
