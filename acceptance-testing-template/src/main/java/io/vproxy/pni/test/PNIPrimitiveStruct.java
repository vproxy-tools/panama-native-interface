package io.vproxy.pni.test;

import io.vproxy.pni.annotation.*;

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

    @Style(Styles.critical)
    abstract void func1Critical(byte aByte, @Unsigned byte unsignedByte,
                                int aInt, @Unsigned int unsignedInt,
                                long aLong, @Unsigned long unsignedLong,
                                short aShort, @Unsigned short unsignedShort);

    abstract void func2(char aChar, double aDouble, float aFloat, boolean aBoolean);

    @Style(Styles.critical)
    abstract void func2Critical(char aChar, double aDouble, float aFloat, boolean aBoolean);

    abstract void func3(byte[] byteArray, @Unsigned byte[] unsignedByteArray,
                        int[] intArray, @Unsigned int[] unsignedIntArray,
                        long[] longArray, @Unsigned long[] unsignedLongArray,
                        short[] shortArray, @Unsigned short[] unsignedShortArray);

    @Style(Styles.critical)
    abstract void func3Critical(byte[] byteArray, @Unsigned byte[] unsignedByteArray,
                                int[] intArray, @Unsigned int[] unsignedIntArray,
                                long[] longArray, @Unsigned long[] unsignedLongArray,
                                short[] shortArray, @Unsigned short[] unsignedShortArray);

    abstract void func4(char[] charArray, double[] doubleArray, float[] floatArray, boolean[] booleanArray);

    @Style(Styles.critical)
    abstract void func4Critical(char[] charArray, double[] doubleArray, float[] floatArray, boolean[] booleanArray);

    abstract byte retrieveByte();

    @Style(Styles.critical)
    abstract byte retrieveByteCritical();

    abstract byte retrieveUnsignedByte();

    @Style(Styles.critical)
    abstract byte retrieveUnsignedByteCritical();

    abstract char retrieveChar();

    @Style(Styles.critical)
    abstract char retrieveCharCritical();

    abstract double retrieveDouble();

    @Style(Styles.critical)
    abstract double retrieveDoubleCritical();

    abstract float retrieveFloat();

    @Style(Styles.critical)
    abstract float retrieveFloatCritical();

    abstract int retrieveInt();

    @Style(Styles.critical)
    abstract int retrieveIntCritical();

    abstract int retrieveUnsignedInt();

    @Style(Styles.critical)
    abstract int retrieveUnsignedIntCritical();

    abstract long retrieveLong();

    @Style(Styles.critical)
    abstract long retrieveLongCritical();

    abstract long retrieveUnsignedLong();

    @Style(Styles.critical)
    abstract long retrieveUnsignedLongCritical();

    abstract short retrieveShort();

    @Style(Styles.critical)
    abstract short retrieveShortCritical();

    abstract short retrieveUnsignedShort();

    @Style(Styles.critical)
    abstract short retrieveUnsignedShortCritical();

    abstract boolean retrieveBoolean();

    @Style(Styles.critical)
    abstract boolean retrieveBooleanCritical();

    abstract byte[] retrieveByteArray();

    @Style(Styles.critical)
    abstract byte[] retrieveByteArrayCritical();

    abstract byte[] retrieveUnsignedByteArray();

    @Style(Styles.critical)
    abstract byte[] retrieveUnsignedByteArrayCritical();

    abstract char[] retrieveCharArray();

    @Style(Styles.critical)
    abstract char[] retrieveCharArrayCritical();

    abstract double[] retrieveDoubleArray();

    @Style(Styles.critical)
    abstract double[] retrieveDoubleArrayCritical();

    abstract float[] retrieveFloatArray();

    @Style(Styles.critical)
    abstract float[] retrieveFloatArrayCritical();

    abstract int[] retrieveIntArray();

    @Style(Styles.critical)
    abstract int[] retrieveIntArrayCritical();

    abstract int[] retrieveUnsignedIntArray();

    @Style(Styles.critical)
    abstract int[] retrieveUnsignedIntArrayCritical();

    abstract long[] retrieveLongArray();

    @Style(Styles.critical)
    abstract long[] retrieveLongArrayCritical();

    abstract long[] retrieveUnsignedLongArray();

    @Style(Styles.critical)
    abstract long[] retrieveUnsignedLongArrayCritical();

    abstract short[] retrieveShortArray();

    @Style(Styles.critical)
    abstract short[] retrieveShortArrayCritical();

    abstract short[] retrieveUnsignedShortArray();

    @Style(Styles.critical)
    abstract short[] retrieveUnsignedShortArrayCritical();

    abstract boolean[] retrieveBooleanArray();

    @Style(Styles.critical)
    abstract boolean[] retrieveBooleanArrayCritical();

    abstract byte[] retrieveByteArrayPointer();

    @Style(Styles.critical)
    abstract byte[] retrieveByteArrayPointerCritical();

    abstract byte[] retrieveUnsignedByteArrayPointer();

    @Style(Styles.critical)
    abstract byte[] retrieveUnsignedByteArrayPointerCritical();

    abstract char[] retrieveCharArrayPointer();

    @Style(Styles.critical)
    abstract char[] retrieveCharArrayPointerCritical();

    abstract double[] retrieveDoubleArrayPointer();

    @Style(Styles.critical)
    abstract double[] retrieveDoubleArrayPointerCritical();

    abstract float[] retrieveFloatArrayPointer();

    @Style(Styles.critical)
    abstract float[] retrieveFloatArrayPointerCritical();

    abstract int[] retrieveIntArrayPointer();

    @Style(Styles.critical)
    abstract int[] retrieveIntArrayPointerCritical();

    abstract int[] retrieveUnsignedIntArrayPointer();

    @Style(Styles.critical)
    abstract int[] retrieveUnsignedIntArrayPointerCritical();

    abstract long[] retrieveLongArrayPointer();

    @Style(Styles.critical)
    abstract long[] retrieveLongArrayPointerCritical();

    abstract long[] retrieveUnsignedLongArrayPointer();

    @Style(Styles.critical)
    abstract long[] retrieveUnsignedLongArrayPointerCritical();

    abstract short[] retrieveShortArrayPointer();

    @Style(Styles.critical)
    abstract short[] retrieveShortArrayPointerCritical();

    abstract short[] retrieveUnsignedShortArrayPointer();

    @Style(Styles.critical)
    abstract short[] retrieveUnsignedShortArrayPointerCritical();

    abstract boolean[] retrieveBooleanArrayPointer();

    @Style(Styles.critical)
    abstract boolean[] retrieveBooleanArrayPointerCritical();

    abstract boolean checkPointerSetToNonNull();

    @Style(Styles.critical)
    abstract boolean checkPointerSetToNonNullCritical();

    abstract boolean checkPointerSetToNull();

    @Style(Styles.critical)
    abstract boolean checkPointerSetToNullCritical();
}
