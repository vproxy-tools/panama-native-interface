# Panama Native Interface

## Abstract

A way of using `Project Panama` that is completely different from using `jextract`.

The `jextract` parses C code and generates Java code, while `Panama Native Interface` reads Java byte code and generates C code as well as user-friendly and native-friendly Java code.

This approach is similar to how `JNI(Java Native Interface)` works, so this project is named as `PNI(Panama Native Interface)`.

## How to build

<details><summary>Click to reveal</summary>

### 1. Install JDKs

You need JDK `21` **AND** any one of JDK `11-17` to build the project.  
The JDK `21` is used to compile all projects except `pni`, and another JDK is used to run `Gradle` and compile project `pni`.

> This is due to the `Gradle` limitation. Everytime a JDK ships a new version,
> `Gradle` has to wait for all its components to be compatible with the new JDK
> before it can officially announce a release.  
> At the time writing this paragraph (`2023-07-27`), `Gradle` doesn't support running on Java 20,
> which was released on `2023-03-21`.  
> As a result, it's better to run `Gradle` on an older JDK and compile `Panama` projects with a new JDK.

### 2. Configure Environment Variables

* Configure `JAVA_HOME` to one of JDK `11-17`.
* Configure `JAVA_HOME_21` to your JDK 21.
* Configure `PATH` to make sure `javac` points to one of JDK `11-17`

After configuring the environment variables, you might need to restart your terminal/ide, and stop current Gradle daemons using `./gradlew --stop`

### 3. Install GCC

You will need `GCC` to compile with the generated headers. Any `GCC` that supports `gnu99` or `c11` should be fine.

If you are using `Windows`, it's recommended to use `MinGW` to work with this project.

### 4. Build

```shell
./gradlew clean shadowJar
```

You will find an executable jar in `build/libs/pni.jar`

```shell
java -jar build/libs/pni.jar -version
java -jar build/libs/pni.jar -help
```

### 5. Sample

There's a sample program, which is an _http server_ listening on `:80`.

```shell
./gradlew clean runSample

curl 127.0.0.1:80
```

### 6. Test

```shell
./gradlew clean runAcceptanceTest
```

</details>

## How to bundle into a Gradle project

<details><summary>Click to reveal</summary>

It's recommended to use `Gradle`, otherwise you will have to generate classes using the `pni` command line tool by your self.

Here's the tutorial when using `Gradle`:

1. Add source root
2. Create folders for generated C headers
3. Add `pni-api` dependency
4. Add a Gradle subproject
5. Add `pni-api` dependency to the subproject
6. Add a task to generate files
7. Add `-parameters` compiler argument
8. Write template classes
9. Generate
10. Implement functions in C
11. Compile
12. Load library in Java

> This tutorial can run smoothly on Linux or macOS or MinGW.

### 1. Add source root

It's recommended to separate generated files and handwritten files, so you may want to add a new `source root`.

```groovy
sourceSets {
    main {
        java {
            srcDirs = ['src/main/java', 'src/main/generated']
        }
    }
}
```

You now have two folders to contain java source files: `java` and `generated`.

### 2. Create folders for generated C headers

Create a directory `src/main/c` to store C files and `src/main/c-generated` to store generated headers.

```shell
mkdir -p src/main/c
mkdir -p src/main/c-generated
```

### 3. Add `pni-api` dependency

Add dependency to your project:

```groovy
dependencies {
    implementation "io.vproxy:pni-api:$version"
}
```

### 4. Add a Gradle subproject

The subproject is used to hold template classes, you may name it as `pni-template`

If you are using `IDEA`, it's easy to create a subproject by simply adding a new module.

Otherwise, you will have to edit `settings.gradle` manually, you may refer to the script:

```bash
#!/bin/bash
set -e

SUBPROJECT="pni-template"

echo "include '$SUBPROJECT'" >> ./settings.gradle
mkdir -p "./$SUBPROJECT/src/main/java"
echo 'compileJava {}' >> "./$SUBPROJECT/build.gradle"

echo 'project(":'$SUBPROJECT'") {}' >> "./build.gradle"
```

### 5. Add `pni-api` dependency to the subproject

Add pni api dependency to the subproject:

```groovy
dependencies {
    implementation "io.vproxy:pni-api:$version"
}
```

### 6. Add a task to generate files

Add the following task to your subproject.

```groovy
def PROJECT_DIR = '' // FIXME: Change this variable to your main project directory
                     // FIXME: If it's the root project, leave it empty.
                     // FIXME: If it's a subproject, set the value to 'sub-project-dir/'
def PNI_JAR_PATH = './misc/pni.jar'
                     // FIXME: Change this variable to the location of pni.jar
                     // FIXME: the location is relevant to your root project directory.

task pniClean(type: Exec) {
    workingDir project.rootProject.rootDir.getAbsolutePath() + '/' + PROJECT_DIR + 'src/main/'
    commandLine 'bash', '-c', 'rm -rf c-generated/* && rm -rf generated/*'
}
task pniGenerate(type: Exec) {
    workingDir project.rootProject.rootDir.getAbsolutePath()
    commandLine('java', '-jar', PNI_JAR_PATH,
        '-cp', "pni-template/build/classes/java/main",
        '-d', "$PROJECT_DIR/src/main/generated",
        '-h', "$PROJECT_DIR/src/main/c-generated")

    dependsOn pniClean
    dependsOn compileJava
}
```

### 7. Add `-parameters` compiler argument

In order to retrieve parameter names from Java byte code, you will need to explicity add `-parameters` compiler argument.  
Add the following code snippet to project `pni-template`.

```groovy
compileJava {
    options.compilerArgs += '-parameters'
}
```

### 8. Write template classes

Write template classes in project `pni-template`. See the below section `How to use`.

### 9. Generate

```shell
./gradlew clean pniGenerate
```

Then you will find C headers in `src/main/c-generated` and Java classes in `src/main/generated`

### 10. Implement functions in C

Go to `src/main/c`, write your C implementation there.

### 11. Compile

To compile the C files, you will need `pni.h` and `jni.h` in your include search path (`-I` option).

You can find `pni.h` [here](https://github.com/vproxy-tools/panama-native-interface/tree/master/api/src/main/c).  
and you can find `jni.h` in `"$JAVA_HOME/include"` and `"$JAVA_HOME/include/$your_platform"`.

You may refer to [make-sample.sh](https://github.com/vproxy-tools/panama-native-interface/blob/master/sample/src/main/c/make-sample.sh) for more info.

### 12. Load library in Java

Finally, you need to load the shared library in Java:

```java
System.loadLibrary("your-library-name");
```

You must ensure your library placed in `-Djava.library.path`.

</details>

## How to use

<details><summary>Click to reveal</summary>

### 1. Define template classes

For performance concern, simple POJOs are not directly converted to/from their native representations,  
but users can define `template` POJO classes, and then automatically generate both user-friendly and native-friendly Java classes.

You may define all template classes inside one single Java file, they don't have to be public.

```java
@Struct
@Name("mbuf_t")
abstract class PNIMBuf {     // typedef PNI_PACK(struct, mbuf_t, {
    MemorySegment bufAddr;   //     void*    bufAddr;
    @Unsigned int pktLen;    //     uint32_t pktLen;
    @Unsigned int pktOff;    //     uint32_t pktOff;
    @Unsigned int bufLen;    //     uint32_t bufLen; /* padding */ uint64_t :32;
    PNIUserData userdata;    //     union {
                             //         void*  userdata;
                             //         uint64 udata64;
                             //     };
}                            // }) mbuf_t;

@Union(embedded = true)
abstract class PNIUserData {
    MemorySegment userdata;
    @Unsigned long udata64;
}

@Function
interface PNISampleFunctions {
    int read(int fd, PNIMBuf buf) throws IOException;
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
```

The pni program will scan all classes in classpath then generate Java and C codes.

The generated Java types will share the same package as the template ones,  
the generated C headers will have almost the same format as JNI output, see the following section for more details.

### 4. Write native implementation

All native functions are in the same pattern **if `@Critical` is NOT annotated**:

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

You should set `env->return_ = the_extra_variable` if you need to return the value, or `env->return_ = NULL` if you want to return `NULL`.  
If you need to pass `errno` to Java, you can call `PNIStoreErrno(env)`. You can retrieve it from `env.ex().errno()` in Java.

**If `@Critical` is annotated**:

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

If the Java method is defined inside a class, then the generated C function will have an extra parameter right after `PNIEnv`, providing the `this` pointer.

If the method's return type requires memory allocation, the generated C function accepts one more argument, as the memory address of that object.

### 5. Use generated Java types

All generated Java classes have getters for all fields, and setters for all non-embedded fields (struct/union/array),
as well as methods defined in the templates.  
Methods defined inside template interfaces will be generated in singleton classes.  
All generated classes will NOT extend/implement template classes/interfaces.

The generated Java types have similar names to their templates.  
If the template type name starts with `PNI`, then the generated type will remove `PNI` prefix, otherwise adding the `PNI` prefix.

If the method's return type requires memory allocation, an extra parameter `Allocator ALLOCATOR` will be added to the last of the arguments list.  
You can release the memory after accessing the returned object by closing the allocator.

`io.vproxy.pni.Allocator` can be constructed with `Arena` or `SegmentAllocator`.

</details>

## Call Java from C

<details><summary>Click to reveal</summary>

Panama provides a way for C to invoke Java methods. `Panama Native Interface` provides a simple encapsulation for this feature
and makes the coding much easier.

Use `CallSite<T>` as a method parameter in template classes, where `T` must be a `Struct` or `Union` or `java.lang.Void`.

The generated Java method also uses `CallSite<T>` as its parameter.  
It is a **functional interface**,
whose function signature is `(T) -> int`, where `T` allows you to share variables between Java and C,
while the returned `int` provides the execution result.

On the C side, the function pointer is wrapped inside a `PNIFunc * func` variable.  
To invoke the function, use `int result = PNIFuncInvoke(func, &value);`

You may store the `PNIFunc` object and use it later, you can even invoke it on a new thread.
As a result, you **MUST** release the object when you finished using it: `PNIFuncRelease(func);`

The `PNIFunc` struct has a field `void * userdata` for you to store you own data in it.
This is useful for example when you store the `PNIFunc*` in `epoll_event.data.ptr`.

</details>

## Annotations

<details><summary>Click to reveal</summary>

### Entrypoint

* `@Struct`: generate C struct from the marked class, you can set `@Struct(skip=true)` to skip generating the type definition (this is useful if the type is already defined in another C header file).
* `@Union`: generate C union from the marked class, you can set `@Union(skip=true)` to skip generating the type definition, while setting `@Union(embedded=true)` will make it embedded into other types automatically.
* `@Function`: generate functions from the marked interface.

> If a `union` is already defined in another C header file, you should use `@Union(skip=true)`. If it's not pre-defined and you want it to be embedded into another struct, you should use `@Union(embedded=true)`.  
  Mixing both will have the same effect of only using `@Union(embedded=true)`.

### Performance Concern

* `@Trivial`: make a MethodHandle `trivial`. See `Linker.Option#isTrivial()` for more info.
* `@Align`: define the minimum alignment bytes. You can set `@Align(packed=true)` to disable padding.
  This annotation has the same effect as setting `__attribute__((aligned(N)))` or `__attribute__((packed))` in `GCC`.
* `@Critical`: generate native functions without `PNIEnv`. You can directly use `return` to return values to Java. However, since the `PNIEnv` is absent, you will not be able to use any functionality associated with it, for example, throwing exceptions from the C code.

### Enhance Java Types

* `@Pointer`: make a custom type field to be a pointer. The default behavior without `@Pointer` annotation, is embedding the type into the parent struct.
* `@Len`: define the element count of an array, or the native memory length of a string (memory length, not string length).
* `@Unsigned`: make an integer type `unsinged`.
* `@Raw`: convert to raw form for native invocation. See the below section `@Raw Annotation` for more info.

### Convention

* `@Name`: define the native name.

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

<details><summary>Click to reveal</summary>

Annotate the data type to be converted to its raw form. You can only mark method parameters with this annotation.

* `ByteBuffer`: will be converted to `MemorySegment`.
  This has the same effect as calling `MemorySegment.ofBuffer(...)`
  after setting `ByteBuffer.position()` to 0 and `ByteBuffer.limit()` to `ByteBuffer.capacity()`,
  without actually modifying these properties.
* `T[]`: arrays will be converted to their raw form without the `PNIBuf` wrapper. There will be no length info, so you might need to pass in their length manually.

</details>

## Type correspondence

<details><summary>Click to reveal</summary>

| Java                | `@Unsigned` | `@Pointer` | `@Len` | C Field           | C Function Param | C Extra Return Param | C `PNIEnv_${type}` | Generated Java Type | Generated Layout                    |
|---------------------|-------------|------------|--------|-------------------|------------------|----------------------|--------------------|---------------------|-------------------------------------|
| int                 | No          | -          | -      | `int32_t`         | `int32_t`        | -                    | `int`              | int                 | `JAVA_INT`                          |
| int                 | Yes         | -          | -      | `uint32_t`        | `uint32_t`       | -                    | `int`              | int                 | `JAVA_INT`                          |
| long                | No          | -          | -      | `int64_t`         | `int64_t`        | -                    | `long`             | long                | `JAVA_LONG`                         |
| long                | Yes         | -          | -      | `uint64_t`        | `uint64_t`       | -                    | `long`             | long                | `JAVA_LONG`                         |
| short               | No          | -          | -      | `int16_t`         | `int16_t`        | -                    | `short`            | short               | `JAVA_SHORT`                        |
| short               | Yes         | -          | -      | `uint16_t`        | `uint16_t`       | -                    | `short`            | short               | `JAVA_SHORT`                        |
| byte                | No          | -          | -      | `int8_t`          | `int8_t`         | -                    | `byte`             | byte                | `JAVA_BYTE`                         |
| byte                | Yes         | -          | -      | `uint8_t`         | `uint8_t`        | -                    | `byte`             | byte                | `JAVA_BYTE`                         |
| float               | -           | -          | -      | `float`           | `float`          | -                    | `float`            | float               | `JAVA_FLOAT`                        |
| double              | -           | -          | -      | `double`          | `double`         | -                    | `double`           | double              | `JAVA_DOUBLE`                       |
| boolean             | -           | -          | -      | `uint8_t`         | `uint8_t`        | -                    | `bool`             | boolean             | `JAVA_BOOLEAN`                      |
| char                | -           | -          | -      | `uint16_t`        | `uint16_t`       | -                    | `char`             | char                | `JAVA_CHAR`                         |
| String              | -           | -          | No     | `char *`          | `char *`         | -                    | `pointer`          | PNIString           | `ADDRESS`                           |
| String              | -           | -          | Yes    | `char x[len]`     | -                | -                    | -                  | String              | `sequenceLayout(len, JAVA_BYTE)`    |
| MemorySegment       | -           | -          | -      | `void *`          | `void *`         | -                    | `pointer`          | MemorySegment       | `ADDRESS`                           |
| ByteBuffer          | -           | -          | -      | `PNIBuf`          | `PNIBuf *`       | `PNIBuf *`           | `buf`              | ByteBuffer          | `PNIBuf.LAYOUT`                     |
| ByteBuffer (`@Raw`) | -           | -          | -      | -                 | `char *`         | -                    | -                  | ByteBuffer          | -                                   |
| Struct/Union        | -           | No         | -      | `Type`            | -                | -                    | -                  | Type                | `Type.LAYOUT`                       |
| Struct/Union        | -           | Yes        | -      | `Type *`          | `Type *`         | `Type *`             | `pointer`          | Type                | `ADDRESS`                           |
| int[]               | `*`         | `*`        | No     | `PNIBuf`          | `PNIBuf *`       | `PNIBuf *`           | `buf`              | IntArray            | `PNIBuf.LAYOUT`                     |
| long[]              | `*`         | `*`        | No     | `PNIBuf`          | `PNIBuf *`       | `PNIBuf *`           | `buf`              | LongArray           | `PNIBuf.LAYOUT`                     |
| short[]             | `*`         | `*`        | No     | `PNIBuf`          | `PNIBuf *`       | `PNIBuf *`           | `buf`              | ShortArray          | `PNIBuf.LAYOUT`                     |
| byte[]              | `*`         | `*`        | No     | `PNIBuf`          | `PNIBuf *`       | `PNIBuf *`           | `buf`              | MemorySegment       | `PNIBuf.LAYOUT`                     |
| byte[] (`@Raw`)     | `*`         | `*`        | No     | -                 | `char *`         | -                    | -                  | MemorySegment       | -                                   |
| float[]             | -           | `*`        | No     | `PNIBuf`          | `PNIBuf *`       | `PNIBuf *`           | `buf`              | FloatArray          | `PNIBuf.LAYOUT`                     |
| double[]            | -           | `*`        | No     | `PNIBuf`          | `PNIBuf *`       | `PNIBuf *`           | `buf`              | DoubleArray         | `PNIBuf.LAYOUT`                     |
| boolean[]           | -           | `*`        | No     | `PNIBuf`          | `PNIBuf *`       | `PNIBuf *`           | `buf`              | BoolArray           | `PNIBuf.LAYOUT`                     |
| char[]              | -           | `*`        | No     | `PNIBuf`          | `PNIBuf *`       | `PNIBuf *`           | `buf`              | CharArray           | `PNIBuf.LAYOUT`                     |
| Type[]              | -           | `*`        | No     | `PNIBuf`          | `PNIBuf *`       | `PNIBuf *`           | `buf`              | Type.Array          | `PNIBuf.LAYOUT`                     |
| int[]               | No          | -          | Yes    | `int32_t  x[len]` | -                | -                    | -                  | IntArray            | `sequenceLayout(len, JAVA_INT)`     |
| int[]               | Yes         | -          | Yes    | `uint32_t x[len]` | -                | -                    | -                  | IntArray            | `sequenceLayout(len, JAVA_INT)`     |
| long[]              | No          | -          | Yes    | `int64_t  x[len]` | -                | -                    | -                  | LongArray           | `sequenceLayout(len, JAVA_LONG)`    |
| long[]              | Yes         | -          | Yes    | `uint64_t x[len]` | -                | -                    | -                  | LongArray           | `sequenceLayout(len, JAVA_LONG)`    |
| short[]             | No          | -          | Yes    | `int16_t  x[len]` | -                | -                    | -                  | ShortArray          | `sequenceLayout(len, JAVA_SHORT)`   |
| short[]             | Yes         | -          | Yes    | `uint16_t x[len]` | -                | -                    | -                  | ShortArray          | `sequenceLayout(len, JAVA_SHORT)`   |
| byte[]              | No          | -          | Yes    | `int8_t   x[len]` | -                | -                    | -                  | MemorySegment       | `sequenceLayout(len, JAVA_BYTE)`    |
| byte[]              | Yes         | -          | Yes    | `uint8_t  x[len]` | -                | -                    | -                  | MemorySegment       | `sequenceLayout(len, JAVA_BYTE)`    |
| float[]             | -           | -          | Yes    | `float    x[len]` | -                | -                    | -                  | FloatArray          | `sequenceLayout(len, JAVA_FLOAT)`   |
| double[]            | -           | -          | Yes    | `double   x[len]` | -                | -                    | -                  | DoubleArray         | `sequenceLayout(len, JAVA_DOUBLE)`  |
| boolean[]           | -           | -          | Yes    | `uint8_t  x[len]` | -                | -                    | -                  | BoolArray           | `sequenceLayout(len, JAVA_BOOLEAN)` |
| char[]              | -           | -          | Yes    | `uint16_t x[len]` | -                | -                    | -                  | CharArray           | `sequenceLayout(len, JAVA_CHAR)`    |
| Type[]              | -           | -          | Yes    | `Type     x[len]` | -                | -                    | -                  | Type.Array          | `sequenceLayout(len, Type.LAYOUT)`  |
| CallSite<T>         | -           | -          | -      | -                 | `PNIFunc *`      | -                    | -                  | CallSite<T>         | -                                   |

`*`: Both `Yes` and `No`.  
`-`: Cannot mark the annotation.

> Note that the return types and parameters are always considered to be marked with `@Pointer` when possible.  

Any other combination except the above table is disallowed.

</details>

## Limitations

<details><summary>Click to reveal</summary>

* This project has a pre assumption: `sizeof(void*)` is 8 bytes.
  In other words, you can only use this project on a 64bit processor.
  This shouldn't be a problem because there's very rare chance that you would run Java on a 32bit platform.
* When you throw an exception from native code, you should ensure that the
  exception type name char array does not require releasing.
* Only primitive types or custom types can be used to generate arrays,
  and the arrays can only be 1 dimension. To use 2 or more dimension arrays,
  the only way to achieve this is to calculate the array length and use
  1 dimension array instead.
* You should avoid using "all upper case" type names or variables.
  The extra params or local variables in the generated code are "all upper case",
  and naming collisions of these variables are not checked during the validation phase.
  This shouldn't be a problem, because normally people won't define
  "all upper case" type names or member fields.
* The `CallSite<T>` is only allowed in parameters, you cannot use it in struct fields.  
  However you can store it in a field inside your C code and use it later, even using it on a new thread.

</details>
