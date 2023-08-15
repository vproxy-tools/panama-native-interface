package io.vproxy.pni.exec.type;

import io.vproxy.pni.exec.ast.AstTypeDesc;
import io.vproxy.pni.exec.internal.Utils;
import io.vproxy.pni.exec.internal.VarOpts;

import java.util.List;

public class PNIRefGenericTypeInfo extends PNIRefTypeInfo {
    private final List<AstTypeDesc> genericTypes;

    public PNIRefGenericTypeInfo(List<AstTypeDesc> genericTypes) {
        super();
        this.genericTypes = genericTypes;
    }

    @Override
    public void checkType(List<String> errors, String path, VarOpts opts) {
        super.checkType(errors, path, opts);
        if (genericTypes.size() != 1) {
            errors.add(path + ": PNIRef should have exactly one generic param: " + genericTypes);
        }
    }

    public String getGenericTypeString(int index) {
        return genericTypes.get(index).toString();
    }

    @Override
    public String javaTypeForField(VarOpts opts) {
        return "PNIRef<" + getGenericTypeString(0) + ">";
    }

    @Override
    public String javaTypeForParam(VarOpts opts) {
        if (opts.isRaw()) {
            return javaTypeForField(opts);
        } else {
            var desc = genericTypes.get(0).desc;
            if (desc.equals("*")) {
                return "Object";
            } else if (desc.startsWith("+")) {
                return Utils.convertDescToJavaName(desc.substring(1));
            } else if (desc.startsWith("-")) {
                return "Object";
            } else {
                return getGenericTypeString(0);
            }
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
            .append("var SEG = (MemorySegment) ").append(fieldName).append("VH.get(MEMORY);\n");
        Utils.appendIndent(sb, indent + 4)
            .append("if (SEG.address() == 0) return null;\n");
        Utils.appendIndent(sb, indent + 4)
            .append("return PNIRef.of(SEG);\n");
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
}
