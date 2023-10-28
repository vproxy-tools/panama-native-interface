package io.vproxy.pni.exec.type;

import io.vproxy.pni.exec.ast.BitFieldInfo;
import io.vproxy.pni.exec.internal.Utils;
import io.vproxy.pni.exec.internal.VarOpts;

public abstract class PrimitiveIntegerTypeInfo extends PrimitiveTypeInfo {
    @Override
    public void generateBitFieldGetterSetter(StringBuilder sb, int indent, String fieldName, BitFieldInfo bitfield, VarOpts varOpts) {
        if (bitfield.bool) {
            Utils.appendIndent(sb, indent)
                .append("public boolean ").append(Utils.getterName(bitfield.name, true)).append("() {\n");
            Utils.appendIndent(sb, indent + 4)
                .append("var N = ").append(Utils.getterName(fieldName)).append("();\n");
            if (bitfield.bit <= 31) {
                Utils.appendIndent(sb, indent + 4)
                    .append("return ((N >> ").append(bitfield.offset).append(") & 0b1) == 1;\n");
            } else {
                Utils.appendIndent(sb, indent + 4)
                    .append("return ((N >> ").append(bitfield.offset).append(") & 0b1L) == 1L;\n");
            }
            Utils.appendIndent(sb, indent).append("}\n");
        } else {
            Utils.appendIndent(sb, indent)
                .append("public ").append(name()).append(" ").append(Utils.getterName(bitfield.name)).append("() {\n");
            Utils.appendIndent(sb, indent + 4)
                .append("var N = ").append(Utils.getterName(fieldName)).append("();\n");
            if (bitfield.bit <= 31) {
                Utils.appendIndent(sb, indent + 4)
                    .append("return (").append(name()).append(") ((N >> ").append(bitfield.offset).append(") & 0b").append("1".repeat(bitfield.bit)).append(");\n");
            } else {
                Utils.appendIndent(sb, indent + 4)
                    .append("return (").append(name()).append(") ((N >> ").append(bitfield.offset).append(") & 0b").append("1".repeat(bitfield.bit)).append("L);\n");
            }
            Utils.appendIndent(sb, indent).append("}\n");
        }
        sb.append("\n");
        if (bitfield.bool) {
            Utils.appendIndent(sb, indent)
                .append("public void ").append(Utils.setterName(bitfield.name)).append("(boolean ").append(bitfield.name).append(") {\n");
            Utils.appendIndent(sb, indent + 4)
                .append("var N = ").append(Utils.getterName(fieldName)).append("();\n");
            if (bitfield.bit + bitfield.offset <= 31) {
                Utils.appendIndent(sb, indent + 4)
                    .append(name()).append(" MASK = (").append(name()).append(") (0b1 << ").append(bitfield.offset).append(");\n");
                Utils.appendIndent(sb, indent + 4)
                    .append("var NN = (").append(name()).append(") (").append(bitfield.name).append(" ? 1 : 0);\n");
            } else {
                Utils.appendIndent(sb, indent + 4)
                    .append(name()).append(" MASK = (").append(name()).append(") (0b1L << ").append(bitfield.offset).append(");\n");
                Utils.appendIndent(sb, indent + 4)
                    .append("var NN = (").append(name()).append(") (").append(bitfield.name).append(" ? 1 : 0);\n");
            }
            Utils.appendIndent(sb, indent + 4)
                .append("NN = (").append(name()).append(") (NN << ").append(bitfield.offset).append(");\n");
            Utils.appendIndent(sb, indent + 4)
                .append("N = (").append(name()).append(") ((N & ~MASK) | (NN & MASK));\n");
            Utils.appendIndent(sb, indent + 4)
                .append(Utils.setterName(fieldName)).append("(N);\n");
            Utils.appendIndent(sb, indent).append("}\n");
        } else {
            Utils.appendIndent(sb, indent)
                .append("public void ").append(Utils.setterName(bitfield.name)).append("(").append(name()).append(" ").append(bitfield.name).append(") {\n");
            Utils.appendIndent(sb, indent + 4)
                .append("var N = ").append(Utils.getterName(fieldName)).append("();\n");
            if (bitfield.bit + bitfield.offset <= 31) {
                Utils.appendIndent(sb, indent + 4)
                    .append(name()).append(" MASK = (").append(name()).append(") (0b").append("1".repeat(bitfield.bit)).append(" << ").append(bitfield.offset).append(");\n");
                Utils.appendIndent(sb, indent + 4)
                    .append(bitfield.name).append(" = (").append(name()).append(") (").append(bitfield.name).append(" & 0b").append("1".repeat(bitfield.bit)).append(");\n");
            } else {
                Utils.appendIndent(sb, indent + 4)
                    .append(name()).append(" MASK = (").append(name()).append(") (0b").append("1".repeat(bitfield.bit)).append("L << ").append(bitfield.offset).append(");\n");
                Utils.appendIndent(sb, indent + 4)
                    .append(bitfield.name).append(" = (").append(name()).append(") (").append(bitfield.name).append(" & 0b").append("1".repeat(bitfield.bit)).append("L);\n");
            }
            Utils.appendIndent(sb, indent + 4)
                .append(bitfield.name).append(" = (").append(name()).append(") (").append(bitfield.name).append(" << ").append(bitfield.offset).append(");\n");
            Utils.appendIndent(sb, indent + 4)
                .append("N = (").append(name()).append(") ((N & ~MASK) | (").append(bitfield.name).append(" & MASK));\n");
            Utils.appendIndent(sb, indent + 4)
                .append(Utils.setterName(fieldName)).append("(N);\n");
            Utils.appendIndent(sb, indent).append("}\n");
        }
    }
}
