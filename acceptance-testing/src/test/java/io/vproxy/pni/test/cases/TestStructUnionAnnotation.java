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
        TestUtils.loadLib();
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
    public void unionOToString() {
        try (var allocator = Allocator.ofConfined()) {
            var u = new UnionO(allocator);
            u.getP().setL(0xaabbccddeeL);
            var addr = Long.toString(u.MEMORY.address(), 16);
            assertEquals("UnionO(\n" +
                         "    s => " + ((short) 0xddee) + ",\n" +
                         "    i => " + 0xbbccddee + ",\n" +
                         "    p => UnionP(\n" +
                         "        i => " + 0xbbccddee + ",\n" +
                         "        l => " + 0xaabbccddeeL + "\n" +
                         "    )@" + addr + "\n" +
                         ")@" + addr, u.toString());
        }
    }

    @Test
    public void shaCheck() throws Exception {
        var s = Files.readAllLines(Path.of("src", "test", "c-generated", "io_vproxy_pni_test_StructM.h"));
        var lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:6a429fda389afeea660424f1c54b3d74848457dd4b0dca7d85503cd78196149f", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "c-generated", "io_vproxy_pni_test_StructN.h"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:9bc612ec2ead2ddc51980351f70cd2426cc7d1cecccbed62d61c753bf68ea46f", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "c-generated", "io_vproxy_pni_test_UnionO.h"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:1743a25ed3e78afb9b2e4382a0ccd59dc39462033a6260f112d66279ad72b096", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "c-generated", "io_vproxy_pni_test_UnionP.h"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:be213ab6409b21777960762d0f954d96ce94541fe877f67bd3f4daa0d8ed5aeb", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "StructM.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:61efa179b924ad5fe5969428724eed87e3231931cafc1610dec1d097e29f3e26", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "StructN.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:d4b7b7370f8fdfaff9458c60455709b52dbd620f0070645803795ff586ab3588", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "UnionO.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:c39fb05ada16e7aa418f7a9f96345ed3a58f17d437a6f2eba3f2a892e1fdbdfd", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "UnionP.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:cacc9c11204a6247dfd167326bfe5bdf1dc800eacdecafc1da6e37f4acce3be3", lastLine);
    }
}
