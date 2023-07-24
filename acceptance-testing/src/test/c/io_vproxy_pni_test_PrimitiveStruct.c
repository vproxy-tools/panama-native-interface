#include "io_vproxy_pni_test_PrimitiveStruct.h"
#include <stdio.h>

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PrimitiveStruct_func1(PNIEnv_void * env, PrimitiveStruct * p, int8_t aByte, uint8_t unsignedByte, int32_t aInt, uint32_t unsignedInt, int64_t aLong, uint64_t unsignedLong, int16_t aShort, uint16_t unsignedShort) {
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

    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PrimitiveStruct_func2(PNIEnv_void * env, PrimitiveStruct * p, uint16_t aChar, double aDouble, float aFloat, uint8_t aBoolean) {
    printf("aChar    = %c\n", aChar);
    printf("aDouble  = %f\n", aDouble);
    printf("aFloat   = %f\n", aFloat);
    printf("aBoolean = %u\n", aBoolean);
    fflush(stdout);

    p->aChar = aChar;
    p->aDouble = aDouble;
    p->aFloat = aFloat;
    p->aBoolean = aBoolean;

    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PrimitiveStruct_func3(PNIEnv_void * env, PrimitiveStruct * p, PNIBuf * byteArray, PNIBuf * unsignedByteArray, PNIBuf * intArray, PNIBuf * unsignedIntArray, PNIBuf * longArray, PNIBuf * unsignedLongArray, PNIBuf * shortArray, PNIBuf * unsignedShortArray) {
    PNIByteBufHandle* byteBufHandle = GetPNIByteBufHandle();
    printf("byteArray = [");
    for (int i = 0; i < byteBufHandle->len(byteArray); ++i) {
        if (i != 0) {
            printf(", ");
        }
        printf("%"PRIi8, byteBufHandle->get(byteArray, i));
        p->byteArray[i] = byteBufHandle->get(byteArray, i);
    }
    printf("]\n");
    byteBufHandle->setIntoBuf(&p->byteArrayPointer, byteArray->buf, byteBufHandle->len(byteArray));

    printf("unsignedByteArray = [");
    for (int i = 0; i < byteBufHandle->len(unsignedByteArray); ++i) {
        if (i != 0) {
            printf(", ");
        }
        printf("%"PRIu8, byteBufHandle->get(unsignedByteArray, i));
        p->unsignedByteArray[i] = byteBufHandle->get(unsignedByteArray, i);
    }
    printf("]\n");
    byteBufHandle->setIntoBuf(&p->unsignedByteArrayPointer, unsignedByteArray->buf, byteBufHandle->len(unsignedByteArray));

    PNIIntBufHandle* intBufHandle = GetPNIIntBufHandle();
    printf("intArray = [");
    for (int i = 0; i < intBufHandle->len(intArray); ++i) {
        if (i != 0) {
            printf(", ");
        }
        printf("%"PRIi32, intBufHandle->get(intArray, i));
        p->intArray[i] = intBufHandle->get(intArray, i);
    }
    printf("]\n");
    intBufHandle->setIntoBuf(&p->intArrayPointer, intArray->buf, intBufHandle->len(intArray));

    printf("unsignedIntArray = [");
    for (int i = 0; i < intBufHandle->len(unsignedIntArray); ++i) {
        if (i != 0) {
            printf(", ");
        }
        printf("%"PRIu32, intBufHandle->get(unsignedIntArray, i));
        p->unsignedIntArray[i] = intBufHandle->get(unsignedIntArray, i);
    }
    printf("]\n");
    intBufHandle->setIntoBuf(&p->unsignedIntArrayPointer, unsignedIntArray->buf, intBufHandle->len(unsignedIntArray));

    PNILongBufHandle* longBufHandle = GetPNILongBufHandle();
    printf("longArray = [");
    for (int i = 0; i < longBufHandle->len(longArray); ++i) {
        if (i != 0) {
            printf(", ");
        }
        printf("%"PRIi64, longBufHandle->get(longArray, i));
        p->longArray[i] = longBufHandle->get(longArray, i);
    }
    printf("]\n");
    longBufHandle->setIntoBuf(&p->longArrayPointer, longArray->buf, longBufHandle->len(longArray));

    printf("unsignedLongArray = [");
    for (int i = 0; i < longBufHandle->len(unsignedLongArray); ++i) {
        if (i != 0) {
            printf(", ");
        }
        printf("%"PRIu64, longBufHandle->get(unsignedLongArray, i));
        p->unsignedLongArray[i] = longBufHandle->get(unsignedLongArray, i);
    }
    printf("]\n");
    longBufHandle->setIntoBuf(&p->unsignedLongArrayPointer, unsignedLongArray->buf, longBufHandle->len(unsignedLongArray));

    PNIShortBufHandle* shortBufHandle = GetPNIShortBufHandle();
    printf("shortArray = [");
    for (int i = 0; i < shortBufHandle->len(shortArray); ++i) {
        if (i != 0) {
            printf(", ");
        }
        printf("%"PRIi16, shortBufHandle->get(shortArray, i));
        p->shortArray[i] = shortBufHandle->get(shortArray, i);
    }
    printf("]\n");
    shortBufHandle->setIntoBuf(&p->shortArrayPointer, shortArray->buf, shortBufHandle->len(shortArray));

    printf("unsignedShortArray = [");
    for (int i = 0; i < shortBufHandle->len(unsignedShortArray); ++i) {
        if (i != 0) {
            printf(", ");
        }
        printf("%"PRIu16, shortBufHandle->get(unsignedShortArray, i));
        p->unsignedShortArray[i] = shortBufHandle->get(unsignedShortArray, i);
    }
    printf("]\n");
    shortBufHandle->setIntoBuf(&p->unsignedShortArrayPointer, unsignedShortArray->buf, shortBufHandle->len(unsignedShortArray));

    fflush(stdout);
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PrimitiveStruct_func4(PNIEnv_void * env, PrimitiveStruct * p, PNIBuf * charArray, PNIBuf * doubleArray, PNIBuf * floatArray, PNIBuf * booleanArray) {
    PNICharBufHandle* charBufHandle = GetPNICharBufHandle();
    printf("charArray = [");
    for (int i = 0; i < charBufHandle->len(charArray); ++i) {
        if (i != 0) {
            printf(", ");
        }
        printf("%c", charBufHandle->get(charArray, i));
        p->charArray[i] = charBufHandle->get(charArray, i);
    }
    printf("]\n");
    charBufHandle->setIntoBuf(&p->charArrayPointer, charArray->buf, charBufHandle->len(charArray));

    PNIDoubleBufHandle* doubleBufHandle = GetPNIDoubleBufHandle();
    printf("doubleArray = [");
    for (int i = 0; i < doubleBufHandle->len(doubleArray); ++i) {
        if (i != 0) {
            printf(", ");
        }
        printf("%f", doubleBufHandle->get(doubleArray, i));
        p->doubleArray[i] = doubleBufHandle->get(doubleArray, i);
    }
    printf("]\n");
    doubleBufHandle->setIntoBuf(&p->doubleArrayPointer, doubleArray->buf, doubleBufHandle->len(doubleArray));

    PNIFloatBufHandle* floatBufHandle = GetPNIFloatBufHandle();
    printf("floatArray = [");
    for (int i = 0; i < floatBufHandle->len(floatArray); ++i) {
        if (i != 0) {
            printf(", ");
        }
        printf("%f", floatBufHandle->get(floatArray, i));
        p->floatArray[i] = floatBufHandle->get(floatArray, i);
    }
    printf("]\n");
    floatBufHandle->setIntoBuf(&p->floatArrayPointer, floatArray->buf, floatBufHandle->len(floatArray));

    PNIBoolBufHandle* boolBufHandle = GetPNIBoolBufHandle();
    printf("charArray = [");
    for (int i = 0; i < boolBufHandle->len(booleanArray); ++i) {
        if (i != 0) {
            printf(", ");
        }
        printf("%u", boolBufHandle->get(booleanArray, i));
        p->booleanArray[i] = boolBufHandle->get(booleanArray, i);
    }
    printf("]\n");
    boolBufHandle->setIntoBuf(&p->booleanArrayPointer, booleanArray->buf, boolBufHandle->len(booleanArray));

    fflush(stdout);
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PrimitiveStruct_retrieveByte(PNIEnv_byte * env, PrimitiveStruct * self) {
    env->return_ = self->aByte;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedByte(PNIEnv_byte * env, PrimitiveStruct * self) {
    env->return_ = self->unsignedByte;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PrimitiveStruct_retrieveChar(PNIEnv_char * env, PrimitiveStruct * self) {
    env->return_ = self->aChar;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PrimitiveStruct_retrieveDouble(PNIEnv_double * env, PrimitiveStruct * self) {
    env->return_ = self->aDouble;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PrimitiveStruct_retrieveFloat(PNIEnv_float * env, PrimitiveStruct * self) {
    env->return_ = self->aFloat;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PrimitiveStruct_retrieveInt(PNIEnv_int * env, PrimitiveStruct * self) {
    env->return_ = self->aInt;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedInt(PNIEnv_int * env, PrimitiveStruct * self) {
    env->return_ = self->unsignedInt;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PrimitiveStruct_retrieveLong(PNIEnv_long * env, PrimitiveStruct * self) {
    env->return_ = self->aLong;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedLong(PNIEnv_long * env, PrimitiveStruct * self) {
    env->return_ = self->unsignedLong;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PrimitiveStruct_retrieveShort(PNIEnv_short * env, PrimitiveStruct * self) {
    env->return_ = self->aShort;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedShort(PNIEnv_short * env, PrimitiveStruct * self) {
    env->return_ = self->unsignedShort;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PrimitiveStruct_retrieveBoolean(PNIEnv_bool * env, PrimitiveStruct * self) {
    env->return_ = self->aBoolean;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PrimitiveStruct_retrieveByteArray(PNIEnv_pointer * env, PrimitiveStruct * p, PNIBuf * return_) {
    PNIByteBufHandle* handle = GetPNIByteBufHandle();
    return_->buf = &p->byteArray;
    return_->len = handle->byteSize(5);
    env->return_ = return_;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedByteArray(PNIEnv_pointer * env, PrimitiveStruct * p, PNIBuf * return_) {
    PNIByteBufHandle* handle = GetPNIByteBufHandle();
    return_->buf = &p->unsignedByteArray;
    return_->len = handle->byteSize(6);
    env->return_ = return_;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PrimitiveStruct_retrieveCharArray(PNIEnv_pointer * env, PrimitiveStruct * p, PNIBuf * return_) {
    PNICharBufHandle* handle = GetPNICharBufHandle();
    return_->buf = &p->charArray;
    return_->len = handle->byteSize(7);
    env->return_ = return_;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PrimitiveStruct_retrieveDoubleArray(PNIEnv_pointer * env, PrimitiveStruct * p, PNIBuf * return_) {
    PNIDoubleBufHandle* handle = GetPNIDoubleBufHandle();
    return_->buf = &p->doubleArray;
    return_->len = handle->byteSize(8);
    env->return_ = return_;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PrimitiveStruct_retrieveFloatArray(PNIEnv_pointer * env, PrimitiveStruct * p, PNIBuf * return_) {
    PNIFloatBufHandle* handle = GetPNIFloatBufHandle();
    return_->buf = &p->floatArray;
    return_->len = handle->byteSize(9);
    env->return_ = return_;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PrimitiveStruct_retrieveIntArray(PNIEnv_pointer * env, PrimitiveStruct * p, PNIBuf * return_) {
    PNIIntBufHandle* handle = GetPNIIntBufHandle();
    return_->buf = &p->intArray;
    return_->len = handle->byteSize(10);
    env->return_ = return_;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedIntArray(PNIEnv_pointer * env, PrimitiveStruct * p, PNIBuf * return_) {
    PNIIntBufHandle* handle = GetPNIIntBufHandle();
    return_->buf = &p->unsignedIntArray;
    return_->len = handle->byteSize(11);
    env->return_ = return_;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PrimitiveStruct_retrieveLongArray(PNIEnv_pointer * env, PrimitiveStruct * p, PNIBuf * return_) {
    PNILongBufHandle* handle = GetPNILongBufHandle();
    return_->buf = &p->longArray;
    return_->len = handle->byteSize(12);
    env->return_ = return_;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedLongArray(PNIEnv_pointer * env, PrimitiveStruct * p, PNIBuf * return_) {
    PNILongBufHandle* handle = GetPNILongBufHandle();
    return_->buf = &p->unsignedLongArray;
    return_->len = handle->byteSize(13);
    env->return_ = return_;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PrimitiveStruct_retrieveShortArray(PNIEnv_pointer * env, PrimitiveStruct * p, PNIBuf * return_) {
    PNIShortBufHandle* handle = GetPNIShortBufHandle();
    return_->buf = &p->shortArray;
    return_->len = handle->byteSize(14);
    env->return_ = return_;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedShortArray(PNIEnv_pointer * env, PrimitiveStruct * p, PNIBuf * return_) {
    PNIShortBufHandle* handle = GetPNIShortBufHandle();
    return_->buf = &p->unsignedShortArray;
    return_->len = handle->byteSize(15);
    env->return_ = return_;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PrimitiveStruct_retrieveBooleanArray(PNIEnv_pointer * env, PrimitiveStruct * p, PNIBuf * return_) {
    PNIBoolBufHandle* handle = GetPNIBoolBufHandle();
    return_->buf = &p->booleanArray;
    return_->len = handle->byteSize(16);
    env->return_ = return_;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PrimitiveStruct_retrieveByteArrayPointer(PNIEnv_pointer * env, PrimitiveStruct * p, PNIBuf * return_) {
    *return_ = p->byteArrayPointer;
    env->return_ = return_;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedByteArrayPointer(PNIEnv_pointer * env, PrimitiveStruct * p, PNIBuf * return_) {
    *return_ = p->unsignedByteArrayPointer;
    env->return_ = return_;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PrimitiveStruct_retrieveCharArrayPointer(PNIEnv_pointer * env, PrimitiveStruct * p, PNIBuf * return_) {
    *return_ = p->charArrayPointer;
    env->return_ = return_;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PrimitiveStruct_retrieveDoubleArrayPointer(PNIEnv_pointer * env, PrimitiveStruct * p, PNIBuf * return_) {
    *return_ = p->doubleArrayPointer;
    env->return_ = return_;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PrimitiveStruct_retrieveFloatArrayPointer(PNIEnv_pointer * env, PrimitiveStruct * p, PNIBuf * return_) {
    *return_ = p->floatArrayPointer;
    env->return_ = return_;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PrimitiveStruct_retrieveIntArrayPointer(PNIEnv_pointer * env, PrimitiveStruct * p, PNIBuf * return_) {
    *return_ = p->intArrayPointer;
    env->return_ = return_;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedIntArrayPointer(PNIEnv_pointer * env, PrimitiveStruct * p, PNIBuf * return_) {
    *return_ = p->unsignedIntArrayPointer;
    env->return_ = return_;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PrimitiveStruct_retrieveLongArrayPointer(PNIEnv_pointer * env, PrimitiveStruct * p, PNIBuf * return_) {
    *return_ = p->longArrayPointer;
    env->return_ = return_;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedLongArrayPointer(PNIEnv_pointer * env, PrimitiveStruct * p, PNIBuf * return_) {
    *return_ = p->unsignedLongArrayPointer;
    env->return_ = return_;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PrimitiveStruct_retrieveShortArrayPointer(PNIEnv_pointer * env, PrimitiveStruct * p, PNIBuf * return_) {
    *return_ = p->shortArrayPointer;
    env->return_ = return_;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedShortArrayPointer(PNIEnv_pointer * env, PrimitiveStruct * p, PNIBuf * return_) {
    *return_ = p->unsignedShortArrayPointer;
    env->return_ = return_;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PrimitiveStruct_retrieveBooleanArrayPointer(PNIEnv_pointer * env, PrimitiveStruct * p, PNIBuf * return_) {
    *return_ = p->booleanArrayPointer;
    env->return_ = return_;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PrimitiveStruct_checkPointerSetToNonNull(PNIEnv_bool * env, PrimitiveStruct * p) {
    env->return_ = p->byteArrayPointer.buf != NULL
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
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PrimitiveStruct_checkPointerSetToNull(PNIEnv_bool * env, PrimitiveStruct * p) {
    env->return_ = p->byteArrayPointer.buf == NULL
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
    return 0;
}