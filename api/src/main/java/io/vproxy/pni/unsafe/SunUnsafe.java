package io.vproxy.pni.unsafe;

import sun.misc.Unsafe;

import java.lang.foreign.MemorySegment;
import java.lang.reflect.Field;

public class SunUnsafe {
    private static final Unsafe U;

    private static final int DEFAULT_ALLOCATION_ALIGNMENT;

    static {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            U = (Unsafe) field.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Reflection failure: get unsafe failed", e);
        }

        var addrs = new long[1024];
        for (int i = 0; i < addrs.length; ++i) {
            addrs[i] = U.allocateMemory(1);
        }
        int assumedAlignment = 1;
        defaultAlignCheckLoop:
        while (true) {
            int foo = assumedAlignment * 2;
            for (var a : addrs) {
                if (a % foo != 0) {
                    break defaultAlignCheckLoop;
                }
            }
            assumedAlignment = foo;
        }
        for (var a : addrs) {
            U.freeMemory(a);
        }
        DEFAULT_ALLOCATION_ALIGNMENT = assumedAlignment;
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

    public static int getDefaultAllocationAlignment() {
        return DEFAULT_ALLOCATION_ALIGNMENT;
    }
}
