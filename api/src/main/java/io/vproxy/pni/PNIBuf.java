package io.vproxy.pni;

import io.vproxy.pni.array.*;

import java.lang.foreign.Arena;
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

    public PNIBuf(Arena arena) {
        this(arena.allocate(LAYOUT.byteSize()));
    }

    private PNIBuf(Arena arena, MemorySegment buf) {
        this(arena.allocate(LAYOUT.byteSize()));
        set(buf);
    }

    public static PNIBuf of(Arena arena, MemorySegment v) {
        if (v == null) return null;
        return new PNIBuf(arena, v);
    }

    public static PNIBuf of(Arena arena, BoolArray v) {
        if (v == null) return null;
        return new PNIBuf(arena, v.MEMORY);
    }

    public static PNIBuf of(Arena arena, CharArray v) {
        if (v == null) return null;
        return new PNIBuf(arena, v.MEMORY);
    }

    public static PNIBuf of(Arena arena, DoubleArray v) {
        if (v == null) return null;
        return new PNIBuf(arena, v.MEMORY);
    }

    public static PNIBuf of(Arena arena, FloatArray v) {
        if (v == null) return null;
        return new PNIBuf(arena, v.MEMORY);
    }

    public static PNIBuf of(Arena arena, IntArray v) {
        if (v == null) return null;
        return new PNIBuf(arena, v.MEMORY);
    }

    public static PNIBuf of(Arena arena, LongArray v) {
        if (v == null) return null;
        return new PNIBuf(arena, v.MEMORY);
    }

    public static PNIBuf of(Arena arena, ShortArray v) {
        if (v == null) return null;
        return new PNIBuf(arena, v.MEMORY);
    }

    public static PNIBuf of(Arena arena, RefArray<?> v) {
        if (v == null) return null;
        return new PNIBuf(arena, v.MEMORY);
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

    public static ByteBuffer getByteBuffer(MemorySegment mem) {
        if (mem == null) {
            return null;
        }
        mem = mem.reinterpret(LAYOUT.byteSize());
        var seg = (MemorySegment) bufVH.get(mem);
        if (seg.address() == 0) {
            return null;
        }
        long len = (long) lenVH.get(mem);
        return seg.reinterpret(len).asByteBuffer();
    }
}
