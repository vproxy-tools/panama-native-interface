/* DO NOT EDIT THIS FILE - it is machine generated */
/* Header for class io_vproxy_pni_test_SizeofStruct */
#ifndef _Included_io_vproxy_pni_test_SizeofStruct
#define _Included_io_vproxy_pni_test_SizeofStruct
#ifdef __cplusplus
extern "C" {
#endif

struct SizeofStruct;
typedef struct SizeofStruct SizeofStruct;

#ifdef __cplusplus
}
#endif

#include <jni.h>
#include <pni.h>

#ifdef __cplusplus
extern "C" {
#endif

PNIEnvExpand(SizeofStruct, SizeofStruct *)

PNI_PACK(struct, SizeofStruct, {
    int32_t a;
    int16_t b; /* padding */ uint64_t :16;
});

JNIEXPORT size_t JNICALL JavaCritical_io_vproxy_pni_test_SizeofStruct___getLayoutByteSize();

#ifdef __cplusplus
}
#endif
#endif // _Included_io_vproxy_pni_test_SizeofStruct
// metadata.generator-version: pni test
// sha256:38f973ecdd6676804af090b1864de1b6346537a349c83dca1783675ba0dbcb47
