package io.vproxy.pni.exec.type;

import io.vproxy.pni.exec.internal.AllocationForParam;
import io.vproxy.pni.exec.internal.AllocationForReturnedValue;
import io.vproxy.pni.exec.internal.Utils;
import io.vproxy.pni.exec.internal.VarOpts;

public class ByteBufferTypeInfo extends BuiltInReferenceTypeInfo {
    private ByteBufferTypeInfo() {
        super("java.nio.ByteBuffer", "java/nio/ByteBuffer", "Ljava/nio/ByteBuffer;");
    }

    @Override
    protected boolean canMarkWithRaw() {
        return true;
    }

    @Override
    public String nativeEnvType(VarOpts opts) {
        return "buf";
    }

    @Override
    public String nativeType(String fieldName, VarOpts opts) {
        var ret = "PNIBuf";
        if (fieldName != null) {
            ret += " " + fieldName;
        }
        return ret;
    }

    @Override
    public String nativeParamType(String fieldName, VarOpts opts) {
        String ret;
        if (opts.isRaw()) {
            ret = "void *";
        } else {
            ret = "PNIBuf *";
        }
        if (fieldName != null) {
            ret += " " + fieldName;
        }
        return ret;
    }

    @Override
    public long nativeMemorySize(VarOpts opts) {
        if (opts.isRaw()) {
            return 8; // address
        } else {
            return 16; // PNIBuf.LAYOUT.byteSize();
        }
    }

    @Override
    public long nativeMemoryAlign(VarOpts opts) {
        return 8;
    }

    @Override
    public String memoryLayoutForField(VarOpts opts) {
        return "PNIBuf.LAYOUT";
    }

    @Override
    public String javaTypeForField(VarOpts opts) {
        return "ByteBuffer";
    }

    @Override
    public void generateGetterSetter(StringBuilder sb, int indent, String fieldName, VarOpts opts) {
        Utils.appendIndent(sb, indent)
            .append("private final PNIBuf ").append(fieldName).append(";\n");
        sb.append("\n");
        Utils.appendIndent(sb, indent)
            .append("public ByteBuffer ").append(Utils.getterName(fieldName)).append("() {\n");
        Utils.appendIndent(sb, indent + 4)
            .append("var SEG = this.").append(fieldName).append(".get();\n");
        Utils.appendIndent(sb, indent + 4)
            .append("if (SEG == null) return null;\n");
        Utils.appendIndent(sb, indent + 4)
            .append("return SEG.asByteBuffer();\n");
        Utils.appendIndent(sb, indent).append("}\n");
        sb.append("\n");
        Utils.appendIndent(sb, indent)
            .append("public void ").append(Utils.setterName(fieldName)).append("(ByteBuffer ").append(fieldName).append(") {\n");
        Utils.appendIndent(sb, indent + 4)
            .append("if (").append(fieldName).append(" == null) {\n");
        Utils.appendIndent(sb, indent + 8)
            .append("this.").append(fieldName).append(".setToNull();\n");
        Utils.appendIndent(sb, indent + 4).append("} else {\n");
        Utils.appendIndent(sb, indent + 8)
            .append("this.").append(fieldName).append(".set(").append(fieldName).append(");\n");
        Utils.appendIndent(sb, indent + 4).append("}\n");
        Utils.appendIndent(sb, indent).append("}\n");
    }

    @Override
    public void generateConstructor(StringBuilder sb, int indent, String fieldName, VarOpts opts) {
        Utils.appendIndent(sb, indent)
            .append("this.").append(fieldName).append(" = new PNIBuf(MEMORY.asSlice(OFFSET, PNIBuf.LAYOUT.byteSize()));\n");
        Utils.appendIndent(sb, indent).append("OFFSET += PNIBuf.LAYOUT.byteSize();\n");
    }

    @Override
    public String methodHandleType(VarOpts opts) {
        if (opts.isRaw()) {
            return "ByteBuffer.class";
        } else {
            return "PNIBuf.class";
        }
    }

    @Override
    public String convertParamToInvokeExactArgument(String name, VarOpts opts) {
        if (opts.isRaw()) {
            return "PanamaUtils.format(" + name + ")";
        } else {
            return "PanamaUtils.format(" + name + ", POOLED)";
        }
    }

    @Override
    public AllocationForReturnedValue allocationInfoForReturnValue(VarOpts opts) {
        if (opts.isCritical()) {
            return AllocationForReturnedValue.ofPooledAllocator("PNIBuf.LAYOUT.byteSize()");
        }
        return super.allocationInfoForReturnValue(opts);
    }

    @Override
    public void convertInvokeExactReturnValueToJava(StringBuilder sb, int indent, VarOpts opts) {
        if (opts.isCritical()) {
            Utils.appendIndent(sb, indent)
                .append("if (RESULT.address() == 0) return null;\n");
            Utils.appendIndent(sb, indent)
                .append("var RES_SEG = new PNIBuf(RESULT);\n");
        } else {
            Utils.appendIndent(sb, indent)
                .append("var RES_SEG = ENV.returnBuf();\n");
        }
        Utils.appendIndent(sb, indent)
            .append("return RES_SEG.toByteBuffer();\n");
    }

    @Override
    public AllocationForParam allocationInfoForParam(VarOpts opts) {
        if (opts.isPointerGeneral() && !opts.isRaw()) {
            return AllocationForParam.ofPooledAllocator();
        }
        return AllocationForParam.noAllocationRequired();
    }

    private static final ByteBufferTypeInfo INSTANCE = new ByteBufferTypeInfo();

    public static ByteBufferTypeInfo get() {
        return INSTANCE;
    }
}
