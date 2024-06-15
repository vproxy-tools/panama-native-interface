package io.vproxy.pni.graal.test;

import io.vproxy.pni.*;
import io.vproxy.pni.hack.*;
import io.vproxy.pni.graal.*;
import io.vproxy.r.org.graalvm.nativeimage.*;
import java.lang.foreign.*;
import java.nio.ByteBuffer;
import org.graalvm.nativeimage.*;
import org.graalvm.nativeimage.hosted.*;

public class Feature implements org.graalvm.nativeimage.hosted.Feature {
    @Override
    public void duringSetup(DuringSetupAccess access) {
        /* PNIFunc & PNIRef & GraalThread */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(void.class, MemorySegment.class), PanamaHack.getCriticalOption());
        RuntimeClassInitialization.initializeAtBuildTime(GraalPNIFunc.class);
        RuntimeClassInitialization.initializeAtBuildTime(GraalPNIRef.class);
        RuntimeClassInitialization.initializeAtBuildTime(PanamaHack.class);
        RuntimeClassInitialization.initializeAtBuildTime(GetSetUtf8String.implClass());
        RuntimeClassInitialization.initializeAtBuildTime(VarHandleW.implClass());
        /* ImageInfo */
        RuntimeClassInitialization.initializeAtRunTime(ImageInfoDelegate.class);
        for (var m : ImageInfo.class.getMethods()) {
            RuntimeReflection.register(m);
        }

        /* JavaCritical_io_vproxy_pni_graal_test_Invoke_invokeSum */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(int.class, MemorySegment.class /* func */, MemorySegment.class /* thread */, int.class /* a */, int.class /* b */));

        /* JavaCritical_io_vproxy_pni_graal_test_Invoke_invokePtr */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(void.class, MemorySegment.class /* func */, MemorySegment.class /* thread */, int.class /* a */, MemorySegment.class /* p */));

        /* JavaCritical_io_vproxy_pni_graal_test_Invoke_releaseRef */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(void.class, PNIRef.class /* ref */));

        /* JavaCritical_io_vproxy_pni_graal_test_Invoke_callFunc */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(int.class, io.vproxy.pni.CallSite.class /* func */));

        /* JavaCritical_io_vproxy_pni_graal_test_Invoke_invokeDoNothingUpcall */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(void.class, MemorySegment.class /* func */));

        /* JavaCritical_io_vproxy_pni_graal_test_Invoke_invokeIntUpcall */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(int.class, MemorySegment.class /* func */, int.class /* a */));

        /* JavaCritical_io_vproxy_pni_graal_test_Invoke_invokeRefUpcall */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(int.class, MemorySegment.class /* func */, PNIRef.class /* ref */));

        /* JavaCritical_io_vproxy_pni_graal_test_Invoke_invokeFuncUpcall */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(int.class, MemorySegment.class /* func */, io.vproxy.pni.CallSite.class /* ff */));

        /* JavaCritical_io_vproxy_pni_graal_test_Invoke_invokeReturnSegUpcall */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(MemorySegment.class, MemorySegment.class /* func */));

        /* graal upcall for io.vproxy.pni.graal.test.Upcall */
        RuntimeClassInitialization.initializeAtBuildTime(io.vproxy.pni.graal.test.Upcall.class);
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(void.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class), PanamaHack.getCriticalOption());
    }
}
// metadata.generator-version: pni test
// sha256:5b86e9436130fd9259760253c921dbcedf29c87a39daa1336b999298a886589d
