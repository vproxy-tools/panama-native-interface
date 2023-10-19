#include "io_vproxy_pni_test_CustomNativeTypeUpcall.h"
#include <stdio.h>
#include <stdlib.h>

#ifdef __cplusplus
extern "C" {
#endif

static int64_t* (*_exec)(SizeofStructExpr*);

JNIEXPORT void JNICALL JavaCritical_io_vproxy_pni_test_CustomNativeTypeUpcall_INIT(
    int64_t* (*exec)(SizeofStructExpr*)
) {
    _exec = exec;
}

JNIEXPORT int64_t* JNICALL JavaCritical_io_vproxy_pni_test_CustomNativeTypeUpcall_exec(SizeofStructExpr* o) {
    if (_exec == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_CustomNativeTypeUpcall_exec function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _exec(o);
}

#ifdef __cplusplus
}
#endif
// metadata.generator-version: pni test
// sha256:3ebeb73d5cfb3ab23476a909c0a7f1d249b6ab981c78b92827f6ab9c294ff7c7
