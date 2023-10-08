package io.vproxy.pni.graal.test;

import io.vproxy.pni.PNIFunc;
import io.vproxy.pni.PNIRef;
import io.vproxy.pni.graal.GraalUtils;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestRefAndFunc {
    @Before
    public void setUp() {
        GraalUtils.setThread();
    }

    @Test
    public void ref() {
        var count = PNIRef.currentRefStorageSize();
        var ref = PNIRef.of(123);
        Invoke.get().releaseRef(ref);
        assertEquals(count, PNIRef.currentRefStorageSize());
    }

    @Test
    public void func() {
        var count = PNIFunc.currentFuncStorageSize();
        var res = Invoke.get().callFunc(v -> 123);
        assertEquals(123, res);
        assertEquals(count, PNIFunc.currentFuncStorageSize());
    }
}
