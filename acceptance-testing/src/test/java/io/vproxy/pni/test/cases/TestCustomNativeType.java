package io.vproxy.pni.test.cases;

import io.vproxy.pni.Allocator;
import io.vproxy.pni.PNIEnv;
import io.vproxy.pni.test.CustomNativeTypeFunc;
import io.vproxy.pni.test.CustomNativeTypeStruct;
import io.vproxy.pni.test.CustomNativeTypeUpcall;
import io.vproxy.pni.test.SizeofStructExpr;
import io.vproxy.pni.unsafe.SunUnsafe;
import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.assertEquals;

public class TestCustomNativeType {
    @BeforeClass
    public static void beforeClass() {
        //noinspection Convert2Lambda
        CustomNativeTypeUpcall.setImpl(new CustomNativeTypeUpcall.Interface() {
            @Override
            public MemorySegment exec(MemorySegment o) {
                return MemorySegment.ofAddress(o.address() + 8);
            }
        });
    }

    @Test
    public void struct() {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);
            var s = new CustomNativeTypeStruct(allocator);
            var ptrS = new SizeofStructExpr(allocator);
            s.setField(ptrS.MEMORY);

            SunUnsafe.setMemory(ptrS.MEMORY.address(), ptrS.MEMORY.byteSize(), (byte) 1);

            var p1 = s.getP1(env);
            assertEquals(0x0101010101010101L, p1);
        }
    }

    @Test
    public void func() {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);
            var ptrS = new SizeofStructExpr(allocator);
            var ptrP1 = CustomNativeTypeFunc.get().exec(ptrS.MEMORY).reinterpret(8);

            assertEquals(8, ptrP1.address() - ptrS.MEMORY.address());
            assertEquals(0, ptrP1.get(ValueLayout.JAVA_LONG, 0));

            SunUnsafe.setMemory(ptrS.MEMORY.address(), ptrS.MEMORY.byteSize(), (byte) 1);
            assertEquals(0x0101010101010101L, ptrP1.get(ValueLayout.JAVA_LONG, 0));

            var s = new CustomNativeTypeStruct(allocator);
            s.setField(ptrS.MEMORY);
            assertEquals(0x0101010101010101L, s.getP1(env));
        }
    }

    @Test
    public void upcall() {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);
            var ptrS = new SizeofStructExpr(allocator);

            var ptrP1 = CustomNativeTypeFunc.get().invoke(ptrS).reinterpret(8);

            assertEquals(8, ptrP1.address() - ptrS.MEMORY.address());
            assertEquals(0, ptrP1.get(ValueLayout.JAVA_LONG, 0));

            SunUnsafe.setMemory(ptrS.MEMORY.address(), ptrS.MEMORY.byteSize(), (byte) 1);
            assertEquals(0x0101010101010101L, ptrP1.get(ValueLayout.JAVA_LONG, 0));

            var s = new CustomNativeTypeStruct(allocator);
            s.setField(ptrS.MEMORY);
            assertEquals(0x0101010101010101L, s.getP1(env));
        }
    }

    @Test
    public void shaCheck() throws Exception {
        var s = Files.readAllLines(Path.of("src", "test", "c-generated", "io_vproxy_pni_test_CustomNativeTypeFunc.h"));
        var lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:cf533886453fd7b44b3071156f30e61145bd57a68e1e8668d90396b494d1d567", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "c-generated", "io_vproxy_pni_test_CustomNativeTypeFunc.impl.h"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:560491e980f7e29055f911f8176743c5426f8d6541133894a8373431af88ac69", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "c-generated", "io_vproxy_pni_test_CustomNativeTypeStruct.h"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:cc504dec2ca3f225d5f30b87125415d60de8bc3535b44afd9d948d344341a24f", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "c-generated", "io_vproxy_pni_test_CustomNativeTypeStruct.impl.h"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:15094ebc88c6c4b8346ae150ee3b24806739f089b43702bb77a6f3a65442d2c4", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "c-generated", "io_vproxy_pni_test_CustomNativeTypeUpcall.c"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:3ebeb73d5cfb3ab23476a909c0a7f1d249b6ab981c78b92827f6ab9c294ff7c7", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "c-generated", "io_vproxy_pni_test_CustomNativeTypeUpcall.h"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:7d124f76a574ad5ba3d54b0ce0874e3eb7b5be69e3aeb18943ef27898d0b17dd", lastLine);
    }
}
