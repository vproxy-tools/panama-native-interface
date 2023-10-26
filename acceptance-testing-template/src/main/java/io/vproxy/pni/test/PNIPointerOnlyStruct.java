package io.vproxy.pni.test;

import io.vproxy.pni.annotation.*;

@Struct
@PointerOnly
abstract class PNIPointerOnlyStruct {
    @Impl(
        // language="c"
        c = """
            return *((long*)self);
            """
    )
    @Style(Styles.critical)
    abstract long retrieve();
}

@Struct
@PointerOnly
@Sizeof("return 32;")
class PNIPointerOnlyStructWithLen {
}
