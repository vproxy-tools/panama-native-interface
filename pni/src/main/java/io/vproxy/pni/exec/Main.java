package io.vproxy.pni.exec;

import io.vproxy.pni.exec.internal.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    private static final String HELP_STR = """
        Usage: java -jar pni.jar <options>
          -cp <path>
                Specify where to find user class files and annotation processors
          -d <directory>               Specify where to place generated class files
          -h <directory>
                Specify where to place generated native header files
          --help, -help, -?            Print this help message
        """.trim();

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println(HELP_STR);
            return;
        }
        var cp = new ArrayList<String>();
        String d = null;
        String h = null;
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
            } else if (a.equals("-v")) {
                verbose = true;
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

        var classReaders = new JavaReader(cp).read();
        var classes = new ASTReader(classReaders).read(verbose);
        for (var cls : classes) {
            new JavaFileWriter(cls).flush(new File(d), verbose);
            new CFileWriter(cls).flush(new File(h), verbose);
            new CImplFileWriter(cls).flush(new File(h), verbose);
        }
        System.out.println("done");
    }
}
