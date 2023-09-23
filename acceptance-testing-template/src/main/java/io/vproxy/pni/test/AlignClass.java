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
    @Critical
    abstract byte aaaa();

    @Impl(
        c = """
            return self->b;
            """
    )
    @Critical
    abstract short bbbb();

    @Impl(
        c = """
            return self->c;
            """
    )
    @Critical
    abstract long cccc();
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
    @Critical
    abstract byte aaaa();

    @Impl(
        c = """
            return self->b;
            """
    )
    @Critical
    abstract byte bbbb();

    @Impl(
        c = """
            return self->c;
            """
    )
    @Critical
    abstract int cccc();
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
    @Critical
    abstract byte aaaa();

    @Impl(
        c = """
            return self->b;
            """
    )
    @Critical
    abstract byte bbbb();

    @Impl(
        c = """
            return self->c;
            """
    )
    @Critical
    abstract int cccc();
}
