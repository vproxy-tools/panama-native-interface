package io.vproxy.pni.exec.generator;

import io.vproxy.pni.exec.CompilerOptions;
import io.vproxy.pni.exec.Main;
import io.vproxy.pni.exec.ast.*;
import io.vproxy.pni.exec.internal.Utils;
import io.vproxy.pni.exec.internal.VarOpts;
import io.vproxy.pni.exec.type.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("SameParameterValue")
public class JavaFileGenerator {
    private final AstClass cls;

    public JavaFileGenerator(AstClass cls) {
        this.cls = cls;
    }

    public void flush(File baseDir, CompilerOptions opts) {
        var javaCode = generateJava();
        var hash = Utils.sha256(javaCode);
        javaCode += Utils.metadata(opts, Main.JAVA_GEN_VERSION);
        javaCode += "// sha256:" + hash + "\n";
        var file = Utils.ensureJavaFile(baseDir, cls.fullName());
        if (Utils.hashesAreTheSame(file, hash)) {
            if (opts.isVerbose()) {
                System.out.println("skipping java file because nothing changed: " + file.getAbsolutePath());
            }
            return;
        }
        if (opts.isVerbose()) {
            System.out.println("writing generated java file: " + file.getAbsolutePath());
        }
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
            sb.append("\n    );\n");
            sb.append("    public final MemorySegment MEMORY;\n");
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
                .append("this(ALLOCATOR.allocate(LAYOUT.byteSize()));\n");
            Utils.appendIndent(sb, 4).append("}\n");
        }

        for (var m : cls.methods) {
            sb.append("\n");
            get(m).generateJava(sb, 4, cls.underlinedName(), !cls.isInterface);
        }

        if (!cls.isInterface) {
            sb.append("\n");
            sb.append("    public static class Array extends RefArray<").append(cls.simpleName()).append("> {\n");
            sb.append("        public Array(MemorySegment buf) {\n");
            sb.append("            super(buf, ").append(cls.simpleName()).append(".LAYOUT);\n");
            sb.append("        }\n");
            sb.append("\n");
            sb.append("        public Array(Allocator allocator, long len) {\n");
            sb.append("            this(allocator.allocate(").append(cls.simpleName()).append(".LAYOUT.byteSize() * len));\n");
            sb.append("        }\n");
            sb.append("\n");
            sb.append("        public Array(PNIBuf buf) {\n");
            sb.append("            this(buf.get());\n");
            sb.append("        }\n");
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
            generateOverrideConstruct(sb);
            sb.append("    }\n");
        }

        sb.append("}\n");
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
        sb.append("    static {\n");
        generateUpcallStatic(sb, 8);
        sb.append("    }\n");

        sb.append("\n");
        sb.append("    private static Interface IMPL = null;\n");
        sb.append("\n");
        sb.append("    public static void setImpl(Interface impl) {\n");
        sb.append("        java.util.Objects.requireNonNull(impl);\n");
        sb.append("        IMPL = impl;\n");
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

    private void generateUpcallStatic(StringBuilder sb, @SuppressWarnings("SameParameterValue") int indent) {
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

        for (var m : cls.methods) {
            Utils.appendIndent(sb, indent)
                .append(m.name).append(" = ");
            get(m).generateUpcallStub(sb);
            sb.append(";\n");
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
            if (layout.endsWith("_UNALIGNED")) {
                if (alwaysAligned || field.isAlwaysAligned()) {
                    layout = layout.substring(0, layout.length() - "_UNALIGNED".length());
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
    }

    private final Map<AstMethod, MethodGenerator> methodGenerators = new HashMap<>();

    private MethodGenerator get(AstMethod m) {
        return methodGenerators.computeIfAbsent(m, MethodGenerator::new);
    }

    private static class MethodGenerator {
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
            if (returnAllocation.requireAllocator()) {
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
            if (!method.critical()) {
                sb.append("PNIEnv ENV");
                if (!method.params.isEmpty()) {
                    sb.append(", ");
                }
            }
            var paramNeedsAllocator = returnAllocation.requirePooledAllocator();
            var isFirst = true;
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
            if (returnAllocation.requireExtraParameter()) {
                if (!method.critical() || !method.params.isEmpty()) {
                    sb.append(", ");
                }
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
            if (!method.critical()) {
                sb.append("ENV.MEMORY");
                if (!method.params.isEmpty() || needSelf) {
                    sb.append(", ");
                }
            }
            if (needSelf) {
                sb.append("MEMORY");
                if (!method.params.isEmpty()) {
                    sb.append(", ");
                }
            }
            isFirst = true;
            for (var p : method.params) {
                if (isFirst) {
                    isFirst = false;
                } else {
                    sb.append(", ");
                }
                get(p).generateConvert(sb, 0);
            }
            if (returnAllocation.requireExtraParameter()) {
                if (!method.critical() || needSelf || !method.params.isEmpty()) {
                    sb.append(", ");
                }
                sb.append("ALLOCATOR.allocate(").append(returnAllocation.byteSize()).append(")");
            } else if (returnAllocation.requirePooledAllocator()) {
                if (!method.critical() || needSelf || !method.params.isEmpty()) {
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
            Utils.appendIndent(sb, indent).append("public static final MemorySegment ").append(method.name).append(";\n");
            sb.append("\n");
            Utils.appendIndent(sb, indent).append("private static ");
            sb.append(method.returnTypeRef.javaTypeForUpcallReturn(method.varOptsForReturn(true)))
                .append(" ").append(method.name).append("(");
            var isFirst = true;
            for (var p : method.params) {
                if (isFirst) {
                    isFirst = false;
                } else {
                    sb.append(", ");
                }
                get(p).generateUpcallParam(sb, 0);
            }
            var returnAllocation = method.returnTypeRef.allocationInfoForReturnValue(method.varOptsForReturn(true));
            var interfaceReturnAllocation = method.returnTypeRef.allocationInfoForUpcallInterfaceReturnValue(method.varOptsForReturn(true));
            if (returnAllocation.requireAllocator()) {
                if (!method.params.isEmpty()) {
                    sb.append(", ");
                }
                sb.append("MemorySegment return_");
            }
            sb.append(") {\n");
            Utils.appendIndent(sb, indent + 4)
                .append("if (IMPL == null) {\n");
            Utils.appendIndent(sb, indent + 8)
                .append("System.out.println(\"").append(classFullName).append("#").append(method.name).append("\");\n");
            Utils.appendIndent(sb, indent + 8)
                .append("System.exit(1);\n");
            Utils.appendIndent(sb, indent + 4).append("}\n");
            Utils.appendIndent(sb, indent + 4);
            if (!(method.returnTypeRef instanceof VoidTypeInfo)) {
                sb.append("var RESULT = ");
            }
            sb.append("IMPL.").append(method.name).append("(");
            for (int i = 0; i < method.params.size(); i++) {
                var p = method.params.get(i);
                sb.append("\n");
                get(p).generateUpcallConvert(sb, indent + 8);
                if (i < method.params.size() - 1) {
                    sb.append(",");
                }
            }
            if (interfaceReturnAllocation.requireAllocator()) {
                if (!method.params.isEmpty()) {
                    sb.append(",");
                }
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
                method.returnTypeRef.convertFromUpcallReturn(sb, indent + 4, method.varOptsForReturn(true));
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
            if (returnAllocation.requireAllocator()) {
                if (!method.params.isEmpty()) {
                    sb.append(", ");
                }
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
            if (method.returnTypeRef.allocationInfoForReturnValue(method.varOptsForReturn(true)).requireAllocator()) {
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
            if (method.returnTypeRef.allocationInfoForReturnValue(method.varOptsForReturn(true)).requireAllocator()) {
                sb.append(", ");
                sb.append("MemorySegment.class");
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

            private void generateParam(StringBuilder sb, int indent) {
                Utils.appendIndent(sb, indent);
                sb.append(param.typeRef.javaTypeForParam(param.varOpts())).append(" ").append(param.name);
            }

            private void generateUpcallParam(StringBuilder sb, int indent) {
                Utils.appendIndent(sb, indent);
                sb.append(param.typeRef.javaTypeForUpcallParam(param.varOpts())).append(" ").append(param.name);
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