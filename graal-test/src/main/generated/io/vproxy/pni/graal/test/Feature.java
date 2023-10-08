package io.vproxy.pni.graal.test;

import io.vproxy.pni.*;
import io.vproxy.pni.graal.*;
import java.lang.foreign.*;
import java.nio.ByteBuffer;
import org.graalvm.nativeimage.hosted.*;

public class Feature implements org.graalvm.nativeimage.hosted.Feature {
    @Override
    public void duringSetup(DuringSetupAccess access) {
        /* PNIFunc & PNIRef & GraalThread */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(void.class, MemorySegment.class), Linker.Option.isTrivial());
        RuntimeClassInitialization.initializeAtBuildTime(GraalPNIFunc.class);
        RuntimeClassInitialization.initializeAtBuildTime(GraalPNIRef.class);

        /* JavaCritical_io_vproxy_pni_graal_test_Invoke_invokeSum */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(int.class, MemorySegment.class /* func */, MemorySegment.class /* thread */, int.class /* a */, int.class /* b */));

        /* JavaCritical_io_vproxy_pni_graal_test_Invoke_invokePtr */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(void.class, MemorySegment.class /* func */, MemorySegment.class /* thread */, int.class /* a */, MemorySegment.class /* p */));

        /* JavaCritical_io_vproxy_pni_graal_test_Invoke_releaseRef */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(void.class, PNIRef.class /* ref */));

        /* JavaCritical_io_vproxy_pni_graal_test_Invoke_callFunc */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(int.class, io.vproxy.pni.CallSite.class /* func */));
    }
}
// metadata.generator-version: pni test
// sha256:81266a47c00153ed76aa79beb1917681ed486d083727c79e77f83b0653c10b55
