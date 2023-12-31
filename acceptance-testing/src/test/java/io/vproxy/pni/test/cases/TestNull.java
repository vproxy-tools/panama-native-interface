package io.vproxy.pni.test.cases;

import io.vproxy.pni.Allocator;
import io.vproxy.pni.PNIEnv;
import io.vproxy.pni.test.Empty;
import io.vproxy.pni.test.Null;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.*;

public class TestNull {
    @Test
    public void param() {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);
            var n = new Null(allocator);

            var b = n.testParam(env, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
            assertTrue(b);
            b = n.testParamCritical(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
            assertTrue(b);
        }
    }

    @Test
    public void rawParam() {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);
            var n = new Null(allocator);

            var b = n.testParamRaw(env, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
            assertTrue(b);
            b = n.testParamRawCritical(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
            assertTrue(b);
        }
    }

    @Test
    public void object() {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);
            var n = new Null(allocator);

            n.setO(null);
            assertNull(n.getO());
            assertNull(n.returnO(env, allocator));
            assertNull(n.returnOCritical(allocator));
        }
    }

    @Test
    public void str() {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);
            var n = new Null(allocator);

            n.setStr(null);
            assertNull(n.getStr());
            assertNull(n.returnStr(env));
            assertNull(n.returnStrCritical());
        }
    }

    @Test
    public void seg() {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);
            var n = new Null(allocator);

            n.setSeg(null);
            assertNull(n.getSeg());
            assertNull(n.returnSeg(env));
            assertNull(n.returnSegCritical());
        }
    }

    @Test
    public void byteBuffer() {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);
            var n = new Null(allocator);

            n.setBuf(null);
            assertNull(n.getBuf());
            assertNull(n.returnBuf(env));
            assertNull(n.returnBufCritical());
            assertNull(n.returnBufCritical2());
        }
    }

    @Test
    public void byteArr() {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);
            var n = new Null(allocator);

            n.setByteArr(null);
            assertNull(n.getByteArr());
            assertNull(n.returnByteArr(env));
            assertNull(n.returnByteArrCritical());
            assertNull(n.returnByteArrCritical2());
        }
    }

    @Test
    public void boolArr() {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);
            var n = new Null(allocator);

            n.setBoolArr(null);
            assertNull(n.getBoolArr());
            assertNull(n.returnBoolArr(env));
            assertNull(n.returnBoolArrCritical());
            assertNull(n.returnBoolArrCritical2());
        }
    }

    @Test
    public void charArr() {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);
            var n = new Null(allocator);

            n.setCharArr(null);
            assertNull(n.getCharArr());
            assertNull(n.returnCharArr(env));
            assertNull(n.returnCharArrCritical());
            assertNull(n.returnCharArrCritical2());
        }
    }

    @Test
    public void floatArr() {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);
            var n = new Null(allocator);

            n.setFloatArr(null);
            assertNull(n.getFloatArr());
            assertNull(n.returnFloatArr(env));
            assertNull(n.returnFloatArrCritical());
            assertNull(n.returnFloatArrCritical2());
        }
    }

    @Test
    public void doubleArr() {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);
            var n = new Null(allocator);

            n.setDoubleArr(null);
            assertNull(n.getDoubleArr());
            assertNull(n.returnDoubleArr(env));
            assertNull(n.returnDoubleArrCritical());
            assertNull(n.returnDoubleArrCritical2());
        }
    }

    @Test
    public void intArr() {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);
            var n = new Null(allocator);

            n.setIntArr(null);
            assertNull(n.getIntArr());
            assertNull(n.returnIntArr(env));
            assertNull(n.returnIntArrCritical());
            assertNull(n.returnIntArrCritical2());
        }
    }

    @Test
    public void longArr() {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);
            var n = new Null(allocator);

            n.setLongArr(null);
            assertNull(n.getLongArr());
            assertNull(n.returnLongArr(env));
            assertNull(n.returnLongArrCritical());
            assertNull(n.returnLongArrCritical2());
        }
    }

    @Test
    public void shortArr() {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);
            var n = new Null(allocator);

            n.setShortArr(null);
            assertNull(n.getShortArr());
            assertNull(n.returnShortArr(env));
            assertNull(n.returnShortArrCritical());
            assertNull(n.returnShortArrCritical2());
        }
    }

    @Test
    public void oArr() {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);
            var n = new Null(allocator);

            n.setOArr(null);
            assertNull(n.getOArr());
            assertNull(n.returnOArr(env));
            assertNull(n.returnOArrCritical());
            assertNull(n.returnOArrCritical2());
        }
    }

    @Test
    public void ref() {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);
            var n = new Null(allocator);

            n.setRef(null);
            assertNull(n.getRef());
            assertNull(n.returnRef(env));
            assertNull(n.returnRefCritical());
        }
    }

    @Test
    public void func() {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);
            var n = new Null(allocator);

            n.setFunc(null);
            assertNull(n.getFunc());
            assertNull(n.returnFunc(env));
            assertNull(n.returnFuncCritical());
        }
    }

    @Test
    public void funcVoid() {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);
            var n = new Null(allocator);

            n.setFuncVoid(null);
            assertNull(n.getFuncVoid());
            assertNull(n.returnFuncVoid(env));
            assertNull(n.returnFuncVoidCritical());
        }
    }

    @Test
    public void funcRef() {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);
            var n = new Null(allocator);

            n.setFuncRef(null);
            assertNull(n.getFuncRef());
            assertNull(n.returnFuncRef(env));
            assertNull(n.returnFuncRefCritical());
        }
    }

    @Test
    public void empty() {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);
            var n = new Null(allocator);

            try (var dummy = Allocator.ofDummy()) {
                var empty = new Empty(dummy);

                var res = n.emptyPassThrough(env, empty, allocator);
                assertNull(res);

                res = n.emptyPassThroughCritical(empty, allocator);
                assertNull(res);
            }
        }
    }

    @Test
    public void toStringTest() {
        try (var allocator = Allocator.ofConfined()) {
            var n = new Null(allocator);
            assertEquals("Null{\n" +
                         "    o => null,\n" +
                         "    str => null,\n" +
                         "    seg => null,\n" +
                         "    buf => null,\n" +
                         "    byteArr => null,\n" +
                         "    boolArr => null,\n" +
                         "    charArr => null,\n" +
                         "    floatArr => null,\n" +
                         "    doubleArr => null,\n" +
                         "    intArr => null,\n" +
                         "    longArr => null,\n" +
                         "    shortArr => null,\n" +
                         "    pArr => null,\n" +
                         "    oArr => null,\n" +
                         "    ref => null,\n" +
                         "    func => null,\n" +
                         "    funcVoid => null,\n" +
                         "    funcRef => null\n" +
                         "}@" + Long.toString(n.MEMORY.address(), 16), n.toString());
        }
    }

    @Test
    public void shaCheck() throws Exception {
        var s = Files.readAllLines(Path.of("src", "test", "c-generated", "io_vproxy_pni_test_Empty.h"));
        var lastLine = s.get(s.size() - 1);
        assertEquals("// sha256:06c1e7dc355b3f3d0310d1dde826f6691de5dcd88a995a77cf075edde176b9ca", lastLine);
    }
}
