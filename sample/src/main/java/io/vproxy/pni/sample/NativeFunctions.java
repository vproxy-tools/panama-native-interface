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

    private final MethodHandle openIPv4TcpSocket = PanamaUtils.lookupPNIFunction(true, "Java_io_vproxy_pni_sample_NativeFunctions_openIPv4TcpSocket");

    public int openIPv4TcpSocket(PNIEnv ENV) throws java.io.IOException {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.openIPv4TcpSocket.invokeExact(ENV.MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwIf(java.io.IOException.class);
            ENV.throwLast();
        }
        return ENV.returnInt();
    }

    private final MethodHandle bindIPv4 = PanamaUtils.lookupPNIFunction(true, "Java_io_vproxy_pni_sample_NativeFunctions_bindIPv4", int.class /* fd */, int.class /* ipv4HostOrder */, int.class /* port */);

    public void bindIPv4(PNIEnv ENV, int fd, int ipv4HostOrder, int port) throws java.io.IOException {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.bindIPv4.invokeExact(ENV.MEMORY, fd, ipv4HostOrder, port);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwIf(java.io.IOException.class);
            ENV.throwLast();
        }
    }

    private final MethodHandle listen = PanamaUtils.lookupPNIFunction(true, "Java_io_vproxy_pni_sample_NativeFunctions_listen", int.class /* fd */, int.class /* n */);

    public void listen(PNIEnv ENV, int fd, int n) throws java.io.IOException {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.listen.invokeExact(ENV.MEMORY, fd, n);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwIf(java.io.IOException.class);
            ENV.throwLast();
        }
    }

    private final MethodHandle accept = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_sample_NativeFunctions_accept", int.class /* fd */);

    public int accept(PNIEnv ENV, int fd) throws java.io.IOException {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.accept.invokeExact(ENV.MEMORY, fd);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwIf(java.io.IOException.class);
            ENV.throwLast();
        }
        return ENV.returnInt();
    }

    private final MethodHandle close = PanamaUtils.lookupPNIFunction(true, "Java_io_vproxy_pni_sample_NativeFunctions_close", int.class /* fd */);

    public void close(PNIEnv ENV, int fd) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.close.invokeExact(ENV.MEMORY, fd);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
    }

    private final MethodHandle write = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_sample_NativeFunctions_write", int.class /* fd */, MemorySegment.class /* mem */, int.class /* off */, int.class /* len */);

    public int write(PNIEnv ENV, int fd, MemorySegment mem, int off, int len) throws java.io.IOException {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.write.invokeExact(ENV.MEMORY, fd, (MemorySegment) (mem == null ? MemorySegment.NULL : mem), off, len);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwIf(java.io.IOException.class);
            ENV.throwLast();
        }
        return ENV.returnInt();
    }

    private final MethodHandle read = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_sample_NativeFunctions_read", int.class /* fd */, MemorySegment.class /* mem */, int.class /* off */, int.class /* len */);

    public int read(PNIEnv ENV, int fd, MemorySegment mem, int off, int len) throws java.io.IOException {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.read.invokeExact(ENV.MEMORY, fd, (MemorySegment) (mem == null ? MemorySegment.NULL : mem), off, len);
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
// sha256:009b33794df067e261e0abd0b2ab2ab2e9fc695cb7689fded8dfb4a8c88055b5
