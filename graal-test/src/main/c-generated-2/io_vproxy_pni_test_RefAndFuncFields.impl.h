#include "io_vproxy_pni_test_RefAndFuncFields.h"

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_RefAndFuncFields_call(PNIEnv_void * env, RefAndFuncFields * self) {
    PNIFuncInvoke(self->func, self->ref);
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_RefAndFuncFields_call2(PNIEnv_void * env, RefAndFuncFields * self, ObjectStruct * o) {
    PNIFuncInvoke(self->func2, o);
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_RefAndFuncFields_set(PNIEnv_void * env, RefAndFuncFields * self, PNIRef * ref, PNIRef * ref2, PNIRef * ref3, PNIFunc * func, PNIFunc * func2) {
    self->ref = ref;
    self->ref2 = ref2;
    self->ref3 = ref3;
    self->func = func;
    self->func2 = func2;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_RefAndFuncFields_setRaw(PNIEnv_void * env, RefAndFuncFields * self, PNIRef * ref, PNIRef * ref2, PNIRef * ref3) {
    self->ref = ref;
    self->ref2 = ref2;
    self->ref3 = ref3;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_RefAndFuncFields_retrieveRef(PNIEnv_ref * env, RefAndFuncFields * self) {
    env->return_ = self->ref;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_RefAndFuncFields_retrieveRef2(PNIEnv_ref * env, RefAndFuncFields * self) {
    env->return_ = self->ref2;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_RefAndFuncFields_retrieveRef3(PNIEnv_ref * env, RefAndFuncFields * self) {
    env->return_ = self->ref3;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_RefAndFuncFields_retrieveFunc(PNIEnv_func * env, RefAndFuncFields * self) {
    env->return_ = self->func;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_RefAndFuncFields_retrieveFunc2(PNIEnv_func * env, RefAndFuncFields * self) {
    env->return_ = self->func2;
    return 0;
}

#ifdef __cplusplus
}
#endif
// metadata.generator-version: pni test
// sha256:f967200111bc3db9b3b8e63d68750c4c3adfe9a9c012f3b400dde24db11def63
