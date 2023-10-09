#include "io_vproxy_pni_test_UpcallNull.h"
#include <stdio.h>
#include <stdlib.h>

#ifdef __cplusplus
extern "C" {
#endif

static uint8_t (*_testParam)(void*,ObjectStruct *,char *,void *,PNIBuf *,PNIBuf_byte *,PNIBuf_bool *,PNIBuf_char *,PNIBuf_float *,PNIBuf_double *,PNIBuf_int *,PNIBuf_long *,PNIBuf_short *,PNIBuf_ObjectStruct *,PNIRef *,PNIFunc *,PNIFunc *,PNIFunc *);
static uint8_t (*_testParamRaw)(void*,PNIRef *,PNIFunc *,PNIFunc *,PNIFunc *);
static ObjectStruct * (*_returnO)(void*,ObjectStruct *);
static char * (*_returnStr)(void*);
static void * (*_returnSeg)(void*);
static PNIBuf * (*_returnBuf)(void*,PNIBuf *);
static PNIBuf_byte * (*_returnByteArr)(void*,PNIBuf_byte *);
static PNIBuf_bool * (*_returnBoolArr)(void*,PNIBuf_bool *);
static PNIBuf_char * (*_returnCharArr)(void*,PNIBuf_char *);
static PNIBuf_float * (*_returnFloatArr)(void*,PNIBuf_float *);
static PNIBuf_double * (*_returnDoubleArr)(void*,PNIBuf_double *);
static PNIBuf_int * (*_returnIntArr)(void*,PNIBuf_int *);
static PNIBuf_long * (*_returnLongArr)(void*,PNIBuf_long *);
static PNIBuf_short * (*_returnShortArr)(void*,PNIBuf_short *);
static PNIBuf_ObjectStruct * (*_returnOArr)(void*,PNIBuf_ObjectStruct *);
static PNIRef * (*_returnRef)(void*);
static PNIFunc * (*_returnFunc)(void*);
static PNIFunc * (*_returnFuncVoid)(void*);
static PNIFunc * (*_returnFuncRef)(void*);

JNIEXPORT void JNICALL JavaCritical_io_vproxy_pni_test_UpcallNull_INIT(
    uint8_t (*testParam)(void*,ObjectStruct *,char *,void *,PNIBuf *,PNIBuf_byte *,PNIBuf_bool *,PNIBuf_char *,PNIBuf_float *,PNIBuf_double *,PNIBuf_int *,PNIBuf_long *,PNIBuf_short *,PNIBuf_ObjectStruct *,PNIRef *,PNIFunc *,PNIFunc *,PNIFunc *),
    uint8_t (*testParamRaw)(void*,PNIRef *,PNIFunc *,PNIFunc *,PNIFunc *),
    ObjectStruct * (*returnO)(void*,ObjectStruct *),
    char * (*returnStr)(void*),
    void * (*returnSeg)(void*),
    PNIBuf * (*returnBuf)(void*,PNIBuf *),
    PNIBuf_byte * (*returnByteArr)(void*,PNIBuf_byte *),
    PNIBuf_bool * (*returnBoolArr)(void*,PNIBuf_bool *),
    PNIBuf_char * (*returnCharArr)(void*,PNIBuf_char *),
    PNIBuf_float * (*returnFloatArr)(void*,PNIBuf_float *),
    PNIBuf_double * (*returnDoubleArr)(void*,PNIBuf_double *),
    PNIBuf_int * (*returnIntArr)(void*,PNIBuf_int *),
    PNIBuf_long * (*returnLongArr)(void*,PNIBuf_long *),
    PNIBuf_short * (*returnShortArr)(void*,PNIBuf_short *),
    PNIBuf_ObjectStruct * (*returnOArr)(void*,PNIBuf_ObjectStruct *),
    PNIRef * (*returnRef)(void*),
    PNIFunc * (*returnFunc)(void*),
    PNIFunc * (*returnFuncVoid)(void*),
    PNIFunc * (*returnFuncRef)(void*)
) {
    _testParam = testParam;
    _testParamRaw = testParamRaw;
    _returnO = returnO;
    _returnStr = returnStr;
    _returnSeg = returnSeg;
    _returnBuf = returnBuf;
    _returnByteArr = returnByteArr;
    _returnBoolArr = returnBoolArr;
    _returnCharArr = returnCharArr;
    _returnFloatArr = returnFloatArr;
    _returnDoubleArr = returnDoubleArr;
    _returnIntArr = returnIntArr;
    _returnLongArr = returnLongArr;
    _returnShortArr = returnShortArr;
    _returnOArr = returnOArr;
    _returnRef = returnRef;
    _returnFunc = returnFunc;
    _returnFuncVoid = returnFuncVoid;
    _returnFuncRef = returnFuncRef;
}

JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_pni_test_UpcallNull_testParam(ObjectStruct * o, char * str, void * seg, PNIBuf * buf, PNIBuf_byte * byteArr, PNIBuf_bool * boolArr, PNIBuf_char * charArr, PNIBuf_float * floatArr, PNIBuf_double * doubleArr, PNIBuf_int * intArr, PNIBuf_long * longArr, PNIBuf_short * shortArr, PNIBuf_ObjectStruct * oArr, PNIRef * ref, PNIFunc * func, PNIFunc * funcVoid, PNIFunc * funcRef) {
    if (_testParam == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_UpcallNull_testParam function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _testParam(PNIGetGraalThread(), o, str, seg, buf, byteArr, boolArr, charArr, floatArr, doubleArr, intArr, longArr, shortArr, oArr, ref, func, funcVoid, funcRef);
}

JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_pni_test_UpcallNull_testParamRaw(PNIRef * ref, PNIFunc * func, PNIFunc * funcVoid, PNIFunc * funcRef) {
    if (_testParamRaw == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_UpcallNull_testParamRaw function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _testParamRaw(PNIGetGraalThread(), ref, func, funcVoid, funcRef);
}

JNIEXPORT ObjectStruct * JNICALL JavaCritical_io_vproxy_pni_test_UpcallNull_returnO(ObjectStruct * return_) {
    if (_returnO == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_UpcallNull_returnO function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnO(PNIGetGraalThread(), return_);
}

JNIEXPORT char * JNICALL JavaCritical_io_vproxy_pni_test_UpcallNull_returnStr(void) {
    if (_returnStr == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_UpcallNull_returnStr function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnStr(PNIGetGraalThread());
}

JNIEXPORT void * JNICALL JavaCritical_io_vproxy_pni_test_UpcallNull_returnSeg(void) {
    if (_returnSeg == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_UpcallNull_returnSeg function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnSeg(PNIGetGraalThread());
}

JNIEXPORT PNIBuf * JNICALL JavaCritical_io_vproxy_pni_test_UpcallNull_returnBuf(PNIBuf * return_) {
    if (_returnBuf == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_UpcallNull_returnBuf function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnBuf(PNIGetGraalThread(), return_);
}

JNIEXPORT PNIBuf_byte * JNICALL JavaCritical_io_vproxy_pni_test_UpcallNull_returnByteArr(PNIBuf_byte * return_) {
    if (_returnByteArr == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_UpcallNull_returnByteArr function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnByteArr(PNIGetGraalThread(), return_);
}

JNIEXPORT PNIBuf_bool * JNICALL JavaCritical_io_vproxy_pni_test_UpcallNull_returnBoolArr(PNIBuf_bool * return_) {
    if (_returnBoolArr == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_UpcallNull_returnBoolArr function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnBoolArr(PNIGetGraalThread(), return_);
}

JNIEXPORT PNIBuf_char * JNICALL JavaCritical_io_vproxy_pni_test_UpcallNull_returnCharArr(PNIBuf_char * return_) {
    if (_returnCharArr == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_UpcallNull_returnCharArr function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnCharArr(PNIGetGraalThread(), return_);
}

JNIEXPORT PNIBuf_float * JNICALL JavaCritical_io_vproxy_pni_test_UpcallNull_returnFloatArr(PNIBuf_float * return_) {
    if (_returnFloatArr == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_UpcallNull_returnFloatArr function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnFloatArr(PNIGetGraalThread(), return_);
}

JNIEXPORT PNIBuf_double * JNICALL JavaCritical_io_vproxy_pni_test_UpcallNull_returnDoubleArr(PNIBuf_double * return_) {
    if (_returnDoubleArr == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_UpcallNull_returnDoubleArr function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnDoubleArr(PNIGetGraalThread(), return_);
}

JNIEXPORT PNIBuf_int * JNICALL JavaCritical_io_vproxy_pni_test_UpcallNull_returnIntArr(PNIBuf_int * return_) {
    if (_returnIntArr == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_UpcallNull_returnIntArr function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnIntArr(PNIGetGraalThread(), return_);
}

JNIEXPORT PNIBuf_long * JNICALL JavaCritical_io_vproxy_pni_test_UpcallNull_returnLongArr(PNIBuf_long * return_) {
    if (_returnLongArr == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_UpcallNull_returnLongArr function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnLongArr(PNIGetGraalThread(), return_);
}

JNIEXPORT PNIBuf_short * JNICALL JavaCritical_io_vproxy_pni_test_UpcallNull_returnShortArr(PNIBuf_short * return_) {
    if (_returnShortArr == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_UpcallNull_returnShortArr function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnShortArr(PNIGetGraalThread(), return_);
}

JNIEXPORT PNIBuf_ObjectStruct * JNICALL JavaCritical_io_vproxy_pni_test_UpcallNull_returnOArr(PNIBuf_ObjectStruct * return_) {
    if (_returnOArr == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_UpcallNull_returnOArr function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnOArr(PNIGetGraalThread(), return_);
}

JNIEXPORT PNIRef * JNICALL JavaCritical_io_vproxy_pni_test_UpcallNull_returnRef(void) {
    if (_returnRef == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_UpcallNull_returnRef function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnRef(PNIGetGraalThread());
}

JNIEXPORT PNIFunc * JNICALL JavaCritical_io_vproxy_pni_test_UpcallNull_returnFunc(void) {
    if (_returnFunc == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_UpcallNull_returnFunc function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnFunc(PNIGetGraalThread());
}

JNIEXPORT PNIFunc * JNICALL JavaCritical_io_vproxy_pni_test_UpcallNull_returnFuncVoid(void) {
    if (_returnFuncVoid == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_UpcallNull_returnFuncVoid function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnFuncVoid(PNIGetGraalThread());
}

JNIEXPORT PNIFunc * JNICALL JavaCritical_io_vproxy_pni_test_UpcallNull_returnFuncRef(void) {
    if (_returnFuncRef == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_UpcallNull_returnFuncRef function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnFuncRef(PNIGetGraalThread());
}

#ifdef __cplusplus
}
#endif
// metadata.generator-version: pni test
// sha256:de3e8dd1fbe4ac267df254ff34b94169418e47c263ff3fa9fa72ea0fe384b561
