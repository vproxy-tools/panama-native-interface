#include "io_vproxy_pni_test_ObjectStruct.h"
#include <stdlib.h>

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_ObjectStruct_func1(PNIEnv_void * env, ObjectStruct * o, char * str, char * str2, void * seg, PNIBuf * buf) {
    printf("str     = %s\n", str);
    printf("str2    = %s\n", str2);
    printf("seg     = %lu\n", (size_t) seg);
    printf("buf.buf = %lu\n", (size_t) buf->buf);
    printf("buf.len = %llu\n", buf->len);

    int n = strlen(str);
    o->str = malloc(n + 1);
    strcpy(o->str, str);
    strncpy(o->lenStr, str2, sizeof(o->lenStr));
    o->lenStr[sizeof(o->lenStr)-1] = '\0';
    o->seg = seg;
    o->buf = *buf;

    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_ObjectStruct_retrieveStr(PNIEnv_pointer * env, ObjectStruct * self) {
    env->return_ = self->str;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_ObjectStruct_retrieveLenStr(PNIEnv_pointer * env, ObjectStruct * self) {
    env->return_ = self->lenStr;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_ObjectStruct_retrieveSeg(PNIEnv_pointer * env, ObjectStruct * self) {
    env->return_ = self->seg;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_ObjectStruct_retrieveBuf(PNIEnv_pointer * env, ObjectStruct * self, PNIBuf * return_) {
    *return_ = self->buf;
    env->return_ = return_;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_ObjectStruct_checkPointerSetToNonNull(PNIEnv_bool * env, ObjectStruct * self) {
    env->return_ = self->str != NULL && self->seg != NULL && self->buf.buf != NULL;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_ObjectStruct_checkPointerSetToNull(PNIEnv_bool * env, ObjectStruct * self) {
    env->return_ = self->str == NULL && self->seg == NULL && self->buf.buf == NULL;
    return 0;
}