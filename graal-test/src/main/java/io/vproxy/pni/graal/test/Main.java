package io.vproxy.pni.graal.test;

import io.vproxy.pni.graal.GraalUtils;
import org.junit.internal.TextListener;
import org.junit.runner.Description;
import org.junit.runner.JUnitCore;
import org.junit.runner.notification.RunListener;

public class Main {
    public static void main(String[] args) {
        System.loadLibrary("graaltest");

        var core = new JUnitCore();
        core.addListener(new TextListener(System.out));
        core.addListener(new RunListener() {
            @Override
            public void testRunStarted(Description description) {
                GraalUtils.setThread();
            }
        });
        var res = core.run(Suite.class);

        if (res.wasSuccessful()) {
            System.exit(0);
        } else {
            System.exit(1);
        }
    }
}
