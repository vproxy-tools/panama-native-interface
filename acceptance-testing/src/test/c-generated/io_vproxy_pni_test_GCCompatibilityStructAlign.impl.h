#include "io_vproxy_pni_test_GCCompatibilityStructAlign.h"

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_GCCompatibilityStructAlign_init(PNIEnv_void * env, GCCompatibilityStructAlign * self) {
    self->n = 1;
    self->l = 2;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_GCCompatibilityStructAlign_size(PNIEnv_long * env, GCCompatibilityStructAlign * self) {
    env->return_ = sizeof(GCCompatibilityStructAlign);
    return 0;
}

#ifdef __cplusplus
}
#endif
// metadata.generator-version: pni test
// sha256:ceb48338897495c9a8ddc116353d92aba7703aee2ed8c2e7b018197c9a0ea317
