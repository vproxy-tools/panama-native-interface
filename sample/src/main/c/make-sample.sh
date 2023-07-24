#!/bin/bash

if [[ -z "$JAVA_HOME" ]]
then
	JAVA_HOME="/Library/Java/JavaVirtualMachines/jdk-11.0.2.jdk/Contents/Home"
fi

os=`uname`

target="pnisample"
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
    -shared -Werror -lc -lpthread -fPIC \
    io_vproxy_pni_sample_NativeFunctions.c \
    -o "$target"
