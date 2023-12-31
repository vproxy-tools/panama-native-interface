package io.vproxy.pni.test;

import io.vproxy.pni.annotation.Downcall;
import io.vproxy.pni.annotation.Impl;
import io.vproxy.pni.annotation.Raw;
import io.vproxy.pni.annotation.Unsigned;

import java.lang.foreign.MemorySegment;

@Downcall
public interface PNIRawArrays {
    @Impl(
        // language="c"
        c = """
            env->return_ = ((int8_t*) array)[off];
            return 0;
            """
    )
    byte byteArray(@Raw byte[] array, int off);

    @Impl(
        // language="c"
        c = """
            env->return_ = array[off];
            return 0;
            """
    )
    byte unsignedByteArray(@Raw @Unsigned byte[] array, int off);

    @Impl(
        // language="c"
        c = """
            env->return_ = array[off];
            return 0;
            """
    )
    boolean boolArray(@Raw boolean[] array, int off);

    @Impl(
        // language="c"
        c = """
            env->return_ = array[off];
            return 0;
            """
    )
    char charArray(@Raw char[] array, int off);

    @Impl(
        // language="c"
        c = """
            env->return_ = array[off];
            return 0;
            """
    )
    double doubleArray(@Raw double[] array, int off);

    @Impl(
        // language="c"
        c = """
            env->return_ = array[off];
            return 0;
            """
    )
    float floatArray(@Raw float[] array, int off);

    @Impl(
        // language="c"
        c = """
            env->return_ = array[off];
            return 0;
            """
    )
    int intArray(@Raw int[] array, int off);

    @Impl(
        // language="c"
        c = """
            env->return_ = array[off];
            return 0;
            """
    )
    int unsignedIntArray(@Raw @Unsigned int[] array, int off);

    @Impl(
        // language="c"
        c = """
            env->return_ = array[off];
            return 0;
            """
    )
    long longArray(@Raw long[] array, int off);

    @Impl(
        // language="c"
        c = """
            env->return_ = array[off];
            return 0;
            """
    )
    long unsignedLongArray(@Raw @Unsigned long[] array, int off);

    @Impl(
        // language="c"
        c = """
            env->return_ = array[off];
            return 0;
            """
    )
    short shortArray(@Raw short[] array, int off);

    @Impl(
        // language="c"
        c = """
            env->return_ = array[off];
            return 0;
            """
    )
    short unsignedShortArray(@Raw @Unsigned short[] array, int off);

    @Impl(
        // language="c"
        c = """
            env->return_ = array[off];
            return 0;
            """
    )
    MemorySegment pointerArray(@Raw MemorySegment[] array, int off);

    @Impl(
        // language="c"
        c = """
            void ** arr = array->buf;
            env->return_ = arr[off];
            return 0;
            """
    )
    MemorySegment pointerArrayNotRaw(MemorySegment[] array, int off);

    @Impl(
        // language="c"
        c = """
            env->return_ = &array[off];
            return 0;
            """
    )
    PNIObjectStruct structArray(@Raw PNIObjectStruct[] array, int off);

    @Impl(
        // language="c"
        c = """
            ObjectStruct* arr = array->buf;
            env->return_ = &arr[off];
            return 0;
            """
    )
    PNIObjectStruct structArrayNotRaw(PNIObjectStruct[] array, int off);
}
