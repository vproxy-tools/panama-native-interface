#ifndef _Included_io_vproxy_pni_test_StructUnionAnnotation
#define _Included_io_vproxy_pni_test_StructUnionAnnotation

#include <inttypes.h>

struct N_st {
    uint16_t s;
    uint64_t l;
} __attribute__((packed));

typedef union {
    uint32_t i;
    uint64_t l;
} __attribute__((packed)) UnionP;

#endif // _Included_io_vproxy_pni_test_StructUnionAnnotation
