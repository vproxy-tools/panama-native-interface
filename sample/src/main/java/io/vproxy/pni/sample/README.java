package io.vproxy.pni.sample;

import io.vproxy.pni.annotation.*;

import java.io.IOException;
import java.lang.foreign.MemorySegment;

@Struct
@Name("mbuf_t")
@AlwaysAligned
class PNIMBuf {
    MemorySegment bufAddr;
    @Unsigned int pktLen;
    @Unsigned int pktOff;
    @Unsigned int bufLen;
    PNIUserData userdata;
}

@Union(embedded = true)
@AlwaysAligned
class PNIUserData {
    MemorySegment userdata;
    @Unsigned long udata64;
}

@Downcall
interface PNISampleFunctions {
    int read(int fd, PNIMBuf buf) throws IOException;
}
