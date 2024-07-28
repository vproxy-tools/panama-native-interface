package io.vproxy.pni.test.cases;

import io.vproxy.pni.Allocator;
import io.vproxy.pni.PNIEnv;
import io.vproxy.pni.test.*;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.assertEquals;

public class TestSuperClass {
    @Test
    public void childClass() {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);
            var child = new ChildClass(allocator);
            child.aaa(env, (byte) 12);
            child.xxx(env, (short) 23);

            assertEquals((byte) 12, child.getA());
            assertEquals((short) 23, child.getX());

            var base = new BaseClass(child.MEMORY);
            assertEquals((byte) 12, base.getA());
        }
    }

    @Test
    public void grandChildClass() {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);
            var grand = new GrandChildClass(allocator);
            grand.aaa(env, (byte) 34);
            grand.xxx(env, (short) 45);
            grand.yyy(env, 56L);

            assertEquals((byte) 34, grand.getA());
            assertEquals((short) 45, grand.getX());
            assertEquals(56L, grand.getY());

            var child = new ChildClass(grand.MEMORY);
            assertEquals((byte) 34, child.getA());
            assertEquals((short) 45, child.getX());

            var base = new BaseClass(child.MEMORY);
            assertEquals((byte) 34, base.getA());
        }
    }

    @Test
    public void childOfPacked() {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);
            var child = new ChildOfPacked(allocator);
            child.aaa(env, (byte) 78);
            child.bbb(env, (short) 90);
            child.xxx(env, 1011);
            var o = new ObjectStruct(allocator);
            o.setLenStr("hello");
            child.ooo(env, o);

            assertEquals((byte) 78, child.getA());
            assertEquals((short) 90, child.getB());
            assertEquals(1011, child.getX());
            assertEquals("hello", child.getO().getLenStr());

            var base = new PackedBaseClass(child.MEMORY);
            assertEquals((byte) 78, base.getA());
            assertEquals((short) 90, base.getB());
        }
    }

    @Test
    public void childOfLargeAlign() {
        try (var allocator = Allocator.ofConfined()) {
            var child = new ChildOfLargeAlign(allocator);
            assertEquals(16, child.MEMORY.byteSize());
            assertEquals(16, ChildOfLargeAlign.LAYOUT.byteSize());
        }
    }

    @Test
    public void grandChildClassToString() {
        try (var allocator = Allocator.ofConfined()) {
            var gc = new GrandChildClass(allocator);
            gc.setA((byte) 12);
            gc.setX((short) 345);
            gc.setY(6789);
            assertEquals("GrandChildClass{\n" +
                         "    SUPER => ChildClass{\n" +
                         "        SUPER => BaseClass{\n" +
                         "            a => 12\n" +
                         "        }@" + Long.toString(gc.MEMORY.address(), 16) + ",\n" +
                         "        x => 345\n" +
                         "    }@" + Long.toString(gc.MEMORY.address(), 16) + ",\n" +
                         "    y => 6789\n" +
                         "}@" + Long.toString(gc.MEMORY.address(), 16),
                gc.toString());
        }
    }

    @Test
    public void shaCheck() throws Exception {
        var s = Files.readAllLines(Path.of("src", "test", "c-generated", "io_vproxy_pni_test_ChildClass.h"));
        var lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:581ea055936898f03845462c7d9796fa4eb06d42163259ff64c55ab2cf27b28e", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "c-generated", "io_vproxy_pni_test_ChildOfLargeAlign.h"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:1a012921bca9c4eea3de512ad93848f227111048b2a72c4636bb7aa623f5e825", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "c-generated", "io_vproxy_pni_test_ChildOfPacked.h"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:a1a68cd535af54bcb81d2d04a8f45c671af93e45a7ac13862ece565747eb3d8b", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "c-generated", "io_vproxy_pni_test_GrandChildClass.h"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:fdc5f7b3a18073047f9e41e1ebde280ef75dcedfed8ff8e5949518464b0aa340", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "c-generated", "io_vproxy_pni_test_EmptyChild.h"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:f091e8889ea77137a08bc2c3cee020d7266ca0ab9442c63f233cfb7c21508d8d", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "ChildClass.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:567c380f81071ff2578cfeb6b6db683f8890e42fc5a354af4f3622ba02ae42ab", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "ChildOfLargeAlign.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:cba63f2d45034f348ee6455fb993d3a2122329311601311845a193e6f30e5726", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "ChildOfPacked.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:3f1e399e2b57f02baa28c66f893a9687513d42c7358354c3569255805db576fa", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "GrandChildClass.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:46000b07ebed3f40aa2754709c11aefaadfce3cfd0e49d3dd9e276c66b71d42f", lastLine);
    }
}
