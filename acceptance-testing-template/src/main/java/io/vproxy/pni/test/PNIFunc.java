package io.vproxy.pni.test;

import io.vproxy.pni.PNIRef;
import io.vproxy.pni.annotation.*;

import java.io.IOException;
import java.lang.foreign.MemorySegment;
import java.nio.ByteBuffer;
import java.util.List;

@Downcall
public interface PNIFunc {
    int func1();

    @Style(Styles.critical)
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
    @Style(Styles.critical)
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
        c = """
            return PNIThrowException(env, "java.io.IOException", strerror(EINVAL));
            """
    )
    int testErrno() throws IOException;

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
    MemorySegment callJavaFromC(io.vproxy.pni.PNIFunc<PNIObjectStruct> func);

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
    @Style(Styles.critical)
    MemorySegment callJavaFromCCritical(io.vproxy.pni.PNIFunc<PNIObjectStruct> func);

    @Impl(
        // language="c"
        c = """
            PNIFuncInvoke(func, ref);
            PNIFuncRelease(func);
            PNIRefRelease(ref);
            return 0;
            """
    )
    void callJavaRefFromC(io.vproxy.pni.PNIFunc<PNIRef<List<String>>> func, PNIRef<List<String>> ref);

    @Impl(
        // language="c"
        c = """
            PNIFuncInvoke(func, ref);
            PNIFuncRelease(func);
            PNIRefRelease(ref);
            """
    )
    @Style(Styles.critical)
    void callJavaRefFromCCritical(io.vproxy.pni.PNIFunc<PNIRef<List<String>>> func, PNIRef<List<String>> ref);

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
    @Style(Styles.critical)
    int callJavaMethodWithRefFromCCritical(MemorySegment func, PNIRef<List<Integer>> ref, int a);
}
