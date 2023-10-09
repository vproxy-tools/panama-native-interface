#include "io_vproxy_pni_test_ChildClass.h"

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_ChildClass_xxx(PNIEnv_void * env, ChildClass * self, int16_t x) {
    self->x = x;
    return 0;
}

#ifdef __cplusplus
}
#endif
// metadata.generator-version: pni test
// sha256:e40a99b37929525284ce4de320ace215f01100c848a94b59ba417355c12d2606
