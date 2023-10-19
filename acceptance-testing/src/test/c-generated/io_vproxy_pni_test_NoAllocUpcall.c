#include "io_vproxy_pni_test_NoAllocUpcall.h"
#include <stdio.h>
#include <stdlib.h>

#ifdef __cplusplus
extern "C" {
#endif

static ObjectStruct * (*_execNoAlloc)(void);

JNIEXPORT void JNICALL JavaCritical_io_vproxy_pni_test_NoAllocUpcall_INIT(
    ObjectStruct * (*execNoAlloc)(void)
) {
    _execNoAlloc = execNoAlloc;
}

JNIEXPORT ObjectStruct * JNICALL JavaCritical_io_vproxy_pni_test_NoAllocUpcall_execNoAlloc(void) {
    if (_execNoAlloc == NULL) {
        printf("JavaCritical_io_vproxy_pni_test_NoAllocUpcall_execNoAlloc function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _execNoAlloc();
}

#ifdef __cplusplus
}
#endif
// metadata.generator-version: pni test
// sha256:5f1de7520f5a3137cfe3469a02387e5e9653342a3b8d128f5c9434522dbb0947
