package io.vproxy.pni.exec.ast;

import io.vproxy.pni.exec.internal.PointerInfo;
import io.vproxy.pni.exec.internal.Utils;
import io.vproxy.pni.exec.internal.VarOpts;
import io.vproxy.pni.exec.type.*;
import org.objectweb.asm.tree.FieldNode;

import java.util.ArrayList;
import java.util.List;

import static io.vproxy.pni.exec.internal.Consts.*;

public class AstField {
    public final List<AstAnno> annos = new ArrayList<>();
    public String name;
    public AstTypeDesc type;

    public TypeInfo typeRef;
    public long padding = 0;

    public AstField(FieldNode f) {
        Utils.readAnnotations(annos, f.visibleAnnotations);
        this.name = f.name;
        this.type = Utils.extractDesc(f.signature == null ? f.desc : f.signature).get(0);
    }

    public AstField() {
        // for unit testing only
    }

    public void ref(TypePool pool) {
        typeRef = pool.find(type);
        for (var a : annos) {
            a.ref(pool);
        }
    }

    public void validate(String path, List<String> errors) {
        path = path + "#field(" + name + ")";
        if (typeRef == null) {
            errors.add(path + ": unable to find typeRef: " + type);
        } else {
            typeRef.checkType(errors, path, varOpts());
            if (typeRef instanceof CallSiteTypeInfo) {
                errors.add(path + ": cannot use CallSite as field");
            }
        }
        for (var a : annos) {
            a.validate(path, errors);
        }
        var name = Utils.getName(annos);
        if (name != null) {
            if (!Utils.isValidName(name, false)) {
                errors.add(path + ": invalid @Name(" + name + ")");
            }
        }
    }

    public String nativeName() {
        var name = Utils.getName(annos);
        if (name == null)
            return this.name;
        return name;
    }

    public PointerInfo pointerInfo() {
        var has = annos.stream().anyMatch(a -> a.typeRef != null && a.typeRef.name().equals(PointerClassName));
        return PointerInfo.ofField(has);
    }

    public boolean isUnsigned() {
        return annos.stream().anyMatch(a -> a.typeRef != null && a.typeRef.name().equals(UnsignedClassName));
    }

    public long getLen() {
        return Utils.getLen(annos);
    }

    public VarOpts varOpts() {
        return VarOpts.of(isUnsigned(), pointerInfo(), getLen());
    }

    public long getNativeMemorySize() {
        return typeRef.nativeMemorySize(varOpts());
    }

    public long getAlign() {
        return Utils.getAlign(annos);
    }

    public boolean isAlignPacked() {
        return Utils.getAlignPacked(annos);
    }

    public long getAlignmentBytes(boolean packed) {
        if (isAlignPacked()) {
            return 0;
        }
        var annoAlign = getAlign();
        if (annoAlign <= 1 && packed) {
            return 0;
        }
        if (packed) {
            return annoAlign;
        }
        long n = typeRef.nativeMemoryAlign(varOpts());
        if (n < annoAlign) {
            n = annoAlign;
        }
        return n;
    }

    public void generateC(StringBuilder sb, int indent) {
        Utils.appendIndent(sb, indent);
        if (typeRef instanceof ClassTypeInfo) {
            var clsTypeInfo = (ClassTypeInfo) typeRef;
            var cls = clsTypeInfo.getClazz();
            if (cls.isUnionEmbed()) {
                cls.generateC(sb, indent, false);
                return;
            }
        }
        sb.append(typeRef.nativeType(nativeName(), varOpts())).append(";");
        if (padding > 0) {
            long p = padding;
            sb.append(" /* padding */");
            while (p > 0) {
                sb.append(" uint64_t :");
                if (p >= 8) {
                    sb.append("64");
                    p -= 8;
                } else {
                    sb.append(p * 8);
                    p = 0;
                }
                sb.append(";");
            }
        }
        sb.append("\n");
    }

    public void generateJavaLayout(StringBuilder sb, int indent) {
        Utils.appendIndent(sb, indent);
        sb.append(typeRef.memoryLayoutForField(varOpts()))
            .append(".withName(\"").append(name).append("\")");
        if (padding > 0) {
            sb.append(",\n");
            Utils.appendIndent(sb, indent)
                .append("MemoryLayout.sequenceLayout(").append(padding).append("L, ValueLayout.JAVA_BYTE) /* padding */");
        }
    }

    public void generateJavaGetterSetter(StringBuilder sb, int indent) {
        typeRef.generateGetterSetter(sb, indent, name, varOpts());
    }

    public void generateJavaConstructor(StringBuilder sb, int indent) {
        typeRef.generateConstructor(sb, indent, name, varOpts());
        if (padding > 0) {
            Utils.appendIndent(sb, indent).append("OFFSET += ").append(padding).append("; /* padding */\n");
        }
    }

    public void toString(StringBuilder sb, int indent) {
        Utils.appendIndent(sb, indent);
        for (var a : annos) {
            a.toString(sb, 0);
            sb.append(" ");
        }
        if (typeRef != null) {
            sb.append(typeRef.name());
        } else {
            sb.append(type);
        }
        sb.append(" ").append(name).append(";");
    }
}
