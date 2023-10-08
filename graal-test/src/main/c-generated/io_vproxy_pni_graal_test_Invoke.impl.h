#include "io_vproxy_pni_graal_test_Invoke.h"

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_pni_graal_test_Invoke_invokeSum(void * func, void * thread, int32_t a, int32_t b) {
    int (*f)(void*,int,int) = func;
    return f(thread, a, b);
}

#ifdef __cplusplus
}
#endif
// metadata.generator-version: pni test
// sha256:d774fe2f72ac5e1e11b4720623308bda958765749fb5b459ebecbb02ed108072
