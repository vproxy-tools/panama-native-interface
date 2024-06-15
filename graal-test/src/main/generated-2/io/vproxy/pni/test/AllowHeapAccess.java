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

public class AllowHeapAccess {
    private AllowHeapAccess() {
    }

    private static final AllowHeapAccess INSTANCE = new AllowHeapAccess();

    public static AllowHeapAccess get() {
        return INSTANCE;
    }

    private static final MethodHandle intValueMH = PanamaUtils.lookupPNIFunction(new PNILinkOptions().setCritical(true), "Java_io_vproxy_pni_test_AllowHeapAccess_intValue", MemorySegment.class /* mem */);

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

    private static final MethodHandle intValueCriticalMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), int.class, "JavaCritical_io_vproxy_pni_test_AllowHeapAccess_intValueCritical", MemorySegment.class /* mem */);

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
// sha256:305e016ff4d7076a3de3f55c9a5c4d948c8887e615c83a768d6b3b546eddcdea
