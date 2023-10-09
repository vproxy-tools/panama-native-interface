#include "io_vproxy_pni_test_GCCCompatibilityPackedAlignFieldSmallerAlign.h"

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_GCCCompatibilityPackedAlignFieldSmallerAlign_init(PNIEnv_void * env, GCCCompatibilityPackedAlignFieldSmallerAlign * self) {
    self->b1 = 7;
    self->l = 8;
    self->n2 = 9;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_GCCCompatibilityPackedAlignFieldSmallerAlign_size(PNIEnv_long * env, GCCCompatibilityPackedAlignFieldSmallerAlign * self) {
    env->return_ = sizeof(GCCCompatibilityPackedAlignFieldSmallerAlign);
    return 0;
}

#ifdef __cplusplus
}
#endif
// metadata.generator-version: pni test
// sha256:ef9fbc8918f3cd3a1487687beb13702f89ca121e29360dc334679cd7798f5133
