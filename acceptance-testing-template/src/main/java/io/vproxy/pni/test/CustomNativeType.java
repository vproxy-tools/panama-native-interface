package io.vproxy.pni.test;

import io.vproxy.pni.annotation.*;

import java.lang.foreign.MemorySegment;

@Struct
@Include("sizeof.h")
abstract class PNICustomNativeTypeStruct {
    @NativeType("SizeofStructExpr*")
    MemorySegment field;

    @Impl(
        // language="c"
        c = """
            env->return_ = self->field->p1;
            return 0;
            """
    )
    abstract long getP1();
}

@Function
@Include({"sizeof.h", "io_vproxy_pni_test_CustomNativeTypeUpcall.h"})
interface PNICustomNativeTypeFunc {
    @Impl(
        c = """
            return &(o->p1);
            """
    )
    @Critical
    @NativeReturnType("int64_t*")
    MemorySegment exec(@NativeType("SizeofStructExpr*") MemorySegment o);

    @Impl(
        c = """
            return JavaCritical_io_vproxy_pni_test_CustomNativeTypeUpcall_exec(s);
            """
    )
    @Critical
    @NativeReturnType("int64_t*")
    MemorySegment invoke(PNISizeofStructExpr s);
}

@Upcall
@Include("sizeof.h")
interface PNICustomNativeTypeUpcall {
    @NativeReturnType("int64_t*")
    MemorySegment exec(@NativeType("SizeofStructExpr*") MemorySegment o);
}
