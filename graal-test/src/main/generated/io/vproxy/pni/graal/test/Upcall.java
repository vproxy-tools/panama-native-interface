package io.vproxy.pni.graal.test;

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

public class Upcall {
    private static final Arena ARENA = Arena.ofShared();

    public static MemorySegment doNothingUpcall;
    public static final CEntryPointLiteral<CFunctionPointer> doNothingUpcallCEPL = GraalUtils.defineCFunctionByName(io.vproxy.pni.graal.test.Upcall.class, "doNothingUpcall");

    @CEntryPoint
    public static void doNothingUpcall(IsolateThread THREAD) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.graal.test.Upcall#doNothingUpcall");
            System.exit(1);
        }
        IMPL.doNothingUpcall();
    }

    public static MemorySegment intUpcall;
    public static final CEntryPointLiteral<CFunctionPointer> intUpcallCEPL = GraalUtils.defineCFunctionByName(io.vproxy.pni.graal.test.Upcall.class, "intUpcall");

    @CEntryPoint
    public static int intUpcall(IsolateThread THREAD, int a) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.graal.test.Upcall#intUpcall");
            System.exit(1);
        }
        var RESULT = IMPL.intUpcall(
            a
        );
        return RESULT;
    }

    public static MemorySegment refUpcall;
    public static final CEntryPointLiteral<CFunctionPointer> refUpcallCEPL = GraalUtils.defineCFunctionByName(io.vproxy.pni.graal.test.Upcall.class, "refUpcall");

    @CEntryPoint
    public static int refUpcall(IsolateThread THREAD, VoidPointer refPTR) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.graal.test.Upcall#refUpcall");
            System.exit(1);
        }
        var ref = MemorySegment.ofAddress(refPTR.rawValue());
        var RESULT = IMPL.refUpcall(
            (ref.address() == 0 ? null : PNIRef.getRef(ref))
        );
        return RESULT;
    }

    public static MemorySegment funcUpcall;
    public static final CEntryPointLiteral<CFunctionPointer> funcUpcallCEPL = GraalUtils.defineCFunctionByName(io.vproxy.pni.graal.test.Upcall.class, "funcUpcall");

    @CEntryPoint
    public static int funcUpcall(IsolateThread THREAD, VoidPointer funcPTR) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.graal.test.Upcall#funcUpcall");
            System.exit(1);
        }
        var func = MemorySegment.ofAddress(funcPTR.rawValue());
        var RESULT = IMPL.funcUpcall(
            (func.address() == 0 ? null : PNIFunc.VoidFunc.of(func).getCallSite())
        );
        return RESULT;
    }

    public static MemorySegment returnSegUpcall;
    public static final CEntryPointLiteral<CFunctionPointer> returnSegUpcallCEPL = GraalUtils.defineCFunctionByName(io.vproxy.pni.graal.test.Upcall.class, "returnSegUpcall");

    @CEntryPoint
    public static VoidPointer returnSegUpcall(IsolateThread THREAD) {
        if (IMPL == null) {
            System.out.println("io.vproxy.pni.graal.test.Upcall#returnSegUpcall");
            System.exit(1);
        }
        var RESULT = IMPL.returnSegUpcall();
        return WordFactory.pointer(RESULT == null ? 0 : RESULT.address());
    }

    private static void setNativeImpl() {
        doNothingUpcall = MemorySegment.ofAddress(doNothingUpcallCEPL.getFunctionPointer().rawValue());
        intUpcall = MemorySegment.ofAddress(intUpcallCEPL.getFunctionPointer().rawValue());
        refUpcall = MemorySegment.ofAddress(refUpcallCEPL.getFunctionPointer().rawValue());
        funcUpcall = MemorySegment.ofAddress(funcUpcallCEPL.getFunctionPointer().rawValue());
        returnSegUpcall = MemorySegment.ofAddress(returnSegUpcallCEPL.getFunctionPointer().rawValue());

        var initMH = PanamaUtils.lookupPNICriticalFunction(true, void.class, "JavaCritical_io_vproxy_pni_graal_test_Upcall_INIT", MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class);
        try {
            initMH.invoke(doNothingUpcall, intUpcall, refUpcall, funcUpcall, returnSegUpcall);
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
        doNothingUpcall = PanamaUtils.lookupFunctionPointer("JavaCritical_io_vproxy_pni_graal_test_Upcall_doNothingUpcall").orElseThrow(() -> new NullPointerException("JavaCritical_io_vproxy_pni_graal_test_Upcall_doNothingUpcall"));
        intUpcall = PanamaUtils.lookupFunctionPointer("JavaCritical_io_vproxy_pni_graal_test_Upcall_intUpcall").orElseThrow(() -> new NullPointerException("JavaCritical_io_vproxy_pni_graal_test_Upcall_intUpcall"));
        refUpcall = PanamaUtils.lookupFunctionPointer("JavaCritical_io_vproxy_pni_graal_test_Upcall_refUpcall").orElseThrow(() -> new NullPointerException("JavaCritical_io_vproxy_pni_graal_test_Upcall_refUpcall"));
        funcUpcall = PanamaUtils.lookupFunctionPointer("JavaCritical_io_vproxy_pni_graal_test_Upcall_funcUpcall").orElseThrow(() -> new NullPointerException("JavaCritical_io_vproxy_pni_graal_test_Upcall_funcUpcall"));
        returnSegUpcall = PanamaUtils.lookupFunctionPointer("JavaCritical_io_vproxy_pni_graal_test_Upcall_returnSegUpcall").orElseThrow(() -> new NullPointerException("JavaCritical_io_vproxy_pni_graal_test_Upcall_returnSegUpcall"));
    }

    private static Interface IMPL = null;

    public static void setImpl(Interface impl) {
        java.util.Objects.requireNonNull(impl);
        IMPL = impl;
        setNativeImpl();
    }

    public interface Interface {
        void doNothingUpcall();

        int intUpcall(int a);

        int refUpcall(java.lang.Integer ref);

        int funcUpcall(io.vproxy.pni.CallSite<Void> func);

        MemorySegment returnSegUpcall();
    }
}
// metadata.generator-version: pni test
// sha256:1933812f86b08225b83e2303e56630d1f6db5015f2cf5630816446a7e128c3ed
