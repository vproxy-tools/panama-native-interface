#include "pni.h"

#if PNI_GRAAL

static __thread void* _graalThread;
static int _PNIHasGraalThread;

JNIEXPORT void JNICALL PNISetGraalThread(void* thread) {
    _PNIHasGraalThread = 1;
    _graalThread = thread;
}

JNIEXPORT void* JNICALL PNIGetGraalThread(void) {
    return _graalThread;
}

JNIEXPORT int JNICALL PNIHasGraalThread(void) {
    return _PNIHasGraalThread;
}

#endif // PNI_GRAAL

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
