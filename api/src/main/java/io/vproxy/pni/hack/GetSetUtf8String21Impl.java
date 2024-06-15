package io.vproxy.pni.hack;

import java.lang.foreign.MemorySegment;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

class GetSetUtf8String21Impl implements GetSetUtf8String {
    private static final MethodHandle getUtf8StringMH;
    private static final MethodHandle setUtf8StringMH;

    static {
        var lookup = MethodHandles.lookup();
        MethodHandle _getUtf8StringMH;
        try {
            var methodType = MethodType.methodType(String.class, long.class);
            _getUtf8StringMH = lookup.findVirtual(MemorySegment.class, "getUtf8String", methodType);
        } catch (Throwable t) {
            throw new RuntimeException("cannot find MemorySegment#getUtf8String(int)");
        }
        MethodHandle _setUtf8StringMH;
        try {
            var methodType = MethodType.methodType(void.class, long.class, String.class);
            _setUtf8StringMH = lookup.findVirtual(MemorySegment.class, "setUtf8String", methodType);
        } catch (Throwable t) {
            throw new RuntimeException("cannot find MemorySegment#setUtf8String(int)");
        }
        getUtf8StringMH = _getUtf8StringMH;
        setUtf8StringMH = _setUtf8StringMH;
    }

    @Override
    public String getUtf8String(MemorySegment seg, long index) {
        try {
            return (String) getUtf8StringMH.invoke(seg, index);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setUtf8String(MemorySegment seg, long index, String value) {
        try {
            setUtf8StringMH.invoke(seg, index, value);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
