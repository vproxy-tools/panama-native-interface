/* DO NOT EDIT THIS FILE - it is machine generated */
/* Header for class io_vproxy_pni_test_StructM */
#ifndef _Included_io_vproxy_pni_test_StructM
#define _Included_io_vproxy_pni_test_StructM
#ifdef __cplusplus
extern "C" {
#endif

struct StructM;

#ifdef __cplusplus
}
#endif

#include <jni.h>
#include <pni.h>
#include "io_vproxy_pni_test_StructN.h"

#ifdef __cplusplus
extern "C" {
#endif

PNIEnvExpand(StructM, struct StructM *)
PNIBufExpand(StructM, struct StructM, 16)

PNI_PACK(struct, StructM, {
    struct N_st n_st;
});

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_StructM_nnn(PNIEnv_void * env, struct StructM * self, struct N_st * n_st);

#ifdef __cplusplus
}
#endif
#endif // _Included_io_vproxy_pni_test_StructM
// metadata.generator-version: pni test
// sha256:bf5a0ec658db095a871fba9836e3fddf5bf62aae31703ad039d74b33163ad66f
