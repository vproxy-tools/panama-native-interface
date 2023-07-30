#include "io_vproxy_pni_test_GCCCompatibilityNonPackedContainPacked.h"

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_GCCCompatibilityNonPackedContainPacked_init(PNIEnv_void * env, GCCCompatibilityNonPackedContainPacked * self) {
    self->b1 = 90;
    init_GCCCompatibilityStruct(self->packed);
    self->n2 = 91;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_GCCCompatibilityNonPackedContainPacked_size(PNIEnv_long * env, GCCCompatibilityNonPackedContainPacked * self) {
    env->return_ = sizeof(GCCCompatibilityNonPackedContainPacked);
    return 0;
}

#ifdef __cplusplus
}
#endif
// sha256:ad40befb7efd9f4ada850f9754c599d382067e32cf47d0de902b01ebe15ed4df
