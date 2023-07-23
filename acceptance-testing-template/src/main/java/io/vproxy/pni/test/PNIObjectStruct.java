package io.vproxy.pni.test;

import io.vproxy.pni.annotation.Len;
import io.vproxy.pni.annotation.Struct;

import java.lang.foreign.MemorySegment;
import java.nio.ByteBuffer;

@Struct
public abstract class PNIObjectStruct {
    String str;
    @Len(16) String lenStr;
    MemorySegment seg;
    ByteBuffer buf;

    abstract void func1(String str, String str2, MemorySegment seg, ByteBuffer buf);

    abstract String retrieveStr();

    abstract String retrieveLenStr();

    abstract MemorySegment retrieveSeg();

    abstract ByteBuffer retrieveBuf();

    abstract boolean checkPointerSetToNonNull();

    abstract boolean checkPointerSetToNull();
}
