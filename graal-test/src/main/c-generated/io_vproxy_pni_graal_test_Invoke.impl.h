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

#ifdef __cplusplus
}
#endif
// metadata.generator-version: pni test
// sha256:a698299c2d8389769f5182f1160c90e44949efe7c5f8351d0cd38f40b4434c66
