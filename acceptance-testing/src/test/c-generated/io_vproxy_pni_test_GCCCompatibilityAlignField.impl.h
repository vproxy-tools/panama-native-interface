#include "io_vproxy_pni_test_GCCCompatibilityAlignField.h"

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_GCCCompatibilityAlignField_init(PNIEnv_void * env, GCCCompatibilityAlignField * self) {
    self->b1 = 1;
    self->s = 2;
    self->n2 = 3;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_GCCCompatibilityAlignField_size(PNIEnv_long * env, GCCCompatibilityAlignField * self) {
    env->return_ = sizeof(GCCCompatibilityAlignField);
    return 0;
}

#ifdef __cplusplus
}
#endif
// metadata.generator-version: pni test
// sha256:877c576b31c7da67d854d4fe233a893fbd1e9aa52ccf95435ab8fc2f0734516a
