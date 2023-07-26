package io.vproxy.pni.exec.ast;

import io.vproxy.pni.exec.internal.Utils;
import io.vproxy.pni.exec.type.TypeInfo;
import io.vproxy.pni.exec.type.TypePool;
import org.objectweb.asm.tree.AnnotationNode;

import java.util.ArrayList;
import java.util.List;

public class AstAnno {
    public String type;
    public List<AstAnnoValue> values = new ArrayList<>();

    public TypeInfo typeRef;

    public AstAnno(AnnotationNode a) {
        type = a.desc;
        if (a.values != null) {
            for (int i = 0; i < a.values.size(); i += 2) {
                values.add(new AstAnnoValue((String) a.values.get(i), a.values.get(i + 1)));
            }
        }
    }

    public void ref(TypePool pool) {
        typeRef = pool.find(type);
    }

    public void validate(String path, List<String> errors) {
        path = path + "#anno(" + type + ")";
        if (typeRef == null) {
            errors.add(path + ": unable to find typeRef: " + type);
        }
    }

    public void toString(StringBuilder sb, int indent) {
        Utils.appendIndent(sb, indent);
        sb.append("@");
        if (typeRef != null) {
            sb.append(typeRef.name());
        } else {
            sb.append(type);
        }
        if (!values.isEmpty()) {
            sb.append("(");
            var isFirst = true;
            for (var v : values) {
                if (isFirst) {
                    isFirst = false;
                } else {
                    sb.append(", ");
                }
                v.toString(sb);
            }
            sb.append(")");
        }
    }
}
