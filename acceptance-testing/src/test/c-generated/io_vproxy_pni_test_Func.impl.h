#include "io_vproxy_pni_test_Func.h"
#include <unistd.h>

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_Func_write(PNIEnv_int * env, int32_t fd, void * buf, int32_t off, int32_t len) {
    int ret = write(fd, buf + off, len);
    if (ret < 0) {
        return PNIThrowException(env, "java.io.Exception", strerror(errno));
    }
    env->return_ = ret;
    return 0;
}

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_pni_test_Func_writeCritical(int32_t fd, void * buf, int32_t off, int32_t len) {
    int n = write(fd, buf + off, len);
    if (n < 0) {
        return -errno;
    }
    return n;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_Func_writeWithErrno(PNIEnv_int * env, int32_t fd, void * buf, int32_t off, int32_t len) {
    int ret = write(fd, buf + off, len);
    if (ret < 0) {
        PNIStoreErrno(env);
    }
    env->return_ = ret;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_Func_writeByteArray(PNIEnv_int * env, int32_t fd, void * buf, int32_t off, int32_t len) {
    int ret = write(fd, buf + off, len);
    if (ret < 0) {
        return PNIThrowException(env, "java.io.Exception", strerror(errno));
    }
    env->return_ = ret;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_Func_callJavaFromC(PNIEnv_pointer * env, PNIFunc * func) {
    ObjectStruct object_struct;
    PNIFuncInvoke(func, &object_struct);
    env->return_ = object_struct.seg;
    PNIFuncRelease(func);
    return 0;
}

JNIEXPORT void * JNICALL JavaCritical_io_vproxy_pni_test_Func_callJavaFromCCritical(PNIFunc * func) {
    ObjectStruct object_struct;
    PNIFuncInvoke(func, &object_struct);
    void* ptr = object_struct.seg;
    PNIFuncRelease(func);
    return ptr;
}

#ifdef __cplusplus
}
#endif
// metadata.generator-version: pni test
// sha256:5dbf609163d3f0e62dd1e04fa351071596ff89a8f807498963cd70cdad2e3b0c
