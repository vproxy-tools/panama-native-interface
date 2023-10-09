package io.vproxy.pni.graal.test;

import org.junit.runner.RunWith;

@RunWith(org.junit.runners.Suite.class)
@org.junit.runners.Suite.SuiteClasses({
    TestFunctionPointer.class,
    TestRefAndFunc.class,
    TestUpcall.class,
})
public class Suite {
}
