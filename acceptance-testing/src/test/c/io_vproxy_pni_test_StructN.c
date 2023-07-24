#include "io_vproxy_pni_test_StructN.h"

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_StructN_retrieveS(PNIEnv_short * env, struct N_st * self) {
    env->return_ = self->s;
    return 0;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_StructN_retrieveL(PNIEnv_long * env, struct N_st * self) {
    env->return_ = self->l;
    return 0;
}
