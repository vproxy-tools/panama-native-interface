package io.vproxy.pni.test

import io.vproxy.pni.annotation.*

@Struct
@SpecifyGeneratedMembers
open class PNIKtStruct {
  @field:GenerateMember
  var aInt: Int = 0

  @field:GenerateMember
  var aLong: Long = 0

  @Impl(
    // language="c++"
    c = """
      env->return_ = self->aInt;
      return 0;
    """
  )
  @GenerateMember
  fun retrieveInt(): Int = 0

  @Impl(
    // language="c++"
    c = """
      env->return_ = self->aLong;
      return 0;
    """
  )
  @GenerateMember
  fun retrieveLong(): Long = 0
}

@Struct
@SpecifyGeneratedMembers
class PNIKtStructInherit : PNIKtStruct() {
  @field:GenerateMember
  lateinit var obj: PNIPrimitiveStruct

  @Impl(
    // language="c++"
    c = """
      env->return_ = &self->obj;
      return 0;
    """
  )
  @NoAlloc
  @GenerateMember
  fun retrieveObj(): PNIPrimitiveStruct? = null
}

@Downcall
interface PNIKtDowncall {
  @Impl(
    // language="c++"
    c = """
      return o->aLong;
    """
  )
  @Style(Styles.critical)
  fun retrieveLong(o: PNIKtStruct): Long

  @Impl(
    include = ["io_vproxy_pni_test_KtUpcall.h", "io_vproxy_pni_test_KtStruct.h"],
    // language="c++"
    c = """
      return JavaCritical_io_vproxy_pni_test_KtUpcall_helloworld(i, l, return_);
    """
  )
  @Style(Styles.critical)
  fun invokeHelloWorld(i: Int, l: Long): PNIKtStruct
}

@Upcall
interface PNIKtUpcall {
  fun helloworld(i: Int, l: Long): PNIKtStruct
}
