package io.vproxy.pni.sample;

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

        /* Java_io_vproxy_pni_sample_NativeFunctions_openIPv4TcpSocket */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(), Linker.Option.isTrivial());
        RuntimeReflection.registerAllConstructors(java.io.IOException.class);
        for (var CONS : java.io.IOException.class.getConstructors()) {
            RuntimeReflection.register(CONS);
        }

        /* Java_io_vproxy_pni_sample_NativeFunctions_bindIPv4 */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(int.class /* fd */, int.class /* ipv4HostOrder */, int.class /* port */), Linker.Option.isTrivial());
        RuntimeReflection.registerAllConstructors(java.io.IOException.class);
        for (var CONS : java.io.IOException.class.getConstructors()) {
            RuntimeReflection.register(CONS);
        }

        /* Java_io_vproxy_pni_sample_NativeFunctions_listen */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(int.class /* fd */, int.class /* n */), Linker.Option.isTrivial());
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
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(int.class /* fd */), Linker.Option.isTrivial());

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
// sha256:087eb3423d7f1431688754f14a066d27a409cb23d424f643ed323d5346382b3f
