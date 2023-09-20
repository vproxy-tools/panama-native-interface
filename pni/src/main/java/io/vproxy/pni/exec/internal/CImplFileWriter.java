package io.vproxy.pni.exec.internal;

import io.vproxy.pni.exec.ast.AstClass;
import io.vproxy.pni.exec.ast.AstMethod;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@SuppressWarnings("SameParameterValue")
public class CImplFileWriter extends CFileWriter {
    public CImplFileWriter(AstClass cls) {
        super(cls);
    }

    protected String gen() {
        return generateCImpl();
    }

    protected String fileName() {
        return cls.underlinedName() + ".impl.h";
    }

    public String generateCImpl() {
        boolean doGenerate = false;
        for (var m : cls.methods) {
            var s = m.getImplC();
            if (s != null) {
                doGenerate = true;
                break;
            }
        }
        if (!doGenerate) {
            return null;
        }

        var sb = new StringBuilder();
        includeClassHeader(sb, cls);
        var imports = new HashSet<String>();
        for (var m : cls.methods) {
            var includeLs = m.getImplInclude();
            if (includeLs == null) {
                continue;
            }
            imports.addAll(includeLs);
        }
        for (var i : imports) {
            if (i.startsWith("<") && i.endsWith(">")) {
                sb.append("#include ").append(i).append("\n");
            } else {
                sb.append("#include \"").append(i).append("\"\n");
            }
        }

        sb.append("\n" +
                  "#ifdef __cplusplus\n" +
                  "extern \"C\" {\n" +
                  "#endif\n");

        for (var m : cls.methods) {
            var impl = m.getImplC();
            if (impl == null) {
                continue;
            }
            sb.append("\n");
            get(m).generateCImpl(sb, 0, cls.underlinedName(), cls.nativeTypeName(), impl);
        }

        sb.append("\n" +
                  "#ifdef __cplusplus\n" +
                  "}\n" +
                  "#endif\n");

        return sb.toString();
    }

    private final Map<AstMethod, MethodGenerator> methodGenerators = new HashMap<>();

    private MethodGenerator get(AstMethod m) {
        return methodGenerators.computeIfAbsent(m, MethodGenerator::new);
    }

    private static class MethodGenerator extends CFileWriter.MethodGenerator {
        protected MethodGenerator(AstMethod method) {
            super(method);
        }

        private void generateCImpl(StringBuilder sb, int indent, String classUnderlinedName, String classNativeTypeName, String impl) {
            generateC0(sb, indent, classUnderlinedName, classNativeTypeName);
            sb.append(" {\n");
            Utils.generateCFunctionImpl(sb, indent + 4, impl);
            Utils.appendIndent(sb, indent).append("}\n");
        }
    }
}
