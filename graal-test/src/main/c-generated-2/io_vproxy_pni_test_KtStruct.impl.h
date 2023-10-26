#include "io_vproxy_pni_test_KtStruct.h"

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_KtStruct_retrieveInt(PNIEnv_int * env, KtStruct * self) {
    env->return_ = self->aInt;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_KtStruct_retrieveLong(PNIEnv_long * env, KtStruct * self) {
    env->return_ = self->aLong;
    return 0;
}

#ifdef __cplusplus
}
#endif
// metadata.generator-version: pni test
// sha256:aaa1d2f8b008e749b634c04eb0b0c450d8c1f45f2d186bfe57e25e6d1367ee38
