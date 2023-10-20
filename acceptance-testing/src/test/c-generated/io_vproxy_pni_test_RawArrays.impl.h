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

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_RawArrays_pointerArray(PNIEnv_pointer * env, void ** array, int32_t off) {
    env->return_ = array[off];
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_RawArrays_pointerArrayNotRaw(PNIEnv_pointer * env, PNIBuf_ptr * array, int32_t off) {
    void ** arr = array->buf;
    env->return_ = arr[off];
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
// sha256:ae1eb3407217c9ea0fb84e57fb3364579296e40f396872f7a76fba27c1d3f0a5
