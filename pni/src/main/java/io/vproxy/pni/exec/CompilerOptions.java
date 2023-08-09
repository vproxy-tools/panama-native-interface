package io.vproxy.pni.exec;

import java.util.LinkedHashMap;

public class CompilerOptions {
    private final boolean verbose;
    private final LinkedHashMap<String, String> metadata = new LinkedHashMap<>();

    public CompilerOptions(
        boolean verbose,
        LinkedHashMap<String, String> metadata
    ) {
        this.verbose = verbose;
        this.metadata.putAll(metadata);
    }

    public boolean verbose() {
        return verbose;
    }

    public static CompilerOptions empty() {
        return new CompilerOptions(
            false,
            new LinkedHashMap<>()
        );
    }

    public LinkedHashMap<String, String> metadata() {
        return metadata;
    }
}
