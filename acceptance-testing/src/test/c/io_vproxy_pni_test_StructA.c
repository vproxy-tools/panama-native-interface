#include "io_vproxy_pni_test_StructA.h"

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_StructA_bbb(PNIEnv_void * env, StructA * self, StructB * b) {
    JavaCritical_io_vproxy_pni_test_StructA_bbbCritical(self, b);
    return 0;
}

JNIEXPORT void JNICALL JavaCritical_io_vproxy_pni_test_StructA_bbbCritical(StructA * self, StructB * b) {
    self->b = *b;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_StructA_ccc(PNIEnv_void * env, StructA * self, UnionC * c) {
    JavaCritical_io_vproxy_pni_test_StructA_cccCritical(self, c);
    return 0;
}

JNIEXPORT void JNICALL JavaCritical_io_vproxy_pni_test_StructA_cccCritical(StructA * self, UnionC * c) {
    self->c = *c;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_StructA_cccPointer(PNIEnv_void * env, StructA * self, UnionC * c) {
    JavaCritical_io_vproxy_pni_test_StructA_cccPointerCritical(self, c);
    return 0;
}

JNIEXPORT void JNICALL JavaCritical_io_vproxy_pni_test_StructA_cccPointerCritical(StructA * self, UnionC * c) {
    self->cPointer = c;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_StructA_retrieveB(PNIEnv_pointer * env, StructA * self, StructB * return_) {
    env->return_ = JavaCritical_io_vproxy_pni_test_StructA_retrieveBCritical(self, return_);
    return 0;
}

JNIEXPORT StructB * JNICALL JavaCritical_io_vproxy_pni_test_StructA_retrieveBCritical(StructA * self, StructB * return_) {
    *return_ = self->b;
    return return_;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_StructA_retrieveC(PNIEnv_pointer * env, StructA * self, UnionC * return_) {
    env->return_ = JavaCritical_io_vproxy_pni_test_StructA_retrieveCCritical(self, return_);
    return 0;
}

JNIEXPORT UnionC * JNICALL JavaCritical_io_vproxy_pni_test_StructA_retrieveCCritical(StructA * self, UnionC * return_) {
    *return_ = self->c;
    return return_;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_StructA_retrieveCPointer(PNIEnv_pointer * env, StructA * self, UnionC * return_) {
    env->return_ = JavaCritical_io_vproxy_pni_test_StructA_retrieveCPointerCritical(self, return_);
    return 0;
}

JNIEXPORT UnionC * JNICALL JavaCritical_io_vproxy_pni_test_StructA_retrieveCPointerCritical(StructA * self, UnionC * return_) {
    if (self->cPointer == NULL) {
        return NULL;
    }
    *return_ = *self->cPointer;
    return return_;
}
