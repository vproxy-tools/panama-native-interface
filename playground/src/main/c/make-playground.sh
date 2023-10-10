#!/bin/bash

os=`uname`

target="pniplayground"
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
    -shared -Werror -lc -lpthread -fPIC \
    ../c-generated/pni.c \
    playground.c \
    -o "$target"
