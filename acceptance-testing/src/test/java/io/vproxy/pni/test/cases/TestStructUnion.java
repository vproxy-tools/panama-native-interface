package io.vproxy.pni.test.cases;

import io.vproxy.pni.Allocator;
import io.vproxy.pni.PNIEnv;
import io.vproxy.pni.test.StructA;
import io.vproxy.pni.test.StructB;
import io.vproxy.pni.test.UnionC;
import io.vproxy.pni.test.UnionEmbedded;
import org.junit.BeforeClass;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TestStructUnion {
    @BeforeClass
    public static void beforeClass() {
        TestUtils.loadLib();
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
    public void unionCToString() {
        try (var allocator = Allocator.ofConfined()) {
            var u = new UnionC(allocator);
            u.setL(0xaabbccddeeffL);
            assertEquals("UnionC(\n" +
                         "    n => " + 0xccddeeff + ",\n" +
                         "    l => " + 0xaabbccddeeffL + "\n" +
                         ")@" + Long.toString(u.MEMORY.address(), 16),
                u.toString());
        }
    }

    @Test
    public void unionEmbeddedToString() {
        try (var allocator = Allocator.ofConfined()) {
            var u = new UnionEmbedded(allocator);
            var seg = allocator.allocate(8);
            u.setSeg(seg);
            assertEquals("UnionEmbedded(\n" +
                         "    n => " + (int) (seg.address() & 0xffffffffL) + ",\n" +
                         "    seg => []@" + Long.toString(seg.address(), 16) + "\n" +
                         ")@" + Long.toString(u.MEMORY.address(), 16),
                u.toString());
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
        assertEquals("// sha256:a406cddc175b7a570e7591b5920f8037374ea53502d938f36359783fa29c3486", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "StructB.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:fd103382d71a58bbe37aeb3f25ad40b94be08eb4f0d9eec8c6a696a25d6edcec", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "StructD.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:fbbcc64d3461a548b1137498d7fa0b7d2227a1e18a6ef30924b87744ce692332", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "UnionC.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:083ce5319737aab077a19da02751b75447bd40c67957f0a343380468f3c8d424", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "UnionEmbedded.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:26ade4e31c58f66c8d9c854e594697943af67b3265b7648c5fb62b8726f0330d", lastLine);
    }
}
