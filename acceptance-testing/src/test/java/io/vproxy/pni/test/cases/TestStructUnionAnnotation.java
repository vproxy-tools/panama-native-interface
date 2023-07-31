package io.vproxy.pni.test.cases;

import io.vproxy.pni.Allocator;
import io.vproxy.pni.PNIEnv;
import io.vproxy.pni.test.StructM;
import io.vproxy.pni.test.StructN;
import io.vproxy.pni.test.UnionO;
import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.foreign.Arena;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.assertEquals;

public class TestStructUnionAnnotation {
    @BeforeClass
    public static void beforeClass() {
        System.loadLibrary("pnitest");
    }

    @Test
    public void structMnnn() {
        try (var arena = Arena.ofConfined()) {
            var allocator = Allocator.of(arena);
            var env = new PNIEnv(arena);

            var m = new StructM(allocator);
            var n = new StructN(allocator);
            n.setS((short) 12);
            n.setL(34L);
            m.nnn(env, n);

            assertEquals((short) 12, m.getN().getS());
            assertEquals(34L, m.getN().getL());
        }
    }

    @Test
    public void structN() {
        try (var arena = Arena.ofConfined()) {
            var allocator = Allocator.of(arena);
            var env = new PNIEnv(arena);

            var n = new StructN(allocator);
            n.setL(99L);
            long l = n.retrieveL(env);
            assertEquals(99L, l);

            n.setS((short) 33);
            short s = n.retrieveS(env);
            assertEquals((short) 33, s);
        }
    }

    @Test
    public void unionOAndP() {
        try (var arena = Arena.ofConfined()) {
            var allocator = Allocator.of(arena);
            var env = new PNIEnv(arena);

            var o = new UnionO(allocator);
            o.setS((short) 0xab);
            assertEquals((short) 0xab, o.getS());
            assertEquals(0xab, o.getI());

            assertEquals(0xab, o.getP().retrieveI(env));
            assertEquals(0xab, o.getP().getI());
            assertEquals(0xabL, o.getP().retrieveL(env));
            assertEquals(0xabL, o.getP().getL());

            o.setI(0xabcdef);
            assertEquals((short) 0xcdef, o.getS());
            assertEquals(0xabcdef, o.getI());
            assertEquals(0xabcdef, o.getP().getI());
            assertEquals(0xabcdefL, o.getP().getL());

            o.getP().setL(0xabcdef123456L);
            assertEquals((short) 0x3456, o.getS());
            assertEquals(0xef123456, o.getI());
            assertEquals(0xef123456, o.getP().getI());
            assertEquals(0xef123456, o.getP().retrieveI(env));
            assertEquals(0xabcdef123456L, o.getP().getL());
            assertEquals(0xabcdef123456L, o.getP().retrieveL(env));
        }
    }

    @Test
    public void shaCheck() throws Exception {
        var s = Files.readAllLines(Path.of("src", "test", "c-generated", "io_vproxy_pni_test_StructM.h"));
        var lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:99ded4e832b2f8f6767c3c1778d88bee4838a0bf67dbe3e0f2789789904a5cf3", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "c-generated", "io_vproxy_pni_test_StructN.h"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:ea5b1adfba3c09acd816652a641d522349fa04ee149ae6cbe5e9294199b2763d", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "c-generated", "io_vproxy_pni_test_UnionO.h"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:db4869c374b9facddd47dadb26c2c4bf6db5797d2aaff8d375039b9cd9f7f4b3", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "c-generated", "io_vproxy_pni_test_UnionP.h"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:d7396983e53a8b753c984b98743a8bfc99a5ed62001f7fafca7867e242133a73", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "StructM.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:0c269c5fdc65217924feaacdc9cd8771b88445e9b9a4fb3f99a42bd3e3ea7b6d", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "StructN.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:0768dbaba38d3b95fdc238b998837478f8b25ac235b3c924cceb3b073f4343ee", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "UnionO.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:be650304c965516c34866f53d89c702d55193a79079900e14fa9719396b98249", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "UnionP.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:b5cd8705b94934ab155ee60013f624509a41a8ac0c2308d9a31b94b14b389785", lastLine);
    }
}
