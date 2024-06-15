package io.vproxy.pni.sample;

import io.vproxy.pni.*;
import io.vproxy.pni.hack.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class SampleFunctions {
    private SampleFunctions() {
    }

    private static final SampleFunctions INSTANCE = new SampleFunctions();

    public static SampleFunctions get() {
        return INSTANCE;
    }

    private static final MethodHandle readMH = PanamaUtils.lookupPNIFunction(new PNILinkOptions(), "Java_io_vproxy_pni_sample_SampleFunctions_read", int.class /* fd */, io.vproxy.pni.sample.MBuf.LAYOUT.getClass() /* buf */);

    public int read(PNIEnv ENV, int fd, io.vproxy.pni.sample.MBuf buf) throws java.io.IOException {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) readMH.invokeExact(ENV.MEMORY, fd, (MemorySegment) (buf == null ? MemorySegment.NULL : buf.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwIf(java.io.IOException.class);
            ENV.throwLast();
        }
        return ENV.returnInt();
    }
}
// metadata.generator-version: pni test
// sha256:8e3f9bda857403094b528f8219843d22baf91d51bb31841a6070660cde4c627c
