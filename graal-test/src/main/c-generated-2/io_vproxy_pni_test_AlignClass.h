/* DO NOT EDIT THIS FILE - it is machine generated */
/* Header for class io_vproxy_pni_test_AlignClass */
#ifndef _Included_io_vproxy_pni_test_AlignClass
#define _Included_io_vproxy_pni_test_AlignClass
#ifdef __cplusplus
extern "C" {
#endif

struct AlignClass;
typedef struct AlignClass AlignClass;

#ifdef __cplusplus
}
#endif

#include <jni.h>
#include <pni.h>

#ifdef __cplusplus
extern "C" {
#endif

PNIEnvExpand(AlignClass, AlignClass *)
PNIBufExpand(AlignClass, AlignClass, 32)

struct AlignClass {
    int8_t a;
    int16_t b;
    int64_t c; /* padding */ uint64_t : 64; uint64_t : 64;
};

JNIEXPORT int8_t JNICALL JavaCritical_io_vproxy_pni_test_AlignClass_aaaa(AlignClass * self);
JNIEXPORT int16_t JNICALL JavaCritical_io_vproxy_pni_test_AlignClass_bbbb(AlignClass * self);
JNIEXPORT int64_t JNICALL JavaCritical_io_vproxy_pni_test_AlignClass_cccc(AlignClass * self);
JNIEXPORT int64_t JNICALL JavaCritical_io_vproxy_pni_test_AlignClass_size(AlignClass * self);

#ifdef __cplusplus
}
#endif
#endif // _Included_io_vproxy_pni_test_AlignClass
// metadata.generator-version: pni test
// sha256:0b4e372ff1644721941b160d2d2d1935db70f39c5703d929715fd440e8218dd7