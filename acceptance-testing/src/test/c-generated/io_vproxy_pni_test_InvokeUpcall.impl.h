#include "io_vproxy_pni_test_InvokeUpcall.h"

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT void JNICALL JavaCritical_io_vproxy_pni_test_InvokeUpcall_primaryParams(int8_t b, int8_t ub, uint8_t z, uint16_t c, double d, float f, int32_t i, int32_t ui, int64_t j, int64_t uj, int16_t s, int16_t us) {
    JavaCritical_io_vproxy_pni_test_Upcall_primaryParams(b, ub, z, c, d, f, i, ui, j, uj, s, us);
}

JNIEXPORT int8_t JNICALL JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnByte(void) {
    return JavaCritical_io_vproxy_pni_test_Upcall_returnByte();
}

JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnBool(void) {
    return JavaCritical_io_vproxy_pni_test_Upcall_returnBool();
}

JNIEXPORT uint16_t JNICALL JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnChar(void) {
    return JavaCritical_io_vproxy_pni_test_Upcall_returnChar();
}

JNIEXPORT double JNICALL JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnDouble(void) {
    return JavaCritical_io_vproxy_pni_test_Upcall_returnDouble();
}

JNIEXPORT float JNICALL JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnFloat(void) {
    return JavaCritical_io_vproxy_pni_test_Upcall_returnFloat();
}

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnInt(void) {
    return JavaCritical_io_vproxy_pni_test_Upcall_returnInt();
}

JNIEXPORT int64_t JNICALL JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnLong(void) {
    return JavaCritical_io_vproxy_pni_test_Upcall_returnLong();
}

JNIEXPORT int16_t JNICALL JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnShort(void) {
    return JavaCritical_io_vproxy_pni_test_Upcall_returnShort();
}

JNIEXPORT void JNICALL JavaCritical_io_vproxy_pni_test_InvokeUpcall_primaryArrayParams(PNIBuf * b, PNIBuf * ub, PNIBuf * z, PNIBuf * c, PNIBuf * d, PNIBuf * f, PNIBuf * i, PNIBuf * ui, PNIBuf * j, PNIBuf * uj, PNIBuf * s, PNIBuf * us) {
    JavaCritical_io_vproxy_pni_test_Upcall_primaryArrayParams(b, ub, z, c, d, f, i, ui, j, uj, s, us);
}

JNIEXPORT PNIBuf * JNICALL JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnByteArray(PNIBuf * return_) {
    return JavaCritical_io_vproxy_pni_test_Upcall_returnByteArray(return_);
}

JNIEXPORT PNIBuf * JNICALL JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnBoolArray(PNIBuf * return_) {
    return JavaCritical_io_vproxy_pni_test_Upcall_returnBoolArray(return_);
}

JNIEXPORT PNIBuf * JNICALL JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnCharArray(PNIBuf * return_) {
    return JavaCritical_io_vproxy_pni_test_Upcall_returnCharArray(return_);
}

JNIEXPORT PNIBuf * JNICALL JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnDoubleArray(PNIBuf * return_) {
    return JavaCritical_io_vproxy_pni_test_Upcall_returnDoubleArray(return_);
}

JNIEXPORT PNIBuf * JNICALL JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnFloatArray(PNIBuf * return_) {
    return JavaCritical_io_vproxy_pni_test_Upcall_returnFloatArray(return_);
}

JNIEXPORT PNIBuf * JNICALL JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnIntArray(PNIBuf * return_) {
    return JavaCritical_io_vproxy_pni_test_Upcall_returnIntArray(return_);
}

JNIEXPORT PNIBuf * JNICALL JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnLongArray(PNIBuf * return_) {
    return JavaCritical_io_vproxy_pni_test_Upcall_returnLongArray(return_);
}

JNIEXPORT PNIBuf * JNICALL JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnShortArray(PNIBuf * return_) {
    return JavaCritical_io_vproxy_pni_test_Upcall_returnShortArray(return_);
}

JNIEXPORT void JNICALL JavaCritical_io_vproxy_pni_test_InvokeUpcall_objectParams(ObjectStruct * o) {
    JavaCritical_io_vproxy_pni_test_Upcall_objectParams(o);
}

JNIEXPORT ObjectStruct * JNICALL JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnObject(ObjectStruct * return_) {
    return JavaCritical_io_vproxy_pni_test_Upcall_returnObject(return_);
}

JNIEXPORT void JNICALL JavaCritical_io_vproxy_pni_test_InvokeUpcall_objectArrayParams(PNIBuf * o) {
    JavaCritical_io_vproxy_pni_test_Upcall_objectArrayParams(o);
}

JNIEXPORT PNIBuf * JNICALL JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnObjectArray(PNIBuf * return_) {
    return JavaCritical_io_vproxy_pni_test_Upcall_returnObjectArray(return_);
}

JNIEXPORT void JNICALL JavaCritical_io_vproxy_pni_test_InvokeUpcall_otherParams(PNIBuf * buffer, PNIFunc * voidCallSite, PNIFunc * objCallSite, PNIFunc * refCallSite, void * mem, PNIFunc * voidFunc, PNIFunc * objFunc, PNIFunc * refFunc, PNIRef * ref, PNIRef * rawRef, char * str) {
    JavaCritical_io_vproxy_pni_test_Upcall_otherParams(buffer, voidCallSite, objCallSite, refCallSite, mem, voidFunc, objFunc, refFunc, ref, rawRef, str);
}

JNIEXPORT PNIBuf * JNICALL JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnBuffer(PNIBuf * return_) {
    return JavaCritical_io_vproxy_pni_test_Upcall_returnBuffer(return_);
}

JNIEXPORT void * JNICALL JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnMem(void) {
    return JavaCritical_io_vproxy_pni_test_Upcall_returnMem();
}

JNIEXPORT PNIFunc * JNICALL JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnVoidFunc(void) {
    return JavaCritical_io_vproxy_pni_test_Upcall_returnVoidFunc();
}

JNIEXPORT PNIFunc * JNICALL JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnObjFunc(void) {
    return JavaCritical_io_vproxy_pni_test_Upcall_returnObjFunc();
}

JNIEXPORT PNIFunc * JNICALL JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnRefFunc(void) {
    return JavaCritical_io_vproxy_pni_test_Upcall_returnRefFunc();
}

JNIEXPORT PNIRef * JNICALL JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnRef(void) {
    return JavaCritical_io_vproxy_pni_test_Upcall_returnRef();
}

JNIEXPORT char * JNICALL JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnStr(void) {
    return JavaCritical_io_vproxy_pni_test_Upcall_returnStr();
}

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_pni_test_InvokeUpcall_sum(int32_t a, int32_t b) {
    return pni_sum(a, b);
}

#ifdef __cplusplus
}
#endif
// metadata.generator-version: pni test
// sha256:db6b980ccc4de749da5c1c3b517de7fa383de59b8c4f09ae8605b3328d8c1644
