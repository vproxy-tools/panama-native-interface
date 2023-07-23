package io.vproxy.pni.test.cases;

import org.junit.runner.RunWith;

@RunWith(org.junit.runners.Suite.class)
@org.junit.runners.Suite.SuiteClasses({
    TestPrimitiveStruct.class,
    TestObjectStruct.class,
    TestStructUnion.class,
})
public class Suite {
}
