/* DO NOT EDIT THIS FILE - it is machine generated */
/* Header for class io_vproxy_pni_test_StructB */
#ifndef _Included_io_vproxy_pni_test_StructB
#define _Included_io_vproxy_pni_test_StructB
#ifdef __cplusplus
extern "C" {
#endif

struct StructB;
typedef struct StructB StructB;

#ifdef __cplusplus
}
#endif

#include <jni.h>
#include <pni.h>
#include "io_vproxy_pni_test_UnionC.h"
#include "io_vproxy_pni_test_StructD.h"
#include "io_vproxy_pni_test_UnionEmbedded.h"

#ifdef __cplusplus
extern "C" {
#endif

PNIEnvExpand(StructB, StructB *)

PNI_PACK(struct, StructB, {
    int32_t i; /* padding */ uint64_t :32;
    UnionC c;
    int64_t l;
    StructD * d;
    union {
        int32_t n;
        void * seg;
    };
});

#ifdef __cplusplus
}
#endif
#endif // _Included_io_vproxy_pni_test_StructB
// sha256:5ad5ff3e7720b65d8f8db2fd5af89f7f81ec132ca581d0ad77e7bde92ad3e6a6
