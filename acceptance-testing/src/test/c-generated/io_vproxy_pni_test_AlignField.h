/* DO NOT EDIT THIS FILE - it is machine generated */
/* Header for class io_vproxy_pni_test_AlignField */
#ifndef _Included_io_vproxy_pni_test_AlignField
#define _Included_io_vproxy_pni_test_AlignField
#ifdef __cplusplus
extern "C" {
#endif

struct AlignField;
typedef struct AlignField AlignField;

#ifdef __cplusplus
}
#endif

#include <jni.h>
#include <pni.h>

#ifdef __cplusplus
extern "C" {
#endif

PNIEnvExpand(AlignField, AlignField *)
PNIBufExpand(AlignField, AlignField, 8)

struct AlignField {
    int8_t a; /* padding */ uint8_t : 8;
    int8_t b;
    int32_t c;
};

JNIEXPORT int8_t JNICALL JavaCritical_io_vproxy_pni_test_AlignField_aaaa(AlignField * self);
JNIEXPORT int8_t JNICALL JavaCritical_io_vproxy_pni_test_AlignField_bbbb(AlignField * self);
JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_pni_test_AlignField_cccc(AlignField * self);

#ifdef __cplusplus
}
#endif
#endif // _Included_io_vproxy_pni_test_AlignField
// metadata.generator-version: pni test
// sha256:7b15a8953850a7dd8164c4c6cc7c4f1c00532131f65a061f99781ee719826df4