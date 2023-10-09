#include "io_vproxy_pni_test_PackedBaseClass.h"

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PackedBaseClass_aaa(PNIEnv_void * env, PackedBaseClass * self, int8_t a) {
    self->a = a;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PackedBaseClass_bbb(PNIEnv_void * env, PackedBaseClass * self, int16_t b) {
    self->b = b;
    return 0;
}

#ifdef __cplusplus
}
#endif
// metadata.generator-version: pni test
// sha256:4498f4525e2809b785a5f75cec89c9290280352f4a3d6882fedc002f20ca850e
