package io.vproxy.pni.test;

import org.junit.runner.RunWith;

@org.junit.runners.Suite.SuiteClasses({
    TestTypes.class,
    TestCorner.class,
    TestTypesNoGen.class,
    TestDescParsing.class,
})
@RunWith(org.junit.runners.Suite.class)
public class Suite {
}
