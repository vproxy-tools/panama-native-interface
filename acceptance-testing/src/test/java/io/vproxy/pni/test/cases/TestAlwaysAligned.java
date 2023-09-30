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
        assertEquals("// sha256:7fe05c0bf0d106bc75409f22dfe1abd78f5f449527b50ea09b9d5982831a52d9", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "AlwaysAlignedField.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:ea39e2f75aaaddb943fdbb1d9732cd5bb2aa0191efbbd9c90b7a5d9bac33dc94", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "AlwaysAlignedBase.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:52123d7acb2b5d8411669b3af58485700d62b38b6146f887eade54ed24ca4b30", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "AlwaysAlignedChild.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:2dbca2418c28770ece06f099e063bba1959ceeb426517dbdee77e18ad69990d8", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "AlwaysAlignedGrandChild.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:5c993610ea9ae279ddac2cab014384dcbeda7bc4baae78461d7d704e2f8275d3", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "AlwaysAlignedSizeof.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:6b8a3ea09e4bd9aa64812c273db9283f992499c1dfccba994ee89f65a5c5d664", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "AlwaysAlignedUnion.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:26f180a420e9c56d4237cc2207c660bd0ae1f6d4e5c2413996850d5c13ec77c3", lastLine);
    }
}
