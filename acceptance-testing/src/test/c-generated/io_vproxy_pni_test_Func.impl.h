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

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_Func_callJavaFromC(PNIEnv_pointer * env, PNIFunc * func) {
    ObjectStruct object_struct;
    PNIFuncInvoke(func, &object_struct);
    env->return_ = object_struct.seg;
    PNIFuncRelease(func);
    return 0;
}

#ifdef __cplusplus
}
#endif
// sha256:dcf9a6599748fe3328f06080b29c0593bd1b64c323a35d041cf78cba89decde8
