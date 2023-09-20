package io.vproxy.pni.exec.internal;

import io.vproxy.pni.exec.ast.AstClass;
import io.vproxy.pni.exec.ast.AstMethod;
import io.vproxy.pni.exec.type.VoidTypeInfo;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("SameParameterValue")
public class CUpcallImplFileWriter extends CFileWriter {
    public CUpcallImplFileWriter(AstClass cls) {
        super(cls);
    }

    protected String gen() {
        return generateCUpcallImpl();
    }

    protected String fileName() {
        return cls.underlinedName() + ".c";
    }

    private String generateCUpcallImpl() {
        if (!cls.isUpcall()) {
            return null;
        }

        var sb = new StringBuilder();
        sb.append("#include \"").append(cls.underlinedName()).append(".h\"\n");
        sb.append("#include <stdio.h>\n");
        sb.append("#include <stdlib.h>\n");

        sb.append("\n" +
                  "#ifdef __cplusplus\n" +
                  "extern \"C\" {\n" +
                  "#endif\n");

        sb.append("\n");
        for (var m : cls.methods) {
            sb.append("static ").append(get(m).nativeUpcallFunctionPointer(false)).append(";\n");
        }

        sb.append("\n");
        sb.append("JNIEXPORT void JNICALL JavaCritical_").append(cls.underlinedName()).append("_INIT").append("(\n");
        for (int i = 0; i < cls.methods.size(); i++) {
            var m = cls.methods.get(i);
            Utils.appendIndent(sb, 4)
                .append(get(m).nativeUpcallFunctionPointer(true));
            if (i < cls.methods.size() - 1) {
                sb.append(",");
            }
            sb.append("\n");
        }
        sb.append(") {\n");
        for (var m : cls.methods) {
            Utils.appendIndent(sb, 4)
                .append("_").append(m.name).append(" = ").append(m.name).append(";\n");
        }
        sb.append("}\n");

        for (var m : cls.methods) {
            sb.append("\n");
            get(m).generateCUpcallImpl(sb, 0, cls.underlinedName());
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
        private MethodGenerator(AstMethod method) {
            super(method);
        }

        private String nativeUpcallFunctionPointer(boolean isParam) {
            var sb = new StringBuilder();
            sb.append(method.returnTypeRef.nativeReturnType(method.varOptsForReturn(true)));
            sb.append(" (*");
            if (!isParam) {
                sb.append("_");
            }
            sb.append(method.name);
            sb.append(")(");
            boolean isFirst = true;
            for (var p : method.params) {
                if (isFirst) {
                    isFirst = false;
                } else {
                    sb.append(",");
                }
                sb.append(p.typeRef.nativeParamType(null, p.varOpts()));
            }
            var returnAllocation = method.returnTypeRef.allocationInfoForReturnValue(method.varOptsForReturn(true));
            String returnTypeExtraType = null;
            if (returnAllocation.requireAllocator()) {
                returnTypeExtraType = method.returnTypeRef.nativeParamType(null, method.varOptsForReturn(true));
            }
            if (returnTypeExtraType != null) {
                if (!method.params.isEmpty()) {
                    sb.append(",");
                }
                sb.append(returnTypeExtraType);
            }
            if (method.params.isEmpty()
                && returnTypeExtraType == null) {
                sb.append("void");
            }
            sb.append(")");
            return sb.toString();
        }

        private void generateCUpcallImpl(StringBuilder sb, int indent, String classUnderlinedName) {
            generateC0(sb, indent, classUnderlinedName, null, true);
            sb.append(" {\n");
            Utils.appendIndent(sb, indent + 4)
                .append("if (_").append(method.name).append(" == NULL) {\n");
            Utils.appendIndent(sb, indent + 8)
                .append("printf(\"").append(method.nativeName(classUnderlinedName, true)).append(" function pointer is null\");\n");
            Utils.appendIndent(sb, indent + 8)
                .append("fflush(stdout);\n");
            Utils.appendIndent(sb, indent + 8)
                .append("exit(1);\n");
            Utils.appendIndent(sb, indent + 4).append("}\n");
            Utils.appendIndent(sb, indent + 4);
            if (!(method.returnTypeRef instanceof VoidTypeInfo)) {
                sb.append("return ");
            }
            sb.append("_").append(method.name).append("(");
            boolean isFirst = true;
            for (var p : method.params) {
                if (isFirst) {
                    isFirst = false;
                } else {
                    sb.append(", ");
                }
                sb.append(p.name);
            }
            if (method.returnTypeRef.allocationInfoForReturnValue(method.varOptsForReturn(true)).requireAllocator()) {
                if (!method.params.isEmpty()) {
                    sb.append(", ");
                }
                sb.append("return_");
            }
            sb.append(");\n");
            Utils.appendIndent(sb, indent).append("}\n");
        }
    }
}
