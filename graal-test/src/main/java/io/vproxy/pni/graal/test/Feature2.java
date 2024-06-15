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
            TestUpcall.class,

            io.vproxy.pni.test.cases.Suite.class,
            io.vproxy.pni.test.cases.TestAlign.class,
            io.vproxy.pni.test.cases.TestAlwaysAligned.class,
            io.vproxy.pni.test.cases.TestBeforeAll.class,
            io.vproxy.pni.test.cases.TestDefiningCFunction.class,
            io.vproxy.pni.test.cases.TestFunc.class,
            io.vproxy.pni.test.cases.TestGCCCompatibility.class,
            io.vproxy.pni.test.cases.TestGeneratedFileFormat.class,
            io.vproxy.pni.test.cases.TestGeneric.class,
            io.vproxy.pni.test.cases.TestGraalRuntime.class,
            io.vproxy.pni.test.cases.TestNull.class,
            io.vproxy.pni.test.cases.TestObjectStruct.class,
            io.vproxy.pni.test.cases.TestPrimitiveStruct.class,
            io.vproxy.pni.test.cases.TestRawArrays.class,
            io.vproxy.pni.test.cases.TestRefAndFunc.class,
            io.vproxy.pni.test.cases.TestSizeof.class,
            io.vproxy.pni.test.cases.TestStructUnion.class,
            io.vproxy.pni.test.cases.TestStructUnionAnnotation.class,
            io.vproxy.pni.test.cases.TestSuperClass.class,
            io.vproxy.pni.test.cases.TestToString.class,
            io.vproxy.pni.test.cases.TestUpcall.class,
            io.vproxy.pni.test.cases.TestUpcallNull.class,
            io.vproxy.pni.test.cases.TestCustomNativeType.class,
            io.vproxy.pni.test.cases.TestNoAlloc.class,
            io.vproxy.pni.test.cases.TestPointerArray.class,
            io.vproxy.pni.test.cases.TestKt.class,
            io.vproxy.pni.test.cases.TestPointerOnly.class,
            io.vproxy.pni.test.cases.TestAllowHeapAccess.class,
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
