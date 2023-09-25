package io.vproxy.pni.exec;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class Main {
    public static final String VERSION;
    public static final String JAVA_GEN_VERSION;
    public static final String C_GEN_VERSION;

    static {
        final String _VERSION = "21.0.0.14-dev"; // _THE_VERSION_
        final String _JAVA_GEN_VERSION = "21.0.0.12";
        final String _C_GEN_VERSION = "21.0.0.14";
        var testing = System.getProperty("io.vproxy.pni.Testing", "false");
        if (testing.equals("true")) {
            JAVA_GEN_VERSION = "test";
            C_GEN_VERSION = "test";
        } else {
            JAVA_GEN_VERSION = _JAVA_GEN_VERSION;
            C_GEN_VERSION = _C_GEN_VERSION;
        }
        VERSION = _VERSION;
    }

    @SuppressWarnings("ConcatenationWithEmptyString")
    private static final String HELP_STR = (
        "Usage: java -jar pni.jar <options>\n" +
        "  -cp <path>\n" +
        "        Specify where to find user class files and annotation processors\n" +
        "  -d <directory>               Specify where to place generated class files\n" +
        "  -h <directory>\n" +
        "        Specify where to place generated native header files\n" +
        "  -F <regexp>                  Only generate for selected classes (default .*)\n" +
        "  -M <key>=<value>             Add metadata on generated files\n" +
        "  --help, -help, -?            Print this help message\n" +
        "  -verbose                     Output messages about what the compiler is doing\n" +
        "  --version, -version          Version information\n" +
        "\n" +
        "  -w                           Inhibit all warning messages\n" +
        "  -W<warning>                  Enable the specified warning\n" +
        "  -Wno-<warning>               Disable the specified warning\n" +
        "  -Werror                      Make all warnings into errors\n" +
        "  -Werror=                     Make the specified warning into an error\n" +
        "  -Wno-error=                  Disable error of a warning\n" +
        "\n" +
        "Note:\n" +
        "  -cp,-F,-M,-W can appear multiple times\n" +
        ""
    ).trim();

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println(HELP_STR);
            return;
        }
        var opts = new CompilerOptions();
        var cp = new ArrayList<String>();
        var filter = new ArrayList<Pattern>();
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
                opts.setJavaOutputBaseDirectory(next.trim());
            } else if (a.equals("-h")) {
                if (next == null) {
                    System.out.println("missing directory after -h");
                    System.exit(1);
                    return;
                }
                ++i;
                opts.setCOutputDirectory(next.trim());
            } else if (a.equals("-F")) {
                if (next == null) {
                    System.out.println("missing regexp after -F");
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
                System.out.println("gen.java " + JAVA_GEN_VERSION);
                System.out.println("gen.c " + C_GEN_VERSION);
                return;
            } else if (a.equals("-w")) {
                opts.setWarningFlags(0);
            } else if (a.startsWith("-W")) {
                if (a.startsWith("-Wno-")) {
                    var name = a.substring("-Wno-".length()).trim();
                    var w = getWarningByNameOrExit(name);
                    opts.unsetWarningBits(w.flag);
                } else if (a.equals("-Werror")) {
                    opts.setWarningAsErrorBits(opts.getWarningFlags());
                } else if (a.startsWith("-Werror=")) {
                    var name = a.substring("-Werror=".length()).trim();
                    var w = getWarningByNameOrExit(name);
                    opts.setWarningAsErrorBits(w.flag);
                } else if (a.startsWith("-Wno-error=")) {
                    var name = a.substring("-Wno-error=".length()).trim();
                    var w = getWarningByNameOrExit(name);
                    opts.unsetWarningAsErrorBits(w.flag);
                } else {
                    var name = a.substring("-W".length()).trim();
                    var w = getWarningByNameOrExit(name);
                    opts.setWarningBits(w.flag);
                }
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
        if (!ok) {
            System.exit(1);
            return;
        }

        opts.setClasspath(cp)
            .setFilters(filter)
            .setVerbose(verbose)
            .putMetadata(metadata);

        var errors = new ArrayList<String>();
        opts.validate(errors);
        if (!errors.isEmpty()) {
            for (var e : errors) {
                System.out.println(e);
            }
            System.exit(1);
            return;
        }

        new Generator(opts).generate();
        System.out.println("done");
    }

    private static WarnType getWarningByNameOrExit(String name) {
        var w = WarnType.searchForWarnTypeByName(name);
        if (w == null) {
            System.out.println("unknown warning " + name);
            System.exit(1);
            throw new RuntimeException("should not reach here");
        }
        return w;
    }
}
