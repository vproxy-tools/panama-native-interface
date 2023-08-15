package io.vproxy.pni.exec.ast;

import io.vproxy.pni.exec.internal.Utils;
import io.vproxy.pni.exec.type.ArrayTypeInfo;
import io.vproxy.pni.exec.type.ClassTypeInfo;
import io.vproxy.pni.exec.type.TypeInfo;

import java.util.ArrayList;
import java.util.List;

public class AstTypeDesc {
    public String desc;
    public final List<AstTypeDesc> genericTypes = new ArrayList<>();

    public TypeInfo typeRef = null; // nullable

    public AstTypeDesc(String desc) {
        this.desc = desc;
    }

    public AstTypeDesc(char desc) {
        this.desc = Character.toString(desc);
    }

    public AstTypeDesc(String desc, List<AstTypeDesc> genericTypes) {
        this.desc = desc;
        this.genericTypes.addAll(genericTypes);
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();

        if (typeRef instanceof ClassTypeInfo) {
            sb.append(((ClassTypeInfo) typeRef).getClazz().fullName());
        } else if (typeRef instanceof ArrayTypeInfo && ((ArrayTypeInfo) typeRef).getElementType() instanceof ClassTypeInfo) {
            sb.append(((ClassTypeInfo) ((ArrayTypeInfo) typeRef).getElementType()).getClazz().fullName());
            sb.append("[]");
        } else {
            sb.append(Utils.convertDescToJavaName(desc));
        }
        if (genericTypes.size() == 0) {
            return sb.toString();
        }

        int arrayDimensions = 0;
        char[] tmp = new char[2];
        sb.getChars(sb.length() - 2, sb.length(), tmp, 0);
        while (tmp[0] == '[' && tmp[1] == ']') {
            ++arrayDimensions;
            sb.delete(sb.length() - 2, sb.length());
            if (sb.length() < 2) {
                break;
            }
            sb.getChars(sb.length() - 2, sb.length(), tmp, 0);
        }

        sb.append("<");
        boolean isFirst = true;
        for (var t : genericTypes) {
            if (isFirst) {
                isFirst = false;
            } else {
                sb.append(", ");
            }
            sb.append(t.toString());
        }
        sb.append(">");

        sb.append("[]".repeat(Math.max(0, arrayDimensions)));

        return sb.toString();
    }
}
