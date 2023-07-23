/* DO NOT EDIT THIS FILE - it is machine generated */
/* Header for class io_vproxy_pni_test_ObjectStruct */
#ifndef _Included_io_vproxy_pni_test_ObjectStruct
#define _Included_io_vproxy_pni_test_ObjectStruct
#ifdef __cplusplus
extern "C" {
#endif

struct PNI_DUMMY_ObjectStruct;
typedef struct PNI_DUMMY_ObjectStruct ObjectStruct;

#ifdef __cplusplus
}
#endif

#include <jni.h>
#include <pni.h>

#ifdef __cplusplus
extern "C" {
#endif

struct PNI_DUMMY_ObjectStruct {
    char * str;
    char lenStr[16];
    void * seg;
    PNIBuf buf;
} PNI_PACKED;

JNIEXPORT int JNICALL Java_io_vproxy_pni_test_ObjectStruct_func1(PNIEnv_void * env, ObjectStruct * self, char * str, char * str2, void * seg, PNIBuf * buf);
JNIEXPORT int JNICALL Java_io_vproxy_pni_test_ObjectStruct_retrieveStr(PNIEnv_pointer * env, ObjectStruct * self);
JNIEXPORT int JNICALL Java_io_vproxy_pni_test_ObjectStruct_retrieveLenStr(PNIEnv_pointer * env, ObjectStruct * self);
JNIEXPORT int JNICALL Java_io_vproxy_pni_test_ObjectStruct_retrieveSeg(PNIEnv_pointer * env, ObjectStruct * self);
JNIEXPORT int JNICALL Java_io_vproxy_pni_test_ObjectStruct_retrieveBuf(PNIEnv_pointer * env, ObjectStruct * self, PNIBuf * return_);
JNIEXPORT int JNICALL Java_io_vproxy_pni_test_ObjectStruct_checkPointerSetToNonNull(PNIEnv_bool * env, ObjectStruct * self);
JNIEXPORT int JNICALL Java_io_vproxy_pni_test_ObjectStruct_checkPointerSetToNull(PNIEnv_bool * env, ObjectStruct * self);

#ifdef __cplusplus
}
#endif
#endif // _Included_io_vproxy_pni_test_ObjectStruct
// sha256:091a141afd755e39188af7848bb4880394a9bed15910bafb9e0d79cdee81a077
