#include "io_vproxy_pni_sample_NativeFunctions.h"
#include <arpa/inet.h>
#include <sys/socket.h>
#include <unistd.h>

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT int JNICALL Java_io_vproxy_pni_sample_NativeFunctions_openIPv4TcpSocket(PNIEnv_int * env) {
    int fd = socket(AF_INET, SOCK_STREAM, 0);
    if (fd < 0) {
        return PNIThrowExceptionBasedOnErrno(env, "java.io.IOException");
    }
    env->return_ = fd;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_sample_NativeFunctions_bindIPv4(PNIEnv_void * env, int32_t fd, int32_t ipv4, int32_t port) {
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
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_sample_NativeFunctions_listen(PNIEnv_void * env, int32_t fd, int32_t n) {
    int res = listen(fd, n);
    if (res < 0) {
        return PNIThrowExceptionBasedOnErrno(env, "java.io.IOException");
    }
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_sample_NativeFunctions_accept(PNIEnv_int * env, int32_t fd) {
    struct sockaddr_in client_addr;
    socklen_t client_addrlen = sizeof(client_addr);
    int client_sock = accept(fd, (struct sockaddr *)&client_addr, &client_addrlen);
    if (client_sock < 0) {
        return PNIThrowExceptionBasedOnErrno(env, "java.io.IOException");
    }
    env->return_ = client_sock;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_sample_NativeFunctions_close(PNIEnv_void * env, int32_t fd) {
    close(fd);
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_sample_NativeFunctions_write(PNIEnv_int * env, int32_t fd, void * mem, int32_t off, int32_t len) {
    int n = write(fd, mem + off, len);
    if (n < 0) {
        return PNIThrowExceptionBasedOnErrno(env, "java.io.IOException");
    }
    env->return_ = n;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_sample_NativeFunctions_read(PNIEnv_int * env, int32_t fd, void * mem, int32_t off, int32_t len) {
    int n = read(fd, mem + off, len);
    if (n < 0) {
        return PNIThrowExceptionBasedOnErrno(env, "java.io.IOException");
    }
    env->return_ = n;
    return 0;
}

#ifdef __cplusplus
}
#endif
// sha256:435d54adc20f443176cd69bf514f46f500fe9aed9c4efd35ebaaf3ec711b6bb8
