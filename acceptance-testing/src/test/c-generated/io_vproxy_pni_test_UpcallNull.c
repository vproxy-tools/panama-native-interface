#include "io_vproxy_pni_test_UpcallNull.h"
#include <stdio.h>
#include <stdlib.h>

#ifdef __cplusplus
extern "C" {
#endif

static uint8_t (*_testParam)(ObjectStruct *,char *,void *,PNIBuf *,PNIBuf *,PNIBuf *,PNIBuf *,PNIBuf *,PNIBuf *,PNIBuf *,PNIBuf *,PNIBuf *,PNIBuf *,PNIRef *,PNIFunc *,PNIFunc *,PNIFunc *);
static uint8_t (*_testParamRaw)(PNIRef *,PNIFunc *,PNIFunc *,PNIFunc *);
static ObjectStruct * (*_returnO)(ObjectStruct *);
static char * (*_returnStr)();
static void * (*_returnSeg)();
static PNIBuf * (*_returnBuf)(PNIBuf *);
static PNIBuf * (*_returnByteArr)(PNIBuf *);
static PNIBuf * (*_returnBoolArr)(PNIBuf *);
static PNIBuf * (*_returnCharArr)(PNIBuf *);
static PNIBuf * (*_returnFloatArr)(PNIBuf *);
static PNIBuf * (*_returnDoubleArr)(PNIBuf *);
static PNIBuf * (*_returnIntArr)(PNIBuf *);
static PNIBuf * (*_returnLongArr)(PNIBuf *);
static PNIBuf * (*_returnShortArr)(PNIBuf *);
static PNIBuf * (*_returnOArr)(PNIBuf *);
static PNIRef * (*_returnRef)();
static PNIFunc * (*_returnFunc)();
static PNIFunc * (*_returnFuncVoid)();
static PNIFunc * (*_returnFuncRef)();

JNIEXPORT void JNICALL JavaCritical_io_vproxy_pni_test_UpcallNull_INIT(
    uint8_t (*testParam)(ObjectStruct *,char *,void *,PNIBuf *,PNIBuf *,PNIBuf *,PNIBuf *,PNIBuf *,PNIBuf *,PNIBuf *,PNIBuf *,PNIBuf *,PNIBuf *,PNIRef *,PNIFunc *,PNIFunc *,PNIFunc *),
    uint8_t (*testParamRaw)(PNIRef *,PNIFunc *,PNIFunc *,PNIFunc *),
    ObjectStruct * (*returnO)(ObjectStruct *),
    char * (*returnStr)(),
    void * (*returnSeg)(),
    PNIBuf * (*returnBuf)(PNIBuf *),
    PNIBuf * (*returnByteArr)(PNIBuf *),
    PNIBuf * (*returnBoolArr)(PNIBuf *),
    PNIBuf * (*returnCharArr)(PNIBuf *),
    PNIBuf * (*returnFloatArr)(PNIBuf *),
    PNIBuf * (*returnDoubleArr)(PNIBuf *),
    PNIBuf * (*returnIntArr)(PNIBuf *),
    PNIBuf * (*returnLongArr)(PNIBuf *),
    PNIBuf * (*returnShortArr)(PNIBuf *),
    PNIBuf * (*returnOArr)(PNIBuf *),
    PNIRef * (*returnRef)(),
    PNIFunc * (*returnFunc)(),
    PNIFunc * (*returnFuncVoid)(),
    PNIFunc * (*returnFuncRef)()
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

JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_pni_test_UpcallNull_testParam(ObjectStruct * o, char * str, void * seg, PNIBuf * buf, PNIBuf * byteArr, PNIBuf * boolArr, PNIBuf * charArr, PNIBuf * floatArr, PNIBuf * doubleArr, PNIBuf * intArr, PNIBuf * longArr, PNIBuf * shortArr, PNIBuf * oArr, PNIRef * ref, PNIFunc * func, PNIFunc * funcVoid, PNIFunc * funcRef) {
    if (_testParam == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_UpcallNull_testParam function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _testParam(o, str, seg, buf, byteArr, boolArr, charArr, floatArr, doubleArr, intArr, longArr, shortArr, oArr, ref, func, funcVoid, funcRef);
}

JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_pni_test_UpcallNull_testParamRaw(PNIRef * ref, PNIFunc * func, PNIFunc * funcVoid, PNIFunc * funcRef) {
    if (_testParamRaw == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_UpcallNull_testParamRaw function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _testParamRaw(ref, func, funcVoid, funcRef);
}

JNIEXPORT ObjectStruct * JNICALL JavaCritical_io_vproxy_pni_test_UpcallNull_returnO(ObjectStruct * return_) {
    if (_returnO == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_UpcallNull_returnO function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnO(return_);
}

JNIEXPORT char * JNICALL JavaCritical_io_vproxy_pni_test_UpcallNull_returnStr() {
    if (_returnStr == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_UpcallNull_returnStr function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnStr();
}

JNIEXPORT void * JNICALL JavaCritical_io_vproxy_pni_test_UpcallNull_returnSeg() {
    if (_returnSeg == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_UpcallNull_returnSeg function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnSeg();
}

JNIEXPORT PNIBuf * JNICALL JavaCritical_io_vproxy_pni_test_UpcallNull_returnBuf(PNIBuf * return_) {
    if (_returnBuf == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_UpcallNull_returnBuf function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnBuf(return_);
}

JNIEXPORT PNIBuf * JNICALL JavaCritical_io_vproxy_pni_test_UpcallNull_returnByteArr(PNIBuf * return_) {
    if (_returnByteArr == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_UpcallNull_returnByteArr function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnByteArr(return_);
}

JNIEXPORT PNIBuf * JNICALL JavaCritical_io_vproxy_pni_test_UpcallNull_returnBoolArr(PNIBuf * return_) {
    if (_returnBoolArr == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_UpcallNull_returnBoolArr function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnBoolArr(return_);
}

JNIEXPORT PNIBuf * JNICALL JavaCritical_io_vproxy_pni_test_UpcallNull_returnCharArr(PNIBuf * return_) {
    if (_returnCharArr == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_UpcallNull_returnCharArr function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnCharArr(return_);
}

JNIEXPORT PNIBuf * JNICALL JavaCritical_io_vproxy_pni_test_UpcallNull_returnFloatArr(PNIBuf * return_) {
    if (_returnFloatArr == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_UpcallNull_returnFloatArr function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnFloatArr(return_);
}

JNIEXPORT PNIBuf * JNICALL JavaCritical_io_vproxy_pni_test_UpcallNull_returnDoubleArr(PNIBuf * return_) {
    if (_returnDoubleArr == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_UpcallNull_returnDoubleArr function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnDoubleArr(return_);
}

JNIEXPORT PNIBuf * JNICALL JavaCritical_io_vproxy_pni_test_UpcallNull_returnIntArr(PNIBuf * return_) {
    if (_returnIntArr == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_UpcallNull_returnIntArr function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnIntArr(return_);
}

JNIEXPORT PNIBuf * JNICALL JavaCritical_io_vproxy_pni_test_UpcallNull_returnLongArr(PNIBuf * return_) {
    if (_returnLongArr == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_UpcallNull_returnLongArr function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnLongArr(return_);
}

JNIEXPORT PNIBuf * JNICALL JavaCritical_io_vproxy_pni_test_UpcallNull_returnShortArr(PNIBuf * return_) {
    if (_returnShortArr == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_UpcallNull_returnShortArr function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnShortArr(return_);
}

JNIEXPORT PNIBuf * JNICALL JavaCritical_io_vproxy_pni_test_UpcallNull_returnOArr(PNIBuf * return_) {
    if (_returnOArr == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_UpcallNull_returnOArr function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnOArr(return_);
}

JNIEXPORT PNIRef * JNICALL JavaCritical_io_vproxy_pni_test_UpcallNull_returnRef() {
    if (_returnRef == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_UpcallNull_returnRef function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnRef();
}

JNIEXPORT PNIFunc * JNICALL JavaCritical_io_vproxy_pni_test_UpcallNull_returnFunc() {
    if (_returnFunc == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_UpcallNull_returnFunc function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnFunc();
}

JNIEXPORT PNIFunc * JNICALL JavaCritical_io_vproxy_pni_test_UpcallNull_returnFuncVoid() {
    if (_returnFuncVoid == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_UpcallNull_returnFuncVoid function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnFuncVoid();
}

JNIEXPORT PNIFunc * JNICALL JavaCritical_io_vproxy_pni_test_UpcallNull_returnFuncRef() {
    if (_returnFuncRef == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_UpcallNull_returnFuncRef function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnFuncRef();
}

#ifdef __cplusplus
}
#endif
// metadata.generator-version: pni test
// sha256:e0a72471b2b3addbeda478328ef9284ed241228a1cb8b825d1d163f573af1c56
