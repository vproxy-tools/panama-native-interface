package io.vproxy.pni.test.cases;

import io.vproxy.pni.Allocator;
import io.vproxy.pni.test.*;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.assertEquals;

public class TestAlwaysAligned {
    @Test
    public void alwaysAlignedClass() {
        try (var allocator = Allocator.ofConfined()) {
            var s = new AlwaysAlignedClass(allocator);
            s.setA((byte) 1);
            s.setB((short) 2);
            s.setC(3L);

            assertEquals((byte) 1, s.getA());
            assertEquals((short) 2, s.getB());
            assertEquals(3L, s.getC());
        }
    }

    @Test
    public void alwaysAlignedField() {
        try (var allocator = Allocator.ofConfined()) {
            var s = new AlwaysAlignedField(allocator);
            s.setA((byte) 1);
            s.setB((short) 2);
            s.setC((byte) 3);
            s.setD(4);
            s.setE(5L);

            assertEquals((byte) 1, s.getA());
            assertEquals((short) 2, s.getB());
            assertEquals((byte) 3, s.getC());
            assertEquals(4, s.getD());
            assertEquals(5L, s.getE());
        }
    }

    @Test
    public void inherit() {
        try (var allocator = Allocator.ofConfined()) {
            var s = new AlwaysAlignedGrandChild(allocator);
            s.setA((byte) 1);
            s.setB((short) 2);
            s.setC(3L);

            assertEquals((byte) 1, s.getA());
            assertEquals((short) 2, s.getB());
            assertEquals(3L, s.getC());
        }
    }

    @Test
    public void sizeof() {
        try (var allocator = Allocator.ofConfined()) {
            var s = new AlwaysAlignedSizeof(allocator);
            s.setB((byte) 1);
            s.setS((short) 2);
            assertEquals(16, s.MEMORY.byteSize());
            assertEquals(16, s.MEMORY.byteSize());

            assertEquals((byte) 1, s.getB());
            assertEquals((short) 2, s.getS());
        }
    }

    @Test
    public void union() {
        try (var allocator = Allocator.ofConfined()) {
            var s = new AlwaysAlignedUnion(allocator);
            s.setC(0x123456789abcL);

            assertEquals((short) 0x9abc, s.getA());
            assertEquals(0x56789abc, s.getB());
            assertEquals(0x123456789abcL, s.getC());
        }
    }

    @Test
    public void shaCheck() throws Exception {
        var s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "AlwaysAlignedClass.java"));
        var lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:ba7624fbadceb2815d7e8d3d5a15d0649419692f7380f9210d40d8f7a7c7baf3", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "AlwaysAlignedField.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:0f6711c9e055150909998cde88ebee8fc3879694a0ffc5497a28defa44925dbb", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "AlwaysAlignedBase.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:2aa3dfb99409a5e9dc8bd7c91113c6109cfe036aa05ce07b85692ebbe58515ea", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "AlwaysAlignedChild.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:6f1271afc9d9d4723bd66da46d6db2922fb340c4e2bf7ee0215b977038fe7286", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "AlwaysAlignedGrandChild.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:3702df61aa153971028c42e81f943d7591b9ec28bff4b56554a086b62bd86abb", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "AlwaysAlignedSizeof.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:92ee819177e71161a8357acd7c31cc956f5db09c4e76250141f03d788879b990", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "AlwaysAlignedUnion.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:16e4f48bdf0526e3c6a10fd9c21a82cbec653223e3b76e74dc8d9256f87c905d", lastLine);
    }
}
