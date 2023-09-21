/* DO NOT EDIT THIS FILE - it is machine generated */
/* Header for class io_vproxy_pni_test_BitField */
#ifndef _Included_io_vproxy_pni_test_BitField
#define _Included_io_vproxy_pni_test_BitField
#ifdef __cplusplus
extern "C" {
#endif

struct BitField;
typedef struct BitField BitField;

#ifdef __cplusplus
}
#endif

#include <jni.h>
#include <pni.h>

#ifdef __cplusplus
extern "C" {
#endif

PNIEnvExpand(BitField, BitField *)
PNIBufExpand(BitField, BitField, 64)

PNI_PACK(struct, BitField, {
    uint8_t a : 1;
    uint8_t b : 3;
    uint8_t : 4;

    uint8_t a2 : 1;
    uint8_t b2 : 3;
    uint8_t : 4;
     /* padding */ uint64_t :48;
    void * sep01;
    uint16_t c : 2;
    uint16_t d : 3;
    uint16_t e : 4;
    uint16_t : 7;

    uint16_t c2 : 2;
    uint16_t d2 : 3;
    uint16_t e2 : 4;
    uint16_t : 7;
     /* padding */ uint64_t :32;
    void * sep02;
    uint32_t f : 5;
    uint32_t g : 6;
    uint32_t h : 7;
    uint32_t i : 8;
    uint32_t : 6;

    uint32_t f2 : 5;
    uint32_t g2 : 6;
    uint32_t h2 : 7;
    uint32_t i2 : 8;
    uint32_t : 6;

    void * sep03;
    uint64_t j : 1;
    uint64_t k : 2;
    uint64_t l : 22;
    uint64_t m : 33;
    uint64_t : 6;

    uint64_t j2 : 1;
    uint64_t k2 : 2;
    uint64_t l2 : 22;
    uint64_t m2 : 33;
    uint64_t : 6;

});

JNIEXPORT void JNICALL JavaCritical_io_vproxy_pni_test_BitField_set(BitField * self, uint8_t a, uint8_t a2, uint8_t b, uint8_t b2, uint16_t c, uint16_t c2, uint16_t d, uint16_t d2, uint16_t e, uint16_t e2, uint32_t f, uint32_t f2, uint32_t g, uint32_t g2, uint32_t h, uint32_t h2, uint32_t i, uint32_t i2, uint64_t j, uint64_t j2, uint64_t k, uint64_t k2, uint64_t l, uint64_t l2, uint64_t m, uint64_t m2);
JNIEXPORT int8_t JNICALL JavaCritical_io_vproxy_pni_test_BitField_a(BitField * self);
JNIEXPORT int8_t JNICALL JavaCritical_io_vproxy_pni_test_BitField_a2(BitField * self);
JNIEXPORT int8_t JNICALL JavaCritical_io_vproxy_pni_test_BitField_b(BitField * self);
JNIEXPORT int8_t JNICALL JavaCritical_io_vproxy_pni_test_BitField_b2(BitField * self);
JNIEXPORT int16_t JNICALL JavaCritical_io_vproxy_pni_test_BitField_c(BitField * self);
JNIEXPORT int16_t JNICALL JavaCritical_io_vproxy_pni_test_BitField_c2(BitField * self);
JNIEXPORT int16_t JNICALL JavaCritical_io_vproxy_pni_test_BitField_d(BitField * self);
JNIEXPORT int16_t JNICALL JavaCritical_io_vproxy_pni_test_BitField_d2(BitField * self);
JNIEXPORT int16_t JNICALL JavaCritical_io_vproxy_pni_test_BitField_e(BitField * self);
JNIEXPORT int16_t JNICALL JavaCritical_io_vproxy_pni_test_BitField_e2(BitField * self);
JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_pni_test_BitField_f(BitField * self);
JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_pni_test_BitField_f2(BitField * self);
JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_pni_test_BitField_g(BitField * self);
JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_pni_test_BitField_g2(BitField * self);
JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_pni_test_BitField_h(BitField * self);
JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_pni_test_BitField_h2(BitField * self);
JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_pni_test_BitField_i(BitField * self);
JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_pni_test_BitField_i2(BitField * self);
JNIEXPORT int64_t JNICALL JavaCritical_io_vproxy_pni_test_BitField_j(BitField * self);
JNIEXPORT int64_t JNICALL JavaCritical_io_vproxy_pni_test_BitField_j2(BitField * self);
JNIEXPORT int64_t JNICALL JavaCritical_io_vproxy_pni_test_BitField_k(BitField * self);
JNIEXPORT int64_t JNICALL JavaCritical_io_vproxy_pni_test_BitField_k2(BitField * self);
JNIEXPORT int64_t JNICALL JavaCritical_io_vproxy_pni_test_BitField_l(BitField * self);
JNIEXPORT int64_t JNICALL JavaCritical_io_vproxy_pni_test_BitField_l2(BitField * self);
JNIEXPORT int64_t JNICALL JavaCritical_io_vproxy_pni_test_BitField_m(BitField * self);
JNIEXPORT int64_t JNICALL JavaCritical_io_vproxy_pni_test_BitField_m2(BitField * self);

#ifdef __cplusplus
}
#endif
#endif // _Included_io_vproxy_pni_test_BitField
// metadata.generator-version: pni test
// sha256:7d509c9275322042bcaa134f80cd8fb057d3ba162336c0e8fd81d8a15a53caee
