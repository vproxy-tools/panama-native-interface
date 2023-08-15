package io.vproxy.pni.test;

import io.vproxy.pni.exec.internal.Utils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestDescParsing {
    @Test
    public void primitive() {
        var res = Utils.extractDesc("I").get(0);
        assertEquals("int", res.toString());
        res = Utils.extractDesc("J").get(0);
        assertEquals("long", res.toString());
        res = Utils.extractDesc("F").get(0);
        assertEquals("float", res.toString());
        res = Utils.extractDesc("D").get(0);
        assertEquals("double", res.toString());
        res = Utils.extractDesc("C").get(0);
        assertEquals("char", res.toString());
        res = Utils.extractDesc("B").get(0);
        assertEquals("byte", res.toString());
        res = Utils.extractDesc("S").get(0);
        assertEquals("short", res.toString());
        res = Utils.extractDesc("Z").get(0);
        assertEquals("boolean", res.toString());
    }

    @Test
    public void primitiveArray() {
        var res = Utils.extractDesc("[I").get(0);
        assertEquals("int[]", res.toString());
        res = Utils.extractDesc("[J").get(0);
        assertEquals("long[]", res.toString());
        res = Utils.extractDesc("[F").get(0);
        assertEquals("float[]", res.toString());
        res = Utils.extractDesc("[D").get(0);
        assertEquals("double[]", res.toString());
        res = Utils.extractDesc("[C").get(0);
        assertEquals("char[]", res.toString());
        res = Utils.extractDesc("[B").get(0);
        assertEquals("byte[]", res.toString());
        res = Utils.extractDesc("[S").get(0);
        assertEquals("short[]", res.toString());
        res = Utils.extractDesc("[Z").get(0);
        assertEquals("boolean[]", res.toString());
    }

    @Test
    public void primitive2DArray() {
        var res = Utils.extractDesc("[[I").get(0);
        assertEquals("int[][]", res.toString());
        res = Utils.extractDesc("[[J").get(0);
        assertEquals("long[][]", res.toString());
        res = Utils.extractDesc("[[F").get(0);
        assertEquals("float[][]", res.toString());
        res = Utils.extractDesc("[[D").get(0);
        assertEquals("double[][]", res.toString());
        res = Utils.extractDesc("[[C").get(0);
        assertEquals("char[][]", res.toString());
        res = Utils.extractDesc("[[B").get(0);
        assertEquals("byte[][]", res.toString());
        res = Utils.extractDesc("[[S").get(0);
        assertEquals("short[][]", res.toString());
        res = Utils.extractDesc("[[Z").get(0);
        assertEquals("boolean[][]", res.toString());
    }

    @Test
    public void object() {
        var res = Utils.extractDesc("Ljava/lang/Object;").get(0);
        assertEquals("java.lang.Object", res.toString());
    }

    @Test
    public void objectArray() {
        var res = Utils.extractDesc("[Ljava/lang/Object;").get(0);
        assertEquals("java.lang.Object[]", res.toString());
    }

    @Test
    public void object2DArray() {
        var res = Utils.extractDesc("[[Ljava/lang/Object;").get(0);
        assertEquals("java.lang.Object[][]", res.toString());
    }

    @Test
    public void generic() {
        var res = Utils.extractDesc("TT;").get(0);
        assertEquals("T", res.toString());
    }

    @Test
    public void genericArray() {
        var res = Utils.extractDesc("[TT;").get(0);
        assertEquals("T[]", res.toString());
    }

    @Test
    public void generic2DArray() {
        var res = Utils.extractDesc("[[TT;").get(0);
        assertEquals("T[][]", res.toString());
    }

    @Test
    public void special() {
        var res = Utils.extractDesc("*").get(0);
        assertEquals("?", res.toString());
        res = Utils.extractDesc("V").get(0);
        assertEquals("void", res.toString());
    }

    @Test
    public void genericTypeParams() {
        var res = Utils.extractDesc("Ljava/util/List<Ljava/lang/String;>;").get(0);
        assertEquals("java.util.List<java.lang.String>", res.toString());

        res = Utils.extractDesc("Ljava/util/List<+Ljava/lang/String;>;").get(0);
        assertEquals("java.util.List<? extends java.lang.String>", res.toString());

        res = Utils.extractDesc("Ljava/util/List<-Ljava/lang/String;>;").get(0);
        assertEquals("java.util.List<? super java.lang.String>", res.toString());
    }

    @Test
    public void genericTypeParamsArray() {
        var res = Utils.extractDesc("[Ljava/util/List<Ljava/lang/String;>;").get(0);
        assertEquals("java.util.List<java.lang.String>[]", res.toString());

        res = Utils.extractDesc("[Ljava/util/List<+Ljava/lang/String;>;").get(0);
        assertEquals("java.util.List<? extends java.lang.String>[]", res.toString());

        res = Utils.extractDesc("[Ljava/util/List<-Ljava/lang/String;>;").get(0);
        assertEquals("java.util.List<? super java.lang.String>[]", res.toString());
    }

    @Test
    public void genericTypeParams2DArray() {
        var res = Utils.extractDesc("[[Ljava/util/List<Ljava/lang/String;>;").get(0);
        assertEquals("java.util.List<java.lang.String>[][]", res.toString());

        res = Utils.extractDesc("[[Ljava/util/List<+Ljava/lang/String;>;").get(0);
        assertEquals("java.util.List<? extends java.lang.String>[][]", res.toString());

        res = Utils.extractDesc("[[Ljava/util/List<-Ljava/lang/String;>;").get(0);
        assertEquals("java.util.List<? super java.lang.String>[][]", res.toString());
    }

    @Test
    public void genericTypeParams2DArrayShortType() {
        var res = Utils.extractDesc("[[LA<Ljava/lang/String;>;").get(0);
        assertEquals("A<java.lang.String>[][]", res.toString());

        res = Utils.extractDesc("[[LA<+Ljava/lang/String;>;").get(0);
        assertEquals("A<? extends java.lang.String>[][]", res.toString());

        res = Utils.extractDesc("[[LA<-Ljava/lang/String;>;").get(0);
        assertEquals("A<? super java.lang.String>[][]", res.toString());
    }

    @Test
    public void arrayInGenericTypeParams() {
        var res = Utils.extractDesc("Ljava/util/List<[Ljava/lang/String;>;").get(0);
        assertEquals("java.util.List<java.lang.String[]>", res.toString());

        res = Utils.extractDesc("Ljava/util/List<[[Ljava/lang/String;>;").get(0);
        assertEquals("java.util.List<java.lang.String[][]>", res.toString());
    }

    @Test
    public void arrayInGenericTypeParamsArray() {
        var res = Utils.extractDesc("[Ljava/util/List<[Ljava/lang/String;>;").get(0);
        assertEquals("java.util.List<java.lang.String[]>[]", res.toString());

        res = Utils.extractDesc("[Ljava/util/List<[[Ljava/lang/String;>;").get(0);
        assertEquals("java.util.List<java.lang.String[][]>[]", res.toString());
    }

    @Test
    public void arrayInGenericTypeParams2DArray() {
        var res = Utils.extractDesc("[[Ljava/util/List<[Ljava/lang/String;>;").get(0);
        assertEquals("java.util.List<java.lang.String[]>[][]", res.toString());

        res = Utils.extractDesc("[[Ljava/util/List<[[Ljava/lang/String;>;").get(0);
        assertEquals("java.util.List<java.lang.String[][]>[][]", res.toString());
    }

    @Test
    public void extendsOrSuperArray() {
        var res = Utils.extractDesc("Ljava/util/List<+[Ljava/lang/Number;>;").get(0);
        assertEquals("java.util.List<? extends java.lang.Number[]>", res.toString());

        res = Utils.extractDesc("Ljava/util/List<+[[Ljava/lang/Number;>;").get(0);
        assertEquals("java.util.List<? extends java.lang.Number[][]>", res.toString());
    }

    @Test
    public void extendsOrSuperArrayArray() {
        var res = Utils.extractDesc("[Ljava/util/List<+[Ljava/lang/Number;>;").get(0);
        assertEquals("java.util.List<? extends java.lang.Number[]>[]", res.toString());

        res = Utils.extractDesc("[Ljava/util/List<+[[Ljava/lang/Number;>;").get(0);
        assertEquals("java.util.List<? extends java.lang.Number[][]>[]", res.toString());
    }

    @Test
    public void genericDef() {
        var res = Utils.extractGenericDefinition("<A:Ljava/lang/Object;>").get(0);
        assertEquals("A extends java.lang.Object", res.toString());

        var res2 = Utils.extractGenericDefinition("<A:Ljava/lang/Object;B::Ljava/util/List;>");
        assertEquals("[A extends java.lang.Object, B extends java.util.List]", res2.toString());

        var res3 = Utils.extractGenericDefinition("<A:Ljava/lang/Object;B::Ljava/util/List<Ljava/lang/String;>;>");
        assertEquals("[A extends java.lang.Object, B extends java.util.List<java.lang.String>]", res3.toString());
    }

    @Test
    public void params() {
        var res = Utils.extractDesc("IJFDBCSZ[I[J[F[D[B[C[S[ZLjava/lang/Object;[Ljava/lang/Object;TT;TABC;[TT;Ljava/util/List<+Ljava/lang/Number;>;Ljava/util/List<-Ljava/lang/Number;>;");
        assertEquals("[int, long, float, double, byte, char, short, boolean, int[], long[], float[], double[], byte[], char[], short[], boolean[], java.lang.Object, java.lang.Object[], T, ABC, T[], java.util.List<? extends java.lang.Number>, java.util.List<? super java.lang.Number>]", res.toString());
    }
}
