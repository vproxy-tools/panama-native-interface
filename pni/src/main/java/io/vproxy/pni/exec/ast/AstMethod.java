package io.vproxy.pni.exec.ast;

import io.vproxy.pni.exec.internal.Utils;
import io.vproxy.pni.exec.internal.VarOpts;
import io.vproxy.pni.exec.type.*;
import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.MethodNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static io.vproxy.pni.exec.internal.Consts.*;

public class AstMethod {
    public final List<AstAnno> annos = new ArrayList<>();
    public String name;
    public List<AstGenericDef> genericDefs = new ArrayList<>();
    public AstTypeDesc returnType;
    public List<AstParam> params = new ArrayList<>();
    public List<String> throwTypes = new ArrayList<>(); // internal name

    public TypeInfo returnTypeRef;
    public final List<TypeInfo> throwTypeRefs = new ArrayList<>();

    public AstMethod(MethodNode m) {
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

    public AstMethod() {
        // for unit testing only
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

    public void validate(String path, List<String> errors) {
        path = path + "#method(" + name + ")";
        if (returnTypeRef == null) {
            errors.add(path + ": unable to find returnTypeRef: " + returnType);
        } else {
            returnTypeRef.checkType(errors, path, varOptsForReturn());
            if (returnTypeRef instanceof CallSiteTypeInfo) {
                errors.add(path + ": cannot use CallSite as return value");
            }
        }
        for (var p : params) {
            p.validate(path, errors);
        }
        if (critical() && !throwTypes.isEmpty()) {
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
        var name = Utils.getName(annos);
        if (name != null)
            return name;
        return (critical() ? "JavaCritical_" : "Java_") + classUnderlinedName + "_" + this.name;
    }

    public void generateC(StringBuilder sb, int indent, String classUnderlinedName, String classNativeTypeName) {
        generateC0(sb, indent, classUnderlinedName, classNativeTypeName);
        sb.append(";\n");
    }

    public void generateCImpl(StringBuilder sb, int indent, String classUnderlinedName, String classNativeTypeName, String impl) {
        generateC0(sb, indent, classUnderlinedName, classNativeTypeName);
        sb.append(" {\n");
        Arrays.stream(impl.replace("\r", "").split("\n")).map(line -> {
            if (line.isBlank()) return "";
            return line;
        }).forEach(line -> {
            if (line.isEmpty()) {
                sb.append("\n");
            } else {
                Utils.appendIndent(sb, indent + 4).append(line).append("\n");
            }
        });
        Utils.appendIndent(sb, indent).append("}\n");
    }

    private void generateC0(StringBuilder sb, int indent, String classUnderlinedName, String classNativeTypeName) {
        Utils.appendIndent(sb, indent)
            .append("JNIEXPORT ");
        if (critical()) {
            sb.append(returnTypeRef.nativeReturnType(varOptsForReturn()));
        } else {
            sb.append("int");
        }
        sb.append(" JNICALL ").append(nativeName(classUnderlinedName)).append("(");
        if (!critical()) {
            sb.append("PNIEnv_").append(returnTypeRef.nativeEnvType(varOptsForReturn()));
            sb.append(" * env");
            if (classNativeTypeName != null || !params.isEmpty()) {
                sb.append(", ");
            }
        }
        if (classNativeTypeName != null) {
            sb.append(classNativeTypeName).append(" * self");
            if (!params.isEmpty()) {
                sb.append(", ");
            }
        }
        var returnAllocation = returnTypeRef.allocationInfoForReturnValue(varOptsForReturn());
        String returnTypeExtraType = null;
        if (returnAllocation.requireAllocator()) {
            returnTypeExtraType = returnTypeRef.nativeParamType(null, varOptsForReturn());
        }
        var isFirst = true;
        for (var p : params) {
            if (isFirst) {
                isFirst = false;
            } else {
                sb.append(", ");
            }
            p.generateC(sb, 0);
        }
        if (returnTypeExtraType != null) {
            if (!critical() || (classNativeTypeName != null || !params.isEmpty())) {
                sb.append(", ");
            }
            sb.append(returnTypeExtraType).append(" return_");
        }
        sb.append(")");
    }

    public void generateJava(StringBuilder sb, int indent, String classUnderlinedName, boolean needSelf) {
        Utils.appendIndent(sb, indent).append("private static final MethodHandle ").append(name).append("MH").append(" = PanamaUtils.");
        if (critical()) {
            sb.append("lookupPNICriticalFunction(");
        } else {
            sb.append("lookupPNIFunction(");
        }
        if (trivial()) {
            sb.append("true, ");
        } else {
            sb.append("false, ");
        }
        if (critical()) {
            sb.append(returnTypeRef.methodHandleType(varOptsForReturn()));
            sb.append(", ");
        }
        sb.append("\"").append(nativeName(classUnderlinedName)).append("\"");
        if (needSelf) {
            sb.append(", MemorySegment.class /* self */");
        }
        for (var p : params) {
            sb.append(", ");
            p.generateMethodHandle(sb, 0);
            sb.append(" /* ").append(p.name).append(" */");
        }
        var returnAllocation = returnTypeRef.allocationInfoForReturnValue(varOptsForReturn());
        if (returnAllocation.requireAllocator()) {
            sb.append(", MemorySegment.class /* return */");
        }
        sb.append(");\n");
        sb.append("\n");

        Utils.appendIndent(sb, indent).append("public ");
        if (!genericDefs.isEmpty()) {
            sb.append("<");
            var isFirst = true;
            for (var g : genericDefs) {
                if (isFirst) {
                    isFirst = false;
                } else {
                    sb.append(", ");
                }
                sb.append(g);
            }
            sb.append("> ");
        }
        sb.append(returnTypeRef.javaTypeForReturn(varOptsForReturn()))
            .append(" ").append(name).append("(");
        if (!critical()) {
            sb.append("PNIEnv ENV");
            if (!params.isEmpty()) {
                sb.append(", ");
            }
        }
        var paramNeedsAllocator = returnAllocation.requirePooledAllocator();
        var isFirst = true;
        for (var p : params) {
            if (isFirst) {
                isFirst = false;
            } else {
                sb.append(", ");
            }
            p.generateParam(sb, 0);
            var paramOpts = p.paramOpts();
            if (paramOpts.isDependOnAllocator()) {
                paramNeedsAllocator = true;
            }
        }
        if (returnAllocation.requireExtraParameter()) {
            if (!critical() || !params.isEmpty()) {
                sb.append(", ");
            }
            sb.append("Allocator ALLOCATOR");
        }
        sb.append(")");
        if (!throwTypeRefs.isEmpty()) {
            sb.append(" throws ");
            isFirst = true;
            for (var t : throwTypeRefs) {
                if (isFirst) {
                    isFirst = false;
                } else {
                    sb.append(", ");
                }
                sb.append(t.name());
            }
        }
        sb.append(" {\n");
        if (!critical()) {
            Utils.appendIndent(sb, indent + 4).append("ENV.reset();\n");
        }
        int invocationIndent = indent + 4;
        if (paramNeedsAllocator) {
            Utils.appendIndent(sb, indent + 4).append("try (var POOLED = Allocator.ofPooled()) {\n");
            invocationIndent += 4;
        }
        if (critical()) {
            if (!(returnTypeRef instanceof VoidTypeInfo)) {
                Utils.appendIndent(sb, invocationIndent);
                if (returnTypeRef instanceof PrimitiveTypeInfo) {
                    sb.append(returnTypeRef.javaTypeForReturn(varOptsForReturn()));
                } else {
                    sb.append("MemorySegment");
                }
                sb.append(" RESULT;\n");
            }
        } else {
            Utils.appendIndent(sb, invocationIndent).append("int ERR;\n");
        }
        Utils.appendIndent(sb, invocationIndent)
            .append("try {\n");
        Utils.appendIndent(sb, invocationIndent + 4);
        if (critical()) {
            if (!(returnTypeRef instanceof VoidTypeInfo)) {
                sb.append("RESULT = (");
                if (returnTypeRef instanceof PrimitiveTypeInfo) {
                    sb.append(returnTypeRef.javaTypeForReturn(varOptsForReturn()));
                } else {
                    sb.append("MemorySegment");
                }
                sb.append(") ");
            }
        } else {
            sb.append("ERR = (int) ");
        }
        sb.append(name).append("MH").append(".invokeExact(");
        if (!critical()) {
            sb.append("ENV.MEMORY");
            if (!params.isEmpty() || needSelf) {
                sb.append(", ");
            }
        }
        if (needSelf) {
            sb.append("MEMORY");
            if (!params.isEmpty()) {
                sb.append(", ");
            }
        }
        isFirst = true;
        for (var p : params) {
            if (isFirst) {
                isFirst = false;
            } else {
                sb.append(", ");
            }
            p.generateConvert(sb, 0);
        }
        if (returnAllocation.requireExtraParameter()) {
            if (!critical() || needSelf || !params.isEmpty()) {
                sb.append(", ");
            }
            sb.append("ALLOCATOR.allocate(").append(returnAllocation.byteSize()).append(")");
        } else if (returnAllocation.requirePooledAllocator()) {
            if (!critical() || needSelf || !params.isEmpty()) {
                sb.append(", ");
            }
            sb.append("POOLED.allocate(").append(returnAllocation.byteSize()).append(")");
        }
        sb.append(");\n");
        Utils.appendIndent(sb, invocationIndent)
            .append("} catch (Throwable THROWABLE) {\n");
        Utils.appendIndent(sb, invocationIndent + 4)
            .append("throw PanamaUtils.convertInvokeExactException(THROWABLE);\n");
        Utils.appendIndent(sb, invocationIndent)
            .append("}\n");
        if (!critical()) {
            Utils.appendIndent(sb, invocationIndent)
                .append("if (ERR != 0) {\n");
            for (var t : throwTypeRefs) {
                Utils.appendIndent(sb, invocationIndent + 4)
                    .append("ENV.throwIf(").append(t.name()).append(".class);\n");
            }
            Utils.appendIndent(sb, invocationIndent + 4).append("ENV.throwLast();\n");
            Utils.appendIndent(sb, invocationIndent).append("}\n");
        }
        returnTypeRef.convertInvokeExactReturnValueToJava(sb, invocationIndent, varOptsForReturn());
        if (paramNeedsAllocator) {
            Utils.appendIndent(sb, indent + 4).append("}\n");
        }
        Utils.appendIndent(sb, indent).append("}\n");
    }

    private VarOpts varOptsForReturn() {
        return VarOpts.ofReturn(critical());
    }

    private boolean trivial() {
        return annos.stream().anyMatch(a -> a.typeRef != null && a.typeRef.name().equals(TrivialClassName));
    }

    private boolean critical() {
        return annos.stream().anyMatch(a -> a.typeRef != null && a.typeRef.name().equals(CriticalClassName));
    }

    public String getImplC() {
        var opt = annos.stream().filter(a -> a.typeRef != null && a.typeRef.name().equals(ImplClassName)).findFirst();
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
        var opt = annos.stream().filter(a -> a.typeRef != null && a.typeRef.name().equals(ImplClassName)).findFirst();
        if (opt.isEmpty()) {
            return null;
        }
        var anno = opt.get();
        var vOpt = anno.values.stream().filter(v -> v.name.equals("include")).findFirst();
        if (vOpt.isEmpty()) {
            return null;
        }
        var v = vOpt.get().value;
        if (v instanceof List) {
            //noinspection rawtypes
            var ls = (List) v;
            for (var o : ls) {
                if (!(o instanceof String)) {
                    return null;
                }
            }
            //noinspection unchecked
            return (List<String>) ls;
        }
        return null;
    }
}
