#include "io_vproxy_pni_test_AlignChildClass.h"

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_pni_test_AlignChildClass_bbbb(AlignChildClass * self) {
    return self->b;
}

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_pni_test_AlignChildClass_cccc(AlignChildClass * self) {
    return self->c;
}

JNIEXPORT int64_t JNICALL JavaCritical_io_vproxy_pni_test_AlignChildClass_size(AlignChildClass * self) {
    return sizeof(*self);
}

#ifdef __cplusplus
}
#endif
// metadata.generator-version: pni test
// sha256:e54f216104ccdbada6d699cd3ba399c5dbea709a249fb8ed6bc57d62f151fe4b
