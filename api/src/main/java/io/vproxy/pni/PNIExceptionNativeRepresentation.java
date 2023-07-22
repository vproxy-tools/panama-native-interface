package io.vproxy.pni;

import io.vproxy.pni.exception.PNIException;

import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.VarHandle;

public class PNIExceptionNativeRepresentation {
    public static final MemoryLayout layout = MemoryLayout.structLayout(
        ValueLayout.ADDRESS_UNALIGNED.withName("type"),
        MemoryLayout.sequenceLayout(4096, ValueLayout.JAVA_BYTE).withName("message")
    );
    private final MemorySegment seg;

    public PNIExceptionNativeRepresentation(MemorySegment seg) {
        this.seg = seg;
    }

    private static final VarHandle typeVH = layout.varHandle(
        MemoryLayout.PathElement.groupElement("type")
    );

    private String _type = null;
    private Class<? extends Throwable> _typeClass = null;

    public String type() {
        if (_type != null) {
            return _type;
        }
        var type = (MemorySegment) typeVH.get(seg);
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
        return seg.getUtf8String(ValueLayout.ADDRESS.byteSize());
    }

    public void reset() {
        _type = null;
        _typeClass = null;
        typeVH.set(seg, MemorySegment.NULL);
    }
}
