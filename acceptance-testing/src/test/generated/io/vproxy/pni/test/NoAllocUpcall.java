package io.vproxy.pni.test;

import io.vproxy.pni.*;
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
        execNoAlloc = PanamaUtils.defineCFunction(ARENA, execNoAllocMH, MemorySegment.class);

        var initMH = PanamaUtils.lookupPNICriticalFunction(true, void.class, "JavaCritical_io_vproxy_pni_test_NoAllocUpcall_INIT", MemorySegment.class);
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
// sha256:374985f8daaf10867a16f5dc180267f94aabca16c2f34b600304b28aeb45ae24
