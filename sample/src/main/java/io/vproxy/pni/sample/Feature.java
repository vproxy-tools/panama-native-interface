package io.vproxy.pni.sample;

import io.vproxy.pni.*;
import java.lang.foreign.*;
import java.nio.ByteBuffer;
import org.graalvm.nativeimage.hosted.*;

public class Feature implements org.graalvm.nativeimage.hosted.Feature {
    @Override
    public void duringSetup(DuringSetupAccess access) {
        /* Java_io_vproxy_pni_sample_SampleFunctions_read */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(int.class /* fd */, io.vproxy.pni.sample.MBuf.LAYOUT.getClass() /* buf */));

        /* Java_io_vproxy_pni_sample_NativeFunctions_openIPv4TcpSocket */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(), Linker.Option.isTrivial());

        /* Java_io_vproxy_pni_sample_NativeFunctions_bindIPv4 */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(int.class /* fd */, int.class /* ipv4HostOrder */, int.class /* port */), Linker.Option.isTrivial());

        /* Java_io_vproxy_pni_sample_NativeFunctions_listen */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(int.class /* fd */, int.class /* n */), Linker.Option.isTrivial());

        /* Java_io_vproxy_pni_sample_NativeFunctions_accept */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(int.class /* fd */));

        /* Java_io_vproxy_pni_sample_NativeFunctions_close */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(int.class /* fd */), Linker.Option.isTrivial());

        /* Java_io_vproxy_pni_sample_NativeFunctions_write */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(int.class /* fd */, MemorySegment.class /* mem */, int.class /* off */, int.class /* len */));

        /* Java_io_vproxy_pni_sample_NativeFunctions_read */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(int.class /* fd */, MemorySegment.class /* mem */, int.class /* off */, int.class /* len */));
    }
}
// metadata.generator-version: pni test
// sha256:cd2731d973e91af51fbcfc3fcd8416a18c2b767219db854ddbc427a8ae9c5bb3
