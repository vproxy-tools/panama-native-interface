#!/bin/bash

os=`uname`

target="graaltest"
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
    -I "../../../../api/src/main/c" \
    -I "../../../../api/src/main/c/jnimock" \
    -I "../c-generated" \
    -shared -Werror -fPIC \
    -DPNI_GRAAL=1 \
    ../../../../api/src/main/c/pni.c \
    graal-test.c \
    ../c-generated/io_vproxy_pni_graal_test_Upcall.c \
    -o "$target"
