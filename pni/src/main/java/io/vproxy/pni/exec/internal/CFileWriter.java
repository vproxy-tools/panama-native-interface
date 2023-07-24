package io.vproxy.pni.exec.internal;

import io.vproxy.pni.exec.ast.AstClass;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CFileWriter {
    protected final AstClass cls;

    public CFileWriter(AstClass cls) {
        this.cls = cls;
    }

    public void flush(File dir, boolean verbose) {
        var cCode = gen();
        if (cCode == null) {
            if (verbose) {
                System.out.println("-----BEGIN C CODE-----");
                System.out.println("no code generated for " + cls.name + " @ " + this);
                System.out.println("-----END C CODE-----");
            }
            return;
        }
        var hash = Utils.sha256(cCode);
        cCode += "// sha256:" + hash + "\n";
        if (verbose) {
            System.out.println("-----BEGIN C CODE-----");
            System.out.println(cCode);
            System.out.println("-----END C CODE-----");
        }
        String fileName = fileName();
        Path path = Path.of(dir.getAbsolutePath(), fileName);
        try {
            Files.writeString(path, cCode);
        } catch (IOException e) {
            throw new RuntimeException("failed writing c code: " + fileName, e);
        }
    }

    protected String gen() {
        return cls.generateC();
    }

    protected String fileName() {
        return cls.underlinedName() + ".h";
    }
}
