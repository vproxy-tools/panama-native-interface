package io.vproxy.pni.test.cases;

import org.junit.runner.RunWith;

@RunWith(org.junit.runners.Suite.class)
@org.junit.runners.Suite.SuiteClasses({
    TestPrimitiveStruct.class,
    TestObjectStruct.class,
    TestStructUnion.class,
    TestFunc.class,
    TestStructUnionAnnotation.class,
    TestGCCCompatibility.class,
    TestRawArrays.class,
    TestNull.class,
    TestGeneratedFileFormat.class,
})
public class Suite {
}
