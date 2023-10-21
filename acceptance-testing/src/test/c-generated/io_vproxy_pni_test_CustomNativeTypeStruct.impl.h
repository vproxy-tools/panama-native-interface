#include "io_vproxy_pni_test_CustomNativeTypeStruct.h"

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_CustomNativeTypeStruct_getP1(PNIEnv_long * env, CustomNativeTypeStruct * self) {
    env->return_ = self->field->p1;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_CustomNativeTypeStruct_getArr(PNIEnv_buf_ptr * env, CustomNativeTypeStruct * self) {
    env->return_.bufLen = ptrPNIBufLen(3);
    env->return_.buf = &self->array;
    return 0;
}

#ifdef __cplusplus
}
#endif
// metadata.generator-version: pni test
// sha256:7958fa6bb6109a91c404703fce933e13cde931e60a3e8bb0a0385d78958b8005
