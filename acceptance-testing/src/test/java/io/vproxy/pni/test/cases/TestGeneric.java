package io.vproxy.pni.test.cases;

import io.vproxy.pni.test.Generic;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.assertEquals;

public class TestGeneric {
    @Test
    public void load() {
        //noinspection ResultOfMethodCallIgnored
        Generic.get();
    }

    private String getLineOf(String match) throws Exception {
        match = " " + match + "(";
        var s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "Generic.java"));
        for (var l : s) {
            l = l.trim();
            if (!l.startsWith("public ")) {
                continue;
            }
            if (!l.endsWith(" {")) {
                continue;
            }
            if (l.contains(match)) {
                l = l.trim();
                return l.substring("public ".length(), l.length() - " {".length());
            }
        }
        throw new AssertionError("not found: " + match);
    }

    @Test
    public void simple() throws Exception {
        var line = getLineOf("simple");
        assertEquals("void simple(PNIEnv ENV, java.lang.String o)", line);
    }

    @Test
    public void simpleArr() throws Exception {
        var line = getLineOf("simpleArr");
        assertEquals("void simpleArr(PNIEnv ENV, java.lang.String[] o)", line);
    }

    @Test
    public void generic() throws Exception {
        var line = getLineOf("generic");
        assertEquals("void generic(PNIEnv ENV, java.util.List<java.lang.String> o)", line);
    }

    @Test
    public void genericArr() throws Exception {
        var line = getLineOf("genericArr");
        assertEquals("void genericArr(PNIEnv ENV, java.util.List<java.lang.String>[] o)", line);
    }

    @Test
    public void ext() throws Exception {
        var line = getLineOf("ext");
        assertEquals("void ext(PNIEnv ENV, java.util.List<? extends java.lang.Number> o)", line);
    }

    @Test
    public void extArr() throws Exception {
        var line = getLineOf("extArr");
        assertEquals("void extArr(PNIEnv ENV, java.util.List<? extends java.lang.Number>[] o)", line);
    }

    @Test
    public void sup() throws Exception {
        var line = getLineOf("sup");
        assertEquals("void sup(PNIEnv ENV, java.util.List<? super java.lang.Number> o)", line);
    }

    @Test
    public void supArr() throws Exception {
        var line = getLineOf("supArr");
        assertEquals("void supArr(PNIEnv ENV, java.util.List<? super java.lang.Number>[] o)", line);
    }

    @Test
    public void def() throws Exception {
        var line = getLineOf("def");
        assertEquals("<T extends java.lang.Object> void def(PNIEnv ENV, T o)", line);
    }

    @Test
    public void defArr() throws Exception {
        var line = getLineOf("defArr");
        assertEquals("<T extends java.lang.Object> void defArr(PNIEnv ENV, T[] o)", line);
    }

    @Test
    public void upper() throws Exception {
        var line = getLineOf("upper");
        assertEquals("<T extends java.lang.Number> void upper(PNIEnv ENV, T o)", line);
    }

    @Test
    public void upperArr() throws Exception {
        var line = getLineOf("upperArr");
        assertEquals("<T extends java.lang.Number> void upperArr(PNIEnv ENV, T[] o)", line);
    }

    @Test
    public void extendsArr() throws Exception {
        var line = getLineOf("extendsArr");
        assertEquals("void extendsArr(PNIEnv ENV, java.util.List<? extends java.lang.Number[]> o)", line);
    }

    @Test
    public void extendsArrArr() throws Exception {
        var line = getLineOf("extendsArrArr");
        assertEquals("void extendsArrArr(PNIEnv ENV, java.util.List<? extends java.lang.Number[]>[] o)", line);
    }

    @Test
    public void defGeneric() throws Exception {
        var line = getLineOf("defGeneric");
        assertEquals("<T extends java.util.List<java.lang.Number>> void defGeneric(PNIEnv ENV, T o)", line);
    }

    @Test
    public void defGenericExtends() throws Exception {
        var line = getLineOf("defGenericExtends");
        assertEquals("<T extends java.util.List<java.lang.Number>> void defGenericExtends(PNIEnv ENV, T o)", line);
    }

    @Test
    public void defGenericExtendsRaw() throws Exception {
        var line = getLineOf("defGenericExtendsRaw");
        assertEquals("<T extends java.util.List<java.lang.Number>> void defGenericExtendsRaw(PNIEnv ENV, PNIRef<? extends T> o)", line);
    }

    @Test
    public void defGenericArrayExtends() throws Exception {
        var line = getLineOf("defGenericArrayExtends");
        assertEquals("<T extends java.util.List<java.lang.Number>> void defGenericArrayExtends(PNIEnv ENV, T[] o)", line);
    }

    @Test
    public void defGenericArrayExtendsRaw() throws Exception {
        var line = getLineOf("defGenericArrayExtendsRaw");
        assertEquals("<T extends java.util.List<java.lang.Number>> void defGenericArrayExtendsRaw(PNIEnv ENV, PNIRef<? extends T[]> o)", line);
    }

    @Test
    public void defGenericSuper() throws Exception {
        var line = getLineOf("defGenericSuper");
        assertEquals("<T extends java.util.List<java.lang.Number>> void defGenericSuper(PNIEnv ENV, Object o)", line);
    }

    @Test
    public void defGenericSuperRaw() throws Exception {
        var line = getLineOf("defGenericSuperRaw");
        assertEquals("<T extends java.util.List<java.lang.Number>> void defGenericSuperRaw(PNIEnv ENV, PNIRef<? super T> o)", line);
    }

    @Test
    public void defGenericArraySuper() throws Exception {
        var line = getLineOf("defGenericArraySuper");
        assertEquals("<T extends java.util.List<java.lang.Number>> void defGenericArraySuper(PNIEnv ENV, Object o)", line);
    }

    @Test
    public void defGenericArraySuperRaw() throws Exception {
        var line = getLineOf("defGenericArraySuperRaw");
        assertEquals("<T extends java.util.List<java.lang.Number>> void defGenericArraySuperRaw(PNIEnv ENV, PNIRef<? super T[]> o)", line);
    }

    @Test
    public void returnSimple() throws Exception {
        var line = getLineOf("returnSimple");
        assertEquals("PNIRef<java.lang.String> returnSimple(PNIEnv ENV)", line);
    }

    @Test
    public void returnSimpleArr() throws Exception {
        var line = getLineOf("returnSimpleArr");
        assertEquals("PNIRef<java.lang.String[]> returnSimpleArr(PNIEnv ENV)", line);
    }

    @Test
    public void returnGeneric() throws Exception {
        var line = getLineOf("returnGeneric");
        assertEquals("PNIRef<java.util.List<java.lang.String>> returnGeneric(PNIEnv ENV)", line);
    }

    @Test
    public void returnGenericArr() throws Exception {
        var line = getLineOf("returnGenericArr");
        assertEquals("PNIRef<java.util.List<java.lang.String>[]> returnGenericArr(PNIEnv ENV)", line);
    }

    @Test
    public void returnExt() throws Exception {
        var line = getLineOf("returnExt");
        assertEquals("PNIRef<java.util.List<? extends java.lang.Number>> returnExt(PNIEnv ENV)", line);
    }

    @Test
    public void returnExtArr() throws Exception {
        var line = getLineOf("returnExtArr");
        assertEquals("PNIRef<java.util.List<? extends java.lang.Number>[]> returnExtArr(PNIEnv ENV)", line);
    }

    @Test
    public void returnSup() throws Exception {
        var line = getLineOf("returnSup");
        assertEquals("PNIRef<java.util.List<? super java.lang.Number>> returnSup(PNIEnv ENV)", line);
    }

    @Test
    public void returnSupArr() throws Exception {
        var line = getLineOf("returnSupArr");
        assertEquals("PNIRef<java.util.List<? super java.lang.Number>[]> returnSupArr(PNIEnv ENV)", line);
    }

    @Test
    public void returnDef() throws Exception {
        var line = getLineOf("returnDef");
        assertEquals("<T extends java.lang.Object> PNIRef<T> returnDef(PNIEnv ENV)", line);
    }

    @Test
    public void returnDefArr() throws Exception {
        var line = getLineOf("returnDefArr");
        assertEquals("<T extends java.lang.Object> PNIRef<T[]> returnDefArr(PNIEnv ENV)", line);
    }

    @Test
    public void returnUpper() throws Exception {
        var line = getLineOf("returnUpper");
        assertEquals("<T extends java.lang.Number> PNIRef<T> returnUpper(PNIEnv ENV)", line);
    }

    @Test
    public void returnUpperArr() throws Exception {
        var line = getLineOf("returnUpperArr");
        assertEquals("<T extends java.lang.Number> PNIRef<T[]> returnUpperArr(PNIEnv ENV)", line);
    }

    @Test
    public void returnExtendsArr() throws Exception {
        var line = getLineOf("returnExtendsArr");
        assertEquals("PNIRef<java.util.List<? extends java.lang.Number[]>> returnExtendsArr(PNIEnv ENV)", line);
    }

    @Test
    public void returnExtendsArrArr() throws Exception {
        var line = getLineOf("returnExtendsArrArr");
        assertEquals("PNIRef<java.util.List<? extends java.lang.Number[]>[]> returnExtendsArrArr(PNIEnv ENV)", line);
    }

    @Test
    public void returnDefGeneric() throws Exception {
        var line = getLineOf("returnDefGeneric");
        assertEquals("<T extends java.util.List<java.lang.Number>> PNIRef<T> returnDefGeneric(PNIEnv ENV)", line);
    }

    @Test
    public void returnDefGenericExtends() throws Exception {
        var line = getLineOf("returnDefGenericExtends");
        assertEquals("<T extends java.util.List<java.lang.Number>> PNIRef<? extends T> returnDefGenericExtends(PNIEnv ENV)", line);
    }

    @Test
    public void returnDefGenericArrayExtends() throws Exception {
        var line = getLineOf("returnDefGenericArrayExtends");
        assertEquals("<T extends java.util.List<java.lang.Number>> PNIRef<? extends T[]> returnDefGenericArrayExtends(PNIEnv ENV)", line);
    }

    @Test
    public void returnDefGenericSuper() throws Exception {
        var line = getLineOf("returnDefGenericSuper");
        assertEquals("<T extends java.util.List<java.lang.Number>> PNIRef<? super T> returnDefGenericSuper(PNIEnv ENV)", line);
    }

    @Test
    public void returnDefGenericArraySuper() throws Exception {
        var line = getLineOf("returnDefGenericArraySuper");
        assertEquals("<T extends java.util.List<java.lang.Number>> PNIRef<? super T[]> returnDefGenericArraySuper(PNIEnv ENV)", line);
    }

    @Test
    public void combined() throws Exception {
        var line = getLineOf("combined");
        assertEquals("<R extends java.lang.Object, T extends java.lang.Number, U extends java.util.List<? super java.lang.String>, V extends java.lang.Object> PNIRef<? super R> combined(PNIEnv ENV, T t, T[] tArr, java.util.List<? super U> ls, java.util.List<? super U[]> arrLs, java.util.List<U[]> arrLs2, java.util.List<? super U>[] lsArr, java.util.List<? super U[]>[] arrLsArr, java.util.List<U[]>[] arrLsArr2, java.util.Map<? super U, ? extends V> map, java.util.Map<? extends U[], ? extends V[]> arrMap, java.util.Map<? super U, ? extends V>[] mapArr, java.util.Map<? super U[], ? super V[]>[] arrMapArr, java.util.Map<U[], V[]>[] arrMapArr2)", line);
    }

    @Test
    public void wildcard() throws Exception {
        var line = getLineOf("wildcard");
        assertEquals("void wildcard(PNIEnv ENV, Object o)", line);
    }

    @Test
    public void shaCheck() throws Exception {
        var s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "Generic.java"));
        var lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:9bf7535c71dafff2ccb43eafe4c4b93056bc6c3dc1c211ca8fe602c19ae8fc61", lastLine);
    }
}
