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

    @Impl(
        // language="c"
        c = """
            void (*f)(void*,int,void*) = func;
            return f(thread, a, p);
            """
    )
    @Critical
    void invokePtr(MemorySegment func, MemorySegment thread, int a, MemorySegment p);
}
