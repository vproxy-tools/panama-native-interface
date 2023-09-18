package io.vproxy.pni.test;

import io.vproxy.pni.annotation.*;

@Struct
@Sizeof("return 16;")
class PNISizeofStruct {
    int a;
    short b;
}

@Struct(skip = true)
@Name("SizeofStructExpr")
@Sizeof("SizeofStructExpr")
@Include("sizeof.h")
class PNISizeofStructExpr {
    byte b;
    short s;
}

@Union
@Sizeof("return 24;")
class PNISizeofUnion {
    PNISizeofStruct st;
    int a;
}

@Struct
@Sizeof(
    // language="c"
    """
        size_t ret = 16;
        ret += JavaCritical_io_vproxy_pni_test_SizeofStructExpr___getLayoutByteSize();
        return ret;
        """)
abstract class PNISizeofEmbed {
    byte x;
    @Pointer PNISizeofUnion un;
    PNISizeofStructExpr st;
}
