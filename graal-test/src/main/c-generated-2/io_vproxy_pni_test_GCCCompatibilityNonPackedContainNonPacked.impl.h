#include "io_vproxy_pni_test_GCCCompatibilityNonPackedContainNonPacked.h"

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_GCCCompatibilityNonPackedContainNonPacked_init(PNIEnv_void * env, GCCCompatibilityNonPackedContainNonPacked * self) {
    self->b1 = 92;
    init_GCCCompatibilityStruct(self->normal);
    self->n2 = 93;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_GCCCompatibilityNonPackedContainNonPacked_size(PNIEnv_long * env, GCCCompatibilityNonPackedContainNonPacked * self) {
    env->return_ = sizeof(GCCCompatibilityNonPackedContainNonPacked);
    return 0;
}

#ifdef __cplusplus
}
#endif
// metadata.generator-version: pni test
// sha256:122936ffd6b6e16faa5118ae21e60e47c7386f53d037d97c020ffecbe3dd4070
