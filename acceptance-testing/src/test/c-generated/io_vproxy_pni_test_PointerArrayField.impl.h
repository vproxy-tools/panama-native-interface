#include "io_vproxy_pni_test_PointerArrayField.h"

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PointerArrayField_set(PNIEnv_void * env, PointerArrayField * self, PNIBuf_ptr * a) {
    for (int i = 0, len = ptrPNIArrayLen(a); i < len; ++i) {
        self->pointerArray[i] = a->array[i];
    }
    self->pointerArrayPointer = *a;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PointerArrayField_getPtrField(PNIEnv_buf_ptr * env, PointerArrayField * self) {
    env->return_ = self->pointerArrayPointer;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_PointerArrayField_getLenField(PNIEnv_buf_ptr * env, PointerArrayField * self) {
    env->return_.bufLen = ptrPNIBufLen(3);
    env->return_.buf = &self->pointerArray;
    return 0;
}

#ifdef __cplusplus
}
#endif
// metadata.generator-version: pni test
// sha256:65526cd6ea4764d4afb6a2a68dbc373ea7f87e4b1516c85f80fe3d3fc559d3c6
