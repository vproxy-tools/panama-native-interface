package io.vproxy.pni.sample;

import io.vproxy.pni.annotation.*;

import java.io.IOException;
import java.lang.foreign.MemorySegment;

@Function
interface PNINativeFunctions {
    @Impl(
        // language="c"
        c = """
            int fd = socket(AF_INET, SOCK_STREAM, 0);
            if (fd < 0) {
                return PNIThrowExceptionBasedOnErrno(env, "java.io.IOException");
            }
            env->return_ = fd;
            return 0;
            """
    )
    @LinkerOption.Critical
    int openIPv4TcpSocket() throws IOException;

    @Impl(
        // language="c"
        c = """
            struct sockaddr_in server_addr;
            memset(&server_addr, 0, sizeof(server_addr));
            server_addr.sin_family = AF_INET;
            server_addr.sin_addr.s_addr = htonl(ipv4);
            server_addr.sin_port = htons(port);

            int res = bind(fd, (struct sockaddr *)&server_addr, sizeof(server_addr));
            if (res < 0) {
                return PNIThrowExceptionBasedOnErrno(env, "java.io.IOException");
            }
            return 0;
            """
    )
    @LinkerOption.Critical
    void bindIPv4(int fd, @Name("ipv4") int ipv4HostOrder, int port) throws IOException;

    @Impl(
        // language="c"
        c = """
            int res = listen(fd, n);
            if (res < 0) {
                return PNIThrowExceptionBasedOnErrno(env, "java.io.IOException");
            }
            return 0;
            """
    )
    @LinkerOption.Critical
    void listen(int fd, int n) throws IOException;

    @Impl(
        // language="c"
        c = """
            struct sockaddr_in client_addr;
            socklen_t client_addrlen = sizeof(client_addr);
            int client_sock = accept(fd, (struct sockaddr *)&client_addr, &client_addrlen);
            if (client_sock < 0) {
                return PNIThrowExceptionBasedOnErrno(env, "java.io.IOException");
            }
            env->return_ = client_sock;
            return 0;
            """
    )
    int accept(int fd) throws IOException;

    @Impl(
        include = "<unistd.h>",
        // language="c"
        c = """
            closesocket(fd);
            return 0;
            """
    )
    @LinkerOption.Critical
    void close(int fd);

    @Impl(
        include = "<unistd.h>",
        // language="c"
        c = """
            int n = send(fd, mem + off, len, 0);
            if (n < 0) {
                return PNIThrowExceptionBasedOnErrno(env, "java.io.IOException");
            }
            env->return_ = n;
            return 0;
            """
    )
    int write(int fd, MemorySegment mem, int off, int len) throws IOException;

    @Impl(
        include = "<unistd.h>",
        // language="c"
        c = """
            int n = recv(fd, mem + off, len, 0);
            if (n < 0) {
                return PNIThrowExceptionBasedOnErrno(env, "java.io.IOException");
            }
            env->return_ = n;
            return 0;
            """
    )
    int read(int fd, MemorySegment mem, int off, int len) throws IOException;
}
