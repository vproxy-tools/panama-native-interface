package io.vproxy.pni.test.cases;

import io.vproxy.pni.Allocator;
import io.vproxy.pni.PNIEnv;
import io.vproxy.pni.test.StructA;
import io.vproxy.pni.test.StructB;
import io.vproxy.pni.test.UnionC;
import org.junit.BeforeClass;
import org.junit.Test;

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
        try (var allocator = Allocator.ofConfined()) {
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
        bbb(0);
    }

    @Test
    public void bbbCritical() {
        bbb(1);
    }

    private void bbb(int round) {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);
            var s = new StructA(allocator);

            var b = new StructB(allocator);
            b.setI(1);
            b.getC().setN(2);
            b.setL(3);

            if (round == 0) {
                s.bbb(env, b);
            } else {
                s.bbbCritical(b);
            }
            assertEquals(1, s.getB().getI());
            assertEquals(2, s.getB().getC().getN());
            assertEquals(3, s.getB().getL());
        }
    }

    @Test
    public void ccc() {
        ccc(0);
    }

    @Test
    public void cccCritical() {
        ccc(1);
    }

    private void ccc(int round) {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);
            var s = new StructA(allocator);

            var c = new UnionC(allocator);
            c.setN(1);

            if (round == 0) {
                s.ccc(env, c);
            } else {
                s.cccCritical(c);
            }
            assertEquals(1, s.getC().getN());
        }
    }

    @Test
    public void cccPointer() {
        cccPointer(0);
    }

    @Test
    public void cccPointerCritical() {
        cccPointer(1);
    }

    private void cccPointer(int round) {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);
            var s = new StructA(allocator);

            var c = new UnionC(allocator);
            c.setN(1);

            if (round == 0) {
                s.cccPointer(env, c);
            } else {
                s.cccPointerCritical(c);
            }
            assertEquals(c.MEMORY.address(), s.getCPointer().MEMORY.address());
            assertEquals(1, s.getCPointer().getN());
        }
    }

    @Test
    public void bArray() {
        bArray(0);
    }

    @Test
    public void bArrayCritical() {
        bArray(1);
    }

    private void bArray(int round) {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);
            var s = new StructA(allocator);

            var bArray = new StructB.Array(allocator, 3);
            for (int i = 0; i < bArray.length(); ++i) {
                bArray.get(i).setI(10 + i);
            }

            if (round == 0) {
                s.bbbArray(env, bArray);
            } else {
                s.bbbArrayCritical(bArray);
            }
            for (int i = 0; i < 3; ++i) {
                assertEquals(10 + i, s.getBArray().get(i).getI());
            }
        }
    }

    @Test
    public void bArray2() {
        bArray2(0);
    }

    @Test
    public void bArray2Critical() {
        bArray2(1);
    }

    private void bArray2(int round) {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);
            var s = new StructA(allocator);

            var bArray = new StructB.Array(allocator, 3);
            for (int i = 0; i < bArray.length(); ++i) {
                bArray.get(i).setI(10 + i);
            }

            if (round == 0) {
                s.bbbArray2(env, bArray);
            } else {
                s.bbbArray2Critical(bArray);
            }
            for (int i = 0; i < 3; ++i) {
                assertEquals(10 + i, s.getBArray2().get(i).getI());
            }
        }
    }

    @Test
    public void retrieveB() {
        retrieveB(0);
    }

    @Test
    public void retrieveBCritical() {
        retrieveB(1);
    }

    private void retrieveB(int round) {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);
            var s = new StructA(allocator);

            s.getB().setI(1);
            s.getB().setL(2);

            var b = round == 0 ? s.retrieveB(env, allocator) : s.retrieveBCritical(allocator);
            assertEquals(1, b.getI());
            assertEquals(2, b.getL());
        }
    }

    @Test
    public void retrieveC() {
        retrieveC(0);
    }

    @Test
    public void retrieveCCritical() {
        retrieveC(1);
    }

    private void retrieveC(int round) {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);
            var s = new StructA(allocator);

            s.getC().setN(1);

            var c = round == 0 ? s.retrieveC(env, allocator) : s.retrieveCCritical(allocator);
            assertEquals(1, c.getN());
        }
    }

    @Test
    public void retrieveCPointer() {
        retrieveCPointer(0);
    }

    @Test
    public void retrieveCPointerCritical() {
        retrieveCPointer(1);
    }

    private void retrieveCPointer(int round) {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);
            var s = new StructA(allocator);

            if (round == 0) {
                assertNull(s.retrieveCPointer(env, allocator));
            } else {
                assertNull(s.retrieveCPointerCritical(allocator));
            }

            var c = new UnionC(allocator);
            c.setN(1);

            s.setCPointer(c);
            var cc = round == 0 ? s.retrieveCPointer(env, allocator) : s.retrieveCPointerCritical(allocator);
            assertEquals(1, cc.getN());
        }
    }

    @Test
    public void union() {
        try (var allocator = Allocator.ofConfined()) {
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
    public void retrieveBArray() {
        retrieveBArray(0);
    }

    @Test
    public void retrieveBArrayCritical() {
        retrieveBArray(1);
    }

    private void retrieveBArray(int round) {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);
            var s = new StructA(allocator);

            var bArray = new StructB.Array(allocator, 4);
            for (int i = 0; i < bArray.length(); ++i) {
                bArray.get(i).setI(20 + i);
            }

            if (round == 0) {
                assertNull(s.retrieveBArray(env));
            } else {
                assertNull(s.retrieveBArrayCritical());
            }

            s.setBArray(bArray);

            StructB.Array res;
            if (round == 0) {
                res = s.retrieveBArray(env);
            } else {
                res = s.retrieveBArrayCritical();
            }
            assertEquals(4, res.length());
            for (int i = 0; i < 4; ++i) {
                assertEquals(20 + i, res.get(i).getI());
            }
        }
    }

    @Test
    public void retrieveBArray2() {
        retrieveBArray2(0);
    }

    @Test
    public void retrieveBArray2Critical() {
        retrieveBArray2(1);
    }

    private void retrieveBArray2(int round) {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);
            var s = new StructA(allocator);

            var bArray = new StructB.Array(allocator, 5);
            for (int i = 0; i < bArray.length(); ++i) {
                bArray.get(i).setI(40 + i);
            }

            if (round == 0) {
                assertEquals(5, s.retrieveBArray2(env).length());
            } else {
                assertEquals(5, s.retrieveBArray2Critical().length());
            }

            for (int i = 0; i < 5; ++i) {
                s.getBArray2().get(i).setI(40 + i);
            }

            StructB.Array res;
            if (round == 0) {
                res = s.retrieveBArray2(env);
            } else {
                res = s.retrieveBArray2Critical();
            }
            assertEquals(5, res.length());
            for (int i = 0; i < 5; ++i) {
                assertEquals(40 + i, res.get(i).getI());
            }
        }
    }

    @Test
    public void shaCheck() throws Exception {
        var s = Files.readAllLines(Path.of("src", "test", "c-generated", "io_vproxy_pni_test_StructA.h"));
        var lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:62e06de82b2cd2b03fa6388e55a871976a4e27f183b1eaa2e37b707ba0bc1a24", lastLine);

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
        assertEquals("// sha256:1714b64a5c7fd01cbdd69222c9cfa74242bd75f0d911e403a9237e87267bead1", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "StructB.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:4c99e489f5cb96706b8640e7aad0d578cdf7153f1df352637e67035eb1ab2b38", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "StructD.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:7904035ca157b7ce920be830d91254db9b267705f45bcb402fcede60fd9df3ac", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "UnionC.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:87d0bc138f452dbded7b533b4bc776ebe8e26d9a7f1947003d5ce6227ed7d3a2", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "UnionEmbedded.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:30f55f76eeb439ec3139025710a9b1deda2830b3ee39baa5e256d5cedd416d59", lastLine);
    }
}
