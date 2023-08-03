package io.vproxy.pni.exec.ast;

import io.vproxy.pni.exec.internal.PointerInfo;
import io.vproxy.pni.exec.internal.ParamOpts;
import io.vproxy.pni.exec.internal.Utils;
import io.vproxy.pni.exec.internal.VarOpts;
import io.vproxy.pni.exec.type.*;
import org.objectweb.asm.tree.AnnotationNode;

import java.util.ArrayList;
import java.util.List;

import static io.vproxy.pni.exec.internal.Consts.*;

public class AstParam {
    public final List<AstAnno> annos = new ArrayList<>();
    public String name;
    public String type;
    public List<String> genericTypes = new ArrayList<>();

    public TypeInfo typeRef;
    public final List<TypeInfo> genericTypeRefs = new ArrayList<>();

    public AstParam(String type, List<String> genericTypes, String name, List<AnnotationNode> visibleParameterAnnotations) {
        Utils.readAnnotations(annos, visibleParameterAnnotations);
        this.name = name;
        this.type = type;
        this.genericTypes.addAll(genericTypes);
    }

    public void ref(TypePool pool) {
        for (var a : annos) {
            a.ref(pool);
        }
        typeRef = pool.find(type);
        for (var t : genericTypes) {
            genericTypeRefs.add(pool.find(t));
        }
    }

    public void validate(String path, List<String> errors) {
        path = path + "#param(" + name + ")";
        if (typeRef == null) {
            errors.add(path + ": unable to find typeRef: " + type);
        } else {
            typeRef.checkType(errors, path, varOpts());
            if (typeRef instanceof ClassTypeInfo) {
                var classTypeInfo = (ClassTypeInfo) typeRef;
                if (classTypeInfo.getClazz().isInterface) {
                    errors.add(path + ": unable to use interface type: " + type);
                }
            } else if (typeRef instanceof CallSiteTypeInfo) {
                if (genericTypeRefs.size() != 1) {
                    errors.add(path + ": CallSite should have exactly one generic param: " + genericTypes);
                } else {
                    var ref = genericTypeRefs.get(0);
                    if (ref instanceof ClassTypeInfo) {
                        var classTypeInfo = (ClassTypeInfo) ref;
                        if (classTypeInfo.getClazz().isInterface) {
                            errors.add(path + "#<0>: unable to use interface type: " + type);
                        }
                    }
                    if (!(ref instanceof ClassTypeInfo) && !(ref instanceof VoidRefTypeInfo)) {
                        errors.add(path + "#<0>: CallSite can only take Struct/Union or java.lang.Void as its argument");
                    }
                }
            }
        }
        var name = Utils.getName(annos);
        if (name != null) {
            if (!Utils.isValidName(name, true)) {
                errors.add(path + ": invalid @Name(" + name + ")");
            }
        }
        if (genericTypes.size() != genericTypeRefs.size()) {
            errors.add(path + ": genericTypeRefs mismatch genericTypes");
        } else {
            for (int i = 0; i < genericTypeRefs.size(); i++) {
                var t = genericTypes.get(i);
                var ref = genericTypeRefs.get(i);
                if (ref == null) {
                    errors.add(path + "#<" + t + ">: unable to find genericTypeRef");
                }
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
        return annos.stream().anyMatch(a -> a.typeRef != null && a.typeRef.name().equals(UnsignedClassName));
    }

    public PointerInfo isPointer() {
        var has = annos.stream().anyMatch(a -> a.typeRef != null && a.typeRef.name().equals(PointerClassName));
        return PointerInfo.ofMethod(has);
    }

    public boolean isRaw() {
        return annos.stream().anyMatch(a -> a.typeRef != null && a.typeRef.name().equals(RawClassName));
    }

    public VarOpts varOpts() {
        return VarOpts.of(isUnsigned(), isPointer(), -1, isRaw(), genericTypeRefs);
    }

    public ParamOpts paramOpts() {
        return ParamOpts.of(typeRef.paramDependOnPooledAllocator(varOpts()));
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
