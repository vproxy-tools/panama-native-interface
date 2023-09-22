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
        assertEquals("// sha256:3b16d8ce35eb5b8a73c99187fc79ac7bbbf5c701663b79a55f6fa4f9e992fcf6", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "c-generated", "io_vproxy_pni_test_StructB.h"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:d90fc0ede1dd5b453c9d36c52ced018e743fce7d6833b6e5871c27efa0fb66fd", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "c-generated", "io_vproxy_pni_test_StructD.h"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:4dfdb06fa36b10f2e8ec963cb2172adbb1d1ec1f42334ddc6ae0e94f45d9c5f4", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "c-generated", "io_vproxy_pni_test_UnionC.h"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:eda297c5239137523e1577fe3ffe6c9185b81f0f5db08aeaa6f575f0cd6b9f1a", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "c-generated", "io_vproxy_pni_test_UnionEmbedded.h"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:f775a97a09212a249e2877d2867b916518f1073766f15a7ff9458b3588c1eeaf", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "StructA.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:f9f0d81693b257f055e8f182133e5033e864655d83cf4f8d40af2ae4366c49c4", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "StructB.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:9dcd47ae41344ad43c38add1cf495580deee1757db8deaf2603a80b41aff6591", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "StructD.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:a8df79c9f238d9faa6ea7126bca929ea45dd3b3bd25a895768ac836b442deab6", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "UnionC.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:5361b172cd0b769c020bc97e49112aaf8c14d1ed08db2559e89bac977fcd6504", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "UnionEmbedded.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:21e41ede4eb64de4a2549882a4a560ba0e8a1c3c9548b162ba2d95a13fac78d6", lastLine);
    }
}
