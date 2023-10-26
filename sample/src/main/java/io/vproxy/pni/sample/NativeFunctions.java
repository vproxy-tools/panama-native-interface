package io.vproxy.pni.sample;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class NativeFunctions {
    private NativeFunctions() {
    }

    private static final NativeFunctions INSTANCE = new NativeFunctions();

    public static NativeFunctions get() {
        return INSTANCE;
    }

    private static final MethodHandle openIPv4TcpSocketMH = PanamaUtils.lookupPNIFunction(new PNILinkOptions().setCritical(true), "Java_io_vproxy_pni_sample_NativeFunctions_openIPv4TcpSocket");

    public int openIPv4TcpSocket(PNIEnv ENV) throws java.io.IOException {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) openIPv4TcpSocketMH.invokeExact(ENV.MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwIf(java.io.IOException.class);
            ENV.throwLast();
        }
        return ENV.returnInt();
    }

    private static final MethodHandle bindIPv4MH = PanamaUtils.lookupPNIFunction(new PNILinkOptions().setCritical(true), "Java_io_vproxy_pni_sample_NativeFunctions_bindIPv4", int.class /* fd */, int.class /* ipv4HostOrder */, int.class /* port */);

    public void bindIPv4(PNIEnv ENV, int fd, int ipv4HostOrder, int port) throws java.io.IOException {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) bindIPv4MH.invokeExact(ENV.MEMORY, fd, ipv4HostOrder, port);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwIf(java.io.IOException.class);
            ENV.throwLast();
        }
    }

    private static final MethodHandle listenMH = PanamaUtils.lookupPNIFunction(new PNILinkOptions().setCritical(true), "Java_io_vproxy_pni_sample_NativeFunctions_listen", int.class /* fd */, int.class /* n */);

    public void listen(PNIEnv ENV, int fd, int n) throws java.io.IOException {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) listenMH.invokeExact(ENV.MEMORY, fd, n);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwIf(java.io.IOException.class);
            ENV.throwLast();
        }
    }

    private static final MethodHandle acceptMH = PanamaUtils.lookupPNIFunction(new PNILinkOptions(), "Java_io_vproxy_pni_sample_NativeFunctions_accept", int.class /* fd */);

    public int accept(PNIEnv ENV, int fd) throws java.io.IOException {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) acceptMH.invokeExact(ENV.MEMORY, fd);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwIf(java.io.IOException.class);
            ENV.throwLast();
        }
        return ENV.returnInt();
    }

    private static final MethodHandle closeMH = PanamaUtils.lookupPNIFunction(new PNILinkOptions().setCritical(true), "Java_io_vproxy_pni_sample_NativeFunctions_close", int.class /* fd */);

    public void close(PNIEnv ENV, int fd) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) closeMH.invokeExact(ENV.MEMORY, fd);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
    }

    private static final MethodHandle writeMH = PanamaUtils.lookupPNIFunction(new PNILinkOptions(), "Java_io_vproxy_pni_sample_NativeFunctions_write", int.class /* fd */, MemorySegment.class /* mem */, int.class /* off */, int.class /* len */);

    public int write(PNIEnv ENV, int fd, MemorySegment mem, int off, int len) throws java.io.IOException {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) writeMH.invokeExact(ENV.MEMORY, fd, (MemorySegment) (mem == null ? MemorySegment.NULL : mem), off, len);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwIf(java.io.IOException.class);
            ENV.throwLast();
        }
        return ENV.returnInt();
    }

    private static final MethodHandle readMH = PanamaUtils.lookupPNIFunction(new PNILinkOptions(), "Java_io_vproxy_pni_sample_NativeFunctions_read", int.class /* fd */, MemorySegment.class /* mem */, int.class /* off */, int.class /* len */);

    public int read(PNIEnv ENV, int fd, MemorySegment mem, int off, int len) throws java.io.IOException {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) readMH.invokeExact(ENV.MEMORY, fd, (MemorySegment) (mem == null ? MemorySegment.NULL : mem), off, len);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwIf(java.io.IOException.class);
            ENV.throwLast();
        }
        return ENV.returnInt();
    }
}
// metadata.generator-version: pni test
// sha256:ed8b6e59c0c0ac383a6764c12f689ee865b6d02472bd7e9188937f6d982c6a09
