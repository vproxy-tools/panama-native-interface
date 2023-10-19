#include "io_vproxy_pni_test_NoAlloc.h"
#include "io_vproxy_pni_test_NoAlloc.impl.h"

static ObjectStruct objectStruct;

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_NoAlloc_execNoAlloc(PNIEnv_ObjectStruct * env) {
    ++objectStruct.lenStr[0];
    env->return_ = &objectStruct;
    return 0;
}
