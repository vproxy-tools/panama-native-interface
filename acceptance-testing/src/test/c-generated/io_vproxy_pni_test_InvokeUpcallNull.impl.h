#include "io_vproxy_pni_test_InvokeUpcallNull.h"

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_pni_test_InvokeUpcallNull_testParam() {
    return JavaCritical_io_vproxy_pni_test_UpcallNull_testParam(
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        NULL);
}

JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_pni_test_InvokeUpcallNull_testParamRaw() {
    return JavaCritical_io_vproxy_pni_test_UpcallNull_testParamRaw(NULL, NULL, NULL, NULL);
}

JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_pni_test_InvokeUpcallNull_returnO() {
    ObjectStruct o;
    return JavaCritical_io_vproxy_pni_test_UpcallNull_returnO(&o) == NULL;
}

JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_pni_test_InvokeUpcallNull_returnStr() {
    return JavaCritical_io_vproxy_pni_test_UpcallNull_returnStr() == NULL;
}

JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_pni_test_InvokeUpcallNull_returnSeg() {
    return JavaCritical_io_vproxy_pni_test_UpcallNull_returnSeg() == NULL;
}

JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_pni_test_InvokeUpcallNull_returnBuf() {
    PNIBuf b;
    return JavaCritical_io_vproxy_pni_test_UpcallNull_returnBuf(&b) == NULL;
}

JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_pni_test_InvokeUpcallNull_returnByteArr() {
    PNIBuf b;
    return JavaCritical_io_vproxy_pni_test_UpcallNull_returnByteArr(&b) == NULL;
}

JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_pni_test_InvokeUpcallNull_returnBoolArr() {
    PNIBuf b;
    return JavaCritical_io_vproxy_pni_test_UpcallNull_returnBoolArr(&b) == NULL;
}

JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_pni_test_InvokeUpcallNull_returnCharArr() {
    PNIBuf b;
    return JavaCritical_io_vproxy_pni_test_UpcallNull_returnCharArr(&b) == NULL;
}

JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_pni_test_InvokeUpcallNull_returnFloatArr() {
    PNIBuf b;
    return JavaCritical_io_vproxy_pni_test_UpcallNull_returnFloatArr(&b) == NULL;
}

JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_pni_test_InvokeUpcallNull_returnDoubleArr() {
    PNIBuf b;
    return JavaCritical_io_vproxy_pni_test_UpcallNull_returnDoubleArr(&b) == NULL;
}

JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_pni_test_InvokeUpcallNull_returnIntArr() {
    PNIBuf b;
    return JavaCritical_io_vproxy_pni_test_UpcallNull_returnIntArr(&b) == NULL;
}

JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_pni_test_InvokeUpcallNull_returnLongArr() {
    PNIBuf b;
    return JavaCritical_io_vproxy_pni_test_UpcallNull_returnLongArr(&b) == NULL;
}

JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_pni_test_InvokeUpcallNull_returnShortArr() {
    PNIBuf b;
    return JavaCritical_io_vproxy_pni_test_UpcallNull_returnShortArr(&b) == NULL;
}

JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_pni_test_InvokeUpcallNull_returnOArr() {
    PNIBuf b;
    return JavaCritical_io_vproxy_pni_test_UpcallNull_returnOArr(&b) == NULL;
}

JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_pni_test_InvokeUpcallNull_returnRef() {
    return JavaCritical_io_vproxy_pni_test_UpcallNull_returnRef() == NULL;
}

JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_pni_test_InvokeUpcallNull_returnFunc() {
    return JavaCritical_io_vproxy_pni_test_UpcallNull_returnFunc() == NULL;
}

JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_pni_test_InvokeUpcallNull_returnFuncVoid() {
    return JavaCritical_io_vproxy_pni_test_UpcallNull_returnFuncVoid() == NULL;
}

JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_pni_test_InvokeUpcallNull_returnFuncRef() {
    return JavaCritical_io_vproxy_pni_test_UpcallNull_returnFuncRef() == NULL;
}

#ifdef __cplusplus
}
#endif
// metadata.generator-version: pni test
// sha256:08b4e4244d80cb9a66441f9e7eba83804fa2d93ab69369a2591a87fe8744ceea
