package io.vproxy.pni.test;

import io.vproxy.pni.CallSite;
import io.vproxy.pni.PNIRef;
import io.vproxy.pni.annotation.Critical;
import io.vproxy.pni.annotation.Function;
import io.vproxy.pni.annotation.Impl;
import io.vproxy.pni.annotation.Raw;

import java.io.IOException;
import java.lang.foreign.MemorySegment;
import java.nio.ByteBuffer;
import java.util.List;

@Function
public interface PNIFunc {
    int func1();

    @Critical
    int func1Critical();

    void func2() throws IOException;

    void func3(String ex) throws IOException, UnsupportedOperationException;

    @Impl(
        include = {"<unistd.h>"},
        // language="c"
        c = """
            int ret = write(fd, buf + off, len);
            if (ret < 0) {
                return PNIThrowException(env, "java.io.Exception", strerror(errno));
            }
            env->return_ = ret;
            return 0;
            """
    )
    int write(int fd, @Raw ByteBuffer buf, int off, int len) throws IOException;

    @Impl(
        include = {"<unistd.h>"},
        // language="c"
        c = """
            int n = write(fd, buf + off, len);
            if (n < 0) {
                return -errno;
            }
            return n;
            """
    )
    @Critical
    int writeCritical(int fd, @Raw ByteBuffer buf, int off, int len);

    @Impl(
        include = {"<unistd.h>"},
        // language="c"
        c = """
            int ret = write(fd, buf + off, len);
            if (ret < 0) {
                PNIStoreErrno(env);
            }
            env->return_ = ret;
            return 0;
            """
    )
    int writeWithErrno(int fd, @Raw ByteBuffer buf, int off, int len);

    @Impl(
        include = {"<unistd.h>"},
        // language="c"
        c = """
            int ret = write(fd, buf + off, len);
            if (ret < 0) {
                return PNIThrowException(env, "java.io.Exception", strerror(errno));
            }
            env->return_ = ret;
            return 0;
            """
    )
    int writeByteArray(int fd, @Raw byte[] buf, int off, int len) throws IOException;

    @Impl(
        // language="c"
        c = """
            ObjectStruct object_struct;
            PNIFuncInvoke(func, &object_struct);
            env->return_ = object_struct.seg;
            PNIFuncRelease(func);
            return 0;
            """
    )
    MemorySegment callJavaFromC(CallSite<PNIObjectStruct> func);

    @Impl(
        // language="c"
        c = """
            ObjectStruct object_struct;
            PNIFuncInvoke(func, &object_struct);
            void* ptr = object_struct.seg;
            PNIFuncRelease(func);
            return ptr;
            """
    )
    @Critical
    MemorySegment callJavaFromCCritical(CallSite<PNIObjectStruct> func);

    @Impl(
        // language="c"
        c = """
            PNIFuncInvoke(func, ref);
            PNIFuncRelease(func);
            PNIRefRelease(ref);
            return 0;
            """
    )
    void callJavaRefFromC(CallSite<PNIRef<List<String>>> func, PNIRef<List<String>> ref);

    @Impl(
        // language="c"
        c = """
            PNIFuncInvoke(func, ref);
            PNIFuncRelease(func);
            PNIRefRelease(ref);
            """
    )
    @Critical
    void callJavaRefFromCCritical(CallSite<PNIRef<List<String>>> func, PNIRef<List<String>> ref);

    @Impl(
        // language="c"
        c = """
            int (*ff)(void*, int) = func;
            env->return_ = ff(ref, a);
            PNIRefRelease(ref);
            return 0;
            """
    )
    int callJavaMethodWithRefFromC(MemorySegment func, PNIRef<List<Integer>> ref, int a);

    @Impl(
        // language="c"
        c = """
            int (*ff)(void*, int) = func;
            int res = ff(ref, a);
            PNIRefRelease(ref);
            return res;
            """
    )
    @Critical
    int callJavaMethodWithRefFromCCritical(MemorySegment func, PNIRef<List<Integer>> ref, int a);
}
