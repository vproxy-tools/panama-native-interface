package io.vproxy.pni.graal.test;

import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;

public class Main {
    public static void main(String[] args) {
        System.loadLibrary("graaltest");

        var core = new JUnitCore();
        core.addListener(new TextListener(System.out));
        var res = core.run(Suite.class);

        if (res.wasSuccessful()) {
            System.exit(0);
        } else {
            System.exit(1);
        }
    }
}
