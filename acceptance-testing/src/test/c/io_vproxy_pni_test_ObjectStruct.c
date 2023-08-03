#include "io_vproxy_pni_test_ObjectStruct.h"
#include <stdlib.h>

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_ObjectStruct_func1(PNIEnv_void * env, ObjectStruct * o, char * str, char * str2, void * seg, PNIBuf * buf) {
    JavaCritical_io_vproxy_pni_test_ObjectStruct_func1Critical(o, str, str2, seg, buf);
    return 0;
}

JNIEXPORT void JNICALL JavaCritical_io_vproxy_pni_test_ObjectStruct_func1Critical(ObjectStruct * o, char * str, char * str2, void * seg, PNIBuf * buf) {
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
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_ObjectStruct_retrieveStr(PNIEnv_pointer * env, ObjectStruct * self) {
    env->return_ = JavaCritical_io_vproxy_pni_test_ObjectStruct_retrieveStrCritical(self);
    return 0;
}

JNIEXPORT char * JNICALL JavaCritical_io_vproxy_pni_test_ObjectStruct_retrieveStrCritical(ObjectStruct * self) {
    return self->str;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_ObjectStruct_retrieveLenStr(PNIEnv_pointer * env, ObjectStruct * self) {
    env->return_ = JavaCritical_io_vproxy_pni_test_ObjectStruct_retrieveLenStrCritical(self);
    return 0;
}

JNIEXPORT char * JNICALL JavaCritical_io_vproxy_pni_test_ObjectStruct_retrieveLenStrCritical(ObjectStruct * self) {
    return self->lenStr;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_ObjectStruct_retrieveSeg(PNIEnv_pointer * env, ObjectStruct * self) {
    env->return_ = JavaCritical_io_vproxy_pni_test_ObjectStruct_retrieveSegCritical(self);
    return 0;
}

JNIEXPORT void * JNICALL JavaCritical_io_vproxy_pni_test_ObjectStruct_retrieveSegCritical(ObjectStruct * self) {
    return self->seg;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_ObjectStruct_retrieveBuf(PNIEnv_buf * env, ObjectStruct * self) {
    JavaCritical_io_vproxy_pni_test_ObjectStruct_retrieveBufCritical(self, &env->return_);
    return 0;
}

JNIEXPORT PNIBuf * JNICALL JavaCritical_io_vproxy_pni_test_ObjectStruct_retrieveBufCritical(ObjectStruct * self, PNIBuf * return_) {
    *return_ = self->buf;
    return return_;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_ObjectStruct_checkPointerSetToNonNull(PNIEnv_bool * env, ObjectStruct * self) {
    env->return_ = JavaCritical_io_vproxy_pni_test_ObjectStruct_checkPointerSetToNonNullCritical(self);
    return 0;
}

JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_pni_test_ObjectStruct_checkPointerSetToNonNullCritical(ObjectStruct * self) {
    return self->str != NULL && self->seg != NULL && self->buf.buf != NULL;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_ObjectStruct_checkPointerSetToNull(PNIEnv_bool * env, ObjectStruct * self) {
    env->return_ = JavaCritical_io_vproxy_pni_test_ObjectStruct_checkPointerSetToNullCritical(self);
    return 0;
}

JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_pni_test_ObjectStruct_checkPointerSetToNullCritical(ObjectStruct * self) {
    return self->str == NULL && self->seg == NULL && self->buf.buf == NULL;
}
