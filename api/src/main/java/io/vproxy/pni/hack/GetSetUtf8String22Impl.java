package io.vproxy.pni.hack;

import java.lang.foreign.MemorySegment;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

class GetSetUtf8String22Impl implements GetSetUtf8String {
    private static final MethodHandle getStringMH;
    private static final MethodHandle setStringMH;

    static {
        var lookup = MethodHandles.lookup();
        MethodHandle _getStringMH;
        try {
            var methodType = MethodType.methodType(String.class, long.class);
            _getStringMH = lookup.findVirtual(MemorySegment.class, "getString", methodType);
        } catch (Throwable t) {
            throw new RuntimeException("cannot find MemorySegment#getString(int)");
        }
        MethodHandle _setStringMH;
        try {
            var methodType = MethodType.methodType(void.class, long.class, String.class);
            _setStringMH = lookup.findVirtual(MemorySegment.class, "setString", methodType);
        } catch (Throwable t) {
            throw new RuntimeException("cannot find MemorySegment#setString(int)");
        }
        getStringMH = _getStringMH;
        setStringMH = _setStringMH;
    }

    @Override
    public String getUtf8String(MemorySegment seg, long index) {
        try {
            return (String) getStringMH.invoke(seg, index);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setUtf8String(MemorySegment seg, long index, String value) {
        try {
            setStringMH.invoke(seg, index, value);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
