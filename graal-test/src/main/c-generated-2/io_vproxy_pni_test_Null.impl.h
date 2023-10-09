#include "io_vproxy_pni_test_Null.h"

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_Null_testParam(PNIEnv_bool * env, Null * self, ObjectStruct * o, char * str, void * seg, PNIBuf * buf, PNIBuf_byte * byteArr, PNIBuf_bool * boolArr, PNIBuf_char * charArr, PNIBuf_float * floatArr, PNIBuf_double * doubleArr, PNIBuf_int * intArr, PNIBuf_long * longArr, PNIBuf_short * shortArr, PNIBuf_ObjectStruct * oArr, PNIRef * ref, PNIFunc * func, PNIFunc * funcVoid, PNIFunc * funcRef) {
    env->return_= o == NULL && str == NULL && seg == NULL && buf == NULL &&
                  byteArr == NULL && boolArr == NULL && charArr == NULL &&
                  floatArr == NULL && doubleArr == NULL && intArr == NULL &&
                  longArr == NULL && shortArr == NULL && oArr == NULL &&
                  ref == NULL && func == NULL && funcVoid == NULL && funcRef == NULL;
    return 0;
}

JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_pni_test_Null_testParamCritical(Null * self, ObjectStruct * o, char * str, void * seg, PNIBuf * buf, PNIBuf_byte * byteArr, PNIBuf_bool * boolArr, PNIBuf_char * charArr, PNIBuf_float * floatArr, PNIBuf_double * doubleArr, PNIBuf_int * intArr, PNIBuf_long * longArr, PNIBuf_short * shortArr, PNIBuf_ObjectStruct * oArr, PNIRef * ref, PNIFunc * func, PNIFunc * funcVoid, PNIFunc * funcRef) {
    return o == NULL && str == NULL && seg == NULL && buf == NULL &&
           byteArr == NULL && boolArr == NULL && charArr == NULL &&
           floatArr == NULL && doubleArr == NULL && intArr == NULL &&
           longArr == NULL && shortArr == NULL && oArr == NULL &&
           ref == NULL && func == NULL && funcVoid == NULL && funcRef == NULL;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_Null_testParamRaw(PNIEnv_bool * env, Null * self, void * buf, void * byteArr, uint8_t * boolArr, uint16_t * charArr, float * floatArr, double * doubleArr, int32_t * intArr, int64_t * longArr, int16_t * shortArr, ObjectStruct * oArr, PNIRef * ref, PNIFunc * func, PNIFunc * funcVoid, PNIFunc * funcRef) {
    env->return_= buf == NULL &&
                  byteArr == NULL && boolArr == NULL && charArr == NULL &&
                  floatArr == NULL && doubleArr == NULL && intArr == NULL &&
                  longArr == NULL && shortArr == NULL && oArr == NULL &&
                  ref == NULL && func == NULL && funcVoid == NULL && funcRef == NULL;
    return 0;
}

JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_pni_test_Null_testParamRawCritical(Null * self, void * buf, void * byteArr, uint8_t * boolArr, uint16_t * charArr, float * floatArr, double * doubleArr, int32_t * intArr, int64_t * longArr, int16_t * shortArr, ObjectStruct * oArr, PNIRef * ref, PNIFunc * func, PNIFunc * funcVoid, PNIFunc * funcRef) {
    return buf == NULL &&
           byteArr == NULL && boolArr == NULL && charArr == NULL &&
           floatArr == NULL && doubleArr == NULL && intArr == NULL &&
           longArr == NULL && shortArr == NULL && oArr == NULL &&
           ref == NULL && func == NULL && funcVoid == NULL && funcRef == NULL;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_Null_returnO(PNIEnv_ObjectStruct * env, Null * self, ObjectStruct * return_) {
    env->return_ = NULL;
    return 0;
}

JNIEXPORT ObjectStruct * JNICALL JavaCritical_io_vproxy_pni_test_Null_returnOCritical(Null * self, ObjectStruct * return_) {
    return NULL;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_Null_returnStr(PNIEnv_string * env, Null * self) {
    env->return_ = NULL;
    return 0;
}

JNIEXPORT char * JNICALL JavaCritical_io_vproxy_pni_test_Null_returnStrCritical(Null * self) {
    return NULL;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_Null_returnSeg(PNIEnv_pointer * env, Null * self) {
    env->return_ = NULL;
    return 0;
}

JNIEXPORT void * JNICALL JavaCritical_io_vproxy_pni_test_Null_returnSegCritical(Null * self) {
    return NULL;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_Null_returnBuf(PNIEnv_buf * env, Null * self) {
    env->return_.buf = NULL;
    return 0;
}

JNIEXPORT PNIBuf * JNICALL JavaCritical_io_vproxy_pni_test_Null_returnBufCritical(Null * self, PNIBuf * return_) {
    return NULL;
}

JNIEXPORT PNIBuf * JNICALL JavaCritical_io_vproxy_pni_test_Null_returnBufCritical2(Null * self, PNIBuf * return_) {
    return_->buf = NULL;
    return return_;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_Null_returnByteArr(PNIEnv_buf_byte * env, Null * self) {
    env->return_.buf = NULL;
    return 0;
}

JNIEXPORT PNIBuf_byte * JNICALL JavaCritical_io_vproxy_pni_test_Null_returnByteArrCritical(Null * self, PNIBuf_byte * return_) {
    return NULL;
}

JNIEXPORT PNIBuf_byte * JNICALL JavaCritical_io_vproxy_pni_test_Null_returnByteArrCritical2(Null * self, PNIBuf_byte * return_) {
    return_->buf = NULL;
    return return_;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_Null_returnBoolArr(PNIEnv_buf_bool * env, Null * self) {
    env->return_.buf = NULL;
    return 0;
}

JNIEXPORT PNIBuf_bool * JNICALL JavaCritical_io_vproxy_pni_test_Null_returnBoolArrCritical(Null * self, PNIBuf_bool * return_) {
    return NULL;
}

JNIEXPORT PNIBuf_bool * JNICALL JavaCritical_io_vproxy_pni_test_Null_returnBoolArrCritical2(Null * self, PNIBuf_bool * return_) {
    return_->buf = NULL;
    return return_;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_Null_returnCharArr(PNIEnv_buf_char * env, Null * self) {
    env->return_.buf = NULL;
    return 0;
}

JNIEXPORT PNIBuf_char * JNICALL JavaCritical_io_vproxy_pni_test_Null_returnCharArrCritical(Null * self, PNIBuf_char * return_) {
    return NULL;
}

JNIEXPORT PNIBuf_char * JNICALL JavaCritical_io_vproxy_pni_test_Null_returnCharArrCritical2(Null * self, PNIBuf_char * return_) {
    return_->buf = NULL;
    return return_;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_Null_returnFloatArr(PNIEnv_buf_float * env, Null * self) {
    env->return_.buf = NULL;
    return 0;
}

JNIEXPORT PNIBuf_float * JNICALL JavaCritical_io_vproxy_pni_test_Null_returnFloatArrCritical(Null * self, PNIBuf_float * return_) {
    return NULL;
}

JNIEXPORT PNIBuf_float * JNICALL JavaCritical_io_vproxy_pni_test_Null_returnFloatArrCritical2(Null * self, PNIBuf_float * return_) {
    return_->buf = NULL;
    return return_;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_Null_returnDoubleArr(PNIEnv_buf_double * env, Null * self) {
    env->return_.buf = NULL;
    return 0;
}

JNIEXPORT PNIBuf_double * JNICALL JavaCritical_io_vproxy_pni_test_Null_returnDoubleArrCritical(Null * self, PNIBuf_double * return_) {
    return NULL;
}

JNIEXPORT PNIBuf_double * JNICALL JavaCritical_io_vproxy_pni_test_Null_returnDoubleArrCritical2(Null * self, PNIBuf_double * return_) {
    return_->buf = NULL;
    return return_;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_Null_returnIntArr(PNIEnv_buf_int * env, Null * self) {
    env->return_.buf = NULL;
    return 0;
}

JNIEXPORT PNIBuf_int * JNICALL JavaCritical_io_vproxy_pni_test_Null_returnIntArrCritical(Null * self, PNIBuf_int * return_) {
    return NULL;
}

JNIEXPORT PNIBuf_int * JNICALL JavaCritical_io_vproxy_pni_test_Null_returnIntArrCritical2(Null * self, PNIBuf_int * return_) {
    return_->buf = NULL;
    return return_;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_Null_returnLongArr(PNIEnv_buf_long * env, Null * self) {
    env->return_.buf = NULL;
    return 0;
}

JNIEXPORT PNIBuf_long * JNICALL JavaCritical_io_vproxy_pni_test_Null_returnLongArrCritical(Null * self, PNIBuf_long * return_) {
    return NULL;
}

JNIEXPORT PNIBuf_long * JNICALL JavaCritical_io_vproxy_pni_test_Null_returnLongArrCritical2(Null * self, PNIBuf_long * return_) {
    return_->buf = NULL;
    return return_;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_Null_returnShortArr(PNIEnv_buf_short * env, Null * self) {
    env->return_.buf = NULL;
    return 0;
}

JNIEXPORT PNIBuf_short * JNICALL JavaCritical_io_vproxy_pni_test_Null_returnShortArrCritical(Null * self, PNIBuf_short * return_) {
    return NULL;
}

JNIEXPORT PNIBuf_short * JNICALL JavaCritical_io_vproxy_pni_test_Null_returnShortArrCritical2(Null * self, PNIBuf_short * return_) {
    return_->buf = NULL;
    return return_;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_Null_returnOArr(PNIEnv_buf_ObjectStruct * env, Null * self) {
    env->return_.buf = NULL;
    return 0;
}

JNIEXPORT PNIBuf_ObjectStruct * JNICALL JavaCritical_io_vproxy_pni_test_Null_returnOArrCritical(Null * self, PNIBuf_ObjectStruct * return_) {
    return NULL;
}

JNIEXPORT PNIBuf_ObjectStruct * JNICALL JavaCritical_io_vproxy_pni_test_Null_returnOArrCritical2(Null * self, PNIBuf_ObjectStruct * return_) {
    return_->buf = NULL;
    return return_;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_Null_returnRef(PNIEnv_ref * env, Null * self) {
    env->return_ = NULL;
    return 0;
}

JNIEXPORT PNIRef * JNICALL JavaCritical_io_vproxy_pni_test_Null_returnRefCritical(Null * self) {
    return NULL;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_Null_returnFunc(PNIEnv_func * env, Null * self) {
    env->return_ = NULL;
    return 0;
}

JNIEXPORT PNIFunc * JNICALL JavaCritical_io_vproxy_pni_test_Null_returnFuncCritical(Null * self) {
    return NULL;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_Null_returnFuncVoid(PNIEnv_func * env, Null * self) {
    env->return_ = NULL;
    return 0;
}

JNIEXPORT PNIFunc * JNICALL JavaCritical_io_vproxy_pni_test_Null_returnFuncVoidCritical(Null * self) {
    return NULL;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_Null_returnFuncRef(PNIEnv_func * env, Null * self) {
    env->return_ = NULL;
    return 0;
}

JNIEXPORT PNIFunc * JNICALL JavaCritical_io_vproxy_pni_test_Null_returnFuncRefCritical(Null * self) {
    return NULL;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_Null_emptyPassThrough(PNIEnv_Empty * env, Null * self, Empty * empty, Empty * return_) {
    env->return_ = empty;
    return 0;
}

JNIEXPORT Empty * JNICALL JavaCritical_io_vproxy_pni_test_Null_emptyPassThroughCritical(Null * self, Empty * empty, Empty * return_) {
    return empty;
}

#ifdef __cplusplus
}
#endif
// metadata.generator-version: pni test
// sha256:518152fb75eca510ca04b5edbe9e59cbfe3b5e0c04646b6c7eb87e18912ca506
