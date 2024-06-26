package io.vproxy.pni.exec.ast;

import io.vproxy.pni.exec.CompilationFlag;
import io.vproxy.pni.exec.CompilerOptions;
import io.vproxy.pni.exec.WarnType;
import io.vproxy.pni.exec.internal.PNILogger;
import io.vproxy.pni.exec.internal.Utils;
import io.vproxy.pni.exec.internal.VarOpts;
import io.vproxy.pni.exec.type.*;
import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.MethodNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class AstMethod {
    private final CompilerOptions opts;
    public final List<AstAnno> annos = new ArrayList<>();
    public String name;
    public List<AstGenericDef> genericDefs = new ArrayList<>();
    public AstTypeDesc returnType;
    public List<AstParam> params = new ArrayList<>();
    public List<String> throwTypes = new ArrayList<>(); // internal name

    public TypeInfo returnTypeRef;
    public final List<TypeInfo> throwTypeRefs = new ArrayList<>();

    public AstMethod(MethodNode m, CompilerOptions opts) {
        this.opts = opts;
        Utils.readAnnotations(annos, m.visibleAnnotations);
        this.name = m.name;
        String returnType;
        String paramsPart;
        if (m.signature == null) {
            returnType = m.desc.substring(m.desc.indexOf(")") + 1);
            paramsPart = m.desc.substring(1, m.desc.indexOf(")"));
        } else {
            returnType = m.signature.substring(m.signature.indexOf(")") + 1);
            paramsPart = m.signature.substring(m.signature.indexOf("(") + 1, m.signature.indexOf(")"));
        }
        if (m.signature != null) {
            this.genericDefs.addAll(Utils.extractGenericDefinition(m.signature));
        }
        this.returnType = Utils.extractDesc(returnType).get(0);
        var paramTypes = Utils.extractDesc(paramsPart);

        for (int i = 0; i < paramTypes.size(); i++) {
            var t = paramTypes.get(i);
            String paramName = "arg" + i;
            if (m.parameters != null) {
                paramName = m.parameters.get(i).name;
            }
            List<AnnotationNode> annotationNodes = null;
            if (m.visibleParameterAnnotations != null) {
                annotationNodes = m.visibleParameterAnnotations[i];
            }
            params.add(new AstParam(t, paramName, annotationNodes));
        }
        throwTypes.addAll(m.exceptions);
    }

    public AstMethod(CompilerOptions opts) {
        // for unit testing only
        this.opts = opts;
    }

    public void ref(TypePool pool) {
        returnTypeRef = pool.find(returnType);
        for (var p : params) {
            p.ref(pool);
        }
        for (var t : throwTypes) {
            throwTypeRefs.add(pool.find(t));
        }
        for (var a : annos) {
            a.ref(pool);
        }
    }

    public void validate(String path, List<String> errors, boolean upcall) {
        path = path + "#method(" + name + ")";
        if (returnTypeRef == null) {
            errors.add(path + ": unable to find returnTypeRef: " + returnType);
        } else {
            returnTypeRef.checkType(errors, path, varOptsForReturn(upcall), upcall);
        }
        for (var p : params) {
            p.validate(path, errors, upcall);
        }
        if (isCriticalStyle() && !throwTypes.isEmpty()) {
            errors.add(path + ": cannot throw exceptions for Critical methods");
        }
        if (throwTypeRefs.size() != throwTypes.size()) {
            errors.add(path + ": throwTypeRefs mismatch throwTypes");
        }
        for (var i = 0; i < throwTypeRefs.size(); ++i) {
            var t = throwTypes.get(i);
            var tRef = throwTypeRefs.get(i);
            var path2 = path + "#throws(" + t + ")";
            if (tRef == null) {
                errors.add(path2 + ": unable to find throwTypeRef");
            }
        }
        if (upcall && !throwTypes.isEmpty()) {
            errors.add(path + ": upcall method cannot have throws list");
        }
        for (var a : annos) {
            a.validate(path, errors);
        }

        var names = new HashSet<String>();
        for (var p : params) {
            if (!names.add(p.nativeName())) {
                var err = path + ": two or more parameters have the same native name " + p.nativeName();
                if (!names.contains(err)) {
                    errors.add(err);
                }
            }
        }

        var name = Utils.getName(annos);
        if (name != null) {
            if (!Utils.isValidName(name, false)) {
                errors.add(path + ": invalid @Name(" + name + ")");
            }
        }

        if (isAllowHeapAccess()) {
            var version = Runtime.version().version().getFirst();
            if (version == 21) {
                PNILogger.warn(errors, path, annos, opts, WarnType.JDK21_ALLOW_HEAP_ACCESS,
                    "@LinkerOption.Critical(allowHeapAccess=true) is set but the running JDK version is 21");
            }
        }
    }

    public void toString(StringBuilder sb, int indent) {
        for (var a : annos) {
            a.toString(sb, indent);
            sb.append("\n");
        }
        Utils.appendIndent(sb, indent);
        if (returnTypeRef != null) {
            sb.append(returnTypeRef.name());
        } else {
            sb.append(returnType);
        }
        sb.append(" ").append(name).append("(");
        var isFirst = true;
        for (var p : params) {
            if (isFirst) {
                isFirst = false;
            } else {
                sb.append(", ");
            }
            p.toString(sb);
        }
        sb.append(")");
        if (!throwTypes.isEmpty()) {
            sb.append(" throws ");
            isFirst = true;
            for (int i = 0; i < throwTypes.size(); i++) {
                var t = throwTypes.get(i);
                var tRef = throwTypeRefs.size() > i ? throwTypeRefs.get(i) : null;
                if (isFirst) {
                    isFirst = false;
                } else {
                    sb.append(", ");
                }
                if (tRef == null) {
                    sb.append(t);
                } else {
                    sb.append(tRef.name());
                }
            }
        }
        sb.append(";");
    }

    public String nativeName(String classUnderlinedName) {
        return nativeName(classUnderlinedName, false);
    }

    public String nativeName(String classUnderlinedName, boolean upcall) {
        var name = Utils.getName(annos);
        if (name != null)
            return name;
        return ((isCriticalStyle() || upcall) ? "JavaCritical_" : "Java_") + classUnderlinedName + "_" + this.name;
    }

    public VarOpts varOptsForReturn() {
        return varOptsForReturn(false);
    }

    public VarOpts varOptsForReturn(boolean upcall) {
        return VarOpts.ofReturn(isCriticalStyle() || upcall);
    }

    public boolean hasCriticalLinkerOption() {
        return annos.stream().anyMatch(a -> a.typeRef instanceof AnnoLinkerOptionCriticalTypeInfo);
    }

    public boolean isAllowHeapAccess() {
        if (opts.hasCompilationFlag(CompilationFlag.DISABLE_ALLOW_HEAP_ACCESS)) {
            return false;
        }
        var annoOpt = annos.stream().filter(a -> a.typeRef instanceof AnnoLinkerOptionCriticalTypeInfo).findAny();
        if (annoOpt.isEmpty())
            return false;
        var anno = annoOpt.get();
        var vOpt = anno.values.stream().filter(v -> v.name.equals("allowHeapAccess")).findAny();
        if (vOpt.isEmpty())
            return false;
        var v = vOpt.get();
        if (v.value instanceof Boolean)
            return (boolean) v.value;
        return false;
    }

    public boolean isCriticalStyle() {
        var annoOpt = annos.stream().filter(a -> a.typeRef instanceof AnnoStyleTypeInfo).findFirst();
        if (annoOpt.isEmpty()) {
            return false;
        }
        var anno = annoOpt.get();
        var vOpt = anno.values.stream().filter(v -> v.name.equals("value")).findFirst();
        if (vOpt.isEmpty()) {
            return false;
        }
        if (!(vOpt.get().value instanceof String[])) {
            return false;
        }
        var v = (String[]) vOpt.get().value;
        if (v.length != 2) {
            return false;
        }
        if (!"Lio/vproxy/pni/annotation/Styles;".equals(v[0])) {
            return false;
        }
        return "critical".equals(v[1]);
    }

    public String getImplC() {
        var opt = annos.stream().filter(a -> a.typeRef instanceof AnnoImplTypeInfo).findFirst();
        if (opt.isEmpty()) {
            return null;
        }
        var anno = opt.get();
        var vOpt = anno.values.stream().filter(v -> v.name.equals("c")).findFirst();
        if (vOpt.isEmpty()) {
            return null;
        }
        var v = vOpt.get().value;
        if (!(v instanceof String)) {
            return null;
        }
        return (String) v;
    }

    public List<String> getImplInclude() {
        return Utils.getStringListFromAnno(annos, t -> t instanceof AnnoImplTypeInfo, "include");
    }

    public String getNativeReturnTypeAnno() {
        return Utils.getNativeReturnType(annos);
    }

    public boolean noAlloc() {
        return annos.stream().anyMatch(a -> a.typeRef instanceof AnnoNoAllocTypeInfo);
    }
}
