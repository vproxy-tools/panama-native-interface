#include "io_vproxy_pni_test_Func.impl.h"

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_Func_func1(PNIEnv_int * env) {
    env->return_ = JavaCritical_io_vproxy_pni_test_Func_func1Critical();
    return 0;
}

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_pni_test_Func_func1Critical(void) {
    return 10;
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_Func_func2(PNIEnv_void * env) {
    return PNIThrowException(env, "java.io.IOException", "hello");
}

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_Func_func3(PNIEnv_void * env, char * ex) {
    if (ex == NULL) {
        return 0;
    }
    return PNIThrowException(env, ex, "aaa");
}
