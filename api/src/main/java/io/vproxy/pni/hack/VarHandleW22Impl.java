package io.vproxy.pni.hack;

import java.lang.foreign.MemorySegment;
import java.lang.invoke.VarHandle;

class VarHandleW22Impl implements VarHandleW {
    private final VarHandle vh;

    public VarHandleW22Impl(VarHandle vh) {
        this.vh = vh;
    }

    @Override
    public int getInt(MemorySegment seg) {
        return (int) vh.get(seg, 0);
    }

    @Override
    public long getLong(MemorySegment seg) {
        return (long) vh.get(seg, 0);
    }

    @Override
    public float getFloat(MemorySegment seg) {
        return (float) vh.get(seg, 0);
    }

    @Override
    public double getDouble(MemorySegment seg) {
        return (double) vh.get(seg, 0);
    }

    @Override
    public byte getByte(MemorySegment seg) {
        return (byte) vh.get(seg, 0);
    }

    @Override
    public short getShort(MemorySegment seg) {
        return (short) vh.get(seg, 0);
    }

    @Override
    public char getChar(MemorySegment seg) {
        return (char) vh.get(seg, 0);
    }

    @Override
    public boolean getBool(MemorySegment seg) {
        return (boolean) vh.get(seg, 0);
    }

    @Override
    public MemorySegment getMemorySegment(MemorySegment seg) {
        return (MemorySegment) vh.get(seg, 0);
    }

    @Override
    public void set(MemorySegment seg, int value) {
        vh.set(seg, 0, value);
    }

    @Override
    public void set(MemorySegment seg, long value) {
        vh.set(seg, 0, value);
    }

    @Override
    public void set(MemorySegment seg, float value) {
        vh.set(seg, 0, value);
    }

    @Override
    public void set(MemorySegment seg, double value) {
        vh.set(seg, 0, value);
    }

    @Override
    public void set(MemorySegment seg, byte value) {
        vh.set(seg, 0, value);
    }

    @Override
    public void set(MemorySegment seg, short value) {
        vh.set(seg, 0, value);
    }

    @Override
    public void set(MemorySegment seg, char value) {
        vh.set(seg, 0, value);
    }

    @Override
    public void set(MemorySegment seg, boolean value) {
        vh.set(seg, 0, value);
    }

    @Override
    public void set(MemorySegment seg, MemorySegment value) {
        vh.set(seg, 0, value);
    }
}
