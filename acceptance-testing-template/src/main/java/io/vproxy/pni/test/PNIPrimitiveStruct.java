package io.vproxy.pni.test;

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

    abstract void func2(char aChar, double aDouble, float aFloat, boolean aBoolean);

    abstract void func3(byte[] byteArray, @Unsigned byte[] unsignedByteArray,
                        int[] intArray, @Unsigned int[] unsignedIntArray,
                        long[] longArray, @Unsigned long[] unsignedLongArray,
                        short[] shortArray, @Unsigned short[] unsignedShortArray);

    abstract void func4(char[] charArray, double[] doubleArray, float[] floatArray, boolean[] booleanArray);

    abstract byte retrieveByte();

    abstract byte retrieveUnsignedByte();

    abstract char retrieveChar();

    abstract double retrieveDouble();

    abstract float retrieveFloat();

    abstract int retrieveInt();

    abstract int retrieveUnsignedInt();

    abstract long retrieveLong();

    abstract long retrieveUnsignedLong();

    abstract short retrieveShort();

    abstract short retrieveUnsignedShort();

    abstract boolean retrieveBoolean();

    abstract byte[] retrieveByteArray();

    abstract byte[] retrieveUnsignedByteArray();

    abstract char[] retrieveCharArray();

    abstract double[] retrieveDoubleArray();

    abstract float[] retrieveFloatArray();

    abstract int[] retrieveIntArray();

    abstract int[] retrieveUnsignedIntArray();

    abstract long[] retrieveLongArray();

    abstract long[] retrieveUnsignedLongArray();

    abstract short[] retrieveShortArray();

    abstract short[] retrieveUnsignedShortArray();

    abstract boolean[] retrieveBooleanArray();

    abstract byte[] retrieveByteArrayPointer();

    abstract byte[] retrieveUnsignedByteArrayPointer();

    abstract char[] retrieveCharArrayPointer();

    abstract double[] retrieveDoubleArrayPointer();

    abstract float[] retrieveFloatArrayPointer();

    abstract int[] retrieveIntArrayPointer();

    abstract int[] retrieveUnsignedIntArrayPointer();

    abstract long[] retrieveLongArrayPointer();

    abstract long[] retrieveUnsignedLongArrayPointer();

    abstract short[] retrieveShortArrayPointer();

    abstract short[] retrieveUnsignedShortArrayPointer();

    abstract boolean[] retrieveBooleanArrayPointer();

    abstract boolean checkPointerSetToNonNull();

    abstract boolean checkPointerSetToNull();
}
