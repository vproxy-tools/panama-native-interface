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
        func1(0);
    }

    @Test
    public void func1Critical() {
        func1(1);
    }

    private void func1(int round) {
        try (var arena = Arena.ofConfined()) {
            var env = new PNIEnv(arena);

            int n = round == 0 ? Func.get().func1(env) : Func.get().func1Critical();
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

                buf.position(0).limit(buf.capacity());
                buf.put("panama ".getBytes(StandardCharsets.UTF_8));
                n = Func.get().writeCritical(fd, buf, 0, buf.position());
                assertEquals(buf.position(), n);

                var bytes = "native\n".getBytes(StandardCharsets.UTF_8);
                n = Func.get().writeByteArray(env, fd,
                    arena.allocate(bytes.length).copyFrom(MemorySegment.ofArray(bytes)),
                    0, bytes.length);
                assertEquals(bytes.length, n);
            }

            var str = Files.readString(file.toPath());
            assertEquals("hello panama native\n", str);
        }
    }

    @Test
    public void callJavaFromC() {
        callJavaFromC(0);
    }

    @Test
    public void callJavaFromCCritical() {
        callJavaFromC(1);
    }

    private void callJavaFromC(int round) {
        try (var arena = Arena.ofConfined()) {
            var env = new PNIEnv(arena);
            var seg = arena.allocate(16);
            MemorySegment ret;
            if (round == 0) {
                ret = Func.get().callJavaFromC(env, o -> {
                    o.setSeg(seg);
                    return 0;
                });
            } else {
                ret = Func.get().callJavaFromCCritical(o -> {
                    o.setSeg(seg);
                    return 0;
                });
            }
            assertEquals(seg.address(), ret.address());
        }
    }

    @Test
    public void shaCheck() throws Exception {
        var s = Files.readAllLines(Path.of("src", "test", "c-generated", "io_vproxy_pni_test_Func.h"));
        var lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:bb1423f4914a9ad31bcf3a400c4fc433a1b7ab4fa453bf13a170975ff134df62", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "c-generated", "io_vproxy_pni_test_Func.impl.h"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:58a9e5ff553a722e9b62baaa991cd76e6e1e9fd4538a2b70d09d08a2ef2a06cd", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "Func.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:e556c3b1c066d1d21ff9c7c2eeec9e1716e77829fea89453215806cdee9a445d", lastLine);
    }
}
