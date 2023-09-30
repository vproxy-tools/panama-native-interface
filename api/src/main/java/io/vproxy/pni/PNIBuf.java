package io.vproxy.pni;

import io.vproxy.pni.array.*;

import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.VarHandle;
import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.Set;

public class PNIBuf implements NativeObject {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        ValueLayout.ADDRESS_UNALIGNED.withName("buf"),
        ValueLayout.JAVA_LONG_UNALIGNED.withName("len")
    );

    public final MemorySegment MEMORY;

    @Override
    public MemorySegment MEMORY() {
        return MEMORY;
    }

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

    public static MemorySegment memoryOf(Allocator allocator, MemorySegment v) {
        if (v == null) return MemorySegment.NULL;
        return of(allocator, v).MEMORY;
    }

    public static PNIBuf of(Allocator allocator, BoolArray v) {
        if (v == null) return null;
        return new PNIBuf(allocator, v.MEMORY);
    }

    public static MemorySegment memoryOf(Allocator allocator, BoolArray v) {
        if (v == null) return MemorySegment.NULL;
        return of(allocator, v).MEMORY;
    }

    public static PNIBuf of(Allocator allocator, CharArray v) {
        if (v == null) return null;
        return new PNIBuf(allocator, v.MEMORY);
    }

    public static MemorySegment memoryOf(Allocator allocator, CharArray v) {
        if (v == null) return MemorySegment.NULL;
        return of(allocator, v).MEMORY;
    }

    public static PNIBuf of(Allocator allocator, DoubleArray v) {
        if (v == null) return null;
        return new PNIBuf(allocator, v.MEMORY);
    }

    public static MemorySegment memoryOf(Allocator allocator, DoubleArray v) {
        if (v == null) return MemorySegment.NULL;
        return of(allocator, v).MEMORY;
    }

    public static PNIBuf of(Allocator allocator, FloatArray v) {
        if (v == null) return null;
        return new PNIBuf(allocator, v.MEMORY);
    }

    public static MemorySegment memoryOf(Allocator allocator, FloatArray v) {
        if (v == null) return MemorySegment.NULL;
        return of(allocator, v).MEMORY;
    }

    public static PNIBuf of(Allocator allocator, IntArray v) {
        if (v == null) return null;
        return new PNIBuf(allocator, v.MEMORY);
    }

    public static MemorySegment memoryOf(Allocator allocator, IntArray v) {
        if (v == null) return MemorySegment.NULL;
        return of(allocator, v).MEMORY;
    }

    public static PNIBuf of(Allocator allocator, LongArray v) {
        if (v == null) return null;
        return new PNIBuf(allocator, v.MEMORY);
    }

    public static MemorySegment memoryOf(Allocator allocator, LongArray v) {
        if (v == null) return MemorySegment.NULL;
        return of(allocator, v).MEMORY;
    }

    public static PNIBuf of(Allocator allocator, ShortArray v) {
        if (v == null) return null;
        return new PNIBuf(allocator, v.MEMORY);
    }

    public static MemorySegment memoryOf(Allocator allocator, ShortArray v) {
        if (v == null) return MemorySegment.NULL;
        return of(allocator, v).MEMORY;
    }

    public static PNIBuf of(Allocator allocator, RefArray<?> v) {
        if (v == null) return null;
        return new PNIBuf(allocator, v.MEMORY);
    }

    public static MemorySegment memoryOf(Allocator allocator, RefArray<?> v) {
        if (v == null) return MemorySegment.NULL;
        return of(allocator, v).MEMORY;
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

    @Override
    public String toString() {
        var sb = new StringBuilder();
        toString(sb, 0, new HashSet<>(), false);
        return sb.toString();
    }

    @Override
    public void toString(StringBuilder sb, int indent, Set<NativeObjectTuple> visited, boolean corrupted) {
        if (corrupted) {
            sb.append("<?>@").append(Long.toString(MEMORY.address(), 16));
        } else {
            sb.append("PNIBuf(").append(PanamaUtils.memorySegmentToString(get())).append(")@")
                .append(Long.toString(MEMORY.address(), 16));
        }
    }
}
