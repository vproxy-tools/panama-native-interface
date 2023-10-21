package io.vproxy.pni.exec.generator;

import io.vproxy.pni.exec.CompilationFlag;
import io.vproxy.pni.exec.CompilerOptions;
import io.vproxy.pni.exec.Main;
import io.vproxy.pni.exec.ast.*;
import io.vproxy.pni.exec.internal.PNILogger;
import io.vproxy.pni.exec.internal.Utils;
import io.vproxy.pni.exec.internal.VarOpts;
import io.vproxy.pni.exec.type.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("SameParameterValue")
public class JavaFileGenerator {
    private final AstClass cls;
    private final CompilerOptions opts;

    public JavaFileGenerator(AstClass cls, CompilerOptions opts) {
        this.cls = cls;
        this.opts = opts;
    }

    public void flush(File baseDir) {
        var javaCode = generateJava();
        var hash = Utils.sha256(javaCode);
        javaCode += Utils.metadata(opts, Main.JAVA_GEN_VERSION);
        javaCode += "// sha256:" + hash + "\n";
        var file = Utils.ensureJavaFile(baseDir, cls.fullName());
        if (Utils.hashesAreTheSame(file, hash)) {
            PNILogger.debug(opts, "skipping java file because nothing changed: " + file.getAbsolutePath());
            return;
        }
        PNILogger.debug(opts, "writing generated java file: " + file.getAbsolutePath());
        try {
            Files.writeString(file.toPath(), javaCode);
        } catch (IOException e) {
            throw new RuntimeException("failed writing java code: " + cls.fullName(), e);
        }
    }

    public String generateJava() {
        var sb = new StringBuilder();
        if (!cls.packageName().isEmpty()) {
            sb.append("package ").append(cls.packageName()).append(";\n\n");
        }

        sb.append("import io.vproxy.pni.*;\n" +
                  "import io.vproxy.pni.array.*;\n" +
                  "import java.lang.foreign.*;\n" +
                  "import java.lang.invoke.*;\n" +
                  "import java.nio.ByteBuffer;\n");
        if (opts.hasCompilationFlag(CompilationFlag.GRAAL_C_ENTRYPOINT_LITERAL_UPCALL)) {
            sb.append("import io.vproxy.pni.graal.*;\n");
            sb.append("import org.graalvm.nativeimage.*;\n");
            sb.append("import org.graalvm.nativeimage.c.function.*;\n");
            sb.append("import org.graalvm.nativeimage.c.type.VoidPointer;\n");
            sb.append("import org.graalvm.word.WordFactory;\n");
        }
        sb.append("\n");
        if (cls.isUpcall()) {
            generateJavaUpcall(sb);
        } else {
            generateJava(sb);
        }
        return sb.toString();
    }

    private void generateJava(StringBuilder sb) {
        sb.append("public class ").append(cls.simpleName());
        if (cls.superTypeRef != null) {
            sb.append(" extends ").append(((ClassTypeInfo) cls.superTypeRef).getClazz().fullName());
        } else if (!cls.isInterface) {
            sb.append(" extends AbstractNativeObject");
        }
        if (!cls.isInterface) {
            sb.append(" implements NativeObject");
        }
        sb.append(" {\n");
        if (cls.isInterface) {
            sb.append("    private ").append(cls.simpleName()).append("() {\n");
            sb.append("    }\n");
            sb.append("\n");
            sb.append("    private static final ").append(cls.simpleName()).append(" INSTANCE = new ").append(cls.simpleName()).append("();\n");
            sb.append("\n");
            sb.append("    public static ").append(cls.simpleName()).append(" get() {\n");
            sb.append("        return INSTANCE;\n");
            sb.append("    }\n");
        } else {
            var sizeof = cls.getSizeof();
            if (sizeof != null) { // build the call to `sizeof` native function
                var meth = new AstMethod();
                meth.returnTypeRef = LongTypeInfo.get();
                meth.name = "__getLayoutByteSize";
                meth.annos.add(new AstAnno() {{
                    typeRef = AnnoCriticalTypeInfo.get();
                }});
                meth.annos.add(new AstAnno() {{
                    typeRef = AnnoTrivialTypeInfo.get();
                }});

                get(meth).generateJava(sb, 4, cls.underlinedName(), false, true, true);
                sb.append("\n");
            }

            sb.append("    public static final MemoryLayout LAYOUT = ");
            if (sizeof != null) {
                sb.append("PanamaUtils.padLayout(__getLayoutByteSize()");
                sb.append(", ");
            }
            sb.append("MemoryLayout");
            if (sizeof == null) {
                sb.append(".");
            } else {
                sb.append("::");
            }
            if (cls.isUnion()) {
                sb.append("unionLayout");
            } else {
                sb.append("structLayout");
            }
            if (sizeof == null) {
                sb.append("(\n");
            } else {
                if (cls.superTypeRef != null || cls.headPadding > 0 || !cls.fields.isEmpty()) {
                    sb.append(",");
                }
                sb.append("\n");
            }
            if (cls.superTypeRef != null) {
                Utils.appendIndent(sb, 8)
                    .append(((ClassTypeInfo) cls.superTypeRef).getClazz().fullName()).append(".LAYOUT");
                if (cls.headPadding > 0 || !cls.fields.isEmpty()) {
                    sb.append(",");
                }
                sb.append("\n");
            }
            if (cls.headPadding > 0) {
                Utils.appendJavaPadding(sb, 8, cls.headPadding);
                if (!cls.fields.isEmpty()) {
                    sb.append(",");
                }
                sb.append("\n");
            }
            var isFirst = true;
            for (var f : cls.fields) {
                if (isFirst) {
                    isFirst = false;
                } else {
                    sb.append(",\n");
                }
                get(f).generateJavaLayout(sb, 8, cls.isAlwaysAligned());
            }
            sb.append("\n    )");
            if (cls.isAlwaysAligned() && cls.largestAlignmentBytes() > 1) {
                sb.append(".withByteAlignment(").append(cls.largestAlignmentBytes()).append(")");
            }
            sb.append(";\n");
            sb.append("    public final MemorySegment MEMORY;\n");
            sb.append("\n");
            sb.append("    @Override\n");
            sb.append("    public MemorySegment MEMORY() {\n");
            sb.append("        return MEMORY;\n");
            sb.append("    }\n");
        }

        if (!cls.isInterface) {
            for (var f : cls.fields) {
                sb.append("\n");
                get(f).generateJavaGetterSetter(sb, 4);
                var bitfields = f.getBitFieldInfo();
                if (bitfields != null) {
                    for (var bitfield : bitfields) {
                        sb.append("\n");
                        get(f).generateJavaBitFieldGetterSetter(sb, 4, bitfield);
                    }
                }
            }
            sb.append("\n");
            Utils.appendIndent(sb, 4)
                .append("public ").append(cls.simpleName()).append("(MemorySegment MEMORY) {\n");
            if (cls.superTypeRef != null) {
                Utils.appendIndent(sb, 8)
                    .append("super(MEMORY);\n");
            }
            Utils.appendIndent(sb, 8)
                .append("MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());\n");
            Utils.appendIndent(sb, 8)
                .append("this.MEMORY = MEMORY;\n");
            Utils.appendIndent(sb, 8)
                .append("long OFFSET = 0;\n");
            if (cls.superTypeRef != null) {
                Utils.appendIndent(sb, 8)
                    .append("OFFSET += ")
                    .append(((ClassTypeInfo) cls.superTypeRef).getClazz().fullName())
                    .append(".LAYOUT.byteSize();\n");
            }
            if (cls.headPadding > 0) {
                Utils.appendIndent(sb, 8)
                    .append("OFFSET += ")
                    .append(cls.headPadding)
                    .append("; // head padding\n");
            }
            for (var f : cls.fields) {
                get(f).generateJavaConstructor(sb, 8);
                if (cls.isUnion()) {
                    Utils.appendIndent(sb, 8).append("OFFSET = 0;\n");
                }
            }
            Utils.appendIndent(sb, 4).append("}\n");
            sb.append("\n");
            Utils.appendIndent(sb, 4)
                .append("public ").append(cls.simpleName()).append("(Allocator ALLOCATOR) {\n");
            Utils.appendIndent(sb, 8)
                .append("this(ALLOCATOR.allocate(LAYOUT));\n");
            Utils.appendIndent(sb, 4).append("}\n");
        }

        for (var m : cls.methods) {
            sb.append("\n");
            get(m).generateJava(sb, 4, cls.underlinedName(), !cls.isInterface);
        }

        if (!cls.isInterface) {
            sb.append("\n");
            generateJavaToString(sb, 4);
            sb.append("\n");
            sb.append("    public static class Array extends RefArray<").append(cls.simpleName()).append("> {\n");
            sb.append("        public Array(MemorySegment buf) {\n");
            sb.append("            super(buf, ").append(cls.simpleName()).append(".LAYOUT);\n");
            sb.append("        }\n");
            sb.append("\n");
            sb.append("        public Array(Allocator allocator, long len) {\n");
            sb.append("            super(allocator, ").append(cls.simpleName()).append(".LAYOUT, len);\n");
            sb.append("        }\n");
            sb.append("\n");
            sb.append("        public Array(PNIBuf buf) {\n");
            sb.append("            super(buf, ").append(cls.simpleName()).append(".LAYOUT);\n");
            sb.append("        }\n");
            sb.append("\n");
            generateJavaArrayToString(sb, 8);
            sb.append("\n");
            generateOverrideConstructAndGetSegment(sb);
            sb.append("    }\n");
            sb.append("\n");
            sb.append("    public static class Func extends PNIFunc<").append(cls.simpleName()).append("> {\n");
            sb.append("        private Func(io.vproxy.pni.CallSite<").append(cls.simpleName()).append("> func) {\n");
            sb.append("            super(func);\n");
            sb.append("        }\n");
            sb.append("\n");
            sb.append("        private Func(io.vproxy.pni.CallSite<").append(cls.simpleName()).append("> func, Options opts) {\n");
            sb.append("            super(func, opts);\n");
            sb.append("        }\n");
            sb.append("\n");
            sb.append("        private Func(MemorySegment MEMORY) {\n");
            sb.append("            super(MEMORY);\n");
            sb.append("        }\n");
            sb.append("\n");
            sb.append("        public static Func of(io.vproxy.pni.CallSite<").append(cls.simpleName()).append("> func) {\n");
            sb.append("            return new Func(func);\n");
            sb.append("        }\n");
            sb.append("\n");
            sb.append("        public static Func of(io.vproxy.pni.CallSite<").append(cls.simpleName()).append("> func, Options opts) {\n");
            sb.append("            return new Func(func, opts);\n");
            sb.append("        }\n");
            sb.append("\n");
            sb.append("        public static Func of(MemorySegment MEMORY) {\n");
            sb.append("            return new Func(MEMORY);\n");
            sb.append("        }\n");
            sb.append("\n");
            generateJavaFuncToString(sb, 8);
            sb.append("\n");
            generateOverrideConstruct(sb);
            sb.append("    }\n");
        }

        sb.append("}\n");
    }

    private void generateJavaToString(StringBuilder sb, int indent) {
        Utils.appendIndent(sb, indent)
            .append("@Override\n");
        Utils.appendIndent(sb, indent)
            .append("public void toString(StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {\n");
        generateJavaToStringBody(sb, indent);
        Utils.appendIndent(sb, indent).append("}\n");
    }

    private void generateJavaToStringBody(StringBuilder sb, int indent) {
        Utils.appendIndent(sb, indent + 4)
            .append("if (!VISITED.add(new NativeObjectTuple(this))) {\n");
        Utils.appendIndent(sb, indent + 8)
            .append("SB.append(\"<...>@\").append(Long.toString(MEMORY.address(), 16));\n");
        Utils.appendIndent(sb, indent + 8)
            .append("return;\n");
        Utils.appendIndent(sb, indent + 4)
            .append("}\n");
        if (cls.isUnion()) {
            Utils.appendIndent(sb, indent + 4)
                .append("CORRUPTED_MEMORY = true;\n");
        }
        generateJavaToStringBody0(sb, indent);
    }

    private void generateJavaToStringBody0(StringBuilder sb, int indent) {
        Utils.appendIndent(sb, indent + 4).append("SB.append(\"").append(cls.simpleName());
        if (cls.isUnion()) {
            sb.append("(\\n");
        } else {
            sb.append("{\\n");
        }
        sb.append("\");\n");
        if (cls.superTypeRef != null) {
            Utils.appendIndent(sb, indent + 4)
                .append("SB.append(\" \".repeat(INDENT + 4)).append(\"SUPER => \");\n");
            Utils.appendIndent(sb, indent + 4).append("{\n");
            Utils.appendIndent(sb, indent + 8).append("INDENT += 4;\n");
            var parent = ((ClassTypeInfo) cls.superTypeRef).getClazz();
            new JavaFileGenerator(parent, opts)
                .generateJavaToStringBody0(sb, indent + 4);
            Utils.appendIndent(sb, indent + 8).append("INDENT -= 4;\n");
            if (cls.fields.isEmpty()) {
                Utils.appendIndent(sb, indent + 8)
                    .append("SB.append(\"\\n\");\n");
                sb.append("\n");
            } else {
                Utils.appendIndent(sb, indent + 8)
                    .append("SB.append(\",\\n\");\n");
            }
            Utils.appendIndent(sb, indent + 4).append("}\n");
        }
        for (int i = 0; i < cls.fields.size(); i++) {
            var f = cls.fields.get(i);
            get(f).generateJavaToString(sb, indent + 4);
            if (i < cls.fields.size() - 1) {
                Utils.appendIndent(sb, indent + 4).append("SB.append(\",\\n\");\n");
            } else {
                Utils.appendIndent(sb, indent + 4).append("SB.append(\"\\n\");\n");
            }
        }
        Utils.appendIndent(sb, indent + 4)
            .append("SB.append(\" \".repeat(INDENT)).append(\"");
        if (cls.isUnion()) {
            sb.append(")");
        } else {
            sb.append("}");
        }
        sb.append("@\").append(Long.toString(MEMORY.address(), 16));\n");
    }

    private void generateJavaArrayToString(StringBuilder sb, int indent) {
        Utils.appendIndent(sb, indent).append("@Override\n");
        Utils.appendIndent(sb, indent)
            .append("protected void elementToString(")
            .append(cls.fullName())
            .append(" ELEM, StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {\n");
        Utils.appendIndent(sb, indent + 4)
            .append("ELEM.toString(SB, INDENT, VISITED, CORRUPTED_MEMORY);\n");
        Utils.appendIndent(sb, indent).append("}\n");
        sb.append("\n");
        Utils.appendIndent(sb, indent).append("@Override\n");
        Utils.appendIndent(sb, indent)
            .append("protected String toStringTypeName() {\n");
        Utils.appendIndent(sb, indent + 4)
            .append("return \"").append(cls.simpleName()).append(".Array\";\n");
        Utils.appendIndent(sb, indent).append("}\n");
    }

    private void generateJavaFuncToString(StringBuilder sb, int indent) {
        Utils.appendIndent(sb, indent).append("@Override\n");
        Utils.appendIndent(sb, indent)
            .append("protected String toStringTypeName() {\n");
        Utils.appendIndent(sb, indent + 4)
            .append("return \"").append(cls.simpleName()).append(".Func\";\n");
        Utils.appendIndent(sb, indent).append("}\n");
    }

    private void generateOverrideConstructAndGetSegment(StringBuilder sb) {
        sb.append("        @Override\n");
        sb.append("        protected ").append(cls.simpleName()).append(" construct(MemorySegment seg) {\n");
        sb.append("            return new ").append(cls.simpleName()).append("(seg);\n");
        sb.append("        }\n");
        sb.append("\n");
        sb.append("        @Override\n");
        sb.append("        protected MemorySegment getSegment(").append(cls.simpleName()).append(" value) {\n");
        sb.append("            return value.MEMORY;\n");
        sb.append("        }\n");
    }

    private void generateOverrideConstruct(StringBuilder sb) {
        sb.append("        @Override\n");
        sb.append("        protected ").append(cls.simpleName()).append(" construct(MemorySegment seg) {\n");
        sb.append("            return new ").append(cls.simpleName()).append("(seg);\n");
        sb.append("        }\n");
    }

    private void generateJavaUpcall(StringBuilder sb) {
        sb.append("public class ").append(cls.simpleName()).append(" {\n");
        sb.append("    private static final Arena ARENA = Arena.ofShared();\n");

        for (var m : cls.methods) {
            get(m).generateJavaUpcall(sb, 4, cls.fullName());
        }

        sb.append("\n");
        if (opts.hasCompilationFlag(CompilationFlag.GRAAL_C_ENTRYPOINT_LITERAL_UPCALL)) {
            sb.append("    private static void setNativeImpl() {\n");
        } else {
            sb.append("    static {\n");
        }
        generateUpcallSetNativeImpl(sb, 8);
        sb.append("    }\n");

        sb.append("\n");
        sb.append("    private static Interface IMPL = null;\n");
        sb.append("\n");
        sb.append("    public static void setImpl(Interface impl) {\n");
        sb.append("        java.util.Objects.requireNonNull(impl);\n");
        sb.append("        IMPL = impl;\n");
        if (opts.hasCompilationFlag(CompilationFlag.GRAAL_C_ENTRYPOINT_LITERAL_UPCALL)) {
            sb.append("        setNativeImpl();\n");
        }
        sb.append("    }\n");

        sb.append("\n");
        sb.append("    public interface Interface {\n");

        boolean isFirst = true;
        for (var m : cls.methods) {
            if (isFirst) {
                isFirst = false;
            } else {
                sb.append("\n");
            }
            Utils.appendIndent(sb, 8);
            get(m).generateJavaUpcallInterfaceMethod(sb);
        }

        sb.append("    }\n");

        sb.append("}\n");
    }

    private void generateUpcallSetNativeImplMethodHandles(StringBuilder sb, int indent) {
        for (var m : cls.methods) {
            Utils.appendIndent(sb, indent)
                .append("MethodHandle ").append(m.name).append("MH;\n");
        }
        Utils.appendIndent(sb, indent)
            .append("try {\n");
        for (var m : cls.methods) {
            Utils.appendIndent(sb, indent + 4)
                .append(m.name).append("MH = ");
            get(m).generateUpcallMethodHandle(sb, cls.fullName());
            sb.append(";\n");
        }
        Utils.appendIndent(sb, indent)
            .append("} catch (Throwable t) {\n");
        Utils.appendIndent(sb, indent + 4)
            .append("throw new RuntimeException(t);\n");
        Utils.appendIndent(sb, indent).append("}\n");
    }

    private void generateUpcallSetNativeImpl(StringBuilder sb, @SuppressWarnings("SameParameterValue") int indent) {
        if (!opts.hasCompilationFlag(CompilationFlag.GRAAL_C_ENTRYPOINT_LITERAL_UPCALL)) {
            generateUpcallSetNativeImplMethodHandles(sb, indent);
        }

        for (var m : cls.methods) {
            if (opts.hasCompilationFlag(CompilationFlag.GRAAL_C_ENTRYPOINT_LITERAL_UPCALL)) {
                Utils.appendIndent(sb, indent)
                    .append(m.name).append(" = MemorySegment.ofAddress(")
                    .append(m.name).append("CEPL").append(".getFunctionPointer().rawValue());\n");
            } else {
                Utils.appendIndent(sb, indent)
                    .append(m.name).append(" = ");
                get(m).generateUpcallStub(sb);
                sb.append(";\n");
            }
        }

        sb.append("\n");
        Utils.appendIndent(sb, indent)
            .append("var initMH = PanamaUtils.lookupPNICriticalFunction(true, void.class, ")
            .append("\"JavaCritical_").append(cls.underlinedName()).append("_INIT\"");
        //noinspection unused
        for (var m : cls.methods) {
            sb.append(", MemorySegment.class");
        }
        sb.append(");\n");
        Utils.appendIndent(sb, indent)
            .append("try {\n");
        Utils.appendIndent(sb, indent + 4)
            .append("initMH.invoke(");
        boolean isFirst = true;
        for (var m : cls.methods) {
            if (isFirst) {
                isFirst = false;
            } else {
                sb.append(", ");
            }
            sb.append(m.name);
        }
        sb.append(");\n");
        Utils.appendIndent(sb, indent)
            .append("} catch (Throwable t) {\n");
        Utils.appendIndent(sb, indent + 4)
            .append("throw new RuntimeException(t);\n");
        Utils.appendIndent(sb, indent)
            .append("}\n");

        if (opts.hasCompilationFlag(CompilationFlag.GRAAL_C_ENTRYPOINT_LITERAL_UPCALL)) {
            generateGraalUpcallSetNativeImpl(sb, indent);
        }
    }

    private void generateGraalUpcallSetNativeImpl(StringBuilder sb, int indent) {
        for (var m : cls.methods) {
            Utils.appendIndent(sb, indent)
                .append(m.name).append(" = PanamaUtils.lookupFunctionPointer(\"")
                .append(m.nativeName(cls.underlinedName(), true))
                .append("\").orElseThrow(() -> new NullPointerException(\"")
                .append(m.nativeName(cls.underlinedName(), true))
                .append("\"));\n");
        }
    }

    private final Map<AstField, FieldGenerator> fieldGenerators = new HashMap<>();

    private FieldGenerator get(AstField f) {
        return fieldGenerators.computeIfAbsent(f, FieldGenerator::new);
    }

    private static class FieldGenerator {
        private final AstField field;

        private FieldGenerator(AstField field) {
            this.field = field;
        }

        private void generateJavaLayout(StringBuilder sb, int indent, boolean alwaysAligned) {
            Utils.appendIndent(sb, indent);
            var layout = field.typeRef.memoryLayoutForField(field.varOpts());
            if (layout.contains("_UNALIGNED")) {
                if (alwaysAligned || field.isAlwaysAligned()) {
                    layout = layout.replace("_UNALIGNED", "");
                }
            }
            sb.append(layout)
                .append(".withName(\"").append(field.name).append("\")");
            if (field.padding > 0) {
                sb.append(",\n");
                Utils.appendJavaPadding(sb, indent, field.padding);
            }
        }

        private void generateJavaGetterSetter(StringBuilder sb, int indent) {
            field.typeRef.generateGetterSetter(sb, indent, field.name, field.varOpts());
        }

        private void generateJavaBitFieldGetterSetter(StringBuilder sb, int indent, BitFieldInfo bitfield) {
            field.typeRef.generateBitFieldGetterSetter(sb, indent, field.name, bitfield, field.varOpts());
        }

        private void generateJavaConstructor(StringBuilder sb, int indent) {
            field.typeRef.generateConstructor(sb, indent, field.name, field.varOpts());
            if (field.padding > 0) {
                Utils.appendIndent(sb, indent).append("OFFSET += ").append(field.padding).append("; /* padding */\n");
            }
        }

        public void generateJavaToString(StringBuilder sb, int indent) {
            Utils.appendIndent(sb, indent).append("{\n");
            var bits = field.getBitFieldInfo();
            Utils.appendIndent(sb, indent + 4)
                .append("SB.append(\" \".repeat(INDENT + 4))")
                .append(".append(\"").append(field.name).append(" => \");\n");
            field.typeRef.javaToString(sb, indent + 4, Utils.getterName(field.name) + "()", field.varOpts());
            if (bits != null && !bits.isEmpty()) {
                Utils.appendIndent(sb, indent + 4)
                    .append("SB.append(\" {\\n\");\n");
                for (int i = 0; i < bits.size(); i++) {
                    var b = bits.get(i);
                    Utils.appendIndent(sb, indent + 4)
                        .append("SB.append(\" \".repeat(INDENT + 8))")
                        .append(".append(\"").append(b.name).append(":").append(b.bit).append(" => \")")
                        .append(".append(").append(Utils.getterName(b.name)).append("());\n");
                    if (i == bits.size() - 1) {
                        Utils.appendIndent(sb, indent + 4)
                            .append("SB.append(\"\\n\");\n");
                    } else {
                        Utils.appendIndent(sb, indent + 4)
                            .append("SB.append(\",\\n\");\n");
                    }
                }
                Utils.appendIndent(sb, indent + 4)
                    .append("SB.append(\" \".repeat(INDENT + 4))")
                    .append(".append(\"}\");\n");
            }
            Utils.appendIndent(sb, indent).append("}\n");
        }
    }

    private final Map<AstMethod, MethodGenerator> methodGenerators = new HashMap<>();

    private MethodGenerator get(AstMethod m) {
        return methodGenerators.computeIfAbsent(m, MethodGenerator::new);
    }

    private class MethodGenerator {
        private final AstMethod method;

        private MethodGenerator(AstMethod method) {
            this.method = method;
        }

        private void generateJava(StringBuilder sb, int indent, String classUnderlinedName, boolean needSelf) {
            generateJava(sb, indent, classUnderlinedName, needSelf, false, false);
        }

        private void generateJava(StringBuilder sb, int indent, String classUnderlinedName, boolean needSelf, boolean isStatic, boolean isPrivate) {
            Utils.appendIndent(sb, indent).append("private static final MethodHandle ").append(method.name).append("MH").append(" = PanamaUtils.");
            if (method.critical()) {
                sb.append("lookupPNICriticalFunction(");
            } else {
                sb.append("lookupPNIFunction(");
            }
            if (method.trivial()) {
                sb.append("true, ");
            } else {
                sb.append("false, ");
            }
            if (method.critical()) {
                sb.append(method.returnTypeRef.methodHandleTypeForReturn(method.varOptsForReturn()));
                sb.append(", ");
            }
            sb.append("\"").append(method.nativeName(classUnderlinedName)).append("\"");
            if (needSelf) {
                sb.append(", MemorySegment.class /* self */");
            }
            for (var p : method.params) {
                sb.append(", ");
                get(p).generateMethodHandle(sb, 0);
                sb.append(" /* ").append(p.name).append(" */");
            }
            var returnAllocation = method.returnTypeRef.allocationInfoForReturnValue(method.varOptsForReturn());
            if (returnAllocation.requireAllocator() && !method.noAlloc()) {
                sb.append(", MemorySegment.class /* return */");
            }
            sb.append(");\n");
            sb.append("\n");

            Utils.appendIndent(sb, indent);
            if (isPrivate) {
                sb.append("private ");
            } else {
                sb.append("public ");
            }
            if (isStatic) {
                sb.append("static ");
            }
            if (!method.genericDefs.isEmpty()) {
                sb.append("<");
                var isFirst = true;
                for (var g : method.genericDefs) {
                    if (isFirst) {
                        isFirst = false;
                    } else {
                        sb.append(", ");
                    }
                    sb.append(g);
                }
                sb.append("> ");
            }
            sb.append(method.returnTypeRef.javaTypeForReturn(method.varOptsForReturn()))
                .append(" ").append(method.name).append("(");
            var isFirst = true;
            if (!method.critical()) {
                isFirst = false;
                sb.append("PNIEnv ENV");
            }
            var paramNeedsAllocator = returnAllocation.requirePooledAllocator();
            for (var p : method.params) {
                if (isFirst) {
                    isFirst = false;
                } else {
                    sb.append(", ");
                }
                get(p).generateParam(sb, 0);
                var paramOpts = p.paramOpts();
                if (paramOpts.isDependOnAllocator()) {
                    paramNeedsAllocator = true;
                }
            }
            if (returnAllocation.requireExtraParameterForJavaDowncall() && !method.noAlloc()) {
                if (!isFirst) {
                    sb.append(", ");
                }
                isFirst = false;
                sb.append("Allocator ALLOCATOR");
            }
            sb.append(")");
            if (!method.throwTypeRefs.isEmpty()) {
                sb.append(" throws ");
                isFirst = true;
                for (var t : method.throwTypeRefs) {
                    if (isFirst) {
                        isFirst = false;
                    } else {
                        sb.append(", ");
                    }
                    sb.append(t.name());
                }
            }
            sb.append(" {\n");
            if (!method.critical()) {
                Utils.appendIndent(sb, indent + 4).append("ENV.reset();\n");
            }
            int invocationIndent = indent + 4;
            if (paramNeedsAllocator) {
                Utils.appendIndent(sb, indent + 4).append("try (var POOLED = Allocator.ofPooled()) {\n");
                invocationIndent += 4;
            }
            if (method.critical()) {
                if (!(method.returnTypeRef instanceof VoidTypeInfo)) {
                    Utils.appendIndent(sb, invocationIndent);
                    if (method.returnTypeRef instanceof PrimitiveTypeInfo) {
                        sb.append(method.returnTypeRef.javaTypeForReturn(method.varOptsForReturn()));
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
            if (method.critical()) {
                if (!(method.returnTypeRef instanceof VoidTypeInfo)) {
                    sb.append("RESULT = (");
                    if (method.returnTypeRef instanceof PrimitiveTypeInfo) {
                        sb.append(method.returnTypeRef.javaTypeForReturn(method.varOptsForReturn()));
                    } else {
                        sb.append("MemorySegment");
                    }
                    sb.append(") ");
                }
            } else {
                sb.append("ERR = (int) ");
            }
            sb.append(method.name).append("MH").append(".invokeExact(");
            isFirst = true;
            if (!method.critical()) {
                isFirst = false;
                sb.append("ENV.MEMORY");
            }
            if (needSelf) {
                if (!isFirst) {
                    sb.append(", ");
                }
                isFirst = false;
                sb.append("MEMORY");
            }
            for (var p : method.params) {
                if (isFirst) {
                    isFirst = false;
                } else {
                    sb.append(", ");
                }
                get(p).generateConvert(sb, 0);
            }
            if (returnAllocation.requireExtraParameterForJavaDowncall() && !method.noAlloc()) {
                if (!isFirst) {
                    sb.append(", ");
                }
                isFirst = false;
                sb.append("ALLOCATOR.allocate(").append(returnAllocation.layout()).append(")");
            } else if (returnAllocation.requirePooledAllocator()) {
                if (!isFirst) {
                    sb.append(", ");
                }
                isFirst = false;
                sb.append("POOLED.allocate(").append(returnAllocation.layout()).append(")");
            }
            sb.append(");\n");
            Utils.appendIndent(sb, invocationIndent)
                .append("} catch (Throwable THROWABLE) {\n");
            Utils.appendIndent(sb, invocationIndent + 4)
                .append("throw PanamaUtils.convertInvokeExactException(THROWABLE);\n");
            Utils.appendIndent(sb, invocationIndent)
                .append("}\n");
            if (!method.critical()) {
                Utils.appendIndent(sb, invocationIndent)
                    .append("if (ERR != 0) {\n");
                for (var t : method.throwTypeRefs) {
                    Utils.appendIndent(sb, invocationIndent + 4)
                        .append("ENV.throwIf(").append(t.name()).append(".class);\n");
                }
                Utils.appendIndent(sb, invocationIndent + 4).append("ENV.throwLast();\n");
                Utils.appendIndent(sb, invocationIndent).append("}\n");
            }
            method.returnTypeRef.convertInvokeExactReturnValueToJava(sb, invocationIndent, method.varOptsForReturn());
            if (paramNeedsAllocator) {
                Utils.appendIndent(sb, indent + 4).append("}\n");
            }
            Utils.appendIndent(sb, indent).append("}\n");
        }

        private void generateJavaUpcall(StringBuilder sb, int indent, String classFullName) {
            sb.append("\n");
            Utils.appendIndent(sb, indent).append("public static");
            if (!opts.hasCompilationFlag(CompilationFlag.GRAAL_C_ENTRYPOINT_LITERAL_UPCALL)) {
                sb.append(" final");
            }
            sb.append(" MemorySegment ").append(method.name).append(";\n");
            if (opts.hasCompilationFlag(CompilationFlag.GRAAL_C_ENTRYPOINT_LITERAL_UPCALL)) {
                Utils.appendIndent(sb, indent).append("public static final CEntryPointLiteral<CFunctionPointer> ")
                    .append(method.name).append("CEPL = GraalUtils.defineCFunctionByName(")
                    .append(cls.fullName()).append(".class, \"").append(method.name).append("\");\n");
            }
            sb.append("\n");

            if (opts.hasCompilationFlag(CompilationFlag.GRAAL_C_ENTRYPOINT_LITERAL_UPCALL)) {
                Utils.appendIndent(sb, indent).append("@CEntryPoint\n");
                Utils.appendIndent(sb, indent).append("public static ");
            } else {
                Utils.appendIndent(sb, indent).append("private static ");
            }
            var returnType = method.returnTypeRef.javaTypeForUpcallReturn(method.varOptsForReturn(true));
            if (opts.hasCompilationFlag(CompilationFlag.GRAAL_C_ENTRYPOINT_LITERAL_UPCALL) && returnType.equals("MemorySegment")) {
                returnType = "VoidPointer";
            }
            sb.append(returnType).append(" ").append(method.name).append("(");

            var voidPointerParams = new ArrayList<String>();

            var isFirst = true;
            if (opts.hasCompilationFlag(CompilationFlag.GRAAL_C_ENTRYPOINT_LITERAL_UPCALL)) {
                isFirst = false;
                sb.append("IsolateThread THREAD");
            }
            for (var p : method.params) {
                if (isFirst) {
                    isFirst = false;
                } else {
                    sb.append(", ");
                }
                var isVoidPointer = get(p).generateUpcallParam(sb, 0);
                if (isVoidPointer) {
                    voidPointerParams.add(p.name);
                }
            }
            var returnAllocation = method.returnTypeRef.allocationInfoForReturnValue(method.varOptsForReturn(true));
            var interfaceReturnAllocation = method.returnTypeRef.allocationInfoForUpcallInterfaceReturnValue(method.varOptsForReturn(true));
            if (returnAllocation.requireAllocator() && !method.noAlloc()) {
                if (isFirst) {
                    isFirst = false;
                } else {
                    sb.append(", ");
                }
                if (opts.hasCompilationFlag(CompilationFlag.GRAAL_C_ENTRYPOINT_LITERAL_UPCALL)) {
                    sb.append("VoidPointer return_PTR");
                    voidPointerParams.add("return_");
                } else {
                    sb.append("MemorySegment return_");
                }
            }
            sb.append(") {\n");
            Utils.appendIndent(sb, indent + 4)
                .append("if (IMPL == null) {\n");
            Utils.appendIndent(sb, indent + 8)
                .append("System.out.println(\"").append(classFullName).append("#").append(method.name).append("\");\n");
            Utils.appendIndent(sb, indent + 8)
                .append("System.exit(1);\n");
            Utils.appendIndent(sb, indent + 4).append("}\n");

            if (opts.hasCompilationFlag(CompilationFlag.GRAAL_C_ENTRYPOINT_LITERAL_UPCALL)) {
                for (var p : voidPointerParams) {
                    Utils.appendIndent(sb, indent + 4)
                        .append("var ").append(p).append(" = MemorySegment.ofAddress(")
                        .append(p).append("PTR").append(".rawValue());\n");
                }
            }

            Utils.appendIndent(sb, indent + 4);
            if (!(method.returnTypeRef instanceof VoidTypeInfo)) {
                sb.append("var RESULT = ");
            }
            sb.append("IMPL.").append(method.name).append("(");
            isFirst = true;
            for (int i = 0; i < method.params.size(); i++) {
                if (isFirst) {
                    isFirst = false;
                } else {
                    sb.append(",");
                }
                sb.append("\n");
                var p = method.params.get(i);
                get(p).generateUpcallConvert(sb, indent + 8);
            }
            if (interfaceReturnAllocation.requireAllocator() && !method.noAlloc()) {
                if (!isFirst) {
                    sb.append(",");
                }
                isFirst = false;
                sb.append("\n");
                Utils.appendIndent(sb, indent + 8)
                    .append(method.returnTypeRef.convertExtraToUpcallArgument("return_", method.varOptsForReturn(true)))
                    .append("\n");
                Utils.appendIndent(sb, indent + 4).append(");\n");
            } else if (!method.params.isEmpty()) {
                sb.append("\n");
                Utils.appendIndent(sb, indent + 4).append(");\n");
            } else {
                sb.append(");\n");
            }
            if (!(method.returnTypeRef instanceof VoidTypeInfo)) {
                if (opts.hasCompilationFlag(CompilationFlag.GRAAL_C_ENTRYPOINT_LITERAL_UPCALL)) {
                    method.returnTypeRef.convertFromUpcallReturnGraal(sb, indent + 4, method.varOptsForReturn(true));
                } else {
                    method.returnTypeRef.convertFromUpcallReturn(sb, indent + 4, method.varOptsForReturn(true));
                }
            }
            Utils.appendIndent(sb, indent).append("}\n");
        }

        private void generateJavaUpcallInterfaceMethod(StringBuilder sb) {
            if (!method.genericDefs.isEmpty()) {
                sb.append("<");
                var isFirst = true;
                for (var g : method.genericDefs) {
                    if (isFirst) {
                        isFirst = false;
                    } else {
                        sb.append(", ");
                    }
                    sb.append(g);
                }
                sb.append("> ");
            }
            sb.append(method.returnTypeRef.javaTypeForUpcallInterfaceReturn(method.varOptsForReturn(true)))
                .append(" ").append(method.name).append("(");
            var isFirst = true;
            for (var p : method.params) {
                if (isFirst) {
                    isFirst = false;
                } else {
                    sb.append(", ");
                }
                get(p).generateUpcallInterfaceParam(sb, 0);
            }
            var returnAllocation = method.returnTypeRef.allocationInfoForUpcallInterfaceReturnValue(method.varOptsForReturn(true));
            if (returnAllocation.requireAllocator() && !method.noAlloc()) {
                if (!isFirst) {
                    sb.append(", ");
                }
                isFirst = false;
                sb.append(method.returnTypeRef.javaTypeForExtraUpcallInterfaceParam(VarOpts.paramDefault())).append(" ").append("return_");
            }
            sb.append(");\n");
        }

        private void generateUpcallMethodHandle(StringBuilder sb, String classFullName) {
            sb.append("MethodHandles.lookup().findStatic(").append(classFullName).append(".class, ")
                .append("\"").append(method.name).append("\", ")
                .append("MethodType.methodType(");
            if (method.returnTypeRef instanceof VoidTypeInfo) {
                sb.append("void.class");
            } else {
                sb.append(method.returnTypeRef.javaTypeForUpcallReturn(method.varOptsForReturn(true))).append(".class");
            }
            for (var p : method.params) {
                sb.append(", ");
                get(p).generateUpcallParamClass(sb, 0);
            }
            if (method.returnTypeRef.allocationInfoForReturnValue(method.varOptsForReturn(true)).requireAllocator()
                && !method.noAlloc()) {
                sb.append(", ");
                sb.append("MemorySegment.class");
            }
            sb.append("))");
        }

        private void generateUpcallStub(StringBuilder sb) {
            sb.append("PanamaUtils.defineCFunction(ARENA, ")
                .append(method.name).append("MH, ");
            if (method.returnTypeRef instanceof VoidTypeInfo) {
                sb.append("void.class");
            } else {
                sb.append(method.returnTypeRef.methodHandleTypeForUpcall(method.varOptsForReturn(true)));
            }
            for (var p : method.params) {
                sb.append(", ");
                get(p).generateMethodHandleForUpcall(sb, 0);
            }
            if (method.returnTypeRef.allocationInfoForReturnValue(method.varOptsForReturn(true)).requireAllocator()
                && !method.noAlloc()) {
                sb.append(", ");
                sb.append("MemorySegment.class");
            }
            sb.append(")");
        }

        private final Map<AstParam, ParamGenerator> paramGenerators = new HashMap<>();

        private ParamGenerator get(AstParam p) {
            return paramGenerators.computeIfAbsent(p, ParamGenerator::new);
        }

        private class ParamGenerator {
            private final AstParam param;

            private ParamGenerator(AstParam param) {
                this.param = param;
            }

            private void generateParam(StringBuilder sb, int indent) {
                Utils.appendIndent(sb, indent);
                sb.append(param.typeRef.javaTypeForParam(param.varOpts())).append(" ").append(param.name);
            }

            /**
             * @return true if it's using the Graal VoidPointer, which requires further transformation
             */
            private boolean generateUpcallParam(StringBuilder sb, int indent) {
                Utils.appendIndent(sb, indent);
                var type = param.typeRef.javaTypeForUpcallParam(param.varOpts());
                var name = param.name;
                var isVoidPointer = false;
                if (opts.hasCompilationFlag(CompilationFlag.GRAAL_C_ENTRYPOINT_LITERAL_UPCALL) && type.equals("MemorySegment")) {
                    type = "VoidPointer";
                    name = name + "PTR";
                    isVoidPointer = true;
                }
                sb.append(type).append(" ").append(name);
                return isVoidPointer;
            }

            private void generateUpcallParamClass(StringBuilder sb, int indent) {
                Utils.appendIndent(sb, indent);
                sb.append(param.typeRef.javaTypeForUpcallParam(param.varOpts())).append(".class");
            }

            private void generateUpcallInterfaceParam(StringBuilder sb, int indent) {
                Utils.appendIndent(sb, indent);
                sb.append(param.typeRef.javaTypeForUpcallInterfaceParam(param.varOpts())).append(" ").append(param.name);
            }

            private void generateMethodHandle(StringBuilder sb, int indent) {
                Utils.appendIndent(sb, indent);
                sb.append(param.typeRef.methodHandleType(param.varOpts()));
            }

            private void generateMethodHandleForUpcall(StringBuilder sb, int indent) {
                Utils.appendIndent(sb, indent);
                sb.append(param.typeRef.methodHandleTypeForUpcall(param.varOpts()));
            }

            private void generateConvert(StringBuilder sb, int indent) {
                Utils.appendIndent(sb, indent);
                sb.append(param.typeRef.convertParamToInvokeExactArgument(param.name, param.varOpts()));
            }

            private void generateUpcallConvert(StringBuilder sb, int indent) {
                Utils.appendIndent(sb, indent);
                sb.append(param.typeRef.convertToUpcallArgument(param.name, param.varOpts()));
            }
        }
    }
}
