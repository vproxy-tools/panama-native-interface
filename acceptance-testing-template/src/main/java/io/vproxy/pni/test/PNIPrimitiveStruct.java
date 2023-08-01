package io.vproxy.pni.test;

import io.vproxy.pni.annotation.Critical;
import io.vproxy.pni.annotation.Len;
import io.vproxy.pni.annotation.Struct;
import io.vproxy.pni.annotation.Unsigned;

@Struct
public abstract class PNIPrimitiveStruct {
    byte aByte;
    @Unsigned byte unsignedByte;
    char aChar;
    double aDouble;
    float aFloat;
    int aInt;
    @Unsigned int unsignedInt;
    long aLong;
    @Unsigned long unsignedLong;
    short aShort;
    @Unsigned short unsignedShort;
    boolean aBoolean;

    @Len(11) byte[] byteArray;
    @Len(12) @Unsigned byte[] unsignedByteArray;
    @Len(13) char[] charArray;
    @Len(14) double[] doubleArray;
    @Len(15) float[] floatArray;
    @Len(16) int[] intArray;
    @Len(17) @Unsigned int[] unsignedIntArray;
    @Len(18) long[] longArray;
    @Len(19) @Unsigned long[] unsignedLongArray;
    @Len(20) short[] shortArray;
    @Len(21) @Unsigned short[] unsignedShortArray;
    @Len(22) boolean[] booleanArray;

    byte[] byteArrayPointer;
    @Unsigned byte[] unsignedByteArrayPointer;
    char[] charArrayPointer;
    double[] doubleArrayPointer;
    float[] floatArrayPointer;
    int[] intArrayPointer;
    @Unsigned int[] unsignedIntArrayPointer;
    long[] longArrayPointer;
    @Unsigned long[] unsignedLongArrayPointer;
    short[] shortArrayPointer;
    @Unsigned short[] unsignedShortArrayPointer;
    boolean[] booleanArrayPointer;

    abstract void func1(byte aByte, @Unsigned byte unsignedByte,
                        int aInt, @Unsigned int unsignedInt,
                        long aLong, @Unsigned long unsignedLong,
                        short aShort, @Unsigned short unsignedShort);

    @Critical
    abstract void func1Critical(byte aByte, @Unsigned byte unsignedByte,
                                int aInt, @Unsigned int unsignedInt,
                                long aLong, @Unsigned long unsignedLong,
                                short aShort, @Unsigned short unsignedShort);

    abstract void func2(char aChar, double aDouble, float aFloat, boolean aBoolean);

    @Critical
    abstract void func2Critical(char aChar, double aDouble, float aFloat, boolean aBoolean);

    abstract void func3(byte[] byteArray, @Unsigned byte[] unsignedByteArray,
                        int[] intArray, @Unsigned int[] unsignedIntArray,
                        long[] longArray, @Unsigned long[] unsignedLongArray,
                        short[] shortArray, @Unsigned short[] unsignedShortArray);

    @Critical
    abstract void func3Critical(byte[] byteArray, @Unsigned byte[] unsignedByteArray,
                                int[] intArray, @Unsigned int[] unsignedIntArray,
                                long[] longArray, @Unsigned long[] unsignedLongArray,
                                short[] shortArray, @Unsigned short[] unsignedShortArray);

    abstract void func4(char[] charArray, double[] doubleArray, float[] floatArray, boolean[] booleanArray);

    @Critical
    abstract void func4Critical(char[] charArray, double[] doubleArray, float[] floatArray, boolean[] booleanArray);

    abstract byte retrieveByte();

    @Critical
    abstract byte retrieveByteCritical();

    abstract byte retrieveUnsignedByte();

    @Critical
    abstract byte retrieveUnsignedByteCritical();

    abstract char retrieveChar();

    @Critical
    abstract char retrieveCharCritical();

    abstract double retrieveDouble();

    @Critical
    abstract double retrieveDoubleCritical();

    abstract float retrieveFloat();

    @Critical
    abstract float retrieveFloatCritical();

    abstract int retrieveInt();

    @Critical
    abstract int retrieveIntCritical();

    abstract int retrieveUnsignedInt();

    @Critical
    abstract int retrieveUnsignedIntCritical();

    abstract long retrieveLong();

    @Critical
    abstract long retrieveLongCritical();

    abstract long retrieveUnsignedLong();

    @Critical
    abstract long retrieveUnsignedLongCritical();

    abstract short retrieveShort();

    @Critical
    abstract short retrieveShortCritical();

    abstract short retrieveUnsignedShort();

    @Critical
    abstract short retrieveUnsignedShortCritical();

    abstract boolean retrieveBoolean();

    @Critical
    abstract boolean retrieveBooleanCritical();

    abstract byte[] retrieveByteArray();

    @Critical
    abstract byte[] retrieveByteArrayCritical();

    abstract byte[] retrieveUnsignedByteArray();

    @Critical
    abstract byte[] retrieveUnsignedByteArrayCritical();

    abstract char[] retrieveCharArray();

    @Critical
    abstract char[] retrieveCharArrayCritical();

    abstract double[] retrieveDoubleArray();

    @Critical
    abstract double[] retrieveDoubleArrayCritical();

    abstract float[] retrieveFloatArray();

    @Critical
    abstract float[] retrieveFloatArrayCritical();

    abstract int[] retrieveIntArray();

    @Critical
    abstract int[] retrieveIntArrayCritical();

    abstract int[] retrieveUnsignedIntArray();

    @Critical
    abstract int[] retrieveUnsignedIntArrayCritical();

    abstract long[] retrieveLongArray();

    @Critical
    abstract long[] retrieveLongArrayCritical();

    abstract long[] retrieveUnsignedLongArray();

    @Critical
    abstract long[] retrieveUnsignedLongArrayCritical();

    abstract short[] retrieveShortArray();

    @Critical
    abstract short[] retrieveShortArrayCritical();

    abstract short[] retrieveUnsignedShortArray();

    @Critical
    abstract short[] retrieveUnsignedShortArrayCritical();

    abstract boolean[] retrieveBooleanArray();

    @Critical
    abstract boolean[] retrieveBooleanArrayCritical();

    abstract byte[] retrieveByteArrayPointer();

    @Critical
    abstract byte[] retrieveByteArrayPointerCritical();

    abstract byte[] retrieveUnsignedByteArrayPointer();

    @Critical
    abstract byte[] retrieveUnsignedByteArrayPointerCritical();

    abstract char[] retrieveCharArrayPointer();

    @Critical
    abstract char[] retrieveCharArrayPointerCritical();

    abstract double[] retrieveDoubleArrayPointer();

    @Critical
    abstract double[] retrieveDoubleArrayPointerCritical();

    abstract float[] retrieveFloatArrayPointer();

    @Critical
    abstract float[] retrieveFloatArrayPointerCritical();

    abstract int[] retrieveIntArrayPointer();

    @Critical
    abstract int[] retrieveIntArrayPointerCritical();

    abstract int[] retrieveUnsignedIntArrayPointer();

    @Critical
    abstract int[] retrieveUnsignedIntArrayPointerCritical();

    abstract long[] retrieveLongArrayPointer();

    @Critical
    abstract long[] retrieveLongArrayPointerCritical();

    abstract long[] retrieveUnsignedLongArrayPointer();

    @Critical
    abstract long[] retrieveUnsignedLongArrayPointerCritical();

    abstract short[] retrieveShortArrayPointer();

    @Critical
    abstract short[] retrieveShortArrayPointerCritical();

    abstract short[] retrieveUnsignedShortArrayPointer();

    @Critical
    abstract short[] retrieveUnsignedShortArrayPointerCritical();

    abstract boolean[] retrieveBooleanArrayPointer();

    @Critical
    abstract boolean[] retrieveBooleanArrayPointerCritical();

    abstract boolean checkPointerSetToNonNull();

    @Critical
    abstract boolean checkPointerSetToNonNullCritical();

    abstract boolean checkPointerSetToNull();

    @Critical
    abstract boolean checkPointerSetToNullCritical();
}
