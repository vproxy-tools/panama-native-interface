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
        assertEquals("// sha256:36d08a78f28fd4ec02755cb27b09aa79749cd4f6bb7573196e12dfbe03c29bcb", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "SizeofStruct.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:25809e0478bd472fd09e099b8b044ddce8054b219ecbd2e211458bbc2157a0c8", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "SizeofStructExpr.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:e1573fb4af20cb1d3865774c506f758b8d757156384870d2e3a46c5bc30a9ae6", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "SizeofUnion.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:594ed30bf19a06827f4c15d68da7ae67e7af6d84bf13592044a8e63bd5dd356b", lastLine);
    }
}
