package io.vproxy.pni.test.cases;

import io.vproxy.pni.Allocator;
import io.vproxy.pni.PNIEnv;
import io.vproxy.pni.PanamaUtils;
import io.vproxy.pni.test.DefiningCFunction;
import org.junit.Test;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

import static org.junit.Assert.*;

@SuppressWarnings("unused")
public class TestDefiningCFunction {
    private static final Arena arena = Arena.ofShared();
    private static final PNIEnv env = new PNIEnv(Allocator.of(arena));

    public static void upcallVoidNoParam0() {
        System.out.println("!!upcallVoidNoParam0!!");
    }

    @Test
    public void upcallVoidNoParam() {
        var func = PanamaUtils.defineCFunction(arena, TestDefiningCFunction.class, "upcallVoidNoParam0");
        DefiningCFunction.get().upcallVoidNoParam(env, func);
    }

    public static void upcallVoid1Param0(MemorySegment data) {
        data.reinterpret(8).set(ValueLayout.JAVA_LONG, 0, 123L);
    }

    @Test
    public void upcallVoid1Param() {
        var func = PanamaUtils.defineCFunctionByName(arena, TestDefiningCFunction.class, "upcallVoid1Param0");
        try (var allocator = Allocator.ofConfined()) {
            var data = allocator.allocate(8);
            DefiningCFunction.get().upcallVoid1Param(env, func, data);
            assertEquals(123L, data.get(ValueLayout.JAVA_LONG, 0));
        }
    }

    public static void upcallVoid2Param0(MemorySegment data, byte b) {
        data.reinterpret(1).set(ValueLayout.JAVA_BYTE, 0, b);
    }

    @Test
    public void upcallVoid2Param() {
        var func = PanamaUtils.defineCFunctionByName(arena, TestDefiningCFunction.class, "upcallVoid2Param0");
        try (var allocator = Allocator.ofConfined()) {
            var data = allocator.allocate(1);
            DefiningCFunction.get().upcallVoid2Param(env, func, data, (byte) 122);
            assertEquals((byte) 122, data.get(ValueLayout.JAVA_BYTE, 0));
        }
    }

    public static void upcallVoid3Param0(MemorySegment data, boolean z, char c) {
        data = data.reinterpret(3);
        data.set(ValueLayout.JAVA_BOOLEAN, 0, z);
        data.set(ValueLayout.JAVA_CHAR_UNALIGNED, 1, c);
    }

    @Test
    public void upcallVoid3Param() {
        var func = PanamaUtils.defineCFunctionByName(arena, TestDefiningCFunction.class, "upcallVoid3Param0");
        try (var allocator = Allocator.ofConfined()) {
            var data = allocator.allocate(3);
            DefiningCFunction.get().upcallVoid3Param(env, func, data, true, 'a');
            assertTrue(data.get(ValueLayout.JAVA_BOOLEAN, 0));
            assertEquals('a', data.get(ValueLayout.JAVA_CHAR_UNALIGNED, 1));
        }
    }

    public static void upcallVoid4Param0(MemorySegment data, double d, float f, int i) {
        data = data.reinterpret(16);
        data.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, 0, d);
        data.set(ValueLayout.JAVA_FLOAT_UNALIGNED, 8, f);
        data.set(ValueLayout.JAVA_INT_UNALIGNED, 12, i);
    }

    @Test
    public void upcallVoid4Param() {
        var func = PanamaUtils.defineCFunctionByName(arena, TestDefiningCFunction.class, "upcallVoid4Param0");
        try (var allocator = Allocator.ofConfined()) {
            var data = allocator.allocate(16);
            DefiningCFunction.get().upcallVoid4Param(env, func, data, 12.8, 6.4f, 121);
            assertEquals(12.8, data.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, 0), 0);
            assertEquals(6.4f, data.get(ValueLayout.JAVA_FLOAT_UNALIGNED, 8), 0);
            assertEquals(121, data.get(ValueLayout.JAVA_INT_UNALIGNED, 12));
        }
    }

    public static void upcallVoid3Param20(MemorySegment data, long l, short s) {
        data = data.reinterpret(10);
        data.set(ValueLayout.JAVA_LONG_UNALIGNED, 0, l);
        data.set(ValueLayout.JAVA_SHORT_UNALIGNED, 8, s);
    }

    @Test
    public void upcallVoid3Param2() {
        var func = PanamaUtils.defineCFunctionByName(arena, TestDefiningCFunction.class, "upcallVoid3Param20");
        try (var allocator = Allocator.ofConfined()) {
            var data = allocator.allocate(10);
            DefiningCFunction.get().upcallVoid3Param2(env, func, data, 120L, (short) 119);
            assertEquals(120, data.get(ValueLayout.JAVA_LONG_UNALIGNED, 0));
            assertEquals((short) 119, data.get(ValueLayout.JAVA_SHORT_UNALIGNED, 8));
        }
    }

    public static byte upcallReturnByteNoParam0() {
        return (byte) 99;
    }

    @Test
    public void upcallReturnByteNoParam() {
        var func = PanamaUtils.defineCFunctionByName(arena, TestDefiningCFunction.class, "upcallReturnByteNoParam0");
        byte b = DefiningCFunction.get().upcallReturnByteNoParam(env, func);
        assertEquals((byte) 99, b);
    }

    public static boolean upcallReturnBoolNoParam0() {
        return true;
    }

    @Test
    public void upcallReturnBoolNoParam() {
        var func = PanamaUtils.defineCFunctionByName(arena, TestDefiningCFunction.class, "upcallReturnBoolNoParam0");
        boolean b = DefiningCFunction.get().upcallReturnBoolNoParam(env, func);
        assertTrue(b);
    }

    public static char upcallReturnCharNoParam0() {
        return 'x';
    }

    @Test
    public void upcallReturnCharNoParam() {
        var func = PanamaUtils.defineCFunctionByName(arena, TestDefiningCFunction.class, "upcallReturnCharNoParam0");
        char c = DefiningCFunction.get().upcallReturnCharNoParam(env, func);
        assertEquals('x', c);
    }

    public static double upcallReturnDoubleNoParam0() {
        return 64.0;
    }

    @Test
    public void upcallReturnDoubleNoParam() {
        var func = PanamaUtils.defineCFunctionByName(arena, TestDefiningCFunction.class, "upcallReturnDoubleNoParam0");
        double d = DefiningCFunction.get().upcallReturnDoubleNoParam(env, func);
        assertEquals(64.0, d, 0);
    }

    public static float upcallReturnFloatNoParam0() {
        return 32.0f;
    }

    @Test
    public void upcallReturnFloatNoParam() {
        var func = PanamaUtils.defineCFunctionByName(arena, TestDefiningCFunction.class, "upcallReturnFloatNoParam0");
        float f = DefiningCFunction.get().upcallReturnFloatNoParam(env, func);
        assertEquals(32.0f, f, 0);
    }

    public static int upcallReturnIntNoParam0() {
        return 98;
    }

    @Test
    public void upcallReturnIntNoParam() {
        var func = PanamaUtils.defineCFunctionByName(arena, TestDefiningCFunction.class, "upcallReturnIntNoParam0");
        int i = DefiningCFunction.get().upcallReturnIntNoParam(env, func);
        assertEquals(98, i);
    }

    public static long upcallReturnLongNoParam0() {
        return 97L;
    }

    @Test
    public void upcallReturnLongNoParam() {
        var func = PanamaUtils.defineCFunctionByName(arena, TestDefiningCFunction.class, "upcallReturnLongNoParam0");
        long l = DefiningCFunction.get().upcallReturnLongNoParam(env, func);
        assertEquals(97L, l);
    }

    public static short upcallReturnShortNoParam0() {
        return (short) 96;
    }

    @Test
    public void upcallReturnShortNoParam() {
        var func = PanamaUtils.defineCFunctionByName(arena, TestDefiningCFunction.class, "upcallReturnShortNoParam0");
        short s = DefiningCFunction.get().upcallReturnShortNoParam(env, func);
        assertEquals((short) 96, s);
    }

    public static MemorySegment upcallReturnPointerNoParam0() {
        var mem = arena.allocate(1);
        mem.set(ValueLayout.JAVA_BYTE, 0, (byte) 95);
        return mem;
    }

    @Test
    public void upcallReturnPointerNoParam() {
        var func = PanamaUtils.defineCFunctionByName(arena, TestDefiningCFunction.class, "upcallReturnPointerNoParam0");
        MemorySegment mem = DefiningCFunction.get().upcallReturnPointerNoParam(env, func);
        assertEquals((byte) 95, mem.reinterpret(1).get(ValueLayout.JAVA_BYTE, 0));
    }

    public static byte upcallReturnByte1Param0(byte b) {
        return (byte) (99 - b);
    }

    @Test
    public void upcallReturnByte1Param() {
        var func = PanamaUtils.defineCFunctionByName(arena, TestDefiningCFunction.class, "upcallReturnByte1Param0");
        byte b = DefiningCFunction.get().upcallReturnByte1Param(env, func, (byte) 10);
        assertEquals((byte) 89, b);
    }

    public static boolean upcallReturnBool1Param0(boolean z) {
        return !z;
    }

    @Test
    public void upcallReturnBool1Param() {
        var func = PanamaUtils.defineCFunctionByName(arena, TestDefiningCFunction.class, "upcallReturnBool1Param0");
        boolean b = DefiningCFunction.get().upcallReturnBool1Param(env, func, true);
        assertFalse(b);
    }

    public static char upcallReturnChar1Param0(char c) {
        return (char) (c + 1);
    }

    @Test
    public void upcallReturnChar1Param() {
        var func = PanamaUtils.defineCFunctionByName(arena, TestDefiningCFunction.class, "upcallReturnChar1Param0");
        char c = DefiningCFunction.get().upcallReturnChar1Param(env, func, 'Y');
        assertEquals('Z', c);
    }

    public static double upcallReturnDouble1Param0(double d) {
        return 10.24 / d;
    }

    @Test
    public void upcallReturnDouble1Param() {
        var func = PanamaUtils.defineCFunctionByName(arena, TestDefiningCFunction.class, "upcallReturnDouble1Param0");
        double d = DefiningCFunction.get().upcallReturnDouble1Param(env, func, 2);
        assertEquals(5.12, d, 0);
    }

    public static float upcallReturnFloat1Param0(float f) {
        return 81.92f / f;
    }

    @Test
    public void upcallReturnFloat1Param() {
        var func = PanamaUtils.defineCFunctionByName(arena, TestDefiningCFunction.class, "upcallReturnFloat1Param0");
        float f = DefiningCFunction.get().upcallReturnFloat1Param(env, func, 4);
        assertEquals(20.48f, f, 0);
    }

    public static int upcallReturnInt1Param0(int n) {
        return 98 - n;
    }

    @Test
    public void upcallReturnInt1Param() {
        var func = PanamaUtils.defineCFunctionByName(arena, TestDefiningCFunction.class, "upcallReturnInt1Param0");
        int i = DefiningCFunction.get().upcallReturnInt1Param(env, func, 11);
        assertEquals(87, i);
    }

    public static long upcallReturnLong1Param0(long l) {
        return 97L - l;
    }

    @Test
    public void upcallReturnLong1Param() {
        var func = PanamaUtils.defineCFunctionByName(arena, TestDefiningCFunction.class, "upcallReturnLong1Param0");
        long l = DefiningCFunction.get().upcallReturnLong1Param(env, func, 12L);
        assertEquals(85L, l);
    }

    public static short upcallReturnShort1Param0(short s) {
        return (short) (96 - s);
    }

    @Test
    public void upcallReturnShort1Param() {
        var func = PanamaUtils.defineCFunctionByName(arena, TestDefiningCFunction.class, "upcallReturnShort1Param0");
        short s = DefiningCFunction.get().upcallReturnShort1Param(env, func, (short) 13);
        assertEquals((short) 83, s);
    }

    public static MemorySegment upcallReturnPointer1Param0(MemorySegment mem) {
        mem = mem.reinterpret(1);
        mem.set(ValueLayout.JAVA_BYTE, 0, (byte) (95 - mem.get(ValueLayout.JAVA_BYTE, 0)));
        return mem;
    }

    @Test
    public void upcallReturnPointer1Param() {
        var func = PanamaUtils.defineCFunctionByName(arena, TestDefiningCFunction.class, "upcallReturnPointer1Param0");
        try (var allocator = Allocator.ofConfined()) {
            var data = allocator.allocate(1);
            data.set(ValueLayout.JAVA_BYTE, 0, (byte) 14);
            MemorySegment mem = DefiningCFunction.get().upcallReturnPointer1Param(env, func, data);
            assertEquals(data.address(), mem.address());
            assertEquals((byte) 81, mem.reinterpret(1).get(ValueLayout.JAVA_BYTE, 0));
        }
    }
}
