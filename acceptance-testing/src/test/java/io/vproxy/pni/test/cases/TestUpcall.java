package io.vproxy.pni.test.cases;

import io.vproxy.pni.Allocator;
import io.vproxy.pni.PNIFunc;
import io.vproxy.pni.PNIRef;
import io.vproxy.pni.PNIString;
import io.vproxy.pni.array.*;
import io.vproxy.pni.test.InvokeUpcall;
import io.vproxy.pni.test.ObjectStruct;
import io.vproxy.pni.test.impl.UpcallImpl;
import org.junit.Test;

import java.lang.foreign.ValueLayout;
import java.nio.ByteBuffer;
import java.util.List;

import static org.junit.Assert.*;

public class TestUpcall {
    @Test
    public void primaryParams() {
        InvokeUpcall.get()
            .primaryParams((byte) 11, (byte) -12,
                true,
                'x',
                3.2,
                1.6f,
                13, -14,
                15, -16,
                (short) 17, (short) -18);
        assertEquals((byte) 11, UpcallImpl.get().b);
        assertEquals((byte) -12, UpcallImpl.get().ub);
        assertTrue(UpcallImpl.get().z);
        assertEquals('x', UpcallImpl.get().c);
        assertEquals(3.2, UpcallImpl.get().d, 0);
        assertEquals(1.6f, UpcallImpl.get().f, 0);
        assertEquals(13, UpcallImpl.get().i);
        assertEquals(-14, UpcallImpl.get().ui);
        assertEquals(15, UpcallImpl.get().j);
        assertEquals(-16, UpcallImpl.get().uj);
        assertEquals((short) 17, UpcallImpl.get().s);
        assertEquals((short) -18, UpcallImpl.get().us);
    }

    @Test
    public void returnByte() {
        var res = InvokeUpcall.get().returnByte();
        assertEquals(21, res);
    }

    @Test
    public void returnBool() {
        var res = InvokeUpcall.get().returnBool();
        assertTrue(res);
    }

    @Test
    public void returnChar() {
        var res = InvokeUpcall.get().returnChar();
        assertEquals('a', res);
    }

    @Test
    public void returnDouble() {
        var res = InvokeUpcall.get().returnDouble();
        assertEquals(12.8, res, 0);
    }

    @Test
    public void returnFloat() {
        var res = InvokeUpcall.get().returnFloat();
        assertEquals(6.4f, res, 0);
    }

    @Test
    public void returnInt() {
        var res = InvokeUpcall.get().returnInt();
        assertEquals(22, res);
    }

    @Test
    public void returnLong() {
        var res = InvokeUpcall.get().returnLong();
        assertEquals(23, res);
    }

    @Test
    public void returnShort() {
        var res = InvokeUpcall.get().returnShort();
        assertEquals(24, res);
    }

    @Test
    public void primaryArrayParams() {
        try (var allocator = Allocator.ofConfined()) {
            // 1 2 3
            var ab = allocator.allocate(3);
            for (int i = 0; i < 3; ++i) {
                ab.set(ValueLayout.JAVA_BYTE, i, (byte) (1 + i));
            }
            // -(4 5 6 7)
            var aub = allocator.allocate(4);
            for (int i = 0; i < 4; ++i) {
                aub.set(ValueLayout.JAVA_BYTE, i, (byte) (-4 - i));
            }
            var az = new BoolArray(allocator, 5);
            for (int i = 0; i < 5; ++i) {
                az.set(i, i % 2 == 0);
            }
            var ac = new CharArray(allocator, 6);
            for (int i = 0; i < 6; ++i) {
                ac.set(i, (char) ('a' + i));
            }
            var ad = new DoubleArray(allocator, 7);
            for (int i = 0; i < 7; ++i) {
                ad.set(i, 1.6 * Math.pow(2, i));
            }
            var af = new FloatArray(allocator, 8);
            for (int i = 0; i < 8; ++i) {
                af.set(i, (float) (20.48 * Math.pow(2, i)));
            }
            // 8 9 10 11 12 13 14 15 16
            var ai = new IntArray(allocator, 9);
            for (int i = 0; i < 9; ++i) {
                ai.set(i, 8 + i);
            }
            // -(17 18 19 20 21 22 23 24 25 26)
            var aui = new IntArray(allocator, 10);
            for (int i = 0; i < 10; ++i) {
                aui.set(i, -17 - i);
            }
            // 27 28 29 30 31 32 33 34 35 36 37
            var aj = new LongArray(allocator, 11);
            for (int i = 0; i < 11; ++i) {
                aj.set(i, 27 + i);
            }
            // -(38 39 40 41 42 43 44 45 46 47 48 49)
            var auj = new LongArray(allocator, 12);
            for (int i = 0; i < 12; ++i) {
                auj.set(i, -38 - i);
            }
            // 50 51 52 53 54 55 56 57 58 59 60 61 62
            var as = new ShortArray(allocator, 13);
            for (int i = 0; i < 13; ++i) {
                as.set(i, (short) (50 + i));
            }
            // 63 64 65 66 67 68 69 70 71 72 73 74 75 76
            var aus = new ShortArray(allocator, 14);
            for (int i = 0; i < 14; ++i) {
                aus.set(i, (short) (-63 - i));
            }
            InvokeUpcall.get().primaryArrayParams(
                ab, aub, az, ac, ad, af, ai, aui, aj, auj, as, aus
            );
        }

        var impl = UpcallImpl.get();
        assertArrayEquals(new byte[]{1, 2, 3}, impl.ab);
        assertArrayEquals(new byte[]{-4, -5, -6, -7}, impl.aub);
        assertArrayEquals(new boolean[]{true, false, true, false, true}, impl.az);
        assertArrayEquals(new char[]{'a', 'b', 'c', 'd', 'e', 'f'}, impl.ac);
        assertArrayEquals(new double[]{1.6, 3.2, 6.4, 12.8, 25.6, 51.2, 102.4}, impl.ad, 0);
        assertArrayEquals(new float[]{20.48f, 40.96f, 81.92f, 163.84f, 327.68f, 655.36f, 1310.72f, 2621.44f}, impl.af, 0);
        assertArrayEquals(new int[]{8, 9, 10, 11, 12, 13, 14, 15, 16}, impl.ai);
        assertArrayEquals(new int[]{-17, -18, -19, -20, -21, -22, -23, -24, -25, -26}, impl.aui);
        assertArrayEquals(new long[]{27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37}, impl.aj);
        assertArrayEquals(new long[]{-38, -39, -40, -41, -42, -43, -44, -45, -46, -47, -48, -49}, impl.auj);
        assertArrayEquals(new short[]{50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62}, impl.as);
        assertArrayEquals(new short[]{-63, -64, -65, -66, -67, -68, -69, -70, -71, -72, -73, -74, -75, -76}, impl.aus);
    }

    @Test
    public void returnByteArray() {
        var res = InvokeUpcall.get().returnByteArray();
        assertEquals(3, res.byteSize());
        assertEquals(11, res.get(ValueLayout.JAVA_BYTE, 0));
        assertEquals(22, res.get(ValueLayout.JAVA_BYTE, 1));
        assertEquals(33, res.get(ValueLayout.JAVA_BYTE, 2));
    }

    @Test
    public void returnBoolArray() {
        var res = InvokeUpcall.get().returnBoolArray();
        assertEquals(4, res.length());
        assertTrue(res.get(0));
        assertFalse(res.get(1));
        assertTrue(res.get(2));
        assertFalse(res.get(3));
    }

    @Test
    public void returnCharArray() {
        var res = InvokeUpcall.get().returnCharArray();
        assertEquals(5, res.length());
        assertEquals('m', res.get(0));
        assertEquals('n', res.get(1));
        assertEquals('o', res.get(2));
        assertEquals('p', res.get(3));
        assertEquals('q', res.get(4));
    }

    @Test
    public void returnDoubleArray() {
        var res = InvokeUpcall.get().returnDoubleArray();
        assertEquals(6, res.length());
        assertEquals(0.2, res.get(0), 0);
        assertEquals(0.4, res.get(1), 0);
        assertEquals(0.8, res.get(2), 0);
        assertEquals(1.6, res.get(3), 0);
        assertEquals(3.2, res.get(4), 0);
        assertEquals(6.4, res.get(5), 0);
    }

    @Test
    public void returnFloatArray() {
        var res = InvokeUpcall.get().returnFloatArray();
        assertEquals(7, res.length());
        assertEquals(12.8, res.get(0), 0.0001);
        assertEquals(25.6, res.get(1), 0.0001);
        assertEquals(51.2, res.get(2), 0.0001);
        assertEquals(102.4, res.get(3), 0.0001);
        assertEquals(204.8, res.get(4), 0.0001);
        assertEquals(409.6, res.get(5), 0.0001);
        assertEquals(819.2, res.get(6), 0.0001);
    }

    @Test
    public void returnIntArray() {
        var res = InvokeUpcall.get().returnIntArray();
        assertEquals(8, res.length());
        assertEquals(12, res.get(0));
        assertEquals(23, res.get(1));
        assertEquals(34, res.get(2));
        assertEquals(45, res.get(3));
        assertEquals(56, res.get(4));
        assertEquals(67, res.get(5));
        assertEquals(78, res.get(6));
        assertEquals(89, res.get(7));
    }

    @Test
    public void returnLongArray() {
        var res = InvokeUpcall.get().returnLongArray();
        assertEquals(9, res.length());
        assertEquals(910, res.get(0));
        assertEquals(1011, res.get(1));
        assertEquals(1112, res.get(2));
        assertEquals(1213, res.get(3));
        assertEquals(1314, res.get(4));
        assertEquals(1415, res.get(5));
        assertEquals(1516, res.get(6));
        assertEquals(1617, res.get(7));
        assertEquals(1718, res.get(8));
    }

    @Test
    public void returnShortArray() {
        var res = InvokeUpcall.get().returnShortArray();
        assertEquals(10, res.length());
        assertEquals(1920, res.get(0));
        assertEquals(2021, res.get(1));
        assertEquals(2122, res.get(2));
        assertEquals(2223, res.get(3));
        assertEquals(2324, res.get(4));
        assertEquals(2425, res.get(5));
        assertEquals(2526, res.get(6));
        assertEquals(2627, res.get(7));
        assertEquals(2728, res.get(8));
        assertEquals(2829, res.get(9));
    }

    @Test
    public void objectParams() {
        try (var allocator = Allocator.ofConfined()) {
            var o = new ObjectStruct(allocator);
            o.setLenStr("hello world");
            InvokeUpcall.get().objectParams(o);
            var impl = UpcallImpl.get();
            assertEquals("hello world", impl.objLenStr);
        }
    }

    @Test
    public void returnObject() {
        try (var allocator = Allocator.ofConfined()) {
            var o = InvokeUpcall.get().returnObject(allocator);
            assertEquals("hello", o.getLenStr());
        }
    }

    @Test
    public void objectArrayParams() {
        try (var allocator = Allocator.ofConfined()) {
            var res = new ObjectStruct.Array(allocator, 3);
            res.get(0).setLenStr("world");
            res.get(1).setLenStr("hello");
            res.get(2).setLenStr("hello world");
            InvokeUpcall.get().objectArrayParams(res);

            assertEquals("world", UpcallImpl.get().objectRawArray[0]);
            assertEquals("hello", UpcallImpl.get().objectRawArray[1]);
            assertEquals("hello world", UpcallImpl.get().objectRawArray[2]);
        }
    }

    @Test
    public void returnObjectArray() {
        var res = InvokeUpcall.get().returnObjectArray();
        assertEquals("hello", res.get(0).getLenStr());
        assertEquals("world", res.get(1).getLenStr());
        assertEquals("hello world", res.get(2).getLenStr());
    }

    @Test
    public void otherParams() {
        var buf = ByteBuffer.allocateDirect(10);
        buf.put(new byte[]{22, 33, 44});
        buf.flip();
        var allocator = Allocator.ofConfined();
        var mem = allocator.allocate(10);
        mem.setUtf8String(0, "aabb");
        boolean[] voidCallSiteCalled = {false};
        boolean[] objCallSiteCalled = {false};
        boolean[] refCallSiteCalled = {false};
        boolean[] voidFuncCalled = {false};
        boolean[] objFuncCalled = {false};
        boolean[] refFuncCalled = {false};
        var oLs = List.of(1, 2, 3);
        InvokeUpcall.get().otherParams(buf, v -> {
            voidCallSiteCalled[0] = true;
            return 1122;
        }, o -> {
            objCallSiteCalled[0] = true;
            return 2233;
        }, ls -> {
            refCallSiteCalled[0] = true;
            return 3344;
        }, mem, PNIFunc.VoidFunc.of(v -> {
            voidFuncCalled[0] = true;
            return 4455;
        }), ObjectStruct.Func.of(v -> {
            objFuncCalled[0] = true;
            return 5566;
        }), PNIRef.Func.of(ls -> {
            refFuncCalled[0] = true;
            return 6677;
        }), oLs, PNIRef.of(oLs), new PNIString(allocator, "hi"));
        assertArrayEquals(new byte[]{22, 33, 44}, UpcallImpl.get()._buffer);
        assertEquals(1122, UpcallImpl.get().voidCallSiteRes);
        assertEquals(2233, UpcallImpl.get().objCallSiteRes);
        assertEquals(3344, UpcallImpl.get().refCallSiteRes);
        assertEquals(UpcallImpl.get()._mem.address(), mem.address());
        assertEquals("aabb", UpcallImpl.get()._mem.reinterpret(10).getUtf8String(0));
        assertEquals(4455, UpcallImpl.get().voidFuncRes);
        assertEquals(5566, UpcallImpl.get().objFuncRes);
        assertEquals(6677, UpcallImpl.get().refFuncRes);
        assertSame(oLs, UpcallImpl.get()._ref);
        assertSame(oLs, UpcallImpl.get()._rawRef);
        assertEquals("hi", UpcallImpl.get()._str);
        assertTrue(voidCallSiteCalled[0]);
        assertTrue(objCallSiteCalled[0]);
        assertTrue(refCallSiteCalled[0]);
        assertTrue(voidFuncCalled[0]);
        assertTrue(objFuncCalled[0]);
        assertTrue(refFuncCalled[0]);
    }

    @Test
    public void returnBuffer() {
        var buffer = InvokeUpcall.get().returnBuffer();
        assertEquals(6, buffer.limit());
        assertEquals(0, buffer.position());
        byte[] buf = new byte[6];
        buffer.get(buf);
        assertArrayEquals(new byte[]{1, 2, 3, 4, 5, 6}, buf);
    }

    @Test
    public void returnMem() {
        var mem = InvokeUpcall.get().returnMem();
        mem = mem.reinterpret(15);
        assertEquals("alice-bob-eve", mem.getUtf8String(0));
    }

    @Test
    public void returnVoidFunc() {
        var n = UpcallImpl.get().testReturnVoidFunc;
        var f = InvokeUpcall.get().returnVoidFunc();
        var res = f.getCallSite().call(null);
        assertEquals(10086, res);
        assertEquals(n + 1, UpcallImpl.get().testReturnVoidFunc);
    }

    @Test
    public void returnObjFunc() {
        try (var allocator = Allocator.ofConfined()) {
            var o = new ObjectStruct(allocator);
            o.setLenStr("obj");

            var f = InvokeUpcall.get().returnObjFunc();
            var res = f.getCallSite().call(o);
            assertEquals(10087, res);
            assertEquals("obj", UpcallImpl.get().testReturnObjFunc);
        }
    }

    @Test
    public void returnRefFunc() {
        var ls = List.of("alice", "bob");

        var f = InvokeUpcall.get().returnRefFunc();
        var res = f.getCallSite().call(ls);
        assertEquals(10088, res);
        assertSame(ls, UpcallImpl.get().testReturnRefFunc);
    }

    @Test
    public void returnRef() {
        var ls = InvokeUpcall.get().returnRef();
        assertEquals(List.of(11, 22, 33), ls.getRef());
    }

    @Test
    public void returnStr() {
        var str = InvokeUpcall.get().returnStr();
        assertEquals("str", str.toString());
    }

    @Test
    public void annotateNameSum() {
        assertEquals(12, InvokeUpcall.get().sum(3, 9));
        assertEquals(47, InvokeUpcall.get().sum(28, 19));
    }
}
