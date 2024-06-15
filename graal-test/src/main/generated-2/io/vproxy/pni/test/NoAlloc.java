package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.hack.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;
import io.vproxy.pni.graal.*;
import org.graalvm.nativeimage.*;
import org.graalvm.nativeimage.c.function.*;
import org.graalvm.nativeimage.c.type.VoidPointer;
import org.graalvm.word.WordFactory;

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
// sha256:8e41df6ee2adf4574ec8d6c522257d8dd8dd5469fae5c592228a7696f5fc568d
