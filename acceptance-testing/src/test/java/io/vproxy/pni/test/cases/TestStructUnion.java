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
        assertEquals("// sha256:7263a1d79d96b2a94da93c92ed77743c8545d0a89fabcfbf68d493f14100f3bc", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "c-generated", "io_vproxy_pni_test_StructB.h"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:c8fc711454d178033710be503d062c6a621bd25504c93d17f8cb889fca234337", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "c-generated", "io_vproxy_pni_test_StructD.h"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:19fe59852fc8909c5dc6eb433db41060cbf0f4070f6e6c7e987a767fab234fc4", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "c-generated", "io_vproxy_pni_test_UnionC.h"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:f1c27eafe30ca0d2e16849f8767d4c8014336124325c92098e4ad4ef12a3ca0d", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "c-generated", "io_vproxy_pni_test_UnionEmbedded.h"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:f775a97a09212a249e2877d2867b916518f1073766f15a7ff9458b3588c1eeaf", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "StructA.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:a63812ebab00a0f96fab0114738bb7f7baeee71d83cd8fa6590cbbe0b4b5ad29", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "StructB.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:d824f32a54cb5913a933a78e18a4823aab2cd5bee27d2bcd1040b7562a1f428c", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "StructD.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:40e5ad045a8ea56d9df8f24db4df4816386ebf2ac15e9d1344573dd12e17e9e5", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "UnionC.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:3ce56c9a9269518d070b891889256c40908bc987c1d280364398ca61570d2362", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "UnionEmbedded.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:a107104276e2574774d194edba1f8651e9a5cf46cac24d2eaee6f716becde19b", lastLine);
    }
}
