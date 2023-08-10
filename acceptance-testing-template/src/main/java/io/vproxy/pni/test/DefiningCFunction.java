package io.vproxy.pni.test;

import io.vproxy.pni.annotation.Function;
import io.vproxy.pni.annotation.Impl;

import java.lang.foreign.MemorySegment;

@Function
interface PNIDefiningCFunction {
    @Impl(
        // language="c"
        c = """
            void (*ff)() = func;
            ff();
            return 0;
            """
    )
    void upcallVoidNoParam(MemorySegment func);

    @Impl(
        // language="c"
        c = """
            void (*ff)(void*) = func;
            ff(data);
            return 0;
            """
    )
    void upcallVoid1Param(MemorySegment func, MemorySegment data);

    @Impl(
        // language="c"
        c = """
            void (*ff)(void*,int8_t) = func;
            ff(data, b);
            return 0;
            """
    )
    void upcallVoid2Param(MemorySegment func, MemorySegment data, byte b);

    @Impl(
        // language="c"
        c = """
            void (*ff)(void*,uint8_t,uint16_t) = func;
            ff(data, z, c);
            return 0;
            """
    )
    void upcallVoid3Param(MemorySegment func, MemorySegment data, boolean z, char c);

    @Impl(
        // language="c"
        c = """
            void (*ff)(void*,double,float,int32_t) = func;
            ff(data, d, f, i);
            return 0;
            """
    )
    void upcallVoid4Param(MemorySegment func, MemorySegment data, double d, float f, int i);

    @Impl(
        // language="c"
        c = """
            void (*ff)(void*,int64_t,int16_t) = func;
            ff(data, l, s);
            return 0;
            """
    )
    void upcallVoid3Param2(MemorySegment func, MemorySegment data, long l, short s);

    @Impl(
        // language="c"
        c = """
            int8_t (*ff)() = func;
            env->return_ = ff();
            return 0;
            """
    )
    byte upcallReturnByteNoParam(MemorySegment func);

    @Impl(
        // language="c"
        c = """
            uint8_t (*ff)() = func;
            env->return_ = ff();
            return 0;
            """
    )
    boolean upcallReturnBoolNoParam(MemorySegment func);

    @Impl(
        // language="c"
        c = """
            uint16_t (*ff)() = func;
            env->return_ = ff();
            return 0;
            """
    )
    char upcallReturnCharNoParam(MemorySegment func);

    @Impl(
        // language="c"
        c = """
            double (*ff)() = func;
            env->return_ = ff();
            return 0;
            """
    )
    double upcallReturnDoubleNoParam(MemorySegment func);

    @Impl(
        // language="c"
        c = """
            float (*ff)() = func;
            env->return_ = ff();
            return 0;
            """
    )
    float upcallReturnFloatNoParam(MemorySegment func);

    @Impl(
        // language="c"
        c = """
            int32_t (*ff)() = func;
            env->return_ = ff();
            return 0;
            """
    )
    int upcallReturnIntNoParam(MemorySegment func);

    @Impl(
        // language="c"
        c = """
            int64_t (*ff)() = func;
            env->return_ = ff();
            return 0;
            """
    )
    long upcallReturnLongNoParam(MemorySegment func);

    @Impl(
        // language="c"
        c = """
            int16_t (*ff)() = func;
            env->return_ = ff();
            return 0;
            """
    )
    short upcallReturnShortNoParam(MemorySegment func);

    @Impl(
        // language="c"
        c = """
            void* (*ff)() = func;
            env->return_ = ff();
            return 0;
            """
    )
    MemorySegment upcallReturnPointerNoParam(MemorySegment func);

    @Impl(
        // language="c"
        c = """
            int8_t (*ff)(int8_t) = func;
            env->return_ = ff(b);
            return 0;
            """
    )
    byte upcallReturnByte1Param(MemorySegment func, byte b);

    @Impl(
        // language="c"
        c = """
            uint8_t (*ff)(uint8_t) = func;
            env->return_ = ff(z);
            return 0;
            """
    )
    boolean upcallReturnBool1Param(MemorySegment func, boolean z);

    @Impl(
        // language="c"
        c = """
            uint16_t (*ff)(uint16_t) = func;
            env->return_ = ff(c);
            return 0;
            """
    )
    char upcallReturnChar1Param(MemorySegment func, char c);

    @Impl(
        // language="c"
        c = """
            double (*ff)(double) = func;
            env->return_ = ff(d);
            return 0;
            """
    )
    double upcallReturnDouble1Param(MemorySegment func, double d);

    @Impl(
        // language="c"
        c = """
            float (*ff)(float) = func;
            env->return_ = ff(f);
            return 0;
            """
    )
    float upcallReturnFloat1Param(MemorySegment func, float f);

    @Impl(
        // language="c"
        c = """
            int32_t (*ff)(int32_t) = func;
            env->return_ = ff(i);
            return 0;
            """
    )
    int upcallReturnInt1Param(MemorySegment func, int i);

    @Impl(
        // language="c"
        c = """
            int64_t (*ff)(int64_t) = func;
            env->return_ = ff(j);
            return 0;
            """
    )
    long upcallReturnLong1Param(MemorySegment func, long j);

    @Impl(
        // language="c"
        c = """
            int16_t (*ff)(int16_t) = func;
            env->return_ = ff(s);
            return 0;
            """
    )
    short upcallReturnShort1Param(MemorySegment func, short s);

    @Impl(
        // language="c"
        c = """
            void* (*ff)(void*) = func;
            env->return_ = ff(p);
            return 0;
            """
    )
    MemorySegment upcallReturnPointer1Param(MemorySegment func, MemorySegment p);
}
