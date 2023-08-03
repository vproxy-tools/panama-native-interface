package io.vproxy.pni;

import io.vproxy.pni.array.*;

import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.VarHandle;
import java.nio.ByteBuffer;

public class PNIBuf {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        ValueLayout.ADDRESS_UNALIGNED.withName("buf"),
        ValueLayout.JAVA_LONG_UNALIGNED.withName("len")
    );

    public final MemorySegment MEMORY;

    public PNIBuf(MemorySegment memory) {
        MEMORY = memory.reinterpret(LAYOUT.byteSize());
    }

    public PNIBuf(Allocator allocator) {
        this(allocator.allocate(LAYOUT.byteSize()));
    }

    private PNIBuf(Allocator allocator, MemorySegment buf) {
        this(allocator);
        set(buf);
    }

    public static PNIBuf of(Allocator allocator, MemorySegment v) {
        if (v == null) return null;
        return new PNIBuf(allocator, v);
    }

    public static PNIBuf of(Allocator allocator, BoolArray v) {
        if (v == null) return null;
        return new PNIBuf(allocator, v.MEMORY);
    }

    public static PNIBuf of(Allocator allocator, CharArray v) {
        if (v == null) return null;
        return new PNIBuf(allocator, v.MEMORY);
    }

    public static PNIBuf of(Allocator allocator, DoubleArray v) {
        if (v == null) return null;
        return new PNIBuf(allocator, v.MEMORY);
    }

    public static PNIBuf of(Allocator allocator, FloatArray v) {
        if (v == null) return null;
        return new PNIBuf(allocator, v.MEMORY);
    }

    public static PNIBuf of(Allocator allocator, IntArray v) {
        if (v == null) return null;
        return new PNIBuf(allocator, v.MEMORY);
    }

    public static PNIBuf of(Allocator allocator, LongArray v) {
        if (v == null) return null;
        return new PNIBuf(allocator, v.MEMORY);
    }

    public static PNIBuf of(Allocator allocator, ShortArray v) {
        if (v == null) return null;
        return new PNIBuf(allocator, v.MEMORY);
    }

    public static PNIBuf of(Allocator allocator, RefArray<?> v) {
        if (v == null) return null;
        return new PNIBuf(allocator, v.MEMORY);
    }

    private static final VarHandle bufVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("buf")
    );

    private static final VarHandle lenVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("len")
    );

    public MemorySegment get() {
        var seg = (MemorySegment) bufVH.get(MEMORY);
        if (seg.address() == 0) {
            return null;
        }
        long len = (long) lenVH.get(MEMORY);
        return seg.reinterpret(len);
    }

    public boolean isNull() {
        var seg = (MemorySegment) bufVH.get(MEMORY);
        return seg.address() == 0;
    }

    public void set(MemorySegment buf) {
        bufVH.set(MEMORY, buf);
        lenVH.set(MEMORY, buf.byteSize());
    }

    public void set(ByteBuffer buf) {
        set(MemorySegment.ofBuffer(buf));
    }

    public void setToNull() {
        lenVH.set(MEMORY, 0L);
        bufVH.set(MEMORY, MemorySegment.NULL);
    }

    public BoolArray toBoolArray() {
        var seg = get();
        return seg == null ? null : new BoolArray(seg);
    }

    public CharArray toCharArray() {
        var seg = get();
        return seg == null ? null : new CharArray(seg);
    }

    public DoubleArray toDoubleArray() {
        var seg = get();
        return seg == null ? null : new DoubleArray(seg);
    }

    public FloatArray toFloatArray() {
        var seg = get();
        return seg == null ? null : new FloatArray(seg);
    }

    public IntArray toIntArray() {
        var seg = get();
        return seg == null ? null : new IntArray(seg);
    }

    public LongArray toLongArray() {
        var seg = get();
        return seg == null ? null : new LongArray(seg);
    }

    public ShortArray toShortArray() {
        var seg = get();
        return seg == null ? null : new ShortArray(seg);
    }

    public ByteBuffer toByteBuffer() {
        var seg = get();
        if (seg == null) {
            return null;
        }
        return seg.asByteBuffer();
    }
}
