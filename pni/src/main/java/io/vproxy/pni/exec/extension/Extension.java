package io.vproxy.pni.exec.extension;

import io.vproxy.pni.exec.CompilerOptions;
import io.vproxy.pni.exec.ast.AstClass;

import java.util.List;

public interface Extension {
    void validate(CompilerOptions opts) throws ExtensionValidationException;

    void generate(List<AstClass> templates, CompilerOptions opts);
}
