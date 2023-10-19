package io.vproxy.pni.test.cases;

import org.junit.runner.RunWith;

@RunWith(org.junit.runners.Suite.class)
@org.junit.runners.Suite.SuiteClasses({
    TestBeforeAll.class,
    TestPrimitiveStruct.class,
    TestObjectStruct.class,
    TestStructUnion.class,
    TestFunc.class,
    TestStructUnionAnnotation.class,
    TestGCCCompatibility.class,
    TestRawArrays.class,
    TestNull.class,
    TestGeneratedFileFormat.class,
    TestDefiningCFunction.class,
    TestRefAndFunc.class,
    TestGeneric.class,
    TestUpcall.class,
    TestUpcallNull.class,
    TestSizeof.class,
    TestSuperClass.class,
    TestAlwaysAligned.class,
    TestAlign.class,
    TestToString.class,
    TestGraalRuntime.class,
    TestCustomNativeType.class,
    TestNoAlloc.class,
})
public class Suite {
}
