#ifndef _Included_gcc_compatibility
#define _Included_gcc_compatibility

#include <inttypes.h>

typedef struct {
  int8_t  b;
  int16_t s;
  int32_t n;
  float   f;
  double  d;
  int64_t l;
} __attribute__((packed)) GCCCompatibilityPacked;

#define init_GCCCompatibilityStruct(self) \
  self.b = 1; \
  self.s = 2; \
  self.n = 3; \
  self.f = 4.0; \
  self.d = 8.0; \
  self.l = 9;

static inline void init_GCCCompatibilityPacked(GCCCompatibilityPacked* self) {
  self->b = 1;
  self->s = 2;
  self->n = 3;
  self->f = 4.0;
  self->d = 8.0;
  self->l = 9;
}

typedef struct {
  int8_t b1;
  GCCCompatibilityPacked packed;
  int32_t n2;
} GCCCompatibilityNonPackedContainPacked;

typedef struct {
  int8_t  b;
  int16_t s;
  int32_t n;
  float   f;
  double  d;
  int64_t l;
} GCCCompatibilityNormal;

static inline void init_GCCCompatibilityNormal(GCCCompatibilityNormal* self) {
  self->b = 1;
  self->s = 2;
  self->n = 3;
  self->f = 4.0;
  self->d = 8.0;
  self->l = 9;
}

typedef struct {
  int8_t b1;
  GCCCompatibilityNormal normal;
  int32_t n2;
} GCCCompatibilityNonPackedContainNonPacked;

typedef struct {
  int8_t b1;
  GCCCompatibilityNormal normal;
  int n2;
} __attribute__((packed)) GCCCompatibilityPackedContainNonPacked;

typedef struct {
  int8_t b1;
  int16_t s __attribute__((aligned(4)));
  int32_t n2;
} GCCCompatibilityAlignField;

typedef struct {
  int8_t b1;
  GCCCompatibilityPacked packed __attribute__((aligned(4)));
  int32_t n2;
} GCCCompatibilityAlignFieldPacked;

typedef struct {
  int8_t b1;
  int16_t s __attribute__((aligned(4)));
  int32_t n2;
} __attribute__((packed)) GCCCompatibilityPackedAlignField;

typedef struct {
  int8_t b1;
  int64_t l __attribute__((aligned(2)));
  int32_t n2;
} __attribute__((packed)) GCCCompatibilityPackedAlignFieldSmallerAlign;

typedef struct {
  int8_t b1;
  GCCCompatibilityPacked array[2];
  int32_t n2;
} GCCCompatibilityPackedArray;

typedef struct {
  int8_t b1;
  GCCCompatibilityNormal array[2];
  int32_t n2;
} GCCCompatibilityNonPackedArray;

typedef struct {
  int8_t b1;
  GCCCompatibilityNormal array[0];
  int32_t n2;
} GCCCompatibilityArrayZero;

typedef union {
  int8_t  b;
  int16_t s;
  int32_t n;
  float   f;
  double  d;
  int64_t l;
} GCCCompatibilityUnion;

typedef struct {
  int8_t b1;
  GCCCompatibilityUnion un;
  int32_t n2;
} GCCompatibilityNormalContainUnion;

typedef struct {
  int8_t b1;
  GCCCompatibilityUnion un;
  int32_t n2;
} __attribute__((packed)) GCCompatibilityPackedContainUnion;

typedef struct {
  int32_t n;
  int64_t l;
} __attribute__((aligned(32))) GCCompatibilityStructAlign;

#endif // _Included_gcc_compatibility
