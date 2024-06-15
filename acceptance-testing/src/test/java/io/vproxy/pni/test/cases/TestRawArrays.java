package io.vproxy.pni.test.cases;

import io.vproxy.pni.Allocator;
import io.vproxy.pni.PNIEnv;
import io.vproxy.pni.PNIString;
import io.vproxy.pni.PanamaHack;
import io.vproxy.pni.array.*;
import io.vproxy.pni.test.ObjectStruct;
import io.vproxy.pni.test.RawArrays;
import org.junit.Test;

import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

import static org.junit.Assert.*;

public class TestRawArrays {
    @Test
    public void byteArray() {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);

            var array = allocator.allocate(5);
            for (int i = 0; i < array.byteSize(); ++i) {
                array.set(ValueLayout.JAVA_BYTE, i, (byte) (10 + i));
            }

            for (int i = 0; i < 5; ++i) {
                byte b = RawArrays.get().byteArray(env, array, i);
                assertEquals((byte) (10 + i), b);
            }
        }
    }

    @Test
    public void unsignedByteArray() {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);

            var array = allocator.allocate(6);
            for (int i = 0; i < array.byteSize(); ++i) {
                array.set(ValueLayout.JAVA_BYTE, i, (byte) (20 + i));
            }

            for (int i = 0; i < 6; ++i) {
                byte b = RawArrays.get().unsignedByteArray(env, array, i);
                assertEquals((byte) (20 + i), b);
            }
        }
    }

    @Test
    public void boolArray() {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);

            var array = new BoolArray(allocator, 7);
            for (int i = 0; i < array.length(); ++i) {
                array.set(i, i % 2 == 0);
            }

            for (int i = 0; i < 7; ++i) {
                boolean b = RawArrays.get().boolArray(env, array, i);
                if (i % 2 == 0) {
                    assertTrue(b);
                } else {
                    assertFalse(b);
                }
            }
        }
    }

    @Test
    public void charArray() {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);

            var array = new CharArray(allocator, 8);
            for (int i = 0; i < array.length(); ++i) {
                array.set(i, (char) ('a' + i));
            }

            for (int i = 0; i < 8; ++i) {
                char c = RawArrays.get().charArray(env, array, i);
                assertEquals('a' + i, c, 0);
            }
        }
    }

    @Test
    public void floatArray() {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);

            var array = new FloatArray(allocator, 9);
            for (int i = 0; i < array.length(); ++i) {
                array.set(i, 30f + i);
            }

            for (int i = 0; i < 9; ++i) {
                float f = RawArrays.get().floatArray(env, array, i);
                assertEquals(30f + i, f, 0);
            }
        }
    }

    @Test
    public void doubleArray() {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);

            var array = new DoubleArray(allocator, 10);
            for (int i = 0; i < array.length(); ++i) {
                array.set(i, 40d + i);
            }

            for (int i = 0; i < 10; ++i) {
                double d = RawArrays.get().doubleArray(env, array, i);
                assertEquals(40d + i, d, 0);
            }
        }
    }

    @Test
    public void intArray() {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);

            var array = new IntArray(allocator, 11);
            for (int i = 0; i < array.length(); ++i) {
                array.set(i, 50 + i);
            }

            for (int i = 0; i < 11; ++i) {
                int n = RawArrays.get().intArray(env, array, i);
                assertEquals(50 + i, n);
            }
        }
    }

    @Test
    public void unsignedIntArray() {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);

            var array = new IntArray(allocator, 12);
            for (int i = 0; i < array.length(); ++i) {
                array.set(i, 60 + i);
            }

            for (int i = 0; i < 12; ++i) {
                int n = RawArrays.get().unsignedIntArray(env, array, i);
                assertEquals(60 + i, n);
            }
        }
    }

    @Test
    public void longArray() {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);

            var array = new LongArray(allocator, 13);
            for (int i = 0; i < array.length(); ++i) {
                array.set(i, 70L + i);
            }

            for (int i = 0; i < 13; ++i) {
                long l = RawArrays.get().longArray(env, array, i);
                assertEquals(70L + i, l);
            }
        }
    }

    @Test
    public void unsignedLongArray() {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);

            var array = new LongArray(allocator, 14);
            for (int i = 0; i < array.length(); ++i) {
                array.set(i, 80L + i);
            }

            for (int i = 0; i < 14; ++i) {
                long l = RawArrays.get().unsignedLongArray(env, array, i);
                assertEquals(80L + i, l);
            }
        }
    }

    @Test
    public void shortArray() {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);

            var array = new ShortArray(allocator, 15);
            for (int i = 0; i < array.length(); ++i) {
                array.set(i, (short) (90 + i));
            }

            for (int i = 0; i < 15; ++i) {
                short s = RawArrays.get().shortArray(env, array, i);
                assertEquals((short) (90 + i), s);
            }
        }
    }

    @Test
    public void unsignedShortArray() {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);

            var array = new ShortArray(allocator, 16);
            for (int i = 0; i < array.length(); ++i) {
                array.set(i, (short) (100 + i));
            }

            for (int i = 0; i < 16; ++i) {
                short s = RawArrays.get().unsignedShortArray(env, array, i);
                assertEquals((short) (100 + i), s);
            }
        }
    }

    @Test
    public void refArray() {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);

            var array = new ObjectStruct.Array(allocator.allocate(17 * ObjectStruct.LAYOUT.byteSize()));
            for (int i = 0; i < array.length(); ++i) {
                array.get(i).setLenStr(String.valueOf('A' + i));
            }

            for (int i = 0; i < 17; ++i) {
                ObjectStruct o = RawArrays.get().structArray(env, array, i, allocator);
                assertEquals(String.valueOf('A' + i), o.getLenStr());

                o = RawArrays.get().structArrayNotRaw(env, array, i, allocator);
                assertEquals(String.valueOf('A' + i), o.getLenStr());
            }
        }
    }

    @Test
    public void pointerArray() {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);

            var array = new PointerArray(allocator, 18);
            for (int i = 0; i < array.length(); ++i) {
                var str = new PNIString(allocator, String.valueOf((char) ('A' + i)));
                array.set(i, str.MEMORY);
            }

            for (int i = 0; i < 18; ++i) {
                MemorySegment p = RawArrays.get().pointerArray(env, array, i);
                assertEquals(String.valueOf((char) ('A' + i)), PanamaHack.getUtf8String(p.reinterpret(2), 0));

                p = RawArrays.get().pointerArrayNotRaw(env, array, i);
                assertEquals(String.valueOf((char) ('A' + i)), PanamaHack.getUtf8String(p.reinterpret(2), 0));
            }
        }
    }
}
