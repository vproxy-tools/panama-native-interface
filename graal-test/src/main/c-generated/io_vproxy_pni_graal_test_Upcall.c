#include "io_vproxy_pni_graal_test_Upcall.h"
#include <stdio.h>
#include <stdlib.h>

#ifdef __cplusplus
extern "C" {
#endif

static void (*_doNothingUpcall)(void *);
static int32_t (*_intUpcall)(void *,int32_t);
static int32_t (*_refUpcall)(void *,PNIRef *);
static int32_t (*_funcUpcall)(void *,PNIFunc *);
static void * (*_returnSegUpcall)(void *);

JNIEXPORT void JNICALL JavaCritical_io_vproxy_pni_graal_test_Upcall_INIT(
    void (*doNothingUpcall)(void *),
    int32_t (*intUpcall)(void *,int32_t),
    int32_t (*refUpcall)(void *,PNIRef *),
    int32_t (*funcUpcall)(void *,PNIFunc *),
    void * (*returnSegUpcall)(void *)
) {
    _doNothingUpcall = doNothingUpcall;
    _intUpcall = intUpcall;
    _refUpcall = refUpcall;
    _funcUpcall = funcUpcall;
    _returnSegUpcall = returnSegUpcall;
}

JNIEXPORT void JNICALL JavaCritical_io_vproxy_pni_graal_test_Upcall_doNothingUpcall(void) {
    if (_doNothingUpcall == NULL) {
        printf("JavaCritical_io_vproxy_pni_graal_test_Upcall_doNothingUpcall function pointer is null");
        fflush(stdout);
        exit(1);
    }
    _doNothingUpcall(GetPNIGraalThread());
}

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_pni_graal_test_Upcall_intUpcall(int32_t a) {
    if (_intUpcall == NULL) {
        printf("JavaCritical_io_vproxy_pni_graal_test_Upcall_intUpcall function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _intUpcall(GetPNIGraalThread(), a);
}

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_pni_graal_test_Upcall_refUpcall(PNIRef * ref) {
    if (_refUpcall == NULL) {
        printf("JavaCritical_io_vproxy_pni_graal_test_Upcall_refUpcall function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _refUpcall(GetPNIGraalThread(), ref);
}

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_pni_graal_test_Upcall_funcUpcall(PNIFunc * func) {
    if (_funcUpcall == NULL) {
        printf("JavaCritical_io_vproxy_pni_graal_test_Upcall_funcUpcall function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _funcUpcall(GetPNIGraalThread(), func);
}

JNIEXPORT void * JNICALL JavaCritical_io_vproxy_pni_graal_test_Upcall_returnSegUpcall(void) {
    if (_returnSegUpcall == NULL) {
        printf("JavaCritical_io_vproxy_pni_graal_test_Upcall_returnSegUpcall function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _returnSegUpcall(GetPNIGraalThread());
}

#ifdef __cplusplus
}
#endif
// metadata.generator-version: pni test
// sha256:6db72ee2648cb38c36391ec2611e6e3b8e693d3546188bc44a93df4098485b0d
