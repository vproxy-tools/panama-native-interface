package io.vproxy.pni.graal.test;

import io.vproxy.pni.annotation.Critical;
import io.vproxy.pni.annotation.Function;
import io.vproxy.pni.annotation.Impl;

import java.lang.foreign.MemorySegment;

@Function
public interface PNIInvoke {
    @Impl(
        // language="c"
        c = """
            int (*f)(void*,int,int) = func;
            return f(thread, a, b);
            """
    )
    @Critical
    int invokeSum(MemorySegment func, MemorySegment thread, int a, int b);
}
