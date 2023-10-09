package io.vproxy.pni.exec.type;

import io.vproxy.pni.exec.internal.AllocationForParam;
import io.vproxy.pni.exec.internal.AllocationForReturnedValue;
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
    public void checkType(List<String> errors, String path, VarOpts opts, boolean upcall) {
        super.checkType(errors, path, opts, upcall);
        if (elementType instanceof PrimitiveTypeInfo || elementType instanceof ClassTypeInfo) {
            if (elementType.nativeMemorySize(opts) == 0) {
                errors.add(path + ": " + name() + " is not supported because the element type byteSize is 0");
            }
        } else {
            errors.add(path + ": " + name() + " is not supported, only primitive and custom types can be used with array");
        }
        if (upcall && opts.isRaw()) {
            errors.add(path + ": upcall array cannot be marked with @Raw");
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
    protected boolean canMarkWithRaw() {
        return true;
    }

    @Override
    public String nativeEnvType(VarOpts opts) {
        return "buf_" + pniBufSuffix(opts);
    }

    private String pniBufSuffix(VarOpts opts) {
        if (elementType instanceof ByteTypeInfo) {
            if (opts.isUnsigned()) return "ubyte";
            return "byte";
        } else if (elementType instanceof CharTypeInfo) {
            return "char";
        } else if (elementType instanceof IntTypeInfo) {
            if (opts.isUnsigned()) return "uint";
            return "int";
        } else if (elementType instanceof LongTypeInfo) {
            if (opts.isUnsigned()) return "ulong";
            return "long";
        } else if (elementType instanceof FloatTypeInfo) {
            return "float";
        } else if (elementType instanceof DoubleTypeInfo) {
            return "double";
        } else if (elementType instanceof ShortTypeInfo) {
            if (opts.isUnsigned()) return "ushort";
            return "short";
        } else if (elementType instanceof BooleanTypeInfo) {
            return "bool";
        } else {
            assert elementType instanceof ClassTypeInfo;
            return ((ClassTypeInfo) elementType).getClazz().nativeName();
        }
    }

    @Override
    public String nativeType(String fieldName, VarOpts opts) {
        if (opts.isPointerGeneral()) {
            if (fieldName == null) {
                return "PNIBuf_" + pniBufSuffix(opts);
            } else {
                return "PNIBuf_" + pniBufSuffix(opts) + " " + fieldName;
            }
        } else {
            return elementType.nativeType(null, opts) + " " + fieldName + "[" + opts.getLen() + "]";
        }
    }

    @Override
    public String nativeParamType(String fieldName, VarOpts opts) {
        String ret;
        if (opts.isRaw()) {
            if (elementType instanceof ByteTypeInfo) {
                if (opts.isUnsigned()) {
                    ret = "int8_t *"; // u prefix will be added later
                } else {
                    ret = "void *";
                }
            } else if (elementType instanceof BooleanTypeInfo) {
                ret = "uint8_t *";
            } else if (elementType instanceof CharTypeInfo) {
                ret = "uint16_t *";
            } else if (elementType instanceof DoubleTypeInfo) {
                ret = "double *";
            } else if (elementType instanceof FloatTypeInfo) {
                ret = "float *";
            } else if (elementType instanceof IntTypeInfo) {
                ret = "int32_t *";
            } else if (elementType instanceof LongTypeInfo) {
                ret = "int64_t *";
            } else if (elementType instanceof ShortTypeInfo) {
                ret = "int16_t *";
            } else {
                assert elementType instanceof ClassTypeInfo;
                var classTypeInfo = (ClassTypeInfo) elementType;
                var clazz = classTypeInfo.getClazz();
                ret = clazz.nativeTypeName() + " *";
            }
            if (opts.isUnsigned()) {
                ret = "u" + ret;
            }
        } else {
            ret = "PNIBuf_" + pniBufSuffix(opts) + " *";
        }
        if (fieldName != null) {
            ret += " " + fieldName;
        }
        return ret;
    }

    @Override
    public long nativeMemorySize(VarOpts opts) {
        if (opts.isPointerGeneral()) {
            return 16; // PNIBuf.LAYOUT.byteSize();
        } else {
            return elementType.nativeMemorySize(opts) * opts.getLen();
        }
    }

    @Override
    public long nativeMemoryAlign(VarOpts opts) {
        if (opts.isPointerGeneral()) {
            return 8;
        } else {
            return elementType.nativeMemoryAlign(opts);
        }
    }

    @Override
    public String memoryLayoutForField(VarOpts opts) {
        if (opts.isPointerGeneral()) {
            return "PNIBuf.LAYOUT";
        } else {
            return "MemoryLayout.sequenceLayout(" + opts.getLen() + "L, " + elementType.memoryLayoutForField(opts) + ")";
        }
    }

    @Override
    public String javaTypeForField(VarOpts opts) {
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
        } else if (elementType instanceof ClassTypeInfo) {
            var classTypeInfo = (ClassTypeInfo) elementType;
            return classTypeInfo.getClazz().fullName() + ".Array";
        } else {
            throw new RuntimeException("unable to handle array with element type " + elementType);
        }
    }

    @Override
    public String javaTypeForUpcallParam(VarOpts opts) {
        return "MemorySegment";
    }

    @Override
    public void generateGetterSetter(StringBuilder sb, int indent, String fieldName, VarOpts opts) {
        if (opts.isPointerGeneral()) {
            Utils.appendIndent(sb, indent)
                .append("private final PNIBuf ").append(fieldName).append(";\n");
            sb.append("\n");

            var typeName = javaTypeForField(opts);

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
            var typeName = javaTypeForField(opts);
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
                    .append("this.").append(fieldName).append(" = new ").append(javaTypeForField(opts)).append("(MEMORY.asSlice(OFFSET, ")
                    .append(opts.getLen()).append(" * ").append(elementType.memoryLayoutForField(opts)).append(".byteSize()")
                    .append("));\n");
                Utils.appendIndent(sb, indent).append("OFFSET += ").append(opts.getLen()).append(" * ")
                    .append(elementType.memoryLayoutForField(opts)).append(".byteSize();\n");
            } else if (elementType instanceof ClassTypeInfo) {
                var classTypeInfo = (ClassTypeInfo) elementType;
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
        if (opts.isRaw()) {
            return "MemorySegment.class";
        } else {
            return "PNIBuf.class";
        }
    }

    @Override
    public String methodHandleTypeForUpcall(VarOpts opts) {
        return "MemorySegment.class";
    }

    @Override
    public String convertParamToInvokeExactArgument(String name, VarOpts opts) {
        if (opts.isRaw()) {
            if (elementType instanceof ByteTypeInfo) {
                return "(MemorySegment) (" + name + " == null ? MemorySegment.NULL : " + name + ")";
            } else {
                return "(MemorySegment) (" + name + " == null ? MemorySegment.NULL : " + name + ".MEMORY)";
            }
        } else {
            return "PNIBuf.memoryOf(POOLED, " + name + ")";
        }
    }

    @Override
    public AllocationForReturnedValue allocationInfoForReturnValue(VarOpts opts) {
        if (opts.isCritical()) {
            return AllocationForReturnedValue.ofPooledAllocator("PNIBuf.LAYOUT");
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
            .append("if (RES_SEG.isNull()) return null;\n");
        Utils.appendIndent(sb, indent)
            .append("return ");
        var typeName = javaTypeForField(opts);
        if (elementType instanceof ByteTypeInfo) {
            sb.append("RES_SEG.get()");
        } else {
            sb.append("new ").append(typeName).append("(RES_SEG)");
        }
        sb.append(";\n");
    }

    @Override
    public AllocationForParam allocationInfoForParam(VarOpts opts) {
        if (opts.isRaw()) {
            return AllocationForParam.noAllocationRequired();
        }
        if (opts.isPointerGeneral()) {
            return AllocationForParam.ofPooledAllocator();
        }
        return AllocationForParam.noAllocationRequired();
    }

    @Override
    public String convertToUpcallArgument(String name, VarOpts opts) {
        var sb = new StringBuilder();
        sb.append("(").append(name).append(".address() == 0 ? null : ");
        if (elementType instanceof ByteTypeInfo) {
            sb.append("new PNIBuf(").append(name).append(").get()");
        } else if (elementType instanceof ClassTypeInfo) {
            var classTypeInfo = (ClassTypeInfo) elementType;
            sb.append("new ").append(classTypeInfo.getClazz().fullName()).append(".Array(new PNIBuf(").append(name).append(").get())");
        } else {
            sb.append("new PNIBuf(").append(name).append(").to");
            if (elementType instanceof BooleanTypeInfo) {
                sb.append("BoolArray");
            } else if (elementType instanceof CharTypeInfo) {
                sb.append("CharArray");
            } else if (elementType instanceof DoubleTypeInfo) {
                sb.append("DoubleArray");
            } else if (elementType instanceof FloatTypeInfo) {
                sb.append("FloatArray");
            } else if (elementType instanceof IntTypeInfo) {
                sb.append("IntArray");
            } else if (elementType instanceof LongTypeInfo) {
                sb.append("LongArray");
            } else if (elementType instanceof ShortTypeInfo) {
                sb.append("ShortArray");
            } else {
                throw new RuntimeException("unable to handle array with element type " + elementType);
            }
            sb.append("()");
        }
        sb.append(")");
        return sb.toString();
    }

    @Override
    public void convertFromUpcallReturn(StringBuilder sb, int indent, VarOpts opts) {
        Utils.appendIndent(sb, indent)
            .append("if (RESULT == null) return MemorySegment.NULL;\n");
        Utils.appendIndent(sb, indent)
            .append("var RETURN = new PNIBuf(return_);\n");
        if (elementType instanceof ByteTypeInfo) {
            Utils.appendIndent(sb, indent)
                .append("RETURN.set(RESULT);\n");
        } else {
            Utils.appendIndent(sb, indent)
                .append("RETURN.set(RESULT.MEMORY);\n");
        }
        Utils.appendIndent(sb, indent)
            .append("return return_;\n");
    }

    @Override
    public void convertFromUpcallReturnGraal(StringBuilder sb, int indent, VarOpts opts) {
        Utils.appendIndent(sb, indent)
            .append("if (RESULT == null) return WordFactory.pointer(0);\n");
        Utils.appendIndent(sb, indent)
            .append("var RETURN = new PNIBuf(return_);\n");
        if (elementType instanceof ByteTypeInfo) {
            Utils.appendIndent(sb, indent)
                .append("RETURN.set(RESULT);\n");
        } else {
            Utils.appendIndent(sb, indent)
                .append("RETURN.set(RESULT.MEMORY);\n");
        }
        Utils.appendIndent(sb, indent)
            .append("return WordFactory.pointer(return_.address());\n");
    }

    @Override
    public void javaToString(StringBuilder sb, int indent, String callGetter, VarOpts opts) {
        Utils.appendIndent(sb, indent)
            .append("if (CORRUPTED_MEMORY) SB.append(\"<?>\");\n");
        if (elementType instanceof ByteTypeInfo) {
            Utils.appendIndent(sb, indent)
                .append("else SB.append(PanamaUtils.memorySegmentToString(").append(callGetter).append("));\n");
        } else {
            Utils.appendIndent(sb, indent)
                .append("else PanamaUtils.nativeObjectToString(").append(callGetter)
                .append(", SB, INDENT + 4, VISITED, CORRUPTED_MEMORY);\n");
        }
    }
}
