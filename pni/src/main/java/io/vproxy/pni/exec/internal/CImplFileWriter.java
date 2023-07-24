package io.vproxy.pni.exec.internal;

import io.vproxy.pni.exec.ast.AstClass;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CImplFileWriter extends CFileWriter {
    public CImplFileWriter(AstClass cls) {
        super(cls);
    }

    protected String gen() {
        return cls.generateCImpl();
    }

    protected String fileName() {
        return cls.underlinedName() + ".impl.h";
    }
}
