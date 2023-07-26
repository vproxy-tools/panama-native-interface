package io.vproxy.pni.exec.internal;

import io.vproxy.pni.exec.ast.AstClass;

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
