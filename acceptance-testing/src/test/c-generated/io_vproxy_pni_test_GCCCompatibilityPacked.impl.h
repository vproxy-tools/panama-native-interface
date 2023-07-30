#include "io_vproxy_pni_test_GCCCompatibilityPacked.h"

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_GCCCompatibilityPacked_init(PNIEnv_void * env, GCCCompatibilityPacked * self) {
    init_GCCCompatibilityPacked(self);
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_GCCCompatibilityPacked_size(PNIEnv_long * env, GCCCompatibilityPacked * self) {
    env->return_ = sizeof(GCCCompatibilityPacked);
    return 0;
}

#ifdef __cplusplus
}
#endif
// sha256:98076d0530de448618c9df89f26e668c62c13cc8675de0d9048d949490c272b1
