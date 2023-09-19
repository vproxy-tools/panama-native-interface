package io.vproxy.pni.test;

import io.vproxy.pni.annotation.Align;
import io.vproxy.pni.annotation.Impl;
import io.vproxy.pni.annotation.Struct;

@Struct
abstract class PNIBaseClass {
    byte a;

    @Impl(
        // language="c"
        c = """
            self->a = a;
            return 0;
            """
    )
    abstract void aaa(byte a);
}

@Struct
abstract class PNIChildClass extends PNIBaseClass {
    short x;

    @Impl(
        // language="c"
        c = """
            self->x = x;
            return 0;
            """
    )
    abstract void xxx(short x);
}

@Struct
abstract class PNIGrandChildClass extends PNIChildClass {
    long y;

    @Impl(
        // language="c"
        c = """
            self->y = y;
            return 0;
            """
    )
    abstract void yyy(long y);
}

@Struct
@Align(packed = true)
abstract class PNIPackedBaseClass {
    byte a;
    short b;

    @Impl(
        // language="c"
        c = """
            self->a = a;
            return 0;
            """
    )
    abstract void aaa(byte a);

    @Impl(
        // language="c"
        c = """
            self->b = b;
            return 0;
            """
    )
    abstract void bbb(short b);
}

@Struct
abstract class PNIChildOfPacked extends PNIPackedBaseClass {
    int x;
    PNIObjectStruct o;

    @Impl(
        // language="c"
        c = """
            self->x = x;
            return 0;
            """
    )
    abstract void xxx(int x);

    @Impl(
        // language="c"
        c = """
            self->o = *o;
            return 0;
            """
    )
    abstract void ooo(PNIObjectStruct o);
}

@Struct
abstract class PNILargeAlignBase {
    long x;
}

@Struct
abstract class PNIChildOfLargeAlign extends PNILargeAlignBase {
    byte y;
}
