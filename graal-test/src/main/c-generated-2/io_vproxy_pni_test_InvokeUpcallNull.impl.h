#include "io_vproxy_pni_test_InvokeUpcallNull.h"

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_pni_test_InvokeUpcallNull_testParam(void) {
    return JavaCritical_io_vproxy_pni_test_UpcallNull_testParam(
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        NULL);
}

JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_pni_test_InvokeUpcallNull_testParamRaw(void) {
    return JavaCritical_io_vproxy_pni_test_UpcallNull_testParamRaw(NULL, NULL, NULL, NULL);
}

JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_pni_test_InvokeUpcallNull_returnO(void) {
    ObjectStruct o;
    return JavaCritical_io_vproxy_pni_test_UpcallNull_returnO(&o) == NULL;
}

JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_pni_test_InvokeUpcallNull_returnStr(void) {
    return JavaCritical_io_vproxy_pni_test_UpcallNull_returnStr() == NULL;
}

JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_pni_test_InvokeUpcallNull_returnSeg(void) {
    return JavaCritical_io_vproxy_pni_test_UpcallNull_returnSeg() == NULL;
}

JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_pni_test_InvokeUpcallNull_returnBuf(void) {
    PNIBuf b;
    return JavaCritical_io_vproxy_pni_test_UpcallNull_returnBuf(&b) == NULL;
}

JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_pni_test_InvokeUpcallNull_returnByteArr(void) {
    PNIBuf_byte b;
    return JavaCritical_io_vproxy_pni_test_UpcallNull_returnByteArr(&b) == NULL;
}

JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_pni_test_InvokeUpcallNull_returnBoolArr(void) {
    PNIBuf_bool b;
    return JavaCritical_io_vproxy_pni_test_UpcallNull_returnBoolArr(&b) == NULL;
}

JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_pni_test_InvokeUpcallNull_returnCharArr(void) {
    PNIBuf_char b;
    return JavaCritical_io_vproxy_pni_test_UpcallNull_returnCharArr(&b) == NULL;
}

JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_pni_test_InvokeUpcallNull_returnFloatArr(void) {
    PNIBuf_float b;
    return JavaCritical_io_vproxy_pni_test_UpcallNull_returnFloatArr(&b) == NULL;
}

JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_pni_test_InvokeUpcallNull_returnDoubleArr(void) {
    PNIBuf_double b;
    return JavaCritical_io_vproxy_pni_test_UpcallNull_returnDoubleArr(&b) == NULL;
}

JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_pni_test_InvokeUpcallNull_returnIntArr(void) {
    PNIBuf_int b;
    return JavaCritical_io_vproxy_pni_test_UpcallNull_returnIntArr(&b) == NULL;
}

JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_pni_test_InvokeUpcallNull_returnLongArr(void) {
    PNIBuf_long b;
    return JavaCritical_io_vproxy_pni_test_UpcallNull_returnLongArr(&b) == NULL;
}

JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_pni_test_InvokeUpcallNull_returnShortArr(void) {
    PNIBuf_short b;
    return JavaCritical_io_vproxy_pni_test_UpcallNull_returnShortArr(&b) == NULL;
}

JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_pni_test_InvokeUpcallNull_returnOArr(void) {
    PNIBuf_ObjectStruct b;
    return JavaCritical_io_vproxy_pni_test_UpcallNull_returnOArr(&b) == NULL;
}

JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_pni_test_InvokeUpcallNull_returnRef(void) {
    return JavaCritical_io_vproxy_pni_test_UpcallNull_returnRef() == NULL;
}

JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_pni_test_InvokeUpcallNull_returnFunc(void) {
    return JavaCritical_io_vproxy_pni_test_UpcallNull_returnFunc() == NULL;
}

JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_pni_test_InvokeUpcallNull_returnFuncVoid(void) {
    return JavaCritical_io_vproxy_pni_test_UpcallNull_returnFuncVoid() == NULL;
}

JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_pni_test_InvokeUpcallNull_returnFuncRef(void) {
    return JavaCritical_io_vproxy_pni_test_UpcallNull_returnFuncRef() == NULL;
}

#ifdef __cplusplus
}
#endif
// metadata.generator-version: pni test
// sha256:4da23a6d7a230c80dcd8ca1a52bdab5dd442133319d703f555c48c5364e8ba8d
