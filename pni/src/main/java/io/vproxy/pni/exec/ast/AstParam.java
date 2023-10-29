package io.vproxy.pni.exec.ast;

import io.vproxy.pni.exec.internal.PointerInfo;
import io.vproxy.pni.exec.internal.ParamOpts;
import io.vproxy.pni.exec.internal.Utils;
import io.vproxy.pni.exec.internal.VarOpts;
import io.vproxy.pni.exec.type.*;
import org.objectweb.asm.tree.AnnotationNode;

import java.util.ArrayList;
import java.util.List;

public class AstParam {
    public final List<AstAnno> annos = new ArrayList<>();
    public String name;
    public AstTypeDesc type;

    public TypeInfo typeRef;
    public final List<TypeInfo> genericTypeRefs = new ArrayList<>();

    public AstParam(AstTypeDesc type, String name, List<AnnotationNode> visibleParameterAnnotations) {
        Utils.readAnnotations(annos, visibleParameterAnnotations);
        this.name = name;
        this.type = type;
    }

    public void ref(TypePool pool) {
        for (var a : annos) {
            a.ref(pool);
        }
        typeRef = pool.find(type);
        for (var t : type.genericTypes) {
            genericTypeRefs.add(pool.find(t));
        }
    }

    public void validate(String path, List<String> errors, boolean upcall) {
        path = path + "#param(" + name + ")";
        if (typeRef == null) {
            errors.add(path + ": unable to find typeRef: " + type);
        } else {
            typeRef.checkType(errors, path, varOpts(), upcall);
        }
        for (var a : annos) {
            a.validate(path, errors);
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
        return annos.stream().anyMatch(a -> a.typeRef instanceof AnnoUnsignedTypeInfo);
    }

    public PointerInfo isPointer() {
        var has = annos.stream().anyMatch(a -> a.typeRef instanceof AnnoPointerTypeInfo);
        return PointerInfo.ofMethod(has);
    }

    public boolean isRaw() {
        return annos.stream().anyMatch(a -> a.typeRef instanceof AnnoRawTypeInfo);
    }

    public String getNativeTypeAnno() {
        return Utils.getNativeType(annos);
    }

    public VarOpts varOpts() {
        return VarOpts.of(isUnsigned(), isPointer(), -1, isRaw());
    }

    public ParamOpts paramOpts() {
        return ParamOpts.of(typeRef.allocationInfoForParam(varOpts()).requireJavaImplicitAllocator());
    }
}
