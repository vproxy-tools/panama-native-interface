package io.vproxy.pni.exec.type;

import io.vproxy.pni.PNIBuf;
import io.vproxy.pni.exec.internal.Utils;
import io.vproxy.pni.exec.internal.VarOpts;

import java.util.List;

public class ArrayTypeInfo extends TypeInfo {
    private final TypeInfo elementType;

    public ArrayTypeInfo(TypeInfo elementType) {
        this.elementType = elementType;
    }

    public TypeInfo getElementType() {
        return elementType;
    }

    @Override
    public String name() {
        return elementType.name() + "[]";
    }

    @Override
    public String internalName() {
        return elementType.internalName() + "[]";
    }

    @Override
    public String desc() {
        return "[" + elementType.desc();
    }

    @Override
    public void checkType(List<String> errors, String path, VarOpts opts) {
        super.checkType(errors, path, opts);
        if (!(elementType instanceof PrimitiveTypeInfo) && !(elementType instanceof ClassTypeInfo)) {
            errors.add(path + ": " + name() + " is not supported, only primitive and custom types can be used with array");
        }
    }

    @Override
    protected boolean canMarkWithUnsigned() {
        return elementType.canMarkWithUnsigned();
    }

    @Override
    protected boolean canMarkWithPointer() {
        return true;
    }

    @Override
    protected boolean canMarkWithLen() {
        return true;
    }

    @Override
    public String nativeType(String fieldName, VarOpts opts) {
        if (opts.isPointerGeneral()) {
            if (fieldName == null) {
                return "PNIBuf";
            } else {
                return "PNIBuf " + fieldName;
            }
        } else {
            return elementType.nativeType(null, opts) + " " + fieldName + "[" + opts.getLen() + "]";
        }
    }

    @Override
    public String nativeParamType(String fieldName, VarOpts opts) {
        String ret = "PNIBuf *";
        if (fieldName != null) {
            ret += " " + fieldName;
        }
        return ret;
    }

    @Override
    public long nativeMemorySize(VarOpts opts) {
        if (opts.isPointerGeneral()) {
            return PNIBuf.LAYOUT.byteSize();
        } else {
            return elementType.nativeMemorySize(opts) * opts.getLen();
        }
    }

    @Override
    public String memoryLayout(VarOpts opts) {
        if (opts.isPointerGeneral()) {
            return "PNIBuf.LAYOUT";
        } else {
            return "MemoryLayout.sequenceLayout(" + opts.getLen() + "L, " + elementType.memoryLayout(opts) + ")";
        }
    }

    @Override
    public String javaType(VarOpts opts) {
        if (elementType instanceof IntTypeInfo) {
            return "IntArray";
        } else if (elementType instanceof LongTypeInfo) {
            return "LongArray";
        } else if (elementType instanceof ShortTypeInfo) {
            return "ShortArray";
        } else if (elementType instanceof ByteTypeInfo) {
            return "MemorySegment";
        } else if (elementType instanceof FloatTypeInfo) {
            return "FloatArray";
        } else if (elementType instanceof DoubleTypeInfo) {
            return "DoubleArray";
        } else if (elementType instanceof BooleanTypeInfo) {
            return "BoolArray";
        } else if (elementType instanceof CharTypeInfo) {
            return "CharArray";
        } else if (elementType instanceof ClassTypeInfo classTypeInfo) {
            return classTypeInfo.getClazz().fullName() + ".Array";
        } else {
            throw new RuntimeException("unable to handle array with element type " + elementType);
        }
    }

    @Override
    public void generateGetterSetter(StringBuilder sb, int indent, String fieldName, VarOpts opts) {
        if (opts.isPointerGeneral()) {
            Utils.appendIndent(sb, indent)
                .append("private final PNIBuf ").append(fieldName).append(";\n");
            sb.append("\n");

            var typeName = javaType(opts);

            Utils.appendIndent(sb, indent)
                .append("public ").append(typeName).append(" ").append(Utils.getterName(fieldName)).append("() {\n");
            Utils.appendIndent(sb, indent + 4)
                .append("var SEG = this.").append(fieldName).append(".get();\n");
            Utils.appendIndent(sb, indent + 4)
                .append("if (SEG == null) return null;\n");
            Utils.appendIndent(sb, indent + 4);
            if (elementType instanceof ByteTypeInfo) {
                sb.append("return SEG;\n");
            } else {
                sb.append("return new ").append(typeName).append("(SEG);\n");
            }
            Utils.appendIndent(sb, indent).append("}\n");
            sb.append("\n");
            Utils.appendIndent(sb, indent)
                .append("public void ").append(Utils.setterName(fieldName)).append("(").append(typeName).append(" ").append(fieldName).append(") {\n");
            Utils.appendIndent(sb, indent + 4)
                .append("if (").append(fieldName).append(" == null) {\n");
            Utils.appendIndent(sb, indent + 8)
                .append("this.").append(fieldName).append(".setToNull();\n");
            Utils.appendIndent(sb, indent + 4).append("} else {\n");
            Utils.appendIndent(sb, indent + 8);
            if (elementType instanceof ByteTypeInfo) {
                sb.append("this.").append(fieldName).append(".set(").append(fieldName).append(");\n");
            } else {
                sb.append("this.").append(fieldName).append(".set(").append(fieldName).append(".MEMORY);\n");
            }
            Utils.appendIndent(sb, indent + 4).append("}\n");
            Utils.appendIndent(sb, indent).append("}\n");
        } else {
            var typeName = javaType(opts);
            Utils.appendIndent(sb, indent)
                .append("private final ").append(typeName).append(" ").append(fieldName).append(";\n");
            sb.append("\n");
            Utils.appendIndent(sb, indent)
                .append("public ").append(typeName).append(" ").append(Utils.getterName(fieldName)).append("() {\n");
            Utils.appendIndent(sb, indent + 4)
                .append("return this.").append(fieldName).append(";\n");
            Utils.appendIndent(sb, indent).append("}\n");
        }
    }

    @Override
    public void generateConstructor(StringBuilder sb, int indent, String fieldName, VarOpts opts) {
        if (opts.isPointerGeneral()) {
            Utils.appendIndent(sb, indent).append("this.").append(fieldName).append(" = new PNIBuf(")
                .append("MEMORY.asSlice(OFFSET, PNIBuf.LAYOUT.byteSize()));\n");
            Utils.appendIndent(sb, indent).append("OFFSET += PNIBuf.LAYOUT.byteSize();\n");
        } else {
            if (elementType instanceof ByteTypeInfo) {
                Utils.appendIndent(sb, indent)
                    .append("this.").append(fieldName).append(" = MEMORY.asSlice(OFFSET, ")
                    .append(opts.getLen()).append(");\n");
                Utils.appendIndent(sb, indent).append("OFFSET += ").append(opts.getLen()).append(";\n");
            } else if (elementType instanceof PrimitiveTypeInfo) {
                Utils.appendIndent(sb, indent)
                    .append("this.").append(fieldName).append(" = new ").append(javaType(opts)).append("(MEMORY.asSlice(OFFSET, ")
                    .append(opts.getLen()).append(" * ").append(elementType.memoryLayout(opts)).append(".byteSize()")
                    .append("));\n");
                Utils.appendIndent(sb, indent).append("OFFSET += ").append(opts.getLen()).append(" * ")
                    .append(elementType.memoryLayout(opts)).append(".byteSize();\n");
            } else if (elementType instanceof ClassTypeInfo classTypeInfo) {
                var cls = classTypeInfo.getClazz();
                Utils.appendIndent(sb, indent)
                    .append("this.").append(fieldName).append(" = new ").append(cls.fullName()).append(".Array(MEMORY.asSlice(OFFSET, ")
                    .append(opts.getLen()).append(" * ").append(cls.fullName()).append(".LAYOUT.byteSize()));\n");
                Utils.appendIndent(sb, indent).append("OFFSET += ").append(opts.getLen()).append(" * ")
                    .append(cls.fullName()).append(".LAYOUT.byteSize();\n");
            } else {
                throw new RuntimeException("unable to handle array with element type " + elementType);
            }
        }
    }

    @Override
    public String methodHandleType(VarOpts opts) {
        return "PNIBuf.class";
    }

    @Override
    public String convertToNativeCallArgument(String name, VarOpts opts) {
        return "PNIBuf.of(ARENA, " + name + ").MEMORY";
    }

    @Override
    public String sizeForConfinedArenaForNativeCallExtraArgument(VarOpts opts) {
        return "PNIBuf.LAYOUT.byteSize()";
    }

    @Override
    public void returnValueFormatting(StringBuilder sb, int indent, VarOpts opts) {
        Utils.appendIndent(sb, indent)
            .append("var RESULT = ENV.returnPointer();\n");
        Utils.appendIndent(sb, indent)
            .append("if (RESULT == null) return null;\n");
        Utils.appendIndent(sb, indent)
            .append("var RES_SEG = new PNIBuf(RESULT);\n");
        Utils.appendIndent(sb, indent)
            .append("if (RES_SEG.isNull()) return null;\n");
        Utils.appendIndent(sb, indent)
            .append("return ");
        var typeName = javaType(opts);
        if (elementType instanceof ByteTypeInfo) {
            sb.append("RES_SEG.get()");
        } else {
            sb.append("new ").append(typeName).append("(RES_SEG)");
        }
        sb.append(";\n");
    }

    @Override
    public boolean paramDependOnConfinedArena(VarOpts opts) {
        return opts.isPointerGeneral();
    }
}
