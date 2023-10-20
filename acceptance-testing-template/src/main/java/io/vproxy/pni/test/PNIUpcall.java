package io.vproxy.pni.test;

import io.vproxy.pni.PNIFunc;
import io.vproxy.pni.PNIRef;
import io.vproxy.pni.annotation.Name;
import io.vproxy.pni.annotation.Raw;
import io.vproxy.pni.annotation.Unsigned;
import io.vproxy.pni.annotation.Upcall;

import java.lang.foreign.MemorySegment;
import java.nio.ByteBuffer;
import java.util.List;

@Upcall
public interface PNIUpcall {
    void primaryParams(byte b, @Unsigned byte ub,
                       boolean z,
                       char c,
                       double d,
                       float f,
                       int i, @Unsigned int ui,
                       long j, @Unsigned long uj,
                       short s, @Unsigned short us);

    byte returnByte();

    boolean returnBool();

    char returnChar();

    double returnDouble();

    float returnFloat();

    int returnInt();

    long returnLong();

    short returnShort();

    void primaryArrayParams(byte[] b, @Unsigned byte[] ub,
                            boolean[] z,
                            char[] c,
                            double[] d,
                            float[] f,
                            int[] i, @Unsigned int[] ui,
                            long[] j, @Unsigned long[] uj,
                            short[] s, @Unsigned short[] us);

    byte[] returnByteArray();

    boolean[] returnBoolArray();

    char[] returnCharArray();

    double[] returnDoubleArray();

    float[] returnFloatArray();

    int[] returnIntArray();

    long[] returnLongArray();

    short[] returnShortArray();

    void objectParams(PNIObjectStruct o);

    PNIObjectStruct returnObject();

    void pointerArrayParams(MemorySegment[] p);

    MemorySegment[] returnPointerArray();

    void objectArrayParams(PNIObjectStruct[] o);

    PNIObjectStruct[] returnObjectArray();

    void otherParams(ByteBuffer buffer,
                     PNIFunc<Void> voidCallSite,
                     PNIFunc<PNIObjectStruct> objCallSite,
                     PNIFunc<PNIRef<List<String>>> refCallSite,
                     MemorySegment mem,
                     @Raw PNIFunc<Void> voidFunc,
                     @Raw PNIFunc<PNIObjectStruct> objFunc,
                     @Raw PNIFunc<PNIRef<List<String>>> refFunc,
                     PNIRef<List<Integer>> ref,
                     @Raw PNIRef<List<Integer>> rawRef,
                     String str);

    ByteBuffer returnBuffer();

    MemorySegment returnMem();

    PNIFunc<Void> returnVoidFunc();

    PNIFunc<PNIObjectStruct> returnObjFunc();

    PNIFunc<PNIRef<List<String>>> returnRefFunc();

    PNIRef<List<Integer>> returnRef();

    String returnStr();

    @Name("pni_sum")
    int sum(int a, int b);
}
