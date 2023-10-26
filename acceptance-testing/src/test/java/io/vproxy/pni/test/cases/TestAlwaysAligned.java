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
            s.getArray().set(0, 22);
            s.getArray().set(1, 33);
            s.getArray().set(2, 55);

            assertEquals((byte) 1, s.getA());
            assertEquals((short) 2, s.getB());
            assertEquals(3L, s.getC());

            assertEquals(22, s.getArray().get(0));
            assertEquals(33, s.getArray().get(1));
            assertEquals(55, s.getArray().get(2));
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
        assertEquals("// sha256:a99ce092c9bb20a08f2e4d22692134ce7e88664ecf6da55bd5f03856ef9e66bf", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "AlwaysAlignedField.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:bce8fee6d0a06612d3f89362118a04b09d89656f9fdc3d5486bf3a536d33d741", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "AlwaysAlignedBase.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:37eaf66eac56387b7a7f770acbf464254aa90a318b8b3764714a7f2cbfc44767", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "AlwaysAlignedChild.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:ee732223b708bc454a7948024d36a1c9a01675dd4992c2bf5a95e2b52cae7593", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "AlwaysAlignedGrandChild.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:e2d0e077cebece7302f633202fb7197ec3e2b5a9a511ce9f52df94814dd949c6", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "AlwaysAlignedSizeof.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:03f53eb697cf302dbd78951756723e329a243e5b590a0f50236ca0c872e1db90", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "AlwaysAlignedUnion.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:46ac67402162c41b4df53e6cc7fc9c199802155e062b7a4dc63b8ecc7cf90596", lastLine);
    }
}
