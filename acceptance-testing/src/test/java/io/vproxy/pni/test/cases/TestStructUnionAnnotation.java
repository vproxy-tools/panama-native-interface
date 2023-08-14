package io.vproxy.pni.test.cases;

import io.vproxy.pni.Allocator;
import io.vproxy.pni.PNIEnv;
import io.vproxy.pni.test.StructM;
import io.vproxy.pni.test.StructN;
import io.vproxy.pni.test.UnionO;
import org.junit.BeforeClass;
import org.junit.Test;

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
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);

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
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);

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
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);

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
        assertEquals("// sha256:dcd1313a2eea604d3ac338770d30a9b6f840fa5a218f9bfdfaed69035b9a49e2", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "c-generated", "io_vproxy_pni_test_StructN.h"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:bbaad5de0420bd4d169cacd53c9dc1708740e70698ec795de4d2af04f3d5b869", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "c-generated", "io_vproxy_pni_test_UnionO.h"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:1ecf3a9c38997ca3f45bdfef86f15b4fcc0771d25cfcd32e725ef3fb0849b20c", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "c-generated", "io_vproxy_pni_test_UnionP.h"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:1811337a7a2090f5a3e957f3d637b8f34b3d3cbbf97da65474477d64ba045e46", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "StructM.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:8fcc70818fd485d4e4ace28a625b0617a82153c4896bd6915643e36db86781dd", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "StructN.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:5624d5fd6f6b7568b713daf3f2cc04324105daf4bc159c3779fbdce52353270e", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "UnionO.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:976e7f3079c08b53685a831fb48b12caf0c667c1dd47a5ae064e223f6b02e562", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "UnionP.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:063c4150369c39c859e26233b33776027c365dfd0a1db8ea7cea0dc4919182fb", lastLine);
    }
}
