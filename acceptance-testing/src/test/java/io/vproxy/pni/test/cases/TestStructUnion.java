package io.vproxy.pni.test.cases;

import io.vproxy.pni.Allocator;
import io.vproxy.pni.PNIEnv;
import io.vproxy.pni.test.StructA;
import io.vproxy.pni.test.StructB;
import io.vproxy.pni.test.UnionC;
import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.foreign.Arena;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TestStructUnion {
    @BeforeClass
    public static void beforeClass() {
        System.loadLibrary("pnitest");
    }

    @Test
    public void checkNull() {
        try (var arena = Arena.ofConfined()) {
            var allocator = Allocator.of(arena);
            var s = new StructA(allocator);

            assertEquals(0, s.getB().getI());
            assertEquals(0, s.getB().getC().getN());
            assertEquals(0, s.getB().getC().getL());
            assertEquals(0, s.getB().getL());
            assertNull(s.getB().getD());
            assertEquals(0, s.getB().getEmbedded().getN());
            assertNull(s.getB().getEmbedded().getSeg());
            assertNull(s.getCPointer());
            assertEquals(0, s.getD().getN());
            assertEquals(0.0, s.getD().getD(), 0);
        }
    }

    @Test
    public void bbb() {
        try (var arena = Arena.ofConfined()) {
            var allocator = Allocator.of(arena);
            var env = new PNIEnv(arena);
            var s = new StructA(allocator);

            var b = new StructB(allocator);
            b.setI(1);
            b.getC().setN(2);
            b.setL(3);

            s.bbb(env, b);
            assertEquals(1, s.getB().getI());
            assertEquals(2, s.getB().getC().getN());
            assertEquals(3, s.getB().getL());
        }
    }

    @Test
    public void ccc() {
        try (var arena = Arena.ofConfined()) {
            var allocator = Allocator.of(arena);
            var env = new PNIEnv(arena);
            var s = new StructA(allocator);

            var c = new UnionC(allocator);
            c.setN(1);

            s.ccc(env, c);
            assertEquals(1, s.getC().getN());
        }
    }

    @Test
    public void cccPointer() {
        try (var arena = Arena.ofConfined()) {
            var allocator = Allocator.of(arena);
            var env = new PNIEnv(arena);
            var s = new StructA(allocator);

            var c = new UnionC(allocator);
            c.setN(1);

            s.cccPointer(env, c);
            assertEquals(c.MEMORY.address(), s.getCPointer().MEMORY.address());
            assertEquals(1, s.getCPointer().getN());
        }
    }

    @Test
    public void retrieveB() {
        try (var arena = Arena.ofConfined()) {
            var allocator = Allocator.of(arena);
            var env = new PNIEnv(arena);
            var s = new StructA(allocator);

            s.getB().setI(1);
            s.getB().setL(2);

            var b = s.retrieveB(env, allocator);
            assertEquals(1, b.getI());
            assertEquals(2, b.getL());
        }
    }

    @Test
    public void retrieveC() {
        try (var arena = Arena.ofConfined()) {
            var allocator = Allocator.of(arena);
            var env = new PNIEnv(arena);
            var s = new StructA(allocator);

            s.getC().setN(1);

            var c = s.retrieveC(env, allocator);
            assertEquals(1, c.getN());
        }
    }

    @Test
    public void retrieveCPointer() {
        try (var arena = Arena.ofConfined()) {
            var allocator = Allocator.of(arena);
            var env = new PNIEnv(arena);
            var s = new StructA(allocator);

            assertNull(s.retrieveCPointer(env, allocator));

            var c = new UnionC(allocator);
            c.setN(1);

            s.setCPointer(c);
            var cc = s.retrieveCPointer(env, allocator);
            assertEquals(1, cc.getN());
        }
    }

    @Test
    public void union() {
        try (var arena = Arena.ofConfined()) {
            var allocator = Allocator.of(arena);

            var c = new UnionC(allocator);
            c.setN(7);

            assertEquals(7, c.getN());
            assertEquals(7, c.getL());

            c.setL(0xabcdef123456L);

            assertEquals(0xabcdef123456L, c.getL());
            assertEquals(0xef123456, c.getN());
        }
    }

    @Test
    public void shaCheck() throws Exception {
        var s = Files.readAllLines(Path.of("src", "test", "c-generated", "io_vproxy_pni_test_StructA.h"));
        var lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:bd6d0c162105eb55767d13c06be5f44d1995640cb1fa89b9cea332bfcbf94848", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "c-generated", "io_vproxy_pni_test_StructB.h"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:6f3bd10c063a680dc8eb8d90a60d57b4de47c2ac9a1ed17fdb67bc4694b79ad1", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "c-generated", "io_vproxy_pni_test_StructD.h"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:1ebc7df07907db8196fa56ad5a1824d655a521f99a4dc6c0a53273fdf03d7ec7", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "c-generated", "io_vproxy_pni_test_UnionC.h"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:daacd9155cb56d5356447139013a7606aa0c5a64e01e87fb22d3f774e5ce77b9", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "c-generated", "io_vproxy_pni_test_UnionEmbedded.h"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:f775a97a09212a249e2877d2867b916518f1073766f15a7ff9458b3588c1eeaf", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "StructA.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:2c1b7dddbe25cead5aaa267896086ca0c59f3191793a3103e9177819b7728fe6", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "StructB.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:9f16564f39d21a7e75429b98c5881a22a5ca1a0b978f790001460d4702afc24a", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "StructD.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:95bc9941606c7d1ef5e545d9b9e4a80ba1097396c9e1b5bb0755537db0d074f8", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "UnionC.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:dae48fb3458789dace4494c98d18971a0b132ebb20df481f317f0d5cca5878f3", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "UnionEmbedded.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:49ed9405033168151913ef7b99827207f54d036cb2474be4e1ca253947e5e48a", lastLine);
    }
}
