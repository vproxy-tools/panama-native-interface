package io.vproxy.pni.test;

import io.vproxy.pni.annotation.Impl;
import io.vproxy.pni.annotation.Len;
import io.vproxy.pni.annotation.Struct;

import java.lang.foreign.MemorySegment;

@Struct
public abstract class PNIPointerArrayField {
    @Len(3) MemorySegment[] pointerArray;
    MemorySegment[] pointerArrayPointer;

    @Impl(
        // language="c"
        c = """
            for (int i = 0, len = ptrPNIArrayLen(a); i < len; ++i) {
                self->pointerArray[i] = a->array[i];
            }
            self->pointerArrayPointer = *a;
            return 0;
            """
    )
    abstract void set(MemorySegment[] a);

    @Impl(
        // language="c"
        c = """
            env->return_ = self->pointerArrayPointer;
            return 0;
            """
    )
    abstract MemorySegment[] getPtrField();

    @Impl(
        // language="c"
        c = """
            env->return_.bufLen = ptrPNIBufLen(3);
            env->return_.buf = &self->pointerArray;
            return 0;
            """
    )
    abstract MemorySegment[] getLenField();
}
