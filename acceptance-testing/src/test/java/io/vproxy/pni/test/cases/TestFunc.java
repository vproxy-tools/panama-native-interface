package io.vproxy.pni.test.cases;

import io.vproxy.pni.PNIEnv;
import io.vproxy.pni.test.Func;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.*;
import java.lang.foreign.Arena;
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
                buf.put("hello world\n".getBytes(StandardCharsets.UTF_8));

                int n = Func.get().write(env, fd, buf, 0, buf.position());
                assertEquals(buf.position(), n);
            }

            var str = Files.readString(file.toPath());
            assertEquals("hello world\n", str);
        }
    }

    @Test
    public void shaCheck() throws Exception {
        var s = Files.readAllLines(Path.of("src", "test", "c-generated", "io_vproxy_pni_test_Func.h"));
        var lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:553cf4d4dc3b3f7badc6e03e8f1d0931ed4c281946ebab924c855bd12e852211", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "c-generated", "io_vproxy_pni_test_Func.impl.h"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:c5be3bf03333b8ae2bdd2a72732ac587edfdaf0fbe6dbf92ec36e0c8990e73fa", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "Func.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:897e1e1564d6eaae91193536501cd9c26f00e8b1f75544aa874ec8b50845585e", lastLine);
    }
}
