package io.vproxy.pni.test;

import io.vproxy.pni.PNIFunc;
import io.vproxy.pni.PNIRef;
import io.vproxy.pni.annotation.*;

import java.lang.foreign.MemorySegment;
import java.nio.ByteBuffer;
import java.util.List;

@Include("io_vproxy_pni_test_Upcall.h")
@Function
public interface PNIInvokeUpcall {
    @Impl(
        c = "JavaCritical_io_vproxy_pni_test_Upcall_primaryParams(b, ub, z, c, d, f, i, ui, j, uj, s, us);"
    )
    @Critical
    void primaryParams(byte b, byte ub, boolean z, char c, double d, float f, int i, int ui, long j, long uj, short s, short us);

    @Impl(
        c = "return JavaCritical_io_vproxy_pni_test_Upcall_returnByte();"
    )
    @Critical
    byte returnByte();

    @Impl(
        c = "return JavaCritical_io_vproxy_pni_test_Upcall_returnBool();"
    )
    @Critical
    boolean returnBool();

    @Impl(
        c = "return JavaCritical_io_vproxy_pni_test_Upcall_returnChar();"
    )
    @Critical
    char returnChar();

    @Impl(
        c = "return JavaCritical_io_vproxy_pni_test_Upcall_returnDouble();"
    )
    @Critical
    double returnDouble();

    @Impl(
        c = "return JavaCritical_io_vproxy_pni_test_Upcall_returnFloat();"
    )
    @Critical
    float returnFloat();

    @Impl(
        c = "return JavaCritical_io_vproxy_pni_test_Upcall_returnInt();"
    )
    @Critical
    int returnInt();

    @Impl(
        c = "return JavaCritical_io_vproxy_pni_test_Upcall_returnLong();"
    )
    @Critical
    long returnLong();

    @Impl(
        c = "return JavaCritical_io_vproxy_pni_test_Upcall_returnShort();"
    )
    @Critical
    short returnShort();

    @Impl(
        c = "JavaCritical_io_vproxy_pni_test_Upcall_primaryArrayParams(b, ub, z, c, d, f, i, ui, j, uj, s, us);"
    )
    @Critical
    void primaryArrayParams(byte[] b, @Unsigned byte[] ub,
                            boolean[] z,
                            char[] c,
                            double[] d,
                            float[] f,
                            int[] i, @Unsigned int[] ui,
                            long[] j, @Unsigned long[] uj,
                            short[] s, @Unsigned short[] us);

    @Impl(
        c = "return JavaCritical_io_vproxy_pni_test_Upcall_returnByteArray(return_);"
    )
    @Critical
    byte[] returnByteArray();

    @Impl(
        c = "return JavaCritical_io_vproxy_pni_test_Upcall_returnBoolArray(return_);"
    )
    @Critical
    boolean[] returnBoolArray();

    @Impl(
        c = "return JavaCritical_io_vproxy_pni_test_Upcall_returnCharArray(return_);"
    )
    @Critical
    char[] returnCharArray();

    @Impl(
        c = "return JavaCritical_io_vproxy_pni_test_Upcall_returnDoubleArray(return_);"
    )
    @Critical
    double[] returnDoubleArray();

    @Impl(
        c = "return JavaCritical_io_vproxy_pni_test_Upcall_returnFloatArray(return_);"
    )
    @Critical
    float[] returnFloatArray();

    @Impl(
        c = "return JavaCritical_io_vproxy_pni_test_Upcall_returnIntArray(return_);"
    )
    @Critical
    int[] returnIntArray();

    @Impl(
        c = "return JavaCritical_io_vproxy_pni_test_Upcall_returnLongArray(return_);"
    )
    @Critical
    long[] returnLongArray();

    @Impl(
        c = "return JavaCritical_io_vproxy_pni_test_Upcall_returnShortArray(return_);"
    )
    @Critical
    short[] returnShortArray();

    @Impl(
        c = "JavaCritical_io_vproxy_pni_test_Upcall_objectParams(o);"
    )
    @Critical
    void objectParams(PNIObjectStruct o);

    @Impl(
        c = "return JavaCritical_io_vproxy_pni_test_Upcall_returnObject(return_);"
    )
    @Critical
    PNIObjectStruct returnObject();

    @Impl(
        c = "JavaCritical_io_vproxy_pni_test_Upcall_objectArrayParams(o);"
    )
    @Critical
    void objectArrayParams(PNIObjectStruct[] o);

    @Impl(
        c = "return JavaCritical_io_vproxy_pni_test_Upcall_returnObjectArray(return_);"
    )
    @Critical
    PNIObjectStruct[] returnObjectArray();

    @Impl(
        c = "JavaCritical_io_vproxy_pni_test_Upcall_otherParams(buffer, voidCallSite, objCallSite, refCallSite, mem, voidFunc, objFunc, refFunc, ref, rawRef, str);"
    )
    @Critical
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

    @Impl(
        c = "return JavaCritical_io_vproxy_pni_test_Upcall_returnBuffer(return_);"
    )
    @Critical
    ByteBuffer returnBuffer();

    @Impl(
        c = "return JavaCritical_io_vproxy_pni_test_Upcall_returnMem();"
    )
    @Critical
    MemorySegment returnMem();

    @Impl(
        c = "return JavaCritical_io_vproxy_pni_test_Upcall_returnVoidFunc();"
    )
    @Critical
    PNIFunc<Void> returnVoidFunc();

    @Impl(
        c = "return JavaCritical_io_vproxy_pni_test_Upcall_returnObjFunc();"
    )
    @Critical
    PNIFunc<PNIObjectStruct> returnObjFunc();

    @Impl(
        c = "return JavaCritical_io_vproxy_pni_test_Upcall_returnRefFunc();"
    )
    @Critical
    PNIFunc<PNIRef<List<String>>> returnRefFunc();

    @Impl(
        c = "return JavaCritical_io_vproxy_pni_test_Upcall_returnRef();"
    )
    @Critical
    PNIRef<List<Integer>> returnRef();

    @Impl(
        c = "return JavaCritical_io_vproxy_pni_test_Upcall_returnStr();"
    )
    @Critical
    String returnStr();
}
