package io.vproxy.pni.test;

import io.vproxy.pni.annotation.Downcall;
import io.vproxy.pni.annotation.Impl;
import io.vproxy.pni.annotation.NoAlloc;
import io.vproxy.pni.annotation.Upcall;

@Downcall
interface PNINoAlloc {
    @NoAlloc
    PNIObjectStruct execNoAlloc();

    @Impl(
        include = "io_vproxy_pni_test_NoAllocUpcall.h",
        c = """
            env->return_ = JavaCritical_io_vproxy_pni_test_NoAllocUpcall_execNoAlloc();
            return 0;
            """
    )
    @NoAlloc
    PNIObjectStruct invokeUpcall();
}

@Upcall
interface PNINoAllocUpcall {
    @NoAlloc
    PNIObjectStruct execNoAlloc();
}
