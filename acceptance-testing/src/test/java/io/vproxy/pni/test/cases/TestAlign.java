package io.vproxy.pni.test.cases;

import io.vproxy.pni.Allocator;
import io.vproxy.pni.test.AlignClass;
import io.vproxy.pni.test.AlignField;
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

            var arr = new AlignClass.Array(allocator, 2);
            arr.get(0).setA((byte) 10);
            arr.get(0).setB((short) 11);
            arr.get(0).setC(12);
            arr.get(1).setA((byte) 20);
            arr.get(1).setB((short) 21);
            arr.get(1).setC(22);

            assertEquals((byte) 10, arr.get(0).aaaa());
            assertEquals((short) 11, arr.get(0).bbbb());
            assertEquals(12, arr.get(0).cccc());
            assertEquals((byte) 20, arr.get(1).aaaa());
            assertEquals((short) 21, arr.get(1).bbbb());
            assertEquals(22, arr.get(1).cccc());
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

            var arr = new AlignField.Array(allocator, 2);
            arr.get(0).setA((byte) 11);
            arr.get(0).setB((byte) 12);
            arr.get(0).setC(13);
            arr.get(1).setA((byte) 21);
            arr.get(1).setB((byte) 22);
            arr.get(1).setC(23);

            assertEquals((byte) 11, arr.get(0).aaaa());
            assertEquals((byte) 12, arr.get(0).bbbb());
            assertEquals(13, arr.get(0).cccc());
            assertEquals((byte) 21, arr.get(1).aaaa());
            assertEquals((byte) 22, arr.get(1).bbbb());
            assertEquals(23, arr.get(1).cccc());
        }
    }
}
