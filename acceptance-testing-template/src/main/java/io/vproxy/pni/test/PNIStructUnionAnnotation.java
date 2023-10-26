package io.vproxy.pni.test;

import io.vproxy.pni.annotation.*;

@Struct(typedef = false)
abstract class PNIStructM {
    @Name("n_st") PNIStructN n;

    @LinkerOption.Critical
    abstract void nnn(@Name("n_st") PNIStructN n);
}

@Include("io_vproxy_pni_test_StructUnionAnnotation.h")
@Struct(typedef = false, skip = true)
@Name("N_st")
@Align(1)
abstract class PNIStructN {
    short s;
    long l;

    @LinkerOption.Critical
    abstract short retrieveS();

    @LinkerOption.Critical
    abstract long retrieveL();
}

@Union(typedef = false)
class PNIUnionO {
    short s;
    int i;
    PNIUnionP p;
}

@Include("io_vproxy_pni_test_StructUnionAnnotation.h")
@Union(skip = true)
abstract class PNIUnionP {
    int i;
    long l;

    @LinkerOption.Critical
    @Name("UnionP_retrieve_i")
    abstract int retrieveI();

    @LinkerOption.Critical
    @Name("UnionP_retrieve_l")
    abstract long retrieveL();
}
