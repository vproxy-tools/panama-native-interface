/* DO NOT EDIT THIS FILE - it is machine generated */
/* Header for class io_vproxy_pni_test_Null */
#ifndef _Included_io_vproxy_pni_test_Null
#define _Included_io_vproxy_pni_test_Null
#ifdef __cplusplus
extern "C" {
#endif

struct Null;
typedef struct Null Null;

#ifdef __cplusplus
}
#endif

#include <jni.h>
#include <pni.h>
#include "io_vproxy_pni_test_ObjectStruct.h"

#ifdef __cplusplus
extern "C" {
#endif

PNIEnvExpand(Null, Null *)

PNI_PACK(struct, Null, {
    ObjectStruct * o;
    char * str;
    void * seg;
    PNIBuf buf;
    PNIBuf byteArr;
    PNIBuf boolArr;
    PNIBuf charArr;
    PNIBuf floatArr;
    PNIBuf doubleArr;
    PNIBuf intArr;
    PNIBuf longArr;
    PNIBuf shortArr;
    PNIBuf oArr;
});

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_Null_testParam(PNIEnv_bool * env, Null * self, ObjectStruct * o, char * str, void * seg, PNIBuf * buf, PNIBuf * byteArr, PNIBuf * boolArr, PNIBuf * charArr, PNIBuf * floatArr, PNIBuf * doubleArr, PNIBuf * intArr, PNIBuf * longArr, PNIBuf * shortArr, PNIBuf * oArr);
JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_pni_test_Null_testParamCritical(Null * self, ObjectStruct * o, char * str, void * seg, PNIBuf * buf, PNIBuf * byteArr, PNIBuf * boolArr, PNIBuf * charArr, PNIBuf * floatArr, PNIBuf * doubleArr, PNIBuf * intArr, PNIBuf * longArr, PNIBuf * shortArr, PNIBuf * oArr);
JNIEXPORT int JNICALL Java_io_vproxy_pni_test_Null_testParamRaw(PNIEnv_bool * env, Null * self, void * buf, void * byteArr, uint8_t * boolArr, uint16_t * charArr, float * floatArr, double * doubleArr, int32_t * intArr, int64_t * longArr, int16_t * shortArr, ObjectStruct * oArr);
JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_pni_test_Null_testParamRawCritical(Null * self, void * buf, void * byteArr, uint8_t * boolArr, uint16_t * charArr, float * floatArr, double * doubleArr, int32_t * intArr, int64_t * longArr, int16_t * shortArr, ObjectStruct * oArr);
JNIEXPORT int JNICALL Java_io_vproxy_pni_test_Null_returnO(PNIEnv_ObjectStruct * env, Null * self, ObjectStruct * return_);
JNIEXPORT ObjectStruct * JNICALL JavaCritical_io_vproxy_pni_test_Null_returnOCritical(Null * self, ObjectStruct * return_);
JNIEXPORT int JNICALL Java_io_vproxy_pni_test_Null_returnStr(PNIEnv_string * env, Null * self);
JNIEXPORT char * JNICALL JavaCritical_io_vproxy_pni_test_Null_returnStrCritical(Null * self);
JNIEXPORT int JNICALL Java_io_vproxy_pni_test_Null_returnSeg(PNIEnv_pointer * env, Null * self);
JNIEXPORT void * JNICALL JavaCritical_io_vproxy_pni_test_Null_returnSegCritical(Null * self);
JNIEXPORT int JNICALL Java_io_vproxy_pni_test_Null_returnBuf(PNIEnv_buf * env, Null * self);
JNIEXPORT PNIBuf * JNICALL JavaCritical_io_vproxy_pni_test_Null_returnBufCritical(Null * self, PNIBuf * return_);
JNIEXPORT PNIBuf * JNICALL JavaCritical_io_vproxy_pni_test_Null_returnBufCritical2(Null * self, PNIBuf * return_);
JNIEXPORT int JNICALL Java_io_vproxy_pni_test_Null_returnByteArr(PNIEnv_buf * env, Null * self);
JNIEXPORT PNIBuf * JNICALL JavaCritical_io_vproxy_pni_test_Null_returnByteArrCritical(Null * self, PNIBuf * return_);
JNIEXPORT PNIBuf * JNICALL JavaCritical_io_vproxy_pni_test_Null_returnByteArrCritical2(Null * self, PNIBuf * return_);
JNIEXPORT int JNICALL Java_io_vproxy_pni_test_Null_returnBoolArr(PNIEnv_buf * env, Null * self);
JNIEXPORT PNIBuf * JNICALL JavaCritical_io_vproxy_pni_test_Null_returnBoolArrCritical(Null * self, PNIBuf * return_);
JNIEXPORT PNIBuf * JNICALL JavaCritical_io_vproxy_pni_test_Null_returnBoolArrCritical2(Null * self, PNIBuf * return_);
JNIEXPORT int JNICALL Java_io_vproxy_pni_test_Null_returnCharArr(PNIEnv_buf * env, Null * self);
JNIEXPORT PNIBuf * JNICALL JavaCritical_io_vproxy_pni_test_Null_returnCharArrCritical(Null * self, PNIBuf * return_);
JNIEXPORT PNIBuf * JNICALL JavaCritical_io_vproxy_pni_test_Null_returnCharArrCritical2(Null * self, PNIBuf * return_);
JNIEXPORT int JNICALL Java_io_vproxy_pni_test_Null_returnFloatArr(PNIEnv_buf * env, Null * self);
JNIEXPORT PNIBuf * JNICALL JavaCritical_io_vproxy_pni_test_Null_returnFloatArrCritical(Null * self, PNIBuf * return_);
JNIEXPORT PNIBuf * JNICALL JavaCritical_io_vproxy_pni_test_Null_returnFloatArrCritical2(Null * self, PNIBuf * return_);
JNIEXPORT int JNICALL Java_io_vproxy_pni_test_Null_returnDoubleArr(PNIEnv_buf * env, Null * self);
JNIEXPORT PNIBuf * JNICALL JavaCritical_io_vproxy_pni_test_Null_returnDoubleArrCritical(Null * self, PNIBuf * return_);
JNIEXPORT PNIBuf * JNICALL JavaCritical_io_vproxy_pni_test_Null_returnDoubleArrCritical2(Null * self, PNIBuf * return_);
JNIEXPORT int JNICALL Java_io_vproxy_pni_test_Null_returnIntArr(PNIEnv_buf * env, Null * self);
JNIEXPORT PNIBuf * JNICALL JavaCritical_io_vproxy_pni_test_Null_returnIntArrCritical(Null * self, PNIBuf * return_);
JNIEXPORT PNIBuf * JNICALL JavaCritical_io_vproxy_pni_test_Null_returnIntArrCritical2(Null * self, PNIBuf * return_);
JNIEXPORT int JNICALL Java_io_vproxy_pni_test_Null_returnLongArr(PNIEnv_buf * env, Null * self);
JNIEXPORT PNIBuf * JNICALL JavaCritical_io_vproxy_pni_test_Null_returnLongArrCritical(Null * self, PNIBuf * return_);
JNIEXPORT PNIBuf * JNICALL JavaCritical_io_vproxy_pni_test_Null_returnLongArrCritical2(Null * self, PNIBuf * return_);
JNIEXPORT int JNICALL Java_io_vproxy_pni_test_Null_returnShortArr(PNIEnv_buf * env, Null * self);
JNIEXPORT PNIBuf * JNICALL JavaCritical_io_vproxy_pni_test_Null_returnShortArrCritical(Null * self, PNIBuf * return_);
JNIEXPORT PNIBuf * JNICALL JavaCritical_io_vproxy_pni_test_Null_returnShortArrCritical2(Null * self, PNIBuf * return_);
JNIEXPORT int JNICALL Java_io_vproxy_pni_test_Null_returnOArr(PNIEnv_buf * env, Null * self);
JNIEXPORT PNIBuf * JNICALL JavaCritical_io_vproxy_pni_test_Null_returnOArrCritical(Null * self, PNIBuf * return_);
JNIEXPORT PNIBuf * JNICALL JavaCritical_io_vproxy_pni_test_Null_returnOArrCritical2(Null * self, PNIBuf * return_);

#ifdef __cplusplus
}
#endif
#endif // _Included_io_vproxy_pni_test_Null
// metadata.generator-version: pni test
// sha256:9b795203a97cc567f14eda48c86e91ec0d6670b9096fc65ae18309505fb2f2bc
