#include "io_vproxy_pni_test_Upcall.h"
#include <stdio.h>
#include <stdlib.h>

#ifdef __cplusplus
extern "C" {
#endif

static void (*_primaryParams)(int8_t,uint8_t,uint8_t,uint16_t,double,float,int32_t,uint32_t,int64_t,uint64_t,int16_t,uint16_t);
static int8_t (*_returnByte)();
static uint8_t (*_returnBool)();
static uint16_t (*_returnChar)();
static double (*_returnDouble)();
static float (*_returnFloat)();
static int32_t (*_returnInt)();
static int64_t (*_returnLong)();
static int16_t (*_returnShort)();
static void (*_primaryArrayParams)(PNIBuf *,PNIBuf *,PNIBuf *,PNIBuf *,PNIBuf *,PNIBuf *,PNIBuf *,PNIBuf *,PNIBuf *,PNIBuf *,PNIBuf *,PNIBuf *);
static PNIBuf * (*_returnByteArray)(PNIBuf *);
static PNIBuf * (*_returnBoolArray)(PNIBuf *);
static PNIBuf * (*_returnCharArray)(PNIBuf *);
static PNIBuf * (*_returnDoubleArray)(PNIBuf *);
static PNIBuf * (*_returnFloatArray)(PNIBuf *);
static PNIBuf * (*_returnIntArray)(PNIBuf *);
static PNIBuf * (*_returnLongArray)(PNIBuf *);
static PNIBuf * (*_returnShortArray)(PNIBuf *);
static void (*_objectParams)(ObjectStruct *);
static ObjectStruct * (*_returnObject)(ObjectStruct *);
static void (*_objectArrayParams)(PNIBuf *);
static PNIBuf * (*_returnObjectArray)(PNIBuf *);
static void (*_otherParams)(PNIBuf *,PNIFunc *,PNIFunc *,PNIFunc *,void *,PNIFunc *,PNIFunc *,PNIFunc *,PNIRef *,PNIRef *,char *);
static PNIBuf * (*_returnBuffer)(PNIBuf *);
static void * (*_returnMem)();
static PNIFunc * (*_returnVoidFunc)();
static PNIFunc * (*_returnObjFunc)();
static PNIFunc * (*_returnRefFunc)();
static PNIRef * (*_returnRef)();
static char * (*_returnStr)();

JNIEXPORT void JNICALL JavaCritical_io_vproxy_pni_test_Upcall_INIT(
    void (*primaryParams)(int8_t,uint8_t,uint8_t,uint16_t,double,float,int32_t,uint32_t,int64_t,uint64_t,int16_t,uint16_t),
    int8_t (*returnByte)(),
    uint8_t (*returnBool)(),
    uint16_t (*returnChar)(),
    double (*returnDouble)(),
    float (*returnFloat)(),
    int32_t (*returnInt)(),
    int64_t (*returnLong)(),
    int16_t (*returnShort)(),
    void (*primaryArrayParams)(PNIBuf *,PNIBuf *,PNIBuf *,PNIBuf *,PNIBuf *,PNIBuf *,PNIBuf *,PNIBuf *,PNIBuf *,PNIBuf *,PNIBuf *,PNIBuf *),
    PNIBuf * (*returnByteArray)(PNIBuf *),
    PNIBuf * (*returnBoolArray)(PNIBuf *),
    PNIBuf * (*returnCharArray)(PNIBuf *),
    PNIBuf * (*returnDoubleArray)(PNIBuf *),
    PNIBuf * (*returnFloatArray)(PNIBuf *),
    PNIBuf * (*returnIntArray)(PNIBuf *),
    PNIBuf * (*returnLongArray)(PNIBuf *),
    PNIBuf * (*returnShortArray)(PNIBuf *),
    void (*objectParams)(ObjectStruct *),
    ObjectStruct * (*returnObject)(ObjectStruct *),
    void (*objectArrayParams)(PNIBuf *),
    PNIBuf * (*returnObjectArray)(PNIBuf *),
    void (*otherParams)(PNIBuf *,PNIFunc *,PNIFunc *,PNIFunc *,void *,PNIFunc *,PNIFunc *,PNIFunc *,PNIRef *,PNIRef *,char *),
    PNIBuf * (*returnBuffer)(PNIBuf *),
    void * (*returnMem)(),
    PNIFunc * (*returnVoidFunc)(),
    PNIFunc * (*returnObjFunc)(),
    PNIFunc * (*returnRefFunc)(),
    PNIRef * (*returnRef)(),
    char * (*returnStr)()
) {
    _primaryParams = primaryParams;
    _returnByte = returnByte;
    _returnBool = returnBool;
    _returnChar = returnChar;
    _returnDouble = returnDouble;
    _returnFloat = returnFloat;
    _returnInt = returnInt;
    _returnLong = returnLong;
    _returnShort = returnShort;
    _primaryArrayParams = primaryArrayParams;
    _returnByteArray = returnByteArray;
    _returnBoolArray = returnBoolArray;
    _returnCharArray = returnCharArray;
    _returnDoubleArray = returnDoubleArray;
    _returnFloatArray = returnFloatArray;
    _returnIntArray = returnIntArray;
    _returnLongArray = returnLongArray;
    _returnShortArray = returnShortArray;
    _objectParams = objectParams;
    _returnObject = returnObject;
    _objectArrayParams = objectArrayParams;
    _returnObjectArray = returnObjectArray;
    _otherParams = otherParams;
    _returnBuffer = returnBuffer;
    _returnMem = returnMem;
    _returnVoidFunc = returnVoidFunc;
    _returnObjFunc = returnObjFunc;
    _returnRefFunc = returnRefFunc;
    _returnRef = returnRef;
    _returnStr = returnStr;
}

JNIEXPORT void JNICALL JavaCritical_io_vproxy_pni_test_Upcall_primaryParams(int8_t b, uint8_t ub, uint8_t z, uint16_t c, double d, float f, int32_t i, uint32_t ui, int64_t j, uint64_t uj, int16_t s, uint16_t us) {
    if (_primaryParams == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_Upcall_primaryParams function pointer is null");
        fflush(stdout);
        exit(1);
    }
    _primaryParams(b, ub, z, c, d, f, i, ui, j, uj, s, us);
}

JNIEXPORT int8_t JNICALL JavaCritical_io_vproxy_pni_test_Upcall_returnByte() {
    if (_returnByte == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_Upcall_returnByte function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnByte();
}

JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_pni_test_Upcall_returnBool() {
    if (_returnBool == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_Upcall_returnBool function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnBool();
}

JNIEXPORT uint16_t JNICALL JavaCritical_io_vproxy_pni_test_Upcall_returnChar() {
    if (_returnChar == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_Upcall_returnChar function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnChar();
}

JNIEXPORT double JNICALL JavaCritical_io_vproxy_pni_test_Upcall_returnDouble() {
    if (_returnDouble == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_Upcall_returnDouble function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnDouble();
}

JNIEXPORT float JNICALL JavaCritical_io_vproxy_pni_test_Upcall_returnFloat() {
    if (_returnFloat == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_Upcall_returnFloat function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnFloat();
}

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_pni_test_Upcall_returnInt() {
    if (_returnInt == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_Upcall_returnInt function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnInt();
}

JNIEXPORT int64_t JNICALL JavaCritical_io_vproxy_pni_test_Upcall_returnLong() {
    if (_returnLong == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_Upcall_returnLong function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnLong();
}

JNIEXPORT int16_t JNICALL JavaCritical_io_vproxy_pni_test_Upcall_returnShort() {
    if (_returnShort == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_Upcall_returnShort function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnShort();
}

JNIEXPORT void JNICALL JavaCritical_io_vproxy_pni_test_Upcall_primaryArrayParams(PNIBuf * b, PNIBuf * ub, PNIBuf * z, PNIBuf * c, PNIBuf * d, PNIBuf * f, PNIBuf * i, PNIBuf * ui, PNIBuf * j, PNIBuf * uj, PNIBuf * s, PNIBuf * us) {
    if (_primaryArrayParams == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_Upcall_primaryArrayParams function pointer is null");
        fflush(stdout);
        exit(1);
    }
    _primaryArrayParams(b, ub, z, c, d, f, i, ui, j, uj, s, us);
}

JNIEXPORT PNIBuf * JNICALL JavaCritical_io_vproxy_pni_test_Upcall_returnByteArray(PNIBuf * return_) {
    if (_returnByteArray == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_Upcall_returnByteArray function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnByteArray(return_);
}

JNIEXPORT PNIBuf * JNICALL JavaCritical_io_vproxy_pni_test_Upcall_returnBoolArray(PNIBuf * return_) {
    if (_returnBoolArray == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_Upcall_returnBoolArray function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnBoolArray(return_);
}

JNIEXPORT PNIBuf * JNICALL JavaCritical_io_vproxy_pni_test_Upcall_returnCharArray(PNIBuf * return_) {
    if (_returnCharArray == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_Upcall_returnCharArray function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnCharArray(return_);
}

JNIEXPORT PNIBuf * JNICALL JavaCritical_io_vproxy_pni_test_Upcall_returnDoubleArray(PNIBuf * return_) {
    if (_returnDoubleArray == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_Upcall_returnDoubleArray function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnDoubleArray(return_);
}

JNIEXPORT PNIBuf * JNICALL JavaCritical_io_vproxy_pni_test_Upcall_returnFloatArray(PNIBuf * return_) {
    if (_returnFloatArray == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_Upcall_returnFloatArray function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnFloatArray(return_);
}

JNIEXPORT PNIBuf * JNICALL JavaCritical_io_vproxy_pni_test_Upcall_returnIntArray(PNIBuf * return_) {
    if (_returnIntArray == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_Upcall_returnIntArray function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnIntArray(return_);
}

JNIEXPORT PNIBuf * JNICALL JavaCritical_io_vproxy_pni_test_Upcall_returnLongArray(PNIBuf * return_) {
    if (_returnLongArray == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_Upcall_returnLongArray function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnLongArray(return_);
}

JNIEXPORT PNIBuf * JNICALL JavaCritical_io_vproxy_pni_test_Upcall_returnShortArray(PNIBuf * return_) {
    if (_returnShortArray == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_Upcall_returnShortArray function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnShortArray(return_);
}

JNIEXPORT void JNICALL JavaCritical_io_vproxy_pni_test_Upcall_objectParams(ObjectStruct * o) {
    if (_objectParams == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_Upcall_objectParams function pointer is null");
        fflush(stdout);
        exit(1);
    }
    _objectParams(o);
}

JNIEXPORT ObjectStruct * JNICALL JavaCritical_io_vproxy_pni_test_Upcall_returnObject(ObjectStruct * return_) {
    if (_returnObject == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_Upcall_returnObject function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnObject(return_);
}

JNIEXPORT void JNICALL JavaCritical_io_vproxy_pni_test_Upcall_objectArrayParams(PNIBuf * o) {
    if (_objectArrayParams == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_Upcall_objectArrayParams function pointer is null");
        fflush(stdout);
        exit(1);
    }
    _objectArrayParams(o);
}

JNIEXPORT PNIBuf * JNICALL JavaCritical_io_vproxy_pni_test_Upcall_returnObjectArray(PNIBuf * return_) {
    if (_returnObjectArray == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_Upcall_returnObjectArray function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnObjectArray(return_);
}

JNIEXPORT void JNICALL JavaCritical_io_vproxy_pni_test_Upcall_otherParams(PNIBuf * buffer, PNIFunc * voidCallSite, PNIFunc * objCallSite, PNIFunc * refCallSite, void * mem, PNIFunc * voidFunc, PNIFunc * objFunc, PNIFunc * refFunc, PNIRef * ref, PNIRef * rawRef, char * str) {
    if (_otherParams == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_Upcall_otherParams function pointer is null");
        fflush(stdout);
        exit(1);
    }
    _otherParams(buffer, voidCallSite, objCallSite, refCallSite, mem, voidFunc, objFunc, refFunc, ref, rawRef, str);
}

JNIEXPORT PNIBuf * JNICALL JavaCritical_io_vproxy_pni_test_Upcall_returnBuffer(PNIBuf * return_) {
    if (_returnBuffer == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_Upcall_returnBuffer function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnBuffer(return_);
}

JNIEXPORT void * JNICALL JavaCritical_io_vproxy_pni_test_Upcall_returnMem() {
    if (_returnMem == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_Upcall_returnMem function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnMem();
}

JNIEXPORT PNIFunc * JNICALL JavaCritical_io_vproxy_pni_test_Upcall_returnVoidFunc() {
    if (_returnVoidFunc == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_Upcall_returnVoidFunc function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnVoidFunc();
}

JNIEXPORT PNIFunc * JNICALL JavaCritical_io_vproxy_pni_test_Upcall_returnObjFunc() {
    if (_returnObjFunc == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_Upcall_returnObjFunc function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnObjFunc();
}

JNIEXPORT PNIFunc * JNICALL JavaCritical_io_vproxy_pni_test_Upcall_returnRefFunc() {
    if (_returnRefFunc == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_Upcall_returnRefFunc function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnRefFunc();
}

JNIEXPORT PNIRef * JNICALL JavaCritical_io_vproxy_pni_test_Upcall_returnRef() {
    if (_returnRef == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_Upcall_returnRef function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnRef();
}

JNIEXPORT char * JNICALL JavaCritical_io_vproxy_pni_test_Upcall_returnStr() {
    if (_returnStr == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_Upcall_returnStr function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnStr();
}

#ifdef __cplusplus
}
#endif
// metadata.generator-version: pni test
// sha256:fbf243eba301a3eb47689b9e60d00bd1ddf9b11358103c129cc933139c9278e6
