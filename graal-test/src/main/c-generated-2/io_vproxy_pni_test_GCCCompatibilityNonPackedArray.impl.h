#include "io_vproxy_pni_test_GCCCompatibilityNonPackedArray.h"

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_GCCCompatibilityNonPackedArray_init(PNIEnv_void * env, GCCCompatibilityNonPackedArray * self) {
    self->b1 = 100;
    init_GCCCompatibilityStruct(self->array[0]);
    init_GCCCompatibilityStruct(self->array[1]);
    self->n2 = 101;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_GCCCompatibilityNonPackedArray_size(PNIEnv_long * env, GCCCompatibilityNonPackedArray * self) {
    env->return_ = sizeof(GCCCompatibilityNonPackedArray);
    return 0;
}

#ifdef __cplusplus
}
#endif
// metadata.generator-version: pni test
// sha256:6dc3f75dccb0a1880a044bb0846c06c5a2523233c39cc22f28f4984b18ffda79
