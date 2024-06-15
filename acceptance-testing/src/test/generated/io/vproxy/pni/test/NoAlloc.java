package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.hack.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class NoAlloc {
    private NoAlloc() {
    }

    private static final NoAlloc INSTANCE = new NoAlloc();

    public static NoAlloc get() {
        return INSTANCE;
    }

    private static final MethodHandle execNoAllocMH = PanamaUtils.lookupPNIFunction(new PNILinkOptions(), "Java_io_vproxy_pni_test_NoAlloc_execNoAlloc");

    public io.vproxy.pni.test.ObjectStruct execNoAlloc(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) execNoAllocMH.invokeExact(ENV.MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RESULT = ENV.returnPointer();
        return RESULT == null ? null : new io.vproxy.pni.test.ObjectStruct(RESULT);
    }

    private static final MethodHandle invokeUpcallMH = PanamaUtils.lookupPNIFunction(new PNILinkOptions(), "Java_io_vproxy_pni_test_NoAlloc_invokeUpcall");

    public io.vproxy.pni.test.ObjectStruct invokeUpcall(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) invokeUpcallMH.invokeExact(ENV.MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RESULT = ENV.returnPointer();
        return RESULT == null ? null : new io.vproxy.pni.test.ObjectStruct(RESULT);
    }
}
// metadata.generator-version: pni test
// sha256:7014dea1cae184f1a86f9b7896da224486a8d23dc2b89cd2d8d04634e069d069
