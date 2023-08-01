package io.vproxy.pni.test;

import io.vproxy.pni.annotation.Critical;
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

    @Critical
    abstract void func1Critical(String str, String str2, MemorySegment seg, ByteBuffer buf);

    abstract String retrieveStr();

    @Critical
    abstract String retrieveStrCritical();

    abstract String retrieveLenStr();

    @Critical
    abstract String retrieveLenStrCritical();

    abstract MemorySegment retrieveSeg();

    @Critical
    abstract MemorySegment retrieveSegCritical();

    abstract ByteBuffer retrieveBuf();

    @Critical
    abstract ByteBuffer retrieveBufCritical();

    abstract boolean checkPointerSetToNonNull();

    @Critical
    abstract boolean checkPointerSetToNonNullCritical();

    abstract boolean checkPointerSetToNull();

    @Critical
    abstract boolean checkPointerSetToNullCritical();
}
