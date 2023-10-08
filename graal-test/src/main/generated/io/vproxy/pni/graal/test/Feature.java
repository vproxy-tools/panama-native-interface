package io.vproxy.pni.graal.test;

import io.vproxy.pni.*;
import java.lang.foreign.*;
import java.nio.ByteBuffer;
import org.graalvm.nativeimage.hosted.*;

public class Feature implements org.graalvm.nativeimage.hosted.Feature {
    @Override
    public void duringSetup(DuringSetupAccess access) {
        /* PNIFunc & PNIRef */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(void.class, MemorySegment.class));

        /* JavaCritical_io_vproxy_pni_graal_test_Invoke_invokeSum */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(int.class, MemorySegment.class /* func */, MemorySegment.class /* thread */, int.class /* a */, int.class /* b */));

        /* JavaCritical_io_vproxy_pni_graal_test_Invoke_invokePtr */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(void.class, MemorySegment.class /* func */, MemorySegment.class /* thread */, int.class /* a */, MemorySegment.class /* p */));
    }
}
// metadata.generator-version: pni test
// sha256:c0d672b07ab3542a00441952e2c0b551636e7247b344f4d815431588a780677b
