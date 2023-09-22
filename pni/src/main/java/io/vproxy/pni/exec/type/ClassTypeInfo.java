package io.vproxy.pni.exec.type;

import io.vproxy.pni.exec.ast.AstClass;
import io.vproxy.pni.exec.internal.AllocationForReturnedValue;
import io.vproxy.pni.exec.internal.Utils;
import io.vproxy.pni.exec.internal.VarOpts;

import java.util.List;

public class ClassTypeInfo extends TypeInfo {
    private final AstClass cls;
    private final String name;
    private final String desc;

    public ClassTypeInfo(AstClass cls) {
        this.cls = cls;
        name = cls.name.replace('/', '.');
        desc = "L" + cls.name + ";";
    }

    public AstClass getClazz() {
        return cls;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String internalName() {
        return cls.name;
    }

    @Override
    public String desc() {
        return desc;
    }

    @Override
    public void checkType(List<String> errors, String path, VarOpts opts, boolean upcall) {
        super.checkType(errors, path, opts, upcall);
        if (getClazz().isInterface) {
            errors.add(path + ": unable to use interface type: " + name);
        }
        if (getClazz().isPointerOnly()) {
            if (!opts.isPointer()) {
                errors.add(path + ": " + name + " is annotated with @PointerOnly, but used as non-pointer");
            }
        }
    }

    @Override
    public boolean canMarkWithPointer() {
        return true;
    }

    @Override
    public String nativeEnvType(VarOpts opts) {
        return cls.nativeName();
    }

    @Override
    public String nativeType(String fieldName, VarOpts opts) {
        var ret = cls.nativeTypeName();
        if (opts.isPointer()) {
            ret += " *";
        }
        if (fieldName != null) {
            ret += " " + fieldName;
        }
        return ret;
    }

    @Override
    public long nativeMemorySize(VarOpts opts) {
        if (opts.isPointer()) {
            return 8;
        }
        return cls.getNativeMemorySize();
    }

    @Override
    public long nativeMemoryAlign(VarOpts opts) {
        if (opts.isPointer()) {
            return 8;
        }
        return cls.largestAlignmentBytes();
    }

    @Override
    public long rawNativeMemoryAlign(VarOpts opts) {
        if (opts.isPointer()) {
            return 8;
        }
        return cls.largestRawAlignmentBytes();
    }

    @Override
    public String memoryLayoutForField(VarOpts opts) {
        if (opts.isPointer()) {
            return "ValueLayout.ADDRESS_UNALIGNED";
        } else {
            return getClazz().fullName() + ".LAYOUT";
        }
    }

    @Override
    public String javaTypeForField(VarOpts opts) {
        return cls.fullName();
    }

    @Override
    public String javaTypeForUpcallParam(VarOpts opts) {
        return "MemorySegment";
    }

    @Override
    public void generateGetterSetter(StringBuilder sb, int indent, String fieldName, VarOpts opts) {
        if (opts.isPointer()) {
            Utils.varHandleField(sb, indent, fieldName);
            sb.append("\n");
            Utils.appendIndent(sb, indent)
                .append("public ").append(cls.fullName()).append(" ").append(Utils.getterName(fieldName)).append("() {\n");
            Utils.appendIndent(sb, indent + 4)
                .append("var SEG = (MemorySegment) ").append(fieldName).append("VH.get(MEMORY);\n");
            Utils.appendIndent(sb, indent + 4)
                .append("if (SEG.address() == 0) return null;\n");
            Utils.appendIndent(sb, indent + 4)
                .append("return new ").append(cls.fullName()).append("(SEG);\n");
            Utils.appendIndent(sb, indent).append("}\n");
            sb.append("\n");
            Utils.appendIndent(sb, indent)
                .append("public void ").append(Utils.setterName(fieldName)).append("(").append(cls.fullName()).append(" ").append(fieldName).append(") {\n");
            Utils.appendIndent(sb, indent + 4)
                .append("if (").append(fieldName).append(" == null) {\n");
            Utils.appendIndent(sb, indent + 8)
                .append(fieldName).append("VH.set(MEMORY, MemorySegment.NULL);\n");
            Utils.appendIndent(sb, indent + 4).append("} else {\n");
            Utils.appendIndent(sb, indent + 8)
                .append(fieldName).append("VH.set(MEMORY, ").append(fieldName).append(".MEMORY);\n");
            Utils.appendIndent(sb, indent + 4).append("}\n");
            Utils.appendIndent(sb, indent).append("}\n");
        } else {
            Utils.appendIndent(sb, indent)
                .append("private final ").append(cls.fullName()).append(" ").append(fieldName).append(";\n");
            sb.append("\n");
            Utils.appendIndent(sb, indent)
                .append("public ").append(cls.fullName()).append(" ").append(Utils.getterName(fieldName)).append("() {\n");
            Utils.appendIndent(sb, indent + 4)
                .append("return this.").append(fieldName).append(";\n");
            Utils.appendIndent(sb, indent).append("}\n");
        }
    }

    @Override
    public void generateConstructor(StringBuilder sb, int indent, String fieldName, VarOpts opts) {
        if (opts.isPointer()) {
            Utils.appendIndent(sb, indent).append("OFFSET += 8;\n");
            return;
        }
        Utils.appendIndent(sb, indent)
            .append("this.").append(fieldName).append(" = new ")
            .append(cls.fullName()).append("(MEMORY.asSlice(OFFSET, ").append(cls.fullName()).append(".LAYOUT.byteSize()));\n");
        Utils.appendIndent(sb, indent).append("OFFSET += ").append(cls.fullName()).append(".LAYOUT.byteSize();\n");
    }

    @Override
    public String methodHandleType(VarOpts opts) {
        return cls.fullName() + ".LAYOUT.getClass()";
    }

    @Override
    public String methodHandleTypeForUpcall(VarOpts opts) {
        return "MemorySegment.class";
    }

    @Override
    public String convertParamToInvokeExactArgument(String name, VarOpts opts) {
        return "(MemorySegment) (" + name + " == null ? MemorySegment.NULL : " + name + ".MEMORY)";
    }

    @Override
    public AllocationForReturnedValue allocationInfoForReturnValue(VarOpts opts) {
        return AllocationForReturnedValue.ofExtraAllocator(cls.fullName() + ".LAYOUT.byteSize()");
    }

    @Override
    public AllocationForReturnedValue allocationInfoForUpcallInterfaceReturnValue(VarOpts opts) {
        return allocationInfoForReturnValue(opts);
    }

    @Override
    public void convertInvokeExactReturnValueToJava(StringBuilder sb, int indent, VarOpts opts) {
        if (opts.isCritical()) {
            Utils.appendIndent(sb, indent)
                .append("if (RESULT.address() == 0) return null;\n");
        } else {
            Utils.appendIndent(sb, indent)
                .append("var RESULT = ENV.returnPointer();\n");
        }
        Utils.appendIndent(sb, indent)
            .append("return RESULT == null ? null : new ").append(getClazz().fullName()).append("(RESULT);\n");
    }

    @Override
    public String convertExtraToUpcallArgument(String name, VarOpts opts) {
        return "new " + cls.fullName() + "(" + name + ")";
    }

    @Override
    public String convertToUpcallArgument(String name, VarOpts opts) {
        return "(" + name + ".address() == 0 ? null : new " + cls.fullName() + "(" + name + "))";
    }
}
