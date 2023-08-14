package io.vproxy.pni.test.cases;

import io.vproxy.pni.Allocator;
import io.vproxy.pni.PNIEnv;
import io.vproxy.pni.PNIString;
import io.vproxy.pni.test.ObjectStruct;
import org.junit.BeforeClass;
import org.junit.Test;

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
        func1(0);
    }

    @Test
    public void func1Critical() {
        func1(1);
    }

    private void func1(int round) {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);
            var s = new ObjectStruct(allocator);

            var seg = allocator.allocate(16);
            var buf = ByteBuffer.allocateDirect(16);
            if (round == 0) {
                s.func1(env, new PNIString(allocator, "abc"), new PNIString(allocator, "def"), seg, buf);
            } else {
                s.func1Critical(new PNIString(allocator, "abc"), new PNIString(allocator, "def"), seg, buf);
            }

            assertEquals("abc", s.getStr().toString());
            assertEquals("def", s.getLenStr());
            assertEquals(seg.address(), s.getSeg().address());
            assertEquals(buf.capacity(), s.getBuf().capacity());
            assertEquals(MemorySegment.ofBuffer(buf).address(), MemorySegment.ofBuffer(s.getBuf()).address());
        }
    }

    @Test
    public void checkNull() {
        try (var allocator = Allocator.ofConfined()) {
            var s = new ObjectStruct(allocator);

            assertNull(s.getStr());
            assertEquals("", s.getLenStr());
            assertNull(s.getSeg());
            assertNull(s.getBuf());
        }
    }

    @Test
    public void retrieve() {
        retrieve(0);
    }

    @Test
    public void retrieveCritical() {
        retrieve(1);
    }

    private void retrieve(int round) {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);
            var s = new ObjectStruct(allocator);

            if (round == 0) {
                assertFalse(s.checkPointerSetToNonNull(env));
            } else {
                assertFalse(s.checkPointerSetToNonNullCritical());
            }

            s.setStr("abc", allocator);
            if (round == 0) {
                assertEquals("abc", s.retrieveStr(env).toString());
            } else {
                assertEquals("abc", s.retrieveStrCritical().toString());
            }

            var strMem = allocator.allocate(16);
            strMem.setUtf8String(0, "aaabbb");
            s.setStr(new PNIString(strMem));
            if (round == 0) {
                assertEquals("aaabbb", s.retrieveStr(env).toString());
            } else {
                assertEquals("aaabbb", s.retrieveStrCritical().toString());
            }

            s.setLenStr("def");
            if (round == 0) {
                assertEquals("def", s.retrieveLenStr(env).toString());
            } else {
                assertEquals("def", s.retrieveLenStrCritical().toString());
            }

            var seg = allocator.allocate(16);
            s.setSeg(seg);
            if (round == 0) {
                assertEquals(seg, s.retrieveSeg(env));
            } else {
                assertEquals(seg, s.retrieveSegCritical());
            }

            var buf = ByteBuffer.allocateDirect(16);
            s.setBuf(buf);
            var retrievedBuf = round == 0 ? s.retrieveBuf(env) : s.retrieveBufCritical();
            assertEquals(buf.capacity(), retrievedBuf.capacity());
            assertEquals(MemorySegment.ofBuffer(buf).address(),
                MemorySegment.ofBuffer(retrievedBuf).address());

            if (round == 0) {
                assertTrue(s.checkPointerSetToNonNull(env));
            } else {
                assertTrue(s.checkPointerSetToNonNullCritical());
            }

            s.setStr(null);
            s.setSeg(null);
            s.setBuf(null);

            if (round == 0) {
                assertTrue(s.checkPointerSetToNull(env));
            } else {
                assertTrue(s.checkPointerSetToNullCritical());
            }
        }
    }

    @Test
    public void shaCheck() throws Exception {
        var s = Files.readAllLines(Path.of("src", "test", "c-generated", "io_vproxy_pni_test_ObjectStruct.h"));
        var lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:04faee6437cf29c2eb3944159730115266e3215e2745679cf8e2e649ab6d0722", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "ObjectStruct.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:683d71e169c28249aeea0bf5ace99d78c81d72eb5e311c0fb50a33d69b88a2bf", lastLine);
    }
}
