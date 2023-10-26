package io.vproxy.pni.test;

import io.vproxy.pni.PNIRef;
import io.vproxy.pni.annotation.Downcall;
import io.vproxy.pni.annotation.Impl;
import io.vproxy.pni.annotation.Raw;

import java.util.List;
import java.util.Map;

@Downcall
public interface PNIGeneric {
    @Impl(c = "return 0;")
    void simple(PNIRef<String> o);

    @Impl(c = "return 0;")
    void simpleArr(PNIRef<String[]> o);

    @Impl(c = "return 0;")
    void generic(PNIRef<List<String>> o);

    @Impl(c = "return 0;")
    void genericArr(PNIRef<List<String>[]> o);

    @Impl(c = "return 0;")
    void ext(PNIRef<List<? extends Number>> o);

    @Impl(c = "return 0;")
    void extArr(PNIRef<List<? extends Number>[]> o);

    @Impl(c = "return 0;")
    void sup(PNIRef<List<? super Number>> o);

    @Impl(c = "return 0;")
    void supArr(PNIRef<List<? super Number>[]> o);

    @Impl(c = "return 0;")
    <T> void def(PNIRef<T> o);

    @Impl(c = "return 0;")
    <T> void defArr(PNIRef<T[]> o);

    @Impl(c = "return 0;")
    <T extends Number> void upper(PNIRef<T> o);

    @Impl(c = "return 0;")
    <T extends Number> void upperArr(PNIRef<T[]> o);

    @Impl(c = "return 0;")
    void extendsArr(PNIRef<List<? extends Number[]>> o);

    @Impl(c = "return 0;")
    void extendsArrArr(PNIRef<List<? extends Number[]>[]> o);

    @Impl(c = "return 0;")
    <T extends List<Number>> void defGeneric(PNIRef<T> o);

    @Impl(c = "return 0;")
    <T extends List<Number>> void defGenericExtends(PNIRef<? extends T> o);

    @Impl(c = "return 0;")
    <T extends List<Number>> void defGenericExtendsRaw(@Raw PNIRef<? extends T> o);

    @Impl(c = "return 0;")
    <T extends List<Number>> void defGenericArrayExtends(PNIRef<? extends T[]> o);

    @Impl(c = "return 0;")
    <T extends List<Number>> void defGenericArrayExtendsRaw(@Raw PNIRef<? extends T[]> o);

    @Impl(c = "return 0;")
    <T extends List<Number>> void defGenericSuper(PNIRef<? super T> o);

    @Impl(c = "return 0;")
    <T extends List<Number>> void defGenericSuperRaw(@Raw PNIRef<? super T> o);

    @Impl(c = "return 0;")
    <T extends List<Number>> void defGenericArraySuper(PNIRef<? super T[]> o);

    @Impl(c = "return 0;")
    <T extends List<Number>> void defGenericArraySuperRaw(@Raw PNIRef<? super T[]> o);

    @Impl(c = "return 0;")
    PNIRef<String> returnSimple();

    @Impl(c = "return 0;")
    PNIRef<String[]> returnSimpleArr();

    @Impl(c = "return 0;")
    PNIRef<List<String>> returnGeneric();

    @Impl(c = "return 0;")
    PNIRef<List<String>[]> returnGenericArr();

    @Impl(c = "return 0;")
    PNIRef<List<? extends Number>> returnExt();

    @Impl(c = "return 0;")
    PNIRef<List<? extends Number>[]> returnExtArr();

    @Impl(c = "return 0;")
    PNIRef<List<? super Number>> returnSup();

    @Impl(c = "return 0;")
    PNIRef<List<? super Number>[]> returnSupArr();

    @Impl(c = "return 0;")
    <T> PNIRef<T> returnDef();

    @Impl(c = "return 0;")
    <T> PNIRef<T[]> returnDefArr();

    @Impl(c = "return 0;")
    <T extends Number> PNIRef<T> returnUpper();

    @Impl(c = "return 0;")
    <T extends Number> PNIRef<T[]> returnUpperArr();

    @Impl(c = "return 0;")
    PNIRef<List<? extends Number[]>> returnExtendsArr();

    @Impl(c = "return 0;")
    PNIRef<List<? extends Number[]>[]> returnExtendsArrArr();

    @Impl(c = "return 0;")
    <T extends List<Number>> PNIRef<T> returnDefGeneric();

    @Impl(c = "return 0;")
    <T extends List<Number>> PNIRef<? extends T> returnDefGenericExtends();

    @Impl(c = "return 0;")
    <T extends List<Number>> PNIRef<? extends T[]> returnDefGenericArrayExtends();

    @Impl(c = "return 0;")
    <T extends List<Number>> PNIRef<? super T> returnDefGenericSuper();

    @Impl(c = "return 0;")
    <T extends List<Number>> PNIRef<? super T[]> returnDefGenericArraySuper();

    @Impl(c = "return 0;")
    <R, T extends Number, U extends List<? super String>, V> PNIRef<? super R> combined(
        PNIRef<T> t,
        PNIRef<T[]> tArr,
        PNIRef<List<? super U>> ls,
        PNIRef<List<? super U[]>> arrLs,
        PNIRef<List<U[]>> arrLs2,
        PNIRef<List<? super U>[]> lsArr,
        PNIRef<List<? super U[]>[]> arrLsArr,
        PNIRef<List<U[]>[]> arrLsArr2,
        PNIRef<Map<? super U, ? extends V>> map,
        PNIRef<Map<? extends U[], ? extends V[]>> arrMap,
        PNIRef<Map<? super U, ? extends V>[]> mapArr,
        PNIRef<Map<? super U[], ? super V[]>[]> arrMapArr,
        PNIRef<Map<U[], V[]>[]> arrMapArr2
    );

    @Impl(c = "return 0;")
    void wildcard(PNIRef<?> o);
}
