#include "io_vproxy_pni_test_KtUpcall.h"
#include <stdio.h>
#include <stdlib.h>

#ifdef __cplusplus
extern "C" {
#endif

static KtStruct * (*_helloworld)(int32_t,int64_t,KtStruct *);

JNIEXPORT void JNICALL JavaCritical_io_vproxy_pni_test_KtUpcall_INIT(
    KtStruct * (*helloworld)(int32_t,int64_t,KtStruct *)
) {
    _helloworld = helloworld;
}

JNIEXPORT KtStruct * JNICALL JavaCritical_io_vproxy_pni_test_KtUpcall_helloworld(int32_t i, int64_t l, KtStruct * return_) {
    if (_helloworld == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_KtUpcall_helloworld function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _helloworld(i, l, return_);
}

#ifdef __cplusplus
}
#endif
// metadata.generator-version: pni test
// sha256:048776dd0b94238775168f2bfea9c838122d9976aed2cae6a7d49d3fe2aaf21e
