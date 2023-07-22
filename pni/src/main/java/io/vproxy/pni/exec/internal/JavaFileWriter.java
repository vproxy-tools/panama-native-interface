package io.vproxy.pni.exec.internal;

import io.vproxy.pni.exec.ast.AstClass;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class JavaFileWriter {
    private final AstClass cls;

    public JavaFileWriter(AstClass cls) {
        this.cls = cls;
    }

    public void flush(File baseDir, boolean verbose) {
        var javaCode = cls.generateJava();
        var hash = Utils.sha256(javaCode);
        javaCode += "// sha256:" + hash + "\n";
        if (verbose) {
            System.out.println("-----BEGIN JAVA CODE-----");
            System.out.println(javaCode);
            System.out.println("-----END JAVA CODE-----");
        }
        var file = Utils.ensureJavaFile(baseDir, cls.fullName());
        try {
            Files.writeString(file.toPath(), javaCode);
        } catch (IOException e) {
            throw new RuntimeException("failed writing java code: " + cls.fullName(), e);
        }
    }
}
