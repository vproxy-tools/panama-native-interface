package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class PrimitiveStruct {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        ValueLayout.JAVA_BYTE.withName("aByte"),
        ValueLayout.JAVA_BYTE.withName("unsignedByte"),
        ValueLayout.JAVA_CHAR_UNALIGNED.withName("aChar"),
        MemoryLayout.sequenceLayout(4L, ValueLayout.JAVA_BYTE) /* padding */,
        ValueLayout.JAVA_DOUBLE_UNALIGNED.withName("aDouble"),
        ValueLayout.JAVA_FLOAT_UNALIGNED.withName("aFloat"),
        ValueLayout.JAVA_INT_UNALIGNED.withName("aInt"),
        ValueLayout.JAVA_INT_UNALIGNED.withName("unsignedInt"),
        MemoryLayout.sequenceLayout(4L, ValueLayout.JAVA_BYTE) /* padding */,
        ValueLayout.JAVA_LONG_UNALIGNED.withName("aLong"),
        ValueLayout.JAVA_LONG_UNALIGNED.withName("unsignedLong"),
        ValueLayout.JAVA_SHORT_UNALIGNED.withName("aShort"),
        ValueLayout.JAVA_SHORT_UNALIGNED.withName("unsignedShort"),
        ValueLayout.JAVA_BOOLEAN.withName("aBoolean"),
        MemoryLayout.sequenceLayout(11L, ValueLayout.JAVA_BYTE).withName("byteArray"),
        MemoryLayout.sequenceLayout(12L, ValueLayout.JAVA_BYTE).withName("unsignedByteArray"),
        MemoryLayout.sequenceLayout(13L, ValueLayout.JAVA_CHAR_UNALIGNED).withName("charArray"),
        MemoryLayout.sequenceLayout(2L, ValueLayout.JAVA_BYTE) /* padding */,
        MemoryLayout.sequenceLayout(14L, ValueLayout.JAVA_DOUBLE_UNALIGNED).withName("doubleArray"),
        MemoryLayout.sequenceLayout(15L, ValueLayout.JAVA_FLOAT_UNALIGNED).withName("floatArray"),
        MemoryLayout.sequenceLayout(16L, ValueLayout.JAVA_INT_UNALIGNED).withName("intArray"),
        MemoryLayout.sequenceLayout(17L, ValueLayout.JAVA_INT_UNALIGNED).withName("unsignedIntArray"),
        MemoryLayout.sequenceLayout(18L, ValueLayout.JAVA_LONG_UNALIGNED).withName("longArray"),
        MemoryLayout.sequenceLayout(19L, ValueLayout.JAVA_LONG_UNALIGNED).withName("unsignedLongArray"),
        MemoryLayout.sequenceLayout(20L, ValueLayout.JAVA_SHORT_UNALIGNED).withName("shortArray"),
        MemoryLayout.sequenceLayout(21L, ValueLayout.JAVA_SHORT_UNALIGNED).withName("unsignedShortArray"),
        MemoryLayout.sequenceLayout(22L, ValueLayout.JAVA_BOOLEAN).withName("booleanArray"),
        PNIBuf.LAYOUT.withName("byteArrayPointer"),
        PNIBuf.LAYOUT.withName("unsignedByteArrayPointer"),
        PNIBuf.LAYOUT.withName("charArrayPointer"),
        PNIBuf.LAYOUT.withName("doubleArrayPointer"),
        PNIBuf.LAYOUT.withName("floatArrayPointer"),
        PNIBuf.LAYOUT.withName("intArrayPointer"),
        PNIBuf.LAYOUT.withName("unsignedIntArrayPointer"),
        PNIBuf.LAYOUT.withName("longArrayPointer"),
        PNIBuf.LAYOUT.withName("unsignedLongArrayPointer"),
        PNIBuf.LAYOUT.withName("shortArrayPointer"),
        PNIBuf.LAYOUT.withName("unsignedShortArrayPointer"),
        PNIBuf.LAYOUT.withName("booleanArrayPointer")
    );
    public final MemorySegment MEMORY;

    private static final VarHandle aByteVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("aByte")
    );

    public byte getAByte() {
        return (byte) aByteVH.get(MEMORY);
    }

    public void setAByte(byte aByte) {
        aByteVH.set(MEMORY, aByte);
    }

    private static final VarHandle unsignedByteVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("unsignedByte")
    );

    public byte getUnsignedByte() {
        return (byte) unsignedByteVH.get(MEMORY);
    }

    public void setUnsignedByte(byte unsignedByte) {
        unsignedByteVH.set(MEMORY, unsignedByte);
    }

    private static final VarHandle aCharVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("aChar")
    );

    public char getAChar() {
        return (char) aCharVH.get(MEMORY);
    }

    public void setAChar(char aChar) {
        aCharVH.set(MEMORY, aChar);
    }

    private static final VarHandle aDoubleVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("aDouble")
    );

    public double getADouble() {
        return (double) aDoubleVH.get(MEMORY);
    }

    public void setADouble(double aDouble) {
        aDoubleVH.set(MEMORY, aDouble);
    }

    private static final VarHandle aFloatVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("aFloat")
    );

    public float getAFloat() {
        return (float) aFloatVH.get(MEMORY);
    }

    public void setAFloat(float aFloat) {
        aFloatVH.set(MEMORY, aFloat);
    }

    private static final VarHandle aIntVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("aInt")
    );

    public int getAInt() {
        return (int) aIntVH.get(MEMORY);
    }

    public void setAInt(int aInt) {
        aIntVH.set(MEMORY, aInt);
    }

    private static final VarHandle unsignedIntVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("unsignedInt")
    );

    public int getUnsignedInt() {
        return (int) unsignedIntVH.get(MEMORY);
    }

    public void setUnsignedInt(int unsignedInt) {
        unsignedIntVH.set(MEMORY, unsignedInt);
    }

    private static final VarHandle aLongVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("aLong")
    );

    public long getALong() {
        return (long) aLongVH.get(MEMORY);
    }

    public void setALong(long aLong) {
        aLongVH.set(MEMORY, aLong);
    }

    private static final VarHandle unsignedLongVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("unsignedLong")
    );

    public long getUnsignedLong() {
        return (long) unsignedLongVH.get(MEMORY);
    }

    public void setUnsignedLong(long unsignedLong) {
        unsignedLongVH.set(MEMORY, unsignedLong);
    }

    private static final VarHandle aShortVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("aShort")
    );

    public short getAShort() {
        return (short) aShortVH.get(MEMORY);
    }

    public void setAShort(short aShort) {
        aShortVH.set(MEMORY, aShort);
    }

    private static final VarHandle unsignedShortVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("unsignedShort")
    );

    public short getUnsignedShort() {
        return (short) unsignedShortVH.get(MEMORY);
    }

    public void setUnsignedShort(short unsignedShort) {
        unsignedShortVH.set(MEMORY, unsignedShort);
    }

    private static final VarHandle aBooleanVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("aBoolean")
    );

    public boolean getABoolean() {
        return (boolean) aBooleanVH.get(MEMORY);
    }

    public void setABoolean(boolean aBoolean) {
        aBooleanVH.set(MEMORY, aBoolean);
    }

    private final MemorySegment byteArray;

    public MemorySegment getByteArray() {
        return this.byteArray;
    }

    private final MemorySegment unsignedByteArray;

    public MemorySegment getUnsignedByteArray() {
        return this.unsignedByteArray;
    }

    private final CharArray charArray;

    public CharArray getCharArray() {
        return this.charArray;
    }

    private final DoubleArray doubleArray;

    public DoubleArray getDoubleArray() {
        return this.doubleArray;
    }

    private final FloatArray floatArray;

    public FloatArray getFloatArray() {
        return this.floatArray;
    }

    private final IntArray intArray;

    public IntArray getIntArray() {
        return this.intArray;
    }

    private final IntArray unsignedIntArray;

    public IntArray getUnsignedIntArray() {
        return this.unsignedIntArray;
    }

    private final LongArray longArray;

    public LongArray getLongArray() {
        return this.longArray;
    }

    private final LongArray unsignedLongArray;

    public LongArray getUnsignedLongArray() {
        return this.unsignedLongArray;
    }

    private final ShortArray shortArray;

    public ShortArray getShortArray() {
        return this.shortArray;
    }

    private final ShortArray unsignedShortArray;

    public ShortArray getUnsignedShortArray() {
        return this.unsignedShortArray;
    }

    private final BoolArray booleanArray;

    public BoolArray getBooleanArray() {
        return this.booleanArray;
    }

    private final PNIBuf byteArrayPointer;

    public MemorySegment getByteArrayPointer() {
        var SEG = this.byteArrayPointer.get();
        if (SEG == null) return null;
        return SEG;
    }

    public void setByteArrayPointer(MemorySegment byteArrayPointer) {
        if (byteArrayPointer == null) {
            this.byteArrayPointer.setToNull();
        } else {
            this.byteArrayPointer.set(byteArrayPointer);
        }
    }

    private final PNIBuf unsignedByteArrayPointer;

    public MemorySegment getUnsignedByteArrayPointer() {
        var SEG = this.unsignedByteArrayPointer.get();
        if (SEG == null) return null;
        return SEG;
    }

    public void setUnsignedByteArrayPointer(MemorySegment unsignedByteArrayPointer) {
        if (unsignedByteArrayPointer == null) {
            this.unsignedByteArrayPointer.setToNull();
        } else {
            this.unsignedByteArrayPointer.set(unsignedByteArrayPointer);
        }
    }

    private final PNIBuf charArrayPointer;

    public CharArray getCharArrayPointer() {
        var SEG = this.charArrayPointer.get();
        if (SEG == null) return null;
        return new CharArray(SEG);
    }

    public void setCharArrayPointer(CharArray charArrayPointer) {
        if (charArrayPointer == null) {
            this.charArrayPointer.setToNull();
        } else {
            this.charArrayPointer.set(charArrayPointer.MEMORY);
        }
    }

    private final PNIBuf doubleArrayPointer;

    public DoubleArray getDoubleArrayPointer() {
        var SEG = this.doubleArrayPointer.get();
        if (SEG == null) return null;
        return new DoubleArray(SEG);
    }

    public void setDoubleArrayPointer(DoubleArray doubleArrayPointer) {
        if (doubleArrayPointer == null) {
            this.doubleArrayPointer.setToNull();
        } else {
            this.doubleArrayPointer.set(doubleArrayPointer.MEMORY);
        }
    }

    private final PNIBuf floatArrayPointer;

    public FloatArray getFloatArrayPointer() {
        var SEG = this.floatArrayPointer.get();
        if (SEG == null) return null;
        return new FloatArray(SEG);
    }

    public void setFloatArrayPointer(FloatArray floatArrayPointer) {
        if (floatArrayPointer == null) {
            this.floatArrayPointer.setToNull();
        } else {
            this.floatArrayPointer.set(floatArrayPointer.MEMORY);
        }
    }

    private final PNIBuf intArrayPointer;

    public IntArray getIntArrayPointer() {
        var SEG = this.intArrayPointer.get();
        if (SEG == null) return null;
        return new IntArray(SEG);
    }

    public void setIntArrayPointer(IntArray intArrayPointer) {
        if (intArrayPointer == null) {
            this.intArrayPointer.setToNull();
        } else {
            this.intArrayPointer.set(intArrayPointer.MEMORY);
        }
    }

    private final PNIBuf unsignedIntArrayPointer;

    public IntArray getUnsignedIntArrayPointer() {
        var SEG = this.unsignedIntArrayPointer.get();
        if (SEG == null) return null;
        return new IntArray(SEG);
    }

    public void setUnsignedIntArrayPointer(IntArray unsignedIntArrayPointer) {
        if (unsignedIntArrayPointer == null) {
            this.unsignedIntArrayPointer.setToNull();
        } else {
            this.unsignedIntArrayPointer.set(unsignedIntArrayPointer.MEMORY);
        }
    }

    private final PNIBuf longArrayPointer;

    public LongArray getLongArrayPointer() {
        var SEG = this.longArrayPointer.get();
        if (SEG == null) return null;
        return new LongArray(SEG);
    }

    public void setLongArrayPointer(LongArray longArrayPointer) {
        if (longArrayPointer == null) {
            this.longArrayPointer.setToNull();
        } else {
            this.longArrayPointer.set(longArrayPointer.MEMORY);
        }
    }

    private final PNIBuf unsignedLongArrayPointer;

    public LongArray getUnsignedLongArrayPointer() {
        var SEG = this.unsignedLongArrayPointer.get();
        if (SEG == null) return null;
        return new LongArray(SEG);
    }

    public void setUnsignedLongArrayPointer(LongArray unsignedLongArrayPointer) {
        if (unsignedLongArrayPointer == null) {
            this.unsignedLongArrayPointer.setToNull();
        } else {
            this.unsignedLongArrayPointer.set(unsignedLongArrayPointer.MEMORY);
        }
    }

    private final PNIBuf shortArrayPointer;

    public ShortArray getShortArrayPointer() {
        var SEG = this.shortArrayPointer.get();
        if (SEG == null) return null;
        return new ShortArray(SEG);
    }

    public void setShortArrayPointer(ShortArray shortArrayPointer) {
        if (shortArrayPointer == null) {
            this.shortArrayPointer.setToNull();
        } else {
            this.shortArrayPointer.set(shortArrayPointer.MEMORY);
        }
    }

    private final PNIBuf unsignedShortArrayPointer;

    public ShortArray getUnsignedShortArrayPointer() {
        var SEG = this.unsignedShortArrayPointer.get();
        if (SEG == null) return null;
        return new ShortArray(SEG);
    }

    public void setUnsignedShortArrayPointer(ShortArray unsignedShortArrayPointer) {
        if (unsignedShortArrayPointer == null) {
            this.unsignedShortArrayPointer.setToNull();
        } else {
            this.unsignedShortArrayPointer.set(unsignedShortArrayPointer.MEMORY);
        }
    }

    private final PNIBuf booleanArrayPointer;

    public BoolArray getBooleanArrayPointer() {
        var SEG = this.booleanArrayPointer.get();
        if (SEG == null) return null;
        return new BoolArray(SEG);
    }

    public void setBooleanArrayPointer(BoolArray booleanArrayPointer) {
        if (booleanArrayPointer == null) {
            this.booleanArrayPointer.setToNull();
        } else {
            this.booleanArrayPointer.set(booleanArrayPointer.MEMORY);
        }
    }

    public PrimitiveStruct(MemorySegment MEMORY) {
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
        OFFSET += ValueLayout.JAVA_BYTE.byteSize();
        OFFSET += ValueLayout.JAVA_BYTE.byteSize();
        OFFSET += ValueLayout.JAVA_CHAR_UNALIGNED.byteSize();
        OFFSET += 4; /* padding */
        OFFSET += ValueLayout.JAVA_DOUBLE_UNALIGNED.byteSize();
        OFFSET += ValueLayout.JAVA_FLOAT_UNALIGNED.byteSize();
        OFFSET += ValueLayout.JAVA_INT_UNALIGNED.byteSize();
        OFFSET += ValueLayout.JAVA_INT_UNALIGNED.byteSize();
        OFFSET += 4; /* padding */
        OFFSET += ValueLayout.JAVA_LONG_UNALIGNED.byteSize();
        OFFSET += ValueLayout.JAVA_LONG_UNALIGNED.byteSize();
        OFFSET += ValueLayout.JAVA_SHORT_UNALIGNED.byteSize();
        OFFSET += ValueLayout.JAVA_SHORT_UNALIGNED.byteSize();
        OFFSET += ValueLayout.JAVA_BOOLEAN.byteSize();
        this.byteArray = MEMORY.asSlice(OFFSET, 11);
        OFFSET += 11;
        this.unsignedByteArray = MEMORY.asSlice(OFFSET, 12);
        OFFSET += 12;
        this.charArray = new CharArray(MEMORY.asSlice(OFFSET, 13 * ValueLayout.JAVA_CHAR_UNALIGNED.byteSize()));
        OFFSET += 13 * ValueLayout.JAVA_CHAR_UNALIGNED.byteSize();
        OFFSET += 2; /* padding */
        this.doubleArray = new DoubleArray(MEMORY.asSlice(OFFSET, 14 * ValueLayout.JAVA_DOUBLE_UNALIGNED.byteSize()));
        OFFSET += 14 * ValueLayout.JAVA_DOUBLE_UNALIGNED.byteSize();
        this.floatArray = new FloatArray(MEMORY.asSlice(OFFSET, 15 * ValueLayout.JAVA_FLOAT_UNALIGNED.byteSize()));
        OFFSET += 15 * ValueLayout.JAVA_FLOAT_UNALIGNED.byteSize();
        this.intArray = new IntArray(MEMORY.asSlice(OFFSET, 16 * ValueLayout.JAVA_INT_UNALIGNED.byteSize()));
        OFFSET += 16 * ValueLayout.JAVA_INT_UNALIGNED.byteSize();
        this.unsignedIntArray = new IntArray(MEMORY.asSlice(OFFSET, 17 * ValueLayout.JAVA_INT_UNALIGNED.byteSize()));
        OFFSET += 17 * ValueLayout.JAVA_INT_UNALIGNED.byteSize();
        this.longArray = new LongArray(MEMORY.asSlice(OFFSET, 18 * ValueLayout.JAVA_LONG_UNALIGNED.byteSize()));
        OFFSET += 18 * ValueLayout.JAVA_LONG_UNALIGNED.byteSize();
        this.unsignedLongArray = new LongArray(MEMORY.asSlice(OFFSET, 19 * ValueLayout.JAVA_LONG_UNALIGNED.byteSize()));
        OFFSET += 19 * ValueLayout.JAVA_LONG_UNALIGNED.byteSize();
        this.shortArray = new ShortArray(MEMORY.asSlice(OFFSET, 20 * ValueLayout.JAVA_SHORT_UNALIGNED.byteSize()));
        OFFSET += 20 * ValueLayout.JAVA_SHORT_UNALIGNED.byteSize();
        this.unsignedShortArray = new ShortArray(MEMORY.asSlice(OFFSET, 21 * ValueLayout.JAVA_SHORT_UNALIGNED.byteSize()));
        OFFSET += 21 * ValueLayout.JAVA_SHORT_UNALIGNED.byteSize();
        this.booleanArray = new BoolArray(MEMORY.asSlice(OFFSET, 22 * ValueLayout.JAVA_BOOLEAN.byteSize()));
        OFFSET += 22 * ValueLayout.JAVA_BOOLEAN.byteSize();
        this.byteArrayPointer = new PNIBuf(MEMORY.asSlice(OFFSET, PNIBuf.LAYOUT.byteSize()));
        OFFSET += PNIBuf.LAYOUT.byteSize();
        this.unsignedByteArrayPointer = new PNIBuf(MEMORY.asSlice(OFFSET, PNIBuf.LAYOUT.byteSize()));
        OFFSET += PNIBuf.LAYOUT.byteSize();
        this.charArrayPointer = new PNIBuf(MEMORY.asSlice(OFFSET, PNIBuf.LAYOUT.byteSize()));
        OFFSET += PNIBuf.LAYOUT.byteSize();
        this.doubleArrayPointer = new PNIBuf(MEMORY.asSlice(OFFSET, PNIBuf.LAYOUT.byteSize()));
        OFFSET += PNIBuf.LAYOUT.byteSize();
        this.floatArrayPointer = new PNIBuf(MEMORY.asSlice(OFFSET, PNIBuf.LAYOUT.byteSize()));
        OFFSET += PNIBuf.LAYOUT.byteSize();
        this.intArrayPointer = new PNIBuf(MEMORY.asSlice(OFFSET, PNIBuf.LAYOUT.byteSize()));
        OFFSET += PNIBuf.LAYOUT.byteSize();
        this.unsignedIntArrayPointer = new PNIBuf(MEMORY.asSlice(OFFSET, PNIBuf.LAYOUT.byteSize()));
        OFFSET += PNIBuf.LAYOUT.byteSize();
        this.longArrayPointer = new PNIBuf(MEMORY.asSlice(OFFSET, PNIBuf.LAYOUT.byteSize()));
        OFFSET += PNIBuf.LAYOUT.byteSize();
        this.unsignedLongArrayPointer = new PNIBuf(MEMORY.asSlice(OFFSET, PNIBuf.LAYOUT.byteSize()));
        OFFSET += PNIBuf.LAYOUT.byteSize();
        this.shortArrayPointer = new PNIBuf(MEMORY.asSlice(OFFSET, PNIBuf.LAYOUT.byteSize()));
        OFFSET += PNIBuf.LAYOUT.byteSize();
        this.unsignedShortArrayPointer = new PNIBuf(MEMORY.asSlice(OFFSET, PNIBuf.LAYOUT.byteSize()));
        OFFSET += PNIBuf.LAYOUT.byteSize();
        this.booleanArrayPointer = new PNIBuf(MEMORY.asSlice(OFFSET, PNIBuf.LAYOUT.byteSize()));
        OFFSET += PNIBuf.LAYOUT.byteSize();
    }

    public PrimitiveStruct(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT.byteSize()));
    }

    private static final MethodHandle func1MH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PrimitiveStruct_func1", MemorySegment.class /* self */, byte.class /* aByte */, byte.class /* unsignedByte */, int.class /* aInt */, int.class /* unsignedInt */, long.class /* aLong */, long.class /* unsignedLong */, short.class /* aShort */, short.class /* unsignedShort */);

    public void func1(PNIEnv ENV, byte aByte, byte unsignedByte, int aInt, int unsignedInt, long aLong, long unsignedLong, short aShort, short unsignedShort) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.func1MH.invokeExact(ENV.MEMORY, MEMORY, aByte, unsignedByte, aInt, unsignedInt, aLong, unsignedLong, aShort, unsignedShort);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
    }

    private static final MethodHandle func1CriticalMH = PanamaUtils.lookupPNICriticalFunction(false, void.class, "JavaCritical_io_vproxy_pni_test_PrimitiveStruct_func1Critical", MemorySegment.class /* self */, byte.class /* aByte */, byte.class /* unsignedByte */, int.class /* aInt */, int.class /* unsignedInt */, long.class /* aLong */, long.class /* unsignedLong */, short.class /* aShort */, short.class /* unsignedShort */);

    public void func1Critical(byte aByte, byte unsignedByte, int aInt, int unsignedInt, long aLong, long unsignedLong, short aShort, short unsignedShort) {
        try {
            this.func1CriticalMH.invokeExact(MEMORY, aByte, unsignedByte, aInt, unsignedInt, aLong, unsignedLong, aShort, unsignedShort);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle func2MH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PrimitiveStruct_func2", MemorySegment.class /* self */, char.class /* aChar */, double.class /* aDouble */, float.class /* aFloat */, boolean.class /* aBoolean */);

    public void func2(PNIEnv ENV, char aChar, double aDouble, float aFloat, boolean aBoolean) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.func2MH.invokeExact(ENV.MEMORY, MEMORY, aChar, aDouble, aFloat, aBoolean);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
    }

    private static final MethodHandle func2CriticalMH = PanamaUtils.lookupPNICriticalFunction(false, void.class, "JavaCritical_io_vproxy_pni_test_PrimitiveStruct_func2Critical", MemorySegment.class /* self */, char.class /* aChar */, double.class /* aDouble */, float.class /* aFloat */, boolean.class /* aBoolean */);

    public void func2Critical(char aChar, double aDouble, float aFloat, boolean aBoolean) {
        try {
            this.func2CriticalMH.invokeExact(MEMORY, aChar, aDouble, aFloat, aBoolean);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle func3MH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PrimitiveStruct_func3", MemorySegment.class /* self */, PNIBuf.class /* byteArray */, PNIBuf.class /* unsignedByteArray */, PNIBuf.class /* intArray */, PNIBuf.class /* unsignedIntArray */, PNIBuf.class /* longArray */, PNIBuf.class /* unsignedLongArray */, PNIBuf.class /* shortArray */, PNIBuf.class /* unsignedShortArray */);

    public void func3(PNIEnv ENV, MemorySegment byteArray, MemorySegment unsignedByteArray, IntArray intArray, IntArray unsignedIntArray, LongArray longArray, LongArray unsignedLongArray, ShortArray shortArray, ShortArray unsignedShortArray) {
        ENV.reset();
        try (var POOLED = Allocator.ofPooled()) {
            int ERR;
            try {
                ERR = (int) this.func3MH.invokeExact(ENV.MEMORY, MEMORY, PNIBuf.memoryOf(POOLED, byteArray), PNIBuf.memoryOf(POOLED, unsignedByteArray), PNIBuf.memoryOf(POOLED, intArray), PNIBuf.memoryOf(POOLED, unsignedIntArray), PNIBuf.memoryOf(POOLED, longArray), PNIBuf.memoryOf(POOLED, unsignedLongArray), PNIBuf.memoryOf(POOLED, shortArray), PNIBuf.memoryOf(POOLED, unsignedShortArray));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (ERR != 0) {
                ENV.throwLast();
            }
        }
    }

    private static final MethodHandle func3CriticalMH = PanamaUtils.lookupPNICriticalFunction(false, void.class, "JavaCritical_io_vproxy_pni_test_PrimitiveStruct_func3Critical", MemorySegment.class /* self */, PNIBuf.class /* byteArray */, PNIBuf.class /* unsignedByteArray */, PNIBuf.class /* intArray */, PNIBuf.class /* unsignedIntArray */, PNIBuf.class /* longArray */, PNIBuf.class /* unsignedLongArray */, PNIBuf.class /* shortArray */, PNIBuf.class /* unsignedShortArray */);

    public void func3Critical(MemorySegment byteArray, MemorySegment unsignedByteArray, IntArray intArray, IntArray unsignedIntArray, LongArray longArray, LongArray unsignedLongArray, ShortArray shortArray, ShortArray unsignedShortArray) {
        try (var POOLED = Allocator.ofPooled()) {
            try {
                this.func3CriticalMH.invokeExact(MEMORY, PNIBuf.memoryOf(POOLED, byteArray), PNIBuf.memoryOf(POOLED, unsignedByteArray), PNIBuf.memoryOf(POOLED, intArray), PNIBuf.memoryOf(POOLED, unsignedIntArray), PNIBuf.memoryOf(POOLED, longArray), PNIBuf.memoryOf(POOLED, unsignedLongArray), PNIBuf.memoryOf(POOLED, shortArray), PNIBuf.memoryOf(POOLED, unsignedShortArray));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
        }
    }

    private static final MethodHandle func4MH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PrimitiveStruct_func4", MemorySegment.class /* self */, PNIBuf.class /* charArray */, PNIBuf.class /* doubleArray */, PNIBuf.class /* floatArray */, PNIBuf.class /* booleanArray */);

    public void func4(PNIEnv ENV, CharArray charArray, DoubleArray doubleArray, FloatArray floatArray, BoolArray booleanArray) {
        ENV.reset();
        try (var POOLED = Allocator.ofPooled()) {
            int ERR;
            try {
                ERR = (int) this.func4MH.invokeExact(ENV.MEMORY, MEMORY, PNIBuf.memoryOf(POOLED, charArray), PNIBuf.memoryOf(POOLED, doubleArray), PNIBuf.memoryOf(POOLED, floatArray), PNIBuf.memoryOf(POOLED, booleanArray));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (ERR != 0) {
                ENV.throwLast();
            }
        }
    }

    private static final MethodHandle func4CriticalMH = PanamaUtils.lookupPNICriticalFunction(false, void.class, "JavaCritical_io_vproxy_pni_test_PrimitiveStruct_func4Critical", MemorySegment.class /* self */, PNIBuf.class /* charArray */, PNIBuf.class /* doubleArray */, PNIBuf.class /* floatArray */, PNIBuf.class /* booleanArray */);

    public void func4Critical(CharArray charArray, DoubleArray doubleArray, FloatArray floatArray, BoolArray booleanArray) {
        try (var POOLED = Allocator.ofPooled()) {
            try {
                this.func4CriticalMH.invokeExact(MEMORY, PNIBuf.memoryOf(POOLED, charArray), PNIBuf.memoryOf(POOLED, doubleArray), PNIBuf.memoryOf(POOLED, floatArray), PNIBuf.memoryOf(POOLED, booleanArray));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
        }
    }

    private static final MethodHandle retrieveByteMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PrimitiveStruct_retrieveByte", MemorySegment.class /* self */);

    public byte retrieveByte(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.retrieveByteMH.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnByte();
    }

    private static final MethodHandle retrieveByteCriticalMH = PanamaUtils.lookupPNICriticalFunction(false, byte.class, "JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveByteCritical", MemorySegment.class /* self */);

    public byte retrieveByteCritical() {
        byte RESULT;
        try {
            RESULT = (byte) this.retrieveByteCriticalMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle retrieveUnsignedByteMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedByte", MemorySegment.class /* self */);

    public byte retrieveUnsignedByte(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.retrieveUnsignedByteMH.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnByte();
    }

    private static final MethodHandle retrieveUnsignedByteCriticalMH = PanamaUtils.lookupPNICriticalFunction(false, byte.class, "JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedByteCritical", MemorySegment.class /* self */);

    public byte retrieveUnsignedByteCritical() {
        byte RESULT;
        try {
            RESULT = (byte) this.retrieveUnsignedByteCriticalMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle retrieveCharMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PrimitiveStruct_retrieveChar", MemorySegment.class /* self */);

    public char retrieveChar(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.retrieveCharMH.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnChar();
    }

    private static final MethodHandle retrieveCharCriticalMH = PanamaUtils.lookupPNICriticalFunction(false, char.class, "JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveCharCritical", MemorySegment.class /* self */);

    public char retrieveCharCritical() {
        char RESULT;
        try {
            RESULT = (char) this.retrieveCharCriticalMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle retrieveDoubleMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PrimitiveStruct_retrieveDouble", MemorySegment.class /* self */);

    public double retrieveDouble(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.retrieveDoubleMH.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnDouble();
    }

    private static final MethodHandle retrieveDoubleCriticalMH = PanamaUtils.lookupPNICriticalFunction(false, double.class, "JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveDoubleCritical", MemorySegment.class /* self */);

    public double retrieveDoubleCritical() {
        double RESULT;
        try {
            RESULT = (double) this.retrieveDoubleCriticalMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle retrieveFloatMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PrimitiveStruct_retrieveFloat", MemorySegment.class /* self */);

    public float retrieveFloat(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.retrieveFloatMH.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnFloat();
    }

    private static final MethodHandle retrieveFloatCriticalMH = PanamaUtils.lookupPNICriticalFunction(false, float.class, "JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveFloatCritical", MemorySegment.class /* self */);

    public float retrieveFloatCritical() {
        float RESULT;
        try {
            RESULT = (float) this.retrieveFloatCriticalMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle retrieveIntMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PrimitiveStruct_retrieveInt", MemorySegment.class /* self */);

    public int retrieveInt(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.retrieveIntMH.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnInt();
    }

    private static final MethodHandle retrieveIntCriticalMH = PanamaUtils.lookupPNICriticalFunction(false, int.class, "JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveIntCritical", MemorySegment.class /* self */);

    public int retrieveIntCritical() {
        int RESULT;
        try {
            RESULT = (int) this.retrieveIntCriticalMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle retrieveUnsignedIntMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedInt", MemorySegment.class /* self */);

    public int retrieveUnsignedInt(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.retrieveUnsignedIntMH.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnInt();
    }

    private static final MethodHandle retrieveUnsignedIntCriticalMH = PanamaUtils.lookupPNICriticalFunction(false, int.class, "JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedIntCritical", MemorySegment.class /* self */);

    public int retrieveUnsignedIntCritical() {
        int RESULT;
        try {
            RESULT = (int) this.retrieveUnsignedIntCriticalMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle retrieveLongMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PrimitiveStruct_retrieveLong", MemorySegment.class /* self */);

    public long retrieveLong(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.retrieveLongMH.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnLong();
    }

    private static final MethodHandle retrieveLongCriticalMH = PanamaUtils.lookupPNICriticalFunction(false, long.class, "JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveLongCritical", MemorySegment.class /* self */);

    public long retrieveLongCritical() {
        long RESULT;
        try {
            RESULT = (long) this.retrieveLongCriticalMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle retrieveUnsignedLongMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedLong", MemorySegment.class /* self */);

    public long retrieveUnsignedLong(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.retrieveUnsignedLongMH.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnLong();
    }

    private static final MethodHandle retrieveUnsignedLongCriticalMH = PanamaUtils.lookupPNICriticalFunction(false, long.class, "JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedLongCritical", MemorySegment.class /* self */);

    public long retrieveUnsignedLongCritical() {
        long RESULT;
        try {
            RESULT = (long) this.retrieveUnsignedLongCriticalMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle retrieveShortMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PrimitiveStruct_retrieveShort", MemorySegment.class /* self */);

    public short retrieveShort(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.retrieveShortMH.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnShort();
    }

    private static final MethodHandle retrieveShortCriticalMH = PanamaUtils.lookupPNICriticalFunction(false, short.class, "JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveShortCritical", MemorySegment.class /* self */);

    public short retrieveShortCritical() {
        short RESULT;
        try {
            RESULT = (short) this.retrieveShortCriticalMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle retrieveUnsignedShortMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedShort", MemorySegment.class /* self */);

    public short retrieveUnsignedShort(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.retrieveUnsignedShortMH.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnShort();
    }

    private static final MethodHandle retrieveUnsignedShortCriticalMH = PanamaUtils.lookupPNICriticalFunction(false, short.class, "JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedShortCritical", MemorySegment.class /* self */);

    public short retrieveUnsignedShortCritical() {
        short RESULT;
        try {
            RESULT = (short) this.retrieveUnsignedShortCriticalMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle retrieveBooleanMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PrimitiveStruct_retrieveBoolean", MemorySegment.class /* self */);

    public boolean retrieveBoolean(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.retrieveBooleanMH.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnBool();
    }

    private static final MethodHandle retrieveBooleanCriticalMH = PanamaUtils.lookupPNICriticalFunction(false, boolean.class, "JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveBooleanCritical", MemorySegment.class /* self */);

    public boolean retrieveBooleanCritical() {
        boolean RESULT;
        try {
            RESULT = (boolean) this.retrieveBooleanCriticalMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle retrieveByteArrayMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PrimitiveStruct_retrieveByteArray", MemorySegment.class /* self */);

    public MemorySegment retrieveByteArray(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.retrieveByteArrayMH.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RES_SEG = ENV.returnBuf();
        if (RES_SEG.isNull()) return null;
        return RES_SEG.get();
    }

    private static final MethodHandle retrieveByteArrayCriticalMH = PanamaUtils.lookupPNICriticalFunction(false, PNIBuf.class, "JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveByteArrayCritical", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public MemorySegment retrieveByteArrayCritical() {
        try (var POOLED = Allocator.ofPooled()) {
            MemorySegment RESULT;
            try {
                RESULT = (MemorySegment) this.retrieveByteArrayCriticalMH.invokeExact(MEMORY, POOLED.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (RESULT.address() == 0) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return RES_SEG.get();
        }
    }

    private static final MethodHandle retrieveUnsignedByteArrayMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedByteArray", MemorySegment.class /* self */);

    public MemorySegment retrieveUnsignedByteArray(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.retrieveUnsignedByteArrayMH.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RES_SEG = ENV.returnBuf();
        if (RES_SEG.isNull()) return null;
        return RES_SEG.get();
    }

    private static final MethodHandle retrieveUnsignedByteArrayCriticalMH = PanamaUtils.lookupPNICriticalFunction(false, PNIBuf.class, "JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedByteArrayCritical", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public MemorySegment retrieveUnsignedByteArrayCritical() {
        try (var POOLED = Allocator.ofPooled()) {
            MemorySegment RESULT;
            try {
                RESULT = (MemorySegment) this.retrieveUnsignedByteArrayCriticalMH.invokeExact(MEMORY, POOLED.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (RESULT.address() == 0) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return RES_SEG.get();
        }
    }

    private static final MethodHandle retrieveCharArrayMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PrimitiveStruct_retrieveCharArray", MemorySegment.class /* self */);

    public CharArray retrieveCharArray(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.retrieveCharArrayMH.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RES_SEG = ENV.returnBuf();
        if (RES_SEG.isNull()) return null;
        return new CharArray(RES_SEG);
    }

    private static final MethodHandle retrieveCharArrayCriticalMH = PanamaUtils.lookupPNICriticalFunction(false, PNIBuf.class, "JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveCharArrayCritical", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public CharArray retrieveCharArrayCritical() {
        try (var POOLED = Allocator.ofPooled()) {
            MemorySegment RESULT;
            try {
                RESULT = (MemorySegment) this.retrieveCharArrayCriticalMH.invokeExact(MEMORY, POOLED.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (RESULT.address() == 0) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return new CharArray(RES_SEG);
        }
    }

    private static final MethodHandle retrieveDoubleArrayMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PrimitiveStruct_retrieveDoubleArray", MemorySegment.class /* self */);

    public DoubleArray retrieveDoubleArray(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.retrieveDoubleArrayMH.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RES_SEG = ENV.returnBuf();
        if (RES_SEG.isNull()) return null;
        return new DoubleArray(RES_SEG);
    }

    private static final MethodHandle retrieveDoubleArrayCriticalMH = PanamaUtils.lookupPNICriticalFunction(false, PNIBuf.class, "JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveDoubleArrayCritical", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public DoubleArray retrieveDoubleArrayCritical() {
        try (var POOLED = Allocator.ofPooled()) {
            MemorySegment RESULT;
            try {
                RESULT = (MemorySegment) this.retrieveDoubleArrayCriticalMH.invokeExact(MEMORY, POOLED.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (RESULT.address() == 0) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return new DoubleArray(RES_SEG);
        }
    }

    private static final MethodHandle retrieveFloatArrayMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PrimitiveStruct_retrieveFloatArray", MemorySegment.class /* self */);

    public FloatArray retrieveFloatArray(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.retrieveFloatArrayMH.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RES_SEG = ENV.returnBuf();
        if (RES_SEG.isNull()) return null;
        return new FloatArray(RES_SEG);
    }

    private static final MethodHandle retrieveFloatArrayCriticalMH = PanamaUtils.lookupPNICriticalFunction(false, PNIBuf.class, "JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveFloatArrayCritical", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public FloatArray retrieveFloatArrayCritical() {
        try (var POOLED = Allocator.ofPooled()) {
            MemorySegment RESULT;
            try {
                RESULT = (MemorySegment) this.retrieveFloatArrayCriticalMH.invokeExact(MEMORY, POOLED.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (RESULT.address() == 0) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return new FloatArray(RES_SEG);
        }
    }

    private static final MethodHandle retrieveIntArrayMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PrimitiveStruct_retrieveIntArray", MemorySegment.class /* self */);

    public IntArray retrieveIntArray(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.retrieveIntArrayMH.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RES_SEG = ENV.returnBuf();
        if (RES_SEG.isNull()) return null;
        return new IntArray(RES_SEG);
    }

    private static final MethodHandle retrieveIntArrayCriticalMH = PanamaUtils.lookupPNICriticalFunction(false, PNIBuf.class, "JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveIntArrayCritical", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public IntArray retrieveIntArrayCritical() {
        try (var POOLED = Allocator.ofPooled()) {
            MemorySegment RESULT;
            try {
                RESULT = (MemorySegment) this.retrieveIntArrayCriticalMH.invokeExact(MEMORY, POOLED.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (RESULT.address() == 0) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return new IntArray(RES_SEG);
        }
    }

    private static final MethodHandle retrieveUnsignedIntArrayMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedIntArray", MemorySegment.class /* self */);

    public IntArray retrieveUnsignedIntArray(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.retrieveUnsignedIntArrayMH.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RES_SEG = ENV.returnBuf();
        if (RES_SEG.isNull()) return null;
        return new IntArray(RES_SEG);
    }

    private static final MethodHandle retrieveUnsignedIntArrayCriticalMH = PanamaUtils.lookupPNICriticalFunction(false, PNIBuf.class, "JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedIntArrayCritical", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public IntArray retrieveUnsignedIntArrayCritical() {
        try (var POOLED = Allocator.ofPooled()) {
            MemorySegment RESULT;
            try {
                RESULT = (MemorySegment) this.retrieveUnsignedIntArrayCriticalMH.invokeExact(MEMORY, POOLED.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (RESULT.address() == 0) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return new IntArray(RES_SEG);
        }
    }

    private static final MethodHandle retrieveLongArrayMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PrimitiveStruct_retrieveLongArray", MemorySegment.class /* self */);

    public LongArray retrieveLongArray(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.retrieveLongArrayMH.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RES_SEG = ENV.returnBuf();
        if (RES_SEG.isNull()) return null;
        return new LongArray(RES_SEG);
    }

    private static final MethodHandle retrieveLongArrayCriticalMH = PanamaUtils.lookupPNICriticalFunction(false, PNIBuf.class, "JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveLongArrayCritical", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public LongArray retrieveLongArrayCritical() {
        try (var POOLED = Allocator.ofPooled()) {
            MemorySegment RESULT;
            try {
                RESULT = (MemorySegment) this.retrieveLongArrayCriticalMH.invokeExact(MEMORY, POOLED.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (RESULT.address() == 0) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return new LongArray(RES_SEG);
        }
    }

    private static final MethodHandle retrieveUnsignedLongArrayMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedLongArray", MemorySegment.class /* self */);

    public LongArray retrieveUnsignedLongArray(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.retrieveUnsignedLongArrayMH.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RES_SEG = ENV.returnBuf();
        if (RES_SEG.isNull()) return null;
        return new LongArray(RES_SEG);
    }

    private static final MethodHandle retrieveUnsignedLongArrayCriticalMH = PanamaUtils.lookupPNICriticalFunction(false, PNIBuf.class, "JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedLongArrayCritical", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public LongArray retrieveUnsignedLongArrayCritical() {
        try (var POOLED = Allocator.ofPooled()) {
            MemorySegment RESULT;
            try {
                RESULT = (MemorySegment) this.retrieveUnsignedLongArrayCriticalMH.invokeExact(MEMORY, POOLED.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (RESULT.address() == 0) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return new LongArray(RES_SEG);
        }
    }

    private static final MethodHandle retrieveShortArrayMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PrimitiveStruct_retrieveShortArray", MemorySegment.class /* self */);

    public ShortArray retrieveShortArray(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.retrieveShortArrayMH.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RES_SEG = ENV.returnBuf();
        if (RES_SEG.isNull()) return null;
        return new ShortArray(RES_SEG);
    }

    private static final MethodHandle retrieveShortArrayCriticalMH = PanamaUtils.lookupPNICriticalFunction(false, PNIBuf.class, "JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveShortArrayCritical", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public ShortArray retrieveShortArrayCritical() {
        try (var POOLED = Allocator.ofPooled()) {
            MemorySegment RESULT;
            try {
                RESULT = (MemorySegment) this.retrieveShortArrayCriticalMH.invokeExact(MEMORY, POOLED.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (RESULT.address() == 0) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return new ShortArray(RES_SEG);
        }
    }

    private static final MethodHandle retrieveUnsignedShortArrayMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedShortArray", MemorySegment.class /* self */);

    public ShortArray retrieveUnsignedShortArray(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.retrieveUnsignedShortArrayMH.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RES_SEG = ENV.returnBuf();
        if (RES_SEG.isNull()) return null;
        return new ShortArray(RES_SEG);
    }

    private static final MethodHandle retrieveUnsignedShortArrayCriticalMH = PanamaUtils.lookupPNICriticalFunction(false, PNIBuf.class, "JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedShortArrayCritical", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public ShortArray retrieveUnsignedShortArrayCritical() {
        try (var POOLED = Allocator.ofPooled()) {
            MemorySegment RESULT;
            try {
                RESULT = (MemorySegment) this.retrieveUnsignedShortArrayCriticalMH.invokeExact(MEMORY, POOLED.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (RESULT.address() == 0) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return new ShortArray(RES_SEG);
        }
    }

    private static final MethodHandle retrieveBooleanArrayMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PrimitiveStruct_retrieveBooleanArray", MemorySegment.class /* self */);

    public BoolArray retrieveBooleanArray(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.retrieveBooleanArrayMH.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RES_SEG = ENV.returnBuf();
        if (RES_SEG.isNull()) return null;
        return new BoolArray(RES_SEG);
    }

    private static final MethodHandle retrieveBooleanArrayCriticalMH = PanamaUtils.lookupPNICriticalFunction(false, PNIBuf.class, "JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveBooleanArrayCritical", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public BoolArray retrieveBooleanArrayCritical() {
        try (var POOLED = Allocator.ofPooled()) {
            MemorySegment RESULT;
            try {
                RESULT = (MemorySegment) this.retrieveBooleanArrayCriticalMH.invokeExact(MEMORY, POOLED.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (RESULT.address() == 0) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return new BoolArray(RES_SEG);
        }
    }

    private static final MethodHandle retrieveByteArrayPointerMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PrimitiveStruct_retrieveByteArrayPointer", MemorySegment.class /* self */);

    public MemorySegment retrieveByteArrayPointer(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.retrieveByteArrayPointerMH.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RES_SEG = ENV.returnBuf();
        if (RES_SEG.isNull()) return null;
        return RES_SEG.get();
    }

    private static final MethodHandle retrieveByteArrayPointerCriticalMH = PanamaUtils.lookupPNICriticalFunction(false, PNIBuf.class, "JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveByteArrayPointerCritical", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public MemorySegment retrieveByteArrayPointerCritical() {
        try (var POOLED = Allocator.ofPooled()) {
            MemorySegment RESULT;
            try {
                RESULT = (MemorySegment) this.retrieveByteArrayPointerCriticalMH.invokeExact(MEMORY, POOLED.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (RESULT.address() == 0) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return RES_SEG.get();
        }
    }

    private static final MethodHandle retrieveUnsignedByteArrayPointerMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedByteArrayPointer", MemorySegment.class /* self */);

    public MemorySegment retrieveUnsignedByteArrayPointer(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.retrieveUnsignedByteArrayPointerMH.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RES_SEG = ENV.returnBuf();
        if (RES_SEG.isNull()) return null;
        return RES_SEG.get();
    }

    private static final MethodHandle retrieveUnsignedByteArrayPointerCriticalMH = PanamaUtils.lookupPNICriticalFunction(false, PNIBuf.class, "JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedByteArrayPointerCritical", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public MemorySegment retrieveUnsignedByteArrayPointerCritical() {
        try (var POOLED = Allocator.ofPooled()) {
            MemorySegment RESULT;
            try {
                RESULT = (MemorySegment) this.retrieveUnsignedByteArrayPointerCriticalMH.invokeExact(MEMORY, POOLED.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (RESULT.address() == 0) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return RES_SEG.get();
        }
    }

    private static final MethodHandle retrieveCharArrayPointerMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PrimitiveStruct_retrieveCharArrayPointer", MemorySegment.class /* self */);

    public CharArray retrieveCharArrayPointer(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.retrieveCharArrayPointerMH.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RES_SEG = ENV.returnBuf();
        if (RES_SEG.isNull()) return null;
        return new CharArray(RES_SEG);
    }

    private static final MethodHandle retrieveCharArrayPointerCriticalMH = PanamaUtils.lookupPNICriticalFunction(false, PNIBuf.class, "JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveCharArrayPointerCritical", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public CharArray retrieveCharArrayPointerCritical() {
        try (var POOLED = Allocator.ofPooled()) {
            MemorySegment RESULT;
            try {
                RESULT = (MemorySegment) this.retrieveCharArrayPointerCriticalMH.invokeExact(MEMORY, POOLED.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (RESULT.address() == 0) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return new CharArray(RES_SEG);
        }
    }

    private static final MethodHandle retrieveDoubleArrayPointerMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PrimitiveStruct_retrieveDoubleArrayPointer", MemorySegment.class /* self */);

    public DoubleArray retrieveDoubleArrayPointer(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.retrieveDoubleArrayPointerMH.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RES_SEG = ENV.returnBuf();
        if (RES_SEG.isNull()) return null;
        return new DoubleArray(RES_SEG);
    }

    private static final MethodHandle retrieveDoubleArrayPointerCriticalMH = PanamaUtils.lookupPNICriticalFunction(false, PNIBuf.class, "JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveDoubleArrayPointerCritical", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public DoubleArray retrieveDoubleArrayPointerCritical() {
        try (var POOLED = Allocator.ofPooled()) {
            MemorySegment RESULT;
            try {
                RESULT = (MemorySegment) this.retrieveDoubleArrayPointerCriticalMH.invokeExact(MEMORY, POOLED.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (RESULT.address() == 0) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return new DoubleArray(RES_SEG);
        }
    }

    private static final MethodHandle retrieveFloatArrayPointerMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PrimitiveStruct_retrieveFloatArrayPointer", MemorySegment.class /* self */);

    public FloatArray retrieveFloatArrayPointer(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.retrieveFloatArrayPointerMH.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RES_SEG = ENV.returnBuf();
        if (RES_SEG.isNull()) return null;
        return new FloatArray(RES_SEG);
    }

    private static final MethodHandle retrieveFloatArrayPointerCriticalMH = PanamaUtils.lookupPNICriticalFunction(false, PNIBuf.class, "JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveFloatArrayPointerCritical", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public FloatArray retrieveFloatArrayPointerCritical() {
        try (var POOLED = Allocator.ofPooled()) {
            MemorySegment RESULT;
            try {
                RESULT = (MemorySegment) this.retrieveFloatArrayPointerCriticalMH.invokeExact(MEMORY, POOLED.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (RESULT.address() == 0) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return new FloatArray(RES_SEG);
        }
    }

    private static final MethodHandle retrieveIntArrayPointerMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PrimitiveStruct_retrieveIntArrayPointer", MemorySegment.class /* self */);

    public IntArray retrieveIntArrayPointer(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.retrieveIntArrayPointerMH.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RES_SEG = ENV.returnBuf();
        if (RES_SEG.isNull()) return null;
        return new IntArray(RES_SEG);
    }

    private static final MethodHandle retrieveIntArrayPointerCriticalMH = PanamaUtils.lookupPNICriticalFunction(false, PNIBuf.class, "JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveIntArrayPointerCritical", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public IntArray retrieveIntArrayPointerCritical() {
        try (var POOLED = Allocator.ofPooled()) {
            MemorySegment RESULT;
            try {
                RESULT = (MemorySegment) this.retrieveIntArrayPointerCriticalMH.invokeExact(MEMORY, POOLED.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (RESULT.address() == 0) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return new IntArray(RES_SEG);
        }
    }

    private static final MethodHandle retrieveUnsignedIntArrayPointerMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedIntArrayPointer", MemorySegment.class /* self */);

    public IntArray retrieveUnsignedIntArrayPointer(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.retrieveUnsignedIntArrayPointerMH.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RES_SEG = ENV.returnBuf();
        if (RES_SEG.isNull()) return null;
        return new IntArray(RES_SEG);
    }

    private static final MethodHandle retrieveUnsignedIntArrayPointerCriticalMH = PanamaUtils.lookupPNICriticalFunction(false, PNIBuf.class, "JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedIntArrayPointerCritical", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public IntArray retrieveUnsignedIntArrayPointerCritical() {
        try (var POOLED = Allocator.ofPooled()) {
            MemorySegment RESULT;
            try {
                RESULT = (MemorySegment) this.retrieveUnsignedIntArrayPointerCriticalMH.invokeExact(MEMORY, POOLED.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (RESULT.address() == 0) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return new IntArray(RES_SEG);
        }
    }

    private static final MethodHandle retrieveLongArrayPointerMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PrimitiveStruct_retrieveLongArrayPointer", MemorySegment.class /* self */);

    public LongArray retrieveLongArrayPointer(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.retrieveLongArrayPointerMH.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RES_SEG = ENV.returnBuf();
        if (RES_SEG.isNull()) return null;
        return new LongArray(RES_SEG);
    }

    private static final MethodHandle retrieveLongArrayPointerCriticalMH = PanamaUtils.lookupPNICriticalFunction(false, PNIBuf.class, "JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveLongArrayPointerCritical", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public LongArray retrieveLongArrayPointerCritical() {
        try (var POOLED = Allocator.ofPooled()) {
            MemorySegment RESULT;
            try {
                RESULT = (MemorySegment) this.retrieveLongArrayPointerCriticalMH.invokeExact(MEMORY, POOLED.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (RESULT.address() == 0) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return new LongArray(RES_SEG);
        }
    }

    private static final MethodHandle retrieveUnsignedLongArrayPointerMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedLongArrayPointer", MemorySegment.class /* self */);

    public LongArray retrieveUnsignedLongArrayPointer(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.retrieveUnsignedLongArrayPointerMH.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RES_SEG = ENV.returnBuf();
        if (RES_SEG.isNull()) return null;
        return new LongArray(RES_SEG);
    }

    private static final MethodHandle retrieveUnsignedLongArrayPointerCriticalMH = PanamaUtils.lookupPNICriticalFunction(false, PNIBuf.class, "JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedLongArrayPointerCritical", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public LongArray retrieveUnsignedLongArrayPointerCritical() {
        try (var POOLED = Allocator.ofPooled()) {
            MemorySegment RESULT;
            try {
                RESULT = (MemorySegment) this.retrieveUnsignedLongArrayPointerCriticalMH.invokeExact(MEMORY, POOLED.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (RESULT.address() == 0) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return new LongArray(RES_SEG);
        }
    }

    private static final MethodHandle retrieveShortArrayPointerMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PrimitiveStruct_retrieveShortArrayPointer", MemorySegment.class /* self */);

    public ShortArray retrieveShortArrayPointer(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.retrieveShortArrayPointerMH.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RES_SEG = ENV.returnBuf();
        if (RES_SEG.isNull()) return null;
        return new ShortArray(RES_SEG);
    }

    private static final MethodHandle retrieveShortArrayPointerCriticalMH = PanamaUtils.lookupPNICriticalFunction(false, PNIBuf.class, "JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveShortArrayPointerCritical", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public ShortArray retrieveShortArrayPointerCritical() {
        try (var POOLED = Allocator.ofPooled()) {
            MemorySegment RESULT;
            try {
                RESULT = (MemorySegment) this.retrieveShortArrayPointerCriticalMH.invokeExact(MEMORY, POOLED.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (RESULT.address() == 0) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return new ShortArray(RES_SEG);
        }
    }

    private static final MethodHandle retrieveUnsignedShortArrayPointerMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedShortArrayPointer", MemorySegment.class /* self */);

    public ShortArray retrieveUnsignedShortArrayPointer(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.retrieveUnsignedShortArrayPointerMH.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RES_SEG = ENV.returnBuf();
        if (RES_SEG.isNull()) return null;
        return new ShortArray(RES_SEG);
    }

    private static final MethodHandle retrieveUnsignedShortArrayPointerCriticalMH = PanamaUtils.lookupPNICriticalFunction(false, PNIBuf.class, "JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedShortArrayPointerCritical", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public ShortArray retrieveUnsignedShortArrayPointerCritical() {
        try (var POOLED = Allocator.ofPooled()) {
            MemorySegment RESULT;
            try {
                RESULT = (MemorySegment) this.retrieveUnsignedShortArrayPointerCriticalMH.invokeExact(MEMORY, POOLED.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (RESULT.address() == 0) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return new ShortArray(RES_SEG);
        }
    }

    private static final MethodHandle retrieveBooleanArrayPointerMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PrimitiveStruct_retrieveBooleanArrayPointer", MemorySegment.class /* self */);

    public BoolArray retrieveBooleanArrayPointer(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.retrieveBooleanArrayPointerMH.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RES_SEG = ENV.returnBuf();
        if (RES_SEG.isNull()) return null;
        return new BoolArray(RES_SEG);
    }

    private static final MethodHandle retrieveBooleanArrayPointerCriticalMH = PanamaUtils.lookupPNICriticalFunction(false, PNIBuf.class, "JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveBooleanArrayPointerCritical", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public BoolArray retrieveBooleanArrayPointerCritical() {
        try (var POOLED = Allocator.ofPooled()) {
            MemorySegment RESULT;
            try {
                RESULT = (MemorySegment) this.retrieveBooleanArrayPointerCriticalMH.invokeExact(MEMORY, POOLED.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (RESULT.address() == 0) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return new BoolArray(RES_SEG);
        }
    }

    private static final MethodHandle checkPointerSetToNonNullMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PrimitiveStruct_checkPointerSetToNonNull", MemorySegment.class /* self */);

    public boolean checkPointerSetToNonNull(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.checkPointerSetToNonNullMH.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnBool();
    }

    private static final MethodHandle checkPointerSetToNonNullCriticalMH = PanamaUtils.lookupPNICriticalFunction(false, boolean.class, "JavaCritical_io_vproxy_pni_test_PrimitiveStruct_checkPointerSetToNonNullCritical", MemorySegment.class /* self */);

    public boolean checkPointerSetToNonNullCritical() {
        boolean RESULT;
        try {
            RESULT = (boolean) this.checkPointerSetToNonNullCriticalMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle checkPointerSetToNullMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PrimitiveStruct_checkPointerSetToNull", MemorySegment.class /* self */);

    public boolean checkPointerSetToNull(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.checkPointerSetToNullMH.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnBool();
    }

    private static final MethodHandle checkPointerSetToNullCriticalMH = PanamaUtils.lookupPNICriticalFunction(false, boolean.class, "JavaCritical_io_vproxy_pni_test_PrimitiveStruct_checkPointerSetToNullCritical", MemorySegment.class /* self */);

    public boolean checkPointerSetToNullCritical() {
        boolean RESULT;
        try {
            RESULT = (boolean) this.checkPointerSetToNullCriticalMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    public static class Array extends RefArray<PrimitiveStruct> {
        public Array(MemorySegment buf) {
            super(buf, PrimitiveStruct.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            this(allocator.allocate(PrimitiveStruct.LAYOUT.byteSize() * len));
        }

        public Array(PNIBuf buf) {
            this(buf.get());
        }

        @Override
        protected PrimitiveStruct construct(MemorySegment seg) {
            return new PrimitiveStruct(seg);
        }

        @Override
        protected MemorySegment getSegment(PrimitiveStruct value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<PrimitiveStruct> {
        private Func(io.vproxy.pni.CallSite<PrimitiveStruct> func) {
            super(func);
        }

        public static Func of(io.vproxy.pni.CallSite<PrimitiveStruct> func) {
            return new Func(func);
        }

        @Override
        protected PrimitiveStruct construct(MemorySegment seg) {
            return new PrimitiveStruct(seg);
        }

        @Override
        protected MemorySegment getSegment(PrimitiveStruct value) {
            return value.MEMORY;
        }
    }
}
// metadata.generator-version: pni test
// sha256:4a632e834abe18e84384063a4a2f23686d332ebd943edf5053f4cdb95c4618c0
