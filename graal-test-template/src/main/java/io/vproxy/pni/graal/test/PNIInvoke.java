package io.vproxy.pni.graal.test;

import io.vproxy.pni.PNIFunc;
import io.vproxy.pni.PNIRef;
import io.vproxy.pni.annotation.Critical;
import io.vproxy.pni.annotation.Function;
import io.vproxy.pni.annotation.Impl;
import io.vproxy.pni.annotation.Raw;

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

    @Impl(
        // language="c"
        c = """
            PNIRefRelease(ref);
            """
    )
    @Critical
    void releaseRef(@Raw PNIRef<Integer> ref);

    @Impl(
        // language="c"
        c = """
            int res = PNIFuncInvoke(func, NULL);
            PNIFuncRelease(func);
            return res;
            """
    )
    @Critical
    int callFunc(PNIFunc<Void> func);

    @Impl(
        // language="c"
        c = """
            void (*f)(void) = func;
            f();
            """
    )
    @Critical
    void invokeDoNothingUpcall(MemorySegment func);

    @Impl(
        // language="c"
        c = """
            int (*f)(int) = func;
            return f(a);
            """
    )
    @Critical
    int invokeIntUpcall(MemorySegment func, int a);

    @Impl(
        // language="c"
        c = """
            int (*f)(PNIRef*) = func;
            int res = f(ref);
            PNIRefRelease(ref);
            return res;
            """
    )
    @Critical
    int invokeRefUpcall(MemorySegment func, PNIRef<Integer> ref);

    @Impl(
        // language="c"
        c = """
            int (*f)(PNIFunc*) = func;
            int res = f(ff);
            PNIFuncRelease(ff);
            return res;
            """
    )
    @Critical
    int invokeFuncUpcall(MemorySegment func, PNIFunc<Void> ff);

    @Impl(
        // language="c"
        c = """
            void* (*f)() = func;
            return f();
            """
    )
    @Critical
    MemorySegment invokeReturnSegUpcall(MemorySegment func);
}
