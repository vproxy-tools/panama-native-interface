package io.vproxy.pni.sample;

import io.vproxy.pni.*;
import java.lang.foreign.*;
import java.nio.ByteBuffer;
import org.graalvm.nativeimage.hosted.*;

public class Feature implements org.graalvm.nativeimage.hosted.Feature {
    @Override
    public void duringSetup(DuringSetupAccess access) {
        /* Java_io_vproxy_pni_sample_NativeFunctions_openIPv4TcpSocket */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(), Linker.Option.isTrivial());
        RuntimeReflection.registerAllConstructors(java.io.IOException.class);

        /* Java_io_vproxy_pni_sample_NativeFunctions_bindIPv4 */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(int.class /* fd */, int.class /* ipv4HostOrder */, int.class /* port */), Linker.Option.isTrivial());
        RuntimeReflection.registerAllConstructors(java.io.IOException.class);

        /* Java_io_vproxy_pni_sample_NativeFunctions_listen */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(int.class /* fd */, int.class /* n */), Linker.Option.isTrivial());
        RuntimeReflection.registerAllConstructors(java.io.IOException.class);

        /* Java_io_vproxy_pni_sample_NativeFunctions_accept */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(int.class /* fd */));
        RuntimeReflection.registerAllConstructors(java.io.IOException.class);

        /* Java_io_vproxy_pni_sample_NativeFunctions_close */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(int.class /* fd */), Linker.Option.isTrivial());

        /* Java_io_vproxy_pni_sample_NativeFunctions_write */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(int.class /* fd */, MemorySegment.class /* mem */, int.class /* off */, int.class /* len */));
        RuntimeReflection.registerAllConstructors(java.io.IOException.class);

        /* Java_io_vproxy_pni_sample_NativeFunctions_read */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(int.class /* fd */, MemorySegment.class /* mem */, int.class /* off */, int.class /* len */));
        RuntimeReflection.registerAllConstructors(java.io.IOException.class);

        /* Java_io_vproxy_pni_sample_SampleFunctions_read */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(int.class /* fd */, io.vproxy.pni.sample.MBuf.LAYOUT.getClass() /* buf */));
        RuntimeReflection.registerAllConstructors(java.io.IOException.class);
    }
}
// metadata.generator-version: pni test
// sha256:d69784d8964ff5cd83b137c0ecebc4bc424d50e74356f32ad65e7ff37ad9e7b3
