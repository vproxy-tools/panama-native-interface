package io.vproxy.pni.graal.test;

import io.vproxy.base.util.LogType;
import io.vproxy.base.util.Logger;
import io.vproxy.pni.graal.GraalUtils;
import org.junit.runner.Description;
import org.junit.runner.JUnitCore;
import org.junit.runner.notification.RunListener;

public class Main {
    public static void main(String[] args) {
        System.loadLibrary("graaltest");

        var core = new JUnitCore();
        core.addListener(new RunListener() {
            @Override
            public void testStarted(Description description) {
                var clsName = description.getClassName();
                if (clsName.contains(".")) {
                    clsName = clsName.substring(clsName.lastIndexOf(".") + 1);
                }
                Logger.warn(LogType.ALERT, clsName + "::" + description.getMethodName() + " START");
                GraalUtils.setThread();
            }

            @Override
            public void testFinished(Description description) {
                var clsName = description.getClassName();
                if (clsName.contains(".")) {
                    clsName = clsName.substring(clsName.lastIndexOf(".") + 1);
                }
                Logger.alert(clsName + "::" + description.getMethodName() + " FINISH");
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
