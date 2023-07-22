package io.vproxy.pni.exec.internal;

import io.vproxy.pni.annotation.Len;
import io.vproxy.pni.annotation.Name;
import io.vproxy.pni.exec.ast.AstAnno;
import org.objectweb.asm.tree.AnnotationNode;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

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

    public static List<String> extractMethodDescParamsPart(String paramsPart) {
        List<String> result = new ArrayList<>();
        var sb = new StringBuilder();
        var chars = paramsPart.toCharArray();
        int state = 0; // 0: normal, 1: array, 2: object type
        for (char c : chars) {
            switch (state) {
                case 0:
                    switch (c) {
                        case 'B', 'C', 'D', 'F', 'I', 'J', 'S', 'Z' -> result.add(String.valueOf(c));
                        case '[' -> {
                            state = 1;
                            sb.append("[");
                        }
                        case 'L' -> {
                            state = 2;
                            sb.append("L");
                        }
                        default -> throw new RuntimeException("unknown symbol " + c + " in desc");
                    }
                    break;
                case 1:
                    sb.append(c);
                    switch (c) {
                        case 'B', 'C', 'D', 'F', 'I', 'J', 'S', 'Z' -> {
                            state = 0;
                            result.add(sb.toString());
                            sb.delete(0, sb.length());
                        }
                        case '[' -> { // do nothing
                        }
                        case 'L' -> state = 2;
                        default -> throw new RuntimeException("unknown symbol " + c + " in desc");
                    }
                    break;
                case 2:
                default:
                    sb.append(c);
                    if (c == ';') {
                        state = 0;
                        result.add(sb.toString());
                        sb.delete(0, sb.length());
                    }
            }
        }
        return result;
    }

    public static String getName(List<AstAnno> annos) {
        var nameOpt = annos.stream().filter(a -> a.typeRef != null && a.typeRef.name().equals(Name.class.getName())).findFirst();
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
        var lenOpt = annos.stream().filter(a -> a.typeRef != null && a.typeRef.name().equals(Len.class.getName())).findFirst();
        long len = -1;
        if (lenOpt.isPresent()) {
            var lenAnno = lenOpt.get();
            var vOpt = lenAnno.values.stream().filter(v -> v.name.equals("value")).findFirst();
            if (vOpt.isPresent()) {
                var v = vOpt.get();
                if (v.value instanceof Long l) {
                    len = l;
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
}
