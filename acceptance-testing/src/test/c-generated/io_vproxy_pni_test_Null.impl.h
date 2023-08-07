#include "io_vproxy_pni_test_Null.h"

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_Null_testParam(PNIEnv_bool * env, Null * self, ObjectStruct * o, char * str, void * seg, PNIBuf * buf, PNIBuf * byteArr, PNIBuf * boolArr, PNIBuf * charArr, PNIBuf * floatArr, PNIBuf * doubleArr, PNIBuf * intArr, PNIBuf * longArr, PNIBuf * shortArr, PNIBuf * oArr) {
    env->return_= o == NULL && str == NULL && seg == NULL && buf == NULL &&
                  byteArr == NULL && boolArr == NULL && charArr == NULL &&
                  floatArr == NULL && doubleArr == NULL && intArr == NULL &&
                  longArr == NULL && shortArr == NULL && oArr == NULL;
    return 0;
}

JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_pni_test_Null_testParamCritical(Null * self, ObjectStruct * o, char * str, void * seg, PNIBuf * buf, PNIBuf * byteArr, PNIBuf * boolArr, PNIBuf * charArr, PNIBuf * floatArr, PNIBuf * doubleArr, PNIBuf * intArr, PNIBuf * longArr, PNIBuf * shortArr, PNIBuf * oArr) {
    return o == NULL && str == NULL && seg == NULL && buf == NULL &&
           byteArr == NULL && boolArr == NULL && charArr == NULL &&
           floatArr == NULL && doubleArr == NULL && intArr == NULL &&
           longArr == NULL && shortArr == NULL && oArr == NULL;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_Null_testParamRaw(PNIEnv_bool * env, Null * self, void * buf, void * byteArr, uint8_t * boolArr, uint16_t * charArr, float * floatArr, double * doubleArr, int32_t * intArr, int64_t * longArr, int16_t * shortArr, ObjectStruct * oArr) {
    env->return_= buf == NULL &&
                  byteArr == NULL && boolArr == NULL && charArr == NULL &&
                  floatArr == NULL && doubleArr == NULL && intArr == NULL &&
                  longArr == NULL && shortArr == NULL && oArr == NULL;
    return 0;
}

JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_pni_test_Null_testParamRawCritical(Null * self, void * buf, void * byteArr, uint8_t * boolArr, uint16_t * charArr, float * floatArr, double * doubleArr, int32_t * intArr, int64_t * longArr, int16_t * shortArr, ObjectStruct * oArr) {
    return buf == NULL &&
           byteArr == NULL && boolArr == NULL && charArr == NULL &&
           floatArr == NULL && doubleArr == NULL && intArr == NULL &&
           longArr == NULL && shortArr == NULL && oArr == NULL;
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

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_Null_returnByteArr(PNIEnv_buf * env, Null * self) {
    env->return_.buf = NULL;
    return 0;
}

JNIEXPORT PNIBuf * JNICALL JavaCritical_io_vproxy_pni_test_Null_returnByteArrCritical(Null * self, PNIBuf * return_) {
    return NULL;
}

JNIEXPORT PNIBuf * JNICALL JavaCritical_io_vproxy_pni_test_Null_returnByteArrCritical2(Null * self, PNIBuf * return_) {
    return_->buf = NULL;
    return return_;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_Null_returnBoolArr(PNIEnv_buf * env, Null * self) {
    env->return_.buf = NULL;
    return 0;
}

JNIEXPORT PNIBuf * JNICALL JavaCritical_io_vproxy_pni_test_Null_returnBoolArrCritical(Null * self, PNIBuf * return_) {
    return NULL;
}

JNIEXPORT PNIBuf * JNICALL JavaCritical_io_vproxy_pni_test_Null_returnBoolArrCritical2(Null * self, PNIBuf * return_) {
    return_->buf = NULL;
    return return_;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_Null_returnCharArr(PNIEnv_buf * env, Null * self) {
    env->return_.buf = NULL;
    return 0;
}

JNIEXPORT PNIBuf * JNICALL JavaCritical_io_vproxy_pni_test_Null_returnCharArrCritical(Null * self, PNIBuf * return_) {
    return NULL;
}

JNIEXPORT PNIBuf * JNICALL JavaCritical_io_vproxy_pni_test_Null_returnCharArrCritical2(Null * self, PNIBuf * return_) {
    return_->buf = NULL;
    return return_;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_Null_returnFloatArr(PNIEnv_buf * env, Null * self) {
    env->return_.buf = NULL;
    return 0;
}

JNIEXPORT PNIBuf * JNICALL JavaCritical_io_vproxy_pni_test_Null_returnFloatArrCritical(Null * self, PNIBuf * return_) {
    return NULL;
}

JNIEXPORT PNIBuf * JNICALL JavaCritical_io_vproxy_pni_test_Null_returnFloatArrCritical2(Null * self, PNIBuf * return_) {
    return_->buf = NULL;
    return return_;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_Null_returnDoubleArr(PNIEnv_buf * env, Null * self) {
    env->return_.buf = NULL;
    return 0;
}

JNIEXPORT PNIBuf * JNICALL JavaCritical_io_vproxy_pni_test_Null_returnDoubleArrCritical(Null * self, PNIBuf * return_) {
    return NULL;
}

JNIEXPORT PNIBuf * JNICALL JavaCritical_io_vproxy_pni_test_Null_returnDoubleArrCritical2(Null * self, PNIBuf * return_) {
    return_->buf = NULL;
    return return_;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_Null_returnIntArr(PNIEnv_buf * env, Null * self) {
    env->return_.buf = NULL;
    return 0;
}

JNIEXPORT PNIBuf * JNICALL JavaCritical_io_vproxy_pni_test_Null_returnIntArrCritical(Null * self, PNIBuf * return_) {
    return NULL;
}

JNIEXPORT PNIBuf * JNICALL JavaCritical_io_vproxy_pni_test_Null_returnIntArrCritical2(Null * self, PNIBuf * return_) {
    return_->buf = NULL;
    return return_;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_Null_returnLongArr(PNIEnv_buf * env, Null * self) {
    env->return_.buf = NULL;
    return 0;
}

JNIEXPORT PNIBuf * JNICALL JavaCritical_io_vproxy_pni_test_Null_returnLongArrCritical(Null * self, PNIBuf * return_) {
    return NULL;
}

JNIEXPORT PNIBuf * JNICALL JavaCritical_io_vproxy_pni_test_Null_returnLongArrCritical2(Null * self, PNIBuf * return_) {
    return_->buf = NULL;
    return return_;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_Null_returnShortArr(PNIEnv_buf * env, Null * self) {
    env->return_.buf = NULL;
    return 0;
}

JNIEXPORT PNIBuf * JNICALL JavaCritical_io_vproxy_pni_test_Null_returnShortArrCritical(Null * self, PNIBuf * return_) {
    return NULL;
}

JNIEXPORT PNIBuf * JNICALL JavaCritical_io_vproxy_pni_test_Null_returnShortArrCritical2(Null * self, PNIBuf * return_) {
    return_->buf = NULL;
    return return_;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_Null_returnOArr(PNIEnv_buf * env, Null * self) {
    env->return_.buf = NULL;
    return 0;
}

JNIEXPORT PNIBuf * JNICALL JavaCritical_io_vproxy_pni_test_Null_returnOArrCritical(Null * self, PNIBuf * return_) {
    return NULL;
}

JNIEXPORT PNIBuf * JNICALL JavaCritical_io_vproxy_pni_test_Null_returnOArrCritical2(Null * self, PNIBuf * return_) {
    return_->buf = NULL;
    return return_;
}

#ifdef __cplusplus
}
#endif
// sha256:ffa602e323a62b89fcfe7501d76dfa4f49f23fe845c15299b4fea55ec7969d93
