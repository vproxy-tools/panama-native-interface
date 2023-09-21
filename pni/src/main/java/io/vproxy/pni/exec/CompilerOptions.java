package io.vproxy.pni.exec;

import io.vproxy.pni.exec.internal.PNILogger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.JarFile;
import java.util.regex.Pattern;

public class CompilerOptions {
    public List<String> classpath;
    public List<Pattern> filters;
    public String javaOutputBaseDirectory;
    public String cOutputDirectory;
    private boolean verbose;
    private long warningFlags = WarnType.defaultWarningFlags();
    private long warningAsErrorFlags = WarnType.defaultWarningAsErrorFlags();
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
            for (var c : classpath) {
                var f = new File(c);
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
            var f = new File(javaOutputBaseDirectory);
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
            var f = new File(cOutputDirectory);
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
        this.classpath = classpath;
        return this;
    }

    public List<String> getClasspath() {
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
        this.javaOutputBaseDirectory = javaOutputBaseDirectory;
        return this;
    }

    public String getJavaOutputBaseDirectory() {
        return javaOutputBaseDirectory;
    }

    public CompilerOptions setCOutputDirectory(String cOutputDirectory) {
        this.cOutputDirectory = cOutputDirectory;
        return this;
    }

    public String getCOutputDirectory() {
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

    public void setWarningBits(long bits) {
        warningFlags |= bits;
    }

    public void unsetWarningBits(long bits) {
        warningFlags &= ~bits;
    }

    public CompilerOptions setWarningAsErrorFlags(long warningAsErrorFlags) {
        this.warningAsErrorFlags = warningAsErrorFlags;
        return this;
    }

    public long getWarningAsErrorFlags() {
        return warningAsErrorFlags;
    }

    public void setWarningAsErrorBits(long bits) {
        warningAsErrorFlags |= bits;
    }

    public void unsetWarningAsErrorBits(long bits) {
        warningAsErrorFlags &= ~bits;
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
