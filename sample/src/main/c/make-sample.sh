#!/bin/bash

os=`uname`

target="pnisample"
include_platform_dir=""
extra_ld=""

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
	extra_ld="-lws2_32"
fi

rm -f "$target"

gcc -std=gnu99 -O2 \
    $GCC_OPTS \
    -I "./" \
    -I "../../../../api/src/main/c" \
    -I "../../../../api/src/main/c/jnimock" \
    -shared -Werror -fPIC \
    ../../../../api/src/main/c/pni.c \
    io_vproxy_pni_sample_NativeFunctions.c \
    $extra_ld \
    -o "$target"
