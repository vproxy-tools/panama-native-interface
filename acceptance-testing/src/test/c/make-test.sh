#!/bin/bash

if [[ -z "$JAVA_HOME" ]]
then
	JAVA_HOME="/Library/Java/JavaVirtualMachines/jdk-11.0.2.jdk/Contents/Home"
fi

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
    -I "$JAVA_HOME/include" \
    -I "$JAVA_HOME/include/$include_platform_dir" \
    -I "../../../../api/src/main/c" \
    -I "../c-generated" \
    -shared -Werror -lc -lpthread -fPIC \
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
    -o "$target"
