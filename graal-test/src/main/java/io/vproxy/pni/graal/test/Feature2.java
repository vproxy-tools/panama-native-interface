package io.vproxy.pni.graal.test;

import org.graalvm.nativeimage.hosted.Feature;
import org.graalvm.nativeimage.hosted.RuntimeClassInitialization;
import org.graalvm.nativeimage.hosted.RuntimeReflection;

public class Feature2 implements Feature {
    @Override
    public void duringSetup(DuringSetupAccess access) {
        RuntimeClassInitialization.initializeAtBuildTime(TestFunctionPointer.class);

        Class<?>[] classes = {
            org.junit.runners.Suite.class,
            Suite.class,
            TestFunctionPointer.class,
            TestRefAndFunc.class,
        };

        for (var c : classes) {
            RuntimeReflection.registerAllDeclaredConstructors(c);
            RuntimeReflection.registerAllDeclaredFields(c);
            RuntimeReflection.registerAllDeclaredMethods(c);
            for (var cons : c.getConstructors()) {
                RuntimeReflection.register(cons);
            }
            for (var meth : c.getMethods()) {
                RuntimeReflection.register(meth);
            }
            for (var field : c.getFields()) {
                RuntimeReflection.register(field);
            }
        }
    }
}
