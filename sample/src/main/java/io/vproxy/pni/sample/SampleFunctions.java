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

    private static final MethodHandle readMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_sample_SampleFunctions_read", int.class /* fd */, io.vproxy.pni.sample.MBuf.LAYOUT.getClass() /* buf */);

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
// sha256:56a7acc1d4a6702dd8aa076e53b3d2b6f652c09b5395356fe109a3669d1378d8
