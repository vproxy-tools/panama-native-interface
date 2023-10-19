#!/bin/bash

os=`uname`

target="pnitest"
include_platform_dir=""

if [[ "Linux" == "$os" ]]
then
	target="lib$target.so"
	include_platform_dir="linux"
elif [[ "Darwin" == "$os" ]]
then
	target="lib$target.dylib"
	include_platform_dir="darwin"
else
	target="$target.dll"
	include_platform_dir="win32"
fi

rm -f "$target"

gcc -std=gnu99 -O2 \
    $GCC_OPTS \
    -I "./" \
    -I "../c-generated" \
    -shared -Werror -fPIC \
    ../c-generated/pni.c \
    io_vproxy_pni_test_PrimitiveStruct.c \
    io_vproxy_pni_test_ObjectStruct.c \
    io_vproxy_pni_test_StructA.c \
    io_vproxy_pni_test_Func.c \
    io_vproxy_pni_test_StructM.c io_vproxy_pni_test_StructN.c io_vproxy_pni_test_UnionP.c \
    gcc_compatibility.c \
    io_vproxy_pni_test_RawArrays.c \
    io_vproxy_pni_test_Null.c \
    io_vproxy_pni_test_DefiningCFunction.c \
    io_vproxy_pni_test_RefAndFuncFields.c \
    io_vproxy_pni_test_Generic.c \
    ../c-generated/io_vproxy_pni_test_Upcall.c \
    io_vproxy_pni_test_InvokeUpcall.c \
    ../c-generated/io_vproxy_pni_test_UpcallNull.c \
    io_vproxy_pni_test_InvokeUpcallNull.c \
    io_vproxy_pni_test_NativeCheck.c \
    sizeof.c \
    ../c-generated/io_vproxy_pni_test_SizeofStruct.extra.c \
    ../c-generated/io_vproxy_pni_test_SizeofStructExpr.extra.c \
    ../c-generated/io_vproxy_pni_test_SizeofUnion.extra.c \
    ../c-generated/io_vproxy_pni_test_SizeofEmbed.extra.c \
    super_class.c \
    ../c-generated/io_vproxy_pni_test_AlwaysAlignedSizeof.extra.c \
    align.c \
    io_vproxy_pni_test_CustomNativeType.c \
    ../c-generated/io_vproxy_pni_test_CustomNativeTypeUpcall.c \
    -o "$target"
