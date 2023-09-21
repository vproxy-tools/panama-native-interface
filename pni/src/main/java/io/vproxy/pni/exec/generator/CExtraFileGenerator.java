package io.vproxy.pni.exec.generator;

import io.vproxy.pni.exec.CompilerOptions;
import io.vproxy.pni.exec.ast.AstClass;
import io.vproxy.pni.exec.internal.Utils;

public class CExtraFileGenerator extends CFileGenerator {
    public CExtraFileGenerator(AstClass cls, CompilerOptions opts) {
        super(cls, opts);
    }

    public String generate() {
        return generateCSizeof();
    }

    protected String fileName() {
        return cls.underlinedName() + ".extra.c";
    }

    private String generateCSizeof() {
        var sizeof = cls.getSizeof();
        if (sizeof == null) {
            return null;
        }

        var sb = new StringBuilder();
        includeClassHeader(sb, cls);
        var extraInclude = cls.getSizeofInclude();
        if (extraInclude != null) {
            for (var i : extraInclude) {
                if (i.startsWith("<") && i.endsWith(">")) {
                    sb.append("#include ").append(i).append("\n");
                } else {
                    sb.append("#include \"").append(i).append("\"\n");
                }
            }
        }
        sb.append("\n");
        sb.append("JNIEXPORT size_t JNICALL JavaCritical_").append(cls.underlinedName()).append("___getLayoutByteSize() {\n");
        if (sizeof.contains("\n") || sizeof.startsWith("return ")) {
            Utils.generateCFunctionImpl(sb, 4, sizeof);
        } else {
            sb.append("    return sizeof(").append(sizeof).append(");\n");
        }
        sb.append("}\n");
        return sb.toString();
    }
}
