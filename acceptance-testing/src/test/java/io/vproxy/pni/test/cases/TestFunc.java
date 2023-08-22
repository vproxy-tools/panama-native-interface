package io.vproxy.pni.test.cases;

import io.vproxy.pni.*;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    public void callJavaRefFromC() {
        callJavaRefFromC(0);
    }

    @Test
    public void callJavaRefFromCCritical() {
        callJavaRefFromC(1);
    }

    private void callJavaRefFromC(int round) {
        long refHolderSizeBefore = PNIRef.currentRefStorageSize();
        long funcHolderSizeBefore = PNIFunc.currentFuncStorageSize();
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);

            var ls = new ArrayList<String>();
            ls.add("hello");

            CallSite<List<String>> callSite = ref -> {
                ref.add("world");
                return 0;
            };
            if (round == 0) {
                Func.get().callJavaRefFromC(env, callSite, ls);
            } else {
                Func.get().callJavaRefFromCCritical(callSite, ls);
            }

            assertEquals(Arrays.asList("hello", "world"), ls);
        }
        assertEquals(refHolderSizeBefore, PNIRef.currentRefStorageSize());
        assertEquals(funcHolderSizeBefore, PNIFunc.currentFuncStorageSize());
    }

    @Test
    public void callJavaMethodWithRefFromC() {
        callJavaMethodWithRefFromC(0);
    }

    @Test
    public void callJavaMethodWithRefFromCCritical() {
        callJavaMethodWithRefFromC(1);
    }

    private void callJavaMethodWithRefFromC(int round) {
        long holderSizeBefore = PNIRef.currentRefStorageSize();
        try (var arena = Arena.ofConfined()) {
            var method = PanamaUtils.defineCFunctionByName(arena,
                TestFunc.class, "methodForCallJavaMethodWithRefFromC");
            var env = new PNIEnv(Allocator.of(arena));

            var ls = new ArrayList<Integer>();
            ls.add(1);
            int res;
            if (round == 0) {
                res = Func.get().callJavaMethodWithRefFromC(env, method, ls, 3);
            } else {
                res = Func.get().callJavaMethodWithRefFromCCritical(method, ls, 3);
            }
            assertEquals(4, res);
            assertEquals(Arrays.asList(1, 3), ls);
        }
        assertEquals(holderSizeBefore, PNIRef.currentRefStorageSize());
    }

    @SuppressWarnings("unused")
    public static int methodForCallJavaMethodWithRefFromC(MemorySegment seg, int a) {
        var ls = PNIRef.<List<Integer>>getRef(seg);
        ls.add(a);
        return ls.get(0) + a;
    }

    @Test
    public void shaCheck() throws Exception {
        var s = Files.readAllLines(Path.of("src", "test", "c-generated", "io_vproxy_pni_test_Func.h"));
        var lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:9128b96bc6344e1be69ee452fec70323b36e4a47bd994febc39f86ff02a50c9c", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "c-generated", "io_vproxy_pni_test_Func.impl.h"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:dee6489c367b273db501832f4057a9dbf0b482bd044b6d17de19a59a6377a90b", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "Func.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:969668a7d254fa6d01d5fb8b5180139f28bde1daadbb5bdbbe2fe06ce234d0d0", lastLine);
    }
}
