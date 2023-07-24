#include "io_vproxy_pni_test_StructM.h"

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_StructM_nnn(PNIEnv_void * env, struct StructM * self, struct N_st * n_st) {
    self->n_st = *n_st;
    return 0;
}
