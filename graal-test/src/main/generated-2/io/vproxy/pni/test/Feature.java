package io.vproxy.pni.test;

import io.vproxy.pni.*;
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
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(void.class, MemorySegment.class), Linker.Option.isTrivial());
        RuntimeClassInitialization.initializeAtBuildTime(GraalPNIFunc.class);
        RuntimeClassInitialization.initializeAtBuildTime(GraalPNIRef.class);
        /* ImageInfo */
        RuntimeClassInitialization.initializeAtRunTime(ImageInfoDelegate.class);
        for (var m : ImageInfo.class.getMethods()) {
            RuntimeReflection.register(m);
        }

        /* JavaCritical_io_vproxy_pni_test_AlignBaseClass_aaaa */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(short.class, MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_AlignBaseClass_size0 */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(long.class, MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_AlignChildClass_bbbb */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(int.class, MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_AlignChildClass_cccc */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(int.class, MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_AlignChildClass_size */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(long.class, MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_AlignClass_aaaa */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(byte.class, MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_AlignClass_bbbb */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(short.class, MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_AlignClass_cccc */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(long.class, MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_AlignClass_size */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(long.class, MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_AlignField_aaaa */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(byte.class, MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_AlignField_bbbb */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(byte.class, MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_AlignField_cccc */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(int.class, MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_AlignField_size */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(long.class, MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_AlignField2_aaaa */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(byte.class, MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_AlignField2_bbbb */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(byte.class, MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_AlignField2_cccc */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(int.class, MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_AlignField2_size */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(long.class, MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_AlignField3_aaaa */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(short.class, MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_AlignField3_bbbb */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(int.class, MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_AlignField3_cccc */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(int.class, MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_AlignField3_size */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(long.class, MemorySegment.class /* self */));

/* JavaCritical_io_vproxy_pni_test_AlwaysAlignedSizeof___getLayoutByteSize */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(long.class), Linker.Option.isTrivial());

        /* Java_io_vproxy_pni_test_BaseClass_aaa */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */, byte.class /* a */));

        /* JavaCritical_io_vproxy_pni_test_BitField_set */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(void.class, MemorySegment.class /* self */, byte.class /* a */, byte.class /* a2 */, byte.class /* b */, byte.class /* b2 */, short.class /* c */, short.class /* c2 */, short.class /* d */, short.class /* d2 */, short.class /* e */, short.class /* e2 */, int.class /* f */, int.class /* f2 */, int.class /* g */, int.class /* g2 */, int.class /* h */, int.class /* h2 */, int.class /* i */, int.class /* i2 */, long.class /* j */, long.class /* j2 */, long.class /* k */, long.class /* k2 */, long.class /* l */, long.class /* l2 */, long.class /* m */, long.class /* m2 */));

        /* JavaCritical_io_vproxy_pni_test_BitField_a */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(byte.class, MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_BitField_a2 */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(byte.class, MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_BitField_b */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(byte.class, MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_BitField_b2 */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(byte.class, MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_BitField_c */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(short.class, MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_BitField_c2 */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(short.class, MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_BitField_d */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(short.class, MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_BitField_d2 */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(short.class, MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_BitField_e */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(short.class, MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_BitField_e2 */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(short.class, MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_BitField_f */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(int.class, MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_BitField_f2 */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(int.class, MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_BitField_g */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(int.class, MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_BitField_g2 */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(int.class, MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_BitField_h */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(int.class, MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_BitField_h2 */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(int.class, MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_BitField_i */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(int.class, MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_BitField_i2 */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(int.class, MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_BitField_j */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(long.class, MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_BitField_j2 */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(long.class, MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_BitField_k */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(long.class, MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_BitField_k2 */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(long.class, MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_BitField_l */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(long.class, MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_BitField_l2 */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(long.class, MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_BitField_m */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(long.class, MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_BitField_m2 */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(long.class, MemorySegment.class /* self */));

        /* Java_io_vproxy_pni_test_ChildClass_xxx */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */, short.class /* x */));

        /* Java_io_vproxy_pni_test_ChildOfPacked_xxx */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */, int.class /* x */));

        /* Java_io_vproxy_pni_test_ChildOfPacked_ooo */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */, MemoryLayout.class /* io.vproxy.pni.test.ObjectStruct.LAYOUT.getClass() */ /* o */));

        /* Java_io_vproxy_pni_test_DefiningCFunction_upcallVoidNoParam */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* func */));

        /* Java_io_vproxy_pni_test_DefiningCFunction_upcallVoid1Param */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* func */, MemorySegment.class /* data */));

        /* Java_io_vproxy_pni_test_DefiningCFunction_upcallVoid2Param */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* func */, MemorySegment.class /* data */, byte.class /* b */));

        /* Java_io_vproxy_pni_test_DefiningCFunction_upcallVoid3Param */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* func */, MemorySegment.class /* data */, boolean.class /* z */, char.class /* c */));

        /* Java_io_vproxy_pni_test_DefiningCFunction_upcallVoid4Param */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* func */, MemorySegment.class /* data */, double.class /* d */, float.class /* f */, int.class /* i */));

        /* Java_io_vproxy_pni_test_DefiningCFunction_upcallVoid3Param2 */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* func */, MemorySegment.class /* data */, long.class /* l */, short.class /* s */));

        /* Java_io_vproxy_pni_test_DefiningCFunction_upcallReturnByteNoParam */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* func */));

        /* Java_io_vproxy_pni_test_DefiningCFunction_upcallReturnBoolNoParam */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* func */));

        /* Java_io_vproxy_pni_test_DefiningCFunction_upcallReturnCharNoParam */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* func */));

        /* Java_io_vproxy_pni_test_DefiningCFunction_upcallReturnDoubleNoParam */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* func */));

        /* Java_io_vproxy_pni_test_DefiningCFunction_upcallReturnFloatNoParam */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* func */));

        /* Java_io_vproxy_pni_test_DefiningCFunction_upcallReturnIntNoParam */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* func */));

        /* Java_io_vproxy_pni_test_DefiningCFunction_upcallReturnLongNoParam */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* func */));

        /* Java_io_vproxy_pni_test_DefiningCFunction_upcallReturnShortNoParam */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* func */));

        /* Java_io_vproxy_pni_test_DefiningCFunction_upcallReturnPointerNoParam */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* func */));

        /* Java_io_vproxy_pni_test_DefiningCFunction_upcallReturnByte1Param */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* func */, byte.class /* b */));

        /* Java_io_vproxy_pni_test_DefiningCFunction_upcallReturnBool1Param */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* func */, boolean.class /* z */));

        /* Java_io_vproxy_pni_test_DefiningCFunction_upcallReturnChar1Param */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* func */, char.class /* c */));

        /* Java_io_vproxy_pni_test_DefiningCFunction_upcallReturnDouble1Param */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* func */, double.class /* d */));

        /* Java_io_vproxy_pni_test_DefiningCFunction_upcallReturnFloat1Param */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* func */, float.class /* f */));

        /* Java_io_vproxy_pni_test_DefiningCFunction_upcallReturnInt1Param */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* func */, int.class /* i */));

        /* Java_io_vproxy_pni_test_DefiningCFunction_upcallReturnLong1Param */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* func */, long.class /* j */));

        /* Java_io_vproxy_pni_test_DefiningCFunction_upcallReturnShort1Param */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* func */, short.class /* s */));

        /* Java_io_vproxy_pni_test_DefiningCFunction_upcallReturnPointer1Param */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* func */, MemorySegment.class /* p */));

        /* Java_io_vproxy_pni_test_Func_func1 */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor());

        /* JavaCritical_io_vproxy_pni_test_Func_func1Critical */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(int.class));

        /* Java_io_vproxy_pni_test_Func_func2 */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor());
        RuntimeReflection.registerAllConstructors(java.io.IOException.class);
        for (var CONS : java.io.IOException.class.getConstructors()) {
            RuntimeReflection.register(CONS);
        }

        /* Java_io_vproxy_pni_test_Func_func3 */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(String.class /* ex */));
        RuntimeReflection.registerAllConstructors(java.io.IOException.class);
        for (var CONS : java.io.IOException.class.getConstructors()) {
            RuntimeReflection.register(CONS);
        }
        RuntimeReflection.registerAllConstructors(java.lang.UnsupportedOperationException.class);
        for (var CONS : java.lang.UnsupportedOperationException.class.getConstructors()) {
            RuntimeReflection.register(CONS);
        }

        /* Java_io_vproxy_pni_test_Func_write */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(int.class /* fd */, ByteBuffer.class /* buf */, int.class /* off */, int.class /* len */));
        RuntimeReflection.registerAllConstructors(java.io.IOException.class);
        for (var CONS : java.io.IOException.class.getConstructors()) {
            RuntimeReflection.register(CONS);
        }

        /* JavaCritical_io_vproxy_pni_test_Func_writeCritical */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(int.class, int.class /* fd */, ByteBuffer.class /* buf */, int.class /* off */, int.class /* len */));

        /* Java_io_vproxy_pni_test_Func_writeWithErrno */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(int.class /* fd */, ByteBuffer.class /* buf */, int.class /* off */, int.class /* len */));

        /* Java_io_vproxy_pni_test_Func_testErrno */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor());
        RuntimeReflection.registerAllConstructors(java.io.IOException.class);
        for (var CONS : java.io.IOException.class.getConstructors()) {
            RuntimeReflection.register(CONS);
        }

        /* Java_io_vproxy_pni_test_Func_writeByteArray */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(int.class /* fd */, MemorySegment.class /* buf */, int.class /* off */, int.class /* len */));
        RuntimeReflection.registerAllConstructors(java.io.IOException.class);
        for (var CONS : java.io.IOException.class.getConstructors()) {
            RuntimeReflection.register(CONS);
        }

        /* Java_io_vproxy_pni_test_Func_callJavaFromC */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(io.vproxy.pni.CallSite.class /* func */));

        /* JavaCritical_io_vproxy_pni_test_Func_callJavaFromCCritical */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(MemorySegment.class, io.vproxy.pni.CallSite.class /* func */));

        /* Java_io_vproxy_pni_test_Func_callJavaRefFromC */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(io.vproxy.pni.CallSite.class /* func */, PNIRef.class /* ref */));

        /* JavaCritical_io_vproxy_pni_test_Func_callJavaRefFromCCritical */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(void.class, io.vproxy.pni.CallSite.class /* func */, PNIRef.class /* ref */));

        /* Java_io_vproxy_pni_test_Func_callJavaMethodWithRefFromC */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* func */, PNIRef.class /* ref */, int.class /* a */));

        /* JavaCritical_io_vproxy_pni_test_Func_callJavaMethodWithRefFromCCritical */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(int.class, MemorySegment.class /* func */, PNIRef.class /* ref */, int.class /* a */));

        /* Java_io_vproxy_pni_test_GCCCompatibilityAlignField_init */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* Java_io_vproxy_pni_test_GCCCompatibilityAlignField_size */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* Java_io_vproxy_pni_test_GCCCompatibilityAlignFieldPacked_init */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* Java_io_vproxy_pni_test_GCCCompatibilityAlignFieldPacked_size */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* Java_io_vproxy_pni_test_GCCCompatibilityArrayZero_init */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* Java_io_vproxy_pni_test_GCCCompatibilityArrayZero_size */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* Java_io_vproxy_pni_test_GCCCompatibilityNonPackedArray_init */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* Java_io_vproxy_pni_test_GCCCompatibilityNonPackedArray_size */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* Java_io_vproxy_pni_test_GCCCompatibilityNonPackedContainNonPacked_init */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* Java_io_vproxy_pni_test_GCCCompatibilityNonPackedContainNonPacked_size */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* Java_io_vproxy_pni_test_GCCCompatibilityNonPackedContainPacked_init */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* Java_io_vproxy_pni_test_GCCCompatibilityNonPackedContainPacked_size */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* Java_io_vproxy_pni_test_GCCCompatibilityNormal_init */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* Java_io_vproxy_pni_test_GCCCompatibilityNormal_size */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* Java_io_vproxy_pni_test_GCCCompatibilityPacked_init */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* Java_io_vproxy_pni_test_GCCCompatibilityPacked_size */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* Java_io_vproxy_pni_test_GCCCompatibilityPackedAlignField_init */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* Java_io_vproxy_pni_test_GCCCompatibilityPackedAlignField_size */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* Java_io_vproxy_pni_test_GCCCompatibilityPackedAlignFieldSmallerAlign_init */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* Java_io_vproxy_pni_test_GCCCompatibilityPackedAlignFieldSmallerAlign_size */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* Java_io_vproxy_pni_test_GCCCompatibilityPackedArray_init */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* Java_io_vproxy_pni_test_GCCCompatibilityPackedArray_size */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* Java_io_vproxy_pni_test_GCCCompatibilityPackedContainNonPacked_init */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* Java_io_vproxy_pni_test_GCCCompatibilityPackedContainNonPacked_size */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_GCCompatibilityBitField_set */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(void.class, MemorySegment.class /* self */, byte.class /* a */, byte.class /* a2 */, byte.class /* b */, byte.class /* b2 */, short.class /* c */, short.class /* c2 */, short.class /* d */, short.class /* d2 */, short.class /* e */, short.class /* e2 */, int.class /* f */, int.class /* f2 */, int.class /* g */, int.class /* g2 */, int.class /* h */, int.class /* h2 */, int.class /* i */, int.class /* i2 */, long.class /* j */, long.class /* j2 */, long.class /* k */, long.class /* k2 */, long.class /* l */, long.class /* l2 */, long.class /* m */, long.class /* m2 */));

        /* JavaCritical_io_vproxy_pni_test_GCCompatibilityBitField_a */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(byte.class, MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_GCCompatibilityBitField_a2 */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(byte.class, MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_GCCompatibilityBitField_b */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(byte.class, MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_GCCompatibilityBitField_b2 */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(byte.class, MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_GCCompatibilityBitField_c */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(short.class, MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_GCCompatibilityBitField_c2 */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(short.class, MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_GCCompatibilityBitField_d */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(short.class, MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_GCCompatibilityBitField_d2 */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(short.class, MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_GCCompatibilityBitField_e */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(short.class, MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_GCCompatibilityBitField_e2 */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(short.class, MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_GCCompatibilityBitField_f */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(int.class, MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_GCCompatibilityBitField_f2 */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(int.class, MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_GCCompatibilityBitField_g */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(int.class, MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_GCCompatibilityBitField_g2 */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(int.class, MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_GCCompatibilityBitField_h */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(int.class, MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_GCCompatibilityBitField_h2 */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(int.class, MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_GCCompatibilityBitField_i */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(int.class, MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_GCCompatibilityBitField_i2 */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(int.class, MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_GCCompatibilityBitField_j */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(long.class, MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_GCCompatibilityBitField_j2 */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(long.class, MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_GCCompatibilityBitField_k */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(long.class, MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_GCCompatibilityBitField_k2 */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(long.class, MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_GCCompatibilityBitField_l */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(long.class, MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_GCCompatibilityBitField_l2 */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(long.class, MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_GCCompatibilityBitField_m */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(long.class, MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_GCCompatibilityBitField_m2 */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(long.class, MemorySegment.class /* self */));

        /* Java_io_vproxy_pni_test_GCCompatibilityNormalContainUnion_initB */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* Java_io_vproxy_pni_test_GCCompatibilityNormalContainUnion_initS */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* Java_io_vproxy_pni_test_GCCompatibilityNormalContainUnion_initN */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* Java_io_vproxy_pni_test_GCCompatibilityNormalContainUnion_initF */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* Java_io_vproxy_pni_test_GCCompatibilityNormalContainUnion_initD */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* Java_io_vproxy_pni_test_GCCompatibilityNormalContainUnion_initL */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* Java_io_vproxy_pni_test_GCCompatibilityNormalContainUnion_size */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* Java_io_vproxy_pni_test_GCCompatibilityPackedContainUnion_initB */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* Java_io_vproxy_pni_test_GCCompatibilityPackedContainUnion_initS */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* Java_io_vproxy_pni_test_GCCompatibilityPackedContainUnion_initN */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* Java_io_vproxy_pni_test_GCCompatibilityPackedContainUnion_initF */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* Java_io_vproxy_pni_test_GCCompatibilityPackedContainUnion_initD */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* Java_io_vproxy_pni_test_GCCompatibilityPackedContainUnion_initL */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* Java_io_vproxy_pni_test_GCCompatibilityPackedContainUnion_size */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* Java_io_vproxy_pni_test_GCCompatibilityStructAlign_init */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* Java_io_vproxy_pni_test_GCCompatibilityStructAlign_size */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* Java_io_vproxy_pni_test_Generic_simple */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(PNIRef.class /* o */));

        /* Java_io_vproxy_pni_test_Generic_simpleArr */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(PNIRef.class /* o */));

        /* Java_io_vproxy_pni_test_Generic_generic */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(PNIRef.class /* o */));

        /* Java_io_vproxy_pni_test_Generic_genericArr */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(PNIRef.class /* o */));

        /* Java_io_vproxy_pni_test_Generic_ext */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(PNIRef.class /* o */));

        /* Java_io_vproxy_pni_test_Generic_extArr */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(PNIRef.class /* o */));

        /* Java_io_vproxy_pni_test_Generic_sup */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(PNIRef.class /* o */));

        /* Java_io_vproxy_pni_test_Generic_supArr */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(PNIRef.class /* o */));

        /* Java_io_vproxy_pni_test_Generic_def */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(PNIRef.class /* o */));

        /* Java_io_vproxy_pni_test_Generic_defArr */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(PNIRef.class /* o */));

        /* Java_io_vproxy_pni_test_Generic_upper */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(PNIRef.class /* o */));

        /* Java_io_vproxy_pni_test_Generic_upperArr */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(PNIRef.class /* o */));

        /* Java_io_vproxy_pni_test_Generic_extendsArr */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(PNIRef.class /* o */));

        /* Java_io_vproxy_pni_test_Generic_extendsArrArr */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(PNIRef.class /* o */));

        /* Java_io_vproxy_pni_test_Generic_defGeneric */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(PNIRef.class /* o */));

        /* Java_io_vproxy_pni_test_Generic_defGenericExtends */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(PNIRef.class /* o */));

        /* Java_io_vproxy_pni_test_Generic_defGenericExtendsRaw */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(PNIRef.class /* o */));

        /* Java_io_vproxy_pni_test_Generic_defGenericArrayExtends */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(PNIRef.class /* o */));

        /* Java_io_vproxy_pni_test_Generic_defGenericArrayExtendsRaw */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(PNIRef.class /* o */));

        /* Java_io_vproxy_pni_test_Generic_defGenericSuper */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(PNIRef.class /* o */));

        /* Java_io_vproxy_pni_test_Generic_defGenericSuperRaw */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(PNIRef.class /* o */));

        /* Java_io_vproxy_pni_test_Generic_defGenericArraySuper */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(PNIRef.class /* o */));

        /* Java_io_vproxy_pni_test_Generic_defGenericArraySuperRaw */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(PNIRef.class /* o */));

        /* Java_io_vproxy_pni_test_Generic_returnSimple */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor());

        /* Java_io_vproxy_pni_test_Generic_returnSimpleArr */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor());

        /* Java_io_vproxy_pni_test_Generic_returnGeneric */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor());

        /* Java_io_vproxy_pni_test_Generic_returnGenericArr */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor());

        /* Java_io_vproxy_pni_test_Generic_returnExt */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor());

        /* Java_io_vproxy_pni_test_Generic_returnExtArr */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor());

        /* Java_io_vproxy_pni_test_Generic_returnSup */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor());

        /* Java_io_vproxy_pni_test_Generic_returnSupArr */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor());

        /* Java_io_vproxy_pni_test_Generic_returnDef */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor());

        /* Java_io_vproxy_pni_test_Generic_returnDefArr */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor());

        /* Java_io_vproxy_pni_test_Generic_returnUpper */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor());

        /* Java_io_vproxy_pni_test_Generic_returnUpperArr */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor());

        /* Java_io_vproxy_pni_test_Generic_returnExtendsArr */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor());

        /* Java_io_vproxy_pni_test_Generic_returnExtendsArrArr */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor());

        /* Java_io_vproxy_pni_test_Generic_returnDefGeneric */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor());

        /* Java_io_vproxy_pni_test_Generic_returnDefGenericExtends */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor());

        /* Java_io_vproxy_pni_test_Generic_returnDefGenericArrayExtends */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor());

        /* Java_io_vproxy_pni_test_Generic_returnDefGenericSuper */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor());

        /* Java_io_vproxy_pni_test_Generic_returnDefGenericArraySuper */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor());

        /* Java_io_vproxy_pni_test_Generic_combined */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(PNIRef.class /* t */, PNIRef.class /* tArr */, PNIRef.class /* ls */, PNIRef.class /* arrLs */, PNIRef.class /* arrLs2 */, PNIRef.class /* lsArr */, PNIRef.class /* arrLsArr */, PNIRef.class /* arrLsArr2 */, PNIRef.class /* map */, PNIRef.class /* arrMap */, PNIRef.class /* mapArr */, PNIRef.class /* arrMapArr */, PNIRef.class /* arrMapArr2 */));

        /* Java_io_vproxy_pni_test_Generic_wildcard */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(PNIRef.class /* o */));

        /* Java_io_vproxy_pni_test_GrandChildClass_yyy */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */, long.class /* y */));

        /* JavaCritical_io_vproxy_pni_test_InvokeUpcall_primaryParams */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(void.class, byte.class /* b */, byte.class /* ub */, boolean.class /* z */, char.class /* c */, double.class /* d */, float.class /* f */, int.class /* i */, int.class /* ui */, long.class /* j */, long.class /* uj */, short.class /* s */, short.class /* us */));

        /* JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnByte */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(byte.class));

        /* JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnBool */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(boolean.class));

        /* JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnChar */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(char.class));

        /* JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnDouble */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(double.class));

        /* JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnFloat */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(float.class));

        /* JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnInt */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(int.class));

        /* JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnLong */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(long.class));

        /* JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnShort */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(short.class));

        /* JavaCritical_io_vproxy_pni_test_InvokeUpcall_primaryArrayParams */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(void.class, PNIBuf.class /* b */, PNIBuf.class /* ub */, PNIBuf.class /* z */, PNIBuf.class /* c */, PNIBuf.class /* d */, PNIBuf.class /* f */, PNIBuf.class /* i */, PNIBuf.class /* ui */, PNIBuf.class /* j */, PNIBuf.class /* uj */, PNIBuf.class /* s */, PNIBuf.class /* us */));

        /* JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnByteArray */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(PNIBuf.class, MemorySegment.class /* return */));

        /* JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnBoolArray */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(PNIBuf.class, MemorySegment.class /* return */));

        /* JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnCharArray */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(PNIBuf.class, MemorySegment.class /* return */));

        /* JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnDoubleArray */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(PNIBuf.class, MemorySegment.class /* return */));

        /* JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnFloatArray */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(PNIBuf.class, MemorySegment.class /* return */));

        /* JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnIntArray */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(PNIBuf.class, MemorySegment.class /* return */));

        /* JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnLongArray */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(PNIBuf.class, MemorySegment.class /* return */));

        /* JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnShortArray */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(PNIBuf.class, MemorySegment.class /* return */));

        /* JavaCritical_io_vproxy_pni_test_InvokeUpcall_objectParams */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(void.class, MemoryLayout.class /* io.vproxy.pni.test.ObjectStruct.LAYOUT.getClass() */ /* o */));

        /* JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnObject */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(MemoryLayout.class /* io.vproxy.pni.test.ObjectStruct.LAYOUT.getClass() */, MemorySegment.class /* return */));

        /* JavaCritical_io_vproxy_pni_test_InvokeUpcall_objectArrayParams */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(void.class, PNIBuf.class /* o */));

        /* JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnObjectArray */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(PNIBuf.class, MemorySegment.class /* return */));

        /* JavaCritical_io_vproxy_pni_test_InvokeUpcall_otherParams */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(void.class, PNIBuf.class /* buffer */, io.vproxy.pni.CallSite.class /* voidCallSite */, io.vproxy.pni.CallSite.class /* objCallSite */, io.vproxy.pni.CallSite.class /* refCallSite */, MemorySegment.class /* mem */, PNIFunc.class /* voidFunc */, PNIFunc.class /* objFunc */, PNIFunc.class /* refFunc */, PNIRef.class /* ref */, PNIRef.class /* rawRef */, String.class /* str */));

        /* JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnBuffer */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(PNIBuf.class, MemorySegment.class /* return */));

        /* JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnMem */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(MemorySegment.class));

        /* JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnVoidFunc */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(PNIFunc.class));

        /* JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnObjFunc */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(PNIFunc.class));

        /* JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnRefFunc */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(PNIFunc.class));

        /* JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnRef */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(PNIRef.class));

        /* JavaCritical_io_vproxy_pni_test_InvokeUpcall_returnStr */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(String.class));

        /* JavaCritical_io_vproxy_pni_test_InvokeUpcall_sum */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(int.class, int.class /* a */, int.class /* b */));

        /* JavaCritical_io_vproxy_pni_test_InvokeUpcallNull_testParam */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(boolean.class));

        /* JavaCritical_io_vproxy_pni_test_InvokeUpcallNull_testParamRaw */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(boolean.class));

        /* JavaCritical_io_vproxy_pni_test_InvokeUpcallNull_returnO */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(boolean.class));

        /* JavaCritical_io_vproxy_pni_test_InvokeUpcallNull_returnStr */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(boolean.class));

        /* JavaCritical_io_vproxy_pni_test_InvokeUpcallNull_returnSeg */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(boolean.class));

        /* JavaCritical_io_vproxy_pni_test_InvokeUpcallNull_returnBuf */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(boolean.class));

        /* JavaCritical_io_vproxy_pni_test_InvokeUpcallNull_returnByteArr */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(boolean.class));

        /* JavaCritical_io_vproxy_pni_test_InvokeUpcallNull_returnBoolArr */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(boolean.class));

        /* JavaCritical_io_vproxy_pni_test_InvokeUpcallNull_returnCharArr */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(boolean.class));

        /* JavaCritical_io_vproxy_pni_test_InvokeUpcallNull_returnFloatArr */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(boolean.class));

        /* JavaCritical_io_vproxy_pni_test_InvokeUpcallNull_returnDoubleArr */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(boolean.class));

        /* JavaCritical_io_vproxy_pni_test_InvokeUpcallNull_returnIntArr */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(boolean.class));

        /* JavaCritical_io_vproxy_pni_test_InvokeUpcallNull_returnLongArr */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(boolean.class));

        /* JavaCritical_io_vproxy_pni_test_InvokeUpcallNull_returnShortArr */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(boolean.class));

        /* JavaCritical_io_vproxy_pni_test_InvokeUpcallNull_returnOArr */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(boolean.class));

        /* JavaCritical_io_vproxy_pni_test_InvokeUpcallNull_returnRef */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(boolean.class));

        /* JavaCritical_io_vproxy_pni_test_InvokeUpcallNull_returnFunc */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(boolean.class));

        /* JavaCritical_io_vproxy_pni_test_InvokeUpcallNull_returnFuncVoid */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(boolean.class));

        /* JavaCritical_io_vproxy_pni_test_InvokeUpcallNull_returnFuncRef */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(boolean.class));

        /* Java_io_vproxy_pni_test_NativeCheck_checkUserdataForRef */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(PNIRef.class /* ref */, MemorySegment.class /* x */, MemorySegment.class /* y */, MemorySegment.class /* z */));

        /* Java_io_vproxy_pni_test_NativeCheck_checkUserdataForFunc */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(PNIFunc.class /* func */, MemorySegment.class /* x */, MemorySegment.class /* y */, MemorySegment.class /* z */));

        /* Java_io_vproxy_pni_test_Null_testParam */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */, MemoryLayout.class /* io.vproxy.pni.test.ObjectStruct.LAYOUT.getClass() */ /* o */, String.class /* str */, MemorySegment.class /* seg */, PNIBuf.class /* buf */, PNIBuf.class /* byteArr */, PNIBuf.class /* boolArr */, PNIBuf.class /* charArr */, PNIBuf.class /* floatArr */, PNIBuf.class /* doubleArr */, PNIBuf.class /* intArr */, PNIBuf.class /* longArr */, PNIBuf.class /* shortArr */, PNIBuf.class /* oArr */, PNIRef.class /* ref */, io.vproxy.pni.CallSite.class /* func */, io.vproxy.pni.CallSite.class /* funcVoid */, io.vproxy.pni.CallSite.class /* funcRef */));

        /* JavaCritical_io_vproxy_pni_test_Null_testParamCritical */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(boolean.class, MemorySegment.class /* self */, MemoryLayout.class /* io.vproxy.pni.test.ObjectStruct.LAYOUT.getClass() */ /* o */, String.class /* str */, MemorySegment.class /* seg */, PNIBuf.class /* buf */, PNIBuf.class /* byteArr */, PNIBuf.class /* boolArr */, PNIBuf.class /* charArr */, PNIBuf.class /* floatArr */, PNIBuf.class /* doubleArr */, PNIBuf.class /* intArr */, PNIBuf.class /* longArr */, PNIBuf.class /* shortArr */, PNIBuf.class /* oArr */, PNIRef.class /* ref */, io.vproxy.pni.CallSite.class /* func */, io.vproxy.pni.CallSite.class /* funcVoid */, io.vproxy.pni.CallSite.class /* funcRef */));

        /* Java_io_vproxy_pni_test_Null_testParamRaw */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */, ByteBuffer.class /* buf */, MemorySegment.class /* byteArr */, MemorySegment.class /* boolArr */, MemorySegment.class /* charArr */, MemorySegment.class /* floatArr */, MemorySegment.class /* doubleArr */, MemorySegment.class /* intArr */, MemorySegment.class /* longArr */, MemorySegment.class /* shortArr */, MemorySegment.class /* oArr */, PNIRef.class /* ref */, PNIFunc.class /* func */, PNIFunc.class /* funcVoid */, PNIFunc.class /* funcRef */));

        /* JavaCritical_io_vproxy_pni_test_Null_testParamRawCritical */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(boolean.class, MemorySegment.class /* self */, ByteBuffer.class /* buf */, MemorySegment.class /* byteArr */, MemorySegment.class /* boolArr */, MemorySegment.class /* charArr */, MemorySegment.class /* floatArr */, MemorySegment.class /* doubleArr */, MemorySegment.class /* intArr */, MemorySegment.class /* longArr */, MemorySegment.class /* shortArr */, MemorySegment.class /* oArr */, PNIRef.class /* ref */, PNIFunc.class /* func */, PNIFunc.class /* funcVoid */, PNIFunc.class /* funcRef */));

        /* Java_io_vproxy_pni_test_Null_returnO */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */, MemorySegment.class /* return */));

        /* JavaCritical_io_vproxy_pni_test_Null_returnOCritical */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(MemoryLayout.class /* io.vproxy.pni.test.ObjectStruct.LAYOUT.getClass() */, MemorySegment.class /* self */, MemorySegment.class /* return */));

        /* Java_io_vproxy_pni_test_Null_returnStr */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_Null_returnStrCritical */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(String.class, MemorySegment.class /* self */));

        /* Java_io_vproxy_pni_test_Null_returnSeg */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_Null_returnSegCritical */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(MemorySegment.class, MemorySegment.class /* self */));

        /* Java_io_vproxy_pni_test_Null_returnBuf */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_Null_returnBufCritical */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(PNIBuf.class, MemorySegment.class /* self */, MemorySegment.class /* return */));

        /* JavaCritical_io_vproxy_pni_test_Null_returnBufCritical2 */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(PNIBuf.class, MemorySegment.class /* self */, MemorySegment.class /* return */));

        /* Java_io_vproxy_pni_test_Null_returnByteArr */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_Null_returnByteArrCritical */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(PNIBuf.class, MemorySegment.class /* self */, MemorySegment.class /* return */));

        /* JavaCritical_io_vproxy_pni_test_Null_returnByteArrCritical2 */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(PNIBuf.class, MemorySegment.class /* self */, MemorySegment.class /* return */));

        /* Java_io_vproxy_pni_test_Null_returnBoolArr */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_Null_returnBoolArrCritical */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(PNIBuf.class, MemorySegment.class /* self */, MemorySegment.class /* return */));

        /* JavaCritical_io_vproxy_pni_test_Null_returnBoolArrCritical2 */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(PNIBuf.class, MemorySegment.class /* self */, MemorySegment.class /* return */));

        /* Java_io_vproxy_pni_test_Null_returnCharArr */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_Null_returnCharArrCritical */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(PNIBuf.class, MemorySegment.class /* self */, MemorySegment.class /* return */));

        /* JavaCritical_io_vproxy_pni_test_Null_returnCharArrCritical2 */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(PNIBuf.class, MemorySegment.class /* self */, MemorySegment.class /* return */));

        /* Java_io_vproxy_pni_test_Null_returnFloatArr */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_Null_returnFloatArrCritical */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(PNIBuf.class, MemorySegment.class /* self */, MemorySegment.class /* return */));

        /* JavaCritical_io_vproxy_pni_test_Null_returnFloatArrCritical2 */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(PNIBuf.class, MemorySegment.class /* self */, MemorySegment.class /* return */));

        /* Java_io_vproxy_pni_test_Null_returnDoubleArr */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_Null_returnDoubleArrCritical */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(PNIBuf.class, MemorySegment.class /* self */, MemorySegment.class /* return */));

        /* JavaCritical_io_vproxy_pni_test_Null_returnDoubleArrCritical2 */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(PNIBuf.class, MemorySegment.class /* self */, MemorySegment.class /* return */));

        /* Java_io_vproxy_pni_test_Null_returnIntArr */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_Null_returnIntArrCritical */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(PNIBuf.class, MemorySegment.class /* self */, MemorySegment.class /* return */));

        /* JavaCritical_io_vproxy_pni_test_Null_returnIntArrCritical2 */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(PNIBuf.class, MemorySegment.class /* self */, MemorySegment.class /* return */));

        /* Java_io_vproxy_pni_test_Null_returnLongArr */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_Null_returnLongArrCritical */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(PNIBuf.class, MemorySegment.class /* self */, MemorySegment.class /* return */));

        /* JavaCritical_io_vproxy_pni_test_Null_returnLongArrCritical2 */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(PNIBuf.class, MemorySegment.class /* self */, MemorySegment.class /* return */));

        /* Java_io_vproxy_pni_test_Null_returnShortArr */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_Null_returnShortArrCritical */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(PNIBuf.class, MemorySegment.class /* self */, MemorySegment.class /* return */));

        /* JavaCritical_io_vproxy_pni_test_Null_returnShortArrCritical2 */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(PNIBuf.class, MemorySegment.class /* self */, MemorySegment.class /* return */));

        /* Java_io_vproxy_pni_test_Null_returnOArr */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_Null_returnOArrCritical */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(PNIBuf.class, MemorySegment.class /* self */, MemorySegment.class /* return */));

        /* JavaCritical_io_vproxy_pni_test_Null_returnOArrCritical2 */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(PNIBuf.class, MemorySegment.class /* self */, MemorySegment.class /* return */));

        /* Java_io_vproxy_pni_test_Null_returnRef */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_Null_returnRefCritical */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(PNIRef.class, MemorySegment.class /* self */));

        /* Java_io_vproxy_pni_test_Null_returnFunc */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_Null_returnFuncCritical */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(PNIFunc.class, MemorySegment.class /* self */));

        /* Java_io_vproxy_pni_test_Null_returnFuncVoid */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_Null_returnFuncVoidCritical */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(PNIFunc.class, MemorySegment.class /* self */));

        /* Java_io_vproxy_pni_test_Null_returnFuncRef */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_Null_returnFuncRefCritical */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(PNIFunc.class, MemorySegment.class /* self */));

        /* Java_io_vproxy_pni_test_Null_emptyPassThrough */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */, MemoryLayout.class /* io.vproxy.pni.test.Empty.LAYOUT.getClass() */ /* empty */, MemorySegment.class /* return */));

        /* JavaCritical_io_vproxy_pni_test_Null_emptyPassThroughCritical */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(MemoryLayout.class /* io.vproxy.pni.test.Empty.LAYOUT.getClass() */, MemorySegment.class /* self */, MemoryLayout.class /* io.vproxy.pni.test.Empty.LAYOUT.getClass() */ /* empty */, MemorySegment.class /* return */));

        /* Java_io_vproxy_pni_test_ObjectStruct_func1 */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */, String.class /* str */, String.class /* str2 */, MemorySegment.class /* seg */, PNIBuf.class /* buf */));

        /* JavaCritical_io_vproxy_pni_test_ObjectStruct_func1Critical */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(void.class, MemorySegment.class /* self */, String.class /* str */, String.class /* str2 */, MemorySegment.class /* seg */, PNIBuf.class /* buf */));

        /* Java_io_vproxy_pni_test_ObjectStruct_retrieveStr */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_ObjectStruct_retrieveStrCritical */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(String.class, MemorySegment.class /* self */));

        /* Java_io_vproxy_pni_test_ObjectStruct_retrieveLenStr */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_ObjectStruct_retrieveLenStrCritical */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(String.class, MemorySegment.class /* self */));

        /* Java_io_vproxy_pni_test_ObjectStruct_retrieveSeg */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_ObjectStruct_retrieveSegCritical */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(MemorySegment.class, MemorySegment.class /* self */));

        /* Java_io_vproxy_pni_test_ObjectStruct_retrieveBuf */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_ObjectStruct_retrieveBufCritical */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(PNIBuf.class, MemorySegment.class /* self */, MemorySegment.class /* return */));

        /* Java_io_vproxy_pni_test_ObjectStruct_checkPointerSetToNonNull */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_ObjectStruct_checkPointerSetToNonNullCritical */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(boolean.class, MemorySegment.class /* self */));

        /* Java_io_vproxy_pni_test_ObjectStruct_checkPointerSetToNull */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_ObjectStruct_checkPointerSetToNullCritical */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(boolean.class, MemorySegment.class /* self */));

        /* Java_io_vproxy_pni_test_PackedBaseClass_aaa */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */, byte.class /* a */));

        /* Java_io_vproxy_pni_test_PackedBaseClass_bbb */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */, short.class /* b */));

        /* Java_io_vproxy_pni_test_PrimitiveStruct_func1 */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */, byte.class /* aByte */, byte.class /* unsignedByte */, int.class /* aInt */, int.class /* unsignedInt */, long.class /* aLong */, long.class /* unsignedLong */, short.class /* aShort */, short.class /* unsignedShort */));

        /* JavaCritical_io_vproxy_pni_test_PrimitiveStruct_func1Critical */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(void.class, MemorySegment.class /* self */, byte.class /* aByte */, byte.class /* unsignedByte */, int.class /* aInt */, int.class /* unsignedInt */, long.class /* aLong */, long.class /* unsignedLong */, short.class /* aShort */, short.class /* unsignedShort */));

        /* Java_io_vproxy_pni_test_PrimitiveStruct_func2 */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */, char.class /* aChar */, double.class /* aDouble */, float.class /* aFloat */, boolean.class /* aBoolean */));

        /* JavaCritical_io_vproxy_pni_test_PrimitiveStruct_func2Critical */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(void.class, MemorySegment.class /* self */, char.class /* aChar */, double.class /* aDouble */, float.class /* aFloat */, boolean.class /* aBoolean */));

        /* Java_io_vproxy_pni_test_PrimitiveStruct_func3 */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */, PNIBuf.class /* byteArray */, PNIBuf.class /* unsignedByteArray */, PNIBuf.class /* intArray */, PNIBuf.class /* unsignedIntArray */, PNIBuf.class /* longArray */, PNIBuf.class /* unsignedLongArray */, PNIBuf.class /* shortArray */, PNIBuf.class /* unsignedShortArray */));

        /* JavaCritical_io_vproxy_pni_test_PrimitiveStruct_func3Critical */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(void.class, MemorySegment.class /* self */, PNIBuf.class /* byteArray */, PNIBuf.class /* unsignedByteArray */, PNIBuf.class /* intArray */, PNIBuf.class /* unsignedIntArray */, PNIBuf.class /* longArray */, PNIBuf.class /* unsignedLongArray */, PNIBuf.class /* shortArray */, PNIBuf.class /* unsignedShortArray */));

        /* Java_io_vproxy_pni_test_PrimitiveStruct_func4 */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */, PNIBuf.class /* charArray */, PNIBuf.class /* doubleArray */, PNIBuf.class /* floatArray */, PNIBuf.class /* booleanArray */));

        /* JavaCritical_io_vproxy_pni_test_PrimitiveStruct_func4Critical */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(void.class, MemorySegment.class /* self */, PNIBuf.class /* charArray */, PNIBuf.class /* doubleArray */, PNIBuf.class /* floatArray */, PNIBuf.class /* booleanArray */));

        /* Java_io_vproxy_pni_test_PrimitiveStruct_retrieveByte */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveByteCritical */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(byte.class, MemorySegment.class /* self */));

        /* Java_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedByte */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedByteCritical */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(byte.class, MemorySegment.class /* self */));

        /* Java_io_vproxy_pni_test_PrimitiveStruct_retrieveChar */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveCharCritical */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(char.class, MemorySegment.class /* self */));

        /* Java_io_vproxy_pni_test_PrimitiveStruct_retrieveDouble */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveDoubleCritical */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(double.class, MemorySegment.class /* self */));

        /* Java_io_vproxy_pni_test_PrimitiveStruct_retrieveFloat */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveFloatCritical */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(float.class, MemorySegment.class /* self */));

        /* Java_io_vproxy_pni_test_PrimitiveStruct_retrieveInt */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveIntCritical */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(int.class, MemorySegment.class /* self */));

        /* Java_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedInt */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedIntCritical */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(int.class, MemorySegment.class /* self */));

        /* Java_io_vproxy_pni_test_PrimitiveStruct_retrieveLong */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveLongCritical */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(long.class, MemorySegment.class /* self */));

        /* Java_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedLong */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedLongCritical */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(long.class, MemorySegment.class /* self */));

        /* Java_io_vproxy_pni_test_PrimitiveStruct_retrieveShort */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveShortCritical */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(short.class, MemorySegment.class /* self */));

        /* Java_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedShort */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedShortCritical */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(short.class, MemorySegment.class /* self */));

        /* Java_io_vproxy_pni_test_PrimitiveStruct_retrieveBoolean */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveBooleanCritical */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(boolean.class, MemorySegment.class /* self */));

        /* Java_io_vproxy_pni_test_PrimitiveStruct_retrieveByteArray */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveByteArrayCritical */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(PNIBuf.class, MemorySegment.class /* self */, MemorySegment.class /* return */));

        /* Java_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedByteArray */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedByteArrayCritical */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(PNIBuf.class, MemorySegment.class /* self */, MemorySegment.class /* return */));

        /* Java_io_vproxy_pni_test_PrimitiveStruct_retrieveCharArray */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveCharArrayCritical */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(PNIBuf.class, MemorySegment.class /* self */, MemorySegment.class /* return */));

        /* Java_io_vproxy_pni_test_PrimitiveStruct_retrieveDoubleArray */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveDoubleArrayCritical */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(PNIBuf.class, MemorySegment.class /* self */, MemorySegment.class /* return */));

        /* Java_io_vproxy_pni_test_PrimitiveStruct_retrieveFloatArray */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveFloatArrayCritical */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(PNIBuf.class, MemorySegment.class /* self */, MemorySegment.class /* return */));

        /* Java_io_vproxy_pni_test_PrimitiveStruct_retrieveIntArray */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveIntArrayCritical */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(PNIBuf.class, MemorySegment.class /* self */, MemorySegment.class /* return */));

        /* Java_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedIntArray */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedIntArrayCritical */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(PNIBuf.class, MemorySegment.class /* self */, MemorySegment.class /* return */));

        /* Java_io_vproxy_pni_test_PrimitiveStruct_retrieveLongArray */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveLongArrayCritical */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(PNIBuf.class, MemorySegment.class /* self */, MemorySegment.class /* return */));

        /* Java_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedLongArray */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedLongArrayCritical */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(PNIBuf.class, MemorySegment.class /* self */, MemorySegment.class /* return */));

        /* Java_io_vproxy_pni_test_PrimitiveStruct_retrieveShortArray */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveShortArrayCritical */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(PNIBuf.class, MemorySegment.class /* self */, MemorySegment.class /* return */));

        /* Java_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedShortArray */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedShortArrayCritical */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(PNIBuf.class, MemorySegment.class /* self */, MemorySegment.class /* return */));

        /* Java_io_vproxy_pni_test_PrimitiveStruct_retrieveBooleanArray */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveBooleanArrayCritical */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(PNIBuf.class, MemorySegment.class /* self */, MemorySegment.class /* return */));

        /* Java_io_vproxy_pni_test_PrimitiveStruct_retrieveByteArrayPointer */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveByteArrayPointerCritical */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(PNIBuf.class, MemorySegment.class /* self */, MemorySegment.class /* return */));

        /* Java_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedByteArrayPointer */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedByteArrayPointerCritical */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(PNIBuf.class, MemorySegment.class /* self */, MemorySegment.class /* return */));

        /* Java_io_vproxy_pni_test_PrimitiveStruct_retrieveCharArrayPointer */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveCharArrayPointerCritical */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(PNIBuf.class, MemorySegment.class /* self */, MemorySegment.class /* return */));

        /* Java_io_vproxy_pni_test_PrimitiveStruct_retrieveDoubleArrayPointer */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveDoubleArrayPointerCritical */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(PNIBuf.class, MemorySegment.class /* self */, MemorySegment.class /* return */));

        /* Java_io_vproxy_pni_test_PrimitiveStruct_retrieveFloatArrayPointer */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveFloatArrayPointerCritical */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(PNIBuf.class, MemorySegment.class /* self */, MemorySegment.class /* return */));

        /* Java_io_vproxy_pni_test_PrimitiveStruct_retrieveIntArrayPointer */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveIntArrayPointerCritical */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(PNIBuf.class, MemorySegment.class /* self */, MemorySegment.class /* return */));

        /* Java_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedIntArrayPointer */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedIntArrayPointerCritical */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(PNIBuf.class, MemorySegment.class /* self */, MemorySegment.class /* return */));

        /* Java_io_vproxy_pni_test_PrimitiveStruct_retrieveLongArrayPointer */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveLongArrayPointerCritical */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(PNIBuf.class, MemorySegment.class /* self */, MemorySegment.class /* return */));

        /* Java_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedLongArrayPointer */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedLongArrayPointerCritical */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(PNIBuf.class, MemorySegment.class /* self */, MemorySegment.class /* return */));

        /* Java_io_vproxy_pni_test_PrimitiveStruct_retrieveShortArrayPointer */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveShortArrayPointerCritical */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(PNIBuf.class, MemorySegment.class /* self */, MemorySegment.class /* return */));

        /* Java_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedShortArrayPointer */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveUnsignedShortArrayPointerCritical */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(PNIBuf.class, MemorySegment.class /* self */, MemorySegment.class /* return */));

        /* Java_io_vproxy_pni_test_PrimitiveStruct_retrieveBooleanArrayPointer */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_PrimitiveStruct_retrieveBooleanArrayPointerCritical */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(PNIBuf.class, MemorySegment.class /* self */, MemorySegment.class /* return */));

        /* Java_io_vproxy_pni_test_PrimitiveStruct_checkPointerSetToNonNull */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_PrimitiveStruct_checkPointerSetToNonNullCritical */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(boolean.class, MemorySegment.class /* self */));

        /* Java_io_vproxy_pni_test_PrimitiveStruct_checkPointerSetToNull */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_PrimitiveStruct_checkPointerSetToNullCritical */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(boolean.class, MemorySegment.class /* self */));

        /* Java_io_vproxy_pni_test_RawArrays_byteArray */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* array */, int.class /* off */));

        /* Java_io_vproxy_pni_test_RawArrays_unsignedByteArray */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* array */, int.class /* off */));

        /* Java_io_vproxy_pni_test_RawArrays_boolArray */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* array */, int.class /* off */));

        /* Java_io_vproxy_pni_test_RawArrays_charArray */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* array */, int.class /* off */));

        /* Java_io_vproxy_pni_test_RawArrays_doubleArray */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* array */, int.class /* off */));

        /* Java_io_vproxy_pni_test_RawArrays_floatArray */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* array */, int.class /* off */));

        /* Java_io_vproxy_pni_test_RawArrays_intArray */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* array */, int.class /* off */));

        /* Java_io_vproxy_pni_test_RawArrays_unsignedIntArray */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* array */, int.class /* off */));

        /* Java_io_vproxy_pni_test_RawArrays_longArray */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* array */, int.class /* off */));

        /* Java_io_vproxy_pni_test_RawArrays_unsignedLongArray */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* array */, int.class /* off */));

        /* Java_io_vproxy_pni_test_RawArrays_shortArray */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* array */, int.class /* off */));

        /* Java_io_vproxy_pni_test_RawArrays_unsignedShortArray */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* array */, int.class /* off */));

        /* Java_io_vproxy_pni_test_RawArrays_structArray */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* array */, int.class /* off */, MemorySegment.class /* return */));

        /* Java_io_vproxy_pni_test_RawArrays_structArrayNotRaw */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(PNIBuf.class /* array */, int.class /* off */, MemorySegment.class /* return */));

        /* Java_io_vproxy_pni_test_RefAndFuncFields_call */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* Java_io_vproxy_pni_test_RefAndFuncFields_call2 */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */, MemoryLayout.class /* io.vproxy.pni.test.ObjectStruct.LAYOUT.getClass() */ /* o */));

        /* Java_io_vproxy_pni_test_RefAndFuncFields_set */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */, PNIRef.class /* ref */, PNIRef.class /* ref2 */, PNIRef.class /* ref3 */, PNIFunc.class /* func */, PNIFunc.class /* func2 */));

        /* Java_io_vproxy_pni_test_RefAndFuncFields_setRaw */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */, PNIRef.class /* ref */, PNIRef.class /* ref2 */, PNIRef.class /* ref3 */));

        /* Java_io_vproxy_pni_test_RefAndFuncFields_retrieveRef */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* Java_io_vproxy_pni_test_RefAndFuncFields_retrieveRef2 */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* Java_io_vproxy_pni_test_RefAndFuncFields_retrieveRef3 */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* Java_io_vproxy_pni_test_RefAndFuncFields_retrieveFunc */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* Java_io_vproxy_pni_test_RefAndFuncFields_retrieveFunc2 */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

/* JavaCritical_io_vproxy_pni_test_SizeofEmbed___getLayoutByteSize */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(long.class), Linker.Option.isTrivial());

/* JavaCritical_io_vproxy_pni_test_SizeofStruct___getLayoutByteSize */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(long.class), Linker.Option.isTrivial());

/* JavaCritical_io_vproxy_pni_test_SizeofStructExpr___getLayoutByteSize */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(long.class), Linker.Option.isTrivial());

/* JavaCritical_io_vproxy_pni_test_SizeofUnion___getLayoutByteSize */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(long.class), Linker.Option.isTrivial());

        /* Java_io_vproxy_pni_test_StructA_bbb */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */, MemoryLayout.class /* io.vproxy.pni.test.StructB.LAYOUT.getClass() */ /* b */));

        /* JavaCritical_io_vproxy_pni_test_StructA_bbbCritical */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(void.class, MemorySegment.class /* self */, MemoryLayout.class /* io.vproxy.pni.test.StructB.LAYOUT.getClass() */ /* b */));

        /* Java_io_vproxy_pni_test_StructA_ccc */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */, MemoryLayout.class /* io.vproxy.pni.test.UnionC.LAYOUT.getClass() */ /* c */));

        /* JavaCritical_io_vproxy_pni_test_StructA_cccCritical */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(void.class, MemorySegment.class /* self */, MemoryLayout.class /* io.vproxy.pni.test.UnionC.LAYOUT.getClass() */ /* c */));

        /* Java_io_vproxy_pni_test_StructA_cccPointer */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */, MemoryLayout.class /* io.vproxy.pni.test.UnionC.LAYOUT.getClass() */ /* c */));

        /* JavaCritical_io_vproxy_pni_test_StructA_cccPointerCritical */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(void.class, MemorySegment.class /* self */, MemoryLayout.class /* io.vproxy.pni.test.UnionC.LAYOUT.getClass() */ /* c */));

        /* Java_io_vproxy_pni_test_StructA_bbbArray */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */, PNIBuf.class /* bArray */));

        /* JavaCritical_io_vproxy_pni_test_StructA_bbbArrayCritical */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(void.class, MemorySegment.class /* self */, PNIBuf.class /* bArray */));

        /* Java_io_vproxy_pni_test_StructA_bbbArray2 */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */, PNIBuf.class /* bArray */));

        /* JavaCritical_io_vproxy_pni_test_StructA_bbbArray2Critical */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(void.class, MemorySegment.class /* self */, PNIBuf.class /* bArray */));

        /* Java_io_vproxy_pni_test_StructA_retrieveB */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */, MemorySegment.class /* return */));

        /* JavaCritical_io_vproxy_pni_test_StructA_retrieveBCritical */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(MemoryLayout.class /* io.vproxy.pni.test.StructB.LAYOUT.getClass() */, MemorySegment.class /* self */, MemorySegment.class /* return */));

        /* Java_io_vproxy_pni_test_StructA_retrieveC */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */, MemorySegment.class /* return */));

        /* JavaCritical_io_vproxy_pni_test_StructA_retrieveCCritical */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(MemoryLayout.class /* io.vproxy.pni.test.UnionC.LAYOUT.getClass() */, MemorySegment.class /* self */, MemorySegment.class /* return */));

        /* Java_io_vproxy_pni_test_StructA_retrieveCPointer */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */, MemorySegment.class /* return */));

        /* JavaCritical_io_vproxy_pni_test_StructA_retrieveCPointerCritical */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(MemoryLayout.class /* io.vproxy.pni.test.UnionC.LAYOUT.getClass() */, MemorySegment.class /* self */, MemorySegment.class /* return */));

        /* Java_io_vproxy_pni_test_StructA_retrieveBArray */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_StructA_retrieveBArrayCritical */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(PNIBuf.class, MemorySegment.class /* self */, MemorySegment.class /* return */));

        /* Java_io_vproxy_pni_test_StructA_retrieveBArray2 */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */));

        /* JavaCritical_io_vproxy_pni_test_StructA_retrieveBArray2Critical */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(PNIBuf.class, MemorySegment.class /* self */, MemorySegment.class /* return */));

        /* Java_io_vproxy_pni_test_StructM_nnn */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */, MemoryLayout.class /* io.vproxy.pni.test.StructN.LAYOUT.getClass() */ /* n */), Linker.Option.isTrivial());

        /* Java_io_vproxy_pni_test_StructN_retrieveS */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */), Linker.Option.isTrivial());

        /* Java_io_vproxy_pni_test_StructN_retrieveL */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */), Linker.Option.isTrivial());

        /* UnionP_retrieve_i */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */), Linker.Option.isTrivial());

        /* UnionP_retrieve_l */
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildFunctionDescriptor(MemorySegment.class /* self */), Linker.Option.isTrivial());

        /* graal upcall for io.vproxy.pni.test.Upcall */
        RuntimeClassInitialization.initializeAtBuildTime(io.vproxy.pni.test.Upcall.class);
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(void.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class), Linker.Option.isTrivial());

        /* graal upcall for io.vproxy.pni.test.UpcallNull */
        RuntimeClassInitialization.initializeAtBuildTime(io.vproxy.pni.test.UpcallNull.class);
        RuntimeForeignAccess.registerForDowncall(PanamaUtils.buildCriticalFunctionDescriptor(void.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class, MemorySegment.class), Linker.Option.isTrivial());
    }
}
// metadata.generator-version: pni test
// sha256:20c4d94e3a20cb798cf4eb394ebdc2a0653d7f60ce6512acbc23c91a5ec05c35
