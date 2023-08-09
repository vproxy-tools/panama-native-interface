package io.vproxy.pni.exec.internal;

import io.vproxy.pni.exec.CompilerOptions;
import io.vproxy.pni.exec.Main;
import io.vproxy.pni.exec.ast.AstAnno;
import org.objectweb.asm.tree.AnnotationNode;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static io.vproxy.pni.exec.internal.Consts.*;

public class Utils {
    private Utils() {
    }

    public static File ensureJavaFile(File baseDir, String className) {
        var packages = className.split("\\.");
        File f = baseDir;
        for (int i = 0; i < packages.length - 1; ++i) {
            var p = packages[i];
            f = new File(f.getAbsolutePath() + File.separator + p);
            if (f.exists()) {
                if (!f.isDirectory()) {
                    throw new RuntimeException("unable to create directory " + f + ": file exists and is not directory");
                }
            } else {
                var ok = f.mkdir();
                if (!ok) {
                    throw new RuntimeException("unable to create directory " + f);
                }
            }
        }
        f = new File(f.getAbsolutePath() + File.separator + packages[packages.length - 1] + ".java");
        if (f.exists()) {
            if (!f.isFile()) {
                throw new RuntimeException("unable to create file " + f + ": file exists and is not file");
            }
        }
        return f;
    }

    public static void readAnnotations(List<AstAnno> annos, List<AnnotationNode> nodes) {
        if (nodes != null) {
            for (var a : nodes) {
                annos.add(new AstAnno(a));
            }
        }
    }

    public static class TypeWithGeneric {
        private final String type;
        private final List<String> genericParams;

        public TypeWithGeneric(String type, List<String> genericParams) {
            this.type = type;
            this.genericParams = genericParams;
        }

        public String type() {
            return type;
        }

        public List<String> genericParams() {
            return genericParams;
        }
    }

    private static String extractFirstDesc(String s, int[] offset) {
        var sb = new StringBuilder();
        var chars = s.toCharArray();
        int state = 0; // 0: normal, 1: array, 2: object type
        while (offset[0] < chars.length) {
            var c = chars[offset[0]++];
            switch (state) {
                case 0:
                    switch (c) {
                        case 'B':
                        case 'C':
                        case 'D':
                        case 'F':
                        case 'I':
                        case 'J':
                        case 'S':
                        case 'Z':
                            return String.valueOf(c);
                        case '[':
                            state = 1;
                            sb.append("[");
                            break;
                        case 'L':
                            state = 2;
                            sb.append("L");
                            break;
                        default:
                            throw new RuntimeException("unknown symbol " + c + " in desc");
                    }
                    break;
                case 1:
                    sb.append(c);
                    switch (c) {
                        case 'B':
                        case 'C':
                        case 'D':
                        case 'F':
                        case 'I':
                        case 'J':
                        case 'S':
                        case 'Z':
                            return sb.toString();
                        case '[':
                            // do nothing
                            break;
                        case 'L':
                            state = 2;
                            break;
                        default:
                            throw new RuntimeException("unknown symbol " + c + " in desc");
                    }
                    break;
                case 2:
                default:
                    if (c == '<') {
                        sb.append("<");
                        do {
                            var genericType = extractFirstDesc(s, offset);
                            sb.append(genericType);
                        } while (offset[0] < chars.length && chars[offset[0]] != '>');
                        if (offset[0] != '>') {
                            throw new RuntimeException("expecting '>': " + s.substring(0, offset[0]) + "|" + s.substring(offset[0]));
                        }
                        sb.append(">");
                        ++offset[0];
                        continue;
                    }
                    sb.append(c);
                    if (c == ';') {
                        return sb.toString();
                    }
            }
        }
        throw new RuntimeException("unknown symbol: " + s.substring(0, offset[0]) + "|" + s.substring(offset[0]));
    }

    public static List<TypeWithGeneric> extractMethodDescParamsPart(String paramsPart) {
        List<TypeWithGeneric> result = new ArrayList<>();
        var sb = new StringBuilder();
        var genericTypes = new ArrayList<String>();
        var chars = paramsPart.toCharArray();
        int state = 0; // 0: normal, 1: array, 2: object type
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            switch (state) {
                case 0:
                    switch (c) {
                        case 'B':
                        case 'C':
                        case 'D':
                        case 'F':
                        case 'I':
                        case 'J':
                        case 'S':
                        case 'Z':
                            result.add(new TypeWithGeneric(String.valueOf(c), Collections.emptyList()));
                            break;
                        case '[':
                            state = 1;
                            sb.append("[");
                            break;
                        case 'L':
                            state = 2;
                            sb.append("L");
                            break;
                        default:
                            throw new RuntimeException("unknown symbol " + c + " in desc");
                    }
                    break;
                case 1:
                    sb.append(c);
                    switch (c) {
                        case 'B':
                        case 'C':
                        case 'D':
                        case 'F':
                        case 'I':
                        case 'J':
                        case 'S':
                        case 'Z':
                            state = 0;
                            result.add(new TypeWithGeneric(sb.toString(), Collections.emptyList()));
                            sb.delete(0, sb.length());
                            break;
                        case '[':
                            // do nothing
                            break;
                        case 'L':
                            state = 2;
                            break;
                        default:
                            throw new RuntimeException("unknown symbol " + c + " in desc");
                    }
                    break;
                case 2:
                default:
                    if (c == '<') {
                        while (true) {
                            var ii = new int[]{i + 1};
                            var genericType = extractFirstDesc(paramsPart, ii);
                            if (ii[0] >= chars.length) {
                                throw new RuntimeException("generic type not finishing with '>': " + paramsPart);
                            }
                            if (chars[ii[0]] != '>') {
                                continue;
                            }
                            i = ii[0];
                            genericTypes.add(genericType);
                            break;
                        }
                        continue;
                    }
                    sb.append(c);
                    if (c == ';') {
                        state = 0;
                        result.add(new TypeWithGeneric(sb.toString(), new ArrayList<>(genericTypes)));
                        genericTypes.clear();
                        sb.delete(0, sb.length());
                    }
            }
        }
        return result;
    }

    public static String getName(List<AstAnno> annos) {
        var nameOpt = annos.stream().filter(a -> a.typeRef != null && a.typeRef.name().equals(NameClassName)).findFirst();
        if (nameOpt.isEmpty()) {
            return null;
        }
        var name = nameOpt.get();
        var valueOpt = name.values.stream().filter(v -> v.name.equals("value")).findFirst();
        if (valueOpt.isEmpty()) {
            return null;
        }
        var value = valueOpt.get();
        if (!(value.value instanceof String)) {
            return null;
        }
        return (String) value.value;
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public static boolean isValidName(String name, boolean isParam) {
        if (name.isEmpty()) {
            return false;
        }
        if (isParam) {
            if (name.equals("env") || name.equals("self")) {
                return false;
            }
        }
        var chars = name.toCharArray();
        if (!isValidNameCharFirst(chars[0])) {
            return false;
        }
        for (int i = 1; i < chars.length; ++i) {
            if (!isValidNameChar(chars[i])) {
                return false;
            }
        }
        return true;
    }

    private static boolean isValidNameCharFirst(char c) {
        return c == '_' || c == '$' || ('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z');
    }

    private static boolean isValidNameChar(char c) {
        return isValidNameCharFirst(c) || ('0' <= c && c <= '9');
    }

    public static long getLen(List<AstAnno> annos) {
        var lenOpt = annos.stream().filter(a -> a.typeRef != null && a.typeRef.name().equals(LenClassName)).findFirst();
        long len = -1;
        if (lenOpt.isPresent()) {
            var lenAnno = lenOpt.get();
            var vOpt = lenAnno.values.stream().filter(v -> v.name.equals("value")).findFirst();
            if (vOpt.isPresent()) {
                var v = vOpt.get();
                if (v.value instanceof Long) {
                    len = (Long) v.value;
                }
            }
        }
        return len;
    }

    public static StringBuilder appendIndent(StringBuilder sb, int indent) {
        if (indent > 0) {
            sb.append(" ".repeat(indent));
        }
        return sb;
    }

    public static String getterName(String fieldName) {
        return "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }

    public static String setterName(String fieldName) {
        return "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }

    public static void varHandleField(StringBuilder sb, int indent, String fieldName) {
        Utils.appendIndent(sb, indent)
            .append("private static final VarHandle ").append(fieldName).append("VH = LAYOUT.varHandle(\n");
        Utils.appendIndent(sb, indent + 4)
            .append("MemoryLayout.PathElement.groupElement(\"").append(fieldName).append("\")\n");
        Utils.appendIndent(sb, indent)
            .append(");\n");
    }

    public static String sha256(String s) {
        try {
            var digest = MessageDigest.getInstance("SHA-256");
            var bytes = digest.digest(s.getBytes(StandardCharsets.UTF_8));
            var hex = new StringBuilder();
            for (var b : bytes) {
                var h = Integer.toHexString(0xff & b);
                if (h.length() == 1) {
                    hex.append("0");
                }
                hex.append(h);
            }
            return hex.toString();
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }

    public static long getAlign(List<AstAnno> annos) {
        var opt = annos.stream().filter(a -> a.typeRef != null && a.typeRef.name().equals(AlignClassName)).findFirst();
        if (opt.isEmpty()) {
            return 0;
        }
        var anno = opt.get();
        var vOpt = anno.values.stream().filter(v -> v.name.equals("value")).findFirst();
        if (vOpt.isEmpty()) {
            return 0;
        }
        var v = vOpt.get().value;
        if (v instanceof Long) {
            return (Long) v;
        }
        return 0;
    }

    public static boolean getAlignPacked(List<AstAnno> annos) {
        var opt = annos.stream().filter(a -> a.typeRef != null && a.typeRef.name().equals(AlignClassName)).findFirst();
        if (opt.isEmpty()) {
            return false;
        }
        var anno = opt.get();
        var vOpt = anno.values.stream().filter(v -> v.name.equals("packed")).findFirst();
        if (vOpt.isEmpty()) {
            return false;
        }
        var v = vOpt.get().value;
        if (v instanceof Boolean) {
            return (Boolean) v;
        }
        return false;
    }

    public static String metadata(CompilerOptions opts) {
        var sb = new StringBuilder();
        sb.append("// metadata.generator-version: pni ").append(Main.VERSION).append("\n");
        for (var entry : opts.metadata().entrySet()) {
            var k = entry.getKey();
            var v = entry.getValue();
            sb.append("// metadata.").append(k).append(": ").append(v).append("\n");
        }
        return sb.toString();
    }
}
