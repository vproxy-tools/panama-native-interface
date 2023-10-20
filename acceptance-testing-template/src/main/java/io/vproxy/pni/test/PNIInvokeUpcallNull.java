package io.vproxy.pni.test;

import io.vproxy.pni.annotation.*;

@Include("io_vproxy_pni_test_UpcallNull.h")
@Function
public interface PNIInvokeUpcallNull {
    @Impl(
        c = """
            return JavaCritical_io_vproxy_pni_test_UpcallNull_testParam(
                NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
                NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
                NULL, NULL);
            """
    )
    @Critical
    boolean testParam();

    @Impl(
        c = """
            return JavaCritical_io_vproxy_pni_test_UpcallNull_testParamRaw(NULL, NULL, NULL, NULL);
            """
    )
    @Critical
    boolean testParamRaw();

    @Impl(
        c = """
            ObjectStruct o;
            return JavaCritical_io_vproxy_pni_test_UpcallNull_returnO(&o) == NULL;
            """
    )
    @Critical
    boolean returnO();

    @Impl(
        c = """
            return JavaCritical_io_vproxy_pni_test_UpcallNull_returnStr() == NULL;
            """
    )
    @Critical
    boolean returnStr();

    @Impl(
        c = """
            return JavaCritical_io_vproxy_pni_test_UpcallNull_returnSeg() == NULL;
            """
    )
    @Critical
    boolean returnSeg();

    @Impl(
        c = """
            PNIBuf b;
            return JavaCritical_io_vproxy_pni_test_UpcallNull_returnBuf(&b) == NULL;
            """
    )
    @Critical
    boolean returnBuf();

    @Impl(
        c = """
            PNIBuf_byte b;
            return JavaCritical_io_vproxy_pni_test_UpcallNull_returnByteArr(&b) == NULL;
            """
    )
    @Critical
    boolean returnByteArr();

    @Impl(
        c = """
            PNIBuf_bool b;
            return JavaCritical_io_vproxy_pni_test_UpcallNull_returnBoolArr(&b) == NULL;
            """
    )
    @Critical
    boolean returnBoolArr();

    @Impl(
        c = """
            PNIBuf_char b;
            return JavaCritical_io_vproxy_pni_test_UpcallNull_returnCharArr(&b) == NULL;
            """
    )
    @Critical
    boolean returnCharArr();

    @Impl(
        c = """
            PNIBuf_float b;
            return JavaCritical_io_vproxy_pni_test_UpcallNull_returnFloatArr(&b) == NULL;
            """
    )
    @Critical
    boolean returnFloatArr();

    @Impl(
        c = """
            PNIBuf_double b;
            return JavaCritical_io_vproxy_pni_test_UpcallNull_returnDoubleArr(&b) == NULL;
            """
    )
    @Critical
    boolean returnDoubleArr();

    @Impl(
        c = """
            PNIBuf_int b;
            return JavaCritical_io_vproxy_pni_test_UpcallNull_returnIntArr(&b) == NULL;
            """
    )
    @Critical
    boolean returnIntArr();

    @Impl(
        c = """
            PNIBuf_long b;
            return JavaCritical_io_vproxy_pni_test_UpcallNull_returnLongArr(&b) == NULL;
            """
    )
    @Critical
    boolean returnLongArr();

    @Impl(
        c = """
            PNIBuf_short b;
            return JavaCritical_io_vproxy_pni_test_UpcallNull_returnShortArr(&b) == NULL;
            """
    )
    @Critical
    boolean returnShortArr();

    @Impl(
        c = """
            PNIBuf_ptr b;
            return JavaCritical_io_vproxy_pni_test_UpcallNull_returnPArr(&b) == NULL;
            """
    )
    @Critical
    boolean returnPArr();

    @Impl(
        c = """
            PNIBuf_ObjectStruct b;
            return JavaCritical_io_vproxy_pni_test_UpcallNull_returnOArr(&b) == NULL;
            """
    )
    @Critical
    boolean returnOArr();

    @Impl(
        c = """
            return JavaCritical_io_vproxy_pni_test_UpcallNull_returnRef() == NULL;
            """
    )
    @Critical
    boolean returnRef();

    @Impl(
        c = """
            return JavaCritical_io_vproxy_pni_test_UpcallNull_returnFunc() == NULL;
            """
    )
    @Critical
    boolean returnFunc();

    @Impl(
        c = """
            return JavaCritical_io_vproxy_pni_test_UpcallNull_returnFuncVoid() == NULL;
            """
    )
    @Critical
    boolean returnFuncVoid();

    @Impl(
        c = """
            return JavaCritical_io_vproxy_pni_test_UpcallNull_returnFuncRef() == NULL;
            """
    )
    @Critical
    boolean returnFuncRef();
}
