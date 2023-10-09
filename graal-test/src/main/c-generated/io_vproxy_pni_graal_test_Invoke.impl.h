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

JNIEXPORT void JNICALL JavaCritical_io_vproxy_pni_graal_test_Invoke_invokeDoNothingUpcall(void * func) {
    void (*f)(void) = func;
    f();
}

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_pni_graal_test_Invoke_invokeIntUpcall(void * func, int32_t a) {
    int (*f)(int) = func;
    return f(a);
}

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_pni_graal_test_Invoke_invokeRefUpcall(void * func, PNIRef * ref) {
    int (*f)(PNIRef*) = func;
    int res = f(ref);
    PNIRefRelease(ref);
    return res;
}

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_pni_graal_test_Invoke_invokeFuncUpcall(void * func, PNIFunc * ff) {
    int (*f)(PNIFunc*) = func;
    int res = f(ff);
    PNIFuncRelease(ff);
    return res;
}

JNIEXPORT void * JNICALL JavaCritical_io_vproxy_pni_graal_test_Invoke_invokeReturnSegUpcall(void * func) {
    void* (*f)() = func;
    return f();
}

#ifdef __cplusplus
}
#endif
// metadata.generator-version: pni test
// sha256:e93f13fcbf47edcae59141d9cdfc08c06bdafdf763ad44ef40328ac8b9d8ce02
