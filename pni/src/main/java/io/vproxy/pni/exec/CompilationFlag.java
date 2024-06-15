package io.vproxy.pni.exec;

import io.vproxy.pni.exec.internal.Utils;

import java.io.File;
import java.util.function.Function;
import java.util.function.Predicate;

public class CompilationFlag<T> {
    public static final CompilationFlag<String> GRAAL_NATIVE_IMAGE_FEATURE = new CompilationFlag<>(
        "graal-native-image-feature", null, "GraalNativeImageFeature",
        Utils::isValidClassName, s -> s,
        "generate graal native-image feature class, you can set the full classname of the feature class");
    public static final CompilationFlag<Void> GRAAL_C_ENTRYPOINT_LITERAL_UPCALL = new CompilationFlag<>(
        "graal-c-entrypoint-literal-upcall", null, "",
        String::isBlank, s -> null,
        "use graal CEntryPointLiteral for upcall, this is useful when native-image doesn't support panama upcall");
    public static final CompilationFlag<File> RELEASE_PNI_H_FILE = new CompilationFlag<>(
        "release-pni-h-file", null, "",
        fun(s -> {
            if (s.isEmpty()) return null;
            if (new File(s).isDirectory()) return null;
            else return s + " does not exist or is not a directory";
        }), s -> s.isEmpty() ? null : new File(s),
        "release the latest pni.h to the C output directory");
    public static final CompilationFlag<File> RELEASE_PNI_C_FILE = new CompilationFlag<>(
        "release-pni-c-file", null, "",
        fun(s -> {
            if (s.isEmpty()) return null;
            if (new File(s).isDirectory()) return null;
            else return s + " does not exist or is not a directory";
        }), s -> s.isEmpty() ? null : new File(s),
        "release the latest pni.c to the C output directory");
    public static final CompilationFlag<File> RELEASE_JNI_H_MOCK_FILE = new CompilationFlag<>(
        "release-jni-h-mock-file", null, "",
        fun(s -> {
            if (s.isEmpty()) return null;
            if (new File(s).isDirectory()) return null;
            else return s + " does not exist or is not a directory";
        }), s -> s.isEmpty() ? null : new File(s),
        "release the latest mock version jni.h to the C output directory");
    public static final CompilationFlag<String> TYPE_NAME_PREFIX = new CompilationFlag<>(
        "type-name-prefix", "", "",
        pred(s -> Utils.isValidName(s, false)), s -> s,
        "set the type name prefix. if template class has the prefix, then generated class will remove the prefix, otherwise prepend the prefix"
    );
    public static final CompilationFlag<Boolean> ALWAYS_ALIGNED = new CompilationFlag<>(
        "always-aligned", "false", "true",
        pred(s -> "true".equals(s) || "false".equals(s)), "true"::equals,
        "set @AlwaysAligned for all template classes. you can use @AlwaysAligned(false) to make them non-aligned even when this flag is set to true"
    );
    public static final CompilationFlag<Boolean> DISABLE_ALLOW_HEAP_ACCESS = new CompilationFlag<>(
        "disable-allow-heap-access", "false", "true",
        pred(s -> "true".equals(s) || "false".equals(s)), "true"::equals,
        "disable \"allow-heap-access\" even if it's enabled by @LinkerOption.Critical(allowHeapAccess=true)"
    );

    public final String name;
    public final String defaultValueWhenNotSet;
    public final String defaultValue;
    public final Function<String, String> validate;
    public final Function<String, T> convert;
    public final String description;

    public CompilationFlag(String name, String defaultValueWhenNotSet, String defaultValue, Predicate<String> validate, Function<String, T> convert, String description) {
        this(name, defaultValueWhenNotSet, defaultValue, (Function<String, String>) s -> {
            if (validate.test(s)) return null; // means no error
            else return ""; // means empty error message
        }, convert, description);
    }

    public CompilationFlag(String name, String defaultValueWhenNotSet, String defaultValue, Function<String, String> validate, Function<String, T> convert, String description) {
        this.name = name;
        this.defaultValueWhenNotSet = defaultValueWhenNotSet;
        this.defaultValue = defaultValue;
        this.validate = validate;
        this.convert = convert;
        this.description = description;
    }

    public static CompilationFlag<?> searchForCompilationFlagByName(String name) {
        for (var v : values()) {
            if (v.name.equals(name)) {
                return v;
            }
        }
        return null;
    }

    private static final CompilationFlag<?>[] VALUES = new CompilationFlag[]{
        GRAAL_NATIVE_IMAGE_FEATURE,
        GRAAL_C_ENTRYPOINT_LITERAL_UPCALL,
        RELEASE_PNI_H_FILE,
        RELEASE_PNI_C_FILE,
        RELEASE_JNI_H_MOCK_FILE,
        TYPE_NAME_PREFIX,
        ALWAYS_ALIGNED,
        DISABLE_ALLOW_HEAP_ACCESS,
    };

    public static CompilationFlag<?>[] values() {
        return VALUES;
    }

    // syntax helpers
    private static Predicate<String> pred(Predicate<String> f) {
        return f;
    }

    private static Function<String, String> fun(Function<String, String> f) {
        return f;
    }
}
