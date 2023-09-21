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

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_StructA_bbbArray(PNIEnv_void * env, StructA * self, PNIBuf_StructB * bArray) {
    JavaCritical_io_vproxy_pni_test_StructA_bbbArrayCritical(self, bArray);
    return 0;
}

JNIEXPORT void JNICALL JavaCritical_io_vproxy_pni_test_StructA_bbbArrayCritical(StructA * self, PNIBuf_StructB * bArray) {
    self->bArray = *bArray;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_StructA_bbbArray2(PNIEnv_void * env, StructA * self, PNIBuf_StructB * bArray) {
    JavaCritical_io_vproxy_pni_test_StructA_bbbArray2Critical(self, bArray);
    return 0;
}

JNIEXPORT void JNICALL JavaCritical_io_vproxy_pni_test_StructA_bbbArray2Critical(StructA * self, PNIBuf_StructB * bArray) {
    memcpy(self->bArray2, bArray->buf, bArray->bufLen);
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_StructA_retrieveB(PNIEnv_StructB * env, StructA * self, StructB * return_) {
    env->return_ = JavaCritical_io_vproxy_pni_test_StructA_retrieveBCritical(self, return_);
    return 0;
}

JNIEXPORT StructB * JNICALL JavaCritical_io_vproxy_pni_test_StructA_retrieveBCritical(StructA * self, StructB * return_) {
    *return_ = self->b;
    return return_;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_StructA_retrieveC(PNIEnv_UnionC * env, StructA * self, UnionC * return_) {
    env->return_ = JavaCritical_io_vproxy_pni_test_StructA_retrieveCCritical(self, return_);
    return 0;
}

JNIEXPORT UnionC * JNICALL JavaCritical_io_vproxy_pni_test_StructA_retrieveCCritical(StructA * self, UnionC * return_) {
    *return_ = self->c;
    return return_;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_StructA_retrieveCPointer(PNIEnv_UnionC * env, StructA * self, UnionC * return_) {
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

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_StructA_retrieveBArray(PNIEnv_buf_StructB * env, StructA * self) {
    JavaCritical_io_vproxy_pni_test_StructA_retrieveBArrayCritical(self, &env->return_);
    return 0;
}

JNIEXPORT PNIBuf_StructB * JNICALL JavaCritical_io_vproxy_pni_test_StructA_retrieveBArrayCritical(StructA * self, PNIBuf_StructB * return_) {
    *return_ = self->bArray;
    return return_;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_StructA_retrieveBArray2(PNIEnv_buf_StructB * env, StructA * self) {
    JavaCritical_io_vproxy_pni_test_StructA_retrieveBArray2Critical(self, &env->return_);
    return 0;
}

JNIEXPORT PNIBuf_StructB * JNICALL JavaCritical_io_vproxy_pni_test_StructA_retrieveBArray2Critical(StructA * self, PNIBuf_StructB * return_) {
    return_->array = self->bArray2;
    return_->bufLen = sizeof(self->bArray2);
    return return_;
}
