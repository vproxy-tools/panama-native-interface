package io.vproxy.pni.test;

import io.vproxy.pni.annotation.*;

@Struct
@AlwaysAligned
@Align(32)
abstract class PNIAlignClass {
    byte a;
    short b;
    long c;

    @Impl(
        c = """
            return self->a;
            """
    )
    @Style(Styles.critical)
    abstract byte aaaa();

    @Impl(
        c = """
            return self->b;
            """
    )
    @Style(Styles.critical)
    abstract short bbbb();

    @Impl(
        c = """
            return self->c;
            """
    )
    @Style(Styles.critical)
    abstract long cccc();

    @Impl(
        c = """
            return sizeof(*self);
            """
    )
    @Style(Styles.critical)
    abstract long size();
}

@Struct
@AlwaysAligned
abstract class PNIAlignField {
    @Align(2) byte a;
    @Align(2) byte b;
    int c;

    @Impl(
        c = """
            return self->a;
            """
    )
    @Style(Styles.critical)
    abstract byte aaaa();

    @Impl(
        c = """
            return self->b;
            """
    )
    @Style(Styles.critical)
    abstract byte bbbb();

    @Impl(
        c = """
            return self->c;
            """
    )
    @Style(Styles.critical)
    abstract int cccc();

    @Impl(
        c = """
            return sizeof(*self);
            """
    )
    @Style(Styles.critical)
    abstract long size();
}

@Struct
@AlwaysAligned
abstract class PNIAlignField2 {
    @Align(2) byte a;
    @Align(32) byte b;
    int c;

    @Impl(
        c = """
            return self->a;
            """
    )
    @Style(Styles.critical)
    abstract byte aaaa();

    @Impl(
        c = """
            return self->b;
            """
    )
    @Style(Styles.critical)
    abstract byte bbbb();

    @Impl(
        c = """
            return self->c;
            """
    )
    @Style(Styles.critical)
    abstract int cccc();

    @Impl(
        c = """
            return sizeof(*self);
            """
    )
    @Style(Styles.critical)
    abstract long size();
}

@Struct
@AlwaysAligned
abstract class PNIAlignField3 {
    @Align(2) short a;
    @Align(32) int b;
    int c;

    @Impl(
        c = """
            return self->a;
            """
    )
    @Style(Styles.critical)
    abstract short aaaa();

    @Impl(
        c = """
            return self->b;
            """
    )
    @Style(Styles.critical)
    abstract int bbbb();

    @Impl(
        c = """
            return self->c;
            """
    )
    @Style(Styles.critical)
    abstract int cccc();

    @Impl(
        c = """
            return sizeof(*self);
            """
    )
    @Style(Styles.critical)
    abstract long size();
}

@Struct
@AlwaysAligned
abstract class PNIAlignBaseClass {
    short a;

    @Impl(
        c = """
            return self->a;
            """
    )
    @Style(Styles.critical)
    abstract short aaaa();

    @Impl(
        c = """
            return sizeof(*self);
            """
    )
    @Style(Styles.critical)
    abstract long size0();
}

@Struct
@AlwaysAligned
abstract class PNIAlignChildClass extends PNIAlignBaseClass {
    @Align(16) int b;
    int c;

    @Impl(
        c = """
            return self->b;
            """
    )
    @Style(Styles.critical)
    abstract int bbbb();

    @Impl(
        c = """
            return self->c;
            """
    )
    @Style(Styles.critical)
    abstract int cccc();

    @Impl(
        c = """
            return sizeof(*self);
            """
    )
    @Style(Styles.critical)
    abstract long size();
}
