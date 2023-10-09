#include "io_vproxy_pni_test_BaseClass.h"

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_BaseClass_aaa(PNIEnv_void * env, BaseClass * self, int8_t a) {
    self->a = a;
    return 0;
}

#ifdef __cplusplus
}
#endif
// metadata.generator-version: pni test
// sha256:5687d2461f27bb845a808030e9471567b31950e3da86a18335c413c373e03884
