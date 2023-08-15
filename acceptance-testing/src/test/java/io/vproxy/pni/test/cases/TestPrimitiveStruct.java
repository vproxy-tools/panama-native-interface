package io.vproxy.pni.test.cases;

import io.vproxy.pni.Allocator;
import io.vproxy.pni.PNIEnv;
import io.vproxy.pni.array.*;
import io.vproxy.pni.test.PrimitiveStruct;
import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.*;

public class TestPrimitiveStruct {
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
            var s = new PrimitiveStruct(allocator);
            if (round == 0) {
                s.func1(env, (byte) -10, (byte) -10, -20, -20, -30L, -30L, (short) -40, (short) -40);
            } else {
                s.func1Critical((byte) -10, (byte) -10, -20, -20, -30L, -30L, (short) -40, (short) -40);
            }

            assertEquals((byte) -10, s.getAByte());
            assertEquals((byte) -10, s.getUnsignedByte());
            assertEquals(-20, s.getAInt());
            assertEquals(-20, s.getUnsignedInt());
            assertEquals(-30L, s.getALong());
            assertEquals(-30L, s.getUnsignedLong());
            assertEquals((short) -40, s.getAShort());
            assertEquals((short) -40, s.getUnsignedShort());
        }
    }

    @Test
    public void func2() {
        func2(0);
    }

    @Test
    public void func2Critical() {
        func2(1);
    }

    private void func2(int round) {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);
            var s = new PrimitiveStruct(allocator);
            if (round == 0) {
                s.func2(env, 'a', 1.2, 12.8f, true);
            } else {
                s.func2Critical('a', 1.2, 12.8f, true);
            }

            assertEquals('a', s.getAChar());
            assertEquals(1.2, s.getADouble(), 0);
            assertEquals(12.8f, s.getAFloat(), 0);
            assertTrue(s.getABoolean());

            s.func2(env, 'b', 3.6, 25.6f, false);

            assertEquals('b', s.getAChar());
            assertEquals(3.6, s.getADouble(), 0);
            assertEquals(25.6f, s.getAFloat(), 0);
            assertFalse(s.getABoolean());
        }
    }

    @Test
    public void func3() {
        func3(0);
    }

    @Test
    public void func3Critical() {
        func3(1);
    }

    private void func3(int round) {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);
            var s = new PrimitiveStruct(allocator);
            var byteBuf = allocator.allocate(5);
            {
                for (int i = 0; i < 5; ++i) {
                    byteBuf.set(ValueLayout.JAVA_BYTE, i, (byte) (-i - 10));
                }
            }
            var unsignedByteBuf = allocator.allocate(6);
            {
                for (int i = 0; i < 6; ++i) {
                    unsignedByteBuf.set(ValueLayout.JAVA_BYTE, i, (byte) (-i - 20));
                }
            }
            var intArray = new IntArray(allocator, 7);
            {
                for (int i = 0; i < 7; ++i) {
                    intArray.set(i, -i - 30);
                }
            }
            var unsignedIntArray = new IntArray(allocator, 8);
            {
                for (int i = 0; i < 8; ++i) {
                    unsignedIntArray.set(i, -i - 40);
                }
            }
            var longArray = new LongArray(allocator, 9);
            {
                for (int i = 0; i < 9; ++i) {
                    longArray.set(i, -i - 50);
                }
            }
            var unsignedLongArray = new LongArray(allocator, 10);
            {
                for (int i = 0; i < 10; ++i) {
                    unsignedLongArray.set(i, -i - 60);
                }
            }
            var shortArray = new ShortArray(allocator, 11);
            {
                for (int i = 0; i < 11; ++i) {
                    shortArray.set(i, (short) (-i - 70));
                }
            }
            var unsignedShortArray = new ShortArray(allocator, 12);
            {
                for (int i = 0; i < 12; ++i) {
                    unsignedShortArray.set(i, (short) (-i - 80));
                }
            }

            if (round == 0) {
                s.func3(env, byteBuf, unsignedByteBuf,
                    intArray, unsignedIntArray,
                    longArray, unsignedLongArray,
                    shortArray, unsignedShortArray);
            } else {
                s.func3Critical(byteBuf, unsignedByteBuf,
                    intArray, unsignedIntArray,
                    longArray, unsignedLongArray,
                    shortArray, unsignedShortArray);
            }

            for (int i = 0; i < 5; ++i) {
                var seg = s.getByteArray();
                assertEquals(-10 - i, seg.get(ValueLayout.JAVA_BYTE, i));
            }
            assertEquals(5, s.getByteArrayPointer().byteSize());
            for (int i = 0; i < 5; ++i) {
                var seg = s.getByteArrayPointer();
                assertEquals(-10 - i, seg.get(ValueLayout.JAVA_BYTE, i));
            }

            for (int i = 0; i < 6; ++i) {
                var seg = s.getUnsignedByteArray();
                assertEquals(-20 - i, seg.get(ValueLayout.JAVA_BYTE, i));
            }
            assertEquals(6, s.getUnsignedByteArrayPointer().byteSize());
            for (int i = 0; i < 6; ++i) {
                var seg = s.getUnsignedByteArrayPointer();
                assertEquals(-20 - i, seg.get(ValueLayout.JAVA_BYTE, i));
            }

            for (int i = 0; i < 7; ++i) {
                var array = s.getIntArray();
                assertEquals(-30 - i, array.get(i));
            }
            assertEquals(7, s.getIntArrayPointer().length());
            for (int i = 0; i < 7; ++i) {
                var array = s.getIntArrayPointer();
                assertEquals(-30 - i, array.get(i));
            }

            for (int i = 0; i < 8; ++i) {
                var array = s.getUnsignedIntArray();
                assertEquals(-40 - i, array.get(i));
            }
            assertEquals(8, s.getUnsignedIntArrayPointer().length());
            for (int i = 0; i < 8; ++i) {
                var array = s.getUnsignedIntArrayPointer();
                assertEquals(-40 - i, array.get(i));
            }

            for (int i = 0; i < 9; ++i) {
                var array = s.getLongArray();
                assertEquals(-50 - i, array.get(i));
            }
            assertEquals(9, s.getLongArrayPointer().length());
            for (int i = 0; i < 9; ++i) {
                var array = s.getLongArrayPointer();
                assertEquals(-50 - i, array.get(i));
            }

            for (int i = 0; i < 10; ++i) {
                var array = s.getUnsignedLongArray();
                assertEquals(-60 - i, array.get(i));
            }
            assertEquals(10, s.getUnsignedLongArrayPointer().length());
            for (int i = 0; i < 10; ++i) {
                var array = s.getUnsignedLongArrayPointer();
                assertEquals(-60 - i, array.get(i));
            }

            for (int i = 0; i < 11; ++i) {
                var array = s.getShortArray();
                assertEquals(-70 - i, array.get(i));
            }
            assertEquals(11, s.getShortArrayPointer().length());
            for (int i = 0; i < 11; ++i) {
                var array = s.getShortArrayPointer();
                assertEquals(-70 - i, array.get(i));
            }

            for (int i = 0; i < 12; ++i) {
                var array = s.getUnsignedShortArray();
                assertEquals(-80 - i, array.get(i));
            }
            assertEquals(12, s.getUnsignedShortArrayPointer().length());
            for (int i = 0; i < 12; ++i) {
                var array = s.getUnsignedShortArrayPointer();
                assertEquals(-80 - i, array.get(i));
            }
        }
    }

    @Test
    public void func4() {
        func4(0);
    }

    @Test
    public void func4Critical() {
        func4(1);
    }

    private void func4(int round) {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);
            var s = new PrimitiveStruct(allocator);

            var charArray = new CharArray(allocator, 5);
            {
                for (int i = 0; i < 5; ++i) {
                    charArray.set(i, (char) ('a' + i));
                }
            }
            var doubleArray = new DoubleArray(allocator, 6);
            {
                for (int i = 0; i < 6; ++i) {
                    doubleArray.set(i, 10.0 + i);
                }
            }
            var floatArray = new FloatArray(allocator, 7);
            {
                for (int i = 0; i < 7; ++i) {
                    floatArray.set(i, (float) (20.0 + i));
                }
            }
            var boolArray = new BoolArray(allocator, 8);
            {
                for (int i = 0; i < 8; ++i) {
                    boolArray.set(i, i % 2 == 0);
                }
            }

            if (round == 0) {
                s.func4(env, charArray, doubleArray, floatArray, boolArray);
            } else {
                s.func4Critical(charArray, doubleArray, floatArray, boolArray);
            }

            for (int i = 0; i < 5; ++i) {
                var array = s.getCharArray();
                assertEquals((char) ('a' + i), array.get(i));
            }
            assertEquals(5, s.getCharArrayPointer().length());
            for (int i = 0; i < 5; ++i) {
                var array = s.getCharArrayPointer();
                assertEquals((char) ('a' + i), array.get(i));
            }

            for (int i = 0; i < 6; ++i) {
                var array = s.getDoubleArray();
                assertEquals(10.0 + i, array.get(i), 0);
            }
            assertEquals(6, s.getDoubleArrayPointer().length());
            for (int i = 0; i < 6; ++i) {
                var array = s.getDoubleArrayPointer();
                assertEquals(10.0 + i, array.get(i), 0);
            }

            for (int i = 0; i < 7; ++i) {
                var array = s.getFloatArray();
                assertEquals(20.0f + i, array.get(i), 0);
            }
            assertEquals(7, s.getFloatArrayPointer().length());
            for (int i = 0; i < 7; ++i) {
                var array = s.getFloatArrayPointer();
                assertEquals(20.0f + i, array.get(i), 0);
            }

            for (int i = 0; i < 8; ++i) {
                var array = s.getBooleanArray();
                assertEquals(i % 2 == 0, array.get(i));
            }
            assertEquals(8, s.getBooleanArrayPointer().length());
            for (int i = 0; i < 8; ++i) {
                var array = s.getBooleanArrayPointer();
                assertEquals(i % 2 == 0, array.get(i));
            }
        }
    }

    @Test
    public void primitiveRetrieve() {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);
            var s = new PrimitiveStruct(allocator);

            s.setAByte((byte) 10);
            assertEquals((byte) 10, s.retrieveByte(env));
            assertEquals((byte) 10, s.retrieveByteCritical());
            assertEquals((byte) 10, s.getAByte());

            s.setUnsignedByte((byte) 20);
            assertEquals((byte) 20, s.retrieveUnsignedByte(env));
            assertEquals((byte) 20, s.retrieveUnsignedByteCritical());
            assertEquals((byte) 20, s.getUnsignedByte());

            s.setAChar('a');
            assertEquals('a', s.retrieveChar(env));
            assertEquals('a', s.retrieveCharCritical());
            assertEquals('a', s.getAChar());

            s.setADouble(30.0);
            assertEquals(30.0, s.retrieveDouble(env), 0);
            assertEquals(30.0, s.retrieveDoubleCritical(), 0);
            assertEquals(30.0, s.getADouble(), 0);

            s.setAFloat(40.0f);
            assertEquals(40.0f, s.retrieveFloat(env), 0);
            assertEquals(40.0f, s.retrieveFloatCritical(), 0);
            assertEquals(40.0f, s.getAFloat(), 0);

            s.setAInt(50);
            assertEquals(50, s.retrieveInt(env));
            assertEquals(50, s.retrieveIntCritical());
            assertEquals(50, s.getAInt());

            s.setUnsignedInt(60);
            assertEquals(60, s.retrieveUnsignedInt(env));
            assertEquals(60, s.retrieveUnsignedIntCritical());
            assertEquals(60, s.getUnsignedInt());

            s.setALong(70);
            assertEquals(70, s.retrieveLong(env));
            assertEquals(70, s.retrieveLongCritical());
            assertEquals(70, s.getALong());

            s.setUnsignedLong(80);
            assertEquals(80, s.retrieveUnsignedLong(env));
            assertEquals(80, s.retrieveUnsignedLongCritical());
            assertEquals(80, s.getUnsignedLong());

            s.setAShort((short) 90);
            assertEquals((short) 90, s.retrieveShort(env));
            assertEquals((short) 90, s.retrieveShortCritical());
            assertEquals((short) 90, s.getAShort());

            s.setUnsignedShort((short) 100);
            assertEquals((short) 100, s.retrieveUnsignedShort(env));
            assertEquals((short) 100, s.retrieveUnsignedShortCritical());
            assertEquals((short) 100, s.getUnsignedShort());

            s.setABoolean(true);
            assertTrue(s.retrieveBoolean(env));
            assertTrue(s.retrieveBooleanCritical());
            assertTrue(s.getABoolean());

            s.setABoolean(false);
            assertFalse(s.retrieveBoolean(env));
            assertFalse(s.retrieveBooleanCritical());
            assertFalse(s.getABoolean());
        }
    }

    @Test
    public void arrays() {
        arrays(0);
    }

    @Test
    public void arraysCritical() {
        arrays(1);
    }

    private void arrays(int round) {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);
            var s = new PrimitiveStruct(allocator);

            for (int i = 0; i < 5; ++i) {
                s.getByteArray().set(ValueLayout.JAVA_BYTE, i, (byte) (10 + i));
            }
            var byteArray = round == 0 ? s.retrieveByteArray(env) : s.retrieveByteArrayCritical();
            assertEquals(5, byteArray.byteSize());
            for (int i = 0; i < 5; ++i) {
                assertEquals((byte) (10 + i), byteArray.get(ValueLayout.JAVA_BYTE, i));
            }

            for (int i = 0; i < 6; ++i) {
                s.getUnsignedByteArray().set(ValueLayout.JAVA_BYTE, i, (byte) (20 + i));
            }
            var unsignedByteArray = round == 0 ? s.retrieveUnsignedByteArray(env) : s.retrieveUnsignedByteArrayCritical();
            assertEquals(6, unsignedByteArray.byteSize());
            for (int i = 0; i < 6; ++i) {
                assertEquals((byte) (20 + i), unsignedByteArray.get(ValueLayout.JAVA_BYTE, i));
            }

            for (int i = 0; i < 7; ++i) {
                s.getCharArray().set(i, (char) ('a' + i));
            }
            var charArray = round == 0 ? s.retrieveCharArray(env) : s.retrieveCharArrayCritical();
            assertEquals(7, charArray.length());
            for (int i = 0; i < 7; ++i) {
                assertEquals((char) ('a' + i), charArray.get(i));
            }

            for (int i = 0; i < 8; ++i) {
                s.getDoubleArray().set(i, 30.0 + i);
            }
            var doubleArray = round == 0 ? s.retrieveDoubleArray(env) : s.retrieveDoubleArrayCritical();
            assertEquals(8, doubleArray.length());
            for (int i = 0; i < 8; ++i) {
                assertEquals(30.0 + i, doubleArray.get(i), 0);
            }

            for (int i = 0; i < 9; ++i) {
                s.getFloatArray().set(i, 40.0f + i);
            }
            var floatArray = round == 0 ? s.retrieveFloatArray(env) : s.retrieveFloatArrayCritical();
            assertEquals(9, floatArray.length());
            for (int i = 0; i < 9; ++i) {
                assertEquals(40.0 + i, floatArray.get(i), 0);
            }

            for (int i = 0; i < 10; ++i) {
                s.getIntArray().set(i, 50 + i);
            }
            var intArray = round == 0 ? s.retrieveIntArray(env) : s.retrieveIntArrayCritical();
            assertEquals(10, intArray.length());
            for (int i = 0; i < 10; ++i) {
                assertEquals(50 + i, intArray.get(i));
            }

            for (int i = 0; i < 11; ++i) {
                s.getUnsignedIntArray().set(i, 60 + i);
            }
            var unsignedIntArray = round == 0 ? s.retrieveUnsignedIntArray(env) : s.retrieveUnsignedIntArrayCritical();
            assertEquals(11, unsignedIntArray.length());
            for (int i = 0; i < 11; ++i) {
                assertEquals(60 + i, unsignedIntArray.get(i));
            }

            for (int i = 0; i < 12; ++i) {
                s.getLongArray().set(i, 70L + i);
            }
            var longArray = round == 0 ? s.retrieveLongArray(env) : s.retrieveLongArrayCritical();
            assertEquals(12, longArray.length());
            for (int i = 0; i < 12; ++i) {
                assertEquals(70 + i, longArray.get(i));
            }

            for (int i = 0; i < 13; ++i) {
                s.getUnsignedLongArray().set(i, 80L + i);
            }
            var unsignedLongArray = round == 0 ? s.retrieveUnsignedLongArray(env) : s.retrieveUnsignedLongArrayCritical();
            assertEquals(13, unsignedLongArray.length());
            for (int i = 0; i < 13; ++i) {
                assertEquals(80 + i, unsignedLongArray.get(i));
            }

            for (int i = 0; i < 14; ++i) {
                s.getShortArray().set(i, (short) (90 + i));
            }
            var shortArray = round == 0 ? s.retrieveShortArray(env) : s.retrieveShortArrayCritical();
            assertEquals(14, shortArray.length());
            for (int i = 0; i < 14; ++i) {
                assertEquals((short) (90 + i), shortArray.get(i));
            }

            for (int i = 0; i < 15; ++i) {
                s.getUnsignedShortArray().set(i, (short) (100 + i));
            }
            var unsignedShortArray = round == 0 ? s.retrieveUnsignedShortArray(env) : s.retrieveUnsignedShortArrayCritical();
            assertEquals(15, unsignedShortArray.length());
            for (int i = 0; i < 15; ++i) {
                assertEquals((short) (100 + i), unsignedShortArray.get(i));
            }

            for (int i = 0; i < 16; ++i) {
                s.getBooleanArray().set(i, i % 2 == 0);
            }
            var booleanArray = round == 0 ? s.retrieveBooleanArray(env) : s.retrieveBooleanArrayCritical();
            assertEquals(16, booleanArray.length());
            for (int i = 0; i < 16; ++i) {
                assertEquals(i % 2 == 0, booleanArray.get(i));
            }
        }
    }

    @Test
    public void arrayPointer() {
        arrayPointer(0);
    }

    @Test
    public void arrayPointerCritical() {
        arrayPointer(1);
    }

    private void arrayPointer(int round) {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);
            var s = new PrimitiveStruct(allocator);

            assertTrue(s.checkPointerSetToNull(env));

            {
                var array = allocator.allocate(5);
                for (int i = 0; i < 5; ++i) {
                    array.set(ValueLayout.JAVA_BYTE, i, (byte) (10 + i));
                }
                s.setByteArrayPointer(array);
                MemorySegment retArray = round == 0 ? s.retrieveByteArrayPointer(env) : s.retrieveByteArrayPointerCritical();
                assertEquals(5, retArray.byteSize());
                for (int i = 0; i < 5; ++i) {
                    assertEquals((byte) (10 + i), retArray.get(ValueLayout.JAVA_BYTE, i));
                }
                assertEquals(array.address(), retArray.address());
            }

            {
                var array = allocator.allocate(6);
                for (int i = 0; i < 6; ++i) {
                    array.set(ValueLayout.JAVA_BYTE, i, (byte) (20 + i));
                }
                s.setUnsignedByteArrayPointer(array);
                MemorySegment retArray = round == 0 ? s.retrieveUnsignedByteArrayPointer(env) : s.retrieveUnsignedByteArrayPointerCritical();
                assertEquals(6, retArray.byteSize());
                for (int i = 0; i < 6; ++i) {
                    assertEquals((byte) (20 + i), retArray.get(ValueLayout.JAVA_BYTE, i));
                }
                assertEquals(array.address(), retArray.address());
            }

            {
                var array = new CharArray(allocator, 7);
                for (int i = 0; i < 7; ++i) {
                    array.set(i, (char) ('a' + i));
                }
                s.setCharArrayPointer(array);
                CharArray retArray = round == 0 ? s.retrieveCharArrayPointer(env) : s.retrieveCharArrayPointerCritical();
                assertEquals(7, retArray.length());
                for (int i = 0; i < 7; ++i) {
                    assertEquals((char) ('a' + i), retArray.get(i));
                }
                assertEquals(array.MEMORY.address(), retArray.MEMORY.address());
            }

            {
                var array = new DoubleArray(allocator, 8);
                for (int i = 0; i < 8; ++i) {
                    array.set(i, 30.0 + i);
                }
                s.setDoubleArrayPointer(array);
                DoubleArray retArray = round == 0 ? s.retrieveDoubleArrayPointer(env) : s.retrieveDoubleArrayPointerCritical();
                assertEquals(8, retArray.length());
                for (int i = 0; i < 8; ++i) {
                    assertEquals(30.0 + i, retArray.get(i), 0);
                }
                assertEquals(array.MEMORY.address(), retArray.MEMORY.address());
            }

            {
                var array = new FloatArray(allocator, 9);
                for (int i = 0; i < 9; ++i) {
                    array.set(i, 40.0f + i);
                }
                s.setFloatArrayPointer(array);
                FloatArray retArray = round == 0 ? s.retrieveFloatArrayPointer(env) : s.retrieveFloatArrayPointerCritical();
                assertEquals(9, retArray.length());
                for (int i = 0; i < 9; ++i) {
                    assertEquals(40.0f + i, retArray.get(i), 0);
                }
                assertEquals(array.MEMORY.address(), retArray.MEMORY.address());
            }

            {
                var array = new IntArray(allocator, 10);
                for (int i = 0; i < 10; ++i) {
                    array.set(i, 50 + i);
                }
                s.setIntArrayPointer(array);
                IntArray retArray = round == 0 ? s.retrieveIntArrayPointer(env) : s.retrieveIntArrayPointerCritical();
                assertEquals(10, retArray.length());
                for (int i = 0; i < 10; ++i) {
                    assertEquals(50 + i, retArray.get(i));
                }
                assertEquals(array.MEMORY.address(), retArray.MEMORY.address());
            }

            {
                var array = new IntArray(allocator, 11);
                for (int i = 0; i < 11; ++i) {
                    array.set(i, 60 + i);
                }
                s.setUnsignedIntArrayPointer(array);
                IntArray retArray = round == 0 ? s.retrieveUnsignedIntArrayPointer(env) : s.retrieveUnsignedIntArrayPointerCritical();
                assertEquals(11, retArray.length());
                for (int i = 0; i < 11; ++i) {
                    assertEquals(60 + i, retArray.get(i));
                }
                assertEquals(array.MEMORY.address(), retArray.MEMORY.address());
            }

            {
                var array = new LongArray(allocator, 12);
                for (int i = 0; i < 12; ++i) {
                    array.set(i, 70L + i);
                }
                s.setLongArrayPointer(array);
                LongArray retArray = round == 0 ? s.retrieveLongArrayPointer(env) : s.retrieveLongArrayPointerCritical();
                assertEquals(12, retArray.length());
                for (int i = 0; i < 12; ++i) {
                    assertEquals(70 + i, retArray.get(i));
                }
                assertEquals(array.MEMORY.address(), retArray.MEMORY.address());
            }

            {
                var array = new LongArray(allocator, 13);
                for (int i = 0; i < 13; ++i) {
                    array.set(i, 80L + i);
                }
                s.setUnsignedLongArrayPointer(array);
                LongArray retArray = round == 0 ? s.retrieveUnsignedLongArrayPointer(env) : s.retrieveUnsignedLongArrayPointerCritical();
                assertEquals(13, retArray.length());
                for (int i = 0; i < 13; ++i) {
                    assertEquals(80L + i, retArray.get(i));
                }
                assertEquals(array.MEMORY.address(), retArray.MEMORY.address());
            }

            {
                var array = new ShortArray(allocator, 14);
                for (int i = 0; i < 14; ++i) {
                    array.set(i, (short) (90 + i));
                }
                s.setShortArrayPointer(array);
                ShortArray retArray = round == 0 ? s.retrieveShortArrayPointer(env) : s.retrieveShortArrayPointerCritical();
                assertEquals(14, retArray.length());
                for (int i = 0; i < 14; ++i) {
                    assertEquals((short) (90 + i), retArray.get(i));
                }
                assertEquals(array.MEMORY.address(), retArray.MEMORY.address());
            }

            {
                var array = new ShortArray(allocator, 15);
                for (int i = 0; i < 15; ++i) {
                    array.set(i, (short) (100 + i));
                }
                s.setUnsignedShortArrayPointer(array);
                ShortArray retArray = round == 0 ? s.retrieveUnsignedShortArrayPointer(env) : s.retrieveUnsignedShortArrayPointerCritical();
                assertEquals(15, retArray.length());
                for (int i = 0; i < 15; ++i) {
                    assertEquals((short) (100 + i), retArray.get(i));
                }
                assertEquals(array.MEMORY.address(), retArray.MEMORY.address());
            }

            {
                var array = new BoolArray(allocator, 16);
                for (int i = 0; i < 16; ++i) {
                    array.set(i, i % 2 == 0);
                }
                s.setBooleanArrayPointer(array);
                BoolArray retArray = round == 0 ? s.retrieveBooleanArrayPointer(env) : s.retrieveBooleanArrayPointerCritical();
                assertEquals(16, retArray.length());
                for (int i = 0; i < 16; ++i) {
                    assertEquals(i % 2 == 0, retArray.get(i));
                }
                assertEquals(array.MEMORY.address(), retArray.MEMORY.address());
            }

            if (round == 0) {
                assertTrue(s.checkPointerSetToNonNull(env));
            } else {
                assertTrue(s.checkPointerSetToNonNullCritical());
            }

            s.setByteArrayPointer(null);
            s.setUnsignedByteArrayPointer(null);
            s.setCharArrayPointer(null);
            s.setDoubleArrayPointer(null);
            s.setFloatArrayPointer(null);
            s.setIntArrayPointer(null);
            s.setUnsignedIntArrayPointer(null);
            s.setLongArrayPointer(null);
            s.setUnsignedLongArrayPointer(null);
            s.setShortArrayPointer(null);
            s.setUnsignedShortArrayPointer(null);
            s.setBooleanArrayPointer(null);

            if (round == 0) {
                assertTrue(s.checkPointerSetToNull(env));
            } else {
                assertTrue(s.checkPointerSetToNullCritical());
            }
        }
    }

    @Test
    public void checkNull() {
        try (var allocator = Allocator.ofConfined()) {
            var s = new PrimitiveStruct(allocator);

            assertEquals((byte) 0, s.getAByte());
            assertEquals((byte) 0, s.getUnsignedByte());
            assertEquals('\0', s.getAChar());
            assertEquals(0.0, s.getADouble(), 0);
            assertEquals(0.0f, s.getAFloat(), 0);
            assertEquals(0, s.getAInt());
            assertEquals(0, s.getUnsignedInt());
            assertEquals(0L, s.getALong());
            assertEquals(0L, s.getUnsignedLong());
            assertEquals((short) 0, s.getAShort());
            assertEquals((short) 0, s.getUnsignedShort());
            assertFalse(s.getABoolean());

            assertEquals(11, s.getByteArray().byteSize());
            for (int i = 0; i < 11; ++i) {
                assertEquals(0, s.getByteArray().get(ValueLayout.JAVA_BYTE, i));
            }
            assertEquals(12, s.getUnsignedByteArray().byteSize());
            for (int i = 0; i < 12; ++i) {
                assertEquals(0, s.getUnsignedByteArray().get(ValueLayout.JAVA_BYTE, i));
            }
            assertEquals(13, s.getCharArray().length());
            for (int i = 0; i < 13; ++i) {
                assertEquals(0, s.getCharArray().get(i));
            }
            assertEquals(14, s.getDoubleArray().length());
            for (int i = 0; i < 14; ++i) {
                assertEquals(0, s.getDoubleArray().get(i), 0);
            }
            assertEquals(15, s.getFloatArray().length());
            for (int i = 0; i < 15; ++i) {
                assertEquals(0f, s.getFloatArray().get(i), 0);
            }
            assertEquals(16, s.getIntArray().length());
            for (int i = 0; i < 16; ++i) {
                assertEquals(0, s.getIntArray().get(i));
            }
            assertEquals(17, s.getUnsignedIntArray().length());
            for (int i = 0; i < 17; ++i) {
                assertEquals(0, s.getUnsignedIntArray().get(i));
            }
            assertEquals(18, s.getLongArray().length());
            for (int i = 0; i < 18; ++i) {
                assertEquals(0, s.getLongArray().get(i));
            }
            assertEquals(19, s.getUnsignedLongArray().length());
            for (int i = 0; i < 19; ++i) {
                assertEquals(0, s.getUnsignedLongArray().get(i));
            }
            assertEquals(20, s.getShortArray().length());
            for (int i = 0; i < 20; ++i) {
                assertEquals(0, s.getShortArray().get(i));
            }
            assertEquals(21, s.getUnsignedShortArray().length());
            for (int i = 0; i < 21; ++i) {
                assertEquals(0, s.getUnsignedShortArray().get(i));
            }
            assertEquals(22, s.getBooleanArray().length());
            for (int i = 0; i < 13; ++i) {
                assertFalse(s.getBooleanArray().get(i));
            }

            assertNull(s.getByteArrayPointer());
            assertNull(s.getUnsignedByteArrayPointer());
            assertNull(s.getCharArrayPointer());
            assertNull(s.getDoubleArrayPointer());
            assertNull(s.getFloatArrayPointer());
            assertNull(s.getIntArrayPointer());
            assertNull(s.getUnsignedIntArrayPointer());
            assertNull(s.getLongArrayPointer());
            assertNull(s.getUnsignedLongArrayPointer());
            assertNull(s.getShortArrayPointer());
            assertNull(s.getUnsignedShortArrayPointer());
            assertNull(s.getBooleanArrayPointer());
        }
    }

    @Test
    public void shaCheck() throws Exception {
        var s = Files.readAllLines(Path.of("src", "test", "c-generated", "io_vproxy_pni_test_PrimitiveStruct.h"));
        var lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:392b784fee13fc28f1643e44e61e3f139885d4cd3872617f44d75cba37a4330a", lastLine);

        s = Files.readAllLines(Path.of("src", "test", "generated", "io", "vproxy", "pni", "test", "PrimitiveStruct.java"));
        lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:241f5e6890a60172192953fbeaf04f0c1616baea592ac81a2390f9cca3b9008c", lastLine);
    }
}
