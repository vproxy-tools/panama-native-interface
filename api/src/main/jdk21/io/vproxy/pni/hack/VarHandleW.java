package io.vproxy.pni.hack;

import java.lang.foreign.MemorySegment;
import java.lang.invoke.VarHandle;

public class VarHandleW {
    public static VarHandleW of(VarHandle vh) {
        return new VarHandleW(vh);
    }

    private final VarHandle vh;

    private VarHandleW(VarHandle vh) {
        this.vh = vh;
    }

    public int getInt(MemorySegment seg) {
        return (int) vh.get(seg);
    }

    public long getLong(MemorySegment seg) {
        return (long) vh.get(seg);
    }

    public float getFloat(MemorySegment seg) {
        return (float) vh.get(seg);
    }

    public double getDouble(MemorySegment seg) {
        return (double) vh.get(seg);
    }

    public byte getByte(MemorySegment seg) {
        return (byte) vh.get(seg);
    }

    public short getShort(MemorySegment seg) {
        return (short) vh.get(seg);
    }

    public char getChar(MemorySegment seg) {
        return (char) vh.get(seg);
    }

    public boolean getBool(MemorySegment seg) {
        return (boolean) vh.get(seg);
    }

    public MemorySegment getMemorySegment(MemorySegment seg) {
        return (MemorySegment) vh.get(seg);
    }

    public void set(MemorySegment seg, int value) {
        vh.set(seg, value);
    }

    public void set(MemorySegment seg, long value) {
        vh.set(seg, value);
    }

    public void set(MemorySegment seg, float value) {
        vh.set(seg, value);
    }

    public void set(MemorySegment seg, double value) {
        vh.set(seg, value);
    }

    public void set(MemorySegment seg, byte value) {
        vh.set(seg, value);
    }

    public void set(MemorySegment seg, short value) {
        vh.set(seg, value);
    }

    public void set(MemorySegment seg, char value) {
        vh.set(seg, value);
    }

    public void set(MemorySegment seg, boolean value) {
        vh.set(seg, value);
    }

    public void set(MemorySegment seg, MemorySegment value) {
        vh.set(seg, value);
    }
}
