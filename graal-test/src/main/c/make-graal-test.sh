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
    -I "../c-generated" \
    -I "../c-generated-2" \
    -I "../../../../acceptance-testing/src/test/c" \
    -shared -Werror -fPIC \
    -DPNI_GRAAL=1 \
    ../c-generated/pni.c \
    graal-test.c \
    ../c-generated-2/*.c \
    -o "$target"
