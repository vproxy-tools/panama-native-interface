#include "io_vproxy_pni_test_GrandChildClass.h"

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_GrandChildClass_yyy(PNIEnv_void * env, GrandChildClass * self, int64_t y) {
    self->y = y;
    return 0;
}

#ifdef __cplusplus
}
#endif
// metadata.generator-version: pni test
// sha256:f97130190d242be4abfcbf6265bffff7aa341c3d5d7ff0c42d98bf15e35d6b7d
