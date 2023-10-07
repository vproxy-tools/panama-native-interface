package io.vproxy.pni.exec.generator;

import io.vproxy.base.util.LogType;
import io.vproxy.base.util.Logger;
import io.vproxy.pni.exec.CompilationFlag;
import io.vproxy.pni.exec.CompilerOptions;
import io.vproxy.pni.exec.Main;
import io.vproxy.pni.exec.ast.AstClass;
import io.vproxy.pni.exec.ast.AstMethod;
import io.vproxy.pni.exec.ast.AstParam;
import io.vproxy.pni.exec.internal.PNILogger;
import io.vproxy.pni.exec.internal.Utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

@SuppressWarnings("SameParameterValue")
public class GraalNativeImageFeatureFileGenerator {
    private final List<AstClass> classes;
    private final CompilerOptions opts;

    public GraalNativeImageFeatureFileGenerator(List<AstClass> classes, CompilerOptions opts) {
        this.classes = classes;
        this.opts = opts;
    }

    public void flush(File baseDir) {
        if (!opts.hasCompilationFlag(CompilationFlag.GRAAL_NATIVE_IMAGE_FEATURE)) {
            if (opts.isVerbose()) {
                Logger.trace(LogType.ALERT, CompilationFlag.GRAAL_NATIVE_IMAGE_FEATURE.name + " is not enabled, no need to generate feature class");
            }
            return;
        }
        var featureClassName = opts.getCompilationFlag(CompilationFlag.GRAAL_NATIVE_IMAGE_FEATURE);

        var javaCode = generateJava();
        var hash = Utils.sha256(javaCode);
        javaCode += Utils.metadata(opts, Main.GRAAL_GEN_VERSION);
        javaCode += "// sha256:" + hash + "\n";
        var file = Utils.ensureJavaFile(baseDir, featureClassName);
        if (Utils.hashesAreTheSame(file, hash)) {
            PNILogger.debug(opts, "skipping feature file because nothing changed: " + file.getAbsolutePath());
            return;
        }
        PNILogger.debug(opts, "writing generated feature file: " + file.getAbsolutePath());
        try {
            Files.writeString(file.toPath(), javaCode);
        } catch (IOException e) {
            throw new RuntimeException("failed writing feature code: " + featureClassName, e);
        }
    }

    public String generateJava() {
        var featureClassName = opts.getCompilationFlag(CompilationFlag.GRAAL_NATIVE_IMAGE_FEATURE);
        String packageName;
        if (featureClassName.contains(".")) {
            packageName = featureClassName.substring(0, featureClassName.lastIndexOf("."));
        } else {
            packageName = "";
        }
        String simpleName;
        if (featureClassName.contains(".")) {
            simpleName = featureClassName.substring(featureClassName.lastIndexOf(".") + 1);
        } else {
            simpleName = featureClassName;
        }

        var sb = new StringBuilder();
        if (!packageName.isEmpty()) {
            sb.append("package ").append(packageName).append(";\n\n");
        }

        sb.append("import io.vproxy.pni.*;\n" +
                  "import java.lang.foreign.*;\n" +
                  "import java.nio.ByteBuffer;\n" +
                  "import org.graalvm.nativeimage.hosted.*;\n");
        sb.append("\n");
        generateJava(sb, simpleName);
        return sb.toString();
    }

    private void generateJava(StringBuilder sb, String simpleName) {
        sb.append("public class ").append(simpleName).append(" implements org.graalvm.nativeimage.hosted.Feature {\n");
        sb.append("    @Override\n");
        sb.append("    public void duringSetup(DuringSetupAccess access) {\n");

        boolean isFirst = true;
        var classes = new ArrayList<>(this.classes);
        classes.sort(Comparator.comparing(a -> a.name));
        for (var cls : classes) {
            if (cls.isUpcall()) {
                continue;
            }
            for (var m : cls.methods) {
                if (isFirst) {
                    isFirst = false;
                } else {
                    sb.append("\n");
                }
                get(m).generateJava(sb, 8, cls.underlinedName(), !cls.isInterface);
            }
        }

        sb.append("    }\n");
        sb.append("}\n");
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
            var nativeName = method.nativeName(classUnderlinedName);
            Utils.appendIndent(sb, indent).append("/* ").append(nativeName).append(" */\n");
            Utils.appendIndent(sb, indent).append("RuntimeForeignAccess.registerForDowncall(PanamaUtils.");
            if (method.critical()) {
                sb.append("buildCriticalFunctionDescriptor(");
            } else {
                sb.append("buildFunctionDescriptor(");
            }
            boolean needComma = false;
            if (method.critical()) {
                needComma = true;
                sb.append(method.returnTypeRef.methodHandleTypeForReturn(method.varOptsForReturn()));
            }
            if (needSelf) {
                if (needComma) {
                    sb.append(", ");
                }
                needComma = true;
                sb.append("MemorySegment.class /* self */");
            }
            for (var p : method.params) {
                if (needComma) {
                    sb.append(", ");
                }
                needComma = true;
                get(p).generateMethodHandle(sb, 0);
                sb.append(" /* ").append(p.name).append(" */");
            }
            var returnAllocation = method.returnTypeRef.allocationInfoForReturnValue(method.varOptsForReturn());
            if (returnAllocation.requireAllocator()) {
                if (needComma) {
                    sb.append(", ");
                }
                //noinspection UnusedAssignment
                needComma = true;
                sb.append("MemorySegment.class /* return */");
            }
            sb.append(")");
            if (method.trivial()) {
                sb.append(", Linker.Option.isTrivial()");
            }
            sb.append(");\n");
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

            private void generateMethodHandle(StringBuilder sb, int indent) {
                Utils.appendIndent(sb, indent);
                sb.append(param.typeRef.methodHandleType(param.varOpts()));
            }
        }
    }
}
