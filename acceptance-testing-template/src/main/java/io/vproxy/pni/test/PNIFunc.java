package io.vproxy.pni.test;

import io.vproxy.pni.annotation.Function;
import io.vproxy.pni.annotation.Raw;

import java.io.IOException;
import java.nio.ByteBuffer;

@Function
public interface PNIFunc {
    int func1();

    void func2() throws IOException;

    void func3(String ex) throws IOException, UnsupportedOperationException;

    int write(int fd, @Raw ByteBuffer buf, int off, int len) throws IOException;
}
