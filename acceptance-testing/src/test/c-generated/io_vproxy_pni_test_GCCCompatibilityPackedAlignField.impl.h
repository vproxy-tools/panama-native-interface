#include "io_vproxy_pni_test_GCCCompatibilityPackedAlignField.h"

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_GCCCompatibilityPackedAlignField_init(PNIEnv_void * env, GCCCompatibilityPackedAlignField * self) {
    self->b1 = 4;
    self->s = 5;
    self->n2 = 6;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_GCCCompatibilityPackedAlignField_size(PNIEnv_long * env, GCCCompatibilityPackedAlignField * self) {
    env->return_ = sizeof(GCCCompatibilityPackedAlignField);
    return 0;
}

#ifdef __cplusplus
}
#endif
// sha256:83000221c37c5d927775585d229a0d1e6de49ac5072db2797078ac025b136625
