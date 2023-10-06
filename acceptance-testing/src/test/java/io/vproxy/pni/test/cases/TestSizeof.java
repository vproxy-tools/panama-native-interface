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
        assertEquals("// sha256:e212fe7d4061276eb16446cecbcfcbed860928c9d6c2ec88bb961176bd5af0cf", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "c-generated", "io_vproxy_pni_test_SizeofStruct.extra.c"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:0d15a4c6f4f9b33c82f0d14e3b73da7b900646a8cafe7ab47ca8b260a4f4ef30", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "c-generated", "io_vproxy_pni_test_SizeofStruct.h"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:7d0fcfbd97f47a488c8dd8cf244dabd450ea1fb6ca876db166fbd7ce98fb3087", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "c-generated", "io_vproxy_pni_test_SizeofStructExpr.extra.c"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:bea3677de8eb22f36044cc33447cf8f751d8e84ac8b93aac3db72e908ced74e1", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "c-generated", "io_vproxy_pni_test_SizeofStructExpr.h"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:6bba9f20403b346456a31ef26d9035117181a4e08b8a6403ec3722dd4cfde78a", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "c-generated", "io_vproxy_pni_test_SizeofUnion.extra.c"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:01fbe1d0d0ad9b97cdc1c6d54c934d4fdd36aaed272ec7125a3586dc4f8b84df", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "c-generated", "io_vproxy_pni_test_SizeofUnion.h"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:bf2bff254dd2de64affb49f03e1b9e9cdec09bd438e95e0d58307d59a6fc48d3", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "SizeofEmbed.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:03113e275bf388fcb1841092a7c37af295a526e307933411a8ec501f423bbe2f", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "SizeofStruct.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:beb0795b6d7f91740981037433c98b218eec723e46f1f5827df516ea449f27bc", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "SizeofStructExpr.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:bf592b6a526c6d1298f040a1209891045f08d0a24ed570b201ec40e94b73a671", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "SizeofUnion.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:ab03785fbd3a4f4c2bb63632466a5100568978b1999cd25275de9a3d04e4dbec", lastLine);
    }
}
