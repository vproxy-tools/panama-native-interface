package io.vproxy.pni.test;

import io.vproxy.pni.PNIFunc;
import io.vproxy.pni.PNIRef;
import io.vproxy.pni.annotation.*;

import java.lang.foreign.MemorySegment;
import java.nio.ByteBuffer;

@Struct
abstract class PNINull {
    @Pointer PNIObjectStruct o;
    String str;
    MemorySegment seg;
    ByteBuffer buf;
    byte[] byteArr;
    boolean[] boolArr;
    char[] charArr;
    float[] floatArr;
    double[] doubleArr;
    int[] intArr;
    long[] longArr;
    short[] shortArr;
    MemorySegment[] pArr;
    PNIObjectStruct[] oArr;
    PNIRef<Object> ref;
    PNIFunc<PNINull> func;
    PNIFunc<Void> funcVoid;
    PNIFunc<PNIRef<Object>> funcRef;

    @Impl(
        // language="c"
        c = """
            env->return_= o == NULL && str == NULL && seg == NULL && buf == NULL &&
                          byteArr == NULL && boolArr == NULL && charArr == NULL &&
                          floatArr == NULL && doubleArr == NULL && intArr == NULL &&
                          longArr == NULL && shortArr == NULL && pArr == NULL && oArr == NULL &&
                          ref == NULL && func == NULL && funcVoid == NULL && funcRef == NULL;
            return 0;
            """
    )
    abstract boolean testParam(
        PNIObjectStruct o,
        String str,
        MemorySegment seg,
        ByteBuffer buf,
        byte[] byteArr,
        boolean[] boolArr,
        char[] charArr,
        float[] floatArr,
        double[] doubleArr,
        int[] intArr,
        long[] longArr,
        short[] shortArr,
        MemorySegment[] pArr,
        PNIObjectStruct[] oArr,
        PNIRef<Object> ref,
        PNIFunc<PNINull> func,
        PNIFunc<Void> funcVoid,
        PNIFunc<PNIRef<Object>> funcRef
    );

    @Style(Styles.critical)
    @Impl(
        // language="c"
        c = """
            return o == NULL && str == NULL && seg == NULL && buf == NULL &&
                   byteArr == NULL && boolArr == NULL && charArr == NULL &&
                   floatArr == NULL && doubleArr == NULL && intArr == NULL &&
                   longArr == NULL && shortArr == NULL && pArr == NULL && oArr == NULL &&
                   ref == NULL && func == NULL && funcVoid == NULL && funcRef == NULL;
            """
    )
    abstract boolean testParamCritical(
        PNIObjectStruct o,
        String str,
        MemorySegment seg,
        ByteBuffer buf,
        byte[] byteArr,
        boolean[] boolArr,
        char[] charArr,
        float[] floatArr,
        double[] doubleArr,
        int[] intArr,
        long[] longArr,
        short[] shortArr,
        MemorySegment[] pArr,
        PNIObjectStruct[] oArr,
        PNIRef<Object> ref,
        PNIFunc<PNINull> func,
        PNIFunc<Void> funcVoid,
        PNIFunc<PNIRef<Object>> funcRef
    );

    @Impl(
        // language="c"
        c = """
            env->return_= buf == NULL &&
                          byteArr == NULL && boolArr == NULL && charArr == NULL &&
                          floatArr == NULL && doubleArr == NULL && intArr == NULL &&
                          longArr == NULL && shortArr == NULL && pArr == NULL && oArr == NULL &&
                          ref == NULL && func == NULL && funcVoid == NULL && funcRef == NULL;
            return 0;
            """
    )
    abstract boolean testParamRaw(
        @Raw ByteBuffer buf,
        @Raw byte[] byteArr,
        @Raw boolean[] boolArr,
        @Raw char[] charArr,
        @Raw float[] floatArr,
        @Raw double[] doubleArr,
        @Raw int[] intArr,
        @Raw long[] longArr,
        @Raw short[] shortArr,
        @Raw MemorySegment[] pArr,
        @Raw PNIObjectStruct[] oArr,
        @Raw PNIRef<Object> ref,
        @Raw PNIFunc<PNINull> func,
        @Raw PNIFunc<Void> funcVoid,
        @Raw PNIFunc<PNIRef<Object>> funcRef
    );

    @Style(Styles.critical)
    @Impl(
        // language="c"
        c = """
            return buf == NULL &&
                   byteArr == NULL && boolArr == NULL && charArr == NULL &&
                   floatArr == NULL && doubleArr == NULL && intArr == NULL &&
                   longArr == NULL && shortArr == NULL && pArr == NULL && oArr == NULL &&
                   ref == NULL && func == NULL && funcVoid == NULL && funcRef == NULL;
            """
    )
    abstract boolean testParamRawCritical(
        @Raw ByteBuffer buf,
        @Raw byte[] byteArr,
        @Raw boolean[] boolArr,
        @Raw char[] charArr,
        @Raw float[] floatArr,
        @Raw double[] doubleArr,
        @Raw int[] intArr,
        @Raw long[] longArr,
        @Raw short[] shortArr,
        @Raw MemorySegment[] pArr,
        @Raw PNIObjectStruct[] oArr,
        @Raw PNIRef<Object> ref,
        @Raw PNIFunc<PNINull> func,
        @Raw PNIFunc<Void> funcVoid,
        @Raw PNIFunc<PNIRef<Object>> funcRef
    );

    @Impl(
        c = """
            env->return_ = NULL;
            return 0;
            """)
    abstract PNIObjectStruct returnO();

    @Impl(
        c = """
            return NULL;
            """)
    @Style(Styles.critical)
    abstract PNIObjectStruct returnOCritical();

    @Impl(
        c = """
            env->return_ = NULL;
            return 0;
            """
    )
    abstract String returnStr();

    @Impl(
        c = """
            return NULL;
            """
    )
    @Style(Styles.critical)
    abstract String returnStrCritical();

    @Impl(
        c = """
            env->return_ = NULL;
            return 0;
            """
    )
    abstract MemorySegment returnSeg();

    @Impl(
        c = """
            return NULL;
            """
    )
    @Style(Styles.critical)
    abstract MemorySegment returnSegCritical();

    @Impl(
        c = """
            env->return_.buf = NULL;
            return 0;
            """
    )
    abstract ByteBuffer returnBuf();

    @Impl(
        c = """
            return NULL;
            """
    )
    @Style(Styles.critical)
    abstract ByteBuffer returnBufCritical();

    @Impl(
        c = """
            return_->buf = NULL;
            return return_;
            """
    )
    @Style(Styles.critical)
    abstract ByteBuffer returnBufCritical2();

    @Impl(
        c = """
            env->return_.buf = NULL;
            return 0;
            """
    )
    abstract byte[] returnByteArr();

    @Impl(
        c = """
            return NULL;
            """
    )
    @Style(Styles.critical)
    abstract byte[] returnByteArrCritical();

    @Impl(
        c = """
            return_->buf = NULL;
            return return_;
            """
    )
    @Style(Styles.critical)
    abstract byte[] returnByteArrCritical2();

    @Impl(
        c = """
            env->return_.buf = NULL;
            return 0;
            """
    )
    abstract boolean[] returnBoolArr();

    @Impl(
        c = """
            return NULL;
            """
    )
    @Style(Styles.critical)
    abstract boolean[] returnBoolArrCritical();

    @Impl(
        c = """
            return_->buf = NULL;
            return return_;
            """
    )
    @Style(Styles.critical)
    abstract boolean[] returnBoolArrCritical2();

    @Impl(
        c = """
            env->return_.buf = NULL;
            return 0;
            """
    )
    abstract char[] returnCharArr();

    @Impl(
        c = """
            return NULL;
            """
    )
    @Style(Styles.critical)
    abstract char[] returnCharArrCritical();

    @Impl(
        c = """
            return_->buf = NULL;
            return return_;
            """
    )
    @Style(Styles.critical)
    abstract char[] returnCharArrCritical2();

    @Impl(
        c = """
            env->return_.buf = NULL;
            return 0;
            """
    )
    abstract float[] returnFloatArr();

    @Impl(
        c = """
            return NULL;
            """
    )
    @Style(Styles.critical)
    abstract float[] returnFloatArrCritical();

    @Impl(
        c = """
            return_->buf = NULL;
            return return_;
            """
    )
    @Style(Styles.critical)
    abstract float[] returnFloatArrCritical2();

    @Impl(
        c = """
            env->return_.buf = NULL;
            return 0;
            """
    )
    abstract double[] returnDoubleArr();

    @Impl(
        c = """
            return NULL;
            """
    )
    @Style(Styles.critical)
    abstract double[] returnDoubleArrCritical();

    @Impl(
        c = """
            return_->buf = NULL;
            return return_;
            """
    )
    @Style(Styles.critical)
    abstract double[] returnDoubleArrCritical2();

    @Impl(
        c = """
            env->return_.buf = NULL;
            return 0;
            """
    )
    abstract int[] returnIntArr();

    @Impl(
        c = """
            return NULL;
            """
    )
    @Style(Styles.critical)
    abstract int[] returnIntArrCritical();

    @Impl(
        c = """
            return_->buf = NULL;
            return return_;
            """
    )
    @Style(Styles.critical)
    abstract int[] returnIntArrCritical2();

    @Impl(
        c = """
            env->return_.buf = NULL;
            return 0;
            """
    )
    abstract long[] returnLongArr();

    @Impl(
        c = """
            return NULL;
            """
    )
    @Style(Styles.critical)
    abstract long[] returnLongArrCritical();

    @Impl(
        c = """
            return_->buf = NULL;
            return return_;
            """
    )
    @Style(Styles.critical)
    abstract long[] returnLongArrCritical2();

    @Impl(
        c = """
            env->return_.buf = NULL;
            return 0;
            """
    )
    abstract short[] returnShortArr();

    @Impl(
        c = """
            return NULL;
            """
    )
    @Style(Styles.critical)
    abstract short[] returnShortArrCritical();

    @Impl(
        c = """
            return_->buf = NULL;
            return return_;
            """
    )
    @Style(Styles.critical)
    abstract short[] returnShortArrCritical2();

    @Impl(
        c = """
            env->return_.buf = NULL;
            return 0;
            """
    )
    abstract MemorySegment[] returnPArr();

    @Impl(
        c = """
            return NULL;
            """
    )
    @Style(Styles.critical)
    abstract MemorySegment[] returnPArrCritical();

    @Impl(
        c = """
            return_->buf = NULL;
            return return_;
            """
    )
    @Style(Styles.critical)
    abstract MemorySegment[] returnPArrCritical2();

    @Impl(
        c = """
            env->return_.buf = NULL;
            return 0;
            """
    )
    abstract PNIObjectStruct[] returnOArr();

    @Impl(
        c = """
            return NULL;
            """
    )
    @Style(Styles.critical)
    abstract PNIObjectStruct[] returnOArrCritical();

    @Impl(
        c = """
            return_->buf = NULL;
            return return_;
            """
    )
    @Style(Styles.critical)
    abstract PNIObjectStruct[] returnOArrCritical2();

    @Impl(
        c = """
            env->return_ = NULL;
            return 0;
            """
    )
    abstract PNIRef<Object> returnRef();

    @Impl(
        c = """
            return NULL;
            """
    )
    @Style(Styles.critical)
    abstract PNIRef<Object> returnRefCritical();

    @Impl(
        c = """
            env->return_ = NULL;
            return 0;
            """
    )
    abstract PNIFunc<PNINull> returnFunc();

    @Impl(
        c = """
            return NULL;
            """
    )
    @Style(Styles.critical)
    abstract PNIFunc<PNINull> returnFuncCritical();

    @Impl(
        c = """
            env->return_ = NULL;
            return 0;
            """
    )
    abstract PNIFunc<Void> returnFuncVoid();

    @Impl(
        c = """
            return NULL;
            """
    )
    @Style(Styles.critical)
    abstract PNIFunc<Void> returnFuncVoidCritical();

    @Impl(
        c = """
            env->return_ = NULL;
            return 0;
            """
    )
    abstract PNIFunc<PNIRef<Object>> returnFuncRef();

    @Impl(
        c = """
            return NULL;
            """
    )
    @Style(Styles.critical)
    abstract PNIFunc<PNIRef<Object>> returnFuncRefCritical();

    @Impl(
        c = """
            env->return_ = empty;
            return 0;
            """
    )
    abstract PNIEmpty emptyPassThrough(PNIEmpty empty);

    @Impl(
        c = """
            return empty;
            """
    )
    @Style(Styles.critical)
    abstract PNIEmpty emptyPassThroughCritical(PNIEmpty empty);
}

@Struct
class PNIEmpty {
}
