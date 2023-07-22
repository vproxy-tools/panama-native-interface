package io.vproxy.pni.sample;

import io.vproxy.pni.annotation.*;

import java.io.IOException;
import java.lang.foreign.MemorySegment;

@Struct
@Name("mbuf_t")
class MBuf {
    MemorySegment bufAddr;
    @Unsigned int pktLen;
    @Unsigned int pktOff;
    @Unsigned int bufLen;
    UserData userdata;
}

@Union(embedded = true)
class UserData {
    MemorySegment userdata;
    @Unsigned long udata64;
}

@Function
interface NativeFunctions {
    int read(int fd, MBuf buf) throws IOException;

    int write(int fd, MBuf buf) throws IOException;
}
