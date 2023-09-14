package io.vproxy.pni.unsafe;

import sun.misc.Unsafe;

import java.lang.foreign.MemorySegment;
import java.lang.reflect.Field;

public class SunUnsafe {
    private static final Unsafe U;

    static {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            U = (Unsafe) field.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Reflection failure: get unsafe failed", e);
        }
    }

    public static MemorySegment allocateMemory(long size) {
        return MemorySegment.ofAddress(U.allocateMemory(size)).reinterpret(size);
    }

    public static void setMemory(long address, long bytes, byte value) {
        U.setMemory(address, bytes, value);
    }

    public static void freeMemory(long address) {
        U.freeMemory(address);
    }
}
