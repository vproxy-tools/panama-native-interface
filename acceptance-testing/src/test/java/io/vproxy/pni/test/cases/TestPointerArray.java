package io.vproxy.pni.test.cases;

import io.vproxy.pni.Allocator;
import io.vproxy.pni.PNIEnv;
import io.vproxy.pni.PNIString;
import io.vproxy.pni.array.PointerArray;
import io.vproxy.pni.test.PointerArrayField;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TestPointerArray {
    @Test
    public void set() {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);
            var s = new PointerArrayField(allocator);

            for (int i = 0; i < 3; ++i) {
                assertNull(s.getPointerArray().get(i));
            }
            assertNull(s.getPointerArrayPointer());

            var a = new PointerArray(allocator, 2);
            a.set(0, new PNIString(allocator, "hello").MEMORY);
            a.set(1, new PNIString(allocator, "world").MEMORY);
            s.set(env, a);

            var s1 = s.getPointerArray().get(0).reinterpret(10).getUtf8String(0);
            var s2 = s.getPointerArray().get(1).reinterpret(10).getUtf8String(0);
            assertEquals("hello", s1);
            assertEquals("world", s2);
            assertNull(s.getPointerArray().get(2));

            assertEquals(2, s.getPointerArrayPointer().length());
            s1 = s.getPointerArrayPointer().get(0).reinterpret(10).getUtf8String(0);
            s2 = s.getPointerArrayPointer().get(1).reinterpret(10).getUtf8String(0);
            assertEquals("hello", s1);
            assertEquals("world", s2);
        }
    }

    @Test
    public void get() throws Exception {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);
            var s = new PointerArrayField(allocator);

            s.getPointerArray().set(0, new PNIString(allocator, "helloA").MEMORY);
            s.getPointerArray().set(1, new PNIString(allocator, "worldB").MEMORY);

            var a = new PointerArray(allocator, 2);
            a.set(0, new PNIString(allocator, "helloX").MEMORY);
            a.set(1, new PNIString(allocator, "worldY").MEMORY);
            s.setPointerArrayPointer(a);

            var arr = s.getLenField(env);
            assertEquals("helloA", arr.get(0).reinterpret(10).getUtf8String(0));
            assertEquals("worldB", arr.get(1).reinterpret(10).getUtf8String(0));
            assertNull(arr.get(2));

            arr = s.getPtrField(env);
            assertEquals(2, arr.length());
            assertEquals("helloX", arr.get(0).reinterpret(10).getUtf8String(0));
            assertEquals("worldY", arr.get(1).reinterpret(10).getUtf8String(0));
        }
    }
}
