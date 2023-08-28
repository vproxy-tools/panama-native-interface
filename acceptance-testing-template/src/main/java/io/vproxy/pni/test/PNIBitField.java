package io.vproxy.pni.test;

import io.vproxy.pni.annotation.*;

import java.lang.foreign.MemorySegment;

@Struct
abstract class PNIBitField {
    @BitField(
        name = {"a", "b"},
        bit = {1, 3}
    )
    @Unsigned byte field01;
    @BitField(
        name = {"a2", "b2"},
        bit = {1, 3}
    )
    @Unsigned byte field02;
    MemorySegment sep01;
    @BitField(
        name = {"c", "d", "e"},
        bit = {2, 3, 4}
    )
    @Unsigned short field03;
    @BitField(
        name = {"c2", "d2", "e2"},
        bit = {2, 3, 4}
    )
    @Unsigned short field04;
    MemorySegment sep02;
    @BitField(
        name = {"f", "g", "h", "i"},
        bit = {5, 6, 7, 8}
    )
    @Unsigned int field05;
    @BitField(
        name = {"f2", "g2", "h2", "i2"},
        bit = {5, 6, 7, 8}
    )
    @Unsigned int field06;
    MemorySegment sep03;
    @BitField(
        name = {"j", "k", "l", "m"},
        bit = {1, 2, 22, 33}
    )
    @Unsigned long field07;
    @BitField(
        name = {"j2", "k2", "l2", "m2"},
        bit = {1, 2, 22, 33}
    )
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
            """
    )
    @Critical
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
                      @Unsigned long m2);

    @Impl(c = "return self->a;")
    @Critical
    abstract byte a();

    @Impl(c = "return self->a2;")
    @Critical
    abstract byte a2();

    @Impl(c = "return self->b;")
    @Critical
    abstract byte b();

    @Impl(c = "return self->b2;")
    @Critical
    abstract byte b2();

    @Impl(c = "return self->c;")
    @Critical
    abstract short c();

    @Impl(c = "return self->c2;")
    @Critical
    abstract short c2();

    @Impl(c = "return self->d;")
    @Critical
    abstract short d();

    @Impl(c = "return self->d2;")
    @Critical
    abstract short d2();

    @Impl(c = "return self->e;")
    @Critical
    abstract short e();

    @Impl(c = "return self->e2;")
    @Critical
    abstract short e2();

    @Impl(c = "return self->f;")
    @Critical
    abstract int f();

    @Impl(c = "return self->f2;")
    @Critical
    abstract int f2();

    @Impl(c = "return self->g;")
    @Critical
    abstract int g();

    @Impl(c = "return self->g2;")
    @Critical
    abstract int g2();

    @Impl(c = "return self->h;")
    @Critical
    abstract int h();

    @Impl(c = "return self->h2;")
    @Critical
    abstract int h2();

    @Impl(c = "return self->i;")
    @Critical
    abstract int i();

    @Impl(c = "return self->i2;")
    @Critical
    abstract int i2();

    @Impl(c = "return self->j;")
    @Critical
    abstract long j();

    @Impl(c = "return self->j2;")
    @Critical
    abstract long j2();

    @Impl(c = "return self->k;")
    @Critical
    abstract long k();

    @Impl(c = "return self->k2;")
    @Critical
    abstract long k2();

    @Impl(c = "return self->l;")
    @Critical
    abstract long l();

    @Impl(c = "return self->l2;")
    @Critical
    abstract long l2();

    @Impl(c = "return self->m;")
    @Critical
    abstract long m();

    @Impl(c = "return self->m2;")
    @Critical
    abstract long m2();
}
