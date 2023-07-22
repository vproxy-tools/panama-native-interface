package io.vproxy.pni.exec.ast;

import io.vproxy.pni.annotation.Pointer;
import io.vproxy.pni.annotation.Raw;
import io.vproxy.pni.annotation.Unsigned;
import io.vproxy.pni.exec.internal.PointerInfo;
import io.vproxy.pni.exec.internal.ParamOpts;
import io.vproxy.pni.exec.internal.Utils;
import io.vproxy.pni.exec.internal.VarOpts;
import io.vproxy.pni.exec.type.ClassTypeInfo;
import io.vproxy.pni.exec.type.TypeInfo;
import io.vproxy.pni.exec.type.TypePool;
import org.objectweb.asm.tree.AnnotationNode;

import java.util.ArrayList;
import java.util.List;

public class AstParam {
    public final List<AstAnno> annos = new ArrayList<>();
    public String name;
    public String type;

    public TypeInfo typeRef;

    public AstParam(String type, String name, List<AnnotationNode> visibleParameterAnnotations) {
        Utils.readAnnotations(annos, visibleParameterAnnotations);
        this.name = name;
        this.type = type;
    }

    public void ref(TypePool pool) {
        for (var a : annos) {
            a.ref(pool);
        }
        typeRef = pool.find(type);
    }

    public void validate(String path, List<String> errors) {
        path = path + "#param(" + name + ")";
        if (typeRef == null) {
            errors.add(path + ": unable to find typeRef: " + type);
        } else {
            typeRef.checkType(errors, path, varOpts());
            if (typeRef instanceof ClassTypeInfo classTypeInfo) {
                if (classTypeInfo.getClazz().isInterface) {
                    errors.add(path + ": unable to use interface type: " + type);
                }
            }
        }
        var name = Utils.getName(annos);
        if (name != null) {
            if (!Utils.isValidName(name, true)) {
                errors.add(path + ": invalid @Name(" + name + ")");
            }
        }
    }

    public void toString(StringBuilder sb) {
        for (var a : annos) {
            a.toString(sb, 0);
            sb.append(" ");
        }
        if (typeRef != null) {
            sb.append(typeRef.name());
        } else {
            sb.append(type);
        }
        sb.append(" ").append(name);
    }

    public String nativeName() {
        var name = Utils.getName(annos);
        if (name == null)
            return this.name;
        return name;
    }

    public boolean isUnsigned() {
        return annos.stream().anyMatch(a -> a.typeRef != null && a.typeRef.name().equals(Unsigned.class.getName()));
    }

    public PointerInfo isPointer() {
        var has = annos.stream().anyMatch(a -> a.typeRef != null && a.typeRef.name().equals(Pointer.class.getName()));
        return PointerInfo.ofMethod(has);
    }

    public boolean isRaw() {
        return annos.stream().anyMatch(a -> a.typeRef != null && a.typeRef.name().equals(Raw.class.getName()));
    }

    public VarOpts varOpts() {
        return VarOpts.of(isUnsigned(), isPointer(), -1, isRaw());
    }

    public ParamOpts paramOpts() {
        return ParamOpts.of(typeRef.paramDependOnConfinedArena(varOpts()));
    }

    public void generateC(StringBuilder sb, int indent) {
        Utils.appendIndent(sb, indent);
        sb.append(typeRef.nativeParamType(nativeName(), varOpts()));
    }

    public void generateParam(StringBuilder sb, int indent) {
        Utils.appendIndent(sb, indent);
        sb.append(typeRef.javaType(varOpts())).append(" ").append(name);
    }

    public void generateMethodHandle(StringBuilder sb, int indent) {
        Utils.appendIndent(sb, indent);
        sb.append(typeRef.methodHandleType(varOpts()));
    }

    public void generateConvert(StringBuilder sb, int indent) {
        Utils.appendIndent(sb, indent);
        sb.append(typeRef.convertToNativeCallArgument(name, varOpts()));
    }
}
