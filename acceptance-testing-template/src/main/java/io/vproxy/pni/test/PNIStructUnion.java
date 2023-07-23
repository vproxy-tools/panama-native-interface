package io.vproxy.pni.test;

import io.vproxy.pni.annotation.Pointer;
import io.vproxy.pni.annotation.Struct;
import io.vproxy.pni.annotation.Union;

import java.lang.foreign.MemorySegment;

@Struct
abstract class PNIStructA {
    PNIStructB b;
    PNIUnionC c;
    @Pointer PNIUnionC cPointer;
    PNIStructD d;

    abstract void bbb(PNIStructB b);

    abstract void ccc(PNIUnionC c);

    abstract void cccPointer(PNIUnionC c);

    abstract PNIStructB retrieveB();

    abstract PNIUnionC retrieveC();

    abstract PNIUnionC retrieveCPointer();
}

@Struct
class PNIStructB {
    int i;
    PNIUnionC c;
    long l;
    @Pointer PNIStructD d;
    PNIUnionEmbedded embedded;
}

@Union
class PNIUnionC {
    int n;
    long l;
}

@Struct
class PNIStructD {
    int n;
    double d;
}

@Union(embedded = true)
class PNIUnionEmbedded {
    int n;
    MemorySegment seg;
}
