package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.hack.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class AllowHeapAccess {
    private AllowHeapAccess() {
    }

    private static final AllowHeapAccess INSTANCE = new AllowHeapAccess();

    public static AllowHeapAccess get() {
        return INSTANCE;
    }

    private static final MethodHandle intValueMH = PanamaUtils.lookupPNIFunction(new PNILinkOptions().setCritical(true).setAllowHeapAccess(true), "Java_io_vproxy_pni_test_AllowHeapAccess_intValue", MemorySegment.class /* mem */);

    public int intValue(PNIEnv ENV, MemorySegment mem) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) intValueMH.invokeExact(ENV.MEMORY, (MemorySegment) (mem == null ? MemorySegment.NULL : mem));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnInt();
    }

    private static final MethodHandle intValueCriticalMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true).setAllowHeapAccess(true), int.class, "JavaCritical_io_vproxy_pni_test_AllowHeapAccess_intValueCritical", MemorySegment.class /* mem */);

    public int intValueCritical(MemorySegment mem) {
        int RESULT;
        try {
            RESULT = (int) intValueCriticalMH.invokeExact((MemorySegment) (mem == null ? MemorySegment.NULL : mem));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }
}
// metadata.generator-version: pni test
// sha256:a323da93434fdb3e2634bb0da272a48e31f893cce9749a9cb5befc83d75b7168
