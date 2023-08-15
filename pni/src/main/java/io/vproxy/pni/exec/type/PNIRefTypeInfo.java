package io.vproxy.pni.exec.type;

import io.vproxy.pni.exec.internal.Utils;
import io.vproxy.pni.exec.internal.VarOpts;

import java.util.List;

public class PNIRefTypeInfo extends BuiltInReferenceTypeInfo {
    protected PNIRefTypeInfo() {
        super("io.vproxy.pni.PNIRef", "io/vproxy/pni/PNIRef", "Lio/vproxy/pni/PNIRef;");
    }

    @Override
    public void checkType(List<String> errors, String path, VarOpts opts) {
        super.checkType(errors, path, opts);
        if (this.getClass() == PNIRefTypeInfo.class) {
            errors.add(path + ": cannot use raw type of PNIRef");
        }
    }

    @Override
    protected boolean canMarkWithRaw() {
        return true;
    }

    @Override
    public String nativeEnvType(VarOpts opts) {
        return "ref";
    }

    @Override
    public String nativeType(String fieldName, VarOpts opts) {
        var ret = "PNIRef *";
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
        throw new UnsupportedOperationException("implemented in subclass");
    }

    @Override
    public void generateGetterSetter(StringBuilder sb, int indent, String fieldName, VarOpts opts) {
        throw new UnsupportedOperationException("implemented in subclass");
    }

    @Override
    public void generateConstructor(StringBuilder sb, int indent, String fieldName, VarOpts opts) {
        Utils.appendIndent(sb, indent).append("OFFSET += ValueLayout.ADDRESS_UNALIGNED.byteSize();\n");
    }

    @Override
    public String methodHandleType(VarOpts opts) {
        return "PNIRef.class";
    }

    @Override
    public String convertParamToInvokeExactArgument(String name, VarOpts opts) {
        if (opts.isRaw()) {
            return "(MemorySegment) (" + name + " == null ? MemorySegment.NULL : " + name + ".MEMORY)";
        } else {
            return "(MemorySegment) (" + name + " == null ? MemorySegment.NULL : PNIRef.of(" + name + ").MEMORY)";
        }
    }

    @Override
    public void convertInvokeExactReturnValueToJava(StringBuilder sb, int indent, VarOpts opts) {
        if (opts.isCritical()) {
            Utils.appendIndent(sb, indent).append("if (RESULT.address() == 0) return null;\n");
        } else {
            Utils.appendIndent(sb, indent)
                .append("var RESULT = ENV.returnPointer();\n");
            Utils.appendIndent(sb, indent).append("if (RESULT == null) return null;\n");
        }
        Utils.appendIndent(sb, indent).append("return PNIRef.of(RESULT);\n");
    }

    private static final PNIRefTypeInfo INSTANCE = new PNIRefTypeInfo();

    public static PNIRefTypeInfo get() {
        return INSTANCE;
    }
}
