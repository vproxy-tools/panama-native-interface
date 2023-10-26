package io.vproxy.pni.test;

import io.vproxy.pni.annotation.Style;
import io.vproxy.pni.annotation.Len;
import io.vproxy.pni.annotation.Struct;
import io.vproxy.pni.annotation.Styles;

import java.lang.foreign.MemorySegment;
import java.nio.ByteBuffer;

@Struct
public abstract class PNIObjectStruct {
    String str;
    @Len(16) String lenStr;
    MemorySegment seg;
    ByteBuffer buf;

    abstract void func1(String str, String str2, MemorySegment seg, ByteBuffer buf);

    @Style(Styles.critical)
    abstract void func1Critical(String str, String str2, MemorySegment seg, ByteBuffer buf);

    abstract String retrieveStr();

    @Style(Styles.critical)
    abstract String retrieveStrCritical();

    abstract String retrieveLenStr();

    @Style(Styles.critical)
    abstract String retrieveLenStrCritical();

    abstract MemorySegment retrieveSeg();

    @Style(Styles.critical)
    abstract MemorySegment retrieveSegCritical();

    abstract ByteBuffer retrieveBuf();

    @Style(Styles.critical)
    abstract ByteBuffer retrieveBufCritical();

    abstract boolean checkPointerSetToNonNull();

    @Style(Styles.critical)
    abstract boolean checkPointerSetToNonNullCritical();

    abstract boolean checkPointerSetToNull();

    @Style(Styles.critical)
    abstract boolean checkPointerSetToNullCritical();
}
