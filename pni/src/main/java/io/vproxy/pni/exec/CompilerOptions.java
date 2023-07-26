package io.vproxy.pni.exec;

public class CompilerOptions {
    private final boolean verbose;

    public CompilerOptions(
        boolean verbose
    ) {
        this.verbose = verbose;
    }

    public boolean verbose() {
        return verbose;
    }

    public static CompilerOptions empty() {
        return new CompilerOptions(
            false
        );
    }
}
