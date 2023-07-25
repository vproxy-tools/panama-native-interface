package io.vproxy.pni.sample;

import io.vproxy.pni.annotation.Function;
import io.vproxy.pni.annotation.Impl;
import io.vproxy.pni.annotation.Name;
import io.vproxy.pni.annotation.Trivial;

import java.io.IOException;
import java.lang.foreign.MemorySegment;

@Function
interface PNINativeFunctions {
    @Impl(
        include = "<sys/socket.h>",
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
    @Trivial
    int openIPv4TcpSocket() throws IOException;

    @Impl(
        include = {
            "<sys/socket.h>",
            "<arpa/inet.h>",
        },
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
    @Trivial
    void bindIPv4(int fd, @Name("ipv4") int ipv4HostOrder, int port) throws IOException;

    @Impl(
        include = "<sys/socket.h>",
        // language="c"
        c = """
            int res = listen(fd, n);
            if (res < 0) {
                return PNIThrowExceptionBasedOnErrno(env, "java.io.IOException");
            }
            return 0;
            """
    )
    @Trivial
    void listen(int fd, int n) throws IOException;

    @Impl(
        include = {
            "<sys/socket.h>",
            "<arpa/inet.h>",
        },
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
            close(fd);
            return 0;
            """
    )
    @Trivial
    void close(int fd);

    @Impl(
        include = "<unistd.h>",
        // language="c"
        c = """
            int n = write(fd, mem + off, len);
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
            int n = read(fd, mem + off, len);
            if (n < 0) {
                return PNIThrowExceptionBasedOnErrno(env, "java.io.IOException");
            }
            env->return_ = n;
            return 0;
            """
    )
    int read(int fd, MemorySegment mem, int off, int len) throws IOException;
}