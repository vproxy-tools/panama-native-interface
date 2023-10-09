#include "io_vproxy_pni_test_ChildOfPacked.h"

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_ChildOfPacked_xxx(PNIEnv_void * env, ChildOfPacked * self, int32_t x) {
    self->x = x;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_ChildOfPacked_ooo(PNIEnv_void * env, ChildOfPacked * self, ObjectStruct * o) {
    self->o = *o;
    return 0;
}

#ifdef __cplusplus
}
#endif
// metadata.generator-version: pni test
// sha256:1f5a7a0e7ff466023073bfe9d095f1e036bf6e216a6caef40bb1c3233bfa3836
