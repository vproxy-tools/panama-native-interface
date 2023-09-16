package io.vproxy.pni.test;

import io.vproxy.pni.PNIFunc;
import io.vproxy.pni.PNIRef;
import io.vproxy.pni.annotation.*;

import java.util.List;

@Function
@Include("io_vproxy_pni_test_Userdata.h")
interface PNINativeCheck {
    @Impl(
        // language="c"
        c = """
            Userdata* ud = ref->userdata;
            *x = ud->x;
            *y = ud->y;
            *z = ud->z;
            return 0;
            """
    )
    void checkUserdataForRef(@Raw PNIRef<List<Integer>> ref, @Raw int[] x, @Raw long[] y, @Raw short[] z);

    @Impl(
        // language="c"
        c = """
            Userdata* ud = func->userdata;
            *x = ud->x;
            *y = ud->y;
            *z = ud->z;
            return 0;
            """
    )
    void checkUserdataForFunc(@Raw PNIFunc<Void> func, @Raw int[] x, @Raw long[] y, @Raw short[] z);
}

@Struct
class PNIUserdata {
    int x;
    long y;
    short z;
}
