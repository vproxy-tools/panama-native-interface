#include "io_vproxy_pni_test_KtUpcall.h"
#include <stdio.h>
#include <stdlib.h>

#ifdef __cplusplus
extern "C" {
#endif

static KtStruct * (*_helloworld)(void *,int32_t,int64_t,KtStruct *);

JNIEXPORT void JNICALL JavaCritical_io_vproxy_pni_test_KtUpcall_INIT(
    KtStruct * (*helloworld)(void *,int32_t,int64_t,KtStruct *)
) {
    _helloworld = helloworld;
}

JNIEXPORT KtStruct * JNICALL JavaCritical_io_vproxy_pni_test_KtUpcall_helloworld(int32_t i, int64_t l, KtStruct * return_) {
    if (_helloworld == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_KtUpcall_helloworld function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _helloworld(GetPNIGraalThread(), i, l, return_);
}

#ifdef __cplusplus
}
#endif
// metadata.generator-version: pni test
// sha256:c66ada5779e64603c629b950ccb9fbf843f82eb8dc844db26d60f5b9fce35d1c
