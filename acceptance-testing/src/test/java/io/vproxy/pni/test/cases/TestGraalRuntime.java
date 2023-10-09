package io.vproxy.pni.test.cases;

import io.vproxy.pni.graal.GraalUtils;
import org.graalvm.nativeimage.ImageInfo;
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
        System.out.println("inImageCode = " + ImageInfo.inImageCode());
        System.out.println("inImageRuntimeCode = " + ImageInfo.inImageRuntimeCode());
        System.out.println("inImageBuildtimeCode = " + ImageInfo.inImageBuildtimeCode());
        System.out.println("isExecutable = " + ImageInfo.isExecutable());
        System.out.println("isSharedLibrary = " + ImageInfo.isSharedLibrary());
    }
}
