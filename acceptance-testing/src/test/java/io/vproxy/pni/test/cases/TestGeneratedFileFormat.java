package io.vproxy.pni.test.cases;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
}
