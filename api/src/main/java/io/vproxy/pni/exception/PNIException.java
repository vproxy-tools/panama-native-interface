package io.vproxy.pni.exception;

public class PNIException extends RuntimeException {
    public PNIException(String msg) {
        super(msg);
    }

    public PNIException(Throwable t) {
        super(t);
    }

    public PNIException(String msg, Throwable t) {
        super(msg, t);
    }
}
