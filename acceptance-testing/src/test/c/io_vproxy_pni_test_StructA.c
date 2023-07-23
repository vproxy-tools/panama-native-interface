#include "io_vproxy_pni_test_StructA.h"

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_StructA_bbb(PNIEnv_void * env, StructA * self, StructB * b) {
    self->b = *b;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_StructA_ccc(PNIEnv_void * env, StructA * self, UnionC * c) {
    self->c = *c;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_StructA_cccPointer(PNIEnv_void * env, StructA * self, UnionC * c) {
    self->cPointer = c;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_StructA_retrieveB(PNIEnv_pointer * env, StructA * self, StructB * return_) {
    *return_ = self->b;
    env->return_ = return_;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_StructA_retrieveC(PNIEnv_pointer * env, StructA * self, UnionC * return_) {
    *return_ = self->c;
    env->return_ = return_;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_StructA_retrieveCPointer(PNIEnv_pointer * env, StructA * self, UnionC * return_) {
    if (self->cPointer == NULL) {
        env->return_ = NULL;
        return 0;
    }
    *return_ = *self->cPointer;
    env->return_ = return_;
    return 0;
}
