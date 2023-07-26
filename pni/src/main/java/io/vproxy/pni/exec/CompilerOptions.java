package io.vproxy.pni.exec;

public record CompilerOptions(
    boolean verbose
) {
    public static CompilerOptions empty() {
        return new CompilerOptions(
            false
        );
    }
}
