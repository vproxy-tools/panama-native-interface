package io.vproxy.pni.test;

import io.vproxy.pni.impl.Utils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestUtils {
    @Test
    public void smallestPositivePowerOf2GE() {
        assertEquals(4096, Utils.smallestPositivePowerOf2GE(4096));
        assertEquals(4096, Utils.smallestPositivePowerOf2GE(4095));
        assertEquals(0, Utils.smallestPositivePowerOf2GE(-1));
        assertEquals(0, Utils.smallestPositivePowerOf2GE(0));
        assertEquals(1, Utils.smallestPositivePowerOf2GE(1));
        assertEquals(16384, Utils.smallestPositivePowerOf2GE(10000));
        assertEquals(0x40000000, Utils.smallestPositivePowerOf2GE(0x41234567));
    }
}
