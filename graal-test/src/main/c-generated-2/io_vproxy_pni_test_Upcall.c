#include "io_vproxy_pni_test_Upcall.h"
#include <stdio.h>
#include <stdlib.h>

#ifdef __cplusplus
extern "C" {
#endif

static void (*_primaryParams)(void *,int8_t,uint8_t,uint8_t,uint16_t,double,float,int32_t,uint32_t,int64_t,uint64_t,int16_t,uint16_t);
static int8_t (*_returnByte)(void *);
static uint8_t (*_returnBool)(void *);
static uint16_t (*_returnChar)(void *);
static double (*_returnDouble)(void *);
static float (*_returnFloat)(void *);
static int32_t (*_returnInt)(void *);
static int64_t (*_returnLong)(void *);
static int16_t (*_returnShort)(void *);
static void (*_primaryArrayParams)(void *,PNIBuf_byte *,PNIBuf_ubyte *,PNIBuf_bool *,PNIBuf_char *,PNIBuf_double *,PNIBuf_float *,PNIBuf_int *,PNIBuf_uint *,PNIBuf_long *,PNIBuf_ulong *,PNIBuf_short *,PNIBuf_ushort *);
static PNIBuf_byte * (*_returnByteArray)(void *,PNIBuf_byte *);
static PNIBuf_bool * (*_returnBoolArray)(void *,PNIBuf_bool *);
static PNIBuf_char * (*_returnCharArray)(void *,PNIBuf_char *);
static PNIBuf_double * (*_returnDoubleArray)(void *,PNIBuf_double *);
static PNIBuf_float * (*_returnFloatArray)(void *,PNIBuf_float *);
static PNIBuf_int * (*_returnIntArray)(void *,PNIBuf_int *);
static PNIBuf_long * (*_returnLongArray)(void *,PNIBuf_long *);
static PNIBuf_short * (*_returnShortArray)(void *,PNIBuf_short *);
static void (*_objectParams)(void *,ObjectStruct *);
static ObjectStruct * (*_returnObject)(void *,ObjectStruct *);
static void (*_pointerArrayParams)(void *,PNIBuf_ptr *);
static PNIBuf_ptr * (*_returnPointerArray)(void *,PNIBuf_ptr *);
static void (*_objectArrayParams)(void *,PNIBuf_ObjectStruct *);
static PNIBuf_ObjectStruct * (*_returnObjectArray)(void *,PNIBuf_ObjectStruct *);
static void (*_otherParams)(void *,PNIBuf *,PNIFunc *,PNIFunc *,PNIFunc *,void *,PNIFunc *,PNIFunc *,PNIFunc *,PNIRef *,PNIRef *,char *);
static PNIBuf * (*_returnBuffer)(void *,PNIBuf *);
static void * (*_returnMem)(void *);
static PNIFunc * (*_returnVoidFunc)(void *);
static PNIFunc * (*_returnObjFunc)(void *);
static PNIFunc * (*_returnRefFunc)(void *);
static PNIRef * (*_returnRef)(void *);
static char * (*_returnStr)(void *);
static int32_t (*_sum)(void *,int32_t,int32_t);

JNIEXPORT void JNICALL JavaCritical_io_vproxy_pni_test_Upcall_INIT(
    void (*primaryParams)(void *,int8_t,uint8_t,uint8_t,uint16_t,double,float,int32_t,uint32_t,int64_t,uint64_t,int16_t,uint16_t),
    int8_t (*returnByte)(void *),
    uint8_t (*returnBool)(void *),
    uint16_t (*returnChar)(void *),
    double (*returnDouble)(void *),
    float (*returnFloat)(void *),
    int32_t (*returnInt)(void *),
    int64_t (*returnLong)(void *),
    int16_t (*returnShort)(void *),
    void (*primaryArrayParams)(void *,PNIBuf_byte *,PNIBuf_ubyte *,PNIBuf_bool *,PNIBuf_char *,PNIBuf_double *,PNIBuf_float *,PNIBuf_int *,PNIBuf_uint *,PNIBuf_long *,PNIBuf_ulong *,PNIBuf_short *,PNIBuf_ushort *),
    PNIBuf_byte * (*returnByteArray)(void *,PNIBuf_byte *),
    PNIBuf_bool * (*returnBoolArray)(void *,PNIBuf_bool *),
    PNIBuf_char * (*returnCharArray)(void *,PNIBuf_char *),
    PNIBuf_double * (*returnDoubleArray)(void *,PNIBuf_double *),
    PNIBuf_float * (*returnFloatArray)(void *,PNIBuf_float *),
    PNIBuf_int * (*returnIntArray)(void *,PNIBuf_int *),
    PNIBuf_long * (*returnLongArray)(void *,PNIBuf_long *),
    PNIBuf_short * (*returnShortArray)(void *,PNIBuf_short *),
    void (*objectParams)(void *,ObjectStruct *),
    ObjectStruct * (*returnObject)(void *,ObjectStruct *),
    void (*pointerArrayParams)(void *,PNIBuf_ptr *),
    PNIBuf_ptr * (*returnPointerArray)(void *,PNIBuf_ptr *),
    void (*objectArrayParams)(void *,PNIBuf_ObjectStruct *),
    PNIBuf_ObjectStruct * (*returnObjectArray)(void *,PNIBuf_ObjectStruct *),
    void (*otherParams)(void *,PNIBuf *,PNIFunc *,PNIFunc *,PNIFunc *,void *,PNIFunc *,PNIFunc *,PNIFunc *,PNIRef *,PNIRef *,char *),
    PNIBuf * (*returnBuffer)(void *,PNIBuf *),
    void * (*returnMem)(void *),
    PNIFunc * (*returnVoidFunc)(void *),
    PNIFunc * (*returnObjFunc)(void *),
    PNIFunc * (*returnRefFunc)(void *),
    PNIRef * (*returnRef)(void *),
    char * (*returnStr)(void *),
    int32_t (*sum)(void *,int32_t,int32_t)
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
    _pointerArrayParams = pointerArrayParams;
    _returnPointerArray = returnPointerArray;
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
    _sum = sum;
}

JNIEXPORT void JNICALL JavaCritical_io_vproxy_pni_test_Upcall_primaryParams(int8_t b, uint8_t ub, uint8_t z, uint16_t c, double d, float f, int32_t i, uint32_t ui, int64_t j, uint64_t uj, int16_t s, uint16_t us) {
    if (_primaryParams == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_Upcall_primaryParams function pointer is null");
        fflush(stdout);
        exit(1);
    }
    _primaryParams(GetPNIGraalThread(), b, ub, z, c, d, f, i, ui, j, uj, s, us);
}

JNIEXPORT int8_t JNICALL JavaCritical_io_vproxy_pni_test_Upcall_returnByte(void) {
    if (_returnByte == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_Upcall_returnByte function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnByte(GetPNIGraalThread());
}

JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_pni_test_Upcall_returnBool(void) {
    if (_returnBool == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_Upcall_returnBool function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnBool(GetPNIGraalThread());
}

JNIEXPORT uint16_t JNICALL JavaCritical_io_vproxy_pni_test_Upcall_returnChar(void) {
    if (_returnChar == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_Upcall_returnChar function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnChar(GetPNIGraalThread());
}

JNIEXPORT double JNICALL JavaCritical_io_vproxy_pni_test_Upcall_returnDouble(void) {
    if (_returnDouble == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_Upcall_returnDouble function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnDouble(GetPNIGraalThread());
}

JNIEXPORT float JNICALL JavaCritical_io_vproxy_pni_test_Upcall_returnFloat(void) {
    if (_returnFloat == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_Upcall_returnFloat function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnFloat(GetPNIGraalThread());
}

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_pni_test_Upcall_returnInt(void) {
    if (_returnInt == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_Upcall_returnInt function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnInt(GetPNIGraalThread());
}

JNIEXPORT int64_t JNICALL JavaCritical_io_vproxy_pni_test_Upcall_returnLong(void) {
    if (_returnLong == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_Upcall_returnLong function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnLong(GetPNIGraalThread());
}

JNIEXPORT int16_t JNICALL JavaCritical_io_vproxy_pni_test_Upcall_returnShort(void) {
    if (_returnShort == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_Upcall_returnShort function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnShort(GetPNIGraalThread());
}

JNIEXPORT void JNICALL JavaCritical_io_vproxy_pni_test_Upcall_primaryArrayParams(PNIBuf_byte * b, PNIBuf_ubyte * ub, PNIBuf_bool * z, PNIBuf_char * c, PNIBuf_double * d, PNIBuf_float * f, PNIBuf_int * i, PNIBuf_uint * ui, PNIBuf_long * j, PNIBuf_ulong * uj, PNIBuf_short * s, PNIBuf_ushort * us) {
    if (_primaryArrayParams == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_Upcall_primaryArrayParams function pointer is null");
        fflush(stdout);
        exit(1);
    }
    _primaryArrayParams(GetPNIGraalThread(), b, ub, z, c, d, f, i, ui, j, uj, s, us);
}

JNIEXPORT PNIBuf_byte * JNICALL JavaCritical_io_vproxy_pni_test_Upcall_returnByteArray(PNIBuf_byte * return_) {
    if (_returnByteArray == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_Upcall_returnByteArray function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnByteArray(GetPNIGraalThread(), return_);
}

JNIEXPORT PNIBuf_bool * JNICALL JavaCritical_io_vproxy_pni_test_Upcall_returnBoolArray(PNIBuf_bool * return_) {
    if (_returnBoolArray == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_Upcall_returnBoolArray function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnBoolArray(GetPNIGraalThread(), return_);
}

JNIEXPORT PNIBuf_char * JNICALL JavaCritical_io_vproxy_pni_test_Upcall_returnCharArray(PNIBuf_char * return_) {
    if (_returnCharArray == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_Upcall_returnCharArray function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnCharArray(GetPNIGraalThread(), return_);
}

JNIEXPORT PNIBuf_double * JNICALL JavaCritical_io_vproxy_pni_test_Upcall_returnDoubleArray(PNIBuf_double * return_) {
    if (_returnDoubleArray == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_Upcall_returnDoubleArray function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnDoubleArray(GetPNIGraalThread(), return_);
}

JNIEXPORT PNIBuf_float * JNICALL JavaCritical_io_vproxy_pni_test_Upcall_returnFloatArray(PNIBuf_float * return_) {
    if (_returnFloatArray == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_Upcall_returnFloatArray function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnFloatArray(GetPNIGraalThread(), return_);
}

JNIEXPORT PNIBuf_int * JNICALL JavaCritical_io_vproxy_pni_test_Upcall_returnIntArray(PNIBuf_int * return_) {
    if (_returnIntArray == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_Upcall_returnIntArray function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnIntArray(GetPNIGraalThread(), return_);
}

JNIEXPORT PNIBuf_long * JNICALL JavaCritical_io_vproxy_pni_test_Upcall_returnLongArray(PNIBuf_long * return_) {
    if (_returnLongArray == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_Upcall_returnLongArray function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnLongArray(GetPNIGraalThread(), return_);
}

JNIEXPORT PNIBuf_short * JNICALL JavaCritical_io_vproxy_pni_test_Upcall_returnShortArray(PNIBuf_short * return_) {
    if (_returnShortArray == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_Upcall_returnShortArray function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnShortArray(GetPNIGraalThread(), return_);
}

JNIEXPORT void JNICALL JavaCritical_io_vproxy_pni_test_Upcall_objectParams(ObjectStruct * o) {
    if (_objectParams == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_Upcall_objectParams function pointer is null");
        fflush(stdout);
        exit(1);
    }
    _objectParams(GetPNIGraalThread(), o);
}

JNIEXPORT ObjectStruct * JNICALL JavaCritical_io_vproxy_pni_test_Upcall_returnObject(ObjectStruct * return_) {
    if (_returnObject == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_Upcall_returnObject function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnObject(GetPNIGraalThread(), return_);
}

JNIEXPORT void JNICALL JavaCritical_io_vproxy_pni_test_Upcall_pointerArrayParams(PNIBuf_ptr * p) {
    if (_pointerArrayParams == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_Upcall_pointerArrayParams function pointer is null");
        fflush(stdout);
        exit(1);
    }
    _pointerArrayParams(GetPNIGraalThread(), p);
}

JNIEXPORT PNIBuf_ptr * JNICALL JavaCritical_io_vproxy_pni_test_Upcall_returnPointerArray(PNIBuf_ptr * return_) {
    if (_returnPointerArray == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_Upcall_returnPointerArray function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnPointerArray(GetPNIGraalThread(), return_);
}

JNIEXPORT void JNICALL JavaCritical_io_vproxy_pni_test_Upcall_objectArrayParams(PNIBuf_ObjectStruct * o) {
    if (_objectArrayParams == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_Upcall_objectArrayParams function pointer is null");
        fflush(stdout);
        exit(1);
    }
    _objectArrayParams(GetPNIGraalThread(), o);
}

JNIEXPORT PNIBuf_ObjectStruct * JNICALL JavaCritical_io_vproxy_pni_test_Upcall_returnObjectArray(PNIBuf_ObjectStruct * return_) {
    if (_returnObjectArray == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_Upcall_returnObjectArray function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnObjectArray(GetPNIGraalThread(), return_);
}

JNIEXPORT void JNICALL JavaCritical_io_vproxy_pni_test_Upcall_otherParams(PNIBuf * buffer, PNIFunc * voidCallSite, PNIFunc * objCallSite, PNIFunc * refCallSite, void * mem, PNIFunc * voidFunc, PNIFunc * objFunc, PNIFunc * refFunc, PNIRef * ref, PNIRef * rawRef, char * str) {
    if (_otherParams == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_Upcall_otherParams function pointer is null");
        fflush(stdout);
        exit(1);
    }
    _otherParams(GetPNIGraalThread(), buffer, voidCallSite, objCallSite, refCallSite, mem, voidFunc, objFunc, refFunc, ref, rawRef, str);
}

JNIEXPORT PNIBuf * JNICALL JavaCritical_io_vproxy_pni_test_Upcall_returnBuffer(PNIBuf * return_) {
    if (_returnBuffer == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_Upcall_returnBuffer function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnBuffer(GetPNIGraalThread(), return_);
}

JNIEXPORT void * JNICALL JavaCritical_io_vproxy_pni_test_Upcall_returnMem(void) {
    if (_returnMem == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_Upcall_returnMem function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnMem(GetPNIGraalThread());
}

JNIEXPORT PNIFunc * JNICALL JavaCritical_io_vproxy_pni_test_Upcall_returnVoidFunc(void) {
    if (_returnVoidFunc == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_Upcall_returnVoidFunc function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnVoidFunc(GetPNIGraalThread());
}

JNIEXPORT PNIFunc * JNICALL JavaCritical_io_vproxy_pni_test_Upcall_returnObjFunc(void) {
    if (_returnObjFunc == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_Upcall_returnObjFunc function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnObjFunc(GetPNIGraalThread());
}

JNIEXPORT PNIFunc * JNICALL JavaCritical_io_vproxy_pni_test_Upcall_returnRefFunc(void) {
    if (_returnRefFunc == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_Upcall_returnRefFunc function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnRefFunc(GetPNIGraalThread());
}

JNIEXPORT PNIRef * JNICALL JavaCritical_io_vproxy_pni_test_Upcall_returnRef(void) {
    if (_returnRef == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_Upcall_returnRef function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnRef(GetPNIGraalThread());
}

JNIEXPORT char * JNICALL JavaCritical_io_vproxy_pni_test_Upcall_returnStr(void) {
    if (_returnStr == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_Upcall_returnStr function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnStr(GetPNIGraalThread());
}

JNIEXPORT int32_t JNICALL pni_sum(int32_t a, int32_t b) {
    if (_sum == NULL) {
        printf("pni_sum function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _sum(GetPNIGraalThread(), a, b);
}

#ifdef __cplusplus
}
#endif
// metadata.generator-version: pni test
// sha256:a7f5633e70fbc00acc6352a62ac57384ea30bb06f6d9765864650d95c9102f43
