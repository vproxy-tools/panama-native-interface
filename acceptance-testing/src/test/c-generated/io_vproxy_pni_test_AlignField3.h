/* DO NOT EDIT THIS FILE - it is machine generated */
/* Header for class io_vproxy_pni_test_AlignField3 */
#ifndef _Included_io_vproxy_pni_test_AlignField3
#define _Included_io_vproxy_pni_test_AlignField3
#ifdef __cplusplus
extern "C" {
#endif

struct AlignField3;
typedef struct AlignField3 AlignField3;

#ifdef __cplusplus
}
#endif

#include <jni.h>
#include <pni.h>

#ifdef __cplusplus
extern "C" {
#endif

PNIEnvExpand(AlignField3, AlignField3 *)
PNIBufExpand(AlignField3, AlignField3, 64)

struct AlignField3 {
    int16_t a; /* padding */ uint16_t : 16; uint16_t : 16; uint16_t : 16; uint16_t : 16; uint16_t : 16; uint16_t : 16; uint16_t : 16; uint16_t : 16; uint16_t : 16; uint16_t : 16; uint16_t : 16; uint16_t : 16; uint16_t : 16; uint16_t : 16; uint16_t : 16;
    int32_t b;
    int32_t c; /* padding */ uint32_t : 32; uint32_t : 32; uint32_t : 32; uint32_t : 32; uint32_t : 32; uint32_t : 32;
};

JNIEXPORT int16_t JNICALL JavaCritical_io_vproxy_pni_test_AlignField3_aaaa(AlignField3 * self);
JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_pni_test_AlignField3_bbbb(AlignField3 * self);
JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_pni_test_AlignField3_cccc(AlignField3 * self);
JNIEXPORT int64_t JNICALL JavaCritical_io_vproxy_pni_test_AlignField3_size(AlignField3 * self);

#ifdef __cplusplus
}
#endif
#endif // _Included_io_vproxy_pni_test_AlignField3
// metadata.generator-version: pni test
// sha256:99ca9f8e80e9caaa4d1ca7ceaf75b90a8b8144e947adbf2c3fd155abe56ba3b0
