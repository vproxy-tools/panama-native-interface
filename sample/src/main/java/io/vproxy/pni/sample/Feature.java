package io.vproxy.pni.sample;

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
        /* ImageInfo */
        RuntimeClassInitialization.initializeAtRunTime(ImageInfoDelegate.class);
        for (var m : ImageInfo.class.getMethods()) {
            RuntimeReflection.register(m);
        }

        /* Java_io_vproxy_pni_sample_NativeFunctions_openIPv4TcpSocket */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(), PanamaHack.getCriticalOption());
        RuntimeReflection.registerAllConstructors(java.io.IOException.class);
        for (var CONS : java.io.IOException.class.getConstructors()) {
            RuntimeReflection.register(CONS);
        }

        /* Java_io_vproxy_pni_sample_NativeFunctions_bindIPv4 */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(int.class /* fd */, int.class /* ipv4HostOrder */, int.class /* port */), PanamaHack.getCriticalOption());
        RuntimeReflection.registerAllConstructors(java.io.IOException.class);
        for (var CONS : java.io.IOException.class.getConstructors()) {
            RuntimeReflection.register(CONS);
        }

        /* Java_io_vproxy_pni_sample_NativeFunctions_listen */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(int.class /* fd */, int.class /* n */), PanamaHack.getCriticalOption());
        RuntimeReflection.registerAllConstructors(java.io.IOException.class);
        for (var CONS : java.io.IOException.class.getConstructors()) {
            RuntimeReflection.register(CONS);
        }

        /* Java_io_vproxy_pni_sample_NativeFunctions_accept */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(int.class /* fd */));
        RuntimeReflection.registerAllConstructors(java.io.IOException.class);
        for (var CONS : java.io.IOException.class.getConstructors()) {
            RuntimeReflection.register(CONS);
        }

        /* Java_io_vproxy_pni_sample_NativeFunctions_close */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(int.class /* fd */), PanamaHack.getCriticalOption());

        /* Java_io_vproxy_pni_sample_NativeFunctions_write */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(int.class /* fd */, MemorySegment.class /* mem */, int.class /* off */, int.class /* len */));
        RuntimeReflection.registerAllConstructors(java.io.IOException.class);
        for (var CONS : java.io.IOException.class.getConstructors()) {
            RuntimeReflection.register(CONS);
        }

        /* Java_io_vproxy_pni_sample_NativeFunctions_read */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(int.class /* fd */, MemorySegment.class /* mem */, int.class /* off */, int.class /* len */));
        RuntimeReflection.registerAllConstructors(java.io.IOException.class);
        for (var CONS : java.io.IOException.class.getConstructors()) {
            RuntimeReflection.register(CONS);
        }

        /* Java_io_vproxy_pni_sample_SampleFunctions_read */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(int.class /* fd */, MemoryLayout.class /* io.vproxy.pni.sample.MBuf.LAYOUT.getClass() */ /* buf */));
        RuntimeReflection.registerAllConstructors(java.io.IOException.class);
        for (var CONS : java.io.IOException.class.getConstructors()) {
            RuntimeReflection.register(CONS);
        }
    }
}
// metadata.generator-version: pni test
// sha256:e6bbced6a1bc4ce281286bafeabbc2aa7808363130b6e06c1aeb41c402780bfc
