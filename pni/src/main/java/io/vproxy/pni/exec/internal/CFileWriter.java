package io.vproxy.pni.exec.internal;

import io.vproxy.pni.exec.CompilerOptions;
import io.vproxy.pni.exec.Main;
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

    public void flush(File dir, CompilerOptions opts) {
        var cCode = gen();
        if (cCode == null) {
            if (opts.verbose()) {
                System.out.println("no native code generated for " + cls.fullName() + " @ " + this.getClass().getSimpleName());
            }
            return;
        }
        var hash = Utils.sha256(cCode);
        cCode += Utils.metadata(opts, Main.C_GEN_VERSION);
        cCode += "// sha256:" + hash + "\n";
        String fileName = fileName();
        Path path = Path.of(dir.getAbsolutePath(), fileName);
        if (opts.verbose()) {
            System.out.println("writing generated native file for " + cls.fullName() + " to " + path);
        }
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
