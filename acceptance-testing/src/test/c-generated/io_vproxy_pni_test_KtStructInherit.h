/* DO NOT EDIT THIS FILE - it is machine generated */
/* Header for class io_vproxy_pni_test_KtStructInherit */
#ifndef _Included_io_vproxy_pni_test_KtStructInherit
#define _Included_io_vproxy_pni_test_KtStructInherit
#ifdef __cplusplus
extern "C" {
#endif

struct KtStructInherit;
typedef struct KtStructInherit KtStructInherit;

#ifdef __cplusplus
}
#endif

#include <jni.h>
#include <pni.h>
#include "io_vproxy_pni_test_KtStruct.h"
#include "io_vproxy_pni_test_PrimitiveStruct.h"

#ifdef __cplusplus
extern "C" {
#endif

PNIEnvExpand(KtStructInherit, KtStructInherit *)
PNIBufExpand(KtStructInherit, KtStructInherit, 1016)

struct KtStructInherit {
    KtStruct SUPER;
    PrimitiveStruct obj;
};

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_KtStructInherit_retrieveObj(PNIEnv_PrimitiveStruct * env, KtStructInherit * self);

#ifdef __cplusplus
}
#endif
#endif // _Included_io_vproxy_pni_test_KtStructInherit
// metadata.generator-version: pni test
// sha256:6572cbdf1edb0cb15b448f699e57b8eaa75816a1dd509622979db21e923a611e
