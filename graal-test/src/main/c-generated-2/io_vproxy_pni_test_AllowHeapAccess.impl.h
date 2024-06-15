#include "io_vproxy_pni_test_AllowHeapAccess.h"

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_AllowHeapAccess_intValue(PNIEnv_int * env, void * mem) {
    env->return_ = *((int*)mem);
    return 0;
}

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_pni_test_AllowHeapAccess_intValueCritical(void * mem) {
    return *((int*)mem);
}

#ifdef __cplusplus
}
#endif
// metadata.generator-version: pni test
// sha256:c2ba96d72d485ed83022cbdc17caa2285804286cee447fa20ce3e9a0d4a63801
