package io.vproxy.pni.test;

import io.vproxy.pni.annotation.*;

import java.lang.foreign.MemorySegment;

@Struct
abstract class PNIBitField {
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
        @Bit.Field(name = "n", bits = 1)
    })
    @Unsigned long field07;
    @Bit({
        @Bit.Field(name = "j2", bits = 1),
        @Bit.Field(name = "k2", bits = 2),
        @Bit.Field(name = "l2", bits = 22),
        @Bit.Field(name = "m2", bits = 33),
        @Bit.Field(name = "n2", bits = 1, bool = true)
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
