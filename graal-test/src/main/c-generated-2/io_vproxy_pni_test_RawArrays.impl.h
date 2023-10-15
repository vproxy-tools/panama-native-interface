#include "io_vproxy_pni_test_RawArrays.h"

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_RawArrays_byteArray(PNIEnv_byte * env, void * array, int32_t off) {
    env->return_ = ((int8_t*) array)[off];
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

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_RawArrays_structArray(PNIEnv_ObjectStruct * env, ObjectStruct * array, int32_t off, ObjectStruct * return_) {
    env->return_ = &array[off];
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_RawArrays_structArrayNotRaw(PNIEnv_ObjectStruct * env, PNIBuf_ObjectStruct * array, int32_t off, ObjectStruct * return_) {
    ObjectStruct* arr = array->buf;
    env->return_ = &arr[off];
    return 0;
}

#ifdef __cplusplus
}
#endif
// metadata.generator-version: pni test
// sha256:bf681487dffbae9f8c7f0a9ea1cb62e0e0ea0f4cf393e42a17ab2a6363fe47ab