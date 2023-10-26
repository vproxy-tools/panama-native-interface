#include "io_vproxy_pni_test_KtStructInherit.h"

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_KtStructInherit_retrieveObj(PNIEnv_PrimitiveStruct * env, KtStructInherit * self) {
    env->return_ = &self->obj;
    return 0;
}

#ifdef __cplusplus
}
#endif
// metadata.generator-version: pni test
// sha256:6ca020ce3a2525f675dbf6e29ff6a178f81c0789d9c1cec5d0d5126965dc0c10
