#include "io_vproxy_pni_test_GCCCompatibilityArrayZero.h"

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_GCCCompatibilityArrayZero_init(PNIEnv_void * env, GCCCompatibilityArrayZero * self) {
    self->b1 = 102;
    self->n2 = 103;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_GCCCompatibilityArrayZero_size(PNIEnv_long * env, GCCCompatibilityArrayZero * self) {
    env->return_ = sizeof(GCCCompatibilityArrayZero);
    return 0;
}

#ifdef __cplusplus
}
#endif
// metadata.generator-version: pni test
// sha256:5bee2e4ea14d1216ff19f95a5ddbe0cc7fd2ca25366ca2cf816e56b05189948c
