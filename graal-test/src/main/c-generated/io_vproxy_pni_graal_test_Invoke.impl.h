#include "io_vproxy_pni_graal_test_Invoke.h"

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_pni_graal_test_Invoke_invokeSum(void * func, void * thread, int32_t a, int32_t b) {
    int (*f)(void*,int,int) = func;
    return f(thread, a, b);
}

JNIEXPORT void JNICALL JavaCritical_io_vproxy_pni_graal_test_Invoke_invokePtr(void * func, void * thread, int32_t a, void * p) {
    void (*f)(void*,int,void*) = func;
    return f(thread, a, p);
}

JNIEXPORT void JNICALL JavaCritical_io_vproxy_pni_graal_test_Invoke_releaseRef(PNIRef * ref) {
    PNIRefRelease(ref);
}

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_pni_graal_test_Invoke_callFunc(PNIFunc * func) {
    int res = PNIFuncInvoke(func, NULL);
    PNIFuncRelease(func);
    return res;
}

#ifdef __cplusplus
}
#endif
// metadata.generator-version: pni test
// sha256:85d6ce503db140177c54ea0d7f4bd3587e2e78b206eb61091c4390ec41c9e300
