package io.vproxy.pni.exec.internal;

import io.vproxy.pni.exec.CompilationFlag;
import io.vproxy.pni.exec.CompilerOptions;
import io.vproxy.pni.exec.ast.AstAnno;
import io.vproxy.pni.exec.ast.AstGenericDef;
import io.vproxy.pni.exec.ast.AstTypeDesc;
import io.vproxy.pni.exec.type.*;
import org.objectweb.asm.tree.AnnotationNode;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
                if (a.desc.startsWith("Lio/vproxy/pni/annotation/")) {
                    annos.add(new AstAnno(a));
                }
            }
        }
    }

    public static String convertDescToJavaName(String desc) {
        if (desc.length() == 1) {
            switch (desc.charAt(0)) {
                case '*':
                    return "?";
                case 'V':
                    return "void";
                case 'B':
                    return "byte";
                case 'C':
                    return "char";
                case 'D':
                    return "double";
                case 'F':
                    return "float";
                case 'I':
                    return "int";
                case 'J':
                    return "long";
                case 'S':
                    return "short";
                case 'Z':
                    return "boolean";
            }
        } else if (desc.startsWith("[")) {
            return convertDescToJavaName(desc.substring(1)) + "[]";
        } else if (desc.startsWith("L") && desc.endsWith(";")) {
            var n = desc.substring(1, desc.length() - 1);
            return n.replace("/", ".");
        } else if (desc.startsWith("T") && desc.endsWith(";")) {
            return desc.substring(1, desc.length() - 1);
        } else if (desc.startsWith("+")) {
            return "? extends " + convertDescToJavaName(desc.substring(1));
        } else if (desc.startsWith("-")) {
            return "? super " + convertDescToJavaName(desc.substring(1));
        }
        throw new RuntimeException("cannot convert desc to java name: " + desc);
    }

    private static AstTypeDesc extractFirstDesc(char[] chars, int[] offset) {
        var genericTypes = new ArrayList<AstTypeDesc>();
        var sb = new StringBuilder();
        int state = 0; // 0: normal, 1: array, 2: object type or generic type, 3: extends or super
        while (offset[0] < chars.length) {
            var c = chars[offset[0]++];
            switch (state) {
                case 0:
                    switch (c) {
                        case '*':
                        case 'V':
                        case 'B':
                        case 'C':
                        case 'D':
                        case 'F':
                        case 'I':
                        case 'J':
                        case 'S':
                        case 'Z':
                            return new AstTypeDesc(c);
                        case '[':
                            state = 1;
                            sb.append("[");
                            break;
                        case 'L':
                        case 'T':
                            state = 2;
                            sb.append(c);
                            break;
                        case '+':
                        case '-':
                            state = 3;
                            sb.append(c);
                            break;
                        default:
                            throw new RuntimeException("unknown symbol " + c + ": " + new String(chars, 0, offset[0]) + "|" + new String(chars, offset[0], chars.length - offset[0]));
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
                            return new AstTypeDesc(sb.toString());
                        case '[':
                            // do nothing
                            break;
                        case 'L':
                        case 'T':
                            state = 2;
                            break;
                        default:
                            throw new RuntimeException("unknown symbol " + c + ": " + new String(chars, 0, offset[0]) + "|" + new String(chars, offset[0], chars.length - offset[0]));
                    }
                    break;
                case 3:
                    switch (c) {
                        case 'L':
                        case 'T':
                            state = 2;
                            sb.append(c);
                            break;
                        case '[':
                            state = 1;
                            sb.append(c);
                            break;
                        default:
                            throw new RuntimeException("expecting 'L' or 'T': " + new String(chars, 0, offset[0]) + "|" + new String(chars, offset[0], chars.length - offset[0]));
                    }
                    break;
                case 2:
                default:
                    if (c == '<') {
                        do {
                            var genericType = extractFirstDesc(chars, offset);
                            genericTypes.add(genericType);
                        } while (offset[0] < chars.length && chars[offset[0]] != '>');
                        if (chars.length == offset[0]) {
                            throw new RuntimeException("unexpected EOF: " + new String(chars) + "|");
                        }
                        if (chars[offset[0]] != '>') {
                            throw new RuntimeException("expecting '>': " + new String(chars, 0, offset[0]) + "|" + new String(chars, offset[0], chars.length - offset[0]));
                        }
                        ++offset[0];
                        continue;
                    }
                    sb.append(c);
                    if (c == ';') {
                        return new AstTypeDesc(sb.toString(), genericTypes);
                    }
            }
        }
        throw new RuntimeException("unexpected EOF: " + new String(chars) + "|");
    }

    public static List<AstTypeDesc> extractDesc(String desc) {
        List<AstTypeDesc> result = new ArrayList<>();
        var chars = desc.toCharArray();
        int[] offset = {0};
        while (offset[0] < chars.length) {
            result.add(extractFirstDesc(chars, offset));
        }
        return result;
    }

    public static List<AstGenericDef> extractGenericDefinition(String desc) {
        if (!desc.startsWith("<")) {
            return Collections.emptyList();
        }
        char[] chars = desc.toCharArray();
        var sb = new StringBuilder();
        int[] offset = {1};
        List<AstGenericDef> result = new ArrayList<>();
        while (offset[0] < chars.length) {
            char c = chars[offset[0]++];
            if (c == ':') {
                if (offset[0] < chars.length && chars[offset[0]] == ':') {
                    offset[0]++;
                }
                var d = extractFirstDesc(chars, offset);
                result.add(new AstGenericDef(sb.toString(), d));
                sb.delete(0, sb.length());
            } else if (c == '>') {
                if (sb.length() != 0) {
                    throw new RuntimeException("unknown symbol " + c + ": " + new String(chars, 0, offset[0]) + "|" + new String(chars, offset[0], chars.length - offset[0]));
                }
                return result;
            } else {
                sb.append(c);
            }
        }
        throw new RuntimeException("unexpected eof : " + desc + "|");
    }

    public static String getName(List<AstAnno> annos) {
        var nameOpt = annos.stream().filter(a -> a.typeRef instanceof AnnoNameTypeInfo).findFirst();
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

    public static boolean isValidClassName(String name) {
        if (name.contains("..") || name.startsWith(".") || name.endsWith("."))
            return false;
        var arr = name.split("\\.");
        for (var s : arr) {
            if (!isValidName(s, false)) {
                return false;
            }
        }
        return true;
    }

    public static long getLen(List<AstAnno> annos) {
        var lenOpt = annos.stream().filter(a -> a.typeRef instanceof AnnoLenTypeInfo).findFirst();
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

    public static boolean defaultIsAlwaysAligned = false;

    public static Boolean isAlwaysAligned(List<AstAnno> annos, CompilerOptions opts) {
        var opt = annos.stream().filter(a -> a.typeRef instanceof AnnoAlwaysAlignedTypeInfo).findFirst();
        if (opt.isEmpty()) {
            if (opts == null) {
                return null;
            }
            if (opts.hasCompilationFlag(CompilationFlag.ALWAYS_ALIGNED)) {
                return opts.getCompilationFlag(CompilationFlag.ALWAYS_ALIGNED);
            }
            return null;
        }
        var anno = opt.get();
        var vOpt = anno.values.stream().filter(v -> v.name.equals("value")).findFirst();
        if (vOpt.isEmpty()) {
            return true; // annotation default value is true
        }
        var v = vOpt.get().value;
        if (v instanceof Boolean) {
            return (boolean) v;
        }
        return true; // annotation default value is true
    }

    public static StringBuilder appendIndent(StringBuilder sb, int indent) {
        if (indent > 0) {
            sb.append(" ".repeat(indent));
        }
        return sb;
    }

    public static String getterName(String fieldName) {
        return getterName(fieldName, false);
    }

    public static String getterName(String fieldName, boolean isBoolean) {
        return (isBoolean ? "is" : "get") + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }

    public static String setterName(String fieldName) {
        return "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }

    public static void varHandleField(StringBuilder sb, int indent, String fieldName) {
        Utils.appendIndent(sb, indent)
            .append("private static final VarHandleW ").append(fieldName).append("VH = VarHandleW.of(\n");
        Utils.appendIndent(sb, indent + 4)
            .append("LAYOUT.varHandle(\n");
        Utils.appendIndent(sb, indent + 8)
            .append("MemoryLayout.PathElement.groupElement(\"").append(fieldName).append("\")\n");
        Utils.appendIndent(sb, indent + 4)
            .append(")\n");
        Utils.appendIndent(sb, indent)
            .append(");\n");
    }

    public static StringBuilder appendCPadding(StringBuilder sb, long maxType, long p) {
        sb.append(" /* padding */");
        while (p > 0) {
            if (maxType >= 8) {
                if (p >= 8) {
                    sb.append(" uint64_t : 64;");
                    p -= 8;
                    continue;
                }
            }
            if (maxType >= 4) {
                if (p >= 4) {
                    sb.append(" uint32_t : 32;");
                    p -= 4;
                    continue;
                }
            }
            if (maxType >= 2) {
                if (p >= 2) {
                    sb.append(" uint16_t : 16;");
                    p -= 2;
                    continue;
                }
            }
            sb.append(" uint8_t : 8;");
            p -= 1;
        }
        return sb;
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
        var opt = annos.stream().filter(a -> a.typeRef instanceof AnnoAlignTypeInfo).findFirst();
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
        var opt = annos.stream().filter(a -> a.typeRef instanceof AnnoAlignTypeInfo).findFirst();
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

    public static String getNativeType(List<AstAnno> annos) {
        return getNativeType(annos, v -> v instanceof AnnoNativeTypeTypeInfo);
    }

    public static String getNativeReturnType(List<AstAnno> annos) {
        return getNativeType(annos, v -> v instanceof AnnoNativeReturnTypeTypeInfo);
    }

    private static String getNativeType(List<AstAnno> annos, Predicate<TypeInfo> filter) {
        var opt = annos.stream().filter(a -> a.typeRef != null && filter.test(a.typeRef)).findFirst();
        if (opt.isEmpty()) {
            return null;
        }
        var anno = opt.get();
        var vOpt = anno.values.stream().filter(v -> v.name.equals("value")).findFirst();
        if (vOpt.isEmpty()) {
            return null;
        }
        var v = vOpt.get().value;
        if (v instanceof String) {
            return (String) v;
        }
        return null;
    }

    public static List<String> getStringListFromAnno(List<AstAnno> annos, Predicate<TypeInfo> check, String fieldName) {
        var opt = annos.stream().filter(a -> a.typeRef != null && check.test(a.typeRef)).findFirst();
        if (opt.isEmpty()) {
            return null;
        }
        var anno = opt.get();
        var vOpt = anno.values.stream().filter(v -> v.name.equals(fieldName)).findFirst();
        if (vOpt.isEmpty()) {
            return null;
        }
        var v = vOpt.get().value;
        if (v instanceof List) {
            //noinspection rawtypes
            var ls = (List) v;
            for (var o : ls) {
                if (!(o instanceof String)) {
                    return null;
                }
            }
            //noinspection unchecked
            return (List<String>) ls;
        }
        return null;
    }

    public static void generateCFunctionImpl(StringBuilder sb, int indent, String impl) {
        var lines = Arrays.stream(impl.replace("\r", "").split("\n")).map(line -> {
            if (line.isBlank()) return "";
            return line;
        }).collect(Collectors.toList());
        int strIndent = -1;
        for (var line : lines) {
            if (line.isBlank())
                continue;
            var chars = line.toCharArray();
            for (int i = 0; i < chars.length; ++i) {
                if (chars[i] == ' ') {
                    continue;
                }
                if (strIndent == -1 || i < strIndent) {
                    strIndent = i;
                }
                break;
            }
        }
        for (int i = 0; i < lines.size(); ++i) {
            var line = lines.get(i);
            if (line.isBlank()) {
                if (i != 0 && i != lines.size() - 1) {
                    sb.append("\n");
                }
            } else {
                if (strIndent > 0) {
                    line = line.substring(strIndent);
                }
                Utils.appendIndent(sb, indent).append(line).append("\n");
            }
        }
    }

    @SuppressWarnings("UnusedReturnValue")
    public static StringBuilder appendJavaPadding(StringBuilder sb, int indent, long padding) {
        Utils.appendIndent(sb, indent)
            .append("MemoryLayout.sequenceLayout(").append(padding).append("L, ValueLayout.JAVA_BYTE) /* padding */");
        return sb;
    }

    public static String metadata(CompilerOptions opts, String version) {
        var sb = new StringBuilder();
        sb.append("// metadata.generator-version: pni ").append(version).append("\n");
        for (var entry : opts.getMetadata().entrySet()) {
            var k = entry.getKey();
            var v = entry.getValue();
            sb.append("// metadata.").append(k).append(": ").append(v).append("\n");
        }
        return sb.toString();
    }

    public static boolean hashesAreTheSame(File file, String hash) {
        if (!file.exists())
            return false;
        if (!file.isFile())
            return false;
        List<String> lines;
        try {
            lines = Files.readAllLines(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException("failed reading file " + file, e);
        }
        if (lines.isEmpty())
            return false;
        String lastLine = null;
        var ite = lines.listIterator(lines.size());
        while (lastLine == null && ite.hasPrevious()) {
            var prev = ite.previous();
            if (prev.isBlank()) {
                continue;
            }
            lastLine = prev.trim();
        }
        if (lastLine == null)
            return false;

        if (!lastLine.startsWith("//")) {
            return false;
        }
        lastLine = lastLine.substring("//".length()).trim();
        if (!lastLine.startsWith("sha256:")) {
            return false;
        }
        lastLine = lastLine.substring("sha256:".length()).trim();
        return lastLine.equals(hash);
    }
}
