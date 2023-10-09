package io.vproxy.pni.test.cases;

import io.vproxy.pni.test.Upcall;
import io.vproxy.pni.test.UpcallNull;
import io.vproxy.pni.test.impl.UpcallImpl;
import io.vproxy.pni.test.impl.UpcallNullImpl;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestBeforeAll {
    @BeforeClass
    public static void beforeClass() {
        TestUtils.loadLib();
        Upcall.setImpl(UpcallImpl.get());
        UpcallNull.setImpl(UpcallNullImpl.get());
    }

    @Test
    public void dummy() {
    }
}
