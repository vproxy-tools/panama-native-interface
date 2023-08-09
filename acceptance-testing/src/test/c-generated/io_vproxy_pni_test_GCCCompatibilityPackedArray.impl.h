#include "io_vproxy_pni_test_GCCCompatibilityPackedArray.h"

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_GCCCompatibilityPackedArray_init(PNIEnv_void * env, GCCCompatibilityPackedArray * self) {
    self->b1 = 98;
    init_GCCCompatibilityStruct(self->array[0]);
    init_GCCCompatibilityStruct(self->array[1]);
    self->n2 = 99;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_GCCCompatibilityPackedArray_size(PNIEnv_long * env, GCCCompatibilityPackedArray * self) {
    env->return_ = sizeof(GCCCompatibilityPackedArray);
    return 0;
}

#ifdef __cplusplus
}
#endif
// metadata.generator-version: pni test
// sha256:06a00b92276acdac1abcba5ae438d496e0cba8fc9eaf8bb978961e9846c65ca6
