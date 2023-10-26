#include "io_vproxy_pni_test_PointerOnlyStruct.h"

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT int64_t JNICALL JavaCritical_io_vproxy_pni_test_PointerOnlyStruct_retrieve(PointerOnlyStruct * self) {
    return *((long*)self);
}

#ifdef __cplusplus
}
#endif
// metadata.generator-version: pni test
// sha256:7adc63ad409317dd1dba66439ba2442bfd4a2e4fb4cb1e5847e65c848af83ee2
