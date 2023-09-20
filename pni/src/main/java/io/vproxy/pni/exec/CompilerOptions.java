package io.vproxy.pni.exec;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class CompilerOptions {
    public List<String> classpath;
    public List<Pattern> filters;
    public String javaOutputBaseDirectory;
    public String cOutputDirectory;
    private boolean verbose;
    private final LinkedHashMap<String, String> metadata = new LinkedHashMap<>();

    public CompilerOptions() {
    }

    public void validate() {
        if (classpath == null || classpath.isEmpty()) {
            throw new RuntimeException("missing classpath");
        }
        if (javaOutputBaseDirectory == null) {
            throw new RuntimeException("missing javaOutputBaseDirectory");
        }
        if (cOutputDirectory == null) {
            throw new RuntimeException("missing cOutputDirectory");
        }
    }

    public void initDefaultValues() {
        if (filters == null) {
            filters = new ArrayList<>();
        }
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
