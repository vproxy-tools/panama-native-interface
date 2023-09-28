package io.vproxy.pni.test;

import io.vproxy.pni.PNIRef;
import io.vproxy.pni.PNIFunc;
import io.vproxy.pni.annotation.Len;
import io.vproxy.pni.annotation.Pointer;
import io.vproxy.pni.annotation.Struct;
import io.vproxy.pni.annotation.Union;

@Struct
class PNIToStringClass {
    long num;
    PNIToStringClassRecurse cr;
}

@Struct
class PNIToStringClass2 {
    long num;
    PNIRef<Integer> ref;
    PNIFunc<Void> func;
    PNIToStringClass[] arrc;
}

@Struct
class PNIToStringClassRecurse {
    long num;
    @Pointer PNIToStringClass c;
    @Len(0) int[] arri;
}

@Struct
class PNIToStringArray {
    @Len(0) PNIToStringClass[] arrc;
    @Len(2) PNIToStringClass[] arrcLen2;
    PNIToStringClass[] parrc;
}

@Union
class PNIToStringUnion {
    long num;
    PNIToStringClass c1;
    PNIToStringClass2 c2;
    PNIToStringClassRecurse cr;
    @Pointer PNIToStringClass pc1;
    @Pointer PNIToStringClass2 pc2;
    @Pointer PNIToStringClassRecurse pcr;
}
