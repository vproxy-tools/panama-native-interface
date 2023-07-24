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

#ifdef __cplusplus
}
#endif
// sha256:c5be3bf03333b8ae2bdd2a72732ac587edfdaf0fbe6dbf92ec36e0c8990e73fa
