package io.vproxy.pni.exec.internal;

import io.vproxy.pni.exec.CompilerOptions;
import io.vproxy.pni.exec.Main;
import io.vproxy.pni.exec.ast.AstClass;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class JavaFileWriter {
    private final AstClass cls;

    public JavaFileWriter(AstClass cls) {
        this.cls = cls;
    }

    public void flush(File baseDir, CompilerOptions opts) {
        var javaCode = cls.generateJava();
        var hash = Utils.sha256(javaCode);
        javaCode += Utils.metadata(opts, Main.JAVA_GEN_VERSION);
        javaCode += "// sha256:" + hash + "\n";
        var file = Utils.ensureJavaFile(baseDir, cls.fullName());
        if (opts.verbose()) {
            System.out.println("writing generated java file: " + file.getAbsolutePath());
        }
        try {
            Files.writeString(file.toPath(), javaCode);
        } catch (IOException e) {
            throw new RuntimeException("failed writing java code: " + cls.fullName(), e);
        }
    }
}
