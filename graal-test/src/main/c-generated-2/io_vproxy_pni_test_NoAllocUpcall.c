#include "io_vproxy_pni_test_NoAllocUpcall.h"
#include <stdio.h>
#include <stdlib.h>

#ifdef __cplusplus
extern "C" {
#endif

static ObjectStruct * (*_execNoAlloc)(void *);

JNIEXPORT void JNICALL JavaCritical_io_vproxy_pni_test_NoAllocUpcall_INIT(
    ObjectStruct * (*execNoAlloc)(void *)
) {
    _execNoAlloc = execNoAlloc;
}

JNIEXPORT ObjectStruct * JNICALL JavaCritical_io_vproxy_pni_test_NoAllocUpcall_execNoAlloc(void) {
    if (_execNoAlloc == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_NoAllocUpcall_execNoAlloc function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _execNoAlloc(GetPNIGraalThread());
}

#ifdef __cplusplus
}
#endif
// metadata.generator-version: pni test
// sha256:50e462ec3bad926eb961f0d5d6b0ca072cf89fa6310812eb3c1e2ed0eaf9ef20
