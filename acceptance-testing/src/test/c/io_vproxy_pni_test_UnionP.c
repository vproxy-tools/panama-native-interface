#include "io_vproxy_pni_test_UnionP.h"

JNIEXPORT int JNICALL UnionP_retrieve_i(PNIEnv_int * env, UnionP * self) {
    env->return_ = self->i;
    return 0;
}

JNIEXPORT int JNICALL UnionP_retrieve_l(PNIEnv_long * env, UnionP * self) {
    env->return_ = self->l;
    return 0;
}
