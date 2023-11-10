package io.vproxy.pni.test;

import io.vproxy.commons.util.IOUtils;
import io.vproxy.pni.exec.CompilationFlag;
import io.vproxy.pni.exec.Generator;
import io.vproxy.pni.exec.ast.AstAnno;
import io.vproxy.pni.exec.ast.AstClass;
import io.vproxy.pni.exec.ast.AstField;
import io.vproxy.pni.exec.type.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static org.junit.Assert.fail;

public class Utils {
    private Utils() {
    }

    static void checkUnsupported(Runnable r) {
        try {
            r.run();
            fail();
        } catch (UnsupportedOperationException ignore) {
        }
    }

    static String sbHelper(Consumer<StringBuilder> func) {
        var sb = new StringBuilder();
        func.accept(sb);
        return sb.toString();
    }

    public static ClassTypeInfo generalClsTypeInfo() {
        var opts = new CompilerOptions();
        var astClass = new AstClass(opts);
        astClass.name = "a/b/PNICls";
        astClass.annos.add(new AstAnno() {{
            typeRef = AnnoStructTypeInfo.get();
        }});
        astClass.fields.add(new AstField() {{
            typeRef = CharTypeInfo.get();
        }});
        astClass.fields.add(new AstField() {{
            typeRef = ShortTypeInfo.get();
        }});
        return new ClassTypeInfo(astClass);
    }

    public static ClassTypeInfo emptyClsTypeInfo() {
        var astClass = new AstClass(new CompilerOptions());
        astClass.name = "a/b/PNIEmptyCls";
        astClass.annos.add(new AstAnno() {{
            typeRef = AnnoStructTypeInfo.get();
        }});
        return new ClassTypeInfo(astClass);
    }

    public record CompilationResult(List<AstClass> classes, Path template, Path compile, Path gen, Runnable delete) {
        @SuppressWarnings("removal")
        @Override
        protected void finalize() {
            delete.run();
        }
    }

    public static CompilationResult load(List<JavaFile> files, io.vproxy.pni.exec.CompilerOptions opts) throws Exception {
        var template = Files.createTempDirectory("test-template");
        var compile = Files.createTempDirectory("test-compile");
        var gen = Files.createTempDirectory("test-gen");
        {
            for (var f : files) {
                var name = f.getName();
                var content = f.getContent();
                IOUtils.writeFileWithBackup(Path.of(template.toString(), name).toString(), content);
            }
            var pb = new ProcessBuilder();
            var arr = new String[5 + files.size()];
            var idx = 0;
            arr[idx++] = Path.of(System.getenv("JAVA_HOME"), "bin", "javac").toString();
            arr[idx++] = "-cp";
            arr[idx++] = "../api/build/classes/java/main";
            arr[idx++] = "-d";
            arr[idx++] = compile.toString();
            for (var f : files) {
                arr[idx++] = Path.of(template.toString(), f.getName()).toString();
            }
            pb.command(arr);
            var p = pb.start();
            int exitCode = p.waitFor();
            if (exitCode != 0) {
                try (var outReader = p.inputReader(); var errReader = p.errorReader()) {
                    var out = new StringBuilder();
                    String line;
                    while ((line = outReader.readLine()) != null) {
                        out.append(line).append("\n");
                    }
                    var err = new StringBuilder();
                    while ((line = errReader.readLine()) != null) {
                        err.append(line).append("\n");
                    }
                    throw new RuntimeException("exitCode = " + exitCode + "\n----OUTPUT----\n" + out + "\n----ERROR----\n" + err);
                }
            }

            opts.setCOutputDirectory(gen.toString());
            opts.setJavaOutputBaseDirectory(gen.toString());
            var cp = new ArrayList<File>();
            if (opts.getClasspath() != null) {
                cp.addAll(opts.getClasspath());
            }
            cp.add(compile.toFile());
            opts.setClasspathFileList(cp);
            var generator = new Generator(opts);
            generator.generate();

            var field = Generator.class.getDeclaredField("lastClasses");
            field.setAccessible(true);
            //noinspection unchecked
            var classes = (List<AstClass>) field.get(generator);

            return new CompilationResult(classes, template, compile, gen, () -> {
                IOUtils.deleteDirectory(template.toFile());
                IOUtils.deleteDirectory(compile.toFile());
                IOUtils.deleteDirectory(gen.toFile());
            });
        }
    }

    public static class CompilerOptions extends io.vproxy.pni.exec.CompilerOptions {
        public CompilerOptions() {
            setCompilationFlag(CompilationFlag.TYPE_NAME_PREFIX, "PNI");
        }
    }
}
