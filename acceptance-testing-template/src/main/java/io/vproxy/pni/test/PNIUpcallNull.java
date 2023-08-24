package io.vproxy.pni.test;

import io.vproxy.pni.PNIFunc;
import io.vproxy.pni.PNIRef;
import io.vproxy.pni.annotation.Raw;
import io.vproxy.pni.annotation.Upcall;

import java.lang.foreign.MemorySegment;
import java.nio.ByteBuffer;

@Upcall
public interface PNIUpcallNull {
    boolean testParam(
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
        PNIObjectStruct[] oArr,
        PNIRef<Object> ref,
        PNIFunc<PNINull> func,
        PNIFunc<Void> funcVoid,
        PNIFunc<PNIRef<Object>> funcRef
    );

    boolean testParamRaw(
        @Raw PNIRef<Object> ref,
        @Raw PNIFunc<PNINull> func,
        @Raw PNIFunc<Void> funcVoid,
        @Raw PNIFunc<PNIRef<Object>> funcRef
    );

    PNIObjectStruct returnO();

    String returnStr();

    MemorySegment returnSeg();

    ByteBuffer returnBuf();

    byte[] returnByteArr();

    boolean[] returnBoolArr();

    char[] returnCharArr();

    float[] returnFloatArr();

    double[] returnDoubleArr();

    int[] returnIntArr();

    long[] returnLongArr();

    short[] returnShortArr();

    PNIObjectStruct[] returnOArr();

    PNIRef<Object> returnRef();

    PNIFunc<PNINull> returnFunc();

    PNIFunc<Void> returnFuncVoid();

    PNIFunc<PNIRef<Object>> returnFuncRef();
}
