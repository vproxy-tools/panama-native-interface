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
    @Style(Styles.critical)
    boolean testParam();

    @Impl(
        c = """
            return JavaCritical_io_vproxy_pni_test_UpcallNull_testParamRaw(NULL, NULL, NULL, NULL);
            """
    )
    @Style(Styles.critical)
    boolean testParamRaw();

    @Impl(
        c = """
            ObjectStruct o;
            return JavaCritical_io_vproxy_pni_test_UpcallNull_returnO(&o) == NULL;
            """
    )
    @Style(Styles.critical)
    boolean returnO();

    @Impl(
        c = """
            return JavaCritical_io_vproxy_pni_test_UpcallNull_returnStr() == NULL;
            """
    )
    @Style(Styles.critical)
    boolean returnStr();

    @Impl(
        c = """
            return JavaCritical_io_vproxy_pni_test_UpcallNull_returnSeg() == NULL;
            """
    )
    @Style(Styles.critical)
    boolean returnSeg();

    @Impl(
        c = """
            PNIBuf b;
            return JavaCritical_io_vproxy_pni_test_UpcallNull_returnBuf(&b) == NULL;
            """
    )
    @Style(Styles.critical)
    boolean returnBuf();

    @Impl(
        c = """
            PNIBuf_byte b;
            return JavaCritical_io_vproxy_pni_test_UpcallNull_returnByteArr(&b) == NULL;
            """
    )
    @Style(Styles.critical)
    boolean returnByteArr();

    @Impl(
        c = """
            PNIBuf_bool b;
            return JavaCritical_io_vproxy_pni_test_UpcallNull_returnBoolArr(&b) == NULL;
            """
    )
    @Style(Styles.critical)
    boolean returnBoolArr();

    @Impl(
        c = """
            PNIBuf_char b;
            return JavaCritical_io_vproxy_pni_test_UpcallNull_returnCharArr(&b) == NULL;
            """
    )
    @Style(Styles.critical)
    boolean returnCharArr();

    @Impl(
        c = """
            PNIBuf_float b;
            return JavaCritical_io_vproxy_pni_test_UpcallNull_returnFloatArr(&b) == NULL;
            """
    )
    @Style(Styles.critical)
    boolean returnFloatArr();

    @Impl(
        c = """
            PNIBuf_double b;
            return JavaCritical_io_vproxy_pni_test_UpcallNull_returnDoubleArr(&b) == NULL;
            """
    )
    @Style(Styles.critical)
    boolean returnDoubleArr();

    @Impl(
        c = """
            PNIBuf_int b;
            return JavaCritical_io_vproxy_pni_test_UpcallNull_returnIntArr(&b) == NULL;
            """
    )
    @Style(Styles.critical)
    boolean returnIntArr();

    @Impl(
        c = """
            PNIBuf_long b;
            return JavaCritical_io_vproxy_pni_test_UpcallNull_returnLongArr(&b) == NULL;
            """
    )
    @Style(Styles.critical)
    boolean returnLongArr();

    @Impl(
        c = """
            PNIBuf_short b;
            return JavaCritical_io_vproxy_pni_test_UpcallNull_returnShortArr(&b) == NULL;
            """
    )
    @Style(Styles.critical)
    boolean returnShortArr();

    @Impl(
        c = """
            PNIBuf_ptr b;
            return JavaCritical_io_vproxy_pni_test_UpcallNull_returnPArr(&b) == NULL;
            """
    )
    @Style(Styles.critical)
    boolean returnPArr();

    @Impl(
        c = """
            PNIBuf_ObjectStruct b;
            return JavaCritical_io_vproxy_pni_test_UpcallNull_returnOArr(&b) == NULL;
            """
    )
    @Style(Styles.critical)
    boolean returnOArr();

    @Impl(
        c = """
            return JavaCritical_io_vproxy_pni_test_UpcallNull_returnRef() == NULL;
            """
    )
    @Style(Styles.critical)
    boolean returnRef();

    @Impl(
        c = """
            return JavaCritical_io_vproxy_pni_test_UpcallNull_returnFunc() == NULL;
            """
    )
    @Style(Styles.critical)
    boolean returnFunc();

    @Impl(
        c = """
            return JavaCritical_io_vproxy_pni_test_UpcallNull_returnFuncVoid() == NULL;
            """
    )
    @Style(Styles.critical)
    boolean returnFuncVoid();

    @Impl(
        c = """
            return JavaCritical_io_vproxy_pni_test_UpcallNull_returnFuncRef() == NULL;
            """
    )
    @Style(Styles.critical)
    boolean returnFuncRef();
}
