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
        assertEquals("// sha256:a1dfd58c690e6b230b494dbfb071eec07d84ddfc86ee85d2d3eb4d7bb35919c8", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "AlwaysAlignedField.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:2a3cffe1f12925e9092fab9b8de3923858f308c8cda3f0463194805f5b3dfc2d", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "AlwaysAlignedBase.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:eeb74d90cf6d6887b2191edca28909cea58b185d5d369925fb00cbd07f6f3129", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "AlwaysAlignedChild.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:30ba614da18aeb07da0ea316df001a6f92f88b6406de31cb24e1cf3c32acf871", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "AlwaysAlignedGrandChild.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:6b57dd1b9892017a026e04179a76c03c13cb962e1eff0c60f62d4f3395ef65a4", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "AlwaysAlignedSizeof.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:7252fb1229d5b887fa22025d47dbf31acbd1c8aa36dbbfd23447ceea3705a9db", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "AlwaysAlignedUnion.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:1655e0fc8c6183d544658f82efaef9c5b630e030d9ff0fccbcadd47a413f4ccb", lastLine);
    }
}
