package io.vproxy.pni.test.cases;

import io.vproxy.pni.Allocator;
import io.vproxy.pni.PNIEnv;
import io.vproxy.pni.test.ObjectStruct;
import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.*;

public class TestObjectStruct {
    @BeforeClass
    public static void beforeClass() {
        System.loadLibrary("pnitest");
    }

    @Test
    public void func1() {
        try (var arena = Arena.ofConfined()) {
            var env = new PNIEnv(arena);
            var s = new ObjectStruct(Allocator.of(arena));

            var seg = arena.allocate(16);
            var buf = ByteBuffer.allocateDirect(16);
            s.func1(env, "abc", "def", seg, buf);

            assertEquals("abc", s.getStr());
            assertEquals("def", s.getLenStr());
            assertEquals(seg.address(), s.getSeg().address());
            assertEquals(buf.capacity(), s.getBuf().capacity());
            assertEquals(MemorySegment.ofBuffer(buf).address(), MemorySegment.ofBuffer(s.getBuf()).address());
        }
    }

    @Test
    public void checkNull() {
        try (var arena = Arena.ofConfined()) {
            var s = new ObjectStruct(Allocator.of(arena));

            assertNull(s.getStr());
            assertEquals("", s.getLenStr());
            assertNull(s.getSeg());
            assertNull(s.getBuf());
        }
    }

    @Test
    public void retrieve() {
        try (var arena = Arena.ofConfined()) {
            var env = new PNIEnv(arena);
            var allocator = Allocator.of(arena);
            var s = new ObjectStruct(allocator);

            assertFalse(s.checkPointerSetToNonNull(env));

            s.setStr("abc", allocator);
            assertEquals("abc", s.retrieveStr(env));

            var strMem = allocator.allocate(16);
            strMem.setUtf8String(0, "aaabbb");
            s.setStr(strMem);
            assertEquals("aaabbb", s.retrieveStr(env));

            s.setLenStr("def");
            assertEquals("def", s.retrieveLenStr(env));

            var seg = allocator.allocate(16);
            s.setSeg(seg);
            assertEquals(seg, s.retrieveSeg(env));

            var buf = ByteBuffer.allocateDirect(16);
            s.setBuf(buf);
            var retrievedBuf = s.retrieveBuf(env);
            assertEquals(buf.capacity(), retrievedBuf.capacity());
            assertEquals(MemorySegment.ofBuffer(buf).address(),
                MemorySegment.ofBuffer(retrievedBuf).address());

            assertTrue(s.checkPointerSetToNonNull(env));

            s.setStr(null);
            s.setSeg(null);
            s.setBuf(null);

            assertTrue(s.checkPointerSetToNull(env));
        }
    }

    @Test
    public void shaCheck() throws Exception {
        var s = Files.readAllLines(Path.of("src", "test", "c-generated", "io_vproxy_pni_test_ObjectStruct.h"));
        var lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:2cb32861a8d45a7aa4cc927dbfb371e6f451764d7f0892f3c9afa2be143c71e6", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "ObjectStruct.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:65cb144ddd8077763c4ae8101edc82422e6e62b8387cd8c84ff0690241c7b3c2", lastLine);
    }
}
