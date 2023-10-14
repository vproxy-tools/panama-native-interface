package io.vproxy.pni.test.cases;

import io.vproxy.pni.graal.GraalUtils;
import io.vproxy.r.org.graalvm.nativeimage.ImageInfoDelegate;
import org.junit.Test;

public class TestGraalRuntime {
    @Test
    public void init() {
        GraalUtils.init();
    }

    @Test
    public void setThread() {
        GraalUtils.setThread();
    }

    @Test
    public void imageInfo() {
        System.out.println("inImageCode = " + ImageInfoDelegate.inImageCode());
        System.out.println("inImageRuntimeCode = " + ImageInfoDelegate.inImageRuntimeCode());
        System.out.println("inImageBuildtimeCode = " + ImageInfoDelegate.inImageBuildtimeCode());
        System.out.println("isExecutable = " + ImageInfoDelegate.isExecutable());
        System.out.println("isSharedLibrary = " + ImageInfoDelegate.isSharedLibrary());
    }
}
