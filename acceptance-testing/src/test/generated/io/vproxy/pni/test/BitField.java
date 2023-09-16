package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class BitField {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        ValueLayout.JAVA_BYTE.withName("field01"),
        ValueLayout.JAVA_BYTE.withName("field02"),
        MemoryLayout.sequenceLayout(6L, ValueLayout.JAVA_BYTE) /* padding */,
        ValueLayout.ADDRESS_UNALIGNED.withName("sep01"),
        ValueLayout.JAVA_SHORT_UNALIGNED.withName("field03"),
        ValueLayout.JAVA_SHORT_UNALIGNED.withName("field04"),
        MemoryLayout.sequenceLayout(4L, ValueLayout.JAVA_BYTE) /* padding */,
        ValueLayout.ADDRESS_UNALIGNED.withName("sep02"),
        ValueLayout.JAVA_INT_UNALIGNED.withName("field05"),
        ValueLayout.JAVA_INT_UNALIGNED.withName("field06"),
        ValueLayout.ADDRESS_UNALIGNED.withName("sep03"),
        ValueLayout.JAVA_LONG_UNALIGNED.withName("field07"),
        ValueLayout.JAVA_LONG_UNALIGNED.withName("field08")
    );
    public final MemorySegment MEMORY;

    private static final VarHandle field01VH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("field01")
    );

    public byte getField01() {
        return (byte) field01VH.get(MEMORY);
    }

    public void setField01(byte field01) {
        field01VH.set(MEMORY, field01);
    }

    public byte getA() {
        var N = getField01();
        return (byte) ((N >> 0) & 0b1);
    }

    public void setA(byte a) {
        var N = getField01();
        byte MASK = (byte) (0b1 << 0);
        a = (byte) (a & 0b1);
        a = (byte) (a << 0);
        N = (byte) ((N & ~MASK) | (a & MASK));
        setField01(N);
    }

    public byte getB() {
        var N = getField01();
        return (byte) ((N >> 1) & 0b111);
    }

    public void setB(byte b) {
        var N = getField01();
        byte MASK = (byte) (0b111 << 1);
        b = (byte) (b & 0b111);
        b = (byte) (b << 1);
        N = (byte) ((N & ~MASK) | (b & MASK));
        setField01(N);
    }

    private static final VarHandle field02VH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("field02")
    );

    public byte getField02() {
        return (byte) field02VH.get(MEMORY);
    }

    public void setField02(byte field02) {
        field02VH.set(MEMORY, field02);
    }

    public byte getA2() {
        var N = getField02();
        return (byte) ((N >> 0) & 0b1);
    }

    public void setA2(byte a2) {
        var N = getField02();
        byte MASK = (byte) (0b1 << 0);
        a2 = (byte) (a2 & 0b1);
        a2 = (byte) (a2 << 0);
        N = (byte) ((N & ~MASK) | (a2 & MASK));
        setField02(N);
    }

    public byte getB2() {
        var N = getField02();
        return (byte) ((N >> 1) & 0b111);
    }

    public void setB2(byte b2) {
        var N = getField02();
        byte MASK = (byte) (0b111 << 1);
        b2 = (byte) (b2 & 0b111);
        b2 = (byte) (b2 << 1);
        N = (byte) ((N & ~MASK) | (b2 & MASK));
        setField02(N);
    }

    private static final VarHandle sep01VH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("sep01")
    );

    public MemorySegment getSep01() {
        var SEG = (MemorySegment) sep01VH.get(MEMORY);
        if (SEG.address() == 0) return null;
        return SEG;
    }

    public void setSep01(MemorySegment sep01) {
        if (sep01 == null) {
            sep01VH.set(MEMORY, MemorySegment.NULL);
        } else {
            sep01VH.set(MEMORY, sep01);
        }
    }

    private static final VarHandle field03VH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("field03")
    );

    public short getField03() {
        return (short) field03VH.get(MEMORY);
    }

    public void setField03(short field03) {
        field03VH.set(MEMORY, field03);
    }

    public short getC() {
        var N = getField03();
        return (short) ((N >> 0) & 0b11);
    }

    public void setC(short c) {
        var N = getField03();
        short MASK = (short) (0b11 << 0);
        c = (short) (c & 0b11);
        c = (short) (c << 0);
        N = (short) ((N & ~MASK) | (c & MASK));
        setField03(N);
    }

    public short getD() {
        var N = getField03();
        return (short) ((N >> 2) & 0b111);
    }

    public void setD(short d) {
        var N = getField03();
        short MASK = (short) (0b111 << 2);
        d = (short) (d & 0b111);
        d = (short) (d << 2);
        N = (short) ((N & ~MASK) | (d & MASK));
        setField03(N);
    }

    public short getE() {
        var N = getField03();
        return (short) ((N >> 5) & 0b1111);
    }

    public void setE(short e) {
        var N = getField03();
        short MASK = (short) (0b1111 << 5);
        e = (short) (e & 0b1111);
        e = (short) (e << 5);
        N = (short) ((N & ~MASK) | (e & MASK));
        setField03(N);
    }

    private static final VarHandle field04VH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("field04")
    );

    public short getField04() {
        return (short) field04VH.get(MEMORY);
    }

    public void setField04(short field04) {
        field04VH.set(MEMORY, field04);
    }

    public short getC2() {
        var N = getField04();
        return (short) ((N >> 0) & 0b11);
    }

    public void setC2(short c2) {
        var N = getField04();
        short MASK = (short) (0b11 << 0);
        c2 = (short) (c2 & 0b11);
        c2 = (short) (c2 << 0);
        N = (short) ((N & ~MASK) | (c2 & MASK));
        setField04(N);
    }

    public short getD2() {
        var N = getField04();
        return (short) ((N >> 2) & 0b111);
    }

    public void setD2(short d2) {
        var N = getField04();
        short MASK = (short) (0b111 << 2);
        d2 = (short) (d2 & 0b111);
        d2 = (short) (d2 << 2);
        N = (short) ((N & ~MASK) | (d2 & MASK));
        setField04(N);
    }

    public short getE2() {
        var N = getField04();
        return (short) ((N >> 5) & 0b1111);
    }

    public void setE2(short e2) {
        var N = getField04();
        short MASK = (short) (0b1111 << 5);
        e2 = (short) (e2 & 0b1111);
        e2 = (short) (e2 << 5);
        N = (short) ((N & ~MASK) | (e2 & MASK));
        setField04(N);
    }

    private static final VarHandle sep02VH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("sep02")
    );

    public MemorySegment getSep02() {
        var SEG = (MemorySegment) sep02VH.get(MEMORY);
        if (SEG.address() == 0) return null;
        return SEG;
    }

    public void setSep02(MemorySegment sep02) {
        if (sep02 == null) {
            sep02VH.set(MEMORY, MemorySegment.NULL);
        } else {
            sep02VH.set(MEMORY, sep02);
        }
    }

    private static final VarHandle field05VH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("field05")
    );

    public int getField05() {
        return (int) field05VH.get(MEMORY);
    }

    public void setField05(int field05) {
        field05VH.set(MEMORY, field05);
    }

    public int getF() {
        var N = getField05();
        return (int) ((N >> 0) & 0b11111);
    }

    public void setF(int f) {
        var N = getField05();
        int MASK = (int) (0b11111 << 0);
        f = (int) (f & 0b11111);
        f = (int) (f << 0);
        N = (int) ((N & ~MASK) | (f & MASK));
        setField05(N);
    }

    public int getG() {
        var N = getField05();
        return (int) ((N >> 5) & 0b111111);
    }

    public void setG(int g) {
        var N = getField05();
        int MASK = (int) (0b111111 << 5);
        g = (int) (g & 0b111111);
        g = (int) (g << 5);
        N = (int) ((N & ~MASK) | (g & MASK));
        setField05(N);
    }

    public int getH() {
        var N = getField05();
        return (int) ((N >> 11) & 0b1111111);
    }

    public void setH(int h) {
        var N = getField05();
        int MASK = (int) (0b1111111 << 11);
        h = (int) (h & 0b1111111);
        h = (int) (h << 11);
        N = (int) ((N & ~MASK) | (h & MASK));
        setField05(N);
    }

    public int getI() {
        var N = getField05();
        return (int) ((N >> 18) & 0b11111111);
    }

    public void setI(int i) {
        var N = getField05();
        int MASK = (int) (0b11111111 << 18);
        i = (int) (i & 0b11111111);
        i = (int) (i << 18);
        N = (int) ((N & ~MASK) | (i & MASK));
        setField05(N);
    }

    private static final VarHandle field06VH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("field06")
    );

    public int getField06() {
        return (int) field06VH.get(MEMORY);
    }

    public void setField06(int field06) {
        field06VH.set(MEMORY, field06);
    }

    public int getF2() {
        var N = getField06();
        return (int) ((N >> 0) & 0b11111);
    }

    public void setF2(int f2) {
        var N = getField06();
        int MASK = (int) (0b11111 << 0);
        f2 = (int) (f2 & 0b11111);
        f2 = (int) (f2 << 0);
        N = (int) ((N & ~MASK) | (f2 & MASK));
        setField06(N);
    }

    public int getG2() {
        var N = getField06();
        return (int) ((N >> 5) & 0b111111);
    }

    public void setG2(int g2) {
        var N = getField06();
        int MASK = (int) (0b111111 << 5);
        g2 = (int) (g2 & 0b111111);
        g2 = (int) (g2 << 5);
        N = (int) ((N & ~MASK) | (g2 & MASK));
        setField06(N);
    }

    public int getH2() {
        var N = getField06();
        return (int) ((N >> 11) & 0b1111111);
    }

    public void setH2(int h2) {
        var N = getField06();
        int MASK = (int) (0b1111111 << 11);
        h2 = (int) (h2 & 0b1111111);
        h2 = (int) (h2 << 11);
        N = (int) ((N & ~MASK) | (h2 & MASK));
        setField06(N);
    }

    public int getI2() {
        var N = getField06();
        return (int) ((N >> 18) & 0b11111111);
    }

    public void setI2(int i2) {
        var N = getField06();
        int MASK = (int) (0b11111111 << 18);
        i2 = (int) (i2 & 0b11111111);
        i2 = (int) (i2 << 18);
        N = (int) ((N & ~MASK) | (i2 & MASK));
        setField06(N);
    }

    private static final VarHandle sep03VH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("sep03")
    );

    public MemorySegment getSep03() {
        var SEG = (MemorySegment) sep03VH.get(MEMORY);
        if (SEG.address() == 0) return null;
        return SEG;
    }

    public void setSep03(MemorySegment sep03) {
        if (sep03 == null) {
            sep03VH.set(MEMORY, MemorySegment.NULL);
        } else {
            sep03VH.set(MEMORY, sep03);
        }
    }

    private static final VarHandle field07VH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("field07")
    );

    public long getField07() {
        return (long) field07VH.get(MEMORY);
    }

    public void setField07(long field07) {
        field07VH.set(MEMORY, field07);
    }

    public long getJ() {
        var N = getField07();
        return (long) ((N >> 0) & 0b1);
    }

    public void setJ(long j) {
        var N = getField07();
        long MASK = (long) (0b1 << 0);
        j = (long) (j & 0b1);
        j = (long) (j << 0);
        N = (long) ((N & ~MASK) | (j & MASK));
        setField07(N);
    }

    public long getK() {
        var N = getField07();
        return (long) ((N >> 1) & 0b11);
    }

    public void setK(long k) {
        var N = getField07();
        long MASK = (long) (0b11 << 1);
        k = (long) (k & 0b11);
        k = (long) (k << 1);
        N = (long) ((N & ~MASK) | (k & MASK));
        setField07(N);
    }

    public long getL() {
        var N = getField07();
        return (long) ((N >> 3) & 0b1111111111111111111111);
    }

    public void setL(long l) {
        var N = getField07();
        long MASK = (long) (0b1111111111111111111111 << 3);
        l = (long) (l & 0b1111111111111111111111);
        l = (long) (l << 3);
        N = (long) ((N & ~MASK) | (l & MASK));
        setField07(N);
    }

    public long getM() {
        var N = getField07();
        return (long) ((N >> 25) & 0b111111111111111111111111111111111L);
    }

    public void setM(long m) {
        var N = getField07();
        long MASK = (long) (0b111111111111111111111111111111111L << 25);
        m = (long) (m & 0b111111111111111111111111111111111L);
        m = (long) (m << 25);
        N = (long) ((N & ~MASK) | (m & MASK));
        setField07(N);
    }

    private static final VarHandle field08VH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("field08")
    );

    public long getField08() {
        return (long) field08VH.get(MEMORY);
    }

    public void setField08(long field08) {
        field08VH.set(MEMORY, field08);
    }

    public long getJ2() {
        var N = getField08();
        return (long) ((N >> 0) & 0b1);
    }

    public void setJ2(long j2) {
        var N = getField08();
        long MASK = (long) (0b1 << 0);
        j2 = (long) (j2 & 0b1);
        j2 = (long) (j2 << 0);
        N = (long) ((N & ~MASK) | (j2 & MASK));
        setField08(N);
    }

    public long getK2() {
        var N = getField08();
        return (long) ((N >> 1) & 0b11);
    }

    public void setK2(long k2) {
        var N = getField08();
        long MASK = (long) (0b11 << 1);
        k2 = (long) (k2 & 0b11);
        k2 = (long) (k2 << 1);
        N = (long) ((N & ~MASK) | (k2 & MASK));
        setField08(N);
    }

    public long getL2() {
        var N = getField08();
        return (long) ((N >> 3) & 0b1111111111111111111111);
    }

    public void setL2(long l2) {
        var N = getField08();
        long MASK = (long) (0b1111111111111111111111 << 3);
        l2 = (long) (l2 & 0b1111111111111111111111);
        l2 = (long) (l2 << 3);
        N = (long) ((N & ~MASK) | (l2 & MASK));
        setField08(N);
    }

    public long getM2() {
        var N = getField08();
        return (long) ((N >> 25) & 0b111111111111111111111111111111111L);
    }

    public void setM2(long m2) {
        var N = getField08();
        long MASK = (long) (0b111111111111111111111111111111111L << 25);
        m2 = (long) (m2 & 0b111111111111111111111111111111111L);
        m2 = (long) (m2 << 25);
        N = (long) ((N & ~MASK) | (m2 & MASK));
        setField08(N);
    }

    public BitField(MemorySegment MEMORY) {
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
        OFFSET += ValueLayout.JAVA_BYTE.byteSize();
        OFFSET += ValueLayout.JAVA_BYTE.byteSize();
        OFFSET += 6; /* padding */
        OFFSET += ValueLayout.ADDRESS_UNALIGNED.byteSize();
        OFFSET += ValueLayout.JAVA_SHORT_UNALIGNED.byteSize();
        OFFSET += ValueLayout.JAVA_SHORT_UNALIGNED.byteSize();
        OFFSET += 4; /* padding */
        OFFSET += ValueLayout.ADDRESS_UNALIGNED.byteSize();
        OFFSET += ValueLayout.JAVA_INT_UNALIGNED.byteSize();
        OFFSET += ValueLayout.JAVA_INT_UNALIGNED.byteSize();
        OFFSET += ValueLayout.ADDRESS_UNALIGNED.byteSize();
        OFFSET += ValueLayout.JAVA_LONG_UNALIGNED.byteSize();
        OFFSET += ValueLayout.JAVA_LONG_UNALIGNED.byteSize();
    }

    public BitField(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT.byteSize()));
    }

    private static final MethodHandle setMH = PanamaUtils.lookupPNICriticalFunction(false, void.class, "JavaCritical_io_vproxy_pni_test_BitField_set", MemorySegment.class /* self */, byte.class /* a */, byte.class /* a2 */, byte.class /* b */, byte.class /* b2 */, short.class /* c */, short.class /* c2 */, short.class /* d */, short.class /* d2 */, short.class /* e */, short.class /* e2 */, int.class /* f */, int.class /* f2 */, int.class /* g */, int.class /* g2 */, int.class /* h */, int.class /* h2 */, int.class /* i */, int.class /* i2 */, long.class /* j */, long.class /* j2 */, long.class /* k */, long.class /* k2 */, long.class /* l */, long.class /* l2 */, long.class /* m */, long.class /* m2 */);

    public void set(byte a, byte a2, byte b, byte b2, short c, short c2, short d, short d2, short e, short e2, int f, int f2, int g, int g2, int h, int h2, int i, int i2, long j, long j2, long k, long k2, long l, long l2, long m, long m2) {
        try {
            setMH.invokeExact(MEMORY, a, a2, b, b2, c, c2, d, d2, e, e2, f, f2, g, g2, h, h2, i, i2, j, j2, k, k2, l, l2, m, m2);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle aMH = PanamaUtils.lookupPNICriticalFunction(false, byte.class, "JavaCritical_io_vproxy_pni_test_BitField_a", MemorySegment.class /* self */);

    public byte a() {
        byte RESULT;
        try {
            RESULT = (byte) aMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle a2MH = PanamaUtils.lookupPNICriticalFunction(false, byte.class, "JavaCritical_io_vproxy_pni_test_BitField_a2", MemorySegment.class /* self */);

    public byte a2() {
        byte RESULT;
        try {
            RESULT = (byte) a2MH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle bMH = PanamaUtils.lookupPNICriticalFunction(false, byte.class, "JavaCritical_io_vproxy_pni_test_BitField_b", MemorySegment.class /* self */);

    public byte b() {
        byte RESULT;
        try {
            RESULT = (byte) bMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle b2MH = PanamaUtils.lookupPNICriticalFunction(false, byte.class, "JavaCritical_io_vproxy_pni_test_BitField_b2", MemorySegment.class /* self */);

    public byte b2() {
        byte RESULT;
        try {
            RESULT = (byte) b2MH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle cMH = PanamaUtils.lookupPNICriticalFunction(false, short.class, "JavaCritical_io_vproxy_pni_test_BitField_c", MemorySegment.class /* self */);

    public short c() {
        short RESULT;
        try {
            RESULT = (short) cMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle c2MH = PanamaUtils.lookupPNICriticalFunction(false, short.class, "JavaCritical_io_vproxy_pni_test_BitField_c2", MemorySegment.class /* self */);

    public short c2() {
        short RESULT;
        try {
            RESULT = (short) c2MH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle dMH = PanamaUtils.lookupPNICriticalFunction(false, short.class, "JavaCritical_io_vproxy_pni_test_BitField_d", MemorySegment.class /* self */);

    public short d() {
        short RESULT;
        try {
            RESULT = (short) dMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle d2MH = PanamaUtils.lookupPNICriticalFunction(false, short.class, "JavaCritical_io_vproxy_pni_test_BitField_d2", MemorySegment.class /* self */);

    public short d2() {
        short RESULT;
        try {
            RESULT = (short) d2MH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle eMH = PanamaUtils.lookupPNICriticalFunction(false, short.class, "JavaCritical_io_vproxy_pni_test_BitField_e", MemorySegment.class /* self */);

    public short e() {
        short RESULT;
        try {
            RESULT = (short) eMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle e2MH = PanamaUtils.lookupPNICriticalFunction(false, short.class, "JavaCritical_io_vproxy_pni_test_BitField_e2", MemorySegment.class /* self */);

    public short e2() {
        short RESULT;
        try {
            RESULT = (short) e2MH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle fMH = PanamaUtils.lookupPNICriticalFunction(false, int.class, "JavaCritical_io_vproxy_pni_test_BitField_f", MemorySegment.class /* self */);

    public int f() {
        int RESULT;
        try {
            RESULT = (int) fMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle f2MH = PanamaUtils.lookupPNICriticalFunction(false, int.class, "JavaCritical_io_vproxy_pni_test_BitField_f2", MemorySegment.class /* self */);

    public int f2() {
        int RESULT;
        try {
            RESULT = (int) f2MH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle gMH = PanamaUtils.lookupPNICriticalFunction(false, int.class, "JavaCritical_io_vproxy_pni_test_BitField_g", MemorySegment.class /* self */);

    public int g() {
        int RESULT;
        try {
            RESULT = (int) gMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle g2MH = PanamaUtils.lookupPNICriticalFunction(false, int.class, "JavaCritical_io_vproxy_pni_test_BitField_g2", MemorySegment.class /* self */);

    public int g2() {
        int RESULT;
        try {
            RESULT = (int) g2MH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle hMH = PanamaUtils.lookupPNICriticalFunction(false, int.class, "JavaCritical_io_vproxy_pni_test_BitField_h", MemorySegment.class /* self */);

    public int h() {
        int RESULT;
        try {
            RESULT = (int) hMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle h2MH = PanamaUtils.lookupPNICriticalFunction(false, int.class, "JavaCritical_io_vproxy_pni_test_BitField_h2", MemorySegment.class /* self */);

    public int h2() {
        int RESULT;
        try {
            RESULT = (int) h2MH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle iMH = PanamaUtils.lookupPNICriticalFunction(false, int.class, "JavaCritical_io_vproxy_pni_test_BitField_i", MemorySegment.class /* self */);

    public int i() {
        int RESULT;
        try {
            RESULT = (int) iMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle i2MH = PanamaUtils.lookupPNICriticalFunction(false, int.class, "JavaCritical_io_vproxy_pni_test_BitField_i2", MemorySegment.class /* self */);

    public int i2() {
        int RESULT;
        try {
            RESULT = (int) i2MH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle jMH = PanamaUtils.lookupPNICriticalFunction(false, long.class, "JavaCritical_io_vproxy_pni_test_BitField_j", MemorySegment.class /* self */);

    public long j() {
        long RESULT;
        try {
            RESULT = (long) jMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle j2MH = PanamaUtils.lookupPNICriticalFunction(false, long.class, "JavaCritical_io_vproxy_pni_test_BitField_j2", MemorySegment.class /* self */);

    public long j2() {
        long RESULT;
        try {
            RESULT = (long) j2MH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle kMH = PanamaUtils.lookupPNICriticalFunction(false, long.class, "JavaCritical_io_vproxy_pni_test_BitField_k", MemorySegment.class /* self */);

    public long k() {
        long RESULT;
        try {
            RESULT = (long) kMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle k2MH = PanamaUtils.lookupPNICriticalFunction(false, long.class, "JavaCritical_io_vproxy_pni_test_BitField_k2", MemorySegment.class /* self */);

    public long k2() {
        long RESULT;
        try {
            RESULT = (long) k2MH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle lMH = PanamaUtils.lookupPNICriticalFunction(false, long.class, "JavaCritical_io_vproxy_pni_test_BitField_l", MemorySegment.class /* self */);

    public long l() {
        long RESULT;
        try {
            RESULT = (long) lMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle l2MH = PanamaUtils.lookupPNICriticalFunction(false, long.class, "JavaCritical_io_vproxy_pni_test_BitField_l2", MemorySegment.class /* self */);

    public long l2() {
        long RESULT;
        try {
            RESULT = (long) l2MH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle mMH = PanamaUtils.lookupPNICriticalFunction(false, long.class, "JavaCritical_io_vproxy_pni_test_BitField_m", MemorySegment.class /* self */);

    public long m() {
        long RESULT;
        try {
            RESULT = (long) mMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle m2MH = PanamaUtils.lookupPNICriticalFunction(false, long.class, "JavaCritical_io_vproxy_pni_test_BitField_m2", MemorySegment.class /* self */);

    public long m2() {
        long RESULT;
        try {
            RESULT = (long) m2MH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    public static class Array extends RefArray<BitField> {
        public Array(MemorySegment buf) {
            super(buf, BitField.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            this(allocator.allocate(BitField.LAYOUT.byteSize() * len));
        }

        public Array(PNIBuf buf) {
            this(buf.get());
        }

        @Override
        protected BitField construct(MemorySegment seg) {
            return new BitField(seg);
        }

        @Override
        protected MemorySegment getSegment(BitField value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<BitField> {
        private Func(io.vproxy.pni.CallSite<BitField> func) {
            super(func);
        }

        private Func(io.vproxy.pni.CallSite<BitField> func, Options opts) {
            super(func, opts);
        }

        private Func(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static Func of(io.vproxy.pni.CallSite<BitField> func) {
            return new Func(func);
        }

        public static Func of(io.vproxy.pni.CallSite<BitField> func, Options opts) {
            return new Func(func, opts);
        }

        public static Func of(MemorySegment MEMORY) {
            return new Func(MEMORY);
        }

        @Override
        protected BitField construct(MemorySegment seg) {
            return new BitField(seg);
        }
    }
}
// metadata.generator-version: pni test
// sha256:1a5478a66b79a918d94dfcfa2bfdb51eaa3bcc519a3e8563ca4ecf6e667740fb
