package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;
import io.vproxy.pni.graal.*;
import org.graalvm.nativeimage.*;
import org.graalvm.nativeimage.c.function.*;
import org.graalvm.nativeimage.c.type.VoidPointer;
import org.graalvm.word.WordFactory;

public class NoAllocUpcall {
    private static final Arena ARENA = Arena.ofShared();

    public static MemorySegment execNoAlloc;
    public static final CEntryPointLiteral<CFunctionPointer> execNoAllocCEPL = GraalUtils.defineCFunctionByName(io.vproxy.pni.test.NoAllocUpcall.class, "execNoAlloc");

    @CEntryPoint
    public static VoidPointer execNoAlloc(IsolateThread THREAD) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.test.NoAllocUpcall#execNoAlloc");
            System.exit(1);
        }
        var RESULT = IMPL.execNoAlloc();
        return WordFactory.pointer(RESULT == null ? 0 : RESULT.MEMORY.address());
    }

    private static void setNativeImpl() {
        execNoAlloc = MemorySegment.ofAddress(execNoAllocCEPL.getFunctionPointer().rawValue());

        var initMH = PanamaUtils.lookupPNICriticalFunction(true, void.class, "JavaCritical_io_vproxy_pni_test_NoAllocUpcall_INIT", MemorySegment.class);
        try {
            initMH.invoke(execNoAlloc);
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
        execNoAlloc = PanamaUtils.lookupFunctionPointer("JavaCritical_io_vproxy_pni_test_NoAllocUpcall_execNoAlloc").orElseThrow(() -> new NullPointerException("JavaCritical_io_vproxy_pni_test_NoAllocUpcall_execNoAlloc"));
    }

    private static Interface IMPL = null;

    public static void setImpl(Interface impl) {
        java.util.Objects.requireNonNull(impl);
        IMPL = impl;
        setNativeImpl();
    }

    public interface Interface {
        io.vproxy.pni.test.ObjectStruct execNoAlloc();
    }
}
// metadata.generator-version: pni test
// sha256:1318d58fc92b4931ed550a11ae70e3d22c159a21edce7f44d006c34f2ac4c624
