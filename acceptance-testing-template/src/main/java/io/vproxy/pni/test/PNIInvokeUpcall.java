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
    @Style(Styles.critical)
    void primaryParams(byte b, byte ub, boolean z, char c, double d, float f, int i, int ui, long j, long uj, short s, short us);

    @Impl(
        c = "return JavaCritical_io_vproxy_pni_test_Upcall_returnByte();"
    )
    @Style(Styles.critical)
    byte returnByte();

    @Impl(
        c = "return JavaCritical_io_vproxy_pni_test_Upcall_returnBool();"
    )
    @Style(Styles.critical)
    boolean returnBool();

    @Impl(
        c = "return JavaCritical_io_vproxy_pni_test_Upcall_returnChar();"
    )
    @Style(Styles.critical)
    char returnChar();

    @Impl(
        c = "return JavaCritical_io_vproxy_pni_test_Upcall_returnDouble();"
    )
    @Style(Styles.critical)
    double returnDouble();

    @Impl(
        c = "return JavaCritical_io_vproxy_pni_test_Upcall_returnFloat();"
    )
    @Style(Styles.critical)
    float returnFloat();

    @Impl(
        c = "return JavaCritical_io_vproxy_pni_test_Upcall_returnInt();"
    )
    @Style(Styles.critical)
    int returnInt();

    @Impl(
        c = "return JavaCritical_io_vproxy_pni_test_Upcall_returnLong();"
    )
    @Style(Styles.critical)
    long returnLong();

    @Impl(
        c = "return JavaCritical_io_vproxy_pni_test_Upcall_returnShort();"
    )
    @Style(Styles.critical)
    short returnShort();

    @Impl(
        c = "JavaCritical_io_vproxy_pni_test_Upcall_primaryArrayParams(b, ub, z, c, d, f, i, ui, j, uj, s, us);"
    )
    @Style(Styles.critical)
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
    @Style(Styles.critical)
    byte[] returnByteArray();

    @Impl(
        c = "return JavaCritical_io_vproxy_pni_test_Upcall_returnBoolArray(return_);"
    )
    @Style(Styles.critical)
    boolean[] returnBoolArray();

    @Impl(
        c = "return JavaCritical_io_vproxy_pni_test_Upcall_returnCharArray(return_);"
    )
    @Style(Styles.critical)
    char[] returnCharArray();

    @Impl(
        c = "return JavaCritical_io_vproxy_pni_test_Upcall_returnDoubleArray(return_);"
    )
    @Style(Styles.critical)
    double[] returnDoubleArray();

    @Impl(
        c = "return JavaCritical_io_vproxy_pni_test_Upcall_returnFloatArray(return_);"
    )
    @Style(Styles.critical)
    float[] returnFloatArray();

    @Impl(
        c = "return JavaCritical_io_vproxy_pni_test_Upcall_returnIntArray(return_);"
    )
    @Style(Styles.critical)
    int[] returnIntArray();

    @Impl(
        c = "return JavaCritical_io_vproxy_pni_test_Upcall_returnLongArray(return_);"
    )
    @Style(Styles.critical)
    long[] returnLongArray();

    @Impl(
        c = "return JavaCritical_io_vproxy_pni_test_Upcall_returnShortArray(return_);"
    )
    @Style(Styles.critical)
    short[] returnShortArray();

    @Impl(
        c = "JavaCritical_io_vproxy_pni_test_Upcall_objectParams(o);"
    )
    @Style(Styles.critical)
    void objectParams(PNIObjectStruct o);

    @Impl(
        c = "return JavaCritical_io_vproxy_pni_test_Upcall_returnObject(return_);"
    )
    @Style(Styles.critical)
    PNIObjectStruct returnObject();

    @Impl(
        c = "JavaCritical_io_vproxy_pni_test_Upcall_pointerArrayParams(o);"
    )
    @Style(Styles.critical)
    void pointerArrayParams(MemorySegment[] o);

    @Impl(
        c = "return JavaCritical_io_vproxy_pni_test_Upcall_returnPointerArray(return_);"
    )
    @Style(Styles.critical)
    MemorySegment[] returnPointerArray();

    @Impl(
        c = "JavaCritical_io_vproxy_pni_test_Upcall_objectArrayParams(o);"
    )
    @Style(Styles.critical)
    void objectArrayParams(PNIObjectStruct[] o);

    @Impl(
        c = "return JavaCritical_io_vproxy_pni_test_Upcall_returnObjectArray(return_);"
    )
    @Style(Styles.critical)
    PNIObjectStruct[] returnObjectArray();

    @Impl(
        c = "JavaCritical_io_vproxy_pni_test_Upcall_otherParams(buffer, voidCallSite, objCallSite, refCallSite, mem, voidFunc, objFunc, refFunc, ref, rawRef, str);"
    )
    @Style(Styles.critical)
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
    @Style(Styles.critical)
    ByteBuffer returnBuffer();

    @Impl(
        c = "return JavaCritical_io_vproxy_pni_test_Upcall_returnMem();"
    )
    @Style(Styles.critical)
    MemorySegment returnMem();

    @Impl(
        c = "return JavaCritical_io_vproxy_pni_test_Upcall_returnVoidFunc();"
    )
    @Style(Styles.critical)
    PNIFunc<Void> returnVoidFunc();

    @Impl(
        c = "return JavaCritical_io_vproxy_pni_test_Upcall_returnObjFunc();"
    )
    @Style(Styles.critical)
    PNIFunc<PNIObjectStruct> returnObjFunc();

    @Impl(
        c = "return JavaCritical_io_vproxy_pni_test_Upcall_returnRefFunc();"
    )
    @Style(Styles.critical)
    PNIFunc<PNIRef<List<String>>> returnRefFunc();

    @Impl(
        c = "return JavaCritical_io_vproxy_pni_test_Upcall_returnRef();"
    )
    @Style(Styles.critical)
    PNIRef<List<Integer>> returnRef();

    @Impl(
        c = "return JavaCritical_io_vproxy_pni_test_Upcall_returnStr();"
    )
    @Style(Styles.critical)
    String returnStr();

    @Impl(
        c = "return pni_sum(a, b);"
    )
    @Style(Styles.critical)
    int sum(int a, int b);
}
