package io.vproxy.pni.exec.type;

import io.vproxy.pni.exec.ast.AstTypeDesc;
import io.vproxy.pni.exec.internal.Utils;
import io.vproxy.pni.exec.internal.VarOpts;

import java.util.List;

public class PNIFuncGenericTypeInfo extends PNIFuncTypeInfo {
    private final List<AstTypeDesc> genericTypes;
    private final List<TypeInfo> genericTypeRefs;

    public PNIFuncGenericTypeInfo(List<AstTypeDesc> genericTypes, List<TypeInfo> genericTypeRefs) {
        super();
        this.genericTypes = genericTypes;
        this.genericTypeRefs = genericTypeRefs;
    }

    @Override
    public void checkType(List<String> errors, String path, VarOpts opts) {
        super.checkType(errors, path, opts);
        if (genericTypeRefs.size() != 1) {
            errors.add(path + ": PNIFunc should have exactly one generic param: " + genericTypes);
        } else if (genericTypeRefs.get(0) != null) {
            var ref = genericTypeRefs.get(0);
            if (!(ref instanceof ClassTypeInfo) && !(ref instanceof VoidRefTypeInfo) && !(ref instanceof PNIRefTypeInfo)) {
                errors.add(path + "#<0>: PNIFunc can only take Struct/Union or PNIRef or java.lang.Void as its argument");
            }
            ref.checkType(errors, path + "#<0>", VarOpts.fieldDefault());
        }
        for (int i = 0; i < genericTypeRefs.size(); i++) {
            var r = genericTypeRefs.get(i);
            if (r == null) {
                errors.add(path + "#<" + i + ">: cannot find generic param: " + genericTypes.get(i));
            }
        }
    }

    @Override
    public String javaTypeForField(VarOpts opts) {
        if (genericTypeRefs.get(0) instanceof VoidRefTypeInfo) {
            return "PNIFunc<Void>";
        } else if (genericTypeRefs.get(0) instanceof PNIRefGenericTypeInfo) {
            var t = (PNIRefGenericTypeInfo) genericTypeRefs.get(0);
            return "PNIFunc<" + t.getGenericTypeString(0) + ">";
        } else if (genericTypeRefs.get(0) instanceof PNIRefTypeInfo) {
            // additional check!
            throw new UnsupportedOperationException("should not reach here");
        }
        return "PNIFunc<" + ((ClassTypeInfo) genericTypeRefs.get(0)).getClazz().fullName() + ">";
    }

    @Override
    public void generateGetterSetter(StringBuilder sb, int indent, String fieldName, VarOpts opts) {
        Utils.varHandleField(sb, indent, fieldName);
        sb.append("\n");
        Utils.appendIndent(sb, indent)
            .append("public ").append(javaTypeForField(opts)).append(" ").append(Utils.getterName(fieldName)).append("() {\n");
        Utils.appendIndent(sb, indent + 4)
            .append("var SEG = (MemorySegment) ").append(fieldName).append("VH.get(MEMORY);\n");
        Utils.appendIndent(sb, indent + 4)
            .append("if (SEG.address() == 0) return null;\n");
        if (genericTypeRefs.get(0) instanceof VoidRefTypeInfo) {
            Utils.appendIndent(sb, indent + 4)
                .append("return PNIFunc.VoidFunc.of(SEG);\n");
        } else if (genericTypeRefs.get(0) instanceof PNIRefTypeInfo) {
            Utils.appendIndent(sb, indent + 4)
                .append("return PNIRef.Func.of(SEG);\n");
        } else {
            Utils.appendIndent(sb, indent + 4)
                .append("return ").append(genericTypeRefs.get(0).javaTypeForField(opts)).append(".Func.of(SEG);\n");
        }
        Utils.appendIndent(sb, indent).append("}\n");
        sb.append("\n");
        Utils.appendIndent(sb, indent)
            .append("public void ").append(Utils.setterName(fieldName)).append("(").append(javaTypeForField(opts)).append(" ").append(fieldName).append(") {\n");
        Utils.appendIndent(sb, indent + 4)
            .append("if (").append(fieldName).append(" == null) {\n");
        Utils.appendIndent(sb, indent + 8)
            .append(fieldName).append("VH.set(MEMORY, MemorySegment.NULL);\n");
        Utils.appendIndent(sb, indent + 4).append("} else {\n");
        Utils.appendIndent(sb, indent + 8)
            .append(fieldName).append("VH.set(MEMORY, ").append(fieldName).append(".MEMORY);\n");
        Utils.appendIndent(sb, indent + 4).append("}\n");
        Utils.appendIndent(sb, indent).append("}\n");
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
        if (genericTypeRefs.get(0) instanceof VoidRefTypeInfo) {
            Utils.appendIndent(sb, indent).append("return PNIFunc.VoidFunc.of(RESULT);\n");
        } else if (genericTypeRefs.get(0) instanceof PNIRefTypeInfo) {
            Utils.appendIndent(sb, indent).append("return PNIRef.Func.of(RESULT);\n");
        } else {
            Utils.appendIndent(sb, indent)
                .append("return ")
                .append(genericTypeRefs.get(0).javaTypeForReturn(opts)).append(".Func.of(RESULT);\n");
        }
    }
}
