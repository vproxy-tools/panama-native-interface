package io.vproxy.pni.test.cases;

import io.vproxy.pni.Allocator;
import io.vproxy.pni.PNIEnv;
import io.vproxy.pni.PNIString;
import io.vproxy.pni.test.Func;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.*;
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
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);

            int n = round == 0 ? Func.get().func1(env) : Func.get().func1Critical();
            assertEquals(10, n);
        }
    }

    @Test
    public void func2() {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);

            Func.get().func2(env);
            fail();
        } catch (IOException e) {
            assertEquals("hello", e.getMessage());
        }
    }

    @Test
    public void func3() throws Throwable {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);
            Func.get().func3(env, new PNIString(allocator, IOException.class.getName()));
            fail();
        } catch (IOException e) {
            assertEquals("aaa", e.getMessage());
        }
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);
            Func.get().func3(env, new PNIString(allocator, UnsupportedOperationException.class.getName()));
            fail();
        } catch (UnsupportedOperationException e) {
            assertEquals("aaa", e.getMessage());
        }
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);
            Func.get().func3(env, null);
        }
    }

    @Test
    public void write() throws Exception {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);

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
                    allocator.allocate(bytes.length).copyFrom(MemorySegment.ofArray(bytes)),
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
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);
            var seg = allocator.allocate(16);
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
    public void errno() {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);

            var buf = ByteBuffer.allocateDirect(30);
            buf.put("hello ".getBytes(StandardCharsets.UTF_8));

            int n = Func.get().writeWithErrno(env, -1, buf, 0, buf.position());
            assertEquals(-1, n);
            assertEquals(9 /* EBADF */, env.ex().errno());
        }
    }

    @Test
    public void shaCheck() throws Exception {
        var s = Files.readAllLines(Path.of("src", "test", "c-generated", "io_vproxy_pni_test_Func.h"));
        var lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:68cb36d6797717757b40f66cefeba2ef7bd9873ab3d0b96b94b7a20783d7822e", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "c-generated", "io_vproxy_pni_test_Func.impl.h"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:5dbf609163d3f0e62dd1e04fa351071596ff89a8f807498963cd70cdad2e3b0c", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "Func.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:53e9cfc0d4bde282dea2b3172c5224f7d09bed6aa1dcc4c2e0093847cf9b08a4", lastLine);
    }
}
