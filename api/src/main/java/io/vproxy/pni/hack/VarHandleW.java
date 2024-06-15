package io.vproxy.pni.hack;

import java.lang.foreign.MemorySegment;
import java.lang.invoke.VarHandle;

public interface VarHandleW {
    static VarHandleW of(VarHandle vh) {
        var version = Runtime.version().version().getFirst();
        if (version < 22) {
            return new VarHandleW21Impl(vh);
        } else {
            return new VarHandleW22Impl(vh);
        }
    }

    static Class<? extends VarHandleW> implClass() {
        var version = Runtime.version().version().getFirst();
        if (version < 22) {
            return VarHandleW21Impl.class;
        } else {
            return VarHandleW22Impl.class;
        }
    }

    int getInt(MemorySegment seg);

    long getLong(MemorySegment seg);

    float getFloat(MemorySegment seg);

    double getDouble(MemorySegment seg);

    byte getByte(MemorySegment seg);

    short getShort(MemorySegment seg);

    char getChar(MemorySegment seg);

    boolean getBool(MemorySegment seg);

    MemorySegment getMemorySegment(MemorySegment seg);

    void set(MemorySegment seg, int value);

    void set(MemorySegment seg, long value);

    void set(MemorySegment seg, float value);

    void set(MemorySegment seg, double value);

    void set(MemorySegment seg, byte value);

    void set(MemorySegment seg, short value);

    void set(MemorySegment seg, char value);

    void set(MemorySegment seg, boolean value);

    void set(MemorySegment seg, MemorySegment value);
}
