package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.hack.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class CustomNativeTypeUpcall {
    private static final Arena ARENA = Arena.ofShared();

    public static final MemorySegment exec;

    private static MemorySegment exec(MemorySegment o) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.CustomNativeTypeUpcall#exec");
            System.exit(1);
        }
        var RESULT = IMPL.exec(
            (o.address() == 0 ? null : o)
        );
        return RESULT == null ? MemorySegment.NULL : RESULT;
    }

    static {
        MethodHandle execMH;
        try {
            execMH = MethodHandles.lookup().findStatic(io.vproxy.pni.test.CustomNativeTypeUpcall.class, "exec", MethodType.methodType(MemorySegment.class, MemorySegment.class));
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
        exec = PanamaUtils.defineCFunction(new PNILinkOptions(), ARENA, execMH, MemorySegment.class, MemorySegment.class);

        var initMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), void.class, "JavaCritical_io_vproxy_pni_test_CustomNativeTypeUpcall_INIT", MemorySegment.class);
        try {
            initMH.invoke(exec);
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
        MemorySegment exec(MemorySegment o);
    }
}
// metadata.generator-version: pni test
// sha256:84d9036fb64eaf725f9e786f40fc96766ce6318ac80e235c6d8293e4e061d259
