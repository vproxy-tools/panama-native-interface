package io.vproxy.pni.test.cases;

import io.vproxy.pni.test.Upcall;
import io.vproxy.pni.test.impl.UpcallImpl;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestBeforeAll {
    @BeforeClass
    public static void beforeClass() {
        System.loadLibrary("pnitest");
        Upcall.setImpl(UpcallImpl.get());
    }

    @Test
    public void dummy() {
    }
}
