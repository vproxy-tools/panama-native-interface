package io.vproxy.pni.test.cases;

import io.vproxy.base.util.OS;
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
        TestUtils.loadLib();
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
        if (OS.isWindows()) {
            return;
        }
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
        if (OS.isWindows()) {
            System.out.println("SKIPPED");
            return;
        }
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
    public void errno2() {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);

            try {
                Func.get().testErrno(env);
            } catch (IOException e) {
                assertEquals("Invalid argument", e.getMessage());
            }
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
        if (TestUtils.skipCase()) {
            return;
        }

        long holderSizeBefore = PNIRef.currentRefStorageSize();
        try (var arena = Arena.ofConfined()) {
            var method = PanamaUtils.defineCFunctionByName(new PNILinkOptions(), arena,
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
        assertEquals("// sha256:d45842a0309b27f93bae6de50130f408d2338c8bf53a627eb148d44e585641a1", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "c-generated", "io_vproxy_pni_test_Func.impl.h"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:c0d299acec345a824d7f38ef403c5049d16784365d57e746bb0520913deeb1c8", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "Func.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:144cc3ca8603df482b13c59c04796c1026045598032c5b1d56bf2dea5160d071", lastLine);
    }
}
