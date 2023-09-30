package io.vproxy.pni.test.cases;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TestGeneratedFileFormat {
    private static void check(List<String> errors, String file, List<String> lines) {
        if (lines.isEmpty()) {
            return;
        }
        if (!file.endsWith(".java") && !file.endsWith(".h")) {
            return;
        }
        for (int i = 0; i < lines.size(); ++i) {
            var l = lines.get(i);
            String next = (i < lines.size() - 1) ? lines.get(i + 1) : null;
            if (next != null) {
                if (l.isBlank() && next.isBlank()) {
                    errors.add(file + ": consecutive blank lines at " + (i + 1));
                }
            }
            if (l.isBlank() && !l.isEmpty()) {
                errors.add(file + ": blank but non-empty line at " + (i + 1));
            }
            if (!l.isBlank()) {
                var chars = l.toCharArray();
                int idx = 0;
                for (; idx < chars.length; ++idx) {
                    var c = chars[idx];
                    if (Character.isWhitespace(c)) {
                        continue;
                    }
                    break;
                }
                for (; idx < chars.length; ++idx) {
                    var c = chars[idx];
                    var nx = (idx < chars.length - 1) ? chars[idx + 1] : '\0';
                    if (nx != 0) {
                        if (Character.isWhitespace(c) && Character.isWhitespace(nx)) {
                            errors.add(file + ": consecutive whitespace characters at " + (i + 1) + ":" + (idx + 1));
                        }
                    } else {
                        if (Character.isWhitespace(c)) {
                            errors.add(file + ": ending with whitespace character at " + (i + 1) + ":" + (idx + 1));
                        }
                    }
                }
            }
        }
        if (file.endsWith(".java")) {
            checkIndent(errors, file, lines);
        }
    }

    private static final Map<String, String> braceMapping = Map.of(
        "}", "{",
        ")", "("
    );

    private static void checkIndent(List<String> errors, String file, List<String> lines) {
        List<String> stack = new ArrayList<>();
        for (int i = 0; i < lines.size(); ++i) {
            var cs = lines.get(i).toCharArray();
            if (cs.length == 0) {
                continue;
            }
            int indent = 0;
            for (var c : cs) {
                if (c != ' ') {
                    break;
                }
                ++indent;
            }
            if (indent != expectedIndent(stack)) {
                if (cs.length > indent && !braceMapping.containsKey("" + cs[indent])) {
                    errors.add(file + ": expecting indent " + expectedIndent(stack) + ", but got " + indent + " at " + (i + 1) + ":" + (indent + 1));
                }
            }
            int state = 0; // 0:normal, 1:string, 2:\ in string
            for (int idx = 0; idx < cs.length; idx++) {
                var c = cs[idx];
                if (state == 0) {
                    if (c == '{') {
                        stack.add("{");
                    } else if (c == '(') {
                        stack.add("(");
                    } else if (c == '}' || c == ')') {
                        if (stack.isEmpty() || !stack.getLast().equals(braceMapping.get("" + c))) {
                            errors.add(file + ": invalid file at " + (i + 1) + ":" + (idx + 1));
                            return;
                        }
                        stack.removeLast();
                    } else if (c == '"') {
                        state = 1;
                    }
                } else if (state == 1) {
                    if (c == '\\') {
                        state = 2;
                    } else if (c == '"') {
                        state = 0;
                    }
                } else {
                    state = 1;
                }
            }
        }
    }

    private static int expectedIndent(List<String> stack) {
        return stack.size() * 4;
    }

    private static void recursiveCheck(File f, List<String> errors) throws IOException {
        if (f.isFile()) {
            check(errors, f.getAbsolutePath(), Files.readAllLines(f.toPath()));
        } else if (f.isDirectory()) {
            var files = f.listFiles();
            assert files != null;
            for (var ff : files) {
                recursiveCheck(ff, errors);
            }
        } else {
            throw new IOException("unknown file " + f.getAbsolutePath());
        }
    }

    @Test
    public void check() throws IOException {
        var errors = new ArrayList<String>();
        var p = Path.of("src", "test", "generated");
        recursiveCheck(p.toFile(), errors);
        p = Path.of("src", "test", "c-generated");
        recursiveCheck(p.toFile(), errors);

        if (errors.isEmpty()) {
            return;
        }
        fail(errors.stream().collect(Collectors.joining("\n", "\n", "")));
    }

    @Test
    public void verifyCheckFunctionIsOk() {
        var errors = new ArrayList<String>();
        check(errors, "a.java", List.of(
            "",
            ""
        ));
        assertEquals(List.of("a.java: consecutive blank lines at 1"), errors);
        errors.clear();

        check(errors, "a.java", List.of(
            "   "
        ));
        assertEquals(List.of("a.java: blank but non-empty line at 1"), errors);
        errors.clear();

        check(errors, "a.java", List.of(
            "a  b"
        ));
        assertEquals(List.of("a.java: consecutive whitespace characters at 1:2"), errors);
        errors.clear();

        check(errors, "a.java", List.of(
            "a "
        ));
        assertEquals(List.of("a.java: ending with whitespace character at 1:2"), errors);
        errors.clear();

        check(errors, "a.java", List.of(
            "class A {",
            "        public int x;" +
            "}"
        ));
        assertEquals(List.of("a.java: expecting indent 4, but got 8 at 2:9"), errors);
        errors.clear();

        check(errors, "a.java", List.of(
            "}"
        ));
        assertEquals(List.of("a.java: invalid file at 1:1"), errors);
        errors.clear();
    }
}
