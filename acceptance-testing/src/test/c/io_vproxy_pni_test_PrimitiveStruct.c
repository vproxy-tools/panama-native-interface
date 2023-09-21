#include "io_vproxy_pni_test_PrimitiveStruct.h"
#include <stdio.h>

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PrimitiveStruct_func1(PNIEnv_void * env, PrimitiveStruct * p, int8_t aByte, uint8_t unsignedByte, int32_t aInt, uint32_t unsignedInt, int64_t aLong, uint64_t unsignedLong, int16_t aShort, uint16_t unsignedShort) {
    JavaCritical_io_vproxy_pni_test_PrimitiveStruct_func1Critical(p, aByte, unsignedByte, aInt, unsignedInt, aLong, unsignedLong, aShort, unsignedShort);
    return 0;
}

JNIEXPORT void JNICALL JavaCritical_io_vproxy_pni_test_PrimitiveStruct_func1Critical(PrimitiveStruct * p, int8_t aByte, uint8_t unsignedByte, int32_t aInt, uint32_t unsignedInt, int64_t aLong, uint64_t unsignedLong, int16_t aShort, uint16_t unsignedShort) {
    printf("aByte         = %"PRIi8"\n", aByte);
    printf("unsignedByte  = %"PRIu8"\n", unsignedByte);
    printf("aInt          = %"PRIi32"\n", aInt);
    printf("unsignedInt   = %"PRIu32"\n", unsignedInt);
    printf("aLong         = %"PRIi64"\n", aLong);
    printf("unsignedLong  = %"PRIu64"\n", unsignedLong);
    printf("aShort        = %"PRIi16"\n", aShort);
    printf("unsignedShort = %"PRIu16"\n", unsignedShort);
    fflush(stdout);

    p->aByte = aByte;
    p->unsignedByte = unsignedByte;
    p->aInt = aInt;
    p->unsignedInt = unsignedInt;
    p->aLong = aLong;
    p->unsignedLong = unsignedLong;
    p->aShort = aShort;
    p->unsignedShort = unsignedShort;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PrimitiveStruct_func2(PNIEnv_void * env, PrimitiveStruct * p, uint16_t aChar, double aDouble, float aFloat, uint8_t aBoolean) {
    JavaCritical_io_vproxy_pni_test_PrimitiveStruct_func2Critical(p, aChar, aDouble, aFloat, aBoolean);
    return 0;
}

JNIEXPORT void JNICALL JavaCritical_io_vproxy_pni_test_PrimitiveStruct_func2Critical(PrimitiveStruct * p, uint16_t aChar, double aDouble, float aFloat, uint8_t aBoolean) {
    printf("aChar    = %c\n", aChar);
    printf("aDouble  = %f\n", aDouble);
    printf("aFloat   = %f\n", aFloat);
    printf("aBoolean = %u\n", aBoolean);
    fflush(stdout);

    p->aChar = aChar;
    p->aDouble = aDouble;
    p->aFloat = aFloat;
    p->aBoolean = aBoolean;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PrimitiveStruct_func3(PNIEnv_void * env, PrimitiveStruct * p, PNIBuf_byte * byteArray, PNIBuf_ubyte * unsignedByteArray, PNIBuf_int * intArray, PNIBuf_uint * unsignedIntArray, PNIBuf_long * longArray, PNIBuf_ulong * unsignedLongArray, PNIBuf_short * shortArray, PNIBuf_ushort * unsignedShortArray) {
    JavaCritical_io_vproxy_pni_test_PrimitiveStruct_func3Critical(p, byteArray, unsignedByteArray, intArray, unsignedIntArray, longArray, unsignedLongArray, shortArray, unsignedShortArray);
    return 0;
}

JNIEXPORT void JNICALL JavaCritical_io_vproxy_pni_test_PrimitiveStruct_func3Critical(PrimitiveStruct * p, PNIBuf_byte * byteArray, PNIBuf_ubyte * unsignedByteArray, PNIBuf_int * intArray, PNIBuf_uint * unsignedIntArray, PNIBuf_long * longArray, PNIBuf_ulong * unsignedLongArray, PNIBuf_short * shortArray, PNIBuf_ushort * unsignedShortArray) {
    printf("byteArray = [");
    for (int i = 0; i < bytePNIArrayLen(byteArray); ++i) {
        if (i != 0) {
            printf(", ");
        }
        printf("%"PRIi8, byteArray->array[i]);
        p->byteArray[i] = byteArray->array[i];
    }
    printf("]\n");
    p->byteArrayPointer.array = byteArray->array;
    p->byteArrayPointer.bufLen = byteArray->bufLen;

    printf("unsignedByteArray = [");
    for (int i = 0; i < ubytePNIArrayLen(unsignedByteArray); ++i) {
        if (i != 0) {
            printf(", ");
        }
        printf("%"PRIu8, unsignedByteArray->array[i]);
        p->unsignedByteArray[i] = unsignedByteArray->array[i];
    }
    printf("]\n");
    p->unsignedByteArrayPointer.array = unsignedByteArray->array;
    p->unsignedByteArrayPointer.bufLen = unsignedByteArray->bufLen;

    printf("intArray = [");
    for (int i = 0; i < intPNIArrayLen(intArray); ++i) {
        if (i != 0) {
            printf(", ");
        }
        printf("%"PRIi32, intArray->array[i]);
        p->intArray[i] = intArray->array[i];
    }
    printf("]\n");
    p->intArrayPointer.array = intArray->array;
    p->intArrayPointer.bufLen = intArray->bufLen;

    printf("unsignedIntArray = [");
    for (int i = 0; i < uintPNIArrayLen(unsignedIntArray); ++i) {
        if (i != 0) {
            printf(", ");
        }
        printf("%"PRIu32, unsignedIntArray->array[i]);
        p->unsignedIntArray[i] = unsignedIntArray->array[i];
    }
    printf("]\n");
    p->unsignedIntArrayPointer.array = unsignedIntArray->array;
    p->unsignedIntArrayPointer.bufLen = unsignedIntArray->bufLen;

    printf("longArray = [");
    for (int i = 0; i < longPNIArrayLen(longArray); ++i) {
        if (i != 0) {
            printf(", ");
        }
        printf("%"PRIi64, longArray->array[i]);
        p->longArray[i] = longArray->array[i];
    }
    printf("]\n");
    p->longArrayPointer.array = longArray->array;
    p->longArrayPointer.bufLen = longArray->bufLen;

    printf("unsignedLongArray = [");
    for (int i = 0; i < ulongPNIArrayLen(unsignedLongArray); ++i) {
        if (i != 0) {
            printf(", ");
        }
        printf("%"PRIu64, unsignedLongArray->array[i]);
        p->unsignedLongArray[i] = unsignedLongArray->array[i];
    }
    printf("]\n");
    p->unsignedLongArrayPointer.array = unsignedLongArray->array;
    p->unsignedLongArrayPointer.bufLen = unsignedLongArray->bufLen;

    printf("shortArray = [");
    for (int i = 0; i < shortPNIArrayLen(shortArray); ++i) {
        if (i != 0) {
            printf(", ");
        }
        printf("%"PRIi16, shortArray->array[i]);
        p->shortArray[i] = shortArray->array[i];
    }
    printf("]\n");
    p->shortArrayPointer.array = shortArray->array;
    p->shortArrayPointer.bufLen = shortArray->bufLen;

    printf("unsignedShortArray = [");
    for (int i = 0; i < ushortPNIArrayLen(unsignedShortArray); ++i) {
        if (i != 0) {
            printf(", ");
        }
        printf("%"PRIu16, unsignedShortArray->array[i]);
        p->unsignedShortArray[i] = unsignedShortArray->array[i];
    }
    printf("]\n");
    p->unsignedShortArrayPointer.array = unsignedShortArray->array;
    p->unsignedShortArrayPointer.bufLen = unsignedShortArray->bufLen;

    fflush(stdout);
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PrimitiveStruct_func4(PNIEnv_void * env, PrimitiveStruct * p, PNIBuf_char * charArray, PNIBuf_double * doubleArray, PNIBuf_float * floatArray, PNIBuf_bool * booleanArray) {
    JavaCritical_io_vproxy_pni_test_PrimitiveStruct_func4Critical(p, charArray, doubleArray, floatArray, booleanArray);
    return 0;
}

JNIEXPORT void JNICALL JavaCritical_io_vproxy_pni_test_PrimitiveStruct_func4Critical(PrimitiveStruct * p, PNIBuf_char * charArray, PNIBuf_double * doubleArray, PNIBuf_float * floatArray, PNIBuf_bool * booleanArray) {
    printf("charArray = [");
    for (int i = 0; i < charPNIArrayLen(charArray); ++i) {
        if (i != 0) {
            printf(", ");
        }
        printf("%c", charArray->array[i]);
        p->charArray[i] = charArray->array[i];
    }
    printf("]\n");
    p->charArrayPointer.array = charArray->array;
    p->charArrayPointer.bufLen = charArray->bufLen;

    printf("doubleArray = [");
    for (int i = 0; i < doublePNIArrayLen(doubleArray); ++i) {
        if (i != 0) {
            printf(", ");
        }
        printf("%f", doubleArray->array[i]);
        p->doubleArray[i] = doubleArray->array[i];
    }
    printf("]\n");
    p->doubleArrayPointer.array = doubleArray->array;
    p->doubleArrayPointer.bufLen = doubleArray->bufLen;

    printf("floatArray = [");
    for (int i = 0; i < floatPNIArrayLen(floatArray); ++i) {
        if (i != 0) {
            printf(", ");
        }
        printf("%f", floatArray->array[i]);
        p->floatArray[i] = floatArray->array[i];
    }
    printf("]\n");
    p->floatArrayPointer.array = floatArray->array;
    p->floatArrayPointer.bufLen = floatArray->bufLen;

    printf("booleanArray = [");
    for (int i = 0; i < boolPNIArrayLen(booleanArray); ++i) {
        if (i != 0) {
            printf(", ");
        }
        printf("%u", booleanArray->array[i]);
        p->booleanArray[i] = booleanArray->array[i];
    }
    printf("]\n");
    p->booleanArrayPointer.array = booleanArray->array;
    p->booleanArrayPointer.bufLen = booleanArray->bufLen;

    fflush(stdout);
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PrimitiveStruct_retrieveByte(PNIEnv_byte * env, PrimitiveStruct * self) {
    env->return_ = JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveByteCritical(self);
    return 0;
}

JNIEXPORT int8_t JNICALL JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveByteCritical(PrimitiveStruct * self) {
    return self->aByte;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedByte(PNIEnv_byte * env, PrimitiveStruct * self) {
    env->return_ = JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedByteCritical(self);
    return 0;
}

JNIEXPORT int8_t JNICALL JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedByteCritical(PrimitiveStruct * self) {
    return self->unsignedByte;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PrimitiveStruct_retrieveChar(PNIEnv_char * env, PrimitiveStruct * self) {
    env->return_ = JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveCharCritical(self);
    return 0;
}

JNIEXPORT uint16_t JNICALL JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveCharCritical(PrimitiveStruct * self) {
    return self->aChar;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PrimitiveStruct_retrieveDouble(PNIEnv_double * env, PrimitiveStruct * self) {
    env->return_ = JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveDoubleCritical(self);
    return 0;
}

JNIEXPORT double JNICALL JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveDoubleCritical(PrimitiveStruct * self) {
    return self->aDouble;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PrimitiveStruct_retrieveFloat(PNIEnv_float * env, PrimitiveStruct * self) {
    env->return_ = JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveFloatCritical(self);
    return 0;
}

JNIEXPORT float JNICALL JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveFloatCritical(PrimitiveStruct * self) {
    return self->aFloat;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PrimitiveStruct_retrieveInt(PNIEnv_int * env, PrimitiveStruct * self) {
    env->return_ = JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveIntCritical(self);
    return 0;
}

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveIntCritical(PrimitiveStruct * self) {
    return self->aInt;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedInt(PNIEnv_int * env, PrimitiveStruct * self) {
    env->return_ = JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedIntCritical(self);
    return 0;
}

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedIntCritical(PrimitiveStruct * self) {
    return self->unsignedInt;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PrimitiveStruct_retrieveLong(PNIEnv_long * env, PrimitiveStruct * self) {
    env->return_ = JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveLongCritical(self);
    return 0;
}

JNIEXPORT int64_t JNICALL JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveLongCritical(PrimitiveStruct * self) {
    return self->aLong;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedLong(PNIEnv_long * env, PrimitiveStruct * self) {
    env->return_ = JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedLongCritical(self);
    return 0;
}

JNIEXPORT int64_t JNICALL JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedLongCritical(PrimitiveStruct * self) {
    return self->unsignedLong;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PrimitiveStruct_retrieveShort(PNIEnv_short * env, PrimitiveStruct * self) {
    env->return_ = JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveShortCritical(self);
    return 0;
}

JNIEXPORT int16_t JNICALL JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveShortCritical(PrimitiveStruct * self) {
    return self->aShort;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedShort(PNIEnv_short * env, PrimitiveStruct * self) {
    env->return_ = JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedShortCritical(self);
    return 0;
}

JNIEXPORT int16_t JNICALL JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedShortCritical(PrimitiveStruct * self) {
    return self->unsignedShort;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PrimitiveStruct_retrieveBoolean(PNIEnv_bool * env, PrimitiveStruct * self) {
    env->return_ = JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveBooleanCritical(self);
    return 0;
}

JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveBooleanCritical(PrimitiveStruct * self) {
    return self->aBoolean;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PrimitiveStruct_retrieveByteArray(PNIEnv_buf_byte * env, PrimitiveStruct * p) {
    JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveByteArrayCritical(p, &env->return_);
    return 0;
}

JNIEXPORT PNIBuf_byte * JNICALL JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveByteArrayCritical(PrimitiveStruct * p, PNIBuf_byte * return_) {
    return_->array = p->byteArray;
    return_->bufLen = bytePNIBufLen(5);
    return return_;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedByteArray(PNIEnv_buf_byte * env, PrimitiveStruct * p) {
    JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedByteArrayCritical(p, &env->return_);
    return 0;
}

JNIEXPORT PNIBuf_byte * JNICALL JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedByteArrayCritical(PrimitiveStruct * p, PNIBuf_byte * return_) {
    return_->array = (int8_t*)p->unsignedByteArray;
    return_->bufLen = ubytePNIBufLen(6);
    return return_;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PrimitiveStruct_retrieveCharArray(PNIEnv_buf_char * env, PrimitiveStruct * p) {
    JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveCharArrayCritical(p, &env->return_);
    return 0;
}

JNIEXPORT PNIBuf_char * JNICALL JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveCharArrayCritical(PrimitiveStruct * p, PNIBuf_char * return_) {
    return_->array = p->charArray;
    return_->bufLen = charPNIBufLen(7);
    return return_;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PrimitiveStruct_retrieveDoubleArray(PNIEnv_buf_double * env, PrimitiveStruct * p) {
    JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveDoubleArrayCritical(p, &env->return_);
    return 0;
}

JNIEXPORT PNIBuf_double * JNICALL JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveDoubleArrayCritical(PrimitiveStruct * p, PNIBuf_double * return_) {
    return_->array = p->doubleArray;
    return_->bufLen = doublePNIBufLen(8);
    return return_;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PrimitiveStruct_retrieveFloatArray(PNIEnv_buf_float * env, PrimitiveStruct * p) {
    JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveFloatArrayCritical(p, &env->return_);
    return 0;
}

JNIEXPORT PNIBuf_float * JNICALL JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveFloatArrayCritical(PrimitiveStruct * p, PNIBuf_float * return_) {
    return_->array = p->floatArray;
    return_->bufLen = floatPNIBufLen(9);
    return return_;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PrimitiveStruct_retrieveIntArray(PNIEnv_buf_int * env, PrimitiveStruct * p) {
    JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveIntArrayCritical(p, &env->return_);
    return 0;
}

JNIEXPORT PNIBuf_int * JNICALL JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveIntArrayCritical(PrimitiveStruct * p, PNIBuf_int * return_) {
    return_->array = p->intArray;
    return_->bufLen = intPNIBufLen(10);
    return return_;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedIntArray(PNIEnv_buf_int * env, PrimitiveStruct * p) {
    JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedIntArrayCritical(p, &env->return_);
    return 0;
}

JNIEXPORT PNIBuf_int * JNICALL JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedIntArrayCritical(PrimitiveStruct * p, PNIBuf_int * return_) {
    return_->array = (int32_t*)p->unsignedIntArray;
    return_->bufLen = uintPNIBufLen(11);
    return return_;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PrimitiveStruct_retrieveLongArray(PNIEnv_buf_long * env, PrimitiveStruct * p) {
    JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveLongArrayCritical(p, &env->return_);
    return 0;
}

JNIEXPORT PNIBuf_long * JNICALL JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveLongArrayCritical(PrimitiveStruct * p, PNIBuf_long * return_) {
    return_->array = p->longArray;
    return_->bufLen = longPNIBufLen(12);
    return return_;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedLongArray(PNIEnv_buf_long * env, PrimitiveStruct * p) {
    JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedLongArrayCritical(p, &env->return_);
    return 0;
}

JNIEXPORT PNIBuf_long * JNICALL JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedLongArrayCritical(PrimitiveStruct * p, PNIBuf_long * return_) {
    return_->array = (int64_t*)p->unsignedLongArray;
    return_->bufLen = ulongPNIBufLen(13);
    return return_;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PrimitiveStruct_retrieveShortArray(PNIEnv_buf_short * env, PrimitiveStruct * p) {
    JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveShortArrayCritical(p, &env->return_);
    return 0;
}

JNIEXPORT PNIBuf_short * JNICALL JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveShortArrayCritical(PrimitiveStruct * p, PNIBuf_short * return_) {
    return_->array = p->shortArray;
    return_->bufLen = shortPNIBufLen(14);
    return return_;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedShortArray(PNIEnv_buf_short * env, PrimitiveStruct * p) {
    JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedShortArrayCritical(p, &env->return_);
    return 0;
}

JNIEXPORT PNIBuf_short * JNICALL JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedShortArrayCritical(PrimitiveStruct * p, PNIBuf_short * return_) {
    return_->array = (int16_t*)p->unsignedShortArray;
    return_->bufLen = ushortPNIBufLen(15);
    return return_;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PrimitiveStruct_retrieveBooleanArray(PNIEnv_buf_bool * env, PrimitiveStruct * p) {
    JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveBooleanArrayCritical(p, &env->return_);
    return 0;
}

JNIEXPORT PNIBuf_bool * JNICALL JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveBooleanArrayCritical(PrimitiveStruct * p, PNIBuf_bool * return_) {
    return_->array = p->booleanArray;
    return_->bufLen = boolPNIBufLen(16);
    return return_;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PrimitiveStruct_retrieveByteArrayPointer(PNIEnv_buf_byte * env, PrimitiveStruct * p) {
    JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveByteArrayPointerCritical(p, &env->return_);
    return 0;
}

JNIEXPORT PNIBuf_byte * JNICALL JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveByteArrayPointerCritical(PrimitiveStruct * p, PNIBuf_byte * return_) {
    *return_ = p->byteArrayPointer;
    return return_;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedByteArrayPointer(PNIEnv_buf_byte * env, PrimitiveStruct * p) {
    JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedByteArrayPointerCritical(p, &env->return_);
    return 0;
}

JNIEXPORT PNIBuf_byte * JNICALL JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedByteArrayPointerCritical(PrimitiveStruct * p, PNIBuf_byte * return_) {
    *return_ = *(PNIBuf_byte*)&p->unsignedByteArrayPointer;
    return return_;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PrimitiveStruct_retrieveCharArrayPointer(PNIEnv_buf_char * env, PrimitiveStruct * p) {
    JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveCharArrayPointerCritical(p, &env->return_);
    return 0;
}

JNIEXPORT PNIBuf_char * JNICALL JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveCharArrayPointerCritical(PrimitiveStruct * p, PNIBuf_char * return_) {
    *return_ = p->charArrayPointer;
    return return_;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PrimitiveStruct_retrieveDoubleArrayPointer(PNIEnv_buf_double * env, PrimitiveStruct * p) {
    JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveDoubleArrayPointerCritical(p, &env->return_);
    return 0;
}

JNIEXPORT PNIBuf_double * JNICALL JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveDoubleArrayPointerCritical(PrimitiveStruct * p, PNIBuf_double * return_) {
    *return_ = p->doubleArrayPointer;
    return return_;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PrimitiveStruct_retrieveFloatArrayPointer(PNIEnv_buf_float * env, PrimitiveStruct * p) {
    JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveFloatArrayPointerCritical(p, &env->return_);
    return 0;
}

JNIEXPORT PNIBuf_float * JNICALL JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveFloatArrayPointerCritical(PrimitiveStruct * p, PNIBuf_float * return_) {
    *return_ = p->floatArrayPointer;
    return return_;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PrimitiveStruct_retrieveIntArrayPointer(PNIEnv_buf_int * env, PrimitiveStruct * p) {
    JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveIntArrayPointerCritical(p, &env->return_);
    return 0;
}

JNIEXPORT PNIBuf_int * JNICALL JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveIntArrayPointerCritical(PrimitiveStruct * p, PNIBuf_int * return_) {
    *return_ = p->intArrayPointer;
    return return_;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedIntArrayPointer(PNIEnv_buf_int * env, PrimitiveStruct * p) {
    JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedIntArrayPointerCritical(p, &env->return_);
    return 0;
}

JNIEXPORT PNIBuf_int * JNICALL JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedIntArrayPointerCritical(PrimitiveStruct * p, PNIBuf_int * return_) {
    *return_ = *(PNIBuf_int*)&p->unsignedIntArrayPointer;
    return return_;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PrimitiveStruct_retrieveLongArrayPointer(PNIEnv_buf_long * env, PrimitiveStruct * p) {
    JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveLongArrayPointerCritical(p, &env->return_);
    return 0;
}

JNIEXPORT PNIBuf_long * JNICALL JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveLongArrayPointerCritical(PrimitiveStruct * p, PNIBuf_long * return_) {
    *return_ = p->longArrayPointer;
    return return_;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedLongArrayPointer(PNIEnv_buf_long * env, PrimitiveStruct * p) {
    JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedLongArrayPointerCritical(p, &env->return_);
    return 0;
}

JNIEXPORT PNIBuf_long * JNICALL JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedLongArrayPointerCritical(PrimitiveStruct * p, PNIBuf_long * return_) {
    *return_ = *(PNIBuf_long*)&p->unsignedLongArrayPointer;
    return return_;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PrimitiveStruct_retrieveShortArrayPointer(PNIEnv_buf_short * env, PrimitiveStruct * p) {
    JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveShortArrayPointerCritical(p, &env->return_);
    return 0;
}

JNIEXPORT PNIBuf_short * JNICALL JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveShortArrayPointerCritical(PrimitiveStruct * p, PNIBuf_short * return_) {
    *return_ = p->shortArrayPointer;
    return return_;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedShortArrayPointer(PNIEnv_buf_short * env, PrimitiveStruct * p) {
    JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedShortArrayPointerCritical(p, &env->return_);
    return 0;
}

JNIEXPORT PNIBuf_short * JNICALL JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedShortArrayPointerCritical(PrimitiveStruct * p, PNIBuf_short * return_) {
    *return_ = *(PNIBuf_short*)&p->unsignedShortArrayPointer;
    return return_;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PrimitiveStruct_retrieveBooleanArrayPointer(PNIEnv_buf_bool * env, PrimitiveStruct * p) {
    JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveBooleanArrayPointerCritical(p, &env->return_);
    return 0;
}

JNIEXPORT PNIBuf_bool * JNICALL JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveBooleanArrayPointerCritical(PrimitiveStruct * p, PNIBuf_bool * return_) {
    *return_ = p->booleanArrayPointer;
    return return_;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PrimitiveStruct_checkPointerSetToNonNull(PNIEnv_bool * env, PrimitiveStruct * p) {
    env->return_ = JavaCritical_io_vproxy_pni_test_PrimitiveStruct_checkPointerSetToNonNullCritical(p);
    return 0;
}

JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_pni_test_PrimitiveStruct_checkPointerSetToNonNullCritical(PrimitiveStruct * p) {
    return         p->byteArrayPointer.buf != NULL
                && p->unsignedByteArrayPointer.buf != NULL
                && p->charArrayPointer.buf != NULL
                && p->doubleArrayPointer.buf != NULL
                && p->floatArrayPointer.buf != NULL
                && p->intArrayPointer.buf != NULL
                && p->unsignedIntArrayPointer.buf != NULL
                && p->longArrayPointer.buf != NULL
                && p->unsignedLongArrayPointer.buf != NULL
                && p->shortArrayPointer.buf != NULL
                && p->unsignedShortArrayPointer.buf != NULL
                && p->booleanArrayPointer.buf != NULL;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PrimitiveStruct_checkPointerSetToNull(PNIEnv_bool * env, PrimitiveStruct * p) {
    env->return_ = JavaCritical_io_vproxy_pni_test_PrimitiveStruct_checkPointerSetToNullCritical(p);
    return 0;
}

JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_pni_test_PrimitiveStruct_checkPointerSetToNullCritical(PrimitiveStruct * p) {
    return         p->byteArrayPointer.buf == NULL
                && p->unsignedByteArrayPointer.buf == NULL
                && p->charArrayPointer.buf == NULL
                && p->doubleArrayPointer.buf == NULL
                && p->floatArrayPointer.buf == NULL
                && p->intArrayPointer.buf == NULL
                && p->unsignedIntArrayPointer.buf == NULL
                && p->longArrayPointer.buf == NULL
                && p->unsignedLongArrayPointer.buf == NULL
                && p->shortArrayPointer.buf == NULL
                && p->unsignedShortArrayPointer.buf == NULL
                && p->booleanArrayPointer.buf == NULL;
}
