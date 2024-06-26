package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.hack.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class NoAllocUpcall {
    private static final Arena ARENA = Arena.ofShared();

    public static final MemorySegment execNoAlloc;

    private static MemorySegment execNoAlloc() {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.NoAllocUpcall#execNoAlloc");
            System.exit(1);
        }
        var RESULT = IMPL.execNoAlloc();
        return RESULT == null ? MemorySegment.NULL : RESULT.MEMORY;
    }

    static {
        MethodHandle execNoAllocMH;
        try {
            execNoAllocMH = MethodHandles.lookup().findStatic(io.vproxy.pni.test.NoAllocUpcall.class, "execNoAlloc", MethodType.methodType(MemorySegment.class));
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
        execNoAlloc = PanamaUtils.defineCFunction(new PNILinkOptions(), ARENA, execNoAllocMH, MemorySegment.class);

        var initMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), void.class, "JavaCritical_io_vproxy_pni_test_NoAllocUpcall_INIT", MemorySegment.class);
        try {
            initMH.invoke(execNoAlloc);
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }

    private static Interface IMPL = null;

    public static void setImpl(Interface impl) {
        java.util.Objects.requireNonNull(impl);
        IMPL = impl;
    }

    public interface Interface {
        io.vproxy.pni.test.ObjectStruct execNoAlloc();
    }
}
// metadata.generator-version: pni test
// sha256:2923f6af322d4a3d8c7e11f88031df8c4060e4c7d4749f307951424754e0d2aa
