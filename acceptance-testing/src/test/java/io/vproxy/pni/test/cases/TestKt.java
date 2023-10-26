package io.vproxy.pni.test.cases;

import io.vproxy.pni.Allocator;
import io.vproxy.pni.PNIEnv;
import io.vproxy.pni.test.*;
import org.junit.BeforeClass;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.assertEquals;

public class TestKt {
    @BeforeClass
    public static void beforeClass() {
        //noinspection Convert2Lambda
        KtUpcall.setImpl(new KtUpcall.Interface() {
            @Override
            public KtStruct helloworld(int i, long l, KtStruct return_) {
                return_.setAInt(i);
                return_.setALong(l);
                return return_;
            }
        });
    }

    @Test
    public void struct() {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);
            var kt = new KtStruct(allocator);

            kt.setAInt(1122);
            kt.setALong(334455);
            var i = kt.retrieveInt(env);
            var l = kt.retrieveLong(env);

            assertEquals(1122, i);
            assertEquals(334455, l);
        }
    }

    @Test
    public void inherit() {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);

            var inherit = new KtStructInherit(allocator);
            inherit.setALong(998877);
            var l = KtDowncall.get().retrieveLong(inherit);
            assertEquals(998877, l);

            var o = inherit.getObj();
            o.setAInt(1234);

            var x = inherit.retrieveObj(env);
            assertEquals(o.MEMORY.address(), x.MEMORY.address());
            assertEquals(1234, x.getAInt());
        }
    }

    @Test
    public void upcall() {
        try (var allocator = Allocator.ofConfined()) {
            var x = KtDowncall.get().invokeHelloWorld(1122, 334455, allocator);
            assertEquals(1122, x.getAInt());
            assertEquals(334455, x.getALong());
        }
    }

    @Test
    public void shaCheck() throws Exception {
        var s = Files.readAllLines(Path.of("src", "test", "c-generated", "io_vproxy_pni_test_KtDowncall.h"));
        var lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:d77fdf36a6537a49873b08c306383f6102e2b3d1e77eccb9daf629461ae7f494", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "c-generated", "io_vproxy_pni_test_KtDowncall.impl.h"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:11c036cf1accefdcd9e00477011c07d0fed9106912bd8ce55b6d3aa30e18afd1", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "c-generated", "io_vproxy_pni_test_KtStruct.h"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:9a7a8c9c3fe953e8ae66b59b74e22b436cdfc24f325459ff07365d87b9ea3693", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "c-generated", "io_vproxy_pni_test_KtStruct.impl.h"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:aaa1d2f8b008e749b634c04eb0b0c450d8c1f45f2d186bfe57e25e6d1367ee38", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "c-generated", "io_vproxy_pni_test_KtStructInherit.h"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:6572cbdf1edb0cb15b448f699e57b8eaa75816a1dd509622979db21e923a611e", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "c-generated", "io_vproxy_pni_test_KtStructInherit.impl.h"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:6ca020ce3a2525f675dbf6e29ff6a178f81c0789d9c1cec5d0d5126965dc0c10", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "c-generated", "io_vproxy_pni_test_KtUpcall.c"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:048776dd0b94238775168f2bfea9c838122d9976aed2cae6a7d49d3fe2aaf21e", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "c-generated", "io_vproxy_pni_test_KtUpcall.h"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:f4d5fc02c0eb8e3a7c72d2fe53b2e5dd25897011efc3103ed97c854e40b4c818", lastLine);
    }
}
