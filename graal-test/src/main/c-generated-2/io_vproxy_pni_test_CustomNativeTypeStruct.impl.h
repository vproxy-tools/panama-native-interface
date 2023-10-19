#include "io_vproxy_pni_test_CustomNativeTypeStruct.h"

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_CustomNativeTypeStruct_getP1(PNIEnv_long * env, CustomNativeTypeStruct * self) {
    env->return_ = self->field->p1;
    return 0;
}

#ifdef __cplusplus
}
#endif
// metadata.generator-version: pni test
// sha256:15094ebc88c6c4b8346ae150ee3b24806739f089b43702bb77a6f3a65442d2c4
