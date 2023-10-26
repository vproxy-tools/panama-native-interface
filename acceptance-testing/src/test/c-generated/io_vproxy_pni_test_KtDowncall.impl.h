#include "io_vproxy_pni_test_KtDowncall.h"
#include "io_vproxy_pni_test_KtStruct.h"
#include "io_vproxy_pni_test_KtUpcall.h"

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT int64_t JNICALL JavaCritical_io_vproxy_pni_test_KtDowncall_retrieveLong(KtStruct * o) {
    return o->aLong;
}

JNIEXPORT KtStruct * JNICALL JavaCritical_io_vproxy_pni_test_KtDowncall_invokeHelloWorld(int32_t i, int64_t l, KtStruct * return_) {
    return JavaCritical_io_vproxy_pni_test_KtUpcall_helloworld(i, l, return_);
}

#ifdef __cplusplus
}
#endif
// metadata.generator-version: pni test
// sha256:11c036cf1accefdcd9e00477011c07d0fed9106912bd8ce55b6d3aa30e18afd1
