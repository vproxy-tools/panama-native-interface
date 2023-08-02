#include "io_vproxy_pni_test_RawArrays.h"

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_RawArrays_byteArray(PNIEnv_byte * env, char * array, int32_t off) {
    env->return_ = array[off];
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_RawArrays_unsignedByteArray(PNIEnv_byte * env, uint8_t * array, int32_t off) {
    env->return_ = array[off];
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_RawArrays_boolArray(PNIEnv_bool * env, uint8_t * array, int32_t off) {
    env->return_ = array[off];
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_RawArrays_charArray(PNIEnv_char * env, uint16_t * array, int32_t off) {
    env->return_ = array[off];
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_RawArrays_doubleArray(PNIEnv_double * env, double * array, int32_t off) {
    env->return_ = array[off];
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_RawArrays_floatArray(PNIEnv_float * env, float * array, int32_t off) {
    env->return_ = array[off];
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_RawArrays_intArray(PNIEnv_int * env, int32_t * array, int32_t off) {
    env->return_ = array[off];
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_RawArrays_unsignedIntArray(PNIEnv_int * env, uint32_t * array, int32_t off) {
    env->return_ = array[off];
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_RawArrays_longArray(PNIEnv_long * env, int64_t * array, int32_t off) {
    env->return_ = array[off];
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_RawArrays_unsignedLongArray(PNIEnv_long * env, uint64_t * array, int32_t off) {
    env->return_ = array[off];
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_RawArrays_shortArray(PNIEnv_short * env, int16_t * array, int32_t off) {
    env->return_ = array[off];
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_RawArrays_unsignedShortArray(PNIEnv_short * env, uint16_t * array, int32_t off) {
    env->return_ = array[off];
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_RawArrays_structArray(PNIEnv_pointer * env, ObjectStruct * array, int32_t off, ObjectStruct * return_) {
    env->return_ = &array[off];
    return 0;
}

#ifdef __cplusplus
}
#endif
// sha256:d7e32bd49de194a4c4331c7870bbc6f502d0449e0da34b574a6f9e9c60229023
