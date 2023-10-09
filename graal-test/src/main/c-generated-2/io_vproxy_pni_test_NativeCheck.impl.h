#include "io_vproxy_pni_test_NativeCheck.h"

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_NativeCheck_checkUserdataForRef(PNIEnv_void * env, PNIRef * ref, int32_t * x, int64_t * y, int16_t * z) {
    Userdata* ud = ref->userdata;
    *x = ud->x;
    *y = ud->y;
    *z = ud->z;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_NativeCheck_checkUserdataForFunc(PNIEnv_void * env, PNIFunc * func, int32_t * x, int64_t * y, int16_t * z) {
    Userdata* ud = func->userdata;
    *x = ud->x;
    *y = ud->y;
    *z = ud->z;
    return 0;
}

#ifdef __cplusplus
}
#endif
// metadata.generator-version: pni test
// sha256:0b946d5a4f8528ed74a6656e61e6a6751bf7da31b39a2e691bfb89495c00d14b
