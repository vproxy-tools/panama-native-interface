#include "pni.h"

static PNIFuncInvokeFunc _PNIFuncInvokeFunc;

JNIEXPORT PNIFuncInvokeFunc JNICALL GetPNIFuncInvokeFunc(void) {
    return _PNIFuncInvokeFunc;
}
JNIEXPORT void JNICALL SetPNIFuncInvokeFunc(PNIFuncInvokeFunc f) {
    _PNIFuncInvokeFunc = f;
}

static PNIFuncReleaseFunc _PNIFuncReleaseFunc;

JNIEXPORT PNIFuncReleaseFunc JNICALL GetPNIFuncReleaseFunc(void) {
    return _PNIFuncReleaseFunc;
}
JNIEXPORT void JNICALL SetPNIFuncReleaseFunc(PNIFuncReleaseFunc f) {
    _PNIFuncReleaseFunc = f;
}

static PNIRefReleaseFunc _PNIRefReleaseFunc;

JNIEXPORT PNIRefReleaseFunc JNICALL GetPNIRefReleaseFunc(void) {
    return _PNIRefReleaseFunc;
}
JNIEXPORT void JNICALL SetPNIRefReleaseFunc(PNIRefReleaseFunc f) {
    _PNIRefReleaseFunc = f;
}
