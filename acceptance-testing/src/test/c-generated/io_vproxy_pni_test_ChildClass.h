/* DO NOT EDIT THIS FILE - it is machine generated */
/* Header for class io_vproxy_pni_test_ChildClass */
#ifndef _Included_io_vproxy_pni_test_ChildClass
#define _Included_io_vproxy_pni_test_ChildClass
#ifdef __cplusplus
extern "C" {
#endif

struct ChildClass;
typedef struct ChildClass ChildClass;

#ifdef __cplusplus
}
#endif

#include <jni.h>
#include <pni.h>
#include "io_vproxy_pni_test_BaseClass.h"

#ifdef __cplusplus
extern "C" {
#endif

PNIEnvExpand(ChildClass, ChildClass *)
PNIBufExpand(ChildClass, ChildClass, 4)

PNI_PACK(struct, ChildClass, {
    BaseClass SUPER;
     /* padding */ uint8_t : 8;
    int16_t x;
});

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_ChildClass_xxx(PNIEnv_void * env, ChildClass * self, int16_t x);

#ifdef __cplusplus
}
#endif
#endif // _Included_io_vproxy_pni_test_ChildClass
// metadata.generator-version: pni test
// sha256:41eb5247fc2590e3430f99602efdb66b586664026d3f9e9f9e7f6cecdb92712b
