package io.vproxy.pni.exec.generator;

import io.vproxy.pni.exec.CompilationFlag;
import io.vproxy.pni.exec.CompilerOptions;
import io.vproxy.pni.exec.internal.PNILogger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class StaticFileGenerator {
    private final CompilerOptions opts;

    public StaticFileGenerator(CompilerOptions opts) {
        this.opts = opts;
    }

    public void flush() {
        releaseFile(CompilationFlag.RELEASE_PNI_H_FILE, "pni.h", PNI_H, opts.getCOutputDirectory());
        releaseFile(CompilationFlag.RELEASE_PNI_C_FILE, "pni.c", PNI_C, opts.getCOutputDirectory());
        releaseFile(CompilationFlag.RELEASE_JNI_H_MOCK_FILE, "jni.h", JNI_H_MOCK, opts.getCOutputDirectory());
    }

    private void releaseFile(CompilationFlag<File> flag, String filename, String content, File defaultDir) {
        if (opts.hasCompilationFlag(flag)) {
            var f = opts.getCompilationFlag(flag);
            if (f == null) {
                f = defaultDir;
            }
            var p = Path.of(f.getAbsolutePath(), filename);
            PNILogger.debug(opts, "release " + filename + " to " + p);

            try {
                Files.writeString(p, content);
            } catch (IOException e) {
                throw new RuntimeException("failed writing " + filename + ": " + p, e);
            }
        } else {
            PNILogger.debug(opts, "will not release " + filename);
        }
    }

    // language="c"
    private static final String PNI_H =
        "#ifndef PNIENV_H\n" +
        "#define PNIENV_H\n" +
        "\n" +
        "#include <jni.h>\n" +
        "#include <inttypes.h>\n" +
        "#include <string.h>\n" +
        "#include <errno.h>\n" +
        "\n" +
        "#if defined(__GNUC__)\n" +
        "  #define PNI_PACK( __t__, __n__, __Declaration__ ) __t__ __n__ __Declaration__ __attribute__((__packed__))\n" +
        "#else\n" +
        "  #define PNI_PACK( __t__, __n__, __Declaration__ ) __pragma(pack(push, 1)) __t__ __n__ __Declaration__ __pragma(pack(pop))\n" +
        "#endif\n" +
        "\n" +
        "typedef PNI_PACK(struct, PNIException, {\n" +
        "    char* type;\n" +
        "#define PNIExceptionMessageLen (4096)\n" +
        "    char  message[PNIExceptionMessageLen];\n" +
        "    int32_t errno_; /* padding */ uint32_t : 32;\n" +
        "}) PNIException;\n" +
        "\n" +
        "typedef PNI_PACK(struct, PNIBuf, {\n" +
        "    void*    buf;\n" +
        "    uint64_t len;\n" +
        "}) PNIBuf;\n" +
        "\n" +
        "typedef PNI_PACK(struct, PNIEnv, {\n" +
        "    PNIException ex;\n" +
        "    union {\n" +
        "        int8_t   return_byte;\n" +
        "        uint16_t return_char;\n" +
        "        double   return_double;\n" +
        "        int32_t  return_int;\n" +
        "        float    return_float;\n" +
        "        int64_t  return_long;\n" +
        "        int16_t  return_short;\n" +
        "        int8_t   return_bool;\n" +
        "        void*    return_pointer;\n" +
        "        PNIBuf   return_buf;\n" +
        "    };\n" +
        "}) PNIEnv;\n" +
        "\n" +
        "typedef PNI_PACK(struct, PNIEnvUnionPlaceHolder, {\n" +
        "    uint64_t : 64;\n" +
        "    uint64_t : 64;\n" +
        "}) PNIEnvUnionPlaceHolder;\n" +
        "\n" +
        "#define PNIEnvExpand(EnvType, ValueType) \\\n" +
        "typedef PNI_PACK(struct, PNIEnv_##EnvType, { \\\n" +
        "    PNIException ex; \\\n" +
        "    union { \\\n" +
        "        ValueType return_; \\\n" +
        "        PNIEnvUnionPlaceHolder __placeholder__; \\\n" +
        "    }; \\\n" +
        "}) PNIEnv_##EnvType;\n" +
        "// end #define PNIEnvExpand\n" +
        "\n" +
        "PNIEnvExpand(byte, int8_t)\n" +
        "PNIEnvExpand(char, uint16_t)\n" +
        "PNIEnvExpand(float, float)\n" +
        "PNIEnvExpand(double, double)\n" +
        "PNIEnvExpand(int, int32_t)\n" +
        "PNIEnvExpand(long, int64_t)\n" +
        "PNIEnvExpand(short, int16_t)\n" +
        "PNIEnvExpand(bool, uint8_t)\n" +
        "PNIEnvExpand(pointer, void*)\n" +
        "PNIEnvExpand(string, char*)\n" +
        "PNIEnvExpand(buf, PNIBuf)\n" +
        "\n" +
        "typedef PNI_PACK(struct, PNIEnv_void, {\n" +
        "    PNIException ex;\n" +
        "    PNIEnvUnionPlaceHolder __placeholder__;\n" +
        "}) PNIEnv_void;\n" +
        "\n" +
        "static inline int PNIThrowException(void* _env, const char* extype, char* message) {\n" +
        "    PNIEnv* env = _env;\n" +
        "    env->ex.type = (char*) extype;\n" +
        "    strncpy(env->ex.message, message, PNIExceptionMessageLen);\n" +
        "    env->ex.message[PNIExceptionMessageLen - 1] = '\\0';\n" +
        "    return -1;\n" +
        "}\n" +
        "\n" +
        "static inline int PNIThrowExceptionBasedOnErrno(void* _env, const char* extype) {\n" +
        "    return PNIThrowException(_env, extype, strerror(errno));\n" +
        "}\n" +
        "\n" +
        "static inline void PNIStoreErrno(void* _env) {\n" +
        "    PNIEnv* env = _env;\n" +
        "    env->ex.errno_ = errno;\n" +
        "}\n" +
        "\n" +
        "#if PNI_GRAAL\n" +
        "JNIEXPORT void  JNICALL SetPNIGraalThread(void* thread);\n" +
        "JNIEXPORT void* JNICALL GetPNIGraalThread(void);\n" +
        "#endif // PNI_GRAAL\n" +
        "\n" +
        "typedef PNI_PACK(struct, PNIFunc, {\n" +
        "    int64_t   index;\n" +
        "    union {\n" +
        "        void*    userdata;\n" +
        "        uint64_t udata64;\n" +
        "    };\n" +
        "}) PNIFunc;\n" +
        "\n" +
        "PNIEnvExpand(func, PNIFunc*)\n" +
        "\n" +
        "#define PNIFuncInvokeExceptionCaught ((int32_t) 0x800000f1)\n" +
        "#define PNIFuncInvokeNoSuchFunction  ((int32_t) 0x800000f2)\n" +
        "\n" +
        "#if PNI_GRAAL\n" +
        "typedef int32_t (*PNIFuncInvokeFunc)(void*,int64_t,void*);\n" +
        "#else\n" +
        "typedef int32_t (*PNIFuncInvokeFunc)(int64_t,void*);\n" +
        "#endif // PNI_GRAAL\n" +
        "JNIEXPORT PNIFuncInvokeFunc JNICALL GetPNIFuncInvokeFunc(void);\n" +
        "JNIEXPORT void JNICALL SetPNIFuncInvokeFunc(PNIFuncInvokeFunc f);\n" +
        "\n" +
        "static inline int32_t PNIFuncInvoke(PNIFunc* f, void* data) {\n" +
        "#if PNI_GRAAL\n" +
        "    return GetPNIFuncInvokeFunc()(GetPNIGraalThread(), f->index, data);\n" +
        "#else\n" +
        "    return GetPNIFuncInvokeFunc()(f->index, data);\n" +
        "#endif // PNI_GRAAL\n" +
        "}\n" +
        "\n" +
        "#if PNI_GRAAL\n" +
        "typedef void (*PNIFuncReleaseFunc)(void*,int64_t);\n" +
        "#else\n" +
        "typedef void (*PNIFuncReleaseFunc)(int64_t);\n" +
        "#endif // PNI_GRAAL\n" +
        "JNIEXPORT PNIFuncReleaseFunc JNICALL GetPNIFuncReleaseFunc(void);\n" +
        "JNIEXPORT void JNICALL SetPNIFuncReleaseFunc(PNIFuncReleaseFunc f);\n" +
        "\n" +
        "static inline void PNIFuncRelease(PNIFunc* f) {\n" +
        "#if PNI_GRAAL\n" +
        "    GetPNIFuncReleaseFunc()(GetPNIGraalThread(), f->index);\n" +
        "#else\n" +
        "    GetPNIFuncReleaseFunc()(f->index);\n" +
        "#endif // PNI_GRAAL\n" +
        "}\n" +
        "\n" +
        "typedef PNI_PACK(struct, PNIRef, {\n" +
        "    int64_t index;\n" +
        "    union {\n" +
        "        void*    userdata;\n" +
        "        uint64_t udata64;\n" +
        "    };\n" +
        "}) PNIRef;\n" +
        "\n" +
        "PNIEnvExpand(ref, PNIRef*)\n" +
        "\n" +
        "#if PNI_GRAAL\n" +
        "typedef void (*PNIRefReleaseFunc)(void*,int64_t);\n" +
        "#else\n" +
        "typedef void (*PNIRefReleaseFunc)(int64_t);\n" +
        "#endif // PNI_GRAAL\n" +
        "JNIEXPORT PNIRefReleaseFunc JNICALL GetPNIRefReleaseFunc(void);\n" +
        "JNIEXPORT void JNICALL SetPNIRefReleaseFunc(PNIRefReleaseFunc f);\n" +
        "\n" +
        "static inline void PNIRefRelease(PNIRef* ref) {\n" +
        "#if PNI_GRAAL\n" +
        "    GetPNIRefReleaseFunc()(GetPNIGraalThread(), ref->index);\n" +
        "#else\n" +
        "    GetPNIRefReleaseFunc()(ref->index);\n" +
        "#endif // PNI_GRAAL\n" +
        "}\n" +
        "\n" +
        "#define PNIBufExpand(BufType, ValueType, Size) \\\n" +
        "typedef PNI_PACK(struct, PNIBuf_##BufType, { \\\n" +
        "    union { \\\n" +
        "        ValueType* array; \\\n" +
        "        void*      buf; \\\n" +
        "    }; \\\n" +
        "    uint64_t bufLen; \\\n" +
        "}) PNIBuf_##BufType; \\\n" +
        "static inline uint64_t BufType##PNIArrayLen(PNIBuf_##BufType* buf) { \\\n" +
        "    return buf->bufLen / (Size == 0 ? 1 : Size); \\\n" +
        "} \\\n" +
        "static inline uint64_t BufType##PNIBufLen(uint64_t arrayLen) { \\\n" +
        "    return arrayLen * Size; \\\n" +
        "} \\\n" +
        "PNIEnvExpand(buf_##BufType, PNIBuf_##BufType)\n" +
        "// end #define PNIBufExpand\n" +
        "\n" +
        "PNIBufExpand(byte, int8_t, 1)\n" +
        "PNIBufExpand(ubyte, uint8_t, 1)\n" +
        "PNIBufExpand(char, uint16_t, 2)\n" +
        "PNIBufExpand(int, int32_t, 4)\n" +
        "PNIBufExpand(uint, uint32_t, 4)\n" +
        "PNIBufExpand(long, int64_t, 8)\n" +
        "PNIBufExpand(ulong, uint64_t, 8)\n" +
        "PNIBufExpand(float, float, 4)\n" +
        "PNIBufExpand(double, double, 8)\n" +
        "PNIBufExpand(short, int16_t, 2)\n" +
        "PNIBufExpand(ushort, uint16_t, 2)\n" +
        "PNIBufExpand(bool, uint8_t, 1)\n" +
        "\n" +
        "#endif // PNIENV_H\n";
    // language="c"
    private static final String PNI_C =
        "#include \"pni.h\"\n" +
        "\n" +
        "#if PNI_GRAAL\n" +
        "\n" +
        "static __thread void* _graalThread;\n" +
        "\n" +
        "JNIEXPORT void JNICALL SetPNIGraalThread(void* thread) {\n" +
        "    _graalThread = thread;\n" +
        "}\n" +
        "\n" +
        "JNIEXPORT void* JNICALL GetPNIGraalThread(void) {\n" +
        "    return _graalThread;\n" +
        "}\n" +
        "\n" +
        "#endif // PNI_GRAAL\n" +
        "\n" +
        "static PNIFuncInvokeFunc _PNIFuncInvokeFunc;\n" +
        "\n" +
        "JNIEXPORT PNIFuncInvokeFunc JNICALL GetPNIFuncInvokeFunc(void) {\n" +
        "    return _PNIFuncInvokeFunc;\n" +
        "}\n" +
        "JNIEXPORT void JNICALL SetPNIFuncInvokeFunc(PNIFuncInvokeFunc f) {\n" +
        "    _PNIFuncInvokeFunc = f;\n" +
        "}\n" +
        "\n" +
        "static PNIFuncReleaseFunc _PNIFuncReleaseFunc;\n" +
        "\n" +
        "JNIEXPORT PNIFuncReleaseFunc JNICALL GetPNIFuncReleaseFunc(void) {\n" +
        "    return _PNIFuncReleaseFunc;\n" +
        "}\n" +
        "JNIEXPORT void JNICALL SetPNIFuncReleaseFunc(PNIFuncReleaseFunc f) {\n" +
        "    _PNIFuncReleaseFunc = f;\n" +
        "}\n" +
        "\n" +
        "static PNIRefReleaseFunc _PNIRefReleaseFunc;\n" +
        "\n" +
        "JNIEXPORT PNIRefReleaseFunc JNICALL GetPNIRefReleaseFunc(void) {\n" +
        "    return _PNIRefReleaseFunc;\n" +
        "}\n" +
        "JNIEXPORT void JNICALL SetPNIRefReleaseFunc(PNIRefReleaseFunc f) {\n" +
        "    _PNIRefReleaseFunc = f;\n" +
        "}\n";
    // language="c"
    private static final String JNI_H_MOCK =
        "#ifndef PNI_JNIMOCK_H\n" +
        "#define PNI_JNIMOCK_H\n" +
        "\n" +
        "#include <stdio.h>\n" +
        "#include <stdarg.h>\n" +
        "\n" +
        "#ifdef WIN32\n" +
        "#define JNIEXPORT __declspec(dllexport)\n" +
        "#else\n" +
        "#define JNIEXPORT __attribute__((visibility(\"default\")))\n" +
        "#endif\n" +
        "\n" +
        "#ifdef WIN32\n" +
        "#define JNICALL __stdcall\n" +
        "#else\n" +
        "#define JNICALL\n" +
        "#endif\n" +
        "\n" +
        "#include <inttypes.h>\n" +
        "\n" +
        "typedef int8_t   jbyte;\n" +
        "typedef uint16_t jchar;\n" +
        "typedef double   jdouble;\n" +
        "typedef float    jfloat;\n" +
        "typedef int32_t  jint;\n" +
        "typedef int64_t  jlong;\n" +
        "typedef int16_t  jshort;\n" +
        "typedef uint8_t  jboolean;\n" +
        "\n" +
        "#define JNI_FALSE (0)\n" +
        "#define JNI_TRUE  (1)\n" +
        "\n" +
        "#define JNI_OK  (0)\n" +
        "#define JNI_ERR (-1)\n" +
        "\n" +
        "#endif // PNI_JNIMOCK_H\n";
}
