#include "io_vproxy_pni_test_AlignClass.h"

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT int8_t JNICALL JavaCritical_io_vproxy_pni_test_AlignClass_aaaa(AlignClass * self) {
    return self->a;
}

JNIEXPORT int16_t JNICALL JavaCritical_io_vproxy_pni_test_AlignClass_bbbb(AlignClass * self) {
    return self->b;
}

JNIEXPORT int64_t JNICALL JavaCritical_io_vproxy_pni_test_AlignClass_cccc(AlignClass * self) {
    return self->c;
}

JNIEXPORT int64_t JNICALL JavaCritical_io_vproxy_pni_test_AlignClass_size(AlignClass * self) {
    return sizeof(*self);
}

#ifdef __cplusplus
}
#endif
// metadata.generator-version: pni test
// sha256:66048a0bd3b18c410a54650af3a60f1e3ed5eb245b35dd5e0107778917dc275e
