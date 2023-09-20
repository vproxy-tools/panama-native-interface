package io.vproxy.pni.exec.internal;

import io.vproxy.pni.exec.CompilerOptions;
import io.vproxy.pni.exec.Main;
import io.vproxy.pni.exec.ast.AstAnno;
import io.vproxy.pni.exec.ast.AstClass;
import io.vproxy.pni.exec.ast.AstMethod;
import io.vproxy.pni.exec.type.AnnoCriticalTypeInfo;
import io.vproxy.pni.exec.type.AnnoTrivialTypeInfo;
import io.vproxy.pni.exec.type.ClassTypeInfo;
import io.vproxy.pni.exec.type.LongTypeInfo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class JavaFileWriter {
    private final AstClass cls;

    public JavaFileWriter(AstClass cls) {
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

    private String generateJava() {
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

                meth.generateJava(sb, 4, cls.underlinedName(), false, true, true);
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
                f.generateJavaLayout(sb, 8, cls.isAlwaysAligned());
            }
            sb.append("\n    );\n");
            sb.append("    public final MemorySegment MEMORY;\n");
        }

        if (!cls.isInterface) {
            for (var f : cls.fields) {
                sb.append("\n");
                f.generateJavaGetterSetter(sb, 4);
                var bitfields = f.getBitFieldInfo();
                if (bitfields != null) {
                    for (var bitfield : bitfields) {
                        sb.append("\n");
                        f.generateJavaBitFieldGetterSetter(sb, 4, bitfield);
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
                f.generateJavaConstructor(sb, 8);
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
            m.generateJava(sb, 4, cls.underlinedName(), !cls.isInterface);
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
            m.generateJavaUpcall(sb, 4, cls.fullName());
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
            m.generateJavaUpcallInterfaceMethod(sb);
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
            m.generateUpcallMethodHandle(sb, cls.fullName());
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
            m.generateUpcallStub(sb);
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
}
