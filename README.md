# Panama Native Interface

All documentations are contained in this single README file.

This page is very long.  
On Github, you may click ![toc-icon](https://raw.githubusercontent.com/vproxy-tools/panama-native-interface/master/doc/toc-icon.png) to open the Github Table of Contents, and redirect to the section you are interested in.  
It's recommended to go [here](https://github.com/vproxy-tools/panama-native-interface/blob/master/README.md) to read this page.

[[_TOC_]]

## Abstract

A way of using `Project Panama` that is completely different from using `jextract`.

The `jextract` parses C code and generates Java code, while `Panama Native Interface` reads Java byte code and generates C code as well as user-friendly and native-friendly Java code.

This approach is similar to how `JNI(Java Native Interface)` works, so this project is named as `PNI(Panama Native Interface)`.

## Why using `Panama Native Interface` instead of `jextract`

<details open><summary>Click to reveal/hide</summary>

The `jextract` tool can automatically generate Java types from C headers. This seems great at first, you can create structs and call functions in Java just like writing C code.

However, when you apply it to your project, you will find that the generated code is so long and so complicated, and contains so much symbols you would never use. Well, this is just the beginning of a nightmare.

Things start to go messy when a C library provides its APIs through `macro`s or `static inline` functions. These information will not preserve after compiling, and you will not be able to call them because they just don't exist.\[1\]  
Using `macro`s or `static inline` functions to define user friendly APIs, is very common in the C world.\[2\]

It would be much messier when you are trying to adapt to a cross platform library, or a bunch of different versions of libraries of the same origin.  
In this situation, you will have to ask `jextract` to generate code for each version, and you will have to write adaptors for all versions because these generated types are different.\[3\]

After all, **C** libraries are written for **C** users. The users are happy as long as the code compiles with a **C** compiler.\[4\]

Yes, the real world is much more complex than a simple poc.

So why not take the opposite direction. Let's put the dirty work in the C world and let the C compiler take care of things for us, and let's provide a group of clean and nice APIs for Java.  
Since the APIs are specifically made for Java, why not just put the definitions on the Java side.

Oh, wait, doesn't that sound familiar? This is exactly the `Java Native Interface` approach.  
Let's take one more step further. We define not only methods(functions) in Java, but also `struct`s and `union`s, and we use `Project Panama` as the base.

The `Panama Native Interface` helps you deal with all the dirty work mentioned above.  
You can not only define types/functions in Java, but also bring pre-defined type/function into Java.

> \[1\]: For example, a very common _variable_: `errno` is defined using macro, actually you are calling a function. The macro is platform specific, but `errno` is cross platform.  
> \[2\]: For example, in Lua, a lot of `documented` APIs are defined using `macro`s.  
> \[3\]: For example, the `msquic` supports multiple platforms, each platform has some specific `typedef`s.  
> \[4\]: For example, the Lua 5.1-5.4 exposes their APIs in different ways, however if you are writing C you are likely to compile properly across all platforms (only a few deprecated functions need to be modified).  
> BTW, `jextract` cannot handle some commonly used syntax for now (2023-09-24), e.g. `align or packed` attributes, `bit fields`, ...

</details>

## How to build

<details open><summary>Click to reveal/hide</summary>

### 1. Install JDKs

You need JDK `21` to build the project.

### 2. Configure Environment Variables

* Configure `JAVA_HOME` to your JDK 21.
* Configure `PATH` to make sure `javac` points to JDK 21

> If you are using `Windows`, it's recommended to use `MinGW UCRT64` to work with this project.
>
> * Configure `MINGW_BASH` to the path to `bash.exe` in your `MinGW` directory, usually it's `C:\msys64\usr\bin\bash.exe`
> * If you need to build Graal native-image on Windows, you will need to install Visual Studio 2022, and at least install the following components:
>   * MSVC v143 - VS 2022 C++ x64/x86 生成工具(最新)
>   * Windows 通用 CRT SDK
>   * Windows 通用 C 运行时
>   * Windows 10 SDK (10.0.20348.0)

After configuring the environment variables, you might need to restart your terminal/ide, and stop current Gradle daemons using `./gradlew --stop`

### 3. Install GCC

You will need `GCC` to compile with the generated headers. Any `GCC` that supports `gnu99` or `c11` should be fine.

### 4. Build

```shell
./gradlew clean shadowJar
```

You will find an executable jar in `build/libs/pni.jar`

```shell
java -jar build/libs/pni.jar -version
java -jar build/libs/pni.jar -help
```

You can make a native-image for `pni.jar`

```shell
./gradlew clean nativeImage

./pni.run --help
```

> You will need `GraalVM for JDK 21` to make the native-image.

### 5. Sample

There's a sample program, which is an _http server_ listening on `:80`.

```shell
./gradlew clean runSample

curl 127.0.0.1:80
```

To run the native image:

```shell
./gradlew clean runSampleNativeImage
```

> You will need `GraalVM for JDK 21` to make the native-image.

### 6. Test

```shell
./gradlew clean runAcceptanceTest
./gradlew clean runUnitTest
# for native image tests:
./gradlew clean runGraalTest
```

</details>

## How to bundle into a Gradle project

<details open><summary>Click to reveal/hide</summary>

It's recommended to use `Gradle` as the building system, otherwise you will have to manually generate files using the `pni` command line tool.

Here's the tutorial for `Gradle`:

1. Configure your building environment
2. Choose a pni version to use
3. Add a new source root for generated java classes
4. Create folders for C files
5. Add `pni-api` dependency to your project
6. Add a Gradle subproject for template classes
7. Add `pni-api` dependency to the subproject
8. Add a Gradle task to run the code generator
9. Add `-parameters` compiler argument
10. Write template classes
11. Generate
12. Implement functions in C
13. Compile
14. Load the shared library in Java

### 1. Configure your building environment

Please follow the steps in chapter `How to build`. Installing JDKs, configuring environment variables, installing compiling tools, etc.  
If you are able to run the sample program, then you are ready to go!

---

You will need to make sure you `Gradle` version is at least `8.3`.  
Check and modify `distributionUrl` in `gradle/wrapper/gradle-wrapper.properties` properly.

In your `build.gradle`, add the following snippet:

```groovy
allprojects {
    apply plugin: 'java'

    java {
        sourceCompatibility = '21'
        targetCompatibility = '21'
    }

    tasks.withType(JavaCompile) {
        options.compilerArgs += '--enable-preview'
    }
    tasks.withType(JavaExec) {
        jvmArgs += '--enable-preview'
        jvmArgs += '--enable-native-access=ALL-UNNAMED'
    }
    tasks.withType(Test) {
        jvmArgs += '--enable-preview'
        jvmArgs += '--enable-native-access=ALL-UNNAMED'
    }

    repositories {
        mavenLocal()
        mavenCentral()
    }
}
```

This tells Gradle to build all projects (including subprojects) with JDK 21 and adding `--enable-preview` compiler option, and also specifies the repositories for all projects.

### 2. Choose a pni version to use

The latest `Panama Native Interface` version is `21.0.0.16`  
The version will appear multiple times in `build.gradle`, so you can define a variable at the beginning of the file:

```groovy
buildscript {
    def PNI_VERSION = '21.0.0.16'
    ext.set("PNI_VERSION", PNI_VERSION)

    // more configuration later ...
}

plugins {
    // ...
}

def PNI_VERSION = project.PNI_VERSION
```

The `buildscript` block will be used later.

### 3. Add a new source root for generated java classes

It's recommended to separate generated files and handwritten files, so you may need a new `source root`:

```groovy
sourceSets {
    main {
        java {
            srcDirs = ['src/main/java', 'src/main/generated']
        }
    }
}
```

Now the project has two folders for java source files: `java` and `generated`.  
You will still need to create the folders manually:

```shell
mkdir -p src/main/java
mkdir -p src/main/generated
```

### 4. Create folders for C files

Create a directory `src/main/c` to store handwritten C files and `src/main/c-generated` to store generated C files.

```shell
mkdir -p src/main/c
mkdir -p src/main/c-generated
```

### 5. Add `pni-api` dependency to your project

```groovy
dependencies {
    implementation "io.vproxy:pni-api-jdk21:"+PNI_VERSION
}
```

This module contains necessary classes for `Panama Native Interface` to work,
and also contains useful classes for you to interact with the native world.

### 6. Add a Gradle subproject for template classes

The subproject is used to hold `template classes`, you may name it as `pni-template`.  
If you want to use a different subproject name, make sure you change all names accordingly during this tutorial.

If you are using `IDEA`, it's easy to create a subproject simply by adding a new module.  
Otherwise, you may have to edit `settings.gradle` and create subproject folders manually.

You may refer to the following bash script to create the subproject:

```bash
#!/bin/bash
set -e
set -x

SUBPROJECT="pni-template"

echo "include '$SUBPROJECT'" >> ./settings.gradle
mkdir -p "./$SUBPROJECT/src/main/java"
echo 'compileJava {}' > "./$SUBPROJECT/build.gradle"

tee -a ./build.gradle <<EOF
project(":$SUBPROJECT") {
}
EOF
```

### 7. Add `pni-api` dependency to the subproject

```groovy
dependencies {
    implementation "io.vproxy:pni-api-jdk21:"+PNI_VERSION
}
```

The template classes also require access to types in the `pni-api` module.

You can add it to the `build.gradle` inside the subproject folder, or you can add it in the root `build.gradle` just like this:

```groovy
project(':pni-template') {
    // ...

    dependencies { /* ... */ }
}
```

### 8. Add a Gradle task to run the code generator

At the beginning of the `build.gradle` file, insert the following code snippet into the `buildscript` block:

```groovy
buildscript {
    // ...

    dependencies {
        classpath group: 'io.vproxy', name: 'pni-exec', version: PNI_VERSION
    }
}
```

This snippet adds the code generator into classpath of the Gradle building system, so that you can directly invoke the generator in `build.gradle`.

---

Add the following task inside the subproject:

```groovy
task pniGenerate() {
    dependsOn compileJava

    def workingDir = project.rootProject.rootDir.getAbsolutePath()
    def gen = new io.vproxy.pni.exec.Generator(
        new io.vproxy.pni.exec.CompilerOptions()
            .setClasspath(List.of(workingDir + '/pni-template/build/classes/java/main'))
            .setJavaOutputBaseDirectory(workingDir + '/src/main/generated')
            .setCOutputDirectory(workingDir + '/src/main/c-generated')
            .setCompilationFlag(io.vproxy.pni.exec.CompilationFlag.RELEASE_PNI_H_FILE)
            .setCompilationFlag(io.vproxy.pni.exec.CompilationFlag.RELEASE_PNI_C_FILE)
            .setCompilationFlag(io.vproxy.pni.exec.CompilationFlag.RELEASE_JNI_H_MOCK_FILE)
    )

    doLast {
        gen.generate()
    }
}
```

You can add it to the `build.gradle` inside the subproject folder, or you can add it in the root `build.gradle` just like this:

```groovy
project(':pni-template') {
    task pniGenerate() { /* ... */ }
}
```

### 9. Add `-parameters` compiler argument

In order to retrieve parameter names from Java byte code, the `-parameters` compiler argument should be added explicitly.

Add the following code snippet inside project `pni-template`:

```groovy
compileJava {
    options.compilerArgs += '-parameters'
}
```

### 10. Write template classes

Write template classes in project `pni-template`. See the below section `How to use`.

### 11. Generate

```shell
./gradlew clean pniGenerate
```

Then you will find generated C files in `src/main/c-generated` and generated Java classes in `src/main/generated`

### 12. Implement functions in C

Go to `src/main/c`, write C implementation here.

### 13. Compile

To compile the C files, you will need `pni.h` and `jni.h` in your include search path (`-I` option), and normally you will need to compile with `pni.c`.

It's recommended to use the following compiler flags to release these files:

* `-frelease-pni-h-file[=<output-directory>]`
* `-frelease-pni-c-file[=<output-directory>]`
* `-frelease-jni-h-mock-file[=<output-directory>]`

or programmatically:

```
new CompilerOptions()
    .setCompilationFlag(CompilationFlag.RELEASE_PNI_H_FILE /* , new File(...) */)
    .setCompilationFlag(CompilationFlag.RELEASE_PNI_C_FILE /* , new File(...) */)
    .setCompilationFlag(CompilationFlag.RELEASE_JNI_H_MOCK_FILE /* , new File(...) */)
```

> The `output-directory` defaults to the c output directory (`-h` option).

---

Or you can include these files manually:

You can find `pni.h` [here](https://github.com/vproxy-tools/panama-native-interface/tree/master/api/src/main/c).  
and you can use the mocked `jni.h` [here](https://github.com/vproxy-tools/panama-native-interface/tree/master/api/src/main/c/jnimock),
or you can add `"$JAVA_HOME/include"` and `"$JAVA_HOME/include/$your_platform"` in your include search path instead, which is the traditional way when using `JNI`.

Normally you will need to add [`pni.c`](https://github.com/vproxy-tools/panama-native-interface/tree/master/api/src/main/c/pni.c) to the c file list and compile it into you library.  
You could also build `pni.c` into a standalone library `libpni.so|libpni.dylib|pni.dll`, and call `PanamaUtils.loadLib()` before loading your own library.

You may refer to [make-sample.sh](https://github.com/vproxy-tools/panama-native-interface/blob/master/sample/src/main/c/make-sample.sh) for more info.

### 14. Load the shared library in Java

The shared library should be loaded in Java before any native capability is used:

```java
System.loadLibrary("your-library-name");
```

The shared library file must be placed in `-Djava.library.path` for Java to load.

</details>

## How to use

<details open><summary>Click to reveal/hide</summary>

### 1. Define template classes

For performance concern, simple POJOs are not directly converted to/from their native representations,  
but users can define `template` POJO classes, and then automatically generate both user-friendly and native-friendly Java classes.

You may define all template classes inside one single Java file, they don't have to be public.

```java
@Struct
@Name("mbuf_t")
@AlwaysAligned
abstract class MBuf {        // typedef struct mbuf_t {
    MemorySegment bufAddr;   //     void*    bufAddr;
    @Unsigned int pktLen;    //     uint32_t pktLen;
    @Unsigned int pktOff;    //     uint32_t pktOff;
    @Unsigned int bufLen;    //     uint32_t bufLen;
    UserData userdata;       //     union {
                             //         void*  userdata;
                             //         uint64 udata64;
                             //     };
}                            // } mbuf_t;

@Union(embedded = true)
@AlwaysAligned
abstract class UserData {
    MemorySegment userdata;
    @Unsigned long udata64;
}

@Function
interface SampleFunctions {
    int read(int fd, MBuf buf) throws IOException;
        // int Java_package_name_SampleFunctions_read(PNIEnv_int * env, int32_t fd, mbuf_t * buf);
}
```

### 2. Add methods to template classes

Methods defined in template classes will also automatically result in methods in Java and functions in C.

Their return types or parameters should be pre-supported types or user defined template classes.

You can add throws list to the method if the native code is expected to raise exceptions.

It's recommended to define methods in template classes as `abstract`.

### 3. Generate Java and C code

```shell
java -jar pni.jar \
    -cp 'path1:path2:jar3' \
    -d java_output_base_directory \
    -h c_headers_output_directory
 // -frelease-pni-h-file -frelease-pni-c-file -frelease-jni-h-mock-file
 // see -verbose --help for more info
```

The pni program will scan all classes in classpath then generate Java and C codes.

The generated Java types will share the same package as the template ones,  
the generated C headers will have almost the same format as JNI output, see the following section for more details.

If you have multiple projects, let's say project `A` and project `B`, where template files of `B` depends on
template files of `A`, you can add both projects' classpath to `-cp`, and specify `-F <regexp>` to filter which
class needs to be generated.  
The regexp matches the full name of the **generated** Java class, for example `io\.vproxy\.luajn\.n\..*`.

Some compilation flags can be provided via `-f<flag-name>[=<value>]`. See `-verbose --help` for more info.

---

You may also use the `Generator` class programmatically to achieve the same effect as using the command line tool.  
You may refer to: chapter `How to bundle into a Gradle project`, section `Add a Gradle task to run the code generator`.

### 4. Write native implementation

**if `@Style` is NOT annotated or is `@Style(pni)`**: (`JNI-like` Style Function)

1. take an argument `PNIEnv* env` as the first argument, but with different type variations based on the result type;
2. return `int` where `0` means OK and any other value (usually `-1`) means an exception is thrown;
3. the actual result should be stored in `env->return_` field;

For example:

```c
JNIEXPORT int JNICALL Java_io_vproxy_vfd_posix_GeneralPosix_createIPv4TcpFD
  (PNIEnv_int* env) {
    int sockfd = socket(AF_INET, SOCK_STREAM, 0);
    if (sockfd < 0) {
        return PNIThrowException(env, "java.io.IOException", strerror(errno));
    }
    env->return_ = sockfd;
    return 0;
}
```

If you need to pass `errno` to Java, you can call `PNIStoreErrno(env)`. You can retrieve it from `env.ex().errno()` in Java.

---

**If `@Style(critical)` is annotated**: (`JavaCritical` Style Function)

1. There will be no `PNIEnv` argument.
2. Directly return values.
3. Since the `PNIEnv` is absent, you will NOT be able to use any functionality associated with it, e.g. throwing exceptions from the native function.

For example:

```c
JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_pni_test_Func_writeCritical
  (int32_t fd, void * buf, int32_t off, int32_t len) {
    int n = write(fd, buf + off, len);
    if (n < 0) {
        return -errno;
    }
    return n;
}
```

---

If the Java method is defined inside a class, then the generated C function will have an extra parameter right after `PNIEnv`, providing the `self` pointer.
For `Critical` style functions, `self` will be the first parameter.

If the method's return type requires memory allocation, the generated C function accepts one more argument, as the memory address of that object.
You should set `env->return_ = the_extra_variable` if you need to return the value, or `env->return_ = NULL` if you want to return `NULL`.
For `Critical` style functions, you can simply return the extra variable or return `NULL`.

### 5. Use generated Java types

All generated Java classes have getters for all fields, and setters for all non-embedded fields (struct/union/array),
as well as methods defined in the templates.  
Template interfaces will generated singleton classes.  
All generated classes will NOT extend/implement template classes/interfaces.

The generated Java types have the same names to their templates.  
You can customize name prefix of template or generated types using `-ftype-name-prefix="..."` or `setCompilationFlag(TYPE_NAME_PREFIX, "...")`. If the template types have the specified prefix, then the generated types will discard the prefix. If template types do not have the prefix, then the generated types will prepend the prefix.

If the method's return type requires memory allocation, an extra parameter `Allocator ALLOCATOR` will be added to the last of the arguments list.  
You can release the memory by closing the allocator.

---

It's recommended to use `PooledAllocator.ofPooled()` or `PooledAllocator.ofConcurrentPooled()` whenever possible.
You could also use `PooledAllocator.ofUnsafePooled()` if really needed.  
You can define your own memory pool via `PooledAllocator.setXxxProvider(...)`.

The default behavior for `Pooled` allocators when custom allocator is not present, is the same as `Confined` allocators.  
The default behavior for `ConcurrentPooled` allocators is the same as `Shared` allocators.

</details>

## Type Inheritance

<details open><summary>Click to reveal/hide</summary>

`Panama Native Interface` supports inheritance. You can use Java `extends` keyword in template classes.  
Only a `struct` can extend from another `struct`.  
`union`s are not allowed to inherit nor to be inherited.  
For example:

```java
@Struct
@AlwaysAligned
abstract class BaseClass {
    byte a;
}

@Struct
@AlwaysAligned
abstract class ChildClass extends BaseClass {
    short x;
}

@Struct
@AlwaysAligned
abstract class GrandChildClass extends ChildClass {
    long y;
}
```

The memory layout of `ChildClass` would be:

```c
struct ChildClass {
    BaseClass SUPER;
    short x;
};
```

So basically what the code generator does is to insert the parent struct before the first field.  
Supporting inheritance can make use of Java's object oriented type system, while composition cannot achieve this.

</details>

## Graal Native Image

<details open><summary>Click to reveal/hide</summary>

`GraalVM for JDK 21` supports building native image with Panama support.

You can add a flag to the `pni` program to generate a `Feature` implmentation, which is required by the native image generation process.

```shell
java -jar pni.jar <...> -fgraal-native-image-feature=<feature-class-name>
# you might also want to add argument -fgraal-c-entrypoint-literal-upcall, see below descriptions for more info
```

or programmatically:

```groovy
new CompilerOptions()
    .setCompilationFlag(io.vproxy.pni.exec.CompilationFlag.GRAAL_NATIVE_IMAGE_FEATURE, "$featureClassName")
 // you migh also want to add the flag:
 // .setCompilationFlag(io.vproxy.pni.exec.CompilationFlag.GRAAL_C_ENTRYPOINT_LITERAL_UPCALL)
 // see below descriptions for more info
```

To compile your project with the `Feature` class, you should use `GraalVM for JDK 21` instead of a traditional JDK.  
You could also use the native image sdk: `org.graalvm.sdk:nativeimage:+` instead of changing the JDK.

Another (maybe better) way of managing the dependencies is to use [the mock version graal sdk](https://github.com/vproxy-tools/graal-sdk-mock):

* for compiling, add dependency `compileOnly 'io.vproxy:graal-sdk-mock-nativeimage:+'`
* for running, add dependency `runtimeOnly 'io.vproxy:graal-sdk-mock-runtime:+'`

The mock library provides all necessary types and members for `Panama Native Interface` generated graal related classes.  
Detailed information can be found in [the repo](https://github.com/vproxy-tools/graal-sdk-mock).

</details>

## Graal Native Image Upcall

<details open><summary>Click to reveal/hide</summary>

As for now `(2023-10-09)` the graal native-image doesn't support Panama upcall yet. But `Panama Native Interface` provides the upcall support
based on graal c native features:  
Add compilation flag `-fgraal-c-entrypoint-literal-upcall` on the command line, or call
`.setCompilationFlag(CompilationFlag.GRAAL_C_ENTRYPOINT_LITERAL_UPCALL)` programmatically to enable this feature.

To build the native image, you may use the following command:

```shell
native-image -jar <jar-file> \
             --features=<feature-class-name> \
             --enable-preview \
             --enable-native-access=ALL-UNNAMED \
             --no-fallback \
             -O3 -march=native \
             -o <binary-name>
```

See `sampleNativeImage` task in `build.gradle` for more info.

You will also need to define `PNI_GRAAL=1` when compiling, which will enable Graal related functions.  
Note that some functions are defined using `static inline`, so you must compile all related files with `PNI_GRAAL=1`.

</details>

## Call Java from C

<details open><summary>Click to reveal/hide</summary>

Panama provides a way for C to invoke Java methods. `Panama Native Interface` provides multiple ways to simplify this process.

* `@Upcall` template interface
* `PanamaUtils.defineCFunction`
* CallSite and PNIFunc
* PNIRef

### `@Upcall` template interface

You can use `@Upcall` template interfaces to generate upcall functions.

The `pni` program will generate the following files:

* a `.h` file, containing the function declarations
* a `.c` file, containing the function definitions, which calls the `Panama upcall stub` function pointer
* a Java class, containing static fields of function memory addresses (`MemorySegment`)
* a Java interface, defining the method signature for you to implement

You must call `TheGeneratedClass.setImpl(yourImpl)` before using these upcall functions,
otherwise the program will print an error message and exit when the functions get called.

### `PanamaUtils.defineCFunction`

You can use `PanamaUtils.defineCFunction` or `PanamaUtils.defineCFunctionByName` to define C functions easily.

1. Store an `Arena` globally, which is used to allocate memory for the defined function.
2. Define a `static` Java method, and make it public.
3. Call `PanamaUtils.defineCFunctionByName(arena, YouClassName.class, "yourMethodName")`

Done!

Note: only `primitive types` and `MemorySegment` are allowed to be used as the method parameter types and return types.

For `native-image`s, you should use `GraalUtils.defineCFunctionXxx` methods, and store the `CFunctionLiteral` in a `static final` field, and the class which stores `static final` fields must be initialized at build time.  
It would be better to use `@Upcall` template interface instead, which had managed everything for you.

### CallSite and PNIFunc

`Panama Native Interface` also provides another encapsulation, which allows you to pass lambda expressions to C.

Use `PNIFunc<T>` as a method parameter in template classes, where `T` must be a `Struct` or `Union` or `java.lang.Void` or `PNIRef<U>`.

The generated Java method uses `CallSite<T>` as its parameter.  
It is a **functional interface**,
whose function signature is `(T) -> int`, where `T` allows you to share variables between Java and C,
while the returned `int` provides the execution result.

On the C side, the function pointer is wrapped inside a `PNIFunc * func` variable.  
To invoke the function, use `int result = PNIFuncInvoke(func, &value);`

You may store the `PNIFunc` object and use it later, you can even invoke it on a new thread.
As a result, you **MUST** release the object when you finished using it: `PNIFuncRelease(func);`

The `PNIFunc` struct has a union field `union { void * userdata; uint64_t udata64; }` for you to store your own data in it.  
This is useful for example when you store the `PNIFunc*` in `epoll_event.data.ptr`.  
You can allocate some extra memory when creating a `PNIFunc` by calling `T.Func.of(<lambda>, new Options().setUserdataByteSize(...))`.  
The extra memory will be stored inside the `userdata` pointer field automatically. In this way, the memory gets released with the func.

If any error thrown from the CallSite, the PNIFunc will catch it and print the exception,
then return `((int32_t) PNIFuncInvokeExceptionCaught)` to C.

You can add `@Raw` annotation on the parameter to set the generated Java method parameter to `PNIFunc<T>` instead of `CallSite<T>`.

You can use `PNIFunc<T>` in method parameters, return types, or fields.

You can create `PNIFunc<T>` using `PNIFunc.VoidFunc.of(CallSite<Void>)` or `T.Func.of(CallSite<T>)` or `PNIRef.Func.of(CallSite<T>)`.  
You can also release a `PNIFunc<T>` using `func.close()` on the Java side.

### PNIRef

To share Java objects with C, you can use `PNIRef<T>`: `PNIRef.of(object)`.

You can release the `PNIRef<T>` on the Java side: `ref.close()`, or release it on the C side: `PNIRefRelease(ref)`.

You will not be able to manipulate the Java object on the C side obviously,
but you can pass it around and use it as an argument in an upcall function or store it in a field.

Also the `PNIRef` provides a similar `userdata` union as the `PNIFunc` does.  
You can call `PNIRef.of(obj, new Options().setUserdataByteSize(...))`, the behavior is exactly the same as `PNIFunc`.

</details>

## Annotations

<details open><summary>Click to reveal/hide</summary>

### Entrypoint

* `@Struct`: generate C struct from the marked class, you can set `@Struct(skip=true)` to skip generating the type definition (this is useful if the type is already defined in another C header file).
* `@Union`: generate C union from the marked class, you can set `@Union(skip=true)` to skip generating the type definition, while setting `@Union(embedded=true)` will make it embedded into other types automatically.
* `@Function`: generate functions from the marked interface.
* `@Upcall`: generate upcall functions from the marked interface.

> If a `union` is already defined in another C header file, you should use `@Union(skip=true)`. If it's not pre-defined and you want it to be embedded into another struct, you should use `@Union(embedded=true)`.  
  Mixing both will have the same effect of only using `@Union(embedded=true)`.

### Performance Concern

* `@Trivial`: make a MethodHandle `trivial`. See `Linker.Option#isTrivial()` for more info.
* `@Align`: define the minimum alignment bytes. You can set `@Align(packed=true)` to disable padding.
  This annotation has the same effect as setting `__attribute__((aligned(N)))` or `__attribute__((packed))` in `GCC`.
* `@AlwaysAligned`: assumes that the annotated class or field to be always aligned. This will result in a Java `ValueLayout` without `_UNALIGNED` suffix. A jmh benchmark shows that accessing "manually aligned" fields has the same performance as accessing "unaligned" fields, and is a little bit slower than "aligned" fields in Panama.  
  This annotation is not the default behavior because adding it means that you will not be allowed to put the type on a random memory location.  
  The generated C code will not be affected by this annotation. The generator calculates the paddings only based on type info and `@Align` annotation, and decide to generate packed or non-packed structs, with or without explicit paddings.

### Enhance Java Types

* `@Pointer`: make a custom type field to be a pointer. The default behavior without `@Pointer` annotation, is embedding the type into the parent struct.
* `@Len`: define the element count of an array, or the native memory length of a string (memory length, not string length).
* `@Unsigned`: make an integer type `unsinged`.
* `@Raw`: convert to raw form for native invocation. See the below section `@Raw Annotation` for more info.
* `@PointerOnly`: this annotation is only effective during validation phase. The marked class cannot have fields, the type should only be used as a pointer. However the generated Java class or C struct/union will stay the same as they were.
* `@BitField`: mark a byte/short/int/long field to be a bit field. For example: `@BitField(name={"a", "b"}, bit={1, 1}) @Unsigned byte x`, defines two bit fields `uint8_t a : 1` and `uint8_t b : 1`, and automatically generates an anonymous padding to fillup the field's type (`uint8_t : 6`).  
  **WARNING**: bit fields' memory layout is **NOT** specified in C standard and is **NOT** compiler/platform portable, use with caution!!!
* `@Sizeof`: specify the minimum byte size of the type. This is useful for `@PointerOnly` types and `skip=true` types when only part of fields are specified in Java.  
  For example:  
  ```java
  @Struct(skip = true)
  @Include("msquic.h")
  @Name("QUIC_ADDR")
  @PointerOnly
  @Sizeof("QUIC_ADDR") // you may also write multi-line statements here, and you can include header files as well
  public abstract class QuicAddr {
  }
  ```  
  With the help of `@Sizeof`, you can allocate memory for `QuicAddr` from Java and pass it to native.  
  Note: You CANNOT use a `@Sizeof` class for a non-pointer field unless it's in a union or is the last field in a struct.  
  Also, the `@Sizeof` annotation is infectious, if `class A` has a non-pointer field whose class is annotated with `@Sizeof`, then `class A` must be annotated with `Sizeof` as well.
* `@NativeType`: specify the native type of the field or parameter.
* `@NativeReturnType`: specify the native return type of the method.  
  The `@NativeType` and `@NativeReturnType` are useful for defining the real type for a `void*` pointer.

### Convention

* `@Name`: define the native name.
* `@Style`: when set to `@Style(critical)`, pni will generate native functions without `PNIEnv`. You can directly use `return` to return values to Java. However, since the `PNIEnv` is absent, you will not be able to use any functionality associated with it, for example, throwing exceptions from the C code.
* `@NoAlloc`: generate functions without `Allocator`, even if the return type might require one.

### Other

* `@Include`: add `#include ...` when generating the header file. This is useful if some type is defined in another C header file.  
  `@Include("...")` will generate `#include "..."`, while `@Include("<...>")` will generate `#include <...>`.
* `@Impl`: write C function definition in Java. See the following example:  
  ```java
  @Impl(
        include = {"<unistd.h>"},
        // language="c"
        c = """
            int ret = write(fd, buf + off, len);
            if (ret < 0) {
                return PNIThrowException(env, "java.io.Exception", strerror(errno));
            }
            env->return_ = ret;
            return 0;
            """
  )
  int write(int fd, @Raw ByteBuffer buf, int off, int len) throws IOException;
  ```  
  When `@Impl` is specified, an extra header file with `.impl.h` suffix will be generated along with the normal `.h` header.
  You can include the `.impl.h` header in your C file.  
  Note that, the comment `// launuage="c"` will let JetBrains IDEA highlight the text block with C syntax.

</details>

## `@Raw` Annotation

<details open><summary>Click to reveal/hide</summary>

Annotate the data type to be converted to its raw form. You can only mark method parameters with this annotation.

* `ByteBuffer`: will be converted to `MemorySegment`.
  This has the same effect as calling `MemorySegment.ofBuffer(...)`
  after setting `ByteBuffer.position()` to 0 and `ByteBuffer.limit()` to `ByteBuffer.capacity()`,
  without actually modifying these properties.
* `T[]`: arrays will be converted to their raw form without the `PNIBuf` wrapper. There will be no length info, so you might need to pass in their length manually.
* `PNIRef<T>`: if without `@Raw` annotation, template `PNIRef<T>` params will result in `T` in generated java params.  
  With `@Raw` annotation, template `PNIRef<T>` params will result in `PNIRef<T>` in generated java params.
* `PNIFunc<T>`: use `PNIFunc<T>` in the generated Java method parameters, instead of the default `CallSite<T>`.

</details>

## Type correspondence

<details open><summary>Click to reveal/hide</summary>

| Java                                | `@Unsigned` | `@Pointer` | `@Len` | C Field           | C Function Param | C Extra Return Param | C `PNIEnv_${type}` | Generated Java Type | Generated Layout                    |
|-------------------------------------|-------------|------------|--------|-------------------|------------------|----------------------|--------------------|---------------------|-------------------------------------|
| int                                 | No          | -          | -      | `int32_t`         | `int32_t`        | -                    | `int`              | int                 | `JAVA_INT`                          |
| int                                 | Yes         | -          | -      | `uint32_t`        | `uint32_t`       | -                    | `int`              | int                 | `JAVA_INT`                          |
| long                                | No          | -          | -      | `int64_t`         | `int64_t`        | -                    | `long`             | long                | `JAVA_LONG`                         |
| long                                | Yes         | -          | -      | `uint64_t`        | `uint64_t`       | -                    | `long`             | long                | `JAVA_LONG`                         |
| short                               | No          | -          | -      | `int16_t`         | `int16_t`        | -                    | `short`            | short               | `JAVA_SHORT`                        |
| short                               | Yes         | -          | -      | `uint16_t`        | `uint16_t`       | -                    | `short`            | short               | `JAVA_SHORT`                        |
| byte                                | No          | -          | -      | `int8_t`          | `int8_t`         | -                    | `byte`             | byte                | `JAVA_BYTE`                         |
| byte                                | Yes         | -          | -      | `uint8_t`         | `uint8_t`        | -                    | `byte`             | byte                | `JAVA_BYTE`                         |
| float                               | -           | -          | -      | `float`           | `float`          | -                    | `float`            | float               | `JAVA_FLOAT`                        |
| double                              | -           | -          | -      | `double`          | `double`         | -                    | `double`           | double              | `JAVA_DOUBLE`                       |
| boolean                             | -           | -          | -      | `uint8_t`         | `uint8_t`        | -                    | `bool`             | boolean             | `JAVA_BOOLEAN`                      |
| char                                | -           | -          | -      | `uint16_t`        | `uint16_t`       | -                    | `char`             | char                | `JAVA_CHAR`                         |
| String                              | -           | -          | No     | `char *`          | `char *`         | -                    | `pointer`          | PNIString           | `ADDRESS`                           |
| String                              | -           | -          | Yes    | `char x[len]`     | -                | -                    | -                  | String              | `sequenceLayout(len, JAVA_BYTE)`    |
| MemorySegment                       | -           | -          | -      | `void *`          | `void *`         | -                    | `pointer`          | MemorySegment       | `ADDRESS`                           |
| ByteBuffer                          | -           | -          | -      | `PNIBuf`          | `PNIBuf *`       | `PNIBuf *`           | `buf`              | ByteBuffer          | `PNIBuf.LAYOUT`                     |
| ByteBuffer (`@Raw`)                 | -           | -          | -      | -                 | `char *`         | -                    | -                  | ByteBuffer          | -                                   |
| Struct/Union                        | -           | No         | -      | `Type`            | -                | -                    | -                  | Type                | `Type.LAYOUT`                       |
| Struct/Union                        | -           | Yes        | -      | `Type *`          | `Type *`         | `Type *`             | `pointer`          | Type                | `ADDRESS`                           |
| int[]                               | No          | `*`        | No     | `PNIBuf_int`      | `PNIBuf_int *`   | `PNIBuf_int *`       | `buf_int`          | IntArray            | `PNIBuf.LAYOUT`                     |
| int[]                               | Yes         | `*`        | No     | `PNIBuf_uint`     | `PNIBuf_uint *`  | `PNIBuf_uint *`      | `buf_uint`         | IntArray            | `PNIBuf.LAYOUT`                     |
| long[]                              | No          | `*`        | No     | `PNIBuf_long`     | `PNIBuf_long *`  | `PNIBuf_long *`      | `buf_long`         | LongArray           | `PNIBuf.LAYOUT`                     |
| long[]                              | Yes         | `*`        | No     | `PNIBuf_ulong`    | `PNIBuf_ulong *` | `PNIBuf_ulong *`     | `buf_ulong`        | LongArray           | `PNIBuf.LAYOUT`                     |
| short[]                             | No          | `*`        | No     | `PNIBuf_short`    | `PNIBuf_short *` | `PNIBuf_short *`     | `buf_short`        | ShortArray          | `PNIBuf.LAYOUT`                     |
| short[]                             | Yes         | `*`        | No     | `PNIBuf_ushort`   | `PNIBuf_ushort *`| `PNIBuf_ushort *`    | `buf_ushort`       | ShortArray          | `PNIBuf.LAYOUT`                     |
| byte[]                              | No          | `*`        | No     | `PNIBuf_byte`     | `PNIBuf_byte *`  | `PNIBuf_byte *`      | `buf_byte`         | MemorySegment       | `PNIBuf.LAYOUT`                     |
| byte[]                              | Yes         | `*`        | No     | `PNIBuf_ubyte`    | `PNIBuf_ubyte *` | `PNIBuf_ubyte *`     | `buf_ubyte`        | MemorySegment       | `PNIBuf.LAYOUT`                     |
| float[]                             | -           | `*`        | No     | `PNIBuf_float`    | `PNIBuf_float *` | `PNIBuf_float *`     | `buf_float`        | FloatArray          | `PNIBuf.LAYOUT`                     |
| double[]                            | -           | `*`        | No     | `PNIBuf_double`   | `PNIBuf_double *`| `PNIBuf_double *`    | `buf_double`       | DoubleArray         | `PNIBuf.LAYOUT`                     |
| boolean[]                           | -           | `*`        | No     | `PNIBuf_bool`     | `PNIBuf_bool *`  | `PNIBuf_bool *`      | `buf_bool`         | BoolArray           | `PNIBuf.LAYOUT`                     |
| char[]                              | -           | `*`        | No     | `PNIBuf_char`     | `PNIBuf_char *`  | `PNIBuf_char *`      | `buf_char`         | CharArray           | `PNIBuf.LAYOUT`                     |
| MemorySegment[]                     | -           | `*`        | No     | `PNIBuf_ptr`      | `PNIBuf_ptr *`   | `PNIBuf_ptr *`       | `buf_ptr`          | PointerArray        | `PNIBuf.LAYOUT`                     |
| Type[]                              | -           | `*`        | No     | `PNIBuf_Type`     | `PNIBuf_Type *`  | `PNIBuf_Type *`      | `buf_Type`         | Type.Array          | `PNIBuf.LAYOUT`                     |
| int[]     (`@Raw`)                  | No          | `*`        | No     | -                 | `int32_t *`      | -                    | -                  | IntArray            | -                                   |
| int[]     (`@Raw`)                  | Yes         | `*`        | No     | -                 | `uint32_t *`     | -                    | -                  | IntArray            | -                                   |
| long[]    (`@Raw`)                  | No          | `*`        | No     | -                 | `int64_t *`      | -                    | -                  | LongArray           | -                                   |
| long[]    (`@Raw`)                  | Yes         | `*`        | No     | -                 | `uint64_t *`     | -                    | -                  | LongArray           | -                                   |
| short[]   (`@Raw`)                  | No          | `*`        | No     | -                 | `int16_t *`      | -                    | -                  | ShortArray          | -                                   |
| short[]   (`@Raw`)                  | Yes         | `*`        | No     | -                 | `uint16_t *`     | -                    | -                  | ShortArray          | -                                   |
| byte[]    (`@Raw`)                  | No          | `*`        | No     | -                 | `void *`         | -                    | -                  | MemorySegment       | -                                   |
| byte[]    (`@Raw`)                  | Yes         | `*`        | No     | -                 | `uint8_t *`      | -                    | -                  | MemorySegment       | -                                   |
| float[]   (`@Raw`)                  | -           | `*`        | No     | -                 | `float *`        | -                    | -                  | FloatArray          | -                                   |
| double[]  (`@Raw`)                  | -           | `*`        | No     | -                 | `double *`       | -                    | -                  | DoubleArray         | -                                   |
| boolean[] (`@Raw`)                  | -           | `*`        | No     | -                 | `uint8_t *`      | -                    | -                  | BoolArray           | -                                   |
| char[]    (`@Raw`)                  | -           | `*`        | No     | -                 | `uint16_t *`     | -                    | -                  | CharArray           | -                                   |
| MemorySegment[] (`@Raw`)            | -           | `*`        | No     | -                 | `void **`        | -                    | -                  | PointerArray        | -                                   |
| Type[]    (`@Raw`)                  | -           | `*`        | No     | -                 | `Type *`         | -                    | -                  | Type.Array          | -                                   |
| int[]                               | No          | -          | Yes    | `int32_t  x[len]` | -                | -                    | -                  | IntArray            | `sequenceLayout(len, JAVA_INT)`     |
| int[]                               | Yes         | -          | Yes    | `uint32_t x[len]` | -                | -                    | -                  | IntArray            | `sequenceLayout(len, JAVA_INT)`     |
| long[]                              | No          | -          | Yes    | `int64_t  x[len]` | -                | -                    | -                  | LongArray           | `sequenceLayout(len, JAVA_LONG)`    |
| long[]                              | Yes         | -          | Yes    | `uint64_t x[len]` | -                | -                    | -                  | LongArray           | `sequenceLayout(len, JAVA_LONG)`    |
| short[]                             | No          | -          | Yes    | `int16_t  x[len]` | -                | -                    | -                  | ShortArray          | `sequenceLayout(len, JAVA_SHORT)`   |
| short[]                             | Yes         | -          | Yes    | `uint16_t x[len]` | -                | -                    | -                  | ShortArray          | `sequenceLayout(len, JAVA_SHORT)`   |
| byte[]                              | No          | -          | Yes    | `int8_t   x[len]` | -                | -                    | -                  | MemorySegment       | `sequenceLayout(len, JAVA_BYTE)`    |
| byte[]                              | Yes         | -          | Yes    | `uint8_t  x[len]` | -                | -                    | -                  | MemorySegment       | `sequenceLayout(len, JAVA_BYTE)`    |
| float[]                             | -           | -          | Yes    | `float    x[len]` | -                | -                    | -                  | FloatArray          | `sequenceLayout(len, JAVA_FLOAT)`   |
| double[]                            | -           | -          | Yes    | `double   x[len]` | -                | -                    | -                  | DoubleArray         | `sequenceLayout(len, JAVA_DOUBLE)`  |
| boolean[]                           | -           | -          | Yes    | `uint8_t  x[len]` | -                | -                    | -                  | BoolArray           | `sequenceLayout(len, JAVA_BOOLEAN)` |
| char[]                              | -           | -          | Yes    | `uint16_t x[len]` | -                | -                    | -                  | CharArray           | `sequenceLayout(len, JAVA_CHAR)`    |
| MemorySegment[]                     | -           | -          | Yes    | `void *   x[len]` | -                | -                    | -                  | PointerArray        | `sequenceLayout(len, ADDRESS)`      |
| Type[]                              | -           | -          | Yes    | `Type     x[len]` | -                | -                    | -                  | Type.Array          | `sequenceLayout(len, Type.LAYOUT)`  |
| `PNIFunc<T>` (field or return)      | -           | -          | -      | `PNIFunc *`       | `PNIFunc *`      | -                    | `func`             | `PNIFunc<T>`        | `ADDRESS`                           |
| `PNIFunc<T>` (param)                | -           | -          | -      | -                 | `PNIFunc *`      | -                    | -                  | `CallSite<T>`       | -                                   |
| `PNIFunc<PNIRef<T>>` (param)        | -           | -          | -      | -                 | `PNIFunc *`      | -                    | -                  | `CallSite<T>`       | -                                   |
| `PNIFunc<T>` (param `@Raw`)         | -           | -          | -      | -                 | `PNIFunc *`      | -                    | -                  | `PNIFunc<T>`        | -                                   |
| `PNIFunc<PNIRef<T>>` (param `@Raw`) | -           | -          | -      | -                 | `PNIFunc *`      | -                    | -                  | `PNIFunc<T>`        | -                                   |
| `PNIRef<T>` (field or return)       | -           | -          | -      | `PNIRef *`        | `PNIRef *`       | -                    | `ref`              | `PNIRef<T>`         | `ADDRESS`                           |
| `PNIRef<T>` (param)                 | -           | -          | -      | -                 | `PNIRef *`       | -                    | -                  | `T`                 | -                                   |
| `PNIRef<T>` (param `@Raw`)          | -           | -          | -      | -                 | `PNIRef *`       | -                    | -                  | `PNIRef<T>`         | -                                   |

`*`: Both `Yes` and `No`.  
`-`: Cannot mark the annotation.

> Note that the return types and parameters are always considered to be marked with `@Pointer` when possible.  

Any other combination except the above table is disallowed.

</details>

## Pooled Allocators

<details open><summary>Click to reveal/hide</summary>

`Panama Native Interface` does not provide built-in pooled allocators implementation, but allow you to register your own impl into the framework. You can use `PooledAllocator.ofXxx` functions to retrieve a pooled allocator.

There are three kinds of _Pooled Allocators_:

1. `ofPooled`: the simplest pooled allocator, which doesn't have to provide multi-thread support.
2. `ofConcurrentPooled`: must provide multi-thread support.
3. `ofUnsafePooled`: usually wraps around unsafe, or provided by a native allocator implementation, such as `jemalloc`, the memory must not be managed by the JVM (because JVM managed MemorySegments and Arenas may have certain limitations).

You can register you implementation via `PooledAllocator.setXxxProvider`:

1. `setPooledAllocatorProvider`
2. `setConcurrentPooledAllocatorProvider`
3. `setUnsafePooledAllocatorProvider`

</details>

## Limitations

<details open><summary>Click to reveal/hide</summary>

* This project has a pre assumption: `sizeof(void*)` is 8 bytes.
  In other words, you can only use this project on a 64bit processor.
  This shouldn't be a problem because there's very rare chance that you would run Java on a 32bit platform.
* When you throw an exception from native code, you should ensure that the
  exception type name char array does not require releasing.
* Only primitive types or `MemorySegment` or custom types can be used to generate arrays,
  and the arrays can only be 1 dimension. To use 2 or more dimension arrays,
  the only way to achieve this is to calculate the array length and use
  1 dimension array instead.
* You should avoid using "all upper case" type names or variables.
  The extra params or local variables in the generated code are "all upper case",
  and naming collisions of these variables are not checked during the validation phase.
  This shouldn't be a problem, because normally people won't define
  "all upper case" type names or member fields.
* Bit fields are not compiler/platform portable. `Panama Native Interface` provides limited bit fields support,
  it allows you to define bit fields, but you must define them on an integer type. The total bit must not exceed
  the integer type's memory size, and a padding will automatically be generated if the bit fields' total bit
  is less than the integer type memory size.  
  You must use bit fields with caution. The most common use case of bit fields is to define switches, and this should
  work well usually, but it's **NOT** guarenteed. \[1\]
* A JMH benchmark shows that **disabling** inlining of `ConcurrentHashMap#get` can improve performance when retrieving values from `ObjectHolder`. However the benchmark only shows performance of the certain case. You may try to add or remove jvm option `-XX:CompileCommand=dontinline,io.vproxy.pni.impl.ForceNoInlineConcurrentLongMap::*` and bench your own code.

> \[1\] C11: An implementation may allocate any addressable storage unit large enough to hold a bit-field. If enough space remains, a bit-field that immediately follows another bit-field in a structure shall be packed into adjacent bits of the same unit. If insufficient space remains, whether a bit-field that does not fit is put into the next unit or overlaps adjacent units is implementation-defined. The order of allocation of bit-fields within a unit (high-order to low-order or low-order to high-order) is implementation-defined. The alignment of the addressable storage unit is unspecified.

</details>

## Real World Examples

<details open><summary>Click to reveal/hide</summary>

* [vproxy](http://github.com/wkgcass/vproxy): LoadBalancer and virtual networking on Java, migrated from the old `JNI` to `PNI`, using the `JNI` style C functions.
* [luajn](https://github.com/vproxy-tools/luajn): A Lua/C/Java binding, built upon `PNI`, using the `Critical` style C functions.
* [msquic-java](https://github.com/wkgcass/msquic-java): MsQuic for Java, built upon `PNI`, heavily uses `Struct(skip=true)`.

</details>
