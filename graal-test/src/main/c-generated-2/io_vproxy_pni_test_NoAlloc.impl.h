#include "io_vproxy_pni_test_NoAlloc.h"
#include "io_vproxy_pni_test_NoAllocUpcall.h"

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_NoAlloc_invokeUpcall(PNIEnv_ObjectStruct * env) {
    env->return_ = JavaCritical_io_vproxy_pni_test_NoAllocUpcall_execNoAlloc();
    return 0;
}

#ifdef __cplusplus
}
#endif
// metadata.generator-version: pni test
// sha256:a969834968f885e20e23af68d742fbd62339c150a63ff01216feb57aa6a6e63d
