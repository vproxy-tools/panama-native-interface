package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.hack.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class GCCompatibilityBitField extends AbstractNativeObject implements NativeObject {
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

    @Override
    public MemorySegment MEMORY() {
        return MEMORY;
    }

    private static final VarHandleW field01VH = VarHandleW.of(
        LAYOUT.varHandle(
            MemoryLayout.PathElement.groupElement("field01")
        )
    );

    public byte getField01() {
        return field01VH.getByte(MEMORY);
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

    private static final VarHandleW field02VH = VarHandleW.of(
        LAYOUT.varHandle(
            MemoryLayout.PathElement.groupElement("field02")
        )
    );

    public byte getField02() {
        return field02VH.getByte(MEMORY);
    }

    public void setField02(byte field02) {
        field02VH.set(MEMORY, field02);
    }

    public boolean isA2() {
        var N = getField02();
        return ((N >> 0) & 0b1) == 1;
    }

    public void setA2(boolean a2) {
        var N = getField02();
        byte MASK = (byte) (0b1 << 0);
        var NN = (byte) (a2 ? 1 : 0);
        NN = (byte) (NN << 0);
        N = (byte) ((N & ~MASK) | (NN & MASK));
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

    private static final VarHandleW sep01VH = VarHandleW.of(
        LAYOUT.varHandle(
            MemoryLayout.PathElement.groupElement("sep01")
        )
    );

    public MemorySegment getSep01() {
        var SEG = sep01VH.getMemorySegment(MEMORY);
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

    private static final VarHandleW field03VH = VarHandleW.of(
        LAYOUT.varHandle(
            MemoryLayout.PathElement.groupElement("field03")
        )
    );

    public short getField03() {
        return field03VH.getShort(MEMORY);
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

    private static final VarHandleW field04VH = VarHandleW.of(
        LAYOUT.varHandle(
            MemoryLayout.PathElement.groupElement("field04")
        )
    );

    public short getField04() {
        return field04VH.getShort(MEMORY);
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

    private static final VarHandleW sep02VH = VarHandleW.of(
        LAYOUT.varHandle(
            MemoryLayout.PathElement.groupElement("sep02")
        )
    );

    public MemorySegment getSep02() {
        var SEG = sep02VH.getMemorySegment(MEMORY);
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

    private static final VarHandleW field05VH = VarHandleW.of(
        LAYOUT.varHandle(
            MemoryLayout.PathElement.groupElement("field05")
        )
    );

    public int getField05() {
        return field05VH.getInt(MEMORY);
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

    private static final VarHandleW field06VH = VarHandleW.of(
        LAYOUT.varHandle(
            MemoryLayout.PathElement.groupElement("field06")
        )
    );

    public int getField06() {
        return field06VH.getInt(MEMORY);
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

    private static final VarHandleW sep03VH = VarHandleW.of(
        LAYOUT.varHandle(
            MemoryLayout.PathElement.groupElement("sep03")
        )
    );

    public MemorySegment getSep03() {
        var SEG = sep03VH.getMemorySegment(MEMORY);
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

    private static final VarHandleW field07VH = VarHandleW.of(
        LAYOUT.varHandle(
            MemoryLayout.PathElement.groupElement("field07")
        )
    );

    public long getField07() {
        return field07VH.getLong(MEMORY);
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

    public long getN() {
        var N = getField07();
        return (long) ((N >> 58) & 0b1);
    }

    public void setN(long n) {
        var N = getField07();
        long MASK = (long) (0b1L << 58);
        n = (long) (n & 0b1L);
        n = (long) (n << 58);
        N = (long) ((N & ~MASK) | (n & MASK));
        setField07(N);
    }

    private static final VarHandleW field08VH = VarHandleW.of(
        LAYOUT.varHandle(
            MemoryLayout.PathElement.groupElement("field08")
        )
    );

    public long getField08() {
        return field08VH.getLong(MEMORY);
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

    public boolean isN2() {
        var N = getField08();
        return ((N >> 58) & 0b1) == 1;
    }

    public void setN2(boolean n2) {
        var N = getField08();
        long MASK = (long) (0b1L << 58);
        var NN = (long) (n2 ? 1 : 0);
        NN = (long) (NN << 58);
        N = (long) ((N & ~MASK) | (NN & MASK));
        setField08(N);
    }

    public GCCompatibilityBitField(MemorySegment MEMORY) {
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

    public GCCompatibilityBitField(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT));
    }

    private static final MethodHandle setMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), void.class, "JavaCritical_io_vproxy_pni_test_GCCompatibilityBitField_set", MemorySegment.class /* self */, byte.class /* a */, byte.class /* a2 */, byte.class /* b */, byte.class /* b2 */, short.class /* c */, short.class /* c2 */, short.class /* d */, short.class /* d2 */, short.class /* e */, short.class /* e2 */, int.class /* f */, int.class /* f2 */, int.class /* g */, int.class /* g2 */, int.class /* h */, int.class /* h2 */, int.class /* i */, int.class /* i2 */, long.class /* j */, long.class /* j2 */, long.class /* k */, long.class /* k2 */, long.class /* l */, long.class /* l2 */, long.class /* m */, long.class /* m2 */, long.class /* n */, long.class /* n2 */);

    public void set(byte a, byte a2, byte b, byte b2, short c, short c2, short d, short d2, short e, short e2, int f, int f2, int g, int g2, int h, int h2, int i, int i2, long j, long j2, long k, long k2, long l, long l2, long m, long m2, long n, long n2) {
        try {
            setMH.invokeExact(MEMORY, a, a2, b, b2, c, c2, d, d2, e, e2, f, f2, g, g2, h, h2, i, i2, j, j2, k, k2, l, l2, m, m2, n, n2);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle aMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), byte.class, "JavaCritical_io_vproxy_pni_test_GCCompatibilityBitField_a", MemorySegment.class /* self */);

    public byte a() {
        byte RESULT;
        try {
            RESULT = (byte) aMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle a2MH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), byte.class, "JavaCritical_io_vproxy_pni_test_GCCompatibilityBitField_a2", MemorySegment.class /* self */);

    public byte a2() {
        byte RESULT;
        try {
            RESULT = (byte) a2MH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle bMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), byte.class, "JavaCritical_io_vproxy_pni_test_GCCompatibilityBitField_b", MemorySegment.class /* self */);

    public byte b() {
        byte RESULT;
        try {
            RESULT = (byte) bMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle b2MH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), byte.class, "JavaCritical_io_vproxy_pni_test_GCCompatibilityBitField_b2", MemorySegment.class /* self */);

    public byte b2() {
        byte RESULT;
        try {
            RESULT = (byte) b2MH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle cMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), short.class, "JavaCritical_io_vproxy_pni_test_GCCompatibilityBitField_c", MemorySegment.class /* self */);

    public short c() {
        short RESULT;
        try {
            RESULT = (short) cMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle c2MH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), short.class, "JavaCritical_io_vproxy_pni_test_GCCompatibilityBitField_c2", MemorySegment.class /* self */);

    public short c2() {
        short RESULT;
        try {
            RESULT = (short) c2MH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle dMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), short.class, "JavaCritical_io_vproxy_pni_test_GCCompatibilityBitField_d", MemorySegment.class /* self */);

    public short d() {
        short RESULT;
        try {
            RESULT = (short) dMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle d2MH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), short.class, "JavaCritical_io_vproxy_pni_test_GCCompatibilityBitField_d2", MemorySegment.class /* self */);

    public short d2() {
        short RESULT;
        try {
            RESULT = (short) d2MH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle eMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), short.class, "JavaCritical_io_vproxy_pni_test_GCCompatibilityBitField_e", MemorySegment.class /* self */);

    public short e() {
        short RESULT;
        try {
            RESULT = (short) eMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle e2MH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), short.class, "JavaCritical_io_vproxy_pni_test_GCCompatibilityBitField_e2", MemorySegment.class /* self */);

    public short e2() {
        short RESULT;
        try {
            RESULT = (short) e2MH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle fMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), int.class, "JavaCritical_io_vproxy_pni_test_GCCompatibilityBitField_f", MemorySegment.class /* self */);

    public int f() {
        int RESULT;
        try {
            RESULT = (int) fMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle f2MH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), int.class, "JavaCritical_io_vproxy_pni_test_GCCompatibilityBitField_f2", MemorySegment.class /* self */);

    public int f2() {
        int RESULT;
        try {
            RESULT = (int) f2MH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle gMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), int.class, "JavaCritical_io_vproxy_pni_test_GCCompatibilityBitField_g", MemorySegment.class /* self */);

    public int g() {
        int RESULT;
        try {
            RESULT = (int) gMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle g2MH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), int.class, "JavaCritical_io_vproxy_pni_test_GCCompatibilityBitField_g2", MemorySegment.class /* self */);

    public int g2() {
        int RESULT;
        try {
            RESULT = (int) g2MH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle hMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), int.class, "JavaCritical_io_vproxy_pni_test_GCCompatibilityBitField_h", MemorySegment.class /* self */);

    public int h() {
        int RESULT;
        try {
            RESULT = (int) hMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle h2MH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), int.class, "JavaCritical_io_vproxy_pni_test_GCCompatibilityBitField_h2", MemorySegment.class /* self */);

    public int h2() {
        int RESULT;
        try {
            RESULT = (int) h2MH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle iMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), int.class, "JavaCritical_io_vproxy_pni_test_GCCompatibilityBitField_i", MemorySegment.class /* self */);

    public int i() {
        int RESULT;
        try {
            RESULT = (int) iMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle i2MH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), int.class, "JavaCritical_io_vproxy_pni_test_GCCompatibilityBitField_i2", MemorySegment.class /* self */);

    public int i2() {
        int RESULT;
        try {
            RESULT = (int) i2MH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle jMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), long.class, "JavaCritical_io_vproxy_pni_test_GCCompatibilityBitField_j", MemorySegment.class /* self */);

    public long j() {
        long RESULT;
        try {
            RESULT = (long) jMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle j2MH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), long.class, "JavaCritical_io_vproxy_pni_test_GCCompatibilityBitField_j2", MemorySegment.class /* self */);

    public long j2() {
        long RESULT;
        try {
            RESULT = (long) j2MH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle kMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), long.class, "JavaCritical_io_vproxy_pni_test_GCCompatibilityBitField_k", MemorySegment.class /* self */);

    public long k() {
        long RESULT;
        try {
            RESULT = (long) kMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle k2MH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), long.class, "JavaCritical_io_vproxy_pni_test_GCCompatibilityBitField_k2", MemorySegment.class /* self */);

    public long k2() {
        long RESULT;
        try {
            RESULT = (long) k2MH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle lMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), long.class, "JavaCritical_io_vproxy_pni_test_GCCompatibilityBitField_l", MemorySegment.class /* self */);

    public long l() {
        long RESULT;
        try {
            RESULT = (long) lMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle l2MH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), long.class, "JavaCritical_io_vproxy_pni_test_GCCompatibilityBitField_l2", MemorySegment.class /* self */);

    public long l2() {
        long RESULT;
        try {
            RESULT = (long) l2MH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle mMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), long.class, "JavaCritical_io_vproxy_pni_test_GCCompatibilityBitField_m", MemorySegment.class /* self */);

    public long m() {
        long RESULT;
        try {
            RESULT = (long) mMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle m2MH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), long.class, "JavaCritical_io_vproxy_pni_test_GCCompatibilityBitField_m2", MemorySegment.class /* self */);

    public long m2() {
        long RESULT;
        try {
            RESULT = (long) m2MH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle nMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), long.class, "JavaCritical_io_vproxy_pni_test_GCCompatibilityBitField_n", MemorySegment.class /* self */);

    public long n() {
        long RESULT;
        try {
            RESULT = (long) nMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle n2MH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), long.class, "JavaCritical_io_vproxy_pni_test_GCCompatibilityBitField_n2", MemorySegment.class /* self */);

    public long n2() {
        long RESULT;
        try {
            RESULT = (long) n2MH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    @Override
    public void toString(StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
        if (!VISITED.add(new NativeObjectTuple(this))) {
            SB.append("<...>@").append(Long.toString(MEMORY.address(), 16));
            return;
        }
        SB.append("GCCompatibilityBitField{\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("field01 => ");
            SB.append(getField01());
            SB.append(" {\n");
            SB.append(" ".repeat(INDENT + 8)).append("a:1 => ").append(getA());
            SB.append(",\n");
            SB.append(" ".repeat(INDENT + 8)).append("b:3 => ").append(getB());
            SB.append("\n");
            SB.append(" ".repeat(INDENT + 4)).append("}");
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("field02 => ");
            SB.append(getField02());
            SB.append(" {\n");
            SB.append(" ".repeat(INDENT + 8)).append("a2:1 => ").append(isA2());
            SB.append(",\n");
            SB.append(" ".repeat(INDENT + 8)).append("b2:3 => ").append(getB2());
            SB.append("\n");
            SB.append(" ".repeat(INDENT + 4)).append("}");
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("sep01 => ");
            SB.append(PanamaUtils.memorySegmentToString(getSep01()));
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("field03 => ");
            SB.append(getField03());
            SB.append(" {\n");
            SB.append(" ".repeat(INDENT + 8)).append("c:2 => ").append(getC());
            SB.append(",\n");
            SB.append(" ".repeat(INDENT + 8)).append("d:3 => ").append(getD());
            SB.append(",\n");
            SB.append(" ".repeat(INDENT + 8)).append("e:4 => ").append(getE());
            SB.append("\n");
            SB.append(" ".repeat(INDENT + 4)).append("}");
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("field04 => ");
            SB.append(getField04());
            SB.append(" {\n");
            SB.append(" ".repeat(INDENT + 8)).append("c2:2 => ").append(getC2());
            SB.append(",\n");
            SB.append(" ".repeat(INDENT + 8)).append("d2:3 => ").append(getD2());
            SB.append(",\n");
            SB.append(" ".repeat(INDENT + 8)).append("e2:4 => ").append(getE2());
            SB.append("\n");
            SB.append(" ".repeat(INDENT + 4)).append("}");
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("sep02 => ");
            SB.append(PanamaUtils.memorySegmentToString(getSep02()));
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("field05 => ");
            SB.append(getField05());
            SB.append(" {\n");
            SB.append(" ".repeat(INDENT + 8)).append("f:5 => ").append(getF());
            SB.append(",\n");
            SB.append(" ".repeat(INDENT + 8)).append("g:6 => ").append(getG());
            SB.append(",\n");
            SB.append(" ".repeat(INDENT + 8)).append("h:7 => ").append(getH());
            SB.append(",\n");
            SB.append(" ".repeat(INDENT + 8)).append("i:8 => ").append(getI());
            SB.append("\n");
            SB.append(" ".repeat(INDENT + 4)).append("}");
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("field06 => ");
            SB.append(getField06());
            SB.append(" {\n");
            SB.append(" ".repeat(INDENT + 8)).append("f2:5 => ").append(getF2());
            SB.append(",\n");
            SB.append(" ".repeat(INDENT + 8)).append("g2:6 => ").append(getG2());
            SB.append(",\n");
            SB.append(" ".repeat(INDENT + 8)).append("h2:7 => ").append(getH2());
            SB.append(",\n");
            SB.append(" ".repeat(INDENT + 8)).append("i2:8 => ").append(getI2());
            SB.append("\n");
            SB.append(" ".repeat(INDENT + 4)).append("}");
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("sep03 => ");
            SB.append(PanamaUtils.memorySegmentToString(getSep03()));
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("field07 => ");
            SB.append(getField07());
            SB.append(" {\n");
            SB.append(" ".repeat(INDENT + 8)).append("j:1 => ").append(getJ());
            SB.append(",\n");
            SB.append(" ".repeat(INDENT + 8)).append("k:2 => ").append(getK());
            SB.append(",\n");
            SB.append(" ".repeat(INDENT + 8)).append("l:22 => ").append(getL());
            SB.append(",\n");
            SB.append(" ".repeat(INDENT + 8)).append("m:33 => ").append(getM());
            SB.append(",\n");
            SB.append(" ".repeat(INDENT + 8)).append("n:1 => ").append(getN());
            SB.append("\n");
            SB.append(" ".repeat(INDENT + 4)).append("}");
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("field08 => ");
            SB.append(getField08());
            SB.append(" {\n");
            SB.append(" ".repeat(INDENT + 8)).append("j2:1 => ").append(getJ2());
            SB.append(",\n");
            SB.append(" ".repeat(INDENT + 8)).append("k2:2 => ").append(getK2());
            SB.append(",\n");
            SB.append(" ".repeat(INDENT + 8)).append("l2:22 => ").append(getL2());
            SB.append(",\n");
            SB.append(" ".repeat(INDENT + 8)).append("m2:33 => ").append(getM2());
            SB.append(",\n");
            SB.append(" ".repeat(INDENT + 8)).append("n2:1 => ").append(isN2());
            SB.append("\n");
            SB.append(" ".repeat(INDENT + 4)).append("}");
        }
        SB.append("\n");
        SB.append(" ".repeat(INDENT)).append("}@").append(Long.toString(MEMORY.address(), 16));
    }

    public static class Array extends RefArray<GCCompatibilityBitField> {
        public Array(MemorySegment buf) {
            super(buf, GCCompatibilityBitField.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            super(allocator, GCCompatibilityBitField.LAYOUT, len);
        }

        public Array(PNIBuf buf) {
            super(buf, GCCompatibilityBitField.LAYOUT);
        }

        @Override
        protected void elementToString(io.vproxy.pni.test.GCCompatibilityBitField ELEM, StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
            ELEM.toString(SB, INDENT, VISITED, CORRUPTED_MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "GCCompatibilityBitField.Array";
        }

        @Override
        protected GCCompatibilityBitField construct(MemorySegment seg) {
            return new GCCompatibilityBitField(seg);
        }

        @Override
        protected MemorySegment getSegment(GCCompatibilityBitField value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<GCCompatibilityBitField> {
        private Func(io.vproxy.pni.CallSite<GCCompatibilityBitField> func) {
            super(func);
        }

        private Func(io.vproxy.pni.CallSite<GCCompatibilityBitField> func, Options opts) {
            super(func, opts);
        }

        private Func(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static Func of(io.vproxy.pni.CallSite<GCCompatibilityBitField> func) {
            return new Func(func);
        }

        public static Func of(io.vproxy.pni.CallSite<GCCompatibilityBitField> func, Options opts) {
            return new Func(func, opts);
        }

        public static Func of(MemorySegment MEMORY) {
            return new Func(MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "GCCompatibilityBitField.Func";
        }

        @Override
        protected GCCompatibilityBitField construct(MemorySegment seg) {
            return new GCCompatibilityBitField(seg);
        }
    }
}
// metadata.generator-version: pni test
// sha256:07208ff9068c308fdcad1b051f659e2636d99e2c925463b860c430368d32241c
