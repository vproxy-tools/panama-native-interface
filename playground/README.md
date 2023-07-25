# Panama Native Interface Playground

An empty project for playing and trying new features.

Gradle and C compiling script are pre-configured, following the instructions below to quickly try and test PNI features.

## How to use

1. Follow instructions in the project root's `README.md` to configure your building environment.
2. Add template classes inside `src/main/template/io/vproxy/pni/playground`
3. Run `./gradlew generatePlayground` to generate Java classes and C headers.
4. Add and edit file `src/main/c/playground.c`, which should contain implementations of native functions.
5. Add `Main.java` inside `src/main/java/io/vproxy/pni/playground` and load library using `System.loadLibrary("pniplayground");`
6. Run `./gradlew runPlayground` to re-generate, compile, and execute the program.
