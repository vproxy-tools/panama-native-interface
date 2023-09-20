package io.vproxy.pni.test;

import io.vproxy.pni.annotation.*;

@Struct
@AlwaysAligned
class PNIAlwaysAlignedClass {
    byte a;
    short b;
    long c;
}

@Struct
@Align(packed = true)
class PNIAlwaysAlignedField {
    byte a;
    short b;
    byte c;
    @AlwaysAligned int d;
    @AlwaysAligned long e;
}

@Struct
@AlwaysAligned
class PNIAlwaysAlignedBase {
    byte a;
}

@Struct
@AlwaysAligned
class PNIAlwaysAlignedChild extends PNIAlwaysAlignedBase {
    short b;
}

@Struct
@AlwaysAligned
class PNIAlwaysAlignedGrandChild extends PNIAlwaysAlignedChild {
    long c;
}

@Struct(skip = true)
@AlwaysAligned
@Name("SizeofStructExpr")
@Sizeof("SizeofStructExpr")
@Include("sizeof.h")
class PNIAlwaysAlignedSizeof {
    byte b;
    short s;
}

@Union
@AlwaysAligned
class PNIAlwaysAlignedUnion {
    short a;
    int b;
    long c;
}
