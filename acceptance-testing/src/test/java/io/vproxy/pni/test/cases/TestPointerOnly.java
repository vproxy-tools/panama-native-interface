package io.vproxy.pni.test.cases;

import io.vproxy.pni.Allocator;
import io.vproxy.pni.test.PointerOnlyStruct;
import io.vproxy.pni.test.PointerOnlyStructWithLen;
import org.junit.Test;

import java.lang.foreign.ValueLayout;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.assertEquals;

public class TestPointerOnly {
    @Test
    public void pointerOnly() {
        try (var allocator = Allocator.ofConfined()) {
            var p = new PointerOnlyStruct(allocator.allocate(8));
            p.MEMORY.reinterpret(8).set(ValueLayout.JAVA_LONG, 0, 112233);

            var l = p.retrieve();
            assertEquals(112233, l);
        }
    }

    @Test
    public void pointerOnlyWithLen() {
        try (var allocator = Allocator.ofConfined()) {
            var p = new PointerOnlyStructWithLen(allocator);
            assertEquals(32, p.MEMORY.byteSize());
        }
    }

    @Test
    public void shaCheck() throws Exception {
        var s = Files.readAllLines(Path.of("src", "test", "c-generated", "io_vproxy_pni_test_PointerOnlyStruct.h"));
        var lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:f8cfe7cc7a0cd5b1b2e608b8cfd0dfd6459c0033728bb2cad72b4a6dd7c1fa80", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "c-generated", "io_vproxy_pni_test_PointerOnlyStruct.impl.h"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:7adc63ad409317dd1dba66439ba2442bfd4a2e4fb4cb1e5847e65c848af83ee2", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "c-generated", "io_vproxy_pni_test_PointerOnlyStructWithLen.extra.c"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:b95e0410f90667e218489fe80486b0f9efa1daf8ae34ad19079bdfd8d24ca956", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "c-generated", "io_vproxy_pni_test_PointerOnlyStructWithLen.h"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:b1de7af3b3ad8d35200b2af436bec581b33c939357a67c90d5b00db8ac3f3ff6", lastLine);
    }
}
