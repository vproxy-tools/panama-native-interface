#include "io_vproxy_pni_test_GCCCompatibilityAlignFieldPacked.h"

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_GCCCompatibilityAlignFieldPacked_init(PNIEnv_void * env, GCCCompatibilityAlignFieldPacked * self) {
    self->b1 = 96;
    init_GCCCompatibilityStruct(self->packed);
    self->n2 = 97;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_GCCCompatibilityAlignFieldPacked_size(PNIEnv_long * env, GCCCompatibilityAlignFieldPacked * self) {
    env->return_ = sizeof(GCCCompatibilityAlignFieldPacked);
    return 0;
}

#ifdef __cplusplus
}
#endif
// sha256:b81b1cb768814a034fffbad0b7ad9290db1df44a151652c6288e28e5e1369e6c
