package io.vproxy.pni.test;

import io.vproxy.pni.annotation.*;

import java.lang.foreign.MemorySegment;

@Struct
abstract class PNIStructA {
    PNIStructB b;
    PNIUnionC c;
    @Pointer PNIUnionC cPointer;
    PNIStructD d;
    PNIStructB[] bArray;
    @Len(5) PNIStructB[] bArray2;

    abstract void bbb(PNIStructB b);

    @Style(Styles.critical)
    abstract void bbbCritical(PNIStructB b);

    abstract void ccc(PNIUnionC c);

    @Style(Styles.critical)
    abstract void cccCritical(PNIUnionC c);

    abstract void cccPointer(PNIUnionC c);

    @Style(Styles.critical)
    abstract void cccPointerCritical(PNIUnionC c);

    abstract void bbbArray(PNIStructB[] bArray);

    @Style(Styles.critical)
    abstract void bbbArrayCritical(PNIStructB[] bArray);

    abstract void bbbArray2(PNIStructB[] bArray);

    @Style(Styles.critical)
    abstract void bbbArray2Critical(PNIStructB[] bArray);

    abstract PNIStructB retrieveB();

    @Style(Styles.critical)
    abstract PNIStructB retrieveBCritical();

    abstract PNIUnionC retrieveC();

    @Style(Styles.critical)
    abstract PNIUnionC retrieveCCritical();

    abstract PNIUnionC retrieveCPointer();

    @Style(Styles.critical)
    abstract PNIUnionC retrieveCPointerCritical();

    abstract PNIStructB[] retrieveBArray();

    @Style(Styles.critical)
    abstract PNIStructB[] retrieveBArrayCritical();

    abstract PNIStructB[] retrieveBArray2();

    @Style(Styles.critical)
    abstract PNIStructB[] retrieveBArray2Critical();
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
