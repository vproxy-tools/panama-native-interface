# Panama Native Interface

## Abstract

A way of using `Project Panama` that is completely different from using `jextract`.

The `jextract` parses C code and generates Java code, while `Panama Native Interface` reads Java byte code and generates C code as well as user-friendly and native-friendly Java code.

This approach is similar to how `JNI(Java Native Interface)` works, so this project is named as `PNI(Panama Native Interface)`.

## How to use

### 1. Define template classes

For performance concern, simple POJOs are not directly converted to/from their native representations,  
but users can define `template` POJO classes, and then automatically generate both user-friendly and native-friendly Java classes.

You may define all template classes inside one single Java file, they don't have to be public.

```java
@Struct
@Name("mbuf_t")
abstract class PNIMBuf {     // typedef struct {
    MemorySegment bufAddr;   //     void*    bufAddr;
    @Unsigned int pktLen;    //     uint32_t pktLen;
    @Unsigned int pktOff;    //     uint32_t pktOff;
    @Unsigned int bufLen;    //     uint32_t bufLen; uint8_t __padding_after_bufLen[4];
    PNIUserData userdata;    //     union {
                             //         void*  userdata;
                             //         uint64 udata64;
                             //     };
}                            // } __attribute__((packed)) mbuf_t;

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

All native functions are in the same pattern:

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

If the Java method is defined inside a class, then the generated C function will have an extra parameter right after `PNIEnv`, providing the `this` pointer.

If the method's return type requires memory allocation, the generated C function accepts one more argument, as the memory address of that object.  
You should set `env->return_ = the_extra_variable` if you need to return the value, or `env->return_ = NULL` if you want to return `NULL`.

### 5. Use generated Java types

All generated Java classes have getters for all fields, and setters for all non-embedded fields (struct/union/array),
as well as methods defined in the templates.  
Methods defined inside template interfaces will be generated in singleton classes.  
All generated classes will NOT extend/implement template classes/interfaces.

The generated Java types have similar names to their templates.  
If the template type name starts with `PNI`, then the generated type will remove `PNI` prefix, otherwise adding the `PNI` prefix.

If the method's return type requires memory allocation, an extra parameter `Allocator ALLOCATOR` will be added to the last of the arguments list.  
You can release the memory after accessing the returned object by closing the allocator.

`io.vproxy.pni.Allocator` can be constructed with `Arena` or `MemoryAllocator`.

## Call Java from C

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

## Annotations

### Entrypoint

* `@Struct`: generate C struct from the marked class, you can set `@Struct(skip=true)` to skip generating the type definition (this is useful if the type is already defined in another C header file).
* `@Union`: generate C union from the marked class, you can set `@Union(skip=true)` to skip generating the type definition, while setting `@Union(embedded=true)` will make it embedded into other types automatically.
* `@Function`: generate functions from the marked interface.

> If a `union` is already defined in another C header file, you should use `@Union(skip=true)`. If it's not pre-defined and you want it to be embedded to another struct, you should use `@Union(embedded=true)`.  
  Mixing both will have the same effect of only using `@Union(embedded=true)`.

### Performance Concern

* `@Trivial`: make a MethodHandle `trivial`. See `Linker.Option#isTrivial()` for more info.
* `@Align`: define the alignment bytes, default is 8. Setting this to a value `<= 1` will disable memory padding.

### Enhance Java Types

* `@Pointer`: make a custom type field to be a pointer. The default behavior without `@Pointer` annotation, is embedding the type into the parent struct.
* `@Len`: define the length of an array or the native memory length of a string (memory length, not string length).
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
  When `@Impl` is specified, an extra header file with `.impl.h` suffix will be generated alone with the normal `.h` header.
  You can include the `.impl.h` header in your C file.  
  Note that, the comment `// launuage="c"` will let JetBrains IDEA highlight the text block with C syntax.

## Type correspondence

| Java          | `@Unsigned` | `@Pointer` | `@Len` | C Field           | C Function Param | C Extra Return Param | C `PNIEnv_${type}` | Generated Java Type | Generated Layout                    |
|---------------|-------------|------------|--------|-------------------|------------------|----------------------|--------------------|---------------------|-------------------------------------|
| int           | No          | -          | -      | `int32_t`         | `int32_t`        | -                    | `int`              | int                 | `JAVA_INT`                          |
| int           | Yes         | -          | -      | `uint32_t`        | `uint32_t`       | -                    | `int`              | int                 | `JAVA_INT`                          |
| long          | No          | -          | -      | `int64_t`         | `int64_t`        | -                    | `long`             | long                | `JAVA_LONG`                         |
| long          | Yes         | -          | -      | `uint64_t`        | `uint64_t`       | -                    | `long`             | long                | `JAVA_LONG`                         |
| short         | No          | -          | -      | `int16_t`         | `int16_t`        | -                    | `short`            | short               | `JAVA_SHORT`                        |
| short         | Yes         | -          | -      | `uint16_t`        | `uint16_t`       | -                    | `short`            | short               | `JAVA_SHORT`                        |
| byte          | No          | -          | -      | `int8_t`          | `int8_t`         | -                    | `byte`             | byte                | `JAVA_BYTE`                         |
| byte          | Yes         | -          | -      | `uint8_t`         | `uint8_t`        | -                    | `byte`             | byte                | `JAVA_BYTE`                         |
| float         | -           | -          | -      | `float`           | `float`          | -                    | `float`            | float               | `JAVA_FLOAT`                        |
| double        | -           | -          | -      | `double`          | `double`         | -                    | `double`           | double              | `JAVA_DOUBLE`                       |
| boolean       | -           | -          | -      | `uint8_t`         | `uint8_t`        | -                    | `bool`             | boolean             | `JAVA_BOOLEAN`                      |
| char          | -           | -          | -      | `uint16_t`        | `uint16_t`       | -                    | `char`             | char                | `JAVA_CHAR`                         |
| String        | -           | -          | No     | `char *`          | `char *`         | -                    | `pointer`          | String              | `ADDRESS`                           |
| String        | -           | -          | Yes    | `char x[len]`     | -                | -                    | -                  | String              | `sequenceLayout(len, JAVA_BYTE)`    |
| MemorySegment | -           | -          | -      | `void *`          | `void *`         | -                    | `pointer`          | MemorySegment       | `ADDRESS`                           |
| ByteBuffer    | -           | -          | -      | `PNIBuf`          | `PNIBuf *`       | `PNIBuf *`           | `pointer`          | ByteBuffer          | `PNIBuf.LAYOUT`                     |
| Struct/Union  | -           | No         | -      | `Type`            | -                | -                    | -                  | Type                | `Type.LAYOUT`                       |
| Struct/Union  | -           | Yes        | -      | `Type *`          | `Type *`         | `Type *`             | `pointer`          | Type                | `PNIBuf.LAYOUT`                     |
| int[]         | `*`         | `*`        | No     | `PNIBuf`          | `PNIBuf *`       | `PNIBuf *`           | `pointer`          | IntArray            | `PNIBuf.LAYOUT`                     |
| long[]        | `*`         | `*`        | No     | `PNIBuf`          | `PNIBuf *`       | `PNIBuf *`           | `pointer`          | LongArray           | `PNIBuf.LAYOUT`                     |
| short[]       | `*`         | `*`        | No     | `PNIBuf`          | `PNIBuf *`       | `PNIBuf *`           | `pointer`          | ShortArray          | `PNIBuf.LAYOUT`                     |
| byte[]        | `*`         | `*`        | No     | `PNIBuf`          | `PNIBuf *`       | `PNIBuf *`           | `pointer`          | MemorySegment       | `PNIBuf.LAYOUT`                     |
| float[]       | -           | `*`        | No     | `PNIBuf`          | `PNIBuf *`       | `PNIBuf *`           | `pointer`          | FloatArray          | `PNIBuf.LAYOUT`                     |
| double[]      | -           | `*`        | No     | `PNIBuf`          | `PNIBuf *`       | `PNIBuf *`           | `pointer`          | DoubleArray         | `PNIBuf.LAYOUT`                     |
| boolean[]     | -           | `*`        | No     | `PNIBuf`          | `PNIBuf *`       | `PNIBuf *`           | `pointer`          | BoolArray           | `PNIBuf.LAYOUT`                     |
| char[]        | -           | `*`        | No     | `PNIBuf`          | `PNIBuf *`       | `PNIBuf *`           | `pointer`          | CharArray           | `PNIBuf.LAYOUT`                     |
| Type[]        | -           | `*`        | No     | `PNIBuf`          | `PNIBuf *`       | `PNIBuf *`           | `pointer`          | RefArray<Type>      | `PNIBuf.LAYOUT`                     |
| int[]         | No          | -          | Yes    | `int32_t  x[len]` | -                | -                    | -                  | IntArray            | `sequenceLayout(len, JAVA_INT)`     |
| int[]         | Yes         | -          | Yes    | `uint32_t x[len]` | -                | -                    | -                  | IntArray            | `sequenceLayout(len, JAVA_INT)`     |
| long[]        | No          | -          | Yes    | `int64_t  x[len]` | -                | -                    | -                  | LongArray           | `sequenceLayout(len, JAVA_LONG)`    |
| long[]        | Yes         | -          | Yes    | `uint64_t x[len]` | -                | -                    | -                  | LongArray           | `sequenceLayout(len, JAVA_LONG)`    |
| short[]       | No          | -          | Yes    | `int16_t  x[len]` | -                | -                    | -                  | ShortArray          | `sequenceLayout(len, JAVA_SHORT)`   |
| short[]       | Yes         | -          | Yes    | `uint16_t x[len]` | -                | -                    | -                  | ShortArray          | `sequenceLayout(len, JAVA_SHORT)`   |
| byte[]        | No          | -          | Yes    | `int8_t   x[len]` | -                | -                    | -                  | MemorySegment       | `sequenceLayout(len, JAVA_BYTE)`    |
| byte[]        | Yes         | -          | Yes    | `uint8_t  x[len]` | -                | -                    | -                  | MemorySegment       | `sequenceLayout(len, JAVA_BYTE)`    |
| float[]       | -           | -          | Yes    | `float    x[len]` | -                | -                    | -                  | FloatArray          | `sequenceLayout(len, JAVA_FLOAT)`   |
| double[]      | -           | -          | Yes    | `double   x[len]` | -                | -                    | -                  | DoubleArray         | `sequenceLayout(len, JAVA_DOUBLE)`  |
| boolean[]     | -           | -          | Yes    | `uint8_t  x[len]` | -                | -                    | -                  | BoolArray           | `sequenceLayout(len, JAVA_BOOLEAN)` |
| char[]        | -           | -          | Yes    | `uint16_t x[len]` | -                | -                    | -                  | CharArray           | `sequenceLayout(len, JAVA_CHAR)`    |
| Type[]        | -           | -          | Yes    | `Type     x[len]` | -                | -                    | -                  | RefArray<Type>      | `sequenceLayout(len, Type.LAYOUT)`  |
| CallSite<T>   | -           | -          | -      | -                 | `PNIFunc *`      | -                    | -                  | T.Func              | -                                   |

`*`: Both `Yes` and `No`.  
`-`: Cannot mark the annotation.

> Note that the return types and parameters are always considered to be marked with `@Pointer` when possible.  

Any other combination except the above table is disallowed.

## `@Raw` Annotation

Annotate the data type to be converted to its raw form.

* `ByteBuffer`: This annotation currently only applies to `ByteBuffer` parameters, which will be converted to `MemorySegment`.
  This has the same effect as setting `ByteBuffer.position()` to 0 and `ByteBuffer.limit()` to `ByteBuffer.capacity()`,
  without actually modifying the buffer.

## Limitations

* When defining a template method that returns a String,
  the generated C code will provide `char * cs` as the output,
  while the generated Java code will extract the UTF-8 `String s` from it.
  Because `cs` is neither returned from the method nor allocated by user code,
  there will be no opportunity for you to release its memory.
  Therefore, you should only use template methods that return String
  if the strings returned from C do not require releasing,
  such as those returned by `strerror(errno)`.  
  Similarly, when you throw an exception from native code, you should ensure that the
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
