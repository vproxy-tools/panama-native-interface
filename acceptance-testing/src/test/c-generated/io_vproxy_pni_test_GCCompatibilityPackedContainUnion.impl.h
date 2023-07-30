#include "io_vproxy_pni_test_GCCompatibilityPackedContainUnion.h"

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_GCCompatibilityPackedContainUnion_initB(PNIEnv_void * env, GCCompatibilityPackedContainUnion * self) {
    self->b1 = 116;
    self->un.b = 1;
    self->n2 = 117;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_GCCompatibilityPackedContainUnion_initS(PNIEnv_void * env, GCCompatibilityPackedContainUnion * self) {
    self->b1 = 118;
    self->un.s = 2;
    self->n2 = 119;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_GCCompatibilityPackedContainUnion_initN(PNIEnv_void * env, GCCompatibilityPackedContainUnion * self) {
    self->b1 = 120;
    self->un.n = 3;
    self->n2 = 121;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_GCCompatibilityPackedContainUnion_initF(PNIEnv_void * env, GCCompatibilityPackedContainUnion * self) {
    self->b1 = 122;
    self->un.f = 4.0;
    self->n2 = 123;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_GCCompatibilityPackedContainUnion_initD(PNIEnv_void * env, GCCompatibilityPackedContainUnion * self) {
    self->b1 = 124;
    self->un.d = 8.0;
    self->n2 = 125;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_GCCompatibilityPackedContainUnion_initL(PNIEnv_void * env, GCCompatibilityPackedContainUnion * self) {
    self->b1 = 126;
    self->un.l = 9;
    self->n2 = 127;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_GCCompatibilityPackedContainUnion_size(PNIEnv_long * env, GCCompatibilityPackedContainUnion * self) {
    env->return_ = sizeof(GCCompatibilityPackedContainUnion);
    return 0;
}

#ifdef __cplusplus
}
#endif
// sha256:048e85e3fdbfdabcc73a038da4df3cbb046e5bbb957054c1d0bc4d4d2b938dfb
