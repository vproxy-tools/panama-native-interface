package io.vproxy.pni.exec.ast;

import io.vproxy.pni.annotation.Trivial;
import io.vproxy.pni.exec.internal.PointerInfo;
import io.vproxy.pni.exec.internal.Utils;
import io.vproxy.pni.exec.internal.VarOpts;
import io.vproxy.pni.exec.type.*;
import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.MethodNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class AstMethod {
    public final List<AstAnno> annos = new ArrayList<>();
    public String name;
    public String returnType;
    public List<AstParam> params = new ArrayList<>();
    public List<String> throwTypes = new ArrayList<>(); // internal name

    public TypeInfo returnTypeRef;
    public final List<TypeInfo> throwTypeRefs = new ArrayList<>();

    public AstMethod(MethodNode m) {
        Utils.readAnnotations(annos, m.visibleAnnotations);
        this.name = m.name;
        this.returnType = m.desc.substring(m.desc.indexOf(")") + 1);
        var paramsPart = m.desc.substring(1, m.desc.indexOf(")"));
        var paramTypes = Utils.extractMethodDescParamsPart(paramsPart);
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
            if (returnTypeRef instanceof ClassTypeInfo classTypeInfo) {
                if (classTypeInfo.getClazz().isInterface) {
                    errors.add(path + ": return type cannot use interface type: " + returnType);
                }
            }
        }
        for (var p : params) {
            p.validate(path, errors);
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
        return "Java_" + classUnderlinedName + "_" + this.name;
    }

    public void generateC(StringBuilder sb, int indent, String classUnderlinedName, String classNativeTypeName) {
        Utils.appendIndent(sb, indent)
            .append("JNIEXPORT int JNICALL ").append(nativeName(classUnderlinedName))
            .append("(PNIEnv_");
        String returnTypeExtraType = null;
        if (returnTypeRef instanceof IntTypeInfo) {
            sb.append("int");
        } else if (returnTypeRef instanceof LongTypeInfo) {
            sb.append("long");
        } else if (returnTypeRef instanceof ShortTypeInfo) {
            sb.append("short");
        } else if (returnTypeRef instanceof ByteTypeInfo) {
            sb.append("byte");
        } else if (returnTypeRef instanceof FloatTypeInfo) {
            sb.append("float");
        } else if (returnTypeRef instanceof DoubleTypeInfo) {
            sb.append("double");
        } else if (returnTypeRef instanceof BooleanTypeInfo) {
            sb.append("bool");
        } else if (returnTypeRef instanceof CharTypeInfo) {
            sb.append("char");
        } else if (returnTypeRef instanceof VoidTypeInfo) {
            sb.append("void");
        } else {
            sb.append("pointer");
            if (returnTypeRef.sizeForUserAllocatorForNativeCallExtraArgument(varOptsForReturn()) != null
                || returnTypeRef.sizeForConfinedArenaForNativeCallExtraArgument(varOptsForReturn()) != null) {
                returnTypeExtraType = returnTypeRef.nativeParamType(null, varOptsForReturn());
            }
        }
        sb.append(" * env");
        if (classNativeTypeName != null) {
            sb.append(", ").append(classNativeTypeName).append(" * self");
        }
        for (var p : params) {
            sb.append(", ");
            p.generateC(sb, 0);
        }
        if (returnTypeExtraType != null) {
            sb.append(", ").append(returnTypeExtraType).append(" return_");
        }
        sb.append(");\n");
    }

    public void generateJava(StringBuilder sb, int indent, String classUnderlinedName, boolean needSelf) {
        Utils.appendIndent(sb, indent).append("private final MethodHandle ").append(name).append(" = PanamaUtils.lookupPNIFunction(");
        if (trivial()) {
            sb.append("true, ");
        } else {
            sb.append("false, ");
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
        var returnAllocatorSize = returnTypeRef.sizeForUserAllocatorForNativeCallExtraArgument(varOptsForReturn());
        var returnArenaSize = returnTypeRef.sizeForConfinedArenaForNativeCallExtraArgument(varOptsForReturn());
        if (returnAllocatorSize != null || returnArenaSize != null) {
            sb.append(", MemorySegment.class /* return */");
        }
        sb.append(");\n");
        sb.append("\n");

        Utils.appendIndent(sb, indent).append("public ")
            .append(returnTypeRef.javaType(varOptsForReturn()))
            .append(" ").append(name).append("(PNIEnv ENV");
        var paramNeedsAllocator = returnArenaSize != null;
        for (var p : params) {
            sb.append(", ");
            p.generateParam(sb, 0);
            var paramOpts = p.paramOpts();
            if (paramOpts.isDependOnAllocator()) {
                paramNeedsAllocator = true;
            }
        }
        if (returnAllocatorSize != null) {
            sb.append(", Allocator ALLOCATOR");
        }
        sb.append(")");
        if (!throwTypeRefs.isEmpty()) {
            sb.append(" throws ");
            var isFirst = true;
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
        Utils.appendIndent(sb, indent + 4).append("ENV.reset();\n");
        int invocationIndent = indent + 4;
        if (paramNeedsAllocator) {
            Utils.appendIndent(sb, indent + 4).append("try (var ARENA = Arena.ofConfined()) {\n");
            invocationIndent += 4;
        }
        Utils.appendIndent(sb, invocationIndent)
            .append("int ERR;\n");
        Utils.appendIndent(sb, invocationIndent)
            .append("try {\n");
        Utils.appendIndent(sb, invocationIndent + 4)
            .append("ERR = (int) this.").append(name).append(".invokeExact(ENV.MEMORY");
        if (needSelf) {
            sb.append(", MEMORY");
        }
        for (var p : params) {
            sb.append(", ");
            p.generateConvert(sb, 0);
        }
        if (returnAllocatorSize != null) {
            sb.append(", ALLOCATOR.allocate(").append(returnAllocatorSize).append(")");
        } else if (returnArenaSize != null) {
            sb.append(", ARENA.allocate(").append(returnArenaSize).append(")");
        }
        sb.append(");\n");
        Utils.appendIndent(sb, invocationIndent)
            .append("} catch (Throwable THROWABLE) {\n");
        Utils.appendIndent(sb, invocationIndent + 4)
            .append("throw PanamaUtils.convertInvokeExactException(THROWABLE);\n");
        Utils.appendIndent(sb, invocationIndent)
            .append("}\n");
        Utils.appendIndent(sb, invocationIndent)
            .append("if (ERR != 0) {\n");
        for (var t : throwTypeRefs) {
            Utils.appendIndent(sb, invocationIndent + 4)
                .append("ENV.throwIf(").append(t.name()).append(".class);\n");
        }
        Utils.appendIndent(sb, invocationIndent + 4).append("ENV.throwLast();\n");
        Utils.appendIndent(sb, invocationIndent).append("}\n");
        if (!(returnTypeRef instanceof VoidTypeInfo)) {
            returnTypeRef.returnValueFormatting(sb, invocationIndent, varOptsForReturn());
        }
        if (paramNeedsAllocator) {
            Utils.appendIndent(sb, indent + 4).append("}\n");
        }
        Utils.appendIndent(sb, indent).append("}\n");
    }

    private VarOpts varOptsForReturn() {
        return VarOpts.of(false, PointerInfo.ofMethod(false), -1);
    }

    private boolean trivial() {
        return annos.stream().anyMatch(a -> a.typeRef != null && a.typeRef.name().equals(Trivial.class.getName()));
    }
}
