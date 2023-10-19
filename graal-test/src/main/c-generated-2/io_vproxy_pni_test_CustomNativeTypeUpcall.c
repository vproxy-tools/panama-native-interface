#include "io_vproxy_pni_test_CustomNativeTypeUpcall.h"
#include <stdio.h>
#include <stdlib.h>

#ifdef __cplusplus
extern "C" {
#endif

static int64_t* (*_exec)(void *,SizeofStructExpr*);

JNIEXPORT void JNICALL JavaCritical_io_vproxy_pni_test_CustomNativeTypeUpcall_INIT(
    int64_t* (*exec)(void *,SizeofStructExpr*)
) {
    _exec = exec;
}

JNIEXPORT int64_t* JNICALL JavaCritical_io_vproxy_pni_test_CustomNativeTypeUpcall_exec(SizeofStructExpr* o) {
    if (_exec == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_CustomNativeTypeUpcall_exec function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _exec(GetPNIGraalThread(), o);
}

#ifdef __cplusplus
}
#endif
// metadata.generator-version: pni test
// sha256:ecc0c6a01b5255fa98d6dca66d2912d4bcb17a65b00a704aeb0bc65f74f420d7
