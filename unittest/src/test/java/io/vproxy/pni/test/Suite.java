package io.vproxy.pni.test;

import org.junit.runner.RunWith;

@org.junit.runners.Suite.SuiteClasses({
    TestTypes.class,
    TestCorner.class,
    TestTypesNoGen.class,
    TestDescParsing.class,
    TestObjectHolder.class,
    TestValidation.class,
    TestAllocator.class,
    TestWarningFlags.class,
    TestRef.class,
    TestUtils.class,
    TestCompilationFlags.class,
    AfterAll.class
})
@RunWith(org.junit.runners.Suite.class)
public class Suite {
}
