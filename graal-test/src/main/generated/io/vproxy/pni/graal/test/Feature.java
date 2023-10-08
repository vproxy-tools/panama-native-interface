package io.vproxy.pni.graal.test;

import io.vproxy.pni.*;
import java.lang.foreign.*;
import java.nio.ByteBuffer;
import org.graalvm.nativeimage.hosted.*;

public class Feature implements org.graalvm.nativeimage.hosted.Feature {
    @Override
    public void duringSetup(DuringSetupAccess access) {
        /* JavaCritical_io_vproxy_pni_graal_test_Invoke_invokeSum */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(int.class, MemorySegment.class /* func */, MemorySegment.class /* thread */, int.class /* a */, int.class /* b */));
    }
}
// metadata.generator-version: pni test
// sha256:008bbb43464c5da34c9ba16aa9a8b3abacd11577eaafba370639e785ccfa9a7c
