package io.vproxy.pni.test.cases;

import io.vproxy.pni.PNIEnv;
import io.vproxy.pni.test.Func;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.*;
import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TestFunc {
    @BeforeClass
    public static void beforeClass() {
        System.loadLibrary("pnitest");
    }

    @Test
    public void func1() {
        try (var arena = Arena.ofConfined()) {
            var env = new PNIEnv(arena);

            int n = Func.get().func1(env);
            assertEquals(10, n);
        }
    }

    @Test
    public void func2() {
        try (var arena = Arena.ofConfined()) {
            var env = new PNIEnv(arena);

            Func.get().func2(env);
            fail();
        } catch (IOException e) {
            assertEquals("hello", e.getMessage());
        }
    }

    @Test
    public void func3() throws Throwable {
        try (var arena = Arena.ofConfined()) {
            var env = new PNIEnv(arena);
            Func.get().func3(env, IOException.class.getName());
            fail();
        } catch (IOException e) {
            assertEquals("aaa", e.getMessage());
        }
        try (var arena = Arena.ofConfined()) {
            var env = new PNIEnv(arena);
            Func.get().func3(env, UnsupportedOperationException.class.getName());
            fail();
        } catch (UnsupportedOperationException e) {
            assertEquals("aaa", e.getMessage());
        }
        try (var arena = Arena.ofConfined()) {
            var env = new PNIEnv(arena);
            Func.get().func3(env, null);
        }
    }

    @Test
    public void write() throws Exception {
        try (var arena = Arena.ofConfined()) {
            var env = new PNIEnv(arena);

            var file = File.createTempFile("pni-test-write", ".log");
            file.deleteOnExit();

            try (var fos = new FileOutputStream(file)) {
                var fdObj = fos.getFD();
                var fdField = FileDescriptor.class.getDeclaredField("fd");
                fdField.setAccessible(true);
                int fd = (int) fdField.get(fdObj);

                var buf = ByteBuffer.allocateDirect(30);
                buf.put("hello ".getBytes(StandardCharsets.UTF_8));

                int n = Func.get().write(env, fd, buf, 0, buf.position());
                assertEquals(buf.position(), n);

                var bytes = "world\n".getBytes(StandardCharsets.UTF_8);
                n = Func.get().writeByteArray(env, fd,
                    arena.allocate(bytes.length).copyFrom(MemorySegment.ofArray(bytes)),
                    0, bytes.length);
                assertEquals(bytes.length, n);
            }

            var str = Files.readString(file.toPath());
            assertEquals("hello world\n", str);
        }
    }

    @Test
    public void callJavaFromC() {
        try (var arena = Arena.ofConfined()) {
            var env = new PNIEnv(arena);
            var seg = arena.allocate(16);
            var ret = Func.get().callJavaFromC(env, o -> {
                o.setSeg(seg);
                return 0;
            });
            assertEquals(seg.address(), ret.address());
        }
    }

    @Test
    public void shaCheck() throws Exception {
        var s = Files.readAllLines(Path.of("src", "test", "c-generated", "io_vproxy_pni_test_Func.h"));
        var lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:6dfaa83d60edde455447264a1097908411e6ca042a78075905eeac784a03927d", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "c-generated", "io_vproxy_pni_test_Func.impl.h"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:8c8e2b429fca89601abd08c41685584bd9a21992ddf32abf2d1a694823eb2270", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "Func.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:85c7823c34a0925c062347decddbbb53ad7a5ffe690abed7dd19feb4016ae7c4", lastLine);
    }
}
