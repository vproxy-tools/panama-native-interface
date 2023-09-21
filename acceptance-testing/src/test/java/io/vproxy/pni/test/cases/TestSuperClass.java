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
    public void shaCheck() throws Exception {
        var s = Files.readAllLines(Path.of("src", "test", "c-generated", "io_vproxy_pni_test_ChildClass.h"));
        var lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:c314a6adc5941ff24d3947c6d465bb01f6594040c0c1d27e9176196903f368ba", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "c-generated", "io_vproxy_pni_test_ChildOfLargeAlign.h"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:286b46f4871906f27774a51d8f14347c9a43e42c065cfc1a0ddbfb9a3507457c", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "c-generated", "io_vproxy_pni_test_ChildOfPacked.h"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:b0247485f314465666c4cde3e242417c9550750259fccec2666a80c2ef173ae9", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "c-generated", "io_vproxy_pni_test_GrandChildClass.h"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:9f099e822b28e1a04e5b4547fa6fb40e58e37e8de25980f095c85cca6338aabb", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "ChildClass.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:aac7e923b188e00c834bab8c1a5620cd266eeb979be93510e2910b339467c49d", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "ChildOfLargeAlign.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:dc9a4eecda98eeee06803e7f282f7c2863ac6ff9e66dc396bb264eff455f4b3a", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "ChildOfPacked.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:62a5161bf181d4bf6e0108718ac85bbacbc65ed01680cd42da6b62b7dc0eaa2b", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "GrandChildClass.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:032e96fb654f82c00a0fd1151752d6029334e3f5d912fdcc3003759298040752", lastLine);
    }
}
