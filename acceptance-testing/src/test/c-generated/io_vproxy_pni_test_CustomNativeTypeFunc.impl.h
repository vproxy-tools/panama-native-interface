#include "io_vproxy_pni_test_CustomNativeTypeFunc.h"

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT int64_t* JNICALL JavaCritical_io_vproxy_pni_test_CustomNativeTypeFunc_exec(SizeofStructExpr* o) {
    return &(o->p1);
}

JNIEXPORT int64_t* JNICALL JavaCritical_io_vproxy_pni_test_CustomNativeTypeFunc_invoke(SizeofStructExpr * s) {
    return JavaCritical_io_vproxy_pni_test_CustomNativeTypeUpcall_exec(s);
}

#ifdef __cplusplus
}
#endif
// metadata.generator-version: pni test
// sha256:560491e980f7e29055f911f8176743c5426f8d6541133894a8373431af88ac69
