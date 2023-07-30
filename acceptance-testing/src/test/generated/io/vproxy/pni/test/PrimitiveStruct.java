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

    private final MethodHandle func1 = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PrimitiveStruct_func1", MemorySegment.class /* self */, byte.class /* aByte */, byte.class /* unsignedByte */, int.class /* aInt */, int.class /* unsignedInt */, long.class /* aLong */, long.class /* unsignedLong */, short.class /* aShort */, short.class /* unsignedShort */);

    public void func1(PNIEnv ENV, byte aByte, byte unsignedByte, int aInt, int unsignedInt, long aLong, long unsignedLong, short aShort, short unsignedShort) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.func1.invokeExact(ENV.MEMORY, MEMORY, aByte, unsignedByte, aInt, unsignedInt, aLong, unsignedLong, aShort, unsignedShort);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
    }

    private final MethodHandle func2 = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PrimitiveStruct_func2", MemorySegment.class /* self */, char.class /* aChar */, double.class /* aDouble */, float.class /* aFloat */, boolean.class /* aBoolean */);

    public void func2(PNIEnv ENV, char aChar, double aDouble, float aFloat, boolean aBoolean) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.func2.invokeExact(ENV.MEMORY, MEMORY, aChar, aDouble, aFloat, aBoolean);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
    }

    private final MethodHandle func3 = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PrimitiveStruct_func3", MemorySegment.class /* self */, PNIBuf.class /* byteArray */, PNIBuf.class /* unsignedByteArray */, PNIBuf.class /* intArray */, PNIBuf.class /* unsignedIntArray */, PNIBuf.class /* longArray */, PNIBuf.class /* unsignedLongArray */, PNIBuf.class /* shortArray */, PNIBuf.class /* unsignedShortArray */);

    public void func3(PNIEnv ENV, MemorySegment byteArray, MemorySegment unsignedByteArray, IntArray intArray, IntArray unsignedIntArray, LongArray longArray, LongArray unsignedLongArray, ShortArray shortArray, ShortArray unsignedShortArray) {
        ENV.reset();
        try (var ARENA = Arena.ofConfined()) {
            int ERR;
            try {
                ERR = (int) this.func3.invokeExact(ENV.MEMORY, MEMORY, PNIBuf.of(ARENA, byteArray).MEMORY, PNIBuf.of(ARENA, unsignedByteArray).MEMORY, PNIBuf.of(ARENA, intArray).MEMORY, PNIBuf.of(ARENA, unsignedIntArray).MEMORY, PNIBuf.of(ARENA, longArray).MEMORY, PNIBuf.of(ARENA, unsignedLongArray).MEMORY, PNIBuf.of(ARENA, shortArray).MEMORY, PNIBuf.of(ARENA, unsignedShortArray).MEMORY);
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (ERR != 0) {
                ENV.throwLast();
            }
        }
    }

    private final MethodHandle func4 = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PrimitiveStruct_func4", MemorySegment.class /* self */, PNIBuf.class /* charArray */, PNIBuf.class /* doubleArray */, PNIBuf.class /* floatArray */, PNIBuf.class /* booleanArray */);

    public void func4(PNIEnv ENV, CharArray charArray, DoubleArray doubleArray, FloatArray floatArray, BoolArray booleanArray) {
        ENV.reset();
        try (var ARENA = Arena.ofConfined()) {
            int ERR;
            try {
                ERR = (int) this.func4.invokeExact(ENV.MEMORY, MEMORY, PNIBuf.of(ARENA, charArray).MEMORY, PNIBuf.of(ARENA, doubleArray).MEMORY, PNIBuf.of(ARENA, floatArray).MEMORY, PNIBuf.of(ARENA, booleanArray).MEMORY);
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (ERR != 0) {
                ENV.throwLast();
            }
        }
    }

    private final MethodHandle retrieveByte = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PrimitiveStruct_retrieveByte", MemorySegment.class /* self */);

    public byte retrieveByte(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.retrieveByte.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnByte();
    }

    private final MethodHandle retrieveUnsignedByte = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedByte", MemorySegment.class /* self */);

    public byte retrieveUnsignedByte(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.retrieveUnsignedByte.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnByte();
    }

    private final MethodHandle retrieveChar = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PrimitiveStruct_retrieveChar", MemorySegment.class /* self */);

    public char retrieveChar(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.retrieveChar.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnChar();
    }

    private final MethodHandle retrieveDouble = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PrimitiveStruct_retrieveDouble", MemorySegment.class /* self */);

    public double retrieveDouble(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.retrieveDouble.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnDouble();
    }

    private final MethodHandle retrieveFloat = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PrimitiveStruct_retrieveFloat", MemorySegment.class /* self */);

    public float retrieveFloat(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.retrieveFloat.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnFloat();
    }

    private final MethodHandle retrieveInt = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PrimitiveStruct_retrieveInt", MemorySegment.class /* self */);

    public int retrieveInt(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.retrieveInt.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnInt();
    }

    private final MethodHandle retrieveUnsignedInt = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedInt", MemorySegment.class /* self */);

    public int retrieveUnsignedInt(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.retrieveUnsignedInt.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnInt();
    }

    private final MethodHandle retrieveLong = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PrimitiveStruct_retrieveLong", MemorySegment.class /* self */);

    public long retrieveLong(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.retrieveLong.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnLong();
    }

    private final MethodHandle retrieveUnsignedLong = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedLong", MemorySegment.class /* self */);

    public long retrieveUnsignedLong(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.retrieveUnsignedLong.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnLong();
    }

    private final MethodHandle retrieveShort = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PrimitiveStruct_retrieveShort", MemorySegment.class /* self */);

    public short retrieveShort(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.retrieveShort.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnShort();
    }

    private final MethodHandle retrieveUnsignedShort = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedShort", MemorySegment.class /* self */);

    public short retrieveUnsignedShort(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.retrieveUnsignedShort.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnShort();
    }

    private final MethodHandle retrieveBoolean = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PrimitiveStruct_retrieveBoolean", MemorySegment.class /* self */);

    public boolean retrieveBoolean(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.retrieveBoolean.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnBool();
    }

    private final MethodHandle retrieveByteArray = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PrimitiveStruct_retrieveByteArray", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public MemorySegment retrieveByteArray(PNIEnv ENV) {
        ENV.reset();
        try (var ARENA = Arena.ofConfined()) {
            int ERR;
            try {
                ERR = (int) this.retrieveByteArray.invokeExact(ENV.MEMORY, MEMORY, ARENA.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (ERR != 0) {
                ENV.throwLast();
            }
            var RESULT = ENV.returnPointer();
            if (RESULT == null) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return RES_SEG.get();
        }
    }

    private final MethodHandle retrieveUnsignedByteArray = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedByteArray", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public MemorySegment retrieveUnsignedByteArray(PNIEnv ENV) {
        ENV.reset();
        try (var ARENA = Arena.ofConfined()) {
            int ERR;
            try {
                ERR = (int) this.retrieveUnsignedByteArray.invokeExact(ENV.MEMORY, MEMORY, ARENA.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (ERR != 0) {
                ENV.throwLast();
            }
            var RESULT = ENV.returnPointer();
            if (RESULT == null) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return RES_SEG.get();
        }
    }

    private final MethodHandle retrieveCharArray = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PrimitiveStruct_retrieveCharArray", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public CharArray retrieveCharArray(PNIEnv ENV) {
        ENV.reset();
        try (var ARENA = Arena.ofConfined()) {
            int ERR;
            try {
                ERR = (int) this.retrieveCharArray.invokeExact(ENV.MEMORY, MEMORY, ARENA.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (ERR != 0) {
                ENV.throwLast();
            }
            var RESULT = ENV.returnPointer();
            if (RESULT == null) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return new CharArray(RES_SEG);
        }
    }

    private final MethodHandle retrieveDoubleArray = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PrimitiveStruct_retrieveDoubleArray", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public DoubleArray retrieveDoubleArray(PNIEnv ENV) {
        ENV.reset();
        try (var ARENA = Arena.ofConfined()) {
            int ERR;
            try {
                ERR = (int) this.retrieveDoubleArray.invokeExact(ENV.MEMORY, MEMORY, ARENA.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (ERR != 0) {
                ENV.throwLast();
            }
            var RESULT = ENV.returnPointer();
            if (RESULT == null) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return new DoubleArray(RES_SEG);
        }
    }

    private final MethodHandle retrieveFloatArray = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PrimitiveStruct_retrieveFloatArray", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public FloatArray retrieveFloatArray(PNIEnv ENV) {
        ENV.reset();
        try (var ARENA = Arena.ofConfined()) {
            int ERR;
            try {
                ERR = (int) this.retrieveFloatArray.invokeExact(ENV.MEMORY, MEMORY, ARENA.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (ERR != 0) {
                ENV.throwLast();
            }
            var RESULT = ENV.returnPointer();
            if (RESULT == null) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return new FloatArray(RES_SEG);
        }
    }

    private final MethodHandle retrieveIntArray = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PrimitiveStruct_retrieveIntArray", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public IntArray retrieveIntArray(PNIEnv ENV) {
        ENV.reset();
        try (var ARENA = Arena.ofConfined()) {
            int ERR;
            try {
                ERR = (int) this.retrieveIntArray.invokeExact(ENV.MEMORY, MEMORY, ARENA.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (ERR != 0) {
                ENV.throwLast();
            }
            var RESULT = ENV.returnPointer();
            if (RESULT == null) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return new IntArray(RES_SEG);
        }
    }

    private final MethodHandle retrieveUnsignedIntArray = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedIntArray", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public IntArray retrieveUnsignedIntArray(PNIEnv ENV) {
        ENV.reset();
        try (var ARENA = Arena.ofConfined()) {
            int ERR;
            try {
                ERR = (int) this.retrieveUnsignedIntArray.invokeExact(ENV.MEMORY, MEMORY, ARENA.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (ERR != 0) {
                ENV.throwLast();
            }
            var RESULT = ENV.returnPointer();
            if (RESULT == null) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return new IntArray(RES_SEG);
        }
    }

    private final MethodHandle retrieveLongArray = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PrimitiveStruct_retrieveLongArray", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public LongArray retrieveLongArray(PNIEnv ENV) {
        ENV.reset();
        try (var ARENA = Arena.ofConfined()) {
            int ERR;
            try {
                ERR = (int) this.retrieveLongArray.invokeExact(ENV.MEMORY, MEMORY, ARENA.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (ERR != 0) {
                ENV.throwLast();
            }
            var RESULT = ENV.returnPointer();
            if (RESULT == null) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return new LongArray(RES_SEG);
        }
    }

    private final MethodHandle retrieveUnsignedLongArray = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedLongArray", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public LongArray retrieveUnsignedLongArray(PNIEnv ENV) {
        ENV.reset();
        try (var ARENA = Arena.ofConfined()) {
            int ERR;
            try {
                ERR = (int) this.retrieveUnsignedLongArray.invokeExact(ENV.MEMORY, MEMORY, ARENA.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (ERR != 0) {
                ENV.throwLast();
            }
            var RESULT = ENV.returnPointer();
            if (RESULT == null) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return new LongArray(RES_SEG);
        }
    }

    private final MethodHandle retrieveShortArray = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PrimitiveStruct_retrieveShortArray", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public ShortArray retrieveShortArray(PNIEnv ENV) {
        ENV.reset();
        try (var ARENA = Arena.ofConfined()) {
            int ERR;
            try {
                ERR = (int) this.retrieveShortArray.invokeExact(ENV.MEMORY, MEMORY, ARENA.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (ERR != 0) {
                ENV.throwLast();
            }
            var RESULT = ENV.returnPointer();
            if (RESULT == null) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return new ShortArray(RES_SEG);
        }
    }

    private final MethodHandle retrieveUnsignedShortArray = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedShortArray", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public ShortArray retrieveUnsignedShortArray(PNIEnv ENV) {
        ENV.reset();
        try (var ARENA = Arena.ofConfined()) {
            int ERR;
            try {
                ERR = (int) this.retrieveUnsignedShortArray.invokeExact(ENV.MEMORY, MEMORY, ARENA.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (ERR != 0) {
                ENV.throwLast();
            }
            var RESULT = ENV.returnPointer();
            if (RESULT == null) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return new ShortArray(RES_SEG);
        }
    }

    private final MethodHandle retrieveBooleanArray = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PrimitiveStruct_retrieveBooleanArray", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public BoolArray retrieveBooleanArray(PNIEnv ENV) {
        ENV.reset();
        try (var ARENA = Arena.ofConfined()) {
            int ERR;
            try {
                ERR = (int) this.retrieveBooleanArray.invokeExact(ENV.MEMORY, MEMORY, ARENA.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (ERR != 0) {
                ENV.throwLast();
            }
            var RESULT = ENV.returnPointer();
            if (RESULT == null) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return new BoolArray(RES_SEG);
        }
    }

    private final MethodHandle retrieveByteArrayPointer = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PrimitiveStruct_retrieveByteArrayPointer", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public MemorySegment retrieveByteArrayPointer(PNIEnv ENV) {
        ENV.reset();
        try (var ARENA = Arena.ofConfined()) {
            int ERR;
            try {
                ERR = (int) this.retrieveByteArrayPointer.invokeExact(ENV.MEMORY, MEMORY, ARENA.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (ERR != 0) {
                ENV.throwLast();
            }
            var RESULT = ENV.returnPointer();
            if (RESULT == null) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return RES_SEG.get();
        }
    }

    private final MethodHandle retrieveUnsignedByteArrayPointer = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedByteArrayPointer", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public MemorySegment retrieveUnsignedByteArrayPointer(PNIEnv ENV) {
        ENV.reset();
        try (var ARENA = Arena.ofConfined()) {
            int ERR;
            try {
                ERR = (int) this.retrieveUnsignedByteArrayPointer.invokeExact(ENV.MEMORY, MEMORY, ARENA.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (ERR != 0) {
                ENV.throwLast();
            }
            var RESULT = ENV.returnPointer();
            if (RESULT == null) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return RES_SEG.get();
        }
    }

    private final MethodHandle retrieveCharArrayPointer = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PrimitiveStruct_retrieveCharArrayPointer", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public CharArray retrieveCharArrayPointer(PNIEnv ENV) {
        ENV.reset();
        try (var ARENA = Arena.ofConfined()) {
            int ERR;
            try {
                ERR = (int) this.retrieveCharArrayPointer.invokeExact(ENV.MEMORY, MEMORY, ARENA.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (ERR != 0) {
                ENV.throwLast();
            }
            var RESULT = ENV.returnPointer();
            if (RESULT == null) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return new CharArray(RES_SEG);
        }
    }

    private final MethodHandle retrieveDoubleArrayPointer = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PrimitiveStruct_retrieveDoubleArrayPointer", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public DoubleArray retrieveDoubleArrayPointer(PNIEnv ENV) {
        ENV.reset();
        try (var ARENA = Arena.ofConfined()) {
            int ERR;
            try {
                ERR = (int) this.retrieveDoubleArrayPointer.invokeExact(ENV.MEMORY, MEMORY, ARENA.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (ERR != 0) {
                ENV.throwLast();
            }
            var RESULT = ENV.returnPointer();
            if (RESULT == null) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return new DoubleArray(RES_SEG);
        }
    }

    private final MethodHandle retrieveFloatArrayPointer = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PrimitiveStruct_retrieveFloatArrayPointer", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public FloatArray retrieveFloatArrayPointer(PNIEnv ENV) {
        ENV.reset();
        try (var ARENA = Arena.ofConfined()) {
            int ERR;
            try {
                ERR = (int) this.retrieveFloatArrayPointer.invokeExact(ENV.MEMORY, MEMORY, ARENA.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (ERR != 0) {
                ENV.throwLast();
            }
            var RESULT = ENV.returnPointer();
            if (RESULT == null) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return new FloatArray(RES_SEG);
        }
    }

    private final MethodHandle retrieveIntArrayPointer = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PrimitiveStruct_retrieveIntArrayPointer", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public IntArray retrieveIntArrayPointer(PNIEnv ENV) {
        ENV.reset();
        try (var ARENA = Arena.ofConfined()) {
            int ERR;
            try {
                ERR = (int) this.retrieveIntArrayPointer.invokeExact(ENV.MEMORY, MEMORY, ARENA.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (ERR != 0) {
                ENV.throwLast();
            }
            var RESULT = ENV.returnPointer();
            if (RESULT == null) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return new IntArray(RES_SEG);
        }
    }

    private final MethodHandle retrieveUnsignedIntArrayPointer = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedIntArrayPointer", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public IntArray retrieveUnsignedIntArrayPointer(PNIEnv ENV) {
        ENV.reset();
        try (var ARENA = Arena.ofConfined()) {
            int ERR;
            try {
                ERR = (int) this.retrieveUnsignedIntArrayPointer.invokeExact(ENV.MEMORY, MEMORY, ARENA.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (ERR != 0) {
                ENV.throwLast();
            }
            var RESULT = ENV.returnPointer();
            if (RESULT == null) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return new IntArray(RES_SEG);
        }
    }

    private final MethodHandle retrieveLongArrayPointer = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PrimitiveStruct_retrieveLongArrayPointer", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public LongArray retrieveLongArrayPointer(PNIEnv ENV) {
        ENV.reset();
        try (var ARENA = Arena.ofConfined()) {
            int ERR;
            try {
                ERR = (int) this.retrieveLongArrayPointer.invokeExact(ENV.MEMORY, MEMORY, ARENA.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (ERR != 0) {
                ENV.throwLast();
            }
            var RESULT = ENV.returnPointer();
            if (RESULT == null) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return new LongArray(RES_SEG);
        }
    }

    private final MethodHandle retrieveUnsignedLongArrayPointer = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedLongArrayPointer", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public LongArray retrieveUnsignedLongArrayPointer(PNIEnv ENV) {
        ENV.reset();
        try (var ARENA = Arena.ofConfined()) {
            int ERR;
            try {
                ERR = (int) this.retrieveUnsignedLongArrayPointer.invokeExact(ENV.MEMORY, MEMORY, ARENA.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (ERR != 0) {
                ENV.throwLast();
            }
            var RESULT = ENV.returnPointer();
            if (RESULT == null) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return new LongArray(RES_SEG);
        }
    }

    private final MethodHandle retrieveShortArrayPointer = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PrimitiveStruct_retrieveShortArrayPointer", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public ShortArray retrieveShortArrayPointer(PNIEnv ENV) {
        ENV.reset();
        try (var ARENA = Arena.ofConfined()) {
            int ERR;
            try {
                ERR = (int) this.retrieveShortArrayPointer.invokeExact(ENV.MEMORY, MEMORY, ARENA.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (ERR != 0) {
                ENV.throwLast();
            }
            var RESULT = ENV.returnPointer();
            if (RESULT == null) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return new ShortArray(RES_SEG);
        }
    }

    private final MethodHandle retrieveUnsignedShortArrayPointer = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedShortArrayPointer", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public ShortArray retrieveUnsignedShortArrayPointer(PNIEnv ENV) {
        ENV.reset();
        try (var ARENA = Arena.ofConfined()) {
            int ERR;
            try {
                ERR = (int) this.retrieveUnsignedShortArrayPointer.invokeExact(ENV.MEMORY, MEMORY, ARENA.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (ERR != 0) {
                ENV.throwLast();
            }
            var RESULT = ENV.returnPointer();
            if (RESULT == null) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return new ShortArray(RES_SEG);
        }
    }

    private final MethodHandle retrieveBooleanArrayPointer = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PrimitiveStruct_retrieveBooleanArrayPointer", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public BoolArray retrieveBooleanArrayPointer(PNIEnv ENV) {
        ENV.reset();
        try (var ARENA = Arena.ofConfined()) {
            int ERR;
            try {
                ERR = (int) this.retrieveBooleanArrayPointer.invokeExact(ENV.MEMORY, MEMORY, ARENA.allocate(PNIBuf.LAYOUT.byteSize()));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (ERR != 0) {
                ENV.throwLast();
            }
            var RESULT = ENV.returnPointer();
            if (RESULT == null) return null;
            var RES_SEG = new PNIBuf(RESULT);
            if (RES_SEG.isNull()) return null;
            return new BoolArray(RES_SEG);
        }
    }

    private final MethodHandle checkPointerSetToNonNull = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PrimitiveStruct_checkPointerSetToNonNull", MemorySegment.class /* self */);

    public boolean checkPointerSetToNonNull(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.checkPointerSetToNonNull.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnBool();
    }

    private final MethodHandle checkPointerSetToNull = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_PrimitiveStruct_checkPointerSetToNull", MemorySegment.class /* self */);

    public boolean checkPointerSetToNull(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.checkPointerSetToNull.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnBool();
    }

    public static class Array extends RefArray<PrimitiveStruct> {
        public Array(MemorySegment buf) {
            super(buf, PrimitiveStruct.LAYOUT);
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
// sha256:e43e244e785cf7bf756c1d77f8ea9430f1dfda93fe81763d8dc2ba762c8b071b
