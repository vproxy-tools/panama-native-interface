#include "io_vproxy_pni_test_InvokeUpcall.h"

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT void JNICALL JavaCritical_io_vproxy_pni_test_InvokeUpcall_primaryParams(int8_t b, int8_t ub, uint8_t z, uint16_t c, double d, float f, int32_t i, int32_t ui, int64_t j, int64_t uj, int16_t s, int16_t us) {
    JavaCritical_io_vproxy_pni_test_Upcall_primaryParams(b, ub, z, c, d, f, i, ui, j, uj, s, us);
}

JNIEXPORT int8_t JNICALL JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnByte() {
    return JavaCritical_io_vproxy_pni_test_Upcall_returnByte();
}

JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnBool() {
    return JavaCritical_io_vproxy_pni_test_Upcall_returnBool();
}

JNIEXPORT uint16_t JNICALL JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnChar() {
    return JavaCritical_io_vproxy_pni_test_Upcall_returnChar();
}

JNIEXPORT double JNICALL JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnDouble() {
    return JavaCritical_io_vproxy_pni_test_Upcall_returnDouble();
}

JNIEXPORT float JNICALL JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnFloat() {
    return JavaCritical_io_vproxy_pni_test_Upcall_returnFloat();
}

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnInt() {
    return JavaCritical_io_vproxy_pni_test_Upcall_returnInt();
}

JNIEXPORT int64_t JNICALL JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnLong() {
    return JavaCritical_io_vproxy_pni_test_Upcall_returnLong();
}

JNIEXPORT int16_t JNICALL JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnShort() {
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

JNIEXPORT void * JNICALL JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnMem() {
    return JavaCritical_io_vproxy_pni_test_Upcall_returnMem();
}

JNIEXPORT PNIFunc * JNICALL JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnVoidFunc() {
    return JavaCritical_io_vproxy_pni_test_Upcall_returnVoidFunc();
}

JNIEXPORT PNIFunc * JNICALL JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnObjFunc() {
    return JavaCritical_io_vproxy_pni_test_Upcall_returnObjFunc();
}

JNIEXPORT PNIFunc * JNICALL JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnRefFunc() {
    return JavaCritical_io_vproxy_pni_test_Upcall_returnRefFunc();
}

JNIEXPORT PNIRef * JNICALL JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnRef() {
    return JavaCritical_io_vproxy_pni_test_Upcall_returnRef();
}

JNIEXPORT char * JNICALL JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnStr() {
    return JavaCritical_io_vproxy_pni_test_Upcall_returnStr();
}

#ifdef __cplusplus
}
#endif
// metadata.generator-version: pni test
// sha256:dd8e317b5b6ff1cda818589b41b6639426499f7a2d4290efff637e63c9ac4b0b
