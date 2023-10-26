package io.vproxy.pni.graal.test;

import io.vproxy.pni.PNIFunc;
import io.vproxy.pni.PNIRef;
import io.vproxy.pni.annotation.*;

import java.lang.foreign.MemorySegment;

@Downcall
public interface PNIInvoke {
    @Impl(
        // language="c"
        c = """
            int (*f)(void*,int,int) = func;
            return f(thread, a, b);
            """
    )
    @Style(Styles.critical)
    int invokeSum(MemorySegment func, MemorySegment thread, int a, int b);

    @Impl(
        // language="c"
        c = """
            void (*f)(void*,int,void*) = func;
            return f(thread, a, p);
            """
    )
    @Style(Styles.critical)
    void invokePtr(MemorySegment func, MemorySegment thread, int a, MemorySegment p);

    @Impl(
        // language="c"
        c = """
            PNIRefRelease(ref);
            """
    )
    @Style(Styles.critical)
    void releaseRef(@Raw PNIRef<Integer> ref);

    @Impl(
        // language="c"
        c = """
            int res = PNIFuncInvoke(func, NULL);
            PNIFuncRelease(func);
            return res;
            """
    )
    @Style(Styles.critical)
    int callFunc(PNIFunc<Void> func);

    @Impl(
        // language="c"
        c = """
            void (*f)(void) = func;
            f();
            """
    )
    @Style(Styles.critical)
    void invokeDoNothingUpcall(MemorySegment func);

    @Impl(
        // language="c"
        c = """
            int (*f)(int) = func;
            return f(a);
            """
    )
    @Style(Styles.critical)
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
    @Style(Styles.critical)
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
    @Style(Styles.critical)
    int invokeFuncUpcall(MemorySegment func, PNIFunc<Void> ff);

    @Impl(
        // language="c"
        c = """
            void* (*f)() = func;
            return f();
            """
    )
    @Style(Styles.critical)
    MemorySegment invokeReturnSegUpcall(MemorySegment func);
}
