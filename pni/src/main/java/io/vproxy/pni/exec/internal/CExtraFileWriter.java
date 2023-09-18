package io.vproxy.pni.exec.internal;

import io.vproxy.pni.exec.ast.AstClass;

public class CExtraFileWriter extends CFileWriter {
    public CExtraFileWriter(AstClass cls) {
        super(cls);
    }

    protected String gen() {
        return cls.generateCSizeof();
    }

    protected String fileName() {
        return cls.underlinedName() + ".extra.c";
    }
}
