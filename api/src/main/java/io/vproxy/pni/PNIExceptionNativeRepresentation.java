package io.vproxy.pni;

import io.vproxy.pni.exception.PNIException;

import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.VarHandle;

public class PNIExceptionNativeRepresentation {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        ValueLayout.ADDRESS_UNALIGNED.withName("type"),
        MemoryLayout.sequenceLayout(4096, ValueLayout.JAVA_BYTE).withName("message"),
        ValueLayout.JAVA_INT_UNALIGNED.withName("errno_"),
        MemoryLayout.sequenceLayout(4L, ValueLayout.JAVA_BYTE) /* padding */
    );
    public final MemorySegment MEMORY;

    public PNIExceptionNativeRepresentation(MemorySegment MEMORY) {
        this.MEMORY = MEMORY;
    }

    private static final VarHandle typeVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("type")
    );

    private String _type = null;
    private Class<? extends Throwable> _typeClass = null;

    public String type() {
        if (_type != null) {
            return _type;
        }
        var type = (MemorySegment) typeVH.get(MEMORY);
        if (type.address() == 0) {
            return null;
        }
        _type = type.reinterpret(Integer.MAX_VALUE).getUtf8String(0);
        return _type;
    }

    public Class<? extends Throwable> typeClass() {
        if (_typeClass != null) {
            return _typeClass;
        }

        var t = type();
        var msg = message();
        if (t == null) {
            throw new PNIException(msg);
        }
        Class<?> cls;
        try {
            cls = Class.forName(t);
        } catch (ClassNotFoundException e) {
            throw new PNIException("exception type " + t + " not found, original error message: " + msg, e);
        }
        if (!Throwable.class.isAssignableFrom(cls)) {
            throw new PNIException("exception type " + t + " is not Throwable, original error message: " + msg);
        }
        //noinspection unchecked
        _typeClass = (Class<? extends Throwable>) cls;
        return _typeClass;
    }

    public String message() {
        return MEMORY.getUtf8String(ValueLayout.ADDRESS.byteSize());
    }

    private static final VarHandle errnoVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("errno_")
    );

    public int errno() {
        return (int) errnoVH.get(this.MEMORY);
    }

    public void reset() {
        _type = null;
        _typeClass = null;
        typeVH.set(MEMORY, MemorySegment.NULL);
        errnoVH.set(MEMORY, 0);
    }
}
