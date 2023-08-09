#include "io_vproxy_pni_test_GCCompatibilityNormalContainUnion.h"

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_GCCompatibilityNormalContainUnion_initB(PNIEnv_void * env, GCCompatibilityNormalContainUnion * self) {
    self->b1 = 104;
    self->un.b = 1;
    self->n2 = 105;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_GCCompatibilityNormalContainUnion_initS(PNIEnv_void * env, GCCompatibilityNormalContainUnion * self) {
    self->b1 = 106;
    self->un.s = 2;
    self->n2 = 107;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_GCCompatibilityNormalContainUnion_initN(PNIEnv_void * env, GCCompatibilityNormalContainUnion * self) {
    self->b1 = 108;
    self->un.n = 3;
    self->n2 = 109;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_GCCompatibilityNormalContainUnion_initF(PNIEnv_void * env, GCCompatibilityNormalContainUnion * self) {
    self->b1 = 110;
    self->un.f = 4.0;
    self->n2 = 111;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_GCCompatibilityNormalContainUnion_initD(PNIEnv_void * env, GCCompatibilityNormalContainUnion * self) {
    self->b1 = 112;
    self->un.d = 8.0;
    self->n2 = 113;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_GCCompatibilityNormalContainUnion_initL(PNIEnv_void * env, GCCompatibilityNormalContainUnion * self) {
    self->b1 = 114;
    self->un.l = 9;
    self->n2 = 115;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_GCCompatibilityNormalContainUnion_size(PNIEnv_long * env, GCCompatibilityNormalContainUnion * self) {
    env->return_ = sizeof(GCCompatibilityNormalContainUnion);
    return 0;
}

#ifdef __cplusplus
}
#endif
// metadata.generator-version: pni test
// sha256:739a4b1a98857733019bfe84d18c87d137c4a2090e1f9b2c04ec4d4f7ed70d3e
