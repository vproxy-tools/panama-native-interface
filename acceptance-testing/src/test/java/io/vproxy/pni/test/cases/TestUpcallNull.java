package io.vproxy.pni.test.cases;

import io.vproxy.pni.test.InvokeUpcallNull;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TestUpcallNull {
    @Test
    public void param() {
        assertTrue(InvokeUpcallNull.get().testParam());
    }

    @Test
    public void paramRaw() {
        assertTrue(InvokeUpcallNull.get().testParamRaw());
    }

    @Test
    public void returnO() {
        assertTrue(InvokeUpcallNull.get().returnO());
    }

    @Test
    public void returnStr() {
        assertTrue(InvokeUpcallNull.get().returnStr());
    }

    @Test
    public void returnSeg() {
        assertTrue(InvokeUpcallNull.get().returnSeg());
    }

    @Test
    public void returnBuf() {
        assertTrue(InvokeUpcallNull.get().returnBuf());
    }

    @Test
    public void returnByteArr() {
        assertTrue(InvokeUpcallNull.get().returnByteArr());
    }

    @Test
    public void returnBoolArr() {
        assertTrue(InvokeUpcallNull.get().returnBoolArr());
    }

    @Test
    public void returnCharArr() {
        assertTrue(InvokeUpcallNull.get().returnCharArr());
    }

    @Test
    public void returnFloatArr() {
        assertTrue(InvokeUpcallNull.get().returnFloatArr());
    }

    @Test
    public void returnDoubleArr() {
        assertTrue(InvokeUpcallNull.get().returnDoubleArr());
    }

    @Test
    public void returnIntArr() {
        assertTrue(InvokeUpcallNull.get().returnIntArr());
    }

    @Test
    public void returnLongArr() {
        assertTrue(InvokeUpcallNull.get().returnLongArr());
    }

    @Test
    public void returnShortArr() {
        assertTrue(InvokeUpcallNull.get().returnShortArr());
    }

    @Test
    public void returnOArr() {
        assertTrue(InvokeUpcallNull.get().returnOArr());
    }

    @Test
    public void returnRef() {
        assertTrue(InvokeUpcallNull.get().returnRef());
    }

    @Test
    public void returnFunc() {
        assertTrue(InvokeUpcallNull.get().returnFunc());
    }

    @Test
    public void returnFuncVoid() {
        assertTrue(InvokeUpcallNull.get().returnFuncVoid());
    }

    @Test
    public void returnFuncRef() {
        assertTrue(InvokeUpcallNull.get().returnFuncRef());
    }
}
