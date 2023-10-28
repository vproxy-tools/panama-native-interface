package io.vproxy.pni.test;

import io.vproxy.pni.annotation.*;

import java.lang.foreign.MemorySegment;

@Include("gcc_compatibility.h")
@Struct(skip = true)
@Align(packed = true)
abstract class PNIGCCCompatibilityPacked {
    byte b;
    short s;
    int n;
    float f;
    double d;
    long l;

    @Impl(
        // language="c"
        c = """
            init_GCCCompatibilityPacked(self);
            return 0;
            """
    )
    abstract void init();

    @Impl(
        // language="c"
        c = """
            env->return_ = sizeof(GCCCompatibilityPacked);
            return 0;
            """
    )
    abstract long size();
}

@Include("gcc_compatibility.h")
@Struct(skip = true)
abstract class PNIGCCCompatibilityNonPackedContainPacked {
    byte b1;
    PNIGCCCompatibilityPacked packed;
    int n2;

    @Impl(
        // language="c"
        c = """
            self->b1 = 90;
            init_GCCCompatibilityStruct(self->packed);
            self->n2 = 91;
            return 0;
            """
    )
    abstract void init();

    @Impl(
        // language="c"
        c = """
            env->return_ = sizeof(GCCCompatibilityNonPackedContainPacked);
            return 0;
            """
    )
    abstract long size();
}

@Include("gcc_compatibility.h")
@Struct(skip = true)
abstract class PNIGCCCompatibilityNormal {
    byte b;
    short s;
    int n;
    float f;
    double d;
    long l;

    @Impl(
        // language="c"
        c = """
            init_GCCCompatibilityNormal(self);
            return 0;
            """
    )
    abstract void init();

    @Impl(
        // language="c"
        c = """
            env->return_ = sizeof(GCCCompatibilityNormal);
            return 0;
            """
    )
    abstract long size();
}

@Include("gcc_compatibility.h")
@Struct(skip = true)
abstract class PNIGCCCompatibilityNonPackedContainNonPacked {
    byte b1;
    PNIGCCCompatibilityNormal normal;
    int n2;

    @Impl(
        // language="c"
        c = """
            self->b1 = 92;
            init_GCCCompatibilityStruct(self->normal);
            self->n2 = 93;
            return 0;
            """
    )
    abstract void init();

    @Impl(
        // language="c"
        c = """
            env->return_ = sizeof(GCCCompatibilityNonPackedContainNonPacked);
            return 0;
            """
    )
    abstract long size();
}

@Include("gcc_compatibility.h")
@Struct(skip = true)
@Align(packed = true)
abstract class PNIGCCCompatibilityPackedContainNonPacked {
    byte b1;
    PNIGCCCompatibilityNormal normal;
    int n2;

    @Impl(
        // language="c"
        c = """
            self->b1 = 94;
            init_GCCCompatibilityStruct(self->normal);
            self->n2 = 95;
            return 0;
            """
    )
    abstract void init();

    @Impl(
        // language="c"
        c = """
            env->return_ = sizeof(GCCCompatibilityPackedContainNonPacked);
            return 0;
            """
    )
    abstract long size();
}

@Include("gcc_compatibility.h")
@Struct(skip = true)
abstract class PNIGCCCompatibilityAlignField {
    byte b1;
    @Align(4) short s;
    int n2;

    @Impl(
        // language="c"
        c = """
            self->b1 = 1;
            self->s = 2;
            self->n2 = 3;
            return 0;
            """
    )
    abstract void init();

    @Impl(
        // language="c"
        c = """
            env->return_ = sizeof(GCCCompatibilityAlignField);
            return 0;
            """
    )
    abstract long size();
}

@Include("gcc_compatibility.h")
@Struct(skip = true)
abstract class PNIGCCCompatibilityAlignFieldPacked {
    byte b1;
    @Align(4) PNIGCCCompatibilityPacked packed;
    int n2;

    @Impl(
        // language="c"
        c = """
            self->b1 = 96;
            init_GCCCompatibilityStruct(self->packed);
            self->n2 = 97;
            return 0;
            """
    )
    abstract void init();

    @Impl(
        // language="c"
        c = """
            env->return_ = sizeof(GCCCompatibilityAlignFieldPacked);
            return 0;
            """
    )
    abstract long size();
}

@Include("gcc_compatibility.h")
@Struct(skip = true)
@Align(packed = true)
abstract class PNIGCCCompatibilityPackedAlignField {
    byte b1;
    @Align(4) short s;
    int n2;

    @Impl(
        // language="c"
        c = """
            self->b1 = 4;
            self->s = 5;
            self->n2 = 6;
            return 0;
            """
    )
    abstract void init();

    @Impl(
        // language="c"
        c = """
            env->return_ = sizeof(GCCCompatibilityPackedAlignField);
            return 0;
            """
    )
    abstract long size();
}

@Include("gcc_compatibility.h")
@Struct(skip = true)
@Align(packed = true)
abstract class PNIGCCCompatibilityPackedAlignFieldSmallerAlign {
    byte b1;
    @Align(2) long l;
    int n2;

    @Impl(
        // language="c"
        c = """
            self->b1 = 7;
            self->l = 8;
            self->n2 = 9;
            return 0;
            """
    )
    abstract void init();

    @Impl(
        // language="c"
        c = """
            env->return_ = sizeof(GCCCompatibilityPackedAlignFieldSmallerAlign);
            return 0;
            """
    )
    abstract long size();
}

@Include("gcc_compatibility.h")
@Struct(skip = true)
abstract class PNIGCCCompatibilityPackedArray {
    byte b1;
    @Len(2) PNIGCCCompatibilityPacked[] array;
    int n2;

    @Impl(
        // language="c"
        c = """
            self->b1 = 98;
            init_GCCCompatibilityStruct(self->array[0]);
            init_GCCCompatibilityStruct(self->array[1]);
            self->n2 = 99;
            return 0;
            """
    )
    abstract void init();

    @Impl(
        // language="c"
        c = """
            env->return_ = sizeof(GCCCompatibilityPackedArray);
            return 0;
            """
    )
    abstract long size();
}

@Include("gcc_compatibility.h")
@Struct(skip = true)
abstract class PNIGCCCompatibilityNonPackedArray {
    byte b1;
    @Len(2) PNIGCCCompatibilityNormal[] array;
    int n2;

    @Impl(
        // language="c"
        c = """
            self->b1 = 100;
            init_GCCCompatibilityStruct(self->array[0]);
            init_GCCCompatibilityStruct(self->array[1]);
            self->n2 = 101;
            return 0;
            """
    )
    abstract void init();

    @Impl(
        // language="c"
        c = """
            env->return_ = sizeof(GCCCompatibilityNonPackedArray);
            return 0;
            """
    )
    abstract long size();
}

@Include("gcc_compatibility.h")
@Struct(skip = true)
abstract class PNIGCCCompatibilityArrayZero {
    byte b1;
    @Len(0) PNIGCCCompatibilityNormal[] array;
    int n2;

    @Impl(
        // language="c"
        c = """
            self->b1 = 102;
            self->n2 = 103;
            return 0;
            """
    )
    abstract void init();

    @Impl(
        // language="c"
        c = """
            env->return_ = sizeof(GCCCompatibilityArrayZero);
            return 0;
            """
    )
    abstract long size();
}

@Union(skip = true)
class PNIGCCCompatibilityUnion {
    byte b;
    short s;
    int n;
    float f;
    double d;
    long l;
}

@Include("gcc_compatibility.h")
@Struct(skip = true)
abstract class PNIGCCompatibilityNormalContainUnion {
    byte b1;
    PNIGCCCompatibilityUnion un;
    int n2;

    @Impl(
        // language="c"
        c = """
            self->b1 = 104;
            self->un.b = 1;
            self->n2 = 105;
            return 0;
            """
    )
    abstract void initB();

    @Impl(
        // language="c"
        c = """
            self->b1 = 106;
            self->un.s = 2;
            self->n2 = 107;
            return 0;
            """
    )
    abstract void initS();

    @Impl(
        // language="c"
        c = """
            self->b1 = 108;
            self->un.n = 3;
            self->n2 = 109;
            return 0;
            """
    )
    abstract void initN();

    @Impl(
        // language="c"
        c = """
            self->b1 = 110;
            self->un.f = 4.0;
            self->n2 = 111;
            return 0;
            """
    )
    abstract void initF();

    @Impl(
        // language="c"
        c = """
            self->b1 = 112;
            self->un.d = 8.0;
            self->n2 = 113;
            return 0;
            """
    )
    abstract void initD();

    @Impl(
        // language="c"
        c = """
            self->b1 = 114;
            self->un.l = 9;
            self->n2 = 115;
            return 0;
            """
    )
    abstract void initL();

    @Impl(
        // language="c"
        c = """
            env->return_ = sizeof(GCCompatibilityNormalContainUnion);
            return 0;
            """
    )
    abstract long size();
}

@Include("gcc_compatibility.h")
@Struct(skip = true)
@Align(packed = true)
abstract class PNIGCCompatibilityPackedContainUnion {
    byte b1;
    PNIGCCCompatibilityUnion un;
    int n2;

    @Impl(
        // language="c"
        c = """
            self->b1 = 116;
            self->un.b = 1;
            self->n2 = 117;
            return 0;
            """
    )
    abstract void initB();

    @Impl(
        // language="c"
        c = """
            self->b1 = 118;
            self->un.s = 2;
            self->n2 = 119;
            return 0;
            """
    )
    abstract void initS();

    @Impl(
        // language="c"
        c = """
            self->b1 = 120;
            self->un.n = 3;
            self->n2 = 121;
            return 0;
            """
    )
    abstract void initN();

    @Impl(
        // language="c"
        c = """
            self->b1 = 122;
            self->un.f = 4.0;
            self->n2 = 123;
            return 0;
            """
    )
    abstract void initF();

    @Impl(
        // language="c"
        c = """
            self->b1 = 124;
            self->un.d = 8.0;
            self->n2 = 125;
            return 0;
            """
    )
    abstract void initD();

    @Impl(
        // language="c"
        c = """
            self->b1 = 126;
            self->un.l = 9;
            self->n2 = 127;
            return 0;
            """
    )
    abstract void initL();

    @Impl(
        // language="c"
        c = """
            env->return_ = sizeof(GCCompatibilityPackedContainUnion);
            return 0;
            """
    )
    abstract long size();
}

@Include("gcc_compatibility.h")
@Struct(skip = true)
@Align(32)
abstract class PNIGCCompatibilityStructAlign {
    int n;
    long l;

    @Impl(
        // language="c"
        c = """
            self->n = 1;
            self->l = 2;
            return 0;
            """
    )
    abstract void init();

    @Impl(
        // language="c"
        c = """
            env->return_ = sizeof(GCCompatibilityStructAlign);
            return 0;
            """
    )
    abstract long size();
}

@Include("gcc_compatibility.h")
@Struct(skip = true)
abstract class PNIGCCompatibilityBitField {
    @Bit({
        @Bit.Field(name = "a", bits = 1),
        @Bit.Field(name = "b", bits = 3),
    })
    @Unsigned byte field01;
    @Bit({
        @Bit.Field(name = "a2", bits = 1, bool = true),
        @Bit.Field(name = "b2", bits = 3),
    })
    @Unsigned byte field02;
    MemorySegment sep01;
    @Bit({
        @Bit.Field(name = "c", bits = 2),
        @Bit.Field(name = "d", bits = 3),
        @Bit.Field(name = "e", bits = 4),
    })
    @Unsigned short field03;
    @Bit({
        @Bit.Field(name = "c2", bits = 2),
        @Bit.Field(name = "d2", bits = 3),
        @Bit.Field(name = "e2", bits = 4),
    })
    @Unsigned short field04;
    MemorySegment sep02;
    @Bit({
        @Bit.Field(name = "f", bits = 5),
        @Bit.Field(name = "g", bits = 6),
        @Bit.Field(name = "h", bits = 7),
        @Bit.Field(name = "i", bits = 8),
    })
    @Unsigned int field05;
    @Bit({
        @Bit.Field(name = "f2", bits = 5),
        @Bit.Field(name = "g2", bits = 6),
        @Bit.Field(name = "h2", bits = 7),
        @Bit.Field(name = "i2", bits = 8),
    })
    @Unsigned int field06;
    MemorySegment sep03;
    @Bit({
        @Bit.Field(name = "j", bits = 1),
        @Bit.Field(name = "k", bits = 2),
        @Bit.Field(name = "l", bits = 22),
        @Bit.Field(name = "m", bits = 33),
        @Bit.Field(name = "n", bits = 1),
    })
    @Unsigned long field07;
    @Bit({
        @Bit.Field(name = "j2", bits = 1),
        @Bit.Field(name = "k2", bits = 2),
        @Bit.Field(name = "l2", bits = 22),
        @Bit.Field(name = "m2", bits = 33),
        @Bit.Field(name = "n2", bits = 1, bool = true),
    })
    @Unsigned long field08;

    @Impl(
        // language="c"
        c = """
            self->a = a;
            self->a2 = a2;
            self->b = b;
            self->b2 = b2;
            self->c = c;
            self->c2 = c2;
            self->d = d;
            self->d2 = d2;
            self->e = e;
            self->e2 = e2;
            self->f = f;
            self->f2 = f2;
            self->g = g;
            self->g2 = g2;
            self->h = h;
            self->h2 = h2;
            self->i = i;
            self->i2 = i2;
            self->j = j;
            self->j2 = j2;
            self->k = k;
            self->k2 = k2;
            self->l = l;
            self->l2 = l2;
            self->m = m;
            self->m2 = m2;
            self->n = n;
            self->n2 = n2;
            """
    )
    @Style(Styles.critical)
    abstract void set(@Unsigned byte a,
                      @Unsigned byte a2,
                      @Unsigned byte b,
                      @Unsigned byte b2,
                      @Unsigned short c,
                      @Unsigned short c2,
                      @Unsigned short d,
                      @Unsigned short d2,
                      @Unsigned short e,
                      @Unsigned short e2,
                      @Unsigned int f,
                      @Unsigned int f2,
                      @Unsigned int g,
                      @Unsigned int g2,
                      @Unsigned int h,
                      @Unsigned int h2,
                      @Unsigned int i,
                      @Unsigned int i2,
                      @Unsigned long j,
                      @Unsigned long j2,
                      @Unsigned long k,
                      @Unsigned long k2,
                      @Unsigned long l,
                      @Unsigned long l2,
                      @Unsigned long m,
                      @Unsigned long m2,
                      @Unsigned long n,
                      @Unsigned long n2);

    @Impl(c = "return self->a;")
    @Style(Styles.critical)
    abstract byte a();

    @Impl(c = "return self->a2;")
    @Style(Styles.critical)
    abstract byte a2();

    @Impl(c = "return self->b;")
    @Style(Styles.critical)
    abstract byte b();

    @Impl(c = "return self->b2;")
    @Style(Styles.critical)
    abstract byte b2();

    @Impl(c = "return self->c;")
    @Style(Styles.critical)
    abstract short c();

    @Impl(c = "return self->c2;")
    @Style(Styles.critical)
    abstract short c2();

    @Impl(c = "return self->d;")
    @Style(Styles.critical)
    abstract short d();

    @Impl(c = "return self->d2;")
    @Style(Styles.critical)
    abstract short d2();

    @Impl(c = "return self->e;")
    @Style(Styles.critical)
    abstract short e();

    @Impl(c = "return self->e2;")
    @Style(Styles.critical)
    abstract short e2();

    @Impl(c = "return self->f;")
    @Style(Styles.critical)
    abstract int f();

    @Impl(c = "return self->f2;")
    @Style(Styles.critical)
    abstract int f2();

    @Impl(c = "return self->g;")
    @Style(Styles.critical)
    abstract int g();

    @Impl(c = "return self->g2;")
    @Style(Styles.critical)
    abstract int g2();

    @Impl(c = "return self->h;")
    @Style(Styles.critical)
    abstract int h();

    @Impl(c = "return self->h2;")
    @Style(Styles.critical)
    abstract int h2();

    @Impl(c = "return self->i;")
    @Style(Styles.critical)
    abstract int i();

    @Impl(c = "return self->i2;")
    @Style(Styles.critical)
    abstract int i2();

    @Impl(c = "return self->j;")
    @Style(Styles.critical)
    abstract long j();

    @Impl(c = "return self->j2;")
    @Style(Styles.critical)
    abstract long j2();

    @Impl(c = "return self->k;")
    @Style(Styles.critical)
    abstract long k();

    @Impl(c = "return self->k2;")
    @Style(Styles.critical)
    abstract long k2();

    @Impl(c = "return self->l;")
    @Style(Styles.critical)
    abstract long l();

    @Impl(c = "return self->l2;")
    @Style(Styles.critical)
    abstract long l2();

    @Impl(c = "return self->m;")
    @Style(Styles.critical)
    abstract long m();

    @Impl(c = "return self->m2;")
    @Style(Styles.critical)
    abstract long m2();

    @Impl(c = "return self->n;")
    @Style(Styles.critical)
    abstract long n();

    @Impl(c = "return self->n2;")
    @Style(Styles.critical)
    abstract long n2();
}
