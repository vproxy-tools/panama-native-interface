package io.vproxy.pni.test;

import io.vproxy.pni.annotation.Function;
import io.vproxy.pni.annotation.Impl;
import io.vproxy.pni.annotation.Raw;

import java.io.IOException;
import java.nio.ByteBuffer;

@Function
public interface PNIFunc {
    int func1();

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
}
