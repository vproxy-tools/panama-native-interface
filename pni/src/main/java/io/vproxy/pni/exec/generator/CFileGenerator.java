package io.vproxy.pni.exec.generator;

import io.vproxy.pni.exec.CompilerOptions;
import io.vproxy.pni.exec.Main;
import io.vproxy.pni.exec.ast.AstClass;
import io.vproxy.pni.exec.ast.AstField;
import io.vproxy.pni.exec.ast.AstMethod;
import io.vproxy.pni.exec.ast.AstParam;
import io.vproxy.pni.exec.internal.PNILogger;
import io.vproxy.pni.exec.internal.Utils;
import io.vproxy.pni.exec.internal.VarOpts;
import io.vproxy.pni.exec.type.ArrayTypeInfo;
import io.vproxy.pni.exec.type.ClassTypeInfo;
import io.vproxy.pni.exec.type.TypeInfo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@SuppressWarnings("SameParameterValue")
public class CFileGenerator {
    protected final AstClass cls;
    protected final CompilerOptions opts;

    public CFileGenerator(AstClass cls, CompilerOptions opts) {
        this.cls = cls;
        this.opts = opts;
    }

    public void flush(File dir) {
        var cCode = generate();
        if (cCode == null) {
            PNILogger.debug(opts, "no native code generated for " + cls.fullName() + " @ " + this.getClass().getSimpleName());
            return;
        }
        var hash = Utils.sha256(cCode);
        cCode += Utils.metadata(opts, Main.C_GEN_VERSION);
        cCode += "// sha256:" + hash + "\n";
        String fileName = fileName();
        Path path = Path.of(dir.getAbsolutePath(), fileName);
        if (Utils.hashesAreTheSame(path.toFile(), hash)) {
            PNILogger.debug(opts, "skipping native file because nothing changed: " + path);
            return;
        }
        PNILogger.debug(opts, "writing generated native file for " + cls.fullName() + " to " + path);
        try {
            Files.writeString(path, cCode);
        } catch (IOException e) {
            throw new RuntimeException("failed writing c code: " + fileName, e);
        }
    }

    public String generate() {
        return generateC();
    }

    protected String fileName() {
        return cls.underlinedName() + ".h";
    }

    protected void includeClassHeader(StringBuilder sb, AstClass cls) {
        sb.append("#include \"").append(cls.underlinedName()).append(".h\"\n");
    }

    @SuppressWarnings("StringConcatenationInsideStringBufferAppend")
    private String generateC() {
        var sb = new StringBuilder();
        sb.append("/* DO NOT EDIT THIS FILE - it is machine generated */\n" +
                  "/* Header for class " + cls.underlinedName() + " */\n");
        sb.append("#ifndef _Included_").append(cls.underlinedName()).append("\n");
        sb.append("#define _Included_").append(cls.underlinedName()).append("\n");
        sb.append("#ifdef __cplusplus\n" +
                  "extern \"C\" {\n" +
                  "#endif\n");
        if (cls.needToGenerateTypeDeclaration()) {
            sb.append("\n");
            // union|struct {nativeName};
            if (cls.isUnion()) {
                sb.append("union ");
            } else {
                sb.append("struct ");
            }
            sb.append(cls.nativeName()).append(";\n");
            if (cls.typedef()) {
                // typedef union|struct {nativeName} {nativeName};
                sb.append("typedef ");
                if (cls.isUnion()) {
                    sb.append("union ");
                } else {
                    sb.append("struct ");
                }
                sb.append(cls.nativeName()).append(" ").append(cls.nativeName()).append(";\n");
            }
        }
        sb.append("\n" +
                  "#ifdef __cplusplus\n" +
                  "}\n" +
                  "#endif\n" +
                  "\n");
        sb.append("#include <jni.h>\n" +
                  "#include <pni.h>\n");
        var extraInclude = cls.extraInclude();
        if (extraInclude != null) {
            for (var i : extraInclude) {
                if (i.startsWith("<") && i.endsWith(">")) {
                    sb.append("#include ").append(i).append("\n");
                } else {
                    sb.append("#include \"").append(i).append("\"\n");
                }
            }
        }
        var includedClasses = new HashSet<AstClass>();
        if (cls.superTypeRef != null) {
            var cls = ((ClassTypeInfo) this.cls.superTypeRef).getClazz();
            includedClasses.add(cls);
            includeClassHeader(sb, cls);
        }
        for (var f : cls.fields) {
            if (f.typeRef instanceof ClassTypeInfo) {
                var classTypeInfo = (ClassTypeInfo) f.typeRef;
                var cls = classTypeInfo.getClazz();
                if (includedClasses.add(cls)) {
                    includeClassHeader(sb, cls);
                }
            }
        }
        for (var m : cls.methods) {
            if (m.returnTypeRef instanceof ClassTypeInfo) {
                var classTypeInfo = (ClassTypeInfo) m.returnTypeRef;
                var cls = classTypeInfo.getClazz();
                if (includedClasses.add(cls)) {
                    includeClassHeader(sb, cls);
                }
            }
            for (var p : m.params) {
                TypeInfo typeInfo = p.typeRef;
                if (typeInfo instanceof ArrayTypeInfo) {
                    typeInfo = ((ArrayTypeInfo) typeInfo).getElementType();
                }
                if (typeInfo instanceof ClassTypeInfo) {
                    var classTypeInfo = (ClassTypeInfo) typeInfo;
                    var cls = classTypeInfo.getClazz();
                    if (includedClasses.add(cls)) {
                        includeClassHeader(sb, cls);
                    }
                }
                for (var g : p.genericTypeRefs) {
                    // note: g might be null
                    if (g instanceof ClassTypeInfo) {
                        var classTypeInfo = (ClassTypeInfo) g;
                        var cls = classTypeInfo.getClazz();
                        if (includedClasses.add(cls)) {
                            includeClassHeader(sb, cls);
                        }
                    }
                }
            }
        }
        sb.append("\n" +
                  "#ifdef __cplusplus\n" +
                  "extern \"C\" {\n" +
                  "#endif\n");
        generateC(sb, 0, true);
        sb.append("\n" +
                  "#ifdef __cplusplus\n" +
                  "}\n" +
                  "#endif\n" +
                  "#endif // _Included_").append(cls.underlinedName()).append("\n");
        return sb.toString();
    }

    private void generateC(StringBuilder sb, int indent, boolean generateCompleteFile) {
        if (generateCompleteFile && cls.needToGenerateExpand()) {
            if (cls.getSizeof() != null) {
                sb.append("\n");
                sb.append("JNIEXPORT size_t JNICALL JavaCritical_").append(cls.underlinedName()).append("___getLayoutByteSize();\n");
            }
            sb.append("\n");
            sb.append("PNIEnvExpand(").append(cls.nativeName()).append(", ").append(cls.nativeTypeName()).append(" *)\n");
            sb.append("PNIBufExpand(")
                .append(cls.nativeName()).append(", ")
                .append(cls.nativeTypeName()).append(", ");
            var sizeof = cls.getSizeof();
            if (sizeof == null) {
                var size = cls.getNativeMemorySize();
                if (size <= 0) {
                    sb.append("(0 /* !!invalid!! */)");
                } else {
                    sb.append(size);
                }
            } else {
                sb.append("JavaCritical_").append(cls.underlinedName()).append("___getLayoutByteSize()");
            }
            sb.append(")\n");
        }
        if (cls.needToGenerateTypeDefinition() || !generateCompleteFile) {
            if (generateCompleteFile) {
                sb.append("\n");
            }
            // for complete:
            // PNI_PACK(union|struct, {nativeName}, { ... });
            // for incomplete:
            // union { ... };
            if (cls.isAligned()) {
                if (cls.isUnion()) {
                    sb.append("union");
                } else {
                    sb.append("struct");
                }
                if (generateCompleteFile) {
                    sb.append(" ").append(cls.nativeName()).append(" ");
                } else {
                    sb.append(" ");
                }
            } else {
                if (generateCompleteFile) {
                    sb.append("PNI_PACK(");
                }
                if (cls.isUnion()) {
                    sb.append("union");
                } else {
                    sb.append("struct");
                }
                if (generateCompleteFile) {
                    sb.append(", ").append(cls.nativeName()).append(", ");
                } else {
                    sb.append(" ");
                }
            }
            sb.append("{\n");
            if (cls.superTypeRef != null) {
                Utils.appendIndent(sb, indent + 4).append(
                    cls.superTypeRef.nativeType("SUPER", VarOpts.fieldDefault())
                ).append(";\n");
            }
            if (cls.headPadding > 0) {
                long maxType = 0;
                if (cls.superTypeRef != null) {
                    maxType = cls.superTypeRef.rawNativeMemoryAlign(VarOpts.fieldDefault());
                }
                if (!cls.isAligned() || cls.extraHeadPadding > 0) {
                    Utils.appendIndent(sb, indent + 4);
                    Utils.appendCPadding(sb, maxType, cls.headPadding).append("\n");
                }
            }
            for (var f : cls.fields) {
                get(f).generateC(sb, indent + 4);
            }
            Utils.appendIndent(sb, indent).append("}");
            if (generateCompleteFile) {
                if (!cls.isAligned()) {
                    sb.append(")");
                }
            }
            sb.append(";\n");
        }
        if (!generateCompleteFile) {
            return;
        }
        if (!cls.methods.isEmpty()) {
            sb.append("\n");
        }
        for (var m : cls.methods) {
            get(m).generateC(sb, indent, cls.underlinedName(), cls.nativeTypeName(), cls.isUpcall());
        }
    }

    private final Map<AstField, FieldGenerator> fieldGenerators = new HashMap<>();

    private FieldGenerator get(AstField f) {
        return fieldGenerators.computeIfAbsent(f, FieldGenerator::new);
    }

    private class FieldGenerator {
        private final AstField field;

        private FieldGenerator(AstField field) {
            this.field = field;
        }

        private void generateC(StringBuilder sb, int indent) {
            if (field.typeRef instanceof ClassTypeInfo) {
                var clsTypeInfo = (ClassTypeInfo) field.typeRef;
                var cls = clsTypeInfo.getClazz();
                if (cls.isUnionEmbed()) {
                    Utils.appendIndent(sb, indent);
                    new CFileGenerator(cls, opts).generateC(sb, indent, false);
                    return;
                }
            }
            var bitfields = field.getBitFieldInfo();
            if (bitfields == null) {
                Utils.appendIndent(sb, indent);
                var type = field.getNativeTypeAnno();
                if (type == null) {
                    sb.append(field.typeRef.nativeType(field.nativeName(), field.varOpts()));
                } else {
                    sb.append(type).append(" ").append(field.nativeName());
                    if (!field.varOpts().isPointerGeneral()) {
                        sb.append("[").append(field.varOpts().getLen()).append("]");
                    }
                }
                sb.append(";");
            } else {
                var tname = field.typeRef.nativeType(null, field.varOpts());
                for (var b : bitfields) {
                    Utils.appendIndent(sb, indent)
                        .append(tname).append(" ").append(b.name).append(" : ").append(b.bit).append(";\n");
                }
                var last = bitfields.get(bitfields.size() - 1);
                if (last.offset + last.bit < field.getNativeMemorySize() * 8) {
                    Utils.appendIndent(sb, indent)
                        .append(tname).append(" : ").append(field.getNativeMemorySize() * 8 - (last.offset + last.bit)).append(";\n");
                }
                // add indent for padding
                if (field.padding > 0) {
                    if (!cls.isAligned() || field.extraPadding > 0) {
                        Utils.appendIndent(sb, indent);
                    }
                }
            }
            if (field.padding > 0) {
                if (!cls.isAligned() || field.extraPadding > 0) {
                    Utils.appendCPadding(sb, field.typeRef.rawNativeMemoryAlign(field.varOpts()), field.padding);
                }
            }
            sb.append("\n");
        }
    }

    private final Map<AstMethod, MethodGenerator> methodGenerators = new HashMap<>();

    private MethodGenerator get(AstMethod m) {
        return methodGenerators.computeIfAbsent(m, MethodGenerator::new);
    }

    protected static class MethodGenerator {
        protected final AstMethod method;

        protected MethodGenerator(AstMethod method) {
            this.method = method;
        }

        private void generateC(StringBuilder sb, int indent, String classUnderlinedName, String classNativeTypeName, boolean upcall) {
            generateC0(sb, indent, classUnderlinedName, classNativeTypeName, upcall);
            sb.append(";\n");
        }

        protected void generateC0(StringBuilder sb, int indent, String classUnderlinedName, String classNativeTypeName) {
            generateC0(sb, indent, classUnderlinedName, classNativeTypeName, false);
        }

        protected void generateC0(StringBuilder sb, int indent, String classUnderlinedName, String classNativeTypeName, boolean upcall) {
            Utils.appendIndent(sb, indent)
                .append("JNIEXPORT ");
            if (method.isCriticalStyle() || upcall) {
                var returnType = method.getNativeReturnTypeAnno();
                if (returnType == null) {
                    sb.append(method.returnTypeRef.nativeReturnType(method.varOptsForReturn(upcall)));
                } else {
                    sb.append(returnType);
                }
            } else {
                sb.append("int");
            }
            sb.append(" JNICALL ").append(method.nativeName(classUnderlinedName, upcall)).append("(");
            var isFirst = true;
            if (!method.isCriticalStyle() && !upcall) {
                sb.append("PNIEnv_").append(method.returnTypeRef.nativeEnvType(method.varOptsForReturn()));
                sb.append(" * env");
                isFirst = false;
            }
            if (classNativeTypeName != null) {
                if (!isFirst) {
                    sb.append(", ");
                }
                isFirst = false;
                sb.append(classNativeTypeName).append(" * self");
            }
            for (var p : method.params) {
                if (isFirst) {
                    isFirst = false;
                } else {
                    sb.append(", ");
                }
                get(p).generateC(sb, 0);
            }
            var returnAllocation = method.returnTypeRef.allocationInfoForReturnValue(method.varOptsForReturn(upcall));
            if (returnAllocation.haveAdditionalAllocatedMemory() && !method.noAlloc()) {
                if (!isFirst) {
                    sb.append(", ");
                }
                isFirst = false;
                var returnTypeExtraType = method.returnTypeRef.nativeParamType(null, method.varOptsForReturn(upcall));
                sb.append(returnTypeExtraType).append(" return_");
            }
            if (isFirst) {
                sb.append("void");
            }
            sb.append(")");
        }

        private final Map<AstParam, ParamGenerator> paramGenerators = new HashMap<>();

        private ParamGenerator get(AstParam p) {
            return paramGenerators.computeIfAbsent(p, ParamGenerator::new);
        }

        private static class ParamGenerator {
            private final AstParam param;

            private ParamGenerator(AstParam param) {
                this.param = param;
            }

            private void generateC(StringBuilder sb, int indent) {
                Utils.appendIndent(sb, indent);
                var type = param.getNativeTypeAnno();
                if (type == null) {
                    sb.append(param.typeRef.nativeParamType(param.nativeName(), param.varOpts()));
                } else {
                    sb.append(type).append(" ").append(param.nativeName());
                }
            }
        }
    }
}
