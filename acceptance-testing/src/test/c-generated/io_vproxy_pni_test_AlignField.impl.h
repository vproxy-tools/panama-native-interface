#include "io_vproxy_pni_test_AlignField.h"

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT int8_t JNICALL JavaCritical_io_vproxy_pni_test_AlignField_aaaa(AlignField * self) {
    return self->a;
}

JNIEXPORT int8_t JNICALL JavaCritical_io_vproxy_pni_test_AlignField_bbbb(AlignField * self) {
    return self->b;
}

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_pni_test_AlignField_cccc(AlignField * self) {
    return self->c;
}

JNIEXPORT int64_t JNICALL JavaCritical_io_vproxy_pni_test_AlignField_size(AlignField * self) {
    return sizeof(*self);
}

#ifdef __cplusplus
}
#endif
// metadata.generator-version: pni test
// sha256:ce2fc9d436357bdf186cdeabe1a2f22987dea7ac3b82c91c463aec7a9d633f77
