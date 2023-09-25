package io.vproxy.pni.test.cases;

import io.vproxy.pni.Allocator;
import io.vproxy.pni.test.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestAlign {
    @Test
    public void alignClass() {
        try (var allocator = Allocator.ofConfined()) {
            var s = new AlignClass(allocator);
            s.setA((byte) 12);
            s.setB((short) 23);
            s.setC(34);

            assertEquals((byte) 12, s.aaaa());
            assertEquals((short) 23, s.bbbb());
            assertEquals(34, s.cccc());

            assertEquals(AlignClass.LAYOUT.byteSize(), s.size());
        }
    }

    @Test
    public void alignField() {
        try (var allocator = Allocator.ofConfined()) {
            var s = new AlignField(allocator);
            s.setA((byte) 10);
            s.setB((byte) 20);
            s.setC(30);

            assertEquals((byte) 10, s.getA());
            assertEquals((byte) 20, s.getB());
            assertEquals(30, s.getC());

            assertEquals(AlignField.LAYOUT.byteSize(), s.size());
        }
    }

    @Test
    public void alignField2() {
        try (var allocator = Allocator.ofConfined()) {
            var s = new AlignField2(allocator);
            s.setA((byte) 10);
            s.setB((byte) 20);
            s.setC(30);

            assertEquals((byte) 10, s.getA());
            assertEquals((byte) 20, s.getB());
            assertEquals(30, s.getC());

            assertEquals(AlignField2.LAYOUT.byteSize(), s.size());
        }
    }

    @Test
    public void alignField3() {
        try (var allocator = Allocator.ofConfined()) {
            var s = new AlignField3(allocator);
            s.setA((short) 10);
            s.setB(20);
            s.setC(30);

            assertEquals((short) 10, s.getA());
            assertEquals(20, s.getB());
            assertEquals(30, s.getC());

            assertEquals(AlignField3.LAYOUT.byteSize(), s.size());
        }
    }

    @Test
    public void alignBaseChild() {
        try (var allocator = Allocator.ofConfined()) {
            var s = new AlignChildClass(allocator);
            s.setA((short) 10);
            s.setB(20);
            s.setC(30);

            assertEquals((short) 10, s.getA());
            assertEquals(20, s.getB());
            assertEquals(30, s.getC());

            assertEquals(AlignBaseClass.LAYOUT.byteSize(), s.size0());
            assertEquals(AlignChildClass.LAYOUT.byteSize(), s.size());
        }
    }
}
