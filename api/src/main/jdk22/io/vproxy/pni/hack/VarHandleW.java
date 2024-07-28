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
        return (int) vh.get(seg, 0);
    }

    public long getLong(MemorySegment seg) {
        return (long) vh.get(seg, 0);
    }

    public float getFloat(MemorySegment seg) {
        return (float) vh.get(seg, 0);
    }

    public double getDouble(MemorySegment seg) {
        return (double) vh.get(seg, 0);
    }

    public byte getByte(MemorySegment seg) {
        return (byte) vh.get(seg, 0);
    }

    public short getShort(MemorySegment seg) {
        return (short) vh.get(seg, 0);
    }

    public char getChar(MemorySegment seg) {
        return (char) vh.get(seg, 0);
    }

    public boolean getBool(MemorySegment seg) {
        return (boolean) vh.get(seg, 0);
    }

    public MemorySegment getMemorySegment(MemorySegment seg) {
        return (MemorySegment) vh.get(seg, 0);
    }

    public void set(MemorySegment seg, int value) {
        vh.set(seg, 0, value);
    }

    public void set(MemorySegment seg, long value) {
        vh.set(seg, 0, value);
    }

    public void set(MemorySegment seg, float value) {
        vh.set(seg, 0, value);
    }

    public void set(MemorySegment seg, double value) {
        vh.set(seg, 0, value);
    }

    public void set(MemorySegment seg, byte value) {
        vh.set(seg, 0, value);
    }

    public void set(MemorySegment seg, short value) {
        vh.set(seg, 0, value);
    }

    public void set(MemorySegment seg, char value) {
        vh.set(seg, 0, value);
    }

    public void set(MemorySegment seg, boolean value) {
        vh.set(seg, 0, value);
    }

    public void set(MemorySegment seg, MemorySegment value) {
        vh.set(seg, 0, value);
    }
}
