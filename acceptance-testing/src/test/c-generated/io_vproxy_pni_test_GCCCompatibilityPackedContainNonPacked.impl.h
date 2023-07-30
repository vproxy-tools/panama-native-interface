#include "io_vproxy_pni_test_GCCCompatibilityPackedContainNonPacked.h"

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_GCCCompatibilityPackedContainNonPacked_init(PNIEnv_void * env, GCCCompatibilityPackedContainNonPacked * self) {
    self->b1 = 94;
    init_GCCCompatibilityStruct(self->normal);
    self->n2 = 95;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_GCCCompatibilityPackedContainNonPacked_size(PNIEnv_long * env, GCCCompatibilityPackedContainNonPacked * self) {
    env->return_ = sizeof(GCCCompatibilityPackedContainNonPacked);
    return 0;
}

#ifdef __cplusplus
}
#endif
// sha256:94a957ab38fad65b7e815001eb52d45514eb710a68c82ab0f913442a90a2393d
