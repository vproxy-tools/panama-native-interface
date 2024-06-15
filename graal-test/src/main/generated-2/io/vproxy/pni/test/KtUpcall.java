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

public class KtUpcall {
    private static final Arena ARENA = Arena.ofShared();

    public static MemorySegment helloworld;
    public static final CEntryPointLiteral<CFunctionPointer> helloworldCEPL = GraalUtils.defineCFunctionByName(new PNILinkOptions(), io.vproxy.pni.test.KtUpcall.class, "helloworld");

    @CEntryPoint
    public static VoidPointer helloworld(IsolateThread THREAD, int i, long l, VoidPointer return_PTR) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.KtUpcall#helloworld");
            System.exit(1);
        }
        var return_ = MemorySegment.ofAddress(return_PTR.rawValue());
        var RESULT = IMPL.helloworld(
            i,
            l,
            new io.vproxy.pni.test.KtStruct(return_)
        );
        return WordFactory.pointer(RESULT == null ? 0 : RESULT.MEMORY.address());
    }

    private static void setNativeImpl() {
        helloworld = MemorySegment.ofAddress(helloworldCEPL.getFunctionPointer().rawValue());

        var initMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), void.class, "JavaCritical_io_vproxy_pni_test_KtUpcall_INIT", MemorySegment.class);
        try {
            initMH.invoke(helloworld);
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
        helloworld = PanamaUtils.lookupFunctionPointer(new PNILookupOptions(), "JavaCritical_io_vproxy_pni_test_KtUpcall_helloworld").orElseThrow(() -> new NullPointerException("JavaCritical_io_vproxy_pni_test_KtUpcall_helloworld"));
    }

    private static Interface IMPL = null;

    public static void setImpl(Interface impl) {
        java.util.Objects.requireNonNull(impl);
        IMPL = impl;
        setNativeImpl();
    }

    public interface Interface {
        io.vproxy.pni.test.KtStruct helloworld(int i, long l, io.vproxy.pni.test.KtStruct return_);
    }
}
// metadata.generator-version: pni test
// sha256:ae98383fc27a111b84aaf932123df9e8ee6e3baec7bd8f4e5a9351f127a65c12
