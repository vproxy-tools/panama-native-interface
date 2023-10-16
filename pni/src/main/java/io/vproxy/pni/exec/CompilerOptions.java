package io.vproxy.pni.exec;

import io.vproxy.pni.exec.internal.PNILogger;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.jar.JarFile;
import java.util.regex.Pattern;

public class CompilerOptions {
    public List<File> classpath;
    public List<Pattern> filters;
    public File javaOutputBaseDirectory;
    public File cOutputDirectory;
    private boolean verbose;
    private long warningFlags = WarnType.defaultWarningFlags();
    private long warningAsErrorFlags = WarnType.defaultWarningAsErrorFlags();
    private final Map<CompilationFlag<?>, Object> compilationFlags = new HashMap<>();
    private final LinkedHashMap<String, String> metadata = new LinkedHashMap<>();

    public CompilerOptions() {
    }

    public void validate() throws RuntimeException {
        validate(null);
    }

    public void validate(List<String> errors) throws RuntimeException {
        if (classpath == null || classpath.isEmpty()) {
            var err = "missing classpath";
            if (errors == null) {
                throw new RuntimeException(err);
            } else {
                errors.add(err);
            }
        }
        if (javaOutputBaseDirectory == null) {
            var err = "missing javaOutputBaseDirectory";
            if (errors == null) {
                throw new RuntimeException(err);
            } else {
                errors.add(err);
            }
        }
        if (cOutputDirectory == null) {
            var err = "missing cOutputDirectory";
            if (errors == null) {
                throw new RuntimeException(err);
            } else {
                errors.add(err);
            }
        }

        if (classpath != null) {
            for (var f : classpath) {
                if (!f.exists()) {
                    PNILogger.warn(errors, null, this, WarnType.INVALID_CLASSPATH_FILE, "classpath file not found: " + f);
                }
                if (f.isFile()) {
                    if (!f.getName().endsWith(".jar")) {
                        PNILogger.warn(errors, null, this, WarnType.INVALID_CLASSPATH_FILE, "unable to handle classpath file of this extension: " + f);
                    } else {
                        try (var jar = new JarFile(f)) {
                            var ite = jar.entries();
                            while (ite.hasMoreElements()) {
                                ite.nextElement();
                            }
                        } catch (IOException e) {
                            PNILogger.debug(this, e.toString());
                            PNILogger.warn(errors, null, this, WarnType.INVALID_CLASSPATH_FILE, "invalid classpath file, not a jar file? " + f);
                        }
                    }
                }
            }
        }
        if (javaOutputBaseDirectory != null) {
            var f = javaOutputBaseDirectory;
            if (!f.exists()) {
                var err = "javaOutputBaseDirectory (" + javaOutputBaseDirectory + ") does not exist";
                if (errors == null) {
                    throw new RuntimeException(err);
                } else {
                    errors.add(err);
                }
            } else if (!f.isDirectory()) {
                var err = "javaOutputBaseDirectory (" + javaOutputBaseDirectory + ") is not a directory";
                if (errors == null) {
                    throw new RuntimeException(err);
                } else {
                    errors.add(err);
                }
            }
        }
        if (cOutputDirectory != null) {
            var f = cOutputDirectory;
            if (!f.exists()) {
                var err = "cOutputDirectory (" + cOutputDirectory + ") does not exist";
                if (errors == null) {
                    throw new RuntimeException(err);
                } else {
                    errors.add(err);
                }
            } else if (!f.isDirectory()) {
                var err = "cOutputDirectory (" + cOutputDirectory + ") is not a directory";
                if (errors == null) {
                    throw new RuntimeException(err);
                } else {
                    errors.add(err);
                }
            }
        }
    }

    public void initDefaultValues() {
        if (filters == null) {
            filters = new ArrayList<>();
        }
        warningFlags |= warningAsErrorFlags;
    }

    public CompilerOptions setClasspath(List<String> classpath) {
        var cp = new ArrayList<File>(classpath.size());
        for (var c : classpath) {
            cp.add(new File(c));
        }
        return setClasspathFileList(cp);
    }

    public CompilerOptions setClasspathFileList(List<File> classpath) {
        this.classpath = classpath;
        return this;
    }

    public List<File> getClasspath() {
        return classpath;
    }

    public CompilerOptions setFilters(List<Pattern> filters) {
        this.filters = filters;
        return this;
    }

    public List<Pattern> getFilters() {
        return filters;
    }

    public CompilerOptions setJavaOutputBaseDirectory(String javaOutputBaseDirectory) {
        return setJavaOutputBaseDirectory(new File(javaOutputBaseDirectory));
    }

    public CompilerOptions setJavaOutputBaseDirectory(File javaOutputBaseDirectory) {
        this.javaOutputBaseDirectory = javaOutputBaseDirectory;
        return this;
    }

    public File getJavaOutputBaseDirectory() {
        return javaOutputBaseDirectory;
    }

    public CompilerOptions setCOutputDirectory(String cOutputDirectory) {
        return setCOutputDirectory(new File(cOutputDirectory));
    }

    public CompilerOptions setCOutputDirectory(File cOutputDirectory) {
        this.cOutputDirectory = cOutputDirectory;
        return this;
    }

    public File getCOutputDirectory() {
        return cOutputDirectory;
    }

    public CompilerOptions setVerbose(boolean verbose) {
        this.verbose = verbose;
        return this;
    }

    public boolean isVerbose() {
        return verbose;
    }

    public CompilerOptions setWarningFlags(long warningFlags) {
        this.warningFlags = warningFlags;
        return this;
    }

    public long getWarningFlags() {
        return warningFlags;
    }

    public CompilerOptions setWarningBits(long bits) {
        warningFlags |= bits;
        return this;
    }

    public CompilerOptions unsetWarningBits(long bits) {
        warningFlags &= ~bits;
        return this;
    }

    public CompilerOptions setWarningAsErrorFlags(long warningAsErrorFlags) {
        this.warningAsErrorFlags = warningAsErrorFlags;
        return this;
    }

    public long getWarningAsErrorFlags() {
        return warningAsErrorFlags;
    }

    public CompilerOptions setWarningAsErrorBits(long bits) {
        warningAsErrorFlags |= bits;
        return this;
    }

    public CompilerOptions unsetWarningAsErrorBits(long bits) {
        warningAsErrorFlags &= ~bits;
        return this;
    }

    public <T> CompilerOptions setCompilationFlag(CompilationFlag<T> flag, T value) {
        compilationFlags.put(flag, value);
        return this;
    }

    public <T> CompilerOptions setCompilationFlag(CompilationFlag<T> flag) {
        return setCompilationFlag(flag, null);
    }

    public <T> boolean hasCompilationFlag(CompilationFlag<T> flag) {
        return compilationFlags.containsKey(flag);
    }

    public <T> T getCompilationFlag(CompilationFlag<T> flag) {
        //noinspection unchecked
        return (T) compilationFlags.get(flag);
    }

    public Map<CompilationFlag<?>, Object> getCompilationFlags() {
        return compilationFlags;
    }

    public CompilerOptions putMetadata(String key, String value) {
        this.metadata.put(key, value);
        return this;
    }

    public CompilerOptions putMetadata(Map<String, String> map) {
        this.metadata.putAll(map);
        return this;
    }

    public LinkedHashMap<String, String> getMetadata() {
        return metadata;
    }
}
