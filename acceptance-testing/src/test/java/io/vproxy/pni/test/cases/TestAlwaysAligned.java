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
        assertEquals("// sha256:f3f6dd9f31b01f6be1921b57a7e51401fe4a66c12a9fe46e68f8e04ddb2d4704", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "AlwaysAlignedField.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:fd43e55348ad756433cf92e28686765ddf35b13d67d4bd6ded7bb075954bbf77", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "AlwaysAlignedBase.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:774c5d0bd0f8e20df897bfe380888bab0deafb69eee8197392cf250efd954d78", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "AlwaysAlignedChild.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:ac0be64d8526583cde2734aa57f247be1c5f95b024a7add9a345586d14ff5f4c", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "AlwaysAlignedGrandChild.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:061df4e919dbddd50097daac623b9b56d8e600a6775a87f205af87e8c6b7e997", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "AlwaysAlignedSizeof.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:9d64eb9cff72dbf8f48199165fb9abb658f92cfbd631c63d4c38913c36bebb30", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "AlwaysAlignedUnion.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:a7417c780cb3927f0dc594b23936ca8a47e4dcea2a7d627b973579d251a103d2", lastLine);
    }
}
