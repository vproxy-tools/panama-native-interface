package io.vproxy.pni.test.cases;

import io.vproxy.pni.Allocator;
import io.vproxy.pni.PNIEnv;
import io.vproxy.pni.test.AllowHeapAccess;
import org.junit.AssumptionViolatedException;
import org.junit.Test;

import java.lang.foreign.MemorySegment;

import static org.junit.Assert.*;

public class TestAllowHeapAccess {
    @Test
    public void allowHeapAccess() {
        if (Runtime.version().version().getFirst() <= 21) {
            throw new AssumptionViolatedException("JDK 21 doesn't support allow-heap-access");
        }
        if (TestUtils.skipCase()) {
            return;
        }
        var bytes = new byte[]{
            0x12, (byte) 0xab, (byte) 0xcd, (byte) 0xef
        };
        var mem = MemorySegment.ofArray(bytes);
        assertFalse(mem.isNative());
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);
            int value = AllowHeapAccess.get().intValue(env, mem);
            assertEquals(0xefcdab12, value);
        }
    }

    @Test
    public void allowHeapAccessCritical() {
        if (Runtime.version().version().getFirst() <= 21) {
            throw new AssumptionViolatedException("JDK 21 doesn't support allow-heap-access");
        }
        if (TestUtils.skipCase()) {
            return;
        }
        var bytes = new byte[]{
            0x12, (byte) 0xab, (byte) 0xcd, (byte) 0xef
        };
        var mem = MemorySegment.ofArray(bytes);
        assertFalse(mem.isNative());
        int value = AllowHeapAccess.get().intValueCritical(mem);
        assertEquals(0xefcdab12, value);
    }
}
