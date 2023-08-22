package io.vproxy.pni.exec.internal;

import io.vproxy.pni.exec.ast.AstClass;

public class CUpcallImplFileWriter extends CFileWriter {
    public CUpcallImplFileWriter(AstClass cls) {
        super(cls);
    }

    protected String gen() {
        return cls.generateCUpcallImpl();
    }

    protected String fileName() {
        return cls.underlinedName() + ".c";
    }
}
