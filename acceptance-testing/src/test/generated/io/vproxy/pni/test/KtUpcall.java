package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class KtUpcall {
    private static final Arena ARENA = Arena.ofShared();

    public static final MemorySegment helloworld;

    private static MemorySegment helloworld(int i, long l, MemorySegment return_) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.KtUpcall#helloworld");
            System.exit(1);
        }
        var RESULT = IMPL.helloworld(
            i,
            l,
            new io.vproxy.pni.test.KtStruct(return_)
        );
        return RESULT == null ? MemorySegment.NULL : RESULT.MEMORY;
    }

    static {
        MethodHandle helloworldMH;
        try {
            helloworldMH = MethodHandles.lookup().findStatic(io.vproxy.pni.test.KtUpcall.class, "helloworld", MethodType.methodType(MemorySegment.class, int.class, long.class, MemorySegment.class));
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
        helloworld = PanamaUtils.defineCFunction(new PNILinkOptions(), ARENA, helloworldMH, MemorySegment.class, int.class, long.class, MemorySegment.class);

        var initMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), void.class, "JavaCritical_io_vproxy_pni_test_KtUpcall_INIT", MemorySegment.class);
        try {
            initMH.invoke(helloworld);
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
        io.vproxy.pni.test.KtStruct helloworld(int i, long l, io.vproxy.pni.test.KtStruct return_);
    }
}
// metadata.generator-version: pni test
// sha256:57551f3588c33ded2c0e9ec124ff62e64c9f0ff640a8b03b23989fbb7ee1db5f
