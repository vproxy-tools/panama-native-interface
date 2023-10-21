package io.vproxy.pni.exec.extension;

public class ExtensionValidationException extends Exception {
    public ExtensionValidationException() {
        super();
    }

    public ExtensionValidationException(String message) {
        super(message);
    }

    public ExtensionValidationException(Throwable cause) {
        super(cause);
    }

    public ExtensionValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
