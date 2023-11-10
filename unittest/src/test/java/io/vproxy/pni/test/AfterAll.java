package io.vproxy.pni.test;

import org.junit.Test;

public class AfterAll {
    @Test
    public void last() throws Exception {
        System.gc();
        Thread.sleep(2_000);
    }
}
