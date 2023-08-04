package io.vproxy.pni.sample;

import io.vproxy.pni.*;
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

    private final MethodHandle read = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_sample_SampleFunctions_read", int.class /* fd */, io.vproxy.pni.sample.MBuf.LAYOUT.getClass() /* buf */);

    public int read(PNIEnv ENV, int fd, io.vproxy.pni.sample.MBuf buf) throws java.io.IOException {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) this.read.invokeExact(ENV.MEMORY, fd, (MemorySegment) (buf == null ? MemorySegment.NULL : buf.MEMORY));
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
// sha256:1c0d09e47bc7921d25be1fce7648971b0271271f82b203213e1471e4965291b3
