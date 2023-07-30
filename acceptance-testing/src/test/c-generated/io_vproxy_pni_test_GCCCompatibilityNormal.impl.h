#include "io_vproxy_pni_test_GCCCompatibilityNormal.h"

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_GCCCompatibilityNormal_init(PNIEnv_void * env, GCCCompatibilityNormal * self) {
    init_GCCCompatibilityNormal(self);
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_GCCCompatibilityNormal_size(PNIEnv_long * env, GCCCompatibilityNormal * self) {
    env->return_ = sizeof(GCCCompatibilityNormal);
    return 0;
}

#ifdef __cplusplus
}
#endif
// sha256:4cde761d103921534abd91f8a590686c7d59c0c28bdda72ecb77255a02450c25
