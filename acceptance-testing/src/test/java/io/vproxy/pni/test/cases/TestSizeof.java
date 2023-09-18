package io.vproxy.pni.test.cases;

import io.vproxy.pni.Allocator;
import io.vproxy.pni.test.SizeofEmbed;
import io.vproxy.pni.test.SizeofStruct;
import io.vproxy.pni.test.SizeofStructExpr;
import io.vproxy.pni.test.SizeofUnion;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TestSizeof {
    @Test
    public void sizeofStruct() {
        try (var allocator = Allocator.ofConfined()) {
            var s = new SizeofStruct(allocator);
            assertEquals(16, s.MEMORY.byteSize());
            assertEquals(16, SizeofStruct.LAYOUT.byteSize());
        }
    }

    @Test
    public void sizeofStructExpr() {
        try (var allocator = Allocator.ofConfined()) {
            var s = new SizeofStructExpr(allocator);
            assertEquals(16, s.MEMORY.byteSize());
            assertEquals(16, SizeofStructExpr.LAYOUT.byteSize());
        }
    }

    @Test
    public void sizeofUnion() {
        try (var allocator = Allocator.ofConfined()) {
            var u = new SizeofUnion(allocator);
            assertEquals(24, u.MEMORY.byteSize());
            assertEquals(24, SizeofUnion.LAYOUT.byteSize());

            var s = u.getSt();
            assertEquals(16, s.MEMORY.byteSize());
        }
    }

    @Test
    public void sizeofEmbed() {
        try (var allocator = Allocator.ofConfined()) {
            var s = new SizeofEmbed(allocator);
            assertEquals(32, s.MEMORY.byteSize());
            assertEquals(32, SizeofEmbed.LAYOUT.byteSize());

            var u = s.getUn();
            assertNull(u);
            var st = s.getSt();
            assertEquals(16, st.MEMORY.byteSize());
        }
    }

    @Test
    public void checksum() throws Exception {
        var s = Files.readAllLines(Path.of("src", "test", "c-generated", "io_vproxy_pni_test_SizeofEmbed.extra.c"));
        var lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:eb5c12b41b7f3d3e0f3e73b0c3e4a7cdb2ac35210722e868edef6a5362671019", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "c-generated", "io_vproxy_pni_test_SizeofEmbed.h"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:bc4ce08bbb41639a23eee49368c3cb4c0c960e9f99591540afb88e835276e04e", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "c-generated", "io_vproxy_pni_test_SizeofStruct.extra.c"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:0d15a4c6f4f9b33c82f0d14e3b73da7b900646a8cafe7ab47ca8b260a4f4ef30", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "c-generated", "io_vproxy_pni_test_SizeofStruct.h"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:38f973ecdd6676804af090b1864de1b6346537a349c83dca1783675ba0dbcb47", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "c-generated", "io_vproxy_pni_test_SizeofStructExpr.extra.c"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:bea3677de8eb22f36044cc33447cf8f751d8e84ac8b93aac3db72e908ced74e1", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "c-generated", "io_vproxy_pni_test_SizeofStructExpr.h"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:82b09f50f820ad9b5303bbd4227934eb9601a9642589ce15c6f5abfba8fcaa78", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "c-generated", "io_vproxy_pni_test_SizeofUnion.extra.c"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:01fbe1d0d0ad9b97cdc1c6d54c934d4fdd36aaed272ec7125a3586dc4f8b84df", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "c-generated", "io_vproxy_pni_test_SizeofUnion.h"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:42c0a3844c82e5270fcfb11f32c3fd2082be680938be2fab5c4176e67aeb94c4", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "SizeofEmbed.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:f9796317eff899552f86ca6eec616c338f3736ce2e208031049e6745ca8fc381", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "SizeofStruct.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:6a7bff6ea714d9eb41878d3c882588a0d302939c3c970ec878b11e529e52f2d7", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "SizeofStructExpr.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:6083f2f86017c51e9fed5d1799229dd49644450580d5b130bfea5b4f551cb77a", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "SizeofUnion.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:285150b07a2daac726933775a734145ebb6de6bbd4f896f404657c7d604dd618", lastLine);
    }
}
