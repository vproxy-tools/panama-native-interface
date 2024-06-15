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
    public void checkType(List<String> errors, String path, VarOpts opts, boolean upcall) {
        super.checkType(errors, path, opts, upcall);
        if (genericTypeRefs.size() != 1) {
            errors.add(path + ": PNIFunc should have exactly one generic param: " + genericTypes);
        } else if (genericTypeRefs.get(0) != null) {
            var ref = genericTypeRefs.get(0);
            if (!(ref instanceof ClassTypeInfo) && !(ref instanceof VoidRefTypeInfo) && !(ref instanceof PNIRefTypeInfo)) {
                errors.add(path + "#<0>: PNIFunc can only take Struct/Union or PNIRef or java.lang.Void as its argument");
            }
            ref.checkType(errors, path + "#<0>", VarOpts.fieldDefault(), upcall);
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
        return javaTypeForParam(VarOpts.of(opts.isUnsigned(), opts.getPointerInfo(), opts.getLen(), true));
    }

    @Override
    public String javaTypeForParam(VarOpts opts) {
        if (genericTypeRefs.get(0) instanceof VoidRefTypeInfo) {
            if (opts.isRaw()) {
                return "PNIFunc<Void>";
            } else {
                return "io.vproxy.pni.CallSite<Void>";
            }
        } else if (genericTypeRefs.get(0) instanceof PNIRefGenericTypeInfo) {
            var t = (PNIRefGenericTypeInfo) genericTypeRefs.get(0);
            if (opts.isRaw()) {
                return "PNIFunc<" + t.getGenericTypeString(0) + ">";
            } else {
                return "io.vproxy.pni.CallSite<" + t.getGenericTypeString(0) + ">";
            }
        } else if (genericTypeRefs.get(0) instanceof PNIRefTypeInfo) {
            // additional check!
            throw new UnsupportedOperationException("should not reach here");
        }
        if (opts.isRaw()) {
            return "PNIFunc<" + ((ClassTypeInfo) genericTypeRefs.get(0)).getClazz().fullName() + ">";
        } else {
            return "io.vproxy.pni.CallSite<" + ((ClassTypeInfo) genericTypeRefs.get(0)).getClazz().fullName() + ">";
        }
    }

    @Override
    public String javaTypeForReturn(VarOpts opts) {
        return javaTypeForField(opts);
    }

    @Override
    public void generateGetterSetter(StringBuilder sb, int indent, String fieldName, VarOpts opts) {
        Utils.varHandleField(sb, indent, fieldName);
        sb.append("\n");
        Utils.appendIndent(sb, indent)
            .append("public ").append(javaTypeForField(opts)).append(" ").append(Utils.getterName(fieldName)).append("() {\n");
        Utils.appendIndent(sb, indent + 4)
            .append("var SEG = ").append(fieldName).append("VH.getMemorySegment(MEMORY);\n");
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
    public String convertParamToInvokeExactArgument(String name, VarOpts opts) {
        if (opts.isRaw()) {
            return "(MemorySegment) (" + name + " == null ? MemorySegment.NULL : " + name + ".MEMORY)";
        } else {
            if (genericTypeRefs.get(0) instanceof VoidRefTypeInfo) {
                return "(MemorySegment) (" + name + " == null ? MemorySegment.NULL : PNIFunc.VoidFunc.of(" + name + ").MEMORY)";
            } else if (genericTypeRefs.get(0) instanceof PNIRefTypeInfo) {
                return "(MemorySegment) (" + name + " == null ? MemorySegment.NULL : PNIRef.Func.of(" + name + ").MEMORY)";
            }
            var fullName = ((ClassTypeInfo) genericTypeRefs.get(0)).getClazz().fullName();
            return "(MemorySegment) (" + name + " == null ? MemorySegment.NULL : " + fullName + ".Func.of(" + name + ").MEMORY)";
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

    @Override
    public String convertToUpcallArgument(String name, VarOpts opts) {
        if (genericTypeRefs.get(0) instanceof VoidRefTypeInfo) {
            if (opts.isRaw()) {
                return "(" + name + ".address() == 0 ? null : PNIFunc.VoidFunc.of(" + name + "))";
            } else {
                return "(" + name + ".address() == 0 ? null : PNIFunc.VoidFunc.of(" + name + ").getCallSite())";
            }
        } else if (genericTypeRefs.get(0) instanceof PNIRefTypeInfo) {
            if (opts.isRaw()) {
                return "(" + name + ".address() == 0 ? null : PNIRef.Func.of(" + name + "))";
            } else {
                return "(" + name + ".address() == 0 ? null : (io.vproxy.pni.CallSite) PNIRef.Func.of(" + name + ").getCallSite())";
            }
        }
        if (opts.isRaw()) {
            return "(" + name + ".address() == 0 ? null : " +
                   genericTypeRefs.get(0).javaTypeForReturn(opts) + ".Func.of(" + name + "))";
        } else {
            return "(" + name + ".address() == 0 ? null : " +
                   ((ClassTypeInfo) genericTypeRefs.get(0)).getClazz().fullName() + ".Func.of(" + name + ").getCallSite())";
        }
    }
}
