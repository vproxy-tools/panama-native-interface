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

public class CustomNativeTypeUpcall {
    private static final Arena ARENA = Arena.ofShared();

    public static MemorySegment exec;
    public static final CEntryPointLiteral<CFunctionPointer> execCEPL = GraalUtils.defineCFunctionByName(new PNILinkOptions(), io.vproxy.pni.test.CustomNativeTypeUpcall.class, "exec");

    @CEntryPoint
    public static VoidPointer exec(IsolateThread THREAD, VoidPointer oPTR) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.CustomNativeTypeUpcall#exec");
            System.exit(1);
        }
        var o = MemorySegment.ofAddress(oPTR.rawValue());
        var RESULT = IMPL.exec(
            (o.address() == 0 ? null : o)
        );
        return WordFactory.pointer(RESULT == null ? 0 : RESULT.address());
    }

    private static void setNativeImpl() {
        exec = MemorySegment.ofAddress(execCEPL.getFunctionPointer().rawValue());

        var initMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), void.class, "JavaCritical_io_vproxy_pni_test_CustomNativeTypeUpcall_INIT", MemorySegment.class);
        try {
            initMH.invoke(exec);
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
        exec = PanamaUtils.lookupFunctionPointer(new PNILookupOptions(), "JavaCritical_io_vproxy_pni_test_CustomNativeTypeUpcall_exec").orElseThrow(() -> new NullPointerException("JavaCritical_io_vproxy_pni_test_CustomNativeTypeUpcall_exec"));
    }

    private static Interface IMPL = null;

    public static void setImpl(Interface impl) {
        java.util.Objects.requireNonNull(impl);
        IMPL = impl;
        setNativeImpl();
    }

    public interface Interface {
        MemorySegment exec(MemorySegment o);
    }
}
// metadata.generator-version: pni test
// sha256:d6880f4b81a91814e70142cb610e74743aaa730cfcc8a853723f178107472815
