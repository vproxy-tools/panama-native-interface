#include "io_vproxy_pni_test_Func.h"
#include <unistd.h>

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_Func_func1(PNIEnv_int * env) {
    env->return_ = 10;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_Func_func2(PNIEnv_void * env) {
    return PNIThrowException(env, "java.io.IOException", "hello");
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_Func_func3(PNIEnv_void * env, char * ex) {
    if (ex == NULL) {
        return 0;
    }
    return PNIThrowException(env, ex, "aaa");
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_Func_write(PNIEnv_int * env, int32_t fd, void * buf, int32_t off, int32_t len) {
    int ret = write(fd, buf + off, len);
    if (ret < 0) {
        return PNIThrowException(env, "java.io.Exception", strerror(errno));
    }
    env->return_ = ret;
    return 0;
}
