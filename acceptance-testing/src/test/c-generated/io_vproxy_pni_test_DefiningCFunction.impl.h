#include "io_vproxy_pni_test_DefiningCFunction.h"

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_DefiningCFunction_upcallVoidNoParam(PNIEnv_void * env, void * func) {
    void (*ff)() = func;
    ff();
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_DefiningCFunction_upcallVoid1Param(PNIEnv_void * env, void * func, void * data) {
    void (*ff)(void*) = func;
    ff(data);
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_DefiningCFunction_upcallVoid2Param(PNIEnv_void * env, void * func, void * data, int8_t b) {
    void (*ff)(void*,int8_t) = func;
    ff(data, b);
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_DefiningCFunction_upcallVoid3Param(PNIEnv_void * env, void * func, void * data, uint8_t z, uint16_t c) {
    void (*ff)(void*,uint8_t,uint16_t) = func;
    ff(data, z, c);
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_DefiningCFunction_upcallVoid4Param(PNIEnv_void * env, void * func, void * data, double d, float f, int32_t i) {
    void (*ff)(void*,double,float,int32_t) = func;
    ff(data, d, f, i);
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_DefiningCFunction_upcallVoid3Param2(PNIEnv_void * env, void * func, void * data, int64_t l, int16_t s) {
    void (*ff)(void*,int64_t,int16_t) = func;
    ff(data, l, s);
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_DefiningCFunction_upcallReturnByteNoParam(PNIEnv_byte * env, void * func) {
    int8_t (*ff)() = func;
    env->return_ = ff();
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_DefiningCFunction_upcallReturnBoolNoParam(PNIEnv_bool * env, void * func) {
    uint8_t (*ff)() = func;
    env->return_ = ff();
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_DefiningCFunction_upcallReturnCharNoParam(PNIEnv_char * env, void * func) {
    uint16_t (*ff)() = func;
    env->return_ = ff();
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_DefiningCFunction_upcallReturnDoubleNoParam(PNIEnv_double * env, void * func) {
    double (*ff)() = func;
    env->return_ = ff();
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_DefiningCFunction_upcallReturnFloatNoParam(PNIEnv_float * env, void * func) {
    float (*ff)() = func;
    env->return_ = ff();
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_DefiningCFunction_upcallReturnIntNoParam(PNIEnv_int * env, void * func) {
    int32_t (*ff)() = func;
    env->return_ = ff();
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_DefiningCFunction_upcallReturnLongNoParam(PNIEnv_long * env, void * func) {
    int64_t (*ff)() = func;
    env->return_ = ff();
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_DefiningCFunction_upcallReturnShortNoParam(PNIEnv_short * env, void * func) {
    int16_t (*ff)() = func;
    env->return_ = ff();
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_DefiningCFunction_upcallReturnPointerNoParam(PNIEnv_pointer * env, void * func) {
    void* (*ff)() = func;
    env->return_ = ff();
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_DefiningCFunction_upcallReturnByte1Param(PNIEnv_byte * env, void * func, int8_t b) {
    int8_t (*ff)(int8_t) = func;
    env->return_ = ff(b);
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_DefiningCFunction_upcallReturnBool1Param(PNIEnv_bool * env, void * func, uint8_t z) {
    uint8_t (*ff)(uint8_t) = func;
    env->return_ = ff(z);
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_DefiningCFunction_upcallReturnChar1Param(PNIEnv_char * env, void * func, uint16_t c) {
    uint16_t (*ff)(uint16_t) = func;
    env->return_ = ff(c);
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_DefiningCFunction_upcallReturnDouble1Param(PNIEnv_double * env, void * func, double d) {
    double (*ff)(double) = func;
    env->return_ = ff(d);
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_DefiningCFunction_upcallReturnFloat1Param(PNIEnv_float * env, void * func, float f) {
    float (*ff)(float) = func;
    env->return_ = ff(f);
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_DefiningCFunction_upcallReturnInt1Param(PNIEnv_int * env, void * func, int32_t i) {
    int32_t (*ff)(int32_t) = func;
    env->return_ = ff(i);
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_DefiningCFunction_upcallReturnLong1Param(PNIEnv_long * env, void * func, int64_t j) {
    int64_t (*ff)(int64_t) = func;
    env->return_ = ff(j);
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_DefiningCFunction_upcallReturnShort1Param(PNIEnv_short * env, void * func, int16_t s) {
    int16_t (*ff)(int16_t) = func;
    env->return_ = ff(s);
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_DefiningCFunction_upcallReturnPointer1Param(PNIEnv_pointer * env, void * func, void * p) {
    void* (*ff)(void*) = func;
    env->return_ = ff(p);
    return 0;
}

#ifdef __cplusplus
}
#endif
// metadata.generator-version: pni test
// sha256:fab881ce66c93eeda880debe92e46f50b52cc297e1b38bc5b9e31b78f51fe5db
