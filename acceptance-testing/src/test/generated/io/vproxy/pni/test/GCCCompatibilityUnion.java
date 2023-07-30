package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class GCCCompatibilityUnion {
    public static final MemoryLayout LAYOUT = MemoryLayout.unionLayout(
        ValueLayout.JAVA_BYTE.withName("b"),
        ValueLayout.JAVA_SHORT_UNALIGNED.withName("s"),
        ValueLayout.JAVA_INT_UNALIGNED.withName("n"),
        ValueLayout.JAVA_FLOAT_UNALIGNED.withName("f"),
        ValueLayout.JAVA_DOUBLE_UNALIGNED.withName("d"),
        ValueLayout.JAVA_LONG_UNALIGNED.withName("l")
    );
    public final MemorySegment MEMORY;

    private static final VarHandle bVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("b")
    );

    public byte getB() {
        return (byte) bVH.get(MEMORY);
    }

    public void setB(byte b) {
        bVH.set(MEMORY, b);
    }

    private static final VarHandle sVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("s")
    );

    public short getS() {
        return (short) sVH.get(MEMORY);
    }

    public void setS(short s) {
        sVH.set(MEMORY, s);
    }

    private static final VarHandle nVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("n")
    );

    public int getN() {
        return (int) nVH.get(MEMORY);
    }

    public void setN(int n) {
        nVH.set(MEMORY, n);
    }

    private static final VarHandle fVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("f")
    );

    public float getF() {
        return (float) fVH.get(MEMORY);
    }

    public void setF(float f) {
        fVH.set(MEMORY, f);
    }

    private static final VarHandle dVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("d")
    );

    public double getD() {
        return (double) dVH.get(MEMORY);
    }

    public void setD(double d) {
        dVH.set(MEMORY, d);
    }

    private static final VarHandle lVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("l")
    );

    public long getL() {
        return (long) lVH.get(MEMORY);
    }

    public void setL(long l) {
        lVH.set(MEMORY, l);
    }

    public GCCCompatibilityUnion(MemorySegment MEMORY) {
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
        OFFSET += ValueLayout.JAVA_BYTE.byteSize();
        OFFSET = 0;
        OFFSET += ValueLayout.JAVA_SHORT_UNALIGNED.byteSize();
        OFFSET = 0;
        OFFSET += ValueLayout.JAVA_INT_UNALIGNED.byteSize();
        OFFSET = 0;
        OFFSET += ValueLayout.JAVA_FLOAT_UNALIGNED.byteSize();
        OFFSET = 0;
        OFFSET += ValueLayout.JAVA_DOUBLE_UNALIGNED.byteSize();
        OFFSET = 0;
        OFFSET += ValueLayout.JAVA_LONG_UNALIGNED.byteSize();
        OFFSET = 0;
    }

    public GCCCompatibilityUnion(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT.byteSize()));
    }

    public static class Array extends RefArray<GCCCompatibilityUnion> {
        public Array(MemorySegment buf) {
            super(buf, GCCCompatibilityUnion.LAYOUT);
        }

        @Override
        protected GCCCompatibilityUnion construct(MemorySegment seg) {
            return new GCCCompatibilityUnion(seg);
        }

        @Override
        protected MemorySegment getSegment(GCCCompatibilityUnion value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<GCCCompatibilityUnion> {
        private Func(io.vproxy.pni.CallSite<GCCCompatibilityUnion> func) {
            super(func);
        }

        public static Func of(io.vproxy.pni.CallSite<GCCCompatibilityUnion> func) {
            return new Func(func);
        }

        @Override
        protected GCCCompatibilityUnion construct(MemorySegment seg) {
            return new GCCCompatibilityUnion(seg);
        }

        @Override
        protected MemorySegment getSegment(GCCCompatibilityUnion value) {
            return value.MEMORY;
        }
    }
}
// sha256:c0c78e45b8c84e7488713e4aaa74e47282f3941bad293824f4914e7e7594c04c
