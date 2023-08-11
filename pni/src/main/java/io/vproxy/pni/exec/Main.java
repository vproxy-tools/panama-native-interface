package io.vproxy.pni.exec;

import io.vproxy.pni.exec.internal.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class Main {
    public static final String VERSION;

    static {
        final String _VERSION = "21.0.0.4"; // _THE_VERSION_
        var testing = System.getProperty("io.vproxy.pni.Testing", "false");
        if (testing.equals("true")) {
            VERSION = "test";
        } else {
            VERSION = _VERSION;
        }
    }

    private static final String HELP_STR = (
        "Usage: java -jar pni.jar <options>\n" +
        "  -cp <path>\n" +
        "        Specify where to find user class files and annotation processors\n" +
        "  -d <directory>               Specify where to place generated class files\n" +
        "  -h <directory>\n" +
        "        Specify where to place generated native header files\n" +
        "  -f <regexp>                  Only generate for selected classes (default .*)\n" +
        "  -M <key>=<value>             Add metadata on generated files\n" +
        "  --help, -help, -?            Print this help message\n" +
        "  -verbose                     Output messages about what the compiler is doing\n" +
        "  --version, -version          Version information\n" +
        "\n" +
        "Note:\n" +
        "  -cp,-f,-M can appear multiple times\n"
    ).trim();

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println(HELP_STR);
            return;
        }
        var cp = new ArrayList<String>();
        var filter = new ArrayList<Pattern>();
        String d = null;
        String h = null;
        LinkedHashMap<String, String> metadata = new LinkedHashMap<>();
        boolean verbose = false;
        for (int i = 0; i < args.length; i++) {
            var a = args[i];
            String next = null;
            if (i + 1 < args.length) {
                next = args[i + 1];
            }
            if (a.equals("--help") || a.equals("-help") || a.equals("-?")) {
                System.out.println(HELP_STR);
                return;
            }
            //noinspection IfCanBeSwitch
            if (a.equals("-cp")) {
                if (next == null) {
                    System.out.println("missing path after -cp");
                    System.exit(1);
                    return;
                }
                ++i;
                Arrays.stream(next.split(File.pathSeparator))
                    .map(String::trim).filter(s -> !s.isEmpty())
                    .forEach(cp::add);
            } else if (a.equals("-d")) {
                if (next == null) {
                    System.out.println("missing directory after -d");
                    System.exit(1);
                    return;
                }
                ++i;
                d = next.trim();
            } else if (a.equals("-h")) {
                if (next == null) {
                    System.out.println("missing directory after -h");
                    System.exit(1);
                    return;
                }
                ++i;
                h = next.trim();
            } else if (a.equals("-f")) {
                if (next == null) {
                    System.out.println("missing regexp after -f");
                    System.exit(1);
                    return;
                }
                ++i;
                filter.add(Pattern.compile(next));
            } else if (a.equals("-M")) {
                if (next == null) {
                    System.out.println("missing k=v after -M");
                    System.exit(1);
                    return;
                }
                ++i;
                if (!next.contains("=")) {
                    System.out.println("missing '=' after -M");
                    System.exit(1);
                    return;
                }
                int index = next.indexOf("=");
                var key = next.substring(index).trim();
                var value = next.substring(index + 1).trim();
                if (key.isEmpty()) {
                    System.out.println("cannot use empty key for metadata");
                    System.exit(1);
                    return;
                }
                metadata.put(key, value);
            } else if (a.equals("-verbose")) {
                verbose = true;
            } else if (a.equals("-version") || a.equals("--version")) {
                System.out.println("pni " + VERSION);
                return;
            } else {
                System.out.println("unexpected argument " + a);
                System.exit(1);
                return;
            }
        }
        var ok = true;
        if (cp.isEmpty()) {
            System.out.println("missing -cp");
            ok = false;
        }
        if (d == null) {
            System.out.println("missing -d");
            ok = false;
        } else {
            var f = new File(d);
            if (!f.exists()) {
                System.out.println("-d " + d + " file not found");
                ok = false;
            } else if (!f.isDirectory()) {
                System.out.println(f + " is not a directory");
                ok = false;
            }
        }
        if (h == null) {
            System.out.println("missing -h");
            ok = false;
        } else {
            var f = new File(h);
            if (!f.exists()) {
                System.out.println("-h " + h + " file not found");
                ok = false;
            } else if (!f.isDirectory()) {
                System.out.println(h + " is not a directory");
                ok = false;
            }
        }
        if (!ok) {
            System.exit(1);
            return;
        }

        var opts = new CompilerOptions(
            verbose, metadata
        );
        var classReaders = new JavaReader(cp).read(opts);
        var classes = new ASTReader(classReaders).read(opts);
        for (var cls : classes) {
            var generate = filter.isEmpty();
            for (var f : filter) {
                if (f.matcher(cls.fullName()).find()) {
                    if (opts.verbose()) {
                        System.out.println(cls.fullName() + " matches filtering rule " + f);
                    }
                    generate = true;
                    break;
                }
            }
            if (!generate) {
                continue;
            }

            new JavaFileWriter(cls).flush(new File(d), opts);
            new CFileWriter(cls).flush(new File(h), opts);
            new CImplFileWriter(cls).flush(new File(h), opts);
        }
        System.out.println("done");
    }
}
