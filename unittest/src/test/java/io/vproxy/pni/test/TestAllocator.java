package io.vproxy.pni.test;

import io.vproxy.pni.Allocator;
import org.junit.Test;

import java.lang.foreign.MemorySegment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class TestAllocator {
    @Test
    public void unsafe() {
        try (var allocator = Allocator.ofUnsafe()) {
            var mem = allocator.allocate(0);
            assertEquals(0, mem.address());
            assertEquals(0, mem.byteSize());
            assertSame(MemorySegment.NULL, mem);
        }
    }
}
