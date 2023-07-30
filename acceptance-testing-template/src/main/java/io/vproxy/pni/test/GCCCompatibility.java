package io.vproxy.pni.test;

import io.vproxy.pni.annotation.*;

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
