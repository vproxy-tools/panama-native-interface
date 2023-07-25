package io.vproxy.pni.exec.ast;

import io.vproxy.pni.annotation.Pointer;
import io.vproxy.pni.annotation.Unsigned;
import io.vproxy.pni.exec.internal.PointerInfo;
import io.vproxy.pni.exec.internal.Utils;
import io.vproxy.pni.exec.internal.VarOpts;
import io.vproxy.pni.exec.type.*;
import org.objectweb.asm.tree.FieldNode;

import java.util.ArrayList;
import java.util.List;

public class AstField {
    public final List<AstAnno> annos = new ArrayList<>();
    public String name;
    public String type;

    public TypeInfo typeRef;
    public long padding = 0;

    public AstField(FieldNode f) {
        Utils.readAnnotations(annos, f.visibleAnnotations);
        this.name = f.name;
        this.type = f.desc;
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
            if (typeRef instanceof ClassTypeInfo classTypeInfo) {
                if (classTypeInfo.getClazz().isInterface) {
                    errors.add(path + ": unable to use interface type: " + type);
                }
            } else if (typeRef instanceof CallSiteTypeInfo) {
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
        var unsignedOpt = annos.stream().filter(a -> a.typeRef != null && a.typeRef.name().equals(Unsigned.class.getName())).findFirst();
        if (unsignedOpt.isPresent()) {
            if (typeRef instanceof ArrayTypeInfo arrayTypeInfo) {
                var elementTypeRef = arrayTypeInfo.getElementType();
                if (!(elementTypeRef instanceof IntTypeInfo) && !(elementTypeRef instanceof LongTypeInfo) && !(elementTypeRef instanceof ShortTypeInfo) && !(elementTypeRef instanceof ByteTypeInfo)) {
                    errors.add(path + ": non-integer type " + elementTypeRef + " in array type " + typeRef + " cannot be annotated with @Unsigned");
                }
            } else {
                if (!(typeRef instanceof IntTypeInfo) && !(typeRef instanceof LongTypeInfo) && !(typeRef instanceof ShortTypeInfo) && !(typeRef instanceof ByteTypeInfo)) {
                    errors.add(path + ": non-integer type " + typeRef + " cannot be annotated with @Unsigned");
                }
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
        var has = annos.stream().anyMatch(a -> a.typeRef != null && a.typeRef.name().equals(Pointer.class.getName()));
        return PointerInfo.ofField(has);
    }

    public boolean isUnsigned() {
        return annos.stream().anyMatch(a -> a.typeRef != null && a.typeRef.name().equals(Unsigned.class.getName()));
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

    public boolean isStruct() {
        if (pointerInfo().isPointer()) return false;
        if (typeRef instanceof ClassTypeInfo classTypeInfo) {
            return classTypeInfo.getClazz().isStruct();
        }
        return false;
    }

    public void generateC(StringBuilder sb, int indent) {
        Utils.appendIndent(sb, indent);
        if (typeRef instanceof ClassTypeInfo clsTypeInfo) {
            var cls = clsTypeInfo.getClazz();
            if (cls.isUnionEmbed()) {
                cls.generateC(sb, indent, false);
                return;
            }
        }
        sb.append(typeRef.nativeType(nativeName(), varOpts())).append(";");
        if (padding > 0) {
            sb.append(" int8_t __padding_after_").append(nativeName()).append("[").append(padding).append("];\n");
        } else {
            sb.append("\n");
        }
    }

    public void generateJavaLayout(StringBuilder sb, int indent) {
        Utils.appendIndent(sb, indent);
        sb.append(typeRef.memoryLayout(varOpts()))
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
