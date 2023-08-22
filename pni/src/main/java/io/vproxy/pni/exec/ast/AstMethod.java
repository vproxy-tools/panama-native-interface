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
        return ((critical() || upcall) ? "JavaCritical_" : "Java_") + classUnderlinedName + "_" + this.name;
    }

    public void generateC(StringBuilder sb, int indent, String classUnderlinedName, String classNativeTypeName, boolean upcall) {
        generateC0(sb, indent, classUnderlinedName, classNativeTypeName, upcall);
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
        generateC0(sb, indent, classUnderlinedName, classNativeTypeName, false);
    }

    private void generateC0(StringBuilder sb, int indent, String classUnderlinedName, String classNativeTypeName, boolean upcall) {
        Utils.appendIndent(sb, indent)
            .append("JNIEXPORT ");
        if (critical() || upcall) {
            sb.append(returnTypeRef.nativeReturnType(varOptsForReturn(upcall)));
        } else {
            sb.append("int");
        }
        sb.append(" JNICALL ").append(nativeName(classUnderlinedName, upcall)).append("(");
        if (!critical() && !upcall) {
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
        var returnAllocation = returnTypeRef.allocationInfoForReturnValue(varOptsForReturn(upcall));
        String returnTypeExtraType = null;
        if (returnAllocation.requireAllocator()) {
            returnTypeExtraType = returnTypeRef.nativeParamType(null, varOptsForReturn(upcall));
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
            if ((!critical() && !upcall) || (classNativeTypeName != null || !params.isEmpty())) {
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
            sb.append(returnTypeRef.methodHandleTypeForReturn(varOptsForReturn()));
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

    public void generateJavaUpcall(StringBuilder sb, int indent, String classFullName) {
        sb.append("\n");
        Utils.appendIndent(sb, indent).append("public static final MemorySegment ").append(name).append(";\n");
        sb.append("\n");
        Utils.appendIndent(sb, indent).append("private static ");
        sb.append(returnTypeRef.javaTypeForUpcallReturn(varOptsForReturn(true)))
            .append(" ").append(name).append("(");
        var isFirst = true;
        for (var p : params) {
            if (isFirst) {
                isFirst = false;
            } else {
                sb.append(", ");
            }
            p.generateUpcallParam(sb, 0);
        }
        var returnAllocation = returnTypeRef.allocationInfoForReturnValue(varOptsForReturn(true));
        var interfaceReturnAllocation = returnTypeRef.allocationInfoForUpcallInterfaceReturnValue(varOptsForReturn(true));
        if (returnAllocation.requireAllocator()) {
            if (!params.isEmpty()) {
                sb.append(", ");
            }
            sb.append("MemorySegment return_");
        }
        sb.append(") {\n");
        Utils.appendIndent(sb, indent + 4)
            .append("if (IMPL == null) {\n");
        Utils.appendIndent(sb, indent + 8)
            .append("System.out.println(\"").append(classFullName).append("#").append(name).append("\");\n");
        Utils.appendIndent(sb, indent + 8)
            .append("System.exit(1);\n");
        Utils.appendIndent(sb, indent + 4).append("}\n");
        Utils.appendIndent(sb, indent + 4);
        if (!(returnTypeRef instanceof VoidTypeInfo)) {
            sb.append("var RESULT = ");
        }
        sb.append("IMPL.").append(name).append("(");
        for (int i = 0; i < params.size(); i++) {
            var p = params.get(i);
            sb.append("\n");
            p.generateUpcallConvert(sb, indent + 8);
            if (i < params.size() - 1) {
                sb.append(",");
            }
        }
        if (interfaceReturnAllocation.requireAllocator()) {
            if (!params.isEmpty()) {
                sb.append(",");
            }
            sb.append("\n");
            Utils.appendIndent(sb, indent + 8)
                .append(returnTypeRef.convertExtraToUpcallArgument("return_", varOptsForReturn(true)))
                .append("\n");
            Utils.appendIndent(sb, indent + 4).append(");\n");
        } else if (!params.isEmpty()) {
            sb.append("\n");
            Utils.appendIndent(sb, indent + 4).append(");\n");
        } else {
            sb.append(");\n");
        }
        if (!(returnTypeRef instanceof VoidTypeInfo)) {
            returnTypeRef.convertFromUpcallReturn(sb, indent + 4, varOptsForReturn(true));
        }
        Utils.appendIndent(sb, indent).append("}\n");
    }

    public void generateJavaUpcallInterfaceMethod(StringBuilder sb) {
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
        sb.append(returnTypeRef.javaTypeForUpcallInterfaceReturn(varOptsForReturn(true)))
            .append(" ").append(name).append("(");
        var isFirst = true;
        for (var p : params) {
            if (isFirst) {
                isFirst = false;
            } else {
                sb.append(", ");
            }
            p.generateUpcallInterfaceParam(sb, 0);
        }
        var returnAllocation = returnTypeRef.allocationInfoForUpcallInterfaceReturnValue(varOptsForReturn(true));
        if (returnAllocation.requireAllocator()) {
            if (!params.isEmpty()) {
                sb.append(", ");
            }
            sb.append(returnTypeRef.javaTypeForExtraUpcallInterfaceParam(VarOpts.paramDefault())).append(" ").append("return_");
        }
        sb.append(");\n");
    }

    private VarOpts varOptsForReturn() {
        return varOptsForReturn(false);
    }

    private VarOpts varOptsForReturn(boolean upcall) {
        return VarOpts.ofReturn(critical() || upcall);
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

    public String nativeUpcallFunctionPointer(boolean isParam) {
        var sb = new StringBuilder();
        sb.append(returnTypeRef.nativeReturnType(varOptsForReturn(true)));
        sb.append(" (*");
        if (!isParam) {
            sb.append("_");
        }
        sb.append(name);
        sb.append(")(");
        boolean isFirst = true;
        for (var p : params) {
            if (isFirst) {
                isFirst = false;
            } else {
                sb.append(",");
            }
            sb.append(p.typeRef.nativeParamType(null, p.varOpts()));
        }
        var returnAllocation = returnTypeRef.allocationInfoForReturnValue(varOptsForReturn(true));
        String returnTypeExtraType = null;
        if (returnAllocation.requireAllocator()) {
            returnTypeExtraType = returnTypeRef.nativeParamType(null, varOptsForReturn(true));
        }
        if (returnTypeExtraType != null) {
            if (!params.isEmpty()) {
                sb.append(",");
            }
            sb.append(returnTypeExtraType);
        }
        sb.append(")");
        return sb.toString();
    }

    public void generateCUpcallImpl(StringBuilder sb, int indent, String classUnderlinedName) {
        generateC0(sb, indent, classUnderlinedName, null, true);
        sb.append(" {\n");
        Utils.appendIndent(sb, indent + 4)
            .append("if (_").append(name).append(" == NULL) {\n");
        Utils.appendIndent(sb, indent + 8)
            .append("printf(\"").append(nativeName(classUnderlinedName, true)).append(" function pointer is null\");\n");
        Utils.appendIndent(sb, indent + 8)
            .append("fflush(stdout);\n");
        Utils.appendIndent(sb, indent + 8)
            .append("exit(1);\n");
        Utils.appendIndent(sb, indent + 4).append("}\n");
        Utils.appendIndent(sb, indent + 4);
        if (!(returnTypeRef instanceof VoidTypeInfo)) {
            sb.append("return ");
        }
        sb.append("_").append(name).append("(");
        boolean isFirst = true;
        for (var p : params) {
            if (isFirst) {
                isFirst = false;
            } else {
                sb.append(", ");
            }
            sb.append(p.name);
        }
        if (returnTypeRef.allocationInfoForReturnValue(varOptsForReturn(true)).requireAllocator()) {
            if (!params.isEmpty()) {
                sb.append(", ");
            }
            sb.append("return_");
        }
        sb.append(");\n");
        Utils.appendIndent(sb, indent).append("}\n");
    }

    public void generateUpcallMethodHandle(StringBuilder sb, String classFullName) {
        sb.append("MethodHandles.lookup().findStatic(").append(classFullName).append(".class, ")
            .append("\"").append(name).append("\", ")
            .append("MethodType.methodType(");
        if (returnTypeRef instanceof VoidTypeInfo) {
            sb.append("void.class");
        } else {
            sb.append(returnTypeRef.javaTypeForUpcallReturn(varOptsForReturn(true))).append(".class");
        }
        for (var p : params) {
            sb.append(", ");
            p.generateUpcallParamClass(sb, 0);
        }
        if (returnTypeRef.allocationInfoForReturnValue(varOptsForReturn(true)).requireAllocator()) {
            sb.append(", ");
            sb.append("MemorySegment.class");
        }
        sb.append("))");
    }

    public void generateUpcallStub(StringBuilder sb) {
        sb.append("PanamaUtils.defineCFunction(ARENA, ")
            .append(name).append("MH, ");
        if (returnTypeRef instanceof VoidTypeInfo) {
            sb.append("void.class");
        } else {
            sb.append(returnTypeRef.methodHandleTypeForUpcall(varOptsForReturn(true)));
        }
        for (var p : params) {
            sb.append(", ");
            p.generateMethodHandleForUpcall(sb, 0);
        }
        if (returnTypeRef.allocationInfoForReturnValue(varOptsForReturn(true)).requireAllocator()) {
            sb.append(", ");
            sb.append("MemorySegment.class");
        }
        sb.append(")");
    }
}
