package io.vproxy.pni.exec.internal;

import io.vproxy.pni.exec.CompilerOptions;
import org.objectweb.asm.ClassReader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarFile;

public class JavaReader {
    private final List<String> classpath;
    private final CompilerOptions opts;
    private final List<ClassReader> classes = new ArrayList<>();

    public JavaReader(List<String> classpath, CompilerOptions opts) {
        this.classpath = classpath;
        this.opts = opts;
    }

    public List<ClassReader> read() {
        for (var cp : classpath) {
            var f = new File(cp);
            if (!f.exists()) {
                continue;
            }
            if (f.isDirectory()) {
                readFile(f);
            } else if (f.getName().equals(".jar")) {
                try {
                    readJar(f);
                } catch (IOException e) {
                    throw new RuntimeException("failed reading jar " + f);
                }
            } else if (f.getName().equals(".class")) {
                readFile(f);
            } else {
                throw new RuntimeException("unknown file " + f.getAbsolutePath());
            }
        }
        return classes;
    }

    private void readJar(File f) throws IOException {
        try (var jar = new JarFile(f)) {
            var e = jar.entries();
            while (e.hasMoreElements()) {
                var entry = e.nextElement();
                if (entry.getName().endsWith(".class")) {
                    readClass(f.getAbsolutePath() + "!" + entry.getName(), jar.getInputStream(entry));
                }
            }
        }
    }

    private void readFile(File f) {
        if (f.isDirectory()) {
            var files = f.listFiles();
            if (files == null) {
                throw new RuntimeException("cannot list files in " + f);
            }
            for (var ff : files) {
                readFile(ff);
            }
        } else {
            try {
                readClass(f.getAbsolutePath(), new FileInputStream(f));
            } catch (FileNotFoundException e) {
                throw new RuntimeException("cannot read content from " + f);
            }
        }
    }

    private void readClass(String filename, InputStream inputStream) {
        PNILogger.debug(opts, "reading class " + filename);
        try {
            classes.add(new ClassReader(inputStream));
        } catch (IOException e) {
            throw new RuntimeException("failed to read class from " + filename, e);
        }
    }
}
